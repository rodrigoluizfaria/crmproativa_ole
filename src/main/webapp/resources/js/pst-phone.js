function sendCall(url, origem, destino, nome) {


	$.ajax({
		type: "POST",
		url: url + "/call",
		data: JSON.stringify({ context: "default", exten: destino, channel: origem, callerId: nome }),
		cache: false,
		async: false,
		contentType: 'application/json',
		dataType: "json",
		success: function(response, textStatus, jqXHR) {
			console.log(response);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR);
			console.log(textStatus);
			console.log(errorThrown);
		}
	});

}