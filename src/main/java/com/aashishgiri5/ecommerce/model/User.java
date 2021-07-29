package com.aashishgiri5.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name="user_db")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {



    @Id
    @GeneratedValue
    @Column()
    private int id;

    @Column()
    private String firstName;

    @Column()
    private String lastName;

    @Column()
    private String userName;

    @Column()
    private String password;

    @Column
    private boolean isenabled;
}
