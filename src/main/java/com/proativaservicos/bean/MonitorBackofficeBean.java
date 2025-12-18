package com.proativaservicos.bean;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.charts.donut.DonutChartOptions;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.optionconfig.tooltip.Tooltip;

import com.proativaservicos.model.Equipe;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.service.AtendimentoBackofficeService;
import com.proativaservicos.service.EquipeService;
import com.proativaservicos.util.ColorUtil;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.constantes.PerfilUsuarioEnum;
import com.proativaservicos.util.constantes.TipoVisualizacaoEnum;

@Named
@ViewScoped
public class MonitorBackofficeBean extends GenericBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EquipeService equipeService;

	@Inject
	private AtendimentoBackofficeService serviceAtendimentoBackoffice;
	
	private Date dataInicioPanel;
	
	private Date dataFimPanel;
	
	private String periodo;

	private DonutChartModel donutModelStatusAtendimento;

	private DonutChartModel donutModelMotivo;
	
	private DonutChartModel donutSubmodelMotivo;
	
	private List<?> listStatusPainel;
	
	private List<?> listMotioPainel;
	
	private List<?> listSubmotioPainel;
	
	private List<?> listQuantidadeConsistencia;

	private BarChartModel stackedBarModelConsistencia;
	
	private Long totalAtendimentos;
	
	private List<Equipe> listEquipes;
	
	private Usuario usuarioLogado;
	


	private List<?> listProdutividade;
	
	private Double totalAtendimento;
	private Double totalPropostas;
	
	private Double totalPago;
	private Double totalVendido;
	private Double totalContratos;
	private Double totalCpf;
	
	private Object[] produtividade;
	
	@PostConstruct
	public void init() {
		
		this.usuarioLogado = retornarUsuarioSessao();
		this.periodo = "DIA";
		this.dataInicioPanel = new Date();
		this.dataFimPanel  = new Date();
		
		criarDash();
		pesquisarQuantidadeConsistencia();
		
	}
	
	
	private void criarDash() {
		
		if(this.usuarioLogado.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {
			
			this.listStatusPainel = this.serviceAtendimentoBackoffice.pesquisarRelatorioStatusAtendimentoPorSupervisor(this.usuarioLogado.getId(), this.dataInicioPanel, this.dataFimPanel, null);
			this.listMotioPainel = this.serviceAtendimentoBackoffice.pesquisarRelatorioMotivoPorSupervisor(this.usuarioLogado.getId(),this.dataInicioPanel, this.dataFimPanel, null);
			this.listSubmotioPainel = this.serviceAtendimentoBackoffice.pesquisarRelatorioSubmotivoPorSupervisor(this.usuarioLogado.getId(), this.dataInicioPanel, this.dataFimPanel, null);
			
		}else {
					
			this.listStatusPainel = this.serviceAtendimentoBackoffice.pesquisarRelatorioStatusAtendimentoPorUsuario(null, this.dataInicioPanel, this.dataFimPanel, null);
			this.listMotioPainel = this.serviceAtendimentoBackoffice.pesquisarRelatorioMotivoPorUsuario(null,this.dataInicioPanel, this.dataFimPanel, null);
			this.listSubmotioPainel = this.serviceAtendimentoBackoffice.pesquisarRelatorioSubmotivoPorUsuario(null, this.dataInicioPanel, this.dataFimPanel, null);
			
		}
		
		criarCharts(listStatusPainel, "Status Atendimento");
		criarChatsMotivo();
		criarChatsSubmotivo();
				
		
	}
	
	private void pesquisarQuantidadeConsistencia() {
		
		this.listQuantidadeConsistencia = null;
	
		this.listProdutividade = null;
					
		if(usuarioLogado.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {
			
			List<Long> ids =	this.equipeService.pesquisarCodEquipesPorSupervisor(usuarioLogado.getId());
			
			if(ids!=null) {
				
				this.listProdutividade =	this.serviceAtendimentoBackoffice.pesquisarProdutividadeAtendimento(ids.toArray(Long[]::new), null,  null, null,	retornarUsuarioSessao(), this.dataInicioPanel, this.dataFimPanel, TipoVisualizacaoEnum.EQUIPE);
			
			}else {
				
				this.listProdutividade = null;
			}
			
			this.listQuantidadeConsistencia = this.serviceAtendimentoBackoffice.pesquisarQuantidadeConsistenciaSupervisor(retornarUsuarioSessao().getId(),this.dataInicioPanel,this.dataFimPanel);

			
		}else {
			
			this.listProdutividade =	this.serviceAtendimentoBackoffice.pesquisarProdutividadeAtendimento(null, null,  null, null,	retornarUsuarioSessao(), this.dataInicioPanel, this.dataFimPanel, TipoVisualizacaoEnum.EQUIPE);
			this.listQuantidadeConsistencia = this.serviceAtendimentoBackoffice.pesquisarQuantidadeConsistencia(null,this.dataInicioPanel,this.dataFimPanel);

		}
		
		somarTotaisFooter(listProdutividade);
		criarDashConsistencia();
	}
	
		
	
	
	private void somarTotaisFooter(List<?> list) {
		
		this.totalAtendimento = Double.valueOf(0.0D);
		this.totalCpf = Double.valueOf(0.0D);
		this.totalContratos = Double.valueOf(0.0D);
		this.totalPago = Double.valueOf(0.0D);
		this.totalPropostas = Double.valueOf(0.0D);
		this.totalVendido = Double.valueOf(0.0D);
		
		if(list!=null&&!list.isEmpty()) {
		
			for (Object obj : list) {
				
			this.totalAtendimento = Double.valueOf(this.totalAtendimento.doubleValue() + ((BigDecimal) ((Object[])obj)[2]).doubleValue());
			this.totalCpf = Double.valueOf(this.totalCpf.doubleValue() + ((BigDecimal) ((Object[])obj)[1]).doubleValue());
			this.totalPropostas = Double.valueOf(this.totalPropostas.doubleValue() + ((BigDecimal) ((Object[])obj)[3]).doubleValue());
			this.totalContratos = Double.valueOf(this.totalContratos.doubleValue() + ((BigDecimal) ((Object[])obj)[4]).doubleValue());
			this.totalVendido = Double.valueOf(this.totalVendido.doubleValue() + ((BigDecimal) ((Object[])obj)[5]).doubleValue());
			this.totalPago = Double.valueOf(this.totalPago.doubleValue() + ((BigDecimal) ((Object[])obj)[6]).doubleValue());
			
			}
			
		}
		
		
		
	}
	
	
	private void criarCharts(List<?> list, String title) {

		this.donutModelStatusAtendimento = new DonutChartModel();

		ChartData data = new ChartData();

		DonutChartDataSet dataSet = new DonutChartDataSet();

		List<Number> values = new ArrayList<>();
		List<String> labels = new ArrayList<>();
		List<String> bgColors = new ArrayList<>();

		for (Object linhaOb : list) {

			Object[] row = (Object[]) linhaOb;

			if (row[0] != null)
				values.add((BigDecimal) row[0]);
			else
				values.add(0);

			labels.add((String) row[2]);
			String cor = ColorUtil.getColorDinamic();
			bgColors.add(cor);
			row[3] = cor;
		}

		dataSet.setData(values);
		dataSet.setBackgroundColor(bgColors);
		data.addChartDataSet(dataSet);
		data.setLabels(labels);

		Legend legend = new Legend();
		legend.setDisplay(false);

		Title tile = new Title();
		tile.setText(title);

		DonutChartOptions op = new DonutChartOptions();
		op.setLegend(legend);
		op.setTitle(tile);

		this.donutModelStatusAtendimento.setOptions(op);

		this.donutModelStatusAtendimento.setData(data);

	}
	
	private void criarChatsMotivo() {

		ChartData data = criarDataSet(this.listMotioPainel);

		DonutChartOptions op = new DonutChartOptions();
		Legend legend = new Legend();
		legend.setDisplay(false);

		op.setLegend(legend);

		this.donutModelMotivo = new DonutChartModel();
		this.donutModelMotivo.setOptions(op);
		this.donutModelMotivo.setData(data);

	}

	private void criarChatsSubmotivo() {

		ChartData data = criarDataSet(this.listSubmotioPainel);

		DonutChartOptions op = new DonutChartOptions();
		Legend legend = new Legend();
		legend.setDisplay(false);

		op.setLegend(legend);

		this.donutSubmodelMotivo = new DonutChartModel();
		this.donutSubmodelMotivo.setOptions(op);
		this.donutSubmodelMotivo.setData(data);

	}

	private ChartData criarDataSet(List<?> list) {

		ChartData data = new ChartData();

		DonutChartDataSet dataSet = new DonutChartDataSet();

		List<Number> values = new ArrayList<>();
		List<String> labels = new ArrayList<>();
		List<String> bgColors = new ArrayList<>();

		for (Object linhaOb : list) {

			Object[] row = (Object[]) linhaOb;

			if (row[0] != null)
				values.add((BigDecimal) row[0]);
			else
				values.add(0);

			labels.add((String) row[2]);
			String cor = ColorUtil.getColorDinamic();
			bgColors.add(cor);
			row[3] = cor;
		}

		dataSet.setData(values);
		dataSet.setBackgroundColor(bgColors);
		data.addChartDataSet(dataSet);
		data.setLabels(labels);

		return data;

	}
	
	private void criarDashConsistencia() {
		
		this.stackedBarModelConsistencia = new BarChartModel();
		ChartData data = new ChartData();
		
	    List<String> labels = new ArrayList<>();
		 
	    BarChartDataSet barDataSetTratada= new BarChartDataSet();
	    BarChartDataSet barDataSetNaoTratada= new BarChartDataSet();
	    
	    barDataSetTratada.setLabel("Tratada");
	    barDataSetTratada.setBackgroundColor("rgb(75, 192, 192)");
	    
	    barDataSetNaoTratada.setLabel("Não tratadas");
	    barDataSetNaoTratada.setBackgroundColor("rgb(255, 99, 132)");
	    List<Number> dataSetTratada = new ArrayList<>();
	    List<Number> dataSetNaoTratada = new ArrayList<>();
	    
	    for (Object objects : this.listQuantidadeConsistencia) {
		    	
	    	Object [] row = (Object[]) objects;
			
			labels.add(String.valueOf(row[0].toString()+" - "+StringUtils.abbreviate(row[1].toString(), 20) ));
				
	        dataSetTratada.add((Integer) row[4]);
	       
	        dataSetNaoTratada.add((Integer) row[5]);
	       
		}
	    
	    barDataSetNaoTratada.setData(dataSetNaoTratada);
	    
	    barDataSetTratada.setData(dataSetTratada);
	    data.addChartDataSet(barDataSetTratada);
	    data.addChartDataSet(barDataSetNaoTratada);
	    BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setStacked(true);
        linearAxes.setOffset(true);
        cScales.addXAxesData(linearAxes);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Consistência ");
        options.setTitle(title);

        Tooltip tooltip = new Tooltip();
        tooltip.setMode("index");
        tooltip.setIntersect(false);
        options.setTooltip(tooltip);
		data.setLabels(labels);
		this.stackedBarModelConsistencia.setOptions(options);
		this.stackedBarModelConsistencia.setData(data);
		
	}

	public void onAtualizarPanel() {

		this.produtividade = null;
		this.listProdutividade = null;
		
		switch (periodo) {

		case "DIA":

			this.dataInicioPanel = new Date();
			this.dataFimPanel = new Date();
			break;

		case "MES":

			this.dataInicioPanel = DateUtil.builder(new Date()).retornarDataPrimeiroDiaMes().retornarDataComHoraInicial().getData();
			this.dataFimPanel = DateUtil.builder(new Date()).retornarDataUltimoDiaMes().retornarDataComHoraFinal().getData();
			break;

		case "SEMANA":

			this.dataInicioPanel = DateUtil.builder(new Date()).retornarDataPrimeiroDiaSemana().retornarDataComHoraInicial().getData();
			this.dataFimPanel = DateUtil.builder(new Date()).retornarDataUltimoDiaSemana().retornarDataComHoraFinal().getData();

			break;

		default:
			
			this.dataInicioPanel = DateUtil.builder(new Date()).retornarDataComHoraInicial().getData();
			this.dataFimPanel =  DateUtil.builder(new Date()).retornarDataComHoraFinal().getData();
			break;
		}
		
		criarDash();
		pesquisarQuantidadeConsistencia();

	}
	
	
	public Long retornarTotalFooter(List<?> list) {

		if (CollectionUtils.isNotEmpty(list)) {

			BigDecimal decimal = BigDecimal.ZERO;

			for (Object linhaOb : list) {

				Object[] row = (Object[]) linhaOb;

				if (row[0] != null)
					decimal = decimal.add((BigDecimal) row[0]);

			}
			
			
			return decimal.longValue();

		}

		return null;
	}

	public Integer retornarQuantidadeTotalFooter(List<?> list) {

		if (CollectionUtils.isNotEmpty(list)) {

			BigInteger total = BigInteger.ZERO;

			for (Object linhaOb : list) {

				Object[] row = (Object[]) linhaOb;

				if (row[1] != null)
					total = total.add((BigInteger) row[1]);

			}
			
			
			return total.intValue();
		}

		return null;
	}
	public Date getDataInicioPanel() {
		return dataInicioPanel;
	}


	public void setDataInicioPanel(Date dataInicioPanel) {
		this.dataInicioPanel = dataInicioPanel;
	}


	public Date getDataFimPanel() {
		return dataFimPanel;
	}


	public void setDataFimPanel(Date dataFimPanel) {
		this.dataFimPanel = dataFimPanel;
	}


	public String getPeriodo() {
		return periodo;
	}


	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}


	public DonutChartModel getDonutModelStatusAtendimento() {
		return donutModelStatusAtendimento;
	}


	public void setDonutModelStatusAtendimento(DonutChartModel donutModelStatusAtendimento) {
		this.donutModelStatusAtendimento = donutModelStatusAtendimento;
	}


	public DonutChartModel getDonutModelMotivo() {
		return donutModelMotivo;
	}


	public void setDonutModelMotivo(DonutChartModel donutModelMotivo) {
		this.donutModelMotivo = donutModelMotivo;
	}


	public DonutChartModel getDonutSubmodelMotivo() {
		return donutSubmodelMotivo;
	}


	public void setDonutSubmodelMotivo(DonutChartModel donutSubmodelMotivo) {
		this.donutSubmodelMotivo = donutSubmodelMotivo;
	}


	public List<?> getListStatusPainel() {
		return listStatusPainel;
	}


	public void setListStatusPainel(List<?> listStatusPainel) {
		this.listStatusPainel = listStatusPainel;
	}


	public List<?> getListMotioPainel() {
		return listMotioPainel;
	}


	public void setListMotioPainel(List<?> listMotioPainel) {
		this.listMotioPainel = listMotioPainel;
	}


	public List<?> getListSubmotioPainel() {
		return listSubmotioPainel;
	}


	public void setListSubmotioPainel(List<?> listSubmotioPainel) {
		this.listSubmotioPainel = listSubmotioPainel;
	}


	public List<?> getListQuantidadeConsistencia() {
		return listQuantidadeConsistencia;
	}


	public void setListQuantidadeConsistencia(List<?> listQuantidadeConsistencia) {
		this.listQuantidadeConsistencia = listQuantidadeConsistencia;
	}


	public BarChartModel getStackedBarModelConsistencia() {
		return stackedBarModelConsistencia;
	}


	public void setStackedBarModelConsistencia(BarChartModel stackedBarModelConsistencia) {
		this.stackedBarModelConsistencia = stackedBarModelConsistencia;
	}


	public Long getTotalAtendimentos() {
		return totalAtendimentos;
	}


	public void setTotalAtendimentos(Long totalAtendimentos) {
		this.totalAtendimentos = totalAtendimentos;
	}


	public List<Equipe> getListEquipes() {
		return listEquipes;
	}


	public void setListEquipes(List<Equipe> listEquipes) {
		this.listEquipes = listEquipes;
	}


	public List<?> getListProdutividade() {
		return listProdutividade;
	}


	public void setListProdutividade(List<?> listProdutividade) {
		this.listProdutividade = listProdutividade;
	}


	public Double getTotalAtendimento() {
		return totalAtendimento;
	}


	public void setTotalAtendimento(Double totalAtendimento) {
		this.totalAtendimento = totalAtendimento;
	}


	public Double getTotalPropostas() {
		return totalPropostas;
	}


	public void setTotalPropostas(Double totalPropostas) {
		this.totalPropostas = totalPropostas;
	}


	public Double getTotalPago() {
		return totalPago;
	}


	public void setTotalPago(Double totalPago) {
		this.totalPago = totalPago;
	}


	public Double getTotalVendido() {
		return totalVendido;
	}


	public void setTotalVendido(Double totalVendido) {
		this.totalVendido = totalVendido;
	}


	public Double getTotalContratos() {
		return totalContratos;
	}


	public void setTotalContratos(Double totalContratos) {
		this.totalContratos = totalContratos;
	}


	public Double getTotalCpf() {
		return totalCpf;
	}


	public void setTotalCpf(Double totalCpf) {
		this.totalCpf = totalCpf;
	}


	public Object[] getProdutividade() {
		return produtividade;
	}


	public void setProdutividade(Object[] produtividade) {
		this.produtividade = produtividade;
	}


}
