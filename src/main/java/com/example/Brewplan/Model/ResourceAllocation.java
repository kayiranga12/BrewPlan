package com.example.Brewplan.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "resource_allocation")
public class ResourceAllocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long allocationId;

    @ManyToOne
    @JoinColumn(name = "production_plan_id", nullable = false)
    private ProductionPlan productionPlan;

    private String resourceName;
    private int allocatedQuantity;
    private String resourceType; // New field for resource type
    private String resourceStatus; // New field for resource status
    private int resourceCapacity;
}
