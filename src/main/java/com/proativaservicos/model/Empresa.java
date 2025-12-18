package com.proativaservicos.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import com.proativaservicos.util.constantes.ColorSchemaEnum;
import com.proativaservicos.util.constantes.ComponentColorEnum;
import com.proativaservicos.util.constantes.ComponentThemeEnum;
import com.proativaservicos.util.constantes.LayoutEnum;
import com.proativaservicos.util.constantes.MenuEstiloEnum;
import com.proativaservicos.util.constantes.MenuThemeEnum;
import com.proativaservicos.util.constantes.TemaEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;

@Entity
@Table(name = "empresa")
public class Empresa extends GenericControle {

	
	public Empresa(Long id) {
		setId(id);
	}

	public Empresa() {

		this.filiais = new ArrayList<>();
		this.listLoja = new ArrayList<>();
		this.sftps = new ArrayList<>();
		this.possuiFiliais = false;
		
	}

	public Empresa(String nome) {
		this.nome = nome;
	}

	public Empresa(Long id, String nome) {
		setId(id);
		this.nome = nome;
	}

	public Empresa(Long id, String  cnpj,String nome) {
		setId(id);
		this.nome = nome;
		this.cnpj = cnpj;
		
	}
	
	
	private static final long serialVersionUID = 1L;

	@Column(name = "nome", nullable = false, length = 150)
	private String nome;

	@Column(name = "logo", length = 150)
	private String logo;

	@Column(name = "cnpj", nullable = true, length = 15)
	private String cnpj;
	
	@Column(name = "nome_arquivo", nullable = true, length = 30)
	private String strArquivo;

	@Column(name = "cod_loja", nullable = true, length = 30)
	private String codLoja;

	@Column(name = "ativo", nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoAcessoEnum ativo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "matriz")
	private Empresa matriz;

