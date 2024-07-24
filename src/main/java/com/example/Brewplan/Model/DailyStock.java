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
//@Table(name = "daily_stock")
//public class DailyStock {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String productName;
//    private int stockLevel;
//    private LocalDate date;
//
//    @ManyToOne
//    @JoinColumn(name = "production_plan_id", nullable = false)
//    private ProductionPlan productionPlan;
//}
