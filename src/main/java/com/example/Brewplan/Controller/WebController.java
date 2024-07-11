package com.example.Brewplan.Controller;

import com.example.Brewplan.Model.EnergyConsumption;
import com.example.Brewplan.Model.ProductionSchedule;
import com.example.Brewplan.Model.Task;
import com.example.Brewplan.Service.EnergyConsumptionService;
import com.example.Brewplan.Service.ProductionScheduleService;
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
    @GetMapping
    public String index() {
        return "welcome";
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

        model.addAttribute("energyTypeCounts", energyTypeCounts);
        model.addAttribute("energyConsumptionData", energyConsumptionData);
        model.addAttribute("energyConsumptionByTask", energyConsumptionByTask);
        model.addAttribute("schedules", schedules);
        return "dashboard";
    }
}
