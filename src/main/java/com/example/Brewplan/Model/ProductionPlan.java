//package com.example.Brewplan.Model;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDate;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "production_plan")
//public class ProductionPlan {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long planId;
//
//    private String planName;
//
//    private LocalDate startDate;
//
//    private LocalDate endDate;
//
//    @Enumerated(EnumType.STRING)
//    private Status status;
//
//    private LocalDate createDate;
//
//    public enum Status {
//        PENDING,
//        COMPLETE,
//        ONGOING
//    }
//}
//
//
