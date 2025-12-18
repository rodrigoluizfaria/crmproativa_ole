package com.proativaservicos.util;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.opencsv.CSVWriter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;


public class ArquivoUtil {

    public static byte[] gerarArquivoCSV(List<String> listCabecalho, List<Object[]> listDados) {
        // TODO Auto-generated method stub

        StringBuilder dadosArquivo = new StringBuilder();

        if (listCabecalho != null && !listCabecalho.isEmpty()) {

            dadosArquivo.append(
                    "".concat(listCabecalho.toString().substring(1).replaceAll("[,]", ";").replaceAll("]", ";\n")));

            if (listDados != null && !listDados.isEmpty()) {

                Object[] informacoes = listDados.get(0);

                if (informacoes[informacoes.length - 1] instanceof List) {

                    for (Object[] objeto : listDados) {

                        int indexLinha = 0;

                        Object[] dados = objeto;

                        for (Object linha : dados) {

                            if (linha instanceof List) {

                                boolean condicaoPrimeiraLinha = true;
                                List<Object[]> linhas = (List<Object[]>) linha;

                                for (Object[] subLinhas : linhas) {

                                    if (condicaoPrimeiraLinha) {

                                        for (int i = 0; i < subLinhas.length; i++)

                                            dadosArquivo.append(subLinhas[i].toString() + ";");
                                        dadosArquivo.append("\n");
                                        condicaoPrimeiraLinha = false;
                                        continue;
                                    }

                                    int index;

                                    for (index = 0; index < dados.length - 1; index++)
                                        dadosArquivo.append(";");

                                    for (index = 0; index < subLinhas.length; index++)
                                        dadosArquivo.append(subLinhas[index].toString() + ";");

                                    dadosArquivo.append("\n");
                                }

                            } else {

                                dadosArquivo.append(dados[indexLinha++].toString() + ";");
                            }
                        }
                    }

                } else {

                    for (Object[] dados : listDados) {

                        for (Object informacao : (Object[]) dados)
                            dadosArquivo.append((informacao == null) ? " ;" : (informacao.toString() + ";"));

                        dadosArquivo.append("\n");
                    }
                }
            }
        }

        return (new String(dadosArquivo.toString().getBytes(), StandardCharsets.ISO_8859_1)).getBytes();
    }

    public static String gerarArquivoCSVString(List<String> listCabecalho, List<Object[]> listDados) {
        // TODO Auto-generated method stub

        StringBuilder dadosArquivo = new StringBuilder();

        if (listCabecalho != null && !listCabecalho.isEmpty()) {

            dadosArquivo.append(
                    "".concat(listCabecalho.toString().substring(1).replaceAll("[,]", ";").replaceAll("]", ";\n")));

            if (listDados != null && !listDados.isEmpty()) {

                Object[] informacoes = listDados.get(0);

                if (informacoes[informacoes.length - 1] instanceof List) {

                    for (Object[] objeto : listDados) {

                        int indexLinha = 0;

                        Object[] dados = objeto;

                        for (Object linha : dados) {

                            if (linha instanceof List) {

                                boolean condicaoPrimeiraLinha = true;
                                List<Object[]> linhas = (List<Object[]>) linha;

                                for (Object[] subLinhas : linhas) {

                                    if (condicaoPrimeiraLinha) {

                                        for (int i = 0; i < subLinhas.length; i++)

                                            dadosArquivo.append(subLinhas[i].toString() + ";");
                                        dadosArquivo.append("\n");
                                        condicaoPrimeiraLinha = false;
                                        continue;
                                    }

                                    int index;

                                    for (index = 0; index < dados.length - 1; index++)
                                        dadosArquivo.append(";");

                                    for (index = 0; index < subLinhas.length; index++)
                                        dadosArquivo.append(subLinhas[index].toString() + ";");

                                    dadosArquivo.append("\n");
                                }

                            } else {

                                dadosArquivo.append(dados[indexLinha++].toString() + ";");
                            }
                        }
                    }

                } else {

                    for (Object[] dados : listDados) {

                        for (Object informacao : (Object[]) dados)
                            dadosArquivo.append((informacao == null) ? " ;" : (informacao.toString() + ";"));

                        dadosArquivo.append("\n");
                    }
                }
            }
        }

        return dadosArquivo.toString();
    }

