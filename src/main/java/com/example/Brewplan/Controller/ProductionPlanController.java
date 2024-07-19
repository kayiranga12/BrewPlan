package com.example.Brewplan.Controller;

import com.example.Brewplan.Model.ProductionPlan;
import com.example.Brewplan.Service.ProductionPlanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/production-plans")
public class ProductionPlanController {

    @Autowired
    private ProductionPlanService productionPlanService;

    @GetMapping
    public String listAll(Model model) {
        List<ProductionPlan> productionPlans = productionPlanService.getAllProductionPlans();
        model.addAttribute("productionPlans", productionPlans);
        return "production-plans/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("productionPlan", new ProductionPlan());
        return "production-plans/add";
    }

    @PostMapping("/add")
    public String addProductionPlan(@Valid @ModelAttribute("productionPlan") ProductionPlan productionPlan, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "production-plans/add";
        }
        productionPlanService.saveProductionPlan(productionPlan);
        return "redirect:/production-plans";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        ProductionPlan productionPlan = productionPlanService.getProductionPlanById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid production plan Id:" + id));
        model.addAttribute("productionPlan", productionPlan);
        return "production-plans/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateProductionPlan(@PathVariable("id") Long id, @Valid @ModelAttribute("productionPlan") ProductionPlan productionPlan, BindingResult result, Model model) {
        if (result.hasErrors()) {
            productionPlan.setPlanId(id);
            return "production-plans/edit";
        }
        productionPlanService.saveProductionPlan(productionPlan);
        return "redirect:/production-plans";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductionPlan(@PathVariable("id") Long id, Model model) {
        ProductionPlan productionPlan = productionPlanService.getProductionPlanById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid production plan Id:" + id));
        productionPlanService.deleteProductionPlan(id);
        return "redirect:/production-plans";
    }
}
