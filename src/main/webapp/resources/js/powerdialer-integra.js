/**
 * 
 */
function iniciarIntegraPowerDialer(url,ramal){

		
        var wsUri =  url.replace('http','ws') + "/primepush/wsagent/"+ramal;
		        
		var socket = new WebSocket(wsUri);

        console.log("socket: "+wsUri);

        socket.onopen = function(handler){
            console.log('recebendo');
            console.table(handler);
           onDisponivelDiscador();

        };

        socket.onerror = function(erro){
	
            console.log("retorno evento erro: "+JSON.stringify(erro));
			onErroDiscador();


        };

        socket.onclose = function (handler){

            console.log("close: " +JSON.stringify(handler));
        };

        socket.onmessage = function (handler){

            if(handler != 'x'){

                var objData = JSON.parse(handler.data);
            
                console.log("escutando evento: "+objData.evento);
                               
                if(objData.evento == 'AgentConnectEvent'){
                    
                    console.table(objData.agentConnectEvent);
                    console.table(objData.agentConnectEvent.variables);
                    
                   // var jsonEvent = JSON.parse(objData.agentConnectEvent);
                 //   console.log(jsonEvent);

                    var fila = objData.agentConnectEvent.queue;
                    var nomeCliente = objData.agentConnectEvent.variables['DISCAGEM-NOME'];
                    var idAtendimento = objData.agentConnectEvent.variables['DISCAGEM-CLIENTE'];
                    var calleId = objData.agentConnectEvent.variables['DISCAGEM-ID'];
                    var destinoChamada  = objData.agentConnectEvent.variables['DISCAGEM-NUMERO'];
                    var ramal  = objData.agentConnectEvent.variables['connectedLineName'];
                    var callfilename  = objData.agentConnectEvent.variables['VSPRD_IDENTIFICADOR'];

                    console.log("CLIENTE: "+nomeCliente+" | ID: "+idAtendimento+" |FILA: "+fila+" |TELEFONE: "+destinoChamada+" | CALLERID: "+calleId);

                    document.getElementById("nomeCliente").innerHTML = nomeCliente;
                    document.getElementById("numeroCliente").innerHTML = destinoChamada;

                    onEnviarAtendimento([
                        {name: 'idAtendimento',value: idAtendimento,},
                        {name: 'contact_name',value: nomeCliente}, 
                        {name: 'queue',value: fila},
                        {name: 'call_id', value: calleId },
                        {name: 'callfilename',value: callfilename},
                        {name: 'to',value: ramal}, 
                        {name: 'from',value: destinoChamada}    
                    ]);

                }else{
                    console.log(objData.evento+" - "+JSON.stringify(objData));
					document.getElementById("nomeCliente").innerHTML = '';
                    document.getElementById("numeroCliente").innerHTML = '';
                }
             
            }else{

                console.log('on_menssage: '+ JSON.stringify(handler.data));
            
            }
        };

	return socket;
}