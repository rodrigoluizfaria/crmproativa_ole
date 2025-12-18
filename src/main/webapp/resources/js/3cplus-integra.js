function enviarMsgGrowl(msg, severity) {


    PF('grl2').renderMessage({
        "summary": msg,
        "detail": "",
        "severity": severity
    });
}

var qualifyManual;

function iniciar3c(token) {

    var ipServer = "https://socket.3c.fluxoti.com";
    console.log('Iniciando ...')
    console.log(token)
    var outputStatus = document.getElementById("lblStatus");
    var outputStatusPreditivo = document.getElementById("lblStatusPreditivo");


    const socket = io(ipServer, {
        transports: ['websocket'],
        query: {token: token}
    })


    // OCIOSO
    socket.on("agent-is-idle", function (event) {

        console.log('OCIOSO....');
        console.log(event);
        $('.cardStatus').css("background-color", "rgb(186, 190, 195)");
        outputStatusPreditivo.innerHTML = "Ocioso";

    })

    //MODO MANUAL
    socket.on("agent-entered-manual", function (event) {
        console.log("Manual: ");
        console.log(event);
    })

    //SAIU MODO MANUAL
    socket.on("agent-left-manual-mode", function (event) {
        console.log("Agente saiu do modo manual: ");
        console.log(event);
    })


    //Agente entrou em modo de ligação manual em TPA
    socket.on("agent-entered-manual-acw", function (event) {
        console.log("Manual TPA: ");
        console.log(event);
    })

    //SAIU MODO MANUAL TPA
    socket.on("agent-left-manual-acw", function (event) {
        console.log("Agente saiu do modo manual TPA: ");
        console.log(event);
    })
    //EFETUANDO LIGAÇÃO
    socket.on("call-was-created", function (event) {
        console.log("Chamada Criada: ");
        console.log(event);
    })

    //Chamada Atendida
    socket.on("call-was-answered", function (event) {
        console.log("Chamada atendida: ");
        console.log(event);
    })

    //Chamada Abandonada
    socket.on("call-was-abandoned", function (event) {
        console.log("Chamada Abandonada: " + Date.now());
        console.log(event);
    })

    //Chamada não atendida
    socket.on("call-was-not-answered", function (event) {
        console.log("Chamada não atendida: " + Date.now());
        console.log(event);
    })

    //Chamada finalizada
    socket.on("call-was-finished", function (event) {
        console.log("Chamada finalizada: " + Date.now());
        console.log(event);
    })

    //Chamada Desligada
    socket.on("call-was-ended", function (event) {
        console.log("Chamada desligada: " + Date.now());
        console.log(event);
    })

    //Chamada com falha
    socket.on("call-was-failed", function (event) {
        console.log("Chamada com falha: " + Date.now());
        console.log(event);
    })

    //Histórico da chamada foi criado
    socket.on("call-history-was-created", function (event) {
        console.log("Histórico da chamada foi criado: " + Date.now());
        console.log(event);
    })


    //Agente está em TPA
    socket.on("agent-in-acw", function (event) {
        console.log("Agente está em TPA " );
        console.log(JSON.stringify(event));
    })


    //Agente saiu
    socket.on("agent-was-logged-out", function (event) {
        console.log("Agente saiu: " );
        console.log(JSON.stringify(event));
    })


    // Tratando erros:
    socket.on('error', function (err) {
        console.log(err)
        $('.cardStatus').css("background-color", "#e83b31");
        outputStatusPreditivo.innerHTML = "Ocorreu um erro";
    })

    //Chamada conectada ao agente
    socket.on("call-was-connected", function (event) {

        var jsonObj = event;
        var contact_name = null;
        var identifier = '';
        var callId = null;
        var audio_gravacao = null;
        var callDate = null;
        var to = null;
        var from = null;
        var fila = null;
        var qualify = null;

        console.log("Chamada conectada ao agente: ");
        console.log(JSON.stringify(jsonObj));

        if (jsonObj.call) {

            identifier = jsonObj.call.identifier;
            callId = jsonObj.call.telephony_id;
            audio_gravacao = jsonObj.call.sid;
            from = jsonObj.call.phone.substring(2);
            fila = jsonObj.call.campaign_id;
            callDate = new Date(jsonObj.call.answered_time * 1000);

        }

        if (jsonObj.mailing) {

            to = jsonObj.mailing.list_id;
            contact_name = jsonObj.mailing.data.Nome;

        }

        if (jsonObj.qualification && jsonObj.qualification.qualifications) {

            qualify = JSON.stringify(jsonObj.qualification);

        }

        console.log("Cliente: "+contact_name + " | ID: " + identifier + " | Callid:" + callId + " | DE:" + to + " | Para:" + from + " | " + fila + " | " + callDate)

        onEnviarAtendimento3c(
            [
                {

                    name: 'idAtendimento',
                    value: identifier,

                }, {

                    name: 'contact_name',
                    value: contact_name

                }, {

                    name: 'queue',
                    value: fila

                }, {

                    name: 'call_id',
                    value: callId

                }, {

                    name: 'audio_gravacao',
                    value: audio_gravacao

                }, {

                    name: 'qualify',
                    value: qualify

                }, {

                    name: 'to',
                    value: to

                }, {

                    name: 'from',
                    value: from

                }, {

                    name: 'date',
                    value: callDate

                }, {

                    name: 'additional_data',
                    value: callDate

                }
            ]);


    })

}

