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
@Table(name = "batch")
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long batchId;
    private String productName;
    private int batchSize;
    private LocalDateTime productionDate;

    @ManyToOne
    @JoinColumn(name = "production_plan_id", nullable = false)
    private ProductionPlan productionPlan;
}
