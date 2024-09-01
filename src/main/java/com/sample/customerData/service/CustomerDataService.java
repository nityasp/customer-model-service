package com.sample.customerData.service;

import com.sample.customerData.model.Customer;
import com.sample.customerData.repository.CustomerDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerDataService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerDataService.class);

    @Autowired
    private CustomerDataRepository customerDataRepository;

    public List<Customer> getAllCustomers() {
        logger.info("Fetching all customers");
        return customerDataRepository.findAll();
    }

    public Optional<Customer> getCustomerByEmail(String email) {
        logger.info("Fetching customer by email: {}", maskEmail(email));
        return customerDataRepository.findByEmail(email);
    }

    public Optional<Customer> getCustomerById(Long id) {
        logger.info("Fetching customer by ID: {}", id);
        return customerDataRepository.findById(id);
    }

    public Customer createCustomer(Customer customer) {
        logger.info("Creating customer with email: {}", maskEmail(customer.getEmail()));
        return customerDataRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer customerDetails) {
        logger.info("Updating customer with ID: {}", id);
        Customer customer = customerDataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.setPrefix(customerDetails.getPrefix());
        customer.setFirstName(customerDetails.getFirstName());
        customer.setMiddleName(customerDetails.getMiddleName());
        customer.setLastName(customerDetails.getLastName());
        customer.setSuffix(customerDetails.getSuffix());
        customer.setEmail(customerDetails.getEmail());
        customer.setPhoneNumber(customerDetails.getPhoneNumber());
        logger.info("Updated customer with ID: {}", id);
        return customerDataRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        logger.info("Deleting customer with ID: {}", id);
        customerDataRepository.deleteById(id);
    }

    private String maskEmail(String email) {
        return email.replaceAll("(?<=.{3}).(?=.*@)", "*");
    }
}
