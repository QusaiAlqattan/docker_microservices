package com.example.dataReader.repository;

import com.example.dataReader.model.AnalysedNumbers;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnalysedNumberRepository extends MongoRepository<AnalysedNumbers, String> {
}
