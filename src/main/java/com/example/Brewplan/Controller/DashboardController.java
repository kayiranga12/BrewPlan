package com.example.Brewplan.Controller;

import com.example.Brewplan.Model.*;
import com.example.Brewplan.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @Autowired
    private RecentActivityRepository recentActivityRepository;

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
        List<RecentActivity> recentActivities = recentActivityRepository.findAll();

        // Calculate Schedule Adherence
        double scheduleAdherence = calculateScheduleAdherence(productionSchedules);

        // Calculate Resource Utilization
        double resourceUtilization = calculateResourceUtilization(resourceAllocations);

        // Fetch Total Production Plans and Completed Batches
        int totalProductionPlans = productionPlans.size();
        int completedBatches = (int) productionSchedules.stream()
                .filter(schedule -> schedule.getStatus() == ProductionSchedule.Status.COMPLETED)
                .count();

        model.addAttribute("demandForecasts", demandForecasts);
        model.addAttribute("energyConsumptions", energyConsumptions);
        model.addAttribute("inventories", inventories);
        model.addAttribute("productionPlans", productionPlans);
        model.addAttribute("productionSchedules", productionSchedules);
        model.addAttribute("qualityControls", qualityControls);
        model.addAttribute("resourceAllocations", resourceAllocations);
        model.addAttribute("suppliers", suppliers);
        model.addAttribute("recentActivities", recentActivities);

        // Add calculated metrics to the model
        model.addAttribute("scheduleAdherence", String.format("%.2f%%", scheduleAdherence));
        model.addAttribute("resourceUtilization", String.format("%.2f%%", resourceUtilization));
        model.addAttribute("totalProductionPlans", totalProductionPlans);
        model.addAttribute("completedBatches", completedBatches);

        return "dashboard";
    }

    private double calculateScheduleAdherence(List<ProductionSchedule> productionSchedules) {
        if (productionSchedules.isEmpty()) return 0.0;
        long totalSchedules = productionSchedules.size();
        long adheredSchedules = productionSchedules.stream()
                .filter(schedule -> !schedule.getScheduledEnd().isAfter(schedule.getActualEnd()))
                .count();
        return (adheredSchedules * 100.0) / totalSchedules;
    }

    private double calculateResourceUtilization(List<ResourceAllocation> resourceAllocations) {
        if (resourceAllocations.isEmpty()) return 0.0;
        double totalAllocated = resourceAllocations.stream()
                .mapToDouble(ResourceAllocation::getAllocatedQuantity)
                .sum();
        double totalCapacity = resourceAllocations.stream()
                .mapToDouble(ResourceAllocation::getResourceCapacity)
                .sum();
        return (totalAllocated * 100.0) / totalCapacity;
    }

    @GetMapping("/dashboard/recent-activities")
    @ResponseBody
    public List<RecentActivity> getRecentActivities() {
        return recentActivityRepository.findAll();
    }
}
