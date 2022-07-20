package com.garycoffee.user.requestModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

    private String id;

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("short_name")
    private String shortName;
    private String location;
    private String bean;

    @JsonProperty("image_url")
    private String imageUrl;
    private Integer price;
    private Integer stock;
    private String description;

}
