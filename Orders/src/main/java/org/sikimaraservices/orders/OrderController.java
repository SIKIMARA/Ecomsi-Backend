package org.sikimaraservices.orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public String createOrder(@RequestBody Orders order) {
        Orders order1;
        if(orderService.createOrder(order)==null){
            throw new RuntimeException("Not enough quantity");
        }
        else{
            return "Order created";

        }
    }
    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @GetMapping("/{orderId}")
    public Orders getOrder(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }

    @GetMapping("/user/{userId}")
    public List<Orders> getOrdersByUser(@PathVariable Long userId) {
        return orderService.getOrdersByUserId(userId);
    }

    // Add more endpoints for updating and deleting orders as needed
}