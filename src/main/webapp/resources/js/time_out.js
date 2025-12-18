/**
 * 
 */

var tempo = null
var stop = true;

function zerarTempo(valor) {
	
	tempo = valor;
	$("#cronometro").css('color', '#6c757d');
		
}

function pararCountDown(value){
	
	stop = value;
	
}

function countdown() {
	
	if(tempo == null){
		
		$("#cronometro").css('color', '#6c757d');
		
	}else if ( (tempo != null && stop === false) && (tempo - 1) >= 0 ) {

		var min = parseInt(tempo / 60);

		var seg = tempo % 60;

		if (min < 10) {

			min = "0" + min;
			min = min.substr(0, 2);

		}
				
		if (seg <= 9) {

			$("#cronometro").css('color', 'red');
			seg = "0" + seg;
		}

		horaImprimivel = '00:' + min + ':' + seg;
		
		$("#cronometro").html(horaImprimivel);
				
		setTimeout('countdown()', 1000);
		
		tempo--;

	}else if(tempo <= 0){
		
		rcCronometro();
		
	}

}


