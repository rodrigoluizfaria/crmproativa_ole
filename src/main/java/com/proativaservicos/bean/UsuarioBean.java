package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.Empresa;
import com.proativaservicos.model.Equipe;
import com.proativaservicos.model.Expediente;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.service.CampanhaService;
import com.proativaservicos.service.EquipeService;
import com.proativaservicos.service.ExpedienteService;
import com.proativaservicos.service.UsuarioService;
import com.proativaservicos.util.CriptografiaUtil;
import com.proativaservicos.util.Util;
import com.proativaservicos.util.VirtualDiscadorPoweDial;
import com.proativaservicos.util.constantes.*;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.file.UploadedFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@ViewScoped
@Named
public class UsuarioBean extends GenericBean  {

    @Inject
    private UsuarioService serviceUsuario;
    @Inject
    private EquipeService equipeService;

    @Inject
    private ExpedienteService serviceExpediente;

    private boolean loginEditar;

    private Usuario usuario;

    private String senhaGerada;

    private String confirmaSenha;

    private List<Empresa> listEmpresa;

    private List<Usuario> listUsuario;

    private List<Equipe> listEquipe;

    private List<Usuario> listSupervisor;

    private List<Usuario> listCoordenador;

    @Inject
    private VirtualDiscadorPoweDial discadorPowerDialer;

    private List<Expediente> listExpediente;

    private LazyDataModel<Usuario> listUsuarioLazy;

    private UploadedFile fotoUpload;

    private TipoPaginaEnum tipoPagina;

    private boolean criarAgentDiscador;

    private List<?> listaDetalheUsuario;

    @Inject
    private CampanhaService serviceCampanha;
    @PostConstruct
    public void init() {

        this.usuario = new Usuario();
        this.usuario.setEquipe(null);
        this.usuario.setEmpresa(retornarEmpresaUsuarioSessao());
        this.senhaGerada = "";

        this.listEquipe = this.equipeService.pesquisarEquipesPorEmpresa(this.usuario.getEmpresa().getId(), TipoAcessoEnum.ATIVO);
        pesquisarUsuario();
        tipoPagina = TipoPaginaEnum.PESQUISA;

    }

    public void salvar() {

        try {

            this.usuario.setCpf(remove(usuario.getCpf()));

            if (usuario.getEquipe() == null || usuario.getEquipe().getId() == null) {

                usuario.setEquipe(null);

            }

            if (this.usuario.getExpediente() == null || this.usuario.getExpediente().getId() == null) {
                this.usuario.setExpediente(null);
            }

            this.usuario.setMetaMensal(Double.valueOf((this.usuario.getMetaMensal() == null) ? 0.0D : this.usuario.getMetaMensal().doubleValue()));
            this.usuario.setMetaSemanal(Double.valueOf((this.usuario.getMetaSemanal() == null) ? 0.0D : this.usuario.getMetaSemanal().doubleValue()));
            this.usuario.setMetaDiaria(Double.valueOf((this.usuario.getMetaDiaria() == null) ? 0.0D : this.usuario.getMetaDiaria().doubleValue()));

            this.usuario.setMetaMensalConcluida(Double.valueOf((this.usuario.getMetaMensalConcluida() == null) ? 0.0D : this.usuario.getMetaMensalConcluida().doubleValue()));
            this.usuario.setMetaSemanalConcluida(Double.valueOf((this.usuario.getMetaSemanalConcluida() == null) ? 0.0D : this.usuario.getMetaSemanalConcluida().doubleValue()));
            this.usuario.setMetaDiariaConcluida(Double.valueOf((this.usuario.getMetaDiariaConcluida() == null) ? 0.0D : this.usuario.getMetaDiariaConcluida().doubleValue()));

            // ATUALIZA USUARIO
            StringBuilder msg = new StringBuilder("");

            if (this.usuario.getId() != null) {

                if (StringUtils.isNotBlank(this.senhaGerada)) {

                    this.usuario.setSenha(CriptografiaUtil.criptografarSHA256(this.senhaGerada));
                }

                alterar((Serializable) this.usuario, false);

                Map<String, String> retornoPwd = criarAgentDiscador();

                msg.append("Usuario, " + MessagesEnum.ALTERADO_COM_SUCESSO.constante + ".");

                msg.append(retornarMsgDiscador(retornoPwd));

                this.senhaGerada = "";
                senhaGerada = "";

                // NOVO USUARIO


            } else {

                usuario.setSenha(senhaGerada.equals("") ? CriptografiaUtil.criptografarSHA256(Util.SENHA_INICIAL) : CriptografiaUtil.criptografarSHA256(senhaGerada));

                usuario.setSenhaInicial(SimNaoEnum.SIM);

                inserir((Serializable) usuario, false);

                Map<String, String> retornoPwd = criarAgentDiscador();

                msg.append("Usuario, " + MessagesEnum.ALTERADO_COM_SUCESSO.constante + ".");

                msg.append(retornarMsgDiscador(retornoPwd));

                usuario = new Usuario();

                senhaGerada = "";
            }

            Messages.addGlobalInfo(msg.toString(), new Object[0]);


        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        } finally {

            if (this.usuario.getEquipe() == null) {
                this.usuario.setEquipe(new Equipe());
            }

        }

    }

