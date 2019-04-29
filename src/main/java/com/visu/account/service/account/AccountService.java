package com.visu.account.service.account;

import java.math.BigInteger;

public interface AccountService {

    Account get(BigInteger accountId);

    void add(Account account);

    void update(Account account);

    void delete(BigInteger accountId);
}