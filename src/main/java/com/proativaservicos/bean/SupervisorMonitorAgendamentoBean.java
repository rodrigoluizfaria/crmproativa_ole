package com.proativaservicos.bean;

import com.proativaservicos.service.HistoricoAtendimentoService;
import com.proativaservicos.util.constantes.MessagesEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.collections4.CollectionUtils;
import org.omnifaces.util.Messages;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@Named
@ViewScoped
public class SupervisorMonitorAgendamentoBean extends GenericBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private HistoricoAtendimentoService serviceHistorico;

	private List<?> listAgendamentos;
	
	private BigInteger valorTotal;

	@PostConstruct
	public void init() {

		try {
			
			inicializarEmpresa(retornarEmpresaUsuarioSessao());
			trocarEmpresa();
	
		} catch (Exception e) {
			
			e.printStackTrace();
			Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
			
		}
	}

	public void trocarEmpresa() {
		
	pesquisar();

	}

	public void pesquisar() {
	
		try {
				this.valorTotal = null;
				
				this.listAgendamentos = this.serviceHistorico.pesquisarAgendamentosDiarios(retornarUsuarioSessao(),getEmpresa().getId());
				
				gerarTotalFooter();
			} catch (Exception e) {
				
				e.printStackTrace();
				Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
				
			}
	}

	public List<?> getListAgendamentos() {
		return listAgendamentos;
	}
	
	private void gerarTotalFooter() {
		
		this.valorTotal = BigInteger.ZERO;
		
		if(CollectionUtils.isNotEmpty(this.listAgendamentos)) {
			
			for (Object object : listAgendamentos) {
				
				Object [] obj = (Object[]) object;
				
				if(obj!=null) {
					this.valorTotal = this.valorTotal.add((BigInteger) obj[1]);
				}
			}
			
			
		}
		
	}

	public void setListAgendamentos(List<?> listAgendamentos) {
		this.listAgendamentos = listAgendamentos;
	}
	
	public BigInteger getValorTotal() {
		return valorTotal;
	}

}
