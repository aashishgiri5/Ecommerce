package com.aashishgiri5.ecommerce.service;

import com.aashishgiri5.ecommerce.dto.ProductRequest;
import com.aashishgiri5.ecommerce.model.Product;
import com.aashishgiri5.ecommerce.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;

    public void sendProduct(ProductRequest productRequest) throws IOException {

        Product product=new Product();
        product.setDescription(productRequest.getDescription());
        product.setCreatedBy(productRequest.getCreatedBy());
        product.setName(productRequest.getName());
        product.setQuantity(productRequest.getQuantity());

        productRepo.save(product);

    }

    public void deleteProduct(int id) {
       productRepo.deleteById(id);
    }

    public Optional<Product> findById(int id) {
        return productRepo.findById(id);
    }
    public void updateProduct(Optional<Product> product, ProductRequest productRequest) {
        product.get().setDescription(productRequest.getDescription());
        product.get().setCreatedBy(productRequest.getCreatedBy());
        product.get().setName(productRequest.getName());
        product.get().setQuantity(productRequest.getQuantity());
        productRepo.save(product.get());
    }
}
