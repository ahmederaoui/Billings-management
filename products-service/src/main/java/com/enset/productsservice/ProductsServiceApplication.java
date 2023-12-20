package com.enset.productsservice;

import com.enset.productsservice.entities.Product;
import com.enset.productsservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductsServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(ProductRepository productRepository){

        return args -> {

            productRepository.save(new Product(null,"LapTop", 134.0));
            productRepository.save(new Product(null,"Phone", 1253.0));
            productRepository.save(new Product(null,"DeskTop", 2315.0));
            productRepository.findAll().forEach(System.out::println);

        };

    }
}
