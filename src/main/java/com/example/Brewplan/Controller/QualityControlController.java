package com.example.Brewplan.Controller;

import com.example.Brewplan.Model.QualityControl;
import com.example.Brewplan.Service.QualityControlService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/quality-control")
public class QualityControlController {

    @Autowired
    private QualityControlService qualityControlService;

    @GetMapping
    public String listAll(@RequestParam(required = false) String query, Model model) {
        List<QualityControl> qualityControls;
        if (query != null) {
            qualityControls = qualityControlService.searchByProductName(query);
        } else {
            qualityControls = qualityControlService.getAllQualityControls();
        }
        model.addAttribute("qualityControls", qualityControls);
        return "quality-control/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("qualityControl", new QualityControl());
        return "quality-control/add";
    }

    @PostMapping("/add")
    public String addQualityControl(@Valid @ModelAttribute("qualityControl") QualityControl qualityControl, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "quality-control/add";
        }
        qualityControl.setCheckDate(LocalDateTime.now());
        qualityControlService.saveQualityControl(qualityControl);
        return "redirect:/quality-control";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        QualityControl qualityControl = qualityControlService.getQualityControlById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid quality control Id:" + id));
        model.addAttribute("qualityControl", qualityControl);
        return "quality-control/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateQualityControl(@PathVariable("id") Long id, @Valid @ModelAttribute("qualityControl") QualityControl qualityControl, BindingResult result, Model model) {
        if (result.hasErrors()) {
            qualityControl.setQcId(id);
            return "quality-control/edit";
        }
        qualityControlService.saveQualityControl(qualityControl);
        return "redirect:/quality-control";
    }

    @GetMapping("/delete/{id}")
    public String deleteQualityControl(@PathVariable("id") Long id) {
        qualityControlService.deleteQualityControl(id);
        return "redirect:/quality-control";
    }

    @GetMapping("/data")
    @ResponseBody
    public List<QualityControl> getQualityControlData() {
        return qualityControlService.getAllQualityControls();
    }
}
