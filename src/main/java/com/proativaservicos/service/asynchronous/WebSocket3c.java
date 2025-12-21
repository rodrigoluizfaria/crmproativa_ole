package com.proativaservicos.service.asynchronous;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.*;
import com.proativaservicos.service.*;
import com.proativaservicos.service.api.Call3c;
import com.proativaservicos.service.api.MongoDB;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.Utils;
import com.proativaservicos.util.constantes.*;
import io.socket.client.IO;
import io.socket.client.Manager;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import io.socket.engineio.client.transports.WebSocket;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.*;
import jakarta.inject.Inject;
import jakarta.transaction.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.json.JSONObject;

import java.io.Serializable;
import java.net.URI;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*@TransactionManagement(TransactionManagementType.BEAN)
@Startup
@Singleton*/
public class WebSocket3c implements Serializable {


    private String alterar;

    private boolean status;

    private IntegracaoWs integracaoWs;

    @Inject
    private IntegracaoService integracaoService;

    @Inject
    private EmpresaService serviceEmpresa;

    @Inject
    private UsuarioService serviceUsuario;

    @Inject
    private StatusTelefoneService serviceStatusTelefone;

    private Socket socket;


    private MongoDB mongoDB;

    private Usuario usuario;

    @EJB
    private StatusAtendimentoService serviceStatusAtendimento;

    @EJB
    private AtendimentoService serviceAtendimento;

    @EJB
    private TelefoneService serviceTelefone;
    @EJB
    private HistoricoAtendimentoService serviceHistorico;

    @EJB
    private ChamadasAtendimentoService serviceChamadasAtendimentos;

    @Resource
    private UserTransaction transaction;

    @PostConstruct
    public void init() {
/*
        atualizarIntegracao(null);

        if (this.integracaoWs != null && this.integracaoWs.getHabilitarWebsocket() != null && this.integracaoWs.getHabilitarWebsocket()) {

            start(this.integracaoWs);
            status = true;
            this.usuario = retornarUsuario();
            System.out.println("INICIANDO SOKET 3C: ID: " + this.socket.id() + " | Ativo: " + socket.isActive());
        }*/

    }

    public String retornarStatus() {

        String conectado = (this.socket != null && this.socket.connected()) ? "Conectado" : "Desconectado";
        String ativo = (this.socket != null && this.socket.isActive()) ? "Ativo" : "Desativado";
        return "Status do socket: " + conectado + " e " + ativo+" | token:  "+this.integracaoWs.getToken();
    }

    public boolean emExecucao() {

        return (this.socket != null && this.socket.connected() && socket.isActive());


    }

