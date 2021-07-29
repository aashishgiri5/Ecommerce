package com.aashishgiri5.ecommerce.repo;

import com.aashishgiri5.ecommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository <Order,Integer>{
}
