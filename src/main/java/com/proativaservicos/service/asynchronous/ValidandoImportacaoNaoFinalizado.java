package com.proativaservicos.service.asynchronous;

import com.proativaservicos.model.Importacao;
import com.proativaservicos.service.ImportacaoService;
import com.proativaservicos.util.constantes.StatusImportacaoEnum;
import jakarta.ejb.*;
import jakarta.inject.Inject;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@TransactionManagement(TransactionManagementType.BEAN)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Startup
@Singleton
public class ValidandoImportacaoNaoFinalizado {

    @Inject
    private ImportacaoService serviceImpostacao;

    @Schedule(hour = "*", minute = "*/10", persistent = false)
    public void validandoImportacao() {

        File file = new File(System.getProperty("user.home") + File.separator + "proativa" + File.separator + "importacao");

        if (file.exists() && file.isFile()) {

            try {

                List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.ISO_8859_1);

                for (String line : lines) {

                    String[] arrayLines = line.split(";");

                    Importacao importacao = serviceImpostacao.pesquisar(Importacao.class, Long.valueOf(arrayLines[0]));

                    if (importacao != null && importacao.getStatusImportacao() != StatusImportacaoEnum.IMPORTADA) {

                        importacao.setLog(arrayLines[3]);
                        importacao.setStatusImportacao(StatusImportacaoEnum.IMPORTADA);
                        importacao.setQtidadeImportado(StringUtils.isNumeric(arrayLines[2]) ? Integer.valueOf(arrayLines[2]) : null);
                        importacao.setDataFim(new Date());
                        this.serviceImpostacao.alterar(importacao);

                    }

                }

                file.delete();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

}
