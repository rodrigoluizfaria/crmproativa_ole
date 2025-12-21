package com.proativaservicos.bean;

import com.proativaservicos.model.Campanha;
import com.proativaservicos.model.StatusAtendimento;
import com.proativaservicos.model.StatusCampanha;
import com.proativaservicos.service.AtendimentoService;
import com.proativaservicos.service.CampanhaService;
import com.proativaservicos.service.StatusAtendimentoService;
import com.proativaservicos.service.StatusCampanhaService;
import com.proativaservicos.util.ColorUtil;
import com.proativaservicos.util.constantes.MessagesEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.omnifaces.util.Messages;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.polar.PolarAreaChartDataSet;
import org.primefaces.model.charts.polar.PolarAreaChartModel;
import org.primefaces.model.charts.polar.PolarAreaChartOptions;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class SupervisorMonitorCampanhaBean extends GenericBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private CampanhaService serviceCampanha;

    @Inject
    private StatusCampanhaService serviceStatusCampanha;

    @Inject
    private StatusAtendimentoService serviceStatusAtendimentos;

    @Inject
    private AtendimentoService serviceAtendimento;

    private List<StatusCampanha> listStatusCampanha;
    private List<Campanha> listCampanha;
    private List<StatusAtendimento> listStatusAtendimento;

    private Long[] listStatusSelect;

    private List<?> listQuantidades;
    private List<?> listQuantidadeStatus;
    private List<?> listQuantidadeStatusTelefones;

    private List<?> listAtendimentosFinalizadosDia;

    private Long idCampanha;
    private Long idStatusCampanha;

    private Double qtdPorcetagemAtendimentosStatus;

    private Integer qtidadeTotalTelefones;
    private Integer qtidadeTotalTelefonesStatus;
    private Integer qtidadeTotalAtendimentos;
    private Integer qtidadeFinalizados;

    private Date dataInicio;
    private Date dataFim;

    private BigDecimal spin;

    private PolarAreaChartModel polarChart;
    private PolarAreaChartModel polarChartStatusAtendimento;

    @PostConstruct
    public void init() {
        try {

            inicializarEmpresa(retornarEmpresaUsuarioSessao());

            trocarEmpresa();
            this.spin = null;
            this.listStatusCampanha = this.serviceStatusCampanha.pesquisarStatusCampanhaPorEmpresa(retornarEmpresaMatrizUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);

            this.listStatusAtendimento = this.serviceStatusAtendimentos.pesquisarStatusAtendimentosPorEmpresa(retornarEmpresaMatrizUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);

        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }
    }

    public void trocarEmpresa() {

        if (getEmpresa() == null) {

            this.listCampanha = null;

        } else {

            this.listCampanha = this.serviceCampanha.pesquisarCampanhasPorEmpresa(getEmpresa().getId());
        }

    }

    public void pesquisar() {

        try {

            this.polarChart = null;
            this.polarChartStatusAtendimento = null;
            this.spin = null;

            this.listQuantidades = this.serviceAtendimento.pesquisarQuantidadeMonitorCampanhaCarga(getEmpresa().getId(), this.idCampanha, null, null);

            this.qtidadeTotalTelefones = this.serviceAtendimento.pesquisarQuantidadeTelefonePorCampanha(getEmpresa().getId(), this.idCampanha);

            this.listAtendimentosFinalizadosDia = this.serviceAtendimento.pesquisarQuantidadeFinalizadosPorDia(this.idCampanha, this.dataInicio, this.dataFim);

            this.listQuantidadeStatus = this.serviceAtendimento.pesquisarQuantidadeStatusAtendimentoPorCampanha(getEmpresa().getId(), this.idCampanha, this.dataInicio, this.dataFim, this.listStatusSelect);

            this.listQuantidadeStatusTelefones = serviceAtendimento.pesquisarQuantidadeStatusTelefonePorCampanha(getEmpresa().getId(), this.idCampanha, this.dataInicio, this.dataFim, this.listStatusSelect);

            this.qtidadeFinalizados = Integer.valueOf(0);

            this.spin = this.serviceAtendimento.pesquisarSpinPorCampanha(this.idCampanha);


            for (Object linha : listAtendimentosFinalizadosDia) {

                this.qtidadeFinalizados = Integer.valueOf(this.qtidadeFinalizados.intValue() + ((BigInteger) ((Object[]) linha)[1]).intValue());

            }

            if (listQuantidadeStatusTelefones != null && !listQuantidadeStatusTelefones.isEmpty())
                createPolarArea(listQuantidadeStatusTelefones);

            if (listQuantidadeStatus != null && !listQuantidadeStatus.isEmpty())
                createPolarAreaStatusAtendimento(listQuantidadeStatus);

            percentual();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    private void percentual() {

        this.qtdPorcetagemAtendimentosStatus = Double.valueOf(this.listQuantidadeStatus.stream().mapToDouble(a -> Double.parseDouble(((Object[]) a)[2].toString())).sum());

        this.qtidadeTotalAtendimentos = Integer.valueOf(this.listQuantidadeStatus.stream().mapToInt(a -> Integer.parseInt(((Object[]) a)[1].toString())).sum());

        this.qtidadeTotalTelefonesStatus = Integer.valueOf(this.listQuantidadeStatusTelefones.stream().mapToInt(a -> Integer.parseInt(((Object[]) a)[1].toString())).sum());

    }

    private void createPolarArea(List<?> listValue) {

        this.polarChart = new PolarAreaChartModel();
        ChartData data = new ChartData();

        PolarAreaChartDataSet dataSet = new PolarAreaChartDataSet();

        List<Number> values = new ArrayList<Number>();
        List<String> labels = new ArrayList<String>();

        List<String> bgColor = new ArrayList<>();

        for (Object object : listValue) {

            values.add(((BigDecimal) ((Object[]) object)[1]).intValue());
            labels.add(((String) ((Object[]) object)[0]));

            bgColor.add(ColorUtil.getColorDinamic());

        }

        PolarAreaChartOptions options = new PolarAreaChartOptions();
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Status Por Telefone");
        options.setAnimateRotate(true);
        options.setTitle(title);

        polarChart.setOptions(options);

        dataSet.setData(values);
        dataSet.setBackgroundColor(bgColor);
        data.addChartDataSet(dataSet);
        data.setLabels(labels);

        polarChart.setData(data);

    }

    private void createPolarAreaStatusAtendimento(List<?> listValue) {

        this.polarChartStatusAtendimento = new PolarAreaChartModel();
        ChartData data = new ChartData();

        PolarAreaChartDataSet dataSet = new PolarAreaChartDataSet();

        List<Number> values = new ArrayList<Number>();
        List<String> labels = new ArrayList<String>();

        List<String> bgColor = new ArrayList<>();

        for (Object object : listValue) {

            values.add(((BigDecimal) ((Object[]) object)[1]).intValue());
            labels.add(((String) ((Object[]) object)[0]));

            bgColor.add(ColorUtil.getColorDinamic());

        }

        PolarAreaChartOptions options = new PolarAreaChartOptions();
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Status Por Atendimento");
        options.setAnimateRotate(true);
        options.setTitle(title);

        this.polarChartStatusAtendimento.setOptions(options);

        dataSet.setData(values);
        dataSet.setBackgroundColor(bgColor);
        data.addChartDataSet(dataSet);
        data.setLabels(labels);

        this.polarChartStatusAtendimento.setData(data);

    }

    public void onChangeStatusCampanha() {

        try {

            if (this.idStatusCampanha != null && getEmpresa() != null && getEmpresa().getId() != null)

                this.listCampanha = this.serviceCampanha.pesquisarCampanhasPorEmpresa(getEmpresa().getId(), this.idStatusCampanha);

        } catch (Exception e) {

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
            e.printStackTrace();

        }

    }

    public PolarAreaChartModel getPolarChart() {
        return polarChart;
    }

    public void setPolarChart(PolarAreaChartModel polarChart) {
        this.polarChart = polarChart;
    }

    public List<StatusCampanha> getListStatusCampanha() {
        return listStatusCampanha;
    }

    public void setListStatusCampanha(List<StatusCampanha> listStatusCampanha) {
        this.listStatusCampanha = listStatusCampanha;
    }

    public Integer retornarValor(Object divisor, Object valor) {

        if (divisor == null || valor == null)
            return null;

        Integer valorUm;
        Integer valor2;


        if (divisor instanceof BigDecimal)
            valorUm = ((BigDecimal) divisor).intValue();
        else
            valorUm = (Integer) divisor;

        if (valor instanceof BigDecimal)
            valor2 = ((BigDecimal) valor).intValue();
        else
            valor2 = (Integer) valor;

        if (valor2 > 0)
            return valorUm * 100 / valor2;

        return null;

    }

    public List<Campanha> getListCampanha() {
        return listCampanha;
    }

    public void setListCampanha(List<Campanha> listCampanha) {
        this.listCampanha = listCampanha;
    }

    public Long getIdCampanha() {
        return idCampanha;
    }

    public void setIdCampanha(Long idCampanha) {
        this.idCampanha = idCampanha;
    }

    public Long getIdStatusCampanha() {
        return idStatusCampanha;
    }

    public void setIdStatusCampanha(Long idStatusCampanha) {
        this.idStatusCampanha = idStatusCampanha;
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

    public List<StatusAtendimento> getListStatusAtendimento() {
        return listStatusAtendimento;
    }

    public void setListStatusAtendimento(List<StatusAtendimento> listStatusAtendimento) {
        this.listStatusAtendimento = listStatusAtendimento;
    }

    public Long[] getListStatusSelect() {
        return listStatusSelect;
    }

    public void setListStatusSelect(Long[] listStatusSelect) {
        this.listStatusSelect = listStatusSelect;
    }

    public List<?> getListQuantidades() {
        return listQuantidades;
    }

    public void setListQuantidades(List<?> listQuantidades) {
        this.listQuantidades = listQuantidades;
    }

    public Integer getQtidadeTotalTelefones() {
        return qtidadeTotalTelefones;
    }

    public void setQtidadeTotalTelefones(Integer qtidadeTotalTelefones) {
        this.qtidadeTotalTelefones = qtidadeTotalTelefones;
    }

    public List<?> getListQuantidadeStatus() {
        return listQuantidadeStatus;
    }

    public void setListQuantidadeStatus(List<?> listQuantidadeStatus) {
        this.listQuantidadeStatus = listQuantidadeStatus;
    }

    public List<?> getListQuantidadeStatusTelefones() {
        return listQuantidadeStatusTelefones;
    }

    public void setListQuantidadeStatusTelefones(List<?> listQuantidadeStatusTelefones) {
        this.listQuantidadeStatusTelefones = listQuantidadeStatusTelefones;
    }

    public Double getQtdPorcetagemAtendimentosStatus() {
        return qtdPorcetagemAtendimentosStatus;
    }

    public void setQtdPorcetagemAtendimentosStatus(Double qtdPorcetagemAtendimentosStatus) {
        this.qtdPorcetagemAtendimentosStatus = qtdPorcetagemAtendimentosStatus;
    }

    public List<?> getListAtendimentosFinalizadosDia() {
        return listAtendimentosFinalizadosDia;
    }

    public void setListAtendimentosFinalizadosDia(List<?> listAtendimentosFinalizadosDia) {
        this.listAtendimentosFinalizadosDia = listAtendimentosFinalizadosDia;
    }

    public Integer getQtidadeFinalizados() {
        return qtidadeFinalizados;
    }

    public void setQtidadeFinalizados(Integer qtidadeFinalizados) {
        this.qtidadeFinalizados = qtidadeFinalizados;
    }

    public Integer getQtidadeTotalAtendimentos() {
        return qtidadeTotalAtendimentos;
    }

    public void setQtidadeTotalAtendimentos(Integer qtidadeTotalAtendimentos) {
        this.qtidadeTotalAtendimentos = qtidadeTotalAtendimentos;
    }

    public void setQtidadeTotalTelefonesStatus(Integer qtidadeTotalTelefonesStatus) {
        this.qtidadeTotalTelefonesStatus = qtidadeTotalTelefonesStatus;
    }

    public Integer getQtidadeTotalTelefonesStatus() {
        return qtidadeTotalTelefonesStatus;
    }

    public PolarAreaChartModel getPolarChartStatusAtendimento() {
        return polarChartStatusAtendimento;
    }

    public void setPolarChartStatusAtendimento(PolarAreaChartModel polarChartStatusAtendimento) {
        this.polarChartStatusAtendimento = polarChartStatusAtendimento;
    }

    public BigDecimal getSpin() {
        return spin;
    }

}
