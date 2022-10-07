package com.ecommerce.springBootEcommerce;

public class ProductCategoryNotFoundException extends RuntimeException{
    private String message;
    public ProductCategoryNotFoundException(String message){
        super(message);
        this.message=message;
    }
    public ProductCategoryNotFoundException(){

    }
}
