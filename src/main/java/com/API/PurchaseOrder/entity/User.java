package com.API.PurchaseOrder.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  int id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "role")
    private int role;

    @Column(name = "status")
    private int status;

    @ManyToOne()
    @JoinColumn(name = "sector")
    private Sector sector;

    @Column(name = "created_at", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private Date createAt;

    @Column(name = "updated_at", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private Date updateAt;

    @Column(name = "deleted_at",columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private Date deleteAt;





}
