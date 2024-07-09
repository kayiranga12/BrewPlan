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
@Table(name = "schedule")
public class ProductionSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;
    private LocalDateTime scheduledStart = LocalDateTime.now();
    private LocalDateTime scheduledEnd = LocalDateTime.now();
    private LocalDateTime actualStart = LocalDateTime.now();
    private LocalDateTime actualEnd = LocalDateTime.now();

}
