package com.proativaservicos.menssagens;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.*;
import com.proativaservicos.service.*;
import com.proativaservicos.service.api.MongoDB;
import com.proativaservicos.service.asynchronous.*;
import com.proativaservicos.util.*;
import com.proativaservicos.util.constantes.*;
import jakarta.annotation.Resource;
import jakarta.ejb.*;
import jakarta.inject.Inject;
import jakarta.jms.JMSException;
import jakarta.jms.MapMessage;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.transaction.UserTransaction;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.bson.Document;
import org.json.JSONObject;

import java.io.*;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.*;


@TransactionManagement(TransactionManagementType.BEAN)
@MessageDriven(name = "ImportacaoMsgAsynchronous", activationConfig = {
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/queue/ImportacaoMsgAsynchronous"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue"),
        @ActivationConfigProperty(propertyName = "maxSession", propertyValue = "1"),
        @ActivationConfigProperty(propertyName = "transactionTimeout", propertyValue = "300"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class ImportacaoMsgAsynchronous implements MessageListener, Serializable {

    @Resource
    private UserTransaction transacao;

    @EJB
    private ServiceAbstract serviceAbstract;

    @EJB
    private AtendimentoService serviceAtendimento;

    @EJB
    private ImportacaoService serviceImportacao;

    @EJB
    private EmpresaService serviceEmpresa;

    @EJB
    private CampanhaService serviceCampanha;

    @EJB
    private StatusTelefoneService serviceStatusTelefone;

    @Inject
    private ProdutoService serviceProduto;

    @Inject
    private BlackListService serviceBlackList;

    @Inject
    private BlackListTelefoneService serviceBlackListTelefone;

    private MongoDB mongoDB;

    @EJB
    private ConsultaAssincronaSaqueBmg consultaSaque;

    @EJB
    private ConsultaAssincronaMasterSaqueExecutor consultaSaqueMaster;

    @EJB
    private ConsultaAssincronaSeguro consultaSeguro;

    @EJB
    private ConsultaAssincronaRefinBmg consultaRefin;

    @Inject
    private ConsultaAssincronaApiPanSimulacao consultaSimulacaoPan;

    @Inject
    private ConsultaAssincronaCartaoBeneficio consultaBeneficio;

    @Inject
    private DiscadorUtil discadorUtil;

    @Inject
    private RegistroSistemaUtil registro;

    @Inject
    private AgendadorConsultaWebservice agendadorRefin;

    private List<Integer> listaIndexDDD;
    private List<Integer> listaIndexNumero;
    private List<Integer> listaIndexDDDNumero;
    private List<Integer> listaIndexStatusTelefone;

    private List<Integer> listaIndexLogradouro;
    private List<Integer> listaIndexNumeroLogradouro;
    private List<Integer> listaIndexComplemento;
    private List<Integer> listaIndexBairro;
    private List<Integer> listaIndexCidade;
    private List<Integer> listaIndexEstado;
    private List<Integer> listaIndexCep;

    private List<Integer> listaIndexBanco;
    private List<Integer> listaIndexAgencia;
    private List<Integer> listaIndexDvAgencia;
    private List<Integer> listaIndexConta;
    private List<Integer> listaIndexDvConta;
    private List<Integer> listaIndexTipoConta;
    private List<Integer> listaIndexEstadoBanco;

    private Integer indicePortabilidadeBancoOrigem;
    private Integer indicePortabilidadeSaldoDevedor;
    private Integer indicePortabilidadeValorParcela;
    private Integer indicePortabilidadePrazoTotal;
    private Integer indicePortabilidadePrazoRestante;
    private Integer indicePortabilidadeTaxaJuros;
    private Integer indicePortabilidadeDataAverbacao;
    private Integer indicePortabilidadeBeneficio;
    private Integer indicePortabilidadeEspecie;

    private List<Integer> listaIndexEmail;
    private List<Integer> listIndexInformacoesComplementares;

    private Integer indexCpf;

    private int indiceLinhaErro;

    private Importacao importacao;

    private Empresa empresa;

    private Usuario usuario;

    private Integer quantidadeTelefone;

    private Integer quantidadeEndereco;

    private Integer quantidadeDadosBancarios;

    private Integer quantidadeEmail;

    private Empresa empresaMatriz;

    private Map<String, StatusTelefone> mapStatusTelefones;
    private Map<String, Produto> mapProduto;

    private Set<String> listBlackList;

    private StringBuilder erros;

    private String objIdMongo;

    private Set<String> listBlackListTelefones;

    @Override
    public void onMessage(Message message) {

        try {

            MapMessage mapParametros = (MapMessage) message;

            Campanha campanha = this.serviceCampanha.pesquisarCampanha(Long.valueOf(mapParametros.getLong("idCampanha")), false);

            iniciarImportacao(mapParametros, campanha);


        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void iniciarImportacao(MapMessage mapMessage, Campanha campanha) {

        //Scanner scanner = null;
        BufferedReader scanner = null;

        try {

            this.erros = new StringBuilder();

            this.indiceLinhaErro = 0;

            this.transacao.begin();

            byte[] byteArquivo = mapMessage.getBytes("arquivo");

            String encoding = mapMessage.getString("encoding");

            scanner = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(byteArquivo), StandardCharsets.ISO_8859_1));
            //scanner = new Scanner(new ByteArrayInputStream(byteArquivo), "ISO-8859-1");

            inicializar(mapMessage, campanha);
            this.importacao.setAgendamentoConsulta(campanha.getAgendamento());
            this.importacao.setStatusImportacao(StatusImportacaoEnum.IMPORTANDO);

            this.serviceAbstract.alterar((Serializable) this.importacao);
            this.transacao.commit();
            this.transacao.begin();

            // HIGIENIZAR..... SERVIÇOS INTEGRACAO....
            System.out.println("Iniciando importação.... |  [ ARQUIVO: " + this.importacao.getNomeArquivo() + " ] | CAMPANHA: " + this.importacao.getCampanha().getDescricao());


            Type type = (new TypeToken<Map<Integer, DadosBaseImportacaoEnum>>() {
            }).getType();

            Map<Integer, DadosBaseImportacaoEnum> mapCabecalhoImportacao = (new Gson()).fromJson(mapMessage.getString("header"), type);

            identificarIndiceElementosLista(mapCabecalhoImportacao);

            validarImportacao(byteArquivo, mapCabecalhoImportacao);

            gerarLogImportacao(this.importacao, mapMessage.getString("header"), byteArquivo);

            String[] listCabecalho = scanner.readLine().split(";");
            String linha = null;
            int indexLinhaLote = 0;
            int linhaLog = 1;

            Map<String, Atendimento> mapAtendimentos = new LinkedHashMap<>();
            Set<String> mapCpfRepetidos = new TreeSet<>();

            while ((linha = scanner.readLine()) != null) {

                String[] dados = linha.replaceAll(";[ ]*", ";").split(";");

                if (this.indexCpf != null && StringUtils.isNotBlank(linha.replaceAll(";[ ]*", "")) && dados.length > this.indexCpf) {

                    String cpf = StringUtils.leftPad(dados[this.indexCpf].trim().replaceAll("\\D+", "").replaceAll(" ", ""), 11, "0");

                    if (StringUtils.isNotBlank(cpf)) {

                        Atendimento atendimento = mapAtendimentos.get(cpf);

                        if (atendimento == null && Boolean.TRUE.equals(campanha.getConsultaCpf())) {

                            GenericAtendimento atendimentoGenerico = this.serviceAtendimento.pesquisarAtendimento(cpf, this.empresa.getId());

                            if (atendimentoGenerico != null) {

                                atendimento = criarBaseAtendimento(atendimentoGenerico, campanha, this.usuario, this.importacao, this.empresa);

                            } else {

                                atendimento = new Atendimento();
                            }

                        } else if (atendimento == null) {

                            atendimento = new Atendimento();
                        }

                        atendimento.setCpf(cpf);
                        /* HIGIENIZAR BASE */

                        // Inserindo dados no Objeto Atendimento...
                        for (int index = 0; index < dados.length; index++) {

                            DadosBaseImportacaoEnum tipoDadosImportacao = mapCabecalhoImportacao.get(Integer.valueOf(index));

                            if (tipoDadosImportacao != null && this.indexCpf.intValue() != index && !tipoDadosImportacao.isLista()) {

                                converterDados((Serializable) atendimento, dados[index], tipoDadosImportacao);

                            }
                        }

                        if (atendimento.getDataCadastro() == null) {
                            atendimento.setDataCadastro(new Date(System.currentTimeMillis()));
                        }

                        atendimento.setUsuarioCadastro(this.usuario);
                        atendimento.setUsuarioAlteracao(this.usuario);
                        atendimento.setImportacao(this.importacao);
                        atendimento.setEmpresa(this.empresa);
                        atendimento.setCampanha(campanha);
                        atendimento.setInstituicaoFinanceira(campanha.getInstituicaoFinanceira());

                        /* condicao Ultimo contrato listNegra */

                        if (!this.listBlackList.contains(cpf)) {

                            criarTelefone(dados, atendimento);
                            criarEndereco(dados, atendimento);
                            criarDadosBancarios(dados, atendimento);
                            criarEmail(dados, atendimento);
                            criarDadosPortabilidade(dados, atendimento);
                            criarJasonInformacoesComplementares(dados, listCabecalho, atendimento);

                            if (!mapAtendimentos.containsKey(cpf)) {

                                this.serviceAbstract.inserirBatch(atendimento, indexLinhaLote++);

                                mapAtendimentos.put(cpf, atendimento);

                            } else {

                                mapCpfRepetidos.add(cpf);
                            }

                            if (indexLinhaLote > 0 && indexLinhaLote % 50 == 0) {

                                this.transacao.commit();

                                this.transacao.begin();

                                this.importacao = (Importacao) this.serviceAbstract.pesquisar(Importacao.class, this.importacao.getId());

                                if (this.importacao.getStatusImportacao().equals(StatusImportacaoEnum.NAO_IMPORTADA)) {

                                    break;

                                }

                            }

                            alterarLogMongoDb(null,null,0,null,linhaLog++);
                            continue;
                        }

                        mapAtendimentos.remove(cpf);
                        alterarLogMongoDb(null,null,0,cpf,null);
                    }


                }

            }

            this.transacao.commit();

            this.transacao.begin();

            List<String> listCpfsRepetidos = new ArrayList<>(mapCpfRepetidos);

            for (int i = 0; i < listCpfsRepetidos.size(); i++) {

                String cpf = listCpfsRepetidos.get(i);

                this.serviceAbstract.alterarBatch(mapAtendimentos.get(cpf), i);

                if (i % 50 == 0) {
                    this.transacao.commit();
                    this.transacao.begin();
                }

            }
            this.transacao.commit();

            this.transacao.begin();
            this.importacao.setStatusImportacao(StatusImportacaoEnum.IMPORTADA);
            this.importacao.setQtidadeImportado(mapAtendimentos.size());
            this.serviceAbstract.alterar(this.importacao);
            this.transacao.commit();

            // ***** FAZER IMPORTACAO DISCADOR E CONSULTAS
            alterarLogMongoDb("Sucesso.",null,null,0,null,mapAtendimentos.size(),"IMPORTADO");

            if (this.importacao.getStatusImportacao().equals(StatusImportacaoEnum.NAO_IMPORTADA)) {

                System.out.println("Importação finalizada..... finalizada pelo usuario.....");


            } else if (campanha.getConsultaSaque().equals(Boolean.TRUE) && campanha.getIntegrarWs() != null && campanha.getIntegrarWs().getTipoIntegracao().getTipoConsultaEnum().equals(TipoConsultaEnum.SAQUE)) {
                // CONSULTA BMG E PAN --- PROVISORIO.

                if (campanha.getAgendarConsulta() == null || campanha.getAgendarConsulta().equals(Boolean.FALSE)) {

                    alterarStatusImportacao(StatusImportacaoEnum.IMPORTANDO_SAQUE);

                    if (campanha.getInstituicaoFinanceira().equals(InstituicaoFinanceiraEnum.PAN)) {

                        System.out.println("INICIANDO CONSULTA PAN...");
                        this.consultaSimulacaoPan.consultarSimulacaoSaque(this.importacao, campanha, this.usuario, this.empresa);

                    } else {

                        this.consultaSaque.consultarSaqueComplementar(this.importacao, campanha, this.usuario, this.empresa);

                    }

                } else if (campanha.getAgendarConsulta() != null || campanha.getAgendarConsulta().equals(Boolean.TRUE)) {

                    agendamentoConsulta(campanha, mapAtendimentos.size());

                }

            } else if (campanha.getConsultaSaque().equals(Boolean.TRUE) && campanha.getIntegrarWs() != null && campanha.getIntegrarWs().getTipoIntegracao().getTipoConsultaEnum().equals(TipoConsultaEnum.SAQUE_MASTER)) {
                // CONSULTA MASTER.

                if (campanha.getAgendarConsulta() == null || campanha.getAgendarConsulta().equals(Boolean.FALSE)) {

                    alterarStatusImportacao(StatusImportacaoEnum.IMPORTANDO_SAQUE);

                    this.consultaSaqueMaster.consultarSaque(this.importacao, campanha, this.usuario, this.empresa);


                } else if (campanha.getAgendarConsulta() != null || campanha.getAgendarConsulta().equals(Boolean.TRUE)) {

                    agendamentoConsulta(campanha, mapAtendimentos.size());

                }

            } else if (campanha.getConsultaSeguro().equals(Boolean.TRUE) && campanha.getIntegrarWs() != null && campanha.getIntegrarWs().getTipoIntegracao().getTipoConsultaEnum().name().startsWith("SEGURO")) {

                System.out.println("CONSULTANDO SEGURO  " + this.importacao.getNomeArquivo());

                if (campanha.getAgendarConsulta() == null || campanha.getAgendarConsulta().equals(Boolean.FALSE)) {

                    alterarStatusImportacao(StatusImportacaoEnum.IMPORTANDO_SEGURO);
                    this.consultaSeguro.consultarProdutoSeguro(this.importacao, campanha, this.usuario, this.empresa);

                } else if (campanha.getAgendarConsulta() != null || campanha.getAgendarConsulta().equals(Boolean.TRUE)) {
                    agendamentoConsulta(campanha, mapAtendimentos.size());
                }

            } else if (campanha.getConsultarRefin().equals(Boolean.TRUE) && campanha.getIntegrarWs() != null && campanha.getIntegrarWs().getTipoIntegracao().getTipoConsultaEnum().equals(TipoConsultaEnum.REFIN)) {

                System.out.println("CONSULTANDO REFIN ");


                this.importacao.setCodTabelaRefin(mapMessage.getString("idCodRefin") == null ? null : Integer.valueOf(mapMessage.getString("idCodRefin")));

                if (campanha.getAgendarConsulta() == null || campanha.getAgendarConsulta().equals(Boolean.FALSE)) {

                    alterarStatusImportacao(StatusImportacaoEnum.IMPORTANDO_REFIN);
                    this.consultaRefin.consultarProdutoRefin(this.importacao, campanha, usuario, empresa);

                } else if (campanha.getAgendarConsulta() != null || campanha.getAgendarConsulta().equals(Boolean.TRUE))
                    agendamentoConsulta(campanha, mapAtendimentos.size());


            } else if (campanha.getConsultarCartaoBeneficio().equals(Boolean.TRUE) && campanha.getIntegrarWs() != null && campanha.getIntegrarWs().getTipoIntegracao().getTipoConsultaEnum().equals(TipoConsultaEnum.CARTAO_BENEFICIO)) {

                System.out.println("CONSULTANDO CARTÃO BENEFICIO ");

                if (campanha.getAgendarConsulta() == null || campanha.getAgendarConsulta().equals(Boolean.FALSE)) {

                    alterarStatusImportacao(StatusImportacaoEnum.IMPORTANDO_REFIN);
                    this.consultaBeneficio.consultarRetornoSeTemCartao(this.importacao, campanha, usuario, empresa);

                } else if (campanha.getAgendarConsulta() != null || campanha.getAgendarConsulta().equals(Boolean.TRUE))
                    agendamentoConsulta(campanha, mapAtendimentos.size());


            } else {

                // OK FINALIZADA POSSIBILIDADE IMPORTAR DISCADOR
                String result = this.discadorUtil.subirCargaDiscador(this.empresa, campanha, mapAtendimentos.values());

                String msgLog = "Importada com Sucesso. ";

                if (StringUtils.isNotBlank(erros.toString())) {

                    msgLog = "Importada com Sucesso. mas aconteceu um erro: " + erros.toString();
                }

                this.transacao.begin();

                this.importacao = (Importacao) this.serviceAbstract.pesquisar(Importacao.class, Long.valueOf(mapMessage.getLong("idImportacao")));
                this.importacao.setDataFim(new Date(System.currentTimeMillis()));
                this.importacao.setQtidadeImportado(mapAtendimentos.size());
                this.importacao.setLog(msgLog + result);
                this.importacao.setStatusImportacao(StatusImportacaoEnum.IMPORTADA);

                System.out.println("Importação finalizada com sucesso.... |  [ ARQUIVO: " + this.importacao.getNomeArquivo() + " ] | CAMPANHA: " + this.importacao.getCampanha().getDescricao() + " | TOTAL IMPORTADO: " + this.importacao.getQtidadeImportado());

                this.serviceAbstract.alterar((Serializable) this.importacao);
                //	this.serviceImportacao.inserirImportacao(this.importacao);
                this.transacao.commit();

                this.registro.registrarLog(this.usuario.getId(), TipoEventoEnum.IMPORTACAO, "Importação finalizada na Campanha: " + campanha.getDescricao(), new Date());

                ArquivoUtil.geraLogCsv(new File(System.getProperty("user.home") + File.separator + "proativa" + File.separator + "importacao"), this.importacao.getId() + ";" + StatusImportacaoEnum.IMPORTADA + ";" + Integer.valueOf(mapAtendimentos.size()) + ";" + msgLog);
            }


        } catch (Exception e) {

            // TODO: handle exception

            // ERROS QUE OCORRERAM GRAVAR NO BANCO

            e.printStackTrace();

            try {

                this.transacao.rollback();

                if (this.importacao != null && this.importacao.getId() != null) {

                    // APAGAR ATENDIMENTOS DA IMPORTACAO
                    if (StatusImportacaoEnum.IMPORTADA.equals(this.importacao.getStatusImportacao())) {

                        this.transacao.begin();
                        this.serviceAtendimento.deletarAtendimentoPorImportacao(this.importacao.getId());
                        this.transacao.commit();

                    }

                    this.transacao.begin();
                    this.importacao.setStatusImportacao(StatusImportacaoEnum.IMPORTADA);
                    this.importacao.setLog("Erro na Importacao, linha: " + indiceLinhaErro + " - " + e.getMessage());
                    this.importacao.setQtidadeImportado(Integer.valueOf(0));
                    this.importacao.setDataFim(new Date(System.currentTimeMillis()));
                    this.serviceAbstract.alterar(this.importacao);
                    this.transacao.commit();
                    alterarLogMongoDb("Erro na Importacao, linha: " + indiceLinhaErro + " - " + e.getMessage(), null, indiceLinhaErro,null,null);
                }

            } catch (Exception e1) {
                e1.printStackTrace();
            }

        } finally {

            if (scanner != null) {

                try {

                    scanner.close();

                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }

    }

    private void agendamentoConsulta(Campanha campanha, Integer qtadeAtendimento) throws Exception {

        campanha.setImportacaoConsulta(this.importacao);
        this.importacao.setCampanha(campanha);
        this.importacao.setAgendamentoConsulta(campanha.getAgendamento());
        this.agendadorRefin.agendamentoConsultaRefin(this.importacao);

        this.importacao.setDataFim(new Date(System.currentTimeMillis()));
        this.importacao.setQtidadeImportado(qtadeAtendimento);
        this.importacao.setLog("Importada com sucesso, agendamento criado.");
        this.importacao.setStatusImportacao(StatusImportacaoEnum.IMPORTANDO_AGENDADO);
        alterarStatusImportacao(StatusImportacaoEnum.IMPORTANDO_AGENDADO);

    }

    private void inicializarTransacao() {

        try {
            if (this.transacao.getStatus() == 1) {
                this.transacao.rollback();
            }

            if (this.transacao.getStatus() != 0) {
                this.transacao.begin();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private void alterarStatusImportacao(StatusImportacaoEnum status) throws Exception {

        this.transacao.begin();
        this.importacao.setStatusImportacao(status);
        //this.registro.alterarImportacao(importacao);
        this.serviceAbstract.alterar((Serializable) this.importacao);
        this.transacao.commit();
    }

    // BASE PARA O ATENDIMENTO HIGI
    private Atendimento criarBaseAtendimento(GenericAtendimento atendimentoGenerico, Campanha campanha, Usuario usuario,
                                             Importacao importacao, Empresa empresa) {

        Atendimento atendimentoNovo = new Atendimento();

        atendimentoNovo.setId(null);
        atendimentoNovo.setAdesao(null);
        atendimentoNovo.setValorLiberado(null);
        atendimentoNovo.setQuantidadeParcela(null);
        atendimentoNovo.setValorParcela(null);
        atendimentoNovo.setStatus(null);
        atendimentoNovo.setUsuarioCadastro(usuario);
        atendimentoNovo.setUsuarioAlteracao(usuario);
        atendimentoNovo.setDataCadastro(new Date(System.currentTimeMillis()));
        atendimentoNovo.setDataAlteracao(new Date(System.currentTimeMillis()));
        atendimentoNovo.setUsuarioOcupado(null);
        atendimentoNovo.setContrato(null);
        atendimentoNovo.setDataInicioAtendimento(null);
        atendimentoNovo.setDataFimAtendimento(null);
        atendimentoNovo.setProtocolo(null);
        atendimentoNovo.setPesoDiscagem(null);
        atendimentoNovo.setPesoCarteira(null);
        atendimentoNovo.setPrioridade(null);
        atendimentoNovo.setDiscou(null);
        atendimentoNovo.setProtocolo(null);

        atendimentoNovo.setCampanha(campanha);
        atendimentoNovo.setEmpresa(empresa);
        atendimentoNovo.setBeneficioPrincipal(atendimentoGenerico.getBeneficioPrincipal());
        atendimentoNovo.setBeneficioSecundario(atendimentoGenerico.getBeneficioSecundario());
        atendimentoNovo.setCpf(atendimentoGenerico.getCpf());
        atendimentoNovo.setDescontoCompulsorio(atendimentoGenerico.getDescontoCompulsorio());
        atendimentoNovo.setDescontoFacultativo(atendimentoGenerico.getDescontoFacultativo());
        atendimentoNovo.setImportacao(importacao);
        atendimentoNovo.setLimite(atendimentoGenerico.getLimite());
        atendimentoNovo.setMargem(atendimentoGenerico.getMargem());
        atendimentoNovo.setDataNascimento(atendimentoGenerico.getDataNascimento());
        atendimentoNovo.setNome(atendimentoGenerico.getNome());
        atendimentoNovo.setOutrasInformacoes(atendimentoGenerico.getOutrasInformacoes());
        atendimentoNovo.setQuantidadeContratos(atendimentoGenerico.getQuantidadeContratos());
        atendimentoNovo.setRisco(atendimentoGenerico.getRisco());
        atendimentoNovo.setSalarioCliente(atendimentoGenerico.getSalarioCliente());
        atendimentoNovo.setSaldoDevedor(atendimentoGenerico.getSaldoDevedor());
        atendimentoNovo.setSeguro(atendimentoGenerico.getSeguro());
        atendimentoNovo.setSexo(atendimentoGenerico.getSexo());
        atendimentoNovo.setValorContrato(atendimentoGenerico.getValorContrato());

        atendimentoNovo.setValorLiberadoEmprestimo(atendimentoGenerico.getValorLiberadoEmprestimo());
        atendimentoNovo.setValorLiberadoRefinanciamento(atendimentoGenerico.getValorLiberadoRefinanciamento());
        atendimentoNovo.setValorMaxOperacao(atendimentoGenerico.getValorMaxOperacao());
        atendimentoNovo.setValorTotal(atendimentoGenerico.getValorTotal());
        atendimentoNovo.setEntidadePrincipal(atendimentoGenerico.getEntidadePrincipal());
        atendimentoNovo.setEntidadeSecundaria(atendimentoGenerico.getEntidadeSecundaria());
        atendimentoNovo.setOrgaoPrincipal(atendimentoGenerico.getOrgaoPrincipal());
        atendimentoNovo.setOrgaoSecundario(atendimentoGenerico.getOrgaoSecundario());
        atendimentoNovo.setProduto(atendimentoGenerico.getProduto());
        atendimentoNovo.setFormaPagamento(atendimentoGenerico.getFormaPagamento());
        atendimentoNovo.setNumeroDocumento(atendimentoGenerico.getNumeroDocumento());
        atendimentoNovo.setOrgaoDocumento(atendimentoGenerico.getOrgaoDocumento());
        atendimentoNovo.setNomeMae(atendimentoGenerico.getNomeMae());

        if (atendimentoNovo.getFormaPagamento() == null || atendimentoNovo.getFormaPagamento().getId() == null) {
            atendimentoNovo.setFormaPagamento(null);
        }

        if (atendimentoNovo.getProduto() == null || atendimentoNovo.getProduto().getId() == null) {
            atendimentoNovo.setProduto(null);
        }

        for (GenericTelefone genericTelefone : atendimentoGenerico.getListaTelefones()) {
            Telefone telefone = new Telefone();

            telefone.setId(null);
            telefone.setAtendimento(null);
            telefone.setStatusTelefone(genericTelefone.getStatusTelefone());
            telefone.setDdd(genericTelefone.getDdd());
            telefone.setNumero(genericTelefone.getNumero());
            telefone.setTipo(genericTelefone.getTipo());
            telefone.setAtende(genericTelefone.getAtende());
            telefone.setExibe(genericTelefone.getExibe());
            telefone.setWhatsapp(genericTelefone.getWhatsapp());

            atendimentoNovo.adicionarTelefone((GenericTelefone) telefone);
        }

        for (GenericDadosBancarios genericDadosBancarios : atendimentoGenerico.getListaDadosBancarios()) {

            DadosBancarios dadosBancarios = new DadosBancarios();

            dadosBancarios.setId(null);
            dadosBancarios.setAtendimento(null);
            dadosBancarios.setAgencia(genericDadosBancarios.getAgencia());
            dadosBancarios.setConta(genericDadosBancarios.getConta());
            dadosBancarios.setDigitoAgencia(genericDadosBancarios.getDigitoAgencia());
            dadosBancarios.setDigitoConta(genericDadosBancarios.getDigitoConta());
            dadosBancarios.setTipoConta(genericDadosBancarios.getTipoConta());
            dadosBancarios.setBanco(genericDadosBancarios.getBanco());
            dadosBancarios.setNomeBanco(genericDadosBancarios.getNomeBanco());

            boolean condicaoExistenciaDadosBancarios = jaPossuiDadosBancarios(atendimentoNovo, dadosBancarios);

            if (!condicaoExistenciaDadosBancarios) {
                atendimentoNovo.adicionarDadosBancarios((GenericDadosBancarios) dadosBancarios);
            }
        }

        for (GenericEmail genericEmail : atendimentoGenerico.getListaEmails()) {
            Email email = new Email();

            email.setId(null);
            email.setAtendimento(null);
            email.setDescricao(genericEmail.getDescricao());
            email.setEmail(genericEmail.getEmail());

            boolean condicaoExistenciaEmail = jaPossuiEmail(atendimentoNovo, email);

            if (!condicaoExistenciaEmail) {
                atendimentoNovo.adicionarEmail((GenericEmail) email);
            }
        }

        for (GenericEndereco genericEndereco : atendimentoGenerico.getListaEnderecos()) {
            Endereco endereco = new Endereco();

            endereco.setId(null);
            endereco.setAtendimento(null);
            endereco.setBairro(genericEndereco.getBairro());
            endereco.setCep(genericEndereco.getCep());
            endereco.setCidade(genericEndereco.getCidade());
            endereco.setComplemento(genericEndereco.getComplemento());
            endereco.setUf(genericEndereco.getUf());
            endereco.setLogradouro(genericEndereco.getLogradouro());
            endereco.setNumero(genericEndereco.getNumero());

            boolean condicaoExistenciaEndereco = jaPossuiEndereco(atendimentoNovo, endereco);

            if (!condicaoExistenciaEndereco) {
                atendimentoNovo.adicionarEndereco((GenericEndereco) endereco);
            }
        }
        atendimentoNovo.setListaCartoesCredito(null);

        return atendimentoNovo;

    }


    private boolean jaPossuiEmail(Atendimento atendimento, Email email) {

        boolean condicaoExistenciaEmail = CollectionUtils.exists(atendimento.getListaEmails(),
                new org.apache.commons.collections4.Predicate<Email>() {
                    public boolean evaluate(Email emailLista) {
                        if (emailLista.getEmail() != null && email.getEmail() != null
                                && emailLista.getEmail().equals(email.getEmail())) {
                            return true;
                        }
                        return false;
                    }
                });

        return condicaoExistenciaEmail;
        // TODO Auto-generated method stub

    }

    private void inicializar(MapMessage mapMessage, Campanha campanha) throws JMSException {

        // TODO Auto-generated method stub
        this.importacao = (Importacao) this.serviceAbstract.pesquisar(Importacao.class, Long.valueOf(mapMessage.getLong("idImportacao")));

        this.usuario = (Usuario) this.serviceAbstract.pesquisar(Usuario.class, Long.valueOf(mapMessage.getLong("idUsuario")));

        this.empresaMatriz = this.serviceEmpresa.pesquisarMatriz(importacao.getEmpresa().getId());

        this.empresa = (Empresa) this.serviceAbstract.pesquisar(Empresa.class, Long.valueOf(mapMessage.getLong("idEmpresa")));

        // LISTA NEGRA TELEFONES BLOQUEADOS....
        if (campanha.getSegmento() != SegmentoEnum.ASSOCIACAO) {
            this.listBlackList = this.serviceBlackList.pesquisarListaNegra(this.empresa.getId());
            this.listBlackListTelefones = this.serviceBlackListTelefone.pesquisarTelefonesBlackListManual(this.empresaMatriz.getId());
        } else {
            this.listBlackList = new TreeSet<>();
            this.listBlackListTelefones = new TreeSet<>();
        }

        this.mapStatusTelefones = new HashMap<String, StatusTelefone>();
        this.mapProduto = new HashMap<String, Produto>();

        List<StatusTelefone> listStatusTelefones = this.serviceStatusTelefone.pesquisarStatusTelefonesPorEmpresa(this.empresaMatriz.getId(), null);

        List<Produto> listProdutos = this.serviceProduto.pesquisarProdutoPorEmpresa(this.empresaMatriz.getId(), null);

        for (Produto produto : listProdutos) {

            this.mapProduto.put(produto.getDescricao().toUpperCase(), produto);

        }

        for (StatusTelefone statusTelefone : listStatusTelefones) {

            this.mapStatusTelefones.put(statusTelefone.getDescricao().toUpperCase(), statusTelefone);
        }

        iniciarLogMongoDb(campanha.getDescricao(), importacao.getNomeArquivo(), campanha.getId(), importacao.getId(), importacao.getQtidadeLinhas());

    }

    private void iniciarLogMongoDb(String campanha, String arquivo, Long idCampanha, Long idImportacao, Integer totalLinhas) {

        mongoDB = new MongoDB("importacao", "importacao_log");

        Document docImportacao = mongoDB.retornarPorImportacao(idImportacao);

        Document document = new Document();
        document.put("campanha", campanha);
        document.put("id_campanha", idCampanha);
        document.put("id_importacao", idImportacao);
        document.put("arquivo", arquivo);
        document.put("status", "IMPORTANDO");
        document.put("total_atendimentos", 0);
        document.put("total_linhas", totalLinhas);
        document.put("data_inicio", DateUtil.builder().converterDataUtcBrasil().getData());
        document.put("data_fim", null);
        document.put("finalizou", false);


        if (docImportacao != null) {
            this.objIdMongo = docImportacao.get("_id").toString();
            this.mongoDB.alterarDocById(objIdMongo, document);

        } else {
            this.objIdMongo = mongoDB.inserirDoc(document);
        }
    }




    private void alterarLogMongoDb(String erro, String cpf, int linhaErro, String cpfBlackList, Integer incrementoAtendimento) {

        alterarLogMongoDb(null,erro,cpf,linhaErro,cpfBlackList,incrementoAtendimento,null);
    }

    private void alterarLogMongoDb(String observacao,String erro, String cpf, int linhaErro, String cpfBlackList, Integer incrementoAtendimento,String status) {

        if (StringUtils.isNotBlank(objIdMongo)) {

            if (StringUtils.isNotBlank(observacao))
                mongoDB.alterarDocById( "observacao", observacao,objIdMongo);

            if (StringUtils.isNotBlank(erro))
                mongoDB.alterarDocById( "erro", erro,objIdMongo);

            if (StringUtils.isNotBlank(cpf))
                mongoDB.inserirLista(objIdMongo, "erro_cpf_list", cpf);

            if (linhaErro > 0)
                mongoDB.inserirLista(objIdMongo, "linhas_erro", linhaErro);

            if(StringUtils.isNotBlank(cpfBlackList))
                mongoDB.inserirLista(objIdMongo, "clientes_blacklist", cpfBlackList);

            if(incrementoAtendimento !=null)
                mongoDB.alterarDocById( "total_atendimentos", incrementoAtendimento,objIdMongo);

            if(StringUtils.isNotBlank(status))
                mongoDB.alterarDocById( "status", status,objIdMongo);


        }


    }

    private void identificarIndiceElementosLista(Map<Integer, DadosBaseImportacaoEnum> mapDadosImportacao) {

        this.listaIndexDDD = new ArrayList<>();
        this.listaIndexNumero = new ArrayList<>();
        this.listaIndexStatusTelefone = new ArrayList<>();
        this.listaIndexDDDNumero = new ArrayList<Integer>();

        this.listaIndexLogradouro = new ArrayList<>();
        this.listaIndexNumeroLogradouro = new ArrayList<>();
        this.listaIndexComplemento = new ArrayList<>();
        this.listaIndexBairro = new ArrayList<>();
        this.listaIndexCidade = new ArrayList<>();
        this.listaIndexEstado = new ArrayList<>();
        this.listaIndexCep = new ArrayList<>();

        this.listaIndexBanco = new ArrayList<>();
        this.listaIndexAgencia = new ArrayList<>();
        this.listaIndexDvAgencia = new ArrayList<>();
        this.listaIndexConta = new ArrayList<>();
        this.listaIndexDvConta = new ArrayList<>();
        this.listaIndexTipoConta = new ArrayList<>();
        this.listaIndexEstadoBanco = new ArrayList<>();

        this.listIndexInformacoesComplementares = new ArrayList<>();
        this.listaIndexEmail = new ArrayList<Integer>();

        this.indicePortabilidadeBancoOrigem = null;
        this.indicePortabilidadeSaldoDevedor = null;
        this.indicePortabilidadeValorParcela = null;
        this.indicePortabilidadePrazoTotal = null;
        this.indicePortabilidadePrazoRestante = null;
        this.indicePortabilidadeTaxaJuros = null;
        this.indicePortabilidadeDataAverbacao = null;
        this.indicePortabilidadeBeneficio = null;
        this.indicePortabilidadeEspecie = null;

        this.indexCpf = null;


        for (Iterator<Integer> iterator = mapDadosImportacao.keySet().iterator(); iterator.hasNext(); ) {

            int index = iterator.next();

            DadosBaseImportacaoEnum tipoDadosImportacao = mapDadosImportacao.get(index);

            if (tipoDadosImportacao != null) {

                if (tipoDadosImportacao.equals(DadosBaseImportacaoEnum.CPF)) {
                    this.indexCpf = index;
                    continue;
                }

                if (tipoDadosImportacao.equals(DadosBaseImportacaoEnum.DDD)) {
                    this.listaIndexDDD.add(index);
                    continue;
                }

                if (tipoDadosImportacao.equals(DadosBaseImportacaoEnum.TELEFONE)) {
                    this.listaIndexNumero.add(index);
                    continue;
                }

                if (tipoDadosImportacao.equals(DadosBaseImportacaoEnum.DDD_TELEFONE)) {
                    this.listaIndexDDDNumero.add(index);
                    continue;

                }

                if (tipoDadosImportacao.equals(DadosBaseImportacaoEnum.STATUS_TELEFONE)) {
                    this.listaIndexStatusTelefone.add(index);
                    continue;
                }

                if (tipoDadosImportacao.equals(DadosBaseImportacaoEnum.LOGRADOURO)) {
                    this.listaIndexLogradouro.add(index);
                    continue;
                }

                if (tipoDadosImportacao.equals(DadosBaseImportacaoEnum.NUMERO)) {
                    this.listaIndexNumeroLogradouro.add(index);
                    continue;
                }

                if (tipoDadosImportacao.equals(DadosBaseImportacaoEnum.COMPLEMENTO)) {
                    this.listaIndexComplemento.add(index);
                    continue;
                }

                if (tipoDadosImportacao.equals(DadosBaseImportacaoEnum.BAIRRO)) {
                    this.listaIndexBairro.add(index);
                    continue;
                }

                if (tipoDadosImportacao.equals(DadosBaseImportacaoEnum.CIDADE)) {
                    this.listaIndexCidade.add(index);
                    continue;
                }

                if (tipoDadosImportacao.equals(DadosBaseImportacaoEnum.ESTADO)) {
                    this.listaIndexEstado.add(index);
                    continue;
                }

                if (tipoDadosImportacao.equals(DadosBaseImportacaoEnum.CEP)) {
                    this.listaIndexCep.add(index);
                    continue;
                }

                if (tipoDadosImportacao.equals(DadosBaseImportacaoEnum.BANCO_CLIENTE)) {
                    this.listaIndexBanco.add(index);
                    continue;
                }
                if (tipoDadosImportacao.equals(DadosBaseImportacaoEnum.AGENCIA)) {
                    this.listaIndexAgencia.add(index);
                    continue;
                }

                if (tipoDadosImportacao.equals(DadosBaseImportacaoEnum.DIGITO_AGENCIA)) {
                    this.listaIndexDvAgencia.add(index);
                    continue;
                }

                if (tipoDadosImportacao.equals(DadosBaseImportacaoEnum.CONTA)) {
                    this.listaIndexConta.add(index);
                    continue;
                }

                if (tipoDadosImportacao.equals(DadosBaseImportacaoEnum.DIGITO_CONTA)) {
                    this.listaIndexDvConta.add(index);
                    continue;
                }

                if (tipoDadosImportacao.equals(DadosBaseImportacaoEnum.TIPO_CONTA)) {
                    this.listaIndexTipoConta.add(index);
                    continue;
                }

                if (tipoDadosImportacao.equals(DadosBaseImportacaoEnum.ESTADO_BANCO)) {
                    this.listaIndexEstadoBanco.add(index);
                    continue;
                }

                if (tipoDadosImportacao.equals(DadosBaseImportacaoEnum.INFORMACOES_COMPLEMENTARES)) {
                    this.listIndexInformacoesComplementares.add(index);
                    continue;
                }

                if (tipoDadosImportacao.equals(DadosBaseImportacaoEnum.PORTABILIDADE_BANCO_ORIGEM)) {
                    this.indicePortabilidadeBancoOrigem = index;
                    continue;
                }

                if (tipoDadosImportacao.equals(DadosBaseImportacaoEnum.PORTABILIDADE_SALDO_DEVEDOR)) {
                    this.indicePortabilidadeSaldoDevedor = index;
                    continue;
                }

                if (tipoDadosImportacao.equals(DadosBaseImportacaoEnum.PORTABILIDADE_VALOR_PARCELA)) {
                    this.indicePortabilidadeValorParcela = index;
                    continue;
                }

                if (tipoDadosImportacao.equals(DadosBaseImportacaoEnum.PORTABILIDADE_PRAZO_TOTAL)) {
                    this.indicePortabilidadePrazoTotal = index;
                    continue;
                }

                if (tipoDadosImportacao.equals(DadosBaseImportacaoEnum.PORTABILIDADE_PRAZO_RESTANTE)) {
                    this.indicePortabilidadePrazoRestante = index;
                    continue;
                }

                if (tipoDadosImportacao.equals(DadosBaseImportacaoEnum.PORTABILIDADE_TAXA_JUROS)) {
                    this.indicePortabilidadeTaxaJuros = index;
                    continue;
                }

                if (tipoDadosImportacao.equals(DadosBaseImportacaoEnum.PORTABILIDADE_DATA_AVERBACAO)) {
                    this.indicePortabilidadeDataAverbacao = index;
                    continue;
                }

                if (tipoDadosImportacao.equals(DadosBaseImportacaoEnum.PORTABILIDADE_BENEFICIO)) {
                    this.indicePortabilidadeBeneficio = index;
                    continue;
                }

                if (tipoDadosImportacao.equals(DadosBaseImportacaoEnum.PORTABILIDADE_ESPECIE)) {
                    this.indicePortabilidadeEspecie = index;
                    continue;
                }

                if (tipoDadosImportacao.equals(DadosBaseImportacaoEnum.EMAIL)) {
                    this.listaIndexEmail.add(index);
                }
            }

        }

        this.quantidadeEmail = this.listaIndexEmail.size();

        this.quantidadeTelefone = Collections.max(Arrays.asList(this.listaIndexDDD.size(), this.listaIndexNumero.size(),
                this.listaIndexStatusTelefone.size(), this.listaIndexDDDNumero.size()));

        this.quantidadeEndereco = Collections
                .max(Arrays.asList(this.listaIndexLogradouro.size(),
                        this.listaIndexNumeroLogradouro.size(),
                        this.listaIndexComplemento.size(),
                        this.listaIndexBairro.size(), this.listaIndexCidade.size(),
                        this.listaIndexEstado.size(), this.listaIndexCep.size()));


        this.quantidadeDadosBancarios = Collections.max(Arrays.asList(this.listaIndexAgencia.size(), this.listaIndexDvAgencia.size(),
                this.listaIndexConta.size(), this.listaIndexDvConta.size(),
                this.listaIndexBanco.size(), this.listaIndexTipoConta.size(),
                this.listaIndexEstadoBanco.size()));
    }

    private void validarImportacao(byte[] arquivo, Map<Integer, DadosBaseImportacaoEnum> mapDadosImportacao)
            throws Exception {

        Scanner reader = null;

        try {

            ByteArrayInputStream byt = new ByteArrayInputStream(arquivo);
            reader = new Scanner(byt, StandardCharsets.UTF_8);

            String[] listaCabecalhos = reader.nextLine().split("[;]");

            String linha = null;

            this.indiceLinhaErro = 1;

            while (reader.hasNext()) {

                this.indiceLinhaErro++;

                Atendimento atendimento = new Atendimento();

                linha = reader.nextLine();

                String[] dados = linha.replaceAll(";[ ]*", "; ").split("[;]");

                for (int index = 0; index < dados.length; index++) {

                    DadosBaseImportacaoEnum tipoDadosImportacao = mapDadosImportacao.get(index);

                    if (tipoDadosImportacao != null && !tipoDadosImportacao.isLista()) {

                        converterDados((Serializable) atendimento, dados[index], tipoDadosImportacao);
                    }
                }

                criarTelefone(dados, atendimento);
                criarEndereco(dados, atendimento);
                criarEmail(dados, atendimento);
                criarDadosBancarios(dados, atendimento);

                criarJasonInformacoesComplementares(dados, listaCabecalhos, atendimento);
            }

        } catch (Exception e) {

            throw e;

        } finally {

            if (reader != null) {

                try {

                    reader.close();

                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        }
    }

    private void converterDados(Serializable entidade, String informacao, DadosBaseImportacaoEnum tipoDadosImportacao) throws Exception {

        String valor = (informacao.trim().length() <= tipoDadosImportacao.getTamanho()) ? informacao.trim() : informacao.trim().substring(0, tipoDadosImportacao.getTamanho()).trim();

        if (tipoDadosImportacao.getTipoAtributo().equals(TiposVariaveisEnum.STRING)) {

            criarMetodo(entidade, tipoDadosImportacao, String.class).invoke(entidade, valor.isEmpty() ? null : Utils.tratarStringLf(valor));

        } else if (tipoDadosImportacao.getTipoAtributo().equals(TiposVariaveisEnum.INTEGER)) {

            criarMetodo(entidade, tipoDadosImportacao, Integer.class).invoke(entidade,
                    Utils.isInteiro(valor) ? Integer.parseInt(valor) : null);

        } else if (tipoDadosImportacao.getTipoAtributo().equals(TiposVariaveisEnum.DOUBLE)) {

            criarMetodo(entidade, tipoDadosImportacao, BigDecimal.class).invoke(entidade, validarNumero(valor) ? NumeroUtil.builder(valor).formatarBigDecimal().getBigdecimal() : null);

        } else if (tipoDadosImportacao.getTipoAtributo().equals(TiposVariaveisEnum.SHORT)) {

            criarMetodo(entidade, tipoDadosImportacao, Short.class).invoke(entidade,
                    validarNumero(valor) ? Short.parseShort(valor) : null);

        } else if (tipoDadosImportacao.getTipoAtributo().equals(TiposVariaveisEnum.DATE)) {

            criarMetodo(entidade, tipoDadosImportacao, Date.class).invoke(entidade, valor.isEmpty() ? null : DateUtil.builder(valor.trim()).formatarStringParaData().getData());

        } else if (tipoDadosImportacao.getTipoAtributo().equals(TiposVariaveisEnum.ENTITY)) {

            if (tipoDadosImportacao.name().contains("STATUS_TELEFONE")) {

            } else if (tipoDadosImportacao.name().contains("PRODUTO")) {

            }

        } else if (tipoDadosImportacao.getTipoAtributo().equals(TiposVariaveisEnum.ENUM)) {


            switch (tipoDadosImportacao.name()) {

                case "BANCO_CLIENTE":
                case "PORTABILIDADE_BANCO_ORIGEM":
                    criarMetodo(entidade, tipoDadosImportacao, InstituicaoFinanceiraEnum.class).invoke(entidade, valor.isEmpty() ? null : InstituicaoFinanceiraEnum.retornarEnum(valor.trim().toUpperCase()));
                    break;

                case "TIPO_CONTA":

                    criarMetodo(entidade, tipoDadosImportacao, TipoContaEnum.class).invoke(entidade, valor.isEmpty() ? null : EnumUtils.getEnum(TipoContaEnum.class, valor.toUpperCase()));
                    break;

                case "ESTADO_CIVIL":

                    criarMetodo(entidade, tipoDadosImportacao, EstadoCivilEnum.class).invoke(entidade, valor.isEmpty() ? null : EnumUtils.getEnum(EstadoCivilEnum.class, TextUtil.builder(valor.toUpperCase()).removerAcento().getTexto()));
                    break;

                case "SEXO":

                    criarMetodo(entidade, tipoDadosImportacao, SexoEnum.class).invoke(entidade,
                            valor.isEmpty() ? null
                                    : EnumUtils.getEnum(SexoEnum.class,
                                    TextUtil.builder(valor.toUpperCase()).removerAcento().getTexto()));
                    break;

                case "STS_OPERACAO":

                    criarMetodo(entidade, tipoDadosImportacao, StatusOperacaoEnum.class).invoke(entidade,
                            valor.isEmpty() ? null
                                    : EnumUtils.getEnum(StatusOperacaoEnum.class,
                                    TextUtil.builder(valor.toUpperCase()).removerAcento().getTexto()));
                    break;

            }

        }
    }

    private Method criarMetodo(Serializable entidade, DadosBaseImportacaoEnum tipoDadosImportacao, Class<?> classe) throws Exception {

        try {

            return entidade.getClass().getDeclaredMethod(tipoDadosImportacao.getMetodo(), classe);

        } catch (NoSuchMethodException e) {

            try {

                return entidade.getClass().getSuperclass().getDeclaredMethod(tipoDadosImportacao.getMetodo(), classe);

            } catch (Exception e1) {

                return entidade.getClass().getSuperclass().getSuperclass().getDeclaredMethod(tipoDadosImportacao.getMetodo(), classe);
            }
        }
    }

    private boolean validarNumero(String valor) {

        try {

            if (valor.contains("$")) {
                valor = valor.replaceAll("R$", "").trim();
            }
            if (valor.contains("%")) {
                valor = valor.replaceAll("%", "").trim();
            }

            if (valor.contains("R")) {
                valor = valor.replaceAll("R", "").trim();
            }

            return !valor.trim().isEmpty() && NumberUtils.isCreatable(
                    valor.replaceAll("[.]", "").replaceAll("[,]", ".").replaceAll("[R$]", "").replaceAll("[%]", "").trim());

        } catch (java.lang.NumberFormatException e) {

            return false;
        }
    }

    private void criarTelefone(String[] dados, Atendimento atendimento) throws Exception {

        Iterator<Integer> iteratorDDD = this.listaIndexDDD.iterator();
        Iterator<Integer> iteratorNumero = this.listaIndexNumero.iterator();
        Iterator<Integer> iteratorStatusTelefone = this.listaIndexStatusTelefone.iterator();
        Iterator<Integer> iteratorDDDNumero = this.listaIndexDDDNumero.iterator();

        for (int index = 0; index < this.quantidadeTelefone.intValue(); index++) {

            try {

                Telefone telefone = new Telefone();
                telefone.setStatusTelefone(null);

                if (iteratorDDD.hasNext()) {

                    Integer ddd = ((Integer) iteratorDDD.next()).intValue();
                    converterDados((Serializable) telefone, dados[ddd], DadosBaseImportacaoEnum.DDD);
                }

                if (iteratorNumero.hasNext()) {

                    Integer numero = ((Integer) iteratorNumero.next()).intValue();

                    converterDados((Serializable) telefone, dados[numero], DadosBaseImportacaoEnum.TELEFONE);
                }


                if (iteratorStatusTelefone.hasNext()) {

                    converterDados((Serializable) telefone, dados[((Integer) iteratorStatusTelefone.next()).intValue()],
                            DadosBaseImportacaoEnum.STATUS_TELEFONE);

                    telefone.setStatusTelefone(telefone.getStatusTelefone());
                }

                if (iteratorDDDNumero.hasNext()) {

                    converterDados((Serializable) telefone, dados[((Integer) iteratorDDDNumero.next()).intValue()], DadosBaseImportacaoEnum.DDD_TELEFONE);

                    if (telefone.getNumero() != null && !telefone.getNumero().isEmpty()) {

                        String numero = telefone.getNumero().trim().replaceAll("[(]", "").replaceAll("[)]", "").replaceAll("[-]", "").replaceAll(" ", "");

                        telefone.setNumero(StringUtils.substring(numero, 2));
                        telefone.setDdd(Short.valueOf(Short.parseShort(StringUtils.substring(numero, 0, 2))));

                    }

                }

                if (telefone.getDdd() != null && telefone.getDdd().toString().length() >= 2 && telefone.getNumero() != null
                        && telefone.getNumero().length() >= 8 && (this.listBlackListTelefones == null || this.listBlackListTelefones.isEmpty() || !this.listBlackListTelefones.contains(telefone.getDdd().toString() + telefone.getNumero()))) {

                    atendimento.adicionarTelefone((GenericTelefone) telefone);
                }

            } catch (Exception e) {

                String erro = "Erro ao inserir telefone: " + e.getMessage();
                System.out.println(erro);
                erros.append("Erro ao inserir telefone na linha: ").append(this.indiceLinhaErro).append(" ,");
                alterarLogMongoDb(erro, atendimento.getCpf(), this.indiceLinhaErro,null,null);

            }
        }
    }

    private void criarEndereco(String[] dados, Atendimento atendimento) throws Exception {

        Iterator<Integer> iteratorLogradouro = this.listaIndexLogradouro.iterator();
        Iterator<Integer> iteratorNumero = this.listaIndexNumeroLogradouro.iterator();
        Iterator<Integer> iteratorComplemento = this.listaIndexComplemento.iterator();
        Iterator<Integer> iteratorBairro = this.listaIndexBairro.iterator();
        Iterator<Integer> iteratorCidade = this.listaIndexCidade.iterator();
        Iterator<Integer> iteratorEstado = this.listaIndexEstado.iterator();
        Iterator<Integer> iteratorCep = this.listaIndexCep.iterator();

        for (int index = 0; index < this.quantidadeEndereco; index++) {

            Endereco endereco = new Endereco();

            try {

                if (iteratorLogradouro.hasNext()) {
                    converterDados((Serializable) endereco, dados[(Integer) iteratorLogradouro.next()],
                            DadosBaseImportacaoEnum.LOGRADOURO);
                }

                if (iteratorNumero.hasNext()) {
                    converterDados((Serializable) endereco, dados[(Integer) iteratorNumero.next()],
                            DadosBaseImportacaoEnum.NUMERO);
                }

                if (iteratorComplemento.hasNext()) {
                    converterDados((Serializable) endereco, dados[(Integer) iteratorComplemento.next()],
                            DadosBaseImportacaoEnum.COMPLEMENTO);
                }

                if (iteratorBairro.hasNext()) {
                    converterDados((Serializable) endereco, dados[(Integer) iteratorBairro.next()],
                            DadosBaseImportacaoEnum.BAIRRO);
                }

                if (iteratorCidade.hasNext()) {
                    converterDados((Serializable) endereco, dados[(Integer) iteratorCidade.next()],
                            DadosBaseImportacaoEnum.CIDADE);
                }

                if (iteratorEstado.hasNext()) {


                    converterDados(endereco, dados[iteratorEstado.next()], DadosBaseImportacaoEnum.ESTADO);


                }

            } catch (Exception e) {
                System.out.println(Arrays.toString(dados));
                System.out.println("Erro: " + e.getMessage());
            }

            if (iteratorCep.hasNext()) {

                preConverterDados(endereco, dados, DadosBaseImportacaoEnum.CEP, iteratorCep.next());

            }

            if (StringUtils.isNotBlank(endereco.getLogradouro()) || StringUtils.isNotBlank(endereco.getNumero())
                    || StringUtils.isNotBlank(endereco.getComplemento()) || StringUtils.isNotBlank(endereco.getBairro())
                    || StringUtils.isNotBlank(endereco.getCidade()) || StringUtils.isNotBlank(endereco.getUf())
                    || StringUtils.isNotBlank(endereco.getCep())) {

                boolean condicaoExistenciaEndereco = jaPossuiEndereco(atendimento, endereco);


                if (!condicaoExistenciaEndereco) {

                    atendimento.adicionarEndereco(endereco);

                }
            }
        }
    }

    private void preConverterDados(Serializable entidade, String[] dados, DadosBaseImportacaoEnum dadosBaseImportacaoEnum, int indice) throws Exception {

        if (dados.length > indice)
            converterDados((Serializable) entidade, dados[indice], dadosBaseImportacaoEnum);
        else {
            System.out.println("indice é maior que " + indice);
        }
    }

    private void criarDadosBancarios(String[] dados, Atendimento atendimento) throws Exception {

        Iterator<Integer> iteratorBanco = this.listaIndexBanco.iterator();
        Iterator<Integer> iteratorAgencia = this.listaIndexAgencia.iterator();
        Iterator<Integer> iteratorDvAgencia = this.listaIndexDvAgencia.iterator();
        Iterator<Integer> iteratorConta = this.listaIndexConta.iterator();
        Iterator<Integer> iteratorDvConta = this.listaIndexDvConta.iterator();
        Iterator<Integer> iteratorTipoConta = this.listaIndexTipoConta.iterator();
        Iterator<Integer> iteratorEstadoBanco = this.listaIndexEstadoBanco.iterator();

        for (int index = 0; index < this.quantidadeDadosBancarios.intValue(); index++) {

            DadosBancarios dadosBancarios = new DadosBancarios();

            if (iteratorBanco.hasNext()) {

                converterDados((Serializable) dadosBancarios, dados[((Integer) iteratorBanco.next()).intValue()], DadosBaseImportacaoEnum.BANCO_CLIENTE);
            }

            if (iteratorAgencia.hasNext()) {
                converterDados((Serializable) dadosBancarios, dados[((Integer) iteratorAgencia.next()).intValue()],
                        DadosBaseImportacaoEnum.AGENCIA);
            }

            if (iteratorDvAgencia.hasNext()) {
                converterDados((Serializable) dadosBancarios, dados[((Integer) iteratorDvAgencia.next()).intValue()],
                        DadosBaseImportacaoEnum.DIGITO_AGENCIA);
            }

            if (iteratorConta.hasNext()) {
                converterDados((Serializable) dadosBancarios, dados[((Integer) iteratorConta.next()).intValue()],
                        DadosBaseImportacaoEnum.CONTA);
            }

            if (iteratorDvConta.hasNext()) {
                converterDados((Serializable) dadosBancarios, dados[((Integer) iteratorDvConta.next()).intValue()],
                        DadosBaseImportacaoEnum.DIGITO_CONTA);
            }

            if (iteratorTipoConta.hasNext()) {
                converterDados((Serializable) dadosBancarios, dados[((Integer) iteratorTipoConta.next()).intValue()],
                        DadosBaseImportacaoEnum.TIPO_CONTA);
            }

            if (iteratorEstadoBanco.hasNext()) {
                converterDados((Serializable) dadosBancarios, dados[((Integer) iteratorEstadoBanco.next()).intValue()],
                        DadosBaseImportacaoEnum.ESTADO_BANCO);
            }

            if (dadosBancarios.getNomeBanco() != null ||

                    StringUtils.isNotBlank(dadosBancarios.getAgencia())
                    || StringUtils.isNotBlank(dadosBancarios.getConta())) {

                boolean condicaoExistenciaDadosBancarios = jaPossuiDadosBancarios(atendimento, dadosBancarios);

                if (!condicaoExistenciaDadosBancarios) {

                    atendimento.adicionarDadosBancarios(dadosBancarios);
                }

            }
        }
    }

    private void criarDadosPortabilidade(String[] dados, Atendimento atendimento) {

        Portabilidade portabilidade = new Portabilidade();

        try {

            if (this.indicePortabilidadeBancoOrigem != null)
                converterDados(portabilidade, dados[this.indicePortabilidadeBancoOrigem], DadosBaseImportacaoEnum.PORTABILIDADE_BANCO_ORIGEM);

            if (this.indicePortabilidadeSaldoDevedor != null)
                converterDados(portabilidade, dados[this.indicePortabilidadeSaldoDevedor], DadosBaseImportacaoEnum.PORTABILIDADE_SALDO_DEVEDOR);

            if (this.indicePortabilidadeValorParcela != null)
                converterDados(portabilidade, dados[this.indicePortabilidadeValorParcela], DadosBaseImportacaoEnum.PORTABILIDADE_VALOR_PARCELA);

            if (this.indicePortabilidadePrazoTotal != null)
                converterDados(portabilidade, dados[this.indicePortabilidadePrazoTotal], DadosBaseImportacaoEnum.PORTABILIDADE_PRAZO_TOTAL);

            if (this.indicePortabilidadePrazoRestante != null)
                converterDados(portabilidade, dados[this.indicePortabilidadePrazoRestante], DadosBaseImportacaoEnum.PORTABILIDADE_PRAZO_RESTANTE);

            if (this.indicePortabilidadeTaxaJuros != null)
                converterDados(portabilidade, dados[this.indicePortabilidadeTaxaJuros], DadosBaseImportacaoEnum.PORTABILIDADE_TAXA_JUROS);

            if (this.indicePortabilidadeDataAverbacao != null)
                converterDados(portabilidade, dados[this.indicePortabilidadeDataAverbacao], DadosBaseImportacaoEnum.PORTABILIDADE_DATA_AVERBACAO);

            if (this.indicePortabilidadeBeneficio != null)
                converterDados(portabilidade, dados[this.indicePortabilidadeBeneficio], DadosBaseImportacaoEnum.PORTABILIDADE_BENEFICIO);

            if (this.indicePortabilidadeEspecie != null)
                converterDados(portabilidade, dados[this.indicePortabilidadeEspecie], DadosBaseImportacaoEnum.PORTABILIDADE_ESPECIE);

            portabilidade.setBeneficio(StringUtils.isEmpty(portabilidade.getBeneficio()) ? atendimento.getBeneficioPrincipal() : portabilidade.getBeneficio());

            boolean condicaoExistenciaPortabilidade = jaPossuiPortabilidade(atendimento, portabilidade);

            if ((portabilidade.getBancoOrigem() != null && (StringUtils.isNotBlank(atendimento.getBeneficioPrincipal()) || StringUtils.isNotBlank(portabilidade.getBeneficio())) && !condicaoExistenciaPortabilidade)) {

                atendimento.adicionarPortabilidade(portabilidade);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao inserir portabilidade: " + e.getMessage());
        }

    }


    private boolean jaPossuiEndereco(Atendimento atendimento, final Endereco endereco) {

        if (CollectionUtils.isEmpty(atendimento.getListaEnderecos())) {
            return false;
        }

        return atendimento.getListaEnderecos().stream().anyMatch(enderecoLista -> Objects.equals(enderecoLista.getCidade(), endereco.getCidade()) && Objects.equals(enderecoLista.getCep(), endereco.getCep()) && Objects.equals(enderecoLista.getUf(), endereco.getUf()) && Objects.equals(enderecoLista.getBairro(), endereco.getBairro()) && Objects.equals(enderecoLista.getNumero(), endereco.getNumero()) && Objects.equals(enderecoLista.getLogradouro(), endereco.getLogradouro()) && Objects.equals(enderecoLista.getComplemento(), endereco.getComplemento()) && Objects.equals(enderecoLista.getLocalidade(), endereco.getLocalidade()));


    }

    private boolean jaPossuiPortabilidade(Atendimento atendimento, final Portabilidade portabilidade) {

        if (CollectionUtils.isEmpty(atendimento.getListPortabilidades())) {
            return false;
        }

        return atendimento.getListPortabilidades().stream().anyMatch(portabilidadeLista -> portabilidadeLista.getBancoOrigem() == portabilidade.getBancoOrigem() && Objects.equals(portabilidadeLista.getSaldoDevedor(), portabilidade.getSaldoDevedor()) && Objects.equals(portabilidadeLista.getValorParcela(), portabilidade.getValorParcela()) && Objects.equals(portabilidadeLista.getPrazoTotal(), portabilidade.getPrazoTotal()) && Objects.equals(portabilidadeLista.getPrazoRestante(), portabilidade.getPrazoRestante()) && Objects.equals(portabilidadeLista.getTaxaJuros(), portabilidade.getTaxaJuros()) && Objects.equals(portabilidadeLista.getDataAverbacao(), portabilidade.getDataAverbacao()) && Objects.equals(portabilidadeLista.getBeneficio(), portabilidade.getBeneficio()) && Objects.equals(portabilidadeLista.getEspecie(), portabilidade.getEspecie()));

    }


    private boolean jaPossuiDadosBancarios(Atendimento atendimento, final DadosBancarios dadosBancarios) {

        boolean jaPossuiDadosBancarios = CollectionUtils.exists(atendimento.getListaDadosBancarios(),
                new org.apache.commons.collections4.Predicate<DadosBancarios>() {

                    @Override
                    public boolean evaluate(DadosBancarios dadosBancariosLista) {
                        // TODO Auto-generated method stub

                        boolean bancoEhOMesmo = ((dadosBancariosLista.getNomeBanco() == null
                                && dadosBancarios.getNomeBanco() == null)
                                || (dadosBancariosLista.getNomeBanco() != null && dadosBancarios.getNomeBanco() != null
                                && dadosBancariosLista.getNomeBanco().equals(dadosBancarios.getNomeBanco())));

                        boolean agenciaEhAMesma = StringUtils.equalsIgnoreCase(dadosBancariosLista.getAgencia(),
                                dadosBancarios.getAgencia());

                        boolean contaEhAMesma = StringUtils.equalsIgnoreCase(dadosBancariosLista.getConta(),
                                dadosBancarios.getConta());

                        return bancoEhOMesmo && agenciaEhAMesma && contaEhAMesma;
                    }

                });

        return jaPossuiDadosBancarios;

    }

    private void criarJasonInformacoesComplementares(String[] dados, String[] listaCabecalhos, Atendimento atendimento)
            throws Exception {

        Map<String, String> mapInformacoesComplementares = new HashMap<>();

        for (Integer index : this.listIndexInformacoesComplementares) {

            if (index < dados.length) {

                if (validarJson(dados[index.intValue()].trim())) {

                    atendimento.setInformacoesComplementares(dados[index.intValue()].trim());
                    continue;
                }

                mapInformacoesComplementares.put(listaCabecalhos[index.intValue()].trim(), dados[index.intValue()].trim());
            }
        }

        if (mapInformacoesComplementares != null && !mapInformacoesComplementares.isEmpty()) {

            JSONObject json = new JSONObject(mapInformacoesComplementares);
            atendimento.setInformacoesComplementares(json.toString());

        }
    }

    private void criarEmail(String[] dados, Atendimento atendimento) throws Exception {

        Iterator<Integer> iteratorEmail = this.listaIndexEmail.iterator();

        for (int index = 0; index < this.quantidadeEmail.intValue(); index++) {

            Email email = new Email();

            if (iteratorEmail.hasNext()) {

                converterDados((Serializable) email, dados[((Integer) iteratorEmail.next()).intValue()],
                        DadosBaseImportacaoEnum.EMAIL);
            }

            if (StringUtils.isNotBlank(email.getEmail())) {
                boolean condicaoExistenciaEmail = jaPossuiEmail(atendimento, email);

                if (!condicaoExistenciaEmail) {
                    atendimento.adicionarEmail((GenericEmail) email);
                }
            }
        }
    }

    private boolean validarJson(String valor) {

        try {
            new JSONObject(valor);
            return true;

        } catch (Exception ex) {
            return false;
        }
    }

    private void gerarLogImportacao(Importacao importacao, String header, byte[] arquivo) {
        try {
            LogImportacao logImportacao = new LogImportacao();

            logImportacao.setImportacao(importacao);
            logImportacao.setHeader(header);
            logImportacao.setArquivo(arquivo);
            logImportacao.setDataImportacao(new Date(System.currentTimeMillis()));

            this.serviceAbstract.inserir((Serializable) logImportacao);

        } catch (ProativaException e) {
            e.printStackTrace();
        }
    }

}
