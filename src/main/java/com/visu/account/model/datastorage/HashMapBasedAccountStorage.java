package com.visu.account.model.datastorage;

import com.visu.account.service.account.Account;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class HashMapBasedAccountStorage implements AccountStorage {

    private static final Map<BigInteger, Account> storage = new HashMap<>();

    @Override
    public Account get(BigInteger accountId) {
        return storage.get(accountId);
    }

    @Override
    public void add(Account account) {
        storage.put(account.getId(), account);
    }

    @Override
    public void update(Account account) {
        storage.put(account.getId(), account);
    }

    @Override
    public void delete(BigInteger accountId) {
        storage.remove(accountId);
    }
}
