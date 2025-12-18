package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.*;
import com.proativaservicos.service.*;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.RegistroSistemaUtil;
import com.proativaservicos.util.constantes.*;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.PrimeFaces;
import org.primefaces.event.*;


import org.primefaces.event.schedule.ScheduleEntryMoveEvent;
import org.primefaces.event.schedule.ScheduleEntryResizeEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Named
@ViewScoped
public class MeusAtendimentosBean extends GenericBean {

    private static final long serialVersionUID = 1L;

    private Usuario usuario;
    private Empresa empresa;
    private Anotacoes anotacao;
    private List<GenericAtendimento> listAtendimentosAgendados;
    private List<StatusAtendimento> listStatusAtendimentos;
    private List<StatusContrato> listStatusContrato;
    private List<Anotacoes> listAnotacoes;
    private List<?> listAtendimentos;
    private List<?> listAtendimentosParaAdiantar;
    private List<?> listCampanhas;
    private List<?> listHistoricosAgendamentos;
    private List<?> listHistoricosAtendimentos;
    private List<?> listAtendimentosPendentes;

    @Inject
    private HistoricoAtendimentoService serviceHistorico;

    @Inject
    private AtendimentoService serviceAtendimento;

    @Inject
    private FichaAtendimentoBean fichaAtendimentoBean;

    @Inject
    private StatusAtendimentoService serviceStatusAtendimento;

    @Inject
    private StatusContratoService serviceStatusContrato;

    @Inject
    private AnotacoesService serviceAnotacoes;

    @Inject
    private CampanhaService serviceCampanha;

    @Inject
    private UsuarioService serviceUsuario;

    private GenericAtendimento atendimentoVisualizar;

    private GenericHistoricoAtendimento historicoAtendimento;

    private Boolean atnPreditivo;

    @Inject
    private RegistroSistemaUtil registro;

    @Inject
    private FormaPagamentoService serviceFormaPagamento;

    @Inject
    private ConcilicarAudioAnexoService serviceConcilicarAudioAnexo;

    private Long equipe;

    private String cpf;
    private String nome;
    private String protocolo;
    private String adesao;
    private Long idAtendimento;
    private Long idHistorico;
    private Long idSatusAtendimento;
    private Long idStatusContrato;
    private Long idCampanha;
    private Date dataAgendamento;
    private Date dataInicio;
    private Date dataAFim;
    private LocalDate dataEvento;


    private String valorPesquisaAdianteamento;

    private ScheduleModel eventModel;

    private List<FormaPagamento> listFormaPagamento;

    private List<ConciliarAudioAnexo> audios;

    @PostConstruct
    public void init() {

        inicializarVariaves();
        pesquisarAgendamentoData();
        this.registro.registrarLog(this.usuario.getId(), TipoEventoEnum.ACESSOU_MEUS_ATENDIMENTOS, "USUARIO: " + this.usuario.getNome() + " - ACESSOU MEUS ATENDIMENTOS");
        this.equipe = null;
    }

    public void inicializarVariaves() {

        try {

            this.valorPesquisaAdianteamento = "CPF";

            this.usuario = retornarUsuarioSessao();
            this.empresa = retornarEmpresaUsuarioSessao();
            this.dataInicio = DateUtil.builder(new Date()).retornarDataPrimeiroDiaMes().getData();
            this.dataAFim = new Date();

            this.listStatusAtendimentos = this.serviceStatusAtendimento.pesquisarStatusAtendimentosPorEmpresa(this.empresa.getId(), TipoAcessoEnum.ATIVO);

            this.listStatusContrato = this.serviceStatusContrato.pesquisarStatusContratoPorEmpresa(this.empresa.getId(), TipoStatusContratoEnum.HISTORICO, null, TipoAcessoEnum.ATIVO);

            this.dataAgendamento = new Date(System.currentTimeMillis());

            this.listAtendimentosPendentes = this.serviceAtendimento.pesquisarAtendimentosPendentes(this.usuario.getId(), Arrays.asList(new String[]{TipoCampanhaEnum.ATIVA.name(), TipoCampanhaEnum.RECEPTIVA.name(), TipoCampanhaEnum.PREDITIVA.name()}));

            if (this.listAtendimentosPendentes.isEmpty()) {

                this.listAtendimentosPendentes = null;
                this.usuario.setQuantidadePendentes(0);

            } else {

                retornarUsuarioSessao().setQuantidadePendentes(this.listAtendimentosPendentes.size());
            }

            this.usuario.setQuantidadeAgendamentos(this.serviceAtendimento.pesquisarQuantidadeAtendimentosAlerta(usuario.getId()));


            this.eventModel = (ScheduleModel) new DefaultScheduleModel();
            this.dataEvento = LocalDate.now();
            this.listAnotacoes = this.serviceAnotacoes.pesquisarAnotacoesPorUsuario(retornarUsuarioSessao().getId());

            for (Anotacoes anotacao : listAnotacoes) {

                this.eventModel.addEvent(DefaultScheduleEvent.builder().data(anotacao).title(anotacao.getTitulo()).startDate(converterLocalDate(anotacao.getDataInicio())).endDate(converterLocalDate(anotacao.getDataFim())).build());

            }

            if (this.usuario.getEmpresa().getParametroUsuarioCampanha() != null && this.usuario.getEmpresa().getParametroUsuarioCampanha().booleanValue()) {

                this.listCampanhas = this.serviceCampanha.pesquisarCampanhaOperacaoPorUsuario(this.usuario.getId());

                if (this.usuario.getCampanha() != null && this.listCampanhas != null && !this.listCampanhas.isEmpty()) {

                    for (Object objCamp : listCampanhas) {

                        Object rows[] = (Object[]) objCamp;

                        if (this.usuario.getCampanha().longValue() == ((Long) rows[0]).longValue()) {

                            this.idCampanha = (Long) rows[0];
                            break;
                        }

                    }

                }

            }

        } catch (Exception e) {

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }
    }

