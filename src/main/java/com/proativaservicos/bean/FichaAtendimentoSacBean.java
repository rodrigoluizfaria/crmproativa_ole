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
import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    public void iniciarPreAtendimento() {

        ///SALVAR
        this.atendimentoIniciado = true;
    }

    public void salvarAtendimento() {
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
            this.atendimento.setValorLiberado(new BigDecimal("10258.52"));
            this.atendimento.setValorCartaCredito(new BigDecimal("7443.09"));
            this.atendimento.setValorSaque(3085.66);
            this.atendimento.setLimite(new BigDecimal("10285.54"));
            this.atendimento.setValorLiberado(new BigDecimal("7199.88"));
            this.atendimento.setProtocolo(gerarProtocolo());

            CartaoCredito cartaoCredito = new CartaoCredito();
            cartaoCredito.setNumeroCartao("5117233260721226");
            cartaoCredito.setValidade("09/2027");
            cartaoCredito.setCodigoSeguranca(616);
            cartaoCredito.setTipo("Múltiplo");
            cartaoCredito.setCartaoAdicional(Boolean.FALSE);
            cartaoCredito.setBandeira("Visa");
            cartaoCredito.setStatus("BLOQUEADO");

            this.atendimento.addCartao(cartaoCredito);


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

    private void carregarAtividadesAtendimentos() {

        // Mocado === TEM QUE PEGAR DE OUTRA MANEIRA....
        this.listHistoricoAtividadesDto = new ArrayList<>();

        listHistoricoAtividadesDto.add(new HistoricoAtividadesDto(TipoStatusAtividadesEnum.SOLICITACAO_AUMENTO_LINITE,
                "De R$ 15.000 para R$ 22.222", "Motivo: Viagem internacional", new Date(), "voce","pi-user"));

        listHistoricoAtividadesDto.add(new HistoricoAtividadesDto(TipoStatusAtividadesEnum.ATENDIMENTO_CLASSIFICADO,
                "Cartão | Limite → Consulta Disponível", "Consulta realizada com sucesso",new Date(), "Você", "pi-user"));

        listHistoricoAtividadesDto.add(new HistoricoAtividadesDto(TipoStatusAtividadesEnum.ATENDIMENTO_CLASSIFICADO,
                "Cartão | Limite → Solicitação Aumento", "Cliente questionou taxas",new Date(), "Você", "pi-user"));

        listHistoricoAtividadesDto.add(new HistoricoAtividadesDto(TipoStatusAtividadesEnum.ATENDIMENTO_CLASSIFICADO,
                "Cartão | Limite → Solicitação Aumento", "Simulação realizada",new Date(), "Você", "pi-user"));

        listHistoricoAtividadesDto.add(new HistoricoAtividadesDto(TipoStatusAtividadesEnum.INICIO_ATENDIMENTO,
                "Cliente conectado via URA", "Protocolo gerado automaticamente", new Date(),"Sistema", "pi-phone"));

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

    }

    public void setStepClassificacao(int step) {
        this.stepClassificacao = step;


        if (step == 0) {
            this.motivoSelecionado = null;
            this.subMotivoSelecionado = null;
        } else if (step == 1) {
            this.subMotivoSelecionado = null;
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
}
