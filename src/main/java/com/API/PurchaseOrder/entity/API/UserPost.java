package com.API.PurchaseOrder.entity.API;

import com.API.PurchaseOrder.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserPost {
    private User user;

    private String token;
}
