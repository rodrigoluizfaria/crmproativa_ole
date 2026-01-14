function retornoArquivo(xhr, status, args) {

    console.log("ok");
    console.log(args);

    if (args.validationFailed) {

    } else {

        alert("OK");
        updateGrowl();

    }

    if (args.retornoPlan) {

        console.log(xhr);
        PF('dlgLayout').show();

    }

}


function topFunction() {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
}


function loginRetorno(xhr, status, args) {

    if (args.status == 'alterar_senha') {

        $('#loginNovo').val(args.login);
        PF('dlgAlterarSenha').show();

    } else if (args.status == 'sucesso') {

        redirecionarLoginSucesso();

    }  else if (args.status == 'redirecionarSac') {

        redirecionarSac();

    } else if (args.status == 'consulta') {
        console.log("CONSULTA")
        redirecionarConsulta();

    }

}


function testeRemoto(teste) {

    document.getElementById('servidor').value = document.getElementById('txt').value;
    console.log(document.getElementById('servidor').value);
    console.log(document.getElementById('txt').value);
    testVonix();
}

function atualizarCombo(indice) {

    $('#scriptIndice').val = indice;

    $('#tipoImportacaoEnvio').val = $('#selectImportacao' + indice + '_input').val();
    console.log(indice);

    atualizarComboRemoto([
        {
            name: 'indice',
            value: indice
        },
        {
            name: 'tidoEnum',
            value: document.getElementById('formTable:tableImportacao:0:selectImportacao' + indice + '_input').value
        }]);

};

function consultaWhatsapp(numero) {
    console.log('OK ' + numero)
    if (numero) {
        onConsultaWhatsapp([

            {

                name: 'idTelefone',
                value: numero

            }

        ]);

    }
}

function somenteNumeros(e) {
    var charCode = e.charCode ? e.charCode : e.keyCode;

    if (charCode != 8 && charCode != 9) {

        if (charCode < 48 || charCode > 57) {
            return false;
        }
    }

}


function somenteLetras(e) {

    var tecla = e.charCode ? e.charCode : e.keyCode;

    if (tecla >= 33 && tecla <= 64 || tecla >= 91 && tecla <= 93 || tecla >= 123 && tecla <= 159 || tecla >= 162 && tecla <= 191) {
        return false;
    } else {
        return true;
    }
}

function onStatus() {

    $("circle").hide();

}

function deslogarVonix(servidor, login) {

    if (servidor && login) {

        vonix = new Vonix(servidor, login);

        vonix.onConnect(function (date) {


            vonix.doStatus();

        });

        vonix.onStatus(function (status, location, since, status_data) {


            console.log(status);

            if (status == 'ONLINE') {
                console.log("DESLOGANDO.")
                vonix.doLogoff();


            }


        });

    }


}


function validaData(valor) {

    console.log(document.getElementById("form:txtAgendamentoInicio").value);

}

function onChangeSelectCheckBox(obj) {

    var objSelect = obj;
    console.log(objSelect.items + " - " + objSelect.inputs);
    for (var i = 0; i < objSelect.items; i++) {
        if (objSelect[i].length == 4) {
            mostra = "OBJ : " + objSelect[i] + "\n";
        }
    }
    ;


}

function downloadBase64File(contentType, base64Data, fileName) {


    const linkSource = 'data:' + contentType + ';base64,' + base64Data;

    const downloadLink = document.createElement("a");
    downloadLink.href = linkSource;
    downloadLink.download = fileName;
    downloadLink.click();

}

function copiarTexto(texto) {

    navigator.clipboard.writeText(texto).then(function () {
	        console.log("Protocolo copiado: " + texto);
    }, function (err) {
        console.error('Erro ao copiar: ', err);
    });
}


/*
 * PrimeFacesExt.locales.TimePicker['pt_BR'] = { hourText: 'Horas', minuteText:
 * 'Minutos', amPmText: ['AM', 'PM'], closeButtonText: 'Fechar', nowButtonText:
 * 'Agora', deselectButtonText: 'Limpar seleção' };
 */

