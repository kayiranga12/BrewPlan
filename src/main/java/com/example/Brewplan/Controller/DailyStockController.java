package com.example.Brewplan.Controller;

import com.example.Brewplan.Model.DailyStock;
import com.example.Brewplan.Service.DailyStockService;
import com.example.Brewplan.Service.ProductionPlanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/daily-stocks")
public class DailyStockController {

    @Autowired
    private DailyStockService dailyStockService;

    @Autowired
    private ProductionPlanService productionPlanService;

    @GetMapping
    public String listAll(Model model) {
        List<DailyStock> dailyStocks = dailyStockService.getAllDailyStocks();
        model.addAttribute("dailyStocks", dailyStocks);
        return "daily-stocks/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("dailyStock", new DailyStock());
        model.addAttribute("productionPlans", productionPlanService.getAllProductionPlans());
        return "daily-stocks/add";
    }

    @PostMapping("/add")
    public String addDailyStock(@Valid @ModelAttribute("dailyStock") DailyStock dailyStock, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("productionPlans", productionPlanService.getAllProductionPlans());
            return "daily-stocks/add";
        }
        dailyStockService.saveDailyStock(dailyStock);
        return "redirect:/daily-stocks";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        DailyStock dailyStock = dailyStockService.getDailyStockById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid daily stock Id:" + id));
        model.addAttribute("dailyStock", dailyStock);
        model.addAttribute("productionPlans", productionPlanService.getAllProductionPlans());
        return "daily-stocks/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateDailyStock(@PathVariable("id") Long id, @Valid @ModelAttribute("dailyStock") DailyStock dailyStock, BindingResult result, Model model) {
        if (result.hasErrors()) {
            dailyStock.setId(id);
            model.addAttribute("productionPlans", productionPlanService.getAllProductionPlans());
            return "daily-stocks/edit";
        }
        dailyStockService.saveDailyStock(dailyStock);
        return "redirect:/daily-stocks";
    }

    @GetMapping("/delete/{id}")
    public String deleteDailyStock(@PathVariable("id") Long id) {
        dailyStockService.deleteDailyStock(id);
        return "redirect:/daily-stocks";
    }
}
