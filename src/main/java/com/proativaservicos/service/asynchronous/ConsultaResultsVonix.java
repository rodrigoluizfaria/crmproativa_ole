package com.proativaservicos.service.asynchronous;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import jakarta.annotation.Resource;
import jakarta.ejb.EJB;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.transaction.UserTransaction;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.proativaservicos.dao.integra.VonixClient;
import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.Atendimento;
import com.proativaservicos.model.ChamadasAtendimento;
import com.proativaservicos.model.Empresa;
import com.proativaservicos.model.IntegracaoWs;
import com.proativaservicos.model.StatusAtendimento;
import com.proativaservicos.model.StatusTelefone;
import com.proativaservicos.model.Telefone;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.model.integracao.Contact;
import com.proativaservicos.model.integracao.Number;
import com.proativaservicos.service.AtendimentoService;
import com.proativaservicos.service.ChamadasAtendimentoService;
import com.proativaservicos.service.EmpresaService;
import com.proativaservicos.service.HistoricoAtendimentoService;
import com.proativaservicos.service.IntegracaoService;
import com.proativaservicos.service.ServiceAbstract;
import com.proativaservicos.service.StatusAtendimentoService;
import com.proativaservicos.service.StatusTelefoneService;
import com.proativaservicos.service.TelefoneService;
import com.proativaservicos.service.UsuarioService;
import com.proativaservicos.util.VerificarLinkUtil;
import com.proativaservicos.util.constantes.AcaoStatusAtendimentoEnum;
import com.proativaservicos.util.constantes.AcaoStatusTelefoneEnum;
import com.proativaservicos.util.constantes.StatusTelefoneVonixEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import com.proativaservicos.util.constantes.TipoIntegracaoEnum;

@TransactionManagement(TransactionManagementType.BEAN)
@Startup
@Singleton
public class ConsultaResultsVonix implements Serializable {

	private static final long serialVersionUID = -7448282026654577609L;

	@EJB
	private TelefoneService serviceTelefone;

	@EJB
	private EmpresaService serviceEmpresa;

	@EJB
	private IntegracaoService integracaoService;

	@EJB
	private StatusTelefoneService serviceStatusTelefone;

	@EJB
	private UsuarioService serviceUsuario;

	@EJB
	private ChamadasAtendimentoService serviceChamadasAtendimentos;

	private IntegracaoWs integracaoVonix;

	@EJB
	private StatusAtendimentoService serviceStatusAtendimento;

	@EJB
	private AtendimentoService serviceAtendimento;

	@EJB
	private HistoricoAtendimentoService serviceHistorico;

	@Resource
	private UserTransaction transaction;

