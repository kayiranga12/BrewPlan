//package com.example.Brewplan.Controller;
//
//import com.example.Brewplan.Model.ResourceAllocation;
//import com.example.Brewplan.Service.RawMaterialsService;
//import com.example.Brewplan.Service.ResourceAllocationService;
//import com.example.Brewplan.Service.TaskService;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/resource-allocations")
//public class ResourceAllocationController {
//
//    @Autowired
//    private ResourceAllocationService service;
//
//    @Autowired
//    private TaskService taskService;
//
//    @Autowired
//    private RawMaterialsService rawMaterialService;
//
//    @GetMapping
//    public String listResourceAllocations(Model model) {
//        model.addAttribute("resourceAllocations", service.getAllResourceAllocations());
//        return "resource-allocations/list";
//    }
//
//    @GetMapping("/add")
//    public String showAddForm(Model model) {
//        model.addAttribute("resourceAllocation", new ResourceAllocation());
//        model.addAttribute("tasks", taskService.getAllTasks());
//        model.addAttribute("materials", rawMaterialService.getAllRawMaterials());
//        return "resource-allocations/add";
//    }
//
//    @PostMapping("/add")
//    public String addResourceAllocation(@Valid @ModelAttribute("resourceAllocation") ResourceAllocation resourceAllocation, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            model.addAttribute("tasks", taskService.getAllTasks());
//            model.addAttribute("materials", rawMaterialService.getAllRawMaterials());
//            return "resource-allocations/add";
//        }
//        service.saveResourceAllocation(resourceAllocation);
//        return "redirect:/resource-allocations";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String showEditForm(@PathVariable("id") Long id, Model model) {
//        ResourceAllocation resourceAllocation = service.getResourceAllocationById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid resource allocation Id:" + id));
//        model.addAttribute("resourceAllocation", resourceAllocation);
//        model.addAttribute("tasks", taskService.getAllTasks());
//        model.addAttribute("materials", rawMaterialService.getAllRawMaterials());
//        return "resource-allocations/edit";
//    }
//
//    @PostMapping("/edit/{id}")
//    public String updateResourceAllocation(@PathVariable("id") Long id, @Valid @ModelAttribute("resourceAllocation") ResourceAllocation resourceAllocation, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            resourceAllocation.setAllocationId(id);
//            model.addAttribute("tasks", taskService.getAllTasks());
//            model.addAttribute("materials", rawMaterialService.getAllRawMaterials());
//            return "resource-allocations/edit";
//        }
//        service.saveResourceAllocation(resourceAllocation);
//        return "redirect:/resource-allocations";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteResourceAllocation(@PathVariable("id") Long id, Model model) {
//        ResourceAllocation resourceAllocation = service.getResourceAllocationById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid resource allocation Id:" + id));
//        service.deleteResourceAllocation(id);
//        return "redirect:/resource-allocations";
//    }
//}
