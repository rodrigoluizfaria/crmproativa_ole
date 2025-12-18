package com.proativaservicos.bean;

import com.google.gson.Gson;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.*;
import com.proativaservicos.service.*;
import com.proativaservicos.util.*;
import com.proativaservicos.util.constantes.*;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.omnifaces.util.Messages;
import org.primefaces.PrimeFaces;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.DragDropEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@ViewScoped
@Named
public class ConciliarAudioBean extends GenericBean {

    private static final long serialVersionUID = 2254597358044666254L;

    private List<ConciliarAudioAnexo> listAtendimentosAudios;

    @Inject
    private ConcilicarAudioAnexoService serviceAudiosAnexos;

    @Inject
    private AtendimentoService serviceAtendimento;

    @Inject
    private ConcilicarSftpService serviceSftp;

    @Inject
    private ProdutoService serviceProdutos;

    @Inject
    private LojaService lojaService;

    @Inject
    private TranscreverService transcreverService;

    private ConciliarSftp conciliarSftp;

    private List<Produto> listProdutos;

    private List<?> listConciliar;

    private List<Object[]> listConciliarDropped;

    private List<?> listConciliarSelecionados;

    private List<ConciliarSftp> listConciliarSftp;

    private List<LsSftp> listFilesSftp;

    private LsSftp lsSelect;

    private Date dataInicio;
    private Date dataFim;

    private boolean conciliar;

    private ConciliarAudioAnexo conciliarAudioAnexoView;

    private String adesao;
    private String cpf;

    private Long idAtendimentoConciliado;

    private Long idProduto;

    private StreamedContent fileWav;
    private StreamedContent fileRar;
    private String nomeArquivoRar;

    @Inject
    private LogConciliarAudioService serviceLog;

    private List<ConciliarAudioAnexo> listAnexosEnviar;

    @PostConstruct
    public void init() {

        this.dataInicio = new Date();
        this.dataFim = new Date();
        this.adesao = null;
        this.cpf = null;
        this.conciliar = false;
        this.listFilesSftp = null;
        this.listAnexosEnviar = null;
        this.nomeArquivoRar = null;
        this.listConciliarDropped = null;
        inicializarEntidades();

    }

    private void inicializarEntidades() {

        inicializarEmpresa();

        if (getEmpresa() != null)
            this.listProdutos = this.serviceProdutos.pesquisarProdutoPorEmpresa(retornarEmpresaMatrizUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);

        pesquisar();

    }

    public void pesquisar() {

        try {

            if (this.dataInicio != null && this.dataFim != null && this.dataInicio.after(this.dataFim))
                throw new ProativaException("A data inicial deve ser maior que a data final.");

            if (getEmpresa() != null)
                this.listConciliar = this.serviceAtendimento.pesquisarAtendimentosConciliarAnexo(getEmpresa().getId(), this.conciliar, this.adesao, this.cpf, this.idProduto, this.dataInicio, this.dataFim);


        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }
    }

