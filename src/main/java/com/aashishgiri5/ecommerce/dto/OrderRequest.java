package com.aashishgiri5.ecommerce.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private int total;
    private String name;
    private String createdBy;
    private String category;

}
