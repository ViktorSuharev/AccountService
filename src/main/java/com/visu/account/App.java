package com.visu.account;

import com.visu.account.config.Database;
import com.visu.account.config.Server;
import com.visu.account.rest.AccountRestService;

public class App {

    public static void main(String[] args) throws Exception {
        Database database = new Database();
        database.start();

        Server server = new Server(8080, AccountRestService.class);
        server.start();
    }
}