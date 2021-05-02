package com.example.xmen.criterion;

import com.example.xmen.model.Node;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Diagonal implements Criterion {
    private static final Logger logger = LogManager.getLogger(Diagonal.class);

    @Override
    public List<LinkedList<Node>> generateNodes(String[] strings) {
        List<LinkedList<Node>> sequences = new ArrayList<>();
        logger.info("Generating diagonal nodes");

        for(int i = 0; i < strings.length / 2; i++) {
            LinkedList<Node> nodes1 = new LinkedList<>();
            LinkedList<Node> nodes2 = new LinkedList<>();

            for (int j = 0; j < strings.length -i; j++) {
                nodes1.add(new Node(strings[j].charAt(j+i)));

                if(i != 0) {
                    nodes2.add(new Node(strings[i + j].charAt(j)));
                }
            }

            if(!nodes1.isEmpty()) {
                sequences.add(nodes1);
            }

            if(!nodes2.isEmpty()) {
                sequences.add(nodes2);
            }
        }
        logger.info("Generation of diagonal nodes finished");

        return sequences;
    }
}
