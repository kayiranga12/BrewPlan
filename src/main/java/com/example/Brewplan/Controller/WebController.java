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
    private RawMaterialsService rawMaterialsService;
    @Autowired
    private TaskService taskService;
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

    @GetMapping("/dashboard")
    public String dashboardPage(Model model) {
        List<EnergyConsumption> energyConsumptions = energyConsumptionService.getAllEnergyConsumptions();
        Map<String, Long> energyTypeCounts = energyConsumptions.stream()
                .collect(Collectors.groupingBy(ec -> ec.getEnergyType().name(), Collectors.counting()));
        List<Map<String, Object>> energyConsumptionData = energyConsumptions.stream()
                .map(ec -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("date", ec.getCreatedAt().toString());
                    map.put("value", ec.getQuantity());
                    return map;
                })
                .collect(Collectors.toList());
        Map<String, Map<String, Double>> energyConsumptionByTask = energyConsumptions.stream()
                .collect(Collectors.groupingBy(
                        ec -> ec.getTask().getTaskName(),
                        Collectors.groupingBy(
                                ec -> ec.getEnergyType().name(),
                                Collectors.summingDouble(EnergyConsumption::getQuantity)
                        )
                ));
        List<ProductionSchedule> schedules = productionScheduleService.getAllSchedules();
        List<RawMaterials> rawMaterials = rawMaterialsService.getAllRawMaterials();
        List<Task> tasks = taskService.getAllTasks();
        List<ResourceAllocation> resourceAllocations = resourceAllocationService.getAllResourceAllocations();

        model.addAttribute("energyTypeCounts", energyTypeCounts);
        model.addAttribute("energyConsumptionData", energyConsumptionData);
        model.addAttribute("energyConsumptionByTask", energyConsumptionByTask);
        model.addAttribute("schedules", schedules);
        model.addAttribute("rawMaterials", rawMaterials);
        model.addAttribute("tasks", tasks);
        model.addAttribute("resourceAllocations", resourceAllocations);
        return "dashboard";
    }

}
