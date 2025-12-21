package com.proativaservicos.bean;

import com.proativaservicos.model.Campanha;
import com.proativaservicos.model.StatusCampanha;
import com.proativaservicos.service.AtendimentoService;
import com.proativaservicos.service.CampanhaService;
import com.proativaservicos.service.ChamadasAtendimentoService;
import com.proativaservicos.service.StatusCampanhaService;
import com.proativaservicos.util.ColorUtil;
import com.proativaservicos.util.constantes.*;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.collections4.CollectionUtils;
import org.omnifaces.util.Messages;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;
import org.primefaces.model.charts.pie.PieChartOptions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;

@Named
@ViewScoped
public class MonitorCampanhaDiscador extends GenericBean {

    private static final long serialVersionUID = 1L;

    @Inject
    private CampanhaService serviceCampanha;

    @Inject
    private ChamadasAtendimentoService serviceChamadasAtendimentos;

    @Inject
    private StatusCampanhaService serviceStatusCampanha;

    @Inject
    private AtendimentoService serviceAtendimento;

    private Long campanha;

    private Long idStatusCampanha;
    private List<Campanha> listCampanhas;
    private List<StatusCampanha> listStatusCampanha;
    private List<?> listResults;
    private List<?> listResultsHorasCompletadas;
    private List<?> listResultsHorasDiscartadas;
    private List<?> listQuantidadesStatusTelDiscador;
    private Date dataInicio;
    private Date dataFim;

    private BigDecimal spin;
    private Integer quantidadeAdesao;
    private BigDecimal valorTotalAdesao;
    private BigInteger descartadas;
    private BigInteger completadas;
    private BigDecimal totalAtendimentos;
    private BigDecimal totalAtendidos;
    private BigDecimal totalFinalizados;
    private BigDecimal totalRestantes;
    private BigDecimal percentualAtendidos;
    private BigDecimal percentualFinalizados;
    private Integer percentualFinalizar;
    private Integer qtidadeTotalTelefones;


    private BigDecimal ticketMedioProduzido;
    private BigDecimal ticketMedioSaque;
    private BigDecimal valorMaximoOperacao;
    private BigDecimal valorMinOperacao;
    private BigDecimal valorTotalOperacao;
    private BigDecimal valorCpc;
    private BigDecimal valorTma;

    private LineChartModel cartesianLinerHours;

    private Object[] analiseCampamha;

    private List<?> listQuantidades;

    private List<?> listQuantidadeStatus;

    private PieChartModel pieModelStatusAtendimento;

    private PieChartModel pieModelDiscagem;

    private List<?> listQuantidadeStatusTelefones;

    private Campanha campanhaDiscador;


    @PostConstruct
    public void init() {

        this.cartesianLinerHours = new LineChartModel();

        inicializarEmpresa(retornarEmpresaMatrizUsuarioSessao());

        trocarEmpresa();
        this.listStatusCampanha = this.serviceStatusCampanha.pesquisarStatusCampanhaPorEmpresa(retornarEmpresaMatrizUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);
        this.pieModelDiscagem = new PieChartModel();

    }

