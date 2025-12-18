function enviarMsgGrowl(msg, severity) {


    PF('grl2').renderMessage({
        "summary": msg,
        "detail": "",
        "severity": severity
    });
}

var qualifyManual;

function iniciarArgus(token,ramal,url) {

    console.log('Iniciando ...')
    console.log(token)
    console.log(ramal)
    var outputStatus = document.getElementById("lblStatus");
    var outputStatusPreditivo = document.getElementById("lblStatusPreditivo");


    fetch("http://localhost:8080/crmproativa/api/discador/argusFicarDisponivel", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            ramal: ramal,
            tokem: token,
            url: url

        })
    }).then(response => {
        if (!response.ok) {
            throw new Error("Erro HTTP: " + response.status);
        }
        return response.json(); // Certifique-se de que isso está presente!
    })
        .then(data => {
            console.log("Resposta completa da API:", data);

            if (!data || typeof data !== "object") {
                console.error("Resposta inválida");
                return;
            }

            if (data.codStatus === 0) {
                console.warn("Erro da API:", data.descStatus);
                enviarMsgGrowl(data.descStatus,"error")
                return;
            }

            // Sucesso
            outputStatusPreditivo.innerHTML.descStatus;
            console.log("Dados válidos:", data);
            enviarMsgGrowl(data.descStatus,"info");
            $('.cardStatus').css("background-color", "#28a745");
            outputStatusPreditivo.innerHTML = "Conectado";
        })
        .catch(error => {
            console.error("Erro ao consumir API:", error);
        });



}