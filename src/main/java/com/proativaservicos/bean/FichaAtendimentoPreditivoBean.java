package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.exception.SessaoInvalidaException;
import com.proativaservicos.model.*;
import com.proativaservicos.model.trescplus.Campanha;
import com.proativaservicos.model.trescplus.ResponseAuthenticate;
import com.proativaservicos.model.trescplus.ResponseListCampanha;
import com.proativaservicos.service.*;
import com.proativaservicos.util.*;
import com.proativaservicos.util.constantes.*;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.PrimeFaces;

import java.text.ParseException;
import java.util.*;

@Named
@ViewScoped
public class FichaAtendimentoPreditivoBean extends GenericBean {

    private static final long serialVersionUID = 1L;

    private Long cod;

    private Atendimento atendimento;

    @Inject
    private AtendimentoService serviceAtendimento;

    @Inject
    private ControlePausaService serviceControlePausa;

    @Inject
    private RegistroSistemaUtil registro;

    @Inject
    private RegistroSistemaDiarioService serviceRegistro;

    @Inject
    private StatusTelefoneService serviceStatusTelefone;

    @Inject
    private TelefoneService serviceTelefone;

    @Inject
    private AtendimentoAudiosService serviceAtendimentoAudios;

    @Inject
    private IntegracaoService serviceIntegracao;

    @Inject
    private DiscadorUtil serviceDiscadorUtil;

    @Inject
    private UsuarioLogadoService serviceUsuarioLogado;

    @Inject
    private VirtualDiscadorPoweDial discadorPowerDialer;

    private boolean mostrarCronometro;


    private Usuario usuario;

    private Long idAtendimento;

    private PontoAtendimento pontoAtendimento;

    private Pabx pabx;

    private IntegracaoWs integracaoEmpresa;

    private IntegracaoWs integracaoContactCenterVirtual;

    private List<CartaoSaqueComplementarBmg> listCatoesBmg;

    private String numeroTelefone;

    private boolean carregarConteudo;

    private boolean mostrarTransferirAtendimento;

    private String nomeCliente;
    private String ramal;
    private String codCliente;

    private String telefone;


    private String urlPwd;

    private String menssagemDiscador;

    private boolean destruir;

    private String qualify;
    @Inject
    private TresCPlusServiceUtil tresCPlusServiceUtil;

    @Inject
    private ArgusService argusService;

    private List<Campanha> listCampanha3c;

    private Integer idCampanha3c;

    private String callid;


