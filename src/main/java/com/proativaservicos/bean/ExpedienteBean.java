package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.Expediente;
import com.proativaservicos.model.ExpedienteDiaSemana;
import com.proativaservicos.service.ExpedienteDiaSemanaService;
import com.proativaservicos.service.ExpedienteService;
import com.proativaservicos.util.constantes.DiasDaSemanaEnum;
import com.proativaservicos.util.constantes.MessagesEnum;
import com.proativaservicos.util.constantes.TipoPaginaEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.omnifaces.util.Messages;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.*;

@Named
@ViewScoped
public class ExpedienteBean extends GenericBean {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Inject
    private ExpedienteService serviceExpediente;

    @Inject
    private ExpedienteDiaSemanaService serviceExpedienteDiaSemana;

    private Expediente expediente;

    private List<Expediente> listExpedientes;

    private List<ExpedienteDiaSemana> listExpedientesDiaSemanaCadastro = new ArrayList<>();

    private ExpedienteDiaSemana novoExpedienteDiaSemana = new ExpedienteDiaSemana();

    private List<DiasDaSemanaEnum> opcoesUtilizadasDiasSemanaEnum = new ArrayList<>();

    private List<DiasDaSemanaEnum> diasSemanaEnumSeremIgnorados;

    private TipoPaginaEnum tipoPagina;

    private boolean statusEditarExpediente;

