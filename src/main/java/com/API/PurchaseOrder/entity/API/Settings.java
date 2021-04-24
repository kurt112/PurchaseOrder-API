package com.API.PurchaseOrder.entity.API;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Settings {
    private int  pageSize;
    private int currentPage;
    private String search;
    private String orderBy;
    private boolean order;
}