package com.example.Brewplan.Service;

import com.example.Brewplan.Model.EnergyConsumption;
import com.example.Brewplan.Repository.EnergyConsumptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnergyConsumptionService {

    @Autowired
    private EnergyConsumptionRepository energyConsumptionRepository;

    public List<EnergyConsumption> getAllEnergyConsumptions() {
        return energyConsumptionRepository.findAll();
    }

    public Optional<EnergyConsumption> getEnergyConsumptionById(Long id) {
        return energyConsumptionRepository.findById(id);
    }

    public void saveEnergyConsumption(EnergyConsumption energyConsumption) {
        energyConsumptionRepository.save(energyConsumption);
    }

    public void deleteEnergyConsumption(Long id) {
        energyConsumptionRepository.deleteById(id);
    }
}
