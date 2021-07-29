package com.aashishgiri5.ecommerce.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {


    //name,price,description,quantity,createdBy,quantity

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private int quantity;

    @Column
    private String createdBy;

    @Column
    private float price;




}
