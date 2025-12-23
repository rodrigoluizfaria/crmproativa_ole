package com.proativaservicos.bean;


import com.proativaservicos.model.*;
import com.proativaservicos.model.dto.HistoricoAtividadesDto;
import com.proativaservicos.model.dto.ProtocoloDTO;
import com.proativaservicos.service.*;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.RegistroSistemaUtil;
import com.proativaservicos.util.constantes.MotivoSolicitacaoSegundaViaCartaoEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import com.proativaservicos.util.constantes.TipoStatusAtividadesEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.PrimeFaces;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.*;

@Named
@ViewScoped
public class FichaAtendimentoSacBean extends GenericBean {


    @Inject
    private AtendimentoService serviceAtendimento;

    @Inject
    private AtendimentoAtivoService serviceAtendimentoAtivo;

    @Inject
    private StatusTelefoneService serviceStatusTelefone;

    @Inject
    private StatusAtendimentoService serviceStatusAtendimento;

    @Inject
    private MotivoService motivoService;

    @Inject
    private SubMotivoService subMotivoService;

    @Inject
    private FormaPagamentoService serviceFormaPagamento;

    @Inject
    private ProdutoService serviceProduto;

    @Inject
    private ControlePausaService serviceControlePausa;

    @Inject
    private LojaService serviceLoja;

    @Inject
    private IntegracaoService serviceIntegracao;

    @Inject
    private CampanhaService serviceCampanha;

    @Inject
    private HistoricoAtendimentoService serviceHistorico;

    @Inject
    private PabxService servicePabx;

    @Inject
    private PontoAtendimentoService servicePontoAtendimento;


    @Inject
    private RegistroSistemaUtil registro;

    private List<ProtocoloDTO> listHistoricoProtocolos;
    private List<HistoricoAtividadesDto> listHistoricoAtividadesDto;

    private List<PontoAtendimento> listPontoAtendimento;

    private List<Motivo> listMotivo;

    private List<SubMotivo> listSubMotivo;

    private Usuario usuario;

    private Atendimento atendimento;

    private HistoricoAtendimento historicoAtendimento;

    private CartaoCredito cartaoCreditoSelecionado;

    private Boolean novoCliente;

    private String cpf;

    private Long motivoSelecionadoId;

    private MotivoSolicitacaoSegundaViaCartaoEnum motivoSolicitacaoSegundaViaCartao;

    // 0=Lista, 1=SubLista, 2=Formulario
    private int stepClassificacao = 0;
    private int stepHistorico = 1;

    //CARTAO, SENHA, 2VIA, NOTAS,LIMITE -> MOCADO TROCAR PARA O ENUM....
    private String abaAtiva = "CARTAO";

    private Motivo motivoSelecionado;
    private SubMotivo subMotivoSelecionado;

    private boolean atendimentoIniciado = false;

    private Double novoLimiteSolicitado;
    private String motivoSolicitacaoLimite;

    @PostConstruct
    public void init() {

        String cpfTmp = (String) Faces.getSession().getAttribute("cpf_atn");
        inicializarAtendimento(cpfTmp);
        this.usuario = retornarUsuarioSessao();
        this.usuario.setPontoAtendimento(this.servicePontoAtendimento.pesquisarPontoAtendimentosPorUsuario(this.usuario.getId()));


    }

    public void inicializarListas() {

        this.listMotivo = this.motivoService.pesquisarMotivosPorEmpresa(retornarEmpresaUsuarioSessao().getId());
    }

    public void iniciarAtendimento() {
        System.out.println("Iniciando atendimento...");
        System.out.println(atendimento.getMotivo().getDescricao());
        System.out.println(atendimento.getSubMotivo().getDescricao());
        ///SALVAR
        this.atendimentoIniciado = true;
        String detalhes = retornarDetalhesAtendimento();
        String descicao = (StringUtils.isBlank(this.atendimento.getObservacaoAdicional()) ? "" : " | " + this.atendimento.getObservacaoAdicional());
        inserirAtividadesAtendimentos(TipoStatusAtividadesEnum.ATENDIMENTO_CLASSIFICADO, detalhes, descicao, "Você", "pi pi-user");
        Messages.addGlobalInfo("Sua classificação registrada com sucesso!");
    }


