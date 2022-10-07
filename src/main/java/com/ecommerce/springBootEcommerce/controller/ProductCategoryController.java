package com.ecommerce.springBootEcommerce.controller;


import com.ecommerce.springBootEcommerce.ProductCategoryNotFoundException;
import com.ecommerce.springBootEcommerce.dao.ProductCategoryRepository;
import com.ecommerce.springBootEcommerce.entity.PCategory;
import com.ecommerce.springBootEcommerce.entity.ProductCategory;
import com.ecommerce.springBootEcommerce.services.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

     @Autowired
     private ProductCategoryRepository proRepo;
    @GetMapping("/productCategory")
    public ResponseEntity<List> getAllPCategory(){
        return new ResponseEntity<List>((List) productCategoryService.getAllPCategory(), HttpStatus.OK );
    }


    @PostMapping("/productCategory")
    public ResponseEntity addProductCategory(@RequestBody PCategory category){
       PCategory addProductCategory = productCategoryService.addProductCategory(category);
        return new ResponseEntity(addProductCategory, HttpStatus.CREATED);

    }



    @GetMapping("/productCategory/{id}")
    public ResponseEntity getProductByCategoryId(@PathVariable long id){
        try{
            return  new ResponseEntity(productCategoryService.getCategoryById(id), HttpStatus.OK);
        }catch (ProductCategoryNotFoundException productCategoryNotFoundException){
            return new ResponseEntity(productCategoryNotFoundException.getMessage(),HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/productCategory/{id}")
    public ResponseEntity deleteProductByCategory(@PathVariable long id) throws ProductCategoryNotFoundException
    {

        try{
            return new ResponseEntity(productCategoryService.deleteProductCategory(id), HttpStatus.NO_CONTENT);
        }catch (ProductCategoryNotFoundException productCategoryNotFoundException){
            return new ResponseEntity(productCategoryNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }



    }

    @GetMapping("product-category2")
    public List<ProductCategory> getAllProductCategory(){
        return proRepo.findAll();
    }




}
