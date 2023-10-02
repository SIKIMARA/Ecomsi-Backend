package org.sikimaraservices.clients.products;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name="products"
)
public interface ProductClient {
    @GetMapping("/products/test")
    public String test();
    @GetMapping(path="/products/getQuantity/{id}")
    public Integer getQuantityProduct( @PathVariable("id") Integer id);
    @PostMapping("/products/updateQuantity/{id}/quantity/{quantity}")
    public String updateQuantityProduct(@PathVariable("id") int id, @PathVariable("quantity") int quantity);
}