    public void pesquisar() {

        this.totalAtendimentos = BigDecimal.ZERO;
        this.totalAtendidos = BigDecimal.ZERO;
        this.totalFinalizados = BigDecimal.ZERO;
        this.totalRestantes = BigDecimal.ZERO;
        this.percentualAtendidos = BigDecimal.ZERO;
        this.percentualFinalizados = BigDecimal.ZERO;
        this.valorTotalOperacao = BigDecimal.ZERO;
        this.valorCpc = BigDecimal.ZERO;
        this.valorTma = BigDecimal.ZERO;
        this.quantidadeAdesao = 0;
        this.completadas = BigInteger.ZERO;
        this.spin = BigDecimal.ZERO;
        this.qtidadeTotalTelefones = 0;
        this.valorMaximoOperacao = BigDecimal.ZERO;
        this.valorMinOperacao = BigDecimal.ZERO;

        this.listQuantidades = null;


        this.qtidadeTotalTelefones = this.serviceAtendimento.pesquisarQuantidadeTelefonePorCampanha(getEmpresa().getId(), this.campanha);
        this.campanhaDiscador = this.serviceCampanha.pesquisarCampanha(this.campanha, false);

        Object[] objCpcTma = null;

        if (this.campanhaDiscador != null && this.campanhaDiscador.getId() != null && this.campanhaDiscador.getTipoCampanha().equals(TipoCampanhaEnum.PREDITIVA_3C))
            objCpcTma = this.serviceChamadasAtendimentos.pesquisarCpcPorCampanha3c(this.campanha);
        else
            objCpcTma = this.serviceChamadasAtendimentos.pesquisarCpcPorCampanha(this.campanha);

        if (objCpcTma != null) {

            if (objCpcTma[0] instanceof BigDecimal)
                this.valorCpc = (objCpcTma[0] == null) ? BigDecimal.ZERO : ((BigDecimal) objCpcTma[0]);
            else
                this.valorCpc = (objCpcTma[0] == null) ? BigDecimal.ZERO : new BigDecimal(((BigInteger) objCpcTma[0]).intValue());

            if (objCpcTma[1] instanceof BigDecimal)
                this.valorTma = (objCpcTma[1] == null) ? BigDecimal.ZERO : ((BigDecimal) objCpcTma[1]);
            else
                this.valorTma = (objCpcTma[1] == null) ? BigDecimal.ZERO : new BigDecimal(((BigInteger) objCpcTma[1]).intValue());

            if (this.valorCpc != null) {

                this.valorCpc = this.valorCpc.setScale(0, RoundingMode.HALF_DOWN);
            }


        }

        this.listQuantidadeStatusTelefones = serviceAtendimento.pesquisarQuantidadeStatusTelefonePorCampanha(getEmpresa().getId(), this.campanha, null, null, null);

        this.listQuantidadeStatus = this.serviceAtendimento.pesquisarQuantidadeStatusAtendimentoPorCampanha(getEmpresa().getId(), this.campanha, null, null, null);

        analisarCampanha();

        validarStatusDiscador();
        gerarRelatorioHora();

        if (listQuantidadeStatus != null && !listQuantidadeStatus.isEmpty())
            createPolarAreaStatusAtendimento(listQuantidadeStatus);
    }

    private void createPolarAreaStatusAtendimento(List<?> listValue) {

        this.pieModelStatusAtendimento = new PieChartModel();
        ChartData data = new ChartData();

        PieChartDataSet dataSet = new PieChartDataSet();

        List<Number> values = new ArrayList<Number>();
        List<String> labels = new ArrayList<String>();

        List<String> bgColor = new ArrayList<>();

        for (Object object : listValue) {

            values.add(((BigDecimal) ((Object[]) object)[1]).intValue());
            labels.add(((String) ((Object[]) object)[0]));

            bgColor.add(ColorUtil.getColorDinamic());

        }

        PieChartOptions options = new PieChartOptions();
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Status de atendimento");
        options.setAnimateRotate(true);
        options.setTitle(title);
        Legend legend = new Legend();
        legend.setPosition("right");
        options.setLegend(legend);

        this.pieModelStatusAtendimento.setOptions(options);

        dataSet.setData(values);
        dataSet.setBackgroundColor(bgColor);
        data.addChartDataSet(dataSet);
        data.setLabels(labels);

        this.pieModelStatusAtendimento.setData(data);

    }

