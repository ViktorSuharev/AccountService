package com.visu.account;

import com.visu.account.config.Database;
import com.visu.account.config.Server;
import com.visu.account.rest.AccountRestService;

public class App {

    private static final int DEFAULT_PORT = 8080;

    public static void main(String[] args) throws Exception {
        Database database = new Database();
        Server server = new Server(getPort(), AccountRestService.class);

        database.start();
        server.start();
    }

    private static int getPort() {
        String port = System.getProperty("port");
        return port == null ? DEFAULT_PORT : Integer.valueOf(port);
    }
}