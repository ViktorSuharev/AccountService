package com.visu.account.service.transfer;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface TransferService {
    boolean transfer(BigInteger senderId, BigInteger receiver, BigDecimal amount);
}
