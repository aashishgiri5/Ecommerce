package com.aashishgiri5.ecommerce.controller;

import com.aashishgiri5.ecommerce.dto.CategoryRequest;
import com.aashishgiri5.ecommerce.dto.OrderRequest;
import com.aashishgiri5.ecommerce.dto.PlayerRequest;
import com.aashishgiri5.ecommerce.model.Category;
import com.aashishgiri5.ecommerce.model.Order;
import com.aashishgiri5.ecommerce.service.CategoryService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = "/createCat",produces="application/json")
    public ResponseEntity<?> createCat(@RequestBody CategoryRequest categoryRequest )
    {
        JSONObject jsonObject=new JSONObject();


        categoryService.createType(categoryRequest);
        jsonObject.put("status",200);
        jsonObject.put("message","successfully created");


        return ResponseEntity.status(200).body(jsonObject.toString());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable int id)
    {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category Deleted Successfully");


    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable int id, @RequestBody CategoryRequest categoryRequest)
    {
        Category category = categoryService.findById(id);
        categoryService.update(category,categoryRequest);



        return ResponseEntity.ok("Category Updated Successfully");

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getCategory(@PathVariable int id)
    {
        Category category=categoryService.findById(id);
        return ResponseEntity.ok("Order Found Successfully");
    }




}
