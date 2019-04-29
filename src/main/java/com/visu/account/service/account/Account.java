package com.visu.account.service.account;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
public class Account {
    private BigInteger id;
    private BigDecimal balance;
}
