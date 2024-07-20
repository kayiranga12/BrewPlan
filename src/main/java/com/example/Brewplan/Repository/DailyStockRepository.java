package com.example.Brewplan.Repository;

import com.example.Brewplan.Model.DailyStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DailyStockRepository extends JpaRepository<DailyStock, Long> {
    List<DailyStock> findByProductNameContainingIgnoreCase(String productName);
}
