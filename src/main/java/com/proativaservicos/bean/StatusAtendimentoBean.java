package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.Motivo;
import com.proativaservicos.model.StatusAtendimento;
import com.proativaservicos.model.SubMotivo;
import com.proativaservicos.service.MotivoService;
import com.proativaservicos.service.StatusAtendimentoService;
import com.proativaservicos.service.SubMotivoService;
import com.proativaservicos.util.constantes.AcaoStatusAtendimentoEnum;
import com.proativaservicos.util.constantes.MessagesEnum;
import com.proativaservicos.util.constantes.TipoPaginaEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.collections4.CollectionUtils;
import org.omnifaces.util.Messages;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Named
@ViewScoped
public class StatusAtendimentoBean extends GenericBean {

    private static final long serialVersionUID = 1L;

    private TipoPaginaEnum tipoPagina;

    private StatusAtendimento statusAtendimento;

    private StatusAtendimento statusAtendimentoSelecionado;

    @Inject
    private StatusAtendimentoService serivce;

    @Inject
    private MotivoService serivceMotivo;

    @Inject
    private SubMotivoService serivceSubMotivo;

    private AcaoStatusAtendimentoEnum acaoStatus;

    private List<StatusAtendimento> listStatusAtendimento;

    private List<Motivo> listMotivos;

    private List<Motivo> listMotivosSelecionados;
    private List<SubMotivo> listSubMotivosSelecionados;

    private List<SubMotivo> listSubMotivos;

    private Motivo motivoModel;

    private SubMotivo subMotivo;

    private Motivo motivoSelecionado;

    private boolean motivo;

    @PostConstruct
    public void init() {

        this.motivoModel = new Motivo();
        this.subMotivo = new SubMotivo();
        this.motivoSelecionado = new Motivo();
        this.listSubMotivos = new ArrayList<SubMotivo>();
        this.statusAtendimento = new StatusAtendimento();
        this.statusAtendimento.setMotivo(new Motivo());
        this.statusAtendimento.setSubmotivo(new SubMotivo());
        pesquisar();
        tipoPagina = TipoPaginaEnum.PESQUISA;
    }

    @SuppressWarnings("static-access")
    public AcaoStatusAtendimentoEnum[] getAcaoStatus() {

        return acaoStatus.values();

    }


    public void novo() {

        this.motivo = false;
        this.motivoSelecionado = new Motivo();
        this.statusAtendimento = new StatusAtendimento();
        this.statusAtendimento.setMotivo(new Motivo());
        this.statusAtendimento.setSubmotivo(new SubMotivo());
        this.tipoPagina = TipoPaginaEnum.CADASTRO;

    }

    public void pesquisar() {

        try {

            this.listStatusAtendimento = this.serivce.pesquisarStatusAtendimentosMotivoSubMotivo(retornarEmpresaMatrizUsuarioSessao().getId(), this.statusAtendimento, true);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addFatal(null, "Erro ao pesquisar.\n" + e.getMessage(), "");
        }

    }

    public void voltar() {

        init();
        tipoPagina = TipoPaginaEnum.PESQUISA;
    }

