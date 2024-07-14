package com.example.Brewplan.Service;

import com.example.Brewplan.Model.DemandForecast;
import com.example.Brewplan.Repository.DemandForecastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandForecastService {

    @Autowired
    private DemandForecastRepository demandForecastRepository;

    public DemandForecast saveForecast(DemandForecast demandForecast) {
        return demandForecastRepository.save(demandForecast);
    }

    public List<DemandForecast> getAllForecasts() {
        return demandForecastRepository.findAll();
    }

    public void deleteAllForecasts() {
        demandForecastRepository.deleteAll();
    }

    public List<DemandForecast> saveAllForecasts(List<DemandForecast> demandForecasts) {
        deleteAllForecasts();
        return demandForecastRepository.saveAll(demandForecasts);
    }
}
