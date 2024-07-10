package com.example.Brewplan.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "raw_materials")
public class RawMaterials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long materialId;

    private String materialName;

    @Enumerated(EnumType.STRING)
    private Category category;

    private int currentStock;

    private int minimumStockLevel;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    public enum Category {
        PACKAGE,
        INGREDIENTS,
        MACHINE
    }

    public boolean needsReplenishment() {
        return currentStock < minimumStockLevel;
    }
}