    private void analisarCampanha() {

        this.listQuantidades = this.serviceAtendimento.pesquisarQuantidadeMonitorCampanhaCarga(getEmpresa().getId(), this.campanha, null, null);

        this.analiseCampamha = this.serviceAtendimento.pesquisarAnsaliseCampanha(campanha);

        if (CollectionUtils.isNotEmpty(this.listCampanhas) && CollectionUtils.isNotEmpty(this.listQuantidades)) {

            Object[] quantidadeCampanha = (Object[]) this.listQuantidades.get(0);

            this.totalAtendimentos = (BigDecimal) quantidadeCampanha[0];
            this.totalAtendidos = (BigDecimal) quantidadeCampanha[1];
            this.percentualAtendidos = (BigDecimal) quantidadeCampanha[3];
            this.percentualFinalizados = (BigDecimal) quantidadeCampanha[4];

            if (quantidadeCampanha[2] != null)
                this.totalFinalizados = (BigDecimal) quantidadeCampanha[2];

            this.totalRestantes = (BigDecimal) quantidadeCampanha[5];

            if (this.totalAtendimentos != null && this.totalAtendimentos.longValue() > 0) {

                BigDecimal val = (this.totalRestantes.multiply(BigDecimal.valueOf(Double.valueOf(100)))).divide(this.totalAtendimentos, RoundingMode.UP);

                this.percentualFinalizar = Integer.valueOf(val.intValue());

            }

            Object[] obSpin = this.serviceChamadasAtendimentos.pesquisarSpin(this.campanha);

            if (obSpin != null) {

                this.spin = (obSpin[1] == null) ? BigDecimal.ZERO : (BigDecimal) obSpin[1];

            }

        }

        if (this.analiseCampamha != null) {

            if (analiseCampamha[6] instanceof BigDecimal)
                this.valorTotalAdesao = (analiseCampamha[6] == null) ? BigDecimal.valueOf(BigDecimal.ZERO.doubleValue()) : BigDecimal.valueOf(((BigDecimal) analiseCampamha[6]).longValue());
            else
                this.valorTotalAdesao = (analiseCampamha[6] == null) ? BigDecimal.valueOf(BigDecimal.ZERO.doubleValue()) : BigDecimal.valueOf(((BigInteger) analiseCampamha[6]).longValue());

            if (analiseCampamha[7] instanceof BigDecimal)
                this.ticketMedioProduzido = (analiseCampamha[7] == null) ? BigDecimal.valueOf(BigDecimal.ZERO.doubleValue()) : BigDecimal.valueOf(((BigDecimal) analiseCampamha[7]).longValue());
            else
                this.ticketMedioProduzido = (analiseCampamha[7] == null) ? BigDecimal.valueOf(BigDecimal.ZERO.doubleValue()) : BigDecimal.valueOf(((BigInteger) analiseCampamha[7]).longValue());

            if (analiseCampamha[8] instanceof BigDecimal)
                this.quantidadeAdesao = (analiseCampamha[8] == null) ? Integer.valueOf(0) : ((BigDecimal) analiseCampamha[8]).intValue();
            else
                this.quantidadeAdesao = (analiseCampamha[8] == null) ? Integer.valueOf(0) : ((BigInteger) analiseCampamha[8]).intValue();

        }

    }

    private void gerarRelatorioHora() {

        this.listResultsHorasCompletadas = this.serviceChamadasAtendimentos.pesquisarQuantidadePorStatusEqual(campanha, this.campanhaDiscador.getTipoCampanha().equals(TipoCampanhaEnum.PREDITIVA_3C) ? "7" : "_16", this.campanhaDiscador.getTipoCampanha());
        this.listResultsHorasDiscartadas = this.serviceChamadasAtendimentos.pesquisarQuantidadePorStatusNotEqual(campanha, this.campanhaDiscador.getTipoCampanha().equals(TipoCampanhaEnum.PREDITIVA_3C) ? "7" : "_16", this.campanhaDiscador.getTipoCampanha());

        Map<String, BigInteger> mapChamadasCompletas = new LinkedHashMap<String, BigInteger>();
        Map<String, BigInteger> mapChamadasDescartadas = new LinkedHashMap<String, BigInteger>();
        List<String> labels = new ArrayList<>();

        for (int i = 0; i <= 23; i++) {

            String hora = retornarHora(i);
            mapChamadasCompletas.put(hora, BigInteger.ZERO);
            mapChamadasDescartadas.put(hora, BigInteger.ZERO);
            labels.add(hora);

        }

        mapChamadasCompletas = popularMap(this.listResultsHorasCompletadas);
        mapChamadasDescartadas = popularMap(this.listResultsHorasDiscartadas);

        ChartData data = new ChartData();

        LineChartDataSet dataSetCompletadas = new LineChartDataSet();

        List<Object> valuesCompletadas = new ArrayList<Object>();

        LineChartDataSet dataSetDescartadas = new LineChartDataSet();

        List<Object> valuesDescartadas = new ArrayList<Object>();

        for (int i = 0; i <= 23; i++) {

            String hora = retornarHora(i);
            valuesCompletadas.add(mapChamadasCompletas.getOrDefault(hora, BigInteger.ZERO));
            valuesDescartadas.add(mapChamadasDescartadas.getOrDefault(hora, BigInteger.ZERO));

        }

        dataSetCompletadas.setData(valuesCompletadas);
        dataSetCompletadas.setLabel("Completadas");

        dataSetCompletadas.setFill(true);
        //  dataSetCompletadas.setLineTension(0.1);
        dataSetCompletadas.setBorderColor("rgb(65,105,225)");
        dataSetCompletadas.setBackgroundColor("rgb(65,105,225)");

        dataSetDescartadas.setData(valuesDescartadas);
        dataSetDescartadas.setLabel("Descartadas/Não atendidas");

        dataSetDescartadas.setFill(true);
        //dataSetDescartadas.setLineTension(0.1);
        dataSetDescartadas.setBorderColor("rgb(0,128,128)");
        dataSetDescartadas.setBackgroundColor("rgb(0,128,128)");

        data.addChartDataSet(dataSetCompletadas);
        data.addChartDataSet(dataSetDescartadas);
        data.setLabels(labels);

        this.cartesianLinerHours.setData(data);

        LineChartOptions options = new LineChartOptions();

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Chamdas por hora");
        options.setTitle(title);

        this.cartesianLinerHours.setOptions(options);

    }

