package com.ecommerce.springBootEcommerce.services;

import com.ecommerce.springBootEcommerce.ProductCategoryNotFoundException;
import com.ecommerce.springBootEcommerce.entity.PCategory;

import java.util.List;

public interface ProductCategoryService {
    PCategory getCategoryById(long id) throws ProductCategoryNotFoundException;
    PCategory addProductCategory(PCategory category);
    List getAllPCategory()throws ProductCategoryNotFoundException;
    PCategory deleteProductCategory(long id );
}
