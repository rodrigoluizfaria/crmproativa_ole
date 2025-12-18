import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.socket.client.IO;
import io.socket.client.Manager;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import io.socket.engineio.client.transports.WebSocket;
import org.apache.commons.collections4.CollectionUtils;

import com.proativaservicos.model.Consistencia;
import org.json.JSONObject;

public class TesteApi3C {


    public static boolean validarConsistencias(List<Consistencia> listaConsistencias) {

        if (CollectionUtils.isEmpty(listaConsistencias))
            return false;


        return listaConsistencias.stream().anyMatch(c -> (c.getTratada() != null && c.getTratada()));


    }

    public static void main(String[] args) throws IOException {

        URI uri = URI.create("https://socket.3c.fluxoti.com");

        Map<String, String> map = new HashMap<>();
        map.put("transports", "['websocket']");
        map.put("token", "tW5D1VVrfbYoLqlJnc48Dg5QfDFOliBJkFc0yLndTW5NMM2RlTw1dHEbl8Sd");
        IO.Options options = IO.Options.builder().setTransports(new String[]{WebSocket.NAME}).setQuery("token=tW5D1VVrfbYoLqlJnc48Dg5QfDFOliBJkFc0yLndTW5NMM2RlTw1dHEbl8Sd").build();
        Socket socket = IO.socket(uri, options);
        System.out.println("LISTEM...");

        socket.io().on(Manager.EVENT_TRANSPORT, new Emitter.Listener() {
            @Override
            public void call(Object... objects) {
                System.out.println("teste " + objects[0]);
            }
        });
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... objects) {
                System.out.println("Conectando...");
            }
        });

        socket.on("call-history-was-created", objects -> {
            System.out.println("HISTORICO...");
            // System.out.println(objects[0].toString());
            JSONObject jsonObjectRoot = new JSONObject(objects[0].toString());
            JSONObject jsonObject = jsonObjectRoot.getJSONObject("callHistory");
            String number = jsonObject.getString("number");
            String callId = jsonObject.getString("_id");
            String callMode = jsonObject.getString("call_mode");
            String identifi = jsonObject.getJSONObject("mailing_data").getString("identifier");
            int status = jsonObject.getInt("status");
            String sip = jsonObjectRoot.getJSONObject("hangupCause").getString("sip");
            System.out.println("ID: "+identifi+" | callID: "+callId+" | call mode: "+callMode+" | number: "+number+" | Status: "+status+" | SIP: "+sip);
        });
        socket.on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
            @Override
            public void call(Object... objects) {
                System.out.println("ERO: " + objects[0]);
            }

        });
        System.out.println("Conectando...");
        socket.open();



    }


}
