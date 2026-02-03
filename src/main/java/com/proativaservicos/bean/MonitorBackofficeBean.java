package com.proativaservicos.bean;

import java.math.BigDecimal;
import java.util.*;


import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.Departamento;
import com.proativaservicos.model.dto.ProdutividadeSacDto;
import com.proativaservicos.service.AtendimentoService;
import com.proativaservicos.service.DepartamentoService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Messages;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.charts.donut.DonutChartOptions;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.optionconfig.tooltip.Tooltip;

import com.proativaservicos.model.Equipe;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.service.AtendimentoBackofficeService;
import com.proativaservicos.service.EquipeService;
import com.proativaservicos.util.ColorUtil;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.constantes.PerfilUsuarioEnum;
import com.proativaservicos.util.constantes.TipoVisualizacaoEnum;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;
import org.primefaces.model.charts.pie.PieChartOptions;
import org.primefaces.model.charts.polar.PolarAreaChartDataSet;
import org.primefaces.model.charts.polar.PolarAreaChartModel;
import org.primefaces.model.charts.polar.PolarAreaChartOptions;

@Named
@ViewScoped
public class MonitorBackofficeBean extends GenericBean {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Inject
    private EquipeService equipeService;

    @Inject
    private AtendimentoBackofficeService serviceAtendimentoBackoffice;

    @Inject
    private AtendimentoService serviceAtendimento;

    @Inject
    private DepartamentoService seviceDepartamento;

    private Date dataInicioPanel;

    private Date dataFimPanel;

    private String periodo;

    private DonutChartModel donutModelStatusAtendimento;

    private DonutChartModel donutModelMotivo;

    private DonutChartModel donutSubmodelMotivo;

    private List<ProdutividadeSacDto> listStatusPainel;

    private List<ProdutividadeSacDto> listMotioPainel;

    private List<ProdutividadeSacDto> listSubmotioPainel;

    private List<ProdutividadeSacDto> listTotal;


    private PolarAreaChartModel polarAreaModelConsistencia;


    private List<Equipe> listEquipes;

    private List<Departamento> listDepartamentos;

    private Usuario usuarioLogado;


    private Long totalAtendimento;
    private Long totalFinalizado;
    private Long totalPrazo;
    private Long totalPrazoExcedido;
    private Long totalCpf;
    private Long totalFcr;
    private Double totalPercentFcr;
    private Double totalPercentReincidencia;
    private Long totalResolvidoN1;
    private Long totalResolvidoN2;
    private Long totalReincidencia;

    private Object[] produtividade;

    @PostConstruct
    public void init() {

        this.usuarioLogado = retornarUsuarioSessao();
        this.periodo = "DIA";
        this.dataInicioPanel = new Date();
        this.dataFimPanel = new Date();
        inicializarModelosVazios();
        criarDash();


    }


    private void criarDash() {


        this.listStatusPainel = this.serviceAtendimentoBackoffice.pesquisarProdutividadeAtendimentoSac(null, null, null, null, this.dataInicioPanel, this.dataFimPanel, TipoVisualizacaoEnum.STATUS);
        this.listMotioPainel = this.serviceAtendimentoBackoffice.pesquisarProdutividadeAtendimentoSac(null, null, null, null, this.dataInicioPanel, this.dataFimPanel, TipoVisualizacaoEnum.MOTIVO);
        this.listSubmotioPainel = this.serviceAtendimentoBackoffice.pesquisarProdutividadeAtendimentoSac(null, null, null, null, this.dataInicioPanel, this.dataFimPanel, TipoVisualizacaoEnum.SUBMOTIVO);

        criarCharts(listStatusPainel, "Status Atendimento");
        criarChatsMotivo();
        criarChatsSubmotivo();

        pesquisarQuantidadeTotal();


    }

    private void pesquisarQuantidadeTotal() {


        this.listTotal = this.serviceAtendimentoBackoffice.pesquisarProdutividadeAtendimentoSacTotal(null, null, null, null, this.dataInicioPanel, this.dataFimPanel);

        somarTotaisFooter(listTotal);
        criarDashConsistencia();
    }


