package com.proativaservicos.bean;

import com.proativaservicos.model.Empresa;
import com.proativaservicos.service.AtendimentoService;
import com.proativaservicos.util.constantes.MessagesEnum;
import com.proativaservicos.util.constantes.PeriodoEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.omnifaces.util.Messages;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class SupervidorBackofficeProducao extends GenericBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<?> listItens;

    private Double valorTotal;

    private Integer qtidadeTota;

    private PeriodoEnum periodo;

    @Inject
    private AtendimentoService serviceAtendimento;

    private BarChartModel modelChart;



    @PostConstruct
    public void init() {

        try {

            this.periodo = PeriodoEnum.MENSAL;

            inicializarEmpresa(retornarEmpresaUsuarioSessao());
            pesquisar();

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }
    }

    public void pesquisar() {

        try {

            this.listItens = this.serviceAtendimento.pesquisarMonitorProducao(getEmpresa().getId(),
                    retornarUsuarioSessao(), this.periodo);

            this.valorTotal = Double.valueOf(0.0D);
            this.qtidadeTota = Integer.valueOf(0);

            List<Number> values = new ArrayList<>();
            List<String> labels = new ArrayList<>();

            List<String> bgColor = new ArrayList<>();
            List<String> borderColor = new ArrayList<>();

            if (listItens != null && !this.listItens.isEmpty()) {

                for (Object object : listItens) {

                    this.qtidadeTota = Integer
                            .valueOf(this.qtidadeTota.intValue() + ((BigInteger) ((Object[]) object)[1]).intValue());

                    this.valorTotal = Double.valueOf(
                            this.valorTotal.doubleValue() + ((BigDecimal) ((Object[]) object)[2]).doubleValue());

                    values.add(((BigDecimal) ((Object[]) object)[2]).intValue());

                    labels.add(((String) ((Object[]) object)[0]));

                    bgColor.add("rgba(54, 162, 235, 0.2)");
                    borderColor.add("rgb(54, 162, 235)");

                }

                createChartModel(values, labels, bgColor, borderColor);

            } else {

                this.modelChart = null;
            }

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }

    public void createChartModel(List<Number> values, List<String> labels, List<String> bgColor,
                                 List<String> borderColor) {

        modelChart = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Produção X Operador");

        barDataSet.setData(values);
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);
        barDataSet.setBackgroundColor(bgColor);
        data.addChartDataSet(barDataSet);

        data.setLabels(labels);
        modelChart.setData(data);

        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
       // ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        modelChart.setOptions(options);

    }

    public void trocarEmpresa(){
        inicializarEmpresa(getEmpresa());

        pesquisar();
    }

    public PeriodoEnum getPeriodo() {
        return periodo;
    }

    public void setPeriodo(PeriodoEnum periodo) {
        this.periodo = periodo;
    }

    public List<?> getListItens() {
        return listItens;
    }

    public void setListItens(List<?> listItens) {
        this.listItens = listItens;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getQtidadeTota() {
        return qtidadeTota;
    }

    public void setQtidadeTota(Integer qtidadeTota) {
        this.qtidadeTota = qtidadeTota;
    }

    public AtendimentoService getServiceAtendimento() {
        return serviceAtendimento;
    }

    public void setServiceAtendimento(AtendimentoService serviceAtendimento) {
        this.serviceAtendimento = serviceAtendimento;
    }

    public BarChartModel getModelChart() {
        return modelChart;
    }

    public void setModelChart(BarChartModel modelChart) {
        this.modelChart = modelChart;
    }

}
