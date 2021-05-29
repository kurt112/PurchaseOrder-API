package com.API.PurchaseOrder.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order", schema = "public")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;

    @ManyToOne()
    @JoinColumn(name = "requestor")
    private User requestor;

    @OneToOne()
    @JoinColumn(name = "approval")
    private Approval approval;

    @Column(name = "transfer_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date transferDate;

    @Column(name = "status")
    private int status;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date createdAt;
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date updatedAt;
    @Column(name = "deleted_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date deletedAt;

    @OneToMany(mappedBy = "order")
    private List<OrderDetails> orderDetailsList;

    public Order(int id, User requestor, Approval approval, Date transferDate, int status, Date createdAt, Date updatedAt, Date deletedAt) {
        this.id = id;
        this.requestor = requestor;
        this.approval = approval;
        this.transferDate = transferDate;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }
}
