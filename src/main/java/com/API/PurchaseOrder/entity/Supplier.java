package com.API.PurchaseOrder.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "supplier", schema = "public")
@Getter
@Setter
public class Supplier {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "contact_person")
    private String contactPerson;

    @Column(name = "tel_no")
    private String telNo;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "state")
    private int status;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date createAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date updateAt;

    @Column(name = "deleted_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date deleteAt;


}
