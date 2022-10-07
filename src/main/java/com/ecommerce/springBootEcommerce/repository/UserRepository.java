package com.ecommerce.springBootEcommerce.repository;

import com.ecommerce.springBootEcommerce.exception.CustomerNotFoundException;
import com.ecommerce.springBootEcommerce.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Optional<User> findById(long id) throws CustomerNotFoundException;
  User deleteById(long id ) throws CustomerNotFoundException;
  // Customer updateCustomer(Customer customer);
  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}
