package com.example.Brewplan.Controller;

import com.example.Brewplan.Model.*;
import com.example.Brewplan.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class WebController {
    @Autowired
    private EnergyConsumptionService energyConsumptionService;
    @Autowired
    private ProductionScheduleService productionScheduleService;
    @Autowired
    private ResourceAllocationService resourceAllocationService;
    @Autowired
    private DemandForecastService demandForecastService;
    @GetMapping
    public String index() {
        return "welcome";
    }
    @GetMapping("/demand-forecast")
    public String demand(Model model) {
        List<DemandForecast> demandForecasts = demandForecastService.getAllForecasts();
        model.addAttribute("demandForecasts", demandForecasts);
        // Add print statements to debug
        System.out.println("Forecast Data Sent to Frontend: " + demandForecasts);
        return "demand-forecast";
    }

}
