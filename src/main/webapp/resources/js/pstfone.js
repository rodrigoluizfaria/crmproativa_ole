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

function iniciarPst(servidor, login) {

    conectar(servidor, login);

}



function tocarNotificacao() {

    const audio = document.getElementById('audioNotificacao');
    if (audio) {
        audio.play().catch(e => console.log('Interação necessária para áudio'));
    }
}

function alterarTag(severity, texto) {
    // pega o elemento pelo id
    var tag = document.getElementById("formAcc:statusTag");

    if (tag) {
        // remove classes antigas de severity
        console.log("ALTERANDO TAG " + texto)
        tag.classList.remove("ui-tag-success", "ui-tag-info", "ui-tag-warning", "ui-tag-danger");

        // adiciona a nova classe
        tag.classList.add("p-tag-" + severity);

        // altera o texto interno
        tag.textContent = texto;
    }
}


function conectar(servidor,login) {

    console.log('LOGANDO:: '+login+" - "+servidor)
    let ramal = login;
    console.log(ramal + ' - RAMAL')

    if (!ramal || !servidor) return;

    const wsUrl = "ws://"+servidor+"/profone/ws/cpf?ramal=" + ramal;
    const socket = new WebSocket(wsUrl);

    socket.onopen = () => {
        alterarTag("success", "ONLINE");
    };


    socket.onmessage = (event) => {

        if (event.data !== "ping") {
            enviarCpf([{name: 'cpf', value: event.data}]);
            tocarNotificacao();
        } else {
            socket.send("pong");
        }
    };


    socket.onclose = () => {
        alterarTag("danger", "OFFLINE");
        setTimeout(conectar, 3000); // tenta reconectar
    };


    socket.onerror = () => {
        alterarTag("warning", "ERRO");
    };

    socket.onclose = () => setTimeout(conectar, 3000);
}


