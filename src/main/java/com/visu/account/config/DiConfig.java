package com.visu.account.config;

import com.google.inject.AbstractModule;
import com.visu.account.dao.AccountDao;
import com.visu.account.dao.JooqAccountDao;
import com.visu.account.service.AccountService;
import com.visu.account.service.AccountServiceImpl;

public class DiConfig extends AbstractModule {

    @Override
    protected void configure() {
        bind(JooqAccountDao.class).toInstance(new JooqAccountDao(ConnectionProvider.getConnection()));
        bind(AccountDao.class).to(JooqAccountDao.class);
        bind(AccountService.class).to(AccountServiceImpl.class);
    }
}