package com.visu.account.model.datastorage;

import com.visu.account.service.account.Account;

import java.math.BigInteger;

public interface AccountStorage {

    Account get(BigInteger accountId);

    void add(Account account);

    void update(Account account);

    void delete(BigInteger accountId);
}
