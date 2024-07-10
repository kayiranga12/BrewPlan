package com.example.Brewplan.Controller;

import com.example.Brewplan.Model.RawMaterials;
import com.example.Brewplan.Service.RawMaterialsService;
import com.example.Brewplan.Service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/raw-materials")
public class RawMaterialsController {

    private final RawMaterialsService rawMaterialsService;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    public RawMaterialsController(RawMaterialsService rawMaterialsService) {
        this.rawMaterialsService = rawMaterialsService;
    }

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

    @GetMapping("/delete/{id}")
    public String deleteRawMaterials(@PathVariable("id") Long id) {
        rawMaterialsService.deleteRawMaterials(id);
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
            rawMaterials.setMaterialId(id);
            model.addAttribute("suppliers", supplierService.getAllSuppliers());
            return "raw-materials/edit";
        }
        rawMaterialsService.addRawMaterial(rawMaterials);
        return "redirect:/raw-materials";
    }

    @PostMapping("/update-inline")
    @ResponseBody
    public ResponseEntity<?> updateInline(@RequestBody Map<String, String> data) {
        Long id = Long.parseLong(data.get("id"));
        String column = data.get("column");
        String newValue = data.get("newValue");

        Optional<RawMaterials> rawMaterialsOptional = rawMaterialsService.getRawMaterialsById(id);
        if (rawMaterialsOptional.isPresent()) {
            RawMaterials rawMaterials = rawMaterialsOptional.get();
            switch (column) {
                case "materialName":
                    rawMaterials.setMaterialName(newValue);
                    break;
                case "category":
                    rawMaterials.setCategory(RawMaterials.Category.valueOf(newValue));
                    break;
                case "currentStock":
                    rawMaterials.setCurrentStock(Integer.parseInt(newValue));
                    break;
                case "minimumStockLevel":
                    rawMaterials.setMinimumStockLevel(Integer.parseInt(newValue));
                    break;
                case "supplier":
                    rawMaterials.setSupplier(supplierService.findBySupplierName(newValue));
                    break;
                default:
                    return ResponseEntity.badRequest().body("Invalid column");
            }
            rawMaterialsService.addRawMaterial(rawMaterials);
            return ResponseEntity.ok().body("Update successful");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Raw material not found");
        }
    }

    @GetMapping("/alerts")
    public String getMaterialsNeedingReplenishment(Model model) {
        List<RawMaterials> materialsNeedingReplenishment = rawMaterialsService.getMaterialsNeedingReplenishment();
        model.addAttribute("materialsNeedingReplenishment", materialsNeedingReplenishment);
        return "raw-materials/alerts";
    }

    @GetMapping("/search")
    @ResponseBody
    public List<RawMaterials> searchRawMaterials(@RequestParam("query") String query) {
        return rawMaterialsService.searchRawMaterials(query);
    }


}
