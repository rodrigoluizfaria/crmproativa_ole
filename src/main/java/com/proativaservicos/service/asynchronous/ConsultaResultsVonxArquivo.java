package com.proativaservicos.service.asynchronous;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.Asynchronous;
import jakarta.ejb.EJB;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.inject.Named;
import jakarta.transaction.UserTransaction;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
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
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.VerificarLinkUtil;
import com.proativaservicos.util.constantes.AcaoStatusAtendimentoEnum;
import com.proativaservicos.util.constantes.AcaoStatusTelefoneEnum;
import com.proativaservicos.util.constantes.StatusTelefoneVonixEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import com.proativaservicos.util.constantes.TipoIntegracaoEnum;

@TransactionManagement(TransactionManagementType.BEAN)
@Stateless
public class ConsultaResultsVonxArquivo implements Serializable {

	private static final long serialVersionUID = -7448282026654577609L;

	@EJB
	private TelefoneService serviceTelefone;

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
	
	@EJB
	private ServiceAbstract abstractService;
	
	

	@Asynchronous
	public void getFiles() {

		VonixClient vonix = new VonixClient("http://10.8.1.251:8003", "proativa", "PsYq29XzK");

		File fileServer = new File(System.getProperty("user.home") + File.separator + "proativa" + File.separator
				+ "retorno_xml" + File.separator + "results_vonix_"
				+ DateUtil.builder().formatarDataParaString("yyyyMMdd").getDataTexto());
		
		File fileServerMove = new File(System.getProperty("user.home") + File.separator + "proativa" + File.separator
				+ "retorno_xml_move" + File.separator + "results_vonix_"
				+ DateUtil.builder().formatarDataParaString("yyyyMMdd").getDataTexto());

		if (!fileServerMove.exists())
			fileServerMove.mkdirs();

		if (!fileServer.exists())
			fileServer.mkdirs();

		List<File> listFiles = retornarArquivosXml(System.getProperty("user.home") + File.separator + "proativa" + File.separator + "retorno_xml");

		int total = 0;
		System.out.println("INICIANDO.... | TOTAL ARQUIVOS: " + listFiles.size());
		int totalArquivod = listFiles.size();

		for (File file : listFiles) {

			List<Contact> contacts = vonix.retornarResultsPorXmlFile(file);

			for (Contact file2 : contacts) {

				if (CollectionUtils.isNotEmpty(file2.getListNumber()))
					total += file2.getListNumber().size();
			}

			getResultsFile(contacts);
			System.out.println("FALTA: " + (--totalArquivod));

			try {
				
				System.out.println("Movendo: " + file.getName());
				FileUtils.moveFileToDirectory(file, fileServerMove, true);

			} catch (IOException e) {

				e.printStackTrace();

			}

		}
		
		System.out.println("TOTAL NUM: " + total);

	}
	
	
	private void getResultsFile(List<Contact> ListContacts) {


		if (CollectionUtils.isNotEmpty(ListContacts)) {

			try {

					System.out.println("Iniciando ARQUIVOS results vonix.");
						
					System.out.println("Iniciando consulta results vonix. LIST: " + ListContacts.size());

					this.integracaoVonix = this.integracaoService.pesquisarIntegracoesVonixResults(TipoIntegracaoEnum.VONIX,	TipoAcessoEnum.ATIVO);
					
					//if(this.integracaoVonix==null)
					//	return;

					int i = 0;
					
					inicializarTransacao();

					for (Contact contact : ListContacts) {
					
						Atendimento atendimento = this.serviceAtendimento.pesquisarAtendimentoPorIdSemDetalhar(contact.getId());
						
						if (atendimento!=null && contact != null && CollectionUtils.isNotEmpty(contact.getListNumber())) {
							
							for (Number number : contact.getListNumber()) {

								Telefone telefone = this.serviceTelefone.pesquisarTelefone(contact.getId(),number.getId());

								i++;
															
								ChamadasAtendimento chamadasAtendimento = new ChamadasAtendimento();
								chamadasAtendimento.setAtendimento(atendimento);
								chamadasAtendimento.setTelefone(telefone);
								chamadasAtendimento.setDataDiscagem(number.getDataDiscagem());
								chamadasAtendimento.setDataAtendida(number.getDataAtendimento());
								chamadasAtendimento.setDataHangup(number.getDataHangup());
								chamadasAtendimento.setFila(contact.getCodQueue());
								chamadasAtendimento.setStatusTelefoneVonix(StatusTelefoneVonixEnum.getStatusTelefone(number.getCodRetorno().intValue()));
								chamadasAtendimento.setDescricao(   StringUtils.endsWithIgnoreCase(number.getCallFilename(), ".WAV") ?  number.getCallFilename().substring(0, number.getCallFilename().length()-4)  : number.getCallFilename()  );
								chamadasAtendimento.setPabx(integracaoVonix);
								chamadasAtendimento.setDestino(number.getDestino());
								chamadasAtendimento.setDuracao(number.getDuracao());
								
								this.serviceChamadasAtendimentos.inserirBatch(chamadasAtendimento, i);
								this.transaction.commit();
								inicializarTransacao();
								
							}

							if (i > 0 && i % 50 == 0) {

								this.transaction.commit();
								this.transaction.begin();

							}
						}

					}

			
					this.transaction.commit();

			} catch (ProativaException e) {

				e.printStackTrace();
				System.err.println(e.getMessage());

			} catch (Exception e) {

				e.printStackTrace();
				System.err.println("Erro.. buscar resultados Vonix. " + e.getMessage());
			}

		} else {

			System.out.println("Nenhum resultado,Vonix");

		}

		System.out.println("Fim consulta results vonix.");
	}

	private void inicializarTransacao() {

		try {
			if (this.transaction.getStatus() == 1) {
				this.transaction.rollback();
			}

			if (this.transaction.getStatus() != 0) {
				this.transaction.begin();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
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

	private List<File> retornarArquivosXml(String dir) {

		try {

			List<File> files = Files.walk(Paths.get(dir)).map(Path::toFile).filter(File::isFile).filter(path -> path.getName().toString().endsWith(".xml")).collect(Collectors.toList());
			return files;
		
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		return null;

	}
	
	public static void main(String[] args) {
		

	ConsultaResultsVonxArquivo c = new ConsultaResultsVonxArquivo();
	c.getFiles();
		


	}

}
