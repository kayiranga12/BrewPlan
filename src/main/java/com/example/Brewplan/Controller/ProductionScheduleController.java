package com.example.Brewplan.Controller;

import com.example.Brewplan.Model.ProductionSchedule;
import com.example.Brewplan.Service.ProductionScheduleService;
import com.example.Brewplan.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/production-schedule")
public class ProductionScheduleController {

    @Autowired
    private ProductionScheduleService productionScheduleService;

    @Autowired
    private TaskService taskService;

    @GetMapping
    public String getAllSchedules(Model model) {
        model.addAttribute("schedules", productionScheduleService.getAllSchedules());
        model.addAttribute("tasks", taskService.getAllTasks());
        model.addAttribute("schedule", new ProductionSchedule());
        return "production-schedule/list";  // Name of the Thymeleaf template
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("schedule", new ProductionSchedule());
        model.addAttribute("tasks", taskService.getAllTasks());
        return "production-schedule/add";  // Name of the Thymeleaf template for adding
    }

    @PostMapping("/add")
    public String addSchedule(@ModelAttribute ProductionSchedule schedule) {
        productionScheduleService.saveSchedule(schedule);
        return "redirect:/production-schedule";
    }

    @GetMapping("/edit/{id}")
    public String editSchedule(@PathVariable("id") Long id, Model model) {
        ProductionSchedule schedule = productionScheduleService.getScheduleById(id).orElseThrow(() -> new IllegalArgumentException("Invalid schedule Id:" + id));
        model.addAttribute("schedule", schedule);
        model.addAttribute("tasks", taskService.getAllTasks());
        return "production-schedule/edit";  // Name of the Thymeleaf template for editing
    }

    @PostMapping("/update/{id}")
    public String updateSchedule(@PathVariable("id") Long id, @ModelAttribute ProductionSchedule schedule) {
        schedule.setScheduleId(id);
        productionScheduleService.saveSchedule(schedule);
        return "redirect:/production-schedule";
    }

    @GetMapping("/delete/{id}")
    public String deleteSchedule(@PathVariable("id") Long id) {
        productionScheduleService.deleteSchedule(id);
        return "redirect:/production-schedule";
    }
}
