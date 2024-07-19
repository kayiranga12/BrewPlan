package com.example.Brewplan.Repository;

import com.example.Brewplan.Model.DailyStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyStockRepository extends JpaRepository<DailyStock, Long> {
    // Custom query methods if needed
}
