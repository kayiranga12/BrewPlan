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
    private Long energyConsumptionId;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @Enumerated(EnumType.STRING)
    private EnergyType energyType;

    private Double quantity;

    @Enumerated(EnumType.STRING)
    private UnitOfMeasure unitOfMeasure;

    private LocalDateTime createdAt = LocalDateTime.now();

    public enum EnergyType {
        ELECTRICITY,
        GAS,
        WATER
    }

    public enum UnitOfMeasure {
        KWH,
        LITERS,
        CUBIC_METERS
    }
}


