package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoConciliarAudioAnexo;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.ConciliarAudioAnexo;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.Date;
import java.util.List;


@Stateless
public class ConcilicarAudioAnexoService extends GenericProService<ConciliarAudioAnexo> {

    @Inject
    private DaoConciliarAudioAnexo dao;

    @Override
    public GenericDao<ConciliarAudioAnexo> getDAO() {
        return (GenericDao<ConciliarAudioAnexo>) this.dao;
    }

    public void salvarConciliarAudio(String nomeclatura, String dir, Long idAtendimento) {
        this.dao.salvarConciliarAudio(nomeclatura, dir, idAtendimento);

    }

    public void salvarConciliarAudio(String nomeclatura, String dir, Long tamanhoArquivo, Long idAtendimento) {
        this.dao.salvarConciliarAudio(nomeclatura, dir, tamanhoArquivo, idAtendimento);

    }

    public void salvarConciliarAudio(String nomeclatura, String dir, Long tamanhoArquivo, Date dataAnexo, Long idAtendimento) {
        this.dao.salvarConciliarAudio(nomeclatura, dir, tamanhoArquivo, dataAnexo, idAtendimento);

    }

    public void salvarConciliarAudio(String nomeclatura, String nomeOriginal, String dir, Long tamanhoArquivo, Date dataAnexo, Long idAtendimento) {
        this.dao.salvarConciliarAudio(nomeclatura, nomeOriginal, dir, tamanhoArquivo, dataAnexo, idAtendimento);

    }

    public List<ConciliarAudioAnexo> pesquisarPorAtendimento(Long idAtendimento) {

        return this.dao.pesquisarPorAtendimento(idAtendimento);
    }

    public List<ConciliarAudioAnexo> pesquisarPorAtendimentos(List<Long> listIds) {

        return this.dao.pesquisarPorAtendimentos(listIds);
    }


    public void excluir(Long audio) {
        this.dao.excluir(audio);
    }

    public void alterarNomeArquivo(Long id, String nomeArquivo, String diretorio, Date data) {
        this.dao.alterarNomeArquivo(id,nomeArquivo,diretorio,data);
    }

    public void salvarTranscricao(Long id, String texto) {
        this.dao.salvarTranscricao(id,texto);
    }

    public void salvarTranscricao(Long id, String texto,Integer codigoAudio ) {
        this.dao.salvarTranscricao(id,texto,codigoAudio);
    }

    public ConciliarAudioAnexo pesquisarConciliar(Long id) {
        return this.dao.pesquisarConciliar(id);
    }
}
