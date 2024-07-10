package com.example.Brewplan.Controller;

import com.example.Brewplan.Model.ProductionPlan;
import com.example.Brewplan.Model.ProductionSchedule;
import com.example.Brewplan.Service.ProductionPlanService;
import com.example.Brewplan.Service.ProductionScheduleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/production-plans")
public class ProductionPlanController {

    @Autowired
    private ProductionPlanService productionPlanService;

    @Autowired
    private ProductionScheduleService productionScheduleService;

    @GetMapping
    public String listProductionPlans(Model model) {
        List<ProductionPlan> plans = productionPlanService.getAllProductionPlans();
        model.addAttribute("productionPlans", plans);

        double totalEfficiency = plans.stream().mapToDouble(productionPlanService::calculateEfficiency).average().orElse(0);
        double totalDowntime = plans.stream().mapToDouble(productionPlanService::calculateDowntime).average().orElse(0);
        double totalCapacityUtilization = plans.stream().mapToDouble(productionPlanService::calculateCapacityUtilization).average().orElse(0);

        int totalMaterialUsed = plans.stream().mapToInt(productionPlanService::calculateMaterialUsed).sum();
        int totalLaborHours = plans.stream().mapToInt(productionPlanService::calculateLaborHours).sum();
        int totalMachineUtilization = plans.stream().mapToInt(productionPlanService::calculateMachineUtilization).sum();

        model.addAttribute("totalEfficiency", totalEfficiency);
        model.addAttribute("totalDowntime", totalDowntime);
        model.addAttribute("totalCapacityUtilization", totalCapacityUtilization);
        model.addAttribute("totalMaterialUsed", totalMaterialUsed);
        model.addAttribute("totalLaborHours", totalLaborHours);
        model.addAttribute("totalMachineUtilization", totalMachineUtilization);

        return "production-plans/list";
    }

    @GetMapping("/schedules")
    public String getAllSchedules(Model model) {
        List<ProductionSchedule> schedules = productionScheduleService.getAllSchedules();
        model.addAttribute("schedules", schedules);

        // Convert schedule data to JSON format
        List<String> events = schedules.stream().map(schedule -> {
            String taskName = schedule.getTask().getTaskName();
            String start = schedule.getScheduledStart().toString();
            String end = schedule.getScheduledEnd().toString();
            return String.format("{title: '%s', start: '%s', end: '%s'}", taskName, start, end);
        }).collect(Collectors.toList());

        model.addAttribute("events", events);
        return "production-schedule/production-schedule-calendar";
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
        productionPlan.setCreateDate(LocalDateTime.now());
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

    @GetMapping("/approve/{id}")
    public String showApprovalForm(@PathVariable("id") Long id, Model model) {
        ProductionPlan productionPlan = productionPlanService.getProductionPlanById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid production plan Id:" + id));
        model.addAttribute("productionPlan", productionPlan);
        return "production-plans/approve";
    }

    @PostMapping("/approve/{id}")
    public String approveProductionPlan(@PathVariable("id") Long id, @RequestParam("approvalStatus") String approvalStatus,
                                        @RequestParam("approvalComments") String approvalComments, Model model) {
        ProductionPlan productionPlan = productionPlanService.getProductionPlanById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid production plan Id:" + id));
        productionPlan.setApprovalStatus(approvalStatus);
        productionPlan.setApprovalComments(approvalComments);
        productionPlanService.saveProductionPlan(productionPlan);
        return "redirect:/production-plans";
    }

    @GetMapping("/notifications")
    public String getNotifications(Model model) {
        List<ProductionPlan> pendingApprovals = productionPlanService.getPendingApprovals();
        List<ProductionPlan> resourceShortages = productionPlanService.getResourceShortages();

        model.addAttribute("pendingApprovals", pendingApprovals);
        model.addAttribute("resourceShortages", resourceShortages);
        return "production-plans/notifications";
    }
}