    private String retornarMsgDiscador(Map<String, String> retornoPwd) {

        String msg = "";

        try {

            if (retornoPwd != null && retornoPwd.containsKey("retorno")) {

                JSONObject obj = new JSONObject(retornoPwd.get("retorno"));

                if (!obj.isEmpty() && !obj.isNull("sucesso") && !obj.isNull("mensagem")) {
                    msg = " - Power Dialer: " + obj.getString("mensagem");
                }

            }

        } catch (Exception e) {
            return "";
        }

        return msg;
    }

    private Map<String, String> criarAgentDiscador() throws ProativaException {


        return this.discadorPowerDialer.criarAgent(this.usuario);
    }

    public void pesquisarUsuario() {


        try {

            this.listUsuario = this.serviceUsuario.pesquisarUsuarios(this.usuario.getEmpresa().getId(), usuario);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }
    }

    public void novoUsuario() {

        this.usuario = new Usuario();
        this.usuario.setEquipe(null);
        inicialziarVariaveis();
        tipoPagina = TipoPaginaEnum.CADASTRO;

    }


    public void voltar() {

        this.usuario = new Usuario();
        this.usuario.setEquipe(null);
        this.usuario.setEmpresa(retornarEmpresaUsuarioSessao());
        this.listEquipe = this.equipeService.pesquisarEquipesPorEmpresa(this.usuario.getEmpresa().getId(),
                TipoAcessoEnum.ATIVO);

        pesquisarUsuario();

        this.tipoPagina = TipoPaginaEnum.PESQUISA;

    }

    public void editarUsuario(Long idUsuario) {


        this.usuario = new Usuario();

        this.usuario = this.serviceUsuario.pesquisarUsuario(idUsuario);

        if (this.usuario.getEquipe() == null) {

            this.usuario.setEquipe(new Equipe());
        }

        if (this.usuario.getExpediente() == null) {

            this.usuario.setExpediente(new Expediente());
        }

        inicialziarVariaveis();
        tipoPagina = TipoPaginaEnum.CADASTRO;
        loginEditar = true;

    }

    public void popularEquipe() {

        listEquipe = new ArrayList<>();

        if (usuario.getEmpresa() != null) {

            try {


                listEquipe = equipeService.pesquisarEquipesAtivo(usuario.getEmpresa().getId());

            } catch (Exception e) {
                e.printStackTrace();
                Messages.addFatal(null, "Erro ao listar", "");
            }

        }

    }

    public void msg(FacesMessage.Severity tipo, String titulo, String msg) {

        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage(null, new FacesMessage(tipo, titulo, msg));
    }

