package com.example.analysis.model;

import org.springframework.data.annotation.Id;

public class AnalysedNumbers {

    @Id
    private String id;
    private int maxTemperature;
    private int minTemperature;
    private double averageTemperature;
    private String date;

    public void setMaxTemperature(int maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public void setMinTemperature(int minTemperature) {
        this.minTemperature = minTemperature;
    }

    public void setAverageTemperature(double averageTemperature) {
        this.averageTemperature = averageTemperature;
    }

    public void setDate(String date) {
        this.date = date;
    }
}