    private void gerarRelatorioDiscagem() {

        this.pieModelDiscagem = new PieChartModel();
        ChartData data = new ChartData();

        PieChartDataSet dataSet = new PieChartDataSet();

        List<Number> values = new ArrayList<Number>();
        List<String> labels = new ArrayList<String>();

        List<String> bgColor = new ArrayList<>();


        values.add(this.completadas);
        values.add(this.descartadas);

        labels.add("Completadas");
        labels.add("Descartadas/Não atendidas");

        bgColor.add("rgb(65,105,225)");
        bgColor.add("rgb(0,128,128)");

        PieChartOptions options = new PieChartOptions();
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Discagem");
        options.setAnimateRotate(true);
        options.setTitle(title);
        Legend legend = new Legend();

        options.setLegend(legend);

        this.pieModelDiscagem.setOptions(options);

        dataSet.setData(values);
        dataSet.setBackgroundColor(bgColor);
        data.addChartDataSet(dataSet);
        data.setLabels(labels);

        this.pieModelDiscagem.setData(data);


    }

    private String retornarHora(int i) {

        if (i < 10) {

            return String.valueOf("0" + i + ":00");
        } else {
            return String.valueOf(i + ":00");
        }

    }

    private Map<String, BigInteger> popularMap(List<?> list) {

        Map<String, BigInteger> map = new LinkedHashMap<String, BigInteger>();

        for (Object completas : list) {

            Object[] ob = (Object[]) completas;

            if (ob[1] != null && ob[0] != null) {

                String hora = retornarHora(Integer.valueOf((String) ob[1]).intValue());
                map.put(hora, (BigInteger) ob[0]);

            }

        }

        return map;
    }

    private void validarStatusDiscador() {


        if (campanhaDiscador != null && campanhaDiscador.getTipoCampanha().name().equals(TipoCampanhaEnum.PREDITIVA_3C.name()))
            this.listQuantidadesStatusTelDiscador = this.serviceChamadasAtendimentos.pesquisarQuantidadePorStatusDiscador(campanha, TipoIntegracaoEnum.TRES_CPLUS);
        else
            this.listQuantidadesStatusTelDiscador = this.serviceChamadasAtendimentos.pesquisarQuantidadePorStatusDiscador(campanha, TipoIntegracaoEnum.VONIX);

        this.descartadas = BigInteger.ZERO;
        this.completadas = BigInteger.ZERO;

        for (Object ob : listQuantidadesStatusTelDiscador) {

            Object[] results = (Object[]) ob;

            if (campanhaDiscador.getTipoCampanha().equals(TipoCampanhaEnum.PREDITIVA_3C))
                gerarStatusTelefoneDiscador3c(results);
            else
                gerarStatusTelefoneDiscadorVonix(results);


        }

        gerarRelatorioDiscagem();
    }

