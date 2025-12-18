package com.proativaservicos.service.asynchronous;
import com.google.common.util.concurrent.RateLimiter;
import java.util.concurrent.BlockingQueue; import java.util.concurrent.ConcurrentLinkedQueue; import java.util.concurrent.ExecutorService; import java.util.concurrent.Executors;
public class RateLimitedExecutor {


    private final RateLimiter rateLimiter;
    private final ExecutorService executor;
    private final ConcurrentLinkedQueue<Long> timestamps = new ConcurrentLinkedQueue<>();

    public RateLimitedExecutor(int tarefasPorMinuto) {
        this.rateLimiter = RateLimiter.create(tarefasPorMinuto / 60.0);
        this.executor = Executors.newFixedThreadPool(20);
    }

    public void processarFila(BlockingQueue<Runnable> fila) {
        while (!fila.isEmpty()) {
            Runnable tarefa = fila.poll();
            if (tarefa == null) continue;

            double espera = rateLimiter.acquire();
            long inicio = System.currentTimeMillis();
            timestamps.add(inicio);
            limparAntigos(inicio);

            System.out.printf("[RATE] %.2fs | Execuções nos últimos 60s: %d%n", espera, timestamps.size());

            executor.submit(() -> {
                try {
                    tarefa.run();
                } catch (Exception e) {
                    System.err.println("[ERRO] Tarefa falhou: " + e.getMessage());
                    reencaminhar(tarefa, fila); // fallback
                }
            });
        }
    }

    private void limparAntigos(long agora) {
        long limite = agora - 60_000;
        timestamps.removeIf(ts -> ts < limite);
    }

    private void reencaminhar(Runnable tarefa, BlockingQueue<Runnable> fila) {
        try {
            Thread.sleep(10 * 60 * 1000); // espera 10 minutos
            fila.offer(tarefa); // reprocessa
            System.out.println("[FALLBACK] Tarefa reencaminhada após falha.");
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

    public void shutdown() {
        executor.shutdown();
    }
}