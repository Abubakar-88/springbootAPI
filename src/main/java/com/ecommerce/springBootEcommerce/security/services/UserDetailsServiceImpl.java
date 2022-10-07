package com.ecommerce.springBootEcommerce.security.services;

import com.ecommerce.springBootEcommerce.models.User;
import com.ecommerce.springBootEcommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Customer Not Found with username: " + username));

    return UserDetailsImpl.build(user);
  }
//  @Override
//  @Transactional
//  public Customer customerById(long id) throws CustomerNotFoundException {
//      Customer customer;
//      if(userRepository.findById(id).isPresent()){
//         throw new CustomerNotFoundException();
//      }else{
//        customer = userRepository.findById(id).get();
//      }
//      return customer;
//  }



//  public List <Customer> getAllCustomer(){
//
//      List<Customer> list = new ArrayList<>();
//     return (List)  userRepository.findAll();
//
//  }

}
