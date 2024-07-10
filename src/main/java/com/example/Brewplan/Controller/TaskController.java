package com.example.Brewplan.Controller;

import com.example.Brewplan.Model.Task;
import com.example.Brewplan.Service.ProductionPlanService;
import com.example.Brewplan.Service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService service;

    @Autowired
    private ProductionPlanService productionPlanService;

    @GetMapping
    public String listTasks(Model model) {
        model.addAttribute("tasks", service.getAllTasks());
        return "tasks/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("productionPlans", productionPlanService.getAllProductionPlans());
        return "tasks/add";
    }

    @PostMapping("/add")
    public String addTask(@Valid @ModelAttribute("task") Task task, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("productionPlans", productionPlanService.getAllProductionPlans());
            return "tasks/add";
        }
        service.saveTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Task task = service.getTaskById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid task Id:" + id));
        model.addAttribute("task", task);
        model.addAttribute("productionPlans", productionPlanService.getAllProductionPlans());
        return "tasks/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateTask(@PathVariable("id") Long id, @Valid @ModelAttribute("task") Task task, BindingResult result, Model model) {
        if (result.hasErrors()) {
            task.setTaskId(id);
            model.addAttribute("productionPlans", productionPlanService.getAllProductionPlans());
            return "tasks/edit";
        }
        service.deleteTask(id);
        service.saveTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable("id") Long id, Model model) {
        Task task = service.getTaskById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid task Id:" + id));
        service.deleteTask(id);
        return "redirect:/tasks";
    }
}
