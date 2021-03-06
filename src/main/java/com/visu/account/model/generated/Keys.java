/*
 * This file is generated by jOOQ.
 */
package com.visu.account.model.generated;


import javax.annotation.Generated;

import org.jooq.UniqueKey;
import com.visu.account.model.generated.tables.Accounts;
import com.visu.account.model.generated.tables.records.AccountsRecord;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>public</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.11"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<AccountsRecord> ACCOUNTS_PKEY = UniqueKeys0.ACCOUNTS_PKEY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class UniqueKeys0 {
        public static final UniqueKey<AccountsRecord> ACCOUNTS_PKEY = Internal.createUniqueKey(Accounts.ACCOUNTS, "accounts_pkey", Accounts.ACCOUNTS.ID);
    }
}
