package com.aashishgiri5.ecommerce.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.beans.ConstructorProperties;
import java.time.Instant;

@Entity
@Table(name= "token_db")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String token;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @Column
    private Instant expiryDate;


}
