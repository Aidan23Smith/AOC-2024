package com.aoc2024.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RowColumnConfig {

    @Bean
    public RowColumn rowColumnDay14() {
        return new RowColumn(103, 101);
    }

    public record RowColumn(int rowCount, int columnCount) {

    }

}
