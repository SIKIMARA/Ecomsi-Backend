package com.sikimara.products;

import com.sikimara.products.Reviews.Reviews;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Reviews> reviews;

}
