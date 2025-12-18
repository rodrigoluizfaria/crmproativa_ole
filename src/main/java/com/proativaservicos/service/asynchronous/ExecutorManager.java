package com.proativaservicos.service.asynchronous;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Singleton
@Startup
public class ExecutorManager {

    private ExecutorService executor;

    @PostConstruct
    public void init() {
        executor = Executors.newFixedThreadPool(130); // ou um valor ajustado
    }

    public ExecutorService getExecutor() {
        return executor;
    }

    @PreDestroy
    public void destroy() {
        executor.shutdown();
    }
}
