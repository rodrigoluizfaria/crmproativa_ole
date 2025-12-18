/**
 * 
 */

let websocket;
let retorno;
let imagem='';
let grupo;
let grupoPrincipal;
let perguntas;
let descricaoGrupo;

function conectarBot(url, login,foto) {

	if (!websocket) {
		
		var uri = url.startsWith('http://') ? (url.replace('http', 'ws')+ "/crmproativa/bot/" + login ): ('ws://'+url) + "/crmproativa/bot/" + login; // ws -> not secure, wss (ssl)
		console.log('conectando: '+uri);
		websocket = new WebSocket(uri);
		
		imagem = foto;
		
		console.log(imagem);
		
		websocket.onopen = function() {

			console.log("a msg from client side");
		}


		websocket.onclose = function() {
			console.log("Server close connection.");

		}

		websocket.onerror = function(evt) {
			alert("error from server: Can't establish a connection to the server!");
			console.info(evt);
		}


		websocket.onmessage = function(msg) {

			if (msg) {

				msg = msg.data;
				
				let retorno = JSON.parse(msg);
				console.log(retorno)
				if(retorno.retorno){
					
					let grupo = JSON.parse(JSON.stringify(retorno.grupo_principal));
					
					console.log(retorno.descricao_grupo);
					
				/*	console.log(grupo.forEach(element => {
						if (element) {
							console.log(element.descricao)
						}
					}))*/
					
					criarMenssagenBot(retorno);
				
	
				}else{
					criarMenssagenNaoEncontrada(retorno);
					
					
				}
				
			}

		}
	}

}

function criarMenssagenNaoEncontrada(retorno){
	
	$("#load_bot").css("display", "block");
	var lista =  '<li class="message-own" >';
	
	lista +='<img  src="/crmproativa/jakarta.faces.resource/img/bot_3.png.jsf" alt="">';
	lista +=  '<div class="div-bot-retorno">  '+ retorno.menssagem.replace('undefined','')+'' ;
	//lista += retornarListaPerguntas(retorno);
	lista += '</div></li>';
	
	$("#ul_bot").append(lista);
	$("#load_bot").css("display", "none");
}

function criarMenssagenBot(retorno){

	$("#load_bot").css("display", "block");
	
	grupoPrincipal = JSON.parse(JSON.stringify(retorno.grupo_principal));
		
	var lista =  '<li class="message-own" >';
	
	lista +='<img  src="/crmproativa/jakarta.faces.resource/img/bot_3.png.jsf" alt="">';
	lista +=  '<div class="div-bot-retorno div-bot-retorno-grupo">'+ retorno.menssagem +'';
	lista += ' <div class="bot_list" style="padding: 0" > <ul> ';
	
	grupoPrincipal.forEach(function(data,index){
		
		lista += ' <li  onclick="onHandleGrupPrincipal('+data.indice+')"> '+data.indice+' - '+data.descricao +'</li>'; 
		
	});
		
	lista +=  '</ul > </div></div>';
	lista += '</li>';

	$("#ul_bot").append(lista);
	
	$("#load_bot").css("display", "none");
}



function criarMenssagenBotRetorno(retorno,menssagem){
	
	var lista =  '<li class="message-own" >';
	
	lista +='<img  src="/crmproativa/jakarta.faces.resource/img/bot_3.png.jsf" alt="">';
	lista +=  '<div class="div-bot-retorno-perguntas div-bot-retorno"> Qual é sua duvida sobre  '+ menssagem.replace('undefined','')+'' ;
	lista += retornarListaPerguntas(retorno);
	lista += '</div></li>';
	
	$("#ul_bot").append(lista);
}



function criarMenssagenBotRetornoGrupo(retorno,menssagem){
	
	var lista =  '<li class="message-own" >';
	
	lista +='<img  src="/crmproativa/jakarta.faces.resource/img/bot_3.png.jsf" alt="">';
	lista +=  '<div class="div-bot-retorno-perguntas div-bot-retorno"> O tópico '+ menssagem.replace('undefined','')+' contêm os itens: ' ;
	lista += retornarListaGrupo(retorno);
	lista += '</div></li>';
	
	$("#ul_bot").append(lista);
}

function retornarListaPerguntas(perguntas){
	
	var lista;

	if(perguntas && Array.isArray(perguntas)){
			
			lista += ' <div class="bot_list"  style="padding: 0"> <ul> ';
			
			perguntas.forEach(function(data,index){
			
			lista += ' <li  onclick="onHandlePergunta('+data.indice+')"> '+data.indice+' - '+data.pergunta +'</li>'; 
	
			});	
			
			lista +=  '</ul > </div>';
				
	}
	
	perguntas = perguntas;
	return lista.replace('undefined','');
	
}

function retornarListaGrupo(grupo){
	
	var lista;

	if(grupo && Array.isArray(grupo)){
			
			lista += ' <div class="bot_list"  style="padding: 0"> <ul> ';
			
			grupo.forEach(function(data,index){
			
			lista += ' <li  onclick="onHandleGrup('+data.indice+')"> '+data.indice+' - '+data.descricao +'</li>'; 
	
			});	
			
			lista +=  '</ul > </div>';
				
	}
	
	grupo = grupo;
	return lista.replace('undefined','');
	
}

function retornarListaLimpar(){
	
	var lista;
			
	lista += ' <div class="bot_list"  style="padding: 0"> <ul> ';
	lista += ' <li  onclick="enviarMenssagemInicio()"> '+'Exibir inicio.' +'</li>'; 
	lista += ' <li onclick="enviarLimparMenssagemInicio()" > Limpar e iniciar. </li>'; 
	lista +=  '</ul > </div>';
	
	perguntas = perguntas;
	return lista.replace('undefined','');
	
}


