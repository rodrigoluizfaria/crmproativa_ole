package com.proativaservicos.service.socket;

import com.proativaservicos.model.Usuario;
import com.proativaservicos.service.GrupoFaqPerguntaService;
import com.proativaservicos.service.UsuarioService;
import com.proativaservicos.util.constantes.SexoEnum;
import jakarta.inject.Inject;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/bot/{login}")
public class BotPoint {

    private static Set<BotPoint> listPoint = new CopyOnWriteArraySet<BotPoint>();

    private String login;

    private Usuario usuario;

    private Session session;

    @Inject
    private UsuarioService serviceUsuario;

    @Inject
    private GrupoFaqPerguntaService serviceGrupos;

    @OnOpen
    public void onOpen(Session session, @PathParam("login") String username) {

        this.login = username;
        this.session = session;

        this.usuario = (Usuario) this.serviceUsuario.pesquisarUsuario(username);

        listPoint.add(this);
        onMessage(session, "Olá " + configurarNome(this.usuario.getNome()) + ".<br/> Sou o Supervisor Virtual.<br/> Para iniciar por favor selecione um tópico.");
        System.out.println("Iniciou o bot: " + username);

    }

    private String configurarNome(String nome) {

        String nomeRetorno = "";

        if (StringUtils.isNotBlank(nome)) {

            String[] nomeArray = nome.split(" ");

            if (nomeArray.length > 1) {

                if (nomeArray[0].length() >= 3) {

                    nomeRetorno = StringUtils.capitalize(nomeArray[0].toLowerCase());

                } else {

                    nomeRetorno = StringUtils.capitalize(nome);
                }

            } else {

                nomeRetorno = StringUtils.capitalize(nome);

            }

        }
        return nomeRetorno;

    }

    @OnMessage
    public void onMessage(Session session, String message) {

        envarRespostas(session, prepararPerguntas(message), "");

    }

    private void envarRespostas(Session sesionOrigem, String msg, String login) {

        Optional<BotPoint> b = listPoint.stream().filter(p -> p.session.getId().equals(sesionOrigem.getId()))
                .findFirst();

        if (b.isPresent()) {
            login = b.get().login;

            try {

                b.get().session.getBasicRemote().sendText(msg);

            } catch (IOException e1) {

                e1.printStackTrace();
            }
        }

    }

    @OnError
    public void onClose(Session session, Throwable tr) throws Throwable {

        Optional<BotPoint> op = BotPoint.listPoint.stream().filter(f -> f.session.getId().equals(session.getId()))
                .findFirst();

        if (op.isPresent()) {

            BotPoint.listPoint.remove(op.get());

        }

    }

    private List<?> buscarTeste() {

        List<Object[]> list = new ArrayList<>();

        Object[] ob = new Object[8];
        ob[0] = Long.valueOf(19);
        ob[1] = "Cartão Inss";
        ob[2] = "Argumentações ";
        ob[3] = "Como argumento para vender o cartão podemos questionar o cliente o motivo dele usar um cartão convencional sendo que o benefício dele permite que ele tenha um cartão exclusivo com muito mais vantagens. É importante destacar que esse cartão não possui anuidade e não possui multa por atraso. Além disso, é importante salientar também a possibilidade de saque e se for interessante ressaltar também as baixas taxas em comparação aos cartões convencionais.";
        ob[4] = "Bigbox";
        ob[5] = Long.valueOf(3);
        ob[6] = Long.valueOf(4);
        ob[7] = "BANCO BMG";

        list.add(ob);

        Object[] ob2 = new Object[8];
        ob2[0] = Long.valueOf(19);
        ob2[1] = "Cartão Inss";
        ob2[2] = "Taxa de juros";
        ob2[3] = "3,06%";
        ob2[4] = "Bigbox";
        ob2[5] = Long.valueOf(3);
        ob2[6] = Long.valueOf(4);
        ob2[7] = "BANCO BMG";
        list.add(ob2);

        Object[] ob3 = new Object[8];
        ob3[0] = Long.valueOf(20);
        ob3[1] = "Cartão Federal";
        ob3[2] = "Benefícios";
        ob3[3] = "Disponível para negativados, possibilidade de saque, taxa de juros menor do que cartão de crédito convencional, sem anuidade, sem multa por atraso.";
        ob3[4] = "Bigbox";
        ob3[5] = Long.valueOf(3);
        ob3[6] = Long.valueOf(4);
        ob3[7] = "BANCO BMG";
        list.add(ob3);

        Object[] ob4 = new Object[8];
        ob4[0] = Long.valueOf(26);
        ob4[1] = "Cartão CRED";
        ob4[2] = "TESTE";
        ob4[3] = "Disponível para SDSA,  de crédito convencional, sem anuidade, sem multa por atraso.";
        ob4[4] = "Bigbox";
        ob4[5] = Long.valueOf(3);
        ob4[6] = Long.valueOf(1);
        ob4[7] = "BANCO CREDCESTA";
        list.add(ob4);

        return list;

    }

