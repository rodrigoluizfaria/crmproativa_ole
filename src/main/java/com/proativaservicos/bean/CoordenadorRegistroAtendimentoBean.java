package com.proativaservicos.bean;

import com.proativaservicos.model.Campanha;
import com.proativaservicos.model.StatusCampanha;
import com.proativaservicos.service.CampanhaService;
import com.proativaservicos.service.LogAtendimentoService;
import com.proativaservicos.service.StatusCampanhaService;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class CoordenadorRegistroAtendimentoBean extends GenericBean {

	private static final long serialVersionUID = 1L;

	@Inject
	private CampanhaService campanhaService;
	
	@Inject
	private StatusCampanhaService statusCampanhaService;
	@Inject
	private LogAtendimentoService serviceLogAtendimento;

	private Long idCampanha;
	private Long Cpf;
	private Long statusCampanha;

	private String strCpf;

	private Date dataInicio;
	private Date dataFim;

	private List<Campanha> listCampanhas;
	private List<StatusCampanha> listStatusCampanhas;

	private List<Object[]> listResults;
	
	
	 private StreamedContent file;

	@PostConstruct
	public void init() {

		inicializarEmpresa();
		inicializarVariaveis();
	}

	private void inicializarVariaveis() {

		this.listCampanhas = this.campanhaService.pesquisarCampanhasPorEmpresa(retornarEmpresaUsuarioSessao().getId());
		
		this.listStatusCampanhas = this.statusCampanhaService.pesquisarStatusCampanhaPorEmpresa(retornarEmpresaUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);

	}

	public void pesquisar() {

		this.listResults = this.serviceLogAtendimento.pesquisarLogAtendimentos(getEmpresa().getId(), this.idCampanha,this.strCpf.replaceAll("[^0-9]", ""), this.dataInicio, this.dataFim);

	}

	public void changeSelectCampanha() {

		if (getEmpresa() != null) {

			this.listCampanhas = this.campanhaService.pesquisarCampanhasPorEmpresa(getEmpresa().getId(),
					this.statusCampanha);
		}

	}
	
	
	public void baixarArquivoXml(String texto) {
		
		this.file = DefaultStreamedContent.builder().name("envio_"+strCpf.replaceAll("[^0-9]", "")+".xml").contentType("text/xml").stream(()->new ByteArrayInputStream(texto.getBytes())).build();
		
	}
	
	public void baixarArquivoRetornoXml(String texto) {
		
		this.file = DefaultStreamedContent.builder().name("retorno_"+strCpf.replaceAll("[^0-9]", "")+".xml").contentType("text/xml").stream(()->new ByteArrayInputStream(texto.getBytes())).build();
		
	}
	
	
	public void baixarArquivoTxt(String texto) {
		
		this.file = DefaultStreamedContent.builder().name("retorno_"+strCpf.replaceAll("[^0-9]", "")+".txt").contentType("text/plain").stream(()->new ByteArrayInputStream(texto.getBytes())).build();
	}

	public void trocarEmpresa() {
		this.listCampanhas = this.campanhaService.pesquisarCampanhasPorEmpresa(getEmpresa().getId());
		
	}
	
	public Long getIdCampanha() {
		return idCampanha;
	}

	public void setIdCampanha(Long idCampanha) {
		this.idCampanha = idCampanha;
	}

	public Long getCpf() {
		return Cpf;
	}

	public void setCpf(Long cpf) {
		Cpf = cpf;
	}

	public Long getStatusCampanha() {
		return statusCampanha;
	}

	public void setStatusCampanha(Long statusCampanha) {
		this.statusCampanha = statusCampanha;
	}

	public String getStrCpf() {
		return strCpf;
	}

	public void setStrCpf(String strCpf) {
		this.strCpf = strCpf;
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

	public List<Campanha> getListCampanhas() {
		return listCampanhas;
	}

	public void setListCampanhas(List<Campanha> listCampanhas) {
		this.listCampanhas = listCampanhas;
	}

	public List<StatusCampanha> getListStatusCampanhas() {
		return listStatusCampanhas;
	}

	public void setListStatusCampanhas(List<StatusCampanha> listStatusCampanhas) {
		this.listStatusCampanhas = listStatusCampanhas;
	}

	public List<Object[]> getListResults() {
		return listResults;
	}

	public void setListResults(List<Object[]> listResults) {
		this.listResults = listResults;
	}

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}
	
	

}
