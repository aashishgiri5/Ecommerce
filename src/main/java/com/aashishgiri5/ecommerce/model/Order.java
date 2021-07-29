package com.aashishgiri5.ecommerce.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@Table(name="order_db")
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @Column
    private int total;

    @Column
    private String name;

    @Column
    private String createdBy;

    @Column
    private String category;
}
