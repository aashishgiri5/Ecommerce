package com.aashishgiri5.ecommerce.service;

import com.aashishgiri5.ecommerce.dto.OrderRequest;
import com.aashishgiri5.ecommerce.model.Order;
import com.aashishgiri5.ecommerce.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderRepo orderRepo;


    public void sendorder(OrderRequest orderRequest) {

        Order order = new Order();

        order.setCategory(orderRequest.getCategory());
        order.setCreatedBy(orderRequest.getCreatedBy());
        order.setName(orderRequest.getName());
        order.setTotal(orderRequest.getTotal());

        orderRepo.save(order);

    }

    public void deleteOrder(int id) {
        orderRepo.deleteById(id);
    }

    public void update(Order order, OrderRequest orderRequest) {
        order.setName(orderRequest.getName());
        order.setCategory(orderRequest.getCategory());
        order.setCreatedBy(orderRequest.getCreatedBy());
        order.setTotal(orderRequest.getTotal());
        orderRepo.save(order);
    }

    public Order findById(int id) {
        return orderRepo.findById(id).get();
    }
}
