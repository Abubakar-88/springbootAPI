package com.ecommerce.springBootEcommerce.services;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    List getAllOrderList();
}
