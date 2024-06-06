package com.avdo.spring_app.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Value;

import java.sql.Date;

@Entity
@Table(name = "profile")
@Data
public class Profile {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "date_created")
    private Date dateCreated;

}
