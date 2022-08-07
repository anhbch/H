package com.application.puranh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class PuranhApplication {

    public static void main(String[] args) {
        SpringApplication.run(PuranhApplication.class, args);
    }

}