    private void gerarStatusTelefoneDiscadorVonix(Object[] results) {

        StatusTelefoneVonixEnum statusTel = StatusTelefoneVonixEnum.getStatusTelefone((String) results[1]);

        if (statusTel != null) {

            results[1] = statusTel.getDescricao();

            if (statusTel.getCodigo() == 16) {

                this.completadas = this.completadas.add((BigInteger) results[0]);

            } else {

                this.descartadas = this.descartadas.add((BigInteger) results[0]);
            }

        }


    }

    private void gerarStatusTelefoneDiscador3c(Object[] results) {

        if (results != null && results.length > 1 && results[1] != null) {

            StatusTelefone3c statusTel = StatusTelefone3c.getStatusTelefone(String.valueOf(results[1]));

            if (statusTel != null) {

                results[1] = statusTel.getDescricao();

                if (statusTel.getCodigo() == 2 || statusTel.getCodigo() == 3 || statusTel.getCodigo() == 7) {

                    this.completadas = this.completadas.add((BigInteger) results[0]);

                } else {

                    this.descartadas = this.descartadas.add((BigInteger) results[0]);
                }

            }
        }


    }

    public void trocarEmpresa() {

        if (getEmpresa() == null) {

            this.listCampanhas = null;

        } else {

            this.listCampanhas = this.serviceCampanha.pesquisarCampanhasPorEmpresa(getEmpresa().getId());
        }

    }

    public void onChangeStatusCampanha() {

        try {

            if (this.idStatusCampanha != null && getEmpresa() != null && getEmpresa().getId() != null) {

                this.listCampanhas = this.serviceCampanha.pesquisarCampanhasPorEmpresa(getEmpresa().getId(), this.idStatusCampanha);
            }

        } catch (Exception e) {

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
            e.printStackTrace();

        }

    }

    /**
     * @return the campanha
     */
    public Long getCampanha() {
        return campanha;
    }

    /**
     * @param campanha the campanha to set
     */
    public void setCampanha(Long campanha) {
        this.campanha = campanha;
    }

    /**
     * @return the listCampanhas
     */
    public List<Campanha> getListCampanhas() {
        return listCampanhas;
    }

    /**
     * @param listCampanhas the listCampanhas to set
     */
    public void setListCampanhas(List<Campanha> listCampanhas) {
        this.listCampanhas = listCampanhas;
    }

    /**
     * @return the idStatusCampanha
     */
    public Long getIdStatusCampanha() {
        return idStatusCampanha;
    }

    /**
     * @param idStatusCampanha the idStatusCampanha to set
     */
    public void setIdStatusCampanha(Long idStatusCampanha) {
        this.idStatusCampanha = idStatusCampanha;
    }

    /**
     * @return the listStatusCampanha
     */
    public List<StatusCampanha> getListStatusCampanha() {
        return listStatusCampanha;
    }

    /**
     * @return the totalAtendimentos
     */
    public BigDecimal getTotalAtendimentos() {
        return totalAtendimentos;
    }

    /**
     * @param totalAtendimentos the totalAtendimentos to set
     */
    public void setTotalAtendimentos(BigDecimal totalAtendimentos) {
        this.totalAtendimentos = totalAtendimentos;
    }

    /**
     * @return the totalAtendidos
     */
    public BigDecimal getTotalAtendidos() {
        return totalAtendidos;
    }

    /**
     * @param totalAtendidos the totalAtendidos to set
     */
    public void setTotalAtendidos(BigDecimal totalAtendidos) {
        this.totalAtendidos = totalAtendidos;
    }

    /**
     * @return the totalFinalizados
     */
    public BigDecimal getTotalFinalizados() {
        return totalFinalizados;
    }

    /**
     * @param totalFinalizados the totalFinalizados to set
     */
    public void setTotalFinalizados(BigDecimal totalFinalizados) {
        this.totalFinalizados = totalFinalizados;
    }

    /**
     * @return the totalRestantes
     */
    public BigDecimal getTotalRestantes() {
        return totalRestantes;
    }

