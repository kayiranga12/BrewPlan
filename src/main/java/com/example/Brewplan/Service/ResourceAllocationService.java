package com.example.Brewplan.Service;

import com.example.Brewplan.Model.ResourceAllocation;
import com.example.Brewplan.Repository.ResourceAllocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceAllocationService {

    @Autowired
    private ResourceAllocationRepository resourceAllocationRepository;

    public List<ResourceAllocation> getAllResourceAllocations() {
        return resourceAllocationRepository.findAll();
    }

    public Optional<ResourceAllocation> getResourceAllocationById(Long id) {
        return resourceAllocationRepository.findById(id);
    }

    public ResourceAllocation saveResourceAllocation(ResourceAllocation resourceAllocation) {
        return resourceAllocationRepository.save(resourceAllocation);
    }

    public void deleteResourceAllocation(Long id) {
        resourceAllocationRepository.deleteById(id);
    }
}
