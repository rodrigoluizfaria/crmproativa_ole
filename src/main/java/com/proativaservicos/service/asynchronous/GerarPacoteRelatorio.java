package com.proativaservicos.service.asynchronous;

import com.proativaservicos.model.FiltroModel;
import com.proativaservicos.model.PacoteArquivos;
import com.proativaservicos.service.AtendimentoService;
import com.proativaservicos.service.ServiceAbstract;
import com.proativaservicos.service.TelefoneService;
import com.proativaservicos.util.ArquivoUtil;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.constantes.EstadoPacoteEnum;
import jakarta.annotation.Resource;
import jakarta.ejb.*;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.UserTransaction;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.attribute.FileAttribute;
import java.util.*;


@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class GerarPacoteRelatorio implements Serializable {

    @Resource
    private UserTransaction transaction;

    @EJB
    private ServiceAbstract abstractService;


    @Inject
    private AtendimentoService serviceAtendimento;

    @Inject
    private TelefoneService serviceTelfone;

    private PacoteArquivos pacotes;

    private Integer totalPesquisa;


    private String cabecalho = "ID Atendimento;Campanha;Cliente;CPF;Status Atendimento;Último Contato;Operador;Adesão;Quantidade Parcela;Valor Parcela;Valor Liberado;Protocolo;Status do Contrato;Valor Emprestimo;Entidade Principal;Entidade Secundária;Orgão Principal;Orgão Secudário;Outras Informações;Produto;Tempo pós Atentimento;Ticket";

    @Asynchronous
    public void gerarPacotes(PacoteArquivos pacote) {

        try {

            System.out.println("Iniciando geração de pacote....");

            this.pacotes = pacote;

            inicializarTransacao();

            this.pacotes.setEstadoPacote(EstadoPacoteEnum.PROCESSANDO);

            this.abstractService.alterar(this.pacotes);

            this.transaction.commit();

            inicializarTransacao();

            Object ob = this.serviceAtendimento.pesquisarQuantidadeAtendimentosPacote(this.pacotes.getCampanha(), this.pacotes.getEquipe(), this.pacotes.getStatusAtendimento(), this.pacotes.getDataInicialPesquisa(), this.pacotes.getDataFinalPesquisa(), this.pacotes.getUsuarioSolicitado(), this.pacotes.getProduto(), this.pacotes.getIdEmpresa());

            Object[] obMaxTel = null;
            Integer quantidadeMaxTel = null;


            BigInteger quantidade = null;

            if (ob != null)
                quantidade = (BigInteger) ob;


            if (quantidade == null || quantidade.equals(BigInteger.ZERO)) {

                inicializarTransacao();
                this.pacotes.setEstadoPacote(EstadoPacoteEnum.CONCLUIDO);
                this.pacotes.setDataFinal(new Date());
                this.pacotes.setLog("Nenhum registro encontrado na pesquisa.");
                this.abstractService.alterar(this.pacotes);
                this.transaction.commit();


            } else {

                inicializarTransacao();
                this.totalPesquisa = Integer.valueOf(quantidade.intValue());
                this.pacotes.setQuantidadeLinhas(this.totalPesquisa);
                this.pacotes.setIdentificador(retornarStringIdentificador());

                File arquivo = retornarArquivo();
                this.pacotes.setArquivo(arquivo.getName());
                this.pacotes.setDiretorio(arquivo.getParent());
                this.abstractService.alterar(this.pacotes);
                this.transaction.commit();

                if (this.pacotes.getPesquisarTelefone() != null && this.pacotes.getPesquisarTelefone().equals(Boolean.TRUE)) {

                    obMaxTel = this.serviceAtendimento.pesquisarQuantidadeMaximaTelefoneAtendimentosPacote(this.pacotes.getCampanha(), this.pacotes.getEquipe(), this.pacotes.getStatusAtendimento(), this.pacotes.getDataInicialPesquisa(), this.pacotes.getDataFinalPesquisa(), this.pacotes.getUsuarioSolicitado(), this.pacotes.getProduto(), this.pacotes.getIdEmpresa());

                    if (obMaxTel != null && obMaxTel.length == 2) {

                        quantidadeMaxTel = ((BigInteger) obMaxTel[1]).intValue();
                    }

                }

                iniciarConsultaPacoteNaoRecursivo(Integer.valueOf(0), quantidade.intValue(), arquivo, true, quantidadeMaxTel);

                System.out.println("TOTAL DE ATENDIMENTOS ENCONTRADOS NA PESQUISA: " + quantidade + " | CRIANDO PACOTE");

                inicializarTransacao();
                this.pacotes.setEstadoPacote(EstadoPacoteEnum.CONCLUIDO);
                this.pacotes.setLog("concluído com sucesso.");
                this.pacotes.setDataFinal(new Date());
                this.abstractService.alterar(this.pacotes);
                this.transaction.commit();

            }


        } catch (Exception e) {

            e.printStackTrace();

            try {

                if (this.transaction.getStatus() == 0) {
                    this.transaction.rollback();
                }

                if (this.pacotes != null && this.pacotes.getId() != null) {

                    inicializarTransacao();
                    this.pacotes.setEstadoPacote(EstadoPacoteEnum.ERRO);
                    this.pacotes.setQuantidadeLinhas(0);
                    this.pacotes.setDataFinal(new Date(System.currentTimeMillis()));
                    this.pacotes.setLog("Erro: " + e.getMessage());
                    this.abstractService.alterar(this.pacotes);
                    this.transaction.commit();
                }

            } catch (Exception e1) {

                e1.printStackTrace();
            }

        } finally {

            System.out.println("fim da criação do pacote relatório.");
        }

    }


    private void iniciarConsultaPacoteNaoRecursivo(Integer fistResult, Integer maxResults, File arquivo, boolean inicio, Integer quantidadeMaxTel) {

        System.out.println("GERANDO PACOTES POR LOTE...");

        if (inicio) {

            gerarCabecalho(arquivo, quantidadeMaxTel);

        }

        for (int i = 0; i < maxResults.intValue() / 1000 + ((maxResults.intValue() % 1000 > 0) ? 1 : 0); i++) {

            escreverPacotes(fistResult, Integer.valueOf(1000), arquivo);
            fistResult += 1000;

        }


    }

    private void escreverPacotes(Integer fistResult, Integer maxResults, File arquivo) {

        FiltroModel filtro = new FiltroModel();

        filtro.setFistResult(fistResult.intValue());

        filtro.setMaxResult(maxResults);

        List<Object[]> listAtendimentos = this.serviceAtendimento.pesquisarAtendimentosPorPacote(this.pacotes.getCampanha(), this.pacotes.getEquipe(), this.pacotes.getStatusAtendimento(), this.pacotes.getDataInicialPesquisa(), this.pacotes.getDataFinalPesquisa(), this.pacotes.getUsuarioSolicitado(), this.pacotes.getProduto(), this.pacotes.getIdEmpresa(), filtro);

        List<Object[]> listAtendimentosGerados = new ArrayList<>();

        if (this.pacotes.getPesquisarTelefone()) {

            Map<Long, List<Object>> mapaTelefones = criarListTelefones(listAtendimentos);

            for (Object[] atendimento : listAtendimentos) {

                List<Object> listTelefones = mapaTelefones.get(Long.valueOf((((BigInteger) atendimento[0]).longValue())));

                listAtendimentosGerados.add(ArrayUtils.addAll(atendimento, new Object[0]));

                if (CollectionUtils.isNotEmpty(listTelefones)) {

                    listAtendimentosGerados.add(ArrayUtils.addAll(atendimento, listTelefones.toArray(new Object[listTelefones.size()])));
                    continue;
                }


            }
        }


        if (this.pacotes.getPesquisarTelefone())
            listAtendimentos = listAtendimentosGerados;


        String dados = ArquivoUtil.gerarArquivoCSVStringSemCabecalho(listAtendimentos);
        ArquivoUtil.geraLogCsv(arquivo, dados.trim());
        dados = null;
        listAtendimentos = null;
        listAtendimentosGerados = null;


    }

    private void iniciarConsultaPacote(Integer fistResult, Integer maxResults, File arquivo, boolean inicio, Integer quantidadeMaxTel) {

        if (inicio) {

            gerarCabecalho(arquivo, quantidadeMaxTel);

        }

        if (totalPesquisa != null && totalPesquisa.intValue() > 0 && totalPesquisa.intValue() > fistResult.intValue()) {

            FiltroModel filtro = new FiltroModel();

            filtro.setFistResult(fistResult.intValue());

            filtro.setMaxResult(maxResults);

            List<Object[]> listAtendimentos = this.serviceAtendimento.pesquisarAtendimentosPorPacote(this.pacotes.getCampanha(), this.pacotes.getEquipe(), this.pacotes.getStatusAtendimento(), this.pacotes.getDataInicialPesquisa(), this.pacotes.getDataFinalPesquisa(), this.pacotes.getUsuarioSolicitado(), this.pacotes.getProduto(), this.pacotes.getIdEmpresa(), filtro);
            List<Object[]> listAtendimentosGerados = new ArrayList<>();

            if (this.pacotes.getPesquisarTelefone()) {

                Map<Long, List<Object>> mapaTelefones = criarListTelefones(listAtendimentos);

                for (Object[] atendimento : listAtendimentos) {

                    List<Object> listTelefones = mapaTelefones.get(Long.valueOf((((BigInteger) atendimento[0]).longValue())));

                    listAtendimentosGerados.add(ArrayUtils.addAll(atendimento, new Object[0]));

                    if (CollectionUtils.isNotEmpty(listTelefones)) {

                        listAtendimentosGerados.add(ArrayUtils.addAll(atendimento, listTelefones.toArray(new Object[listTelefones.size()])));
                        continue;
                    }


                }
            }

            if (this.pacotes.getPesquisarTelefone())
                listAtendimentos = listAtendimentosGerados;


            fistResult += 1000;
            String dados = ArquivoUtil.gerarArquivoCSVStringSemCabecalho(listAtendimentos);
            ArquivoUtil.geraLogCsv(arquivo, dados.trim());
            dados = null;
            listAtendimentos = null;
            listAtendimentosGerados = null;
            iniciarConsultaPacote(fistResult, maxResults, arquivo, false, quantidadeMaxTel);

        }

    }

    public void gerarCabecalho(File arquivo, Integer quantidadeMaxTel) {

        if (this.pacotes.getPesquisarTelefone() != null && this.pacotes.getPesquisarTelefone().equals(Boolean.TRUE) && quantidadeMaxTel != null) {

            System.out.println("MAAXIMO TEL: " + quantidadeMaxTel);

            for (int i = 0; i < quantidadeMaxTel.intValue(); i++) {

                this.cabecalho = this.cabecalho + ";DDD";
                this.cabecalho = this.cabecalho + ";telefone";
                this.cabecalho = this.cabecalho + ";Status Telefone";

            }

        }
        ArquivoUtil.geraLogCsv(arquivo, this.cabecalho);
    }

    private String retornarStringIdentificador() {

        String nomeArquivo = DateUtil.builder(this.pacotes.getDataInicialPesquisa()).formatarDataParaString("yyyyMMdd").getDataTexto();
        nomeArquivo = nomeArquivo + "_" + DateUtil.builder(this.pacotes.getDataFinalPesquisa()).formatarDataParaString("yyyyMMdd").getDataTexto();
        nomeArquivo = nomeArquivo + "_" + StringUtils.leftPad(String.valueOf(this.pacotes.getId()), 5, "0");
        return nomeArquivo;
    }

    private String retornaNomeArquivo() {


        return System.getProperty("user.home") + File.separator + "proativa" + File.separator + "pacotes_csv" + File.separator + retornarStringIdentificador() + ".csv";
    }

    private File retornarArquivo() throws IOException {

        File file = new File(retornaNomeArquivo());

        File diretorio = new File(file.getParent());

        if (!diretorio.exists()) {
            Files.createDirectories(diretorio.toPath(), (FileAttribute<?>[]) new FileAttribute[0]);
        }


        Files.deleteIfExists(file.toPath());

        return file;

    }

    private void inicializarTransacao() {

        try {
            if (this.transaction.getStatus() == 1) {
                this.transaction.rollback();
            }

            if (this.transaction.getStatus() != 0) {
                this.transaction.begin();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private Map<Long, List<Object>> criarListTelefones(List<Object[]> listAtendimentos) {

        List<Long> idsAtendimentos = new ArrayList<>();

        List<Object[]> listTelefones = new ArrayList<Object[]>();

        for (Object[] atendimento : listAtendimentos) {

            idsAtendimentos.add(Long.valueOf(((BigInteger) atendimento[0]).longValue()));

            if (idsAtendimentos.size() == 1000) {

                listTelefones.addAll(this.serviceTelfone.pesquisarTelefonesPorAtendimentos(idsAtendimentos));

                idsAtendimentos = new ArrayList<>();
            }

        }

        if (idsAtendimentos.size() > 0) {

            listTelefones.addAll(this.serviceTelfone.pesquisarTelefonesPorAtendimentos(idsAtendimentos));
            idsAtendimentos = new ArrayList<Long>();
        }

        Map<Long, List<Object>> mapTelefones = new HashMap<Long, List<Object>>();

        if (CollectionUtils.isNotEmpty(listTelefones)) {

            for (Object[] telefone : listTelefones) {

                ((List<Object>) mapTelefones.computeIfAbsent(Long.valueOf(((BigInteger) telefone[0]).longValue()), t -> new ArrayList<Object>())).add((telefone[1] == null) ? "" : telefone[1]);

                ((List<Object>) mapTelefones.get(Long.valueOf(((BigInteger) telefone[0]).longValue()))).add((telefone[2] == null) ? "" : telefone[2]);

                ((List<Object>) mapTelefones.get(Long.valueOf(((BigInteger) telefone[0]).longValue()))).add((telefone[3] == null) ? "" : telefone[3]);
            }

        }

        return mapTelefones;

    }

}
