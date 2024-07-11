package com.example.Brewplan.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplierId;
    private String supplierName;
    private String contactName;
    private String contactEmail;
    private String contactPhone;
    private String address;
    private int onTimeDeliveryRate;
    private int defectRate;
    private int orderFulfillmentAccuracy;
    private int rating;

    // Getters and setters omitted for brevity

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    // Other getters and setters
}
