package com.example.Brewplan.Controller;

import com.example.Brewplan.Model.Supplier;
import com.example.Brewplan.Service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public String getAllSuppliers(Model model) {
        List<Supplier> suppliers = supplierService.getAllSuppliers();
        model.addAttribute("suppliers", suppliers);
        return "suppliers/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("supplier", new Supplier());
        return "suppliers/add";
    }

    @PostMapping("/add")
    public String addSupplier(@Valid @ModelAttribute("supplier") Supplier supplier, BindingResult result) {
        if (result.hasErrors()) {
            return "suppliers/add";
        }
        supplierService.addSupplier(supplier);
        return "redirect:/suppliers";
    }

    @GetMapping("/delete/{id}")
    public String deleteSupplier(@PathVariable("id") Long id) {
        supplierService.deleteSupplier(id);
        return "redirect:/suppliers";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Supplier supplier = supplierService.getSupplierById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid supplier Id:" + id));
        model.addAttribute("supplier", supplier);
        return "suppliers/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateSupplier(@PathVariable("id") Long id, @Valid @ModelAttribute("supplier") Supplier supplier, BindingResult result) {
        if (result.hasErrors()) {
            supplier.setSupplierId(id);
            return "suppliers/edit";
        }
        supplierService.updateSupplier(id, supplier);
        return "redirect:/suppliers";
    }
}

