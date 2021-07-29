package com.aashishgiri5.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.build.CachedReturnPlugin;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="cart_db")
public class Cart {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String user;

    @Column
    private int productId;

    @Column
    private int quantity;

    @Column
    private int price;
}
