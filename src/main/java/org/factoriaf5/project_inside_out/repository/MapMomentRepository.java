package org.factoriaf5.project_inside_out.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.factoriaf5.project_inside_out.model.Moment;

public class MapMomentRepository implements MomentRepository {
    private Map<Integer, Moment> moments = new HashMap<>();

    @Override
    public void save(Moment moment) {
        moments.put(moment.getId(), moment);
    }

    @Override
    public List<Moment> findAll() {
        return new ArrayList<>(moments.values());
    }

    @Override
    public boolean deleteById(int id) {
        return moments.remove(id) != null;
    }
}
