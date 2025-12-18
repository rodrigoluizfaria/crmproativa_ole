package com.proativaservicos.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.Empresa;
import com.proativaservicos.model.Fila;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.model.*;
import com.proativaservicos.model.pwd.Campanha;
import com.proativaservicos.model.pwd.*;
import com.proativaservicos.service.EmpresaService;
import com.proativaservicos.service.FilaService;
import com.proativaservicos.service.IntegracaoService;
import com.proativaservicos.service.LogImportacaoDiscadorService;
import com.proativaservicos.util.constantes.*;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Named
public class VirtualDiscadorPoweDial implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EmpresaService serviceEmpresa;


    @Inject
    private IntegracaoService serviceIntegra;

    @Inject
    private FilaService serviceFila;

    @Inject
    private LogImportacaoDiscadorService serviceLogDiscador;

    private static final String URL_CAMPANHA_PWD = "/services/V1/campanha";

    private static final String URL_CRIAR_FILA = "/services/V1/createQueue";
    private static final String URL_CRIAR_AGENT = "/services/V1/createAgent";
    private static final String URL_IMPORTAR_MAILING = "/services/V1/campanha/importMailing";
    private static final String URL_CRIAR_EMPRESA = "/services/V1/empresa";

    private static final String URL_LOGAR_AGENT = "/services/V1/loginAgent";
    private static final String URL_DESLOGAR_AGENT = "/services/V1/logoutAgent";

    private static final String URL_PAUSAR = "/services/V1/pauseAgent";
    private static final String URL_AGENT_FILAS = "/services/V1/agentQueues/";
    private static final String URL_ADICIONAR_FILAS = "/services/V1/addOnQueues";
    private static final String URL_REMOVER_FILAS = "/services/V1/removeFromQueues";


    private static final String URL_BUSCAR_EMPRESA_POR_IDENTIFICADO = "/services/V1/empresa/identificador/";


    public String enviarContatosCampanha(IntegracaoWs integracao, com.proativaservicos.model.Campanha campanha, Collection<Atendimento> listAtendimentos) throws ProativaException {


        try {

            if (campanha == null || campanha.getCodImportacaoPwd() == null)
                throw new ProativaException("Nenhuma integração com discador foi informado.");


            if (integracao == null || StringUtils.isBlank(integracao.getUrl()))
                throw new ProativaException("Nenhuma integração com discador foi informado.");

            if (CollectionUtils.isEmpty(listAtendimentos))
                throw new ProativaException("Nenhum atendimento deve ser informado.");


            DiscadorPowerDialer discador = new DiscadorPowerDialer();

            discador.getCampanha().setCodigo(campanha.getCodImportacaoPwd());

            if (CollectionUtils.isNotEmpty(listAtendimentos)) {

                List<Atendimento> listAtendimentosUnicos = (List<Atendimento>) listAtendimentos.stream().filter(com.proativaservicos.util.CollectionUtils.distinctByKey(a -> a.getId())).collect(Collectors.toList());

                for (Atendimento atendimento : listAtendimentosUnicos) {

                    Mailing mailing = new Mailing();

                    mailing.setIdentificador(String.valueOf(atendimento.getId()));

                    mailing.setAtendimento(String.valueOf(atendimento.getId()));

                    mailing.setNome(StringUtils.isBlank(atendimento.getNome()) ? "NÃO INFORMADO" : atendimento.getNome());

                    mailing.setNumeros(new ArrayList<Numero>());

                    for (Telefone tel : atendimento.getListTelefones()) {

                        Numero numero = new Numero();
                        numero.setDdd(tel.getDdd());
                        numero.setTelefone(tel.getNumero());
                        mailing.getNumeros().add(numero);

                    }

                    discador.getMailing().add(mailing);

                }

                String jsonString = (new Gson()).toJson(discador);

                String url = integracao.getUrl() + URL_IMPORTAR_MAILING;

                criarJsonEnvio(TextUtil.builder(jsonString).removerAcento().getTexto(), "cargaDiscadorPwd");

                Map<String, String> retornoMap = HttpPostUtil.enviarPostUrl(url, null, TextUtil.builder(jsonString).removerAcento().getTexto(), false);

                gerarLogDiscador(integracao, retornoMap, listAtendimentosUnicos);

                return "sucesso";
            }

        } catch (ProativaException e) {

            e.printStackTrace();
            tratarErro(e);

        } catch (Exception e) {

            e.printStackTrace();
            throw new ProativaException("Erro Power dialer: " + ((e.getMessage().length() < 120) ? e.getMessage() : e.getMessage().substring(0, 120)));
        }

        return null;
    }


    private void gerarLogDiscador(IntegracaoWs integra, Map<String, String> retornoMap, List<Atendimento> listAtn) throws ProativaException {

        if (retornoMap != null && !retornoMap.isEmpty()) {

            LogImportacaoDiscador logDiscador = new LogImportacaoDiscador();

            logDiscador.setDadoRetorno(retornoMap.containsKey("retorno") ? retornoMap.get("retorno") : "");

            logDiscador.setQuantidadeEnviado(CollectionUtils.isNotEmpty(listAtn) ? Long.valueOf(listAtn.size()) : Long.valueOf(0));

            logDiscador.setImportacao(listAtn.get(0).getImportacao());

            logDiscador.setDataEnvio(new Date());

            logDiscador.setHttpStatus(retornoMap.containsKey("status") ? retornoMap.get("status") : "");

            logDiscador.setTipoIntegracao(integra.getTipoIntegracao());

            System.out.println("Importacao finalizada: " + retornoMap.get("status") + " | Retorno: " + retornoMap.get("retorno"));

            if (retornoMap.containsKey("sucesso")) {

                boolean sucesso = Boolean.valueOf(retornoMap.get("sucesso"));

                if (sucesso) {

                    try {

                        Long longQuantidade = null;

                        String quantidade = retornoMap.get("mensagem").split(" ")[0];

                        longQuantidade = Long.valueOf(quantidade);


                        logDiscador.setQuantidadeEnviado(longQuantidade);

                    } catch (Exception e) {

                    }
                }


            }

            this.serviceLogDiscador.inserir(logDiscador);

        }

    }


    public Long inserirCampanhaPwd(com.proativaservicos.model.Campanha campanha, String urlApi) throws ProativaException {

        try {

            if (campanha == null || StringUtils.isBlank(campanha.getDescricao()) || !TipoCampanhaEnum.PREDITIVA.equals(campanha.getTipoCampanha())) {

                return null;
            }

            Empresa empresa = this.serviceEmpresa.pesquisarEmpresa(campanha.getEmpresa().getId());

            IntegracaoWs servicePwd = this.serviceIntegra.pesquisarIntegracoes(TipoIntegracaoEnum.VIRTUAL_POWER_DIALER, empresa.getId(), TipoAcessoEnum.ATIVO);

            IntegracaoWs serviceVsphoneContactCenter = this.serviceIntegra.pesquisarIntegracoes(TipoIntegracaoEnum.VIRTUAL_CONTACT_CENTER, empresa.getId(), TipoAcessoEnum.ATIVO);

            if (servicePwd != null && serviceVsphoneContactCenter != null) {

                if (campanha.getFila() == null)
                    throw new ProativaException("Fila deve ser informada.");

                String urlCampanha = servicePwd.getUrl() + URL_CAMPANHA_PWD;


                Campanha campanhaDto = new Campanha();

                if (campanha.getCodImportacaoPwd() != null) {

                    campanhaDto.setCodigo(campanha.getCodImportacaoPwd());
                    String jsonCampanha = HttpUrlUtil.enviarGet(urlCampanha + "/" + campanhaDto.getCodigo().toString());
                    campanhaDto = (Campanha) (new Gson()).fromJson(jsonCampanha, Campanha.class);

                }

                campanhaDto.setNome(campanha.getDescricao());

                campanhaDto.setNomeArquivo("PROATIVA_" + String.valueOf(campanha.getId()) + "_" + DateUtil.builder().formatarDataParaString("yyyyMMddHHmmss").getDataTexto());

                campanhaDto.getEmpresa().setIdentificador("PROATEND_" + campanha.getEmpresa().getCnpj());

                com.proativaservicos.model.pwd.Empresa empresaPwd = buscarEmpresaPorIdentificador(servicePwd, "PROATEND_" + campanha.getEmpresa().getCnpj());


                if (empresaPwd == null) {

                    Map<String, String> retornoEmpresa = null;

                    retornoEmpresa = inserirEmpresaPwd(servicePwd, "PROATEND_" + campanha.getEmpresa().getCnpj());

                    if (retornoEmpresa != null && (retornoEmpresa.get("status").equals("200") || retornoEmpresa.get("status").equals("201"))) {

                        empresaPwd = (new Gson()).fromJson(retornoEmpresa.get("retorno"), com.proativaservicos.model.pwd.Empresa.class);

                        empresaPwd.setName("PROATEND_" + empresa.getNome());
                    }

                }

                campanhaDto.setEmpresa(empresaPwd);

                campanhaDto.setFila(campanha.getFila().getNome());

                campanhaDto.setWebhook("http://10.8.10.95:8080/crmproativa/" + "rest/onPreditivo/receberDados");

                criarFila(serviceVsphoneContactCenter, campanha.getFila(), empresa);

                String json = (new Gson()).toJson(campanhaDto);

                criarJsonEnvio(TextUtil.builder(json).removerAcento().getTexto(), "salvarCampanha");

                Map<String, String> retorno = enviarPostPutCampanha(campanha, json, urlCampanha);


                Campanha campanhaReturn = (new Gson()).fromJson(retorno.get("retorno"), Campanha.class);

                alterarStatusCampanhaDiscador(servicePwd, campanha, campanhaReturn.getCodigo());

                return campanhaReturn.getCodigo();

            }

        } catch (ProativaException e) {

            e.printStackTrace();

        } catch (Exception e) {

            e.printStackTrace();
            throw new ProativaException("Erro ao importar no discador.");
        }

        return null;

    }

    private Map<String, String> enviarPostPutCampanha(com.proativaservicos.model.Campanha campanha, String json, String url) throws ProativaException, Exception {

        Map<String, String> retorno = null;

        if (campanha.getCodImportacaoPwd() != null) {

            retorno = HttpPostUtil.enviarPutUrl(url, null, json, url.startsWith("https"));

        } else {

            retorno = HttpPostUtil.enviarPostUrl(url, null, json, url.startsWith("https"));

        }


        return retorno;
    }


    public Map<String, String> inserirEmpresaPwd(IntegracaoWs integraPwd, String identidicador) throws ProativaException, Exception {

        if (integraPwd == null)
            throw new ProativaException("Integracao deve ser informada.");

        if (StringUtils.isBlank(identidicador))
            throw new ProativaException("Identificador da empresa deve ser informada.");

        com.proativaservicos.model.pwd.Empresa empresa = new com.proativaservicos.model.pwd.Empresa();

        empresa.setIdentificador(identidicador);

        //	String [] strPabx = {"3d5c462a-d14b-4b47-a81a-4d2c4690aa25"};

        //	empresa.setPabx(strPabx);

        String json = (new Gson()).toJson(empresa);


        criarJsonEnvio(TextUtil.builder(json).removerAcento().getTexto(), "salvarEmpresa");

        Map<String, String> retorno = null;

        String urlEmpresa = integraPwd.getUrl() + URL_CRIAR_EMPRESA;


        retorno = HttpPostUtil.enviarPostUrl(urlEmpresa, null, json, urlEmpresa.startsWith("https"));


        return retorno;


    }

    public com.proativaservicos.model.pwd.Empresa buscarEmpresaPorIdentificador(IntegracaoWs integraPwd, String identificador) throws ProativaException {

        if (integraPwd == null)
            throw new ProativaException("Integracao deve ser informada.");

        if (StringUtils.isBlank(identificador))
            throw new ProativaException("Identificador da empresa deve ser informada.");

        String retorno = null;

        try {

            retorno = HttpUrlUtil.enviarGet(integraPwd.getUrl() + URL_BUSCAR_EMPRESA_POR_IDENTIFICADO + identificador);

            return (new Gson()).fromJson(retorno, com.proativaservicos.model.pwd.Empresa.class);


        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;

    }

    public void alterarStatusCampanhaDiscador(IntegracaoWs integraPwd, com.proativaservicos.model.Campanha campanha, Long discadorPwd) throws ProativaException {

        try {

            String url = integraPwd.getUrl() + URL_CAMPANHA_PWD;

            if (campanha.getStatus().getAcao().equals(AcaoCampanhaEnum.LIBERAR)) {

                url = url + "/start";

            } else if (campanha.getStatus().getAcao().equals(AcaoCampanhaEnum.SUSPENDER)) {

                url = url + "/pause";

            } else {
                url = url + "/finalizar";
            }


            JsonObject jsonObj = new JsonObject();

            jsonObj.addProperty("codigo", discadorPwd);

            criarJsonEnvio(jsonObj.toString(), "alterarCampanha");

            HttpPostUtil.enviarPostUrl(url, null, jsonObj.toString(), url.startsWith("https"));


        } catch (ProativaException e) {
            tratarErro(e);

        } catch (Exception e) {

            e.printStackTrace();
            throw new ProativaException("Erro Discador > " + e.getMessage());
        }


    }


    public String criarFila(IntegracaoWs integra, Fila fila, Empresa empresa) throws ProativaException {

        try {

            if (integra == null || StringUtils.isBlank(integra.getUrl()))
                throw new ProativaException("Pabx não informado.");

            if (empresa == null)
                throw new ProativaException("Empresa não informado.");

            if (fila == null)
                throw new ProativaException("Fila não informado.");

            PowerDialerFila pwdFila = new PowerDialerFila();

            pwdFila.setEmpresa(new com.proativaservicos.model.pwd.Company());
            pwdFila.getEmpresa().setPrefix("PROATEND_" + empresa.getCnpj());
            pwdFila.getEmpresa().setName(empresa.getNome());

            pwdFila.setFila(new com.proativaservicos.model.pwd.Fila());
            pwdFila.getFila().setDescricao(fila.getNome().toLowerCase());
            pwdFila.getFila().setName(fila.getNome().toLowerCase());

            String url = integra.getUrl() + URL_CRIAR_FILA;

            String json = (new Gson()).toJson(pwdFila);


            criarJsonEnvio(TextUtil.builder(json).removerAcento().getTexto(), "criarFila");

            Map<String, String> retorno = HttpPostUtil.enviarPostUrl(url, null, TextUtil.builder(json).removerAcento().getTexto(), url.startsWith("https"));


            return retorno.get("retorno");

        } catch (ProativaException e) {

            e.printStackTrace();
            return null;

        } catch (Exception e) {

            e.printStackTrace();
            throw new ProativaException("Erro ao importar no discador.");
        }

    }

    public Map<String, String> criarAgent(Usuario usuario) throws ProativaException {
        return criarAgent(usuario, null);
    }

    public Map<String, String> criarAgent(Usuario usuario, IntegracaoWs integracao) throws ProativaException {

        try {

            if (usuario == null)
                return null;

            if (usuario.getEmpresa() == null)
                return null;

            if (integracao == null)
                integracao = serviceIntegra.pesquisarIntegracoes(TipoIntegracaoEnum.VIRTUAL_CONTACT_CENTER, usuario.getEmpresa().getId(), TipoAcessoEnum.ATIVO);

            if (integracao != null) {
                com.proativaservicos.model.pwd.Usuario agent = new com.proativaservicos.model.pwd.Usuario();

                agent.setLogin(retornarUsuarioCodLogin(usuario));
                agent.setName(usuario.getNome());
                agent.setPassword("pro123");
                agent.getCompany().setName(usuario.getEmpresa().getNome());
                agent.getCompany().setPrefix("PROATEND_" + usuario.getEmpresa().getCnpj());

                String url = integracao.getUrl().replaceAll("/powerdialer", "") + URL_CRIAR_AGENT;

                String json = TextUtil.builder((new Gson()).toJson(agent)).removerAcento().getTexto();

                criarJsonEnvio(json, "salvarUsuario");

                Map<String, String> retorno = HttpPostUtil.enviarPostUrl(url, null, json, url.startsWith("https"));


                return retorno;
            }

        } catch (ProativaException e) {

            e.printStackTrace();

        } catch (Exception e) {

            e.printStackTrace();
            throw new ProativaException("Erro ao importar no discador.");
        }

        return null;

    }

    private void criarJsonEnvio(String texto, String metodo) {


        try {

            File arquivo = File.createTempFile(metodo, ".txt", new File(System.getProperty("java.io.tmpdir")));

            FileUtils.writeStringToFile(arquivo, texto, "UTF-8");

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public String retornarJsonFilas(Usuario usuario, String hostPwd) throws ProativaException, Exception {

        if (usuario == null || StringUtils.isBlank(usuario.getLogin()) || StringUtils.isBlank(hostPwd) || !hostPwd.startsWith("http")) {
            return null;

        } else {


            return HttpUrlUtil.enviarGet(hostPwd + URL_AGENT_FILAS + retornarUsuarioCodLogin(usuario));
        }

    }

    public String retornarUsuarioCodLogin(Usuario usuario) {

        if (usuario != null)
            return usuario.getEmpresa().getCnpj() + StringUtils.leftPad(String.valueOf(usuario.getId()), 5, "0");

        return "";

    }

    @SuppressWarnings("unchecked")
    public Map<String, String> logarAgent(IntegracaoWs integracao, Usuario usuario) throws ProativaException {

        try {

            usuario.setRespostaLoginPowerDialer(null);

            String[] queueString = null;

            List<String> listFilas = new ArrayList<String>();

            String filasRetorno = null;

            if (integracao == null || validarUsuario(usuario) || usuario.getPerfil().equals(PerfilUsuarioEnum.ADMIN)) {

                return null;
            }


            String urPwd = retornarHostContactCenter(integracao);

            try {

                filasRetorno = retornarJsonFilas(usuario, urPwd);

            } catch (Exception e) {

                filasRetorno = "";
                System.err.println(e.getMessage());

            }

            if (StringUtils.isNotBlank(filasRetorno)) {

                usuario.setFilasCadastradasPoweDialer((List<String>) (new Gson()).fromJson(filasRetorno, List.class));
                listFilas.addAll(usuario.getFilasCadastradasPoweDialer());

            }

            listFilas.removeAll(this.serviceFila.pesquisarFilasPorEquipe(usuario.getEquipe()));

            String url = urPwd + URL_LOGAR_AGENT;


            listFilas.add("ativo_manual_" + usuario.getEmpresa().getCnpj());

            if (CollectionUtils.isNotEmpty(listFilas)) {

                queueString = (String[]) listFilas.toArray(new String[0]);
            }

            LoginAgent login = new LoginAgent(retornarUsuarioCodLogin(usuario), "pro123", usuario.getPontoAtendimento().getRamal(), queueString);

            String json = TextUtil.builder((new Gson()).toJson(login)).removerAcento().getTexto();

            criarJsonEnvio(json, "logarUsuario");


            Map<String, String> retorno = HttpPostUtil.enviarPostUrl(url, null, json, url.startsWith("https"));

            if (validarLoginCriarFila(usuario, retorno)) {

                criarFila(integracao, new Fila("ativo_manual_" + usuario.getEmpresa().getCnpj()), usuario.getEmpresa());


                retorno = HttpPostUtil.enviarPostUrl(url, null, json, url.startsWith("https"));

            }

            usuario.setRespostaLoginPowerDialer((RespostaLoginPowerDialer) (new Gson()).fromJson(retorno.get("retorno"), RespostaLoginPowerDialer.class));

            return retorno;

        } catch (Exception e) {

            e.printStackTrace();
            throw new ProativaException("Erro ao enviar no discador.");
        }

    }

    private boolean validarLoginCriarFila(Usuario usuario, Map<String, String> retorno) {

        if (retorno != null && retorno.containsKey("retorno")) {

            JSONObject obj = new JSONObject(retorno.get("retorno"));

            if (obj != null && !obj.isEmpty() && !obj.isNull("sucess") && !obj.getBoolean("sucess") && !obj.isNull("message") && StringUtils.isNotBlank(obj.getString("message"))) {

                if (StringUtils.containsIgnoreCase(obj.getString("message"), "Verificar filas do usuario")) {

                    return true;
                }


            }

        }
        return false;

    }

    public Map<String, String> deslogarAgent(IntegracaoWs integracao, Usuario usuario) throws ProativaException {


        try {

            integracao = validarUsuario(integracao, usuario);

            String url = integracao.getUrl().replaceAll("/powerdialer", "") + URL_DESLOGAR_AGENT;

            LoginAgent login = new LoginAgent();

            login.setLogin(retornarUsuarioCodLogin(usuario));

            String json = TextUtil.builder((new Gson()).toJson(login)).removerAcento().getTexto();

            criarJsonEnvio(json, "logarUsuario");
            System.out.println(json);

            Map<String, String> retorno = HttpPostUtil.enviarPostUrl(url, null, json, url.startsWith("https"));
            System.out.println(retorno.get("retorno"));
            return retorno;

        } catch (Exception e) {

            e.printStackTrace();
            throw new ProativaException("Erro ao importar no discador.");
        }
    }


    public void entrarEmpPausa(Usuario usuario, String strPausa) throws ProativaException {

        IntegracaoWs integracao = this.serviceIntegra.pesquisarIntegracoes(TipoIntegracaoEnum.VIRTUAL_CONTACT_CENTER, usuario.getEmpresa().getId(), TipoAcessoEnum.ATIVO);
        entrarEmPausa(integracao, usuario, strPausa);

    }

    public Map<String, String> entrarEmPausa(IntegracaoWs integracao, Usuario usuario, String strPausa) throws ProativaException {

        try {

            integracao = validarUsuario(integracao, usuario);

            if (usuario.getRespostaLoginPowerDialer() == null) {
                throw new ProativaException("Sem resposta discador.");
            }

            if (CollectionUtils.isEmpty(usuario.getRespostaLoginPowerDialer().getMotivosChamada())) {
                throw new ProativaException("Nenhuma Pausa disponível no discador.");
            }

            Integer pausaId = 0;

            if (StringUtils.isBlank(strPausa)) {

                pausaId = usuario.getRespostaLoginPowerDialer().getMotivosPausa().get(0).getId();

            } else {

                MotivosPausa motivo = usuario.getRespostaLoginPowerDialer().getMotivosPausa().stream().filter(m -> m.getName().equalsIgnoreCase(strPausa)).findFirst().orElse(null);
                pausaId = (motivo != null) ? motivo.getId() : usuario.getRespostaLoginPowerDialer().getMotivosPausa().get(0).getId();
            }

            String url = retornarHostContactCenter(integracao) + URL_PAUSAR;
            JsonObject jsonEnty = new JsonObject();

            jsonEnty.addProperty("login", retornarUsuarioCodLogin(usuario));
            jsonEnty.addProperty("pauseId", pausaId);
            jsonEnty.addProperty("pause", true);

            Map<String, String> retorno = HttpPostUtil.enviarPostUrl(url, null, jsonEnty.toString(), url.startsWith("https"));

            usuario.setPausaContactCenter(true);

            return retorno;


        } catch (ProativaException e) {

            e.printStackTrace();
            tratarErro(e);


        } catch (Exception e) {

            throw new ProativaException("Erro power dialer: " + e.getMessage());
        }

        return null;
    }

    private String retornarHostContactCenter(IntegracaoWs integraCc) {

        return integraCc.getUrl().replaceAll("/powerdialer", "");

    }

    private IntegracaoWs retornarIntegracaoPwd(IntegracaoWs integracaoContactCenter, Usuario usuario) {

        if (TipoIntegracaoEnum.VIRTUAL_CONTACT_CENTER.equals(integracaoContactCenter.getTipoIntegracao())) {
            return this.serviceIntegra.pesquisarIntegracao(TipoIntegracaoEnum.VIRTUAL_POWER_DIALER, usuario.getEmpresa().getId(), TipoAcessoEnum.ATIVO);
        }

        return integracaoContactCenter;
    }


    private IntegracaoWs validarUsuario(IntegracaoWs integracao, Usuario usuario) throws ProativaException {

        if (usuario == null || StringUtils.isBlank(usuario.getLogin()) || usuario.getPontoAtendimento() == null || StringUtils.isBlank(usuario.getPontoAtendimento().getRamal()))
            throw new ProativaException("Usuario ou ponto de atendimento não informado.");

        if (integracao == null) {

            integracao = this.serviceIntegra.pesquisarIntegracoes(TipoIntegracaoEnum.VIRTUAL_CONTACT_CENTER, usuario.getEmpresa().getId(), TipoAcessoEnum.ATIVO);

            if (integracao == null || StringUtils.isBlank(integracao.getUrl()))
                throw new ProativaException("Nenhuma integração com PowerDialer disponível.");

        }

        return integracao;
    }

    private void tratarErro(ProativaException e) throws ProativaException {


        throw new ProativaException("Erro power dialer: " + e.getMessage());

    }


    public String ficarDisponivelDiscadora(IntegracaoWs integracao, Usuario usuario) throws ProativaException {

        return associarFilas(usuario, integracao);
    }

    public Map<String, String> desassociarFilasDiscadora(IntegracaoWs integraCc, Usuario usuario, boolean removerFilaAtivo) throws ProativaException {

        List<String> listFilas = new ArrayList<String>();

        if (usuario.getEquipe() != null) {

            listFilas.addAll(this.serviceFila.pesquisarFilasPorEquipe(usuario.getEquipe()));

        }

        if (CollectionUtils.isNotEmpty(listFilas)) {

            return removerFilas(usuario, listFilas, integraCc, removerFilaAtivo);

        }
        return null;

    }


    private Map<String, String> removerFilas(Usuario usuario, List<String> listFilas, IntegracaoWs integraCc, boolean removerFilaAtivo) throws ProativaException {
        try {

            if (validarUsuario(usuario))
                return null;

            if (integraCc == null)
                throw new ProativaException("Nenhuma integração contact center informada.");


            String url = retornarHostContactCenter(integraCc) + URL_REMOVER_FILAS;
            JsonObject jsonEntity = new JsonObject();

            jsonEntity.addProperty("login", retornarUsuarioCodLogin(usuario));

            JsonArray jasonArray = new JsonArray();

            if (CollectionUtils.isNotEmpty(listFilas)) {

                for (String fila : listFilas) {

                    jasonArray.add(new JsonPrimitive(TextUtil.builder(fila.toLowerCase()).removerAcento().getTexto()));
                }

                if (removerFilaAtivo)
                    jasonArray.add(new JsonPrimitive("ativo_manual_" + usuario.getEmpresa().getCnpj()));

                jsonEntity.add("queues", jasonArray);

                System.out.println(jsonEntity);

                Map<String, String> mapRestorno = HttpPostUtil.enviarPostUrl(url, null, jsonEntity.toString().replaceAll("\\\\", ""), url.startsWith("https"));

                System.out.println(mapRestorno.get("retorno"));
                return mapRestorno;
            }

        } catch (ProativaException e) {

            throw new ProativaException(e.getMessage());

        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;


    }


    private String associarFilas(Usuario usuario, IntegracaoWs integracao) throws ProativaException {

        List<String> listFilas = new ArrayList<String>();

        if (usuario.getEquipe() != null) {

            listFilas.addAll(this.serviceFila.pesquisarFilasPorEquipe(usuario.getEquipe()));
        }

        if (CollectionUtils.isNotEmpty(usuario.getFilasCadastradasPoweDialer())) {

            listFilas.addAll(usuario.getFilasCadastradasPoweDialer());
        }


        if (CollectionUtils.isNotEmpty(listFilas)) {

            Map<String, String> retorno = adicionarFilas(usuario, listFilas, integracao);

            if (retorno != null && retorno.containsKey("retorno")) {

                JSONObject obj = new JSONObject(retorno.get("retorno"));

                if (obj != null && !obj.isEmpty() && !obj.isNull("sucesso") && !obj.getBoolean("sucesso")) {

                    return obj.getString("mensagem");

                }
            }


        } else {

            return "Nenhuma fila associada a seu login.";
        }

        return null;

    }


    private Map<String, String> adicionarFilas(Usuario usuario, List<String> listFilas, IntegracaoWs integracao) throws ProativaException {

        try {

            if (validarUsuario(usuario))
                return null;


            if (integracao == null)
                throw new ProativaException("Nenhuma integração contact center informada.");


            String url = retornarHostContactCenter(integracao) + URL_ADICIONAR_FILAS;

            JsonObject jsonEntity = new JsonObject();

            jsonEntity.addProperty("login", retornarUsuarioCodLogin(usuario));

            JsonArray jasonArray = new JsonArray();

            if (CollectionUtils.isNotEmpty(listFilas)) {

                for (String fila : listFilas) {

                    jasonArray.add(new JsonPrimitive(TextUtil.builder(fila.toLowerCase()).removerAcento().getTexto()));
                }

                jasonArray.add(new JsonPrimitive("ativo_manual_" + usuario.getEmpresa().getCnpj()));

                jsonEntity.add("queues", jasonArray);


                Map<String, String> mapRestorno = HttpPostUtil.enviarPostUrl(url, null, jsonEntity.toString().replaceAll("\\\\", ""), url.startsWith("https"));


                atualizarFilasUsuario(mapRestorno, usuario, jasonArray);

                return mapRestorno;

            }

        } catch (ProativaException e) {

            throw new ProativaException(e.getMessage());

        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }

    private void atualizarFilasUsuario(Map<String, String> retorno, Usuario usuario, JsonArray array) {

        if (retorno != null && retorno.containsKey("retorno") && StringUtils.isNotBlank(retorno.get("retorno"))) {

            JSONObject obj = new JSONObject(retorno.get("retorno"));

            List<String> listFilas = new ArrayList<String>();

            if (obj != null && !obj.isEmpty() && !obj.isNull("sucesso") && obj.getBoolean("sucesso")) {

                for (int i = 0; i < array.size(); i++) {
                    listFilas.add(array.get(i).getAsString());
                }
                if (CollectionUtils.isNotEmpty(listFilas)) {
                    usuario.getRespostaLoginPowerDialer().setFilas(listFilas);
                }


            }
        }


    }


    public Map<String, String> unPauseDiscador(IntegracaoWs integracao, Usuario usuario) throws ProativaException {

        try {

            if (usuario == null || StringUtils.isBlank(usuario.getLogin()))
                return null;


            if (integracao == null || StringUtils.isBlank(integracao.getUrl()))
                throw new ProativaException("Nenhuma integração contact center informada.");


            String url = retornarHostContactCenter(integracao) + URL_PAUSAR;

            JsonObject jsonEnty = new JsonObject();

            jsonEnty.addProperty("login", retornarUsuarioCodLogin(usuario));

            jsonEnty.addProperty("pause", false);

            Map<String, String> retorno = HttpPostUtil.enviarPostUrl(url, null, jsonEnty.toString(), url.startsWith("https"));

            usuario.setPausaContactCenter(false);


            return retorno;


        } catch (ProativaException e) {

            throw new ProativaException(e.getMessage());

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;

    }


    private boolean validarUsuario(Usuario usuario) {

        if (usuario.getEmpresa() == null || usuario.getPontoAtendimento() == null || StringUtils.isBlank(usuario.getPontoAtendimento().getRamal()) || usuario.getEquipe() == null) {

            return true;

        }
        return false;
    }


    public static void main(String[] args) throws ProativaException, Exception {


        IntegracaoWs integra = new IntegracaoWs();
        integra.setUrl("http://10.8.1.215:8880/powerdialer");

        com.proativaservicos.model.Campanha cam = new com.proativaservicos.model.Campanha();
        cam.setId(1L);
        cam.setCodImportacaoPwd(663L);

        VirtualDiscadorPoweDial discador = new VirtualDiscadorPoweDial();

        //discador.consultarContatosDiscador(integra, cam);


    }


    public CampanhaRetornoDiscagem consultarContatosDiscador(IntegracaoWs integracao, com.proativaservicos.model.Campanha campanha, Usuario usuario) throws ProativaException {

        if (integracao == null || campanha == null)
            return null;

        integracao = retornarIntegracaoPwd(integracao, usuario);

        String urlEnvio = integracao.getUrl() + "/services/V1/campanha/" + String.valueOf(campanha.getCodImportacaoPwd()) + "/resultado";

        System.out.println(urlEnvio);

        String json;

        try {

            json = HttpUrlUtil.enviarGet(urlEnvio);
            CampanhaRetornoDiscagem retorno = (new Gson()).fromJson(json, CampanhaRetornoDiscagem.class);
            return retorno;

        } catch (ProativaException e) {

            throw new ProativaException(e.getMessage());

        } catch (Exception e) {

            throw new ProativaException("Erro ao conectar do discador.");
        }


    }


}
