package com.example.Brewplan.Repository;

import com.example.Brewplan.Model.ProductionSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<ProductionSchedule, Long> {
}

