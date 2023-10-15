package org.sikimaraservices.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(
        scanBasePackages = {
                "org.sikimaraservices.orders",
                "org.sikimaraservices.amqp"

        }
)
@EnableFeignClients(
        basePackages = "org.sikimaraservices.clients"
)
@PropertySource("classpath:clients-${spring.profiles.active}.properties")

public class OrdersApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrdersApplication.class, args);
    }

}
