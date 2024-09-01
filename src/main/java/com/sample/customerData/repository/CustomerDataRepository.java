package com.sample.customerData.repository;

import com.sample.customerData.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CustomerDataRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
}
