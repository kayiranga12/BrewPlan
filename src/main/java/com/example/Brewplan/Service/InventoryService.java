package com.example.Brewplan.Service;

import com.example.Brewplan.Model.Inventory;
import com.example.Brewplan.Repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    public Optional<Inventory> getInventoryById(Long id) {
        return inventoryRepository.findById(id);
    }

    public void saveInventory(Inventory inventory) {
        inventoryRepository.save(inventory);
    }

    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }

    public List<Inventory> searchByProductName(String query) {
        return inventoryRepository.findAll()
                .stream()
                .filter(inventory -> inventory.getProductName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}
