package com.ecommerce.springBootEcommerce.security.services;

import com.ecommerce.springBootEcommerce.exception.CustomerNotFoundException;
import com.ecommerce.springBootEcommerce.models.User;

public interface CustomerService {

    User getCustomerById(long id) throws CustomerNotFoundException;
}
