package com.garycoffee.user.requestModel;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItem {


    private Integer quantity;
    private Integer amount;
    @JsonProperty("product_short_name")
    private String productShortName;
}
