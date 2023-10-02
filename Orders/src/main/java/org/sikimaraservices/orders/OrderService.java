package org.sikimaraservices.orders;
import org.sikimaraservices.amqp.RabbitMQMessageProducer;
import org.sikimaraservices.clients.notification.NotificationRequest;
import org.sikimaraservices.clients.products.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductClient productClient;
    @Autowired
    private RabbitMQMessageProducer rabbitMQMessageProducer;
    public Orders createOrder(Orders order) {


        if(order.getOrderItems() != null){
            order.getOrderItems().forEach(orderItem -> {
                orderItem.setOrder(order);
                System.out.println(orderItem.getQuantity());
                System.out.println(productClient.getQuantityProduct(orderItem.getIdProduct()));
                if(orderItem.getQuantity()<=productClient.getQuantityProduct(orderItem.getIdProduct())){
                    productClient.updateQuantityProduct(orderItem.getIdProduct(),productClient.getQuantityProduct(orderItem.getIdProduct())-orderItem.getQuantity());
                }
                else{
                    throw new RuntimeException("Not enough quantity");
                }

            });
        }
        // Construct the HTML email body
        StringBuilder emailBody = new StringBuilder();
        emailBody.append("<h1>Order Review</h1>");
        emailBody.append("<p>Hello ").append(order.getFullName()).append(",</p>");
        emailBody.append("<p>Your order has been successfully \uD83C\uDF89\uD83C\uDF89\uD83C\uDF89  placed with the following details:</p>");
        emailBody.append("<table>");
        emailBody.append("<thead><tr><th>Product</th><th>Quantity</th><th>Total Price</th></tr></thead>");
        emailBody.append("<tbody>");

        // Loop through order items and populate the table
        for (OrderItem orderItem : order.getOrderItems()) {
            emailBody.append("<tr>");
            emailBody.append("<td>").append(orderItem.getProduct()).append("</td>");
            emailBody.append("<td>").append(orderItem.getQuantity()).append("</td>");
            emailBody.append("<td>").append(orderItem.getTotalPrice()).append("</td>");
            emailBody.append("</tr>");
        }

        emailBody.append("</tbody></table>");
        emailBody.append("<p>Shipping Information:</p>");
        emailBody.append("<p>Full Name: ").append(order.getFullName()).append("</p>");
        emailBody.append("<p>Email: ").append(order.getEmail()).append("</p>");
        emailBody.append("<p>Address: ").append(order.getAddress()).append("</p>");
        emailBody.append("<p>City: ").append(order.getCity()).append("</p>");
        emailBody.append("<p>Country: ").append(order.getCountry()).append("</p>");
        emailBody.append("<p>Phone: ").append(order.getPhone()).append("</p>");
        emailBody.append("<p>Postal Code: ").append(order.getPostalCode()).append("</p>");
        emailBody.append("<p>Thank you for shopping with us!</p>");

        // Send the email with the emailBody string
        rabbitMQMessageProducer.publish(
                new NotificationRequest(
                        order.getEmail(),
                        "Order created",
                        emailBody.toString()
                ),
                "internal.exchange",
                "internal.notification.routing-key"
        );


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