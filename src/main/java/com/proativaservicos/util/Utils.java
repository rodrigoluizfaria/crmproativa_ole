package com.proativaservicos.util;

import com.proativaservicos.exception.ProativaException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Utils {


    public static boolean isInteiro(String s) {

        boolean d = true;

        if (StringUtils.isBlank(s))
            return false;

        for (int i = 0; i < s.length(); i++) {
            // verifica se o char não é um dígito
            if (!Character.isDigit(s.charAt(i))) {
                return false;

            }
        }

        return d;

    }

    public static boolean ehNumero(String str) {

        if (str == null || str.isEmpty()) {
            return false;
        }

        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public static boolean isNotInteiro(String s) {

        boolean d = true;

        if (StringUtils.isBlank(s))
            return true;

        for (int i = 0; i < s.length(); i++) {
            // verifica se o char não é um dígito
            if (!Character.isDigit(s.charAt(i))) {
                return true;

            }
        }

        return d;

    }

    public static String acertarCPF(String CPF) {

        if (CPF == null || CPF.isEmpty())
            return CPF;

        CPF = CPF.replace(".", "");
        CPF = CPF.replace("-", "");

        return StringUtils.leftPad(CPF, 11, "0");
    }


    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {

        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static List<Long> converterLong(List<Long> lista) {

        return (List<Long>) CollectionUtils.collect(lista, new Transformer() {
            public Long transform(Object object) {

                if (object instanceof Integer)

                    return Long.valueOf(((Integer) object).longValue());

                else
                    return Long.valueOf(((BigInteger) object).longValue());
            }
        });
    }

    public static boolean validaCPF(String cpf) {
        cpf = remove(cpf);

        if (cpf == null || cpf.length() != 11 || isCPFPadrao(cpf))
            return false;

        try {
            Long.parseLong(cpf);
        } catch (NumberFormatException e) { // CPF não possui somente números
            return false;
        }

        return calcDigVerif(cpf.substring(0, 9)).equals(cpf.substring(9, 11));
    }

    /**
     * @param cpf String valor a ser testado
     * @return boolean indicando se o usuário entrou com um CPF padrão
     */
    private static boolean isCPFPadrao(String cpf) {
        if (cpf.equals("11111111111") || cpf.equals("22222222222") || cpf.equals("33333333333")
                || cpf.equals("44444444444") || cpf.equals("55555555555") || cpf.equals("66666666666")
                || cpf.equals("77777777777") || cpf.equals("88888888888") || cpf.equals("99999999999")) {

            return true;
        }

        return false;
    }

    @SuppressWarnings("deprecation")
    private static String calcDigVerif(String num) {
        Integer primDig, segDig;
        int soma = 0, peso = 10;
        for (int i = 0; i < num.length(); i++)
            soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;

        if (soma % 11 == 0 | soma % 11 == 1)
            primDig = new Integer(0);
        else
            primDig = new Integer(11 - (soma % 11));

        soma = 0;
        peso = 11;
        for (int i = 0; i < num.length(); i++)
            soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;

        soma += primDig.intValue() * 2;
        if (soma % 11 == 0 | soma % 11 == 1)
            segDig = new Integer(0);
        else
            segDig = new Integer(11 - (soma % 11));

        return primDig.toString() + segDig.toString();
    }

    public static String remove(String CPF) {

        CPF = CPF.replace(".", "");
        CPF = CPF.replace("-", "");

        return CPF;
    }

    public static String encodeFileToBase64Binary(File file) throws IOException {

        byte[] bytes = loadFile(file);
        byte[] encoded = org.apache.commons.codec.binary.Base64.encodeBase64(bytes);
        String encodedString = new String(encoded);

        return encodedString;
    }

    private static byte[] loadFile(File file) throws IOException {

        byte[] bytes;

        try (InputStream is = new FileInputStream(file)) {

            long length = file.length();

            if (length > Integer.MAX_VALUE) {

                throw new IOException("Arquivo maior que o permitido: " + file.getName());
            }

            bytes = new byte[(int) length];
            int offset = 0;
            int numRead = 0;

            while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }

            if (offset < bytes.length) {

                throw new IOException("Não foi possivel ler o arquivo: " + file.getName());

            }
        }

        return bytes;
    }


    public static File compactarRar(List<File> listFiles, File diretorioRar, String strFileRar) throws InterruptedException, IOException {

        File rar = new File(diretorioRar.getAbsolutePath() + File.separator + strFileRar + (StringUtils.endsWithIgnoreCase(strFileRar, "rar") ? "" : ".rar"));

        if (rar.exists())
            rar.delete();

        StringBuilder builder = new StringBuilder();

        for (File file : listFiles) {
            builder.append(" " + file.getAbsolutePath());


        }

        Runtime.getRuntime().exec(retornarRarSo() + " a  -ep " + rar.getAbsolutePath() + builder.toString()).waitFor();

        if (rar.exists()) {
            return rar;
        }


        return null;

    }

    public static File compactarArquivoZip(List<File> listFiles, File diretorioRar, String strFileZipe) throws ProativaException {

        if (CollectionUtils.isEmpty(listFiles) || StringUtils.isBlank(strFileZipe))
            throw new ProativaException("Nenhum arquivo encontrado.");

        File zip = new File(diretorioRar.getAbsolutePath() + File.separator + strFileZipe + (StringUtils.endsWithIgnoreCase(strFileZipe, "zip") ? "" : ".zip"));

        try {

            FileOutputStream fos = new FileOutputStream(diretorioRar.getAbsolutePath() + File.separator + strFileZipe + (StringUtils.endsWithIgnoreCase(strFileZipe, "zip") ? "" : ".zip"));
            ZipOutputStream zos = new ZipOutputStream(fos);

            for (File file : listFiles) {

                try (FileInputStream fis = new FileInputStream(file)) {

                    ZipEntry zipEntry = new ZipEntry(file.getName());
                    zos.putNextEntry(zipEntry);

                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fis.read(buffer)) >= 0) {
                        zos.write(buffer, 0, length);
                    }

                    zos.closeEntry();
                }


            }
            zos.close();

        } catch (Exception e) {

            e.printStackTrace();
            throw new ProativaException("Ops, Ocorreu um erro inesperado.");
        }
        System.out.println("RETORNANDO O ZIP:  "+zip.getAbsolutePath());
        return zip;

    }

    public static File converterAudioMp3Format16(File src, File dst) {

        try {

            if (src == null || !src.exists() || !src.isFile())
                return null;

            // Runtime.getRuntime().exec(retornarffmpegSo() + " -y -i  " + src.getAbsolutePath() + " -ac 2 -ab 16k  " + dst.getAbsolutePath()).waitFor();
            Runtime.getRuntime().exec(retornarffmpegSo() + " -y -i  " + src.getAbsolutePath() + " -codec:a libmp3lame -b:a 96k  " + dst.getAbsolutePath()).waitFor();

            if (dst != null && dst.exists() && dst.isFile())
                return dst;

        } catch (Exception e) {

            return null;

        }

        return null;

    }

    private static String retornarRarSo() {


        String osName = System.getProperty("os.name");

        if (osName.contains("Windows")) {

            return "C:\\Program Files\\WinRAR\\Rar.exe";

        } else {

            return "/usr/local/bin/rar";
        }
    }


    private static String retornarffmpegSo() {


        String osName = System.getProperty("os.name");

        if (osName.contains("Windows")) {
            return "C:\\Program Files\\ffmpeg\\bin\\ffmpeg";

        } else {
            return "/usr/bin/ffmpeg";
        }
    }

    /**
     * @param horas
     * @return Long em segundos
     */
    public static Long converterHorasMinutosSegundosToSegundos(String horas) {

        try {

            if (StringUtils.isBlank(horas))
                return null;

            String ho[] = horas.split(":");

            Long segundos = (Long.parseLong(ho[0]) * 3600) + (Long.parseLong(ho[1]) * 60) + (Long.parseLong(ho[2]));


            return segundos;


        } catch (Exception e) {
            return null;
        }

    }

    public static String converterSegundosToHorasMinutosSegundos(Long segundos) {

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

    public static boolean isJSON(String json) {

        try {

            new JSONObject(json);
            return true;

        } catch (Exception e) {
            return false;
        }
    }


    public static boolean isArrayJSON(String json) {

        try {

            new JSONArray(json);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public static Long calcularTempoGasto(Long time) {

        if (time == null)
            return null;

        return ((System.currentTimeMillis() - time) / 1000);

    }

    public static String tratarStringLf(String str) {

        String dado = "";

        if (StringUtils.isNotBlank(str)) {

            dado = Utils.removerAcentos(str.trim().replaceAll(System.getProperty("line.separator"), "").replaceAll("\\r", "").replaceAll("\\n", "")).replaceAll("\\t", "");
            dado = StringUtils.trimToEmpty(dado);
        }

        return dado;

    }


    public static String tratarStringLfCsv(String str) {

        String dado = "";

        if (StringUtils.isNotBlank(str)) {

            dado = Utils.removerAcentos(str.trim().replaceAll(System.getProperty("line.separator"), "").replaceAll("\\r", "").replaceAll("\\n", "")).replaceAll("\\t", "").replaceAll(";", "").replaceAll(",", "");
            dado = StringUtils.trimToEmpty(dado);
        }

        return dado;

    }

    public static String removerAcentos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }


    public static String acertarCodificacaoString(String texto) {


        if (StringUtils.isBlank(texto))
            return texto;

        return new String(texto.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

    }

    public static void main(String[] args) throws InterruptedException {

        System.out.println(StringUtils.trimToEmpty(removerAcentos("LEDA DA SILVA VIEIRA                                        ")).replaceAll("\\t", ""));
        System.out.println(acertarCodificacaoString("Limite cartÃ£o nÃ£o disponive"));
        System.out.println(validaCPF("00000000005"));

        File a = new File("C:\\Users\\Rodrigo\\Downloads\\LIF_39540_12340000_43275516787_20250712164031_1_1.mp3");
        File b = new File("C:\\Users\\Rodrigo\\Downloads\\SEG_39540_03355454249_20250712161526_1_1_222221111.mp3");
        File c = new File("C:\\Users\\Rodrigo\\Downloads\\SEG_39540_07462852721_20250712161030_1_1_87745.mp3");
        List<File> lista = new ArrayList<File>();
        lista.add(a);
        lista.add(b);
        lista.add(c);
        try {
            File zip = compactarArquivoZip(lista,new File("C:\\Users\\Rodrigo\\Downloads"),"20250582.zip");
        } catch (ProativaException e) {
            throw new RuntimeException(e);
        }
    }


    public static String getNumeroRandomico(){
        SecureRandom random = new SecureRandom();


        int numero = random.nextInt(100);


        String numeroFormatado = String.format("%02d", numero);

        return numeroFormatado;
    }

}
