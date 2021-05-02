package com.example.xmen.criterion;

import com.example.xmen.model.Node;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Horizontal implements Criterion {
    private static final Logger logger = LogManager.getLogger(Horizontal.class);

    @Override
    public List<LinkedList<Node>> generateNodes(String[] strings) {
        List<LinkedList<Node>> sequences = new ArrayList<>();
        logger.info("Generating horizontal nodes");

        for (String s: strings) {
            LinkedList<Node> nodes = new LinkedList<>();
            for (char c: s.toCharArray()) {
                nodes.add(new Node(c));
            }
            sequences.add(nodes);
        }
        logger.info("Generation of horizontal nodes finished");

        return sequences;
    }
}