    @PostConstruct
    public void inicializar() {

        try {

            this.expediente = new Expediente();

            this.expediente.setEmpresa(retornarEmpresaUsuarioSessao());

            pesquisar();
            statusEditarExpediente = false;
            this.tipoPagina = TipoPaginaEnum.PESQUISA;

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }

    public void pesquisar() {

        try {

            this.listExpedientes = this.serviceExpediente.pesquisarExpedientes(this.expediente.getEmpresa().getId(),
                    this.expediente);

        } catch (Exception e) {

            e.printStackTrace();

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }
    }

    private void inicializarVariaveis() {

        this.opcoesUtilizadasDiasSemanaEnum = new ArrayList<>();

    }

    public void novo() {

        inicializarVariaveis();
        this.expediente = new Expediente();
        this.tipoPagina = TipoPaginaEnum.CADASTRO;

    }

    public void editar(Expediente exp) {


        inicializarVariaveis();

        this.expediente = new Expediente();
        this.expediente = serviceExpediente.pesquisarExpediente(exp.getId());

        iniciarListaExpedientes(this.expediente.getListDiasDaSemana());

        this.tipoPagina = TipoPaginaEnum.CADASTRO;

    }

    private void iniciarListaExpedientes(List<ExpedienteDiaSemana> listDiasSemana) {

        Map<String, ExpedienteDiaSemana> mapHorarioDiaSemana = new HashMap<>();

        this.listExpedientesDiaSemanaCadastro = new ArrayList<>();

        for (ExpedienteDiaSemana expedienteDiaSemana : listDiasSemana) {

            String entrada = expedienteDiaSemana.getHoraEntrada().toString();
            String saida = expedienteDiaSemana.getHoraSaida().toString();
            String chave = entrada + saida;

            this.opcoesUtilizadasDiasSemanaEnum.add(expedienteDiaSemana.getId().getDiaSemana());
            ExpedienteDiaSemana value = mapHorarioDiaSemana.get(chave);

            if (value != null) {

                value.getListDiasSemanaSelecionado().add(expedienteDiaSemana.getId().getDiaSemana());
                continue;
            }

            ExpedienteDiaSemana diaSemanaId = new ExpedienteDiaSemana(expedienteDiaSemana.getId().getDiaSemana(),
                    this.expediente, expedienteDiaSemana.getHoraEntrada(), expedienteDiaSemana.getHoraSaida());

            mapHorarioDiaSemana.put(chave, diaSemanaId);

            this.listExpedientesDiaSemanaCadastro.add(diaSemanaId);

        }

    }

    public void salvar() {

        try {

            if (this.expediente.getId() != null) {

                System.out.println("DELETANDO....");
                serviceExpedienteDiaSemana.deletarListExpedienteDiaSemana(this.expediente.getId());
                this.expediente.setListDiasDaSemana(new ArrayList<>());

            }

            adicionarDiasSemana(this.expediente, this.listExpedientesDiaSemanaCadastro);

            if (this.expediente.getId() == null) {

                inserir((Serializable) this.expediente, false);

                this.expediente = new Expediente();

                Messages.addGlobalInfo(MessagesEnum.SALVO_COM_SUCESSO.constante, new Object[0]);

            } else {

                System.out.println("ALTERAR....");

                serviceExpedienteDiaSemana.deletarListExpedienteDiaSemana(this.expediente.getId());

                for (ExpedienteDiaSemana exp : this.expediente.getListDiasDaSemana()) {

                    System.out.println(exp.getId().getExpediente().getDescricao());
                    System.out.println(exp.getId().getDiaSemana());

                }

                alterar((Serializable) this.expediente, false);

                Messages.addGlobalInfo(MessagesEnum.ALTERADO_COM_SUCESSO.constante, new Object[0]);
            }

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }
    }

    private void adicionarDiasSemana(Expediente expediente, List<ExpedienteDiaSemana> listExpedientesCadastro) {

        for (ExpedienteDiaSemana expedienteDiaSemanaPK : listExpedientesCadastro) {

            if (expedienteDiaSemanaPK.getListDiasSemanaSelecionado() != null
                    && !expedienteDiaSemanaPK.getListDiasSemanaSelecionado().isEmpty()) {

                for (DiasDaSemanaEnum diaSemana : expedienteDiaSemanaPK.getListDiasSemanaSelecionado()) {

                    System.out.println("DIA SEMANA: " + diaSemana.getDescricao());

                    ExpedienteDiaSemana pk = new ExpedienteDiaSemana(diaSemana, expediente,
                            expedienteDiaSemanaPK.getHoraEntrada(), expedienteDiaSemanaPK.getHoraSaida());

                    if (expediente.getListDiasDaSemana() != null && expediente.getListDiasDaSemana().contains(pk)) {

                        int indice = expediente.getListDiasDaSemana().indexOf(pk);

                        if (indice != -1) {

                            ExpedienteDiaSemana expedienteExistente = expediente.getListDiasDaSemana().get(indice);
                            expedienteExistente.setHoraEntrada(expedienteDiaSemanaPK.getHoraEntrada());
                            expedienteExistente.setHoraSaida(expedienteDiaSemanaPK.getHoraSaida());
                            expediente.getListDiasDaSemana().set(indice, expedienteExistente);
                        }

                        continue;
                    }

                    expediente.getListDiasDaSemana().add(pk);
                }
            }
        }
    }

    public boolean validarDiaSemana(DiasDaSemanaEnum dia) {

        if (opcoesUtilizadasDiasSemanaEnum != null && this.statusEditarExpediente) {

            for (DiasDaSemanaEnum diasDaSemanaEnum : opcoesUtilizadasDiasSemanaEnum) {
                if (dia.equals(diasDaSemanaEnum)) {

                    return true;
                }

            }

        }

        return false;
    }

    public void voltar() {

        this.expediente = new Expediente();
        this.expediente.setEmpresa(retornarEmpresaUsuarioSessao());
        this.listExpedientesDiaSemanaCadastro = new ArrayList<>();

        pesquisar();

        this.tipoPagina = TipoPaginaEnum.PESQUISA;
    }

    /**
     * Adiciona novo expediente d Dias da semana
     */

    public void adicionarNovoExpediente() {


        this.statusEditarExpediente = true;
        this.novoExpedienteDiaSemana = new ExpedienteDiaSemana();

    }

    public void editarExpediente(ExpedienteDiaSemana expedienteEditar) {

        this.novoExpedienteDiaSemana = expedienteEditar;

        this.diasSemanaEnumSeremIgnorados = this.novoExpedienteDiaSemana.getListDiasSemanaSelecionado();
        this.statusEditarExpediente = false;

    }


    public void removerExpediente(ExpedienteDiaSemana expedienteEdicao) {


        this.opcoesUtilizadasDiasSemanaEnum.removeAll(expedienteEdicao.getListDiasSemanaSelecionado());
        this.listExpedientesDiaSemanaCadastro.remove(expedienteEdicao);

    }

    public String getListDiasSemanaValue(List<DiasDaSemanaEnum> diasSemana) {

        if (diasSemana != null) {

            StringJoiner joiner = new StringJoiner(", ");

            for (DiasDaSemanaEnum diaSemanaEnum : diasSemana) {

                joiner.add(diaSemanaEnum.toString());

            }
            return joiner.toString();
        }

        return "";
    }

    public boolean verificarDisponibilidade(DiasDaSemanaEnum dia) {

        if (opcoesUtilizadasDiasSemanaEnum == null)
            return false;

        for (DiasDaSemanaEnum diaSemana : this.novoExpedienteDiaSemana.getListDiasSemanaSelecionado()) {

            System.out.println(diaSemana);

            if (opcoesUtilizadasDiasSemanaEnum.contains(diaSemana)) {

                return true;
            }

        }

        return false;
    }

    public void adicionarExpediente() {

        List<DiasDaSemanaEnum> opcoesUtilizadasAux = new ArrayList<>(this.opcoesUtilizadasDiasSemanaEnum);

        if (this.diasSemanaEnumSeremIgnorados != null) {

            opcoesUtilizadasAux.removeAll(this.diasSemanaEnumSeremIgnorados);
        }

        boolean diaSemanaJaFoiUtilizado = false;

        for (DiasDaSemanaEnum diaSemana : this.novoExpedienteDiaSemana.getListDiasSemanaSelecionado()) {

            if (opcoesUtilizadasAux != null && !opcoesUtilizadasAux.contains(diaSemana)) {

                opcoesUtilizadasAux.add(diaSemana);
                continue;
            }

            diaSemanaJaFoiUtilizado = true;

        }

        if (diaSemanaJaFoiUtilizado) {

            if (opcoesUtilizadasAux != null) {

                if (this.diasSemanaEnumSeremIgnorados != null)
                    opcoesUtilizadasAux.removeAll(this.diasSemanaEnumSeremIgnorados);

            }

            StringJoiner descricoes = new StringJoiner(", ");

            opcoesUtilizadasAux.forEach(opcao -> descricoes.add(opcao.getDescricao()));

            Messages.addGlobalError("Informações indisponiveis " + ": " + descricoes.toString() + ".", new Object[0]);

        } else {

            boolean jaEstavaNaLista = false;

            for (ExpedienteDiaSemana item : this.listExpedientesDiaSemanaCadastro) {

                if (item.getListDiasSemanaSelecionado()
                        .equals(this.novoExpedienteDiaSemana.getListDiasSemanaSelecionado())) {

                    item.setListDiasSemanaSelecionado(this.novoExpedienteDiaSemana.getListDiasSemanaSelecionado());
                    jaEstavaNaLista = true;
                }
            }

            if (!jaEstavaNaLista) {

                this.listExpedientesDiaSemanaCadastro.add(this.novoExpedienteDiaSemana);

            }

            this.opcoesUtilizadasDiasSemanaEnum = new ArrayList<>(opcoesUtilizadasAux);

            PrimeFaces.current().ajax().update("formCadastro:tableExpedientesCadastro");
            PrimeFaces.current().executeScript("PF('dlgCadastroExpediente').hide();");
        }
    }

    public void trocarEmpresa() {

    }

    public Expediente getExpediente() {
        return expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

    public List<Expediente> getListExpedientes() {
        return listExpedientes;
    }

    public void setListExpedientes(List<Expediente> listaExpedientes) {
        this.listExpedientes = listaExpedientes;
    }

    public List<ExpedienteDiaSemana> getListExpedientesDiaSemanaCadastro() {
        return listExpedientesDiaSemanaCadastro;
    }

    public void setListExpedientesDiaSemanaCadastro(List<ExpedienteDiaSemana> listExpedientesDiaSemanaCadastro) {
        this.listExpedientesDiaSemanaCadastro = listExpedientesDiaSemanaCadastro;
    }

    public DiasDaSemanaEnum[] getDiasSemanaEnum() {
        return DiasDaSemanaEnum.values();
    }

    public void setDiasSemanaEnum(DiasDaSemanaEnum diasSemanaEnum) {
    }

    public ExpedienteDiaSemana getNovoExpedienteDiaSemana() {
        return novoExpedienteDiaSemana;
    }

    public void setNovoExpedienteDiaSemana(ExpedienteDiaSemana novoExpediente) {
        this.novoExpedienteDiaSemana = novoExpediente;
    }

    public List<DiasDaSemanaEnum> getOpcoesUtilizadas() {
        return opcoesUtilizadasDiasSemanaEnum;
    }

    public void setOpcoesUtilizadas(List<DiasDaSemanaEnum> opcoesUtilizadas) {
        this.opcoesUtilizadasDiasSemanaEnum = opcoesUtilizadas;
    }

    public List<DiasDaSemanaEnum> getDiasSemanaSeremIgnorados() {
        return diasSemanaEnumSeremIgnorados;
    }

    public void setDiasSemanaSeremIgnorados(List<DiasDaSemanaEnum> diasSemanaSeremIgnorados) {
        this.diasSemanaEnumSeremIgnorados = diasSemanaSeremIgnorados;
    }

    public TipoPaginaEnum getTipoPagina() {
        return tipoPagina;
    }

    public void setTipoPagina(TipoPaginaEnum tipoPagina) {
        this.tipoPagina = tipoPagina;
    }

    public boolean isStatusEditarExpediente() {
        return statusEditarExpediente;
    }

}
