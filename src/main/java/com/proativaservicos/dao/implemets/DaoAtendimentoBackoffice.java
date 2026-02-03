package com.proativaservicos.dao.implemets;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.proativaservicos.model.dto.ProdutividadeSacDto;
import com.proativaservicos.model.dto.RelatorioFiltroDto;
import com.proativaservicos.model.dto.RelatorioSacDto;
import com.proativaservicos.util.DateUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.proativaservicos.model.AtendimentoBackoffice;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.util.Utils;
import com.proativaservicos.util.constantes.DaoEnum;
import com.proativaservicos.util.constantes.TipoVisualizacaoEnum;

@SuppressWarnings("unchecked")
public class DaoAtendimentoBackoffice extends GenericDao<AtendimentoBackoffice> {

    public AtendimentoBackoffice pesquisarAtendimentoPorAdesao(String adesao, Long idEmpresa) {

        StringBuilder query = new StringBuilder();

        query.append("select a ");
        query.append("from AtendimentoBackoffice a ");
        query.append("\tleft join fetch a.usuarioAlteracao ua ");
        query.append("\tleft join fetch a.usuarioCadastro uc ");
        query.append("\tleft join fetch a.usuarioEmAtendimento oc ");
        query.append("\tleft join fetch a.equipe e ");
        query.append("\tleft join fetch a.produto p ");
        query.append("\tleft join fetch a.loja l ");
        query.append("\tleft join fetch a.extrator ex ");
        query.append("\tleft join fetch a.status s ");
        query.append("\tleft join fetch a.motivo m ");
        query.append("\tleft join fetch a.submotivo sb ");

        query.append("where  ");
        query.append("  a.adesao = :adesao ");

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("adesao", adesao.replaceAll("\\D+", "").replaceAll(" ", ""));

        return (AtendimentoBackoffice) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros, Integer.valueOf(0), Integer.valueOf(1));
    }

    public List<Long> pesquisarAtendimentosPorEquipe(Long idEquipe, boolean limiteConsulta) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        query.append("SELECT ab.id  ");
        query.append("from atendimento_backoffice ab   ");

        query.append("\tjoin loja l on l.id = ab.loja  ");
        query.append("\tleft join status_atendimento s on s.id = ab.status  ");
        query.append("\t	left join motivo m on m.id = ab.motivo   ");
        query.append("\tleft join submotivo sb on sb.id = ab.submotivo  ");
        query.append("  \tjoin equipe_loja el on el.loja = ab.loja ");
        query.append("  where ");
        query.append("  el.equipe = :equipe ");
        query.append("  and (ab.enviado is null or ab.enviado = false)   ");
        query.append("   and   (ab.submotivo is null or ( sb.acao in ('NONE','FIM_FILA') ) )      ");
        query.append(" and  (ab.resp_corban is not null or ab.resp_corban <> '' )  ");

        query.append("	order by  ab.enviado desc ,ab.id    ");

        if (limiteConsulta) {
            query.append(" limit 50 ");
        }

        parametros.put("equipe", idEquipe);

        List<Long> listIds = Utils.converterLong(searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros));

        return listIds;

    }

    public Long pesquisarProximoAtendimentoAgendamento(Long idUsuario) {

        return null;
    }

    public void atualizarAtendimentoOcupado(Long idAtendimento, Long idUsuario) {

        String query = " update atendimento_backoffice set usuario_em_atendimento = :usuario, usuario_alteracao = :usuarioAlteracao, data_alteracao = :data  where id = :id ";

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("id", idAtendimento);
        parametros.put("usuario", idUsuario);
        parametros.put("usuarioAlteracao", idUsuario);
        parametros.put("data", new Date(System.currentTimeMillis()));

        executarSql(DaoEnum.NATIVE_OBJECT, query, parametros);

    }

    public Long pesquisarProximoAtendimentoOcupado(Long idUsuario) {

        StringBuilder query = new StringBuilder();
        query.append("select a.id ");
        query.append("from atendimento_backoffice a ");
        query.append("where a.usuario_em_atendimento = :usuario ");
        query.append("limit 1 ");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("usuario", idUsuario);
        BigInteger resultado = (BigInteger) searchEntidade(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

        return (resultado == null) ? null : Long.valueOf(resultado.longValue());

    }

    public boolean verificarJaFoiAtendido(Long idAtendimento) {

        StringBuilder query = new StringBuilder();
        query.append(" select a.id ");
        query.append(" from atendimento_backoffice a ");
        query.append("\tjoin status_atendimento s on a.status = s.id ");
        query.append(" where a.id = :id ");
        query.append("  and s.acao <> 'FIM_FILA' ");
        Map<String, Object> parametros = new HashMap<>();

        parametros.put("id", idAtendimento);
        Object resultado = searchEntidade(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

        return (resultado != null);
    }

    public boolean verificarCpfEmAtendimento(String cpf) {

        StringBuilder query = new StringBuilder();
        query.append(
                " SELECT ab.cpf,ab.id from atendimento_backoffice ab WHERE   ab.usuario_em_atendimento is not null and ab.cpf = :cpf ");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("cpf", cpf);
        Object resultado = searchEntidade(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

        return (resultado != null);
    }

    public AtendimentoBackoffice pesquisarAtendimentoPorId(Long idAtendimento) {

        StringBuilder query = new StringBuilder();

        query.append("select a ");
        query.append("from AtendimentoBackoffice a ");
        query.append("\tleft join fetch a.extrator c ");
        query.append("\tleft join fetch a.loja ");
        query.append("\tleft join fetch a.atendimento ");
        query.append("\tleft join fetch a.produto ");
        query.append("\tleft join fetch a.status sa ");
        query.append("\tleft join fetch a.usuarioEmAtendimento o ");
        query.append("\tleft join fetch a.motivo m ");
        query.append("\tleft join fetch a.submotivo sb ");
        query.append("where a.id = :id ");

        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("id", idAtendimento);

        return (AtendimentoBackoffice) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

    public AtendimentoBackoffice pesquisarAtendimentosOcupadoPorId(Long idAtendimento) {
        StringBuilder query = new StringBuilder();

        query.append("select a ");
        query.append("from AtendimentoBackoffice a ");

        query.append("\tleft join fetch a.usuarioEmAtendimento o ");

        query.append("where a.id = :id ");

        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("id", idAtendimento);

        return (AtendimentoBackoffice) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

    public String pesquisarCpfPorId(Long idAtendimento) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<String, Object>();

        query.append(" select a.cpf from atendimento_backoffice a  where a.id = :atendimento ");

        parametros.put("atendimento", idAtendimento);

        return (String) searchEntidade(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);
    }

    public List<AtendimentoBackoffice> pesquisarHqlCpfPorId(String cpf) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<String, Object>();

        query.append("select a ");
        query.append("from AtendimentoBackoffice a ");
        query.append("\tjoin fetch a.extrator c ");
        query.append("\tleft join fetch a.loja ");
        query.append("\tleft join fetch a.status sa ");
        query.append("\tleft join fetch a.usuarioEmAtendimento o ");
        query.append("\tleft join fetch a.motivo m ");
        query.append("\tleft join fetch a.submotivo sb ");
        query.append("where a.cpf = :cpf ");

        parametros.put("cpf", cpf);

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

    public void atualizarAtendimentosEnviado(List<Long> listAtendimentos) {

        if (CollectionUtils.isEmpty(listAtendimentos)) {
            return;
        }

        Map<String, Object> parametros = new HashMap<>();
        StringBuilder query = new StringBuilder();

        query.append("update atendimento_backoffice ");
        query.append("set enviado = true ");
        query.append("where id in (:listAtendimentos) ");
        parametros.put("listAtendimentos", listAtendimentos);

        executarSql(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }

    public List<Long> pesquisarIdsPorCpf(String cpf) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();
        query.append("SELECT ab.id  ");
        query.append("from atendimento_backoffice ab   ");
        query.append("  where ");
        query.append("  ab.cpf = :cpf ");
        parametros.put("cpf", cpf);

        List<Long> listIds = Utils.converterLong(searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros));
        return listIds;

    }

    public List<Object[]> pesquisarAtendimentosAgendados(Long idUsuario) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        query.append(
                "SELECT a.id, ab.nome as cliente, u.nome as nome_usuario,ab.cpf,s.descricao,a.data_cadastro,a.observacao,a.atendimento_backoffice,a.agendamento ");
        query.append("from historico_atendimento_backoffice a   ");

        query.append("  JOIN usuario u on u.id = a.usuario_cadastro  ");
        query.append("  JOIN atendimento_backoffice ab on ab.id = a.atendimento_backoffice  ");
        query.append("  JOIN submotivo s on s.id = a.submotivo ");
        query.append(
                "  WHERE a.data_visualizado is null and s.acao like '%AGENDAR%' and  a.agendamento > current_timestamp and ab.usuario_em_atendimento is null ");

        if (idUsuario != null) {
            query.append("   and a.usuario_cadastro = :usuario ");
            parametros.put("usuario", idUsuario);
        }

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }

    public List<Object[]> pesquisarAtendimentosPendentes(Long idUsuario) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        query.append("SELECT a.id, a.nome as cliente,a.cpf,u.nome as operador,s.descricao,a.data_cadastro  ");
        query.append("FROM atendimento_backoffice a   ");

        query.append("  JOIN usuario u on u.id = a.usuario_alteracao  ");
        query.append(" LEFT JOIN status_atendimento s on s.id = a.status ");
        query.append("  WHERE 1=1  ");

        if (idUsuario != null) {
            query.append("   and  a.usuario_em_atendimento = :usuario and a.status is null ");
            parametros.put("usuario", idUsuario);
        }
        query.append("  ORDER BY a.adesao asc ");
        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }

    public List<Object[]> pesquisarAtendimentosPendentesPorStatusExtrator(Long idUsuario, String statusExtrator) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        query.append("SELECT a.id, a.nome as cliente,a.cpf,u.nome as operador,s.descricao,a.data_cadastro  ");
        query.append("FROM atendimento_backoffice a   ");

        query.append("  JOIN usuario u on u.id = a.usuario_alteracao  ");
        query.append(" LEFT JOIN status_atendimento s on s.id = a.status ");
        query.append("  WHERE 1=1  ");

        if (idUsuario != null) {
            query.append("   and  a.usuario_em_atendimento = :usuario and a.status is not null ");
            parametros.put("usuario", idUsuario);
        }

        if (StringUtils.isNotBlank(statusExtrator)) {
            query.append("   and  a.status_extrator = upper(:status_extrator)   ");
            parametros.put("status_extrator", statusExtrator.toUpperCase());

        }

        query.append("  ORDER BY a.adesao asc ");
        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }

    public List<Object[]> pesquisarAtendimentosPendentesPorStatusExtrator(Long idUsuario, List<String> statusExtrator) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        query.append(
                "SELECT a.id, a.nome as cliente,a.cpf,u.nome as operador,s.descricao,a.data_cadastro,a.status_extrator,a.adesao  ");
        query.append("FROM atendimento_backoffice a   ");

        query.append("  JOIN usuario u on u.id = a.usuario_alteracao  ");
        query.append(" LEFT JOIN status_atendimento s on s.id = a.status ");
        query.append("  WHERE 1=1  ");

        if (idUsuario != null) {
            query.append("   and  a.usuario_em_atendimento = :usuario and a.status is not null ");
            parametros.put("usuario", idUsuario);
        }

        if (CollectionUtils.isNotEmpty(statusExtrator)) {

            query.append("   and upper(a.status_extrator) in " + sqlFormatedListObect(statusExtrator));

        }

        query.append("  ORDER BY a.adesao asc ");

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }

    public void atualizarAtendimentoOcupado(List<Long> listIds, Long idUsuario) {

        String query = "update atendimento_backoffice set usuario_em_atendimento = :usuario, usuario_alteracao = :usuarioAlteracao, data_alteracao = :data  where id IN  "
                + sqlFormatedList(listIds);

        HashMap<String, Object> parametros = new HashMap<>();

        parametros.put("usuario", idUsuario);
        parametros.put("usuarioAlteracao", idUsuario);
        parametros.put("data", new Date(System.currentTimeMillis()));

        executarSql(DaoEnum.NATIVE_CLASSE, query, parametros);

    }

    public List<Object[]> pesquisarAtendimentos(String cpf, String nome, String adesao, String protocolo, Long equipe,
                                                Long operador, Long consistencia, Long statusAtendimento, Date dataInicio, Date dataFim, Usuario usuario,
                                                Long produto, Boolean tradata, Long loja, Long motivo, Long submotivo) {

        StringBuilder query = new StringBuilder();

        query.append("SELECT ");
        query.append("    a.id as id_atendimento,  ");
        query.append("    a.atendimento, ");
        query.append("    a.nome, ");
        query.append("    a.cpf, ");
        query.append("    a.adesao, ");
        query.append("    a.data_alteracao, ");
        query.append("    a.servico,  ");
        query.append("    a.valor,  ");
        query.append("    a.resp_corban,  ");
        query.append("   a.cod_loja,  ");
        query.append("    u.nome as operador, ");

        query.append("    s.descricao status, ");
        query.append("\t   m.descricao as motivo,  ");
        query.append("\t   sm.descricao as submotivo, ");
        query.append("   p.descricao as produto, ");
        query.append("\t   ac.arquivo,  ");
        query.append("   c.id  id_consistencia, ");
        query.append("    c.descricao as descricao_consistencia, ");
        query.append("\t  c.instituicao_financeira, ");
        query.append("\t  c.codigo_consistencia, ");
        query.append("\t  c.acao, ");

        query.append("\t  c.convenio, ");
        query.append("\t  c.prazo, ");
        query.append("\t  c.responsabilidade, ");
        query.append("\t   c.significado,  ");
        query.append("\t   ac.tratada, ");
        query.append("   a.status_extrator  ");

        query.append("FROM atendimento_backoffice a   ");
        query.append("\t  JOIN usuario u on u.id = a.usuario_alteracao   ");
        query.append("    LEFT JOIN status_atendimento s on s.id = a.status  ");
        query.append("    LEFT JOIN motivo m on m.id = a.motivo  ");
        query.append("    LEFT JOIN submotivo sm on sm.id = a.submotivo  ");
        query.append("    LEFT JOIN  produto p on p.id = a.produto ");
        query.append("    LEFT JOIN atendimento_backoffice_consistencia ac on ac.atendimento_id = a.id  ");
        query.append("\t  LEFT JOIN  consistencia c on c.id = ac.consistencia_id ");

        query.append("where  ");

        query.append("\t a.status is not null ");

        HashMap<String, Object> parametros = new HashMap<>();

        if (cpf != null && !cpf.isEmpty()) {

            query.append("and a.cpf = :cpf ");
            parametros.put("cpf", cpf.replaceAll("\\D+", "").replaceAll(" ", ""));
        }

        if (nome != null && !nome.isEmpty()) {
            query.append("and upper(a.nome) like :nome ");
            parametros.put("nome", "%" + nome.toUpperCase() + "%");
        }

        if (adesao != null && !adesao.isEmpty()) {

            query.append(" and (a.adesao = :adesao ) ");
            parametros.put("adesao", adesao);

        }

        if (equipe != null && operador == null) {

            query.append("\tand a.equipe = :equipe ");
            parametros.put("equipe", equipe);

        }

        if (operador != null) {
            query.append(" and ( a.usuario_alteracao = :operador) ");
            parametros.put("operador", operador);
        }

        if (statusAtendimento != null) {
            query.append(" and a.status = :status ");
            parametros.put("status", statusAtendimento);
        }

        if (produto != null) {
            query.append(" and a.produto = :produto ");
            parametros.put("produto", produto);
        }

        if (consistencia != null) {
            query.append(" and c.id = :consistencia ");
            parametros.put("consistencia", consistencia);
        }

        if (loja != null) {
            query.append(" and a.loja = :loja ");
            parametros.put("loja", loja);
        }

        if (motivo != null) {
            query.append(" and a.motivo = :motivo ");
            parametros.put("motivo", motivo);
        }

        if (submotivo != null) {
            query.append(" and a.submotivo = :submotivo ");
            parametros.put("submotivo", submotivo);
        }

        /*
         * if (tradata != null) {
         *
         * query.append(" and ( ac.tratada = :tratada  " );
         * query.append(tradata.equals(Boolean.FALSE) ?"or ac.tratada is null )" :" ) "
         * );
         *
         * parametros.put("tratada", tradata.booleanValue());
         *
         * }
         */
        if (dataInicio != null && dataFim != null) {

            query.append(" and date(a.data_alteracao) BETWEEN date(:periodoInicio) and date(:periodoFim) ");
            parametros.put("periodoInicio", dataInicio);
            parametros.put("periodoFim", dataFim);

        } else if (dataInicio != null) {

            query.append("and date(a.data_alteracao) >= date(:periodoInicio) ");
            parametros.put("periodoInicio", dataInicio);

        } else if (dataFim != null) {

            query.append("and date(a.data_alteracao) <= date(:periodoFim) ");
            parametros.put("periodoFim", dataFim);
        }

        query.append(" order by a.data_alteracao desc ");

        List<Object[]> list = searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

        return tratarDadosAtendimento(list);
    }

    public List<Object[]> tratarDadosAtendimento(List<Object[]> list) {

        if (CollectionUtils.isEmpty(list))
            return list;

        Map<Long, Object[]> mapAtendimentos = new TreeMap<Long, Object[]>();

        for (Object[] objects : list) {

            Long id = ((BigInteger) objects[0]).longValue();

            if (mapAtendimentos.containsKey(id)) {

                int consistencia = 0;

                Object[] objectConsistencia = new Object[11];

                for (int i = 16; i < objects.length; i++) {

                    objectConsistencia[consistencia++] = objects[i];
                }

                objectConsistencia[10] = objects[15];

                if (objectConsistencia == null || objectConsistencia.length < 0 || objectConsistencia[0] == null)
                    ((ArrayList<Object>) mapAtendimentos.get(id)[16]).add(null);
                else
                    ((ArrayList<Object>) mapAtendimentos.get(id)[16]).add(objectConsistencia);

            } else {

                Object[] objectAtend = new Object[17];
                Object[] objectConsistencia = new Object[11];

                objectAtend[0] = id;
                int consistencia = 0;

                for (int i = 1; i < objects.length; i++) {

                    if (i < 16)
                        objectAtend[i] = objects[i];
                    else
                        objectConsistencia[consistencia++] = objects[i];

                }

                objectConsistencia[10] = objectAtend[15];

                objectAtend[16] = new ArrayList<Object>();

                if (objectConsistencia == null || objectConsistencia.length < 0 || objectConsistencia[0] == null)
                    ((ArrayList<Object>) objectAtend[16]).add(null);
                else
                    ((ArrayList<Object>) objectAtend[16]).add(objectConsistencia);

                mapAtendimentos.put(id, objectAtend);

            }

        }

        return new ArrayList<Object[]>(mapAtendimentos.values());

    }

    public List<Object[]> tratarDadosAtendimentoConsistencia(List<Object[]> list) {

        if (CollectionUtils.isEmpty(list))
            return list;

        Map<Long, Object[]> mapAtendimentos = new TreeMap<Long, Object[]>();

        for (Object[] objects : list) {

            Long id = ((BigInteger) objects[0]).longValue();

            if (!mapAtendimentos.containsKey(id)) {

                Object[] objectAtend = new Object[14];

                objectAtend[0] = id;

                for (int i = 1; i < objects.length; i++) {

                    objectAtend[i] = objects[i];

                }

                objectAtend[10] = new ArrayList<Object>();
                mapAtendimentos.put(id, objectAtend);

            }

        }

        return new ArrayList<Object[]>(mapAtendimentos.values());

    }

    public List<?> pesquisarAtendimentosPendentes(Long equipePendencia, Long operadorPendencia, Usuario usuario, List<Long> lojas, Long idEmpresa, String adesao, String cpf, String nome, boolean statusVazio) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        query.append("SELECT a.id, a.nome as cliente,a.cpf,u.nome as operador,s.descricao,a.data_alteracao,a.status_extrator,a.adesao  ");
        query.append("FROM atendimento_backoffice a   ");

        query.append("  JOIN usuario u on u.id = a.usuario_em_atendimento  ");
        query.append(" LEFT JOIN status_atendimento s on s.id = a.status ");

        query.append("  WHERE  1=1 ");

        if (statusVazio) {

            query.append("  and  a.status is null ");

        }

        if (equipePendencia != null) {

            query.append("  and  a.equipe = :equipe   ");
            parametros.put("equipe", equipePendencia);
        }

        if (StringUtils.isNotBlank(cpf)) {

            query.append("  and a.cpf like :cpf ");
            parametros.put("cpf", "%" + cpf.trim() + "%");
        }

        if (StringUtils.isNotBlank(nome)) {

            query.append("  and a.nome like :nome ");
            parametros.put("nome", "%" + nome.trim() + "%");
        }

        if (StringUtils.isNotBlank(adesao)) {
            query.append("  and a.adesao like :ade ");
            parametros.put("ade", "%" + adesao.trim() + "%");
        }

        if (CollectionUtils.isNotEmpty(lojas)) {

            query.append("   and  a.loja in  " + sqlFormatedList(lojas));

        }

        if (operadorPendencia != null) {

            query.append("   and  a.usuario_em_atendimento = :usuario  ");
            parametros.put("usuario", operadorPendencia);

        } else {

            query.append("   and  a.usuario_em_atendimento is not null  ");

        }
        query.append("  order by a.data_alteracao");

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }

    public List<?> pesquisarMinhaListaAtendimento(Long operadorPendencia, String cpf, String adesao, String nome,
                                                  Date dataInicio, Date dataFim) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        query.append(
                "SELECT a.id, a.nome as cliente,a.cpf,u.nome as operador,s.descricao,a.data_alteracao,a.status_extrator,a.adesao ,a.valor  ");
        query.append("FROM atendimento_backoffice a   ");

        query.append("  JOIN usuario u on u.id = a.usuario_alteracao  ");
        query.append(" LEFT JOIN status_atendimento s on s.id = a.status ");

        query.append("  WHERE a.usuario_alteracao = :usuario ");
        parametros.put("usuario", operadorPendencia);

        if (StringUtils.isNotBlank(cpf)) {

            query.append("  and a.cpf like :cpf ");
            parametros.put("cpf", "%" + cpf.trim() + "%");
        }

        if (StringUtils.isNotBlank(nome)) {

            query.append("  and a.nome like :nome ");
            parametros.put("nome", "%" + nome.trim() + "%");
        }

        if (StringUtils.isNotBlank(adesao)) {
            query.append("  and a.adesao like :ade ");
            parametros.put("ade", "%" + adesao.trim() + "%");
        }

        if (dataInicio != null && dataFim != null) {

            query.append(" and date(a.data_alteracao) BETWEEN date(:periodoInicio) and date(:periodoFim) ");
            parametros.put("periodoInicio", dataInicio);
            parametros.put("periodoFim", dataFim);

        } else if (dataInicio != null) {

            query.append("and date(a.data_alteracao) >= date(:periodoInicio) ");
            parametros.put("periodoInicio", dataInicio);

        } else if (dataFim != null) {

            query.append("and date(a.data_alteracao) <= date(:periodoFim) ");
            parametros.put("periodoFim", dataFim);
        }

        query.append("  order by a.data_alteracao");

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }

    public boolean isAtendimentoOcupado(Long idAtendimento, Long idUsuario) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        query.append("SELECT  a.usuario_em_atendimento ");
        query.append("\tFROM atendimento_backoffice a   ");
        query.append("\t WHERE   a.usuario_em_atendimento = :usuario ");
        parametros.put("usuario", idUsuario);

        query.append("  and   a.id = :atn ");
        parametros.put("atn", idAtendimento);

        BigInteger resultado = (BigInteger) searchEntidade(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

        return resultado == null ? false : (Long.valueOf(resultado.longValue()).longValue() != idUsuario.longValue());

    }

    public Long pesquisarUsuarioOcupadoPorAtendimento(Long idAtendimento) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        query.append("SELECT  a.usuario_em_atendimento ");
        query.append("FROM atendimento_backoffice a   ");
        query.append("  WHERE   a.id = :atn ");
        parametros.put("atn", idAtendimento);

        BigInteger resultado = (BigInteger) searchEntidade(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

        return resultado == null ? null : Long.valueOf(resultado.longValue());

    }

    public List<?> pesquisarNaoTrabalhados(Long consistenciaNaoTrabalhada, Long lojaNaoTrabalhada, List<Long> lojas,
                                           String cpfNaoTrabalhado, String adesaoNaoTrabalhada, Usuario usuario, Long idEmpresa) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        query.append(
                "SELECT a.id, a.nome as cliente,a.cpf,a.adesao, a.servico, a.status_extrator,a.resp_corban , a.valor, a.data_cadastro, a.cod_loja  ");
        query.append("FROM atendimento_backoffice a   ");

        query.append(" LEFT JOIN  atendimento_backoffice_consistencia ac on ac.atendimento_id = a.id  ");

        query.append("  WHERE  a.status is null and a.usuario_em_atendimento is null ");

        if (consistenciaNaoTrabalhada != null) {

            query.append("  and  ac.atendimento_id = :consistenciaAtn   ");
            parametros.put("consistenciaAtn", consistenciaNaoTrabalhada);
        }

        if (lojaNaoTrabalhada != null) {

            query.append("   and  a.loja = :loja  ");
            parametros.put("loja", lojaNaoTrabalhada);

        }

        if (StringUtils.isNotBlank(cpfNaoTrabalhado)) {

            query.append("   and  a.cpf = :cpf  ");
            parametros.put("cpf", cpfNaoTrabalhado);

        }

        if (StringUtils.isNotBlank(adesaoNaoTrabalhada)) {

            query.append("   and  a.adesao like :adesao  ");
            parametros.put("adesao", "%" + adesaoNaoTrabalhada.trim() + "%");

        }

        if (CollectionUtils.isNotEmpty(lojas)) {

            query.append("   and  a.loja in  " + sqlFormatedList(lojas));
        }
        System.out.println(query.toString());

        return tratarDadosAtendimentoConsistencia(searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros));
    }

    public List<?> pesquisarRelatoriosPorUsuario(Long idUsuario, Date dataInicio, Date dataFim) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        query.append(
                " SELECT SUM(ab.valor) as total,count(ab.id) as qtdade,  s.descricao status ,m.descricao as motivo,sb.descricao as submotivo ");
        query.append(" from atendimento_backoffice ab ");
        query.append(" JOIN status_atendimento s on s.id = ab.status  ");
        query.append(" JOIN motivo m on m.id = ab.motivo ");
        query.append(" JOIN submotivo sb on sb.id = ab.submotivo ");
        query.append(" WHERE ab.usuario_alteracao = :usuario ");

        if (dataInicio != null && dataFim != null) {

            query.append(" and date(ab.data_alteracao) BETWEEN date(:periodoInicio) and date(:periodoFim) ");
            parametros.put("periodoInicio", dataInicio);
            parametros.put("periodoFim", dataFim);

        } else if (dataInicio != null) {

            query.append("and date(ab.data_alteracao) >= date(:periodoInicio) ");
            parametros.put("periodoInicio", dataInicio);

        } else if (dataFim != null) {

            query.append("and date(ab.data_alteracao) <= date(:periodoFim)  ");
            parametros.put("periodoFim", dataFim);
        }

        query.append("  GROUP BY s.descricao, m.descricao,sb.descricao  ORDER BY s.descricao  ");
        parametros.put("usuario", idUsuario);

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }

    public List<?> pesquisarRelatoriosStatusAtendimentoPorUsuario(Long idUsuario, Date dataInicio, Date dataFim, Long idStatus) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        query.append(" SELECT SUM(ab.valor) as total, count(ab.id) as qtdade,  s.descricao status, '' as cor  ");
        query.append(" from atendimento_backoffice ab ");
        query.append(" JOIN status_atendimento s on s.id = ab.status  ");

        query.append(" WHERE 1=1 ");

        if (idUsuario != null) {

            query.append(" and  ab.usuario_alteracao = :usuario ");
            parametros.put("usuario", idUsuario);
        }

        if (idStatus != null) {

            query.append(" and ab.status = :status ");
            parametros.put("status", idStatus);

        }

        if (dataInicio != null && dataFim != null) {

            query.append(" and date(ab.data_alteracao) BETWEEN date(:periodoInicio) and date(:periodoFim) ");
            parametros.put("periodoInicio", dataInicio);
            parametros.put("periodoFim", dataFim);

        } else if (dataInicio != null) {

            query.append("and date(ab.data_alteracao) >= date(:periodoInicio) ");
            parametros.put("periodoInicio", dataInicio);

        } else if (dataFim != null) {

            query.append("and date(ab.data_alteracao) <= date(:periodoFim)  ");
            parametros.put("periodoFim", dataFim);
        }

        query.append("  GROUP BY s.descricao ORDER BY s.descricao  ");


        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }


    public List<?> pesquisarRelatoriosStatusAtendimentoPorSupervisor(Long idUsuario, Date dataInicio, Date dataFim, Long idStatus) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        query.append(" SELECT SUM(ab.valor) as total, count(ab.id) as qtdade,  s.descricao status, '' as cor  ");
        query.append(" from atendimento_backoffice ab ");
        query.append(" JOIN status_atendimento s on s.id = ab.status  ");

        query.append(" WHERE 1=1 ");

        if (idUsuario != null) {

            query.append(" and ab.equipe in ( SELECT  DISTINCT es.equipe from usuario u INNER JOIN equipe_supervisor es on es.supervisor = u.id WHERE es.supervisor = :supervisor   )  ");
            parametros.put("supervisor", idUsuario);
        }

        if (idStatus != null) {

            query.append(" and ab.status = :status ");
            parametros.put("status", idStatus);

        }

        if (dataInicio != null && dataFim != null) {

            query.append(" and date(ab.data_alteracao) BETWEEN date(:periodoInicio) and date(:periodoFim) ");
            parametros.put("periodoInicio", dataInicio);
            parametros.put("periodoFim", dataFim);

        } else if (dataInicio != null) {

            query.append("and date(ab.data_alteracao) >= date(:periodoInicio) ");
            parametros.put("periodoInicio", dataInicio);

        } else if (dataFim != null) {

            query.append("and date(ab.data_alteracao) <= date(:periodoFim)  ");
            parametros.put("periodoFim", dataFim);
        }

        query.append("  GROUP BY s.descricao ORDER BY s.descricao  ");


        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }

    public List<?> pesquisarRelatoriosMotivoPorUsuario(Long idUsuario, Date dataInicio, Date dataFim, Long idMotivo) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        query.append(" SELECT SUM(ab.valor) as total,count(ab.id) as qtdade,  m.descricao motivo ,'' as cor  ");
        query.append(" from atendimento_backoffice ab ");
        query.append(" JOIN motivo m on m.id = ab.motivo ");

        query.append(" WHERE 1=1 ");

        if (idUsuario != null) {
            query.append("  and ab.usuario_alteracao = :usuario ");
            parametros.put("usuario", idUsuario);
        }

        if (idMotivo != null) {
            query.append(" and  ab.motivo = :motivo ");
            parametros.put("motivo", idMotivo);
        }

        if (dataInicio != null && dataFim != null) {

            query.append(" and date(ab.data_alteracao) BETWEEN date(:periodoInicio) and date(:periodoFim) ");
            parametros.put("periodoInicio", dataInicio);
            parametros.put("periodoFim", dataFim);

        } else if (dataInicio != null) {

            query.append("and date(ab.data_alteracao) >= date(:periodoInicio) ");
            parametros.put("periodoInicio", dataInicio);

        } else if (dataFim != null) {

            query.append("and date(ab.data_alteracao) <= date(:periodoFim)  ");
            parametros.put("periodoFim", dataFim);
        }

        query.append("  GROUP BY m.descricao ORDER BY m.descricao  ");


        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }


    public List<?> pesquisarRelatoriosMotivoPorSupervisor(Long idUsuario, Date dataInicio, Date dataFim, Long idMotivo) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        query.append(" SELECT SUM(ab.valor) as total,count(ab.id) as qtdade,  m.descricao motivo ,'' as cor  ");
        query.append(" from atendimento_backoffice ab ");
        query.append(" JOIN motivo m on m.id = ab.motivo ");

        query.append(" WHERE 1=1 ");

        if (idUsuario != null) {

            query.append(" and ab.equipe in ( SELECT  DISTINCT es.equipe from usuario u INNER JOIN equipe_supervisor es on es.supervisor = u.id WHERE es.supervisor = :supervisor   )  ");
            parametros.put("supervisor", idUsuario);
        }

        if (idMotivo != null) {
            query.append(" and  ab.motivo = :motivo ");
            parametros.put("motivo", idMotivo);
        }

        if (dataInicio != null && dataFim != null) {

            query.append(" and date(ab.data_alteracao) BETWEEN date(:periodoInicio) and date(:periodoFim) ");
            parametros.put("periodoInicio", dataInicio);
            parametros.put("periodoFim", dataFim);

        } else if (dataInicio != null) {

            query.append("and date(ab.data_alteracao) >= date(:periodoInicio) ");
            parametros.put("periodoInicio", dataInicio);

        } else if (dataFim != null) {

            query.append("and date(ab.data_alteracao) <= date(:periodoFim)  ");
            parametros.put("periodoFim", dataFim);
        }

        query.append("  GROUP BY m.descricao ORDER BY m.descricao  ");


        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }

    public List<?> pesquisarRelatoriosSubmotivoPorUsuario(Long idUsuario, Date dataInicio, Date dataFim,
                                                          Long idSubmotivo) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        query.append(" SELECT SUM(ab.valor) as total,count(ab.id) as qtdade, sb.descricao submotivo ,'' as cor  ");
        query.append(" from atendimento_backoffice ab ");
        query.append(" JOIN submotivo sb on sb.id = ab.submotivo ");

        query.append(" WHERE 1=1 ");

        if (idUsuario != null) {
            query.append("  and ab.usuario_alteracao = :usuario ");
            parametros.put("usuario", idUsuario);
        }

        if (idSubmotivo != null) {

            query.append(" and ab.submotivo = :submotivo ");
            parametros.put("submotivo", idSubmotivo);
        }

        if (dataInicio != null && dataFim != null) {

            query.append(" and date(ab.data_alteracao) BETWEEN date(:periodoInicio) and date(:periodoFim) ");
            parametros.put("periodoInicio", dataInicio);
            parametros.put("periodoFim", dataFim);

        } else if (dataInicio != null) {

            query.append("and date(ab.data_alteracao) >= date(:periodoInicio) ");
            parametros.put("periodoInicio", dataInicio);

        } else if (dataFim != null) {

            query.append("and date(ab.data_alteracao) <= date(:periodoFim)  ");
            parametros.put("periodoFim", dataFim);
        }

        query.append("  GROUP BY sb.descricao ORDER BY sb.descricao  ");


        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }

    public List<?> pesquisarRelatoriosSubmotivoPorSupervisor(Long idUsuario, Date dataInicio, Date dataFim,
                                                             Long idSubmotivo) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        query.append(" SELECT SUM(ab.valor) as total,count(ab.id) as qtdade, sb.descricao submotivo ,'' as cor  ");
        query.append(" from atendimento_backoffice ab ");
        query.append(" JOIN submotivo sb on sb.id = ab.submotivo ");

        query.append(" WHERE 1=1 ");

        if (idUsuario != null) {

            query.append(" and ab.equipe in ( SELECT  DISTINCT es.equipe from usuario u INNER JOIN equipe_supervisor es on es.supervisor = u.id WHERE es.supervisor = :supervisor   )  ");
            parametros.put("supervisor", idUsuario);
        }

        if (idSubmotivo != null) {

            query.append(" and ab.submotivo = :submotivo ");
            parametros.put("submotivo", idSubmotivo);
        }

        if (dataInicio != null && dataFim != null) {

            query.append(" and date(ab.data_alteracao) BETWEEN date(:periodoInicio) and date(:periodoFim) ");
            parametros.put("periodoInicio", dataInicio);
            parametros.put("periodoFim", dataFim);

        } else if (dataInicio != null) {

            query.append("and date(ab.data_alteracao) >= date(:periodoInicio) ");
            parametros.put("periodoInicio", dataInicio);

        } else if (dataFim != null) {

            query.append("and date(ab.data_alteracao) <= date(:periodoFim)  ");
            parametros.put("periodoFim", dataFim);
        }

        query.append("  GROUP BY sb.descricao ORDER BY sb.descricao  ");


        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }

    public List<?> pesquisarQuantidadeConsistencia(Long idUsuario, Date dataInicio, Date dataFim) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        query.append(
                "SELECT   c.codigo_consistencia as codigo, c.descricao, COUNT(c.codigo_consistencia) as quantidade, SUM(ab.valor) as total,abc.tratada as tratadas  ");
        query.append("  		from atendimento_backoffice ab ");
        query.append("			JOIN atendimento_backoffice_consistencia abc on abc.atendimento_id = ab.id ");
        query.append("			JOIN consistencia c on c.id = abc.consistencia_id ");
        query.append(" 		WHERE 1=1  ");

        if (idUsuario != null) {

            query.append(" 		and ab.usuario_alteracao = :usuario  ");
            parametros.put("usuario", idUsuario);
        }

        if (dataInicio != null && dataFim != null) {

            query.append(" and date(ab.data_alteracao) BETWEEN date(:periodoInicio) and date(:periodoFim) ");
            parametros.put("periodoInicio", dataInicio);
            parametros.put("periodoFim", dataFim);

        } else if (dataInicio != null) {

            query.append("and date(ab.data_alteracao) >= date(:periodoInicio) ");
            parametros.put("periodoInicio", dataInicio);

        } else if (dataFim != null) {

            query.append("and date(ab.data_alteracao) <= date(:periodoFim)  ");
            parametros.put("periodoFim", dataFim);
        }

        query.append(" GROUP BY c.codigo_consistencia,c.descricao, abc.tratada,c.codigo ");
        query.append(" 	ORDER BY c.codigo");

        return tratarDadosAtendimentoQuantidadeConsistencia(searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros));
    }


    public List<?> pesquisarQuantidadeConsistenciaSupervisor(Long idSupervisor, Date dataInicio, Date dataFim) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        query.append(
                "SELECT   c.codigo_consistencia as codigo, c.descricao, COUNT(c.codigo_consistencia) as quantidade, SUM(ab.valor) as total,abc.tratada as tratadas  ");
        query.append("  		from atendimento_backoffice ab ");
        query.append("			JOIN atendimento_backoffice_consistencia abc on abc.atendimento_id = ab.id ");
        query.append("			JOIN consistencia c on c.id = abc.consistencia_id ");

        query.append(" WHERE ab.equipe in ( SELECT  DISTINCT es.equipe from usuario u INNER JOIN equipe_supervisor es on es.supervisor = u.id WHERE es.supervisor = :supervisor   )  ");
        parametros.put("supervisor", idSupervisor);


        if (dataInicio != null && dataFim != null) {

            query.append(" and date(ab.data_alteracao) BETWEEN date(:periodoInicio) and date(:periodoFim) ");
            parametros.put("periodoInicio", dataInicio);
            parametros.put("periodoFim", dataFim);

        } else if (dataInicio != null) {

            query.append("and date(ab.data_alteracao) >= date(:periodoInicio) ");
            parametros.put("periodoInicio", dataInicio);

        } else if (dataFim != null) {

            query.append("and date(ab.data_alteracao) <= date(:periodoFim)  ");
            parametros.put("periodoFim", dataFim);
        }

        query.append(" GROUP BY c.codigo_consistencia,c.descricao, abc.tratada,c.codigo ");
        query.append(" 	ORDER BY c.codigo");

        return tratarDadosAtendimentoQuantidadeConsistencia(searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros));
    }


    public List<?> pesquisarProdutividadeAtendimento(List<Long> equipeLong, List<Long> usuarioLong, Long produtoLong, Long loja, Usuario usurioLogado, Date dataInicio, Date dataFim, TipoVisualizacaoEnum tipoVisualizacao) {

        Map<String, Object> parametros = new HashMap<>();
        StringBuilder where = new StringBuilder();

        if (CollectionUtils.isNotEmpty(usuarioLong)) {

            where.append("\tand h.usuario_cadastro in (:usuario) ");
            parametros.put("usuario", usuarioLong);
        }

        if (CollectionUtils.isNotEmpty(equipeLong)) {
            where.append("\tand h.usuario_cadastro in (select id from usuario where equipe in (:equipe)) ");
            parametros.put("equipe", equipeLong);
        }

        if (produtoLong != null) {

            where.append("\tand a.produto = :produto ");
            parametros.put("produto", produtoLong);
        }

        if (loja != null) {

            where.append("\tand a.loja = :loja ");
            parametros.put("loja", loja);
        }


        String field = "";
        String join = "";

        if (TipoVisualizacaoEnum.LOJA.equals(tipoVisualizacao)) {

            field = "l.cod_loja ";
            join = "left join loja l on a.loja = l.id ";

        } else if (TipoVisualizacaoEnum.EQUIPE.equals(tipoVisualizacao)) {

            field = "e.nome ";
            join = "join equipe e on a.equipe = e.id ";

        } else if (TipoVisualizacaoEnum.USUARIO.equals(tipoVisualizacao)) {

            field = "u.nome ";
            join = " join usuario u on h.usuario_cadastro = u.id ";

        } else if (TipoVisualizacaoEnum.PRODUTO.equals(tipoVisualizacao)) {

            field = "p.descricao ";
            join = " join produto p on a.produto = p.id ";
        } else if (TipoVisualizacaoEnum.CONSISTENCIA.equals(tipoVisualizacao)) {

            field = " c.codigo_consistencia ";
            join = " 	JOIN atendimento_backoffice_consistencia abc ON a.id = abc.atendimento_id " + "	JOIN consistencia c ON abc.consistencia_id = c.id  ";
        }

        StringBuilder query = new StringBuilder();
        query.append("select a.visualizacao, ");
        query.append("\t\tsum(a.qtde_cpf) as qtde_cpf, ");
        query.append("\t\tsum(a.qtde_atendimento) as qtde_atendimento, ");
        query.append("\t\tsum(a.qtde_proposta) as qtde_proposta, ");
        query.append("\t\tsum(a.qtde_contrato) as qtde_contrato, ");
        query.append("\t\tsum(a.valor_total_vendido) as valor_total_vendido, ");
        query.append("\t\tsum(a.valor_total_pago) as valor_total_pago, ");
        query.append("\t\tcoalesce(cast(sum(a.qtde_proposta) as numeric) / cast(sum(a.qtde_atendimento) as numeric) , 0) as porcentagem_proposta, ");
        query.append("\t\tcoalesce(cast(sum(a.qtde_contrato) as numeric) / cast(sum(a.qtde_atendimento) as numeric) , 0) as porcentagem_contrato ");
        query.append("\t\tfrom (  ");
        query.append("\t\tselect " + field + " as visualizacao, ");
        query.append("\t\t count(distinct a.cpf) as qtde_cpf, ");
        query.append("\t\t  count(distinct h.id) as qtde_atendimento, ");
        query.append("\t\t sum(case when s.acao = 'PROPOSTA_EFETIVADA' then 1 else 0 end) as qtde_proposta, ");
        query.append("\t\t  sum(case when (s.acao = 'PROPOSTA_EFETIVADA' ) then 1 else 0 end) as qtde_contrato, ");
        query.append("\t\t  coalesce(sum( a.valor ), 0) as valor_total_vendido, ");
        query.append("\t\t coalesce(sum(case when (s.acao = 'PROPOSTA_EFETIVADA' ) then a.valor else 0 end), 0) as valor_total_pago");
        query.append("\t\t from public.historico_atendimento_backoffice h ");
        query.append("\t\tjoin public.atendimento_backoffice a on h.atendimento_backoffice = a.id ");
        query.append("\t\tLEFT JOIN submotivo s on h.submotivo = s.id ");
        query.append(join);
        query.append("\twhere date(h.data_cadastro) between date(:dataInicial) and date(:dataFinal) ");
        query.append(where.toString());
        query.append("\tgroup by " + field);
        query.append(") a ");
        query.append(" GROUP BY a.visualizacao ");
        query.append(" ORDER BY a.visualizacao ");

        parametros.put("dataInicial", dataInicio);
        parametros.put("dataFinal", dataFim);

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }


    public List<ProdutividadeSacDto> pesquisarProdutividadeAtendimentoSac(List<Long> equipeLong, List<Long> usuarioLong,
                                                                          Long produtoLong, Long loja,
                                                                          Date dataInicio, Date dataFim,
                                                                          TipoVisualizacaoEnum tipoVisualizacao) {

        Map<String, Object> parametros = new HashMap<>();
        StringBuilder where = new StringBuilder();

        // ... (Seus IFs de filtro continuam iguais) ...
        if (CollectionUtils.isNotEmpty(usuarioLong)) {
            where.append("\tand a.usuario_cadastro in (:usuario) ");
            parametros.put("usuario", usuarioLong);
        }
        if (CollectionUtils.isNotEmpty(equipeLong)) {
            where.append("\tand a.usuario_cadastro in (select id from usuario where equipe in (:equipe)) ");
            parametros.put("equipe", equipeLong);
        }
        if (produtoLong != null) {
            where.append("\tand a.produto = :produto ");
            parametros.put("produto", produtoLong);
        }
        if (loja != null) {
            where.append("\tand a.loja = :loja ");
            parametros.put("loja", loja);
        }

        String fieldSelect = "";
        String fieldGroup = "";
        String join = "";
        String selectViw = "a.visualizacao,";

        if (TipoVisualizacaoEnum.LOJA.equals(tipoVisualizacao)) {
            fieldSelect = "l.cod_loja AS visualizacao, sm.descricao AS cor";
            fieldGroup = "l.cod_loja, sm.descricao";
            join = "LEFT JOIN loja l ON a.loja = l.id JOIN submotivo sm ON a.submotivo = sm.id";
            selectViw = "a.visualizacao, a.cor,";
        } else if (TipoVisualizacaoEnum.EQUIPE.equals(tipoVisualizacao)) {
            fieldSelect = "e.nome AS visualizacao, sm.descricao AS cor";
            fieldGroup = "e.nome, sm.descricao";
            join = "JOIN equipe e ON a.equipe = e.id JOIN submotivo sm ON a.submotivo = sm.id";
            selectViw = "a.visualizacao, a.cor,";
        } else if (TipoVisualizacaoEnum.CAMPANHA.equals(tipoVisualizacao)) {
            fieldSelect = "c.descricao AS visualizacao";
            fieldGroup = "c.descricao ";
            join = " JOIN campanha c ON a.campanha = c.id JOIN submotivo sm ON a.submotivo = sm.id";
            selectViw = " a.visualizacao, ";
        } else if (TipoVisualizacaoEnum.USUARIO.equals(tipoVisualizacao)) {
            fieldSelect = "u.nome AS visualizacao";
            fieldGroup = "u.nome";
            join = "JOIN usuario u ON a.usuario_cadastro = u.id JOIN submotivo sm ON a.submotivo = sm.id";
            selectViw = "a.visualizacao, ";
        } else if (TipoVisualizacaoEnum.PRODUTO.equals(tipoVisualizacao)) {
            fieldSelect = "p.descricao AS visualizacao, sm.descricao AS cor";
            fieldGroup = "p.descricao, sm.descricao";
            join = "JOIN produto p ON a.produto = p.id JOIN submotivo sm ON a.submotivo = sm.id";
            selectViw = "a.visualizacao, a.cor,";
        } else if (TipoVisualizacaoEnum.SUBMOTIVO.equals(tipoVisualizacao)) {
            fieldSelect = "sm.descricao AS visualizacao, sm.cor AS cor";
            fieldGroup = "sm.descricao, sm.cor";
            join = "JOIN submotivo sm ON a.submotivo = sm.id";
            selectViw = "a.visualizacao, a.cor,";
        } else if (TipoVisualizacaoEnum.MOTIVO.equals(tipoVisualizacao)) {
            fieldSelect = "m.descricao AS visualizacao, m.cor AS cor";
            fieldGroup = "m.descricao, m.cor";
            join = "JOIN motivo m ON a.motivo = m.id";
            selectViw = "a.visualizacao, a.cor,";
        } else if (TipoVisualizacaoEnum.STATUS.equals(tipoVisualizacao)) {
            fieldSelect = "s.descricao AS visualizacao";
            fieldGroup = "s.descricao";
            join = ""; // já está no LEFT JOIN abaixo
            selectViw = "a.visualizacao,";
        }

        StringBuilder query = new StringBuilder();
        query.append("SELECT ");
        query.append(selectViw);
        query.append("       SUM(a.qtde_cpf) AS qtdeCpf, ");
        query.append("       SUM(a.qtde_atendimento) AS qtdeAtendimento, ");
        query.append("       SUM(a.qtdade_resolvido_n1) AS qtdadeResolvidoN1, ");
        query.append("       SUM(a.qtde_resolvido_n2) AS qtdeResolvidoN2, ");
        query.append("       SUM(a.qtde_derivado) AS qtdeDerivado, ");
        query.append("       SUM(a.qtdade_concluido) AS qtdadeConcluido, ");

        // --- NOVO: Totais de FCR ---
        query.append("       SUM(a.qtde_fcr) AS qtdeFcr, ");

        // --- NOVO: Percentual de FCR (Cálculo seguro contra divisão por zero) ---
        // Nota: Mantive o padrão de retornar decimal (0.95). Se quiser 95%, adicione * 100
        query.append("       COALESCE(CAST(SUM(a.qtde_fcr) AS NUMERIC) / NULLIF(SUM(a.qtde_atendimento),0), 0) AS percentualFcr, ");

        query.append("       SUM(a.qtdade_demanda_no_prazo) AS qtdadeDemandaNoPrazo, ");
        query.append("       SUM(a.qtdade_demanda_prazo_estourado) AS qtdadeDemandaPrazoEstourado, ");
        query.append("       COALESCE(CAST(SUM(a.qtdade_demanda_no_prazo) AS NUMERIC) / NULLIF(SUM(a.qtde_atendimento),0), 0) AS percentualNoPrazo, ");
        query.append("       COALESCE(CAST(SUM(a.qtdade_demanda_prazo_estourado) AS NUMERIC) / NULLIF(SUM(a.qtde_atendimento),0), 0) AS percentualPrazoEstourado, ");
        query.append("       SUM(a.qtidade_aberto) AS qtidade_aberto ");
        query.append("FROM ( ");
        query.append("    SELECT " + fieldSelect + ", ");
        query.append("           COUNT(DISTINCT a.cpf) AS qtde_cpf, ");
        query.append("           COUNT(DISTINCT a.id) AS qtde_atendimento, ");

        // --- NOVO: Contagem baseada na flag do banco ---
        // Se ind_fcr for TRUE, conta 1.
        query.append("           SUM(CASE WHEN a.ind_fcr IS TRUE THEN 1 ELSE 0 END) AS qtde_fcr, ");

        query.append("           SUM(CASE WHEN s.acao = 'CONCLUIR_N1' THEN 1 ELSE 0 END) AS qtdade_resolvido_n1, ");
        query.append("           SUM(CASE WHEN s.acao = 'CONCLUIR' THEN 1 ELSE 0 END) AS qtde_resolvido_n2, ");
        query.append("           SUM(CASE WHEN (s.acao = 'DERIVAR' OR a.enviar_n2 IS TRUE) THEN 1 ELSE 0 END) AS qtde_derivado, ");
        query.append("           SUM(CASE WHEN ((s.acao = 'CONCLUIR' OR a.demanda_encerrada IS TRUE) AND a.enviar_n2 IS TRUE) THEN 1 ELSE 0 END) AS qtdade_concluido, ");
        query.append("           SUM(CASE WHEN ((s.acao NOT LIKE 'CONCLUIR%' OR a.demanda_encerrada IS FALSE) AND a.abertura_demanda <= a.prazo_demanda) THEN 1 ELSE 0 END) AS qtdade_demanda_no_prazo, ");
        query.append("           SUM(CASE WHEN ((s.acao NOT LIKE 'CONCLUIR%' OR a.demanda_encerrada IS FALSE) AND a.abertura_demanda > a.prazo_demanda) THEN 1 ELSE 0 END) AS qtdade_demanda_prazo_estourado, ");
        query.append("           SUM(CASE WHEN ((s.acao NOT LIKE 'CONCLUIR%' OR a.demanda_encerrada IS FALSE) AND a.enviar_n2 IS TRUE) THEN 1 ELSE 0 END) AS qtidade_aberto ");
        query.append("    FROM public.atendimento a ");
        query.append("    LEFT JOIN status_atendimento s ON a.status = s.id ");
        query.append(join);
        query.append("    WHERE DATE(a.data_cadastro) BETWEEN DATE(:dataInicial) AND DATE(:dataFinal) ");
        query.append(where.toString());
        query.append("    GROUP BY ").append(fieldGroup);
        query.append(") a ");

        if (fieldSelect.contains("cor")) {
            query.append("GROUP BY a.visualizacao, a.cor ");
        } else {
            query.append("GROUP BY a.visualizacao ");
        }

        query.append("ORDER BY a.visualizacao ");

        parametros.put("dataInicial", dataInicio);
        parametros.put("dataFinal", dataFim);

        System.out.println(query.toString());
        return gerarDtoProdutividade(searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros));
    }

    public List<ProdutividadeSacDto> pesquisarProdutividadeAtendimentoSacTotal(List<Long> equipeLong, List<Long> usuarioLong,
                                                                               Long produtoLong, Long loja,
                                                                               Date dataInicio, Date dataFim) {

        Map<String, Object> parametros = new HashMap<>();
        StringBuilder where = new StringBuilder();

        if (CollectionUtils.isNotEmpty(usuarioLong)) {
            where.append("\tand a.usuario_cadastro in (:usuario) ");
            parametros.put("usuario", usuarioLong);
        }

        if (CollectionUtils.isNotEmpty(equipeLong)) {
            where.append("\tand a.usuario_cadastro in (select id from usuario where equipe in (:equipe)) ");
            parametros.put("equipe", equipeLong);
        }

        if (produtoLong != null) {
            where.append("\tand a.produto = :produto ");
            parametros.put("produto", produtoLong);
        }

        if (loja != null) {
            where.append("\tand a.loja = :loja ");
            parametros.put("loja", loja);
        }


        StringBuilder query = new StringBuilder();
        query.append("SELECT ");
        query.append("       COUNT(DISTINCT a.cpf) AS qtdeCpf, ");
        query.append("       COUNT(DISTINCT a.id) AS qtdeAtendimento, ");
        query.append("       SUM(CASE WHEN s.acao = 'CONCLUIR_N1' THEN 1 ELSE 0 END) AS qtdadeResolvidoN1, ");
        query.append("       SUM(CASE WHEN s.acao = 'CONCLUIR' THEN 1 ELSE 0 END) AS qtdeResolvidoN2, ");
        query.append("       SUM(CASE WHEN (s.acao = 'DERIVAR' OR a.enviar_n2 IS TRUE) THEN 1 ELSE 0 END) AS qtdeDerivado, ");
        query.append("       SUM(CASE WHEN (s.acao LIKE 'CONCLUIR%' OR a.demanda_encerrada IS TRUE) THEN 1 ELSE 0 END) AS qtdadeConcluido, ");

        // --- FCR (Resolvido de Primeira) ---
        query.append("       SUM(CASE WHEN a.ind_fcr IS TRUE THEN 1 ELSE 0 END) AS qtdeFcr, ");
        query.append("       COALESCE(CAST(SUM(CASE WHEN a.ind_fcr IS TRUE THEN 1 ELSE 0 END) AS numeric) / NULLIF(COUNT(DISTINCT a.id),0), 0) AS percentualFcr, ");

        // --- NOVO: REINCIDÊNCIA (Rechamada) ---
        // Quantidade Absoluta
        query.append("       SUM(CASE WHEN a.ind_reincidencia IS TRUE THEN 1 ELSE 0 END) AS qtdeReincidencia, ");

        // Taxa de Reincidência (Quanto menor, melhor)
        query.append("       COALESCE(CAST(SUM(CASE WHEN a.ind_reincidencia IS TRUE THEN 1 ELSE 0 END) AS numeric) / NULLIF(COUNT(DISTINCT a.id),0), 0) AS percentualReincidencia, ");
        // ---------------------------------------

        // --- PRAZOS ---
        // No Prazo
        // Se prazo for NULL, usa 9999 (nunca vence)
        query.append("       SUM(CASE WHEN ");
        query.append("           (s.acao NOT LIKE 'CONCLUIR%' AND (a.demanda_encerrada IS NULL OR a.demanda_encerrada IS FALSE)) ");
        query.append("           AND (DATE(a.data_cadastro) + COALESCE(sm.prazo_demanda, 9999)) >= CURRENT_DATE ");
        query.append("       THEN 1 ELSE 0 END) AS qtdadeDemandaNoPrazo, ");

        // --- ESTOURADO ---
        //  Se prazo for NULL, usa 9999
        query.append("       SUM(CASE WHEN ");
        query.append("           (s.acao NOT LIKE 'CONCLUIR%' AND (a.demanda_encerrada IS NULL OR a.demanda_encerrada IS FALSE)) ");
        query.append("           AND (DATE(a.data_cadastro) + COALESCE(sm.prazo_demanda, 9999)) < CURRENT_DATE ");
        query.append("       THEN 1 ELSE 0 END) AS qtdadeDemandaPrazoEstourado, ");

        // % No Prazo
        query.append("       COALESCE(CAST(SUM(CASE WHEN (s.acao NOT LIKE 'CONCLUIR%' AND (a.demanda_encerrada IS NULL OR a.demanda_encerrada IS FALSE)) AND (DATE(a.data_cadastro) + sm.prazo_demanda) >= CURRENT_DATE THEN 1 ELSE 0 END) AS numeric) / NULLIF(COUNT(DISTINCT a.id),0), 0) AS percentualNoPrazo, ");

        // % Estourado
        query.append("       COALESCE(CAST(SUM(CASE WHEN (s.acao NOT LIKE 'CONCLUIR%' AND (a.demanda_encerrada IS NULL OR a.demanda_encerrada IS FALSE)) AND (DATE(a.data_cadastro) + sm.prazo_demanda) < CURRENT_DATE THEN 1 ELSE 0 END) AS numeric) / NULLIF(COUNT(DISTINCT a.id),0), 0) AS percentualPrazoEstourado, ");

        query.append("       SUM(CASE WHEN (s.acao NOT LIKE 'CONCLUIR%' AND (a.demanda_encerrada IS NULL OR a.demanda_encerrada IS FALSE)) THEN 1 ELSE 0 END) AS qtidadeAberto ");

        query.append("FROM public.atendimento a ");
        query.append("LEFT JOIN status_atendimento s ON a.status = s.id ");
        query.append("LEFT JOIN submotivo sm ON a.submotivo = sm.id "); // Obrigatório para o cálculo de prazo
        query.append("WHERE DATE(a.data_cadastro) BETWEEN DATE(:dataInicial) AND DATE(:dataFinal) ");
        query.append(where);

        parametros.put("dataInicial", dataInicio);
        parametros.put("dataFinal", dataFim);

        return gerarDtoProdutividadeTotal(searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros));
    }

    private List<ProdutividadeSacDto> gerarDtoProdutividade(List<Object[]> resultados) {

        if (CollectionUtils.isEmpty(resultados))
            return null;

        List<ProdutividadeSacDto> lista = new ArrayList<>();

        for (Object[] row : resultados) {
            ProdutividadeSacDto dto = new ProdutividadeSacDto();

            int idx = 0;

            // 1. Visualização (Sempre é o primeiro)
            dto.setVisualizacao((String) row[idx++]);

            // 2. Cor (Condicional)
            // Antes era > 12. Como adicionamos 2 colunas novas (Qtde FCR e % FCR),
            // a régua sobe para 14. Se tiver 15 colunas, significa que tem COR.
            if (row.length > 14) {
                dto.setCor((String) row[idx++]);
            }

            // 3. Mapeamento sequencial conforme a Query
            dto.setQtdeCpf(safeLong(row, idx++));
            dto.setQtdeAtendimento(safeLong(row, idx++));
            dto.setQtdadeResolvidoN1(safeLong(row, idx++));
            dto.setQtdeResolvidoN2(safeLong(row, idx++));
            dto.setQtdeDerivado(safeLong(row, idx++));
            dto.setQtdadeConcluido(safeLong(row, idx++));

            // --- NOVOS CAMPOS (Inseridos aqui conforme a ordem da Query) ---
            dto.setQtdeFcr(safeLong(row, idx++));
            dto.setPercentualFcr(safeDouble(row, idx++));
            // --------------------------------------------------------------

            dto.setQtdadeDemandaNoPrazo(safeLong(row, idx++));
            dto.setQtdadeDemandaPrazoEstourado(safeLong(row, idx++));
            dto.setPercentualNoPrazo(safeDouble(row, idx++));
            dto.setPercentualPrazoEstourado(safeDouble(row, idx++));

            // O último campo (sem incremento de idx no final, ou com, tanto faz pois acaba aqui)
            dto.setQtidadeEmAberto(safeLong(row, idx));

            lista.add(dto);
        }
        return lista;
    }

    private List<ProdutividadeSacDto> gerarDtoProdutividadeTotal(List<Object[]> resultados) {

        if (CollectionUtils.isEmpty(resultados)) return new ArrayList<>();

        List<ProdutividadeSacDto> lista = new ArrayList<>();

        // Geralmente query de totais retorna apenas 1 linha, mas iteramos por segurança
        for (Object[] row : resultados) {
            ProdutividadeSacDto dto = new ProdutividadeSacDto();
            int idx = 0;

            dto.setQtdeCpf(safeLong(row, idx++));              // 0
            dto.setQtdeAtendimento(safeLong(row, idx++));      // 1
            dto.setQtdadeResolvidoN1(safeLong(row, idx++));    // 2
            dto.setQtdeResolvidoN2(safeLong(row, idx++));      // 3
            dto.setQtdeDerivado(safeLong(row, idx++));         // 4
            dto.setQtdadeConcluido(safeLong(row, idx++));      // 5

            // --- FCR ---
            dto.setQtdeFcr(safeLong(row, idx++));              // 6
            dto.setPercentualFcr(safeDouble(row, idx++));      // 7

            // --- NOVO: REINCIDÊNCIA ---
            dto.setQtdeReincidencia(safeLong(row, idx++));     // 8
            dto.setPercentualReincidencia(safeDouble(row, idx++)); // 9
            // --------------------------

            // --- PRAZOS (Índices deslocados) ---
            dto.setQtdadeDemandaNoPrazo(safeLong(row, idx++));         // 10
            dto.setQtdadeDemandaPrazoEstourado(safeLong(row, idx++));  // 11
            dto.setPercentualNoPrazo(safeDouble(row, idx++));          // 12
            dto.setPercentualPrazoEstourado(safeDouble(row, idx++));   // 13

            dto.setQtidadeEmAberto(safeLong(row, idx));                // 14

            lista.add(dto);
        }
        return lista;
    }

    private Long safeLong(Object[] row, int idx) {
        return row[idx] != null ? ((Number) row[idx]).longValue() : 0L;
    }

    private Double safeDouble(Object[] row, int idx) {
        return row[idx] != null ? ((Number) row[idx]).doubleValue() : 0.0;
    }


    private List<?> tratarDadosAtendimentoQuantidadeConsistencia(List<?> list) {

        if (CollectionUtils.isEmpty(list))
            return list;

        Map<Long, Object[]> mapAtendimentos = new TreeMap<Long, Object[]>();

        for (Object objects : list) {

            Object[] row = (Object[]) objects;

            Long id = ((Integer) row[0]).longValue();

            if (mapAtendimentos.isEmpty() || !mapAtendimentos.containsKey(id)) {

                Object[] objectValue = new Object[7];

                objectValue[0] = id;
                objectValue[1] = row[1];
                objectValue[2] = row[2];
                objectValue[3] = row[3];

                objectValue[4] = Integer.valueOf(0);
                objectValue[5] = Integer.valueOf(0);

                if (row[4] != null && ((Boolean) row[4]).booleanValue()) {

                    objectValue[4] = ((BigInteger) row[2]).intValue();

                } else {

                    objectValue[5] = ((BigInteger) row[2]).intValue();

                }

                mapAtendimentos.put(id, objectValue);

            } else {

                if (row[4] != null && ((Boolean) row[4]).booleanValue()) {

                    mapAtendimentos.get(id)[4] = Integer.valueOf(((BigInteger) row[2]).intValue());
                    mapAtendimentos.get(id)[3] = row[3];
                } else {

                    mapAtendimentos.get(id)[5] = Integer.valueOf(((BigInteger) row[2]).intValue());

                }

            }

        }

        return new ArrayList<Object[]>(mapAtendimentos.values());

    }

    public void resetarAtendimentosEnviados() {

        StringBuilder query = new StringBuilder();

        query.append("update atendimento_backoffice ");
        query.append("set enviado = false ");
        query.append("where enviado = true ");
        query.append(" and status is null ");

        executarSql(DaoEnum.NATIVE_OBJECT, query.toString(), null);

    }

    public void resetarAtendimentosEnviadosPorEquipe(Long equipe) {

        StringBuilder query = new StringBuilder();

        Map<String, Object> parametros = new HashMap<>();
        query.append("update atendimento_backoffice set enviado = false  ");
        query.append("	WHERE id in ");
        query.append(" (SELECT ab.id FROM atendimento_backoffice ab ");
        query.append(" join loja l on l.id = ab.loja ");
        query.append(" join equipe_loja el on el.loja = ab.loja ");
        query.append(" WHERE el.equipe = :equipe  and enviado = true	and status is null) ");
        parametros.put("equipe", equipe);

        executarSql(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }

    public List<?> pesquisarQuantidadeMonitorMailing(List<Long> idsImportacao, List<Long> lojas, List<Long> equipes) {

        StringBuilder query = new StringBuilder();

        Map<String, Object> parametros = new HashMap<>();

        query.append("SELECT b.total_atendimentos,");
        query.append(" b.total_realizados,");

        query.append(" b.total_nao_trabalhado, ");

        query.append(" 	b.total_concluida, ");
        query.append(" 		b.total_integrada, ");
        query.append(" 		b.total_fim_fila, ");
        query.append(" 	coalesce((b.total_atendimentos - b.total_concluida),0) as qRestanteRealizado,  ");
        query.append(" 	case  WHEN b.total_atendimentos = 0  THEN 0 else coalesce((cast(b.total_realizados as numeric) / cast(b.total_atendimentos as numeric)),0)  END as p_realizado,  ");
        query.append(" 	case  WHEN b.total_atendimentos = 0  THEN 0 else coalesce((cast(b.total_concluida as numeric) / cast(b.total_atendimentos as numeric)),0)  END as p_concluido ");
        query.append("\tFROM ( ");
        query.append(" 	SELECT  COUNT ( a1.id ) AS total_atendimentos,  ");
        query.append("\t SUM ( CASE WHEN ( a1.status IS NULL ) THEN 1 ELSE 0 END ) AS total_nao_trabalhado,");
        query.append("  count(DISTINCT CASE when(a1.status is not null) THEN a1.id ELSE NULL END) as total_realizados,  ");
        query.append("  count(DISTINCT CASE when(sb1.acao IN ('PROPOSTA_EFETIVADA', 'PROPOSTA_PARCIAL', 'CONCLUIR', 'CONTATO', 'SEM_ACAO', 'BLOQUEAR_CPF')) THEN a1.id ELSE NULL END) as total_concluida, ");
        query.append("  count(DISTINCT CASE when(m1.acao IN ('NONE','INTEGRADA')) THEN a1.id ELSE NULL END) as total_integrada, ");
        query.append("  count(DISTINCT CASE when(sb1.acao in  ('NONE','FIM_FILA') ) THEN a1.id ELSE NULL END) as total_fim_fila ");
        query.append("  FROM atendimento_backoffice a1  ");
        query.append("  LEFT JOIN  status_atendimento s1 on s1.id = a1.status  ");
        query.append(" 	LEFT JOIN  motivo m1 on m1.id = a1.motivo ");
        query.append("  LEFT JOIN  submotivo sb1 on sb1.id = a1.submotivo ");
        query.append("  WHERE 1=1");

        if (CollectionUtils.isNotEmpty(idsImportacao)) {

            query.append(" and a1.extrator in " + sqlFormatedList(idsImportacao));
            query.append("");

        }

        if (CollectionUtils.isNotEmpty(lojas)) {

            query.append(" and a1.loja in " + sqlFormatedList(lojas));

        }

        if (CollectionUtils.isNotEmpty(equipes)) {
            query.append(" and a1.equipe in " + sqlFormatedList(equipes));

        }


        query.append(" ) b");
        System.out.println(query.toString());
        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);


    }

    public List<Object[]> pesquisarQuantidadeMonitorMailingStatusExtrator(List<Long> idsImportacao, List<Long> lojas, List<Long> equipes) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();
        query.append(" SELECT  count(DISTINCT a1.id) as total, ");
        query.append(" s1.descricao || ', ' || m1.descricao || ', ' || sb1.descricao as tabulacao, ");
        query.append(" '' as perc");
        query.append(" from atendimento_backoffice	a1 ");
        query.append(" LEFT JOIN  status_atendimento s1 on s1.id = a1.status ");
        query.append(" LEFT JOIN  motivo m1 on m1.id = a1.motivo ");
        query.append(" LEFT JOIN  submotivo sb1 on sb1.id = a1.submotivo  ");
        query.append(" WHERE 1=1 ");

        if (CollectionUtils.isNotEmpty(idsImportacao)) {

            query.append(" and a1.extrator in " + sqlFormatedList(idsImportacao));

        }

        if (CollectionUtils.isNotEmpty(lojas)) {

            query.append(" and a1.loja in " + sqlFormatedList(lojas));

        }

        if (CollectionUtils.isNotEmpty(equipes)) {
            query.append(" and a1.equipe in " + sqlFormatedList(equipes));

        }

        query.append(" GROUP BY tabulacao ");
        System.out.println(query.toString());
        return tratarDadosAtendimentoStatusExtrator(searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros));


    }

    public List<Object[]> tratarDadosAtendimentoStatusExtrator(List<Object[]> list) {

        if (CollectionUtils.isEmpty(list))
            return list;

        Long total = Long.valueOf(0);

        List<Object[]> resultado = new ArrayList<>();

        for (Object[] objects : list) {

            Long quantidade = ((BigInteger) objects[0]).longValue();

            total = total + quantidade;


        }

        for (Object[] objects : list) {


            String status = objects[1] == null ? null : objects[1].toString();

            if (StringUtils.isNotBlank(status)) {

                Long quantidade = ((BigInteger) objects[0]).longValue();

                Object[] aux = new Object[3];
                aux[0] = quantidade;
                aux[1] = status;
                aux[2] = (total == 0 ? Long.valueOf(0) : Double.valueOf((double) quantidade.longValue() / total.longValue()));
                resultado.add(aux);
            }


        }


        return resultado;

    }

    public Integer pesquisarQuantidadeTelefonePorExtrator(List<Long> extrator, List<Long> lojas) {
        // TODO Auto-generated method stub
        Map<String, Object> parametros = new HashMap<>();
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT Sum(x.qtd) ");
        sql.append("FROM   (SELECT Count(DISTINCT t1) AS qtd ");
        sql.append("         FROM   PUBLIC.atendimento_backoffice ab  ");
        sql.append("               INNER JOIN atendimento a ");
        sql.append("                       ON ab.atendimento = a.id ");
        sql.append("               INNER JOIN telefone t1 ");
        sql.append("                       on t1.atendimento = a.id ");

        sql.append("        WHERE 1=1 ");

        if (CollectionUtils.isNotEmpty(extrator)) {

            sql.append("        and  ab.extrator in " + sqlFormatedList(extrator));
        }

        if (CollectionUtils.isNotEmpty(lojas)) {
            sql.append("        and  ab.loja in " + sqlFormatedList(lojas));
        }

        sql.append("        GROUP  BY t1.id ) x");


        BigDecimal retorno = (BigDecimal) searchEntidade(DaoEnum.NATIVE_OBJECT, sql.toString(), parametros);

        if (retorno != null)
            return Integer.valueOf(retorno.intValue());

        return Integer.valueOf(0);
    }

    public List<?> pesquisarQuantidadeMonitorMailingFiltro(List<Long> idsImportacao, List<Long> lojas, List<Long> equipes, String fild) {

        StringBuilder query = new StringBuilder();

        Map<String, Object> parametros = new HashMap<>();

        query.append("SELECT b.total_atendimentos,");

        query.append(" b." + fild + ", ");
        query.append(" b.total_realizados,");

        query.append(" b.total_nao_trabalhado, ");

        query.append(" 	b.total_concluida, ");
        query.append(" 		b.total_integrada, ");
        query.append(" 		b.total_fim_fila, ");
        query.append(" 	coalesce((b.total_atendimentos - b.total_concluida),0) as qRestanteRealizado,  ");
        query.append(" 	case  WHEN b.total_atendimentos = 0  THEN 0 else coalesce((cast(b.total_realizados as numeric) / cast(b.total_atendimentos as numeric)),0)  END as p_realizado,  ");
        query.append(" 	case  WHEN b.total_atendimentos = 0  THEN 0 else coalesce((cast(b.total_concluida as numeric) / cast(b.total_atendimentos as numeric)),0)  END as p_concluido ");
        query.append("\tFROM ( ");
        query.append(" 	SELECT   ");

        if (fild.equals("loja_")) {
            query.append("l.cod_loja as " + fild);
        } else if (fild.equals("equipe_e")) {
            query.append("e.equipe as " + fild);

        }
        query.append(" 	  COUNT ( a1.id ) AS total_atendimentos,  ");
        query.append("\t SUM ( CASE WHEN ( a1.status IS NULL ) THEN 1 ELSE 0 END ) AS total_nao_trabalhado,");
        query.append("  count(DISTINCT CASE when(a1.status is not null) THEN a1.id ELSE NULL END) as total_realizados,  ");
        query.append("  count(DISTINCT CASE when(sb1.acao IN ('PROPOSTA_EFETIVADA', 'PROPOSTA_PARCIAL', 'CONCLUIR', 'CONTATO', 'SEM_ACAO', 'BLOQUEAR_CPF')) THEN a1.id ELSE NULL END) as total_concluida, ");
        query.append("  count(DISTINCT CASE when(m1.acao IN ('NONE','INTEGRADA')) THEN a1.id ELSE NULL END) as total_integrada, ");
        query.append("  count(DISTINCT CASE when(sb1.acao in  ('NONE','FIM_FILA') ) THEN a1.id ELSE NULL END) as total_fim_fila ");
        query.append("  FROM atendimento_backoffice a1  ");
        query.append("  LEFT JOIN  status_atendimento s1 on s1.id = a1.status  ");
        query.append(" 	LEFT JOIN  motivo m1 on m1.id = a1.motivo ");
        query.append("  LEFT JOIN  submotivo sb1 on sb1.id = a1.submotivo ");

        if (fild.equals("loja_")) {
            query.append(" JOIN  loja l on l.id = a1.loja ");
        } else if (fild.equals("equipe_")) {
            query.append(" JOIN  equipe e on e.id = a1.equipe ");

        }

        query.append("  WHERE 1=1");

        if (CollectionUtils.isNotEmpty(idsImportacao)) {
            query.append(" a1.extrator in " + sqlFormatedList(idsImportacao));
            query.append("");

        }

        if (CollectionUtils.isNotEmpty(lojas)) {

            query.append(" and a1.loja in " + sqlFormatedList(lojas));

        }

        if (CollectionUtils.isNotEmpty(equipes)) {
            query.append(" and a1.equipe in " + sqlFormatedList(equipes));

        }

        query.append(" GROUP BY " + fild);
        query.append(" ) b");
        System.out.println(query.toString());
        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);


    }

    public List<RelatorioSacDto> pesquisarRelatorioDetalhado(RelatorioFiltroDto filtro) {

        StringBuilder sql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        sql.append("SELECT ");
        sql.append("  a.protocolo, ");                          // 0
        sql.append("  a.data_cadastro, ");                      // 1
        sql.append("  c.nome AS nome_cliente, ");               // 2
        sql.append("  u.nome AS nome_operador, ");              // 3
        sql.append("  s.descricao AS status_desc, ");           // 4 (Novo)
        sql.append("  m.descricao AS motivo_desc, ");           // 5
        sql.append("  sm.descricao AS submotivo_desc, ");       // 6 (Novo)
        sql.append("  COALESCE(a.ind_fcr, true) AS fcr, ");     // 7
        sql.append("  COALESCE(a.ind_reincidencia, false) AS reincidencia, "); // 8

        // Cálculo de Prazo na Query (Para evitar lógica no Java)
        // Se (Data Cadastro + Prazo Submotivo) < Hoje e não está concluído -> Vencido
        sql.append("  CASE ");
        sql.append("    WHEN (s.acao NOT LIKE 'CONCLUIR%' AND (a.demanda_encerrada IS FALSE OR a.demanda_encerrada IS NULL)) ");
        sql.append("         AND (DATE(a.data_cadastro) + COALESCE(sm.prazo_demanda, 0)) < CURRENT_DATE ");
        sql.append("    THEN 'VENCIDO' ");
        sql.append("    ELSE 'NO PRAZO' ");
        sql.append("  END AS situacao_prazo ");                 // 9

        sql.append("FROM atendimento a ");
        sql.append("LEFT JOIN cliente c ON a.cliente = c.id ");
        sql.append("LEFT JOIN usuario u ON a.usuario_cadastro = u.id ");
        sql.append("LEFT JOIN status_atendimento s ON a.status = s.id ");
        sql.append("LEFT JOIN motivo m ON a.motivo = m.id ");
        sql.append("LEFT JOIN submotivo sm ON a.submotivo = sm.id ");

        sql.append("WHERE 1=1 ");

        // --- FILTROS ---
        if (filtro.getDataInicio() != null) {
            sql.append("AND a.data_cadastro >= :dtInicio ");
            params.put("dtInicio", filtro.getDataInicio());
        }
        if (filtro.getDataFim() != null) {
            sql.append("AND a.data_cadastro <= :dtFim ");
            params.put("dtFim", filtro.getDataFim());
        }
        if (filtro.getUsuarioSelecionado() != null) {
            sql.append("AND a.usuario_cadastro = :idUsuario ");
            params.put("idUsuario", filtro.getUsuarioSelecionado().getId());
        }


        if (filtro.getListaStatus() != null && !filtro.getListaStatus().isEmpty()) {
            sql.append("AND a.status IN (:listaStatus) ");
            params.put("listaStatus", filtro.getListaStatus());
        }

        if (filtro.isApenasFcr()) {
            sql.append(" AND a.ind_fcr = true ");
        }

        if (filtro.isApenasReincidencia()) {
            sql.append(" AND a.ind_reincidencia = true ");
        }

        sql.append("ORDER BY a.data_cadastro DESC ");

        List<Object[]> result = searchEntidades(DaoEnum.NATIVE_OBJECT, sql.toString(), params);
        return converterParaDto(result);
    }


    private List<RelatorioSacDto> converterParaDto(List<Object[]> rows) {

        List<RelatorioSacDto> lista = new ArrayList<>();

        if (rows == null) return lista;

        for (Object[] row : rows) {

            RelatorioSacDto dto = new RelatorioSacDto();
            int i = 0;

            dto.setProtocolo((String) row[i++]);
            dto.setDataCadastro((Date) row[i++]);
            dto.setNomeCliente((String) row[i++]);
            dto.setNomeOperador((String) row[i++]);
            dto.setStatusDescricao((String) row[i++]);
            dto.setMotivoDescricao((String) row[i++]);
            dto.setSubmotivoDescricao((String) row[i++]);

            // Booleanos (Postgres pode retornar 't'/'f' ou Boolean, dependendo do driver)
            dto.setFcr(Boolean.TRUE.equals(row[i++]));
            dto.setReincidencia(Boolean.TRUE.equals(row[i++]));

            dto.setSituacaoPrazo((String) row[i++]); // "VENCIDO" ou "NO PRAZO"

            lista.add(dto);
        }

        return lista;
    }


}
