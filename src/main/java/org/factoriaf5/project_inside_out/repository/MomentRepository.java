package org.factoriaf5.project_inside_out.repository;

import java.util.List;

import org.factoriaf5.project_inside_out.model.Moment;

public interface MomentRepository {
    void save(Moment moment);

    List<Moment> findAll();

    boolean deleteById(int id);
}
