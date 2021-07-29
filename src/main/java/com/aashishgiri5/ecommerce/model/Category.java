package com.aashishgiri5.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="category_db")
public class Category {
    @Id
    @GeneratedValue
    @Column
    private int id;

    @Column
    private String type;

    @Column
    private String createdBy;

}
