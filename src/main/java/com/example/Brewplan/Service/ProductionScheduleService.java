package com.example.Brewplan.Service;

import com.example.Brewplan.Model.ProductionSchedule;
import com.example.Brewplan.Repository.ProductionScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductionScheduleService {

    @Autowired
    private ProductionScheduleRepository productionScheduleRepository;

    public List<ProductionSchedule> getAllProductionSchedules() {
        return productionScheduleRepository.findAll();
    }

    public Optional<ProductionSchedule> getProductionScheduleById(Long id) {
        return productionScheduleRepository.findById(id);
    }

    public ProductionSchedule saveProductionSchedule(ProductionSchedule productionSchedule) {
        return productionScheduleRepository.save(productionSchedule);
    }

    public void deleteProductionSchedule(Long id) {
        productionScheduleRepository.deleteById(id);
    }
}
