// ProductionPlan.java
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
    private String planName;
    private LocalDateTime startDate = LocalDateTime.now();
    private LocalDateTime endDate = LocalDateTime.now();
    private String approvalComments;
    private String approvalStatus;

    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime createDate = LocalDateTime.now();

    private int plannedProduction;
    private int actualProduction;
    private int downtimeMinutes;
    private int totalAvailableMinutes;

    private int materialUsed;
    private int laborHours;
    private int machineUtilization;

    public enum Status {
        PENDING,
        COMPLETE,
        ONGOING,
        APPROVED,
        REJECTED
    }
}
