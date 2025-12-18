package com.proativaservicos.dao.implemets;

import com.proativaservicos.model.GenericHistoricoAtendimento;
import com.proativaservicos.model.HistoricoAtendimento;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.constantes.AcaoStatusAtendimentoEnum;
import com.proativaservicos.util.constantes.DaoEnum;
import com.proativaservicos.util.constantes.PerfilUsuarioEnum;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
@Named
public class DaoHistoricoAtendimentoImp extends GenericDao<HistoricoAtendimento> implements Serializable {

    private static final long serialVersionUID = 1L;

    public List<GenericHistoricoAtendimento> pesquisarHistoricosPorAtendimento(Long id) {

        StringBuilder query = new StringBuilder();

        query.append("select h from HistoricoAtendimento h ");
        query.append("\tjoin fetch h.usuario ");
        query.append("\tjoin fetch h.statusAtendimento ");

        query.append("where h.atendimento.id = :atendimento  ");
        query.append("order by h.dataCadastro desc ");

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("atendimento", id);

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

    public List<GenericHistoricoAtendimento> pesquisarHistoricosPorAtendimentoTest(Long id) {

        StringBuilder query = new StringBuilder();

        query.append("select h from HistoricoAtendimento h ");
        query.append("where h.atendimento.id = :atendimento  ");
        query.append("order by h.dataCadastro desc ");

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("atendimento", id);


        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

    public List<Object[]> pesquisarHistoricoPorCpf(String cpf, Long idEmpresa) {

        // TODO Auto-generated method stub

        StringBuilder query = new StringBuilder();
        query.append("select * from ( ");
        query.append("select c.descricao as campanha, ");
        query.append("\t   \t a.id as atendimento, ");
        query.append("       u.nome as usuario, ");
        query.append("       s.descricao as status, ");
        query.append("       h.data_cadastro, ");
        query.append("       h.agendamento, ");
        query.append("       h.observacao, ");
        query.append("       a.data_inicio_atendimento, ");
        query.append("       a.data_fim_atendimento ");
        query.append("from atendimento a ");
        query.append("\t  join empresa e on a.empresa = e.id ");
        query.append("\t  join campanha c on a.campanha = c.id ");
        query.append("\t  join historico_atendimento h on h.atendimento = a.id ");
        query.append("    join status_atendimento s on h.status_atendimento = s.id ");
        query.append("    join usuario u on h.usuario = u.id ");
        query.append("where a.cpf = :cpf ");
        query.append("\tand (e.id = :empresa or e.matriz = :empresa) ");
        query.append("\tand a.status is not null ) a ");
        query.append("order by a.data_cadastro desc ");

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("cpf", cpf.replaceAll("\\D+", "").replaceAll(" ", ""));
        parametros.put("empresa", idEmpresa);


        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }

    public List<?> pesquisarAgendamentos(Long idUsuario, Long idEquipe, Long idStatusAtendimento, String cpf,
                                         Date dataInicio, Date dataFim, Usuario usuario, Long empresa, boolean isGlobal) {

        HashMap<String, Object> parametros = new HashMap<>();

        StringBuilder query = new StringBuilder();

        query.append("Select h.id as id_historico, ");
        query.append("\t\t a.id as id_atendimento, ");
        query.append("\t\t a.campanha, ");
        query.append("\t\t to_char(h.agendamento, 'dd/mm/yyyy HH24:mi:ss'), ");
        query.append("\t\t a.nome as cliente, ");
        query.append("\t\t s.descricao, ");
        query.append("\t\t u.nome, ");
        query.append("\t\t a.cpf, ");
        query.append("\t\t c.descricao as descricao_campanha ");
        query.append("from historico_atendimento h ");
        query.append("\tjoin atendimento a on h.atendimento = a.id");
        query.append("\tjoin campanha c on c.id = a.campanha ");
        query.append("\tjoin status_atendimento s on s.id = h.status_atendimento ");
        query.append("\tjoin usuario u on u.id = h.usuario ");
        query.append("where date(h.agendamento) between date(:dataAgendamentoInicial) and date(:dataAgendamentoFinal) ");
        query.append("\tand s.acao like 'AGENDAR%' ");

        if (!isGlobal)
            query.append("\tand s.acao <> 'AGENDAR_GLOBAL' ");

        if (usuario.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {
            query.append("\tand h.usuario in ( ");
            query.append("\t\tselect distinct u.id ");
            query.append("\t\tfrom usuario u ");
            query.append("\t\t\tjoin equipe e on u.equipe = e.id ");
            query.append("\t\t\tjoin equipe_supervisor es on es.equipe = e.id ");
            query.append("\t\twhere es.supervisor = :supervisor) ");
            parametros.put("supervisor", usuario.getId());
        }

        if (idEquipe != null) {
            query.append("\tand u.equipe = :equipe ");
            parametros.put("equipe", idEquipe);
        }

        if (idUsuario != null) {
            query.append("\tand u.id = :usuario ");
            parametros.put("usuario", idUsuario);
        }

        if (idStatusAtendimento != null) {
            query.append("\tand a.status = :statusAtendimento ");
            parametros.put("statusAtendimento", idStatusAtendimento);
        }

        if (StringUtils.isNotBlank(cpf)) {
            query.append("\tand a.cpf = :cpf ");
            parametros.put("cpf", cpf);
        }

        query.append("\tand h.data_visualizado is null ");
        query.append("\tand a.empresa = :empresa ");
        query.append("order by h.agendamento ");

        parametros.put("dataAgendamentoInicial", dataInicio);
        parametros.put("dataAgendamentoFinal", dataFim);
        parametros.put("empresa", empresa);


        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }


    public Integer pesquisarTotalAgendamentos(Long idUsuario, Date dataInicio, Date dataFim, Usuario usuario, Long empresa, boolean isGlobal) {

        HashMap<String, Object> parametros = new HashMap<>();

        StringBuilder query = new StringBuilder();

        query.append("SELECT COUNT(a.id) as total ");

        query.append("from historico_atendimento h ");
        query.append("\tjoin atendimento a on h.atendimento = a.id");
        query.append("\tjoin campanha c on c.id = a.campanha ");
        query.append("\tjoin status_atendimento s on s.id = h.status_atendimento ");
        query.append("\tjoin usuario u on u.id = h.usuario ");

        query.append("where date(h.agendamento) between date(:dataAgendamentoInicial) and date(:dataAgendamentoFinal) ");

        query.append("\tand s.acao like 'AGENDAR%' ");

        if (!isGlobal)
            query.append("\tand s.acao <> 'AGENDAR_GLOBAL' ");


        if (idUsuario != null) {
            query.append("\tand u.id = :usuario ");
            parametros.put("usuario", idUsuario);
        }


        query.append("\tand h.data_visualizado is null ");
        query.append("\tand a.empresa = :empresa ");


        parametros.put("dataAgendamentoInicial", dataInicio);
        parametros.put("dataAgendamentoFinal", dataFim);
        parametros.put("empresa", empresa);


        BigInteger total = (BigInteger) searchEntidade(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

        if (total == null)
            return null;


        return Integer.valueOf(total.intValue());

    }


    public int pesquisarQuantidadeAgendamentos(Long idUsuario, Long idEquipe, Long idStatusAtendimento, String cpf,
                                               Date dataInicio, Date dataFim, Usuario usuario, Long empresa, boolean isGlobal) {


        HashMap<String, Object> parametros = new HashMap<>();

        StringBuilder query = new StringBuilder();

        query.append("Select count(h.id) ");

        query.append("from historico_atendimento h ");
        query.append("\tjoin atendimento a on h.atendimento = a.id");
        query.append("\tjoin campanha c on c.id = a.campanha ");
        query.append("\tjoin status_atendimento s on s.id = h.status_atendimento ");
        query.append("\tjoin usuario u on u.id = h.usuario ");
        query.append(
                "where date(h.agendamento) between date(:dataAgendamentoInicial) and date(:dataAgendamentoFinal) ");
        query.append("\tand s.acao like 'AGENDAR%' ");

        if (!isGlobal)
            query.append("\tand s.acao <> 'AGENDAR_GLOBAL' ");

        if (usuario.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {
            query.append("\tand h.usuario in ( ");
            query.append("\t\tselect distinct u.id ");
            query.append("\t\tfrom usuario u ");
            query.append("\t\t\tjoin equipe e on u.equipe = e.id ");
            query.append("\t\t\tjoin equipe_supervisor es on es.equipe = e.id ");
            query.append("\t\twhere es.supervisor = :supervisor) ");
            parametros.put("supervisor", usuario.getId());
        }

        if (idEquipe != null) {
            query.append("\tand u.equipe = :equipe ");
            parametros.put("equipe", idEquipe);
        }

        if (idUsuario != null) {
            query.append("\tand u.id = :usuario ");
            parametros.put("usuario", idUsuario);
        }

        if (idStatusAtendimento != null) {
            query.append("\tand a.status = :statusAtendimento ");
            parametros.put("statusAtendimento", idStatusAtendimento);
        }

        if (StringUtils.isNotBlank(cpf)) {
            query.append("\tand a.cpf = :cpf ");
            parametros.put("cpf", cpf);
        }

        query.append("\tand h.data_visualizado is null ");
        query.append("\tand a.empresa = :empresa ");
        query.append("order by h.agendamento ");

        parametros.put("dataAgendamentoInicial", dataInicio);
        parametros.put("dataAgendamentoFinal", dataFim);
        parametros.put("empresa", empresa);


        BigInteger resultado = (BigInteger) searchEntidade(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);
        return Integer.valueOf((resultado == null) ? 0 : resultado.intValue());
    }

    public void adiantarAgendamento(Long idHistorico) {
        // TODO Auto-generated method stub
        String query = "update historico_atendimento set agendamento = now() where id = :historico";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("historico", idHistorico);
        executarSql(DaoEnum.NATIVE_OBJECT, query, parametros);

    }

    /**
     * Pesquisa Agendamentos Diarios
     *
     * @param Usuario usuario
     * @param Long    idEmpresa
     * @return List<?>
     */
    public List<?> pesquisarAgendamentosDiarios(Usuario usuario, Long idEmpresa) {


        HashMap<String, Object> parametros = new HashMap<>();
        StringBuilder query = new StringBuilder();

        query.append("select c.descricao, count(h.id) ");
        query.append("from historico_atendimento h  ");
        query.append("\tjoin atendimento a on h.atendimento = a.id ");
        query.append("\tjoin campanha c on a.campanha = c.id ");
        query.append("\tjoin status_atendimento s on s.id = h.status_atendimento ");
        query.append("where date(h.agendamento) = date(:dataAgendamento) ");
        query.append("\tand s.acao like :acaoStatusAtendimento ");
        query.append("\tand h.data_visualizado is null ");
        query.append("\tand a.empresa = :empresa ");

        if (usuario.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

            query.append("\tand h.usuario in ( ");
            query.append("\t\tselect distinct u.id ");
            query.append("\t\tfrom usuario u ");
            query.append("\t\t\tjoin equipe e on u.equipe = e.id ");
            query.append("\t\t\tjoin equipe_supervisor es on es.equipe = e.id ");
            query.append("\t\twhere es.supervisor = :supervisor) ");
            parametros.put("supervisor", usuario);

        }

        query.append("group by c.descricao ");
        query.append("order by c.descricao ");

        parametros.put("dataAgendamento", new Date(System.currentTimeMillis()));
        parametros.put("acaoStatusAtendimento", AcaoStatusAtendimentoEnum.AGENDAR.name() + "%");
        parametros.put("empresa", idEmpresa);

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }

    public List<Object[]> pesquisarHistoricosPorCampanha(Long campanha) {

        StringBuilder query = new StringBuilder();

        query.append("select *  ");
        query.append("from ( ");
        query.append("\tSELECT h.atendimento,  ");
        query.append("           h.id,  ");
        query.append("\t\t\t u.nome,  ");
        query.append("\t\t\t s.descricao as status,  ");
        query.append("\t\t   to_char(h.data_cadastro, 'DD/MM/YYYY HH24:MI:SS') as data_cadastro,  ");
        query.append("\t\t   coalesce(to_char(h.agendamento, 'DD/MM/YYYY HH24:MI:SS'), '') as agendamento,   ");
        query.append("\t\t   coalesce(h.observacao, '') as manifesto  ");
        query.append("\tFROM historico_atendimento h   ");
        query.append("\t\t join atendimento a on h.atendimento = a.id  ");
        query.append("\t\t   join usuario u on h.usuario = u.id  ");
        query.append("\t\t   join status_atendimento s on h.status_atendimento = s.id  ");
        query.append("           join ( ");
        query.append("           \tselect h.atendimento, max(h.data_cadastro) as data_cadastro ");
        query.append("              from historico_atendimento h ");
        query.append("              \tjoin atendimento a on h.atendimento = a.id ");
        query.append("              where a.campanha = :campanha ");
        query.append("              group by h.atendimento ");
        query.append("           ) x on h.atendimento = x.atendimento and h.data_cadastro = x.data_cadastro ");
        query.append("\twhere a.campanha = :campanha  ) hs ");

        query.append("order by atendimento, data_cadastro desc, agendamento ");

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("campanha", campanha);

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);


    }

    @Transactional
    public void inserirHistoricoAtendimento(Long idAtendimento, Long idStatus, Long idUsuario, Date dataCadastro, Date dataVisualizado, String observacao) {

        String query = "INSERT INTO historico_atendimento (atendimento,status_atendimento,usuario,data_cadastro,data_visualizado,observacao) VALUES (:atendimento,:status,:usuario,'" + DateUtil.builder(dataCadastro).formatarDataParaString("yyyy-MM-dd HH:mm:ss").getDataTexto() + "','" + DateUtil.builder(dataVisualizado).formatarDataParaString("yyyy-MM-dd HH:mm:ss").getDataTexto() + "',:observacao)";

        HashMap<String, Object> parametros = new HashMap<>();

        parametros.put("atendimento", idAtendimento);

        parametros.put("status", idStatus);
        parametros.put("usuario", idUsuario);
        parametros.put("observacao", observacao);

        executarSql(DaoEnum.NATIVE_OBJECT, query, parametros);

    }

    public List<Object[]> pesquisarManifestoPorAtendimentos(List<Long> idsAtendimentos) {

        HashMap<String, Object> parametros = new HashMap<>();
        StringBuilder query = new StringBuilder();

        query.append("select distinct a.id , to_char(h.data_cadastro,'DD/MM/YYYY HH:mm:ss') as data_cadstro, coalesce( h.observacao,'') as manifesto, s.descricao as status ");
        query.append(" from historico_atendimento h  ");
        query.append("\tjoin atendimento a on h.atendimento = a.id ");
        query.append("\tjoin status_atendimento s on s.id = h.status_atendimento ");
        query.append("  where a.id in (:ids) ");
        query.append("  ORDER BY data_cadstro ");

        parametros.put("ids", idsAtendimentos);

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }

}
