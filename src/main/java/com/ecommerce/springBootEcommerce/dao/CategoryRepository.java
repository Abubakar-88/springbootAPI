package com.ecommerce.springBootEcommerce.dao;

import com.ecommerce.springBootEcommerce.entity.PCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
public interface CategoryRepository extends JpaRepository<PCategory, Long> {
}
