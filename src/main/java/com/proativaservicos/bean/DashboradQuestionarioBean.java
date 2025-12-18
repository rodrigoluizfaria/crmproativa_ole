package com.proativaservicos.bean;

import com.proativaservicos.model.Campanha;
import com.proativaservicos.model.StatusCampanha;
import com.proativaservicos.model.pesquisa.Pergunta;
import com.proativaservicos.model.pesquisa.Questionario;
import com.proativaservicos.service.CampanhaService;
import com.proativaservicos.service.QuestionarioService;
import com.proativaservicos.service.RespostaService;
import com.proativaservicos.service.StatusCampanhaService;
import com.proativaservicos.util.ColorUtil;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.collections4.CollectionUtils;
import org.omnifaces.util.Messages;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;
import org.primefaces.model.charts.pie.PieChartOptions;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.*;


@ViewScoped
@Named
public class DashboradQuestionarioBean extends GenericBean {


    private static final long serialVersionUID = 1L;


    @Inject
    private CampanhaService serviceCampanha;

    @Inject
    private QuestionarioService serviceQuestionario;

    @Inject
    private RespostaService serviceResposta;
    @Inject
    private StatusCampanhaService serviceStatusCampanha;
    private Long idCampanha;
    private Long idStatusCampanha;
    private Long idQuestionario;
    private Questionario questionario;
    private Pergunta pergunta;
    private List<StatusCampanha> listStatusCampanha;
    private List<Campanha> listCampanhas;
    private List<Questionario> listQuestionarios;
    private List<Object[]> listResultList;

    private List<PieChartModel> listPieModel;

    private Date dataInicio;

    private Date dataFim;

    private List<Object[]> listResultListTexto;


    @PostConstruct
    public void init() {

        inicializarEmpresa();


        this.listQuestionarios = this.serviceQuestionario.pesquisarPorEmpresa(retornarEmpresaMatrizUsuarioSessao().getId());
        this.listStatusCampanha = this.serviceStatusCampanha.pesquisarStatusCampanhaPorEmpresa(retornarEmpresaMatrizUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);

        this.listCampanhas = new ArrayList<Campanha>();

        this.dataInicio = DateUtil.builder(new Date()).retornarDataPrimeiroDiaMes().getData();

        this.dataFim = new Date();


    }


    public void pesquisar() {

        try {

            Long empresa = (getEmpresa() == null) ? null : getEmpresa().getId();

            this.listResultList = null;
            this.listResultListTexto = null;
            this.listPieModel = null;

            this.listResultList = this.serviceResposta.pesquisarQuantidadeRegistrosRespostas(empresa, this.idStatusCampanha, this.idCampanha, this.idQuestionario, this.dataInicio, this.dataFim);

            this.listResultListTexto = this.serviceResposta.pesquisarQuantidadeRegistrosRespostasTexto(empresa, this.idStatusCampanha, this.idCampanha, this.idQuestionario, this.dataInicio, this.dataFim);

            if (CollectionUtils.isNotEmpty(this.listResultList) || CollectionUtils.isNotEmpty(this.listResultListTexto))
                gerarRelatorio(listResultList);


        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(e.getMessage(), new Object());

        }

    }


