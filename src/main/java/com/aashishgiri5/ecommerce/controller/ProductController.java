package com.aashishgiri5.ecommerce.controller;


import com.aashishgiri5.ecommerce.dto.ProductRequest;
import com.aashishgiri5.ecommerce.model.Product;
import com.aashishgiri5.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

  @PostMapping(value="/createProduct")
    public ResponseEntity<?> createP (@RequestBody ProductRequest productRequest) throws IOException {
      productService.sendProduct(productRequest);
      return ResponseEntity.ok("Product created Successfully");
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteP(@PathVariable int id)
  {
      productService.deleteProduct(id);

      return ResponseEntity.ok("Product deleted Successfully");

}

@PutMapping("/update/{id}")
public ResponseEntity<?> updateP(@RequestBody ProductRequest productRequest,@PathVariable int id)
{
    Optional<Product> product=productService.findById(id);
    productService.updateProduct(product,productRequest);
    return ResponseEntity.ok("Product Updated Successfully");
}
}
