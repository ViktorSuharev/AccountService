/*
 * This file is generated by jOOQ.
 */
package com.visu.account.model.generated.tables;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import com.visu.account.model.generated.Indexes;
import com.visu.account.model.generated.Keys;
import com.visu.account.model.generated.Public;
import com.visu.account.model.generated.tables.records.AccountsRecord;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.11"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Accounts extends TableImpl<AccountsRecord> {

    private static final long serialVersionUID = -1638719282;

    /**
     * The reference instance of <code>public.accounts</code>
     */
    public static final Accounts ACCOUNTS = new Accounts();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AccountsRecord> getRecordType() {
        return AccountsRecord.class;
    }

    /**
     * The column <code>public.accounts.id</code>.
     */
    public final TableField<AccountsRecord, Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.accounts.balance</code>.
     */
    public final TableField<AccountsRecord, BigDecimal> BALANCE = createField("balance", org.jooq.impl.SQLDataType.NUMERIC(10, 2).defaultValue(org.jooq.impl.DSL.field("NULL::numeric", org.jooq.impl.SQLDataType.NUMERIC)), this, "");

    /**
     * Create a <code>public.accounts</code> table reference
     */
    public Accounts() {
        this(DSL.name("accounts"), null);
    }

    /**
     * Create an aliased <code>public.accounts</code> table reference
     */
    public Accounts(String alias) {
        this(DSL.name(alias), ACCOUNTS);
    }

    /**
     * Create an aliased <code>public.accounts</code> table reference
     */
    public Accounts(Name alias) {
        this(alias, ACCOUNTS);
    }

    private Accounts(Name alias, Table<AccountsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Accounts(Name alias, Table<AccountsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Accounts(Table<O> child, ForeignKey<O, AccountsRecord> key) {
        super(child, key, ACCOUNTS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.ACCOUNTS_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<AccountsRecord> getPrimaryKey() {
        return Keys.ACCOUNTS_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<AccountsRecord>> getKeys() {
        return Arrays.<UniqueKey<AccountsRecord>>asList(Keys.ACCOUNTS_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Accounts as(String alias) {
        return new Accounts(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Accounts as(Name alias) {
        return new Accounts(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Accounts rename(String name) {
        return new Accounts(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Accounts rename(Name name) {
        return new Accounts(name, null);
    }
}
