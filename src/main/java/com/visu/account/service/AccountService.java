package com.visu.account.service;

import com.visu.account.model.Account;

import java.math.BigDecimal;

public interface AccountService {

    Account get(Long id);

    void add(Account account);

    void update(Account account);

    void delete(Long id);

    void transfer(Long senderId, Long receiverId, BigDecimal amount);
}