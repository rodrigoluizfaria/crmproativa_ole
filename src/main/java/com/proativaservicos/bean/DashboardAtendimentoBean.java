package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.Equipe;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.service.AtendimentoService;
import com.proativaservicos.service.EquipeService;
import com.proativaservicos.service.UsuarioService;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.constantes.DataEnum;
import com.proativaservicos.util.constantes.MessagesEnum;
import com.proativaservicos.util.constantes.PerfilUsuarioEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.omnifaces.util.Messages;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class DashboardAtendimentoBean extends GenericBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private AtendimentoService serviceAtendimento;

    @Inject
    private EquipeService serviceEquipe;

    @Inject
    private UsuarioService serviceUsuario;

    private Usuario usuario;

    private Date dateInicial;

    private Date dataFinal;

    private Long[] arrayEquipesSelecionadas;

    private Long[] arrayUsuariosSelecionados;

    private List<Equipe> listEquipes;

    private List<Usuario> listUsuarios;

    private Map<String, Object[]> mapListAtendimentos;

    private LineChartModel lineChartModel;

    @PostConstruct
    public void init() {

        try {

            this.usuario = retornarUsuarioSessao();

            inicializarEmpresa(retornarEmpresaUsuarioSessao());

            trocarEmpresa();

            this.dateInicial = DateUtil.builder().removerTempoData(DataEnum.DIA, Integer.valueOf(30)).getData();
            this.dataFinal = new Date();

            pesquisar();

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }
    }

    public void pesquisar() {

        try {

            if (this.dateInicial != null && this.dataFinal != null && this.dateInicial.after(this.dataFinal)) {

                throw new ProativaException(MessagesEnum.A_DATA_DE_INICIO_DEVE_SER_MENOR_OU_IGUAL_A_DATA_DE_TERMINO.constante);

            }

            this.mapListAtendimentos = this.serviceAtendimento.pesquisarDashBoardAtendimento(getEmpresa().getId(),
                    this.arrayEquipesSelecionadas, this.arrayUsuariosSelecionados, this.dateInicial, this.dataFinal);

            criarGrafico();

        } catch (ProativaException e) {


            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    private void criarGrafico() {

        try {

            this.lineChartModel = new LineChartModel();
            ChartData data = new ChartData();

            LineChartDataSet dataSetAtendimentos = new LineChartDataSet();
            LineChartDataSet dataSetPropostas = new LineChartDataSet();


            List<Object> valuesAtendimentos = new ArrayList<>();
            List<Object> valuesPropostas = new ArrayList<>();

            dataSetPropostas.setFill(false);
            dataSetPropostas.setLabel("Qtidade Propostas");
            dataSetPropostas.setBorderColor("rgba(75, 192, 192)");
            //  dataSetPropostas.setLineTension(0.1);
            data.addChartDataSet(dataSetPropostas);

            dataSetAtendimentos.setFill(false);
            dataSetAtendimentos.setLabel("Qtidade Atendimentos");
            dataSetAtendimentos.setBorderColor("rgba(54, 162, 235)");
            //    dataSetAtendimentos.setLineTension(0.1);
            data.addChartDataSet(dataSetAtendimentos);

            List<String> labels = new ArrayList<>();


            for (Date dataVal = this.dateInicial; dataVal.before(this.dataFinal); dataVal = DateUtil.builder(dataVal).adicionarTempoData(DataEnum.DIA, Integer.valueOf(1)).getData()) {

                String strDataFormat = DateUtil.builder(dataVal).formatarDataParaString("dd/MM/yyyy").getDataTexto();

                Object[] retorno = this.mapListAtendimentos.get(strDataFormat);

                if (retorno == null) {

                    labels.add(strDataFormat);
                    valuesAtendimentos.add(Integer.valueOf(0));
                    valuesPropostas.add(Integer.valueOf(0));


                } else {

                    labels.add(strDataFormat);

                    valuesAtendimentos.add((BigInteger) retorno[1]);
                    valuesPropostas.add((BigInteger) retorno[2]);

                }

            }

            dataSetAtendimentos.setData(valuesAtendimentos);
            dataSetPropostas.setData(valuesPropostas);
            data.setLabels(labels);


            //Options
            LineChartOptions options = new LineChartOptions();

            Title title = new Title();
            title.setDisplay(true);
            title.setText("Atendimentos Relizados Por Dia.");

            CartesianScales cScales = new CartesianScales();

            CartesianLinearAxes linearAxes = new CartesianLinearAxes();
            linearAxes.setId("left-y-axis");
            linearAxes.setPosition("left");
            linearAxes.setId("Quantidade Propostas");
            cScales.addXAxesData(linearAxes);
            cScales.setDisplay(true);

            options.setScales(cScales);

            options.setTitle(title);

            this.lineChartModel.setOptions(options);
            this.lineChartModel.setData(data);


        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }

    public void trocarEmpresa() {

        if (getEmpresa() == null) {

            this.listEquipes = null;
            this.listUsuarios = null;

        } else if (this.usuario.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

            this.listEquipes = this.serviceEquipe.pesquisarEquipesPorSupervisor(this.usuario.getId(), getEmpresa().getId());
            this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorSupervisor(this.usuario.getId(), getEmpresa().getId());

        } else {

            this.listEquipes = this.serviceEquipe.pesquisarEquipesPorEmpresa(getEmpresa().getId(), TipoAcessoEnum.ATIVO);
            this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorEmpresa(getEmpresa().getId());
        }

    }

    public Date getDateInicial() {
        return dateInicial;
    }

    public void setDateInicial(Date dateInicial) {
        this.dateInicial = dateInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Long[] getArrayEquipesSelecionadas() {
        return arrayEquipesSelecionadas;
    }

    public void setArrayEquipesSelecionadas(Long[] arrayEquipesSelecionadas) {
        this.arrayEquipesSelecionadas = arrayEquipesSelecionadas;
    }

    public Long[] getArrayUsuariosSelecionados() {
        return arrayUsuariosSelecionados;
    }

    public void setArrayUsuariosSelecionados(Long[] arrayUsuariosSelecionados) {
        this.arrayUsuariosSelecionados = arrayUsuariosSelecionados;
    }

    public List<Equipe> getListEquipes() {
        return listEquipes;
    }

    public void setListEquipes(List<Equipe> listEquipes) {
        this.listEquipes = listEquipes;
    }

    public List<Usuario> getListUsuarios() {
        return listUsuarios;
    }

    public void setListUsuarios(List<Usuario> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    public Map<String, Object[]> getMapListAtendimentos() {
        return mapListAtendimentos;
    }

    public void setMapListAtendimentos(Map<String, Object[]> mapListAtendimentos) {
        this.mapListAtendimentos = mapListAtendimentos;
    }

    public LineChartModel getLineChartModel() {
        return lineChartModel;
    }

    public void setLineChartModel(LineChartModel lineChartModel) {
        this.lineChartModel = lineChartModel;
    }

}
