package com.proativaservicos.service;

import java.util.Date;
import java.util.List;

import jakarta.ejb.Asynchronous;
import jakarta.ejb.Stateless;


import jakarta.inject.Inject;
import org.apache.commons.collections4.CollectionUtils;

import com.proativaservicos.dao.implemets.DaoCampanhaImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.Atendimento;
import com.proativaservicos.model.Campanha;
import com.proativaservicos.model.Empresa;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.util.DiscadorUtil;
import com.proativaservicos.util.constantes.SegmentoEnum;
import com.proativaservicos.util.constantes.TipoCampanhaEnum;

@Stateless
public class CampanhaService extends GenericProService<Campanha> {

    @Inject
    private DaoCampanhaImp dao;

    @Inject
    private EquipeService serviceEquipe;

    @Inject
    private AtendimentoService serviceAtendimento;

    @Inject
    private StatusAtendimentoService serviceStatusAtendimento;

    @Inject
    private ProdutoService serviceProduto;

    @Inject
    private StatusTelefoneService serviceStatusTelefone;

    @Inject
    private PausaService servicePausa;

    @Inject
    private FormaPagamentoService serviceFormaPagamento;

    @Inject
    private QuestionarioService serviceQuestionario;


    @Inject
    private ImportacaoService serviceImportacao;

    @Inject
    private DiscadorUtil discadorUtil;

    public Campanha pesquisarCampanha(Long idCampanha, boolean buscarLists) {

        Campanha campanha = dao.pesquisarCampanha(idCampanha);

        if (buscarLists) {

            campanha.setListEquipe(serviceEquipe.pesquisarEquipesPorCampanha(idCampanha));
            campanha.setListStatusAtendimentos(serviceStatusAtendimento.pesquisarStatusAtendimentosPorCampanha(idCampanha));
            campanha.setListProdutos(serviceProduto.pesquisarProdutoPorCampanha(idCampanha));
            campanha.setListStatusTelefone(serviceStatusTelefone.pesquisarStatusTelefonesPorCampanha(idCampanha));
            campanha.setListPausa(this.servicePausa.pesquisarPausaPorCampanha(idCampanha));
            campanha.setListFormaPagamento(this.serviceFormaPagamento.pesquisarFormaPagamentosPorCampanha(idCampanha));
            campanha.setListImportacao(serviceImportacao.pesquisarImportacaoPorCampanha(idCampanha));
            campanha.setListQuestionatios(this.serviceQuestionario.pesquisarQuestionatiosPorCampanha(idCampanha));

        }

        return campanha;
    }

    @Override
    public GenericDao<Campanha> getDAO() {

        return (GenericDao<Campanha>) this.dao;
    }

    public void excluirEquipesCampanha(Long id) {
        this.dao.excluirEquipesCampanha(id);

    }

    public List<?> pesquisarCampanhas(Campanha campanha, Date dataInicio, Date dataFim) {
        return this.dao.pesquisarCampanhas(campanha, dataInicio, dataFim);
    }

    public Campanha pesquisarCampanhaAtivoPorUsuario(Long idUsuario) {
        return this.dao.pesquisarCampanhaAtivaPorUsuario(idUsuario);
    }

    public List<Campanha> pesquisarCampanhasAtivas() {
        return this.dao.pesquisarCampanhasAtivas();
    }

    public List<SegmentoEnum> pesquisarSegmentosUtilizadosPorEmpresa(Empresa empresa, TipoCampanhaEnum tipo) {
        // TODO Auto-generated method stub
        return dao.pesquisarSegmentosUtilizadosPorEmpresa(empresa, tipo);
    }

    public List<?> pesquisarCampanhaOperacaoPorUsuario(Long id) {
        return this.dao.pesquisarCampanhaOperacaoPorUsuario(id);
    }

    public List<Campanha> pesquisarCampanhasPorEmpresa(Long id) {
        // TODO Auto-generated method stub
        return this.dao.pesquisarCampanhasPorEmpresa(id, null, null);
    }

    public List<Campanha> pesquisarCampanhaPorSupervisor(Long idUsuario, Long idEmpresa) {

        return this.dao.pesquisarCampanhaPorSupervidor(idUsuario, idEmpresa);

    }

    public Campanha pesquisarCampanhaComFila(Long campanha) {
        return this.dao.pesquisarCampanhaComFila(campanha);
    }

