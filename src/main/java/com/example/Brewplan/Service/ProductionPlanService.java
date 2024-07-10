// ProductionPlanService.java
package com.example.Brewplan.Service;

import com.example.Brewplan.Model.ProductionPlan;
import com.example.Brewplan.Repository.ProductionPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductionPlanService {

    @Autowired
    private ProductionPlanRepository repository;

    public List<ProductionPlan> getAllProductionPlans() {
        return repository.findAll();
    }

    public Optional<ProductionPlan> getProductionPlanById(Long id) {
        return repository.findById(id);
    }

    public ProductionPlan saveProductionPlan(ProductionPlan productionPlan) {
        return repository.save(productionPlan);
    }

    public void deleteProductionPlan(Long id) {
        repository.deleteById(id);
    }

    public double calculateEfficiency(ProductionPlan plan) {
        if (plan.getPlannedProduction() == 0) return 0;
        return (double) plan.getActualProduction() / plan.getPlannedProduction() * 100;
    }

    public double calculateDowntime(ProductionPlan plan) {
        return (double) plan.getDowntimeMinutes() / plan.getTotalAvailableMinutes() * 100;
    }

    public double calculateCapacityUtilization(ProductionPlan plan) {
        return (double) plan.getActualProduction() / plan.getTotalAvailableMinutes() * 100;
    }

    public int calculateMaterialUsed(ProductionPlan plan) {
        // logic to calculate material used
        return plan.getMaterialUsed();
    }

    public int calculateLaborHours(ProductionPlan plan) {
        // logic to calculate labor hours
        return plan.getLaborHours();
    }

    public int calculateMachineUtilization(ProductionPlan plan) {
        // logic to calculate machine utilization
        return plan.getMachineUtilization();
    }
    // ProductionPlanService.java
    public List<ProductionPlan> getPendingApprovals() {
        return repository.findAll().stream()
                .filter(plan -> "PENDING".equals(plan.getApprovalStatus()))
                .collect(Collectors.toList());
    }

    public List<ProductionPlan> getResourceShortages() {
        return repository.findAll().stream()
                .filter(plan -> plan.getMaterialUsed() < plan.getPlannedProduction()) // Simplified condition
                .collect(Collectors.toList());
    }

// Add methods to check deviations and other conditions as necessary

}
