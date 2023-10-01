package org.sikimaraservices;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int idProduct;
    private String product;

    private int quantity;
    private int totalPrice;

    @ManyToOne
    private Orders order;

    // Getters and setters
}
