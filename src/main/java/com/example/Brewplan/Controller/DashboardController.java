package com.example.Brewplan.Controller;

import com.example.Brewplan.Model.*;
import com.example.Brewplan.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private DemandForecastRepository demandForecastRepository;

    @Autowired
    private EnergyConsumptionRepository energyConsumptionRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ProductionPlanRepository productionPlanRepository;

    @Autowired
    private ProductionScheduleRepository productionScheduleRepository;

    @Autowired
    private QualityControlRepository qualityControlRepository;

    @Autowired
    private ResourceAllocationRepository resourceAllocationRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        List<DemandForecast> demandForecasts = demandForecastRepository.findAll();
        List<EnergyConsumption> energyConsumptions = energyConsumptionRepository.findAll();
        List<Inventory> inventories = inventoryRepository.findAll();
        List<ProductionPlan> productionPlans = productionPlanRepository.findAll();
        List<ProductionSchedule> productionSchedules = productionScheduleRepository.findAll();
        List<QualityControl> qualityControls = qualityControlRepository.findAll();
        List<ResourceAllocation> resourceAllocations = resourceAllocationRepository.findAll();
        List<Supplier> suppliers = supplierRepository.findAll();

        model.addAttribute("demandForecasts", demandForecasts);
        model.addAttribute("energyConsumptions", energyConsumptions);
        model.addAttribute("inventories", inventories);
        model.addAttribute("productionPlans", productionPlans);
        model.addAttribute("productionSchedules", productionSchedules);
        model.addAttribute("qualityControls", qualityControls);
        model.addAttribute("resourceAllocations", resourceAllocations);
        model.addAttribute("suppliers", suppliers);

        return "dashboard";
    }
}