    public void salvarAtendimento() {

        System.out.println("Salnvando atendimento");
        System.out.println(this.atendimento.getNome() + " - " + atendimento.getCpf());
        System.out.println("Motivo: " + this.atendimento.getMotivo().getDescricao());
        System.out.println("Sub Motico: " + this.atendimento.getSubMotivo().getDescricao());
        System.out.println("AObs: " + this.atendimento.getObservacao());
        System.out.println("Obs Adicioal: " + this.atendimento.getObservacaoAdicional());
        System.out.println("ENVIAR N2: " + this.atendimento.getEnviarN2());

        System.out.println("SALVAR HISTORICO DE ATENDIMENTO.......");
        PrimeFaces.current().executeScript("PF('dlgFinalizar').hide();");
        Messages.addGlobalInfo("Histórido de atendimento salvo com sucesso");


        String descricao = this.atendimento.getObservacao();

        inserirAtividadesAtendimentos(TipoStatusAtividadesEnum.SOLICITACAO_ABERTA, retornarDetalhesAtendimento(), descricao, "Você", "pi pi-user");

        resetarAtendimento();

    }

    private String retornarDetalhesAtendimento() {

        if (this.atendimento == null || this.atendimento.getMotivo() == null || this.atendimento.getSubMotivo() == null)
            return "";
        return this.atendimento.getMotivo().getDescricao() + " -> " + this.atendimento.getSubMotivo().getDescricao();
    }

    private void resetarAtendimento() {
        resetClassificacao();
        atendimentoIniciado = false;
    }

    public void encerrarAtendimento() {
        System.out.println("Encerrando atendimento");
    }

    public void derivarAtendimentoN2() {

        System.out.println("Derivar: " + this.atendimento.getObservacaoN2());
        PrimeFaces.current().executeScript("PF('dlgDerivarN2').hide();");
    }


    public void onCliente() {

        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        String cpf = params.get("cpf");
        System.out.println("O cliente foi encontrado: " + cpf);

        if (StringUtils.isNotBlank(cpf)) {

            inicializarAtendimento(cpf);

            Faces.getSession().setAttribute("cpf_atn", cpf);

            System.out.println("CLIENTE: " + this.cpf);
        }
    }

    public void inicializarAtendimento(String cpf) {

        if (StringUtils.isNotBlank(cpf)) {
            inicializarListas();
            this.cpf = cpf;
            this.atendimento = new Atendimento();
            this.atendimento.setCpf(cpf);
            this.atendimento.setNome("JOSE ANTONIO");
            this.atendimento.setLimite(new BigDecimal("10258.52"));
            this.atendimento.setLimiteDisponivel(new BigDecimal("7443.09"));
            this.atendimento.setValorLiberado(new BigDecimal("3085.66"));
            this.atendimento.setValorMaxOperacao(new BigDecimal("7199.88"));
            this.atendimento.setProtocolo(gerarProtocolo());
            this.atendimento.setClienteVip(Boolean.TRUE);

            this.cartaoCreditoSelecionado = new CartaoCredito();
            cartaoCreditoSelecionado.setNumeroCartao("5117233260721226");
            cartaoCreditoSelecionado.setValidade("09/2027");
            cartaoCreditoSelecionado.setCodigoSeguranca(616);
            cartaoCreditoSelecionado.setTipo("Múltiplo");
            cartaoCreditoSelecionado.setCartaoAdicional(Boolean.FALSE);
            cartaoCreditoSelecionado.setBandeira("Visa");
            cartaoCreditoSelecionado.setStatus("BLOQUEADO");

            cartaoCreditoSelecionado.setLimiteTotal(new BigDecimal("15000.00"));
            cartaoCreditoSelecionado.setLimiteDisponivel(new BigDecimal("8500.00"));
            cartaoCreditoSelecionado.setLimiteEmergencial(new BigDecimal("2000.00"));

            Telefone telefone = new Telefone();
            telefone.setDdd(Short.valueOf("31"));
            telefone.setNumero("999631311");
            atendimento.adicionarTelefone(telefone);

            BigDecimal limiteTotal = cartaoCreditoSelecionado.getLimiteTotal();
            BigDecimal limiteDisponivel = cartaoCreditoSelecionado.getLimiteDisponivel();


            BigDecimal usado = limiteTotal.subtract(limiteDisponivel);

            BigDecimal percentualUsado = usado
                    .multiply(BigDecimal.valueOf(100))
                    .divide(limiteTotal, RoundingMode.HALF_UP);

            System.out.println("Percentual usado: " + percentualUsado + "%");

            this.atendimento.addCartao(cartaoCreditoSelecionado);


            carregarHistoricosAtendimentos();
            carregarAtividadesAtendimentos();

        }

    }

