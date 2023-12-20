package com.enset.billingservice.feignServices;

import com.enset.billingservice.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface IProductService {
    @GetMapping("/products/{id}")
    Product findProductById(@PathVariable("id") Long id);
    @GetMapping("/products")
    PagedModel<Product> findAll();
}
