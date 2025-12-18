package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.Campanha;
import com.proativaservicos.model.StatusAtendimento;
import com.proativaservicos.model.StatusCampanha;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.service.*;
import com.proativaservicos.util.constantes.MessagesEnum;
import com.proativaservicos.util.constantes.PerfilUsuarioEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import com.proativaservicos.util.constantes.TipoCampanhaEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.omnifaces.util.Messages;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class RelatoriosAgendamentosDiarios extends GenericBean {

    private static final long serialVersionUID = 1L;

    private Long idIsuario;

    private Long idCampanha;

    private Long idStatusAtendimento;

    private Long idStatusCampanha;

    private List<Usuario> listUsuarios;

    private List<Campanha> listCampanhas;

    private List<StatusCampanha> listStatusCampanha;

    private List<StatusAtendimento> listStatusAtendimentos;

    @Inject
    private UsuarioService serviceUsuario;

    @Inject
    private AtendimentoService serviceAtendimento;

    @Inject
    private CampanhaService serviceCampanha;

    @Inject
    private StatusAtendimentoService serviceStatusAtendimento;

    @Inject
    private StatusCampanhaService serviceStatusCampanha;

    private Usuario usuarioLogado;

    private Date dataInicio;

    private Date dataFim;

    private TipoCampanhaEnum tipoCampanha;

    private List<Object[]> listInformacaoes;

    @PostConstruct
    public void init() {

        try {

            this.usuarioLogado = retornarUsuarioSessao();

            inicializarEmpresa();

            trocarEmpresa();

            this.listStatusAtendimentos = this.serviceStatusAtendimento.pesquisarStatusAtendimentosAgendamentosPorEmpresa(retornarEmpresaMatrizUsuarioSessao().getId());
            this.listStatusCampanha = this.serviceStatusCampanha.pesquisarStatusCampanhaPorEmpresa(retornarEmpresaMatrizUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);

        } catch (Exception e) {

            e.printStackTrace();

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void pesquisar() {

        try {


            if (this.dataInicio != null && this.dataFim != null && this.dataInicio.after(this.dataFim))
                throw new ProativaException("Data inicio deve ser menor que a data final.");

            this.listInformacaoes = this.serviceAtendimento.pesquisarRelatorioAgendamentoDiarioOperador(this.idCampanha, this.idStatusCampanha, this.tipoCampanha, this.idIsuario, this.idStatusAtendimento, this.dataInicio, this.dataFim, getEmpresa().getId());


        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        }
    }


    public void trocarEmpresa() {

        if (getEmpresa() == null) {

            this.listCampanhas = null;
            this.listUsuarios = null;

        } else if (this.usuarioLogado.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

            this.listCampanhas = this.serviceCampanha.pesquisarCampanhaPorSupervisor(this.usuarioLogado.getId(), getEmpresa().getId());
            this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorSupervisor(this.usuarioLogado.getId(), getEmpresa().getId());

        } else {

            inicializarSelectOneMenu();
            this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorEmpresa(getEmpresa().getId());

        }

    }

    private void inicializarSelectOneMenu() {


        if (getEmpresa() != null) {

            this.listCampanhas = this.serviceCampanha.pesquisarCampanhasPorEmpresa(getEmpresa().getId(), this.idStatusCampanha, this.tipoCampanha);

        }

    }

    public void onExportar() {

        try {

            exportarArquivoCsv(Arrays.asList(new String[]{"Data", "Usuário", "Tipo Campanha", "Total de Agendamentos", "Status", "Quantidade", "Percentual"}), this.listInformacaoes, "relatorioAgendamentoDiaOperador.csv");

        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }


    }


    public void onAtualizarSelectOneCampanha() {
        inicializarSelectOneMenu();
    }

    public Long getIdIsuario() {
        return idIsuario;
    }

    public void setIdIsuario(Long idIsuario) {
        this.idIsuario = idIsuario;
    }

    public Long getIdCampanha() {
        return idCampanha;
    }

    public void setIdCampanha(Long idCampanha) {
        this.idCampanha = idCampanha;
    }

    public Long getIdStatusAtendimento() {
        return idStatusAtendimento;
    }

    public void setIdStatusAtendimento(Long idStatusAtendimento) {
        this.idStatusAtendimento = idStatusAtendimento;
    }

    public List<Usuario> getListUsuarios() {
        return listUsuarios;
    }

    public void setListUsuarios(List<Usuario> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    public List<Campanha> getListCampanhas() {
        return listCampanhas;
    }

    public void setListCampanhas(List<Campanha> listCampanhas) {
        this.listCampanhas = listCampanhas;
    }

    public List<StatusCampanha> getListStatusCampanha() {
        return listStatusCampanha;
    }

    public void setListStatusCampanha(List<StatusCampanha> listStatusCampanha) {
        this.listStatusCampanha = listStatusCampanha;
    }

    public List<StatusAtendimento> getListStatusAtendimentos() {
        return listStatusAtendimentos;
    }

    public void setListStatusAtendimentos(List<StatusAtendimento> listStatusAtendimentos) {
        this.listStatusAtendimentos = listStatusAtendimentos;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
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

    public TipoCampanhaEnum getTipoCampanha() {
        return tipoCampanha;
    }

    public void setTipoCampanha(TipoCampanhaEnum tipoCampanha) {
        this.tipoCampanha = tipoCampanha;
    }

    public Long getIdStatusCampanha() {
        return idStatusCampanha;
    }

    public void setIdStatusCampanha(Long idStatusCampanha) {
        this.idStatusCampanha = idStatusCampanha;
    }

    public List<Object[]> getListInformacaoes() {
        return listInformacaoes;
    }

    public void setListInformacaoes(List<Object[]> listInformacaoes) {
        this.listInformacaoes = listInformacaoes;
    }


}