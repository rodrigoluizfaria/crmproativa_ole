
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
	while (new Date().getTime() < now + sleepDuration) { /* do nothing */ }

}

function iniciarIntegraVonix(servidor, login) {
	iniciarIntegraVonix(servidor, login, null);

}

function logarRamal(ramal) {

	if (ramal) {
		console.log(ramal);
		vonix.doLogin(ramal);
	}
}

/*MODO MANUAL*/
function iniciarIntegraVonixAtivo(servidor, login, isAutomatico) {

	var automatico = isAutomatico == true;

	vonix = new Vonix(servidor, login);

	console.log("Iniciando script Vonix.... " + automatico + " - ");

	agentOnline = false;


	vonix.onDial(function(call_id, date, queue, from, to, callfilename, contact_name, action_id) {

		enviarMsgGrowl('Discando para:  ' + to, 'info');

	});

	vonix.onDialAnswer(function(call_id, date) {


		if (transfer) {

			idMasterCentral = call_id;

		} else {

			idMasterClient = call_id;
			idMasterCentral = null;

		}
	});

	vonix.onDialFailure(function(call_id, date, cause_id, cause_description) {

		//	console.log("(DialFailure) Dados: call_id: " + call_id + " date: " + date + " cause: " + cause_id + " cause_description: " + cause_description + "\n");

		realizarPausa();
		vonix.doPause(9);

		if (automatico) {
			realizarPausa();
			vonix.doPause(9);
		}

	});

	vonix.onReceive(function(call_id, date, queue, from, to, callfilename, contact_name, action_id, additional_data) {

		if (isHard) {

			onEnviarAtendimento([{

				name: 'idAtendimento',
				value: action_id,

			}, {
				name: 'contact_name',
				value: contact_name

			}, {
				name: 'queue',
				value: queue

				,
			}, {
				name: 'call_id',
				value: call_id
			}, {

				name: 'callfilename',
				value: callfilename

			}, {

				name: 'to',
				value: to

			}, {

				name: 'from',
				value: from

			}, {

				name: 'date',
				value: date

			}, {

				name: 'additional_data',
				value: additional_data

			}]);
		}

		if (isAutomatico) {
			vonix.doStatus();
		}

	});

	vonix.onReceiveAnswer(function(call_id, date, waiting_seconds) {

		console.log("(ReceiveAnswer) Dados: call_id: " + call_id + " date: " + date + " waiting_seconds: " + waiting_seconds + "\n");


	});

	vonix.onReceiveFailure(function(call_id, date, waiting_seconds) {

		console.log("(ReceiveFailure) Dados: call_id: " + call_id + " date: " + date + " waiting_seconds: " + waiting_seconds + "\n");


	});

	vonix.onHangUp(function(call_id, date, cause_id, cause_description) {

		console.log("(HangUp) Dados: call_id: " + call_id + " date: " + date + " cause: " + cause_id + " OK - cause_description: " + cause_description + "\n");

		if (automatico) {
			//vonix.doStatus();
			realizarPausa();
			vonix.doPause(9);
		}
		realizarPausa();
		vonix.doPause(9);



	});

	vonix.onLogin(function(date, location) {

		console.log("(Login) Dados: date: " + date + " location: " + location + "\n");

		vonix.doStatus();

		if (automatico) {

			realizarPausa();
			vonix.doPause(9);

		}

	});

	vonix.onLogoff(function(date, location, duration) {

		console.log("(Logoff) Dados: date: " + date + " location: " + location + " duration: " + duration + "\n");
		vonix.doStatus();


	});

	vonix.onPause(function(date, reason) {

		//	console.log("(Pause) Dados: date: " + date + " reason: " + reason + "\n>");

		if (automatico) {
			vonix.doStatus();
		}
	});

	vonix.onUnpause(function(date, reason, duration) {

		console.log("(Unpause) Dados: date: " + date + " reason: " + reason + " duration: " + duration + "\n>");

		if (automatico) {
			//	vonix.doStatus();
		}

	});


	vonix.onStatus(function(status, location, since, status_data) {

		if (status == 'OFFLINE') {
			enviarMsgGrowl('Agente Deslogado', 'warn');
			agentOnline = false;

		} else if (status == 'ONLINE') {

			agentOnline = true;
		}



	});

	vonix.onError(function(date, action_id, description) {

		console.log("(Error) Dados: date: " + date + " action_id: " + action_id + " description: " + description + "\n");

	});

	vonix.onConnect(function(date) {

		//console.log("(Connect) Dados: date: " + date + "\n")

		if (automatico) {

			realizarPausa();
			vonix.doPause(9);
			vonix.doStatus();
		}

	});


}
/* FIM MODO MANUAL*/



//COM PAUSA
function iniciarIntegraVonixPausa(servidor, login, pausa) {


	agentOnline = false;

	vonix = new Vonix(servidor, login);

	console.log("Iniciando script Vonix.... PAUSA: " + pausa);

	vonix.onLogoff(function(date, location, duration) {

		console.log("(Logoff) Dados: date: " + date + " location: " + location + " duration: " + duration + "\n");

	});

	vonix.onPause(function(date, reason) {

		console.log("(Pause) Dados: date: " + date + " reason: " + reason + "\n>");


	});

	vonix.onUnpause(function(date, reason, duration) {

		console.log("(Unpause) Dados: date: " + date + " reason: " + reason + " duration: " + duration + "\n>");



	});


	vonix.onConnect(function(date) {

		console.log("(Connect) Dados: date: " + date + "\n");

		vonix.doUnpause();

		sleepFor(1000);

		if (pausa) {

			vonix.doPause(pausa);
		}


	});

}

//FIM COM PAUSA;

function retornarStatusVonix(status) {

	switch (status) {

		case 'ONLINE':
			return "Agente logado e disponível";
		case 'OFFLINE':
			return "Agente deslogado";
		case 'PAUSED':
			return "Em Pausa";
		case 'UNAVAILABLE':
			return "Agente indisponível";
		case 'RINGING':
			return "Tocando";
		case 'ONTHEPHONE':
			return "Agente ao telefone";
		default:
			return "";

	}

}

function deslogar() {
	console.log("Deslogando agent.")
	vonix.doLogoff();
}

function iniciarDlgStatusVonix() {

	console.log('OK');

	PF('dlgVonix').show();

}


function realizarPausa() {

	//realizarPausaVonix();
}

function desPausaVonix() {
	vonix.doUnpause();
}




function vonixDial(numero, nome, id) {

	console.log("Discando...")
	vonix.doUnpause();

	sleepFor(1000);

	vonix.doStatus();



	vonix.doDial(numero, nome, '', 1, id);


}

function vonixDialBack(numero, nome, id) {

	console.log("Discando... : " + nome)

	vonix.doUnpause();

	sleepFor(1000);

	vonix.doStatus();

	vonix.doDial(numero, nome, '', 1, id);


}

function vonixDialMaster(numero, nome, id) {

	transfer = true;
	vonix.doDial(numero, nome, '', 1, id)

}




function onStateVonix(fila) {

	vonix.doStatus();

}



