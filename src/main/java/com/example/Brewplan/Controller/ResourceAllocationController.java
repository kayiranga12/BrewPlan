package com.example.Brewplan.Controller;

import com.example.Brewplan.Model.ResourceAllocation;
import com.example.Brewplan.Service.ProductionPlanService;
import com.example.Brewplan.Service.ResourceAllocationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/resource-allocations")
public class ResourceAllocationController {

    @Autowired
    private ResourceAllocationService resourceAllocationService;

    @Autowired
    private ProductionPlanService productionPlanService;

    @GetMapping
    public String listAll(Model model) {
        List<ResourceAllocation> resourceAllocations = resourceAllocationService.getAllResourceAllocations();
        model.addAttribute("resourceAllocations", resourceAllocations);
        return "resource-allocations/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("resourceAllocation", new ResourceAllocation());
        model.addAttribute("productionPlans", productionPlanService.getAllProductionPlans());
        return "resource-allocations/add";
    }

    @PostMapping("/add")
    public String addResourceAllocation(@Valid @ModelAttribute("resourceAllocation") ResourceAllocation resourceAllocation, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("productionPlans", productionPlanService.getAllProductionPlans());
            return "resource-allocations/add";
        }
        resourceAllocationService.saveResourceAllocation(resourceAllocation);
        return "redirect:/resource-allocations";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        ResourceAllocation resourceAllocation = resourceAllocationService.getResourceAllocationById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid resource allocation Id:" + id));
        model.addAttribute("resourceAllocation", resourceAllocation);
        model.addAttribute("productionPlans", productionPlanService.getAllProductionPlans());
        return "resource-allocations/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateResourceAllocation(@PathVariable("id") Long id, @Valid @ModelAttribute("resourceAllocation") ResourceAllocation resourceAllocation, BindingResult result, Model model) {
        if (result.hasErrors()) {
            resourceAllocation.setAllocationId(id);
            model.addAttribute("productionPlans", productionPlanService.getAllProductionPlans());
            return "resource-allocations/edit";
        }
        resourceAllocationService.saveResourceAllocation(resourceAllocation);
        return "redirect:/resource-allocations";
    }

    @GetMapping("/delete/{id}")
    public String deleteResourceAllocation(@PathVariable("id") Long id) {
        resourceAllocationService.deleteResourceAllocation(id);
        return "redirect:/resource-allocations";
    }
    @GetMapping("/data")
    @ResponseBody
    public List<ResourceAllocation> getResourceAllocationData() {
        return resourceAllocationService.getAllResourceAllocations();
    }

}
