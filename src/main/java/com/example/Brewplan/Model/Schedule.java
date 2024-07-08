//package com.example.Brewplan.Model;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDateTime;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "schedule")
//public class Schedule {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long scheduleId;
//
//    @ManyToOne
//    @JoinColumn(name = "task_id", nullable = false)
//    private Task task;
//
//    private LocalDateTime scheduledStart;
//
//    private LocalDateTime scheduledEnd;
//
//    private LocalDateTime actualStart;
//
//    private LocalDateTime actualEnd;
//}
//
//
//
