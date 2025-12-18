package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.Campanha;
import com.proativaservicos.model.Equipe;
import com.proativaservicos.model.StatusCampanha;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.service.*;
import com.proativaservicos.util.ColorUtil;
import com.proativaservicos.util.constantes.MessagesEnum;
import com.proativaservicos.util.constantes.PerfilUsuarioEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import com.proativaservicos.util.constantes.TipoCampanhaEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.omnifaces.util.Messages;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;
import org.primefaces.model.charts.pie.PieChartOptions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class RelatorioStatusAtendimentoOperadorBean extends GenericBean {

    private static final long serialVersionUID = 1L;


    @Inject
    private EquipeService serviceEquipe;

    @Inject
    private UsuarioService serviceUsuario;

    @Inject
    private CampanhaService serviceCampanha;

    @Inject
    private AtendimentoService serviceAtendimento;

    @Inject
    private StatusCampanhaService serviceStatusCampanha;

    private Date dataInicial;

    private Date dataFinal;

    private List<Object[]> listRelatorio;

    private List<StatusCampanha> listStatusCampanha;

    private List<Equipe> listEquipes;

    private List<Campanha> listCampanhas;

    private List<Usuario> listUsuarios;

    private Long idUsuario;

    private Long idCampanha;

    private Long idStatusCampanha;

    private Long idEquipe;

    private Usuario usuarioLogado;

    private TipoCampanhaEnum tipoCampanha;

    private PieChartModel pieModel;

    @PostConstruct
    public void init() {

        try {
            this.pieModel = new PieChartModel();

            this.usuarioLogado = retornarUsuarioSessao();

            inicializarEmpresa();

            trocarEmpresa();

            this.listStatusCampanha = this.serviceStatusCampanha.pesquisarStatusCampanhaPorEmpresa(retornarEmpresaMatrizUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }

    public void pesquisar() {

        try {

            if (this.dataInicial != null && this.dataFinal != null && this.dataInicial.after(this.dataFinal))
                throw new ProativaException("A data inicial deve ser maior que a data final.");
            this.listRelatorio = this.serviceAtendimento.pesquisarRelatorioStatusAtendimentoOperador(this.idCampanha, this.idStatusCampanha, this.tipoCampanha, this.idEquipe, this.idUsuario, this.dataInicial, this.dataFinal, getEmpresa().getId());

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }


    }

    public void onActiveModal(List<Object[]> obj, String nome, String tipo) {

        this.pieModel = new PieChartModel();
        ChartData data = new ChartData();
        PieChartDataSet dataSet = new PieChartDataSet();

        List<Number> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        List<String> bgColors = new ArrayList<>();

        for (Object[] objects : obj) {

            labels.add((String) objects[0]);
            values.add((Number) objects[1]);
            bgColors.add(ColorUtil.getColorDinamic());


        }

        dataSet.setBackgroundColor(bgColors);
        dataSet.setData(values);
        data.setLabels(labels);
        data.addChartDataSet(dataSet);
        PieChartOptions options = new PieChartOptions();

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Agente: " + nome + " - Tipo Campanha: " + tipo);
        options.setTitle(title);
        this.pieModel.setOptions(options);
        this.pieModel.setData(data);

    }

    public void trocarEmpresa() {

        if (getEmpresa() == null) {

            this.listEquipes = null;
            this.listUsuarios = null;
            this.listCampanhas = null;

        } else if (this.usuarioLogado.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

            this.listCampanhas = this.serviceCampanha.pesquisarCampanhaPorSupervisor(this.usuarioLogado.getId(), getEmpresa().getId());
            this.listEquipes = this.serviceEquipe.pesquisarEquipesPorSupervisor(this.usuarioLogado.getId(), getEmpresa().getId());

            this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorSupervisor(this.usuarioLogado.getId(), getEmpresa().getId());

        } else {

            onUpdateSelectCampanha();

            this.listEquipes = this.serviceEquipe.pesquisarEquipesPorEmpresa(getEmpresa().getId(), TipoAcessoEnum.ATIVO);
            this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorEmpresa(getEmpresa().getId());

        }

    }

    public void onExportar() {

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyMMddmm");

            exportarArquivoCsv(Arrays.asList(new String[]{"usuario", "tipo_campanha", "total_status", "taxa_conversao", "proposta_efetivada", "agendamento", "alos", "cpfs_trabalhados", "cpc", "status", "quantidade", "percentual"}), this.listRelatorio, "relatorio_" + format.format(new Date()) + ".csv");

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }
    }

    public void onUpdateSelectCampanha() {

        if (getEmpresa() != null) {

            this.listCampanhas = this.serviceCampanha.pesquisarCampanhasPorEmpresa(getEmpresa().getId(), this.idStatusCampanha, this.tipoCampanha);

        }

    }

    public void onChangeEquipe(Long idTrocaEquipe) {

        try {

            if (idTrocaEquipe != null) {

                this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorEquipes(idTrocaEquipe);

            } else if (this.usuarioLogado.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

                this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorSupervisor(this.usuarioLogado.getId(),
                        getEmpresa().getId());

            } else {

                this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorEmpresa(getEmpresa().getId());

            }

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void setTipoCampanha(TipoCampanhaEnum tipoCampanha) {
        this.tipoCampanha = tipoCampanha;
    }

    public TipoCampanhaEnum getTipoCampanha() {
        return tipoCampanha;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public List<Object[]> getListRelatorio() {
        return listRelatorio;
    }

    public void setListRelatorio(List<Object[]> listRelatorio) {
        this.listRelatorio = listRelatorio;
    }

    public List<StatusCampanha> getListStatusCampanha() {
        return listStatusCampanha;
    }

    public void setListStatusCampanha(List<StatusCampanha> listStatusCampanha) {
        this.listStatusCampanha = listStatusCampanha;
    }

    public List<Equipe> getListEquipes() {
        return listEquipes;
    }

    public void setListEquipes(List<Equipe> listEquipes) {
        this.listEquipes = listEquipes;
    }

    public List<Campanha> getListCampanhas() {
        return listCampanhas;
    }

    public void setListCampanhas(List<Campanha> listCampanhas) {
        this.listCampanhas = listCampanhas;
    }

    public List<Usuario> getListUsuarios() {
        return listUsuarios;
    }

    public void setListUsuarios(List<Usuario> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdCampanha() {
        return idCampanha;
    }

    public void setIdCampanha(Long idCampanha) {
        this.idCampanha = idCampanha;
    }

    public Long getIdStatusCampanha() {
        return idStatusCampanha;
    }

    public void setIdStatusCampanha(Long idStatusCampanha) {
        this.idStatusCampanha = idStatusCampanha;
    }

    public Long getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(Long idEquipe) {
        this.idEquipe = idEquipe;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

}
