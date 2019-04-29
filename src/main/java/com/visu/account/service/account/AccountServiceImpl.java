package com.visu.account.service.account;

import com.google.inject.Inject;
import com.visu.account.service.account.dao.AccountDao;

import java.math.BigInteger;

public class AccountServiceImpl implements AccountService {

    private final AccountDao dao;

    @Inject
    public AccountServiceImpl(AccountDao accountDao) {
        this.dao = accountDao;
    }

    @Override
    public Account get(BigInteger accountId) {
        return dao.get(accountId);
    }

    @Override
    public void add(Account account) {
        dao.add(account);
    }

    @Override
    public void update(Account account) {
        dao.update(account);
    }

    @Override
    public void delete(BigInteger accountId) {
        dao.delete(accountId);
    }
}
