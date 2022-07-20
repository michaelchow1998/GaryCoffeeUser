package com.garycoffee.user.requestModel;

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

    public Product(String productName, String shortName, String location, String bean, String imageUrl, Integer price, Integer stock, String description) {
        this.productName = productName;
        this.shortName = shortName;
        this.location = location;
        this.bean = bean;
        this.imageUrl = imageUrl;
        this.price = price;
        this.stock = stock;
        this.description = description;
    }
}
