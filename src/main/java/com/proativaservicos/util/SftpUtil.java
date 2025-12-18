package com.proativaservicos.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.proativaservicos.util.constantes.TipoLoginSftpEnum;
import jakarta.ejb.Asynchronous;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DefaultTreeNode;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.proativaservicos.exception.ProativaException;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.SftpProgressMonitor;

public class SftpUtil {

    private String host;

    private String usr;

    private String psw;

    private int porta;

    private Session session;

    private Channel channel;

    private ChannelSftp sftp;

    private JSch jsch;

    private TipoLoginSftpEnum tipoLoginSftp;

    private File arquivo;

    public SftpUtil(String host, String usr, String psw, int porta) {

        this.host = host;
        this.usr = usr;
        this.psw = psw;
        this.porta = porta;
        this.tipoLoginSftp = TipoLoginSftpEnum.NORMAL;
    }

    public SftpUtil(String host, String usr, File arquivo, int porta) {

        this.host = host;
        this.usr = usr;
        this.arquivo = arquivo;
        this.porta = porta;
        this.tipoLoginSftp = TipoLoginSftpEnum.ARQUIVO;
    }


    public SftpUtil() {
    }


    public static SftpUtil builder(String host, String usr, String psw, int porta) {

        return new SftpUtil(host, usr, psw, porta);
    }

    public static SftpUtil builder(String host, String usr, File arquivo, int porta) {

        return new SftpUtil(host, usr, arquivo, porta);
    }


    public void conectar() throws ProativaException {

        if (this.session == null) {

            try {

                this.jsch = new JSch();
                this.session = jsch.getSession(this.usr, this.host, this.porta);

                autenticar();

                java.util.Properties config = new java.util.Properties();
                config.put("StrictHostKeyChecking", "no");
                this.session.setConfig(config);
                this.session.connect();
                this.channel = session.openChannel("sftp");
                this.channel.connect();
                this.sftp = (ChannelSftp) channel;

            } catch (Exception e) {
                e.printStackTrace();
                throw new ProativaException("Erro ao conectar sftp: " + this.host);
            }
        }

    }

    public void autenticar() throws ProativaException, JSchException {

        if (tipoLoginSftp == TipoLoginSftpEnum.ARQUIVO) {

            if (arquivo == null || !arquivo.exists() || !arquivo.isFile()) {
                throw new ProativaException("O arquivo de autenticação não foi encontrado");
            }

            this.jsch.addIdentity(arquivo.getAbsolutePath());

        } else {
            this.session.setPassword(this.psw);
        }
    }

    public List<LsEntry> listarDiretorio(String diretorio) throws ProativaException {

        List<LsEntry> listFiles = null;

        try {

            if (StringUtils.isNotBlank(diretorio) && this.session != null) {

                this.sftp.cd(diretorio);
                Vector<ChannelSftp.LsEntry> files = this.sftp.ls(diretorio);

                listFiles = new ArrayList<LsEntry>(files);

            }

        } catch (Exception e) {

            e.printStackTrace();
            this.session.disconnect();
            throw new ProativaException("Não foi possivel listar o diretório: " + diretorio);
        } finally {

            if (this.session != null)
                this.session.disconnect();

            if (this.channel != null)
                this.channel.disconnect();
        }

        return listFiles;
    }


    public void desconectar() {

        if (session != null) {
            this.session.disconnect();
        }

    }

    public boolean verificarConexao() {

        if (this.sftp != null) {

            return this.sftp.isConnected();
        }

        return false;
    }


    public void enviarSftp(File rar, String dir) throws SftpException, FileNotFoundException {

        if (rar != null && rar.isFile() && rar.exists() && this.session != null) {

            if (!this.sftp.pwd().equals(dir)) {

                this.sftp.cd(dir);

            }

            this.sftp.put(new FileInputStream(rar), rar.getName(), ChannelSftp.APPEND);
        }

    }

    public void deletarArquivo(String diretorio, String fileName) throws ProativaException {

        try {

            if (StringUtils.isBlank(fileName))
                throw new ProativaException("Nenhum arquivo foi informado.");

            this.sftp.cd(diretorio);

            this.sftp.rm(fileName);


        } catch (Exception e) {

            e.printStackTrace();
            throw new ProativaException("Não foi possivel remover  o arquivo: " + fileName);
        }
    }

    public void renomearArquivo(String diretorio, String oldFile, String newFile) throws ProativaException {

        try {

            if (StringUtils.isAllBlank(oldFile) || StringUtils.isBlank(newFile))
                throw new ProativaException("Informe os arquivos.");

            this.sftp.cd(diretorio);

            this.sftp.rename(oldFile, newFile);


        } catch (Exception e) {

            e.printStackTrace();
            throw new ProativaException("Não foi possivel renomear  o arquivo: " + oldFile);
        }
    }


    public static void main(String[] args) {

        SftpUtil sftp = SftpUtil.builder("sftp.bancomaxima.com.br", "sftp-externo-dracmatelemarketing", new File("C:\\Users\\Rodrigo\\Documents\\sftp-externo-dracmatelemarketing.pem"), 22);

        try {

            sftp.conectar();
            //   List<LsEntry> list = sftp.listarDiretorio("/sftp-externo-dracmatelemarketing");
            sftp.enviarSftp(new File("C:\\Users\\Rodrigo\\Downloads\\SEG_loja.52586_20250710.rar"), "/sftp-externo-dracmatelemarketing");
            //  list.forEach(System.out::println);
            sftp.desconectar();

        } catch (ProativaException e) {

            e.printStackTrace();
        } catch (SftpException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public class SftpMonitor implements SftpProgressMonitor {

        @Override
        public void init(int op, String src, String dest, long max) {

            System.out.println("ENVIANDO: " + op);
            System.out.println("ENVIANDO: " + max);
        }

        @Override
        public boolean count(long count) {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public void end() {


        }

    }

    public String getHost() {
        return host;
    }


    public void setHost(String host) {
        this.host = host;
    }


    public String getUsr() {
        return usr;
    }


    public void setUsr(String usr) {
        this.usr = usr;
    }


    public String getPsw() {
        return psw;
    }


    public void setPsw(String psw) {
        this.psw = psw;
    }


}
