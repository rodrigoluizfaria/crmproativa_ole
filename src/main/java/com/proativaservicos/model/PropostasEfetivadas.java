package com.proativaservicos.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.google.common.collect.ComparisonChain;

@SuppressWarnings({"serial","unchecked"})
@Entity
@Table(name = "propostas_realizadas")
public class PropostasEfetivadas extends GenericAtendimento{


	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, mappedBy = "atendimento")
	private List<Telefone> listTelefones;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, mappedBy = "atendimento")
	@NotFound(action = NotFoundAction.IGNORE)
	private List<DadosBancarios> listDadosBancarios;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, mappedBy = "atendimento")
	@NotFound(action = NotFoundAction.IGNORE)
	private List<Endereco> listEnderecos;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, mappedBy = "atendimento")
	@NotFound(action = NotFoundAction.IGNORE)
	private List<CartaoCredito> listCartoesCreditos;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, mappedBy = "atendimento")
	@NotFound(action = NotFoundAction.IGNORE)
	private List<Email> listEmails;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, mappedBy = "atendimento")
	@NotFound(action = NotFoundAction.IGNORE)
	private List<HistoricoAtendimento> listHistoricos;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, mappedBy = "atendimento")
	@NotFound(action = NotFoundAction.IGNORE)
	private List<ContratoEfetivado> listContratosEfetivados;

	public PropostasEfetivadas() {

		setPesoDiscagem(Integer.valueOf(0));

		this.listDadosBancarios = new ArrayList<>();
		this.listTelefones = new ArrayList<Telefone>();

		this.listEnderecos = new ArrayList<>();

		this.listCartoesCreditos = new ArrayList<>();

		this.listEmails = new ArrayList<>();

		this.listHistoricos = new ArrayList<>();
		this.listContratosEfetivados = new ArrayList<>();
	}

	public PropostasEfetivadas(String nome) {
		super(nome);
	}

	public PropostasEfetivadas(Long id) {
		super(id);
	}

	
	public List<Telefone> getListTelefones() {
		return listTelefones;
	}

	public void setListTelefones(List<Telefone> listTelefones) {
		this.listTelefones = listTelefones;
	}

	public List<DadosBancarios> getListDadosBancarios() {
		return listDadosBancarios;
	}

	public void setListDadosBancarios(List<DadosBancarios> listDadosBancarios) {
		this.listDadosBancarios = listDadosBancarios;
	}

	public List<Endereco> getListEnderecos() {
		return listEnderecos;
	}

	public void setListEnderecos(List<Endereco> listEnderecos) {
		this.listEnderecos = listEnderecos;
	}

	public List<CartaoCredito> getListCartoesCreditos() {
		return listCartoesCreditos;
	}

	public void setListCartoesCreditos(List<CartaoCredito> listCartoesCreditos) {
		this.listCartoesCreditos = listCartoesCreditos;
	}

	public List<Email> getListEmails() {
		return listEmails;
	}

	public void setListEmails(List<Email> listEmails) {
		this.listEmails = listEmails;
	}

	public List<HistoricoAtendimento> getListHistoricos() {
		return listHistoricos;
	}

	

	public List<ContratoEfetivado> getListContratosEfetivados() {
		return listContratosEfetivados;
	}

	public void setListContratosEfetivados(List<ContratoEfetivado> listContratosEfetivados) {
		this.listContratosEfetivados = listContratosEfetivados;
	}

	@Override
	public void setListHistoricos(List<? extends GenericHistoricoAtendimento> list) {
		// TODO Auto-generated method stub
		this.listHistoricos = (List<HistoricoAtendimento>) list;
		
	}

	@Override
	public void adicionarHistorico(GenericHistoricoAtendimento historico) {
		historico.setAtendimento(this);
		this.listHistoricos.add((HistoricoAtendimento) historico);
	}

	@Override
	public List<? extends GenericTelefone> getListaTelefones() {
		// TODO Auto-generated method stub
		return this.listTelefones;
	}


	@Override
	public List<? extends GenericTelefone> getListaTelefonesOrdenada() {
		
		List<Telefone> listaOrdenada = new ArrayList<>(this.listTelefones);
		
	    Collections.sort(listaOrdenada, new Comparator<Telefone>() {
	       
	    	public int compare(Telefone telefone1, Telefone telefone2) {
	    
	            if (telefone1.getId() != null && telefone2.getId() != null) {
	              return ComparisonChain.start()
	                .compare((telefone1.getStatusTelefone() == null || telefone1.getStatusTelefone().getId() == null) ? 10 : telefone1
	                  .getStatusTelefone().getParametro().getOrdenacao().intValue(), (telefone2
	                  
	                  .getStatusTelefone() == null || telefone2.getStatusTelefone().getId() == null) ? 10 : telefone2
	                  .getStatusTelefone().getParametro().getOrdenacao().intValue())
	                .compare(telefone1.getId(), telefone2.getId())
	                .result();
	            }
	            
	            return ComparisonChain.start()
	              .compare((telefone1.getStatusTelefone() == null || telefone1.getStatusTelefone().getId() == null) ? 10 : telefone1
	                .getStatusTelefone().getParametro().getOrdenacao().intValue(), (telefone2
	                
	                .getStatusTelefone() == null || telefone2.getStatusTelefone().getId() == null) ? 10 : telefone2
	                .getStatusTelefone().getParametro().getOrdenacao().intValue())
	              .compare(telefone1.getDdd(), telefone2.getDdd())
	              .compare(telefone1.getNumero(), telefone2.getNumero())
	              .result();
	          }
	        });


	    
	    return listaOrdenada;
		
	}

	@Override
	public void setListaTelefones(List<? extends GenericTelefone> list) {
		// TODO Auto-generated method stub
		
		this.listTelefones = (List<Telefone>) list;
		
	}

	@Override
	public boolean adicionarTelefone(GenericTelefone telefone) {
		
		if (this.listTelefones == null) {

			this.listTelefones = new ArrayList<>();
		}

		boolean naoExisteTelefone = this.listTelefones.stream()
				.noneMatch(t -> (t.getDdd().shortValue() == telefone.getDdd().shortValue()
						&& t.getNumero().equals(telefone.getNumero())));

		if (naoExisteTelefone) {
			telefone.setAtendimento(this);
			this.listTelefones.add((Telefone) telefone);
		}

		return naoExisteTelefone;
	}
	

	public boolean adicionarTelefoneSemObject(GenericTelefone telefone) {
		
		if (this.listTelefones == null) {

			this.listTelefones = new ArrayList<>();
		}

		boolean naoExisteTelefone = this.listTelefones.stream()
				.noneMatch(t -> (t.getDdd().shortValue() == telefone.getDdd().shortValue()
						&& t.getNumero().equals(telefone.getNumero())));

		if (naoExisteTelefone) {
		
			this.listTelefones.add((Telefone) telefone);
		}

		return naoExisteTelefone;
	}
	
	

	@Override
	public List<? extends GenericDadosBancarios> getListaDadosBancarios() {
		// TODO Auto-generated method stub
		return this.listDadosBancarios;
	}

	
	@Override
	public void setListaDadosBancarios(List<? extends Generic> list) {
		// TODO Auto-generated method stub
		this.listDadosBancarios = (List<DadosBancarios>) list;
	}

	@Override
	public List<? extends GenericCartaoCredito> getListaCartoesCredito() {
		// TODO Auto-generated method stub
		return this.listCartoesCreditos;
	}

	@Override
	public void setListaCartoesCredito(List<? extends GenericCartaoCredito> list) {
		// TODO Auto-generated method stub
		this.listCartoesCreditos =  (List<CartaoCredito>) list;
		
	}

	@Override
	public void adicionarDadosBancarios(GenericDadosBancarios dadosBancarios) {
		
		dadosBancarios.setAtendimento(this);
		this.listDadosBancarios.add((DadosBancarios)dadosBancarios);
		
	}
	

	@Override
	public void adicionarCartoesCredito(GenericCartaoCredito cartaoCredito) {

		 cartaoCredito.setAtendimento(this);
		    this.listCartoesCreditos.add((CartaoCredito)cartaoCredito);

	}
	@Override
	public List<? extends GenericEmail> getListaEmails() {
		// TODO Auto-generated method stub
		return this.listEmails;
	}

	@Override
	public void setListaEmails(List<? extends GenericEmail> list) {
		// TODO Auto-generated method stub
		this.listEmails = (List<Email>) list;
	}

	@Override
	public void adicionarEmail(GenericEmail email) {
		
		email.setAtendimento(this);
	    this.listEmails.add((Email)email);

	}

	@Override
	public List<? extends GenericEndereco> getListaEnderecos() {
		// TODO Auto-generated method stub
		return this.listEnderecos;
	}

	@Override
	public void setListaEnderecos(List<? extends GenericEndereco> list) {
		// TODO Auto-generated method stub
		this.listEnderecos = (List<Endereco>) list;
		
	}

	@Override
	public void adicionarEndereco(GenericEndereco endereco) {

		endereco.setAtendimento(this);
		
		this.listEnderecos.add((Endereco) endereco);

	}
	
	@Override
	public List<? extends GenericContratoEfetivado> getListaContratosEfetivados() {
		// TODO Auto-generated method stub
		return this.listContratosEfetivados;
	}

	
	@Override
	public void setListaContratosEfetivados(List<? extends GenericContratoEfetivado> list) {
		// TODO Auto-generated method stub
		this.listContratosEfetivados = (List<ContratoEfetivado>) list;
	}

	@Override
	public void adicionarContratoEfetivado(GenericContratoEfetivado contrato) {
		contrato.setAtendimento(this);
		this.listContratosEfetivados.add((ContratoEfetivado) contrato);

	}

	@Override
	public void adicionarPortabilidade(Portabilidade portabilidade) {

	}

	@Override
	public List<? extends GenericHistoricoAtendimento> getListaHistoricos() {
		// TODO Auto-generated method stub
		return this.listHistoricos;
	}

	@Override
	public List<Portabilidade> getListPortabilidades() {
		return List.of();
	}

	@Override
	public void setListPortabilidades(List<Portabilidade> listPortabilidades) {

	}


}
