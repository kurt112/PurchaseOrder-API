package com.API.PurchaseOrder.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "code")
    private String code;

    @ManyToOne()
    @JoinColumn(name = "supplier")
    private Supplier supplier;

    @ManyToOne()
    @JoinColumn(name = "sub_sector")
    private SubSector subSector;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "sku")
    private String sku;

    @Column(name = "unit_mesure")
    private String unitMeasure;

    @Column(name = "price")
    private int price;

    @Column(name = "status")
    private int status;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date createAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date updateAt;

    @Column(name = "deleted_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date deleteAt;



}
