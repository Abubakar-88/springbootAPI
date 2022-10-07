package com.ecommerce.springBootEcommerce.dao;

import com.ecommerce.springBootEcommerce.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
