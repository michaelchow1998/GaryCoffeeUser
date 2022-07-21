package com.garycoffee.user.dto.webclient.log;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Getter
@Setter
public class ProductLog {

    @Id
    private String id;

    @JsonProperty("staff_id")
    private Integer staffId;

    @JsonProperty("product_short_name")
    private String productShortName;

    @JsonProperty("transaction_type")
    private TransactionType transactionType;

    private Integer amount;

    @JsonProperty("create_date")
    private Date createDate;

}
