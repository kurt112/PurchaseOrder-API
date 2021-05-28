package com.API.PurchaseOrder.entity.API;


import lombok.Data;

public @Data class ProductSettings {
    private int  pageSize;
    private int currentPage;
    private String search;
    private int supplierId;
    private int subSectorId;
    private int status;
}
