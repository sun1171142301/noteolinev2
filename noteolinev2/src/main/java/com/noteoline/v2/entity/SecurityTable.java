package com.noteoline.v2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "securitytable")
public class SecurityTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int Id;

    @Column(name = "username",nullable = false)
    String username;

    @Column(name = "password",nullable = false)
    String password;

    @Column(name = "role",nullable = false)
    String role;


   /* public SecurityTable(String name, String paseword, String auth) {

    }*/
}
