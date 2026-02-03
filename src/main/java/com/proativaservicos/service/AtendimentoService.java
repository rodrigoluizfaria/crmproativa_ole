package com.proativaservicos.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.proativaservicos.dao.implemets.DaoAtendimentoImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.*;
import com.proativaservicos.util.constantes.*;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

@Stateless
public class AtendimentoService extends GenericProService<Atendimento> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private DaoAtendimentoImp dao;

    @Inject
    private HistoricoAtendimentoService serviceHistorico;

    @Inject
    private HistoricoAtividadeService serviceHistoricoAtividade;

    @Inject
    private HistoricoContratoService serviceHistoricoContrato;

    @Inject
    private ContratoPendenciaService serviceContratoPendencia;

    @Inject
    private TelefoneService serviceTelefone;

    @Inject
    private EnderecoService serviceEndereco;

    @Inject
    private DadosBancariosService serviceDadosBancarios;

    @Inject
    private EmailService serviceEmail;

    @Inject
    private MetaService serviceMeta;

    @Inject
    private DocumentoService serviceDocumento;

    @Inject
    private ClienteService serviceCliente;

    public GenericAtendimento pesquisarAtendimento(String cpf, Long id) {

        return pesquisarAtendimento(cpf, id, null);
    }

    private GenericAtendimento pesquisarAtendimento(String cpf, Long id, SegmentoEnum segmento) {

        // POR ESQUEMA....Object[] dados =
        // this.dao.pesquisarEsquemaPorAtendimentoPorCpf((cpf == null) ? null :
        // cpf.replaceAll("[.]", "").replaceAll("[-]", "").trim(), id);
        // String esquema = dados[1].toString();

        // SCHEMA... BUSCAR }

        GenericAtendimento atendimento = this.dao.pesquisarAtendimentoPorCpf(cpf, id);

        return detalharAtendimento(atendimento, true, segmento);
    }

    public GenericAtendimento pesquisarAtendimentoPorCpf(String cpf, Long idEmpresa, SegmentoEnum segmentoEnum) {

        Object[] line = this.dao.pesquisarEsquemaPorAtendimentoPorCpf(cpf == null ? null : cpf.replaceAll("[.]", "").replaceAll("[-]", "").trim(), idEmpresa);

        if (line == null)
            return null;

        GenericAtendimento atendimento = this.dao.pesquisarAtendimentoPorId(((BigInteger) line[0]).longValue());

        return detalharAtendimento(atendimento, true, segmentoEnum);


    }


    @SuppressWarnings({"unchecked", "rawtypes"})
    private GenericAtendimento detalharAtendimento(GenericAtendimento atendimento, boolean historico, SegmentoEnum segmento) {

        if (atendimento != null) {

            if (atendimento.getFormaPagamento() != null && atendimento.getFormaPagamento().getId() != null) {

                atendimento.setFormaPagamento(new FormaPagamento(atendimento.getFormaPagamento().getId(), atendimento.getFormaPagamento().getDescricao(), atendimento.getFormaPagamento().getParametro(), atendimento.getFormaPagamento().getAtivo()));
            }

            // INFORMAÇÕES COMPLEMENTARES
            if (StringUtils.isNotBlank(atendimento.getInformacoesComplementares())) {

                Type type = (new TypeToken<Map<String, String>>() {
                }).getType();
                atendimento.setListInformacoesComplementares(((Map) (new Gson()).fromJson(atendimento.getInformacoesComplementares(), type)));
            }

            // Buscar Documento Contrato
            if (atendimento.getContrato() != null && atendimento.getContrato().getId() != null) {

                atendimento.getContrato().setDocumentos(this.serviceDocumento.pesquisarDocumentosPorContrato(atendimento.getContrato().getId()));
            }

            if (historico) {

                atendimento.setListHistoricos(this.serviceHistorico.pesquisarHistoricoPorAtendimento(atendimento.getId()));

            }

            atendimento.setListaTelefones(this.serviceTelefone.pesquisarTelefonesPorAtendimento(atendimento.getId()));

            atendimento.setListaEnderecos(this.serviceEndereco.pesquisarEnderecoPorAtendimento(atendimento.getId()));

            atendimento.setListaDadosBancarios(this.serviceDadosBancarios.pesquisarDadosBancariosPorAtendimento(atendimento.getId()));

            atendimento.setListaEmails(this.serviceEmail.pesquisarEmailPorAtendimento(atendimento.getId()));

            if (atendimento.getEquipe() != null && atendimento.getEquipe().getConsultarAmbec() != null && atendimento.getEquipe().getConsultarAmbec()) {
                //this.serviceIntegracao ALTERAR....
            }

            return atendimento;
        }

        return null;
    }

    @Override
    public GenericDao<Atendimento> getDAO() {

        return (GenericDao<Atendimento>) this.dao;
    }

    public void deletarAtendimentoPorImportacao(Long id) {
        this.dao.deletarAtendimentosPorImportacao(id);

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<Atendimento> pesquisarAtendimentosPorCampanha(Long idCampanha, Long idImportacao, boolean possuiErro) {
        try {

            return dao.pesquisarAtendimentosPorCampanha(idCampanha, idImportacao, possuiErro);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }


    }

    public List<Atendimento> pesquisarAtendimentosPorCampanha(Campanha campanha) {

        List<Long> listIdAtendimentos = dao.pesquisarAtendimentosPorCampanha(campanha, null, false);

        if (listIdAtendimentos != null && !listIdAtendimentos.isEmpty()) {


            return dao.pesquisarAtendimentosPorId(listIdAtendimentos);

        }

        return new ArrayList<>();
    }


    public List<Long> pesquisarAtendimentosPorCampanhaFinalizar(Campanha campanha) {

        return dao.pesquisarAtendimentosPorCampanha(campanha, null, false);

    }

    public Integer pesquisarQuantidadeImportadosSaque(Long id) {
        return dao.pesquisarQuantidadeImportadosSaque(id);
    }

    public void atualizarAtendimentoSaqueComplementar(Long idAtendimento, BigDecimal valorMaxOperacao,
                                                      BigDecimal margem, BigDecimal limite, BigDecimal seguro, String beneficioPrincipal, String observacao) {

        this.dao.atualizarAtendimentoSaqueComplementar(idAtendimento, valorMaxOperacao, margem, limite, seguro,
                beneficioPrincipal, observacao);

    }

    public void atualizarAtendimentoSeguroPapCard(Long idAtendimento, BigDecimal valorMaxOperacao, BigDecimal margem, BigDecimal limite, BigDecimal seguro, String beneficioPrincipal, Date dataNascimento, Integer rating, String observacao) {

        this.dao.atualizarAtendimentoSeguroPapCard(idAtendimento, valorMaxOperacao, margem, limite, seguro, beneficioPrincipal, dataNascimento, rating, observacao);

    }

    public void atualizarAtendimentoResultsVonix(Long idAtendimento, Long status, Date dataAlteracao) {

        this.dao.atualizarAtendimentoResultsVonix(idAtendimento, status, dataAlteracao);

    }

    public void atualizarAtendimentoResultsVonix(Long idAtendimento, Long status, Date dataAlteracao, Integer quantidadeDiscado) {

        this.dao.atualizarAtendimentoResultsVonix(idAtendimento, status, dataAlteracao, quantidadeDiscado);

    }

    public void atualizarAtendimentoRefin(Long idAtendimento, BigDecimal valorMaxOperacao, BigDecimal margem, BigDecimal limite, BigDecimal taxa, BigDecimal valorLiberado, Integer quantidadeParcelas, String informacoesComplementares, String observacao) {


        this.dao.atualizarAtendimentoRefin(idAtendimento, valorMaxOperacao, margem, limite, taxa, valorLiberado, quantidadeParcelas, informacoesComplementares, observacao);

    }


    public void atualizarAtendimentoSaquePan(Long idAtendimento, BigDecimal valorMaxOperacao, BigDecimal margem, BigDecimal limite, BigDecimal taxa, BigDecimal valorLiberado, Integer quantidadeParcelas, BigDecimal valorParcela, String informacoesComplementares, String observacao) {


        this.dao.atualizarAtendimentoSaquePan(idAtendimento, valorMaxOperacao, margem, limite, taxa, valorLiberado, quantidadeParcelas, valorParcela, informacoesComplementares, observacao);

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void atualizarAtendimentoSaqueMaster(Long idAtendimento, BigDecimal valorMaxOperacao, BigDecimal margem, BigDecimal limite, String matricula, String informacoesComplementares, String observacao) {


        this.dao.atualizarAtendimentoMaster(idAtendimento, valorMaxOperacao, margem, limite, matricula, informacoesComplementares, observacao);

    }

    public void atualizarValidaCartaoBeneficio(Long idAtendimento, String observacao) {


        this.dao.atualizarValidaCartaoBeneficio(idAtendimento, observacao);

    }

    /**
     * Metodo de Pesquisa de Atendimento Por ID
     *
     * @param idAtendimento Long - identificador do atendimento
     * @return Atendimento
     */
    public Atendimento pesquisarAtendimentoPorId(Long idAtendimento) {

        GenericAtendimento atendimento = this.dao.pesquisarAtendimentoPorId(idAtendimento);

        return (Atendimento) detalharAtendimento(atendimento, true, null);

    }

    public Atendimento pesquisarAtendimentoPorBkoId(Long idAtendimento) {

        GenericAtendimento atendimento = this.dao.pesquisarAtendimentoPorBkoId(idAtendimento);

        return (Atendimento) detalharAtendimento(atendimento, true, null);

    }

    public Atendimento pesquisarAtendimentoPorIdSemDetalhar(Long idAtendimento) {

        GenericAtendimento atendimento = this.dao.pesquisarAtendimentoPorIdSemDetalhar(idAtendimento);

        if (atendimento != null) {

            atendimento.setListaTelefones(this.serviceTelefone.pesquisarTelefonesPorAtendimento(atendimento.getId()));
        }

        return (Atendimento) atendimento;

    }

    public Atendimento pesquisarAtendimentoPorIdSemDetalharComHistorico(Long idAtendimento) {

        GenericAtendimento atendimento = this.dao.pesquisarAtendimentoPorIdSemDetalhar(idAtendimento);

        if (atendimento != null) {

            atendimento.setListHistoricos(this.serviceHistorico.pesquisarHistoricoPorAtendimento(atendimento.getId()));
        }

        return (Atendimento) atendimento;

    }

    public boolean verificarExistenciaAtendimento(Long idAtendimento) {
        return dao.verificarExistenciaAtendimento(idAtendimento);
    }

    public boolean verificarTicketAtendimento(String tiket, Long idAtendimento) {
        return dao.verificarTicketAtendimento(tiket, idAtendimento);
    }


    public void atualizarAtendimentoOcupado(Long idAtendimento, Long idUsr) {
        this.dao.atualizarAtendimentoOcupado(idAtendimento, idUsr);
    }

    public List<Long> pesquisarProximoAtendimentoLotePorCampanha(Campanha campanha) {

        // List<CampanhaOrdenacao> ordenacoes =
        // this.campanhaOrdenacaoFacade.pesquisarOrdenacaoPorCampanha(campanha.getCodigo());

        List<Long> listAtendimentos = this.dao.pesquisarAtendimentosPorCampanha(campanha, null, true);

        this.dao.atualizarAtendimentosEnviado(listAtendimentos);

        return listAtendimentos;
    }

    public void resetarAtendimentosEnviados(Long campanha) {
        this.dao.resetarAtendimentosEnviados(campanha);

    }

    public void resetarAtendimentosEnviados() {
        resetarAtendimentosEnviados(null);

    }

    public int pesquisarQuantidadeImportados(Long idImportacao) {
        return dao.pesquisarQuantidadeImportados(idImportacao);
    }

    public int pesquisarQuantidadePendentes(Long idUsuario, List<String> listTiposCampanha) {
        return dao.pesquisarQuantidadePendentes(idUsuario, listTiposCampanha);
    }

    public int pesquisarQuantidadeAtendimentosAlerta(Long idUsuario) {
        return dao.pesquisarQuantidadeAtendimentosAlerta(idUsuario);
    }


    public List<?> pesquisarAtendimentosAlerta(Long id) {

        return dao.pesquisarAtendimentosAlerta(id);
    }

    public List<Object[]> pesquisarAtendimentosPendentes(Long idusuario, List<String> listTiposCampanha) {

        return dao.pesquisarAtendimentosPendentes(idusuario, listTiposCampanha);
    }

    public List<Object[]> pesquisarAtendimentosPendentesSac(Long idUsuario, Date dataInicio, Date dataFim) {
        return this.dao.pesquisarAtendimentosPendentesSac(idUsuario, dataInicio, dataFim);
    }

    public List<Object[]> pesquisarUltimosAtendimentosSac(Long idUsuario, Date dataInicio, Date dataFim) {
        return this.dao.pesquisarUltimosAtendimentosSac(idUsuario, dataInicio, dataFim);

    }

    public Long pesquisarTmaGeral(Long idUsuario) {
        return this.dao.pesquisarTmaGeral(idUsuario);
    }

    public Long pesquisarQuantidadeFinalizadosGeral(Long idUsuario, Date dataInicio, Date dataFim) {
        return this.dao.pesquisarQuantidadeFinalizadosGeral(idUsuario, dataInicio, dataFim);
    }

    public boolean validarProposta(Long idAtendimento, String adesao, InstituicaoFinanceiraEnum instituicaoFinanceiraEnum, Long idUsuario) {

        return dao.validarProposta(idAtendimento, adesao, instituicaoFinanceiraEnum, idUsuario);
    }


    public List<Object[]> pesquisarAtendimentosPorNomeCpf(String cpf, String nome, String adesao, String protocolo,
                                                          Long campanha, Long[] equipes, Long[] operador,
                                                          Long[] statusAtendimentos, Long statusContrato, Date dataInicio,
                                                          Date dataAFim, Usuario usuario, Long produto, String tiket,
                                                          Long idEmpresa, Long idMotivo, Long idSubMotivo) {

        return this.dao.pesquisarAtendimentosPorNomeCpf(cpf, nome, adesao, protocolo, campanha, getLists(equipes),
                getLists(operador), getLists(statusAtendimentos), statusContrato, dataInicio, dataAFim,
                usuario, produto, tiket, idEmpresa, idMotivo, idSubMotivo);
    }


    public List<Object[]> pesquisarAtendimentosPorNomeCpf(String cpf, String nome, String adesao, String protocolo,
                                                          Long campanha, Long equipe, Long operador,
                                                          Long statusAtendimento, Long statusContrato, Date dataInicio,
                                                          Date dataAFim, Usuario usuario, Long produto, Long idEmpresa, Long idMotivo, Long idSubmotivo) {

        return this.dao.pesquisarAtendimentosPorNomeCpf(cpf, nome, adesao, protocolo, campanha, equipe, operador,
                statusAtendimento, statusContrato, dataInicio, dataAFim, usuario, produto, idEmpresa, idMotivo, idSubmotivo);
    }

    //PESQUISA QUANTIDADE PACOTE
    public Object pesquisarQuantidadeAtendimentosPacote(Long campanha, Long equipe, Long statusAtendimento, Date dataInicio,
                                                        Date dataAFim, Usuario usuario, Long produto, Long idEmpresa) {

        return this.dao.pesquisarQuantidadeAtendimentosFiltroPacote(campanha, equipe, statusAtendimento, dataInicio, dataAFim, usuario, produto, idEmpresa);
    }

    //PESQUISA QUANTIDADE PACOTE
    public Object[] pesquisarQuantidadeMaximaTelefoneAtendimentosPacote(Long campanha, Long equipe, Long statusAtendimento, Date dataInicio,
                                                                        Date dataAFim, Usuario usuario, Long produto, Long idEmpresa) {

        return this.dao.pesquisarQuantidadeMaximaTelefoneAtendimentosFiltroPacote(campanha, equipe, statusAtendimento, dataInicio, dataAFim, usuario, produto, idEmpresa);
    }

    //PESQUISA PACOTE
    public List<Object[]> pesquisarAtendimentosPorPacote(Long campanha, Long equipe, Long statusAtendimento, Date dataInicio, Date dataAFim, Usuario usuario, Long produto, Long idEmpresa, FiltroModel filtro) {

        return this.dao.pesquisarAtendimentosPacotes(campanha, equipe, statusAtendimento, dataInicio, dataAFim, usuario, produto, idEmpresa, filtro, false);
    }


    public List<Object[]> pesquisarAtendimentosPorNomeCpfFiltro(String cpf, String nome, String adesao, String protocolo, Long campanha, Long equipe, Long operador, Long statusAtendimento, Long statusContrato, Date dataInicio,
                                                                Date dataAFim, Usuario usuario, Long produto, Long idEmpresa, FiltroModel filtro) {

        return this.dao.pesquisarAtendimentosPorNomeCpf(cpf, nome, adesao, protocolo, campanha, equipe, operador, statusAtendimento, statusContrato, dataInicio, dataAFim, usuario, produto, idEmpresa, filtro, false);
    }

    public List<Object[]> pesquisarAtendimentosPorNomeCpfFiltro(String cpf, String nome, String adesao, String protocolo, Long campanha, Long[] equipes, Long[] operador, Long[] statusAtendimentos, Long statusContrato, Date dataInicio,
                                                                Date dataAFim, Usuario usuario, Long produto, String tiket, Long idEmpresa, FiltroModel filtro) {

        return this.dao.pesquisarAtendimentosPorNomeCpf(cpf, nome, adesao, protocolo, campanha, getLists(equipes), getLists(operador), getLists(statusAtendimentos), statusContrato, dataInicio, dataAFim, usuario, produto, tiket, idEmpresa, filtro, false);
    }

    public List<Object[]> pesquisarQuantidadeAtendimentosPorNomeCpfFiltro(String cpf, String nome, String adesao, String protocolo, Long campanha, Long equipe, Long operador, Long statusAtendimento, Long statusContrato, Date dataInicio,
                                                                          Date dataAFim, Usuario usuario, Long produto, Long idEmpresa) {

        return this.dao.pesquisarQuantidadeAtendimentosPorNomeCpf(cpf, nome, adesao, protocolo, campanha, equipe, operador, statusAtendimento, statusContrato, dataInicio, dataAFim, usuario, produto, idEmpresa);
    }

    public List<Object[]> pesquisarQuantidadeAtendimentosPorNomeCpfFiltro(String cpf, String nome, String adesao, String protocolo, Long campanha, Long[] equipes, Long[] operador, Long[] statusAtendimentos, Long statusContrato, Date dataInicio,
                                                                          Date dataAFim, Usuario usuario, Long produto, String tiket, Long idEmpresa) {

        return this.dao.pesquisarQuantidadeAtendimentosPorNomeCpf(cpf, nome, adesao, protocolo, campanha, getLists(equipes), getLists(operador), getLists(statusAtendimentos), statusContrato, dataInicio, dataAFim, usuario, produto, tiket, idEmpresa);

    }


    public List<Object[]> pesquisarAtendimentosPorNomeCpfProduto(String cpf, String nome, String adesao, String protocolo, Long campanha, Long equipe, Long operador, Long statusAtendimento, Long statusContrato, Date dataInicio,
                                                                 Date dataAFim, Usuario usuario, Long idProduto, Long idEmpresa) {

        return this.dao.pesquisarAtendimentosPorNomeCpfProduto(cpf, nome, adesao, protocolo, campanha, equipe, operador,
                statusAtendimento, statusContrato, dataInicio, dataAFim, usuario, idProduto, idEmpresa);
    }

    public List<?> pesquisarAtendimentosNaoTrabalhados(String cpf, Long idEmpresa, Long equipe) {
        return this.dao.pesquisarAtendimentosNaoTrabalhados(cpf, idEmpresa, equipe);
    }

    public GenericAtendimento pesquisarAtendimentoContrato(Long idAtendimento) {

        GenericAtendimento atendimento = detalharAtendimento(dao.pesquisarAtendimentoContrato(idAtendimento), true);

        if (atendimento != null && atendimento.getContrato() != null) {

            atendimento.getContrato().setHistoricos(this.serviceHistoricoContrato.pesquisarHistoricosPorContrato(atendimento.getContrato().getId()));
            atendimento.getContrato().setPendencias(this.serviceContratoPendencia.pesquisarPendenciasContratoPorContrato(atendimento.getContrato().getId()));

        }
        return atendimento;

    }

    private GenericAtendimento detalharAtendimento(GenericAtendimento atendimento, boolean historico) {

        return detalharAtendimento(atendimento, historico, atendimento.getCampanha().getSegmento());
    }

    public GenericAtendimento pesquisarAtendimentosComHistorico(Long idAtendimento) {

        GenericAtendimento atendimento = this.dao.pesquisarAtendimentoPorId(idAtendimento);

        if (atendimento != null)
            atendimento.setListHistoricos(this.serviceHistorico.pesquisarHistoricoPorAtendimento(idAtendimento));

        return atendimento;
    }

    public List<?> pesquisarPendencias(Long idEquipe, Long idUsuario, Usuario usuario, Long idEmpresa) {

        return dao.pesquisarPendencias(idEquipe, idUsuario, usuario, idEmpresa);
    }

    public List<Atendimento> pesquisarPendenciasPorOperadores(List<Long> listOperadores) {

        return this.dao.pesquisarPendenciasPorOperadores(listOperadores);
    }

    public List<?> pesquisarMonitoramentoAtendimentos(Long idEmpresa) {

        return this.dao.pesquisarMonitoramentoAtendimentos(idEmpresa);
    }

    public List<?> pesquisarQuantidadeMonitorCampanhaCarga(Long idEmpresa, Long idCampanha, Date inicio, Date fim) {

        return this.dao.pesquisarQuantidadeMonitorCampanhaCarga(idEmpresa, idCampanha, inicio, fim);
    }

    public Integer pesquisarQuantidadeTelefonePorCampanha(Long idEmpresa, Long idCampanha) {

        return this.dao.pesquisarQuantidadeTelefonePorCampanha(idEmpresa, idCampanha);
    }

    public List<?> pesquisarQuantidadeStatusAtendimentoPorCampanha(Long idEmpresa, Long idCampanha, Date dataInicio, Date dataFim, Long[] listStatusSelect) {

        return this.dao.pesquisarQuantidadeStatusAtendimentoPorCampanha(idEmpresa, idCampanha, dataInicio, dataFim, listStatusSelect);
    }

    public List<?> pesquisarQuantidadeFinalizadosPorDia(Long idCampanha, Date dataInicio, Date dataFim) {

        return this.dao.pesquisarQuantidadeFinalizadosPorDia(idCampanha, dataInicio, dataFim);
    }

    public List<?> pesquisarQuantidadeStatusTelefonePorCampanha(Long idEmpresa, Long idCampanha, Date dataInicio, Date dataFim,
                                                                Long[] listStatusSelect) {

        return this.dao.pesquisarQuantidadeStatusTelefonePorCampanha(idEmpresa, idCampanha, dataInicio, dataFim, listStatusSelect);
    }

    public List<?> pesquisarMonitorProducao(Long idEmpresa, Usuario usuario, PeriodoEnum periodo) {

        return this.dao.pesquisarMonitorProducao(idEmpresa, usuario, periodo);
    }


    public List<?> pesquisarMonitorProjecaoAtendimento(Date dataInicio, Date dataFim, Usuario usuario,
                                                       Empresa empresa) {
        Meta meta = this.serviceMeta.pesquisarMetasPorEmpresa(empresa.getId());
        return this.dao.pesquisarMonitorProjecaoAtendimento(dataInicio, dataFim, usuario, empresa, meta);
    }

    public List<Object[]> pesquisarQuantidadeMonitorDiscagem(Long campanhaLong, Long equipeLong,
                                                             Long statusCampanhaLong, Long usuarioLong, Long empresaLong, Date dataInicio, Date dataFim) {

        return this.dao.pesquisarQuantidadeMonitorDiscagem(campanhaLong, equipeLong, statusCampanhaLong, usuarioLong, empresaLong, dataInicio, dataFim);
    }

    public List<?> pesquisarProdutividadeAtendimento(Long[] campanhaLongs, Long statusCampanhaLong,
                                                     TipoCampanhaEnum tipoCampanha, Long[] equipeLong, Long[] usuarioLong, Long idEmpresa, Long produtoLong,
                                                     Usuario usurioLogado, Date dataInicio, Date dataFim, TipoVisualizacaoEnum tipoVisualizacao) {

        return this.dao.pesquisarProdutividadeAtendimento(getLists(campanhaLongs), statusCampanhaLong, tipoCampanha, getLists(equipeLong), getLists(usuarioLong), idEmpresa, produtoLong, usurioLogado, dataInicio, dataFim, tipoVisualizacao);
    }


    public List<Long> getLists(Long... valores) {
        return (valores == null) ? null : Arrays.<Long>asList(valores);
    }

    public Map<String, Object[]> pesquisarDashBoardAtendimento(Long idEmpresa, Long[] arrayEquipesSelecionadas,
                                                               Long[] arrayUsuariosSelecionados, Date dateInicial, Date dataFinal) {

        return this.dao.pesquisarDashBoardAtendimento(idEmpresa, arrayEquipesSelecionadas, arrayUsuariosSelecionados, dateInicial, dataFinal);
    }

    public Atendimento pesquisarAtendimentoValidacaoPreditivo(Long idAtendimento) {
        return this.dao.pesquisarAtendimentoValidacaoPreditivo(idAtendimento);
    }

    public String pesquisarMaiorInformacoesComplementares(Long campanha) {

        return this.dao.pesquisarMaiorInformacoesComplementares(campanha);
    }

    public List<?> pesquisarAtendimentosPorCampanhaPlan(Long campanha) {

        return this.dao.pesquisarAtendimentosPorCampanhaPlan(campanha);
    }

    public List<?> pesquisarAtendimentosEnvio3c(Long campanha) {

        return this.dao.pesquisarAtendimentosEnvio3c(campanha);
    }


    public List<Atendimento> pesquisarAtendimentosComTelefones(Campanha campanha) {

        return this.dao.pesquisarAtendimentosComTelefones(campanha);
    }

    public List<Atendimento> pesquisarAtendimentosFimFilaComTelefones(Campanha campanha, List<Long> ids, boolean retrabalharFimFila) {

        return this.dao.pesquisarAtendimentosFimFilaComTelefones(campanha, ids, retrabalharFimFila);
    }

    public List<Atendimento> pesquisarAtendimentosFimFilaComTelefones(Campanha campanha, List<Long> ids, List<Long> importacaoes, boolean retrabalharFimFila) {

        return this.dao.pesquisarAtendimentosFimFilaComTelefones(campanha, ids, importacaoes, retrabalharFimFila);
    }

    public List<Object[]> pesquisarRelatorioAgendamentoDiarioOperador(Long idCampanha, Long idStatusCampanha,
                                                                      TipoCampanhaEnum tipoCampanha, Long idIsuario, Long idStatusAtendimento, Date dataInicio, Date dataFim,
                                                                      Long idEmpresa) {

        return this.dao.pesquisarRelatorioAgendamentoDiarioOperador(idCampanha, idStatusCampanha, tipoCampanha, idIsuario, idStatusAtendimento, dataInicio, dataFim, idEmpresa);
    }

    public void atualizarAtendimentoProposta(Long idAtendimento, String adesao, Long idUsuario, String observacao) {

        this.dao.atualizarAtendimentoProposta(idAtendimento, adesao, idUsuario, observacao);
    }

    public List<Object[]> pesquisarRelatorioStatusAtendimentoOperador(Long idCampanha, Long idStatusCampanha,
                                                                      TipoCampanhaEnum tipoCampanha, Long idEquipe, Long idUsuario, Date dataInicial, Date dataFinal, Long idEmpresa) {

        return this.dao.pesquisarRelatorioStatusAtendimentoOperador(idCampanha, idStatusCampanha, tipoCampanha, idEquipe, idUsuario, dataInicial, dataFinal, idEmpresa);
    }

    public List<Object[]> pesquisarRelatorioStatusAtendimentoData(Long idCampanha, Long idStatusCampanha,
                                                                  TipoCampanhaEnum tipoCampanha, Long idEquipe, Long idUsuario, Date dataInicial, Date dataFinal, Long idEmpresa) {
        return this.dao.pesquisarRelatorioStatusAtendimentoData(idCampanha, idStatusCampanha, tipoCampanha, idEquipe, idUsuario, dataInicial, dataFinal, idEmpresa);
    }

    public List<?> pesquisarRelatorioTimeOutSemana(List<Long> listUsuarios, Long idSupervisor, Long idEquipe, Long idCoordenador,
                                                   Long idEmpresa, Date dataInicio, Date dataFim) {

        return this.dao.pesquisarRelatorioTimeOutSemana(listUsuarios, idSupervisor, idEquipe, idCoordenador, idEmpresa, dataInicio, dataFim);

    }

    public List<?> pesquisarRelatorioTimeOut(List<Long> listUsuarios, Long idSupervisor, Long idEquipe, Long idCoordenador,
                                             Long idEmpresa, String filtro, Date dataInicio, Date dataFim) {

        return this.dao.pesquisarRelatorioTimeOut(listUsuarios, idSupervisor, idEquipe, idCoordenador, idEmpresa, filtro, dataInicio, dataFim);

    }

    public List<?> pesquisarAtendimentosNaoTrabalhadosPorTelefone(String cpf, Long idEmpresa, Long idEquipe) {
        return this.dao.pesquisarAtendimentosNaoTrabalhadosPorTelefone(cpf, idEmpresa, idEquipe);
    }

    public List<Object[]> pesquisarAtendimentosPorTelefone(String numeroTelefone, Long idEmpresa) {
        return this.dao.pesquisarAtendimentosPorTelefone(numeroTelefone, idEmpresa);
    }

    public List<Object[]> pesquisarAtendimentosPorTelefone(String numeroTelefone, Long idCampanha, Long idEmpresa) {
        return this.dao.pesquisarAtendimentosPorTelefone(numeroTelefone, idCampanha, idEmpresa);
    }

    public GenericAtendimento pesquisarAtendimentoPorTelefone(String numeroTelefone, Long idCampanhaPropect, Long idEmpresa) {

        return pesquisarAtendimentoPorTelefone(numeroTelefone, null, idCampanhaPropect, idEmpresa);
    }


    public GenericAtendimento pesquisarAtendimentoPorTelefone(String numeroTelefone, String cpf, Long idCampanhaPropect, Long idEmpresa) {

        Object[] obj = this.dao.pesquisarAtendimentoPorTelefone(numeroTelefone, cpf, idCampanhaPropect, idEmpresa);

        if (obj == null)
            return null;


        GenericAtendimento atendimento = this.dao.pesquisarAtendimentoPorId(((BigInteger) obj[0]).longValue());
        detalharAtendimento(atendimento, true);

        return atendimento;
    }

    public List<?> pesquisarAtendimentosConciliarAnexo(Long idEmpresa, Boolean conciliado) {

        return this.dao.pesquisarAtendimentosConciliarAnexo(idEmpresa, conciliado);
    }

    public List<?> pesquisarAtendimentosConciliarAnexo(Long idEmpresa, Loja loja, Boolean conciliado) {

        return this.dao.pesquisarAtendimentosConciliarAnexo(idEmpresa, loja, conciliado);
    }

    public List<?> pesquisarAtendimentosConciliarAnexo(Long idEmpresa, boolean conciliar, Date dataInicio, Date dataFim) {

        return this.dao.pesquisarAtendimentosConciliarAnexo(idEmpresa, conciliar, dataInicio, dataFim);
    }

    public List<?> pesquisarAtendimentosConciliarAnexo(Long idEmpresa, boolean conciliar, String adesao, String cpf, Long idProduto, Date dataInicio, Date dataFim) {

        return this.dao.pesquisarAtendimentosConciliarAnexo(idEmpresa, conciliar, adesao, cpf, idProduto, dataInicio, dataFim);
    }

    public void atualizarAtendimentosConciliados(List<Long> idsAtendimentos, boolean conciliado) {
        this.dao.atualizarAtendimentosConciliados(idsAtendimentos, conciliado);

    }

    public void atualizarAtendimentoConciliado(Long idAtendimento, boolean conciliado) {
        this.dao.atualizarAtendimentoConciliado(idAtendimento, conciliado);

    }

    public BigDecimal pesquisarSpinPorCampanha(Long idCampanha) {

        return this.dao.pesquisarSpinPorCampanha(idCampanha);
    }


    public Object[] pesquisarAnsaliseCampanha(Long campanha) {

        return this.dao.pesquisarAnsaliseCampanha(campanha);
    }

    public Atendimento pesquisarPropostasPorAdesao(String adesao) {
        // TODO Auto-generated method stub
        return this.dao.pesquisarAtendimentoPorAdesao(adesao);
    }

    public void removerObservacaoValorMaximo(Long importacao, Long campanha) {

        this.dao.removerObservacaoValorMaximo(importacao, campanha);

    }

    public List<Atendimento> pesquisarAtendimentosConsulta(String cpf, Long empresa) {

        return this.dao.pesquisarAtendimentosConsulta(cpf, empresa);
    }

    public Atendimento pesquisarAtendimentosConsulta(String cpf, SegmentoEnum segmentoEnum, Long empresa) {

        return pesquisarAtendimentosConsultaAtivo(cpf, segmentoEnum, empresa, true);
    }

    public Atendimento pesquisarAtendimentosConsultaAtivo(String cpf, SegmentoEnum segmentoEnum, Long empresa, boolean campanhaConsulta) {

        return this.dao.pesquisarAtendimentosConsulta(cpf, segmentoEnum, empresa, campanhaConsulta);
    }

    public List<Atendimento> pesquisarAtendimentosSacPorCpf(String cpf) {
        return this.dao.pesquisarAtendimentosSacPorCpf(cpf);

    }

    public Atendimento pesquisarAtendimentoSacPorCodigo(Long idAtendimento) {
        return pesquisarAtendimentoSacPorCodigo(idAtendimento, false);
    }

    public Atendimento pesquisarAtendimentoSacPorPrococolo(String protocolo) {

        Long idAtendimento = this.dao.pesquisarIdAtendimentoSacPorProtocolo(protocolo);

        if (idAtendimento != null)
            return pesquisarAtendimentoSacPorCodigo(idAtendimento, true);

        return null;
    }

    public Atendimento pesquisarAtendimentoSacPorCodigo(Long idAtendimento, boolean carregarListas) {

        Atendimento atendimento = this.dao.pesquisarAtendimentoSacPorCodigo(idAtendimento);

        if (atendimento != null) {
            if (atendimento.getCliente() != null)
                atendimento.setCliente(this.serviceCliente.pesquisarClienteAtendimentoSacPorId(atendimento.getCliente().getId()));
        }

        if (atendimento != null && carregarListas) {

            carregarListasAtendimento(atendimento);

            if (atendimento.getAtendimentoPai()) {

                atendimento.setListAtendimentosFilhas(this.dao.pesquisarAtendimentosFilhos(atendimento.getProtocolo()));


            } else {

                Long atn = this.dao.pesquisarIdAtendimentoSacPorProtocolo(atendimento.getProtocoloPai());

                if (atn != null) {
                    atendimento.setAtendimentoPaiObj(this.dao.pesquisarAtendimentoSacPorCodigo(atn));
                    // carregarListasAtendimento(atendimento.getAtendimentoPaiObj());
                }
            }
        }


        return atendimento;
    }

    private void carregarListasAtendimento(Atendimento atendimento) {

        if (atendimento != null && atendimento.getId() != null) {

            atendimento.setListHistoricos(this.serviceHistorico.pesquisarHistoricoSacPorAtendimento(atendimento.getId()));

            if (StringUtils.isNotBlank(atendimento.getProtocolo()))
                atendimento.setListHistoricoAtividades(this.serviceHistoricoAtividade.pesquisarHistoricoAtividadePorProtocolo(atendimento.getProtocolo()));


        }

    }

    public List<Atendimento> pesquisarAtendimentosSacPorCliente(Long codigoCliente) {
        return this.dao.pesquisarAtendimentosSacPorCliente(codigoCliente);

    }

    public void deletarAtendimentoSemClassificacao(Long idCliente, String protocolo) {
        this.dao.deletarAtendimentoSemClassificacao(idCliente, protocolo);

    }

    public void deletarAtendimentoSemClassificacaoCodAtendimento(Long idAtendimento, String protocolo) {
        this.dao.deletarAtendimentoSemClassificacaoCodAtendimento(idAtendimento, protocolo);
    }

    public Long buscarQuantidadeClientesAtendidosDiario(Long idUsuario, String protocoloAtual) {
        return this.dao.buscarQuantidadeClientesAtendidosDiario(idUsuario, protocoloAtual);
    }
    public Long buscarQuantidadeAtendimentosDoClienteUltimos7Dias(Long idCliente, String protocoloAtual) {
        return this.dao.buscarQuantidadeAtendimentosDoClienteUltimos7Dias(idCliente, protocoloAtual);
    }

    public List<Atendimento> pesquisarAtendimentosDerivados() {

        return this.dao.pesquisarAtendimentosDerivados();
    }

    public List<Atendimento> pesquisarAtendimentosSacFiltros(String filtroProtocolo, String filtroCpf, Boolean encerrado, Long idDepartamento) {
        return this.dao.pesquisarAtendimentosSacFiltros(filtroProtocolo, filtroCpf, encerrado, idDepartamento);
    }

    public List<Atendimento> pesquisarAtendimentosSacFiltros(String filtroProtocolo, String filtroCpf, List<Long> listDpo) {
        return this.dao.pesquisarAtendimentosSacFiltros(filtroProtocolo, filtroCpf, listDpo);
    }

    public List<Atendimento> pesquisarAtendimentosSacFiltrosDepartamento(String filtroProtocolo, String filtroCpf, Boolean encerrado, List<Long> departamentos, Long idDepartamento) {
        return this.dao.pesquisarAtendimentosSacFiltrosDepartamentos(filtroProtocolo, filtroCpf, encerrado, departamentos, idDepartamento);
    }

    public List<?> listarQuantidadeResumoDerivadosSac(Date dataInicio, Date dataFim, Long codUsuario) {

        return this.dao.listarQuantidadeResumoDerivadosSac(dataInicio, dataFim, codUsuario);

    }

    public List<Object[]> buscarQuantidadePorMotivo(Date dataInicio, Date dataFim, Long codUsuario) {
        return this.dao.buscarQuantidadePorMotivo(dataInicio, dataFim, codUsuario);
    }

    public List<Object[]> buscarTop10Usuarios(Date dataInicio, Date dataFim) {
        return this.dao.buscarTop10Usuarios(dataInicio, dataFim);
    }

    public String buscarTmaAtendimentosSac(Date dataInicio, Date dataFim, Long codUsuario) {
        return this.dao.buscarTmaAtendimentosSac(dataInicio, dataFim, codUsuario);
    }

    public List<Object[]> buscarQuantidadePorStatus(Date dataInicio, Date dataFim, Long codUsuario) {
        return this.dao.buscarQuantidadePorStatus(dataInicio, dataFim, codUsuario);
    }


    public void atualizarProtocoloDataFimAtendimento(Long idCliente, String protocoloPai, Date dataFim) {
        this.dao.atualizarProtocoloDataFimAtendimento(idCliente, protocoloPai, dataFim);
    }

    public void atualizarProtocoloDataFimAtendimentoPorProtocolo(String protocoloPai, Date dataFim) {
        this.dao.atualizarProtocoloDataFimAtendimentoPorProtocolo(protocoloPai, dataFim);
    }

    public void atualizarAtendimentoAnonimo(boolean anonimo, Long codCliente, Long idAtendimento) {
        this.dao.atualizarAtendimentoAnonimo(anonimo, codCliente, idAtendimento);
    }

    public void atualizarAtendimentoSac(Long id, Long status, String observacao, Date dataAlteracao, Long usuario) {
        this.dao.atualizarAtendimentoSac(id, status, observacao, dataAlteracao, usuario);
    }

    public Atendimento buscarUltimoAtendimentoDoCliente(Long idCliente, Long idMotivo, Long subMotivo, Date dataLimite, Long idAtendimentoAtual) {
        return this.dao.buscarUltimoAtendimentoDoCliente(idCliente, idMotivo, subMotivo, dataLimite, idAtendimentoAtual);
    }

    public void alterarFrc(Long idAtendimento, Boolean frc) {
        this.dao.alterarFrc(idAtendimento,frc);
    }

    public Atendimento buscarUltimaInteracao(Long idCliente, Long idMotivo, Long subMotivo, Long idAtual) {
        return this.dao.buscarUltimaInteracao(idCliente, idMotivo, subMotivo, idAtual);
    }

    public boolean verificarSeClienteEhReincidente(Long idCliente,String protocoloPai) {
        return this.dao.verificarSeClienteEhReincidente(idCliente,protocoloPai);
    }
}
