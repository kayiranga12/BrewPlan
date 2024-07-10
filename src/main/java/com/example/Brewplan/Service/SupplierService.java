package com.example.Brewplan.Service;

import com.example.Brewplan.Model.Supplier;
import com.example.Brewplan.Repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public void addSupplier(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    public Optional<Supplier> getSupplierById(Long id) {
        return supplierRepository.findById(id);
    }

    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }

    public void updateSupplier(Long id, Supplier supplier) {
        Supplier existingSupplier = supplierRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid supplier Id:" + id));
        existingSupplier.setSupplierName(supplier.getSupplierName());
        existingSupplier.setContactName(supplier.getContactName());
        existingSupplier.setContactEmail(supplier.getContactEmail());
        existingSupplier.setContactPhone(supplier.getContactPhone());
        existingSupplier.setAddress(supplier.getAddress());
        supplierRepository.save(existingSupplier);
    }

    public Supplier findBySupplierName(String supplierName) {
        return supplierRepository.findBySupplierName(supplierName).orElseThrow(() -> new IllegalArgumentException("Supplier not found"));
    }
}
