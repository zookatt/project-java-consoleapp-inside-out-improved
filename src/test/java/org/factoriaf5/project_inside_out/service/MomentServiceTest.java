package org.factoriaf5.project_inside_out.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.factoriaf5.project_inside_out.dto.MomentRequestDTO;
import org.factoriaf5.project_inside_out.model.Emotion;
import org.factoriaf5.project_inside_out.model.Moment;
import org.factoriaf5.project_inside_out.repository.MapMomentRepository;
import org.factoriaf5.project_inside_out.repository.MomentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MomentServiceTest {
    private MomentRepository repository;
    private MomentService service;

    @BeforeEach
    void setUp() {
        repository = new MapMomentRepository();
        service = new MomentService(repository);
    }

    @Test
    void shouldAddMoment() {
        service.addMoment(new MomentRequestDTO(
                "Summer day by the sea",
                "I went to the seaside and bath all day.",
                Emotion.JOY,
                LocalDate.of(2026, 7, 15)));
        assertEquals(1, service.getAllMoments().size());
        assertEquals("Summer day by the sea", service.getAllMoments().get(0).getTitle());
    }

    @Test
    void testDeleteMomentById() {
        service.addMoment(new MomentRequestDTO("Summer day",
                "Description",
                Emotion.JOY,
                LocalDate.of(2026, 7, 15)));
        boolean deleted = service.deleteMomentById(1);
        assertTrue(deleted);
        assertEquals(0, service.getAllMoments().size());
    }

    @Test
    void testFindMomentsByEmotion() {
        service.addMoment(new MomentRequestDTO("Happy moment",
                "Description happy moment",
                Emotion.JOY,
                LocalDate.of(2026, 7, 15)));
        service.addMoment(new MomentRequestDTO("Sad moment",
                "Description sad moment",
                Emotion.SADNESS,
                LocalDate.of(2026, 7, 16)));
        List<Moment> moments = service.findMomentsByEmotion(Emotion.JOY);
        assertEquals(1, moments.size());
        assertEquals(Emotion.JOY, moments.get(0).getEmotion());
    }

    @Test
    void testFindMomentsByMonth() {
        service.addMoment(new MomentRequestDTO("July moment",
                "Description july moment",
                Emotion.JOY,
                LocalDate.of(2026, 7, 15)));
        service.addMoment(new MomentRequestDTO("August moment",
                "Description august moment",
                Emotion.SADNESS,
                LocalDate.of(2026, 8, 16)));
        List<Moment> moments = service.findMomentsByMonth(7);
        assertEquals(1, moments.size());
        assertEquals(7, moments.get(0).getMomentDate().getMonthValue());
    }

    @Test
    void testGetAllMoments() {
        service.addMoment(new MomentRequestDTO("Moment 1", "Description 1", Emotion.JOY, LocalDate.of(2026, 7, 15)));
        service.addMoment(new MomentRequestDTO("Moment 2", "Description 2", Emotion.NOSTALGIA, LocalDate.of(2026, 4, 9)));
        assertEquals(2, service.getAllMoments().size());
    }
}
