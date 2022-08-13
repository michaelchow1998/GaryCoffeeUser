package com.garycoffee.user.requestModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Order {

    private String id;

    private String phone;

    @JsonProperty("staff_id")
    private Integer staffId;

    private Integer originAmount;

    private Integer totalAmount;

    @JsonProperty("is_user_buy")
    private Boolean isUserBuy;

    @JsonProperty("is_use_integral")
    private Boolean isUseIntegral;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @JsonProperty("item_list")
    private List<OrderItem> orderItemList;
}
