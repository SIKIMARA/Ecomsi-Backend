package org.sikimaraservices.products;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name="PRODUCTS"
)
public interface ProductClient {
    @GetMapping(path = "/products/getQuanity/{id}")
    public Integer getQuantityProduct(@RequestParam int id);
    @PutMapping(path = "/products/updateQuantity/{id}")
    public String updateQuantityProduct(@RequestParam int id, @RequestParam int quantity);
    @GetMapping("/getPrice/{id}")
    public Double getPrice(@RequestParam int id);
}
