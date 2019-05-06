package com.visu.account.dao;

import com.google.inject.Inject;
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
    public Account get(Long id) {
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
    public boolean delete(Long id) {
        int result = context.delete(Tables.ACCOUNTS)
                .where(Tables.ACCOUNTS.ID.eq(id))
                .execute();

        return result != 0;
    }

    @Override
    public void transfer(Long senderId, Long receiverId, BigDecimal amount) {
        context.transaction(tr -> {
            Account sender = get(senderId);
            Account receiver = get(receiverId);

            sender.setBalance(sender.getBalance().subtract(amount));
            receiver.setBalance(receiver.getBalance().add(amount));
            update(sender);
            update(receiver);
        });
    }
}