    private String gerarProtocolo() {

        if (StringUtils.isNotBlank(this.atendimento.getProtocolo()))
            return this.atendimento.getProtocolo();

        String formatoData = DateUtil.builder(new Date()).formatarDataParaString("yyyyMMddHHmm").getDataTexto();
        SecureRandom random = new SecureRandom();

        int numero = 10000000 + random.nextInt(90000000);

        return formatoData + numero;

    }

    private void carregarHistoricosAtendimentos() {

        //MOCADO...
        this.listHistoricoProtocolos = new ArrayList<>();

        this.listHistoricoProtocolos.add(new ProtocoloDTO(
                "SAC12345678",
                "Encerrado",
                "Reclamação",
                "Reclamação sobre cobrança indevida na fatura",
                "14/10/2024",
                "21/10/2024"
        ));

        this.listHistoricoProtocolos.add(new ProtocoloDTO(
                "SAC23456789",
                "Encerrado",
                "Contestação",
                "Contestação de compra não reconhecida",
                "09/09/2024",
                "16/09/2024"
        ));
    }

    private void inserirAtividadesAtendimentos(TipoStatusAtividadesEnum tipoAtendimento, String detalhes, String descicao, String autor, String icon) {

        if (CollectionUtils.isEmpty(this.listHistoricoAtividadesDto))
            this.listHistoricoAtividadesDto = new ArrayList<>();

        this.listHistoricoAtividadesDto.add(new HistoricoAtividadesDto(tipoAtendimento, descicao, detalhes, new Date(), autor, icon));

        this.listHistoricoAtividadesDto.stream().sorted(Comparator.comparing(HistoricoAtividadesDto::getData).reversed());

        if (this.atendimento.getMotivo() != null && this.atendimento.getSubMotivo() != null) {


            HistoricoAtividadesDto historicoAtividadesDto = new HistoricoAtividadesDto(tipoAtendimento,
                    detalhes, descicao, new Date(), autor, icon);
            //INSERIR NO BANCO...
        }


    }

    private void carregarAtividadesAtendimentos() {

        // Mocado === TEM QUE PEGAR DE OUTRA MANEIRA....

        this.listHistoricoAtividadesDto = new ArrayList<>();

      /*  listHistoricoAtividadesDto.add(new HistoricoAtividadesDto(TipoStatusAtividadesEnum.SOLICITACAO_AUMENTO_LINITE,
                "De R$ 15.000 para R$ 22.222", "Motivo: Viagem internacional", new Date(), "voce", "pi-user"));

        listHistoricoAtividadesDto.add(new HistoricoAtividadesDto(TipoStatusAtividadesEnum.ATENDIMENTO_CLASSIFICADO,
                "Cartão | Limite → Consulta Disponível", "Consulta realizada com sucesso", new Date(), "Você", "pi-user"));

        listHistoricoAtividadesDto.add(new HistoricoAtividadesDto(TipoStatusAtividadesEnum.ATENDIMENTO_CLASSIFICADO,
                "Cartão | Limite → Solicitação Aumento", "Cliente questionou taxas", new Date(), "Você", "pi-user"));

        listHistoricoAtividadesDto.add(new HistoricoAtividadesDto(TipoStatusAtividadesEnum.ATENDIMENTO_CLASSIFICADO,
                "Cartão | Limite → Solicitação Aumento", "Simulação realizada", new Date(), "Você", "pi-user"));*/

        listHistoricoAtividadesDto.add(new HistoricoAtividadesDto(TipoStatusAtividadesEnum.INICIO_ATENDIMENTO,
                "Cliente conectado via URA", "Protocolo gerado automaticamente", new Date(), "Sistema", "pi-phone"));

    }


    private void gerarHistoricoAtividade() {

    }

    public void onChangeMotivo(Motivo mo) {
        this.motivoSelecionado = mo;
        this.atendimento.setMotivo(mo);
        this.listSubMotivo = this.subMotivoService.pesquisarSubMotivosPorMotivo(mo.getId(), TipoAcessoEnum.ATIVO);
        this.stepClassificacao = 1;
    }

    public void onSelecionarSubMotivo(SubMotivo sub) {

        this.atendimento.setSubMotivo(sub);
        this.subMotivoSelecionado = sub;
        this.stepClassificacao = 2;

        if (CollectionUtils.isNotEmpty(sub.getListTipoMetodosMotivos())) {
            this.abaAtiva = sub.getListTipoMetodosMotivos().get(0).name();
        } else {
            this.abaAtiva = null;
        }

    }

