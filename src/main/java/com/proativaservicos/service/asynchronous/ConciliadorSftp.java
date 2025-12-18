package com.proativaservicos.service.asynchronous;

import com.google.gson.Gson;
import com.jcraft.jsch.SftpException;
import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.ConciliarAudio;
import com.proativaservicos.model.ConciliarAudioAnexo;
import com.proativaservicos.model.ConciliarSftp;
import com.proativaservicos.model.LogConciliarAudio;
import com.proativaservicos.service.AtendimentoService;
import com.proativaservicos.service.ConciliarAudioService;
import com.proativaservicos.service.ConcilicarAudioAnexoService;
import com.proativaservicos.service.LogConciliarAudioService;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.SftpUtil;
import com.proativaservicos.util.Utils;
import com.proativaservicos.util.constantes.CronEnum;
import com.proativaservicos.util.constantes.StatusConciliarSftp;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import com.proativaservicos.util.constantes.TipoConciliarEnum;
import jakarta.ejb.*;
import jakarta.inject.Inject;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ConciliadorSftp {

    @Inject
    private AtendimentoService serviceAtendimento;

    @Inject
    private ConciliarAudioService serviceConciliarAudio;

    @Inject
    private ConcilicarAudioAnexoService serviceAudiosAnexos;


    @Inject
    private LogConciliarAudioService serviceLog;

    @Schedules({@Schedule(hour = "*/1", persistent = true)})
    public void conciliarSftp() {

        List<ConciliarAudio> listConcilicar = this.serviceConciliarAudio.pesquisarConciliarAudioAutomaticoCron(CronEnum.SESSENTA, TipoAcessoEnum.ATIVO);


        if (CollectionUtils.isNotEmpty(listConcilicar)) {


            String formato = DateUtil.builder().formatarDataParaString("yyyyMMdd").getDataTexto();

            for (ConciliarAudio conciliarAudio : listConcilicar) {

                LogConciliarAudio log = new LogConciliarAudio();
                log.setDataInicio(new Date());
                log.setSftp(conciliarAudio.getSftp());
                log.setEmpresa(conciliarAudio.getEmpresa());
                log.setUsuario(conciliarAudio.getUsuarioAlteracao());
                log.setLoja(conciliarAudio.getLoja());
                log.setStatus(StatusConciliarSftp.PROCESSANDO);

                List<?> listConciliar = this.serviceAtendimento.pesquisarAtendimentosConciliarAnexo(conciliarAudio.getEmpresa().getId(), conciliarAudio.getLoja(), false);


                List<ConciliarAudioAnexo> listConciliarAnexo = buscarConciliarAudios(listConciliar);

                if (CollectionUtils.isNotEmpty(listConciliarAnexo)) {

                    if (conciliarAudio.getSftp() != null) {

                        try {

                            String nomeRar = (StringUtils.isBlank(conciliarAudio.getPrefixoArquivoCompactado()) ? "" : conciliarAudio.getPrefixoArquivoCompactado() + "_") + conciliarAudio.getSftp().getUsr() + "_" + formato + ".rar";

                            log.setArquivoCompactado(nomeRar);
                            log.setDataFim(new Date());

                            enviarSftp(listConciliarAnexo, nomeRar, conciliarAudio.getSftp());

                            log.setObservacao("Enviado com sucesso!");
                            log.setTipoConciliar(TipoConciliarEnum.AUTOMATICO);
                            log.setStatus(StatusConciliarSftp.ENVIADO);
                            log.setAquivos(new Gson().toJson(listConciliarAnexo.stream().map(e -> e.getNomeArquivo()).collect(Collectors.toList())));

                            this.serviceLog.inserir(log);

                            this.serviceAtendimento.atualizarAtendimentosConciliados(retornarIdsAtendimentoConciliar(listConciliar), true);

                        } catch (Exception e) {

                            e.printStackTrace();

                            try {

                                log.setObservacao(e.getMessage());
                                log.setStatus(StatusConciliarSftp.ERRO_AO_ENVIAR);
                                log.setTipoConciliar(TipoConciliarEnum.AUTOMATICO);
                                log.setDataFim(new Date());
                                this.serviceLog.inserir(log);

                            } catch (ProativaException e1) {

                                e1.printStackTrace();
                            }
                        }
                    }

                }


            }

        }

    }


    private void enviarSftp(List<ConciliarAudioAnexo> listAnexosEnviar, String nomeArquivoRar, ConciliarSftp conciliarSftp) throws ProativaException, SftpException, InterruptedException, IOException {

        List<File> listWav = new ArrayList<File>();

        for (ConciliarAudioAnexo conciliarAudioAnexo : listAnexosEnviar)
            listWav.add(new File(conciliarAudioAnexo.getArquivoCompleto() + File.separator + conciliarAudioAnexo.getNomeArquivo()));

        String nomeRar = StringUtils.endsWithIgnoreCase(nomeArquivoRar, "rar") ? nomeArquivoRar : nomeArquivoRar + ".rar";

        File rar = Utils.compactarRar(listWav, criarDiretorios("arquivos_baixados"), nomeRar);

        if (rar == null || (rar != null && !rar.exists()))
            throw new ProativaException("Não foi possivel compactar os arquivos.");

        SftpUtil sftp = criarSftp(conciliarSftp);

        sftp.conectar();

        if (sftp.verificarConexao()) {

            sftp.enviarSftp(rar, conciliarSftp.getDiretorio());

            sftp.desconectar();


        } else {

            throw new ProativaException("Conexão indisponível.");
        }


    }

    private File criarDiretorios(String strDiretorio) throws IOException {

        File diretorio = new File(System.getProperty("user.home") + File.separator + "proativa" + File.separator + strDiretorio);

        if (!diretorio.exists()) {

            Files.createDirectories(diretorio.toPath(), (FileAttribute<?>[]) new FileAttribute[0]);
        }
        return diretorio;

    }

    private SftpUtil criarSftp(ConciliarSftp conciliarSftp) throws ProativaException {

        if (conciliarSftp == null || StringUtils.isBlank(conciliarSftp.getUrl()) || StringUtils.isBlank(conciliarSftp.getUsr()) || StringUtils.isBlank(conciliarSftp.getPorta()) || StringUtils.isBlank(conciliarSftp.getPsw()))
            throw new ProativaException("Sftp invalido, verifique,");

        int porta = 0;

        try {

            porta = StringUtils.isNumeric(conciliarSftp.getPorta()) ? Integer.valueOf(conciliarSftp.getPorta()).intValue() : 22;

        } catch (Exception e) {

            throw new ProativaException("Porta invalida, verifique,");
        }

        return SftpUtil.builder(conciliarSftp.getUrl(), conciliarSftp.getUsr(), conciliarSftp.getPsw(), porta);
    }

    private List<ConciliarAudioAnexo> buscarConciliarAudios(List<?> listConciliarSelecionados) {


        List<Long> listIds = retornarIdsAtendimentoConciliar(listConciliarSelecionados);

        if (CollectionUtils.isNotEmpty(listIds)) {

            return this.serviceAudiosAnexos.pesquisarPorAtendimentos(listIds);

        }

        return null;

    }

    private List<Long> retornarIdsAtendimentoConciliar(List<?> listConciliarSelecionados) {

        if (CollectionUtils.isEmpty(listConciliarSelecionados))
            return null;


        List<Long> listIds = new ArrayList<Long>();

        for (Object obj : listConciliarSelecionados) {

            Object[] arrayObj = (Object[]) obj;
            listIds.add(((BigInteger) arrayObj[0]).longValue());

        }

        return listIds;


    }


}
