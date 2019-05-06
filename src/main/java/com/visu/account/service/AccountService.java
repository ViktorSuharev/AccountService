package com.visu.account.service;

import com.visu.account.model.Account;

import java.math.BigDecimal;

public interface AccountService {

    Account get(long id);

    void add(Account account);

    void update(Account account);

    void delete(long id);

    void transfer(long senderId, long receiverId, BigDecimal amount);
}