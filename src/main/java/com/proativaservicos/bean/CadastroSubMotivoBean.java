package com.proativaservicos.bean;


import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.Motivo;
import com.proativaservicos.model.SubMotivo;
import com.proativaservicos.service.MotivoService;
import com.proativaservicos.service.SubMotivoService;
import com.proativaservicos.util.constantes.AcaoStatusAtendimentoEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import com.proativaservicos.util.constantes.TipoMetodosMotivoEnum;
import com.proativaservicos.util.constantes.TipoPaginaEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Messages;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class CadastroSubMotivoBean extends GenericBean {


    @Inject
    private SubMotivoService subMotivoService;

    @Inject
    private MotivoService motivoService;

    private Motivo motivo;

    private SubMotivo subMotivo;

    private String subMotivoDescricao;

    private TipoAcessoEnum tipoAcessoSubmotivo;

    private Long idMotivo;

    private List<SubMotivo> listSubMotivos;

    private List<Motivo> listMotivos;

    private List<TipoMetodosMotivoEnum> listTipoMetodosMotivoSelecionados;

    private TipoPaginaEnum tipoPaginaEnum;

    @PostConstruct
    public void init() {

        iniciarVariaveis();
        pesquisar();
        this.tipoPaginaEnum = TipoPaginaEnum.PESQUISA;
    }

    public void pesquisar() {

        this.listSubMotivos = this.subMotivoService.pesquisarSubMotivos(this.subMotivoDescricao, idMotivo, tipoAcessoSubmotivo);
    }

    public void iniciarVariaveis() {

        this.motivo = new Motivo();

        this.listMotivos = new ArrayList<>();
        this.subMotivoDescricao = null;
        this.tipoAcessoSubmotivo = null;
        this.listTipoMetodosMotivoSelecionados = new ArrayList<>();

        this.listMotivos = this.motivoService.pesquisarMotivos(null, null);
    }

    public void salvar() {

        try {

            validarSubMotivo();

            if (this.subMotivo.getId() == null) {

                this.subMotivo.setDataCadastro(new Date());
                this.subMotivo.setUsuarioCadastro(retornarUsuarioSessao());
                this.subMotivo.setEmpresa(retornarEmpresaUsuarioSessao());
                inserir(this.subMotivo);
                this.subMotivo = new SubMotivo();

            } else {

                this.subMotivo.setDataAlteracao(new Date());
                this.subMotivo.setUsuarioAlteracao(retornarUsuarioSessao());

                alterar(this.subMotivo);
            }

            Messages.addGlobalInfo("SubMotivo Salvo com sucesso");

        } catch (ProativaException e) {
            Messages.addGlobalError(e.getMessage());
        }

    }

    public void validarSubMotivo() throws ProativaException {

        if (StringUtils.isBlank(this.subMotivo.getDescricao()))
            throw new ProativaException("Descrição é obrigatório");

        if (this.subMotivo.getMotivo() == null)
            throw new ProativaException("O motivo é obrigatório");


        if (StringUtils.isBlank(this.subMotivo.getCor()))
            this.subMotivo.setCor((StringUtils.isBlank(this.subMotivo.getMotivo().getCor())) ? "#4B576F" : this.subMotivo.getMotivo().getCor());


        if (CollectionUtils.isEmpty(listTipoMetodosMotivoSelecionados))
            this.subMotivo.setTipoMetodosMotivo(null);
        else
            this.subMotivo.adicionarListMetodo(listTipoMetodosMotivoSelecionados);
    }

    public void editar(Long id) {

        this.subMotivo = this.subMotivoService.pesquisarSubMotivosPorId(id);

        if (StringUtils.isNotBlank(this.subMotivo.getTipoMetodosMotivo()))
            this.listTipoMetodosMotivoSelecionados = this.subMotivo.getListTipoMetodosMotivos();


        this.tipoPaginaEnum = TipoPaginaEnum.CADASTRO;
    }

    public void novo() {

        iniciarVariaveis();
        this.subMotivo = new SubMotivo();
        this.tipoPaginaEnum = TipoPaginaEnum.CADASTRO;
    }

    public void voltar() {

        iniciarVariaveis();
        this.subMotivo = new SubMotivo();
        pesquisar();
        this.tipoPaginaEnum = TipoPaginaEnum.PESQUISA;
    }

    public Motivo getMotivo() {
        return motivo;
    }

    public void setMotivo(Motivo motivo) {
        this.motivo = motivo;
    }

    public SubMotivo getSubMotivo() {
        return subMotivo;
    }

    public void setSubMotivo(SubMotivo subMotivo) {
        this.subMotivo = subMotivo;
    }

    public String getSubMotivoDescricao() {
        return subMotivoDescricao;
    }

    public void setSubMotivoDescricao(String subMotivoDescricao) {
        this.subMotivoDescricao = subMotivoDescricao;
    }

    public TipoAcessoEnum getTipoAcessoSubmotivo() {
        return tipoAcessoSubmotivo;
    }

    public void setTipoAcessoSubmotivo(TipoAcessoEnum tipoAcessoSubmotivo) {
        this.tipoAcessoSubmotivo = tipoAcessoSubmotivo;
    }

    public Long getIdMotivo() {
        return idMotivo;
    }

    public void setIdMotivo(Long idMotivo) {
        this.idMotivo = idMotivo;
    }

    public List<SubMotivo> getListSubMotivos() {
        return listSubMotivos;
    }

    public void setListSubMotivos(List<SubMotivo> listSubMotivos) {
        this.listSubMotivos = listSubMotivos;
    }

    public List<Motivo> getListMotivos() {
        return listMotivos;
    }

    public void setListMotivos(List<Motivo> listMotivos) {
        this.listMotivos = listMotivos;
    }

    public TipoPaginaEnum getTipoPaginaEnum() {
        return tipoPaginaEnum;
    }

    public void setTipoPaginaEnum(TipoPaginaEnum tipoPaginaEnum) {
        this.tipoPaginaEnum = tipoPaginaEnum;
    }

    public AcaoStatusAtendimentoEnum[] getListAcaoStatusAtendimentoEnum() {
        return AcaoStatusAtendimentoEnum.values();
    }

    public TipoMetodosMotivoEnum[] getListTipoMetodosMotivoEnum() {
        return TipoMetodosMotivoEnum.values();
    }

    public List<TipoMetodosMotivoEnum> getListTipoMetodosMotivoSelecionados() {
        return listTipoMetodosMotivoSelecionados;
    }

    public void setListTipoMetodosMotivoSelecionados(List<TipoMetodosMotivoEnum> listTipoMetodosMotivoSelecionados) {
        this.listTipoMetodosMotivoSelecionados = listTipoMetodosMotivoSelecionados;
    }
}
