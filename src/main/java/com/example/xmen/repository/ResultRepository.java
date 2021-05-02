package com.example.xmen.repository;

import com.example.xmen.dao.Dna;
import com.example.xmen.dao.Result;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends MongoRepository<Result, Dna> {
}
