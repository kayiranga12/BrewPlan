//package com.example.Brewplan.Model;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "task")
//public class Task {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long taskId;
//
//    @ManyToOne
//    @JoinColumn(name = "plan_id", nullable = false)
//    private ProductionPlan productionPlan;
//
//    private String taskName;
//
//    private String description;
//
//    private String empName;
//
//    @Enumerated(EnumType.STRING)
//    private Status status;
//
//    @Enumerated(EnumType.STRING)
//    private Priority priority;
//
//    public enum Status {
//        PENDING,
//        COMPLETE,
//        ONGOING
//    }
//
//    public enum Priority {
//        LOW,
//        MEDIUM,
//        HIGH
//    }
//}
//
//