/*Atendimento Manual*/

function iniciar3cManual(token) {

    var ipServer = "https://socket.3c.fluxoti.com";
    console.log('Iniciando Modo Manual...')
    console.log("Token: "+ token)
    var outputStatus = document.getElementById("lblStatus");
    var outputStatusPreditivo = document.getElementById("lblStatusPreditivo");
    qualifyManual = null;

    const socket = io(ipServer, {
        transports: ['websocket'],
        query: {token: token}
    })

    // OCIOSO
    socket.on("agent-is-idle", function (event) {

        console.log('OCIOSO....');
        console.log(event);

    })

    //MODO MANUAL
    socket.on("agent-entered-manual", function (event) {
        console.log("Modo Manual: ");
        console.log(event);
    })

    //SAIU MODO MANUAL
    socket.on("agent-left-manual-mode", function (event) {
        console.log("Agente saiu do modo manual: ");
        console.log(event);
    })


    //Agente entrou em modo de ligação manual em TPA
    socket.on("agent-entered-manual-acw", function (event) {
        console.log("Manual TPA: ");
        console.log(event);
    })

    //SAIU MODO MANUAL TPA
    socket.on("agent-left-manual-acw", function (event) {
        console.log("Agente saiu do modo manual TPA: ");
        console.log(event);
    })

    //EFETUANDO LIGAÇÃO
    socket.on("call-was-created", function (event) {
        console.log("Chamada Criada: ");
        console.log(event);
    })

    //Chamada Atendida
    socket.on("call-was-answered", function (event) {
        console.log("Chamada atendida: ");
        console.log(event);
    })

    //Chamada Abandonada
    socket.on("call-was-abandoned", function (event) {
        console.log("Chamada Abandonada: " + Date.now());
        console.log(event);
    })

    //Chamada não atendida
    socket.on("call-was-not-answered", function (event) {
        console.log("Chamada não atendida: " + Date.now());
        console.log(event);
    })


    //Chamada finalizada
    socket.on("call-was-finished", function (event) {
        console.log("----------------------------------------------------\m ");
        console.log("Chamada finalizada: " + Date.now());

        var jsonObj = event;
        var callId = null;
        var audio_gravacao = null;
        var to = null;
        var fila = null;
        var qualify = null;

        console.log(JSON.stringify(jsonObj))


        if (jsonObj.call) {

            fila = jsonObj.call.campaign_id;
            callId = jsonObj.call.telephony_id;
            audio_gravacao = jsonObj.call.sid;
            to = jsonObj.call.phone.substring(2);

        }

        if (jsonObj.qualification && jsonObj.qualification.qualifications) {

            qualify = JSON.stringify(jsonObj.qualification);

        }
        if(qualifyManual){
            qualify = qualifyManual;
        }
        console.log("QUALIFY: "+qualify)
        onSalvarAtendimentoAudio3c(
            [
              {

                name: 'fila',
                value: fila

            }, {

                name: 'call_id',
                value: callId

            }, {

                name: 'audio_gravacao',
                value: audio_gravacao

            },{

                name: 'qualify',
                value: qualify

            }, {

                name: 'to',
                value: to

            }
            ]);
        console.log("----------------------------------------------------\m ");

    })

    //Chamada Desligada
    socket.on("call-was-ended", function (event) {
        console.log("Chamada desligada: " + Date.now());
        console.log(event);
    })

    //Chamada com falha
    socket.on("call-was-failed", function (event) {
        console.log("Chamada com falha: " + Date.now());
        console.log(event);
    })

    //Histórico da chamada foi criado
    socket.on("call-history-was-created", function (event) {
        console.log("Histórico da chamada foi criado: " + Date.now());
        console.log(event);
    })

    //Agente está em TPA
    socket.on("agent-in-acw", function (event) {
        console.log("Agente está em TPA " );
        console.log(JSON.stringify(event));
    })

    //Agente saiu
    socket.on("agent-was-logged-out", function (event) {
        console.log("Agente saiu: " );
        console.log(JSON.stringify(event));
    })


    // Tratando erros:
    socket.on('error', function (err) {
        console.log(err)

    })

    //Chamada conectada ao agente
    socket.on("call-was-connected", function (event) {

        var jsonObj = event;
        var contact_name = null;
        var identifier = '';
        var callId = null;
        var audio_gravacao = null;
        var callDate = null;
        var to = null;
        var from = null;
        var fila = null;
        var qualify = null;
        console.log("----------------------------------------------------\m ");
        console.log("Chamada conectada ao agente: ");
        /*console.log(JSON.stringify(jsonObj));*/

        if (jsonObj.call) {

            identifier = jsonObj.call.identifier;
            callId = jsonObj.call.telephony_id;
            audio_gravacao = jsonObj.call.sid;
            from = jsonObj.call.phone.substring(2);
            fila = jsonObj.call.campaign_id;
            callDate = new Date(jsonObj.call.answered_time * 1000);

        }

        if (jsonObj.mailing) {

            to = jsonObj.mailing.list_id;
            contact_name = jsonObj.mailing.data.Nome;
            console.log('NOME: ' + contact_name+" | TO: "+to)

        }

        if (jsonObj.qualification && jsonObj.qualification.qualifications) {

            qualify = JSON.stringify(jsonObj.qualification);
            qualifyManual = JSON.stringify(jsonObj.qualification);

        }

        console.log("QUALIFY: "+qualify)
        console.log('\n'+contact_name + " | " + identifier + " | " + callId + " | " + to + " | " + from + " | " + fila + " | " + callDate)

       /* onEnviarAtendimento3c(
            [
                {

                    name: 'idAtendimento',
                    value: identifier,

                }, {

                name: 'contact_name',
                value: contact_name

            }, {

                name: 'queue',
                value: fila

            }, {

                name: 'call_id',
                value: callId

            }, {

                name: 'audio_gravacao',
                value: audio_gravacao

            }, {

                name: 'qualify',
                value: qualify

            }, {

                name: 'to',
                value: to

            }, {

                name: 'from',
                value: from

            }, {

                name: 'date',
                value: callDate

            }, {

                name: 'additional_data',
                value: callDate

            }
            ]);
*/
        console.log("----------------------------------------------------\m ");
    })



}

function testeEnvio(){

    onEnviarAtendimento3c(
        [
            {

                name: 'idAtendimento',
                value: 13702857,

            }, {

            name: 'contact_name',
            value: 'JOSE LUIZ'

        }, {

            name: 'queue',
            value: 'TESTE'

        }, {

            name: 'call_id',
            value: '10580589'

        }, {

            name: 'audio_gravacao',
            value: '10580589'

        }, {

            name: 'qualify',
            value: 'ATENDIDA'

        }, {

            name: 'to',
            value: '21999631311'

        }, {

            name: 'from',
            value: '31996124295'

        }, {

            name: 'date',
            value: 'callDate'

        }, {

            name: 'additional_data',
            value: 'callDate'

        }
        ]);
}