    /**
     * @param totalRestantes the totalRestantes to set
     */
    public void setTotalRestantes(BigDecimal totalRestantes) {
        this.totalRestantes = totalRestantes;
    }

    /**
     * @return the listResultsHorasCompletadas
     */
    public List<?> getListResultsHorasCompletadas() {
        return listResultsHorasCompletadas;
    }

    /**
     * @param listResultsHorasCompletadas the listResultsHorasCompletadas to set
     */
    public void setListResultsHorasCompletadas(List<?> listResultsHorasCompletadas) {
        this.listResultsHorasCompletadas = listResultsHorasCompletadas;
    }

    /**
     * @return the listResultsHorasDiscartadas
     */
    public List<?> getListResultsHorasDiscartadas() {
        return listResultsHorasDiscartadas;
    }

    /**
     * @param listResultsHorasDiscartadas the listResultsHorasDiscartadas to set
     */
    public void setListResultsHorasDiscartadas(List<?> listResultsHorasDiscartadas) {
        this.listResultsHorasDiscartadas = listResultsHorasDiscartadas;
    }

    /**
     * @return the completadas
     */
    public BigInteger getCompletadas() {
        return completadas;
    }

    /**
     * @param completadas the completadas to set
     */
    public void setCompletadas(BigInteger completadas) {
        this.completadas = completadas;
    }

    /**
     * @return the descartadas
     */
    public BigInteger getDescartadas() {
        return descartadas;
    }

    /**
     * @param descartadas the descartadas to set
     */
    public void setDescartadas(BigInteger descartadas) {
        this.descartadas = descartadas;
    }

    /**
     * @return the listQuantidades
     */
    public List<?> getListQuantidades() {
        return listQuantidades;
    }

    /**
     * @param listQuantidades the listQuantidades to set
     */
    public void setListQuantidades(List<?> listQuantidades) {
        this.listQuantidades = listQuantidades;
    }

    /**
     * @param listQuantidadesStatusTelDiscador the listQuantidadesStatusTelDiscador
     *                                         to set
     */
    public void setListQuantidadesStatusTelDiscador(List<?> listQuantidadesStatusTelDiscador) {
        this.listQuantidadesStatusTelDiscador = listQuantidadesStatusTelDiscador;
    }

    /**
     * @param listStatusCampanha the listStatusCampanha to set
     */
    public void setListStatusCampanha(List<StatusCampanha> listStatusCampanha) {
        this.listStatusCampanha = listStatusCampanha;
    }

    /**
     * @return the listResults
     */
    public List<?> getListResults() {
        return listResults;
    }

    /**
     * @param listResults the listResults to set
     */
    public void setListResults(List<?> listResults) {
        this.listResults = listResults;
    }

    /**
     * @return the dataInicio
     */
    public Date getDataInicio() {
        return dataInicio;
    }

    /**
     * @param dataInicio the dataInicio to set
     */
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * @return the dataFim
     */
    public Date getDataFim() {
        return dataFim;
    }

    /**
     * @param dataFim the dataFim to set
     */
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public BigDecimal getPercentualAtendidos() {
        return percentualAtendidos;
    }

    public BigDecimal getSpin() {
        return spin;
    }

    public void setSpin(BigDecimal spin) {
        this.spin = spin;
    }

    public List<?> getListQuantidadesStatusTelDiscador() {
        return listQuantidadesStatusTelDiscador;
    }

    public LineChartModel getCartesianLinerHours() {
        return cartesianLinerHours;
    }

    public Integer getQtidadeTotalTelefones() {
        return qtidadeTotalTelefones;
    }

    public BigDecimal getPercentualFinalizados() {
        return percentualFinalizados;
    }

    /**
     * @return the percentualFinalizar
     */
    public Integer getPercentualFinalizar() {
        return percentualFinalizar;
    }

    /**
     * @param percentualFinalizar the percentualFinalizar to set
     */
    public void setPercentualFinalizar(Integer percentualFinalizar) {
        this.percentualFinalizar = percentualFinalizar;
    }

    /**
     * @return the quantidadeAdesao
     */
    public Integer getQuantidadeAdesao() {
        return quantidadeAdesao;
    }

