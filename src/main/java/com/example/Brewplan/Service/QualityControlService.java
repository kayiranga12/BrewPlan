package com.example.Brewplan.Service;

import com.example.Brewplan.Model.QualityControl;
import com.example.Brewplan.Repository.QualityControlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QualityControlService {

    @Autowired
    private QualityControlRepository qualityControlRepository;

    public List<QualityControl> getAllQualityControls() {
        return qualityControlRepository.findAll();
    }

    public Optional<QualityControl> getQualityControlById(Long id) {
        return qualityControlRepository.findById(id);
    }

    public void saveQualityControl(QualityControl qualityControl) {
        qualityControlRepository.save(qualityControl);
    }

    public void deleteQualityControl(Long id) {
        qualityControlRepository.deleteById(id);
    }

    public List<QualityControl> searchByProductName(String query) {
        return qualityControlRepository.findAll()
                .stream()
                .filter(qc -> qc.getProductName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}
