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
}

