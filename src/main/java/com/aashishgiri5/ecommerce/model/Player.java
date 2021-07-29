package com.aashishgiri5.ecommerce.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="player_db")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String first_name;

    @Column
    private String last_name;

    @Column
    private int age;

    @Column
    private String club;
}
