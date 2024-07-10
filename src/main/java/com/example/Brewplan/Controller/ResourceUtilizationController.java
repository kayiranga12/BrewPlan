package com.example.Brewplan.Controller;

import com.example.Brewplan.Model.RawMaterials;
import com.example.Brewplan.Service.RawMaterialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/resource-utilization")
public class ResourceUtilizationController {

    private final RawMaterialsService rawMaterialsService;

    @Autowired
    public ResourceUtilizationController(RawMaterialsService rawMaterialsService) {
        this.rawMaterialsService = rawMaterialsService;
    }

    @GetMapping
    public String getResourceUtilization(Model model) {
        List<RawMaterials> rawMaterialsList = rawMaterialsService.getAllRawMaterials();
        model.addAttribute("rawMaterialsList", rawMaterialsList);

        // Calculate counts for each category
        Map<String, Long> categoryCounts = rawMaterialsList.stream()
                .collect(Collectors.groupingBy(r -> r.getCategory().name(), Collectors.counting()));

        model.addAttribute("categoryCounts", categoryCounts);
        return "resource-utilization/statistics";
    }
}
