package com.proativaservicos.bean;


import com.proativaservicos.service.AtendimentoService;
import com.proativaservicos.service.DashboardRepository;
import com.proativaservicos.service.UsuarioLogadoService;
import com.proativaservicos.util.ColorUtil;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.constantes.DataEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;

@Named
@ViewScoped
public class DashboardBean extends GenericBean implements Serializable {

    private DashboardRepository repository = new DashboardRepository();

    @Inject
    private AtendimentoService serviceAtendimento;

    @Inject
    private UsuarioLogadoService usuarioLogadoService;
    // Filtros
    private String periodoSelecionado = "DIARIO";
    private Date dataInicio = new Date();
    private Date dataFim = new Date();

    // Cards
    private Long agentesOnline;
    private Long totalAtendimentosAbertos;
    private Long totalAtendimentosFinalizados;
    private Long totalDerivadosN2;
    private Long totalFinalizadosN1;
    private Long totalFinalizadosN2;

    // Métricas Formatadas
    private String valorTotalVendasFormatado;
    private String tmaFormatado;

    // Gráficos e Tabelas
    private DonutChartModel donutModel;
    private BarChartModel barModel;
    private List<Object[]> listaRanking;

    private String periodoRank;

    @PostConstruct
    public void init() {

        periodoRank = "Hoje";


        atualizarDados();

    }

    public void atualizarDados() {

        System.out.println(periodoSelecionado+" "+dataInicio+" "+dataFim);

        if (StringUtils.isNotBlank(periodoSelecionado)) {

            switch (periodoSelecionado) {
                case "DIARIO":
                    this.dataInicio = new Date();
                    this.dataFim = new Date();
                    this.periodoRank = "Hoje";
                    break;
                case "SEMANAL":
                    this.dataInicio = DateUtil.builder(new Date()).removerTempoData(DataEnum.SEMANA, 1).getData();
                    this.dataFim = new Date();
                    this.periodoRank = "Semanal";
                    break;
                case "MENSAL":
                    this.dataInicio = DateUtil.builder(new Date()).removerTempoData(DataEnum.MES, 1).getData();
                    this.dataFim = new Date();
                    this.periodoRank = "Mensal";
                    break;
                case "CUSTOM":
                    this.periodoRank = retornarPeridoRankString();


            }
        }

        // Simula busca no banco (Repository Mock)
        this.agentesOnline = (long) this.usuarioLogadoService.pesquisarQuantidadeUsuariosLogados(retornarEmpresaUsuarioSessao().getId());

        List<?> listResumo = this.serviceAtendimento.listarQuantidadeResumoDerivadosSac(this.dataInicio, this.dataFim);

        List<Object[]> listMotivo = this.serviceAtendimento.buscarQuantidadePorMotivo(this.dataInicio, this.dataFim);

        this.listaRanking = serviceAtendimento.buscarTop10Usuarios(this.dataInicio, this.dataFim);

        this.tmaFormatado = this.serviceAtendimento.buscarTmaAtendimentosSac(this.dataInicio, this.dataFim);


        List<Object[]> listStatus = this.serviceAtendimento.buscarQuantidadePorStatus(this.dataInicio, this.dataFim);

        if (CollectionUtils.isNotEmpty(listResumo)) {

            Object[] linha = (Object[]) listResumo.get(0);

            for (Object o : linha) {
                System.out.println("Linha: " + o);
            }

            this.totalAtendimentosFinalizados = safeLong(linha[0]);
            this.totalDerivadosN2 = safeLong(linha[1]);
            this.totalAtendimentosAbertos = safeLong(linha[2]);
            this.totalFinalizadosN1 = safeLong(linha[3]);
            this.totalFinalizadosN2 = safeLong(linha[4]);

        } else {

            this.totalAtendimentosFinalizados = 0L;
            this.totalDerivadosN2 = 0L;
            this.totalAtendimentosAbertos = 0L;
            this.totalFinalizadosN1 = 0L;
            this.totalFinalizadosN2 = 0L;
        }


        for (Object objects : listResumo) {
            System.out.println(objects.getClass().getSimpleName());

        }

    /*    this.totalAtendimentosAbertos = repository.contarAtendimentos("ABERTO");
        this.totalAtendimentosFinalizados = repository.contarAtendimentos("FINALIZADO");
        this.totalDerivadosN2 = repository.contarAtendimentos("N2");
        this.totalFinalizadosN1 = repository.contarAtendimentos("VIP") / 10; // Só um pouco
*/
        // Formata Moeda
        BigDecimal vendas = repository.somarValorVendas();
        this.valorTotalVendasFormatado = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(vendas);


        // Carrega Listas


        // Monta Gráficos
        createDonutModel(listMotivo);
        createBarModel(listStatus);
    }

