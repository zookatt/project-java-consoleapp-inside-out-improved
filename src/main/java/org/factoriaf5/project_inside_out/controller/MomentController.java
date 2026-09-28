package org.factoriaf5.project_inside_out.controller;

import java.util.List;

import org.factoriaf5.project_inside_out.dto.MomentRequestDTO;
import org.factoriaf5.project_inside_out.dto.MomentResponseDTO;
import org.factoriaf5.project_inside_out.model.Emotion;
import org.factoriaf5.project_inside_out.model.Moment;
import org.factoriaf5.project_inside_out.service.MomentService;

public class MomentController {
    private MomentService service;

    public MomentController(MomentService service) {
        this.service = service;
    }

    public void addMoment(MomentRequestDTO dto) {
        service.addMoment(dto);
    }

    public List<MomentResponseDTO> getAllMoments() {
        return service.getAllMoments()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public boolean deleteMomentById(int id) {
        return service.deleteMomentById(id);
    }

    public List<MomentResponseDTO> findMomentsByEmotion(Emotion emotion) {
        return service.findMomentsByEmotion(emotion)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public List<MomentResponseDTO> findMomentsByMonth(int month) {
        return service.findMomentsByMonth(month)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    private MomentResponseDTO toResponseDTO(Moment moment) {
        return new MomentResponseDTO(
                moment.getId(),
                moment.getTitle(),
                moment.getDescription(),
                moment.getEmotion(),
                moment.getMomentDate());
    }
}
