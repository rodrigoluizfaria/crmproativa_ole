package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.*;
import com.proativaservicos.model.trescplus.MailingEnvio3c;
import com.proativaservicos.service.*;
import com.proativaservicos.util.*;
import com.proativaservicos.util.constantes.*;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.PrimeFaces;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSeparator;
import org.primefaces.model.menu.DefaultSubMenu;

import java.io.Serializable;
import java.text.ParseException;
import java.util.*;

@Named
@RequestScoped
public class LoginBean extends GenericBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Inject
    private UsuarioService serviceUsuario;

    @Inject
    private UsuarioLogadoService serviceUsuarioLogado;

    @Inject
    private ControleUsuarioBloqueadoService serviceUsuarioBloqueado;

    @Inject
    private EmpresaService serviceEmpresa;

    @Inject
    private AtendimentoService serviceAtendimento;

    @Inject
    private RegistroSistemaUtil registro;

    @Inject
    private DiscadorUtil discadorUtil;

    List<GenericHistoricoAtendimento> listHistorico;

    private String strLogin;
    private String senha;

    private String novaSenha;
    private String confirmaSenha;

    private boolean gerarSenha = false;

    private boolean manterConectado = false;

    @Inject
    private ProcessarCampanhaCsv csv;

    @PostConstruct
    public void init() {

        this.senha = "";
        this.strLogin = "";
    }

    public void login() {

        try {

            checkUsuarioLogado(strLogin);

            // Faces.login(this.strLogin, this.senha);

            Usuario usuario = pesquisarUsuarioSenha(this.strLogin, this.senha);

            if (usuario.getAtivo().equals(TipoAcessoEnum.INATIVO)) {

                logout();

                Messages.addGlobalError(MessagesEnum.USUARIO_INVALIDO.constante, new Object[0]);

            } else {

                if (validarUsuarioBloqueado(usuario))
                    throw new ProativaException(MessagesEnum.USUARIO_BLOQUEADO.constante);


                if (usuario.getSenhaInicial() == SimNaoEnum.NAO) {

                    // VALIDAR MASTER ANTES
                    if (usuario.isPerfilMaster()) {

                    }

                    // VALIDAR QUANTIDADE USUARIO LOGADO
                    validarExpediente(usuario);

                    usuario.setIp(getMyIP());

                    if (usuario.getPerfil().equals(PerfilUsuarioEnum.OPERADOR)) {

                        //  usuario.setQuantidadePendentes(this.serviceAtendimento.pesquisarQuantidadePendentes(usuario.getId(), Arrays.asList(new String[]{TipoCampanhaEnum.ATIVA.name(), TipoCampanhaEnum.RECEPTIVA.name(), TipoCampanhaEnum.PREDITIVA.name()})));
                        //  usuario.setQuantidadeAgendamentos(this.serviceAtendimento.pesquisarQuantidadeAtendimentosAlerta(usuario.getId()));

                    }

                    String ipServer = Faces.getRequestDomainURL();
                    ipServer = ipServer.startsWith("http://") ? ipServer : "http://" + ipServer;
                    usuario.setIpServer(ipServer);
                    Faces.setSessionAttribute("usuario", usuario);

                    try {

                        if (usuario.getPontoAtendimento() != null && usuario.getPerfil().equals(PerfilUsuarioEnum.OPERADOR) && usuario.getPontoAtendimento().getPabx() != null && usuario.getPontoAtendimento().getPabx().getTipo().equals(TipoPabxEnum.VSPHONE)) {

                            this.discadorUtil.logar(usuario);

                            if (usuario.getRespostaLoginPowerDialer() != null && usuario.getRespostaLoginPowerDialer().getSucess() != null && !usuario.getRespostaLoginPowerDialer().getSucess()) {


                                if (usuario.getRespostaLoginPowerDialer().getMessage().contains("está logado com")) {
                                    //A PENSAR.....

                                    usuario.setPontoAtendimento(null);

                                }

                                Messages.addFlashGlobalWarn(usuario.getRespostaLoginPowerDialer().getMessage(), new Object[0]);
                            }
                        }

                    } catch (Exception e) {

                        e.printStackTrace();

                    }

                    UsuarioLogado logado = new UsuarioLogado(usuario, Faces.getSessionId());
                    PrimeFaces.current().ajax().addCallbackParam("sucesso", "sucesso");


                    if (!usuario.isPerfilMaster() && !usuario.getPerfil().equals(PerfilUsuarioEnum.USUARIO_CONSULTA)) {
                        this.serviceUsuarioLogado.excluirUsuarioLogado(usuario.getId());
                        this.serviceUsuarioLogado.inserir(logado);
                    }

                    // ALTERAR ULTIMA ALTERACAO
                    this.serviceUsuario.atualizarUltimoAcesso(usuario.getId());

                    this.registro.registrarLog(usuario.getId(), TipoEventoEnum.LOGIN, "usuario Logado", usuario.getIp());

                    creatMenu(usuario);

                    if (isManterConectado()) {

                        Faces.setSessionMaxInactiveInterval(-1);

                    } else if (usuario.getEmpresa() != null && usuario.getEmpresa().getSessionTime() != null) {

                        Faces.setSessionMaxInactiveInterval(retornarSegundos(usuario.getEmpresa().getSessionTime()));
                    }

                    if (usuario.getPerfil().equals(PerfilUsuarioEnum.USUARIO_CONSULTA)) {

                        PrimeFaces.current().ajax().addCallbackParam("status", "consulta");

                    } else
                        PrimeFaces.current().ajax().addCallbackParam("status", "sucesso");


                } else {

                    // sair();
                    PrimeFaces.current().ajax().addCallbackParam("status", "alterar_senha");
                    PrimeFaces.current().ajax().addCallbackParam("login", strLogin);
                    Messages.addGlobalInfo(MessagesEnum.ALTERAR_SENHA.constante, new Object[0]);

                }

            }


        } catch (ProativaException e1) {

            logout();
            Messages.addGlobalError(e1.getMessage(), new Object[0]);

        } catch (Exception e) {

            Messages.addGlobalError(MessagesEnum.FALHA_LOGAR_SISTEMA.constante, new Object[0]);
            logout();
            e.printStackTrace();
        }

    }

    private Integer retornarSegundos(Integer segundos) {

        if (segundos == null)
            return null;

        if (segundos <= 0) {
            return 0;
        }

        return segundos * 60;
    }

    private void validarExpediente(Usuario usuario) throws ProativaException, ParseException {

        if (usuario != null && usuario.getExpediente() != null && TipoAcessoEnum.ATIVO.equals(usuario.getExpediente().getAcesso()) && usuario.getExpediente().getListDiasDaSemana() != null
                && !usuario.getExpediente().getListDiasDaSemana().isEmpty()) {

            Map<Integer, ExpedienteDiaSemana> listaDiasSemana = new HashMap<>();

            for (ExpedienteDiaSemana diaSemana : usuario.getExpediente().getListDiasDaSemana()) {

                listaDiasSemana.put(diaSemana.getId().getDiaSemana().getDiaSemana(), diaSemana);

            }

            Date dateHoraServidor = pesquisarDataCorrenteServidor();

            Calendar hoje = Calendar.getInstance();
            hoje.setTime(dateHoraServidor);
            int diaSemana = hoje.get(7);

            if (!listaDiasSemana.containsKey(Integer.valueOf(diaSemana))) {

                throw new ProativaException(MessagesEnum.EXPEDIENTE_ENCERROU.constante);
            }
            Date horaServidor = DateUtil.builder(dateHoraServidor).retornarHora().getData();

            ExpedienteDiaSemana diaSemanaExpediente = listaDiasSemana.get(Integer.valueOf(diaSemana));
            Date horaEntrada = diaSemanaExpediente.getHoraEntrada();
            Date horaSaida = diaSemanaExpediente.getHoraSaida();

            if (horaEntrada == null || horaSaida == null || horaServidor.before(horaEntrada)
                    || horaServidor.after(horaSaida))

                throw new ProativaException(MessagesEnum.EXPEDIENTE_ENCERROU.constante);

            if (usuario.getExpediente().getTolerancia() != null) {

                Object objAcesso = this.serviceUsuario.pesquisarAcessoUsuario(usuario.getId(), usuario.getEmpresa().getId());

                if (objAcesso == null && horaServidor.getTime() - horaEntrada.getTime() > (usuario.getExpediente().getTolerancia() * 60000)) {

                    ControleUsuarioBloqueado usuarioBloqueador = new ControleUsuarioBloqueado();
                    usuarioBloqueador.setUsuarioBloqueado(usuario);
                    usuarioBloqueador.setDataCadastro(new Date(System.currentTimeMillis()));
                    usuarioBloqueador.setEmpresa(usuario.getEmpresa());
                    alterarGenerico((Serializable) usuarioBloqueador);
                    throw new ProativaException(MessagesEnum.USUARIO_BLOQUEADO.constante);

                }

            }

        }
    }

    public void creatMenu(Usuario usuario) {

        Map<MenuEnum, DefaultSubMenu> mapListMenu = new TreeMap<MenuEnum, DefaultSubMenu>();

        DefaultMenuModel menuModel = new DefaultMenuModel();

        for (SubMenuEnum subMenuEnum : SubMenuEnum.values()) {

            if (subMenuEnum.getMenu().getListPermicoes().contains(usuario.getPerfil()) && !subMenuEnum.getListPermecaoNegada().contains(usuario.getPerfil())) {

                DefaultSubMenu menu;

                if (mapListMenu.containsKey(subMenuEnum.getMenu())) {

                    menu = mapListMenu.get(subMenuEnum.getMenu());

                } else {

                    menu = new DefaultSubMenu().builder().label(subMenuEnum.getMenu().nomeCaptalize()).build();

                    if (subMenuEnum.getMenu().getIcon() != null)
                        menu.setIcon(subMenuEnum.getMenu().getIcon());

                    mapListMenu.put(subMenuEnum.getMenu(), menu);
                }

                DefaultMenuItem menuItem = new DefaultMenuItem().builder().global(false).styleClass("active-menuitem").immediate(true).command(subMenuEnum.getPageComplementar() + "?faces-redirect=true").build();
                menuItem.setValue(LocaleUtil.getInstance().getMessage(subMenuEnum.getNome()));

                if (subMenuEnum.getIcon() != null)
                    menuItem.setIcon(subMenuEnum.getIcon());

                menu.getElements().add(menuItem);

            }

        }

        DefaultSubMenu menuPrincipal = new DefaultSubMenu().builder().icon("pi pi-home").label("").build();

        DefaultMenuItem itemDash = new DefaultMenuItem().builder().icon("pi pi-home").value("Home").immediate(true).outcome("/pages/home/inicial.jsf").build();

        menuPrincipal.getElements().add(itemDash);

        menuModel.getElements().add(itemDash);
        // menuModel.getElements().add(new DefaultSeparator());

        for (MenuEnum menu : mapListMenu.keySet()) {

            menuModel.getElements().add(mapListMenu.get(menu));
            //     menuModel.getElements().add(new DefaultSeparator());

        }

        Faces.setSessionAttribute("menuModel", menuModel);
    }

    public void checkUsuarioLogado(String login) throws ProativaException {


        if (serviceUsuarioLogado.checkUsuarioLogado(login)) {

            throw new ProativaException(MessagesEnum.USUARIO_LOGADO.constante);

        }

    }

    private Usuario pesquisarUsuarioSenha(String strLogin, String senha) throws ProativaException {

        Usuario usuario = serviceUsuario.pesquisarUsuario(strLogin, senha);


        if (usuario != null && usuario.getEmpresa() != null && usuario.getEmpresa().getMatriz() == null) {

            usuario.getEmpresa().setFiliais(this.serviceEmpresa.pesquisarEmpresasFiliais(usuario.getEmpresa().getId()));

            if (usuario.getEmpresa().getFiliais() != null && usuario.getEmpresa().getFiliais().size() > 1)
                usuario.getEmpresa().setPossuiFiliais(true);


        }

        return usuario;
    }

    private boolean validarUsuarioBloqueado(Usuario usuario) {

        ControleUsuarioBloqueado usuarioBloquedo = serviceUsuarioBloqueado.findUsuarioBloqueado(usuario);

        return usuarioBloquedo != null;
    }

    public void sair() throws ProativaException {

        try {

            Usuario usuario = retornarUsuarioSessao();

            if (usuario != null && usuario.getId() != null) {

                this.registro.registrarLog(usuario.getId(), TipoEventoEnum.LOGOUT, MessagesEnum.LOGOUT.constante);
                serviceUsuarioLogado.excluirUsuarioLogado(usuario.getId());

            }

        } catch (Exception e) {


        } finally {

            logout();

        }

    }

    public void alterarSenha() {

        try {

            if (StringUtils.isBlank(this.strLogin))
                throw new ProativaException("Informe o login de acesso.");

            if (StringUtils.isBlank(this.novaSenha) || StringUtils.isBlank(this.confirmaSenha))
                throw new ProativaException("Obrigatório informar informa a nova senha.");

            if (!this.novaSenha.equals(this.confirmaSenha))
                throw new ProativaException("As senhas não correspondem");

            Usuario usuario = this.serviceUsuario.pesquisarUsuarioPorLogin(this.strLogin);

            if (usuario == null)
                throw new ProativaException("Houve um erro ao atualizar a senha.");

            usuario.setSenhaInicial(SimNaoEnum.NAO);
            usuario.setSenha(CriptografiaUtil.criptografarSHA256(this.novaSenha));
            alterarGenerico((Serializable) usuario);
            this.registro.registrarLog(usuario.getId(), TipoEventoEnum.USUARIO_ALTEROU_SENHA,
                    "USUARIO ALTEROU SENHA NO LOGIN", getMyIP());

            PrimeFaces.current().executeScript("PF('dlgAlterarSenha').hide();");
            this.novaSenha = null;
            this.confirmaSenha = null;

            Messages.addGlobalInfo(MessagesEnum.SENHA_ALTERADA_COM_SUCESSO.constante, new Object[0]);
            sair();

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);
        } catch (Exception e) {
            // TODO: handle exception
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }

    public Usuario retornarUsuarioSessao() {

        return (Usuario) Faces.getSessionAttribute("usuario");
    }

    private void logout() {
        try {
            Faces.logout();
            Faces.invalidateSession();
        } catch (ServletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String getMyIP() {

        try {

            FacesContext fc = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
            return request.getRemoteAddr();
        } catch (Exception e) {
            return "";

        }
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getStrLogin() {
        return strLogin;
    }

    public void setStrLogin(String login) {
        this.strLogin = login;
    }

    public boolean isGerarSenha() {
        return gerarSenha;
    }

    public void setGerarSenha(boolean gerarSenha) {
        this.gerarSenha = gerarSenha;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public boolean isManterConectado() {
        return manterConectado;
    }

    public void setManterConectado(boolean manterConectado) {
        this.manterConectado = manterConectado;
    }


}
