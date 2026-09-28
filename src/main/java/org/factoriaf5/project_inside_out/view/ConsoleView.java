package org.factoriaf5.project_inside_out.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.List;
import org.factoriaf5.project_inside_out.controller.MomentController;
import org.factoriaf5.project_inside_out.dto.MomentRequestDTO;
import org.factoriaf5.project_inside_out.dto.MomentResponseDTO;
import org.factoriaf5.project_inside_out.model.Emotion;

public class ConsoleView {

    private MomentController controller;
    private Scanner scanner;

    public ConsoleView(MomentController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {
            showMenu();
            int option = scanner.nextInt();

            if (option == 1) {
                addMoment();
            } else if (option == 2) {
                showAllMoments();
            } else if (option == 3) {
                deleteMoment();
            } else if (option == 4) {
                filterMoments();
            } else if (option == 5) {
                running = false;
                System.out.println("Hasta luego");
            } else {
                System.out.println("Opcion pendiente de implementar.");
            }
        }

    }

    private void showMenu() {
        System.out.println("""
                Mi Diario

                1. Anadir momento
                2. Ver todos los momentos
                3. Eliminar momento
                4. Filtrar momentos
                5. Salir

                Seleccione una opcion:
                """);
    }

    private void addMoment() {
        scanner.nextLine();

        String title = askRequiredText(
                "Ingrese el titulo:",
                "Titulo no valido. Intentalo otra vez.");
        String description = askRequiredText(
                "Ingrese la descripcion:",
                "Descripcion no valida. Intentalo otra vez.");

        LocalDate momentDate = askMomentDate();

        Emotion emotion = askEmotion();

        MomentRequestDTO dto = new MomentRequestDTO(title, description, emotion, momentDate);

        controller.addMoment(dto);

        System.out.println("Momento vivido anadido correctamente.");
    }

    private String askRequiredText(String prompt, String errorMessage) {
        String text;

        do {
            System.out.println(prompt);
            text = scanner.nextLine();

            if (text.isBlank()) {
                System.out.println(errorMessage);
            }
        } while (text.isBlank());

        return text;
    }

    private LocalDate askMomentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate momentDate = null;

        while (momentDate == null) {
            System.out.println("Ingrese la fecha (dd/MM/yyyy):");
            String dateText = scanner.nextLine();

            try {
                momentDate = LocalDate.parse(dateText, formatter);
            } catch (DateTimeParseException exception) {
                System.out.println("Formato de fecha no valido. Intentalo otra vez.");
            }
        }

        return momentDate;
    }

    private Emotion askEmotion() {
        Emotion emotion = null;

        while (emotion == null) {
            showEmotionMenu();

            System.out.print("Ingrese su opcion: ");
            int emotionOption = scanner.nextInt();

            emotion = Emotion.fromOption(emotionOption);

            if (emotion == null) {
                System.out.println("Opcion de emocion no valida. Intentalo otra vez.");
            }
        }

        return emotion;
    }

    private void showEmotionMenu() {
        System.out.println("""

                Seleccione una emocion:

                1. Alegria
                2. Tristeza
                3. Ira
                4. Asco
                5. Miedo
                6. Ansiedad
                7. Envidia
                8. Verguenza
                9. Aburrimiento
                10. Nostalgia

                """);
    }

    private void showAllMoments() {
        List<MomentResponseDTO> moments = controller.getAllMoments();
        showMoments(moments);
    }

    private void showMoments(List<MomentResponseDTO> moments) {
        if (moments.isEmpty()) {
            System.out.println("No hay momentos guardados.");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Lista de momentos vividos:");

        for (MomentResponseDTO moment : moments) {
            String formattedDate = moment.momentDate().format(formatter);

            System.out.println(moment.id() + ". Ocurrio el: "
                    + formattedDate
                    + ". Titulo: " + moment.title()
                    + ". Descripcion: " + moment.description()
                    + ". Emocion: " + moment.emotion().getDisplayName());
        }
    }

    private void deleteMoment() {
        System.out.println("Ingrese el identificador del momento:");
        int id = scanner.nextInt();

        boolean deleted = controller.deleteMomentById(id);

        if (deleted) {
            System.out.println("Momento eliminado correctamente.");
        } else {
            System.out.println("No existe un momento con ese id");
        }
    }

    private void filterMoments() {
        System.out.println("""
                Filtrar por:
                1. Emocion
                2. Mes
                """);

        System.out.print("Ingrese una opcion: ");
        int option = scanner.nextInt();

        if (option == 1) {
            filterMomentsByEmotion();
        } else if (option == 2) {
            filterMomentsByMonth();
        } else {
            System.out.println("Opcion de filtro no valida.");
        }
    }

    private void filterMomentsByEmotion() {
        Emotion emotion = askEmotion();

        List<MomentResponseDTO> moments = controller.findMomentsByEmotion(emotion);

        showMoments(moments);
    }

    private void filterMomentsByMonth() {
        System.out.println("Ingrese el mes (1-12):");
        int month = scanner.nextInt();

        List<MomentResponseDTO> moments = controller.findMomentsByMonth(month);

        showMoments(moments);
    }

}
