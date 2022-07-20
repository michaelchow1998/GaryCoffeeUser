package com.garycoffee.user.dto.webclient.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccountRequest {

    private String username;
    private String phone;
}
