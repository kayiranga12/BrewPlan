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
@Table(name = "energy_consumption")
public class EnergyConsumption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private LocalDateTime recordDate;
    private double energyUsed; // Energy used in kWh

    @ManyToOne
    @JoinColumn(name = "production_plan_id", nullable = false)
    private ProductionPlan productionPlan;
}