    public String prepararPerguntas(String msg) {

        try {

            JSONObject jsoGrupo = new JSONObject();
            /*
             * this.usuario = new Usuario(Long.valueOf(1), "Administrador");
             * this.usuario.setEquipe(new Equipe(Long.valueOf(2), "BigBox", null));
             */
            if (this.usuario != null && this.usuario.getEquipe() != null && this.usuario.getEquipe().getId() != null) {

                List<?> lista = this.serviceGrupos.pesquisarGruposPerguntasPorEquipes(this.usuario.getEquipe().getId());
                // List<?> lista = buscarTeste();
                jsoGrupo.put("menssagem", msg);
                jsoGrupo.put("equipe", this.usuario.getEquipe().getNome());
                jsoGrupo.put("equipe_id", this.usuario.getEquipe().getId());
                jsoGrupo.put("retorno", true);

                if (CollectionUtils.isNotEmpty(lista)) {

                    for (Object object : lista) {

                        Object[] ob = (Object[]) object;

                        if (!jsoGrupo.has("grupo_principal")
                                || ((JSONArray) jsoGrupo.get("grupo_principal")).isEmpty()) {

                            jsoGrupo.put("grupo_principal", retornarJsonArrayGrupoPrincipal(ob, 1));

                        } else {

                            boolean possuiEquipe = false;

                            int i = 0;

                            for (Object objArray : jsoGrupo.getJSONArray("grupo_principal")) {

                                JSONObject json = (JSONObject) objArray;

                                if (ob[6].toString().equals(json.get("id_grupo_principal"))) {
                                    possuiEquipe = true;
                                    break;
                                }
                                i++;

                            }

                            if (possuiEquipe) {

                                if (jsoGrupo.getJSONArray("grupo_principal").getJSONObject(i).has("grupos")) {

                                    popularGrupoPerguntas(jsoGrupo, ob, i);
                                    // jsoGrupo.getJSONArray("grupo_principal").getJSONObject(i).getJSONArray("grupos").put(retornarJsonObjectGrupoPrincipal(ob,jsoGrupo.getJSONArray("grupo_principal").getJSONObject(i).getJSONArray("grupos").length()+1));

                                } else {
                                    // popularGrupoPerguntas(jsoGrupo,ob,i);
                                    jsoGrupo.getJSONArray("grupo_principal").getJSONObject(i).put("grupos",
                                            retornarJsonArrayGrupo(ob, 1));

                                }

                            } else {

                                jsoGrupo.getJSONArray("grupo_principal").put(retornarJsonObjectGrupoPrincipal(ob,
                                        jsoGrupo.getJSONArray("grupo_principal").length() + 1));

                            }

                        }

                    }

                } else {

                    jsoGrupo.put("retorno", false);
                    jsoGrupo.put("menssagem", "Desculpe, não encontrei nenhuma relação de perguntas.");

                }
                // System.out.println(jsoGrupo.toString());

                return jsoGrupo.toString();

            } else {

                jsoGrupo.put("retorno", false);
                jsoGrupo.put("menssagem",
                        "Você não está " + (this.usuario.getSexo() == SexoEnum.FEMININO ? "associada " : "associado ")
                                + " a uma equipe, não há nenhuma pergunta para exibir. ");
                jsoGrupo.put("equipe", "");
                jsoGrupo.put("equipe_id", "");
                return jsoGrupo.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private void popularGrupoPerguntas(JSONObject jsoGrupo, Object[] ob, int indicePrincipal) {

        boolean possuiEquipe = false;
        int i = 0;

        for (Object objArray : jsoGrupo.getJSONArray("grupo_principal").getJSONObject(indicePrincipal)
                .getJSONArray("grupos")) {

            JSONObject json = (JSONObject) objArray;
            // System.out.println( json.get("id_grupo"));

            if (ob[0].toString().equals(json.get("id_grupo"))) {
                possuiEquipe = true;
                break;
            }
            i++;

        }

        if (possuiEquipe) {

            if (jsoGrupo.getJSONArray("grupo_principal").getJSONObject(indicePrincipal).getJSONArray("grupos")
                    .getJSONObject(i).has("perguntas")) {

                jsoGrupo.getJSONArray("grupo_principal").getJSONObject(indicePrincipal).getJSONArray("grupos")
                        .getJSONObject(i).getJSONArray("perguntas")
                        .put(retornarJsonObjectPergunta(ob,
                                jsoGrupo.getJSONArray("grupo_principal").getJSONObject(indicePrincipal)
                                        .getJSONArray("grupos").getJSONObject(i).getJSONArray("perguntas").length()
                                        + 1));

            } else {

                jsoGrupo.getJSONArray("grupo_principal").getJSONObject(indicePrincipal).getJSONArray("grupos")
                        .getJSONObject(i).put("perguntas", retornarJsonArrayPergunta(ob));

            }

        } else {

            jsoGrupo.getJSONArray("grupo_principal").getJSONObject(indicePrincipal).getJSONArray("grupos")
                    .put(retornarJsonObjectGrupo(ob, jsoGrupo.getJSONArray("grupo_principal")
                            .getJSONObject(indicePrincipal).getJSONArray("grupos").length() + 1));

        }

    }

    private JSONArray retornarJsonArrayGrupo(Object[] ob, int indice) {

        JSONArray arrayGrupos = new JSONArray();

        arrayGrupos.put(retornarJsonObjectGrupo(ob, indice));
        // System.out.println(jsonGrupo.toString());

        return arrayGrupos;

    }

    private JSONArray retornarJsonArrayGrupoPrincipal(Object[] ob, int indice) {

        JSONArray arrayGrupos = new JSONArray();

        arrayGrupos.put(retornarJsonObjectGrupoPrincipal(ob, indice));
        // System.out.println(jsonGrupo.toString());

        return arrayGrupos;

    }

    private JSONObject retornarJsonObjectGrupoPrincipal(Object[] ob, int indice) {

        JSONObject jsonGrupo = new JSONObject();

        jsonGrupo.put("id_grupo_principal", ob[6].toString());
        jsonGrupo.put("descricao", ob[7].toString());
        jsonGrupo.put("indice", indice);
        jsonGrupo.put("grupos", retornarJsonArrayGrupo(ob, 1));

        return jsonGrupo;
    }

    private JSONObject retornarJsonObjectGrupo(Object[] ob, int indice) {

        JSONObject jsonGrupo = new JSONObject();
        jsonGrupo.put("id_grupo", ob[0].toString());
        jsonGrupo.put("descricao", ob[1].toString());

        jsonGrupo.put("indice", indice);

        jsonGrupo.put("perguntas", retornarJsonArrayPergunta(ob));

        return jsonGrupo;
    }

    private JSONArray retornarJsonArrayPergunta(Object[] ob) {

        JSONArray arrayPerguntas = new JSONArray();
        arrayPerguntas.put(retornarJsonObjectPergunta(ob, 1));
        return arrayPerguntas;
    }

    private JSONObject retornarJsonObjectPergunta(Object[] ob, int indice) {

        JSONObject jsonPerguntas = new JSONObject();
        jsonPerguntas.put("pergunta", ob[2].toString());
        jsonPerguntas.put("resposta", ob[3].toString());
        jsonPerguntas.put("equipe", ob[4].toString());
        jsonPerguntas.put("id_equipe", ob[5].toString());
        jsonPerguntas.put("indice", indice);
        return jsonPerguntas;
    }

    public static void main(String[] args) {

        //BotPoint b = new BotPoint();

        ///b.prepararPerguntas("Olá");

        String nome = "PEDRO MOREIRA VIEIRA DE FARIA";

        String nomeRetorno = "";

        if (StringUtils.isNotBlank(nome)) {

            String[] nomeArray = nome.split(" ");

            if (nomeArray.length > 1) {

                if (nomeArray[0].length() >= 3) {

                    nomeRetorno = StringUtils.capitalize(nomeArray[0].toLowerCase());

                } else {

                    nomeRetorno = StringUtils.capitalize(nome);
                }

            } else {

                nomeRetorno = StringUtils.capitalize(nome);

            }

        }

        System.out.println("Olá " + nomeRetorno);

    }

}
