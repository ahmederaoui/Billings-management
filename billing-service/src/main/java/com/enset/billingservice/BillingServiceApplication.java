package com.enset.billingservice;

import com.enset.billingservice.entities.Bill;
import com.enset.billingservice.entities.ProductItem;
import com.enset.billingservice.feignServices.ICustomerService;
import com.enset.billingservice.feignServices.IProductService;
import com.enset.billingservice.models.Customer;
import com.enset.billingservice.repositories.BillRepository;
import com.enset.billingservice.repositories.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(BillRepository billRepository, ProductItemRepository productItemRepository,
                            IProductService inventoryServiceClient, ICustomerService customerServiceClient){

        return args -> {

            Bill bill=new Bill();
            bill.setBillingDate(new Date());
            //new Customer(1L,"eraoui","ahmed@eraoui.com");
            Customer customer=customerServiceClient.findCustomerById(1L);
            bill.setCustomerID(1L);
            billRepository.save(bill);
            inventoryServiceClient.findAll().getContent().forEach(p->{

                productItemRepository.save(new ProductItem(null,p.getId(),p.getPrice(),(Double) (1+Math.random()*1000),bill,null));

            });

        };

    }
}
