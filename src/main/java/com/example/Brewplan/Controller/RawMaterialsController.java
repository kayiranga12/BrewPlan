package com.example.Brewplan.Controller;

import com.example.Brewplan.Model.RawMaterials;
import com.example.Brewplan.Service.RawMaterialsService;
import com.example.Brewplan.Service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/raw-materials")
public class RawMaterialsController {

    @Autowired
    private RawMaterialsService rawMaterialsService;

    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public String getAllRawMaterials(Model model) {
        List<RawMaterials> rawMaterialsList = rawMaterialsService.getAllRawMaterials();
        model.addAttribute("rawMaterialsList", rawMaterialsList);
        return "raw-materials/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("rawMaterials", new RawMaterials());
        model.addAttribute("suppliers", supplierService.getAllSuppliers());
        return "raw-materials/add";
    }

    @PostMapping("/add")
    public String addRawMaterial(@Valid @ModelAttribute("rawMaterials") RawMaterials rawMaterial, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("suppliers", supplierService.getAllSuppliers());
            return "raw-materials/add";
        }
        rawMaterialsService.addRawMaterial(rawMaterial);
        return "redirect:/raw-materials";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        RawMaterials rawMaterials = rawMaterialsService.getRawMaterialsById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid material Id:" + id));
        model.addAttribute("rawMaterials", rawMaterials);
        model.addAttribute("suppliers", supplierService.getAllSuppliers());
        return "raw-materials/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateRawMaterials(@PathVariable("id") Long id, @Valid @ModelAttribute("rawMaterials") RawMaterials rawMaterials, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("suppliers", supplierService.getAllSuppliers());
            rawMaterials.setMaterialId(id);
            return "raw-materials/edit";
        }
        rawMaterialsService.updateRawMaterial(id, rawMaterials);
        return "redirect:/raw-materials";
    }

    @GetMapping("/delete/{id}")
    public String deleteRawMaterials(@PathVariable("id") Long id) {
        rawMaterialsService.deleteRawMaterials(id);
        return "redirect:/raw-materials";
    }
}
