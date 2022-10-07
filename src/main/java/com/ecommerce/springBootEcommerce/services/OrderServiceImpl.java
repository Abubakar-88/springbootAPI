package com.ecommerce.springBootEcommerce.services;

import com.ecommerce.springBootEcommerce.dao.OrderRepository;
import com.ecommerce.springBootEcommerce.dao.ProductsRepository;
import com.ecommerce.springBootEcommerce.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    public OrderServiceImpl (ProductsRepository productRepo){
        this.orderRepo = orderRepo;
    }

    @Override
    public List getAllOrderList() {
        List <Order> list = new ArrayList<>();
        return (List) orderRepo.findAll();
    }
}
