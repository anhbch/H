package com.application.puranh;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class PuranhApplication {

    @Value("${cloudName}")
    private  String cloudName;
    @Value("${apiKey}")
    private  String apiKey;
    @Value("${apiSecret}")
    private  String apiSecret;

    public static void main(String[] args) {
        SpringApplication.run(PuranhApplication.class, args);
    }

    @Bean
    public Cloudinary cloudinaryConfig() {
        Cloudinary cloudinary = null;
        Map config = new HashMap();
        config.put("cloud_name", cloudName);
        config.put("api_key", apiKey);
        config.put("api_secret",apiSecret);
        cloudinary = new Cloudinary(config);
        return cloudinary;
    }


}
