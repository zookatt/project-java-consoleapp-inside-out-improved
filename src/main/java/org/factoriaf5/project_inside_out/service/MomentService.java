package org.factoriaf5.project_inside_out.service;

import java.util.ArrayList;
import java.util.List;

import org.factoriaf5.project_inside_out.dto.MomentRequestDTO;
import org.factoriaf5.project_inside_out.model.Emotion;
import org.factoriaf5.project_inside_out.model.Moment;
import org.factoriaf5.project_inside_out.repository.MomentRepository;

public class MomentService {
    private MomentRepository repository;
    private int nextId = 1;

    public MomentService(MomentRepository repository) {
        this.repository = repository;
    }

    public void addMoment(MomentRequestDTO dto) {
        Moment moment = new Moment(nextId, dto.title(), dto.description(), dto.emotion(), dto.momentDate());
        repository.save(moment);
        nextId++;
    }

    public List<Moment> getAllMoments() {
        return repository.findAll();
    }

    public boolean deleteMomentById(int id) {
        return repository.deleteById(id);
    }

    public List<Moment> findMomentsByEmotion(Emotion emotion) {
        List<Moment> result = new ArrayList<>();

        for (Moment moment : repository.findAll()) {
            if (moment.getEmotion() == emotion) {
                result.add(moment);
            }
        }

        return result;
    }

    public List<Moment> findMomentsByMonth(int month) {
        List<Moment> result = new ArrayList<>();

        for (Moment moment : repository.findAll()) {
            if (moment.getMomentDate().getMonthValue() == month) {
                result.add(moment);
            }
        }

        return result;
    }
}
