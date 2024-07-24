package com.example.Brewplan.Controller;

import com.example.Brewplan.Model.Batch;
import com.example.Brewplan.Service.BatchService;
import com.example.Brewplan.Service.ProductionPlanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/batches")
public class BatchController {

    @Autowired
    private BatchService batchService;

    @Autowired
    private ProductionPlanService productionPlanService;

    @GetMapping
    public String listAll(Model model) {
        List<Batch> batches = batchService.getAllBatches();
        model.addAttribute("batches", batches);
        return "batches/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("batch", new Batch());
        model.addAttribute("productionPlans", productionPlanService.getAllProductionPlans());
        return "batches/add";
    }

    @PostMapping("/add")
    public String addBatch(@Valid @ModelAttribute("batch") Batch batch, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("productionPlans", productionPlanService.getAllProductionPlans());
            return "batches/add";
        }
        batchService.saveBatch(batch);
        return "redirect:/batches";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Batch batch = batchService.getBatchById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid batch Id:" + id));
        model.addAttribute("batch", batch);
        model.addAttribute("productionPlans", productionPlanService.getAllProductionPlans());
        return "batches/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateBatch(@PathVariable("id") Long id, @Valid @ModelAttribute("batch") Batch batch, BindingResult result, Model model) {
        if (result.hasErrors()) {
            batch.setBatchId(id);
            model.addAttribute("productionPlans", productionPlanService.getAllProductionPlans());
            return "batches/edit";
        }
        batchService.saveBatch(batch);
        return "redirect:/batches";
    }

    @GetMapping("/delete/{id}")
    public String deleteBatch(@PathVariable("id") Long id) {
        batchService.deleteBatch(id);
        return "redirect:/batches";
    }
    @GetMapping("/data")
    @ResponseBody
    public List<Batch> getBatchData() {
        return batchService.getAllBatches();
    }

}
