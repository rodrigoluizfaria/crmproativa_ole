package com.proativaservicos.util;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.proativaservicos.model.*;
import com.proativaservicos.model.argus.MailingArgus;
import com.proativaservicos.model.trescplus.MailingEnvio3c;
import com.proativaservicos.service.AtendimentoService;
import com.proativaservicos.service.DadosBancariosService;
import com.proativaservicos.service.HistoricoAtendimentoService;
import com.proativaservicos.service.TelefoneService;
import jakarta.inject.Inject;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.attribute.FileAttribute;
import java.util.*;
import java.util.stream.Collectors;

public class ProcessarCampanhaCsv implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private AtendimentoService serviceAtendimento;

    @Inject
    private HistoricoAtendimentoService serviceHistoricoAtendimento;

    @Inject
    private TelefoneService serviceTelefone;

    @Inject
    private DadosBancariosService serviceDadosBancarios;

    private Integer quantidadeTelefones;

    private Integer quantidadeHistoricos;

    private Integer quantidadeDadosBancarios;

    private LinkedHashMap<String, String> mapHeader;

    private TreeSet<String> listCabecalhoInformacoesComplementares;

    private Long campanha;

    public byte[] executar(Long idCampanha) throws Exception {

        this.campanha = idCampanha;

        String arrayCabecalho[] = gerarCabecalho();

        List<String[]> listLinhas = gerarLinhas();

        byte[] file = ArquivoUtil.gerarArquivoCSV(arrayCabecalho, listLinhas);

        arrayCabecalho = null;
        listLinhas = null;

        return file;

    }

    public MailingEnvio3c criarArquivoCampanha3c(List<Atendimento> atendimentoList) throws Exception {

        String[] arrayCabecalho = gerarCabecalhoCampanha3c(atendimentoList);

        List<String[]> listLinhas = gerarLinhasEnvio3c(atendimentoList);

        byte[] file = ArquivoUtil.gerarArquivoCSV(arrayCabecalho, listLinhas);

        if (CollectionUtils.isEmpty(listLinhas))
            return null;

        // salvarArquivoMailing(new ByteArrayInputStream(file), "teste_3c.csv", "campanhas_3c");

        return new MailingEnvio3c(arrayCabecalho, listLinhas, this.quantidadeTelefones, this.mapHeader, file);

    }


    public MailingArgus criarArquivoCampanhaArgus(List<Atendimento> atendimentoList) throws Exception {

        String[] arrayCabecalho = gerarCabecalhoCampanhaArgus(atendimentoList);

        List<String[]> listLinhas = gerarLinhasEnvioArgus(atendimentoList);

        byte[] file = ArquivoUtil.gerarArquivoCSV(arrayCabecalho, listLinhas);

        if (CollectionUtils.isEmpty(listLinhas))
            return null;


        return new MailingArgus(arrayCabecalho, listLinhas, this.quantidadeTelefones, this.mapHeader, file);

    }


    private String[] gerarCabecalho() {

        List<String> listCabecalhos = new ArrayList<String>();

        this.quantidadeHistoricos = 1;

        this.quantidadeDadosBancarios = this.serviceDadosBancarios.pesquisarQuantidadeDadosBancariosPorCampanha(this.campanha);

        this.quantidadeTelefones = this.serviceTelefone.pesquisarQuantidadeTelefonesPorCampanha(this.campanha);

        listCabecalhos.add("id");
        listCabecalhos.add("Data Cadastro");
        listCabecalhos.add("Nome");
        listCabecalhos.add("CPF");
        listCabecalhos.add("Data de Nascimento");
        listCabecalhos.add("Número Documento");
        listCabecalhos.add("Orgão Emissor");
        listCabecalhos.add("Nome da Mãe");
        listCabecalhos.add("Status do Atendimento");
        listCabecalhos.add("Usuário");
        listCabecalhos.add("Produto");
        listCabecalhos.add("Benefício principal");
        listCabecalhos.add("Benefício secundário");
        listCabecalhos.add("Entidade principal");
        listCabecalhos.add("Entidade secundário");
        listCabecalhos.add("Orgão principal");
        listCabecalhos.add("Orgão secundário");
        listCabecalhos.add("Margem");
        listCabecalhos.add("Salario");
        listCabecalhos.add("Limite");
        listCabecalhos.add("Descontos compulsórios");
        listCabecalhos.add("Descontos facultativos");
        listCabecalhos.add("Valor máximo da operação");
        listCabecalhos.add("valor Seguro");
        listCabecalhos.add("Observação");
        listCabecalhos.add("Valor parcela");
        listCabecalhos.add("Valor liberado");
        listCabecalhos.add("Número da adesão");
        listCabecalhos.add("Taxa");
        listCabecalhos.add("Saldo devedor");
        listCabecalhos.add("Valor Total");
        listCabecalhos.add("Data Inicio");
        listCabecalhos.add("Data fim");
        listCabecalhos.add("Discou");
        listCabecalhos.add("Outras informações"); // 31
        listCabecalhos.add("Raing BMG MED"); //32

        if (this.quantidadeHistoricos != null && this.quantidadeHistoricos.intValue() > 0) {

            for (int i = 0; i < this.quantidadeHistoricos.intValue(); i++) {

                listCabecalhos.add("Operador");
                listCabecalhos.add("Status Historico");
                listCabecalhos.add("Data Contato");
                listCabecalhos.add("Data agendamento");
                listCabecalhos.add("Manifesto");
            }

        }

        if (this.quantidadeDadosBancarios != null && this.quantidadeDadosBancarios.intValue() > 0) {

            for (int i = 0; i < this.quantidadeDadosBancarios.intValue(); i++) {

                listCabecalhos.add("Banco");
                listCabecalhos.add("Agência");
                listCabecalhos.add("Digito agência");
                listCabecalhos.add("Conta");
                listCabecalhos.add("Digito Conta");
                listCabecalhos.add("Tipo Conta");
                listCabecalhos.add("UF da Conta");
            }

        }

        if (this.quantidadeTelefones != null && this.quantidadeTelefones.intValue() > 0) {

            for (int i = 0; i < this.quantidadeTelefones.intValue(); i++) {

                listCabecalhos.add("DDD");
                listCabecalhos.add("Telefone");
                listCabecalhos.add("Status Telefone");

            }

        }

        String informacoesComplementares = this.serviceAtendimento.pesquisarMaiorInformacoesComplementares(this.campanha);

        if (StringUtils.isNotBlank(informacoesComplementares)) {
            Type type = (new TypeToken<Map<String, String>>() {
            }).getType();

            Map<String, String> listInformacoesComplementares = (Map<String, String>) (new Gson()).fromJson(informacoesComplementares, type);
            this.listCabecalhoInformacoesComplementares = new TreeSet<String>(listInformacoesComplementares.keySet());
            listCabecalhos.addAll(this.listCabecalhoInformacoesComplementares);

        } else {

            this.listCabecalhoInformacoesComplementares = null;
        }

        return listCabecalhos.<String>toArray(new String[listCabecalhos.size()]);

    }

    private List<String[]> gerarLinhas() {

        Map<Long, List<String>> mapHistoricos = gerarListHistoricos();
        Map<Long, List<String>> mapTelefones = gerarListTelefones();
        Map<Long, List<String>> mapDadosBancarios = gerarListDadosBancarios();

        List<String[]> linhas = (List) new ArrayList<String[]>();

        for (Object obj : this.serviceAtendimento.pesquisarAtendimentosPorCampanhaPlan(this.campanha.longValue())) {

            List<String> listLinhas = new ArrayList<String>();

            Object[] arrayAtendimentos = (Object[]) obj;

            int indice = 0;

            listLinhas.add(((BigInteger) Optional.<BigInteger>ofNullable((BigInteger) arrayAtendimentos[indice]).orElse(BigInteger.ZERO)).toString());

            String dataCadastro = "";


            if (arrayAtendimentos[++indice] != null) {

                dataCadastro = DateUtil.builder(new Date(((java.sql.Timestamp) arrayAtendimentos[indice]).getTime())).formatarDataParaString("dd/MM/yyyy").getDataTexto();

            }

            listLinhas.add(dataCadastro);
            listLinhas.add(Optional.ofNullable(((String) arrayAtendimentos[++indice])).orElse(""));
            listLinhas.add(Optional.ofNullable(((String) arrayAtendimentos[++indice])).orElse(""));
            listLinhas.add(Optional.<String>ofNullable(((String) arrayAtendimentos[++indice])).orElse(""));
            listLinhas.add(Optional.<String>ofNullable(((String) arrayAtendimentos[++indice])).orElse(""));
            listLinhas.add(Optional.<String>ofNullable(((String) arrayAtendimentos[++indice])).orElse(""));
            listLinhas.add(Optional.<String>ofNullable(((String) arrayAtendimentos[++indice])).orElse(""));
            listLinhas.add(Optional.<String>ofNullable(((String) arrayAtendimentos[++indice])).orElse(""));
            listLinhas.add(Optional.<String>ofNullable(((String) arrayAtendimentos[++indice])).orElse(""));
            listLinhas.add(Optional.<String>ofNullable(((String) arrayAtendimentos[++indice])).orElse(""));
            listLinhas.add(Optional.<String>ofNullable(((String) arrayAtendimentos[++indice])).orElse(""));
            listLinhas.add(Optional.<String>ofNullable(((String) arrayAtendimentos[++indice])).orElse(""));
            listLinhas.add(Optional.<String>ofNullable(((String) arrayAtendimentos[++indice])).orElse(""));
            listLinhas.add(Optional.<String>ofNullable(((String) arrayAtendimentos[++indice])).orElse(""));
            listLinhas.add(Optional.<String>ofNullable(((String) arrayAtendimentos[++indice])).orElse(""));
            listLinhas.add(Optional.ofNullable(((String) arrayAtendimentos[++indice])).orElse(""));
            listLinhas.add(Optional.<String>ofNullable(((String) arrayAtendimentos[++indice])).orElse(""));
            listLinhas.add(Optional.<String>ofNullable(((String) arrayAtendimentos[++indice])).orElse(""));
            listLinhas.add(Optional.<String>ofNullable(((String) arrayAtendimentos[++indice])).orElse(""));
            listLinhas.add(Optional.<String>ofNullable(((String) arrayAtendimentos[++indice])).orElse(""));
            listLinhas.add(Optional.<String>ofNullable(((String) arrayAtendimentos[++indice])).orElse(""));
            listLinhas.add(Optional.<String>ofNullable(((String) arrayAtendimentos[++indice])).orElse(""));
            listLinhas.add(Optional.<String>ofNullable(((String) arrayAtendimentos[++indice])).orElse(""));
            listLinhas.add(Optional.<String>ofNullable(((String) arrayAtendimentos[++indice])).orElse(""));
            listLinhas.add(Optional.<String>ofNullable(((String) arrayAtendimentos[++indice])).orElse(""));
            listLinhas.add(Optional.<String>ofNullable(((String) arrayAtendimentos[++indice])).orElse(""));
            listLinhas.add(Optional.<String>ofNullable(((String) arrayAtendimentos[++indice])).orElse(""));
            listLinhas.add(Optional.<String>ofNullable(((String) arrayAtendimentos[++indice])).orElse(""));
            listLinhas.add(Optional.<String>ofNullable(((String) arrayAtendimentos[++indice])).orElse(""));
            listLinhas.add(Optional.<String>ofNullable(((String) arrayAtendimentos[++indice])).orElse(""));
            listLinhas.add(Optional.<String>ofNullable(((String) arrayAtendimentos[++indice])).orElse(""));
            listLinhas.add(Optional.<String>ofNullable(((String) arrayAtendimentos[++indice])).orElse(""));
            listLinhas.add(Optional.<String>ofNullable(((String) arrayAtendimentos[++indice])).orElse(""));
            listLinhas.add(Optional.<String>ofNullable(((String) arrayAtendimentos[++indice])).orElse(""));
            listLinhas.add(Optional.<String>ofNullable(((String) arrayAtendimentos[++indice])).orElse(""));


            // HISTORICO

            List<String> listStrHistoricos = mapHistoricos
                    .get(Long.valueOf(((BigInteger) arrayAtendimentos[0]).longValue()));

            List<String> listStrTelefone = mapTelefones
                    .get(Long.valueOf(((BigInteger) arrayAtendimentos[0]).longValue()));

            List<String> listStrDadosBancarios = mapDadosBancarios.get(Long.valueOf(((BigInteger) arrayAtendimentos[0]).longValue()));

            int qtdeHistoricoTmp = (listStrHistoricos == null) ? 0 : listStrHistoricos.size();
            int qtdeTelefoleTmp = (listStrTelefone == null) ? 0 : listStrTelefone.size();
            int qtdeDadosBancariosTmp = listStrDadosBancarios == null ? 0 : listStrDadosBancarios.size();

            if (qtdeHistoricoTmp != this.quantidadeHistoricos * 5) {

                if (listStrHistoricos == null)
                    listStrHistoricos = new ArrayList<String>();

                for (int i = qtdeHistoricoTmp; i < this.quantidadeHistoricos * 5; i++) {

                    listStrHistoricos.add("");

                }

            }

            if (qtdeDadosBancariosTmp != this.quantidadeDadosBancarios * 7) {

                if (listStrDadosBancarios == null)
                    listStrDadosBancarios = new ArrayList<String>();

                for (int i = qtdeDadosBancariosTmp; i < this.quantidadeDadosBancarios * 7; i++) {

                    listStrDadosBancarios.add("");

                }

            }

            if (qtdeTelefoleTmp != this.quantidadeTelefones * 3) {

                if (listStrTelefone == null)
                    listStrTelefone = new ArrayList<String>();

                for (int i = qtdeTelefoleTmp; i < this.quantidadeTelefones * 3; i++) {

                    listStrTelefone.add("");

                }

            }

            if (listStrHistoricos != null)
                listLinhas.addAll(listStrHistoricos);


            if (listStrDadosBancarios != null)
                listLinhas.addAll(listStrDadosBancarios);

            if (listStrTelefone != null) {
                listLinhas.addAll(listStrTelefone);
            }


            if (this.listCabecalhoInformacoesComplementares != null) {

                String infoComplementar = Optional.<String>ofNullable((String) arrayAtendimentos[++indice]).orElse("");

                if (StringUtils.isNotBlank(infoComplementar)) {

                    Type type = (new TypeToken<Map<String, String>>() {

                    }).getType();

                    Map<String, String> lisInfoComplementar = (new Gson()).fromJson(infoComplementar, type);

                    for (String info : this.listCabecalhoInformacoesComplementares) {


                        listLinhas.add(StringUtils.defaultString(lisInfoComplementar.get(info), ""));

                    }

                } else {

                    this.listCabecalhoInformacoesComplementares.forEach(i -> listLinhas.add(""));

                }
            }


            linhas.add(listLinhas.toArray(new String[listLinhas.size()]));
        }

        return linhas;

    }

    private Map<Long, List<String>> gerarListDadosBancarios() {

        List<Object[]> listDadosBancarios = this.serviceDadosBancarios.pesquisarDadosBancariosPorCampanha(this.campanha);
        Map<Long, List<String>> mapDadosBancarios = new HashedMap<Long, List<String>>();

        if (listDadosBancarios != null && !listDadosBancarios.isEmpty()) {

            for (Object[] dados : listDadosBancarios) {

                if (mapDadosBancarios.containsKey(Long.valueOf(((BigInteger) dados[0]).longValue()))) {

                    List<String> list = mapDadosBancarios.get(Long.valueOf(((BigInteger) dados[0]).longValue()));

                    list.add(dados[1].toString());
                    list.add(dados[2].toString());
                    list.add(dados[3].toString());
                    list.add(dados[4].toString());
                    list.add(dados[5].toString());
                    list.add(dados[6].toString());
                    list.add(dados[7].toString());
                    continue;

                }
                List<String> dadosBancarios = new ArrayList<String>();

                dadosBancarios.add(dados[1].toString());
                dadosBancarios.add(dados[2].toString());
                dadosBancarios.add(dados[3].toString());
                dadosBancarios.add(dados[4].toString());
                dadosBancarios.add(dados[5].toString());
                dadosBancarios.add(dados[6].toString());
                dadosBancarios.add(dados[7].toString());
                mapDadosBancarios.put(Long.valueOf(((BigInteger) dados[0]).longValue()), dadosBancarios);

            }

        }

        return mapDadosBancarios;
    }

    private Map<Long, List<String>> gerarListTelefones() {

        List<Object[]> listTelefones = this.serviceTelefone.pesquisarTelefonesPorCampanha(this.campanha);
        Map<Long, List<String>> mapTelefones = new HashedMap<Long, List<String>>();

        if (listTelefones != null && !listTelefones.isEmpty()) {

            for (Object[] telefone : listTelefones) {

                if (mapTelefones.containsKey(((BigInteger) telefone[0]).longValue())) {

                    List<String> list = mapTelefones.get(((BigInteger) telefone[0]).longValue());
                    list.add(telefone[1].toString());
                    list.add(telefone[2].toString());
                    list.add(telefone[3].toString());
                    continue;

                }

                List<String> telefones = new ArrayList<String>();
                telefones.add(telefone[1].toString());
                telefones.add(telefone[2].toString());
                telefones.add(telefone[3].toString());
                mapTelefones.put(((BigInteger) telefone[0]).longValue(), telefones);

            }

        }

        return mapTelefones;
    }

    private Map<Long, List<String>> gerarListHistoricos() {

        List<Object[]> listHistoricos = this.serviceHistoricoAtendimento.pesquisarHistoricosPorCampanha(this.campanha);

        Map<Long, List<String>> mapHistorico = new HashedMap<Long, List<String>>();

        if (listHistoricos != null && !listHistoricos.isEmpty()) {

            for (Object[] historico : listHistoricos) {

                if (mapHistorico.containsKey(Long.valueOf(((BigInteger) historico[0]).longValue()))) {

                    List<String> list = mapHistorico.get(Long.valueOf(((BigInteger) historico[0]).longValue()));
                    list.add(historico[2].toString());
                    list.add(historico[3].toString());
                    list.add(historico[4].toString());
                    list.add(historico[5].toString());
                    list.add(historico[6].toString());

                    continue;
                }

                List<String> historicos = new ArrayList<String>();
                historicos.add(historico[2].toString());
                historicos.add(historico[3].toString());
                historicos.add(historico[4].toString());
                historicos.add(historico[5].toString());
                historicos.add(historico[6].toString());
                mapHistorico.put(Long.valueOf(((BigInteger) historico[0]).longValue()), historicos);

            }
        }

        return mapHistorico;
    }


    private Map<String, String> criarMapParametros3c() {

        mapHeader = new LinkedHashMap<>();
        mapHeader.put("header[0]", "identifier");
        mapHeader.put("header[1]", "Nome");
        mapHeader.put("header[2]", "CPF");
        mapHeader.put("header[3]", "Data de Nascimento");
        mapHeader.put("header[4]", "Nome da Mãe");
        mapHeader.put("header[5]", "Benefício principal");
        mapHeader.put("header[6]", "Benefício secundário");
        mapHeader.put("header[7]", "Entidade principal");
        mapHeader.put("header[8]", "Entidade secundário");
        mapHeader.put("header[9]", "Orgão principal");
        mapHeader.put("header[10]", "Orgão secundário");
        mapHeader.put("header[11]", "Margem");
        mapHeader.put("header[12]", "Outras informações");
        return mapHeader;

    }

    private String[] gerarCabecalhoCampanha3c(List<Atendimento> atendimentoList) {

        List<String> listCabecalhos = new ArrayList<String>();

        this.quantidadeTelefones = 0;

        for (Atendimento atendimento : atendimentoList) {

            if (CollectionUtils.isNotEmpty(atendimento.getListaTelefones()) && this.quantidadeTelefones < atendimento.getListTelefones().size())
                this.quantidadeTelefones = atendimento.getListTelefones().size();

        }

        listCabecalhos.add("identifier");
        listCabecalhos.add("Nome");
        listCabecalhos.add("CPF");
        listCabecalhos.add("Data de Nascimento");
        listCabecalhos.add("Nome da Mãe");
        listCabecalhos.add("Benefício principal");
        listCabecalhos.add("Benefício secundário");
        listCabecalhos.add("Entidade principal");
        listCabecalhos.add("Entidade secundário");
        listCabecalhos.add("Orgão principal");
        listCabecalhos.add("Orgão secundário");
        listCabecalhos.add("Margem");
        listCabecalhos.add("Outras informações");// 13


        Optional<Atendimento> optional = atendimentoList.stream().filter(a -> StringUtils.isNotEmpty(a.getInformacoesComplementares())).findFirst();
        String informacoesComplementares = optional.filter(atendimento -> StringUtils.isNotBlank(atendimento.getInformacoesComplementares())).map(GenericAtendimento::getInformacoesComplementares).orElse(null);

        if (StringUtils.isNotBlank(informacoesComplementares)) {
            Type type = (new TypeToken<Map<String, String>>() {
            }).getType();
            Map<String, String> listInformacoesComplementares = (new Gson()).fromJson(informacoesComplementares, type);
            this.listCabecalhoInformacoesComplementares = new TreeSet<String>(listInformacoesComplementares.keySet());
            listCabecalhos.addAll(this.listCabecalhoInformacoesComplementares);

        } else {

            this.listCabecalhoInformacoesComplementares = null;
        }

        if (this.quantidadeTelefones != null && this.quantidadeTelefones > 0) {

            for (int i = 0; i < this.quantidadeTelefones; i++) {

                listCabecalhos.add("areacodephone");
                // listCabecalhos.add("phone");
            }

        }

        this.mapHeader = new LinkedHashMap<>();

        if (CollectionUtils.isNotEmpty(listCabecalhos)) {

            for (int i = 0; i < listCabecalhos.size(); i++) {

                this.mapHeader.put("header[" + i + "]", listCabecalhos.get(i));

            }

        }

        return listCabecalhos.<String>toArray(new String[0]);

    }


    private String[] gerarCabecalhoCampanhaArgus(List<Atendimento> atendimentoList) {

        List<String> listCabecalhos = new ArrayList<String>();

        this.quantidadeTelefones = 0;

        for (Atendimento atendimento : atendimentoList) {

            if (CollectionUtils.isNotEmpty(atendimento.getListaTelefones()) && this.quantidadeTelefones < atendimento.getListTelefones().size())
                this.quantidadeTelefones = atendimento.getListTelefones().size();

        }

        listCabecalhos.add("identifier");
        listCabecalhos.add("cpf");
        listCabecalhos.add("Nome");

        if (this.quantidadeTelefones != null && this.quantidadeTelefones > 0) {

            for (int i = 0; i < this.quantidadeTelefones; i++) {

                listCabecalhos.add("Telefone");

            }

        }

        this.mapHeader = new LinkedHashMap<>();

        if (CollectionUtils.isNotEmpty(listCabecalhos)) {

            for (int i = 0; i < listCabecalhos.size(); i++) {

                this.mapHeader.put("header[" + i + "]", listCabecalhos.get(i));

            }

        }

        return listCabecalhos.<String>toArray(new String[0]);

    }


    private List<String[]> gerarLinhasEnvio3c(List<Atendimento> atendimentoList) {


        Map<Long, List<String>> mapTelefones = gerarListTelefonesEnvio3c(atendimentoList);

        List<String[]> linhas = (List) new ArrayList<String[]>();

        List<Atendimento> listAtendimentoUnicos = (List<Atendimento>) atendimentoList.stream().filter(Utils.distinctByKey(Generic::getId)).collect(Collectors.toList());
        int totalTelefone = 0;

        for (Atendimento atendimento : listAtendimentoUnicos) {

            if (CollectionUtils.isEmpty(atendimento.getListaTelefones()))
                continue;

            List<String> listLinhas = new ArrayList<String>();

            int indice = 0;

            listLinhas.add(atendimento.getId().toString());

            listLinhas.add(Utils.tratarStringLfCsv(atendimento.getNome()));
            listLinhas.add(Optional.ofNullable((atendimento.getCpf())).orElse("-"));

            listLinhas.add(Utils.tratarStringLf(converDataToString(atendimento.getDataNascimento())));
            listLinhas.add(Utils.tratarStringLf((atendimento.getNomeMae())));
            listLinhas.add(Utils.tratarStringLf((atendimento.getBeneficioPrincipal())));
            listLinhas.add(Utils.tratarStringLf((atendimento.getBeneficioSecundario())));
            listLinhas.add(Utils.tratarStringLf((atendimento.getEntidadePrincipal())));
            listLinhas.add(Utils.tratarStringLf((atendimento.getEntidadeSecundaria())));
            listLinhas.add(Utils.tratarStringLf((atendimento.getOrgaoPrincipal())));
            listLinhas.add(Utils.tratarStringLf((atendimento.getOrgaoSecundario())));
            listLinhas.add(Optional.ofNullable((atendimento.getMargem() == null ? null : atendimento.getMargem().toString())).orElse("-"));
            listLinhas.add(Utils.tratarStringLf((atendimento.getOutrasInformacoes())));

            if (this.listCabecalhoInformacoesComplementares != null) {

                String infoComplementar = Optional.<String>ofNullable(atendimento.getInformacoesComplementares()).orElse("");

                if (StringUtils.isNotBlank(infoComplementar)) {

                    Type type = (new TypeToken<Map<String, String>>() {

                    }).getType();

                    Map<String, String> lisInfoComplementar = (Map<String, String>) (new Gson()).fromJson(infoComplementar, type);

                    for (String info : this.listCabecalhoInformacoesComplementares) {

                        listLinhas.add(Utils.tratarStringLf(StringUtils.defaultString(lisInfoComplementar.get(info), "-")));

                    }

                } else {

                    this.listCabecalhoInformacoesComplementares.forEach(i -> listLinhas.add("-"));

                }
            }

            List<String> listStrTelefone = mapTelefones.get(atendimento.getId());


            int qtdeTelefoleTmp = (listStrTelefone == null) ? 0 : listStrTelefone.size();
            totalTelefone += qtdeTelefoleTmp;

            if (qtdeTelefoleTmp != this.quantidadeTelefones) {

                if (CollectionUtils.isEmpty(listStrTelefone))
                    listStrTelefone = new ArrayList<>();

                for (int i = qtdeTelefoleTmp; i < this.quantidadeTelefones; i++) {

                    listStrTelefone.add("");

                }

            }

            if (CollectionUtils.isNotEmpty(listStrTelefone)) {
                listLinhas.addAll(listStrTelefone);
            }

            linhas.add(listLinhas.toArray(new String[listLinhas.size()]));
        }
        System.out.println("TOTAL TELEFONES: " + totalTelefone);
        return linhas;

    }




    private List<String[]> gerarLinhasEnvioArgus(List<Atendimento> atendimentoList) {


        Map<Long, List<String>> mapTelefones = gerarListTelefonesEnvio3c(atendimentoList);

        List<String[]> linhas = (List) new ArrayList<String[]>();

        List<Atendimento> listAtendimentoUnicos = (List<Atendimento>) atendimentoList.stream().filter(Utils.distinctByKey(Generic::getId)).collect(Collectors.toList());
        int totalTelefone = 0;

        for (Atendimento atendimento : listAtendimentoUnicos) {

            if (CollectionUtils.isEmpty(atendimento.getListaTelefones()))
                continue;

            List<String> listLinhas = new ArrayList<String>();

            int indice = 0;

            listLinhas.add(atendimento.getId().toString());
            listLinhas.add(Optional.ofNullable((atendimento.getCpf())).orElse("-"));
            listLinhas.add(Utils.tratarStringLfCsv(atendimento.getNome()));

            List<String> listStrTelefone = mapTelefones.get(atendimento.getId());


            int qtdeTelefoleTmp = (listStrTelefone == null) ? 0 : listStrTelefone.size();
            totalTelefone += qtdeTelefoleTmp;

            if (qtdeTelefoleTmp != this.quantidadeTelefones) {

                if (CollectionUtils.isEmpty(listStrTelefone))
                    listStrTelefone = new ArrayList<>();

                for (int i = qtdeTelefoleTmp; i < this.quantidadeTelefones; i++) {

                    listStrTelefone.add("");

                }

            }

            if (CollectionUtils.isNotEmpty(listStrTelefone)) {
                listLinhas.addAll(listStrTelefone);
            }

            linhas.add(listLinhas.toArray(new String[listLinhas.size()]));
        }
        System.out.println("TOTAL TELEFONES: " + totalTelefone);
        return linhas;

    }


    private String converDataToString(Date date) {
        if (date == null)
            return null;

        return DateUtil.builder(date).formatarDataParaString("dd/MM/yyyy").getDataTexto();

    }


    private Map<Long, List<String>> gerarListTelefonesEnvio3c(List<Atendimento> atendimentoList) {

        Map<Long, List<String>> mapTelefones = new HashedMap<Long, List<String>>();

        for (Atendimento atendimento : atendimentoList) {

            if (CollectionUtils.isNotEmpty(atendimento.getListaTelefones())) {

                for (Telefone tel : atendimento.getListTelefones()) {

                    if (mapTelefones.containsKey(atendimento.getId())) {

                        List<String> list = mapTelefones.get(atendimento.getId());
                        list.add(tel.getDdd().toString() + tel.getNumero());

                    } else {

                        List<String> telefones = new ArrayList<>();
                        telefones.add(tel.getDdd().toString() + tel.getNumero());
                        mapTelefones.put(atendimento.getId(), telefones);
                    }
                }

            }


        }


        return mapTelefones;
    }

    public String salvarArquivoMailing(InputStream mailing, String nome, String strDiretorio) {

        try {

            if (mailing != null && StringUtils.isNotBlank(nome) && StringUtils.isNotBlank(strDiretorio)) {

                File diretorio = new File(System.getProperty("user.home") + File.separator + "proativa" + File.separator + strDiretorio);

                if (!diretorio.exists()) {

                    Files.createDirectories(diretorio.toPath(), (FileAttribute<?>[]) new FileAttribute[0]);
                }

                File arquivo = new File(System.getProperty("user.home") + File.separator + "proativa" + File.separator + strDiretorio + File.separator + nome);

                Files.deleteIfExists(arquivo.toPath());

                Files.copy(mailing, arquivo.toPath());

                mailing.close();

                return diretorio.getAbsolutePath();

            } else {

                System.err.println("Não foi possivel salvar audio Diretorio e ou nome do arquivo não iformado.....");

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return strDiretorio;
    }


}
