package com.proativaservicos.bean;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class DashboardBean implements Serializable {

    private DonutChartModel donutModel;
    private BarChartModel barModel;

    // Variáveis para os KPIs
    private Long totalMotivos = 120L;
    private Long motivosAtivos = 95L;
    private Long totalSubMotivos = 340L;
    private Integer percentualN2 = 15;
    private Integer agentesOnline = 42;

    private String periodoSelecionado = "DIARIO";

    private Date dataInicio;
    private Date dataFim;

    @PostConstruct
    public void init() {
        createDonutModel();
        createBarModel();
    }

    private void createDonutModel() {
        donutModel = new DonutChartModel();
        ChartData data = new ChartData();

        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(motivosAtivos); // Ativos
        values.add(totalMotivos - motivosAtivos); // Inativos
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(34, 197, 94)"); // Green
        bgColors.add("rgb(239, 68, 68)"); // Red
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Ativos");
        labels.add("Inativos");
        data.setLabels(labels);

        donutModel.setData(data);
    }

    private void createBarModel() {

        barModel = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Quantidade de Motivos");

        List<Number> values = new ArrayList<>();
        values.add(45); values.add(30); values.add(20); values.add(25);
        barDataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgba(54, 162, 235, 0.5)");
        bgColors.add("rgba(255, 159, 64, 0.5)");
        bgColors.add("rgba(75, 192, 192, 0.5)");
        bgColors.add("rgba(153, 102, 255, 0.5)");
        barDataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(barDataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Ação 1"); labels.add("Ação 2"); labels.add("Ação 3"); labels.add("Ação 4");
        data.setLabels(labels);

        barModel.setData(data);
    }

    public void atualizarDados() {


        if (this.periodoSelecionado == null) {
            this.periodoSelecionado = "CUSTOM";
        }
        switch (periodoSelecionado) {
            case "DIARIO":
                this.totalMotivos = 15L; // Exemplo de busca no banco
                break;
            case "SEMANAL":
                this.totalMotivos = 100L;
                break;
            case "MENSAL":
                this.totalMotivos = 450L;
                break;
        }
        // Re-inicia os modelos de gráfico com os novos valores
        init();


    }

    private void configurarDatasHoje() {
        this.dataInicio = new Date();
        this.dataFim = new Date();
    }
    public DonutChartModel getDonutModel() {
        return donutModel;
    }

    public void setDonutModel(DonutChartModel donutModel) {
        this.donutModel = donutModel;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public Long getTotalMotivos() {
        return totalMotivos;
    }

    public void setTotalMotivos(Long totalMotivos) {
        this.totalMotivos = totalMotivos;
    }

    public Long getMotivosAtivos() {
        return motivosAtivos;
    }

    public void setMotivosAtivos(Long motivosAtivos) {
        this.motivosAtivos = motivosAtivos;
    }

    public Long getTotalSubMotivos() {
        return totalSubMotivos;
    }

    public void setTotalSubMotivos(Long totalSubMotivos) {
        this.totalSubMotivos = totalSubMotivos;
    }

    public Integer getPercentualN2() {
        return percentualN2;
    }

    public void setPercentualN2(Integer percentualN2) {
        this.percentualN2 = percentualN2;
    }

    public Integer getAgentesOnline() {
        return agentesOnline;
    }

    public void setAgentesOnline(Integer agentesOnline) {
        this.agentesOnline = agentesOnline;
    }


    public String getPeriodoSelecionado() {
        return periodoSelecionado;
    }

    public void setPeriodoSelecionado(String periodoSelecionado) {
        this.periodoSelecionado = periodoSelecionado;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
}