	@Schedule(hour = "*", minute = "*/30", persistent = false)
	public void getResults() {

		this.integracaoVonix = this.integracaoService.pesquisarIntegracoesVonixResults(TipoIntegracaoEnum.VONIX,TipoAcessoEnum.ATIVO);

		if (this.integracaoVonix != null) {

			try {

				verificarSeServicoEstaDisponivel(this.integracaoVonix);

				VonixClient vonix = new VonixClient(this.integracaoVonix.getUrl(), this.integracaoVonix.getUsr(),	this.integracaoVonix.getPsw());

				System.out.println("Iniciando busca consulta results vonix.");

				List<Contact> list = vonix.retornarContacts(180000);

				if (list != null && !list.isEmpty()) {

					System.out.println("Iniciando consulta results vonix. total contatos: " + list.size());

					this.transaction.begin();

					Empresa empresa = this.serviceEmpresa.pesquisarMatriz(integracaoVonix.getEmpresa().getId());
					Usuario usuario = this.serviceUsuario.pesquisarUsuario(Long.valueOf(1));

					List<StatusTelefone> listStatusTel = new ArrayList<>();

					StatusAtendimento statusAtendimentoContatoDiscador = this.serviceStatusAtendimento.pesquisarStatusAtendimentoPorDescricao("TRABALHADO DISCADOR");

					if (statusAtendimentoContatoDiscador == null) {

						statusAtendimentoContatoDiscador = new StatusAtendimento();
						statusAtendimentoContatoDiscador.setUsuarioCadastro(usuario);
						statusAtendimentoContatoDiscador.setUsuarioAlteracao(usuario);
						statusAtendimentoContatoDiscador.setAcao(AcaoStatusAtendimentoEnum.FIM_FILA);
						statusAtendimentoContatoDiscador.setAtivo(TipoAcessoEnum.ATIVO);
						statusAtendimentoContatoDiscador.setEmpresa(empresa);
						statusAtendimentoContatoDiscador.setDescricao("TRABALHADO DISCADOR");
						statusAtendimentoContatoDiscador.setDataCadastro(new Date());
						statusAtendimentoContatoDiscador.setDataCadastro(new Date());
						this.serviceStatusAtendimento.inserir(statusAtendimentoContatoDiscador);
						this.transaction.commit();
						this.transaction.begin();
					}

					int i = 0;

					for (Contact contact : list) {

						Date dataAlteracao = new Date();
						Atendimento atendimento = this.serviceAtendimento.pesquisarAtendimentoPorIdSemDetalhar(contact.getId());

						if (atendimento != null && CollectionUtils.isNotEmpty(contact.getListNumber())) {

							for (Number number : contact.getListNumber()) {

								Telefone telefone = this.serviceTelefone.pesquisarTelefone(contact.getId(),	number.getId());

								if (telefone != null && telefone.getStatusTelefone() == null) {

									StatusTelefoneVonixEnum statusVonix = StatusTelefoneVonixEnum.getStatusTelefone(Integer.parseInt(number.getCodRetorno().toString()));

									if (statusVonix == null) {
										System.err.println("Status Vonix não cadastrado: " + number.getCodRetorno());
										continue;
										//throw new ProativaException("Status Vonix não cadastrado: " + number.getCodRetorno());
									}
									Optional<StatusTelefone> statusOpcao = listStatusTel.stream()
											.filter(s -> s.getDescricao().equalsIgnoreCase(statusVonix.getDescricao()))
											.findFirst();

									StatusTelefone status = null;

									if (statusOpcao.isEmpty()) {

										status = this.serviceStatusTelefone.pesquisarStatusTelefone(empresa.getId(),statusVonix.getDescricao());

										if (status != null)
											listStatusTel.add(status);

									} else {

										status = statusOpcao.get();
									}

									if (status == null) {

										status = new StatusTelefone(statusVonix.getDescricao());
										status.setParametro(AcaoStatusTelefoneEnum.CONTATO_CLIENTE);
										status.setAtivo(TipoAcessoEnum.ATIVO);
										status.setEmpresa(empresa);
										status.setDataCadastro(new Date(System.currentTimeMillis()));
										status.setDataAlteracao(new Date(System.currentTimeMillis()));
										status.setUsuarioCadastro(usuario);
										status.setUsuarioAlteracao(usuario);
										this.serviceStatusTelefone.inserir((StatusTelefone) status);
										this.transaction.commit();
										this.transaction.begin();
									}

									telefone.setStatusTelefone(status);

									boolean sucesso = (number.getDataAtendimento() != null);

									telefone.setQuantidadeDiscado((telefone.getQuantidadeDiscado() == null ? Integer.valueOf(1)
											: Integer.valueOf(telefone.getQuantidadeDiscado() + 1)));

									if (sucesso)
										telefone.setQuantidadeAtendido((telefone.getQuantidadeAtendido() == null ? Integer.valueOf(1): Integer.valueOf(telefone.getQuantidadeAtendido() + 1)));

									this.serviceTelefone.alterarBatch(telefone, i);

								}

								i++;

								if (number.getDataDiscagem() != null)
									dataAlteracao = number.getDataDiscagem();

								ChamadasAtendimento chamadasAtendimento = new ChamadasAtendimento();
								chamadasAtendimento.setAtendimento(atendimento);
								chamadasAtendimento.setTelefone(telefone);
								chamadasAtendimento.setDataDiscagem(number.getDataDiscagem());
								chamadasAtendimento.setDataAtendida(number.getDataAtendimento());
								chamadasAtendimento.setDataHangup(number.getDataHangup());
								chamadasAtendimento.setFila(contact.getCodQueue()); 
								chamadasAtendimento.setStatusTelefoneVonix(StatusTelefoneVonixEnum.getStatusTelefone(number.getCodRetorno().intValue()));
								chamadasAtendimento.setDescricao(StringUtils.endsWithIgnoreCase(number.getCallFilename(), ".WAV") ? number.getCallFilename().substring(0,number.getCallFilename().length() - 4)	: number.getCallFilename());
								chamadasAtendimento.setPabx(integracaoVonix);
								chamadasAtendimento.setDestino(number.getDestino());
								chamadasAtendimento.setDuracao(number.getDuracao());
								chamadasAtendimento.setIrvDigit((StringUtils.isNotBlank(number.getIrvDigit()) )?  number.getIrvDigit() : null);
								this.serviceChamadasAtendimentos.alterarBatch(chamadasAtendimento, i);

							}

							if (atendimento.getStatus() == null) {

								if (statusAtendimentoContatoDiscador != null	&& statusAtendimentoContatoDiscador.getId() != null) {

									String fila = StringUtils.isNotEmpty(contact.getCodQueue())	? "Trabalhado pelo discador | FILA: " + contact.getCodQueue()	: "Trabalhado pelo discador.";

									fila = fila + getStatusVonix(contact.getStatus());

									Integer quantidadeDiscado = atendimento.getQuantidadeDiscagem() != null	? (Integer.valueOf(atendimento.getQuantidadeDiscagem() + 1)): Integer.valueOf(1);

									this.serviceAtendimento.atualizarAtendimentoResultsVonix(atendimento.getId(),statusAtendimentoContatoDiscador.getId(), dataAlteracao, quantidadeDiscado);
									this.serviceHistorico.inserirHistoricoAtendimento(atendimento.getId(),	statusAtendimentoContatoDiscador.getId(), usuario.getId(), dataAlteracao,dataAlteracao, fila);
								}

							}

							if (i > 0 && i % 50 == 0) {

								this.transaction.commit();
								this.transaction.begin();

							}
						}

					}

					this.transaction.commit();

				}else{

					System.out.println("Nenhum resultado retornado.");
				}

				System.out.println("Fim consulta results vonix.");

			} catch (ProativaException e) {

				e.printStackTrace();
				System.err.println(e.getMessage());

			} catch (Exception e) {

				e.printStackTrace();
				System.err.println("Erro.. buscar resultados Vonix. " + e.getMessage());
			}

		}


	}



	private String getStatusVonix(String status) {

		if (StringUtils.isNotEmpty(status)) {

			switch (status) {

			case "failure":

				return " | status discador: não atendida.";

			case "success":

				return " | status discador: atendida.";

			case "error":

				return " | status discador: não discado.";

			default:
				return " | status discador: " + status;
			}
		}
		return "";
	}

	private void verificarSeServicoEstaDisponivel(IntegracaoWs servico) {

		boolean online = false;
		int i = 0;

		do {

			try {

				VerificarLinkUtil.verificarLink((servico.getUrl()));
				online = true;

			} catch (ProativaException ce) {

				System.out.println("Inoperante:" + ce.getMessage());
				online = false;
				i++;

				try {

					Thread.sleep(50000);
				} catch (InterruptedException e) {

					e.printStackTrace();

				}
			}

		} while (!online && i < 4);
	}

}
