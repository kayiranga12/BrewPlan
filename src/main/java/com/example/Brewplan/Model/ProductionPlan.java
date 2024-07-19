package com.example.Brewplan.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "production_plan")
public class ProductionPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;
    private String productName;
    private int plannedQuantity;
    private int actualQuantity;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String shiftTiming; // New field for shift timing
    private int dailyTarget; // New field for daily targets
    private String plannedDowntime; // New field for planned downtime

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        PLANNED,
        IN_PROGRESS,
        COMPLETED
    }
}
