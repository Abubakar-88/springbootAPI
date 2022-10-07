package com.ecommerce.springBootEcommerce.controller;


import com.ecommerce.springBootEcommerce.ProductNotFoundException;
import com.ecommerce.springBootEcommerce.dao.ProductsRepository;
import com.ecommerce.springBootEcommerce.entity.Product;
import com.ecommerce.springBootEcommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/v01")
public class ProductController {
    @Autowired
    private ProductService productService;



  private ProductsRepository productsRepository;

    @GetMapping("/product/all")
    public ResponseEntity<List> getProduct() {
        return new ResponseEntity<List>((List) productService.getAllProduct(), HttpStatus.OK );
    }

    @GetMapping("/product/{id}")
    public ResponseEntity getProductById(@PathVariable long id){
        try{
            return  new ResponseEntity(productService.getProductById(id), HttpStatus.OK);
        }catch (ProductNotFoundException productNotFoundException){
            return new ResponseEntity(productNotFoundException.getMessage(),HttpStatus.NOT_FOUND);
        }
    }





    @PostMapping("/product")
    public ResponseEntity addProduct(@RequestBody Product product){
            Product addProduct = productService.addProduct(product);
            return new ResponseEntity(addProduct, HttpStatus.CREATED);

    }

//    @PostMapping("/product")
//    public ResponseEntity<Product> addProduct(@RequestBody Product product){
//        Product product1 = productsRepository.save(product)
//               ;
//        product1.setName(product.getName());
//        product1.setSku(product.getSku());
//        product1.setDescription(product.getDescription());
//        product1.setUnitsInStock(product.getUnitsInStock());
//        product1.setUnitPrice(product.getUnitPrice());
//        product1.setImageUrl(product.getImageUrl());
//       product1.setCategory(product.getCategory());
//        Product addProduct = productsRepository.save(product);
//        return ResponseEntity.ok(addProduct);
//    }



    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long id){
        Product product1 = productsRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not exist with this id: "+id));
        product1.setName(product.getName());
        product1.setSku(product.getSku());
        product1.setDescription(product.getDescription());
        product1.setUnitsInStock(product.getUnitsInStock());
        product1.setUnitPrice(product.getUnitPrice());
        product1.setImageUrl(product.getImageUrl());
        //product1.setCategory(product.getCategory());
       Product updateProduct = productsRepository.save(product);
       return ResponseEntity.ok(updateProduct);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity deleteProduct(@PathVariable long id) throws ProductNotFoundException
    {

        try{
            return new ResponseEntity(productService.deleteProductById(id), HttpStatus.NO_CONTENT);
        }catch (ProductNotFoundException productNotFoundException){
            return new ResponseEntity(productNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }



    }

}
