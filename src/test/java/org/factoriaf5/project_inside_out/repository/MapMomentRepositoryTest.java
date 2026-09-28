package org.factoriaf5.project_inside_out.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.factoriaf5.project_inside_out.model.Emotion;
import org.factoriaf5.project_inside_out.model.Moment;
import org.junit.jupiter.api.Test;

public class MapMomentRepositoryTest {
    @Test
    void shouldDeleteMomentById() {
        MomentRepository repository = new MapMomentRepository();
        repository.save(createMoment(1));
        assertTrue(repository.deleteById(1));
        assertEquals(0, repository.findAll().size());
    }

    @Test
    void shouldFindAllMoments() {
        MomentRepository repository = new MapMomentRepository();
        repository.save(createMoment(1));
        repository.save(createMoment(2));
        assertEquals(2, repository.findAll().size());
    }

    @Test
    void shouldSaveMoment() {
        MomentRepository repository = new MapMomentRepository();
        Moment moment = createMoment(1);
        repository.save(moment);
        assertEquals(1, repository.findAll().size());
    }

    private Moment createMoment(int id) {
        return new Moment(
                id,
                "Summer day by the sea",
                "I went to the seaside and bath all day.",
                Emotion.JOY,
                LocalDate.of(2026, 7, 15));
    }
}