PrimeFaces.locales['pt_BR'] = {
    closeText: 'Fechar',
    prevText: 'Anterior',
    nextText: 'Próximo',
    monthNames: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho',
        'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
    monthNamesShort: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago',
        'Set', 'Out', 'Nov', 'Dez'],
    dayNames: ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta',
        'Sábado'],
    dayNamesShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb'],
    dayNamesMin: ['D', 'S', 'T', 'Q', 'Q', 'S', 'S'],
    weekHeader: 'Semana',
    weekNumberTitle: 'Sm',
    firstDay: 0,
    isRTL: false,
    showMonthAfterYear: false,
    yearSuffix: '',
    timeOnlyTitle: 'Só Horas',
    timeText: 'Tempo',
    hourText: 'Hora',
    minuteText: 'Minuto',
    secondText: 'Segundo',
    currentText: 'Começo',
    ampm: false,
    month: 'Mês',
    week: 'Semana',
    day: 'Dia',
    today: 'Hoje',
    clear: 'Limpar',
    noEventsToDisplay: 'Nenhum evento para exibir',
    allDayText: 'Todo o Dia',
    messages: { // optional for Client Side Validation
        'javax.faces.component.UIInput.REQUIRED': '{0}: Erro de validação: é necessário um valor.',
        'javax.faces.converter.IntegerConverter.INTEGER': '{2}: \'{0}\' deve ser um número consistindo de um ou mais dígitos.',
        'javax.faces.converter.IntegerConverter.INTEGER_detail': '{2}: \'{0}\' deve ser um número entre -2147483648 e 2147483647 Exemplo: {1}',
        'javax.faces.converter.DoubleConverter.DOUBLE': '{2}: \'{0}\' deve ser um número consistindo de um ou mais dígitos.',
        'javax.faces.converter.DoubleConverter.DOUBLE_detail': '{2}: \'{0}\' deve ser um número entre 4.9E-324 e 1.7976931348623157E308 Exemplo: {1}',
        'javax.faces.converter.BigDecimalConverter.DECIMAL': '{2}: \'{0}\' deve ser um número decimal assinado.',
        'javax.faces.converter.BigDecimalConverter.DECIMAL_detail': '{2}: \'{0}\' deve ser um número decimal assinado consistindo de zero ou mais dígitos, que podem ser seguidos por um ponto decimal e uma fração. Exemplo {1}',
        'javax.faces.converter.BigIntegerConverter.BIGINTEGER': '{2}: \'{0}\' deve ser um número consistindo de um ou mais dígitos.',
        'javax.faces.converter.BigIntegerConverter.BIGINTEGER_detail': '{2}: \'{0}\' deve ser um número consistindo de um ou mais dígitos. Exemplo {1}',
        'javax.faces.converter.ByteConverter.BYTE': '{2}: \'{0}\' deve ser um número entre 0 e 255.',
        'javax.faces.converter.ByteConverter.BYTE_detail': '{2}: \'{0}\' deve ser um número entre 0 e 255. Exemplo: {1}',
        'javax.faces.converter.CharacterConverter.CHARACTER': '{1}: \'{0}\' deve ser um caractere válido.',
        'javax.faces.converter.CharacterConverter.CHARACTER_detail': '{1}: \'{0}\' deve ser um caractere ASCII válido.',
        'javax.faces.converter.ShortConverter.SHORT': '{2}: \'{0}\' deve ser um número consistindo de um ou mais dígitos.',
        'javax.faces.converter.ShortConverter.SHORT_detail': '{2}: \'{0}\' deve ser um número entre -32768 e 32767 Exemplo: {1}',
        'javax.faces.converter.BooleanConverter.BOOLEAN': '{1}: \'{0}\' deve ser \'verdadeiro\' ou \'falso\'',
        'javax.faces.converter.BooleanConverter.BOOLEAN_detail': '{1}: \'{0}\' deve ser \'verdadeiro\' ou \'falso\'. Qualquer valor diferente de \'verdadeiro\' irá avaliar para \'falso\'.',
        'javax.faces.validator.LongRangeValidator.MAXIMUM': '{1}: Erro de validação: o valor é maior que o máximo permitido de \'{0}\'',
        'javax.faces.validator.LongRangeValidator.MINIMUM': '{1}: Erro de validação: O valor é menor que o mínimo permitido de \'{0}\'',
        'javax.faces.validator.LongRangeValidator.NOT_IN_RANGE': '{2}: Erro de validação: O atributo especificado não está entre os valores esperados de {0} e {1}.',
        'javax.faces.validator.LongRangeValidator.TYPE={0}': 'Erro de validação: O valor não é do tipo correto.',
        'javax.faces.validator.DoubleRangeValidator.MAXIMUM': '{1}: Erro de validação: o valor é maior que o máximo permitido de \'{0}\'',
        'javax.faces.validator.DoubleRangeValidator.MINIMUM': '{1}: Erro de validação: o valor é menor que o mínimo permitido de \'{0}\'',
        'javax.faces.validator.DoubleRangeValidator.NOT_IN_RANGE': '{2}: Erro de validação: o atributo especificado não está entre os valores esperados de {0} e {1}',
        'javax.faces.validator.DoubleRangeValidator.TYPE={0}': 'Erro de validação: O valor não é do tipo correto',
        'javax.faces.converter.FloatConverter.FLOAT': '{2}: \'{0}\' deve ser um número consistindo de um ou mais dígitos.',
        'javax.faces.converter.FloatConverter.FLOAT_detail': '{2}: \'{0}\' deve ser um número entre 1.4E-45 e 3.4028235E38 Exemplo: {1}',
        'javax.faces.converter.DateTimeConverter.DATE': '{2}: \'{0}\' não pode ser entendido como uma data.',
        'javax.faces.converter.DateTimeConverter.DATE_detail': '{2}: \'{0}\' não pode ser entendido como uma data. Exemplo {1}',
        'javax.faces.converter.DateTimeConverter.TIME': '{2}: \'{0}\' não pode ser entendido como uma hora.',
        'javax.faces.converter.DateTimeConverter.TIME_detail': '{2}: \'{0}\' não pode ser entendido como uma hora. Exemplo {1}',
        'javax.faces.converter.DateTimeConverter.DATETIME': '{2}: \'{0}\' não pode ser entendido como data e hora.',
        'javax.faces.converter.DateTimeConverter.DATETIME_detail': '{2}: \'{0}\' não pode ser entendido como uma data e hora. Exemplo {1}',
        'javax.faces.converter.DateTimeConverter.PATTERN_TYPE': '{1}: A \'padrão\' ou \'tipo\' atributo deve ser especificado para converter o valor \'{0}\'',
        'javax.faces.converter.NumberConverter.CURRENCY': '{2}: \'{0}\' não pode ser entendido como um valor de moeda.',
        'javax.faces.converter.NumberConverter.CURRENCY_detail': '{2}: \'{0}\' não pode ser entendido como um valor de moeda. Exemplo {1}',
        'javax.faces.converter.NumberConverter.PERCENT': '{2}: \'{0}\' não pode ser entendido como uma porcentagem.',
        'javax.faces.converter.NumberConverter.PERCENT_detail': '{2}: \'{0}\' não pode ser entendido como uma porcentagem. Exemplo {1}',
        'javax.faces.converter.NumberConverter.NUMBER': '{2}: \'{0}\' não pode ser entendido como um número.',
        'javax.faces.converter.NumberConverter.NUMBER_detail': '{2}: \'{0}\' não é um número. Exemplo {1}',
        'javax.faces.converter.NumberConverter.PATTERN': '{2}: \'{0}\' não é uma expressão regular de número.',
        'javax.faces.converter.NumberConverter.PATTERN_detail': '{2}: \'{0}\' não é uma expressão regular de número. Exemplo {1}',
        'javax.faces.validator.LengthValidator.MINIMUM': '{1}: Erro de validação: o comprimento é menor que o mínimo permitido de \'{0}\'',
        'javax.faces.validator.LengthValidator.MAXIMUM': '{1}: Erro de validação: o comprimento é maior que o máximo permitido de \'{0}\'',
        'javax.faces.validator.RegexValidator.PATTERN_NOT_SET': 'A expressão regular deve ser configurado.',
        'javax.faces.validator.RegexValidator.PATTERN_NOT_SET_detail': 'A expressão regular deve ser definido como valor não vazio.',
        'javax.faces.validator.RegexValidator.NOT_MATCHED': 'Expressão regular não combina',
        'javax.faces.validator.RegexValidator.NOT_MATCHED_detail': 'Padrão Regex de \'{0}\' não combinado',
        'javax.faces.validator.RegexValidator.MATCH_EXCEPTION': 'Erro na expressão regular.',
        'javax.faces.validator.RegexValidator.MATCH_EXCEPTION_detail': 'Erro na expressão regular, \'{0}\'',
        // optional for bean validation integration in client side validation
        'javax.faces.validator.BeanValidator.MESSAGE': '{0}',
        'javax.validation.constraints.AssertFalse.message': 'deve ser falso',
        'javax.validation.constraints.AssertTrue.message': 'deve ser verdade',
        'javax.validation.constraints.DecimalMax.message': 'deve ser menor ou igual a {0}',
        'javax.validation.constraints.DecimalMin.message': 'deve ser maior ou igual a {0}',
        'javax.validation.constraints.Digits.message': 'valor numérico fora dos limites (<{0} dígitos>.<{1} dígitos> esperado)',
        'javax.validation.constraints.Future.message': 'deve estar no futuro',
        'javax.validation.constraints.Max.message': 'deve ser menor ou igual a {0}',
        'javax.validation.constraints.Min.message': 'deve ser maior ou igual a {0}',
        'javax.validation.constraints.NotNull.message': 'pode não ser nulo',
        'javax.validation.constraints.Null.message': 'deve ser nulo',
        'javax.validation.constraints.Past.message': 'deve estar no passado',
        'javax.validation.constraints.Pattern.message': 'deve corresponder "{0}"',
        'javax.validation.constraints.Size.message': 'o tamanho deve estar entre {0} e {1}'
    }
};

/*PrimeFacesExt.locales.TimeAgo['pt_BR'] = {
	     prefixAgo: "há",
	     prefixFromNow: "em",
	     suffixAgo: null,
	     suffixFromNow: null,
	     seconds: "alguns segundos",
	     minute: "um minuto",
	     minutes: "%d minutos",
	     hour: "uma hora",
	     hours: "%d horas",
	     day: "um dia",
	     days: "%d dias",
	     month: "um mês",
	     months: "%d meses",
	     year: "um ano",
	     years: "%d anos"
}

PrimeFacesExt.locales.TimePicker['pt_BR'] = {
    hourText: 'Horas',
    minuteText: 'Minutos',
    amPmText: ['AM', 'PM'],
    closeButtonText: 'Fechar',
    nowButtonText: 'Agora',
    deselectButtonText: 'Limpar seleção'
};*/
