package com.aashishgiri5.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerRequest {
    private String firstName;
    private String lastName;
    private int age;
    private String club;
}
