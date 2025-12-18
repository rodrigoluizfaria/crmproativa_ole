package com.proativaservicos.util;

import java.io.Serializable;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.google.gson.internal.bind.util.ISO8601Utils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import com.proativaservicos.util.constantes.DataEnum;
import com.proativaservicos.util.constantes.FormatoDataEnum;
import com.proativaservicos.util.constantes.MesEnum;

public class DateUtil implements Serializable {

    private static final long serialVersionUID = 1L;
    private Date data;
    private Date data1;
    private Date data2;
    private String dataTexto;
    private Number dataNumerico;

    private DateUtil(Date data) {
        this.data = data;
    }

    private DateUtil(Date data1, Date data2) {

        this.data1 = data1;
        this.data2 = data2;
    }

    private DateUtil(String data) {

        this.dataTexto = data;
    }

    public static DateUtil builder() {

        return new DateUtil(new Date(System.currentTimeMillis()));
    }

    public static DateUtil builder(Date data) {

        if (data == null) {

            data = new Date(System.currentTimeMillis());
        }

        return new DateUtil(data);
    }

    public static DateUtil builder(Date data1, Date data2) {

        if (data1 == null) {

            data1 = new Date(System.currentTimeMillis());
        }

        if (data2 == null) {

            data2 = new Date(System.currentTimeMillis());
        }

        return new DateUtil(data1, data2);
    }

    public static DateUtil builder(String data) {

        if (StringUtils.isEmpty(data)) {

            data = builder(new Date(System.currentTimeMillis())).formatarDataParaString("dd/MM/yyyy").getDataTexto();
        }

        return new DateUtil(data);
    }

    public static DateUtil builder(String data, boolean hora) {

        if (StringUtils.isEmpty(data)) {
            if (hora)
                data = builder(new Date(System.currentTimeMillis())).formatarDataParaString("dd/MM/yyyy HH:mm:ss")
                        .getDataTexto();
            else
                data = builder(new Date(System.currentTimeMillis())).formatarDataParaString("dd/MM/yyyy")
                        .getDataTexto();

        }

        return new DateUtil(data);
    }

    public Date getData() {
        return this.data;
    }

    public String getDataTexto() {
        return this.dataTexto;
    }

    public Number getDataNumerico() {
        return this.dataNumerico;
    }

    public DateUtil retornarDataPrimeiroDiaMesAnterior() {

        if (this.data == null) {

            return this;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.data);
        calendar.add(2, -1);
        calendar.set(5, 1);

        this.data = calendar.getTime();

        return this;
    }

    public DateUtil retornarDataUltimoDiaMesAnterior() {

        if (this.data == null) {

            return this;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.data);
        calendar.add(2, -1);
        calendar.set(5, calendar.getActualMaximum(5));

        this.data = calendar.getTime();

        return this;
    }

    public DateUtil retornarDataPrimeiroDiaMes() {

        if (this.data == null) {

            return this;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.data);
        calendar.set(5, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);

        this.data = calendar.getTime();

        return this;
    }

    public DateUtil retornarInicioMes(Integer diminuir) {

        if (this.data == null || diminuir == null) {

            this.data = null;
            return this;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.data);
        calendar.add(2, diminuir.intValue());
        calendar.set(5, 1);
        this.data = calendar.getTime();
        return this;
    }

    public DateUtil retornaDataAPartirDe(MesEnum mes, Integer ano) {

        if (mes == null || ano == null) {

            this.data = null;
            return this;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(5, 1);
        calendar.set(2, mes.getValor().intValue() - 1);
        calendar.set(1, ano.intValue());
        calendar.set(10, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        this.data = calendar.getTime();

        return this;
    }

    public DateUtil retornarDataPrimeiroDiaMesEspecifico(Integer mes) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(1, calendar.get(1));
        calendar.set(2, mes.intValue());
        calendar.set(5, 1);

        this.data = calendar.getTime();

        return this;
    }

    public DateUtil retornarDataUltimoDiaMesEspecifico(Integer mes) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(1, calendar.get(1));
        calendar.set(2, mes.intValue());
        calendar.set(5, calendar.getActualMaximum(5));

        this.data = calendar.getTime();

        return this;
    }

    public DateUtil retornarDataUltimoDiaMes() {

        if (this.data == null) {

            return this;

        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.data);
        calendar.add(2, 1);
        calendar.set(5, 1);
        calendar.add(5, -1);
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);

        this.data = calendar.getTime();

