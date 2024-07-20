package com.example.Brewplan.Controller;

import com.example.Brewplan.Model.EnergyConsumption;
import com.example.Brewplan.Service.EnergyConsumptionService;
import com.example.Brewplan.Service.ProductionPlanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/energy-consumptions")
public class EnergyConsumptionController {

    @Autowired
    private EnergyConsumptionService energyConsumptionService;

    @Autowired
    private ProductionPlanService productionPlanService;

    @GetMapping
    public String listAll(@RequestParam(required = false) String query, Model model) {
        List<EnergyConsumption> energyConsumptions;
        if (query != null && !query.isEmpty()) {
            energyConsumptions = energyConsumptionService.searchByProductName(query);
        } else {
            energyConsumptions = energyConsumptionService.getAllEnergyConsumptions();
        }
        model.addAttribute("energyConsumptions", energyConsumptions);
        return "energy-consumptions/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("energyConsumption", new EnergyConsumption());
        model.addAttribute("productionPlans", productionPlanService.getAllProductionPlans());
        return "energy-consumptions/add";
    }

    @PostMapping("/add")
    public String addEnergyConsumption(@Valid @ModelAttribute("energyConsumption") EnergyConsumption energyConsumption, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("productionPlans", productionPlanService.getAllProductionPlans());
            return "energy-consumptions/add";
        }
        energyConsumption.setRecordDate(LocalDateTime.now());
        energyConsumptionService.saveEnergyConsumption(energyConsumption);
        return "redirect:/energy-consumptions";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        EnergyConsumption energyConsumption = energyConsumptionService.getEnergyConsumptionById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid energy consumption Id:" + id));
        model.addAttribute("energyConsumption", energyConsumption);
        model.addAttribute("productionPlans", productionPlanService.getAllProductionPlans());
        return "energy-consumptions/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateEnergyConsumption(@PathVariable("id") Long id, @Valid @ModelAttribute("energyConsumption") EnergyConsumption energyConsumption, BindingResult result, Model model) {
        if (result.hasErrors()) {
            energyConsumption.setId(id);
            model.addAttribute("productionPlans", productionPlanService.getAllProductionPlans());
            return "energy-consumptions/edit";
        }
        energyConsumptionService.saveEnergyConsumption(energyConsumption);
        return "redirect:/energy-consumptions";
    }

    @GetMapping("/delete/{id}")
    public String deleteEnergyConsumption(@PathVariable("id") Long id) {
        energyConsumptionService.deleteEnergyConsumption(id);
        return "redirect:/energy-consumptions";
    }

    @GetMapping("/data")
    @ResponseBody
    public List<EnergyConsumption> getEnergyConsumptionData() {
        return energyConsumptionService.getAllEnergyConsumptions();
    }
}
