package com.ecommerce.springBootEcommerce.services;

import com.ecommerce.springBootEcommerce.entity.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(Product product);
    Product updateProduct(Product product);
    List getAllProduct();
   Product getProductById(long id);
    Product deleteProductById(long id );
}
