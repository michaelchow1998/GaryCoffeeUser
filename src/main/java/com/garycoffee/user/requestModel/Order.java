package com.garycoffee.user.requestModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Order {

    private String id;

    private String phone;

    private Integer staffId;

    private Integer originAmount;

    private Integer totalAmount;

    private Boolean isUserBuy;

    private Boolean isUseIntegral;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    private List<OrderItem> orderItemList;
}
