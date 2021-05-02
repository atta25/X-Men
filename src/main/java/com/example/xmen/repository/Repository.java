package com.example.xmen.repository;

import com.example.xmen.dto.DnaRequestBody;
import com.example.xmen.dao.Dna;
import com.example.xmen.dao.Result;
import com.example.xmen.dao.Stat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.ComparisonOperators;
import org.springframework.data.mongodb.core.aggregation.ConditionalOperators;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;

@Component
public class Repository {
    private final ResultRepository resultRepository;
    private final MongoTemplate template;
    private static final Logger logger = LogManager.getLogger(Repository.class);

    public Repository(ResultRepository resultRepository, MongoTemplate template) {
        this.resultRepository = resultRepository;
        this.template = template;
    }

    @Async
    public void saveResult(DnaRequestBody dnaRequestBody, boolean result) {
        Dna dna = new Dna(Arrays.asList(dnaRequestBody.getDna()));
        logger.info("Saving result");
        resultRepository.save(new Result(dna, result));
        logger.info("Saved result");
    }

    public Stat getStats() {
        Stat stat = calculateStat();
        stat.setRatio(calculateRatio(stat));
        return stat;
    }

    private Stat calculateStat() {
        Aggregation aggregation = Aggregation.newAggregation(
                group()
                        .sum(ConditionalOperators
                                .when(ComparisonOperators.valueOf("isMutant").equalToValue(true)).then(1).otherwise(0)).as("mutantCount")
                        .sum(ConditionalOperators
                                .when(ComparisonOperators.valueOf("isMutant").equalToValue(false)).then(1).otherwise(0)).as("humanCount")
                        .count().as("total")
        );

        Stat stat = this.template.aggregate(aggregation, Result.class, Stat.class).getUniqueMappedResult();

        return stat != null ? stat : new Stat();
    }

    private Double calculateRatio(Stat stat) {
        Double ratio = 0.0;

        if (stat.getMutantCount() != 0) {
            if (stat.getHumanCount() == 0) {
                ratio = 1.0;
            } else {
                Long mutant = stat.getMutantCount();
                Long human = stat.getHumanCount();
                ratio = mutant.doubleValue() / human.doubleValue();
            }
        }

        return ratio;
    }
}
