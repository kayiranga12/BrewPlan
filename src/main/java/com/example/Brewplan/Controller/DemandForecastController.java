package com.example.Brewplan.Controller;

import com.example.Brewplan.Model.DemandForecast;
import com.example.Brewplan.Service.DemandForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/forecast")
public class DemandForecastController {

    @Autowired    private DemandForecastService demandForecastService;

    @PostMapping
    public List<DemandForecast> addForecasts(@RequestBody List<DemandForecast> demandForecasts) {
        return demandForecastService.saveAllForecasts(demandForecasts);
    }

    @GetMapping
    public List<DemandForecast> getAllForecasts() {
        return demandForecastService.getAllForecasts();
    }
}