        return this;
    }

    public DateUtil retornarDataComHoraInicial() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.data);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);

        this.data = calendar.getTime();

        return this;
    }

    public DateUtil retornarDataComHoraFinal() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.data);
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);

        this.data = calendar.getTime();

        return this;
    }

    public DateUtil acrescentarMesData() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.data);
        calendar.add(2, 1);

        this.data = calendar.getTime();

        return this;
    }

    public DateUtil removerMesData(Integer quantidadeMeses) {

        if (this.data == null || quantidadeMeses == null) {
            this.data = null;
            return this;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.data);
        calendar.add(2, -quantidadeMeses.intValue());

        this.data = calendar.getTime();

        return this;
    }

    public int calcularIdade() {

        Calendar calendarIdade = Calendar.getInstance();
        calendarIdade.setTime(this.data);

        Calendar hoje = Calendar.getInstance();

        int idade = hoje.get(1) - calendarIdade.get(1);

        calendarIdade.add(1, idade);

        if (hoje.before(calendarIdade)) {
            idade--;
        }

        return idade;
    }

    public DateUtil removerTempoData(DataEnum dataEnum, Integer tempo) {

        if (this.data == null || dataEnum == null || tempo == null) {

            this.data = null;
            return this;
        }

        if (tempo.intValue() < 0) {
            tempo = Integer.valueOf(Math.abs(tempo.intValue()));
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.data);
        switch (dataEnum) {

            case ANO:
                calendar.add(1, -tempo.intValue());

                this.data = calendar.getTime();
                return this;
            case MES:
                calendar.add(2, -tempo.intValue());
                this.data = calendar.getTime();
                return this;
            case SEMANA:
                calendar.add(4, -tempo.intValue());
            case DIA:
                calendar.add(5, -tempo.intValue());
                this.data = calendar.getTime();
                return this;
            case HORA:
                calendar.add(11, -tempo.intValue());
                this.data = calendar.getTime();
                return this;
            case MINUTO:
                calendar.add(12, -tempo.intValue());
                this.data = calendar.getTime();
                return this;
            case SEGUNDO:
                calendar.add(13, -tempo.intValue());
                this.data = calendar.getTime();
                return this;
        }
        this.data = null;
        return this;
    }

    /**
     * Seta no data numerico o valor passado
     *
     * @param dataEnum
     * @return DateUtil dataNumerico
     */
    public DateUtil retornarTempoData(DataEnum dataEnum) {

        if (dataEnum == null || this.data == null) {
            this.dataNumerico = null;
            return this;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.data);
        switch (dataEnum) {
            case ANO:
                this.dataNumerico = Integer.valueOf(calendar.get(1));

                return this;
            case MES:
                this.dataNumerico = MesEnum.getMes(Integer.valueOf(calendar.get(2))).getValor();
                return this;
            case SEMANA:
                this.dataNumerico = Integer.valueOf(calendar.get(4));
                return this;
            case DIA:
                this.dataNumerico = Integer.valueOf(calendar.get(5));
                return this;
            case HORA:
                this.dataNumerico = Integer.valueOf(calendar.get(11));
                return this;
            case MINUTO:
                this.dataNumerico = Integer.valueOf(calendar.get(12));
                return this;
            case SEGUNDO:
                this.dataNumerico = Integer.valueOf(calendar.get(13));
                return this;
        }

        this.dataNumerico = null;
        return this;
    }

    public DateUtil retornarTempo() {

        if (this.data == null) {

            this.dataTexto = null;
            return this;
        }

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(this.data);

        Number hora = Integer.valueOf(calendar.get(Calendar.HOUR_OF_DAY));

        Number minuto = Integer.valueOf(calendar.get(Calendar.MINUTE));

        Number segundo = Integer.valueOf(calendar.get(Calendar.SECOND));

        String retorno = "";

        Duration total = Duration.ofSeconds(7300);

        if (hora != null && hora.intValue() > 0) {

            Duration totals = Duration.ofHours(hora.longValue());
        }

        if (minuto != null && minuto.intValue() > 0) {

        }

        if (segundo != null && segundo.intValue() > 0) {

        }

        this.dataTexto = null;
        return this;

    }

    public DateUtil alterarTempoData(Integer fieldCalendar, Integer tempo) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.data);
        calendar.set(fieldCalendar.intValue(), tempo.intValue());

        this.data = calendar.getTime();

        return this;
    }

    public DateUtil adicionarTempoData(DataEnum dataEnum, Integer tempo) {

        if (this.data == null || dataEnum == null || tempo == null) {
            this.data = null;
            return this;
        }

        if (tempo.intValue() < 0) {
            tempo = Integer.valueOf(Math.abs(tempo.intValue()));
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.data);

        switch (dataEnum) {
            case ANO:
                calendar.add(1, tempo.intValue());

                this.data = calendar.getTime();
                return this;
            case MES:
                calendar.add(2, tempo.intValue());
                this.data = calendar.getTime();
                return this;
            case SEMANA:
                calendar.add(4, tempo.intValue());
                this.data = calendar.getTime();
                return this;
            case DIA:
                calendar.add(5, tempo.intValue());
                this.data = calendar.getTime();
                return this;
            case HORA:
                calendar.add(11, tempo.intValue());
                this.data = calendar.getTime();
                return this;
            case MINUTO:
                calendar.add(12, tempo.intValue());
                this.data = calendar.getTime();
                return this;
            case SEGUNDO:
                calendar.add(Calendar.SECOND, tempo);
                this.data = calendar.getTime();
                return this;
        }
        this.data = null;
        return this;
    }

    /**
     * Calcula a diferença entre as datas passadas (Date1 , Date2) - seta data
     * numerico
     *
     * @param constante
     * @return DateUtil - dataNumerico
     */
    public DateUtil calcularDiferencaDatas(DataEnum constante) {

        if (constante == null || this.data1 == null || this.data2 == null) {
            this.dataNumerico = null;
            return this;
        }

        Calendar calendarInicio = Calendar.getInstance();
        calendarInicio.setTime(this.data1);

        Calendar calendarFim = Calendar.getInstance();
        calendarFim.setTime(this.data2);

        long tempo = 1L;
        switch (constante) {
            case ANO:
                tempo = (this.data2.getTime() - this.data1.getTime()) / 86400000L / 30L / 12L;
                break;
            case MES:
                tempo = (this.data2.getTime() - this.data1.getTime()) / 86400000L / 30L;
                break;
            case SEMANA:
                tempo = (this.data2.getTime() - this.data1.getTime()) / 86400000L / 7L;
                break;
            case DIA:
                tempo = (this.data2.getTime() - this.data1.getTime()) / 86400000L;
                break;
            case HORA:
                tempo = (this.data2.getTime() - this.data1.getTime()) / 3600000L;
                break;
            case MINUTO:
                tempo = (this.data2.getTime() - this.data1.getTime()) / 60000L;
                break;
            case SEGUNDO:
                tempo = (this.data2.getTime() - this.data1.getTime()) / 1000L;
                break;
            default:
                this.dataNumerico = null;
                return this;
        }
        tempo = (tempo < 0L) ? 0L : tempo;

        this.dataNumerico = Long.valueOf(tempo);
        return this;
    }

    public DateUtil retornarDiferencaEntreDatas() {

        if (this.data1 == null || this.data2 == null) {
            this.dataNumerico = null;
            return this;
        }

        Calendar calendarInicio = Calendar.getInstance();
        calendarInicio.setTime(this.data1);

        Calendar calendarFim = Calendar.getInstance();
        calendarFim.setTime(this.data2);

        long tempo = 1L;

        tempo = (this.data2.getTime() - this.data1.getTime()) / 1000L;

        tempo = (tempo < 0L) ? 0L : tempo;

        this.dataNumerico = Long.valueOf(tempo);

        if (this.dataNumerico != null && this.dataNumerico.longValue() > 0)
            this.dataTexto = converterSegundosToHorasMinutosSegundos(this.dataNumerico.longValue());
        else
            this.dataTexto = null;

        return this;
    }

    public DateUtil retornarDiferencaEntreDatasScala() {

        if (this.data1 == null || this.data2 == null) {
            this.dataNumerico = null;
            return this;
        }

        Calendar calendarInicio = Calendar.getInstance();
        calendarInicio.setTime(this.data1);

        Calendar calendarFim = Calendar.getInstance();
        calendarFim.setTime(this.data2);

        long tempo = 1L;

        tempo = (this.data2.getTime() - this.data1.getTime()) / 1000L;

        tempo = (tempo < 0L) ? 0L : tempo;

        this.dataNumerico = Long.valueOf(tempo);

        if (this.dataNumerico != null && this.dataNumerico.longValue() > 0)
            this.dataTexto = converterSegundosToHorasMinutosSegundosEscala(this.dataNumerico.longValue());
        else
            this.dataTexto = null;

        return this;
    }

    private String converterSegundosToHorasMinutosSegundosEscala(Long segundos) {

        try {

            if (segundos == null)
                return null;

            Long segundo = segundos % 60;
            Long minutos = segundos / 60;
            Long minuto = minutos % 60;
            Long hora = minutos / 60;

            StringBuilder builder = new StringBuilder();

            if (hora > 0) {

                builder.append(hora + "h ");

            }

            if (minuto > 0) {
                builder.append(minuto + "m ");
            }

            builder.append(segundo + "s");

            return builder.toString();

        } catch (Exception e) {
            return null;
        }

    }

    private String converterSegundosToHorasMinutosSegundos(Long segundos) {

        try {

            if (segundos == null)
                return null;

            Long segundo = segundos % 60;
            Long minutos = segundos / 60;
            Long minuto = minutos % 60;
            Long hora = minutos / 60;

            return String.format("%02d:%02d:%02d", hora, minuto, segundo);

        } catch (Exception e) {
            return null;
        }

    }

    /**
     * Metodo formata Data para String
     *
     * @param String - pattern
     * @return DateUtil - dataTexto
     */
    public DateUtil formatarDataParaString(String formato) {

        try {

            if (this.data == null || StringUtils.isEmpty(formato)) {
                this.dataTexto = null;
                return this;
            }

            SimpleDateFormat df = new SimpleDateFormat(formato);
            this.dataTexto = df.format(this.data);

            return this;

        } catch (Exception e) {
            this.dataTexto = null;
            return this;
        }
    }

    public DateUtil formatarDataParaISO8601() {

        try {

            if (StringUtils.isBlank(this.dataTexto)) {
                this.dataTexto = null;
                return this;
            }

            this.data = Date.from(ZonedDateTime.parse(this.dataTexto).toInstant());
            return this;

        } catch (Exception e) {
            this.dataTexto = null;
            return this;
        }
    }

    public DateUtil retornarDataParaO(Integer dia) {
        if (dia == null) {
            this.data = null;
            return this;
        }

        Calendar dataAtual = Calendar.getInstance();
        dataAtual.set(5, dia.intValue());

        this.data = dataAtual.getTime();

        return this;
    }

    public DateUtil formatarStringParaData(String formato) {
        try {
            if (StringUtils.isEmpty(this.dataTexto) || StringUtils.isEmpty(formato)) {

                this.data = null;
                return this;
            }

            SimpleDateFormat dateFormato = new SimpleDateFormat(formato);
            this.data = dateFormato.parse(this.dataTexto);

            return this;

        } catch (Exception e) {
            this.data = null;
            return this;
        }
    }

    public DateUtil formatarStringParaData() throws ParseException {

        String formato = determinarFormato();

        if (StringUtils.isEmpty(formato)) {

            this.data = null;

            return this;
        }
        return formatarStringParaData(formato);
    }

    public String diferencaDatasEmHora() {

        long diferenca = Math.abs(this.data1.getTime() - this.data2.getTime()) / 1000L;

        String formato = String.format("%%0%dd", new Object[]{Integer.valueOf(2)});
        String segundo = String.format(formato, new Object[]{Long.valueOf(diferenca % 60L)});
        String minuto = String.format(formato, new Object[]{Long.valueOf(diferenca % 3600L / 60L)});
        String hora = String.format(formato, new Object[]{Long.valueOf(diferenca / 3600L)});
        String tempo = hora + ":" + minuto + ":" + segundo;

        return tempo;
    }

    public DateUtil diferencaDatasMes() {
        if (this.data1 == null || this.data2 == null) {
            this.dataNumerico = null;
            return this;
        }

        if (this.data1.after(this.data2)) {
            this.dataNumerico = Integer.valueOf(0);
            return this;
        }

        Calendar calendarInicio = new GregorianCalendar();
        calendarInicio.setTime(this.data1);

        Calendar calendarFim = new GregorianCalendar();
        calendarFim.setTime(this.data2);

        int diferencaAnos = calendarFim.get(1) - calendarInicio.get(1);

        this.dataNumerico = Integer.valueOf(diferencaAnos * 12 + calendarFim.get(2) - calendarInicio.get(2));

        return this;
    }

    public DateUtil encontrarMenorData() {
        if (this.data1.before(this.data2)) {
            this.data = this.data1;
        } else {
            this.data = this.data2;
        }

        return this;
    }

    public DateUtil encontrarMaiorData() {
        if (this.data1.after(this.data2)) {
            this.data = this.data1;
        } else {
            this.data = this.data2;
        }

        return this;
    }

    public DateUtil retornarSaudacao() {
        if (this.data == null) {
            this.dataTexto = null;
            return this;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.data);
        if (calendar.get(11) < 12) {
            this.dataTexto = "Bom dia";
        } else if (calendar.get(11) >= 12 && calendar.get(11) <= 17) {
            this.dataTexto = "Boa tarde";
        } else {
            this.dataTexto = "Boa noite";
        }
        return this;
    }

    public DateUtil retornarHora() throws ParseException {
        this.dataTexto = formatarDataParaString("HH:mm:ss").getDataTexto();

        return formatarStringParaData("HH:mm:ss");
    }

    public DateUtil formatarHora() {
        if (StringUtils.isEmpty(this.dataTexto)) {
            return this;
        }
        while (this.dataTexto.length() < 6) {
            this.dataTexto = "0" + this.dataTexto;
        }

        String hora = this.dataTexto.substring(0, 2);
        String minuto = this.dataTexto.substring(2, 4);
        String segundo = this.dataTexto.substring(4, 6);

        this.dataTexto = hora + ":" + minuto + ":" + segundo;
        return this;
    }

    public Integer retornarDiferencaEntreDatasEmMeses() {
        if (this.data1.after(this.data2)) {
            return Integer.valueOf(0);
        }
        Calendar calendarInicio = Calendar.getInstance();
        calendarInicio.setTime(this.data1);

        Calendar calendarFim = Calendar.getInstance();
        calendarFim.setTime(this.data2);

        int diferencaAno = calendarFim.get(1) - calendarInicio.get(1);
        int diferencaMes = diferencaAno * 12 + calendarFim.get(2) - calendarInicio.get(2);

        return Integer.valueOf(diferencaMes);
    }

    public DateUtil converterStringHorarioParaDouble() {
        if (StringUtils.isEmpty(this.dataTexto)) {
            this.dataNumerico = null;
            return this;
        }

        String[] horario = this.dataTexto.split("[:]");

        if (horario.length == 3) {
            double hora = horario[0].equals("00") ? 0.0D : (Double.valueOf(horario[0]).doubleValue() * 60.0D);
            double minuto = Double.valueOf(horario[1]).doubleValue();
            double segundo = horario[2].equals("00") ? 0.0D
                    : NumeroUtil.builder(Double.valueOf(Double.valueOf(horario[2]).doubleValue() / 60.0D))
                    .arredondarNumero(Integer.valueOf(1)).getNumero().doubleValue();

            this.dataNumerico = Double.valueOf(hora + minuto + segundo);
        } else {
            this.dataNumerico = Double.valueOf(0.0D);
        }

        return this;
    }

    public String determinarFormato() {

        for (FormatoDataEnum dataFormato : FormatoDataEnum.values()) {

            if (this.dataTexto.toLowerCase().matches(dataFormato.getExpressaoRegular())) {

                return dataFormato.getFormato();
            }
        }

        return null;
    }

    public int retornarQuantidadeDiasUteis() {

        Date primeiroDia = retornarDataPrimeiroDiaMes().getData();
        Date ultimoDia = retornarDataUltimoDiaMes().getData();

        int quantidadeDiasUteis = 0;
        Calendar calendar = Calendar.getInstance();

        while (primeiroDia.before(ultimoDia)) {
            calendar.setTime(primeiroDia);

            if (calendar.get(7) != 1 && calendar.get(7) != 7) {
                quantidadeDiasUteis++;
            }

            calendar.add(5, 1);
            primeiroDia = calendar.getTime();
        }

        return quantidadeDiasUteis;
    }

    public XMLGregorianCalendar convertDataToXmlGregorian() throws DatatypeConfigurationException {

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(this.data);

        return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
    }

    public DateUtil retornarDataEmissaoExtenso() {

        if (this.data == null) {
            this.dataTexto = null;
            return this;
        }

        StringBuilder dataExtenso = new StringBuilder();

        Calendar calendario = Calendar.getInstance();
        calendario.setTime(this.data);

        Locale localizacao = new Locale("pt", "BR");

        dataExtenso.append(calendario.getDisplayName(7, 2, localizacao));
        dataExtenso.append(", " + Integer.toString(calendario.get(5)));
        dataExtenso.append(" de " + calendario.getDisplayName(2, 2, localizacao).toLowerCase());
        dataExtenso.append(" de " + Integer.toString(calendario.get(1)));
        this.dataTexto = dataExtenso.toString();
        return this;
    }

    public DateUtil retornarAnoMesReferenciaFormatado() {
        MesEnum mesReferencia = MesEnum
                .getMesPorValor(Integer.valueOf(retornarTempoData(DataEnum.MES).getDataNumerico().intValue()));
        int anoReferencia = retornarTempoData(DataEnum.ANO).getDataNumerico().intValue();
        this.dataNumerico = retornarAnoMesReferenciaFormatado(mesReferencia, Integer.valueOf(anoReferencia))
                .getDataNumerico();
        return this;
    }

    public DateUtil retornarAnoMesReferenciaFormatado(MesEnum mesReferencia, Integer anoReferencia) {
        if (mesReferencia == null || anoReferencia == null) {
            this.dataNumerico = null;
            return this;
        }
        String mesAnoReferencia = anoReferencia.toString();

        if (mesReferencia.getValor().intValue() >= 1 && mesReferencia.getValor().intValue() <= 9) {
            mesAnoReferencia = mesAnoReferencia + "0" + mesReferencia.getValor();
        } else {
            mesAnoReferencia = mesAnoReferencia + mesReferencia.getValor();
        }
        this.dataNumerico = Integer.valueOf(Integer.parseInt(mesAnoReferencia));
        return this;
    }

    public DateUtil formatarAnoMesReferenciaComBarra() {
        if (StringUtils.isEmpty(this.dataTexto)) {
            return this;
        }
        this.dataTexto = this.dataTexto.substring(4) + "/" + this.dataTexto.substring(0, 4);
        return this;
    }

    public DateUtil retornarMesAPartirAnoMesFormatado() {
        if (StringUtils.isEmpty(this.dataTexto)) {
            this.dataNumerico = null;
            return this;
        }
        String mes = this.dataTexto.substring(0, 2);
        this.dataNumerico = Integer.valueOf(Integer.parseInt(mes));
        return this;
    }

    public DateUtil retornarAnoAPartirAnoMesFormatado() {
        if (StringUtils.isEmpty(this.dataTexto)) {
            this.dataNumerico = null;
            return this;
        }
        String ano = this.dataTexto.substring(3);
        this.dataNumerico = Integer.valueOf(Integer.parseInt(ano));
        return this;
    }

    public DateUtil retornarDataParaDia(Integer dia) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.data);
        calendar.set(5, dia.intValue());

        this.data = calendar.getTime();

        return this;
    }

    public DateUtil retornarMesFormatado() {
        if (this.data == null) {
            this.dataTexto = null;
            return this;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.data);

        String mes = String.valueOf(calendar.get(2) + 1);

        if (mes.length() < 2) {
            mes = "0" + mes;
        }
        this.dataTexto = mes;

        return this;
    }

    public DateUtil retornarDataPrimeiroDiaSemana() {

        if (this.data == null) {
            return this;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.data);
        int diasRemovidos = calendar.get(7);
        this.data = removerTempoData(DataEnum.DIA, Integer.valueOf(diasRemovidos - 1)).getData();
        return this;
    }

    public DateUtil retornarDataUltimoDiaSemana() {
        if (this.data == null) {
            return this;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.data);
        int diasAdicionados = 7 - calendar.get(7);
        this.data = adicionarTempoData(DataEnum.DIA, Integer.valueOf(diasAdicionados)).getData();
        return this;
    }

    public DateUtil converterDataUtcBrasil() {
        if (this.data == null) {
            return this;
        }
        ZoneId zonaBrasil = ZoneId.of("America/Sao_Paulo");
        ZonedDateTime agoraBrasil = ZonedDateTime.now(zonaBrasil);
        this.data = Date.from(agoraBrasil.toInstant());
        return this;
    }

    public DateUtil converterStringTimeZoneDate(String dataTimeZone) {

        if (StringUtils.isNotBlank(this.dataTexto))
            return this;

        try {
            this.dataTexto = dataTimeZone;


            DateTimeFormatter parse = ISODateTimeFormat.dateTimeParser();
            DateTime dateTime = parse.parseDateTime(this.dataTexto);
            this.data = dateTime.toDate();

        } catch (Exception e) {
            // TODO: handle exception

        }

        return this;

    }

    public static void main(String[] args) {

        System.out.println(DateUtil.builder().converterDataUtcBrasil().getData());
    }
}
