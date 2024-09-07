package com.example.dataReader.services;

import com.example.dataReader.model.AnalysedNumbers;
import com.example.dataReader.repository.AnalysedNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalysedNumberService {

    @Autowired
    private AnalysedNumberRepository analysedNumberRepository;

    public List<AnalysedNumbers> getAllAnalysedNumbers() {
        return analysedNumberRepository.findAll();
    }
}
