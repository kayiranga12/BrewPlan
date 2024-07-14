package com.example.Brewplan.Repository;

import com.example.Brewplan.Model.DemandForecast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandForecastRepository extends JpaRepository<DemandForecast, Long> {
}
