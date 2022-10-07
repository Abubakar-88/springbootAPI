package com.ecommerce.springBootEcommerce.services;


import com.ecommerce.springBootEcommerce.ProductNotFoundException;
import com.ecommerce.springBootEcommerce.dao.ProductsRepository;
import com.ecommerce.springBootEcommerce.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductsRepository productRepo;
    @Autowired
    public ProductServiceImpl (ProductsRepository productRepo){
        this.productRepo = productRepo;
    }

    @Override
    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public List getAllProduct() {

        List <Product> list = new ArrayList<>();
        return (List) productRepo.findAll();
    }

    @Override
    public Product getProductById(long id) {
        Product product;
        if(productRepo.findById(id).isEmpty()){
            throw new ProductNotFoundException();
        }else {
            product = productRepo.findById(id).get();
        };
        return product;
    }

//    @Override
//    public Product getProductByCategoryId( long id) {
//        Product product;
//        if(productRepo.findByCategoryId(id).isEmpty()){
//
//        }
//
//    }

    @Override
    public Product deleteProductById(long id) {
        Product product;
        if(productRepo.findById(id).isEmpty()){
            throw new ProductNotFoundException();
        }else {
            productRepo.deleteById(id);
        }
        return null;
    }
}
