package com.visu.account.service.balance;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface BalanceService {

    BigDecimal get(BigInteger accountId);

    void set(BigInteger accountId, BigDecimal amount);

    void topUp(BigInteger accountId, BigDecimal amount);

    void withdraw(BigInteger accountId, BigDecimal amount);
}
