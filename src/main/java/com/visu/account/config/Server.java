package com.visu.account.config;

import com.google.inject.Guice;
import com.google.inject.Module;
import io.logz.guice.jersey.JerseyModule;
import io.logz.guice.jersey.JerseyServer;
import io.logz.guice.jersey.configuration.JerseyConfiguration;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class Server {

    private final int port;
    private final Class resourceClass;

    private JerseyServer server;

    public void start() throws Exception {
        init();
        server.start();
    }

    public void stop() throws Exception {
        server.stop();
    }

    private void init() {
        JerseyConfiguration configuration = JerseyConfiguration.builder()
                .addResourceClass(resourceClass)
                .addPort(port)
                .build();

        List<Module> modules = Collections.singletonList(new JerseyModule(configuration));

        server = Guice.createInjector(modules)
                .getInstance(JerseyServer.class);
    }
}
