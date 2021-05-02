package com.example.xmen.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dna_result")
public class Result {
    @Id
    @Indexed
    private Dna dna;
    private boolean isMutant;

    public Result(Dna dna, boolean isMutant) {
        this.dna = dna;
        this.isMutant = isMutant;
    }

    @Override
    public String toString() {
        return "Result {" +
                "dna=" + dna +
                ", isMutant=" + isMutant +
                '}';
    }
}
