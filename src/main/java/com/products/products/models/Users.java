package com.products.products.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tb_users")
public class Users {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    private String name;
    private String email;
    private String password;
}
