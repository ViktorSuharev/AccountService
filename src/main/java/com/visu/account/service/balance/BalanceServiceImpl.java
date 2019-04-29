package com.visu.account.service.balance;

import com.google.inject.Inject;
import com.visu.account.service.account.dao.AccountDao;
import com.visu.account.service.account.Account;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BalanceServiceImpl implements BalanceService {

    private final AccountDao dao;

    @Inject
    public BalanceServiceImpl(AccountDao dao) {
        this.dao = dao;
    }

    @Override
    public BigDecimal get(BigInteger accountId) {
        Account account = dao.get(accountId);

        return account.getBalance();
    }

    @Override
    public void set(BigInteger accountId, BigDecimal amount) {
        Account account = dao.get(accountId);
        account.setBalance(amount);

        dao.update(account);
    }

    @Override
    public void topUp(BigInteger accountId, BigDecimal amount) {
        Account account = dao.get(accountId);
        BigDecimal oldBalance = account.getBalance();
        BigDecimal newBalance = oldBalance.add(amount);

        account.setBalance(newBalance);
        dao.update(account);
    }

    @Override
    public void withdraw(BigInteger accountId, BigDecimal amount) {
        Account account = dao.get(accountId);
        BigDecimal oldBalance = account.getBalance();
        BigDecimal newBalance = oldBalance.subtract(amount);

        account.setBalance(newBalance);
        dao.update(account);
    }
}
