package com.garycoffee.user.requestModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {

    private String id;

    private String username;

    private String phone;

    private Integer AccountBalance;

    private Integer IntegralBalance;
}