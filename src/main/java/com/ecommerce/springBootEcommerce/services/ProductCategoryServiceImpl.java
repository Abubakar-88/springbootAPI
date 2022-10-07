package com.ecommerce.springBootEcommerce.services;

import com.ecommerce.springBootEcommerce.ProductCategoryNotFoundException;
import com.ecommerce.springBootEcommerce.ProductNotFoundException;
import com.ecommerce.springBootEcommerce.dao.CategoryRepository;
import com.ecommerce.springBootEcommerce.dao.ProductCategoryRepository;
import com.ecommerce.springBootEcommerce.entity.PCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    public ProductCategoryServiceImpl (ProductCategoryRepository productCategoryRepository){
        this.categoryRepository = categoryRepository;
    }


    @Override
    public PCategory getCategoryById(long id) {
        PCategory category;
        if(categoryRepository.findById(id).isEmpty()){
             throw new ProductCategoryNotFoundException();

        }else{
            category = categoryRepository.findById(id).get();
        }

        return category;
    }

    @Override
    public PCategory addProductCategory(PCategory category) {
        return categoryRepository.save(category);
    }


    @Override
    public List getAllPCategory() {

        List <PCategory> list = new ArrayList<>();
        return (List) categoryRepository.findAll();
    }

    @Override
    public PCategory deleteProductCategory(long id) {
        PCategory category;
        if(categoryRepository.findById(id).isPresent()){

            categoryRepository.deleteById(id);
        }else {
            throw new ProductNotFoundException();
        }
        return null;
    }
}
