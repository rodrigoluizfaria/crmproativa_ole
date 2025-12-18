package com.proativaservicos.model;

import java.io.Serializable;

/**
 * Classe de Usuario
 * @author rodrigo
 */

import java.util.Date;
import java.util.List;

import com.proativaservicos.model.argus.PausaItem;
import com.proativaservicos.model.trescplus.Intervalo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import com.proativaservicos.model.pwd.RespostaLoginPowerDialer;
import com.proativaservicos.util.constantes.PerfilUsuarioEnum;
import com.proativaservicos.util.constantes.SexoEnum;
import com.proativaservicos.util.constantes.SimNaoEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;

@Entity
@Table(name = "usuario")
public class Usuario extends GenericControle implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = -3459844804397177474L;

	public Usuario() {
		this.ip = "";
	}

	public Usuario(Long id, String nome, String login) {
		setId(id);
		this.nome = nome;
		this.login = login;
		this.ip = "";
	}

	public Usuario(Long id, String nome) {

		setId(id);
		this.nome = nome;
		this.ip = "";

	}

	public Usuario(Long idUsuario) {
		// TODO Auto-generated constructor stub

		setId(idUsuario);
		this.ip = "";
	}

	@Column(name = "nome", nullable = false, length = 150)
	private String nome;

	@Column(name = "login", nullable = false, length = 80)
	private String login;

	@Column(name = "senha", nullable = false, length = 100)
	private String senha;

	@Column(name = "senha_inicial", nullable = true, length = 3)
	@Enumerated(EnumType.STRING)
	private SimNaoEnum senhaInicial;

	@Column(name = "perfil", nullable = false, length = 30)
	@Enumerated(EnumType.STRING)
	private PerfilUsuarioEnum perfil;

	@Column(name = "sexo", nullable = false, length = 20)
	@Enumerated(EnumType.STRING)
	private SexoEnum sexo;

	@Column(name = "cpf", nullable = false, length = 14)
	private String cpf;

	@Column(name = "perfil_master", nullable = false)
	private boolean perfilMaster;

	@Column(name = "telefone", nullable = true, length = 20)
	private String telefone;

	@Column(name = "email", nullable = true, length = 150)
	private String email;

	@Column(name = "usuario_consig", nullable = true, length = 80)
	private String usuarioConsig;

	@Column(name = "senha_consig", nullable = true, length = 80)
	private String senhaConsig;

	@Column(name = "ativo", nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoAcessoEnum ativo;

	@Column(name = "bloqueado", nullable = false)
	private boolean bloqueado;

	@Column(name = "data_ultimo_acesso", nullable = true)
	private Date dataUltimoAcesso;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "empresa", foreignKey = @ForeignKey(name = "usr_empresa_fk"))
	private Empresa empresa;

	@JoinColumn(name = "ponto_atendimento", foreignKey = @ForeignKey(name = "usr_ponto_atn_fk"))
	@OneToOne(fetch = FetchType.LAZY)
	private PontoAtendimento pontoAtendimento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "equipe", foreignKey = @ForeignKey(name = "usr_equipe_fk"))
	private Equipe equipe;

	@JoinColumn(name = "supervisor", foreignKey = @ForeignKey(name = "usuario_supervisor_fk"))
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario supervisor;
	
	@JoinColumn(name = "coordenador", foreignKey = @ForeignKey(name = "usuario_coordenador_fk"))
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario coordenador;

	@Column(name = "parametro_tempo_atendimento")
	private Boolean parametroTempoAtendimento;
	@Column(name = "parametro_usuario_susurro")
	private Boolean parametroUsuarioSusurro;

	@Transient
	private Long campanha;

	@Transient
	private boolean pausaContactCenter;

	@Column(name = "meta_mensal")
	private Double metaMensal;
	@Column(name = "meta_semanal")
	private Double metaSemanal;
	@Column(name = "meta_diaria")
	private Double metaDiaria;
	@Column(name = "meta_mensal_concluida")
	private Double metaMensalConcluida;
	@Column(name = "meta_semanal_concluida")
	private Double metaSemanalConcluida;
	@Column(name = "meta_diaria_concluida")
	private Double metaDiariaConcluida;
	@Column(name = "meta_mensal_quantidade")
	private Integer metaMensalQuantidade;
	@Column(name = "meta_semanal_quantidade")
	private Integer metaSemanalQuantidade;
	@Column(name = "meta_diaria_quantidade")
	private Integer metaDiariaQuantidade;
	@Column(name = "meta_mensal_concluida_quantidade")
	private Integer metaMensalConcluidaQuantidade;
	@Column(name = "meta_semanal_concluida_quantidade")
	private Integer metaSemanalConcluidaQuantidade;
	@Column(name = "meta_diaria_concluida_quantidade")
	private Integer metaDiariaConcluidaQuantidade;
	@Column(name = "permissao_exportar")
	private Boolean permissaoExportar;
	@Column(name = "meta_concluido")
	private Double metaConcluido;

	@Transient
	private List<String> listFilas;

	@Transient
	private Integer pausaVonix;

	private String foto;
	
	@Transient
	private String ip;
	
	@Transient
	private int quantidadePendentes;

	@Transient
	private int quantidadeAgendamentos;
	
	@Transient
	private RespostaLoginPowerDialer respostaLoginPowerDialer;
	
	@Transient
	private List<String> filasCadastradasPoweDialer;

	@Transient
	private List<Intervalo> intervalos3c;

	@Transient
	private List<PausaItem> listaPausaArgus;

	@JoinColumn(name = "expediente", foreignKey = @ForeignKey(name = "usuario_expediente_fk"))
	@ManyToOne(fetch = FetchType.LAZY)
	private Expediente expediente;

	@Transient
	private String ipServer;

	@Transient
	private Integer idCampanha3c;

	public Long getCampanha() {
		return campanha;
	}

	public void setCampanha(Long campanha) {
		this.campanha = campanha;
	}

	public boolean isPausaContactCenter() {
		return pausaContactCenter;
	}

	public void setPausaContactCenter(boolean pausaContactCenter) {
		this.pausaContactCenter = pausaContactCenter;
	}

	public Double getMetaMensal() {
		return metaMensal;
	}
	
	public Usuario getCoordenador() {
		return coordenador;
	}
	
	public void setCoordenador(Usuario coordenador) {
		this.coordenador = coordenador;
	}

	public void setMetaMensal(Double metaMensal) {
		this.metaMensal = metaMensal;
	}

	public Double getMetaSemanal() {
		return metaSemanal;
	}

	
	
	public void setMetaSemanal(Double metaSemanal) {
		this.metaSemanal = metaSemanal;
	}

	public Double getMetaDiaria() {
		return metaDiaria;
	}

	public void setMetaDiaria(Double metaDiaria) {
		this.metaDiaria = metaDiaria;
	}

	public Double getMetaMensalConcluida() {
		return metaMensalConcluida;
	}

	public void setMetaMensalConcluida(Double metaMensalConcluida) {
		this.metaMensalConcluida = metaMensalConcluida;
	}

	public Double getMetaSemanalConcluida() {
		return metaSemanalConcluida;
	}

	public void setMetaSemanalConcluida(Double metaSemanalConcluida) {
		this.metaSemanalConcluida = metaSemanalConcluida;
	}

	public Double getMetaDiariaConcluida() {
		return metaDiariaConcluida;
	}

	public void setMetaDiariaConcluida(Double metaDiariaConcluida) {
		this.metaDiariaConcluida = metaDiariaConcluida;
	}

	public Integer getMetaMensalQuantidade() {
		return metaMensalQuantidade;
	}

	public void setMetaMensalQuantidade(Integer metaMensalQuantidade) {
		this.metaMensalQuantidade = metaMensalQuantidade;
	}

	public Integer getMetaSemanalQuantidade() {
		return metaSemanalQuantidade;
	}

	public void setMetaSemanalQuantidade(Integer metaSemanalQuantidade) {
		this.metaSemanalQuantidade = metaSemanalQuantidade;
	}

	public Integer getMetaDiariaQuantidade() {
		return metaDiariaQuantidade;
	}

	public void setMetaDiariaQuantidade(Integer metaDiariaQuantidade) {
		this.metaDiariaQuantidade = metaDiariaQuantidade;
	}

	public Integer getMetaMensalConcluidaQuantidade() {
		return metaMensalConcluidaQuantidade;
	}

	public void setMetaMensalConcluidaQuantidade(Integer metaMensalConcluidaQuantidade) {
		this.metaMensalConcluidaQuantidade = metaMensalConcluidaQuantidade;
	}

	public Integer getMetaSemanalConcluidaQuantidade() {
		return metaSemanalConcluidaQuantidade;
	}

	public void setMetaSemanalConcluidaQuantidade(Integer metaSemanalConcluidaQuantidade) {
		this.metaSemanalConcluidaQuantidade = metaSemanalConcluidaQuantidade;
	}

	public Integer getMetaDiariaConcluidaQuantidade() {
		return metaDiariaConcluidaQuantidade;
	}

	public void setMetaDiariaConcluidaQuantidade(Integer metaDiariaConcluidaQuantidade) {
		this.metaDiariaConcluidaQuantidade = metaDiariaConcluidaQuantidade;
	}

	public Boolean getPermissaoExportar() {
		return permissaoExportar;
	}

	public void setPermissaoExportar(Boolean permissaoExportar) {
		this.permissaoExportar = permissaoExportar;
	}

	public Double getMetaConcluido() {
		return metaConcluido;
	}

	public void setMetaConcluido(Double metaConcluido) {
		this.metaConcluido = metaConcluido;
	}

	public List<String> getListFilas() {
		return listFilas;
	}

	public void setListFilas(List<String> listFilas) {
		this.listFilas = listFilas;
	}

	public Integer getPausaVonix() {
		return pausaVonix;
	}

	public void setPausaVonix(Integer pausaVonix) {
		this.pausaVonix = pausaVonix;
	}

	public String getNome() {
		return nome;
	}

	public boolean isPerfilMaster() {
		return perfilMaster;
	}

	public void setPerfilMaster(boolean perfilMaster) {
		this.perfilMaster = perfilMaster;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public PerfilUsuarioEnum getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilUsuarioEnum perfil) {
		this.perfil = perfil;
	}

	public SimNaoEnum getSenhaInicial() {
		return senhaInicial;
	}

	public void setSenhaInicial(SimNaoEnum senhaInicial) {
		this.senhaInicial = senhaInicial;
	}

	public SexoEnum getSexo() {
		return sexo;
	}

	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getUsuarioConsig() {
		return usuarioConsig;
	}

	public void setUsuarioConsig(String usuarioConsig) {
		this.usuarioConsig = usuarioConsig;
	}

	public String getSenhaConsig() {
		return senhaConsig;
	}

	public void setSenhaConsig(String senhaConsig) {
		this.senhaConsig = senhaConsig;
	}

	public TipoAcessoEnum getAtivo() {
		return ativo;
	}

	public void setAtivo(TipoAcessoEnum ativo) {
		this.ativo = ativo;
	}

	public boolean isBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	public Date getDataUltimoAcesso() {
		return dataUltimoAcesso;
	}

	public void setDataUltimoAcesso(Date dataUltimoAcesso) {
		this.dataUltimoAcesso = dataUltimoAcesso;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public PontoAtendimento getPontoAtendimento() {
		return pontoAtendimento;
	}

	public void setPontoAtendimento(PontoAtendimento pontoAtendimento) {
		this.pontoAtendimento = pontoAtendimento;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Usuario getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Usuario supervisor) {
		this.supervisor = supervisor;
	}

	public Boolean getParametroTempoAtendimento() {
		return parametroTempoAtendimento;
	}

	public void setParametroTempoAtendimento(Boolean parametroTempoAtendimento) {
		this.parametroTempoAtendimento = parametroTempoAtendimento;
	}

	public Boolean getParametroUsuarioSusurro() {
		return parametroUsuarioSusurro;
	}

	public void setParametroUsuarioSusurro(Boolean parametroUsuarioSusurro) {
		this.parametroUsuarioSusurro = parametroUsuarioSusurro;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Expediente getExpediente() {
		return expediente;
	}

	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}

	@PrePersist
	@PreUpdate
	public void configuraDataCriacaoAlteracao() {

		setDataAlteracao(new Date());

		if (getDataCadastro() == null) {

			setDataCadastro(new Date());
		}
	}
	
	public int getQuantidadePendentes() {
		return quantidadePendentes;
	}
	
	public void setQuantidadePendentes(int quantidadePendentes) {
		this.quantidadePendentes = quantidadePendentes;
	}

	public int getQuantidadeAgendamentos() {
		return quantidadeAgendamentos;
	}
	
	public void setQuantidadeAgendamentos(int quantidadeAgendamentos) {
		this.quantidadeAgendamentos = quantidadeAgendamentos;
	}
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public RespostaLoginPowerDialer getRespostaLoginPowerDialer() {
		return respostaLoginPowerDialer;
	}
	public void setRespostaLoginPowerDialer(RespostaLoginPowerDialer respostaLoginPowerDialer) {
		this.respostaLoginPowerDialer = respostaLoginPowerDialer;
	}

	public List<String> getFilasCadastradasPoweDialer() {
		return filasCadastradasPoweDialer;
	}
	
	public void setFilasCadastradasPoweDialer(List<String> filasCadastradasPoweDialer) {
		this.filasCadastradasPoweDialer = filasCadastradasPoweDialer;
	}
	public String getIpServer() {
		return ipServer;
	}
	
	public void setIpServer(String ipServer) {
		this.ipServer = ipServer;
		
	}

	public void setIdCampanha3c(Integer idCampanha3c) {
		this.idCampanha3c = idCampanha3c;
	}

	public Integer getIdCampanha3c() {
		return idCampanha3c;
	}

	public List<Intervalo> getIntervalos3c() {
		return intervalos3c;
	}

	public void setIntervalos3c(List<Intervalo> intervalos3c) {
		this.intervalos3c = intervalos3c;
	}

	public List<PausaItem> getListaPausaArgus() {
		return listaPausaArgus;
	}

	public void setListaPausaArgus(List<PausaItem> listaPausaArgus) {
		this.listaPausaArgus = listaPausaArgus;
	}
}
