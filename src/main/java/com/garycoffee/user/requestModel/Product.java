package com.garycoffee.user.requestModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

    private String id;

    private String productName;

    private String shortName;
    private String location;
    private String bean;

    private String imageUrl;
    private Integer price;
    private Integer stock;
    private String description;

}
