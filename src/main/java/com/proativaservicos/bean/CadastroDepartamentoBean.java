package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.Departamento;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.service.DepartamentoService;
import com.proativaservicos.service.UsuarioService;
import com.proativaservicos.util.constantes.PerfilUsuarioEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import com.proativaservicos.util.constantes.TipoPaginaEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Messages;
import org.primefaces.model.DualListModel;

import java.util.*;

@Named
@ViewScoped
public class CadastroDepartamentoBean extends GenericBean {

    @Inject
    private DepartamentoService serviceDepartamento;

    @Inject
    private UsuarioService usuarioService;

    private Departamento departamento;

    private List<Usuario> listUsuariosAssociados;

    private List<Usuario> listUsuarios;

    private List<Departamento> listDepartamentos;

    private DualListModel<Usuario> dualListModel;

    private String descricao;

    private TipoAcessoEnum acesso;

    private TipoPaginaEnum tipoPaginaEnum;

    @PostConstruct
    public void init() {

        this.tipoPaginaEnum = TipoPaginaEnum.PESQUISA;
        pesquisar();

    }

    public void pesquisar() {

        this.listDepartamentos = this.serviceDepartamento.pesquisarDepartamentos(this.descricao, this.acesso);

    }

    public void salvar() {

        try {

            if (StringUtils.isBlank(this.departamento.getDescricao()))
                throw new ProativaException("Informe o nome do departamento");

            salvarDualList();

            if (this.departamento.getId() == null) {

                this.departamento.setDataCadastro(new Date());
                this.departamento.setDataAlteracao(new Date());


                this.departamento.setEmpresa(retornarEmpresaUsuarioSessao());
                inserir(this.departamento, true);
            } else {

                this.departamento.setDataAlteracao(new Date());
                alterar(this.departamento);
            }

            Messages.addGlobalInfo("Departamento salvo com sucesso!");

        } catch (ProativaException e) {
            Messages.addGlobalError(e.getMessage());
        }
    }

    private void salvarDualList() {

        List<Usuario> usuariosTarget = Optional.ofNullable(dualListModel.getTarget()).orElse(Collections.emptyList());

        this.departamento.setListUsuariosDepartamento(new ArrayList<>(usuariosTarget));
    }

    public void editar(Long idDepartamento) {

        this.departamento = this.serviceDepartamento.pesquisarDepartamentoPorId(idDepartamento);
        this.listUsuariosAssociados = new ArrayList<>();

        if (this.departamento != null && CollectionUtils.isNotEmpty(this.departamento.getListUsuariosDepartamento())) {
            this.listUsuariosAssociados = this.departamento.getListUsuariosDepartamento();
        }

        iniciarDualListModel();
        this.tipoPaginaEnum = TipoPaginaEnum.CADASTRO;


    }

    private void iniciarDualListModel() {

        this.listUsuarios = Optional.ofNullable(
                usuarioService.pesquisarUsuariosPorNiveis(
                        List.of(PerfilUsuarioEnum.OPERADOR_BACKOFFICE),
                        retornarEmpresaUsuarioSessao().getId()
                )
        ).orElse(Collections.emptyList());

        List<Usuario> source = new ArrayList<>(CollectionUtils.subtract(this.listUsuarios, this.listUsuariosAssociados));

        this.dualListModel = new DualListModel<>(source, this.listUsuariosAssociados);
    }

    public void novo() {
        this.departamento = new Departamento();
        this.listUsuariosAssociados = new ArrayList<>();
        iniciarDualListModel();
        this.tipoPaginaEnum = TipoPaginaEnum.CADASTRO;
    }

    public void voltar() {

        this.descricao = null;
        this.acesso = null;
        this.tipoPaginaEnum = TipoPaginaEnum.PESQUISA;
        pesquisar();


    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Usuario> getListUsuariosAssociados() {
        return listUsuariosAssociados;
    }

    public void setListUsuariosAssociados(List<Usuario> listUsuariosAssociados) {
        this.listUsuariosAssociados = listUsuariosAssociados;
    }

    public List<Usuario> getListUsuarios() {
        return listUsuarios;
    }

    public void setListUsuarios(List<Usuario> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    public List<Departamento> getListDepartamentos() {
        return listDepartamentos;
    }

    public void setListDepartamentos(List<Departamento> listDepartamentos) {
        this.listDepartamentos = listDepartamentos;
    }

    public DualListModel<Usuario> getDualListModel() {
        return dualListModel;
    }

    public void setDualListModel(DualListModel<Usuario> dualListModel) {
        this.dualListModel = dualListModel;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public TipoPaginaEnum getTipoPaginaEnum() {
        return tipoPaginaEnum;
    }

    public void setTipoPaginaEnum(TipoPaginaEnum tipoPaginaEnum) {
        this.tipoPaginaEnum = tipoPaginaEnum;
    }

    public TipoAcessoEnum getAcesso() {
        return acesso;
    }

    public void setAcesso(TipoAcessoEnum acesso) {
        this.acesso = acesso;
    }
}
