//package com.example.Brewplan.Service;
//
//import com.example.Brewplan.Model.EnergyConsumption;
//import com.example.Brewplan.Repository.EnergyConsumptionRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//
//@Service
//public class EnergyConsumptionService {
//
//    @Autowired
//    private EnergyConsumptionRepository repository;
//
//    public List<EnergyConsumption> getAllEnergyConsumptions() {
//        return repository.findAll();
//    }
//
//    public Optional<EnergyConsumption> getEnergyConsumptionById(Long id) {
//        return repository.findById(id);
//    }
//
//    public EnergyConsumption saveEnergyConsumption(EnergyConsumption energyConsumption) {
//        return repository.save(energyConsumption);
//    }
//
//    public void deleteEnergyConsumption(Long id) {
//        repository.deleteById(id);
//    }
//}
