package com.ecommerce.springBootEcommerce.services;

import com.ecommerce.springBootEcommerce.dto.Purchase;
import com.ecommerce.springBootEcommerce.dto.PurchaseResponse;

public interface CheckoutService {

PurchaseResponse placeOrder(Purchase purchase);

}
