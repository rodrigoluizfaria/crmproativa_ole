package com.proativaservicos.service.asynchronous.tasks;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class RateLimiterBean {

    private final int maxPerMinute = 100;
    private Semaphore tokens;
    private ScheduledExecutorService refillScheduler;

    @PostConstruct
    public void init() {
        tokens = new Semaphore(maxPerMinute);
        refillScheduler = Executors.newSingleThreadScheduledExecutor();

        refillScheduler.scheduleAtFixedRate(() -> {
            int missing = maxPerMinute - tokens.availablePermits();
            if (missing > 0) {
                tokens.release(missing);
            }
        }, 1, 1, TimeUnit.MINUTES);
    }

    public void acquire() {
        tokens.acquireUninterruptibly();
    }

    public boolean tryAcquire(long timeout, TimeUnit unit) throws InterruptedException {
        return tokens.tryAcquire(timeout, unit);
    }

}
