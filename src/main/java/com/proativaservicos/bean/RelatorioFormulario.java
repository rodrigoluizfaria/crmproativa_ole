package com.proativaservicos.bean;

import com.proativaservicos.model.Campanha;
import com.proativaservicos.model.FiltroModel;
import com.proativaservicos.model.StatusCampanha;
import com.proativaservicos.model.pesquisa.Pergunta;
import com.proativaservicos.model.pesquisa.Questionario;
import com.proativaservicos.service.CampanhaService;
import com.proativaservicos.service.QuestionarioService;
import com.proativaservicos.service.RespostaService;
import com.proativaservicos.service.StatusCampanhaService;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Messages;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@ViewScoped
@Named
public class RelatorioFormulario extends GenericBean implements Serializable {


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
    private Long idPergunta;
    private Long idOpcao;

    private Questionario questionario;

    private Pergunta pergunta;

    private List<StatusCampanha> listStatusCampanha;

    private List<Campanha> listCampanhas;

    private List<Pergunta> listPerguntas;

    private List<Questionario> listQuestionarios;

    private List<Object[]> listResultList;

    private LazyDataModel<Object[]> listModel;


    private Date dataInicio;

    private Date dataFim;


    @PostConstruct
    public void init() {

        inicializarEmpresa();

        this.listQuestionarios = this.serviceQuestionario.pesquisarPorEmpresa(retornarEmpresaMatrizUsuarioSessao().getId());
        this.listStatusCampanha = this.serviceStatusCampanha.pesquisarStatusCampanhaPorEmpresa(retornarEmpresaMatrizUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);
        this.listPerguntas = new ArrayList<Pergunta>();
        this.listCampanhas = new ArrayList<Campanha>();

        this.dataInicio = DateUtil.builder(new Date()).retornarDataPrimeiroDiaMes().getData();
        this.dataFim = new Date();

       this.listModel = new LazyDataModel<Object[]>() {

           @Override
           public int count(Map<String, FilterMeta> map) {
               return pesquisarContador();
           }

           @Override
           public List<Object[]> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> map1) {


               FiltroModel  filtro = new FiltroModel();

              // filtro.setPropriedadeOrdenacao(retornarSortBy(sortField));

            //   filtro.setAscendente(SortOrder.ASCENDING.equals(sortOrder));

               sortBy.forEach((k, v) -> {
                   filtro.setAscendente(SortOrder.ASCENDING.equals(v.getOrder()));
                   filtro.setPropriedadeOrdenacao(retornarSortBy(v.getSortBy().getExpressionString().replaceAll("\\#\\{", "").replaceAll("\\}", "")));

               });

               filtro.setFistResult(first);

               filtro.setMaxResult(pageSize);

               return pesquisarList(filtro);

           }




        };


    }


    public void pesquisar() {

        try {

            this.listModel.setRowCount(pesquisarContador());

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(e.getMessage(), new Object());

        }

    }

    public int pesquisarContador(){

        Long empresa = (getEmpresa() == null) ? null : getEmpresa().getId();
        Integer quantidade = this.serviceResposta.pesquisarQuantidadeRegistrosRespostas(empresa, this.idStatusCampanha, this.idCampanha, this.idQuestionario, this.idPergunta, this.idOpcao, this.dataInicio, this.dataFim);
        return  quantidade == null ? 0 :quantidade;
    }


    public List<Object[]> pesquisarList(int fistResult, int maxResult) {

        Long empresa = (getEmpresa() == null) ? null : getEmpresa().getId();

        this.listResultList = this.serviceResposta.pesquisarRelatorioRespostas(empresa, this.idStatusCampanha, this.idCampanha, this.idQuestionario, this.idPergunta, this.idOpcao, this.dataInicio, this.dataFim, fistResult, maxResult);
        return this.listResultList;
    }

    public List<Object[]> pesquisarList(FiltroModel filtro) {

        Long empresa = (getEmpresa() == null) ? null : getEmpresa().getId();

        this.listResultList = this.serviceResposta.pesquisarRelatorioRespostas(empresa, this.idStatusCampanha, this.idCampanha, this.idQuestionario, this.idPergunta, this.idOpcao, this.dataInicio, this.dataFim, filtro);


        return this.listResultList;
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

    public void onChangePergunta() {

        if (this.idPergunta == null) {
            this.pergunta = null;

        } else {

            this.pergunta = this.serviceQuestionario.pesquisarPerguntaPorId(idPergunta);
        }

    }

    public void onChangeQuestionario() {

        if (this.idQuestionario == null) {

            this.pergunta = null;
            this.questionario = null;
            this.idOpcao = null;
            this.idPergunta = null;

        } else {

            this.questionario = this.serviceQuestionario.pesquisarQuestionarioPorId(this.idQuestionario);

        }


    }

    private String retornarSortBy(String obj) {

        if (StringUtils.isBlank(obj))
            return null;

        switch (obj) {

            case "obj[1]":

                return "c.descricao";


            case "obj[2]":

                return "a.cpf";

            case "obj[3]":

                return "a.nome";

            case "obj[4]":

                return "r.data_alteracao";

            case "obj[5]":

                return "p.descricao";

            case "obj[6]":

                return "resposta";

            case "obj[7]":

                return "u.nome";

            case "obj[8]":

                return "sa.descricao";

            case "obj[9]":

                return "a.valor_liberado";

            case "obj[0]":

                return "q.descricao";


            default:
                return null;
        }

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
     * @return the idPergunta
     */
    public Long getIdPergunta() {
        return idPergunta;
    }


    /**
     * @param idPergunta the idPergunta to set
     */
    public void setIdPergunta(Long idPergunta) {
        this.idPergunta = idPergunta;
    }


    /**
     * @return the idOpcao
     */
    public Long getIdOpcao() {
        return idOpcao;
    }


    /**
     * @param idOpcao the idOpcao to set
     */
    public void setIdOpcao(Long idOpcao) {
        this.idOpcao = idOpcao;
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
     * @return the listPerguntas
     */
    public List<Pergunta> getListPerguntas() {
        return listPerguntas;
    }


    /**
     * @param listPerguntas the listPerguntas to set
     */
    public void setListPerguntas(List<Pergunta> listPerguntas) {
        this.listPerguntas = listPerguntas;
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


    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public LazyDataModel<Object[]> getListModel() {
        return listModel;
    }

    public void setListModel(LazyDataModel<Object[]> listModel) {
        this.listModel = listModel;
    }

}
