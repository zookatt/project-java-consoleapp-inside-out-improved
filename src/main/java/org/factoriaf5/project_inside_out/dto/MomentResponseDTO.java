package org.factoriaf5.project_inside_out.dto;

import java.time.LocalDate;

import org.factoriaf5.project_inside_out.model.Emotion;

public record MomentResponseDTO(
        int id,
        String title,
        String description,
        Emotion emotion,
        LocalDate momentDate) {
}
