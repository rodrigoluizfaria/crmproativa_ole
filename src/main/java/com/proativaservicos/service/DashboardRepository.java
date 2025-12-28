package com.proativaservicos.service;

import java.math.BigDecimal;
import java.util.*;

public class DashboardRepository {

    private final Random random = new Random();

    // Simula contagem de atendimentos
    public Long contarAtendimentos(String tipo) {
        // Retorna um número aleatório entre 100 e 500 para parecer real
        return (long) (random.nextInt(400) + 100);
    }

    public Long contarAgentesOnline() {
        return (long) (random.nextInt(20) + 5); // Entre 5 e 25 agentes
    }

    public BigDecimal somarValorVendas() {
        // Retorna valor entre 10.000 e 50.000
        return new BigDecimal(random.nextInt(40000) + 10000);
    }

    // Simula o TMA em segundos
    public Long calcularMediaTmaEmSegundos() {
        return (long) (random.nextInt(600) + 120); // Entre 2min e 12min
    }

    // Mock para Gráfico de Rosca (Motivos)
    public Map<String, Number> getTopMotivos() {
        Map<String, Number> map = new LinkedHashMap<>();
        map.put("Informação Geral", random.nextInt(100));
        map.put("Reclamação", random.nextInt(80));
        map.put("Vendas/Contratação", random.nextInt(60));
        map.put("Suporte Técnico", random.nextInt(40));
        map.put("Cancelamento", random.nextInt(20));
        return map;
    }

    // Mock para Gráfico de Barras (Status)
    public Map<String, Number> getStatusDistribuicao() {
        Map<String, Number> map = new LinkedHashMap<>();
        map.put("Finalizado Sucesso", random.nextInt(200));
        map.put("Derivado N2", random.nextInt(50));
        map.put("Queda de Ligação", random.nextInt(30));
        map.put("Improdutivo", random.nextInt(20));
        return map;
    }

    // Mock para Tabela de Ranking
    public List<Object[]> getRankingOperadores() {
        List<Object[]> lista = new ArrayList<>();
        lista.add(new Object[]{"Maria Silva", random.nextInt(50) + 50});
        lista.add(new Object[]{"João Souza", random.nextInt(40) + 40});
        lista.add(new Object[]{"Ana Costa", random.nextInt(30) + 30});
        lista.add(new Object[]{"Pedro Santos", random.nextInt(20) + 20});
        lista.add(new Object[]{"Lucas Oliveira", random.nextInt(10) + 10});
        return lista;
    }
}