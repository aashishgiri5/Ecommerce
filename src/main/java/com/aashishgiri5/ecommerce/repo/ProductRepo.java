package com.aashishgiri5.ecommerce.repo;

import com.aashishgiri5.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Integer> {
}
