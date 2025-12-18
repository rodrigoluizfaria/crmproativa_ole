package com.proativaservicos.model;


import com.proativaservicos.util.constantes.TipoLoginSftpEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

import com.proativaservicos.util.constantes.TipoAcessoEnum;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * Classe Domain para o SFTP
 *
 * @author rodrigo
 */

@Entity
@Table(name = "conciliar_sftp")
public class ConciliarSftp extends GenericControle {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Column(name = "descricao", length = 150, nullable = false)
    private String descricao;

    @Column(name = "url", length = 150, nullable = false)
    private String url;

    @Column(name = "usr", length = 50)
    private String usr;

    @Column(name = "psw", length = 50)
    private String psw;

    @Column(name = "porta", length = 5)
    private String porta;

    @Column(name = "diretorio", length = 50)
    private String diretorio;

    @Column(name = "arquivo_chave")
    private String arquivoChave;

    @Column(name = "ativo")
    @Enumerated(EnumType.STRING)
    private TipoAcessoEnum ativo;

    @Column(name = "tipo_login")
    @Enumerated(EnumType.STRING)
    private TipoLoginSftpEnum tipoLoginSftp;


    public ConciliarSftp() {

    }

    //c.id,c.descricao,c.url,c.usr
    public ConciliarSftp(Long id, String descricao, String url, String porta, String usr, String psw, String diretorio) {
        setId(id);
        this.descricao = descricao;
        this.url = url;
        this.usr = usr;
        this.psw = psw;
        this.porta = porta;
        this.diretorio = diretorio;


    }

    public String getDiretorio() {
        return diretorio;
    }

    public void setDiretorio(String diretorio) {
        this.diretorio = diretorio;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public TipoAcessoEnum getAtivo() {
        return ativo;
    }

    public void setAtivo(TipoAcessoEnum ativo) {
        this.ativo = ativo;
    }


    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }

    public TipoLoginSftpEnum getTipoLoginSftp() {
        return tipoLoginSftp;
    }

    public void setTipoLoginSftp(TipoLoginSftpEnum tipoLoginSftp) {
        this.tipoLoginSftp = tipoLoginSftp;
    }

    public String getArquivoChave() {
        return arquivoChave;
    }


    public String getFileArquivoChave() {

        if (StringUtils.isNotBlank(arquivoChave)) {

            File file = new File(arquivoChave);

            if (file.exists()) {
                return file.getName();
            }
        }

        return "";
    }

    public void setArquivoChave(String arquivoChave) {
        this.arquivoChave = arquivoChave;
    }
}
