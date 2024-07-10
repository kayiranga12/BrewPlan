package com.example.Brewplan.Service;

import com.example.Brewplan.Model.RawMaterials;
import com.example.Brewplan.Repository.RawMaterialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
}
