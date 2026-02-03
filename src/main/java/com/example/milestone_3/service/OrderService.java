package com.example.milestone_3.service;

import com.example.milestone_3.dto.request.OrderRequest;
import com.example.milestone_3.dto.response.OrderResponse;

public interface OrderService {

    OrderResponse placeOrder(OrderRequest request);

    OrderResponse getOrderById(Long orderId);
}

