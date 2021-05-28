package com.API.PurchaseOrder.entity.API;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductSettings {
    private int  pageSize;
    private int currentPage;
    private String search;
    private int supplierId;
    private int subSectorId;
    private int status;
}
