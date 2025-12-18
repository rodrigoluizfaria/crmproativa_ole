package com.proativaservicos.util;

import com.proativaservicos.model.ConciliarAudioAnexo;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TranscreverPython {


    public static String transcreverAudioAnexoApi(String arquivo) {

        File file = new File(arquivo);

        if (file.exists() && file.isFile()) {

            try {

                String apiUrl = "http://10.8.1.99:8000/transcribe/";

                // Criando a conexão
                HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=---BOUNDARY");

                // Preparando o arquivo para envio
                String boundary = "---BOUNDARY";
                String crlf = "\r\n"; // Linha nova
                String twoHyphens = "--";

                // Definindo o fluxo de saída
                OutputStream os = connection.getOutputStream();
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(os, "UTF-8"), true);

                // Início do corpo multipart
                writer.append(twoHyphens + boundary).append(crlf);
                writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"").append(crlf);
                writer.append("Content-Type: " + Files.probeContentType(Paths.get(arquivo))).append(crlf);
                writer.append(crlf).flush();

                // Enviando o arquivo
                Files.copy(file.toPath(), os);
                os.flush();

                // Finalizando o corpo multipart
                writer.append(crlf).flush();
                writer.append(twoHyphens + boundary + twoHyphens).append(crlf).flush();

                // Lendo a resposta da API
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Imprimindo a transcrição
                System.out.println("Resposta da API: " + response.toString());
                return response.toString();


            } catch (Exception e) {

                e.printStackTrace();
            }
        }

        return null;
    }

    public static String transcrever(String arquivo) {

        System.out.println("TranscreverPython Class: " + arquivo);

        if (StringUtils.isNotBlank(arquivo) && (new File(arquivo).exists() && new File(arquivo).isFile())) {

            try {

                ProcessBuilder builder = new ProcessBuilder("python", "E:\\transcrever.py", arquivo
                );

                builder.redirectErrorStream(true);
                Process process = builder.start();
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
                String linha;
                StringBuilder texto = new StringBuilder();

                while ((linha = reader.readLine()) != null) {
                    texto.append(linha);
                }
                System.out.println("texto: " + tratarTexto(texto.toString()));
                reader.close();

                return tratarTexto(texto.toString());

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }

        return null;

    }

    //ALTERAR A URL PERIODO DE TESTE....
    public static String buscarTranscricaoApi(Integer audioId) {

        if (audioId != null) {

            System.out.println("TranscreverApi Class: " + audioId);

            String apiUrl = "http://10.8.1.99:8000/transcription/" + audioId;

            try {
                HttpClient client = HttpClient.newHttpClient();

                // Criar a requisição GET
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(apiUrl))
                        .GET()
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                // Verificar o status da resposta
                if (response.statusCode() == 200) {
                    System.out.println("Resposta da API: " + response.body());
                    return tratarTexto(response.body());
                } else {
                    System.out.println("Erro: " + response.statusCode());
                    System.out.println("Detalhes: " + response.body());
                    return response.body();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        return null;

    }

    private static String tratarTexto(String texto) {

        int ultimaPosicao = texto.lastIndexOf(')');
        return texto.substring(ultimaPosicao + 1);


    }

    public static void main(String[] args) {
        System.out.println(tratarTexto("C:\\Users\\Rodrigo\\AppData\\Local\\Programs\\Python\\Python310\\lib\\site-packages\\whisper\\transcribe.py:132: UserWarning: FP16 is not supported on CPU; using FP32 instead  warnings.warn(\"FP16 is not supported on CPU; using FP32 instead\") conforme estávamos falando sem um Roberto Rincid de Unimenome Camille, sou correspondente bancária do Banco BMG. E vamos concluir a sua adesão do seguro, informa que a ligação está sendo gravada, por favor poderem me confirmar se este é o seu número de celulada DD219, é o 747-737-28. Sim. Ótimo, agora me confirme apenas os três primeiros números do seu CPF. Já do qual fluim? Excelente por último me confirme o mês do seu aniversário. O seguro BMG Médito, você está contratando te dará cobertura, em caso de mocha se dentar no valor de mil reais, além disso você ainda terá vários outros benefícios que virão, incluídos como sorteios no valor de 10 mil reais pela Loteria Federal, checape a noau gratuito com direito a uma consulta médica por ano, mais exame os básicos, totalmente gratuito. O senhor já fez a com checape a noau esse ano? Então, aí é oportunidade para o senhor se apazando checape, tá? Para os fluídios, todas as coberturas e benefícios do seguro, pelo perro de 12 meses, o seu pagará o valor de 29,90 poderá ser mensal, 29,90 cobrada em cada mês, ou parcelado do de parcelas de 29,90 de acordo com sua escolha. Os benefícios estarão disponíveis em até 24 horas após a aprovação do pagamento em seu cartão, a renovação de seguro será anual, sendo a primeira realizada de forma automática e as criazer mais serão feitas através da corretora de seguro, mediante seu consentimento. Caso tenha dúvidas, basta entrar em contato com o central BMG, no telefone 4020707, seu Roberto Rice Junho confirma a contratação do seguro BMG MediPlus nessa data 21 do 3 de 2025 no valor de 29,90. Comestos, o 12 meses de cobertura, que será lançado na fatura do seu cartão configurado BMG sem... Informamos que o BMG MediPlus é uma parceria em seu agentirário Brasil Seguros SAA e BMG Seguradores, as condições contratuais do seguro serão enviadas para o senhor por SNMS e as informações da corretagem estão disponíveis no sátiro Banco BMG. Lembrando que o produto não é um seguro de saúde, o serviço de assistência são complementares ao seguro de morte, acidental o gazecemos da confiança, tem uma excelente idade."));
    }

}
