package org.factoriaf5.project_inside_out.view;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import org.factoriaf5.project_inside_out.controller.MomentController;
import org.factoriaf5.project_inside_out.dto.MomentRequestDTO;
import org.factoriaf5.project_inside_out.model.Emotion;
import org.factoriaf5.project_inside_out.repository.MapMomentRepository;
import org.factoriaf5.project_inside_out.service.MomentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConsoleViewTest {
    private InputStream originalIn;
    private PrintStream originalOut;
    private ByteArrayOutputStream output;
    private MomentService service;

    @BeforeEach
    void setUp() {
        originalIn = System.in;
        originalOut = System.out;

        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    void shouldShowMenuAndExit() {
        ConsoleView view = createViewWithInput("5\n");
        view.start();
        String consoleText = output.toString(StandardCharsets.UTF_8);
        assertTrue(consoleText.contains("Mi Diario"));
        assertTrue(consoleText.contains("Hasta luego"));
    }

    @Test
    void shouldAddMomentFromConsole() {
        ConsoleView view = createViewWithInput("""
                1
                Mi primer recuerdo
                Un dia tranquilo
                18/08/2026
                1
                5
                """);
        view.start();
        String consoleText = output.toString(StandardCharsets.UTF_8);
        assertTrue(consoleText.contains("Momento vivido anadido correctamente."));
        assertTrue(service.getAllMoments().size() == 1);
    }

    @Test
    void shouldShowAllMoments() {
        ConsoleView view = createViewWithInput("2\n5\n");
        service.addMoment(new MomentRequestDTO("Dia feliz", "Paseo por el parque", Emotion.JOY,
                LocalDate.of(2026, 8, 18)));
        view.start();
        String consoleText = output.toString(StandardCharsets.UTF_8);
        assertTrue(consoleText.contains("Lista de momentos vividos:"));
        assertTrue(consoleText.contains("Titulo: Dia feliz"));
        assertTrue(consoleText.contains("Descripcion: Paseo por el parque"));
        assertTrue(consoleText.contains("Emocion: Alegria"));
    }

    @Test
    void shouldShowEmptyMessage() {
        ConsoleView view = createViewWithInput("2\n5\n");
        view.start();
        String consoleText = output.toString(StandardCharsets.UTF_8);
        assertTrue(consoleText.contains("No hay momentos guardados."));
    }

    @Test
    void shouldDeleteMoment() {
        ConsoleView view = createViewWithInput("3\n1\n5\n");
        service.addMoment(new MomentRequestDTO("Recuerdo para borrar", "Descripcion", Emotion.SADNESS,
                LocalDate.of(2026, 8, 18)));
        view.start();
        String consoleText = output.toString(StandardCharsets.UTF_8);
        assertTrue(consoleText.contains("Momento eliminado correctamente."));
        assertTrue(service.getAllMoments().isEmpty());
    }

    @Test
    void shouldFilterByEmotion() {
        ConsoleView view = createViewWithInput("4\n1\n1\n5\n");
        service.addMoment(new MomentRequestDTO("Recuerdo alegre", "Descripcion alegre", Emotion.JOY,
                LocalDate.of(2026, 8, 18)));
        service.addMoment(new MomentRequestDTO("Recuerdo triste", "Descripcion triste", Emotion.SADNESS,
                LocalDate.of(2026, 8, 19)));
        view.start();
        String consoleText = output.toString(StandardCharsets.UTF_8);
        assertTrue(consoleText.contains("Titulo: Recuerdo alegre"));
        assertFalse(consoleText.contains("Titulo: Recuerdo triste"));
    }

    @Test
    void shouldFilterByMonth() {
        ConsoleView view = createViewWithInput("4\n2\n8\n5\n");
        service.addMoment(new MomentRequestDTO("Recuerdo de agosto", "Descripcion agosto", Emotion.JOY,
                LocalDate.of(2026, 8, 18)));
        service.addMoment(new MomentRequestDTO("Recuerdo de julio", "Descripcion julio", Emotion.NOSTALGIA,
                LocalDate.of(2026, 7, 18)));
        view.start();
        String consoleText = output.toString(StandardCharsets.UTF_8);
        assertTrue(consoleText.contains("Titulo: Recuerdo de agosto"));
        assertFalse(consoleText.contains("Titulo: Recuerdo de julio"));
    }

    @Test
    void shouldAskDateAgainWhenFormatIsWrong() {
        ConsoleView view = createViewWithInput("""
                1
                Recuerdo con fecha
                Descripcion
                18/08-2026
                18/08/2026
                1
                5
                """);

        view.start();
        String consoleText = output.toString(StandardCharsets.UTF_8);
        assertTrue(consoleText.contains("Formato de fecha no valido. Intentalo otra vez."));
        assertTrue(consoleText.contains("Momento vivido anadido correctamente."));
    }

    private ConsoleView createViewWithInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
        service = new MomentService(new MapMomentRepository());
        MomentController controller = new MomentController(service);
        return new ConsoleView(controller);
    }
}