    public List<Object[]> pesquisarCampanhasStatusTipoEquipe(Long idEmpresa, Long idCampanha,
                                                             TipoCampanhaEnum tipoCampanha, Long statusCampanha, Long equipeCampanha, Long idUsuario) {
        return this.dao.pesquisarCampanhasStatusTipoEquipe(idEmpresa, idCampanha, tipoCampanha, statusCampanha,
                equipeCampanha, idUsuario);
    }

    public List<Campanha> pesquisarCampanhasPorEmpresa(Long idEmpresa, Long statusCampanha,
                                                       TipoCampanhaEnum tipoCampanha) {
        return this.dao.pesquisarCampanhasPorEmpresa(idEmpresa, statusCampanha, tipoCampanha);
    }

    public List<Campanha> pesquisarCampanhasPorEmpresa(Long idEmpresa, Long statusCampanha) {
        return this.dao.pesquisarCampanhasPorEmpresa(idEmpresa, statusCampanha, null);
    }

    public void limparEnviadosCampanhas() {

        this.dao.limparEnviadosCampanhas();

    }

    public List<Object[]> pesquisarCampanhasPorCpf(String cpf, Long idUsuario, Long idEmpresa) {

        return this.dao.pesquisarCampanhasPorCpf(cpf, idUsuario, idEmpresa);

    }

    public List<Object[]> pesquisarCampanhasProspect(Long idUsuario, Long idEmpresa) {
        return this.dao.pesquisarCampanhasProspect(idUsuario, idEmpresa);
    }

    public List<?> pesquisaCampanhaPorUsuario(Long idUsuario) {
        // TODO Auto-generated method stub
        return dao.pesquisarCampanhaPorUsuario(idUsuario);
    }

    @Asynchronous
    public void retrabalharCampanhaFimFila(Campanha campanha, Usuario usuario, List<Long> ids, boolean retrabalharFimFila) {

        try {


            if (campanha != null && campanha.getId() != null && usuario != null && usuario.getId() != null) {

                List<Atendimento> listAtendimentos = this.serviceAtendimento.pesquisarAtendimentosFimFilaComTelefones(campanha, ids, retrabalharFimFila);

                if (!CollectionUtils.isEmpty(listAtendimentos)) {

                    this.discadorUtil.subirCargaDiscador(campanha.getEmpresa(), campanha, listAtendimentos);

                }

            }


        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    @Asynchronous
    public void finalizarCampanhaDiscador(Campanha campanha, Usuario usuario) {

        if (campanha != null && campanha.getId() != null && usuario != null && usuario.getId() != null) {

            List<Long> listIds = this.serviceAtendimento.pesquisarAtendimentosPorCampanhaFinalizar(campanha);

            if (!CollectionUtils.isEmpty(listIds)) {

                System.out.println("Finalizando contatos discador..");

                this.discadorUtil.finalizarContatosDiscador(usuario, listIds);

            }

        }

    }

    public void atualizarCampanhaImportacaoPowerDialer(Campanha campanha) {
        this.dao.atualizarCampanhaImportacaoPowerDialer(campanha);
    }

    public List<Object[]> pesquisarCampanhasPorTelefone(String telefone, Long idUsuario, Long idEmpresa) {

        return this.dao.pesquisarCampanhasPorTelefone(telefone, idUsuario, idEmpresa);

    }

    @Asynchronous
    public void retrabalharCampanhaFimFila(Campanha campanha, Usuario usuario, List<Long> ids, List<Long> listImportacao, boolean retrabalharFimFila) {

        try {

            if (campanha != null && campanha.getId() != null && usuario != null && usuario.getId() != null) {

                List<Atendimento> listAtendimentos = this.serviceAtendimento.pesquisarAtendimentosFimFilaComTelefones(campanha, ids, listImportacao, retrabalharFimFila);

                if (!CollectionUtils.isEmpty(listAtendimentos)) {
                    this.discadorUtil.subirCargaDiscador(campanha.getEmpresa(), campanha, listAtendimentos);
                }

            }


        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    public List<Campanha> pesquisarCampanhasConsulta() {
        return this.dao.pesquisarCampanhasConsulta();
    }

    public List<Long> pesquisarIdsCampanhasConsulta() {
        return this.dao.pesquisarIdsCampanhasConsulta();
    }

    public void atualizarCampanhaAgendamento(Campanha campanha) {
        this.dao.atualizarCampanhaAgendamento(campanha);
    }

    public Campanha pesquisarCampanhaPorTipo(Long id, TipoCampanhaEnum tipoCampanhaEnum) {

       return this.dao.pesquisarCampanhaPorTipo(id,tipoCampanhaEnum);
    }
}