    private void somarTotaisFooter(List<ProdutividadeSacDto> list) {

        this.totalAtendimento = 0L;
        this.totalCpf = 0L;
        this.totalPrazo = 0L;
        this.totalPrazoExcedido = 0L;
        this.totalFinalizado = 0L;
        this.totalFcr = 0L;
        this.totalResolvidoN1 = 0L;
        this.totalResolvidoN2 = 0L;
        this.totalReincidencia = 0L;

        if (CollectionUtils.isNotEmpty(list)) {

            for (ProdutividadeSacDto obj : list) {

                this.totalAtendimento = this.totalAtendimento + safeLong(obj.getQtdeAtendimento());
                this.totalCpf = this.totalCpf + safeLong(obj.getQtdeCpf());
                this.totalPrazo = totalPrazo + safeLong(obj.getQtdadeDemandaNoPrazo());
                this.totalPrazoExcedido = totalPrazoExcedido + safeLong(obj.getQtdadeDemandaPrazoEstourado());
                this.totalFinalizado = totalFinalizado + safeLong(obj.getQtdadeConcluido());
                this.totalFcr += safeLong(obj.getQtdeFcr());
                this.totalResolvidoN1 += safeLong(obj.getQtdadeResolvidoN1());
                this.totalResolvidoN2 += safeLong(obj.getQtdeResolvidoN2());
                this.totalReincidencia += safeLong(obj.getQtdeReincidencia());

                System.out.print(this.totalAtendimento);
                System.out.print(" - " + this.totalCpf);
                System.out.print(" - " + this.totalPrazo);
                System.out.print(" - " + this.totalPrazoExcedido);
                System.out.println(" - " + this.totalFinalizado);


            }

            if (this.totalAtendimento > 0) {

                // Percentual Total de FCR
                this.totalPercentFcr = (double) this.totalFcr / this.totalAtendimento;
                this.totalPercentReincidencia = (double) this.totalReincidencia / this.totalAtendimento;
                // Percentual Total No Prazo
                // this.totalPercentualNoPrazo = (double) this.totalPrazo / this.totalAtendimento;

                // Percentual Total Excedido
                //   this.totalPercentualPrazoExcedido = (double) this.totalPrazoExcedido / this.totalAtendimento;

            } else {
                // Se não houve atendimento, zera os percentuais
                this.totalPercentFcr = 0.0;
                this.totalPercentReincidencia = 0.0;
                // this.totalPercentualNoPrazo = 0.0;
                //this.totalPercentualPrazoExcedido = 0.0;
            }

        }


    }


    private void criarCharts(List<ProdutividadeSacDto> list, String title) {

        if (CollectionUtils.isNotEmpty(list)) {

            this.donutModelStatusAtendimento = new DonutChartModel();

            ChartData data = new ChartData();

            DonutChartDataSet dataSet = new DonutChartDataSet();

            List<Number> values = new ArrayList<>();
            List<String> labels = new ArrayList<>();
            List<String> bgColors = new ArrayList<>();

            for (ProdutividadeSacDto linhaOb : list) {

                values.add(safeLong(linhaOb.getQtdeAtendimento()));
                labels.add(linhaOb.getVisualizacao());
                String cor = ColorUtil.getColorDinamic();
                linhaOb.setCor(cor);
                bgColors.add(cor);

            }

            dataSet.setData(values);
            dataSet.setBackgroundColor(bgColors);
            data.addChartDataSet(dataSet);
            data.setLabels(labels);

            Legend legend = new Legend();
            legend.setDisplay(false);

            Title tile = new Title();
            tile.setText(title);

            DonutChartOptions op = new DonutChartOptions();
            op.setLegend(legend);
            op.setTitle(tile);

            this.donutModelStatusAtendimento.setOptions(op);
            this.donutModelStatusAtendimento.setData(data);
        }
    }

    private long safeLong(Long l) {
        if (l == null) {
            return 0;
        }
        return l;
    }

    private void criarChatsMotivo() {

        if (CollectionUtils.isNotEmpty(this.listMotioPainel)) {

            ChartData data = criarDataSet(this.listMotioPainel);

            DonutChartOptions op = new DonutChartOptions();
            Legend legend = new Legend();
            legend.setDisplay(false);

            op.setLegend(legend);

            this.donutModelMotivo = new DonutChartModel();
            this.donutModelMotivo.setOptions(op);
            this.donutModelMotivo.setData(data);
        }
    }

    private void criarChatsSubmotivo() {

        if (CollectionUtils.isNotEmpty(this.listMotioPainel)) {

            ChartData data = criarDataSet(this.listSubmotioPainel);

            DonutChartOptions op = new DonutChartOptions();
            Legend legend = new Legend();
            legend.setDisplay(false);

            op.setLegend(legend);

            this.donutSubmodelMotivo = new DonutChartModel();
            this.donutSubmodelMotivo.setOptions(op);
            this.donutSubmodelMotivo.setData(data);
        }
    }

