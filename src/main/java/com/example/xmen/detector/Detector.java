package com.example.xmen.detector;

import com.example.xmen.criterion.Diagonal;
import com.example.xmen.criterion.Horizontal;
import com.example.xmen.criterion.Vertical;
import com.example.xmen.generator.Generator;
import com.example.xmen.model.Node;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Component
public class Detector {
    private final Generator generator;
    private static final int MINIMUM_NUMBER_OF_OCCURRENCES = 4;
    private static final int MINIMUM_NUMBER_OF_SEQUENCES = 2;
    private static final Logger logger = LogManager.getLogger(Detector.class);

    public Detector(Generator generator) {
        this.generator = generator;
    }

    public boolean isMutant(String[] dna) {
        logger.info("Generating sequences");
        List<LinkedList<Node>> sequences = generator.buildSequences(dna);
        logger.info("Generated sequences");
        int match = 0;
        boolean flag = false;

        logger.info("Looking for sequences");

        for (LinkedList<Node> sequence: sequences) {
            Iterator<Node> iterator = sequence.iterator();
            int occurrences = 1;
            Node first = iterator.next();
            Node second = iterator.next();
            while (second != null) {
                if (first.equals(second)) {
                    occurrences++;
                    if (occurrences == MINIMUM_NUMBER_OF_OCCURRENCES) {
                        match++;
                        break;
                    }
                } else {
                    occurrences = 1;
                }
                first = second;
                second = (iterator.hasNext()) ? iterator.next(): null;
            }

            if (match == MINIMUM_NUMBER_OF_SEQUENCES) {
                logger.info("Number of completed matches");
                flag = true;
                break;
            }
        }
        logger.info("Looking for finished sequences");

        return flag;
    }

    public void loadCriteria() {
        this.generator.addCriterion(new Horizontal());
        this.generator.addCriterion(new Vertical());
        this.generator.addCriterion(new Diagonal());
    }
}
