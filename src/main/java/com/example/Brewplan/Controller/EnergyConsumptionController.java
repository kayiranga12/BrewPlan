package com.example.Brewplan.Controller;

import com.example.Brewplan.Model.EnergyConsumption;
import com.example.Brewplan.Model.Task;
import com.example.Brewplan.Service.EnergyConsumptionService;
import com.example.Brewplan.Service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/energy-consumptions")
public class EnergyConsumptionController {

    @Autowired
    private EnergyConsumptionService service;

    @Autowired
    private TaskService taskService;

    @GetMapping
    public String listEnergyConsumptions(Model model) {
        model.addAttribute("energyConsumptions", service.getAllEnergyConsumptions());
        return "energy-consumptions/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("energyConsumption", new EnergyConsumption());
        model.addAttribute("tasks", taskService.getAllTasks());
        return "energy-consumptions/add";
    }

    @PostMapping("/add")
    public String addEnergyConsumption(@Valid @ModelAttribute("energyConsumption") EnergyConsumption energyConsumption, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("tasks", taskService.getAllTasks());
            return "energy-consumptions/add";
        }
        Optional<Task> task = taskService.getTaskById(energyConsumption.getTask().getTaskId());
        task.ifPresent(energyConsumption::setTask);
        energyConsumption.setCreatedAt(LocalDateTime.now());
        service.saveEnergyConsumption(energyConsumption);
        return "redirect:/energy-consumptions";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        EnergyConsumption energyConsumption = service.getEnergyConsumptionById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid energy consumption Id:" + id));
        model.addAttribute("energyConsumption", energyConsumption);
        model.addAttribute("tasks", taskService.getAllTasks());
        return "energy-consumptions/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateEnergyConsumption(@PathVariable("id") Long id, @Valid @ModelAttribute("energyConsumption") EnergyConsumption energyConsumption, BindingResult result, Model model) {
        if (result.hasErrors()) {
            energyConsumption.setEnergyConsumptionId(id);
            model.addAttribute("tasks", taskService.getAllTasks());
            return "energy-consumptions/edit";
        }
        Optional<Task> task = taskService.getTaskById(energyConsumption.getTask().getTaskId());
        task.ifPresent(energyConsumption::setTask);
        service.saveEnergyConsumption(energyConsumption);
        return "redirect:/energy-consumptions";
    }

    @GetMapping("/delete/{id}")
    public String deleteEnergyConsumption(@PathVariable("id") Long id, Model model) {
        EnergyConsumption energyConsumption = service.getEnergyConsumptionById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid energy consumption Id:" + id));
        service.deleteEnergyConsumption(id);
        return "redirect:/energy-consumptions";
    }
}
