package com.proativaservicos.bean;

import com.proativaservicos.model.Usuario;
import com.proativaservicos.model.dto.RelatorioFiltroDto;
import com.proativaservicos.model.dto.RelatorioSacDto;
import com.proativaservicos.service.AtendimentoBackofficeService;
import com.proativaservicos.service.UsuarioService;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.constantes.PerfilUsuarioEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.collections4.CollectionUtils;
import org.omnifaces.util.Messages;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class RelatorioSacBean extends GenericBean {

    @Inject
    private AtendimentoBackofficeService service;

    @Inject
    private UsuarioService usuarioService;

    private RelatorioFiltroDto filtro = new RelatorioFiltroDto();

    private List<RelatorioSacDto> listaRelatorio;
    private List<Usuario> listaUsuarios;

    @PostConstruct
    public void init() {
        filtro.setDataInicio(DateUtil.builder(new Date()).retornarDataPrimeiroDiaMes().getData());
        filtro.setDataFim(DateUtil.builder(new Date()).retornarDataUltimoDiaMes().getData());

        this.listaUsuarios = usuarioService.pesquisarUsuariosPorNiveis(List.of(PerfilUsuarioEnum.OPERADOR), retornarUsuarioSessao().getId());
    }

    public void pesquisar() {

        this.listaRelatorio = service.pesquisarRelatorioDetalhado(filtro);

        if (CollectionUtils.isEmpty(listaRelatorio)) {
            Messages.addGlobalWarn("Nenhum registro encontrado para os filtros informados.");
        }
    }
    public void limpar() {
        this.filtro = new RelatorioFiltroDto();
        this.listaRelatorio = null;
        init();
    }
    public String getDataAtualFormatada() {
        return new SimpleDateFormat("yyyyMMdd_HHmm").format(new Date());
    }

    public RelatorioFiltroDto getFiltro() {
        return filtro;
    }

    public void setFiltro(RelatorioFiltroDto filtro) {
        this.filtro = filtro;
    }

    public List<RelatorioSacDto> getListaRelatorio() {
        return listaRelatorio;
    }

    public void setListaRelatorio(List<RelatorioSacDto> listaRelatorio) {
        this.listaRelatorio = listaRelatorio;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
}
