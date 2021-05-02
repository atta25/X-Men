package com.example.xmen.generator;

import com.example.xmen.criterion.Criterion;
import com.example.xmen.model.Node;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
public class Generator {
    private List<Criterion> criteria = new ArrayList<>();

    public List<LinkedList<Node>> buildSequences(String[] strings) {
        List<LinkedList<Node>> sequences = new ArrayList<>();

        for (Criterion criterion: criteria) {
            sequences.addAll(criterion.generateNodes(strings));
        }

        return sequences;
    }

    public void addCriterion(Criterion criterion) {
        criteria.add(criterion);
    }
}
