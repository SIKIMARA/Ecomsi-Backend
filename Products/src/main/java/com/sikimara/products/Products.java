package com.sikimara.products;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Products {
    @Id
    @GeneratedValue
    private int id;
    private String productName;
    private String des;
    private float price;
    private int quantity;
    @Column(columnDefinition = "TEXT")
    private String img;
    private boolean badge;
    private String color;
    private String brand;
    private String category;

}
