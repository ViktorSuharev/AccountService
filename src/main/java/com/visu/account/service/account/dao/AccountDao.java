package com.visu.account.service.account.dao;

import com.visu.account.service.account.Account;

import java.math.BigInteger;

public interface AccountDao {

    Account get(BigInteger accountId);

    void add(Account account);

    void update(Account account);

    void delete(BigInteger accountId);
}
