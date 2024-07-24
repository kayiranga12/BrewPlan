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
@Table(name = "production_schedule")
public class ProductionSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    private LocalDateTime scheduledStart;
    private LocalDateTime scheduledEnd;
    private LocalDateTime actualStart;
    private LocalDateTime actualEnd;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private ProductionPlan productionPlan;

    public enum Status {
        PLANNED,
        IN_PROGRESS,
        COMPLETED
    }
}
