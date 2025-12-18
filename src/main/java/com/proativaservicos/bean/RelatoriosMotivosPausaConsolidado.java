package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.Equipe;
import com.proativaservicos.model.Pausa;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.service.EquipeService;
import com.proativaservicos.service.PausaService;
import com.proativaservicos.service.UsuarioService;
import com.proativaservicos.util.ColorUtil;
import com.proativaservicos.util.Utils;
import com.proativaservicos.util.constantes.MessagesEnum;
import com.proativaservicos.util.constantes.PerfilUsuarioEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
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
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.optionconfig.tooltip.Tooltip;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;
import org.primefaces.model.charts.pie.PieChartOptions;

import java.io.Serializable;
import java.util.*;

@Named
@ViewScoped
public class RelatoriosMotivosPausaConsolidado extends GenericBean {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Inject
    private EquipeService serviceEquipe;

    @Inject
    private UsuarioService serviceUsuario;

    @Inject
    private PausaService servicePausa;

    private Usuario usuarioLogado;

    private List<Usuario> listOperadores;

    private List<Equipe> listEquipes;

    private List<Pausa> listPausas;

    private List<Object[]> listRelatorioPausa;

    private List<Object[]> listTablePausaColunas;

    private Long idOperador;

    private List<Long> listIdsOperadores;

    private Long idPausa;

    private Long idEquipe;

    private Date dataInicio;

    private Date DataFinal;

    private List<ColumnModel> titleColuns;

    private Map<String, Long> mapPausaTotal;

    private BarChartModel stackedBarModel;

    private PieChartModel pieModel;

