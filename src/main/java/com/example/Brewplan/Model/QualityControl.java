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
@Table(name = "quality_control")
public class QualityControl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qcId;
    private String productName;
    private String metrics;
    private LocalDateTime checkDate;
    private String result;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        PASS,
        FAIL
    }
}
