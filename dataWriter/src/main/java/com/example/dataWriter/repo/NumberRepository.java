package com.example.dataWriter.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class NumberRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Method to check if table exists and create it if it doesn't
    public void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS numbers ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "number_value INT NOT NULL)";
        jdbcTemplate.execute(sql);
    }

    public void saveNumber(int number) {
        // Ensure the table exists
        createTableIfNotExists();

        // Insert the number into the database
        String sql = "INSERT INTO numbers (number_value) VALUES (?)";
        jdbcTemplate.update(sql, number);
    }
}