    /**
     * @param quantidadeAdesao the quantidadeAdesao to set
     */
    public void setQuantidadeAdesao(Integer quantidadeAdesao) {
        this.quantidadeAdesao = quantidadeAdesao;
    }

    /**
     * @return the valorTotalAdesao
     */
    public BigDecimal getValorTotalAdesao() {
        return valorTotalAdesao;
    }

    /**
     * @param valorTotalAdesao the valorTotalAdesao to set
     */
    public void setValorTotalAdesao(BigDecimal valorTotalAdesao) {
        this.valorTotalAdesao = valorTotalAdesao;
    }

    /**
     * @param percentualAtendidos the percentualAtendidos to set
     */
    public void setPercentualAtendidos(BigDecimal percentualAtendidos) {
        this.percentualAtendidos = percentualAtendidos;
    }

    /**
     * @param percentualFinalizados the percentualFinalizados to set
     */
    public void setPercentualFinalizados(BigDecimal percentualFinalizados) {
        this.percentualFinalizados = percentualFinalizados;
    }

    /**
     * @param qtidadeTotalTelefones the qtidadeTotalTelefones to set
     */
    public void setQtidadeTotalTelefones(Integer qtidadeTotalTelefones) {
        this.qtidadeTotalTelefones = qtidadeTotalTelefones;
    }

    /**
     * @param cartesianLinerHours the cartesianLinerHours to set
     */
    public void setCartesianLinerHours(LineChartModel cartesianLinerHours) {
        this.cartesianLinerHours = cartesianLinerHours;
    }

    /**
     * @return the ticketMedioProduzido
     */
    public BigDecimal getTicketMedioProduzido() {
        return ticketMedioProduzido;
    }

    /**
     * @param ticketMedioProduzido the ticketMedioProduzido to set
     */
    public void setTicketMedioProduzido(BigDecimal ticketMedioProduzido) {
        this.ticketMedioProduzido = ticketMedioProduzido;
    }

    /**
     * @return the ticketMedioSaque
     */
    public BigDecimal getTicketMedioSaque() {
        return ticketMedioSaque;
    }

    /**
     * @param ticketMedioSaque the ticketMedioSaque to set
     */
    public void setTicketMedioSaque(BigDecimal ticketMedioSaque) {
        this.ticketMedioSaque = ticketMedioSaque;
    }

    /**
     * @return the valorMaximoOperacao
     */
    public BigDecimal getValorMaximoOperacao() {
        return valorMaximoOperacao;
    }

    /**
     * @param valorMaximoOperacao the valorMaximoOperacao to set
     */
    public void setValorMaximoOperacao(BigDecimal valorMaximoOperacao) {
        this.valorMaximoOperacao = valorMaximoOperacao;
    }

    /**
     * @return the valorMinOperacao
     */
    public BigDecimal getValorMinOperacao() {
        return valorMinOperacao;
    }

    /**
     * @param valorMinOperacao the valorMinOperacao to set
     */
    public void setValorMinOperacao(BigDecimal valorMinOperacao) {
        this.valorMinOperacao = valorMinOperacao;
    }

    /**
     * @return the valorTotalOperacao
     */
    public BigDecimal getValorTotalOperacao() {
        return valorTotalOperacao;
    }

    /**
     * @param valorTotalOperacao the valorTotalOperacao to set
     */
    public void setValorTotalOperacao(BigDecimal valorTotalOperacao) {
        this.valorTotalOperacao = valorTotalOperacao;
    }

    public PieChartModel getPieModelStatusAtendimento() {
        return pieModelStatusAtendimento;
    }

    public BigDecimal getValorCpc() {
        return valorCpc;
    }

    public List<?> getListQuantidadeStatus() {
        return listQuantidadeStatus;
    }

    public Object[] getAnaliseCampamha() {
        return analiseCampamha;
    }

    public PieChartModel getPieModelDiscagem() {
        return pieModelDiscagem;
    }

    public List<?> getListQuantidadeStatusTelefones() {
        return listQuantidadeStatusTelefones;
    }

    public BigDecimal getValorTma() {
        return valorTma;
    }
}
