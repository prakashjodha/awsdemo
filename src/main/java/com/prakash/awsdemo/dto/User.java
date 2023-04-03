package com.prakash.awsdemo.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public User(String firstName,String lastName,String email,String password){
        this.email=email;
        this.firstName=firstName;
        this.lastName=lastName;
        this.password=password;

    }
    private String firstName;
    private String lastName;
    @Column(unique = false)
    private String email;
    private String password;

}