function criarMenssagemResposta(resposta,menssagem){

	var lista =  '<li class="message-own" >';
	
	lista +='<img  src="/crmproativa/jakarta.faces.resource/img/bot_3.png.jsf" alt="">';
	lista +=  '<div class="div-bot-retorno"> ' ;
	lista+='<div style=" line-height: 2;">'+ resposta;
	lista += '</div> ';
	lista += retornarListaLimpar()+'</div></li>';
	
	$("#ul_bot").append(lista);
	
}


function onHandlePergunta(indice){
	
	$("#load_bot").css("display", "block");
	
		var resposta = '';
		var menssagem;
		
		perguntas.forEach(function(data,index){
			
			if(data.indice == indice){
				menssagem = data.pergunta;
				resposta += data.resposta; 
			}
			
			
		});
		
		
	var children = $('#ul_bot').children();
	var qtd = children.length
	console.log(qtd);
	
	if(qtd && qtd > 1){
		
		children.each(function(idx, val){
			if(idx > 4)
   				$(this).hide( 500, function() {
	  				  $(this).remove();
	  			});

		});
		
	}
	criarMenssagenUsuario(indice +' - '+menssagem);
	criarMenssagemResposta(resposta,menssagem);
	
	$("#load_bot").css("display", "none");
}

function onHandleGrup(indice){
	
	$("#load_bot").css("display", "block");
	
	var menssagem;
			
	grupo.forEach(function(data,index){
	
		if(data.indice == indice){
			menssagem = data.descricao;
			console.log(data.perguntas)
			descricaoGrupo = data.descricao;
			perguntas = JSON.parse(JSON.stringify(data.perguntas));
			
		}
	
	
	});
	
	var children = $('#ul_bot').children();
	var qtd = children.length
	console.log(qtd);
	
	if(qtd && qtd > 1){
		
		children.each(function(idx, val){
			if(idx > 2)
   				$(this).hide( 500, function() {
	  				  $(this).remove();
	  			});

		});


	}
	criarMenssagenUsuario(indice +' - '+menssagem);
	criarMenssagenBotRetorno(perguntas,menssagem)
	$("#load_bot").css("display", "none");
}

function onHandleGrupPrincipal(indice){
	
	$("#load_bot").css("display", "block");
	
	var menssagem;
			
	grupoPrincipal.forEach(function(data,index){
	
		if(data.indice == indice){
			menssagem = data.descricao;
			console.log(data.perguntas)
			descricaoGrupo = data.descricao;
			grupo = JSON.parse(JSON.stringify(data.grupos));
			
		}
	
	
	});
	
	var children = $('#ul_bot').children();
	var qtd = children.length
	console.log(qtd);
	
	if(qtd && qtd > 1){
		
		children.each(function(idx, val){
			if(idx > 0)
   				$(this).hide( 500, function() {
	  				  $(this).remove();
	  			});

		});


	}
	criarMenssagenUsuario(indice +' - '+menssagem);
	criarMenssagenBotRetornoGrupo(grupo,menssagem)
	$("#load_bot").css("display", "none");
}

function criarMenssagenBotGeneric(titulo,corpo){
	
	grupo = JSON.parse(JSON.stringify(retorno.grupos));
		
	var lista = '<li class="message-own">';
	lista += '<img  src="/crmproativa/jakarta.faces.resource/img/bot_3.png.jsf" alt="">';
	lista += '<div class="div-bot-retorno">';
	
	if(titulo){
		
		lista += titulo.replace('undefined','');
	}

	lista += corpo;
	lista += '</div>';
	lista += '</li>';
	
	$("#ul_bot").append(lista.replace('undefined',''));

}


function limparListas(){

	$("#ul_bot").children("li").remove();
	
}

function criarMenssagenUsuario(msg){
	
	var listaUsr =  '  <li class="message-from" >';
	
	listaUsr +='<img  src="' +imagem+'" alt="">';
	listaUsr+='  <div>';
	listaUsr+=msg;
	listaUsr+=' </div> </li>';
	
	$("#ul_bot").append(listaUsr);
}


function procurarRespostas(value){
	
	console.log(grupo +' - '+value)
	var	 corpoResposta;
	
	if(value && value < grupo.length ){
		
		resposta = JSON.parse(JSON.stringify(grupo[(value-1)].perguntas));
		corpoResposta += '<ul >';
		
		resposta.forEach(function(data,index){
			
			corpoResposta += ' <li style="padding: 0.1rem;"> '+(index+=1)+' - '+data.pergunta +'</li>'; 
			
		});
		
		corpoResposta +=  '</ul';
		criarMenssagenBotGeneric(grupo[(value-1)].descricao,corpoResposta);
	}
	
}

function enviarLimparMenssagemInicio() {
	
	$("#load_bot").css("display", "block");
	limparListas();
	
	//criarMenssagenUsuario('Inicio');
	websocket.send('ok, limpando e iniciando Grupo de perguntas. ');
	$("#load_bot").css("display", "none");
}

function enviarMenssagemInicio() {
	$("#load_bot").css("display", "block");

	criarMenssagenUsuario('Inicio');
	websocket.send('ok, iniciando grupo de perguntas. ');
	$("#load_bot").css("display", "none");
	
}

function enviarMenssagemBot() {
	
	$("#load_bot").css("display", "block");

	var msg = document.getElementById('txt');

	criarMenssagenUsuario(msg.value);
	procurarRespostas(msg.value);
	//websocket.send(msg.value);
	document.getElementById('txt').value = '';
	$("#load_bot").css("display", "none");
	
}
