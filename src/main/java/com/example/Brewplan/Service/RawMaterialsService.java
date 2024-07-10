package com.example.Brewplan.Service;

import com.example.Brewplan.Model.RawMaterials;
import com.example.Brewplan.Repository.RawMaterialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RawMaterialsService {

    @Autowired
    private RawMaterialsRepository rawMaterialsRepository;

    public List<RawMaterials> getAllRawMaterials() {
        return rawMaterialsRepository.findAll();
    }

    public void addRawMaterial(RawMaterials rawMaterials) {
        rawMaterialsRepository.save(rawMaterials);
    }

    public Optional<RawMaterials> getRawMaterialsById(Long id) {
        return rawMaterialsRepository.findById(id);
    }

    public void updateRawMaterial(Long id, RawMaterials updatedRawMaterials) {
        updatedRawMaterials.setMaterialId(id);
        rawMaterialsRepository.save(updatedRawMaterials);
    }

    public void deleteRawMaterials(Long id) {
        rawMaterialsRepository.deleteById(id);
    }

    public List<RawMaterials> getMaterialsNeedingReplenishment() {
        return rawMaterialsRepository.findAll().stream()
                .filter(RawMaterials::needsReplenishment)
                .collect(Collectors.toList());
    }

    public Map<String, Integer> getResourceUtilizationStatistics() {
        List<RawMaterials> rawMaterialsList = rawMaterialsRepository.findAll();
        Map<String, Integer> utilizationStats = new HashMap<>();

        int totalMaterials = rawMaterialsList.size();
        int materialsBelowMinimum = (int) rawMaterialsList.stream().filter(RawMaterials::needsReplenishment).count();
        int totalCurrentStock = rawMaterialsList.stream().mapToInt(RawMaterials::getCurrentStock).sum();
        int totalMinimumStockLevel = rawMaterialsList.stream().mapToInt(RawMaterials::getMinimumStockLevel).sum();

        utilizationStats.put("Total Materials", totalMaterials);
        utilizationStats.put("Materials Below Minimum", materialsBelowMinimum);
        utilizationStats.put("Total Current Stock", totalCurrentStock);
        utilizationStats.put("Total Minimum Stock Level", totalMinimumStockLevel);

        return utilizationStats;
    }
}
