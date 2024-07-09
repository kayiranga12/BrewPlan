package com.example.Brewplan.Service;


import com.example.Brewplan.Model.ProductionSchedule;
import com.example.Brewplan.Repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductionScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    public List<ProductionSchedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public Optional<ProductionSchedule> getScheduleById(Long id) {
        return scheduleRepository.findById(id);
    }

    public ProductionSchedule saveSchedule(ProductionSchedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }
}

