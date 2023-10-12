package com.sikimara.products.Reviews;

import com.sikimara.products.Products;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
    private int id;
    private int rating;
    private String comment;
    @ManyToOne
    private Products product;
    private String user_name;
    private String user_email;
    private String date;
    private String user_id;

}
