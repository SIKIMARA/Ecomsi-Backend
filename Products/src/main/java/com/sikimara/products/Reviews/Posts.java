package com.sikimara.products.Reviews;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Posts {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String body;
    private String email;
}
