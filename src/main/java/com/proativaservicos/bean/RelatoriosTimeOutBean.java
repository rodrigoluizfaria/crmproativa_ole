package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.Equipe;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.service.AtendimentoService;
import com.proativaservicos.service.EquipeService;
import com.proativaservicos.service.UsuarioService;
import com.proativaservicos.util.constantes.MessagesEnum;
import com.proativaservicos.util.constantes.PerfilUsuarioEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.model.ListDataModel;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.collections4.CollectionUtils;
import org.omnifaces.util.Messages;
import org.primefaces.component.datatable.DataTable;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.*;

@Named
@ViewScoped
public class RelatoriosTimeOutBean extends GenericBean {

    private static final long serialVersionUID = 1L;

    @Inject
    private EquipeService serviceEquipe;

    @Inject
    private UsuarioService serviceUsuario;

    @Inject
    private AtendimentoService serviceAtendimento;


    private Usuario usuario;

    private Long idSupervisor;

    private Long idEquipe;

    private Long[] listIdUsuarios;

    private Long idCoordenador;

    private Long idEmpresa;


    private Date dataInicio;

    private Date dataFim;

    private String filtro;

    private List<String> listFiltros;

    private List<Usuario> listUsuarios;

    private List<Usuario> listSupervisores;

    private List<Usuario> listCoordenadores;

    private List<Equipe> listEquipes;

    private DataTable dataTablePersonalizada;

    private LinkedHashMap<String, List<Object[]>> mapRelatorio;

    private List<ColumnModel> columns;

    private ListDataModel<String[]> dataModel;

    private List<Object[]> listFinal;

    @PostConstruct
    public void init() {

        inicializarEmpresa();

        this.usuario = retornarUsuarioSessao();

        this.listFiltros = Arrays.asList("DIA", "MES", "SEMANA");

        this.filtro = "DIA";

        this.dataInicio = new Date();
        this.dataFim = new Date();

        this.idEmpresa = this.usuario.getEmpresa().getId();


        trocarEmpresa();

    }

    public void trocarEmpresa() {

        if (getEmpresa() == null) {

            this.listCoordenadores = null;
            this.listUsuarios = null;
            this.listSupervisores = null;
            this.listEquipes = null;

        } else if (this.usuario.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

            this.listEquipes = this.serviceEquipe.pesquisarEquipesPorSupervisor(this.usuario.getId(), getEmpresa().getId());

            this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorSupervisor(this.usuario.getId(), getEmpresa().getId());

            if (this.usuario.getCoordenador() != null) {

                this.listCoordenadores = new ArrayList<Usuario>();
                this.listCoordenadores.add(this.usuario.getCoordenador());
            }

        } else {

            this.listSupervisores = this.serviceUsuario.pesquisarUsuariosPorNiveis(Arrays.asList(PerfilUsuarioEnum.SUPERVISOR), getEmpresa().getId());

            this.listCoordenadores = this.serviceUsuario.pesquisarUsuariosPorNiveis(Arrays.asList(PerfilUsuarioEnum.COORDENADOR), getEmpresa().getId());

            this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorNiveis(Arrays.asList(PerfilUsuarioEnum.OPERADOR), getEmpresa().getId());

            this.listEquipes = this.serviceEquipe.pesquisarEquipesPorEmpresa(getEmpresa().getId(), TipoAcessoEnum.ATIVO);

        }

    }

