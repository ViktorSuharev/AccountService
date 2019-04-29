package com.visu.account.service.account.dao;

import com.google.inject.Inject;
import com.visu.account.service.account.Account;
import com.visu.account.model.datastorage.HashMapBasedAccountStorage;

import java.math.BigInteger;

public class MapBasedAccountDao implements AccountDao {

    private final HashMapBasedAccountStorage storage;

    @Inject
    public MapBasedAccountDao(HashMapBasedAccountStorage storage) {
        this.storage = storage;
    }

    @Override
    public Account get(BigInteger accountId) {
        return storage.get(accountId);
    }

    @Override
    public void add(Account account) {
        storage.add(account);
    }

    @Override
    public void update(Account account) {
        storage.update(account);
    }

    @Override
    public void delete(BigInteger accountId) {
        storage.delete(accountId);
    }
}
