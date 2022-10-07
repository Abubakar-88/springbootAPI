package com.ecommerce.springBootEcommerce.controller;


import com.ecommerce.springBootEcommerce.dao.CustomerRepository;
import com.ecommerce.springBootEcommerce.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
   private CustomerRepository customerRepository;

    @GetMapping("/customers")
    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }

}
