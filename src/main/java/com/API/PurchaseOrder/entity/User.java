package com.API.PurchaseOrder.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter

public class User {


    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
}
