package com.ecommerce.springBootEcommerce.repository;

import java.util.Optional;

import com.ecommerce.springBootEcommerce.models.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.springBootEcommerce.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