    private ChartData criarDataSet(List<ProdutividadeSacDto> list) {

        ChartData data = new ChartData();

        DonutChartDataSet dataSet = new DonutChartDataSet();

        List<Number> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        List<String> bgColors = new ArrayList<>();

        for (ProdutividadeSacDto linhaOb : list) {

            values.add(safeLong(linhaOb.getQtdeAtendimento()));
            labels.add(linhaOb.getVisualizacao());
            String cor = ColorUtil.getColorDinamic();

            if (StringUtils.isNotBlank(linhaOb.getCor()))
                bgColors.add(linhaOb.getCor());
            else
                bgColors.add(cor);

        }

        dataSet.setData(values);
        dataSet.setBackgroundColor(bgColors);
        data.addChartDataSet(dataSet);
        data.setLabels(labels);

        return data;

    }

    private void criarDashConsistencia() {

        this.polarAreaModelConsistencia = new PolarAreaChartModel(); // agora é Polar Area Chart

        if (CollectionUtils.isNotEmpty(this.listTotal)) {
            ChartData data = new ChartData();
            List<String> labels = new ArrayList<>();

            PolarAreaChartDataSet polarDataSet = new PolarAreaChartDataSet();

            // cores para cada setor
            polarDataSet.setBackgroundColor(List.of("rgb(75, 192, 192)", "rgb(255, 99, 132)"));

            List<Number> valores = new ArrayList<>();
            valores.add(safeLong(this.listTotal.get(0).getQtdadeConcluido()));   // concluídas
            valores.add(safeLong(this.listTotal.get(0).getQtidadeEmAberto()));   // em aberto

            labels.add("Concluídas");
            labels.add("Em Aberto");

            polarDataSet.setData(valores);
            data.addChartDataSet(polarDataSet);
            data.setLabels(labels);

            Title title = new Title();
            title.setDisplay(true);
            title.setText("Atendimentos Concluídos x Em Aberto");

            PolarAreaChartOptions options = new PolarAreaChartOptions();
            options.setTitle(title);

            this.polarAreaModelConsistencia.setOptions(options);
            this.polarAreaModelConsistencia.setData(data);
        }
    }

    public void onAtualizarPanel() {
        try {


            this.produtividade = null;
            this.listTotal = null;

            switch (periodo) {

                case "DIA":

                    this.dataInicioPanel = new Date();
                    this.dataFimPanel = new Date();
                    break;

                case "MES":

                    this.dataInicioPanel = DateUtil.builder(new Date()).retornarDataPrimeiroDiaMes().retornarDataComHoraInicial().getData();
                    this.dataFimPanel = DateUtil.builder(new Date()).retornarDataUltimoDiaMes().retornarDataComHoraFinal().getData();
                    break;

                case "SEMANA":

                    this.dataInicioPanel = DateUtil.builder(new Date()).retornarDataPrimeiroDiaSemana().retornarDataComHoraInicial().getData();
                    this.dataFimPanel = DateUtil.builder(new Date()).retornarDataUltimoDiaSemana().retornarDataComHoraFinal().getData();
                    break;

                case "CUSTOM":
                    if (this.dataInicioPanel == null || this.dataFimPanel == null) {

                        throw new ProativaException("Informe o período");
                    }
                    break;

                default:
                    this.dataInicioPanel = DateUtil.builder(new Date()).retornarDataComHoraInicial().getData();
                    this.dataFimPanel = DateUtil.builder(new Date()).retornarDataComHoraFinal().getData();
                    break;
            }

            criarDash();


        } catch (ProativaException e) {
            Messages.addGlobalError(e.getMessage());
        }

    }

    private void inicializarModelosVazios() {
        // Crie instâncias vazias. O PrimeFaces precisa do objeto e do ChartData instanciados.

        // 1. Status
        donutModelStatusAtendimento = new DonutChartModel();
        donutModelStatusAtendimento.setData(new ChartData()); // Importante: Data não pode ser null

        // 2. Motivo
        donutModelMotivo = new DonutChartModel();
        donutModelMotivo.setData(new ChartData());

        // 3. Submotivo
        donutSubmodelMotivo = new DonutChartModel();
        donutSubmodelMotivo.setData(new ChartData());

        // 4. Bar Chart
        polarAreaModelConsistencia = new PolarAreaChartModel();
        polarAreaModelConsistencia.setData(new ChartData());
    }


