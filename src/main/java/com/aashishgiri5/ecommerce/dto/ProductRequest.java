package com.aashishgiri5.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {


    private String name;
    private String description;
    private int quantity;
    private float price;
    private String createdBy;
}
