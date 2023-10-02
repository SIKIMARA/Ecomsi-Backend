package org.sikimaraservices.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {
                "org.sikimaraservices.orders",
                "org.sikimaraservices.amqp"

        }
)
@EnableFeignClients(
        basePackages = "org.sikimaraservices.clients"
)
public class OrdersApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrdersApplication.class, args);
    }

}
