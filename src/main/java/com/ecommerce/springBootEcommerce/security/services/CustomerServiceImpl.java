package com.ecommerce.springBootEcommerce.security.services;

import com.ecommerce.springBootEcommerce.exception.CustomerNotFoundException;
import com.ecommerce.springBootEcommerce.models.User;
import com.ecommerce.springBootEcommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public abstract class CustomerServiceImpl implements CustomerService{

    @Autowired
     private UserRepository userRepository;
    public CustomerServiceImpl (UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Bean
    @Override
    public User getCustomerById(long id) throws CustomerNotFoundException {
        User user;
       if(userRepository.findById(id).isPresent()){
           throw new CustomerNotFoundException();
       }else {
           user = userRepository.findById(id).get();
       }
       return user;
    }

}
