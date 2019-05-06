package com.visu.account.config;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String CREATE_DATABASE = "CREATE DATABASE account_service";
    private static final String CREATE_TABLE = "CREATE TABLE accounts (id bigint not null, balance numeric(10, 2) default null, primary key (id), constraint non_negative_balance check (balance >= 0));";
    private static final String INSERT_DATA = "INSERT INTO accounts (id, balance) VALUES (1, 1000)";

    private static final String DATABASE = "postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private EmbeddedPostgres postgres;

    public void start() throws Exception {
        postgres = EmbeddedPostgres.start();
        init();
    }

    private void init() throws Exception {
        initMetadata();
        initData();

        initConnectionProvider();
    }

    public void stop() throws Exception {
        postgres.close();
    }

    private void initMetadata() throws SQLException {
        DataSource dataSource = postgres.getDatabase(USERNAME, DATABASE);
        try (Connection conn = dataSource.getConnection(USERNAME, PASSWORD)) {
            Statement statement = conn.createStatement();
            statement.execute(CREATE_DATABASE);
            statement.execute(CREATE_TABLE);
        }
    }

    private void initData() throws SQLException {
        DataSource dataSource = postgres.getDatabase(USERNAME, DATABASE);
        try (Connection conn = dataSource.getConnection(USERNAME, PASSWORD)) {
            Statement statement = conn.createStatement();
            statement.execute(INSERT_DATA);
        }
    }

    private void initConnectionProvider() {
        String url = postgres.getJdbcUrl(USERNAME, DATABASE);
        ConnectionProvider.initConnection(url, USERNAME, PASSWORD);
    }
}