    public void pesquisar() {

        try {

            if (this.dataInicio != null && this.dataFim != null && this.dataInicio.after(this.dataFim))
                throw new ProativaException(MessagesEnum.A_DATA_DE_INICIO_DEVE_SER_MENOR_OU_IGUAL_A_DATA_DE_TERMINO.constante);

            List<?> listRelatorioTimeOut = null;
            this.listFinal = null;
            this.mapRelatorio = null;

            switch (this.filtro) {

                case "SEMANA":
                    listRelatorioTimeOut = this.serviceAtendimento.pesquisarRelatorioTimeOut(Arrays.asList(this.listIdUsuarios), this.idSupervisor, this.idEquipe, this.idCoordenador, this.idEmpresa, "SEMANA", this.dataInicio, this.dataFim);
                    break;

                case "DIA":
                    listRelatorioTimeOut = this.serviceAtendimento.pesquisarRelatorioTimeOut(Arrays.asList(this.listIdUsuarios), this.idSupervisor, this.idEquipe, this.idCoordenador, this.idEmpresa, "DIA", this.dataInicio, this.dataFim);
                    break;

                case "MES":
                    listRelatorioTimeOut = this.serviceAtendimento.pesquisarRelatorioTimeOut(Arrays.asList(this.listIdUsuarios), this.idSupervisor, this.idEquipe, this.idCoordenador, this.idEmpresa, "MES", this.dataInicio, this.dataFim);

                    break;

                default:
                    break;
            }


            gerarRelatorios(listRelatorioTimeOut);

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }

    @SuppressWarnings("unchecked")
    private void gerarRelatorios(List<?> listRelatorio) {

        this.mapRelatorio = new LinkedHashMap<String, List<Object[]>>();

        if (CollectionUtils.isNotEmpty(listRelatorio)) {

            this.listFinal = new ArrayList<Object[]>();

            Map<String, Object[]> mapNome = new HashedMap();

            for (Object object : listRelatorio) {

                Object ob[] = (Object[]) object;

                List<Object[]> listRelatorioObj = new ArrayList<Object[]>();

                Object[] relatorio = new Object[3];

                relatorio[0] = ob[0];
                relatorio[1] = ob[2];
                relatorio[2] = ob[3];

                String headerText = (ob[1] instanceof Double ? String.format("%.0f", Double.valueOf((Double) ob[1])) : (String) ob[1]);

                if (this.filtro.equals("SEMANA"))
                    headerText = "SEMANA " + headerText;

                listRelatorioObj.add(relatorio);

                this.columns = new ArrayList<RelatoriosTimeOutBean.ColumnModel>();

                if (!this.mapRelatorio.containsKey(headerText)) {

                    this.mapRelatorio.put(headerText.toUpperCase(), new ArrayList<Object[]>());
                }

                this.mapRelatorio.get(headerText.toUpperCase()).add(relatorio);

                String nome = (String) relatorio[0];

                mapNome.put(nome, relatorio);

                listFinal.add(relatorio);

            }

            int indice = 0;

            this.columns.add(new ColumnModel("NOME", 0, indice++));

            List<Object[]> listTotal = new ArrayList<Object[]>();

            mapNome.forEach((k, v) -> {

                Object[] relatorio = new Object[this.mapRelatorio.keySet().size() + 1];
                relatorio[0] = k;
                listTotal.add(relatorio);

            });


            for (String key : this.mapRelatorio.keySet()) {

                this.columns.add(new ColumnModel(key.toUpperCase(), 2, indice++));

                for (Object[] objects : this.mapRelatorio.get(key)) {

                    for (Object[] objectsLinha : listTotal) {

                        String nomeLinha = (String) objectsLinha[0];

                        String nomeMap = (String) objects[0];

                        if (nomeLinha.toUpperCase().equalsIgnoreCase(nomeMap.toUpperCase())) {


                            objectsLinha[indice - 1] = objects[2];

                        }

                    }

                }

            }

            this.listFinal = listTotal;

        }

    }


    public void onChangeEquipe() {

        try {

            if (this.idEquipe != null) {

                this.listSupervisores = this.serviceUsuario.pesquisarSupervisoresPorEquipe(this.idEquipe);
                this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorEquipes(this.idEquipe);


            } else if (this.usuario.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

                this.listEquipes = this.serviceEquipe.pesquisarEquipesPorSupervisor(this.usuario.getId(), this.idEmpresa);

                this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorSupervisor(this.usuario.getId(), this.idEmpresa);

                if (this.usuario.getCoordenador() != null) {

                    this.listCoordenadores = new ArrayList<Usuario>();
                    this.listCoordenadores.add(this.usuario.getCoordenador());
                }

            } else {

                this.listSupervisores = this.serviceUsuario.pesquisarUsuariosPorNiveis(Arrays.asList(PerfilUsuarioEnum.SUPERVISOR), this.idEmpresa);

                this.listCoordenadores = this.serviceUsuario.pesquisarUsuariosPorNiveis(Arrays.asList(PerfilUsuarioEnum.COORDENADOR), this.idEmpresa);

                this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorNiveis(Arrays.asList(PerfilUsuarioEnum.OPERADOR), this.idEmpresa);

                this.listEquipes = this.serviceEquipe.pesquisarEquipesPorEmpresa(getEmpresa().getId(), TipoAcessoEnum.ATIVO);

            }


        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }

    public void onChangeCoordenador() {

        if (this.idCoordenador != null) {

            this.listSupervisores = this.serviceUsuario.pesquisarSupervisoresPorCoordenador(this.idCoordenador, this.idEmpresa);
            this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorCoordenador(this.idCoordenador, this.idEmpresa);

        } else {

            if (this.usuario.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

                this.listEquipes = this.serviceEquipe.pesquisarEquipesPorSupervisor(this.usuario.getId(), this.idEmpresa);

                this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorSupervisor(this.usuario.getId(), this.idEmpresa);

                if (this.usuario.getCoordenador() != null) {

                    this.listCoordenadores = new ArrayList<Usuario>();
                    this.listCoordenadores.add(this.usuario.getCoordenador());
                }

            } else {

                this.listSupervisores = this.serviceUsuario.pesquisarUsuariosPorNiveis(Arrays.asList(PerfilUsuarioEnum.SUPERVISOR), this.idEmpresa);

                this.listCoordenadores = this.serviceUsuario.pesquisarUsuariosPorNiveis(Arrays.asList(PerfilUsuarioEnum.COORDENADOR), this.idEmpresa);

                this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorNiveis(Arrays.asList(PerfilUsuarioEnum.OPERADOR), this.idEmpresa);


            }
        }
    }

    public void onChangeSupervisor() {

        try {

            if (this.idSupervisor != null) {

                this.listEquipes = this.serviceEquipe.pesquisarEquipesPorSupervisor(this.idSupervisor, this.idEmpresa);

                this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorSupervisor(this.idSupervisor, this.idEmpresa);

            } else {

                trocarEmpresa();

            }

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }
    }


    public String retornoFooter(String header) {

        BigInteger total = BigInteger.ZERO;

        if (header.equals("NOME"))
            return "TOTAL ";

        List<Object[]> list = this.mapRelatorio.get(header.toUpperCase());

        if (CollectionUtils.isEmpty(list))
            return "";

        for (Object[] objects : list) {

            if (objects[2] != null) {

                total = total.add((BigInteger) objects[2]);

            }
        }

        return String.valueOf(total.longValue());

    }

    static public class ColumnModel implements Serializable {


        private static final long serialVersionUID = -502873467260071561L;
        private String header;
        private int property;
        private int indice;

        public ColumnModel(String header, int property, int indice) {
            this.header = header;
            this.property = property;
            this.indice = indice;
        }

        public String getHeader() {
            return header;
        }

        public int getProperty() {
            return property;
        }

        public int getIndice() {
            return indice;
        }

        public void setIndice(int indice) {
            this.indice = indice;
        }
    }

    public Long getIdSupervisor() {
        return idSupervisor;
    }

    public void setIdSupervisor(Long idSupervisor) {
        this.idSupervisor = idSupervisor;
    }

    public Long getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(Long idEquipe) {
        this.idEquipe = idEquipe;
    }

    public Long getIdCoordenador() {
        return idCoordenador;
    }

    public void setIdCoordenador(Long idCoordenador) {
        this.idCoordenador = idCoordenador;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public List<String> getListFiltros() {
        return listFiltros;
    }

    public void setListFiltros(List<String> listFiltros) {
        this.listFiltros = listFiltros;
    }

    public List<Usuario> getListUsuarios() {
        return listUsuarios;
    }

    public void setListUsuarios(List<Usuario> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    public List<Usuario> getListSupervisores() {
        return listSupervisores;
    }

    public void setListSupervisores(List<Usuario> listSupervisores) {
        this.listSupervisores = listSupervisores;
    }

    public List<Usuario> getListCoordenadores() {
        return listCoordenadores;
    }

    public void setListCoordenadores(List<Usuario> listCoordenadores) {
        this.listCoordenadores = listCoordenadores;
    }

    public List<Equipe> getListEquipes() {
        return listEquipes;
    }

    public void setListEquipes(List<Equipe> listEquipes) {
        this.listEquipes = listEquipes;
    }

    public static long getSerialversionuid() {

        return serialVersionUID;

    }

    public Long[] getListIdUsuarios() {
        return listIdUsuarios;
    }

    public void setListIdUsuarios(Long[] listIdUsuarios) {
        this.listIdUsuarios = listIdUsuarios;
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }


    public LinkedHashMap<String, List<Object[]>> getMapRelatorio() {
        return mapRelatorio;
    }

    public void setMapRelatorio(LinkedHashMap<String, List<Object[]>> mapRelatorio) {
        this.mapRelatorio = mapRelatorio;
    }

    public DataTable getDataTablePersonalizada() {
        return dataTablePersonalizada;
    }

    public void setDataTablePersonalizada(DataTable dataTablePersonalizada) {
        this.dataTablePersonalizada = dataTablePersonalizada;
    }

    public ListDataModel<String[]> getDataModel() {
        return dataModel;
    }

    public void setDataModel(ListDataModel<String[]> dataModel) {
        this.dataModel = dataModel;
    }

    public List<ColumnModel> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnModel> columns) {
        this.columns = columns;
    }

    public List<Object[]> getListFinal() {
        return listFinal;
    }

    public void setListFinal(List<Object[]> listFinal) {
        this.listFinal = listFinal;
    }
}
