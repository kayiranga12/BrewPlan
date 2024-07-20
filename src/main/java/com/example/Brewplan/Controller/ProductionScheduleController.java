package com.example.Brewplan.Controller;

import com.example.Brewplan.Model.ProductionSchedule;
import com.example.Brewplan.Service.ProductionPlanService;
import com.example.Brewplan.Service.ProductionScheduleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/production-schedule")
public class ProductionScheduleController {

    @Autowired
    private ProductionScheduleService productionScheduleService;

    @Autowired
    private ProductionPlanService productionPlanService;

    @GetMapping
    public String listAll(Model model) {
        List<ProductionSchedule> productionSchedules = productionScheduleService.getAllProductionSchedules();
        model.addAttribute("productionSchedules", productionSchedules);
        return "production-schedule/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("productionSchedule", new ProductionSchedule());
        model.addAttribute("productionPlans", productionPlanService.getAllProductionPlans());
        return "production-schedule/add";
    }

    @PostMapping("/add")
    public String addSchedule(@Valid @ModelAttribute("productionSchedule") ProductionSchedule productionSchedule, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("productionPlans", productionPlanService.getAllProductionPlans());
            return "production-schedule/add";
        }
        productionScheduleService.saveProductionSchedule(productionSchedule);
        return "redirect:/production-schedule";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        ProductionSchedule productionSchedule = productionScheduleService.getProductionScheduleById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid production schedule Id:" + id));
        model.addAttribute("productionSchedule", productionSchedule);
        model.addAttribute("productionPlans", productionPlanService.getAllProductionPlans());
        return "production-schedule/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateProductionSchedule(@PathVariable("id") Long id, @Valid @ModelAttribute("productionSchedule") ProductionSchedule productionSchedule, BindingResult result, Model model) {
        if (result.hasErrors()) {
            productionSchedule.setScheduleId(id);
            model.addAttribute("productionPlans", productionPlanService.getAllProductionPlans());
            return "production-schedule/edit";
        }
        productionScheduleService.saveProductionSchedule(productionSchedule);
        return "redirect:/production-schedule";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductionSchedule(@PathVariable("id") Long id, Model model) {
        ProductionSchedule productionSchedule = productionScheduleService.getProductionScheduleById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid production schedule Id:" + id));
        productionScheduleService.deleteProductionSchedule(id);
        return "redirect:/production-schedule";
    }

    @GetMapping("/data")
    @ResponseBody
    public List<ProductionSchedule> getProductionScheduleData() {
        return productionScheduleService.getAllProductionSchedules();
    }

}
