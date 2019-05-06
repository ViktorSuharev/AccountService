package com.visu.account.dao;

import com.opentable.db.postgres.junit.EmbeddedPostgresRules;
import com.opentable.db.postgres.junit.SingleInstancePostgresRule;
import com.visu.account.model.Account;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Statement;

public class JooqAccountDaoTest {

    @ClassRule
    public static SingleInstancePostgresRule postgres = EmbeddedPostgresRules.singleInstance();

    private static final String CREATE_DATABASE = "CREATE DATABASE account_service";
    private static final String CREATE_TABLE = "CREATE TABLE accounts (id bigint not null, balance numeric(10, 2) default null, primary key (id));";
    private static final String INSERT_DATA = "INSERT INTO accounts (id, balance) VALUES (1, 1000)";
    private static final String DELETE_DATA = "DELETE FROM accounts";

    private static final String DATABASE = "postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private static AccountDao dao;

    @BeforeClass
    public static void setUpDb() throws Exception {
        DataSource dataSource = postgres.getEmbeddedPostgres().getDatabase(USERNAME, DATABASE);
        try (Connection connection = dataSource.getConnection(USERNAME, PASSWORD)) {
            Statement statement = connection.createStatement();
            statement.execute(CREATE_DATABASE);
            statement.execute(CREATE_TABLE);
        }

        dao = new JooqAccountDao(dataSource.getConnection(USERNAME, PASSWORD));
    }

    @Before
    public void setUpData() throws Exception {
        DataSource dataSource = postgres.getEmbeddedPostgres().getDatabase(USERNAME, DATABASE);
        try (Connection connection = dataSource.getConnection(USERNAME, PASSWORD)) {
            Statement statement = connection.createStatement();
            statement.execute(DELETE_DATA);
            statement.execute(INSERT_DATA);
        }
    }

    @AfterClass
    public static void tearDown() throws Exception {
        postgres.getEmbeddedPostgres().close();
    }

    @Test
    public void get() {
        Account actual = dao.get(1L);
        Account expected = new Account(1L, new BigDecimal(1000));

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void add() {
        Account notExistYet = dao.get(2L);
        Assert.assertNull(notExistYet);

        Account candidate = new Account(2L, new BigDecimal(1000));
        dao.add(candidate);

        Account saved = dao.get(2L);
        Assert.assertEquals(candidate, saved);
    }

    @Test
    public void update() {
        Account before = new Account(1L, new BigDecimal(1000));
        Account toBeUpdated = dao.get(1L);
        Assert.assertEquals(before, toBeUpdated);

        toBeUpdated.setBalance(BigDecimal.valueOf(1100L));
        dao.update(toBeUpdated);

        Account after = new Account(1L, new BigDecimal(1000));
        Account updated = dao.get(1L);
        Assert.assertEquals(after, updated);
    }

    @Test
    public void delete() {
        Account existent = dao.get(1L);
        Assert.assertNotNull(existent);

        dao.delete(existent.getId());

        Account deleted = dao.get(1L);
        Assert.assertNull(deleted);
    }
}