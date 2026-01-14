package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.Motivo;
import com.proativaservicos.model.SubMotivo;
import com.proativaservicos.service.MotivoService;
import com.proativaservicos.service.SubMotivoService;
import com.proativaservicos.util.constantes.AcaoStatusAtendimentoEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import com.proativaservicos.util.constantes.TipoPaginaEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Messages;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class CadastroMotivoBean extends GenericBean {


    @Inject
    private MotivoService motivoService;

    @Inject
    private SubMotivoService subMotivoService;

    private List<Motivo> listMotivos;

    private List<SubMotivo> listSubMotivos;

    private Motivo motivo;

    private SubMotivo subMotivo;

    private TipoPaginaEnum tipoPaginaEnum;

    private String nomeMotivo;

    private TipoAcessoEnum motivoAtivoEnum;


    @PostConstruct
    public void init() {

        this.motivo = new Motivo();
        pesquisar();
        this.tipoPaginaEnum = TipoPaginaEnum.PESQUISA;

    }

    public void pesquisar() {

        this.listMotivos = this.motivoService.pesquisarMotivos(nomeMotivo, motivoAtivoEnum);
    }

    public void salvar() {

        try {

            validarMotivo();
            this.motivo.setListSubMotivos(new ArrayList<>());

            if (this.motivo.getId() == null) {

                this.motivo.setUsuarioCadastro(retornarUsuarioSessao());
                this.motivo.setEmpresa(retornarEmpresaUsuarioSessao());
                this.motivo.setDataCadastro(new Date());
                inserir(this.motivo, true);
                this.motivo = new Motivo();

            } else {

                this.motivo.setDataAlteracao(new Date());
                this.motivo.setUsuarioAlteracao(retornarUsuarioSessao());
                alterar(this.motivo);
            }

            Messages.addGlobalInfo("Motivo salvo com sucesso");

        } catch (ProativaException e) {
            Messages.addGlobalError(e.getMessage());
        }
    }

    public void validarMotivo() throws ProativaException {

        if (StringUtils.isBlank(this.motivo.getDescricao())) {
            throw new ProativaException("Informe o nome do motivo");
        }

        if(StringUtils.isBlank(this.motivo.getCor()))
            this.motivo.setCor("#4B576F");

    }

    public void novo() {
        this.motivo = new Motivo();
        this.subMotivo = new SubMotivo();
        this.tipoPaginaEnum = TipoPaginaEnum.CADASTRO;
    }

    public void editar(Long idMotivo) {

        this.subMotivo = new SubMotivo();
        this.motivo = this.motivoService.pesquisarMotivoPorId(idMotivo);
        this.tipoPaginaEnum = TipoPaginaEnum.CADASTRO;

    }

    public void onListarSubMotivos(Motivo motivo) {
        this.motivo = motivo;

    }

    public void voltar() {
        motivo = new Motivo();
        subMotivo = null;
        nomeMotivo = "";
        tipoPaginaEnum = null;
        pesquisar();
        tipoPaginaEnum = TipoPaginaEnum.PESQUISA;
    }

    public List<Motivo> getListMotivos() {
        return listMotivos;
    }

    public void setListMotivos(List<Motivo> listMotivos) {
        this.listMotivos = listMotivos;
    }

    public List<SubMotivo> getListSubMotivos() {
        return listSubMotivos;
    }

    public void setListSubMotivos(List<SubMotivo> listSubMotivos) {
        this.listSubMotivos = listSubMotivos;
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

    public TipoPaginaEnum getTipoPaginaEnum() {
        return tipoPaginaEnum;
    }

    public void setTipoPaginaEnum(TipoPaginaEnum tipoPaginaEnum) {
        this.tipoPaginaEnum = tipoPaginaEnum;
    }

    public String getNomeMotivo() {
        return nomeMotivo;
    }

    public void setNomeMotivo(String nomeMotivo) {
        this.nomeMotivo = nomeMotivo;
    }

    public AcaoStatusAtendimentoEnum[] getListAcaoStatusAtendimentoEnum() {
        return AcaoStatusAtendimentoEnum.values();
    }

    public TipoAcessoEnum getMotivoAtivoEnum() {
        return motivoAtivoEnum;
    }

    public void setMotivoAtivoEnum(TipoAcessoEnum motivoAtivoEnum) {
        this.motivoAtivoEnum = motivoAtivoEnum;
    }
}
