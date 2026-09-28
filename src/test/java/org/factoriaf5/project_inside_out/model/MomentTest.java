package org.factoriaf5.project_inside_out.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class MomentTest {
    @Test
    void shouldCreateMomentWithReceivedData() {
        Moment moment = new Moment(
                1,
                "Summer day by the sea",
                "I went to the seaside and bath all day.",
                Emotion.JOY,
                LocalDate.of(2026, 7, 15));

        assertEquals(1, moment.getId());
        assertEquals("Summer day by the sea", moment.getTitle());
        assertEquals("I went to the seaside and bath all day.", moment.getDescription());
        assertEquals(Emotion.JOY, moment.getEmotion());
        assertEquals(LocalDate.of(2026, 7, 15), moment.getMomentDate());
    }

    @Test
    void shouldSetDatesAutomatically() {
        Moment moment = new Moment(
                1,
                "Summer day by the sea",
                "I went to the seaside and bath all day.",
                Emotion.JOY,
                LocalDate.of(2026, 7, 15));

        assertNotNull(moment.getCreatedDate());
        assertNotNull(moment.getModifiedDate());
    }
}