    public void gerarSenha() {

        if (this.usuario != null && this.usuario.getId() != null) {

            senhaGerada = Util.gerarSenha();
            this.usuario.setSenhaInicial(SimNaoEnum.SIM);
            this.usuario.setSenha(CriptografiaUtil.criptografarSHA256(senhaGerada));


        } else {

            senhaGerada = "123456";
        }

        try {

            this.serviceUsuario.atualizarSenhaInicialUsuario(this.usuario.getLogin(),senhaGerada);

        } catch (ProativaException e) {
            Messages.addGlobalError(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void salvarSenha() {


    }

    public void buscarDetalhesUsuario(Long idUsuario) {

        this.listaDetalheUsuario = this.serviceCampanha.pesquisaCampanhaPorUsuario(idUsuario);

    }

    private static String remove(String CPF) {

        CPF = CPF.replace(".", "");
        CPF = CPF.replace("-", "");
        return CPF;
    }

    public void trocarEmpresa() {

        if (this.usuario.getEmpresa() == null) {

            this.listEquipe = null;
            this.listExpediente = null;

        } else {

            preencherListas(this.usuario.getEmpresa());

        }
    }

    private void preencherListas(Empresa empresa) {

        this.listEquipe = this.equipeService.pesquisarEquipes(empresa.getId(), TipoAcessoEnum.ATIVO);

        this.listExpediente = this.serviceExpediente.pesquisarExpedientes(empresa.getId(), TipoAcessoEnum.ATIVO);

        this.listCoordenador = this.serviceUsuario.pesquisarUsuariosPorNiveis(Arrays.asList(new PerfilUsuarioEnum[]{PerfilUsuarioEnum.COORDENADOR, PerfilUsuarioEnum.SUPERVISOR}), retornarEmpresaUsuarioSessao().getId());

        this.listSupervisor = this.serviceUsuario.pesquisarUsuariosPorNiveis(Arrays.asList(new PerfilUsuarioEnum[]{PerfilUsuarioEnum.SUPERVISOR}), retornarEmpresaUsuarioSessao().getId());

    }

    public void inicialziarVariaveis() {

        this.criarAgentDiscador = false;

        if (retornarUsuarioSessao().getEmpresa().isPossuiFiliais()) {

            trocarEmpresa();

        } else {

            preencherListas(retornarEmpresaUsuarioSessao());
        }

    }

    public void realizarUploadFoto(FileUploadEvent event) throws IOException {

        this.fotoUpload = event.getFile();

        this.usuario.setFoto(event.getFile().getFileName());

        if (this.fotoUpload != null) {

            File diretorio = new File(System.getProperty("user.home") + File.separator + "proativa" + File.separator + "fotos");

            if (!diretorio.exists()) {

                Files.createDirectories(diretorio.toPath(), (FileAttribute<?>[]) new FileAttribute[0]);
            }

            File arquivo = new File(System.getProperty("user.home") + File.separator + "proativa" + File.separator + "fotos" + File.separator + event.getFile().getFileName());

            InputStream inputStream = this.fotoUpload.getInputStream();

            Files.deleteIfExists(arquivo.toPath());

            Files.copy(inputStream, arquivo.toPath(), new java.nio.file.CopyOption[0]);

            BufferedImage img = ImageIO.read(this.fotoUpload.getInputStream());


            if (img.getWidth() > 128) {

                Files.deleteIfExists(arquivo.toPath());
            }


            inputStream.close();

        }

    }

    /* GET AND SET */

    public TipoPaginaEnum getTipoPagina() {
        return tipoPagina;
    }

    public SexoEnum[] getSexoEnum() {
        return SexoEnum.getSexosSemIndefinido();
    }


    public void setTipoPagina(TipoPaginaEnum tipoPagina) {
        this.tipoPagina = tipoPagina;
    }

    public LazyDataModel<Usuario> getListUsuarioLazy() {

        return listUsuarioLazy;
    }

    public void setListUsuarioLazy(LazyDataModel<Usuario> listUsuarioLazy) {
        this.listUsuarioLazy = listUsuarioLazy;
    }

    public List<Usuario> getListSupervisor() {
        return listSupervisor;
    }

    public void setListSupervisor(List<Usuario> listSupervisor) {
        this.listSupervisor = listSupervisor;
    }

    public List<Equipe> getListEquipe() {
        return listEquipe;
    }

    public void setListEquipe(List<Equipe> listEquipe) {
        this.listEquipe = listEquipe;
    }

    public List<Usuario> getListCoordenador() {
        return listCoordenador;
    }

    public void setListCoordenador(List<Usuario> listCoordenador) {
        this.listCoordenador = listCoordenador;
    }

    public boolean isLoginEditar() {
        return loginEditar;
    }

    public void setLoginEditar(boolean loginEditar) {
        this.loginEditar = loginEditar;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getSenhaGerada() {
        return senhaGerada;
    }

    public void setSenhaGerada(String senha) {
        this.senhaGerada = senha;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    public List<Empresa> getListEmpresa() {

        return listEmpresa;

    }

    public void setListEmpresa(List<Empresa> listEmpresa) {
        this.listEmpresa = listEmpresa;
    }

    public PerfilUsuarioEnum[] getPerfis() {
        return PerfilUsuarioEnum.values();
    }

    public List<Usuario> getListUsuario() {
        return listUsuario;
    }

    public void setListUsuario(List<Usuario> listUsuario) {
        this.listUsuario = listUsuario;
    }

    public List<Expediente> getListExpediente() {
        return listExpediente;
    }

    public void setListExpediente(List<Expediente> listExpediente) {
        this.listExpediente = listExpediente;
    }

    public List<?> getListaDetalheUsuario() {
        return listaDetalheUsuario;
    }

    public void setListaDetalheUsuario(List<?> listaDetalheUsuario) {
        this.listaDetalheUsuario = listaDetalheUsuario;
    }

    public boolean isCriarAgentDiscador() {
        return criarAgentDiscador;
    }

    public void setCriarAgentDiscador(boolean criarAgentDiscador) {
        this.criarAgentDiscador = criarAgentDiscador;
    }

}