    private String retornarPeridoRankString(){


        if(this.dataInicio!=null && this.dataFim!=null){

            String inicio = DateUtil.builder(this.dataInicio).formatarDataParaString("dd/MM/yyyy").getDataTexto();
            String fim = DateUtil.builder(this.dataFim).formatarDataParaString("dd/MM/yyyy").getDataTexto();

            return inicio+" - "+fim;


        }

        return "Personalizado";
    }

    private long safeLong(Object value) {
        return value != null ? ((Number) value).longValue() : 0L;
    }

    private int safeInt(Object value) {
        return value != null ? ((Number) value).intValue() : 0;
    }


    private void createDonutModel(List<Object[]> listMotivo) {


        donutModel = new DonutChartModel();
        ChartData data = new ChartData();
        DonutChartDataSet dataSet = new DonutChartDataSet();

        Map<String, Number> dados = new HashMap<>();
        List<String> bgColors = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(listMotivo)) {

            for (Object[] objects : listMotivo) {

                String motivo = (String) objects[0];
                String cor = (String) objects[1];
                Number valor = (Number) objects[2];
                dados.put(motivo, valor.intValue());
                bgColors.add(cor);


            }

            List<Number> values = new ArrayList<>(dados.values());
            List<String> labels = new ArrayList<>(dados.keySet());
            dataSet.setData(values);
            dataSet.setBackgroundColor(bgColors);
            data.addChartDataSet(dataSet);
            data.setLabels(labels);
            donutModel.setData(data);
        }
    }

    private void createBarModel(List<Object[]> listStatus) {

        barModel = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet dataSet = new BarChartDataSet();

        List<String> bgColors = new ArrayList<>();
        List<String> borderColors = new ArrayList<>();


        dataSet.setLabel("Qtd Atendimentos");
        Map<String, Number> dados = new HashMap<>();
        if (CollectionUtils.isNotEmpty(listStatus)) {

            for (Object[] objects : listStatus) {

                String status = (String) objects[0];
                Number valor = (Number) objects[1];
                bgColors.add(ColorUtil.getColorDinamic());
                borderColors.add(ColorUtil.getColorDinamic());
                dados.put(status, valor.intValue());
            }


            List<Number> values = new ArrayList<>(dados.values());
            List<String> labels = new ArrayList<>(dados.keySet());


            dataSet.setData(values);


            dataSet.setBackgroundColor(borderColors);
            dataSet.setBorderColor(borderColors);
            dataSet.setBorderWidth(1);


            data.addChartDataSet(dataSet);
            data.setLabels(labels);
            barModel.setData(data);
        }
    }

    // Getters e Setters obrigatórios para o JSF funcionar
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

    public Long getAgentesOnline() {
        return agentesOnline;
    }

    public Long getTotalAtendimentosAbertos() {
        return totalAtendimentosAbertos;
    }

    public Long getTotalAtendimentosFinalizados() {
        return totalAtendimentosFinalizados;
    }

    public Long getTotalDerivadosN2() {
        return totalDerivadosN2;
    }

    public Long getTotalFinalizadosN1() {
        return totalFinalizadosN1;
    }

    public String getValorTotalVendasFormatado() {
        return valorTotalVendasFormatado;
    }

    public String getTmaFormatado() {
        return tmaFormatado;
    }

    public DonutChartModel getDonutModel() {
        return donutModel;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public List<Object[]> getListaRanking() {
        return listaRanking;
    }

    public String getPeriodoRank() {
        return periodoRank;
    }
}