    public Date getDataInicioPanel() {
        return dataInicioPanel;
    }


    public void setDataInicioPanel(Date dataInicioPanel) {
        this.dataInicioPanel = dataInicioPanel;
    }


    public Date getDataFimPanel() {
        return dataFimPanel;
    }


    public void setDataFimPanel(Date dataFimPanel) {
        this.dataFimPanel = dataFimPanel;
    }


    public String getPeriodo() {
        return periodo;
    }


    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }


    public DonutChartModel getDonutModelStatusAtendimento() {
        return donutModelStatusAtendimento;
    }


    public void setDonutModelStatusAtendimento(DonutChartModel donutModelStatusAtendimento) {
        this.donutModelStatusAtendimento = donutModelStatusAtendimento;
    }


    public DonutChartModel getDonutModelMotivo() {
        return donutModelMotivo;
    }


    public void setDonutModelMotivo(DonutChartModel donutModelMotivo) {
        this.donutModelMotivo = donutModelMotivo;
    }


    public DonutChartModel getDonutSubmodelMotivo() {
        return donutSubmodelMotivo;
    }


    public void setDonutSubmodelMotivo(DonutChartModel donutSubmodelMotivo) {
        this.donutSubmodelMotivo = donutSubmodelMotivo;
    }


    public List<ProdutividadeSacDto> getListStatusPainel() {
        return listStatusPainel;
    }


    public void setListStatusPainel(List<ProdutividadeSacDto> listStatusPainel) {
        this.listStatusPainel = listStatusPainel;
    }


    public List<ProdutividadeSacDto> getListMotioPainel() {
        return listMotioPainel;
    }


    public void setListMotioPainel(List<ProdutividadeSacDto> listMotioPainel) {
        this.listMotioPainel = listMotioPainel;
    }


    public List<ProdutividadeSacDto> getListSubmotioPainel() {
        return listSubmotioPainel;
    }


    public void setListSubmotioPainel(List<ProdutividadeSacDto> listSubmotioPainel) {
        this.listSubmotioPainel = listSubmotioPainel;
    }


    public PolarAreaChartModel getPolarAreaModelConsistencia() {
        return polarAreaModelConsistencia;
    }

    public void setPolarAreaModelConsistencia(PolarAreaChartModel polarAreaModelConsistencia) {
        this.polarAreaModelConsistencia = polarAreaModelConsistencia;
    }

    public List<Equipe> getListEquipes() {
        return listEquipes;
    }


    public void setListEquipes(List<Equipe> listEquipes) {
        this.listEquipes = listEquipes;
    }


    public Long getTotalFinalizado() {
        return totalFinalizado;
    }

    public void setTotalFinalizado(Long totalFinalizado) {
        this.totalFinalizado = totalFinalizado;
    }

    public Long getTotalPrazo() {
        return totalPrazo;
    }

    public void setTotalPrazo(Long totalPrazo) {
        this.totalPrazo = totalPrazo;
    }

    public Long getTotalPrazoExcedido() {
        return totalPrazoExcedido;
    }

    public void setTotalPrazoExcedido(Long totalPrazoExcedido) {
        this.totalPrazoExcedido = totalPrazoExcedido;
    }

    public Long getTotalCpf() {
        return totalCpf;
    }

    public void setTotalCpf(Long totalCpf) {
        this.totalCpf = totalCpf;
    }

    public List<ProdutividadeSacDto> getListTotal() {
        return listTotal;
    }

    public void setListTotal(List<ProdutividadeSacDto> listTotal) {
        this.listTotal = listTotal;
    }

    public Long getTotalAtendimento() {
        return totalAtendimento;
    }

    public Long getTotalFcr() {
        return totalFcr;
    }

    public void setTotalFcr(Long totalFcr) {
        this.totalFcr = totalFcr;
    }

    public Double getTotalPercentFcr() {
        return totalPercentFcr;
    }

    public void setTotalPercentFcr(Double totalPercentFcr) {
        this.totalPercentFcr = totalPercentFcr;
    }

    public Long getTotalResolvidoN1() {
        return totalResolvidoN1;
    }

    public Long getTotalResolvidoN2() {
        return totalResolvidoN2;
    }

    public Double getTotalPercentReincidencia() {
        return totalPercentReincidencia;
    }

    public Long getTotalReincidencia() {
        return totalReincidencia;
    }
}
