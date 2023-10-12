package com.sikimara.products.Reviews;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sikimara.products.Products;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Reviews {
    @Id
    @GeneratedValue
    private int id;
    private int rating;
    private String comment;
    @ManyToOne
    @JsonBackReference
    private Products product;
    private String user_name;
    private String user_email;
    private String date;
    private String user_id;

}