    public void pesquisarAgendamentoData() {

        try {

            this.listHistoricosAgendamentos = this.serviceHistorico.pesquisarAgendamentos(this.usuario.getId(), null, null, null, this.dataAgendamento, this.dataAgendamento, this.usuario, this.usuario.getEmpresa().getId(), false);

        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void pesquisarAtendimentos() {

        try {

            this.listAtendimentos = this.serviceAtendimento.pesquisarAtendimentosPorNomeCpf(this.cpf, this.nome,
                    this.adesao, this.protocolo, null, null, this.usuario.getId(), this.idSatusAtendimento,
                    this.idStatusContrato, this.dataInicio, this.dataAFim, this.usuario, null,
                    this.usuario.getEmpresa().getId());

        } catch (Exception e) {
            // TODO: handle exception
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }
    }

    public void pesquisarAtendimentosParaAdiantar() {

        try {

            this.equipe = this.serviceUsuario.pesquisarEquipeUsuario(this.usuario.getId());

            if (equipe == null)
                throw new ProativaException("Você de estar associado a uma equipe");


            if (this.valorPesquisaAdianteamento.equals("TELEFONE"))
                this.listAtendimentosParaAdiantar = this.serviceAtendimento.pesquisarAtendimentosNaoTrabalhadosPorTelefone(this.cpf, retornarEmpresaUsuarioSessao().getId(), this.equipe);

            else
                this.listAtendimentosParaAdiantar = this.serviceAtendimento.pesquisarAtendimentosNaoTrabalhados(this.cpf, retornarEmpresaUsuarioSessao().getId(), this.equipe);

        } catch (Exception e) {

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }
    }

    public void pesquisarHistoricosAtendimentos() {

        try {

            this.listHistoricosAtendimentos = this.serviceHistorico.pesquisarHIstoricoPorCpf(this.cpf, retornarEmpresaUsuarioSessao().getId());

        } catch (Exception e) {

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }

    public String adiantarFichaAtendimento() {

        try {

            if (this.idAtendimento == null || this.idHistorico == null) {

                throw new ProativaException("Erro ao adiantar ficha de atendimento.");
            }

            this.serviceHistorico.adiantarAgendamento(this.idHistorico);

            return this.fichaAtendimentoBean.adiantarAtendimento(this.idAtendimento);

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);
            return null;

        } catch (Exception e) {

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
            e.printStackTrace();
            return null;

        }

    }


    public String adiantarFichaAtendimentoNaoTrabalado() {

        if (this.idAtendimento != null) {

        //    if (this.atnPreditivo != null && Boolean.TRUE.equals(this.atnPreditivo)) {

                Faces.setFlashAttribute("idAtendimento", this.idAtendimento);
                Faces.setFlashAttribute("preditivo", Boolean.valueOf(true));

           // }

            return this.fichaAtendimentoBean.adiantarAtendimento(this.idAtendimento);

        }
        return null;
    }

    public void dateChange(SelectEvent<Date> event) {
        System.out.println("File Date: " + (Date) event.getObject());
        System.out.println("Hello... I am in DateChange");
    }

    private LocalDateTime converterLocalDate(Date data) {

        if (data == null)
            return null;

        return data.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

    }

    private Date converterLocalDateParaDate(LocalDateTime data) {

        if (data == null)
            return null;

        return Date.from(data.atZone(ZoneId.systemDefault()).toInstant());

    }

    public void salvarAnotacao() {

        try {

            if (StringUtils.isAllBlank(this.anotacao.getAnotacao())) {

                throw new ProativaException("Campo Anotação deve ser preenchido.");
            }

            if (this.anotacao.getDataInicio().after(this.anotacao.getDataFim())) {

                throw new ProativaException("Data de inicio de ser maior que a Data Fim");

            }

            if (this.anotacao.getId() == null) {

                inserir((Serializable) this.anotacao);

                this.eventModel.addEvent(DefaultScheduleEvent.builder().data(this.anotacao)
                        .startDate(converterLocalDate(this.anotacao.getDataInicio()))
                        .endDate(converterLocalDate(this.anotacao.getDataFim())).description(this.anotacao.getTitulo())
                        .build());

            } else {

                alterar((Serializable) this.anotacao);

                for (ScheduleEvent<?> e : this.eventModel.getEvents()) {

                    if (((Anotacoes) e.getData()).getId().longValue() == this.anotacao.getId()) {

                        this.eventModel.deleteEvent(e);

                        this.eventModel.addEvent(
                                DefaultScheduleEvent.builder().data(this.anotacao).title(this.anotacao.getTitulo())
                                        .startDate(converterLocalDate(this.anotacao.getDataInicio()))
                                        .endDate(converterLocalDate(this.anotacao.getDataFim())).build());

                        break;
                    }
                }

            }

            PrimeFaces.current().executeScript("PF('dlgAganda').hide();");

        } catch (ProativaException e) {
            e.printStackTrace();
            Messages.addGlobalError(e.getMessage(), new Object[0]);
        } catch (Exception e) {
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
            e.printStackTrace();
        }

    }

    public void deletarAnotacao() {

        try {

            for (ScheduleEvent<?> event : this.eventModel.getEvents()) {

                if (((Anotacoes) event.getData()).getId().longValue() == this.anotacao.getId().longValue()) {

                    this.eventModel.deleteEvent(event);
                    break;
                }

            }

            excluir(Anotacoes.class, this.anotacao.getId());
            PrimeFaces.current().executeScript("PF('dlgAganda').hide();");

        } catch (Exception e) {

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void alterarCampanhaAtiva() {

        this.usuario.setCampanha(this.idCampanha);
        Messages.addGlobalInfo(MessagesEnum.CAMPANHA_DE_TRABALHO_ALTERADA.constante, new Object[0]);

    }

    public void trocarData() {

    }

    public void onDateSelect(SelectEvent<?> selecEvent) {

        this.anotacao = new Anotacoes();
        this.anotacao.setUsuario(retornarUsuarioSessao());
        this.anotacao.setDataInicio(converterLocalDateParaDate((LocalDateTime) selecEvent.getObject()));
        this.anotacao.setDataFim(converterLocalDateParaDate((LocalDateTime) selecEvent.getObject()));

    }

    public void onEventSelect(SelectEvent<?> selecEvent) {

        this.anotacao = (Anotacoes) ((DefaultScheduleEvent<?>) selecEvent.getObject()).getData();
    }

    public void onEventMove(ScheduleEntryMoveEvent entry) {

        try {

            Anotacoes anotacao = (Anotacoes) entry.getScheduleEvent().getData();
            anotacao.setDataInicio(converterLocalDateParaDate(entry.getScheduleEvent().getStartDate()));
            anotacao.setDataFim(converterLocalDateParaDate(entry.getScheduleEvent().getEndDate()));

            alterar((Serializable) anotacao);

            Messages.addGlobalInfo("Alterado!", new Object[0]);

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void onEventResized(ScheduleEntryResizeEvent entry) {

        try {
            Anotacoes anotacao = (Anotacoes) entry.getScheduleEvent().getData();
            anotacao.setDataInicio(converterLocalDateParaDate(entry.getScheduleEvent().getStartDate()));
            anotacao.setDataFim(converterLocalDateParaDate(entry.getScheduleEvent().getEndDate()));

            alterar((Serializable) anotacao);
            Messages.addGlobalInfo("Alterado!", new Object[0]);

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void visualizarAtendimento(Long idAtendimento) {

        try {

            this.atendimentoVisualizar = this.serviceAtendimento.pesquisarAtendimentoContrato(idAtendimento);

            if (this.atendimentoVisualizar.getListaHistoricos() != null && !this.atendimentoVisualizar.getListaHistoricos().isEmpty()) {

                this.historicoAtendimento = this.atendimentoVisualizar.getListaHistoricos().get(0);

            } else {

                this.historicoAtendimento = new HistoricoAtendimento();
                this.historicoAtendimento.setDataCadastro(new Date(System.currentTimeMillis()));
            }

            // PESQUISA E QUESTIONARIO>>>>> outros

            this.listFormaPagamento = this.serviceFormaPagamento.pesquisarFormaPagamentosPorCampanha(this.atendimentoVisualizar.getCampanha().getId());
            this.audios = this.serviceConcilicarAudioAnexo.pesquisarPorAtendimento(idAtendimento);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }
    }

    public String gerarNovaProposta() {

        try {


            if (this.atendimentoVisualizar != null) {

                return this.fichaAtendimentoBean.criarNovaProposta(this.atendimentoVisualizar);

            }
            return null;

        } catch (Exception e) {

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
            return null;
        }
    }

    public void onPesquisarAudiosAnexos() {


    }


    public void enviarAudios(FileUploadEvent event) {

        try {

            if (CollectionUtils.isEmpty(audios)) {

                this.audios = new ArrayList<ConciliarAudioAnexo>();

            } else {

                for (ConciliarAudioAnexo conciliarAudioAnexo : audios) {

                    if (conciliarAudioAnexo.getNomeArquivoOriginal().equals(event.getFile().getFileName()))

                        throw new ProativaException("Não foi possivel anexar o arquivo: [ " + event.getFile().getFileName() + " ]. Arquivo já foi anexado.");

                }

            }

            if (event != null && event.getFile() != null) {

                int tamanhoArquivo = event.getFile().getFileName().length();

                if (event.getFile().getFileName().substring(tamanhoArquivo - 3, tamanhoArquivo).equalsIgnoreCase("wav")) {

                    ConciliarAudioAnexo conciliar = new ConciliarAudioAnexo();
                    conciliar.setNomeArquivoOriginal(event.getFile().getFileName());
                    conciliar.setTamanhoArquivo(event.getFile().getSize());
                    conciliar.setInpuStream(event.getFile().getInputStream());
                    this.audios.add(conciliar);

                } else {

                    throw new ProativaException("Formato de arquivo invalido");
                }

                Messages.addGlobalInfo("Áudio enviado com sucesso!", new Object[0]);
            }

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void onTrocarAudio(ReorderEvent event) {

        if (CollectionUtils.isNotEmpty(this.audios)) {


            //Collections.swap(this.audios, event.getFromIndex()	, event.getToIndex());


        }

    }

    //EXCLUIR DO DIRETORIO
    public void excluirAnexoArquivo(String audio) {

        try {

            if (CollectionUtils.isNotEmpty(this.audios)) {

                this.audios.removeIf(a -> a.getNomeArquivoOriginal().equals(audio));

                Optional<ConciliarAudioAnexo> conciliar = this.audios.stream().filter(a -> a.getNomeArquivoOriginal().equals(audio)).findFirst();

                if (conciliar.isPresent())
                    this.serviceConcilicarAudioAnexo.excluir(conciliar.get());

            }

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<GenericAtendimento> getListAtendimentosAgendados() {
        return listAtendimentosAgendados;
    }

    public void setListAtendimentosAgendados(List<GenericAtendimento> listAtendimentosAgendados) {
        this.listAtendimentosAgendados = listAtendimentosAgendados;
    }

    public List<?> getListAtendimentos() {
        return listAtendimentos;
    }

    public void setListAtendimentos(List<?> listAtendimentos) {
        this.listAtendimentos = listAtendimentos;
    }

    public List<StatusAtendimento> getListStatusAtendimentos() {
        return listStatusAtendimentos;
    }

    public void setListStatusAtendimentos(List<StatusAtendimento> listStatusAtendimentos) {
        this.listStatusAtendimentos = listStatusAtendimentos;
    }

    public List<StatusContrato> getListStatusContrato() {
        return listStatusContrato;
    }

    public void setListStatusContrato(List<StatusContrato> listStatusContrato) {
        this.listStatusContrato = listStatusContrato;
    }

    public List<?> getListHistoricosAgendamentos() {
        return listHistoricosAgendamentos;
    }

    public void setListHistoricosAgendamentos(List<?> listHistoricosAgendamentos) {
        this.listHistoricosAgendamentos = listHistoricosAgendamentos;
    }

    public FichaAtendimentoBean getFichaAtendimentoBean() {
        return fichaAtendimentoBean;
    }

    public void setFichaAtendimentoBean(FichaAtendimentoBean fichaAtendimentoBean) {
        this.fichaAtendimentoBean = fichaAtendimentoBean;
    }

    public Long getIdAtendimento() {
        return idAtendimento;
    }

    public void setIdAtendimento(Long idAtendimento) {
        this.idAtendimento = idAtendimento;
    }

    public Long getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(Long idHistorico) {
        this.idHistorico = idHistorico;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getIdSatusAtendimento() {
        return idSatusAtendimento;
    }

    public void setIdSatusAtendimento(Long idSatusAtendimento) {
        this.idSatusAtendimento = idSatusAtendimento;
    }

    public Long getIdStatusContrato() {
        return idStatusContrato;
    }

    public void setIdStatusContrato(Long idStatusContrato) {
        this.idStatusContrato = idStatusContrato;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public Date getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(Date dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataAFim() {
        return dataAFim;
    }

    public void setDataAFim(Date dataAFim) {
        this.dataAFim = dataAFim;
    }

    public String getAdesao() {
        return adesao;
    }

    public void setAdesao(String adesao) {
        this.adesao = adesao;
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public Anotacoes getAnotacao() {
        return anotacao;
    }

    public void setAnotacao(Anotacoes anotacao) {
        this.anotacao = anotacao;
    }

    public LocalDate getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }

    public List<?> getListCampanhas() {
        return listCampanhas;
    }

    public void setListCampanhas(List<?> listCampanhas) {
        this.listCampanhas = listCampanhas;
    }

    public Long getIdCampanha() {
        return idCampanha;
    }

    public void setIdCampanha(Long idCampanha) {
        this.idCampanha = idCampanha;
    }

    public List<?> getListAtendimentosParaAdiantar() {
        return listAtendimentosParaAdiantar;
    }

    public void setListAtendimentosParaAdiantar(List<?> listAtendimentosParaAdiantar) {
        this.listAtendimentosParaAdiantar = listAtendimentosParaAdiantar;
    }

    public List<?> getListHistoricosAtendimentos() {
        return listHistoricosAtendimentos;
    }

    public void setListHistoricosAtendimentos(List<?> listHistoricosAtendimentos) {
        this.listHistoricosAtendimentos = listHistoricosAtendimentos;
    }

    public List<Anotacoes> getListAnotacoes() {
        return listAnotacoes;
    }

    public void setListAnotacoes(List<Anotacoes> listAnotacoes) {
        this.listAnotacoes = listAnotacoes;
    }

    public GenericAtendimento getAtendimentoVisualizar() {
        return atendimentoVisualizar;
    }

    public void setAtendimentoVisualizar(GenericAtendimento atendimentoVisualizar) {
        this.atendimentoVisualizar = atendimentoVisualizar;
    }

    public GenericHistoricoAtendimento getHistoricoAtendimento() {
        return historicoAtendimento;
    }

    public void setHistoricoAtendimento(GenericHistoricoAtendimento historicoAtendimento) {
        this.historicoAtendimento = historicoAtendimento;
    }

    public List<FormaPagamento> getListFormaPagamento() {
        return listFormaPagamento;
    }

    public void setListFormaPagamento(List<FormaPagamento> listFormaPagamento) {
        this.listFormaPagamento = listFormaPagamento;
    }

    public Integer getIdade() {

        if (this.atendimentoVisualizar != null && this.atendimentoVisualizar.getDataNascimento() != null) {

            return Integer.valueOf(DateUtil.builder(this.atendimentoVisualizar.getDataNascimento()).calcularIdade());

        }
        return null;
    }

    public List<?> getListAtendimentosPendentes() {
        return listAtendimentosPendentes;
    }

    public void setListAtendimentosPendentes(List<?> listAtendimentosPendentes) {
        this.listAtendimentosPendentes = listAtendimentosPendentes;
    }

    public String getValorPesquisaAdianteamento() {
        return valorPesquisaAdianteamento;
    }

    public void setValorPesquisaAdianteamento(String valorPesquisaAdianteamento) {
        this.valorPesquisaAdianteamento = valorPesquisaAdianteamento;
    }

    public Boolean getAtnPreditivo() {
        return atnPreditivo;
    }

    public void setAtnPreditivo(Boolean atnPreditivo) {
        this.atnPreditivo = atnPreditivo;
    }

    public List<ConciliarAudioAnexo> getAudios() {
        return audios;
    }

    public void setAudios(List<ConciliarAudioAnexo> audios) {
        this.audios = audios;
    }

}
