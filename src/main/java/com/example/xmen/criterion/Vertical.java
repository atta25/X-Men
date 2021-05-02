package com.example.xmen.criterion;

import com.example.xmen.model.Node;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Vertical implements Criterion {
    private static final Logger logger = LogManager.getLogger(Vertical.class);

    @Override
    public List<LinkedList<Node>> generateNodes(String[] strings) {
        List<LinkedList<Node>> sequences = new ArrayList<>();
        logger.info("Generating vertical nodes");

        for(int i = 0; i < strings.length; i++) {
            LinkedList<Node> nodes = new LinkedList<>();
            for (String string : strings) {
                nodes.add(new Node(string.charAt(i)));
            }
            sequences.add(nodes);
        }
        logger.info("Generation of vertical nodes finished");

        return sequences;
    }
}
