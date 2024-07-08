//package com.example.Brewplan.Controller;
//
//import com.example.Brewplan.Model.Schedule;
//import com.example.Brewplan.Service.ScheduleService;
//import com.example.Brewplan.Service.TaskService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/production-schedule")
//public class ScheduleController {
//
//    @Autowired
//    private ScheduleService scheduleService;
//
//    @Autowired
//    private TaskService taskService;
//
//    @GetMapping
//    public String getAllSchedules(Model model) {
//        model.addAttribute("schedules", scheduleService.getAllSchedules());
//        model.addAttribute("tasks", taskService.getAllTasks());
//        model.addAttribute("schedule", new Schedule());
//        return "production-schedule";  // Name of the Thymeleaf template
//    }
//
//    @PostMapping("/add")
//    public String addSchedule(@ModelAttribute Schedule schedule) {
//        scheduleService.saveSchedule(schedule);
//        return "redirect:/production-schedule";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String editSchedule(@PathVariable("id") Long id, Model model) {
//        Schedule schedule = scheduleService.getScheduleById(id).orElseThrow(() -> new IllegalArgumentException("Invalid schedule Id:" + id));
//        model.addAttribute("schedule", schedule);
//        model.addAttribute("tasks", taskService.getAllTasks());
//        return "edit-schedule";  // Name of the Thymeleaf template for editing
//    }
//
//    @PostMapping("/update/{id}")
//    public String updateSchedule(@PathVariable("id") Long id, @ModelAttribute Schedule schedule) {
//        schedule.setScheduleId(id);
//        scheduleService.saveSchedule(schedule);
//        return "redirect:/production-schedule";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteSchedule(@PathVariable("id") Long id) {
//        scheduleService.deleteSchedule(id);
//        return "redirect:/production-schedule";
//    }
//}
//
