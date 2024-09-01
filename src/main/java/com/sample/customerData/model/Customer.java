package com.sample.customerData.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String prefix;
    private String firstName;
    private String middleName;
    private String lastName;
    private String suffix;

    @Column(nullable = false, unique = true)
    private String email;

    private String phoneNumber;
}
