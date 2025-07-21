package com.example.place_data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.example.place_data.controller.client")
@SpringBootApplication
public class PlaceDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlaceDataApplication.class, args);
    }

}
