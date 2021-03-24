package com.API.PurchaseOrder.entity.API;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserPost {
    private String email;
    private String password;
    private String token;
}
