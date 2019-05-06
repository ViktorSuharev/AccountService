package com.visu.account.dao;

import com.visu.account.model.Account;

import java.math.BigDecimal;

public interface AccountDao {

    Account get(long id);

    boolean add(Account account);

    boolean update(Account account);

    boolean delete(long id);

    void transfer(long senderId, long receiverId, BigDecimal amount);
}