    public void onBuscarConciliarAudioAnexo() {

        try {

            if (this.idAtendimentoConciliado == null)
                throw new ProativaException("Por favor, selecione o atendimento.");

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }

    public void onConciliar(Long idAtendimento, String adesao) {

        try {

            if (idAtendimento != null) {

                this.listAtendimentosAudios = this.serviceAudiosAnexos.pesquisarPorAtendimentos(List.of(idAtendimento));
                this.serviceAtendimento.pesquisarAtendimentoPorId(idAtendimento);

                boolean encontrouAudioErrado = this.listAtendimentosAudios.stream().anyMatch(c -> c.getNomeArquivo().split("_").length != 7 || !c.getNomeArquivo().contains(adesao));

                if (encontrouAudioErrado) {

                    Map<Long, List<ConciliarAudioAnexo>> map = new HashMap<>();
                    map.put(idAtendimento, listAtendimentosAudios);
                    renomearGravacaoMap(map);
                    this.listAtendimentosAudios = this.serviceAudiosAnexos.pesquisarPorAtendimento(idAtendimento);

                }

                PrimeFaces.current().executeScript("PF('dlgAudioAdm').show();");

            } else {

                Messages.addGlobalError("Nenhum áudio encontrado.");
            }

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }


    public void listarFilesSftp() {

        try {


            SftpUtil sftp = criarSftp();

            sftp.conectar();

            if (sftp.verificarConexao()) {

                List<LsEntry> listLs = sftp.listarDiretorio(this.conciliarSftp.getDiretorio());

                if (CollectionUtils.isNotEmpty(listLs)) {

                    listLs.removeIf(l -> l.getAttrs().isDir());

                    this.listFilesSftp = new ArrayList<LsSftp>();
                    int i = 0;
                    for (LsEntry ls : listLs) {

                        this.listFilesSftp.add(new LsSftp().builder(ls, Long.valueOf(i)));
                        i++;
                    }

                }


                sftp.desconectar();


            } else {

                throw new ProativaException("Conexão indisponível.");
            }

        } catch (ProativaException e) {

            e.printStackTrace();
            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }


    public void trocarEmpresa() {

        if (getEmpresa() != null) {
            this.listConciliarSftp = this.serviceSftp.pesquisarListConciliarPorEmpresa(getEmpresa().getId(), TipoAcessoEnum.ATIVO);
            this.listProdutos = this.serviceProdutos.pesquisarProdutoPorEmpresa(getEmpresa().getId(), TipoAcessoEnum.ATIVO);
        }
    }

    public void onModalSftp() {

        try {


            if (CollectionUtils.isEmpty(this.listConciliarSelecionados))
                throw new ProativaException("Por favor, selecione os atendimentos.");

            if (this.conciliarSftp == null)
                this.listFilesSftp = null;
            else
                listarFilesSftp();

            this.listConciliarSftp = this.serviceSftp.pesquisarListConciliarPorEmpresa(getEmpresa().getId(), TipoAcessoEnum.ATIVO);

            onRenomearGravacaoSelecionadas();

            this.listAnexosEnviar = buscarConciliarAudios();

            PrimeFaces.current().executeScript("PF('dlgSftp').show();");

        } catch (ProativaException e) {

            e.printStackTrace();
            Messages.addGlobalError(e.getMessage(), new Object[0]);


        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }
    }


    public void onRenomearGravacaoSelecionadas() {

        try {


            if (CollectionUtils.isEmpty(this.listConciliarSelecionados))
                throw new ProativaException("Por favor, selecione os atendimentos.");


            this.listAnexosEnviar = buscarConciliarAudios();

            if (CollectionUtils.isNotEmpty(this.listAnexosEnviar)) {

                Map<Long, List<ConciliarAudioAnexo>> atendimentoListMap = new HashMap<Long, List<ConciliarAudioAnexo>>();

                for (ConciliarAudioAnexo ca : this.listAnexosEnviar) {

                    if (atendimentoListMap.isEmpty() || !atendimentoListMap.containsKey(ca.getAtendimento().getId())) {

                        atendimentoListMap.put(ca.getAtendimento().getId(), new ArrayList<>());
                        atendimentoListMap.get(ca.getAtendimento().getId()).add(ca);

                    } else
                        atendimentoListMap.get(ca.getAtendimento().getId()).add(ca);

                }

                renomearGravacaoMap(atendimentoListMap);


            }


        } catch (ProativaException e) {

            e.printStackTrace();
            Messages.addGlobalError(e.getMessage(), new Object[0]);


        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }
    }

    private void renomearGravacaoMap(Map<Long, List<ConciliarAudioAnexo>> atendimentoListMap) throws ProativaException {

        try {

            if (atendimentoListMap != null && !atendimentoListMap.isEmpty()) {

                List<Loja> listLojas = this.lojaService.pesquisarLojas(retornarUsuarioSessao().getEmpresa().getId(), TipoAcessoEnum.ATIVO);

                String loja = CollectionUtils.isEmpty(listLojas) ? "" : listLojas.get(0).getCodigoLoja();

                for (Long a : atendimentoListMap.keySet()) {

                    List<ConciliarAudioAnexo> conciliarAudioAnexos = atendimentoListMap.get(a);

                    int i = 1;

                    for (ConciliarAudioAnexo ca : conciliarAudioAnexos) {

                        File fileOrigem = new File(ca.getArquivoCompleto(), ca.getNomeArquivo());

                        loja = ca.getAtendimento().getLoja() == null ? loja : ca.getAtendimento().getLoja().getCodigoLoja();

                        //String nomeClatura = "SEG_" + loja + "_" + StringUtils.leftPad(ca.getAtendimento().getCpf(), 11, "0") + "_" + DateUtil.builder(ca.getDataAnexo()).adicionarTempoData(DataEnum.SEGUNDO, 120).formatarDataParaString("yyyyMMddhhmmss").getDataTexto() + "_" + i + "_" + conciliarAudioAnexos.size() + "_" + ca.getAtendimento().getAdesao() + "." + ArquivoUtil.obterExtensao(fileOrigem);
                        String nomeClatura = gerarNomeclatura(ca, loja, i, conciliarAudioAnexos.size(), fileOrigem.getName());

                        File fileDestino = new File(ca.getArquivoCompleto() + File.separator + nomeClatura);

                        File renomeou = ArquivoUtil.renomearArquivo(fileOrigem.getAbsolutePath(), fileDestino.getAbsolutePath());

                        if (renomeou != null && renomeou.exists()) {
                            this.serviceAudiosAnexos.alterarNomeArquivo(ca.getId(), renomeou.getName(), renomeou.getParent(), ca.getDataAnexo());
                        } else
                            throw new ProativaException("Ocorreu um erro ao renomear a gravação.");

                        i++;

                    }

                }

                Messages.addGlobalInfo("Gravações renomeadas com sucesso.");

            }

        } catch (Exception e) {

            e.printStackTrace();
            throw new ProativaException(MessagesEnum.ERRO_INERPERADO.constante);
        }

    }


    public String gerarNomeclatura(ConciliarAudioAnexo ca, String loja, int num, int quantidade, String fileOrigem) {


        String nomeclatura = "";

        if (ca.getAtendimento().getInstituicaoFinanceira().equals(InstituicaoFinanceiraEnum.BANCO_MASTER)) {
            nomeclatura = "LIF_" + loja + "_" + ca.getAtendimento().getAdesao() + "_" + StringUtils.leftPad(ca.getAtendimento().getCpf(), 11, "0") + "_" + DateUtil.builder(ca.getDataAnexo()).adicionarTempoData(DataEnum.SEGUNDO, 120).formatarDataParaString("yyyyMMddHHmmss").getDataTexto() + "_" + num + "_" + quantidade + "." + ArquivoUtil.obterExtensao(fileOrigem);

        } else
            nomeclatura = "SEG_" + loja + "_" + StringUtils.leftPad(ca.getAtendimento().getCpf(), 11, "0") + "_" + DateUtil.builder(ca.getDataAnexo()).adicionarTempoData(DataEnum.SEGUNDO, 120).formatarDataParaString("yyyyMMddHHmmss").getDataTexto() + "_" + num + "_" + quantidade + "_" + ca.getAtendimento().getAdesao() + "." + ArquivoUtil.obterExtensao(fileOrigem);

        return nomeclatura;
    }

    public void onBaixarAudiosSelecionados() {

        try {


            List<ConciliarAudioAnexo> listAnexos = buscarConciliarAudios();

            if (CollectionUtils.isNotEmpty(listAnexos)) {

                if (CollectionUtils.isEmpty(listAnexos))
                    throw new ProativaException("Nenhum áudio encontrado.");

                List<File> listWav = new ArrayList<File>();

                for (ConciliarAudioAnexo conciliarAudioAnexo : listAnexos)
                    listWav.add(new File(conciliarAudioAnexo.getArquivoCompleto() + File.separator + conciliarAudioAnexo.getNomeArquivo()));

                boolean validarList = listWav.stream().allMatch(f -> (!f.exists()));

                if (validarList)
                    throw new ProativaException("Nenhum arquivo encontrado");

                File rar = Utils.compactarRar(listWav, criarDiretorios("arquivos_baixados"), "anexados");


                if (rar == null || (rar != null && !rar.exists()) || validarList)
                    throw new ProativaException("Não foi possivel compactar os arquivos.");

                byte[] array = Files.readAllBytes(Paths.get(rar.getAbsolutePath()));

                this.fileRar = DefaultStreamedContent.builder().name("anexados.rar").contentType("application/x-rar-compressed").stream(() -> new ByteArrayInputStream(array)).build();
                rar.delete();

                this.listConciliarSelecionados = null;


            }

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }


    private List<ConciliarAudioAnexo> buscarConciliarAudios() throws ProativaException {


        List<Long> listIds = retornarIdsAtendimentoConciliar();

        if (CollectionUtils.isNotEmpty(listIds)) {

            return this.serviceAudiosAnexos.pesquisarPorAtendimentos(listIds);

        }

        return null;

    }


    private List<Long> retornarIdsAtendimentoConciliar() throws ProativaException {

        if (CollectionUtils.isEmpty(this.listConciliarSelecionados))
            throw new ProativaException("Por favor, selecione os atendimentos.");

        List<Long> listIds = new ArrayList<Long>();

        for (Object obj : this.listConciliarSelecionados) {

            Object[] arrayObj = (Object[]) obj;
            listIds.add(((BigInteger) arrayObj[0]).longValue());

        }

        return listIds;


    }


    public void baixarArquivoWav(ConciliarAudioAnexo conciliar) {

        if (conciliar != null) {

            try {

                File file = new File(conciliar.getArquivoCompleto() + File.separator + conciliar.getNomeArquivo());

                if (!file.exists())
                    throw new ProativaException("Audio não encontrado.");

                byte[] array = Files.readAllBytes(Paths.get(conciliar.getArquivoCompleto() + File.separator + conciliar.getNomeArquivo()));

                this.fileWav = DefaultStreamedContent.builder().name(conciliar.getNomeArquivo()).contentType("audio/wav").stream(() -> new ByteArrayInputStream(array)).build();

            } catch (ProativaException e) {

                Messages.addGlobalError(e.getMessage());

            } catch (Exception e) {

                e.printStackTrace();
                Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
            }
        }
    }


    public void onEnviarSftp() {

        try {

            if (StringUtils.isEmpty(this.nomeArquivoRar) || this.nomeArquivoRar.length() < 5)
                throw new ProativaException("Nome do arquivo inválido.");

            if (this.conciliarSftp == null || StringUtils.isBlank(this.conciliarSftp.getUrl()) || StringUtils.isBlank(this.conciliarSftp.getUsr()) || StringUtils.isBlank(this.conciliarSftp.getPorta()) || (this.conciliarSftp.getTipoLoginSftp().equals(TipoLoginSftpEnum.NORMAL)  && StringUtils.isBlank(this.conciliarSftp.getPsw())) || (this.conciliarSftp.getTipoLoginSftp().equals(TipoLoginSftpEnum.ARQUIVO) && StringUtils.isBlank(this.conciliarSftp.getArquivoChave())))
                throw new ProativaException("Sftp invalido, verifique,");

            if (CollectionUtils.isEmpty(this.listAnexosEnviar))
                throw new ProativaException("Não foi possivel encontrar os áudios.");

            List<File> listWav = new ArrayList<File>();

            for (ConciliarAudioAnexo conciliarAudioAnexo : this.listAnexosEnviar) {
                listWav.add(new File(conciliarAudioAnexo.getArquivoCompleto() + File.separator + conciliarAudioAnexo.getNomeArquivo()));
            }

            this.nomeArquivoRar = this.nomeArquivoRar.trim();

            File arquivoCompaquitado = compactar(listWav);

            if (!arquivoCompaquitado.exists())
                throw new ProativaException("Não foi possivel compactar os arquivos.");

            LogConciliarAudio log = new LogConciliarAudio();
            log.setDataInicio(new Date());

            SftpUtil sftp = criarSftp();

            sftp.conectar();

            if (sftp.verificarConexao()) {

                sftp.enviarSftp(arquivoCompaquitado, this.conciliarSftp.getDiretorio());

                sftp.desconectar();

                Messages.addGlobalInfo("Arquivo enviado com sucesso.", new Object[0]);

                listarFilesSftp();

                this.serviceAtendimento.atualizarAtendimentosConciliados(retornarIdsAtendimentoConciliar(), true);
                log.setArquivoCompactado(arquivoCompaquitado.getName());
                log.setEmpresa(retornarEmpresaMatrizUsuarioSessao());
                log.setUsuario(retornarUsuarioSessao());
                log.setStatus(StatusConciliarSftp.ENVIADO);
                log.setTipoConciliar(TipoConciliarEnum.MANUAL);
                log.setAquivos(new Gson().toJson(listWav.stream().map(e -> e.getName()).collect(Collectors.toList())));
                log.setDataFim(new Date());
                log.setObservacao("Enviado com sucesso! | usuario: " + retornarUsuarioSessao().getNome());
                this.serviceLog.inserir(log);

            } else {

                throw new ProativaException("Conexão indisponível.");
            }


        } catch (ProativaException e) {

            e.printStackTrace();
            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    private File compactar(List<File> listWav) throws IOException, InterruptedException, ProativaException {

        String extencao = (ArquivoUtil.obterExtensao(this.nomeArquivoRar));

        if ( extencao != null && extencao.trim().equalsIgnoreCase("zip")) {

            return Utils.compactarArquivoZip(listWav, criarDiretorios("arquivos_baixados"), StringUtils.endsWithIgnoreCase(this.nomeArquivoRar, "zip") ? this.nomeArquivoRar : this.nomeArquivoRar + ".zip");

        }

        return Utils.compactarRar(listWav, criarDiretorios("arquivos_baixados"), StringUtils.endsWithIgnoreCase(this.nomeArquivoRar, "rar") ? this.nomeArquivoRar : this.nomeArquivoRar + ".rar");
    }

    private SftpUtil criarSftp() throws ProativaException {

        if (this.conciliarSftp == null || StringUtils.isBlank(this.conciliarSftp.getUrl()) || StringUtils.isBlank(this.conciliarSftp.getUsr()) || StringUtils.isBlank(this.conciliarSftp.getPorta()) || (conciliarSftp.getTipoLoginSftp().equals(TipoLoginSftpEnum.NORMAL) && StringUtils.isBlank(this.conciliarSftp.getPsw())))
            throw new ProativaException("Sftp invalido, verifique,");

        int porta = 0;

        try {

            porta = Integer.valueOf(this.conciliarSftp.getPorta()).intValue();

        } catch (Exception e) {

            throw new ProativaException("Porta invalida, verifique,");
        }

        if (this.conciliarSftp.getTipoLoginSftp().equals(TipoLoginSftpEnum.ARQUIVO)) {
            return SftpUtil.builder(this.conciliarSftp.getUrl(), this.conciliarSftp.getUsr(), new File(this.conciliarSftp.getArquivoChave()), porta);

        }

        return SftpUtil.builder(this.conciliarSftp.getUrl(), this.conciliarSftp.getUsr(), this.conciliarSftp.getPsw(), porta);

    }


    public void deletarFileSftp() {

        try {

            if (this.lsSelect != null && this.conciliarSftp != null) {

                SftpUtil sftp = criarSftp();

                sftp.conectar();

                sftp.deletarArquivo(this.conciliarSftp.getDiretorio(), this.lsSelect.getFileName());
                sftp.desconectar();
                Messages.addGlobalInfo("Arquivo removido com sucesso.", new Object[0]);
                listarFilesSftp();
            }

        } catch (ProativaException e) {

            e.printStackTrace();
            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void onTranscreverAudio(Long id) {

        this.listAtendimentosAudios = this.serviceAudiosAnexos.pesquisarPorAtendimento(id);
        System.out.println("TRANSCREVER: " + id);

        if (CollectionUtils.isNotEmpty(this.listAtendimentosAudios)) {

            for (ConciliarAudioAnexo conciliarAudioAnexo : this.listAtendimentosAudios) {

                if (conciliarAudioAnexo.getCodigoExterno() == null) {

                    this.transcreverService.transcreverConciliarApiPyton(conciliarAudioAnexo);
                    conciliarAudioAnexo.setTranscricao("Processando transcrição, em instantes o resultado estará disponível.");

                } else if (StringUtils.isBlank(conciliarAudioAnexo.getTranscricao()) || (StringUtils.isNotBlank(conciliarAudioAnexo.getTranscricao()) && (conciliarAudioAnexo.getTranscricao().startsWith("Processando") || conciliarAudioAnexo.getTranscricao().startsWith("Internal Server Error")))) {

                    conciliarAudioAnexo.setTranscricao(tratarJsonApi(TranscreverPython.buscarTranscricaoApi(conciliarAudioAnexo.getCodigoExterno())));
                    System.out.println("TRANSCREVENDO: " + conciliarAudioAnexo.getTranscricao());

                    if (StringUtils.isNotBlank(conciliarAudioAnexo.getTranscricao())) {
                        this.serviceAudiosAnexos.salvarTranscricao(conciliarAudioAnexo.getId(), conciliarAudioAnexo.getTranscricao());
                    }

                }

            }

        }

    }

    private String tratarJsonApi(String texto) {

        if (StringUtils.isNotBlank(texto) && Utils.isJSON(texto)) {

            JSONObject json = new JSONObject(texto);

            if (json.has("transcription")) {
                return json.getString("transcription");
            }


        }
        return texto;
    }

    public void onCellEdit(CellEditEvent<?> event) {

        String oldValue = (String) event.getOldValue();
        String newValue = (String) event.getNewValue();

        try {

            if (newValue != null && !newValue.equals(oldValue)) {

                SftpUtil sftp = criarSftp();
                sftp.conectar();
                sftp.renomearArquivo(this.conciliarSftp.getDiretorio(), oldValue, newValue);
                sftp.desconectar();
                Messages.addGlobalInfo("Arquivo alterado com sucesso.", new Object[0]);
                listarFilesSftp();

            }

        } catch (ProativaException e) {

            e.printStackTrace();
            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }


    public void onCarDrop(DragDropEvent<Object[]> ddEvent) {

        Object[] arrayObj = (Object[]) ddEvent.getData();

        if (CollectionUtils.isEmpty(this.listConciliarDropped))
            this.listConciliarDropped = new ArrayList<Object[]>();

        this.listConciliar.remove(arrayObj);
        this.listConciliarDropped.add(arrayObj);
    }

    /**
     * @return the listAtendimentosAudios
     */
    public List<ConciliarAudioAnexo> getListAtendimentosAudios() {
        return listAtendimentosAudios;
    }

    /**
     * @param listAtendimentosAudios the listAtendimentosAudios to set
     */
    public void setListAtendimentosAudios(List<ConciliarAudioAnexo> listAtendimentosAudios) {
        this.listAtendimentosAudios = listAtendimentosAudios;
    }

    /**
     * @return the conciliarSftp
     */
    public ConciliarSftp getConciliarSftp() {
        return conciliarSftp;
    }

    /**
     * @param conciliarSftp the conciliarSftp to set
     */
    public void setConciliarSftp(ConciliarSftp conciliarSftp) {
        this.conciliarSftp = conciliarSftp;
    }

    /**
     * @return the listProdutos
     */
    public List<Produto> getListProdutos() {
        return listProdutos;
    }

    /**
     * @param listProdutos the listProdutos to set
     */
    public void setListProdutos(List<Produto> listProdutos) {
        this.listProdutos = listProdutos;
    }

    /**
     * @return the listConciliar
     */
    public List<?> getListConciliar() {
        return listConciliar;
    }

    /**
     * @param listConciliar the listConciliar to set
     */
    public void setListConciliar(List<?> listConciliar) {
        this.listConciliar = listConciliar;
    }

    /**
     * @return the dataInicio
     */
    public Date getDataInicio() {
        return dataInicio;
    }

    /**
     * @param dataInicio the dataInicio to set
     */
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * @return the dataFim
     */
    public Date getDataFim() {
        return dataFim;
    }

    /**
     * @param dataFim the dataFim to set
     */
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * @return the conciliar
     */
    public boolean isConciliar() {
        return conciliar;
    }

    /**
     * @param conciliar the conciliar to set
     */
    public void setConciliar(boolean conciliar) {
        this.conciliar = conciliar;
    }

    public StreamedContent getFileWav() {
        return fileWav;
    }

    public void setFileWav(StreamedContent fileWav) {
        this.fileWav = fileWav;
    }

    /**
     * @return the listConciliarSelecionados
     */
    public List<?> getListConciliarSelecionados() {
        return listConciliarSelecionados;
    }

    /**
     * @param listConciliarSelecionados the listConciliarSelecionados to set
     */
    public void setListConciliarSelecionados(List<?> listConciliarSelecionados) {
        this.listConciliarSelecionados = listConciliarSelecionados;
    }

    /**
     * @return the idAtendimentoConciliado
     */
    public Long getIdAtendimentoConciliado() {
        return idAtendimentoConciliado;
    }

    /**
     * @param idAtendimentoConciliado the idAtendimentoConciliado to set
     */
    public void setIdAtendimentoConciliado(Long idAtendimentoConciliado) {
        this.idAtendimentoConciliado = idAtendimentoConciliado;
    }

    /**
     * @return the fileRar
     */
    public StreamedContent getFileRar() {
        return fileRar;
    }

    /**
     * @param fileRar the fileRar to set
     */
    public void setFileRar(StreamedContent fileRar) {
        this.fileRar = fileRar;
    }

    public String getAdesao() {
        return adesao;
    }

    public void setAdesao(String adesao) {
        this.adesao = adesao;
    }

    /**
     * @return the idProduto
     */
    public Long getIdProduto() {
        return idProduto;
    }

    /**
     * @param idProduto the idProduto to set
     */
    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<ConciliarSftp> getListConciliarSftp() {
        return listConciliarSftp;
    }

    public List<LsSftp> getListFilesSftp() {
        return listFilesSftp;
    }

    public void setListFilesSftp(List<LsSftp> listFilesSftp) {
        this.listFilesSftp = listFilesSftp;
    }

    public List<ConciliarAudioAnexo> getListAnexosEnviar() {
        return listAnexosEnviar;
    }

    public String getNomeArquivoRar() {
        return nomeArquivoRar;
    }

    public void setNomeArquivoRar(String nomeArquivoRar) {
        this.nomeArquivoRar = nomeArquivoRar;
    }

    public void setLsSelect(LsSftp lsSelect) {
        this.lsSelect = lsSelect;
    }

    public LsSftp getLsSelect() {
        return lsSelect;
    }

    public List<Object[]> getListConciliarDropped() {
        return listConciliarDropped;
    }

    public void setListConciliarDropped(List<Object[]> listConciliarDropped) {
        this.listConciliarDropped = listConciliarDropped;
    }

    public ConciliarAudioAnexo getConciliarAudioAnexoView() {
        return conciliarAudioAnexoView;
    }

    public void setConciliarAudioAnexoView(ConciliarAudioAnexo conciliarAudioAnexoView) {
        this.conciliarAudioAnexoView = conciliarAudioAnexoView;
    }
}
