package com.example.analysis.repo;

import com.example.mysqlreader.model.YourData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface YourDataRepository extends MongoRepository<Data, String> {
}
