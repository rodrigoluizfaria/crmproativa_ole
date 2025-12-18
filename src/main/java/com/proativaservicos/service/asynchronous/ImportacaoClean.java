package com.proativaservicos.service.asynchronous;

import com.proativaservicos.model.Importacao;
import com.proativaservicos.service.ImportacaoService;
import com.proativaservicos.util.constantes.StatusImportacaoEnum;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;
import org.apache.commons.collections4.CollectionUtils;
import org.omnifaces.util.Faces;

import java.util.List;

@Startup
@Singleton
public class ImportacaoClean {

	@Inject
	private ImportacaoService serviceImportacao;


	public void verificarImportacao() {

		try {

			System.out.println("procutando importacao...");

			if (Faces.getApplicationMap() != null && Faces.getApplicationAttribute("importacao")!=null) {

				List<Importacao> listaImportacao = Faces.getApplicationAttribute("importacao");

				System.out.println("Total de importacao: " + listaImportacao.size());

				if (CollectionUtils.isNotEmpty(listaImportacao)) {

					for (Importacao importacao : listaImportacao) {

						Importacao importacaoPesquisa = this.serviceImportacao.pesquisar(Importacao.class,
								importacao.getId());

						if (importacaoPesquisa != null
								&& !importacaoPesquisa.getStatusImportacao().equals(StatusImportacaoEnum.IMPORTADA)) {

							System.out.println("Alterando importacao: " + importacaoPesquisa.getNomeArquivo());

							importacaoPesquisa.setDataFim(importacao.getDataFim());
							importacaoPesquisa.setStatusImportacao(importacao.getStatusImportacao());
							importacaoPesquisa.setLog(importacao.getLog());
							importacaoPesquisa.setQtidadeImportado(importacao.getQtidadeImportado());

							this.serviceImportacao.alterar(importacaoPesquisa);

						}

					}

				}

			}
			
		} catch (NullPointerException e) {
			//e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}
