package com.garycoffee.user.dto.webclient.log;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Getter
@Setter
public class UserLog {

    private String id;

    private String phone;

    @JsonProperty("transaction_type")
    private TransactionType transactionType;

    private String message;

    @JsonProperty("modified_date")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifiedDate;

}
