package com.garycoffee.user.dto.webclient.product;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestUpdateProduct {

    @JsonProperty("short_name")
    private String shortName;

    private Integer price;

    private Integer stock;
}