    @PostConstruct
    public void init() {

        try {

            FacesUtil.removendoAtributos("id_preditivo_view");
            this.mostrarCronometro = true;
            this.menssagemDiscador = null;
            this.mostrarTransferirAtendimento = false;
            this.carregarConteudo = false;
            this.usuario = retornarUsuarioSessao();
            this.pontoAtendimento = this.usuario.getPontoAtendimento();
            this.destruir = true;

            if (validarClienteDiscador()) {

                if (this.usuario.getPontoAtendimento() == null) {

                    mostrarErro("Ponto de atendimento não associado");

                }

                this.pabx = this.pontoAtendimento.getPabx();

                if (this.pabx == null || this.pabx.getAtivo().equals(TipoAcessoEnum.INATIVO)) {

                    mostrarErro("Servidor de contact center inativo ou não informado.");

                }

                validarLog();

                if (this.pontoAtendimento != null && this.pontoAtendimento.getId() != null && StringUtils.isNotEmpty(this.pontoAtendimento.getRamal())) {

                    String usuarioEmUso = validarRamalEmUso();

                    if (StringUtils.isNotBlank(usuarioEmUso)) {

                        mostrarErro("Seu ramal [ " + this.usuario.getPontoAtendimento().getRamal() + " ] está em uso com o agent: " + usuarioEmUso + " , informe seu supervisor. ");

                        this.pontoAtendimento = null;
                        this.usuario.setPontoAtendimento(null);

                    }

                }

                validarServidorPabx();
                validarExpediente();
                validarPausa();

                this.integracaoEmpresa = this.serviceIntegracao.pesquisarIntegracaoDiscadora(this.usuario.getEmpresa().getId());

                if (this.pabx.getTipo().equals(TipoPabxEnum.VSPHONE)) {

                    this.integracaoContactCenterVirtual = this.serviceIntegracao.pesquisarIntegracao(TipoIntegracaoEnum.VIRTUAL_CONTACT_CENTER, this.usuario.getEmpresa().getId(), TipoAcessoEnum.ATIVO);


                    if (this.integracaoContactCenterVirtual == null)
                        throw new ProativaException("");

                    if (this.integracaoEmpresa == null)
                        throw new ProativaException("");

                    this.urlPwd = this.integracaoEmpresa.getUrl();

                    if (this.usuario.getRespostaLoginPowerDialer() != null && this.usuario.getRespostaLoginPowerDialer().getSucess() != null && this.usuario.getRespostaLoginPowerDialer().getSucess().equals(Boolean.FALSE)) {

                        mostrarErro("RETORNO DISCADOR: " + this.usuario.getRespostaLoginPowerDialer().getMessage());
                    }

                } else if (this.pabx.getTipo().equals(TipoPabxEnum.TRES_CPLUS)) {

                    String token = null;

                    if (this.pontoAtendimento != null && this.pontoAtendimento.getId() != null && StringUtils.isBlank(this.pontoAtendimento.getApiToken())) {

                        if (!this.integracaoEmpresa.getTipoIntegracao().equals(TipoIntegracaoEnum.TRES_CPLUS))
                            this.integracaoEmpresa = this.serviceIntegracao.pesquisarIntegracoes(TipoIntegracaoEnum.TRES_CPLUS, retornarEmpresaMatrizUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);

                        if (this.integracaoEmpresa == null)
                            throw new ProativaException("Nenhum serviço de integração encontrado.");

                        ResponseAuthenticate responseAuthenticate = this.tresCPlusServiceUtil.autenticacao(integracaoEmpresa, this.pontoAtendimento.getRamal(), this.pontoAtendimento.getSenha());
                        this.pontoAtendimento.setApiToken(responseAuthenticate.getAgent().getApiToken());


                    }

                    if (this.pontoAtendimento != null && this.pontoAtendimento.getId() != null && StringUtils.isNotBlank(this.pontoAtendimento.getApiToken()) && this.usuario.getIdCampanha3c() == null) {

                        ResponseListCampanha responseListCampanha = this.tresCPlusServiceUtil.listarCampanhasAgent(integracaoEmpresa, this.pontoAtendimento.getApiToken());

                        if (responseListCampanha == null || CollectionUtils.isEmpty(responseListCampanha.getData())) {
                            Messages.addGlobalWarn("Usuário não está associado a nenhum campanha 3C");
                        } else {
                            this.listCampanha3c = responseListCampanha.getData();
                        }

                    }


                } else if (this.pabx.getTipo().equals(TipoPabxEnum.ARGUS)) {

                    this.integracaoEmpresa = new IntegracaoWs();

                    if (this.pontoAtendimento != null && this.pontoAtendimento.getId() != null && StringUtils.isNotBlank(this.pontoAtendimento.getApiToken())) {

                        this.integracaoEmpresa.setTipoIntegracao(TipoIntegracaoEnum.ARGUS);
                        this.integracaoEmpresa.setToken(this.pontoAtendimento.getApiToken());

                    } else {

                        this.integracaoEmpresa = this.serviceIntegracao.pesquisarIntegracoes(TipoIntegracaoEnum.ARGUS, retornarEmpresaMatrizUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);

                    }

                }

                this.atendimento = new Atendimento();
                this.carregarConteudo = true;
            }

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private boolean validarClienteDiscador() {

        this.codCliente = Faces.getRequestParameter("idAtendimento");

        if (StringUtils.isNotBlank(this.codCliente)) {

            Faces.setRequestAttribute("idAtendimento", codCliente);
            this.pabx = this.pontoAtendimento.getPabx();
            mostrarTransferirAtendimento = true;
            onEnviarAtendimento();
            transferirAtendimento();
            String url = Faces.getRequestContextPath() + "/pages/atendimento/fichaAtendimento2.jsf";
            Faces.redirect(url);
            return false;
        }

        return true;

    }

    private void validarLog() {

        try {

            String tipo = null;

            Object logSistem = this.serviceRegistro.pesquisarUltimoEvento(this.usuario.getId(), null);


            if (logSistem != null)
                tipo = (String) logSistem;


            if (StringUtils.isNotBlank(tipo) && !tipo.equals(TipoEventoEnum.AGUARDANDO_ATENDIMENTO.name()))
                this.registro.registrarLog(this.usuario.getId(), TipoEventoEnum.AGUARDANDO_ATENDIMENTO, "aguardando atendimento", usuario.getIp());

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void onErroDiscador() {

        try {

            mostrarErro("Não foi possivel conectar ao discador: " + this.urlPwd);

        } catch (ProativaException e) {

            Messages.addGlobalError("Não foi possivel conectar ao discador: {0}", this.urlPwd);

        }

    }


    public String validarRamalEmUso() {

        String usuarioRamal = this.serviceUsuarioLogado.pesquisarSessaoUsuarioRamal(this.pontoAtendimento.getRamal(), this.usuario.getId());

        if (StringUtils.isNotBlank(usuarioRamal)) {

            return usuarioRamal;

        }
        return null;
    }

    @PreDestroy
    public void destroy() {


        if (isDestruir()) {


            try {

                if (this.integracaoContactCenterVirtual != null)
                    this.discadorPowerDialer.desassociarFilasDiscadora(this.integracaoContactCenterVirtual, this.usuario, false);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void mostrarErro(String msg) throws ProativaException {

        this.mostrarCronometro = false;
        this.menssagemDiscador = msg;

        throw new ProativaException(msg);
    }


    public void validarServidorPabx() throws ProativaException {

        this.urlPwd = null;

        if (!Arrays.asList(new TipoPabxEnum[]{TipoPabxEnum.VONIX, TipoPabxEnum.VSPHONE, TipoPabxEnum.TRES_CPLUS, TipoPabxEnum.ARGUS}).contains(this.pabx.getTipo())) {

            throw new ProativaException("Servidor Voip " + this.pabx.getDescricao() + " não Implementado. consulte o suporte para a Integração");
        }
    }

    public void onEnviarAtendimento() {

        try {

            this.idAtendimento = null;
            String idAtendimento;
            String fila;
            String callId;
            String telefone;

            this.destruir = false;

            idAtendimento = Faces.getRequestParameter("idAtendimento");

            if (StringUtils.isBlank(idAtendimento))
                throw new ProativaException("Atendimento não enviado.");

            this.idAtendimento = Long.parseLong(idAtendimento);

            fila = Faces.getRequestParameter("queue");

            callId = Faces.getRequestParameter("call_id");

            telefone = Faces.getRequestParameter("from");

            // CALL ID ATENDIMENTO AUDIOS INSERIR......
            AtendimentoAudios atendimentoAudios = new AtendimentoAudios(callId, this.pabx.getTipo(), this.usuario.getPontoAtendimento().getRamal(), new Atendimento(idAtendimento));
            atendimentoAudios.setData(new Date());
            //	inserir((Serializable)atendimentoAudios);

            Atendimento atendimentoPreditivo;

            if ((atendimentoPreditivo = this.serviceAtendimento.pesquisarAtendimentoValidacaoPreditivo(this.idAtendimento)) != null) {

                this.serviceAtendimentoAudios.salvarAtendimentoAudio(callId, this.idAtendimento, this.pabx, this.usuario.getPontoAtendimento().getRamal(), new Date(), fila, telefone);

                if (atendimentoPreditivo.getStatus() != null && !AcaoStatusAtendimentoEnum.FIM_FILA.equals(atendimentoPreditivo.getStatus().getAcao())) {

                    System.err.println("Atendimento já enviado pelo discador: [ " + this.idAtendimento + " ] | CPF: " + atendimentoPreditivo.getCpf() + " | TELEFONE: " + Faces.getRequestParameter("from"));

                    throw new ProativaException("Atendimento já enviado pelo discador [ ID: " + this.idAtendimento + " ] | CPF: " + atendimentoPreditivo.getCpf() + " | TELEFONE: " + Faces.getRequestParameter("from"));

                }

                this.numeroTelefone = StringUtils.isBlank(Faces.getRequestParameter("from")) ? "Não informado" : Faces.getRequestParameter("from");
                this.nomeCliente = StringUtils.isBlank(Faces.getRequestParameter("contact_name")) ? "Não informado" : Faces.getRequestParameter("contact_name");
                this.mostrarTransferirAtendimento = true;
                this.destruir = false;
                onIndisponivelDiscador();

            } else {

                throw new ProativaException("Atendimento não localizado: [ " + this.idAtendimento + " ]");

            }


        } catch (ProativaException e) {

            this.destruir = false;

            if (!e.getMessage().startsWith("Atendimento já enviado pelo discador [ ID: "))
                e.printStackTrace();

            Messages.addGlobalError(e.getMessage());

        } catch (Exception e) {

            this.destruir = false;
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante);

        }

    }


    public void onEnviarAtendimento3c() {

        try {
            this.idAtendimento = null;
            String idAtendimento;
            String fila;
            this.callid = null;
            String telefone;
            String qualify;
            String audioGravacao;
            String nome = Faces.getRequestParameter("contact_name");

            this.destruir = false;

            idAtendimento = Faces.getRequestParameter("idAtendimento");

            if (StringUtils.isBlank(idAtendimento))
                throw new ProativaException("Atendimento não enviado.");

            this.idAtendimento = Long.parseLong(idAtendimento);

            fila = Faces.getRequestParameter("queue");

            this.callid = Faces.getRequestParameter("call_id");

            audioGravacao = Faces.getRequestParameter("audio_gravacao");

            qualify = Faces.getRequestParameter("qualify");

            telefone = Faces.getRequestParameter("from");

            this.qualify = qualify;

            // System.out.println("NOME: "+nome+" | ID: "+idAtendimento+" | AUDIO_GRAVAÇÃO "+this.callid +" | "+audioGravacao +" | "+telefone+" | "+fila+" | " );

            // CALL ID ATENDIMENTO AUDIOS INSERIR......
            AtendimentoAudios atendimentoAudios = new AtendimentoAudios(audioGravacao, this.pabx.getTipo(), this.usuario.getPontoAtendimento().getRamal(), new Atendimento(idAtendimento));
            atendimentoAudios.setData(new Date());
            Atendimento atendimentoPreditivo;

            if ((atendimentoPreditivo = this.serviceAtendimento.pesquisarAtendimentoValidacaoPreditivo(this.idAtendimento)) != null) {

                this.serviceAtendimentoAudios.salvarAtendimentoAudio(this.callid, audioGravacao, this.idAtendimento, this.pabx, this.usuario.getPontoAtendimento().getRamal(), new Date(), fila, telefone);

                if (atendimentoPreditivo.getStatus() != null && !AcaoStatusAtendimentoEnum.FIM_FILA.equals(atendimentoPreditivo.getStatus().getAcao())) {

                    System.err.println("Atendimento já enviado pelo discador: [ " + this.idAtendimento + " ] | CPF: " + atendimentoPreditivo.getCpf() + " | TELEFONE: " + Faces.getRequestParameter("from"));

                    throw new ProativaException("Atendimento já enviado pelo discador [ ID: " + this.idAtendimento + " ] | CPF: " + atendimentoPreditivo.getCpf() + " | TELEFONE: " + Faces.getRequestParameter("from"));

                }

                this.numeroTelefone = StringUtils.isBlank(telefone) ? "Não informado" : telefone;
                this.nomeCliente = StringUtils.isBlank(nome) ? "Não informado" : nome;
                this.mostrarTransferirAtendimento = true;
                this.destruir = false;
                onIndisponivelDiscador();

            } else {

                throw new ProativaException("Atendimento não localizado: [ " + this.idAtendimento + " ]");

            }


        } catch (ProativaException e) {

            this.destruir = false;

            if (!e.getMessage().startsWith("Atendimento já enviado pelo discador [ ID: "))
                e.printStackTrace();

            Messages.addGlobalError(e.getMessage());

        } catch (Exception e) {

            this.destruir = false;
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante);

        }

    }

    public void onEnviarRamal() {
        try {

            String location = Faces.getRequestParameter("location");

            if (StringUtils.isNotEmpty(location) && this.pontoAtendimento != null && this.pontoAtendimento.getId() != null) {

                this.pontoAtendimento.setRamalAux(location);

                alterar(pontoAtendimento);

                retornarUsuarioSessao().getPontoAtendimento().setRamalAux(location);

                this.usuario.setPontoAtendimento(pontoAtendimento);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public void onDisponivelDiscador() {

        try {

            this.mostrarCronometro = true;

            if (this.pabx.getTipo().equals(TipoPabxEnum.VSPHONE)) {


                Map<String, String> retorno = this.serviceDiscadorUtil.ficarDisponivelDiscador(this.integracaoContactCenterVirtual, this.usuario);

                if (retorno != null && retorno.containsKey("retornoDisponivel")) {

                    this.mostrarCronometro = false;

                    this.menssagemDiscador = retorno.get("retornoDisponivel");

                    mostrarErro("Retorno Power Dialer: " + retorno.get("retornoDisponivel"));

                } else if (retorno != null && retorno.containsKey("retorno")) {

                    JSONObject obj = new JSONObject(retorno.get("retorno"));

                    if (!obj.isEmpty() && !obj.isNull("sucesso") && !obj.getBoolean("sucesso") && !obj.isNull("mensagem")) {


                        Map<String, String> retornoCriarUsuario = criarAgentDiscador(this.usuario);

                        if (retornoCriarUsuario.containsKey("retorno")) {

                            System.out.println("RETORNO USUARIO CRIADO: " + retornoCriarUsuario.get("retorno"));
                        }

                        mostrarErro("Retorno Power Dialer: " + retornoCriarUsuario.get("retorno"));


                    } else if (!obj.isEmpty() && !obj.isNull("sucesso") && obj.getBoolean("sucesso") && !obj.isNull("mensagem")) {

                        Messages.addGlobalInfo("Agent logado. ");

                        System.out.println("RESTORNO: " + obj.get("mensagem"));

                    }

                }
            } else {

                System.out.println("" + this.pabx.getTipo());
            }

        } catch (ProativaException e) {

            Messages.addGlobalFatal(e.getMessage());

        }

    }

    public void onIndisponivelDiscador() {

        try {

            this.serviceDiscadorUtil.ficarIndisponivelDiscador(this.integracaoContactCenterVirtual, this.usuario, "POS ATENDIMENTO PROATEND");

        } catch (ProativaException e) {

            e.printStackTrace();
            Messages.addGlobalError(e.getMessage());

        }

    }


    private Map<String, String> criarAgentDiscador(Usuario usuario) throws ProativaException {


        return this.discadorPowerDialer.criarAgent(usuario);
    }

    public String transferirAtendimento() {

        try {

            this.registro.registrarLog(this.idAtendimento, this.usuario, TipoEventoEnum.TRANSFERIU_ATENDIMENTO, "ATENDIMENTO TRANSFERIDO [PREDITIVO].");

            if (StringUtils.isNotBlank(this.numeroTelefone) && this.idAtendimento != null) {

                StatusTelefone statusTel = this.serviceStatusTelefone.pesquisarStatusTelefone(retornarEmpresaMatrizUsuarioSessao().getId(), "Entregue Pelo Discador");

                if (statusTel != null) {

                    this.serviceTelefone.alterarStatusTelefone(this.idAtendimento, this.numeroTelefone, statusTel.getId());

                }

            }


            Faces.setFlashAttribute("idAtendimento", this.idAtendimento);
            Faces.setFlashAttribute("qualify", this.qualify);
            Faces.setFlashAttribute("callid", this.callid);
            Faces.setFlashAttribute("preditivo", Boolean.TRUE);
            return PagesEnum.ATIVO.getConstante() + PagesEnum.REDIRECT.getConstante();

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(e.getMessage());

        }

        return null;
    }

    public void test() {

        this.atendimento = serviceAtendimento.pesquisarAtendimentoPorId(Long.valueOf("10008295"));
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.put("atendimento", this.atendimento);
        Faces.redirect("/crmproativa/pages/cadastro/fichaAtendimento.jsf");

    }


    public String retornarFilas() {

        if (usuario != null && usuario.getPontoAtendimento() != null && usuario.getPontoAtendimento().getPabx() != null
                && usuario.getRespostaLoginPowerDialer() != null
                && CollectionUtils.isNotEmpty(usuario.getRespostaLoginPowerDialer().getFilas())) {

            StringBuilder builder = new StringBuilder();

            for (String filas : this.usuario.getRespostaLoginPowerDialer().getFilas()) {

                builder.append(filas).append(" , ");
            }


            return StringUtils.isBlank(builder.toString()) ? "Nenhuma fila associada." : builder.toString();


        } else {

            return "nenhuma fila associada";
        }


    }

    public void onPausaVonix() {

        String codigoAtendimento = Faces.getRequestParameter("codigoAtendimento");

        if (StringUtils.isNotBlank(codigoAtendimento) && this.serviceAtendimento.verificarExistenciaAtendimento(Long.parseLong(codigoAtendimento))) {

            PrimeFaces.current().executeScript("onChangePause(9);");
        }
    }

    private void validarExpediente() throws SessaoInvalidaException, ParseException {

        if (this.usuario != null && this.usuario.getExpediente() != null && TipoAcessoEnum.ATIVO.equals(this.usuario.getExpediente().getAcesso()) && this.usuario.getExpediente().getListDiasDaSemana() != null
                && !this.usuario.getExpediente().getListDiasDaSemana().isEmpty()) {

            Map<Integer, ExpedienteDiaSemana> listaDiasSemana = new HashMap<>();

            for (ExpedienteDiaSemana diaSemana : this.usuario.getExpediente().getListDiasDaSemana()) {

                listaDiasSemana.put(diaSemana.getId().getDiaSemana().getDiaSemana(), diaSemana);

            }

            Date dateHoraServidor = pesquisarDataCorrenteServidor();

            Calendar hoje = Calendar.getInstance();
            hoje.setTime(dateHoraServidor);
            int diaSemana = hoje.get(Calendar.DAY_OF_WEEK);

            if (!listaDiasSemana.containsKey(diaSemana)) {

                throw new SessaoInvalidaException(MessagesEnum.EXPEDIENTE_ENCERROU.constante);
            }

            Date horaServidor = DateUtil.builder(dateHoraServidor).retornarHora().getData();

            ExpedienteDiaSemana diaSemanaExpediente = listaDiasSemana.get(diaSemana);
            Date horaEntrada = diaSemanaExpediente.getHoraEntrada();
            Date horaSaida = diaSemanaExpediente.getHoraSaida();

            if (horaEntrada == null || horaSaida == null || horaServidor.before(horaEntrada)
                    || horaServidor.after(horaSaida)) {

                throw new SessaoInvalidaException(MessagesEnum.EXPEDIENTE_ENCERROU.constante);

            }
        }
    }

    private void validarPausa() throws ProativaException {

        ControlePausa controlePausa = this.serviceControlePausa.pesquisarControlePausaPorUsuario(this.usuario, new Date(System.currentTimeMillis()));

        if (controlePausa == null) {
            return;
        }

        if (controlePausa.getPausa().getTempo() == null || DateUtil.builder(controlePausa.getDataCadastro(), new Date()).calcularDiferencaDatas(DataEnum.SEGUNDO).getDataNumerico().intValue() <= controlePausa.getPausa().getTempo().intValue() * 60) {

            controlePausa.setDataRetorno(new Date(System.currentTimeMillis()));

            alterarGenerico(controlePausa);

        } else {

            throw new ProativaException(MessagesEnum.TEMPO_EXEDIDO.constante);

        }
    }

    public void salvarCampanha3c() {

        if (this.idCampanha3c != null) {

            try {

                this.tresCPlusServiceUtil.logarAgenteCampanha(this.integracaoEmpresa, this.idCampanha3c, this.pontoAtendimento.getApiToken());

                //     retornarUsuarioSessao().setIdCampanha3c(this.idCampanha3c);

            } catch (ProativaException e) {
                System.err.println(e.getMessage());
                Messages.addGlobalError(e.getMessage());
            }

            retornarUsuarioSessao().setIdCampanha3c(this.idCampanha3c);
        }

    }

    public void onFicarDisponivelArgus() {
        try {
            System.out.println("PAUSANDO...");
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    public Long getCod() {
        return cod;
    }

    public void setCod(Long cod) {
        this.cod = cod;
    }

    public PausaVonixEnum[] getPausaVonix() {
        return PausaVonixEnum.values();
    }

    public List<CartaoSaqueComplementarBmg> getListCatoesBmg() {
        return listCatoesBmg;
    }

    public void setListCatoesBmg(List<CartaoSaqueComplementarBmg> listCatoesBmg) {
        this.listCatoesBmg = listCatoesBmg;
    }

    public PontoAtendimento getPontoAtendimento() {
        return pontoAtendimento;
    }


    public boolean isMostrarTransferirAtendimento() {
        return mostrarTransferirAtendimento;
    }

    public void setPontoAtendimento(PontoAtendimento pontoAtendimento) {
        this.pontoAtendimento = pontoAtendimento;
    }

    public Pabx getPabx() {
        return pabx;
    }

    public void setPabx(Pabx pabx) {
        this.pabx = pabx;
    }

    public boolean isDestruir() {
        return destruir;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public boolean isCarregarConteudo() {
        return carregarConteudo;
    }

    public void setCarregarConteudo(boolean carregarConteudo) {
        this.carregarConteudo = carregarConteudo;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getUrlPwd() {
        return urlPwd;
    }

    public boolean isMostrarCronometro() {
        return mostrarCronometro;
    }

    public String getMenssagemDiscador() {
        return menssagemDiscador;
    }

    public String getQualify() {
        return qualify;
    }

    public void setQualify(String qualify) {
        this.qualify = qualify;
    }

    public List<Campanha> getListCampanha3c() {
        return listCampanha3c;
    }

    public void setListCampanha3c(List<Campanha> listCampanha3c) {
        this.listCampanha3c = listCampanha3c;
    }

    public Integer getIdCampanha3c() {
        return idCampanha3c;
    }

    public void setIdCampanha3c(Integer idCampanha3c) {
        this.idCampanha3c = idCampanha3c;
    }

    public String getRamal() {
        return ramal;
    }

    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

    public String getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(String codCliente) {
        this.codCliente = codCliente;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setUrlPwd(String urlPwd) {
        this.urlPwd = urlPwd;
    }
}
