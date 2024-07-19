package com.example.Brewplan.Controller;

import com.example.Brewplan.Model.Inventory;
import com.example.Brewplan.Service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public String listAll(Model model) {
        List<Inventory> inventories = inventoryService.getAllInventories();
        model.addAttribute("inventories", inventories);
        return "inventory/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("inventory", new Inventory());
        return "inventory/add";
    }

    @PostMapping("/add")
    public String addInventory(@Valid @ModelAttribute("inventory") Inventory inventory, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "inventory/add";
        }
        inventory.setLastUpdated(LocalDateTime.now());
        inventoryService.saveInventory(inventory);
        return "redirect:/inventory";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Inventory inventory = inventoryService.getInventoryById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid inventory Id:" + id));
        model.addAttribute("inventory", inventory);
        return "inventory/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateInventory(@PathVariable("id") Long id, @Valid @ModelAttribute("inventory") Inventory inventory, BindingResult result, Model model) {
        if (result.hasErrors()) {
            inventory.setInventoryId(id);
            return "inventory/edit";
        }
        inventory.setLastUpdated(LocalDateTime.now());
        inventoryService.saveInventory(inventory);
        return "redirect:/inventory";
    }

    @GetMapping("/delete/{id}")
    public String deleteInventory(@PathVariable("id") Long id) {
        inventoryService.deleteInventory(id);
        return "redirect:/inventory";
    }
}
