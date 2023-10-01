package org.sikimaraservices;
import org.sikimaraservices.products.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductClient productClient;

    public Orders createOrder(Orders order) {
        // Add any business logic or validation before saving the order
        if(order.getOrderItems() != null){
            order.getOrderItems().forEach(orderItem -> {
                orderItem.setOrder(order);
                if(orderItem.getQuantity()<productClient.getQuantityProduct(orderItem.getIdProduct())){
                    productClient.updateQuantityProduct(orderItem.getIdProduct(),productClient.getQuantityProduct(orderItem.getIdProduct())-orderItem.getQuantity());
                }
                else{
                    throw new RuntimeException("Not enough quantity");
                }

            });
        }
        return orderRepository.save(order);
    }

    public Orders getOrderById(Long orderId) {
        // Add error handling and validation here
        return orderRepository.findById(orderId).orElse(null);
    }

    public List<Orders> getOrdersByUserId(Long userId) {
        // Add error handling and validation here
        return orderRepository.findOrdersByUserId(userId);
    }

    // Add methods for updating and deleting orders as needed
}