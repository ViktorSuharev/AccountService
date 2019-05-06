package com.visu.account.rest.payload;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferDto {

    private long senderId;
    private long receiverId;
    private BigDecimal amount;
}
