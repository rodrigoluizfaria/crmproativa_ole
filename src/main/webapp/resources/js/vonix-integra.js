
let loginExterno ='';
let pabxExterno = '';
let vonixExterno;

function enviarMsgGrowl(msg, severity) {


	PF('grl2').renderMessage({
		"summary": msg,
		"detail": "",
		"severity": severity
	});
}

function sleepFor( sleepDuration ){
	
  var now = new Date().getTime();
  while(new Date().getTime() < now + sleepDuration){ /* do nothing */ } 

}

function iniciarIntegraVonix(servidor, login) {
	iniciarIntegraVonix(servidor,login,null);

}
//PREDITIVO....
function iniciarIntegraVonix(servidor, login,ramalAux) {

	output = document.getElementById("areaTxt");
	outputStatus = document.getElementById("lblStatus");
	outputStatusPreditivo = document.getElementById("lblStatusPreditivo");
	
	outputRamal = document.getElementById("lblRamal");
	outputRamalPreditivo = document.getElementById("lblRamalPreditivo");
	
	agentOnline = false;
	ramal = ramalAux;
	vonix = new Vonix(servidor, login);
	vonixExterno = vonix;
	
	console.log("Iniciando script Vonix....")

	vonix.onDial(function(call_id, date, queue, from, to, callfilename, contact_name, action_id) {

		console.log("(Dial) Dados: call_id: " + call_id + " date: " + date + " queue: " + queue + " from: " + from + " to: " + to + " callfilename: " + callfilename + " contact_name: " + contact_name + " action_id: " + action_id + "\n");
		output.innerHTML += "(Dial) Dados: call_id: " + call_id + " date: " + date + " queue: " + queue + " from: " + from + " to: " + to + " callfilename: " + callfilename + " contact_name: " + contact_name + " action_id: " + action_id + "\n";

		enviarMsgGrowl('Discando ' + to, 'info')

		//vonix.doStatus();
	});

	vonix.onDialAnswer(function(call_id, date) {

		console.log("(DialAnswer) Dados: call_id: " + call_id + " date: " + date + "\n");
		output.innerHTML += "(DialAnswer) Dados: call_id: " + call_id + " date: " + date + "\n";
		//	vonix.doStatus();
	});

	vonix.onDialFailure(function(call_id, date, cause_id, cause_description) {

		console.log("(DialFailure) Dados: call_id: " + call_id + " date: " + date + " cause: " + cause_id + " cause_description: " + cause_description + "\n");
		output.innerHTML += "(DialFailure) Dados: call_id: " + call_id + " date: " + date + " cause: " + cause_id + " cause_description: " + cause_description + "\n";
		//vonix.doStatus();

	});

	vonix.onReceive(function(call_id, date, queue, from, to, callfilename, contact_name, action_id, additional_data) {

		output.innerHTML += "(Receive) Dados: call_id: " + call_id + " date: " + date + " queue: " + queue + " from: " + from + " to: " + to + " callfilename: " + callfilename + " contact_name: " + contact_name + " action_id: " + action_id + " additional_data: " + JSON.stringify(additional_data) + "\n";

		console.log(JSON.stringify(additional_data));

		onEnviarAtendimento(
			[
				{
		
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
		
				}
			]);

		//vonix.doStatus();

	});

	vonix.onReceiveAnswer(function(call_id, date, waiting_seconds) {

		output.innerHTML += "(ReceiveAnswer) Dados: call_id: " + call_id + " date: " + date + " waiting_seconds: " + waiting_seconds + "\n";
		//vonix.doStatus();

	});

	vonix.onReceiveFailure(function(call_id, date, waiting_seconds) {

		output.innerHTML += "(ReceiveFailure) Dados: call_id: " + call_id + " date: " + date + " waiting_seconds: " + waiting_seconds + "\n";
		//vonix.doStatus();

	});

	vonix.onHangUp(function(call_id, date, cause_id, cause_description) {

		output.innerHTML += "(HangUp) Dados: call_id: " + call_id + " date: " + date + " cause: " + cause_id + " cause_description: " + cause_description + "\n";
		vonix.doStatus();
		realizarPausa();
		vonix.doPause(9);
	});

	vonix.onLogin(function(date, location) {

		output.innerHTML += "(Login) Dados: date: " + date + " location: " + location + "\n";
	
		vonix.doUnpause();
	
		ramal = location;
		
		outputRamal.innerHTML = location;
			
		if (location) {

			onEnviarRamal(
				[
					{

						name: 'location',
						value: location

					}

				]);
		}
		
		sleepFor(1000);
		vonix.doStatus();

	});

	vonix.onLogoff(function(date, location, duration) {

		output.innerHTML += "(Logoff) Dados: date: " + date + " location: " + location + " duration: " + duration + "\n";
		vonix.doStatus();
	});

	vonix.onPause(function(date, reason) {

		output.innerHTML += "(Pause) Dados: date: " + date + " reason: " + reason + "\n>";
		vonix.doStatus();

	});

	vonix.onUnpause(function(date, reason, duration) {

		output.innerHTML += "(Unpause) Dados: date: " + date + " reason: " + reason + " duration: " + duration + "\n>";
		vonix.doStatus();


	});

	vonix.onStatus(function(status, location, since, status_data) {

		console.log("(Status) Dados: Status: " + status + " location: " + location + " since: " + since + " status_data: " + JSON.stringify(status_data) + "\n");
		output.innerHTML += "(Status) Dados: status: " + status + " location: " + location + " since: " + since + " status_data: " + JSON.stringify(status_data) + "\n";

		outputStatus.innerHTML = retornarStatusVonix(status);
		outputStatusPreditivo.innerHTML = retornarStatusVonix(status);

		if (status == 'PAUSED') {

			$('#btnDespausar').show();
			$('#selectPausaVonix').hide();
			$('#btnDespausar').insertAfter("#selectPausaVonix");
			$('.cardStatus').css("background-color", "#ffc107");

		} else {
			$('#btnDespausar').hide();
			$('#selectPausaVonix').show();
			$('#selectPausaVonix').insertAfter('#btnDespausar');

		}

		if (status == 'OFFLINE') {

			enviarMsgGrowl('Agente Deslogado', 'warn');
			
			agentOnline = false;
			
			$('.iconStatus').css("color", "red");
		
			if (status != 'PAUSED') 
				$('.cardStatus').css("background-color", "#dc3545");

		} else if (status == 'ONLINE') {

			agentOnline = true;
			
			$('.iconStatus').css("color", "green");
		
			if (status != 'PAUSED') 
				$('.cardStatus').css("background-color", "#28a745");

		}
	


	});

	vonix.onError(function(date, action_id, description) {

		if (action_id == 'broker') {

			$('#btnDespausar').hide();
			$('#selectPausaVonix').hide();
			$('#btnStatusPanelAgent').addClass('red-btn');
			$('#lblStatusServer').css('color', 'red');
			outputStatus.innerHTML = "Servidor Offline"


		} else {

			vonix.doStatus();
		}

		output.innerHTML += "(Erro) Dados: Data: " + date + " Action id: " + action_id + " Descrição: " + description + "\n";


	});

	vonix.onConnect(function(date) {
		
		output.innerHTML += "(Connect) Dados: date: " + date + "\n";

		$('#lblStatusServer').css('color', 'green');

		$('#btnDespausar').show();
		$('#selectPausaVonix').show();
		$('#btnStatusPanelAgent').removeClass('red-btn');
		enviarMsgGrowl('Vonix Conectada!', 'info');
		
		console.log('removendo a pausa....');
		vonix.doUnpause();
		sleepFor(1000);
		vonix.doStatus();

	});

}



	var idMasterClient = null;
	var idMasterCentral = null;
	var transfer = false;	

