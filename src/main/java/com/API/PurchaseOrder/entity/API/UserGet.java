package com.API.PurchaseOrder.entity.API;

import com.API.PurchaseOrder.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserGet {


    private int sectorId;
    private String email;
    private String password;
    private String employeeId;
    private String firstName;
    private String lastName;
    private int role;
    private int status;
    private Date createAt;
    private Date updateAt;
    private Date deleteAt;
    private String statusName;
    private String statusColor;
    public UserGet(User user) {

        this.sectorId = user.getSectorId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.employeeId = user.getEmployeeId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.role = user.getRole();
        this.status = user.getStatus();
        this.createAt = user.getCreateAt();
        this.deleteAt = user.getUpdateAt();
        this.updateAt = user.getUpdateAt();
        this.statusName =  status == 1? "active":"inactive";
        this.statusColor = status == 1? "primary":"danger";

    }
}
