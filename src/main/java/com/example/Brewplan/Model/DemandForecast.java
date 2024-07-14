package com.example.Brewplan.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "demand_forecast")
public class DemandForecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private int forecastQuantity;
    private String forecastPeriod;

    @ElementCollection
    private List<Integer> actualSales;

    @ElementCollection
    private List<Integer> forecastValues;

    @ElementCollection
    private List<String> dates;
}
