package com.proativaservicos.bean;

import com.proativaservicos.model.Empresa;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.service.EmpresaService;
import com.proativaservicos.service.EquipeService;
import com.proativaservicos.service.ProdutoService;
import com.proativaservicos.service.UsuarioService;
import com.proativaservicos.util.ColorUtil;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.NumeroUtil;
import com.proativaservicos.util.constantes.*;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.omnifaces.util.Messages;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.hbar.HorizontalBarChartDataSet;
import org.primefaces.model.charts.hbar.HorizontalBarChartModel;
import org.primefaces.model.charts.optionconfig.title.Title;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

@Named
@ViewScoped
public class ProjecaoDashBean extends GenericBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private GraficosEnum graficoEnum;

    @Inject
    private EmpresaService serviceEmpresa;

    @Inject
    private UsuarioService serviceUsuario;

    @Inject
    private ProdutoService serviceProduto;

    @Inject
    private EquipeService equipeService;

    private Usuario usuario;

    private Empresa empresa;

    private PeriodoEnum periodoEnum;

    private TipoDadosEnum tipoDadosEnum;

    private SituacaoEnum situacaoEnum;

    private List<Map<String, Object>> listMapDados;

    private HorizontalBarChartModel barModel;

    private List<Date> listData;

    @PostConstruct
    public void init() {

        this.periodoEnum = PeriodoEnum.DIARIO;
        this.situacaoEnum = SituacaoEnum.REALIZADO;
        this.tipoDadosEnum = TipoDadosEnum.VALOR;
        this.usuario = retornarUsuarioSessao();
        this.empresa = this.serviceEmpresa.pesquisarEmpresa(this.usuario.getEmpresa().getId());

        if (PerfilUsuarioEnum.OPERADOR.equals(this.usuario.getPerfil())) {

            this.graficoEnum = GraficosEnum.AGENTE;


        } else if (PerfilUsuarioEnum.SUPERVISOR.equals(this.usuario.getPerfil())) {

            this.graficoEnum = GraficosEnum.EQUIPE;

        } else {

            this.graficoEnum = GraficosEnum.EMPRESA;

        }

        pesquisar();

    }

    public void pesquisar() {

        try {

            Date dataInicio = null;
            Date dataFim = null;

            if (this.listData != null && !listData.isEmpty()) {


                if (this.listData.size() > 1) {

                    dataInicio = this.listData.get(0);
                    dataFim = this.listData.get(this.listData.size() - 1);

                } else {

                    dataInicio = this.listData.get(0);
                    dataFim = this.listData.get(0);
                }

            }

            if (GraficosEnum.AGENTE.equals(this.graficoEnum)) {

                this.listMapDados = calcularProjecao(this.serviceUsuario.pesquisarProjecaoPorOperador(this.usuario, this.situacaoEnum, this.periodoEnum, this.tipoDadosEnum, dataInicio, dataFim));
                criarGrafico("Agentes");


            } else if (GraficosEnum.EMPRESA.equals(this.graficoEnum)) {


                this.listMapDados = calcularProjecao(this.serviceEmpresa.pesquisarProjecaoPorEmpresa(this.usuario, this.situacaoEnum, this.periodoEnum, this.tipoDadosEnum, dataInicio, dataFim));
                criarGrafico("Empresa");

            } else if (GraficosEnum.PRODUTO.equals(this.graficoEnum)) {


                this.listMapDados = calcularProjecao(this.serviceProduto.pesquisarProjecaoPorProduto(this.usuario, this.situacaoEnum, this.periodoEnum, dataInicio, dataFim, this.tipoDadosEnum));
                criarGrafico("Produto");

            } else if (GraficosEnum.EQUIPE.equals(this.graficoEnum)) {


                this.listMapDados = calcularProjecao(this.equipeService.pesquisarProjecaoPorEquipe(this.usuario, this.situacaoEnum, this.periodoEnum, dataInicio, dataFim, this.tipoDadosEnum));
                criarGrafico("Equipes");

            }
            if (!this.periodoEnum.equals(PeriodoEnum.DATA))
                this.listData = null;

        } catch (Exception e) {

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
            e.printStackTrace();

        }

    }

    public void test() {


        System.out.println(this.graficoEnum + " - " + this.periodoEnum + " - " + this.situacaoEnum + " - " + this.tipoDadosEnum);

    }

    private List<Map<String, Object>> calcularProjecao(List<Object[]> listMapDados) {

        List<Map<String, Object>> mapListProjecoes = new ArrayList<>();

        for (Object[] map : listMapDados) {

            Double valorProducao, meta = NumeroUtil.builder((map[4] == null) ? 0.0D : ((map[4] instanceof Double) ? (Double) map[4] : ((map[4] instanceof BigDecimal) ? ((BigDecimal) map[4]).doubleValue() : ((Integer) map[4]).doubleValue()))).arredondarNumero(2).getNumero();

            meta = (meta == null) ? 0.0D : meta;

            double tempoTrabalho = 0.0D;

            if (map[1] instanceof BigDecimal) {

                BigDecimal aux = (BigDecimal) map[1];
                tempoTrabalho = NumeroUtil.builder(aux).arredondarNumero(2).getBigdecimal().doubleValue();

            } else {

                tempoTrabalho = NumeroUtil.builder(map[1] == null? 0.0D: (Double) map[1]).arredondarNumero(2).getNumero();

            }

            int operadores = 1;

            if (map[2] instanceof BigInteger) {

                operadores = (map[2] == null || ((BigInteger) map[2]).intValue() == 1) ? 1 : ((BigInteger) map[2]).intValue();

            } else {
                operadores = (map[2] == null || ((Integer) map[2]).intValue() == 1) ? 1 : ((Integer) map[2]).intValue();
            }


            if (map[3] instanceof BigInteger) {

                valorProducao = NumeroUtil.builder(Double.valueOf((map[3] == null) ? 0.0D : ((BigInteger) map[3]).doubleValue())).arredondarNumero(Integer.valueOf(2)).getNumero();

            } else {

                valorProducao = NumeroUtil.builder(Double.valueOf((map[3] == null) ? 0.0D : ((BigDecimal) map[3]).doubleValue())).arredondarNumero(Integer.valueOf(2)).getNumero();


            }

            double tma = (this.empresa.getMeta() == null || this.empresa.getMeta().getTma() == null || this.empresa.getMeta().getTma().doubleValue() == 0.0D) ? 10.0D : this.empresa.getMeta().getTma().doubleValue();
            Double valorProjecao = Double.valueOf(0.0D);
            int quantidade = (map[5] == null) ? 0 : ((BigInteger) map[5]).intValue();

            if (map[4] != null)

                if (map[4] instanceof Integer) {

                    meta = Double.valueOf((((Integer) map[4]).doubleValue() > 0.0D) ? ((Integer) map[4]).doubleValue() : meta.doubleValue());

                } else if (map[4] instanceof Double) {

                    meta = Double.valueOf((((Double) map[4]).doubleValue() > 0.0D) ? ((Double) map[4]).doubleValue() : meta.doubleValue());

                } else {
                    meta = Double.valueOf((((BigDecimal) map[4]).doubleValue() > 0.0D) ? ((BigDecimal) map[4]).doubleValue() : meta.doubleValue());


                }

            int fator = 0;

            if (PeriodoEnum.DIARIO.equals(this.periodoEnum))
                fator = 1;
            else if (PeriodoEnum.SEMANAL.equals(this.periodoEnum))
                fator = 6;
            else
                fator = DateUtil.builder().retornarQuantidadeDiasUteis();

            valorProjecao = Double.valueOf(((operadores * 360 * fator) - tempoTrabalho) / tma * meta.doubleValue() / (operadores * 360 * fator) / tma + valorProducao.doubleValue());
            double percentualProjecao = (meta.doubleValue() == 0.0D) ? 0.0D : NumeroUtil.builder(Double.valueOf(valorProjecao.doubleValue() / meta.doubleValue() * 100.0D)).arredondarNumero(Integer.valueOf(2)).getNumero().doubleValue();
            double percentualVendido = (meta.doubleValue() == 0.0D) ? 0.0D : NumeroUtil.builder(Double.valueOf(valorProducao.doubleValue() / meta.doubleValue() * 100.0D)).arredondarNumero(Integer.valueOf(2)).getNumero().doubleValue();
            Map<String, Object> mapInformacoes = new TreeMap<String, Object>();
            mapInformacoes.put("nome", map[0]);

            if (this.tipoDadosEnum.equals(TipoDadosEnum.VALOR)) {

                mapInformacoes.put("projecao", (valorProjecao.doubleValue() <= 0.0D) ? "R$ 0,00" : NumeroUtil.builder(valorProjecao).formatarNumeroParaMoeda());
                mapInformacoes.put("producao", (valorProducao.doubleValue() == 0.0D) ? "R$ 0,00" : NumeroUtil.builder(valorProducao).formatarNumeroParaMoeda());
                mapInformacoes.put("meta", (meta == null) ? "R$ 0,00" : NumeroUtil.builder(meta).formatarNumeroParaMoeda());

                mapInformacoes.put("producaoRelatorio", valorProducao.doubleValue());

            } else {

                mapInformacoes.put("projecao", Integer.valueOf((valorProjecao.doubleValue() <= 0.0D) ? 0 : valorProjecao.intValue()));
                mapInformacoes.put("producao", Integer.valueOf((valorProducao.doubleValue() == 0.0D) ? 0 : valorProducao.intValue()));
                mapInformacoes.put("meta", Integer.valueOf((meta == null) ? 0 : meta.intValue()));
                mapInformacoes.put("producaoRelatorio", Integer.valueOf((valorProducao.doubleValue() == 0.0D) ? 0 : valorProducao.intValue()));

            }

            mapInformacoes.put("quantidade", Integer.valueOf(quantidade));
            mapInformacoes.put("percentualProjecao", Double.valueOf((percentualProjecao <= 0.0D) ? 0.0D : percentualProjecao));
            mapInformacoes.put("percentualProducao", Double.valueOf((percentualVendido <= 0.0D) ? 0.0D : percentualVendido));
            mapListProjecoes.add(mapInformacoes);

        }

        return mapListProjecoes;

    }

    private void criarGrafico(String title) {

        try {

            this.barModel = new HorizontalBarChartModel();

            ChartData data = new ChartData();
            HorizontalBarChartDataSet dataSetProducao = new HorizontalBarChartDataSet();
            dataSetProducao.setLabel("Produção");
            List<String> labels = new ArrayList<>();
            List<Number> listValuesPoducao = new ArrayList<>();
            List<String> bgColor = new ArrayList<>();

            int i = 0;

            for (Map<String, Object> mapDados : this.listMapDados) {

                String labelNome = mapDados.get("nome").toString();

                labels.add(labelNome);

                //double producao =   ( mapDados.get("producaoRelatorio") instanceof Double ? ((Double)mapDados.get("producaoRelatorio")).doubleValue() : ((Integer)mapDados.get("producaoRelatorio")).intValue() );

                //double projecao = ((Double)mapDados.get("percentualProjecao")).doubleValue();

                listValuesPoducao.add((mapDados.get("producaoRelatorio") instanceof Double ? ((Double) mapDados.get("producaoRelatorio")).doubleValue() : ((Integer) mapDados.get("producaoRelatorio")).intValue()));

                bgColor.add(ColorUtil.getColorDinamic());

                if (title.contains("Agentes") && i == 9 && !this.usuario.getPerfil().equals(PerfilUsuarioEnum.OPERADOR)) {
                    title = "TOP 10 - Agentes";
                    break;
                }

                i++;

            }

            dataSetProducao.setData(listValuesPoducao);
            dataSetProducao.setBackgroundColor(bgColor);
            dataSetProducao.setBorderWidth(1);

            data.addChartDataSet(dataSetProducao);
            data.setLabels(labels);

            this.barModel.setData(data);

            //Options
            BarChartOptions options = new BarChartOptions();
            CartesianScales cScales = new CartesianScales();
            CartesianLinearAxes linearAxes = new CartesianLinearAxes();
            linearAxes.setOffset(true);
            CartesianLinearTicks ticks = new CartesianLinearTicks();
            //   ticks.setBeginAtZero(true);
            linearAxes.setTicks(ticks);
            cScales.addXAxesData(linearAxes);
            options.setScales(cScales);

            Title titles = new Title();
            titles.setDisplay(true);

            if (this.usuario.getPerfil().equals(PerfilUsuarioEnum.OPERADOR))
                title = "";

            titles.setText(title);

            options.setTitle(titles);

            this.barModel.setOptions(options);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public GraficosEnum getGraficoEnum() {
        return graficoEnum;
    }

    public void setGraficoEnum(GraficosEnum graficoEnum) {
        this.graficoEnum = graficoEnum;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public PeriodoEnum getPeriodoEnum() {
        return periodoEnum;
    }

    public void setPeriodoEnum(PeriodoEnum periodoEnum) {
        this.periodoEnum = periodoEnum;
    }

    public TipoDadosEnum getTipoDadosEnum() {
        return tipoDadosEnum;
    }

    public void setTipoDadosEnum(TipoDadosEnum tipoDadosEnum) {
        this.tipoDadosEnum = tipoDadosEnum;


    }

    public SituacaoEnum getSituacaoEnum() {
        return situacaoEnum;
    }

    public void setSituacaoEnum(SituacaoEnum situacaoEnum) {
        this.situacaoEnum = situacaoEnum;
    }

    public List<Map<String, Object>> getListMapDados() {
        return listMapDados;
    }

    public void setListMapDados(List<Map<String, Object>> listMapDados) {
        this.listMapDados = listMapDados;
    }

    public HorizontalBarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(HorizontalBarChartModel barModel) {
        this.barModel = barModel;
    }

    public List<Date> getListData() {
        return listData;
    }

    public void setListData(List<Date> listData) {
        this.listData = listData;
    }

}
