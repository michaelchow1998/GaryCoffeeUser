package com.garycoffee.user.dto.webclient.product;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestUpdateList {

    @JsonProperty("update_list")
    private List<RequestUpdateProduct> requestUpdateProductList;
}
