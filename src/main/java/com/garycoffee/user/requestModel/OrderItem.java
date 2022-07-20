package com.garycoffee.user.requestModel;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItem {

    private String productShortName;
    private Integer quantity;
    private Integer amount;

}
