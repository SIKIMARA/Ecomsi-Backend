package com.sikimara.products;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Products,Integer> {

    public List<Products> findTop4ByOrderByQuantityAsc();
    public List<Products> findTop4ByOrderByQuantityDesc();
    public List<Products> findTop4ByOrderByPriceAsc();

    public Double findPriceById(Integer id);
}