    @PostConstruct
    public void init() {
        try {
            this.pieModel = new PieChartModel();
            this.usuarioLogado = retornarUsuarioSessao();
            inicializarEmpresa();
            trocarEmpresa();

            this.listPausas = this.servicePausa.pesquisarPausaPorEmpresa(retornarEmpresaMatrizUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);

            this.dataInicio = new Date();
            this.DataFinal = new Date();

        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }

    public void pesquisar() {

        try {


            if (this.dataInicio != null && this.DataFinal != null && this.dataInicio.after(this.DataFinal)) {
                throw new ProativaException(MessagesEnum.A_DATA_DE_INICIO_DEVE_SER_MENOR_OU_IGUAL_A_DATA_DE_TERMINO.constante);
            }

            this.listTablePausaColunas = null;
            this.listRelatorioPausa = this.servicePausa.pesquisarRelatoriosPausaPorOperador(this.listIdsOperadores, this.idEquipe, this.idPausa, this.dataInicio, this.DataFinal, getEmpresa().getId());
            gerarRelatorio();

        } catch (ProativaException e) {

            e.printStackTrace();
            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }

    private void gerarRelatorio() {

        Map<String, Map<String, Long>> mapOperador = new HashMap<String, Map<String, Long>>();

        if (CollectionUtils.isNotEmpty(this.listRelatorioPausa)) {

            for (Object[] objPausa : this.listRelatorioPausa) {

                String operador = (String) objPausa[0];

                String pausa = (String) objPausa[1];

                String tempo = (String) objPausa[5];

                Long segundos = Utils.converterHorasMinutosSegundosToSegundos(tempo);

                if (mapOperador.containsKey(operador)) {

                    if (mapOperador.get(operador).containsKey(pausa)) {

                        if (segundos != null)
                            mapOperador.get(operador).put(pausa, (mapOperador.get(operador).get(pausa) + segundos));

                    } else {

                        mapOperador.get(operador).put(pausa, segundos);

                    }

                } else {

                    mapOperador.put(operador, new HashMap<String, Long>());
                    mapOperador.get(operador).put(pausa, segundos);
                }

            }

        }

        List<Object[]> listTableColunas = new ArrayList<Object[]>();

        int totalColunas = this.listPausas.size() + 2;


        this.mapPausaTotal = new HashMap<String, Long>();

        for (String keyOperador : mapOperador.keySet()) {

            String colunas[] = new String[totalColunas];
            colunas[0] = keyOperador;
            Long totalSegundosPausa = 0L;

            for (int j = 0; j < this.listPausas.size(); j++) {

                if (mapOperador.get(keyOperador).containsKey(this.listPausas.get(j).getDescricao())) {

                    colunas[j + 1] = String.valueOf(mapOperador.get(keyOperador).get(this.listPausas.get(j).getDescricao()));

                    totalSegundosPausa = totalSegundosPausa + mapOperador.get(keyOperador).get(this.listPausas.get(j).getDescricao());

                    this.mapPausaTotal.put(this.listPausas.get(j).getDescricao(), (mapPausaTotal.containsKey(this.listPausas.get(j).getDescricao()) ? (mapPausaTotal.get(this.listPausas.get(j).getDescricao()) + mapOperador.get(keyOperador).get(this.listPausas.get(j).getDescricao())) : mapOperador.get(keyOperador).get(this.listPausas.get(j).getDescricao())));

                } else {

                    colunas[j + 1] = "0";
                    this.mapPausaTotal.put(this.listPausas.get(j).getDescricao(), (mapPausaTotal.containsKey(this.listPausas.get(j).getDescricao()) ? (mapPausaTotal.get(this.listPausas.get(j).getDescricao()) + 0L) : 0L));

                }


            }


            colunas[totalColunas - 1] = totalSegundosPausa.toString();

            listTableColunas.add(colunas);
        }


        for (Object[] objects : listTableColunas) {

            for (int i = 0; i < objects.length; i++) {

                String dados = (String) objects[i];

                if (StringUtils.isNotBlank(StringUtils.getDigits(dados))) {
                    objects[i] = Utils.converterSegundosToHorasMinutosSegundos(Long.parseLong(StringUtils.getDigits(dados)));
                }


            }

        }
        this.listTablePausaColunas = listTableColunas;

        //CRIANDO CABECALHO
        this.titleColuns = new ArrayList<RelatoriosMotivosPausaConsolidado.ColumnModel>();
        this.titleColuns.add(new ColumnModel("AGENTE", 0, 0, "15%", true));

        for (int j = 0; j < this.listPausas.size(); j++) {

            this.titleColuns.add(new ColumnModel(this.listPausas.get(j).getDescricao(), 0, j + 1, "", false));
        }

        this.titleColuns.add(new ColumnModel("TOTAL", 0, totalColunas - 1, "", false));
        gerarGrafico(mapOperador);
    }


    public void trocarEmpresa() {

        if (getEmpresa() == null) {

            this.listEquipes = null;
            this.listOperadores = null;

        } else if (this.usuarioLogado.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

            this.listOperadores = this.serviceUsuario.pesquisarUsuariosPorSupervisor(this.usuarioLogado.getId(), getEmpresa().getId());
            this.listEquipes = this.serviceEquipe.pesquisarEquipesPorSupervisor(this.usuarioLogado.getId(), getEmpresa().getId());

        } else {

            this.listEquipes = this.serviceEquipe.pesquisarEquipesPorEmpresa(getEmpresa().getId(), TipoAcessoEnum.ATIVO);
            this.listOperadores = this.serviceUsuario.pesquisarUsuariosPorEmpresa(getEmpresa().getId());
        }

    }


    private void gerarGrafico(Map<String, Map<String, Long>> mapOperador) {

        if (mapOperador != null) {

            List<String> labels = new ArrayList<>();

            this.stackedBarModel = new BarChartModel();

            org.primefaces.model.charts.ChartData data = new org.primefaces.model.charts.ChartData();

            List<BarChartDataSet> listBarChart = new ArrayList<BarChartDataSet>();

            for (Pausa pausa : this.listPausas) {

                BarChartDataSet barDataSet = new BarChartDataSet();
                barDataSet.setLabel(pausa.getDescricao());
                barDataSet.setBackgroundColor(ColorUtil.getColorDinamic());
                listBarChart.add(barDataSet);
                barDataSet.setData(new ArrayList<Number>());

            }

            for (int i = 0; i < listBarChart.size(); i++) {

                for (Object[] ob : this.listTablePausaColunas) {

                    listBarChart.get(i).getData().add(Utils.converterHorasMinutosSegundosToSegundos((String) ob[i + 1]));
                }

                data.addChartDataSet(listBarChart.get(i));
            }


            for (Object[] ob : this.listTablePausaColunas) {
                labels.add((String) ob[0]);
            }

            data.setLabels(labels);
            this.stackedBarModel.setData(data);

            BarChartOptions options = new BarChartOptions();
            CartesianScales cScales = new CartesianScales();
            CartesianLinearAxes linearAxes = new CartesianLinearAxes();
            linearAxes.setStacked(true);
            cScales.addXAxesData(linearAxes);
            cScales.addYAxesData(linearAxes);

            options.setScales(cScales);

            Title title = new Title();
            title.setDisplay(true);
            title.setText("Gráfico agente X Motivo Pausa (segundos)");

            options.setTitle(title);

            Tooltip tooltip = new Tooltip();
            tooltip.setMode("index");
            tooltip.setIntersect(false);

            options.setTooltip(tooltip);
            this.stackedBarModel.setOptions(options);

        }

    }

    public void onModalCharts(int indice) {

        this.pieModel = new PieChartModel();
        ChartData data = new ChartData();
        PieChartDataSet dataSet = new PieChartDataSet();

        List<Number> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        List<String> bgColors = new ArrayList<>();

        Object[] listObj = this.listTablePausaColunas.get(indice);

        for (int i = 1; i < listObj.length - 1; i++) {

            Long value = Utils.converterHorasMinutosSegundosToSegundos((String) listObj[i]);

            if (value > 0) {
                values.add(value);
                labels.add(this.listPausas.get(i - 1).getDescricao() + ": " + listObj[i]);
                bgColors.add(ColorUtil.getColorDinamic());
            }
        }

        dataSet.setBackgroundColor(bgColors);
        dataSet.setData(values);
        data.setLabels(labels);
        data.addChartDataSet(dataSet);
        PieChartOptions options = new PieChartOptions();
        Legend legend = new Legend();
        legend.setPosition("right");

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Agente: " + listObj[0] + "  -  Total: " + listObj[listObj.length - 1]);
        options.setTitle(title);
        options.setLegend(legend);
        this.pieModel.setOptions(options);
        this.pieModel.setData(data);

    }

    public String retornoFooter(String header) {


        if (header.equals("AGENTE"))
            return "TOTAL ";

        if (this.mapPausaTotal.containsKey(header)) {

            return Utils.converterSegundosToHorasMinutosSegundos(this.mapPausaTotal.get(header));

        } else if (header.equals("TOTAL")) {

            Long total = 0L;

            for (Object[] obj : listTablePausaColunas) {

                String dados = (String) obj[obj.length - 1];

                if (Utils.converterHorasMinutosSegundosToSegundos(dados) != null) {
                    total += Long.valueOf(Utils.converterHorasMinutosSegundosToSegundos(dados));
                }
            }

            return Utils.converterSegundosToHorasMinutosSegundos(total);

        } else {

            return "";

        }


    }


    static public class ColumnModel implements Serializable {


        private static final long serialVersionUID = -502873467260071561L;
        private String header;
        private int property;
        private int indice;
        private String percent;
        private boolean name;

        public ColumnModel(String header, int property, int indice, String percent, boolean name) {
            this.header = header;
            this.property = property;
            this.indice = indice;
            this.percent = percent;
            this.name = name;
        }

        public String getHeader() {
            return header;
        }

        public int getProperty() {
            return property;
        }

        public int getIndice() {
            return indice;
        }

        public void setIndice(int indice) {
            this.indice = indice;
        }

        public String getPercent() {
            return percent;
        }

        public void setPercent(String percent) {
            this.percent = percent;
        }

        public boolean isName() {
            return name;
        }

        public void setName(boolean name) {
            this.name = name;
        }
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public List<Usuario> getListOperadores() {
        return listOperadores;
    }

    public void setListOperadores(List<Usuario> listOperadores) {
        this.listOperadores = listOperadores;
    }

    public List<Equipe> getListEquipes() {
        return listEquipes;
    }

    public void setListEquipes(List<Equipe> listEquipes) {
        this.listEquipes = listEquipes;
    }

    public Long getIdOperador() {
        return this.idOperador;
    }

    public void setIdOperador(Long idOperador) {
        this.idOperador = idOperador;
    }

    public Long getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(Long idEquipe) {
        this.idEquipe = idEquipe;
    }

    public void setListPausas(List<Pausa> listPausas) {
        this.listPausas = listPausas;
    }

    public List<Pausa> getListPausas() {
        return listPausas;
    }

    public Long getIdPausa() {
        return idPausa;
    }

    public void setIdPausa(Long idPausa) {
        this.idPausa = idPausa;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFinal() {
        return DataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        DataFinal = dataFinal;
    }

    public List<Object[]> getListRelatorioPausa() {
        return listRelatorioPausa;
    }

    public void setListRelatorioPausa(List<Object[]> listRelatorioPausa) {
        this.listRelatorioPausa = listRelatorioPausa;
    }

    public List<Object[]> getListTablePausaColunas() {
        return listTablePausaColunas;
    }

    public List<ColumnModel> getTitleColuns() {
        return titleColuns;
    }

    public Map<String, Long> getMapPausaTotal() {
        return mapPausaTotal;
    }

    public BarChartModel getStackedBarModel() {
        return stackedBarModel;
    }

    public List<Long> getListIdsOperadores() {
        return listIdsOperadores;
    }

    public void setListIdsOperadores(List<Long> listIdsOperadores) {
        this.listIdsOperadores = listIdsOperadores;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }
}