    private void gerarRelatorio(List<Object[]> listObj) {


        Map<String, List<Object[]>> mapQuestionario = new HashMap<String, List<Object[]>>();

        this.listPieModel = new ArrayList<PieChartModel>();

        for (Object[] objects : listObj) {

            Object[] arrayObj = new Object[4];

            String questionario = (String) objects[0];

            String pergunta = (String) objects[1];
            String tipoPergunta = (String) objects[2];
            String resposta = tipoPergunta.equals("TEXTO") ? "Aberta" : (String) objects[3];
            BigInteger total = (BigInteger) objects[4];


            arrayObj[0] = pergunta;
            arrayObj[1] = tipoPergunta;
            arrayObj[2] = resposta;
            arrayObj[3] = String.valueOf(total);


            List<Object[]> listObjct = new ArrayList<Object[]>();

            listObjct.add(arrayObj);

            if (mapQuestionario.containsKey(questionario)) {

                mapQuestionario.get(questionario).add(arrayObj);

            } else {

                mapQuestionario.put(questionario, listObjct);
            }

        }

        Map<String, List<Object[]>> mapRespostas = new HashMap<String, List<Object[]>>();

        for (String string : mapQuestionario.keySet()) {


            for (Object[] objects : mapQuestionario.get(string)) {

                Object[] arrayObj = new Object[3];

                String pergunta = String.valueOf(objects[0]);
                String resposta = String.valueOf(objects[1]);
                String tipo = String.valueOf(objects[2]);
                String total = String.valueOf(objects[3]);

                arrayObj[0] = resposta;
                arrayObj[1] = total;
                arrayObj[2] = tipo;

                if (mapRespostas.containsKey(pergunta)) {

                    mapRespostas.get(pergunta).add(arrayObj);

                } else {

                    mapRespostas.put(pergunta, new ArrayList<Object[]>());
                    mapRespostas.get(pergunta).add(arrayObj);

                }


            }


        }

        DecimalFormat format = new DecimalFormat("#.##");


        for (String key : mapRespostas.keySet()) {

            PieChartModel pieModel = new PieChartModel();

            ChartData data = new ChartData();

            PieChartDataSet dataSet = new PieChartDataSet();

            List<String> bgColors = new ArrayList<>();

            List<String> labels = new ArrayList<>();

            List<Number> values = new ArrayList<>();

            Double total = 0.0D;

            for (Object[] objects : mapRespostas.get(key)) {

                values.add(Double.valueOf(String.valueOf(objects[1])));
                labels.add(String.valueOf(objects[2]));
                bgColors.add(ColorUtil.getColorDinamic());

                total += Double.valueOf(String.valueOf(objects[1]));


            }

            dataSet.setData(values);
            dataSet.setBackgroundColor(bgColors);

            data.addChartDataSet(dataSet);

            for (int i = 0; i < labels.size(); i++) {

                double porcentagem = 0.0D;

                Number valor = values.get(i);

                porcentagem = (valor.doubleValue() * 100) / total.doubleValue();

                labels.set(i, labels.get(i) + " (" + format.format(porcentagem) + "%)");

            }

            data.setLabels(labels);

            pieModel.setData(data);

            PieChartOptions opt = new PieChartOptions();
            Legend legend = new Legend();
            legend.setPosition("right");
            legend.setDisplay(true);
            opt.setLegend(legend);
            Title title = new Title();
            title.setDisplay(true);
            title.setText(key);

            opt.setTitle(title);

            pieModel.setOptions(opt);

            this.listPieModel.add(pieModel);

        }

        if (CollectionUtils.isNotEmpty(this.listResultListTexto)) {

            PieChartModel pieModel = new PieChartModel();
            ChartData data = new ChartData();

            PieChartDataSet dataSet = new PieChartDataSet();

            List<String> bgColors = new ArrayList<>();

            List<String> labels = new ArrayList<>();

            List<Number> values = new ArrayList<>();

            Integer total = 0;


            for (Object[] objects : this.listResultListTexto) {

                labels.add(String.valueOf(objects[1]));
                values.add(Integer.valueOf(String.valueOf(objects[2])));

                bgColors.add(ColorUtil.getColorDinamic());

                total += Integer.valueOf(String.valueOf(objects[2]));
            }

            for (int i = 0; i < labels.size(); i++) {

                double porcentagem = 0.0D;

                Number valor = values.get(i);

                porcentagem = (valor.intValue() * 100) / total;

                labels.set(i, labels.get(i) + " (" + format.format(porcentagem) + "%)");

            }

            dataSet.setData(values);
            dataSet.setBackgroundColor(bgColors);
            data.addChartDataSet(dataSet);
            data.setLabels(labels);

            pieModel.setData(data);

            PieChartOptions opt = new PieChartOptions();
            Legend legend = new Legend();
            legend.setPosition("right");
            legend.setDisplay(true);

            opt.setLegend(legend);
            Title title = new Title();
            title.setDisplay(true);
            title.setText("Perguntas Abertas");

            opt.setTitle(title);

            pieModel.setOptions(opt);

            this.listPieModel.add(pieModel);
        }
    }


    public void onChangeEmpresa() {

        this.idCampanha = null;

        if (getEmpresa() == null) {

            this.listCampanhas.clear();

        } else {

            this.listCampanhas = this.serviceCampanha.pesquisarCampanhasPorEmpresa(getEmpresa().getId());
        }
    }

    public void onChangeStatusCampanha() {

        this.idCampanha = null;

        if (getEmpresa() != null)
            this.listCampanhas = this.serviceCampanha.pesquisarCampanhasPorEmpresa(getEmpresa().getId(), idStatusCampanha);

    }


    /**
     * @return the idCampanha
     */
    public Long getIdCampanha() {
        return idCampanha;
    }


    /**
     * @param idCampanha the idCampanha to set
     */
    public void setIdCampanha(Long idCampanha) {
        this.idCampanha = idCampanha;
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
     * @return the idQuestionario
     */
    public Long getIdQuestionario() {
        return idQuestionario;
    }


    /**
     * @param idQuestionario the idQuestionario to set
     */
    public void setIdQuestionario(Long idQuestionario) {
        this.idQuestionario = idQuestionario;
    }


    /**
     * @return the questionario
     */
    public Questionario getQuestionario() {
        return questionario;
    }


    /**
     * @param questionario the questionario to set
     */
    public void setQuestionario(Questionario questionario) {
        this.questionario = questionario;
    }


    /**
     * @return the pergunta
     */
    public Pergunta getPergunta() {
        return pergunta;
    }


    /**
     * @param pergunta the pergunta to set
     */
    public void setPergunta(Pergunta pergunta) {
        this.pergunta = pergunta;
    }


    /**
     * @return the listStatusCampanha
     */
    public List<StatusCampanha> getListStatusCampanha() {
        return listStatusCampanha;
    }


    /**
     * @param listStatusCampanha the listStatusCampanha to set
     */
    public void setListStatusCampanha(List<StatusCampanha> listStatusCampanha) {
        this.listStatusCampanha = listStatusCampanha;
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
     * @return the listQuestionarios
     */
    public List<Questionario> getListQuestionarios() {
        return listQuestionarios;
    }


    /**
     * @param listQuestionarios the listQuestionarios to set
     */
    public void setListQuestionarios(List<Questionario> listQuestionarios) {
        this.listQuestionarios = listQuestionarios;
    }


    /**
     * @return the listResultList
     */
    public List<Object[]> getListResultList() {
        return listResultList;
    }


    /**
     * @param listResultList the listResultList to set
     */
    public void setListResultList(List<Object[]> listResultList) {
        this.listResultList = listResultList;
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

    public List<PieChartModel> getListPieModel() {
        return listPieModel;
    }


}
