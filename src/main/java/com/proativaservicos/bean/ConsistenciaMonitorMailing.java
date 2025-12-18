package com.proativaservicos.bean;

import com.proativaservicos.model.Consistencia;
import com.proativaservicos.model.ExtratorImportacao;
import com.proativaservicos.model.Loja;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.service.*;
import com.proativaservicos.util.constantes.PerfilUsuarioEnum;
import com.proativaservicos.util.constantes.StatusImportacaoExtratorEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class ConsistenciaMonitorMailing extends GenericBean {


    private static final long serialVersionUID = 1L;

    @Inject
    private LojaService serviceLoja;

    @Inject
    private ConsistenciaService serviceConsistencia;

    @Inject
    private EquipeService serviceEquipe;

    @Inject
    private ExtracaoImportacaoService serviceExtrator;

    @Inject
    private AtendimentoBackofficeService serviceAtendimentoBko;

    private List<Loja> listLojas;

    private Long[] listIdsLojas;


    private List<Consistencia> listConcistencias;

    private List<ExtratorImportacao> listImportacao;

    private Long[] listIdsImportacao;

    private List<?> listRelatorioAtendimentos;
    private List<?> listRelatorioStatusExtrator;

    private Usuario usuarioLogado;

    private Date dataInicio;

    private Date dataFim;

    private Integer quantidadeTelefones;

    private Long totalStatusExtrator;

    private Double percentExtrator;

    @PostConstruct
    public void init() {

        this.usuarioLogado = retornarUsuarioSessao();
        inicializarEntidades();

    }

    private void inicializarEntidades() {

        this.listLojas = this.serviceLoja.pesquisarLojas(retornarEmpresaMatrizUsuarioSessao().getId());

        this.listLojas = null;
        this.listConcistencias = null;

        if (this.usuarioLogado.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

            this.serviceEquipe.pesquisarCodEquipesPorSupervisor(this.usuarioLogado.getId());
            List<Long> idsEquipe = this.serviceEquipe.pesquisarCodEquipesPorSupervisor(this.usuarioLogado.getId());

            if (CollectionUtils.isNotEmpty(idsEquipe))
                this.listLojas = this.serviceLoja.pesquisarLojasPorEquipes(idsEquipe);

        } else {

            this.listLojas = this.serviceLoja.pesquisarLojas(retornarEmpresaMatrizUsuarioSessao().getId());
        }

        this.listConcistencias = this.serviceConsistencia.pesquisarConsistenciaPorEmpresa(retornarEmpresaMatrizUsuarioSessao().getId());
        this.listImportacao = this.serviceExtrator.pesquisarExtracaoImportacao(null, StatusImportacaoExtratorEnum.FINALIZADA);

    }

    public void pesquisar() {

        this.listRelatorioAtendimentos = null;
        this.quantidadeTelefones = null;

        if (this.usuarioLogado.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

            this.listRelatorioAtendimentos = this.serviceAtendimentoBko.pesquisarQuantidadeMonitorMailing(listIdsImportacao, listIdsLojas, null);
            this.quantidadeTelefones = this.serviceAtendimentoBko.pesquisarQuantidadeTelefonePorExtrator(listIdsImportacao, this.listIdsLojas);
            this.listRelatorioStatusExtrator = this.serviceAtendimentoBko.pesquisarQuantidadeMonitorMailingStatusExtrator(listIdsImportacao, listIdsLojas, null);

        } else {

            this.listRelatorioAtendimentos = this.serviceAtendimentoBko.pesquisarQuantidadeMonitorMailing(listIdsImportacao, listIdsLojas, null);
            this.quantidadeTelefones = this.serviceAtendimentoBko.pesquisarQuantidadeTelefonePorExtrator(listIdsImportacao, this.listIdsLojas);
            this.listRelatorioStatusExtrator = this.serviceAtendimentoBko.pesquisarQuantidadeMonitorMailingStatusExtrator(listIdsImportacao, listIdsLojas, null);

        }
        somarTotais();
    }


    public void somarTotais() {

        this.totalStatusExtrator = null;
        this.percentExtrator = null;

        if (CollectionUtils.isNotEmpty(this.listRelatorioStatusExtrator)) {

            List<Object[]> list = (List<Object[]>) this.listRelatorioStatusExtrator;

            this.totalStatusExtrator = Long.valueOf(0);
            this.percentExtrator = Double.valueOf(0);

            for (Object[] objects : list) {

                Long quantidade = ((Long) objects[0]).longValue();
                Double quantidadePerc = ((Double) objects[2]).doubleValue();

                this.totalStatusExtrator = this.totalStatusExtrator + quantidade;
                this.percentExtrator = this.percentExtrator + quantidadePerc;


            }

        }
    }

    public List<Loja> getListLojas() {
        return listLojas;
    }

    public void setListLojas(List<Loja> listLojas) {
        this.listLojas = listLojas;
    }

    public Long[] getListIdsLojas() {
        return listIdsLojas;
    }

    public void setListIdsLojas(Long[] listIdsLojas) {
        this.listIdsLojas = listIdsLojas;
    }

    public List<Consistencia> getListConcistencias() {
        return listConcistencias;
    }

    public void setListConcistencias(List<Consistencia> listConcistencias) {
        this.listConcistencias = listConcistencias;
    }

    public List<ExtratorImportacao> getListImportacao() {
        return listImportacao;
    }

    public void setListImportacao(List<ExtratorImportacao> listImportacao) {
        this.listImportacao = listImportacao;
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

    public Long[] getListIdsImportacao() {
        return listIdsImportacao;
    }

    public void setListIdsImportacao(Long[] listIdsImportacao) {
        this.listIdsImportacao = listIdsImportacao;
    }

    public Integer getQuantidadeTelefones() {
        return quantidadeTelefones;
    }

    public void setQuantidadeTelefones(Integer quantidadeTelefones) {
        this.quantidadeTelefones = quantidadeTelefones;
    }

    public List<?> getListRelatorioAtendimentos() {
        return listRelatorioAtendimentos;
    }

    public void setListRelatorioAtendimentos(List<?> listRelatorioAtendimentos) {
        this.listRelatorioAtendimentos = listRelatorioAtendimentos;
    }

    public List<?> getListRelatorioStatusExtrator() {
        return listRelatorioStatusExtrator;
    }

    public Long getTotalStatusExtrator() {
        return totalStatusExtrator;
    }

    public Double getPercentExtrator() {
        return percentExtrator;
    }

}
