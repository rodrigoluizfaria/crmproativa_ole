package com.proativaservicos.bean;

import com.proativaservicos.listener.ControlUserSession;
import com.proativaservicos.model.Equipe;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.service.EquipeService;
import com.proativaservicos.service.RegistroSistemaDiarioService;
import com.proativaservicos.service.UsuarioLogadoService;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.constantes.DataEnum;
import com.proativaservicos.util.constantes.PerfilUsuarioEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import com.proativaservicos.util.constantes.TipoEventoEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.*;

@Named
@ViewScoped
public class SupervisorMonitorAtividadeBean extends GenericBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EquipeService serviceEquipe;

    private List<Equipe> listEquipes;

    private Long idEquipe;

    private Usuario usuario;

    @Inject
    private RegistroSistemaDiarioService serviceRegistroSistemaDiario;

    private final List<TipoEventoEnum> LISTA_EVENTOS = Arrays.asList(TipoEventoEnum.retornarEventosOperacional());

    private List<?> listRegistroOperacional;

    private List<Object[]> listaMonitorAgente;

    private Long taxaAtualizacao;

    private String vizualizacao;

    private Integer aguardando;
    private Integer emAtendimento;
    private Integer emPausa;
    private Integer outros;

    @Inject
    private ControlUserSession controlSession;


    @Inject
    private UsuarioLogadoService serviceUsuarioLogado;


    @PostConstruct
    public void init() {

        this.taxaAtualizacao = 20L;
        inicializarEmpresa(retornarEmpresaUsuarioSessao());
        this.vizualizacao = "GRID";
        this.usuario = retornarUsuarioSessao();

        trocarSupervisor();
        onUpdate();

    }

    public void onUpdate() {
        pesquisar();
    }

    public void trocarSupervisor() {

        if (this.usuario.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

            this.listEquipes = this.serviceEquipe.pesquisarEquipesPorSupervisor(this.usuario.getId(), getEmpresa().getId());

        } else {

            this.listEquipes = this.serviceEquipe.pesquisarEquipesPorEmpresa(getEmpresa().getId(), TipoAcessoEnum.ATIVO);

        }
    }

    public void trocarEmpresa() {
        trocarSupervisor();
    }

    public void pesquisar() {

        this.emAtendimento = null;
        this.aguardando = null;
        this.outros = null;
        this.emPausa = null;

        if (this.usuario.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

            this.listRegistroOperacional = this.serviceRegistroSistemaDiario.pesquisarEventosOperacional(this.usuario.getId(), this.idEquipe, this.LISTA_EVENTOS);

        } else {

            this.listRegistroOperacional = this.serviceRegistroSistemaDiario.pesquisarEventosOperacional(null, this.idEquipe, this.LISTA_EVENTOS);

        }

        Map<Integer, Object[]> mapValues = new HashMap<Integer, Object[]>();

        for (Object object : listRegistroOperacional) {

            Object[] ob = (Object[]) object;

            Integer idUsuario = (Integer) ob[0];

            if (!mapValues.containsKey(idUsuario)) {

                mapValues.put(idUsuario, new Object[11]);

                for (int i = 0; i < ob.length; i++) {

                    mapValues.get(idUsuario)[i] = ob[i];

                }

                mapValues.get(idUsuario)[10] = DateUtil.builder((Date) ob[3], new Date()).calcularDiferencaDatas(DataEnum.SEGUNDO).getDataNumerico();

                mapValues.get(idUsuario)[2] = EnumUtils.getEnum(TipoEventoEnum.class, (String) ob[2]);

            }

        }


        this.listaMonitorAgente = new ArrayList<Object[]>();

        for (Integer val : mapValues.keySet()) {

            Object[] values = new Object[14];

            Object[] ob = (Object[]) mapValues.get(val);

            TipoEventoEnum tipoEvento = (TipoEventoEnum) ob[2];
            values[0] = tipoEvento.getConstante();
            values[1] = (String) ob[1];
            values[2] = (String) ob[5];
            values[3] = (String) ob[6];

            String msgTempo = "";

            if (tipoEvento.equals(TipoEventoEnum.INICIOU_ATENDIMENTO) || tipoEvento.equals(TipoEventoEnum.ENCERROU_ATENDIMENTO)) {

                String evento[] = validarEventos(((String) ob[4]), tipoEvento).split("[|]");

                if (evento.length >= 2) {

                    values[4] = StringUtils.trimToEmpty(evento[0]);
                    values[5] = StringUtils.trimToEmpty(evento[1]);
                } else {

                    values[4] = validarEventos(((String) ob[4]), tipoEvento);
                    values[5] = "";
                }


                if (tipoEvento.equals(TipoEventoEnum.INICIOU_ATENDIMENTO))
                    msgTempo = "Em atendimento à ";

            } else {

                values[4] = StringUtils.trimToEmpty(validarEventos(((String) ob[4]), tipoEvento));
                values[5] = "";

            }

            values[6] = (String) "INÍCIO: " + DateUtil.builder((Date) ob[3]).formatarDataParaString("dd/MM/yyyy HH:mm:ss").getDataTexto();

            Number tempo = ((Number) ob[10]);

            values[7] = msgTempo + retornarTempo(tempo.intValue());

            if (tempo.intValue() > tipoEvento.getTempoAlerta()) {

                //#dc3545 !important;
                values[8] = "#f03a3e";

            } else if (tempo.intValue() > tipoEvento.getTempoAviso() && tempo.intValue() < tipoEvento.getTempoAlerta()) {

                //#ffc107 !important
                values[8] = "#f5a623";

            } else {
                //#007bff !important;
                values[8] = "#495057";
            }

            values[9] = (String) ob[7];
            values[10] = (String) ob[8];
            values[11] = ob[0];
            values[12] = ob[9];
            values[13] = retornarCor(tipoEvento);

            this.listaMonitorAgente.add(values);

        }

    }

    private String retornarCor(TipoEventoEnum evento) {

        if (evento.equals(TipoEventoEnum.INICIOU_ATENDIMENTO)) {
            return "bg-red-700";


        } else if (evento.equals(TipoEventoEnum.AGUARDANDO_ATENDIMENTO)) {
            return "bg-green-700";

        } else if (evento.equals(TipoEventoEnum.PAUSA)) {
            return "bg-orange-700";

        }

        return "bg-primary-700";
    }

    private String retornarTempo(Integer tempo) {

        if (tempo != null) {

            int horas = tempo / 3600;
            int minutos = (tempo - (horas * 3600)) / 60;
            int segundos = tempo - (horas * 3600) - (minutos * 60);

            if (tempo.intValue() < 60) {

                return tempo.toString() + "s.";

            } else if (tempo.intValue() > 60 && tempo.intValue() < 3600) {

                return tempo / 60 + "min " + segundos + "s";

            } else {

                return (horas) + "h " + minutos + "min " + segundos + "s.";

            }

        }
        return "";

    }

    public String validarEventos(String evento, TipoEventoEnum tipoEvento) {

        if (tipoEvento.equals(TipoEventoEnum.INICIOU_ATENDIMENTO)) {

            this.emAtendimento = (this.emAtendimento == null) ? Integer.valueOf(0) : (this.emAtendimento + 1);

            String desc[] = evento.split("[|]");

            if (desc.length > 2)
                return desc[2] + " | " + desc[1];

        } else if (tipoEvento.equals(TipoEventoEnum.FINALIZOU_ATENDIMENTO)) {

            String desc[] = evento.split("[|]");

            if (desc.length > 2)
                return desc[1] + " | " + desc[2];

        } else if (tipoEvento.equals(TipoEventoEnum.PAUSA)) {

            this.emPausa = (this.emPausa == null) ? Integer.valueOf(0) : (this.emPausa + 1);
            return "PAUSA: " + evento;

        } else if (tipoEvento.equals(TipoEventoEnum.AGUARDANDO_ATENDIMENTO)) {

            this.aguardando = (this.aguardando == null) ? Integer.valueOf(0) : (this.aguardando + 1);

        } else if (tipoEvento.equals(TipoEventoEnum.LOGIN)) {

            return "EFETUOU LOGIN.";

        } else if (tipoEvento.equals(TipoEventoEnum.LOGOUT)) {


        } else if (tipoEvento.equals(TipoEventoEnum.ACESSOU_MEUS_ATENDIMENTOS)) {

            return "ACESSOU MEUS ATENDIMENTOS.";

        } else {

            this.outros = (this.outros == null) ? Integer.valueOf(0) : (this.outros + 1);
        }

        return evento;

    }

    public void onDeslogar(Long id) {

        invalidSession(id);
    }

    public void alterarVizualizacao() {

        if (this.vizualizacao.equals("GRID"))
            this.vizualizacao = "TABLE";
        else
            this.vizualizacao = "GRID";
    }


    private void invalidSession(Long id) {

        String sessao = this.serviceUsuarioLogado.pesquisarSessaoUsuario(id);

        if (sessao != null) {

            this.serviceUsuarioLogado.excluirUsuarioLogadoPorLogin(sessao);
            this.controlSession.invalidarSessao(sessao);

        }
        pesquisar();


    }

    public List<Equipe> getListEquipes() {
        return listEquipes;
    }

    public void setListEquipes(List<Equipe> listEquipes) {
        this.listEquipes = listEquipes;
    }

    public Long getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(Long idEquipe) {
        this.idEquipe = idEquipe;
    }

    public Long getTaxaAtualizacao() {
        return taxaAtualizacao;
    }

    public void setTaxaAtualizacao(Long taxaAtualizacao) {
        this.taxaAtualizacao = taxaAtualizacao;
    }

    public List<Object[]> getListaMonitorAgente() {
        return listaMonitorAgente;
    }

    public String getVizualizacao() {


        return vizualizacao;
    }

    public void setVizualizacao(String vizualizacao) {
        this.vizualizacao = vizualizacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @return the aguardando
     */
    public Integer getAguardando() {
        return aguardando;
    }

    /**
     * @param aguardando the aguardando to set
     */
    public void setAguardando(Integer aguardando) {
        this.aguardando = aguardando;
    }

    /**
     * @return the emAtendimento
     */
    public Integer getEmAtendimento() {
        return emAtendimento;
    }

    /**
     * @param emAtendimento the emAtendimento to set
     */
    public void setEmAtendimento(Integer emAtendimento) {
        this.emAtendimento = emAtendimento;
    }

    /**
     * @return the emPausa
     */
    public Integer getEmPausa() {
        return emPausa;
    }

    /**
     * @param emPausa the emPausa to set
     */
    public void setEmPausa(Integer emPausa) {
        this.emPausa = emPausa;
    }

    /**
     * @return the outros
     */
    public Integer getOutros() {
        return outros;
    }

    /**
     * @param outros the outros to set
     */
    public void setOutros(Integer outros) {
        this.outros = outros;
    }


}
