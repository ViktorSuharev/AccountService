package com.visu.account.config;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final Logger LOGGER = LoggerFactory.getLogger(Database.class);

    public static final String CREATE_DATABASE = "CREATE DATABASE account_service";
    public static final String CREATE_TABLE =
            "CREATE TABLE accounts(" +
                    "id bigint not null," +
                    "balance numeric(10, 2) default null," +
                    "primary key (id)," +
                    "constraint non_negative_balance check (balance >= 0)" +
                    ");";

    public static final String DATABASE = "postgres";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "postgres";

    private EmbeddedPostgres postgres;

    public void start() throws Exception {
        postgres = EmbeddedPostgres.start();
        init();
    }

    private void init() throws Exception {
        createDbAndTable();
        initConnectionProvider();
    }

    public void stop() throws Exception {
        closeConnection();
        postgres.close();
    }

    private void createDbAndTable() throws SQLException {
        DataSource dataSource = postgres.getDatabase(USERNAME, DATABASE);
        try (Connection conn = dataSource.getConnection(USERNAME, PASSWORD)) {
            Statement statement = conn.createStatement();
            statement.execute(CREATE_DATABASE);
            statement.execute(CREATE_TABLE);
        }
    }

    private void initConnectionProvider() {
        String url = postgres.getJdbcUrl(USERNAME, DATABASE);
        ConnectionProvider.initConnection(url, USERNAME, PASSWORD);
    }

    private void closeConnection() {
        Connection connection = ConnectionProvider.getConnection();
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Error while closing SQL connection", e);
            }
        }
    }
}
