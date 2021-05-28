package com.API.PurchaseOrder.entity.API;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SettingByStatus {
    private int  pageSize;
    private int currentPage;
    private String search;
    private int status;
}
