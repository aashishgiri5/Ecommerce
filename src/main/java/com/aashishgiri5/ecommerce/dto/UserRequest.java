package com.aashishgiri5.ecommerce.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotBlank(message = "First name cannot be blank")
    private String first_name;

    @NotBlank(message = "last name cannot be blank")
    private String last_name;

    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "password cannot be blank")
    private String password;
}
