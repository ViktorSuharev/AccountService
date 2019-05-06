package com.visu.account.service;

import com.google.inject.Inject;
import com.visu.account.dao.AccountDao;
import com.visu.account.exception.AccountException;
import com.visu.account.model.Account;

import java.math.BigDecimal;

public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao;

    @Inject
    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public Account get(long id) {
        Account account = accountDao.get(id);

        if (account == null) {
            throw AccountException.createWithAccountNotFound(id);
        }

        return account;
    }

    @Override
    public void add(Account account) {
        accountDao.add(account);
    }

    @Override
    public void update(Account account) {
        boolean success = accountDao.update(account);

        if (!success) {
            throw AccountException.createWithAccountNotFound(account.getId());
        }
    }

    @Override
    public void delete(long id) {
        boolean success = accountDao.delete(id);

        if (!success) {
            throw AccountException.createWithAccountNotFound(id);
        }
    }

    @Override
    public void transfer(long senderId, long receiverId, BigDecimal amount) {
        accountDao.transfer(senderId, receiverId, amount);
    }
}
