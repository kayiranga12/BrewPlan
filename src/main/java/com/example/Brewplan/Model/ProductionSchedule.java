//package com.example.Brewplan.Model;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotEmpty;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.time.LocalDateTime;
//@Getter
//@Setter
//@Entity
//public class ProductionSchedule {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long scheduleId;
//
//    @NotEmpty(message = "Task name is required")
//    private String taskName;
//
//    @NotEmpty(message = "Start date is required")
//    private LocalDateTime startDate;
//
//    @NotEmpty(message = "End date is required")
//    private LocalDateTime endDate;
//
//}