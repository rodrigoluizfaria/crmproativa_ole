package com.proativaservicos.bean;

import com.proativaservicos.model.Atendimento;
import com.proativaservicos.model.Portabilidade;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.service.AtendimentoService;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.constantes.SegmentoEnum;
import com.proativaservicos.util.constantes.TipoPaginaEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class FichaAtendimentoConsultaBean extends GenericBean {


    private List<Atendimento> atendimentoList;

    private Atendimento atendimento;

    private String cpf;

    private String especie;

    private Double totalParcelas;

    private Double totalSaldoDevedor;

    private Usuario usuario;

    private Atendimento atendimentoView;

    private TipoPaginaEnum tipoPaginaEnum;

    private List<Portabilidade> listPortabilidades;

    private List<Portabilidade> selectPortabilidades;

    @Inject
    private AtendimentoService atendimentoService;

    private Map<String, Object> mapInfornacoes;

    private String menssagem;

    @PostConstruct
    public void init() {

        this.usuario = retornarUsuarioSessao();
        inicializarVariaveis();
        this.tipoPaginaEnum = TipoPaginaEnum.PESQUISA;
    }

    public void inicializarVariaveis() {

        this.atendimentoList = new ArrayList<>();
        this.atendimentoView = new Atendimento();
        this.listPortabilidades = new ArrayList<>();
        this.mapInfornacoes = new HashMap<>();
        this.atendimento = new Atendimento();
        this.atendimentoView = new Atendimento();
        this.selectPortabilidades = new ArrayList<>();
        this.especie = "";
        this.totalParcelas = 0.0;
        this.totalSaldoDevedor = 0.0;
        this.menssagem =null;

    }

    public void pesquisarAtendimentos() {

        inicializarVariaveis();
        this.atendimento = this.atendimentoService.pesquisarAtendimentosConsulta(this.cpf, SegmentoEnum.PORTABILIDADE, retornarEmpresaMatrizUsuarioSessao().getId());
        this.listPortabilidades = new ArrayList<>();

        if (this.atendimento != null && CollectionUtils.isNotEmpty(this.atendimento.getListPortabilidades())) {

            for (Portabilidade portabilidade : this.atendimento.getListPortabilidades()) {

                if (CollectionUtils.isEmpty(this.listPortabilidades))
                    this.listPortabilidades.add(portabilidade);

                else if (this.listPortabilidades.stream().noneMatch(p -> portabilidade.getEspecie().equalsIgnoreCase(p.getEspecie())))
                    this.listPortabilidades.add(portabilidade);


            }

        }else{

            this.menssagem = "Nenhum registro localizado.";

        }

    }

    public void consultarAtendimento() {

        String atendimento = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");

        if (StringUtils.isNotBlank(atendimento)) {

            this.atendimentoView = this.atendimentoService.pesquisarAtendimentoPorId(Long.parseLong(atendimento));
            gerarMapInformacoes();
        }
    }

    public void consultarPortabilidades() {

        this.especie = "";
        this.selectPortabilidades = new ArrayList<>();
        this.totalSaldoDevedor = 0.0;
        this.totalParcelas = 0.0;

        String especie = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        this.especie = especie;
        this.listPortabilidades = this.atendimento.getListPortabilidades().stream().filter(p -> p.getEspecie().equalsIgnoreCase(especie)).collect(Collectors.toList());
        this.atendimentoView = this.atendimentoService.pesquisarAtendimentoPorId(atendimento.getId());
        gerarMapInformacoes();
        this.tipoPaginaEnum = TipoPaginaEnum.CONSULTA;

    }

    public void voltar() {

        inicializarVariaveis();
        this.tipoPaginaEnum = TipoPaginaEnum.PESQUISA;
    }

    public Integer getIdade() {

        if (this.atendimentoView != null && this.atendimentoView.getDataNascimento() != null) {

            return Integer.valueOf(DateUtil.builder(this.atendimentoView.getDataNascimento()).calcularIdade());

        }

        return null;
    }

    public void gerarMapInformacoes() {

        if (StringUtils.isNotBlank(this.atendimentoView.getInformacoesComplementares())) {

            JSONObject json = new JSONObject(this.atendimentoView.getInformacoesComplementares());

            this.mapInfornacoes = json.toMap();
        }
    }

    public void onSelectCheckBoxPortabilidade() {

        somarValoresPortabilidade();

    }

    public void onUnSelectCheckBoxPortabilidade() {

        somarValoresPortabilidade();
    }

    private void somarValoresPortabilidade() {

        if (CollectionUtils.isNotEmpty(this.selectPortabilidades)) {

            this.totalParcelas = this.selectPortabilidades.stream().mapToDouble(p -> p.getValorParcela().doubleValue()).sum();
            this.totalSaldoDevedor = this.selectPortabilidades.stream().mapToDouble(p -> p.getSaldoDevedor().doubleValue()).sum();

        } else {

            this.totalSaldoDevedor = 0.0;
            this.totalParcelas = 0.0;
        }

    }

    public long retornarQuantidadeContratosPorEspecie(String especie) {

        if (CollectionUtils.isEmpty(this.atendimento.getListPortabilidades())) {
            return 0L;
        }

        return this.atendimento.getListPortabilidades().stream().filter(p -> p.getEspecie().equalsIgnoreCase(especie)).count();

    }

    public List<Atendimento> getAtendimentoList() {
        return atendimentoList;
    }

    public void setAtendimentoList(List<Atendimento> atendimentoList) {
        this.atendimentoList = atendimentoList;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Atendimento getAtendimentoView() {
        return atendimentoView;
    }

    public void setAtendimentoView(Atendimento atendimentoView) {
        this.atendimentoView = atendimentoView;
    }

    public Map<String, Object> getMapInfornacoes() {
        return mapInfornacoes;
    }

    public void setMapInfornacoes(Map<String, Object> mapInfornacoes) {
        this.mapInfornacoes = mapInfornacoes;
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    public TipoPaginaEnum getTipoPaginaEnum() {
        return tipoPaginaEnum;
    }

    public void setTipoPaginaEnum(TipoPaginaEnum tipoPaginaEnum) {
        this.tipoPaginaEnum = tipoPaginaEnum;
    }

    public List<Portabilidade> getListPortabilidades() {
        return listPortabilidades;
    }

    public void setListPortabilidades(List<Portabilidade> listPortabilidades) {
        this.listPortabilidades = listPortabilidades;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public List<Portabilidade> getSelectPortabilidades() {
        return selectPortabilidades;
    }

    public void setSelectPortabilidades(List<Portabilidade> selectPortabilidades) {
        this.selectPortabilidades = selectPortabilidades;
    }

    public Double getTotalParcelas() {
        return totalParcelas;
    }

    public void setTotalParcelas(Double totalParcelas) {
        this.totalParcelas = totalParcelas;
    }

    public Double getTotalSaldoDevedor() {
        return totalSaldoDevedor;
    }

    public void setTotalSaldoDevedor(Double totalSaldoDevedor) {
        this.totalSaldoDevedor = totalSaldoDevedor;
    }

    public String getMenssagem() {
        return menssagem;
    }

    public void setMenssagem(String menssagem) {
        this.menssagem = menssagem;
    }
}
