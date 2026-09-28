package org.factoriaf5.project_inside_out.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EmotionTest {
    @Test
    void shouldReturnJoyWhenOptionIsOne() {
        Emotion emotion = Emotion.fromOption(1);
        assertEquals(Emotion.JOY, emotion);
    }

    @Test
    void shouldReturnDisplayName() {
        Emotion emotion = Emotion.JOY;
        assertEquals("Alegria", emotion.getDisplayName());
    }
}
