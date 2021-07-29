package com.aashishgiri5.ecommerce.controller;


import com.aashishgiri5.ecommerce.dto.OrderRequest;
import com.aashishgiri5.ecommerce.dto.PlayerRequest;
import com.aashishgiri5.ecommerce.model.Order;
import com.aashishgiri5.ecommerce.model.Player;
import com.aashishgiri5.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order_op")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<?> createOrder (@RequestBody OrderRequest orderRequest)
    {
        orderService.sendorder(orderRequest);
        return ResponseEntity.ok("Order Created Successfully");

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable int id)
    {
        orderService.deleteOrder(id);
        return ResponseEntity.ok("Order Deleted Successfully");


    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable int id, @RequestBody OrderRequest orderRequest)
    {
        Order order = orderService.findById(id);
        orderService.update(order,orderRequest);



        return ResponseEntity.ok("Order Updated Successfully");

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getOrder(@PathVariable int id)
    {
        Order order=orderService.findById(id);
        return ResponseEntity.ok("Order Found Successfully");
    }



}