    public void salvar() {

        try {

            String msg = "";

            if (this.statusAtendimento.isTarget() && !this.statusAtendimento.isEfetivo()) {

                Messages.addGlobalWarn("Deve selecionar contato efetivo...", new Object[0]);

            } else if (this.statusAtendimento.getId() == null) {

                inserir((Serializable) this.statusAtendimento);
                msg = "Cadastrado com Sucesso!";
                Messages.addGlobalInfo(msg, new Object[0]);

                this.statusAtendimento = new StatusAtendimento();

            } else {

                alterar((Serializable) this.statusAtendimento);
                msg = "Atualizado com Sucesso!";
                Messages.addGlobalInfo(msg, new Object[0]);
            }

        } catch (ProativaException e) {

            e.printStackTrace();
            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_SALVAR.constante, new Object[0]);
        }

    }


    public void acaoEfetivo() {

        if (statusAtendimento.isTarget()) {

            statusAtendimento.setEfetivo(true);

        }

    }

    public void onChangeMotivo() {

        if (this.motivo) {

            this.listMotivos = this.serivceMotivo.pesquisarMotivosPorEmpresa(retornarEmpresaMatrizUsuarioSessao().getId());

            this.listMotivosSelecionados = new ArrayList<Motivo>();

            if (CollectionUtils.isNotEmpty(this.statusAtendimento.getListMotivos()))
                this.listMotivosSelecionados = this.statusAtendimento.getListMotivos();

        }
        this.motivoSelecionado = null;

    }

    public void onChangeSubMotivo() {

        this.subMotivo = new SubMotivo();

    }

    public void salvarMotivo() {

        try {

            this.motivoModel.setEmpresa(retornarUsuarioSessao().getEmpresa());

            if (this.motivoModel != null && this.motivoModel.getId() != null) {

                alterar((Serializable) this.motivoModel, true);

            } else {

                inserir((Serializable) this.motivoModel, true);

            }

            if (CollectionUtils.isEmpty(this.listMotivos))
                this.listMotivos = new ArrayList<Motivo>();

            Optional<Motivo> op = this.listMotivos.stream().filter(m -> m.getId().equals(this.motivoModel.getId())).findFirst();

            if (!op.isPresent())
                this.listMotivos.add(this.motivoModel);

            this.motivoModel = new Motivo();

        } catch (ProativaException e) {

            e.printStackTrace();
            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_SALVAR.constante, new Object[0]);
        }

    }

    public void salvarSubMotivo() {

        try {

            this.subMotivo.setEmpresa(retornarUsuarioSessao().getEmpresa());
            this.subMotivo.setMotivo(this.motivoSelecionado);

            if (this.subMotivo != null && this.subMotivo.getId() != null) {

                alterar((Serializable) this.subMotivo, true);

            } else {

                inserir((Serializable) this.subMotivo, true);

            }

            if (CollectionUtils.isEmpty(this.motivoSelecionado.getListSubMotivos()))
                this.motivoSelecionado.setListSubMotivos(new ArrayList<SubMotivo>());

            Optional<SubMotivo> op = this.motivoSelecionado.getListSubMotivos().stream().filter(m -> m.getId().equals(this.subMotivo.getId())).findFirst();

            if (!op.isPresent())
                this.motivoSelecionado.getListSubMotivos().add(this.subMotivo);

            this.subMotivo = new SubMotivo();
            PrimeFaces.current().executeScript("PF('panelSubMotivos').hide();");

        } catch (ProativaException e) {

            e.printStackTrace();
            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_SALVAR.constante, new Object[0]);
        }

    }

    public void editar(StatusAtendimento status) {

        this.motivo = false;

        this.tipoPagina = TipoPaginaEnum.CADASTRO;

        this.motivoSelecionado = null;

        this.statusAtendimento = this.serivce.pesquisarStatusAtendimentosListaMotivos(status.getId(), null);

        if (CollectionUtils.isNotEmpty(this.statusAtendimento.getListMotivos())) {

            this.motivo = true;

        } else {

            this.statusAtendimento.setListMotivos(new ArrayList<Motivo>());

        }

    }

    public void onClickMotivo(StatusAtendimento status) {

        this.statusAtendimentoSelecionado = status;

        if (this.statusAtendimentoSelecionado != null && this.statusAtendimentoSelecionado.getId() != null && CollectionUtils.isNotEmpty(this.statusAtendimentoSelecionado.getListMotivos())) {

            for (Motivo motivo : this.statusAtendimentoSelecionado.getListMotivos()) {

                motivo.setListSubMotivos(new ArrayList<>());
                motivo.setListSubMotivos(this.serivceSubMotivo.pesquisarSubMotivosPorMotivo(motivo.getId(), null));

            }

        }

    }

    public void selecionarMotivos() {

        this.statusAtendimento.setListMotivos(new ArrayList<Motivo>());

        if (CollectionUtils.isNotEmpty(this.listMotivosSelecionados)) {

            this.statusAtendimento.setListMotivos(this.listMotivosSelecionados);
            this.statusAtendimento.getListMotivos().forEach(m -> {
                System.out.println(m.getDescricao());
            });

        }
    }

    public void selecionarSubMotivos(Motivo motivo) {

        this.listSubMotivos = this.serivceSubMotivo.pesquisarSubMotivoPorEmpresa(retornarEmpresaMatrizUsuarioSessao().getId());

        this.statusAtendimento.getListMotivos().forEach(f -> {

            if (f.getId().equals(motivo.getId())) {

                f.setSelecionado(true);

            } else {

                f.setSelecionado(false);

            }

        });

        if (CollectionUtils.isEmpty(motivo.getListSubMotivos()))
            motivo.setListSubMotivos(new ArrayList<SubMotivo>());

    }

    public void onChangeComboSubmotivo() {

        try {

            if (CollectionUtils.isNotEmpty(this.motivoSelecionado.getListSubMotivos())) {

                for (Motivo motivo : this.statusAtendimento.getListMotivos()) {

                    if (motivo.getId().equals(this.motivoSelecionado.getId())) {

                        motivo.setListSubMotivos(new ArrayList<SubMotivo>());
                        motivo.getListSubMotivos().addAll(this.motivoSelecionado.getListSubMotivos());
                        alterar((Serializable) motivo);
                    }

                }

            }
        } catch (ProativaException e) {

            e.printStackTrace();
            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_SALVAR.constante, new Object[0]);
        }
    }

    public List<StatusAtendimento> getListStatusAtendimento() {
        return listStatusAtendimento;
    }

    public void setListStatusAtendimento(List<StatusAtendimento> listStatusAtendimento) {
        this.listStatusAtendimento = listStatusAtendimento;
    }

    public boolean isMotivo() {
        return motivo;
    }

    public void setMotivo(boolean motivo) {
        this.motivo = motivo;
    }

    public Motivo getMotivoModel() {
        return motivoModel;
    }

    public void setMotivoModel(Motivo motivoModel) {
        this.motivoModel = motivoModel;
    }

    /**
     * @return the listMotivos
     */
    public List<Motivo> getListMotivos() {
        return listMotivos;
    }

    /**
     * @param listMotivos the listMotivos to set
     */
    public void setListMotivos(List<Motivo> listMotivos) {
        this.listMotivos = listMotivos;
    }

    /**
     * @return the listSubMotivos
     */
    public List<SubMotivo> getListSubMotivos() {
        return listSubMotivos;
    }

    /**
     * @param listSubMotivos the listSubMotivos to set
     */
    public void setListSubMotivos(List<SubMotivo> listSubMotivos) {
        this.listSubMotivos = listSubMotivos;
    }

    /**
     * @param acaoStatus the acaoStatus to set
     */
    public void setAcaoStatus(AcaoStatusAtendimentoEnum acaoStatus) {
        this.acaoStatus = acaoStatus;
    }

    public TipoPaginaEnum getTipoPagina() {
        return tipoPagina;
    }

    public void setTipoPagina(TipoPaginaEnum tipoPagina) {
        this.tipoPagina = tipoPagina;
    }

    public StatusAtendimento getStatusAtendimento() {
        return statusAtendimento;
    }

    public void setStatusAtendimento(StatusAtendimento statusAtendimento) {
        this.statusAtendimento = statusAtendimento;
    }

    public List<Motivo> getListMotivosSelecionados() {
        return listMotivosSelecionados;
    }

    public void setListMotivosSelecionados(List<Motivo> listMotivosSelecionados) {
        this.listMotivosSelecionados = listMotivosSelecionados;
    }

    public SubMotivo getSubMotivo() {
        return subMotivo;
    }

    public void setSubMotivo(SubMotivo subMotivo) {
        this.subMotivo = subMotivo;
    }

    public Motivo getMotivoSelecionado() {
        return motivoSelecionado;
    }

    public void setMotivoSelecionado(Motivo motivoSelecionado) {
        this.motivoSelecionado = motivoSelecionado;
    }

    public List<SubMotivo> getListSubMotivosSelecionados() {
        return listSubMotivosSelecionados;
    }

    public void setListSubMotivosSelecionados(List<SubMotivo> listSubMotivosSelecionados) {
        this.listSubMotivosSelecionados = listSubMotivosSelecionados;
    }

    public StatusAtendimento getStatusAtendimentoSelecionado() {
        return statusAtendimentoSelecionado;
    }

    public void setStatusAtendimentoSelecionado(StatusAtendimento statusAtendimentoSelecionado) {
        this.statusAtendimentoSelecionado = statusAtendimentoSelecionado;
    }
}