	/* Deprecated */
	@Enumerated(EnumType.STRING)
	@Column(name = "component_theme")
	private ComponentThemeEnum componentTheme;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "menu_theme")
	private MenuThemeEnum menuTheme;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "color_schema")
	private ColorSchemaEnum colorSchema;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, orphanRemoval = true)
	@JoinColumn(name = "empresa", nullable = false, insertable = true, updatable = true)
	private List<Loja> listLoja;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, orphanRemoval = true)
	@JoinColumn(name = "empresa", nullable = false, insertable = true, updatable = true)
	private List<ConciliarSftp> sftps;

	@OneToMany(mappedBy = "matriz", fetch = FetchType.LAZY)
	private List<Empresa> filiais;
	
	@Column(name = "licencas",length = 100)
	private String licencas;
	
	@Column(name = "template",length = 30)
	private String template;
	
	@Column(name = "layout",length = 20)
	@Enumerated(EnumType.STRING)
	private LayoutEnum layout;

	/* Deprecated */
	@Column(name = "tema",length = 20)
	@Enumerated(EnumType.STRING)
	private TemaEnum tema;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "empresa")
	private Meta meta;
	
	@Transient
	private boolean possuiFiliais;

	@Transient
	private String situacao;

	@Column(name = "parametro_permitir_excluir_dados_bancarios")
	private Boolean parametroPermitirExcluirDadosBancarios;

	@Column(name = "paramentro_nao_atender_cpf_impedido_comissionar")
	private Boolean paramentroNaoAtenderCpfImpedidoComissionar;

	@Column(name = "parametro_gravacao_parcial")
	private Boolean parametroGravacaoParcial;

	@Column(name = "parametro_timeout_atendimento")
	private Integer parametroTimeoutAtendimento;

	@Column(name = "parametro_permitir_antencipar_atendimento", columnDefinition = "boolean default true")
	private Boolean parametroPermitirAntenciparAtendimento;

	@Column(name = "parametro_conciliar_audio_monitorado")
	private Boolean parametroConciliarAudioMonitorado;

	@Column(name = "parametro_proposta_atendimento")
	private Boolean parametroPropostaAtendimento;

	@Column(name = "parametro_conciliar_audio_ramal")
	private Boolean parametroConciliarAudioRamal;

	@Column(name = "parametro_status_telefone")
	private Boolean parametroStatusTelefone;

	@Column(name = "parametro_usuario_campanha")
	private Boolean parametroUsuarioCampanha;

	@Column(name = "parametro_atribuir_cliente")
	private Boolean parametroAtribuirCliente;

	@Column(name = "parametro_qtde_dias_atendimento")
	private Integer parametroQuantidadeDiasAtendimento;

	@Column(name = "parametro_dia_agendamento")
	private Integer parametroDiaAgendamento;

	@Column(name = "parametro_tabulacao_telefone")
	private Boolean parametroTabulacaoTelefone;

	@Column(name = "parametro_historico_atendimento")
	private Boolean parametroHistoricoAtendimento;
	
	@Column(name = "parametro_permitir_timeout_atendimento")
	private Boolean parametroPermitirTimeOut;
	
	@Column(name = "parametro_mostrar_timeout_atendimento")
	private Boolean parametroMostrarTimeOut;
	
	@Column(name = "parametro_adesao_campo_obrigatorio")
	private Boolean parametroAdesaoCampoObrigatorio;
	
	@Column(name = "menu_mode",length = 20)
	@Enumerated(EnumType.STRING)
	private MenuEstiloEnum menuMode;
	
	@Column(name = "components_colors",length = 20)
	@Enumerated(EnumType.STRING)
	private ComponentColorEnum componentsColors;
	
	@Column(name = "menu_escuro")
	private boolean menuEscuro;
	
	@Column(name = "exibir_nome_campanha")
	private Boolean exibirNomeCampanha;
	
	@Column(name = "session_time")
	private Integer sessionTime;
	
	
    public String getSidebarThemeClass() {
        
	 if (this.colorSchema.equals(ColorSchemaEnum.LIGHT)) {
		 
            return "layout-sidebar-" + this.menuTheme.getConstante(); 
        }
        else {
        	
            return "layout-sidebar-" + this.colorSchema.getConstante();
        } 
    }

	public boolean isPossuiFiliais() {
		return possuiFiliais;
	}

	public void setPossuiFiliais(boolean possuiFiliais) {
		this.possuiFiliais = possuiFiliais;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getSituacao() {
		return situacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public List<Empresa> getFiliais() {
		return filiais;
	}

	public void setFiliais(List<Empresa> filiais) {
		this.filiais = filiais;
	}

	public String getCnpj() {
		return cnpj;
	}

	public Meta getMeta() {
		return meta;
	}
	
	public void setMeta(Meta meta) {
		this.meta = meta;
	}
	
	public Boolean getParametroHistoricoAtendimento() {
		return parametroHistoricoAtendimento;
	}

	public void setParametroHistoricoAtendimento(Boolean parametroHistoricoAtendimento) {
		this.parametroHistoricoAtendimento = parametroHistoricoAtendimento;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public TemaEnum getTema() {
		return tema;
	}

	public void setTema(TemaEnum tema) {
		this.tema = tema;
	}
	
	public LayoutEnum getLayout() {
		return layout;
	}
	
	public void setLayout(LayoutEnum layout) {
		this.layout = layout;
	}

	public String getStrArquivo() {
		return strArquivo;
	}

	public void setStrArquivo(String strArquivo) {
		this.strArquivo = strArquivo;
	}

	public String getCodLoja() {
		return codLoja;
	}

	public void setCodLoja(String codLoja) {
		this.codLoja = codLoja;
	}

	public TipoAcessoEnum getAtivo() {
		return ativo;
	}

	public void setAtivo(TipoAcessoEnum ativo) {
		this.ativo = ativo;
	}

	public Empresa getMatriz() {
		return matriz;
	}

	public void setMatriz(Empresa matriz) {
		this.matriz = matriz;
	}

	public List<ConciliarSftp> getSftps() {
		return sftps;
	}

	public void setSftps(List<ConciliarSftp> sftps) {
		this.sftps = sftps;
	}

	public List<Loja> getListLoja() {
		return listLoja;
	}

	public void setListLoja(List<Loja> listLoja) {
		this.listLoja = listLoja;
	}

	public Boolean getParametroPermitirExcluirDadosBancarios() {
		return parametroPermitirExcluirDadosBancarios;
	}

	public void setParametroPermitirExcluirDadosBancarios(Boolean parametroPermitirExcluirDadosBancarios) {
		this.parametroPermitirExcluirDadosBancarios = parametroPermitirExcluirDadosBancarios;
	}

	public Boolean getParamentroNaoAtenderCpfImpedidoComissionar() {
		return paramentroNaoAtenderCpfImpedidoComissionar;
	}

	public void setParamentroNaoAtenderCpfImpedidoComissionar(Boolean paramentroNaoAtenderCpfImpedidoComissionar) {
		this.paramentroNaoAtenderCpfImpedidoComissionar = paramentroNaoAtenderCpfImpedidoComissionar;
	}

	public Boolean getParametroGravacaoParcial() {
		return parametroGravacaoParcial;
	}

	public void setParametroGravacaoParcial(Boolean parametroGravacaoParcial) {
		this.parametroGravacaoParcial = parametroGravacaoParcial;
	}

	public Integer getParametroTimeoutAtendimento() {
		return parametroTimeoutAtendimento;
	}

	public void setParametroTimeoutAtendimento(Integer parametroTimeoutAtendimento) {
		this.parametroTimeoutAtendimento = parametroTimeoutAtendimento;
	}

	public Boolean getParametroPermitirAntenciparAtendimento() {
		return parametroPermitirAntenciparAtendimento;
	}

	public void setParametroPermitirAntenciparAtendimento(Boolean parametroPermitirAntenciparAtendimento) {
		this.parametroPermitirAntenciparAtendimento = parametroPermitirAntenciparAtendimento;
	}

	public Boolean getParametroConciliarAudioMonitorado() {
		return parametroConciliarAudioMonitorado;
	}

	public void setParametroConciliarAudioMonitorado(Boolean parametroConciliarAudioMonitorado) {
		this.parametroConciliarAudioMonitorado = parametroConciliarAudioMonitorado;
	}

	public Boolean getParametroPropostaAtendimento() {
		return parametroPropostaAtendimento;
	}

	public void setParametroPropostaAtendimento(Boolean parametroPropostaAtendimento) {
		this.parametroPropostaAtendimento = parametroPropostaAtendimento;
	}

	public Boolean getParametroConciliarAudioRamal() {
		return parametroConciliarAudioRamal;
	}

	public void setParametroConciliarAudioRamal(Boolean parametroConciliarAudioRamal) {
		this.parametroConciliarAudioRamal = parametroConciliarAudioRamal;
	}

	public Boolean getParametroStatusTelefone() {
		return parametroStatusTelefone;
	}

	public void setParametroStatusTelefone(Boolean parametroStatusTelefone) {
		this.parametroStatusTelefone = parametroStatusTelefone;
	}

	public Boolean getParametroUsuarioCampanha() {
		return parametroUsuarioCampanha;
	}

	
	
	public void setParametroUsuarioCampanha(Boolean parametroUsuarioCampanha) {
		this.parametroUsuarioCampanha = parametroUsuarioCampanha;
	}

	public Boolean getParametroAtribuirCliente() {
		return parametroAtribuirCliente;
	}

	public void setParametroAtribuirCliente(Boolean parametroAtribuirCliente) {
		this.parametroAtribuirCliente = parametroAtribuirCliente;
	}

	public Integer getParametroQuantidadeDiasAtendimento() {
		return parametroQuantidadeDiasAtendimento;
	}

	public void setParametroQuantidadeDiasAtendimento(Integer parametroQuantidadeDiasAtendimento) {
		this.parametroQuantidadeDiasAtendimento = parametroQuantidadeDiasAtendimento;
	}

	public Integer getParametroDiaAgendamento() {
		return parametroDiaAgendamento;
	}

	public void setParametroDiaAgendamento(Integer parametroDiaAgendamento) {
		this.parametroDiaAgendamento = parametroDiaAgendamento;
	}

	public Boolean getParametroTabulacaoTelefone() {
		return parametroTabulacaoTelefone;
	}

	public void setParametroTabulacaoTelefone(Boolean parametroTabulacaoTelefone) {
		this.parametroTabulacaoTelefone = parametroTabulacaoTelefone;
	}
	
	
	public MenuEstiloEnum getMenuMode() {
		return menuMode;
	}
	
	public void setMenuMode(MenuEstiloEnum menuMode) {
		this.menuMode = menuMode;
	}
	

	public String getLicencas() {
		return licencas;
	}

	public void setLicencas(String licencas) {
		this.licencas = licencas;
	}

	public ComponentColorEnum getComponentsColors() {
		return componentsColors;
	}
	
	public void setComponentsColors(ComponentColorEnum componetsColors) {
		this.componentsColors = componetsColors;
	}
	
	
	public boolean isMenuEscuro() {
		return menuEscuro;
	}
	
	public void setMenuEscuro(boolean menuEscuro) {
		this.menuEscuro = menuEscuro;
	}
	
	public MenuThemeEnum getMenuTheme() {
		return menuTheme;
	}
	
	public void setMenuTheme(MenuThemeEnum menuTheme) {
		this.menuTheme = menuTheme;
	}
	
	public ComponentThemeEnum getComponentTheme() {
		return componentTheme;
	}
	
	public void setComponentTheme(ComponentThemeEnum componentTheme) {
		this.componentTheme = componentTheme;
	}

	public ColorSchemaEnum getColorSchema() {


		return colorSchema;
	}

	public void setColorSchema(ColorSchemaEnum colorSchema) {
		this.colorSchema = colorSchema;
	}

	public Boolean getParametroPermitirTimeOut() {
		return parametroPermitirTimeOut;
	}

	public void setParametroPermitirTimeOut(Boolean parametroPermitirTimeOut) {
		this.parametroPermitirTimeOut = parametroPermitirTimeOut;
	}

	public Boolean getParametroMostrarTimeOut() {
		return parametroMostrarTimeOut;
	}

	public void setParametroMostrarTimeOut(Boolean parametroMostrarTimeOut) {
		this.parametroMostrarTimeOut = parametroMostrarTimeOut;
	}
	
	public Boolean getExibirNomeCampanha() {
		return exibirNomeCampanha;
	}
	
	public void setExibirNomeCampanha(Boolean exibirNomeCampanha) {
		this.exibirNomeCampanha = exibirNomeCampanha;
	}
	
	public Boolean getParametroAdesaoCampoObrigatorio() {
		return parametroAdesaoCampoObrigatorio;
	}
	
	public void setParametroAdesaoCampoObrigatorio(Boolean parametroAdesaoCampoObrigatorio) {
		this.parametroAdesaoCampoObrigatorio = parametroAdesaoCampoObrigatorio;
	}
	
	public Integer getSessionTime() {
		return sessionTime;
	}
	
	public void setSessionTime(Integer sessionTime) {
		this.sessionTime = sessionTime;
	}

}