    public void stop() {

        System.out.println("PARANDO: " + status);

        this.status = false;

        try {

            this.socket.close();
            this.socket.disconnect();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void start(IntegracaoWs integracaoWs) {


        if (this.socket != null && this.socket.connected() && this.socket.isActive())
            stop();


        if (this.socket == null || !socket.connected() || !socket.isActive())
            iniciandoWebSocket(integracaoWs);

        this.status = true;
        this.usuario = retornarUsuario();

    }

    public boolean isIntegracaoIsActive(Long id) {

        if (integracaoWs == null || socket == null)
            return false;

        return (integracaoWs.getId().equals(id) && socket.connected() && socket.isActive());

    }


    private void iniciandoWebSocket(IntegracaoWs integracaoWs) {

        this.integracaoWs = integracaoWs;

        if (integracaoWs != null && StringUtils.isNotBlank(integracaoWs.getToken()) && integracaoWs.getHabilitarWebsocket()) {

            URI uri = URI.create("https://socket.3c.fluxoti.com");
            Map<String, String> map = new HashMap<>();
            map.put("transports", "['websocket']");
            map.put("token", integracaoWs.getToken());
            IO.Options options = IO.Options.builder().setTransports(new String[]{WebSocket.NAME}).setQuery("token=" + integracaoWs.getToken()).build();

            this.socket = IO.socket(uri, options);

            System.out.println("LISTEM...");
            System.out.println("SOCKET: " + integracaoWs.getToken());

            socket.io().on(Manager.EVENT_TRANSPORT, new Emitter.Listener() {
                @Override
                public void call(Object... objects) {

                }
            });
            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... objects) {

                    conectarMongDb();

                }
            });

            socket.on("call-history-was-created", objects -> {

                Document document = retornarDocumentoMongo3c(objects[0].toString());

                if (document != null) {

                    mongoDB.inserirDoc(document);
                }
                if (!status)
                    stop();

            });

            socket.on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    System.out.println("ERRO ao conectar: " + objects[0]);
                }

            });

            System.out.println("Conectando...");
            socket.open();

        }

    }

    private void conectarMongDb() {

        System.out.println("Conectado.");
        Logger logger = Logger.getLogger("org.mongodb.driver");
        logger.setLevel(Level.SEVERE);
        mongoDB = new MongoDB("calls_3c", "call");
    }

    private Document retornarDocumentoMongo3c(String json) {

        if (StringUtils.isBlank(json))
            return null;

        Document document = null;
        JSONObject jsonObjectRoot = new JSONObject(json);
        JSONObject jsonObject = jsonObjectRoot.getJSONObject("callHistory");
        String callMode = jsonObject.isNull("call_mode") ? "" : jsonObject.getString("call_mode");
        JSONObject jsonMailingData = jsonObject.isNull("mailing_data") ? null : jsonObject.getJSONObject("mailing_data");

        if (StringUtils.isNotBlank(callMode) && callMode.equals("dialer") && jsonMailingData != null) {

            document = new Document();

            int status = jsonObject.getInt("status");
            String identifi = jsonMailingData.getString("identifier");
            String number = jsonObject.getString("number");
            int speakingTime = jsonObject.getInt("speaking_time");
            String callId = jsonObject.getString("_id");
            Integer fila = jsonMailingData.getInt("company_id");
            Integer listId = jsonMailingData.getInt("list_id");
            String data = jsonObject.isNull("call_date") ? null : jsonObject.getString("call_date");
            String sip = jsonObjectRoot.getJSONObject("hangupCause").getString("sip");
            String hangupCauseText = jsonObjectRoot.getJSONObject("hangupCause").getString("text");
            Integer hangupCause = jsonObjectRoot.getJSONObject("hangupCause").getInt("id");

            //System.out.println("Data: " + jsonObject.getString("call_date") + " | ID: " + identifi + " | callID: " + callId + " | call mode: " + callMode + " | number: " + number + " | Status: " + status + " | SIP: " + sip);

            document.append("identifier", identifi).append("call_id", callId).append("number", number).append("call_date", data)
                    .append("status", status).append("status_text", retornarStatusTelefone3c(status)).append("fila", fila).append("data_discagem", data).append("sip", sip).append("call_mode", callMode)
                    .append("speaking_time", speakingTime).append("date_create", new Date()).append("list_id", listId).append("hangup_cause", hangupCause)
                    .append("hangup_text", hangupCauseText)
                    .append("processado", false);
        }

        return document;
    }

    private void atualizarIntegracao(Long id) {

        if (id != null && integracaoWs != null && !integracaoWs.getId().equals(id))
            this.integracaoWs = this.integracaoService.pesquisarIntegracoes(id);
        else
            this.integracaoWs = this.integracaoService.pesquisarIntegracoes(TipoIntegracaoEnum.TRES_CPLUS, null, TipoAcessoEnum.ATIVO);

    }

    @Schedule(hour = "*", minute = "*/20", persistent = false)
    private void processandoLigacao3c() {

       /* Logger logger = Logger.getLogger("org.mongodb.driver");
        logger.setLevel(Level.SEVERE);
        System.out.println("Pesquisando dados do discador 3C+");

        MongoDB db = new MongoDB("calls_3c", "call");
        List<Call3c> list = db.retornarListaDoc("processado", false, "call", "calls_3c");

        if (CollectionUtils.isNotEmpty(list) && this.status) {

            try {

                System.out.println("Processando dados Discador 3C+. total contatos: " + list.size());

                this.transaction.begin();
                Empresa empresa = retornarEmpresa();
                StatusAtendimento statusAtendimentoDiscador = retornarStatusAtendimento(empresa);
                List<StatusTelefone> listStatusTel = new ArrayList<>();

                int i = 0;

                for (Call3c call : list) {

                    Date dataAlteracao = new Date();

                    if (call.getId() == null || StringUtils.isBlank(call.getIdentifier()) || !Utils.isInteiro(call.getIdentifier()) || call.getStatus() == null || empresa == null)
                        continue;

                    Atendimento atendimento = this.serviceAtendimento.pesquisarAtendimentoPorIdSemDetalhar(Long.parseLong(call.getIdentifier()));

                    if (atendimento != null && atendimento.getId() != null) {

                        Telefone telefone = this.serviceTelefone.pesquisarTelefonePorDddNumero(atendimento.getId(), call.getNumeroFormatado().substring(0, 2), call.getNumeroFormatado().substring(2));

                        if (telefone != null && telefone.getStatusTelefone() == null) {

                            Optional<StatusTelefone> statusOpcao = listStatusTel.stream()
                                    .filter(s -> s.getDescricao().equalsIgnoreCase(retornarStatusTelefone3c(call.getStatus())))
                                    .findFirst();

                            StatusTelefone status;

                            if (statusOpcao.isEmpty()) {

                                status = criarStatusTelefone(retornarStatusTelefone3c(call.getStatus()), empresa);
                                listStatusTel.add(status);

                            } else {

                                status = statusOpcao.get();
                            }

                            telefone.setStatusTelefone(status);

                            this.serviceTelefone.alterarBatch(telefone, i);

                        }

                        i++;

                        if (StringUtils.isNotBlank(call.getCallDate()))
                            dataAlteracao = DateUtil.builder(call.getCallDate()).formatarDataParaISO8601().getData();

                        ChamadasAtendimento chamadasAtendimento = new ChamadasAtendimento();
                        chamadasAtendimento.setAtendimento(atendimento);
                        chamadasAtendimento.setTelefone(telefone);
                        chamadasAtendimento.setDataDiscagem(dataAlteracao);
                        chamadasAtendimento.setDataAtendida(dataAlteracao);
                        chamadasAtendimento.setDataHangup(dataAlteracao);
                        chamadasAtendimento.setFila(call.getFila().toString());
                        chamadasAtendimento.setCodStatusTelefone(call.getStatus());
                        chamadasAtendimento.setDescricao(call.getCallId());
                        chamadasAtendimento.setPabx(this.integracaoWs);
                        chamadasAtendimento.setDestino(call.getNumeroFormatado());
                        chamadasAtendimento.setDuracao(call.getSpeakingTime() == null ? null : Long.parseLong(call.getSpeakingTimeString()));
                        this.serviceChamadasAtendimentos.alterarBatch(chamadasAtendimento, i);

                        if (atendimento.getStatus() == null) {

                            if (statusAtendimentoDiscador.getId() != null) {

                                String fila = call.getFila() != null ? ("Trabalhado pelo discador | Fila: " + call.getFila().toString() + ((call.getListId() != null) ? " | Lista : " + call.getListId().toString() : "")) + " | Status: " + retornarStatusTelefone3c(call.getStatus()) : "Trabalhado pelo discador.";

                                Integer quantidadeDiscado = atendimento.getQuantidadeDiscagem() != null ? (Integer.valueOf(atendimento.getQuantidadeDiscagem() + 1)) : Integer.valueOf(1);

                                this.serviceAtendimento.atualizarAtendimentoResultsVonix(atendimento.getId(), statusAtendimentoDiscador.getId(), dataAlteracao, quantidadeDiscado);
                                this.serviceHistorico.inserirHistoricoAtendimento(atendimento.getId(), statusAtendimentoDiscador.getId(), this.usuario.getId(), dataAlteracao, dataAlteracao, fila);
                            }

                        }

                        if (i > 0 && i % 50 == 0) {

                            this.transaction.commit();
                            this.transaction.begin();

                        }


                    }

                    db.alterarDocById("processado", true, call.getId().toString());
                }

                this.transaction.commit();
                System.out.println("Fim processamento Discador 3C+");

            } catch (Exception e) {

                if (e instanceof ProativaException) {

                    System.err.println("ERRO PROCESSAR: " + e.getMessage());

                } else {


                    System.err.println("Erro.. buscar dados 3C+. " + e.getMessage());
                }
            }


        } else {

            System.out.println("Nenhum dado para processar. [Discador 3C+]");
        }*/

    }


    private String retornarStatusTelefone3c(int status) {

        switch (status) {
            case 1:
                return "Discando";
            case 2:
                return "Atendida";
            case 3:
                return "Conectada";
            case 4:
                return "Encerrada";
            case 5:
                return "Não atendida";
            case 6:
                return "Abandonada";
            case 7:
                return "Finalizada";
            case 8:
                return "Falha";
            case 9:
                return "Caixa postal pós atendimento";
            case 15:
                return "Caixa postal pré atendimento";

            default:
                return null;


        }

    }

    private StatusTelefone criarStatusTelefone(String strStatus, Empresa empresa) throws Exception {

        StatusTelefone status = this.serviceStatusTelefone.pesquisarStatusTelefone(empresa.getId(), strStatus);

        if (status == null) {

            status = new StatusTelefone(strStatus);
            status.setParametro(AcaoStatusTelefoneEnum.CONTATO_CLIENTE);
            status.setAtivo(TipoAcessoEnum.ATIVO);
            status.setEmpresa(empresa);
            status.setDataCadastro(new Date(System.currentTimeMillis()));
            status.setDataAlteracao(new Date(System.currentTimeMillis()));
            status.setUsuarioCadastro(usuario);
            status.setUsuarioAlteracao(usuario);
            this.serviceStatusTelefone.inserir(status);
            this.transaction.commit();
            this.transaction.begin();
        }

        return status;
    }


    private Empresa retornarEmpresa() {

        if (this.integracaoWs == null)
            atualizarIntegracao(null);

        if (this.integracaoWs == null)
            return null;

        return this.serviceEmpresa.pesquisarMatriz(integracaoWs.getEmpresa().getId());

    }

    private Usuario retornarUsuario() {
        return this.serviceUsuario.pesquisarUsuario(Long.valueOf(1));
    }

    private StatusAtendimento retornarStatusAtendimento(Empresa empresa) throws Exception {

        StatusAtendimento statusAtendimentoContatoDiscador = this.serviceStatusAtendimento.pesquisarStatusAtendimentoPorDescricao("TRABALHADO DISCADOR");

        if (statusAtendimentoContatoDiscador == null) {

            statusAtendimentoContatoDiscador = new StatusAtendimento();
            statusAtendimentoContatoDiscador.setUsuarioCadastro(usuario);
            statusAtendimentoContatoDiscador.setUsuarioAlteracao(usuario);
            statusAtendimentoContatoDiscador.setAcao(AcaoStatusAtendimentoEnum.FIM_FILA);
            statusAtendimentoContatoDiscador.setAtivo(TipoAcessoEnum.ATIVO);
            statusAtendimentoContatoDiscador.setEmpresa(empresa);
            statusAtendimentoContatoDiscador.setDescricao("TRABALHADO DISCADOR");
            statusAtendimentoContatoDiscador.setDataCadastro(new Date());
            statusAtendimentoContatoDiscador.setDataCadastro(new Date());
            this.serviceStatusAtendimento.inserir(statusAtendimentoContatoDiscador);
            this.transaction.commit();
            this.transaction.begin();
        }
        return statusAtendimentoContatoDiscador;
    }


    public String getAlterar() {
        return alterar;
    }

    public void setAlterar(String alterar) {
        this.alterar = alterar;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public IntegracaoWs getIntegracaoWs() {
        return integracaoWs;
    }

    public void setIntegracaoWs(IntegracaoWs integracaoWs) {
        this.integracaoWs = integracaoWs;
    }
}
