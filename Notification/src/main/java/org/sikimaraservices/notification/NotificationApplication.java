package org.sikimaraservices.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(
        scanBasePackages = {
                "org.sikimaraservices.notification",
                "org.sikimaraservices.amqp"
        }
)
@PropertySource("classpath:clients-${spring.profiles.active}.properties")

public class NotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

}