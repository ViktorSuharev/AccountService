package com.visu.account.dao;

import com.google.inject.Inject;
import com.visu.account.exception.AccountException;
import com.visu.account.model.Account;
import org.jooq.DSLContext;
import org.jooq.Record;
import com.visu.account.model.generated.Tables;
import org.jooq.impl.DSL;

import java.math.BigDecimal;
import java.sql.Connection;

public class JooqAccountDao implements AccountDao {

    private final DSLContext context;

    @Inject
    public JooqAccountDao(Connection connection) {
        this.context = DSL.using(connection);
    }

    @Override
    public Account get(long id) {
        Record record = context
                .select()
                .from(Tables.ACCOUNTS)
                .where(Tables.ACCOUNTS.ID.eq(id))
                .fetchOne();

        return record == null ? null : record.into(Account.class);
    }

    @Override
    public boolean add(Account account) {
        int result = context.insertInto(Tables.ACCOUNTS, Tables.ACCOUNTS.ID, Tables.ACCOUNTS.BALANCE)
                .values(account.getId(), account.getBalance())
                .execute();

        return result != 0;
    }

    @Override
    public boolean update(Account account) {
        int result = context.update(Tables.ACCOUNTS)
                .set(Tables.ACCOUNTS.BALANCE, account.getBalance())
                .where(Tables.ACCOUNTS.ID.eq(account.getId()))
                .execute();

        return result != 0;
    }

    @Override
    public boolean delete(long id) {
        int result = context.delete(Tables.ACCOUNTS)
                .where(Tables.ACCOUNTS.ID.eq(id))
                .execute();

        return result != 0;
    }

    @Override
    public void transfer(long senderId, long receiverId, BigDecimal amount) {
        context.transaction(tr -> {
            withdraw(senderId, amount);
            popUp(receiverId, amount);
        });
    }

    private void withdraw(long accountId, BigDecimal amount) {
        Account account = getOrThrowException(accountId);
        account.setBalance(account.getBalance().subtract(amount));
        update(account);
    }

    private void popUp(long accountId, BigDecimal amount) {
        Account account = getOrThrowException(accountId);
        account.setBalance(account.getBalance().add(amount));
        update(account);
    }

    private Account getOrThrowException(long accountId) {
        Account account = get(accountId);
        if (account == null) {
            throw AccountException.createWithAccountNotFound(accountId);
        }

        return account;
    }
}
