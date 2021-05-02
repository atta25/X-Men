package com.example.xmen.service;

import com.example.xmen.dto.DnaRequestBody;
import com.example.xmen.dao.Stat;
import com.example.xmen.detector.Detector;
import com.example.xmen.repository.Repository;
import org.springframework.stereotype.Service;

@Service
public class MutantService {
    private final Detector detector;
    private final Repository repository;

    public MutantService(Detector detector, Repository repository) {
        this.detector = detector;
        this.repository = repository;
    }

    public boolean isMutant(DnaRequestBody dnaRequestBody) {
        boolean result = detector.isMutant(dnaRequestBody.getDna());
        repository.saveResult(dnaRequestBody, result);
        return result;
    }

    public Stat getStats() {
        return repository.getStats();
    }
}
