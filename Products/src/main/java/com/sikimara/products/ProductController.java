package com.sikimara.products;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    ProductRepository productRepository;
    @GetMapping("/all")
    public List<Products> getAllProducts(){
        return productRepository.findAll();
    }
    @PostMapping("/create")
    public Products createProduct(@RequestBody Products products){
        return productRepository.save(products);
    }
    @PutMapping("/update/{id}")
    public Products updateProduct(@RequestBody Products products, @RequestParam int id){
        products.setId(id);
        return productRepository.save(products);
    }
    @GetMapping("/test")
    public String test(){
        return "test";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@RequestParam int id){
        if(productRepository.findById(id).isEmpty())
            return "Product Not Found";
        productRepository.deleteById(id);
        return "Product Deleted";
    }
    @GetMapping("/get/{id}")
    public Products getProduct(@RequestParam int id){
        return productRepository.findById(id).orElse(
                null
        );
    }
    @GetMapping("/getQuantity/{id}")
    public Integer getQuantityProduct(@PathVariable("id") Integer id){
        if(productRepository.findById(id).isEmpty())
            return 0;
        else return productRepository.findById(id).get().getQuantity();


    }
    @PostMapping("/updateQuantity/{id}/quantity/{quantity}")
    public String updateQuantityProduct(@PathVariable("id") int id, @PathVariable("quantity") int quantity){
        Products products = productRepository.findById(id).get();
        products.setQuantity(quantity);
        productRepository.save(products);
        return "Quantity Updated";
    }
    @GetMapping("/NewArrivals")
    public List<Products> getNewArrivals(){
        return productRepository.findTop4ByOrderByQuantityDesc();
    }
    @GetMapping("/BestSellers")
    public List<Products> getBestSellers(){
        return productRepository.findTop4ByOrderByQuantityAsc();
    }
    @GetMapping("/SpecialOffers")
    public List<Products> getSpecialOffers(){
        return productRepository.findTop4ByOrderByPriceAsc();
    }
    @GetMapping("/getPrice/{id}")
    public Double getPrice(@RequestParam int id){
        return productRepository.findPriceById(id);
    }
}