function atualizar(){
	
	//vonix.doStatus();
}

function logarRamal(ramal){
	
	if(ramal){
		console.log(ramal);
		vonix.doLogin(ramal);
	}
}

/*MODO MANUAL*/
function iniciarIntegraVonixAtivo(servidor, login, isAutomatico, isHard,timeoutTmp,pausarDiscador) {

	var automatico = isAutomatico == true;
	
	var automatico = isAutomatico == true;
	var pausar = pausarDiscador;
	var timeout = timeoutTmp;
	
	vonix = new Vonix(servidor, login);
	vonix.onConnect();
	console.log("Iniciando script Vonix.... " + automatico+" |TIMEOUT: "+timeout+" | PAUSAR: "+pausar);

	agentOnline = false;
	
	if(pausar){
		console.log('INSERINDO PAUSA');
		//vonix.doPause(9);
	}
	
	
	vonix.onDial(function(call_id, date, queue, from, to, callfilename, contact_name, action_id) {

		enviarMsgGrowl('Discando para:  ' + to, 'info');
		
		validarTimeOut();
		
		ondDialAtivo([{
			
			name: 'call_id',
			value: call_id,

		}, { name: 'data', value: date },
			
			{name: 'to',value: to},
			
			{name: 'fila',value: queue},
			
			{name: 'action_id',value: action_id},
			
			{name: 'from',value: from}]);
			

	});

	vonix.onDialAnswer(function(call_id, date) {
		
		inserirAudioIdVonix([{
			name: 'call_id',
			value: call_id,

		}, { name: 'data', value: date }]);
		
		if(transfer){
			
			idMasterCentral = call_id;
		
		}else{
			
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
		
	if ( $("#valorTimeOut").length && $("#permitirTimeOut").length) {
			
			if( timeout ) {
				//console.log("INICIANDO CRONOMETRO");
				zerarTempo($("#valorTimeOut").val());
				pararCountDown(false);
				countdown();
				
			}
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

	//	console.log("(ReceiveAnswer) Dados: call_id: " + call_id + " date: " + date + " waiting_seconds: " + waiting_seconds + "\n");

		if (automatico) {
			///	vonix.doStatus();
		}
	});

	vonix.onReceiveFailure(function(call_id, date, waiting_seconds) {

	//	console.log("(ReceiveFailure) Dados: call_id: " + call_id + " date: " + date + " waiting_seconds: " + waiting_seconds + "\n");
		
		if (automatico) {
			//vonix.doStatus();
		}
	});

	vonix.onHangUp(function(call_id, date, cause_id, cause_description) {

	//	console.log("(HangUp) Dados: call_id: " + call_id + " date: " + date + " cause: " + cause_id + " OK - cause_description: " + cause_description + "\n");
		
		if (automatico) {
			//vonix.doStatus();
			realizarPausa();
			vonix.doPause(9);
		}
			realizarPausa();
			vonix.doPause(9);
		
		onInicioTabulacaoAtendimento();
					
		if ( $("#valorTimeOut").length && $("#permitirTimeOut").length) {
			
			if( timeout ) {
				//console.log("INICIANDO CRONOMETRO");
				zerarTempo($("#valorTimeOut").val());
				pararCountDown(false);
				countdown();
				
			}
		}
		
	});

	vonix.onLogin(function(date, location) {

		//console.log("(Login) Dados: date: " + date + " location: " + location + "\n");

		vonix.doStatus();

		if (automatico) {
			
			realizarPausa();
			vonix.doPause(9);

		}

	});

	vonix.onLogoff(function(date, location, duration) {

	//	console.log("(Logoff) Dados: date: " + date + " location: " + location + " duration: " + duration + "\n");
		vonix.doStatus();


	});

	vonix.onPause(function(date, reason) {

	//	console.log("(Pause) Dados: date: " + date + " reason: " + reason + "\n>");

		if (automatico) {
			vonix.doStatus();
		}
	});

	vonix.onUnpause(function(date, reason, duration) {

	//	console.log("(Unpause) Dados: date: " + date + " reason: " + reason + " duration: " + duration + "\n>");

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

		console.log("(Connect) Dados: date: " + date + "\n")

		if (automatico || pausar) {
						
			vonix.doUnpause();
			sleepFor(1000);
			vonix.doPause(9);
			vonix.doStatus();
		}

	});


}
/* FIM MODO MANUAL*/


function validarTimeOut(){
	
		if ($("#permitirTimeOut").length) {
			
			var valorTimeOut = $("#permitirTimeOut").val();

			if (valorTimeOut) {
				console.log("parando cronometro.");
				zerarTempo(null);
				pararCountDown(true);
			}
		}
}


//COM PAUSA
function iniciarIntegraVonixPausa(servidor, login, pausa) {


	agentOnline = false;

	vonix = new Vonix(servidor, login);

	console.log("Iniciando script Vonix.... PAUSA: " + pausa);
	
	vonix.onConnect();

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
			
		}else{
			
			vonix.doPause(7);
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

function deslogar(){
	console.log("Deslogando agent.")
	vonix.doLogoff();
}

function iniciarDlgStatusVonix() {

	console.log('OK');

	PF('dlgVonix').show();
	
}

function onChangePause(select) {

	console.log(select);

	if (select == '') {

		vonix.doUnpause();
		vonix.doStatus();
		$('#btnDespausar').hide();

	} else {
		console.log(select);
		vonix.doPause(select);
		vonix.doStatus();
		$('#btnDespausar').show();
	}

}

function realizarPausa() {

	//realizarPausaVonix();
}

function desPausaVonix() {
	vonix.doUnpause();
}

function handlerBtnPause() {

	vonix.doUnpause();
	PF('selectPausaVonix').selectValue('');

}

function doLogarVonix() {

	vonix.doLogin($('#txtRamalLogar').val());

}

function vonixDial(numero, nome, id) {
	
	console.log("Discando...")
		vonix.doUnpause();
		
		sleepFor(1000);
	
		vonix.doStatus();
		
		validarTimeOut();
			
		vonix.doDial(numero, nome, '', 1, id);
	

}

function vonixDialBack(numero, nome, id) {
	
		console.log("Discando... : "+nome)
		
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

function openPanelDiscador() {
	vonix.doStatus();
	PF('dlgVonix').show();
}



function onTest(){
	
	var action_id = '10151189';
	var contact_name = 'GERALDA MARIA FERREIRA';
	var queue = 'bigbox';
	var call_id = '112547441';
	var callfilename = 'teste.wav';
	var to = '377777';
	var from = '999631255';
	var date = '';
	var additional_data = '';
	
		onEnviarAtendimento(
			[
				{
		
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
		
				}
			]);

	
}