    public void setStepClassificacao(int step) {

        this.stepClassificacao = step;

        if (step == 0) {

            this.atendimento.setMotivo(null);
            this.atendimento.setSubMotivo(null);
            this.motivoSelecionado = null;
            this.subMotivoSelecionado = null;

        } else if (step == 1) {
            this.subMotivoSelecionado = null;
            this.atendimento.setSubMotivo(null);
        }
    }

    public void onSetStepModalHistorico(int step) {

        this.stepHistorico = step;


    }


    public void resetClassificacao() {
        setStepClassificacao(0);
    }

    public void onListarPontosAtendimentos() {

        this.listPontoAtendimento = this.servicePontoAtendimento.pesquisarPontoAtendimentosPorEmpresa(retornarEmpresaUsuarioSessao().getId());

    }

//METODOS -> SO SUBMOTIVO... API....

    public void registrarSolicitacaoLimite() {

        // CHAMAR A API;;;


        Messages.addGlobalInfo("Solicitação de aumento registrada para análise.");

        // Limpar campos
        this.novoLimiteSolicitado = null;
        this.motivoSolicitacaoLimite = null;
    }

    public void trocarAba(String aba) {
        System.out.println("ABA: " + aba);
        this.abaAtiva = aba;
    }

    public void onAssociarRamal() {
        System.out.println("onAssociarRamal");

    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public List<PontoAtendimento> getListPontoAtendimento() {
        return listPontoAtendimento;
    }

    public List<Motivo> getListMotivo() {
        return listMotivo;
    }

    public List<SubMotivo> getListSubMotivo() {
        return listSubMotivo;
    }

    public void setMotivoSelecionadoId(Long motivoSelecionadoId) {
        this.motivoSelecionadoId = motivoSelecionadoId;
    }

    public Long getMotivoSelecionadoId() {
        return motivoSelecionadoId;
    }

    public Motivo getMotivoSelecionado() {
        return motivoSelecionado;
    }

    public void setMotivoSelecionado(Motivo motivoSelecionado) {
        this.motivoSelecionado = motivoSelecionado;
    }

    public SubMotivo getSubMotivoSelecionado() {
        return subMotivoSelecionado;
    }

    public void setSubMotivoSelecionado(SubMotivo subMotivoSelecionado) {
        this.subMotivoSelecionado = subMotivoSelecionado;
    }

    public int getStepClassificacao() {
        return stepClassificacao;
    }

    public Boolean getNovoCliente() {
        return novoCliente;
    }

    public void setNovoCliente(Boolean novoCliente) {
        this.novoCliente = novoCliente;
    }

    public List<ProtocoloDTO> getListHistoricoProtocolos() {


        return listHistoricoProtocolos;
    }

    public boolean isAtendimentoIniciado() {
        return atendimentoIniciado;
    }

    // Getter e Setter
    public String getAbaAtiva() {
        return abaAtiva;
    }

    public void setAbaAtiva(String abaAtiva) {
        this.abaAtiva = abaAtiva;
    }

    public MotivoSolicitacaoSegundaViaCartaoEnum[] getListMotivoSolicitacaoSegundaViaCartao() {
        return MotivoSolicitacaoSegundaViaCartaoEnum.values();
    }

    public MotivoSolicitacaoSegundaViaCartaoEnum getMotivoSolicitacaoSegundaViaCartao() {
        return motivoSolicitacaoSegundaViaCartao;
    }

    public void setMotivoSolicitacaoSegundaViaCartao(MotivoSolicitacaoSegundaViaCartaoEnum motivoSolicitacaoSegundaViaCartao) {
        this.motivoSolicitacaoSegundaViaCartao = motivoSolicitacaoSegundaViaCartao;
    }

    public String getMotivoSolicitacaoLimite() {
        return motivoSolicitacaoLimite;
    }

    public void setMotivoSolicitacaoLimite(String motivoSolicitacaoLimite) {
        this.motivoSolicitacaoLimite = motivoSolicitacaoLimite;
    }

    public Double getNovoLimiteSolicitado() {
        return novoLimiteSolicitado;
    }

    public void setNovoLimiteSolicitado(Double novoLimiteSolicitado) {
        this.novoLimiteSolicitado = novoLimiteSolicitado;
    }

    public int getStepHistorico() {
        return stepHistorico;
    }

    public void setStepHistorico(int stepHistorico) {
        this.stepHistorico = stepHistorico;
    }

    public List<HistoricoAtividadesDto> getListHistoricoAtividadesDto() {
        return listHistoricoAtividadesDto;
    }

    public CartaoCredito getCartaoCreditoSelecionado() {
        return cartaoCreditoSelecionado;
    }
}