    public static String gerarArquivoCSVStringSemCabecalho(List<Object[]> listDados) {
        // TODO Auto-generated method stub

        StringBuilder dadosArquivo = new StringBuilder();

        if (listDados != null && !listDados.isEmpty()) {

            Object[] informacoes = listDados.get(0);

            if (informacoes[informacoes.length - 1] instanceof List) {

                for (Object[] objeto : listDados) {

                    int indexLinha = 0;

                    Object[] dados = objeto;

                    for (Object linha : dados) {

                        if (linha instanceof List) {

                            boolean condicaoPrimeiraLinha = true;
                            List<Object[]> linhas = (List<Object[]>) linha;

                            for (Object[] subLinhas : linhas) {

                                if (condicaoPrimeiraLinha) {

                                    for (int i = 0; i < subLinhas.length; i++)

                                        dadosArquivo.append(subLinhas[i].toString() + ";");
                                    dadosArquivo.append("\n");
                                    condicaoPrimeiraLinha = false;
                                    continue;
                                }

                                int index;

                                for (index = 0; index < dados.length - 1; index++)
                                    dadosArquivo.append(";");

                                for (index = 0; index < subLinhas.length; index++)
                                    dadosArquivo.append(subLinhas[index].toString() + ";");

                                dadosArquivo.append("\n");
                            }

                        } else {

                            dadosArquivo.append(dados[indexLinha++].toString() + ";");
                        }
                    }
                }

            } else {

                int i = 1;
                for (Object[] dados : listDados) {

                    for (Object informacao : (Object[]) dados)
                        dadosArquivo.append((informacao == null) ? " ;" : (informacao.toString() + ";"));

                    if (i < listDados.size())
                        dadosArquivo.append("\n");

                    i++;
                }
            }
        }

        return dadosArquivo.toString();
    }

    public static byte[] gerarArquivoCSV(String[] cabecalho, List<String[]> valores) throws Exception {

        ByteArrayOutputStream byteArrayOutputStream = null;
        CSVWriter csvWriter = null;

        try {

            byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(239);
            byteArrayOutputStream.write(187);
            byteArrayOutputStream.write(191);
            csvWriter = new CSVWriter(new OutputStreamWriter(byteArrayOutputStream, StandardCharsets.UTF_8), ';',
                    CSVWriter.DEFAULT_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

            csvWriter.writeNext(cabecalho, false);
            csvWriter.writeAll(valores, false);
            csvWriter.flush();
            return byteArrayOutputStream.toByteArray();

        } catch (Exception e) {

            throw e;

        } finally {

            if (byteArrayOutputStream != null) {

                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();

            }

            if (csvWriter != null)
                csvWriter.close();
        }
    }

    public static void geraLogCsv(File fileCsv, String str) {

        if (!fileCsv.exists()) {

            try {

                fileCsv.createNewFile();

            } catch (IOException ex) {

                Logger.getLogger(ArquivoUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {

            FileWriter fw = new FileWriter(fileCsv, Charset.forName("ISO-8859-1"), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(str.trim());
            bw.newLine();
            bw.close();
            fw.close();

        } catch (IOException ex) {
            Logger.getLogger(ArquivoUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void escreverByteParaArquivo(String arquivo, String strDiretorio, byte[] bytes) {

        if (StringUtils.isNotBlank(arquivo) && StringUtils.isNotBlank(strDiretorio) && bytes != null) {

            try {

                File diretorio = new File(System.getProperty("user.home") + File.separator + "proativa" + File.separator + strDiretorio);

                if (!diretorio.exists()) {

                    Files.createDirectories(diretorio.toPath(), (FileAttribute<?>[]) new FileAttribute[0]);
                }

                FileUtils.writeByteArrayToFile(new File(diretorio, arquivo), bytes);

            } catch (IOException e) {

                System.out.println("Erro ao escrever o arquivo: [ " + e.getMessage() + " ]");
            }


        }

    }

    public static String obterExtensao(File arquivo) {
        // Obtém o nome do arquivo
        String nomeArquivo = arquivo.getName();

        // Verifica se o arquivo tem um ponto (.) no nome
        int indicePonto = nomeArquivo.lastIndexOf(".");

        // Se o ponto foi encontrado, a extensão está após ele
        if (indicePonto > 0 && indicePonto < nomeArquivo.length() - 1) {
            return nomeArquivo.substring(indicePonto + 1); // Retorna a extensão
        } else {
            return null; // Retorna null se não houver extensão
        }
    }

    public static String obterExtensao(String arquivo) {
        // Obtém o nome do arquivo

        try {

            String nomeArquivo = arquivo;

            // Verifica se o arquivo tem um ponto (.) no nome
            int indicePonto = nomeArquivo.lastIndexOf(".");

            // Se o ponto foi encontrado, a extensão está após ele
            if (indicePonto > 0 && indicePonto < nomeArquivo.length() - 1) {
                return nomeArquivo.substring(indicePonto + 1).trim(); // Retorna a extensão
            } else {
                return null; // Retorna null se não houver extensão
            }

        } catch (Exception e) {
            return "";
        }
    }

    public static File renomearArquivo(String origem, String destino) {

        // Caminho do arquivo original
        Path caminhoOriginal = Paths.get(origem);

        // Caminho para o novo arquivo
        Path caminhoRenomeado = Paths.get(destino);


        try {
            // Renomeando o arquivo


            Path arquivo = Files.move(caminhoOriginal, caminhoRenomeado);
            return arquivo.toFile();


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Falha ao renomear o arquivo: " + e.getMessage());
            return null;
        }
    }


}
