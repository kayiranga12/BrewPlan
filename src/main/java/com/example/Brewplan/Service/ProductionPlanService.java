package com.example.Brewplan.Service;

import com.example.Brewplan.Model.ProductionPlan;
import com.example.Brewplan.Repository.ProductionPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductionPlanService {

    @Autowired
    private ProductionPlanRepository productionPlanRepository;

    public List<ProductionPlan> getAllProductionPlans() {
        return productionPlanRepository.findAll();
    }

    public Optional<ProductionPlan> getProductionPlanById(Long id) {
        return productionPlanRepository.findById(id);
    }

    public ProductionPlan saveProductionPlan(ProductionPlan productionPlan) {
        return productionPlanRepository.save(productionPlan);
    }

    public void deleteProductionPlan(Long id) {
        productionPlanRepository.deleteById(id);
    }
}
