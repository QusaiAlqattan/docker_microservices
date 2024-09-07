package com.example.analysis.services;

import com.example.analysis.model.AnalysedNumbers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Map;

@Service
public class AnalysisServer {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Transactional
    @Scheduled(fixedRate = 5000) // 5 seconds
    public void readFromDatabase() {
        String sql = "SELECT \n" +
                "    MAX(number_value) AS max_value,\n" +
                "    MIN(number_value) AS min_value,\n" +
                "    AVG(number_value) AS average_value\n" +
                "FROM \n" +
                "    numbers;"; // Replace with your actual table name
        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);

        AnalysedNumbers analysedNumbers = new AnalysedNumbers();
        Object max = results.getFirst().get("max_value");
        Object min = results.getFirst().get("min_value");
        Object avarage = results.getFirst().get("average_value");

        if (max == null){
            max = 0;
        }
        if (min == null){
            min = 0;
        }
        if (avarage == null){
            avarage = 0;
        }

        if(!((int)max == 0 && (int)min == 0 && (double)avarage == 0)){

            analysedNumbers.setMaxTemperature((int)max);
            analysedNumbers.setMinTemperature((int)min);
            analysedNumbers.setAverageTemperature(Double.parseDouble(String.valueOf(avarage)));

            // Get the current date and time
            LocalDateTime now = LocalDateTime.now();
            // Define the format for the date and time
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            // Format the current date and time to a string
            String dateTimeString = now.format(formatter);

            analysedNumbers.setDate(dateTimeString);

            // Save the results to MongoDB
            mongoTemplate.save(analysedNumbers, "analysedNumbers");
        }
    }
}
