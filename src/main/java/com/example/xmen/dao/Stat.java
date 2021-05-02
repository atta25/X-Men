package com.example.xmen.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Stat {
    @JsonProperty("count_mutant_dna")
    private Long mutantCount = 0L;
    @JsonProperty("count_human_dna")
    private Long humanCount = 0L;
    private Double ratio;
    @JsonIgnore
    private Long total = 0L;

    @Override
    public String toString() {
        return "Stat{" +
                "mutantCount=" + mutantCount +
                ", humanCount=" + humanCount +
                ", ratio=" + ratio +
                ", total=" + total +
                '}';
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public Long getMutantCount() {
        return mutantCount;
    }

    public Long getHumanCount() {
        return humanCount;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
