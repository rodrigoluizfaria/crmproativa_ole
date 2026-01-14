let loginExterno = '';
let pabxExterno = '';
let vonixExterno;

function enviarMsgGrowl(msg, severity) {


    PF('grl2').renderMessage({
        "summary": msg,
        "detail": "",
        "severity": severity
    });
}

function sleepFor(sleepDuration) {

    var now = new Date().getTime();
    while (new Date().getTime() < now + sleepDuration) { /* do nothing */
    }

}

function tocarNotificacao() {

    const audio = document.getElementById('audioNotificacao');
    if (audio) {
        audio.play().catch(e => console.log('Interação necessária para áudio'));
    }
}

function alterarTag(severity, texto, detalhe) {

    var tag = document.getElementById("statusTag");
    var msg = document.getElementById("statusMsg");

    var iconMap = {
        'success': 'pi-check-circle', // Livre
        'danger':  'pi-phone',        // Ocupado/Offline
        'warning': 'pi-clock',        // Em espera/Pausa
        'info':    'pi-spin pi-spinner' // Conectando/Chamando
    };

    console.log(tag)

    if (tag) {

        tag.classList.remove('ui-tag-success', 'ui-tag-danger', 'ui-tag-warning', 'ui-tag-info');
        tag.classList.add('ui-tag-' + severity);


        var spanValue = tag.querySelector('.ui-tag-value');
        if (spanValue) {
            spanValue.innerText = texto;
        }


        var spanIcon = tag.querySelector('.ui-tag-icon');
        if (spanIcon) {

            var classes = spanIcon.className.split(" ");
            for (var i = 0; i < classes.length; i++) {
                if (classes[i].startsWith('pi-') && classes[i] !== 'pi') {
                    spanIcon.classList.remove(classes[i]);
                }
            }

            var novoIcone = iconMap[severity] || 'pi-info-circle';

            novoIcone.split(" ").forEach(cls => spanIcon.classList.add(cls));
        }
    }

    // Atualiza a mensagem
    if (msg) {
        msg.textContent = detalhe ? detalhe : "";
        // Reseta as classes de cor do texto
        msg.className = "text-sm mt-1 block"; // Classes base

        if (severity === 'danger') {
            msg.classList.add('text-red-500', 'font-bold');
        } else if (severity === 'success') {
            msg.classList.add('text-green-500');
        } else {
            msg.classList.add('text-500');
        }
    }
}

function iniciarPst(servidor, login) {

    conectar(servidor, login);

}

function conectar(servidor,login) {

    // Pega o ramal injetado pelo JSF
    let ramal = login;
    let host = servidor;

    if (!ramal || !host) {
        console.warn('Ramal ou servidor não definido. WebSocket abortado.');
        alterarTag('danger','SEM RAMAL','Ramal não definido.')
        return;
    }

    console.log('--- Iniciando WebSocket para Ramal: ' + ramal + ' ---');

    // URL Dinâmica (Funciona em Localhost, IP de Rede e HTTPS)
    const protocolo = window.location.protocol === 'https:' ? 'wss:' : 'ws:';
    const path =  "/profone"
    const wsUrl = protocolo+"//"+host+path+"/ws/cpf?ramal="+ramal;

    console.log(wsUrl+" --- wsUrl")
    // Monta: ws://192.168.0.10:8080/profone/ws/cpf?ramal=1001


    console.log("Conectando em: " + wsUrl);

    const socket = new WebSocket(wsUrl);

    socket.onopen = () => {
        console.log("WebSocket Conectado!");
        // Não alteramos a TAG aqui, esperamos o Backend dizer o status real
    };

    socket.onmessage = (event) => {
        // Ignora Heartbeat
        if (event.data === "pong" || event.data === "ping") return;

        try {

            const data = JSON.parse(event.data);

            // --- CENÁRIO 1: ATUALIZAÇÃO DE STATUS (Livre, Ocupado, Offline) ---
            if (data.type === "STATUS") {

                console.log("Status Recebido:", data.state);
                console.log(data.message+' -- MSG')
                if (data.connected === false) {
                    alterarTag("danger", "OFFLINE", data.message);
                }
                else {
                    switch (data.state) {

                        case "EM_LIGACAO":
                            alterarTag("warning", "EM LIGAÇÃO", "Em atendimento");
                            break;
                        case "CHAMANDO":
                            alterarTag("info", "TOCANDO...", "Chamada recebida");
                            tocarNotificacao(); // Opcional: Toca som quando chama
                            break;
                        case "OCUPADO":
                            alterarTag("danger", "OCUPADO", "Rejeitou ou Ocupado");
                            break;
                        case "LIVRE":
                            alterarTag("success", "LIVRE", "Pronto para atender");
                            break;
                        case "EM_ESPERA":
                            alterarTag("warning", "EM ESPERA", "Chamada em hold");
                            break;
                        default:
                            alterarTag("info", data.state, data.message);
                    }
                }
            }

            // DADOS DA CHAMADA (CPF, Áudio, Número) ---
            else if (data.type === "CHAMADA_ATIVA") {
                console.log(">>> DADOS DA CHAMADA RECEBIDOS ");
                console.log("CPF:", data.cpf);
                console.log("Audio:", data.audioId);
                console.log("Numero:", data.numero);
                console.log("Opcao:", data.opcao);

                // Chama o método no Bean via p:remoteCommand
                // Certifique-se que o remoteCommand na página tem esse nome
                if(window.receberDadosChamada) {

                    receberDadosChamada([
                        {name: 'cpf', value: data.cpf},
                        {name: 'audioId', value: data.audioId},
                        {name: 'numero', value: data.numero},
                        {name: 'opcao', value: data.opcao}
                    ]);

                } else {
                    console.error("ERRO: p:remoteCommand 'receberDadosChamada' não encontrado na página.");
                }
            }

        } catch (e) {
            console.error("Erro ao processar mensagem JSON:", e, event.data);
        }
    };

    socket.onclose = (event) => {
        if (event.wasClean) {
            console.log(`Conexão fechada limpa.`);
        } else {
            console.warn('Queda de conexão (Server morreu ou rede caiu).');
            alterarTag("danger", "DESCONECTADO", "Tentando reconectar...");
        }
        // Tenta reconectar em 5 segundos
        setTimeout(conectar, 5000);
    };

    socket.onerror = (err) => {
        console.error("Erro no WebSocket:", err);
        socket.close();
    };
}








