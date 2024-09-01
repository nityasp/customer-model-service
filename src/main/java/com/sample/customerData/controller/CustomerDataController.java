package com.sample.customerData.controller;

import com.sample.customerData.model.Customer;
import com.sample.customerData.service.CustomerDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerDataController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerDataController.class);
    @Autowired
    private CustomerDataService customerDataService;

    @GetMapping("/getCustomers")
    public List<Customer> getAllCustomers() {
        logger.info("Received request to fetch all customers");
        return customerDataService.getAllCustomers();
    }

    @GetMapping("/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email) {
        logger.info("Received request to fetch customer by email: {}", maskEmail(email));
        return customerDataService.getCustomerByEmail(email)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    logger.warn("Customer not found with email: {}", maskEmail(email));
                    return ResponseEntity.notFound().build();
                });
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        logger.info("Received request to fetch customer by ID: {}", id);
        return customerDataService.getCustomerById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    logger.warn("Customer not found with ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        logger.info("Received request to create a new customer with email: {}", maskEmail(customer.getEmail()));
        return customerDataService.createCustomer(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        logger.info("Received request to update customer with ID: {}", id);
        return ResponseEntity.ok(customerDataService.updateCustomer(id, customerDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        logger.info("Received request to delete customer with ID: {}", id);
        customerDataService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    private String maskEmail(String email) {
        return email.replaceAll("(?<=.{3}).(?=.*@)", "*");
    }
}
