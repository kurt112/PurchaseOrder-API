package com.API.PurchaseOrder.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "approval", schema = "public")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Approval {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "approver")
    private User approver;
    @Column(name = "approved_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date approvedDate;
    @Column(name = "status")
    private int status;
    @Column(name = "notes")
    private String notes;
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date updatedAt;
    @Column(name = "deleted_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date deletedAt;
}
