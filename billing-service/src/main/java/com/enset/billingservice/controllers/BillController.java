package com.enset.billingservice.controllers;

import com.enset.billingservice.entities.Bill;
import com.enset.billingservice.feignServices.ICustomerService;
import com.enset.billingservice.feignServices.IProductService;
import com.enset.billingservice.repositories.BillRepository;
import com.enset.billingservice.repositories.ProductItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BillController {
    private final BillRepository billRepository;
    private final ProductItemRepository productItemRepository;
    private final ICustomerService customerServiceClient;
    private final IProductService inventoryServiceClient;
    @GetMapping("/bills/full/{id}")
    public Bill getBill(@PathVariable(name = "id") Long id){
        Bill bill=billRepository.findById(id).get();
        bill.setCustomer(customerServiceClient.findCustomerById(bill.getCustomerID()));
        bill.setProductItems(productItemRepository.findByBillId(id));
        bill.getProductItems().forEach(pi->{
            pi.setProduct(inventoryServiceClient.findProductById(pi.getProductID()));
        });
        return bill; }
}
