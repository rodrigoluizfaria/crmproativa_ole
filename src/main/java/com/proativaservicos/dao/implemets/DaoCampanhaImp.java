package com.proativaservicos.dao.implemets;

import com.proativaservicos.model.Campanha;
import com.proativaservicos.model.Empresa;
import com.proativaservicos.util.ConverterUtilList;
import com.proativaservicos.util.Utils;
import com.proativaservicos.util.constantes.AcaoCampanhaEnum;
import com.proativaservicos.util.constantes.DaoEnum;
import com.proativaservicos.util.constantes.SegmentoEnum;
import com.proativaservicos.util.constantes.TipoCampanhaEnum;
import jakarta.inject.Named;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
@Named
public class DaoCampanhaImp extends GenericDao<Campanha> {

    public Campanha pesquisarCampanha(Long id) {

        StringBuilder query = new StringBuilder();
        query.append("select c ");
        query.append("from Campanha c ");
        query.append("\tjoin fetch c.status sc ");
        query.append("\tjoin fetch c.empresa e ");
        query.append("\tleft join fetch c.usuarioAlteracao u ");
        query.append("\tleft join fetch c.fila ");
        query.append("\tleft join fetch c.integrarWs ");
        query.append("where c.id = :campanha");

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("campanha", id);
        return (Campanha) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

    public void excluirEquipesCampanha(Long id) {

        String query = "delete from campanha_equipe where campanha = :campanha";

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("campanha", id);

        executarSql(DaoEnum.NATIVE_OBJECT, query, parametros);
    }

    public List<?> pesquisarCampanhas(Campanha campanha, Date dataInicio, Date dataFim) {

        HashMap<String, Object> parametros = new HashMap<>();
        StringBuilder query = new StringBuilder();

        query.append("select c.id, ");
        query.append(" \t c.descricao as descricao, ");
        query.append("\t c.tipo_campanha, ");
        query.append("\t s.descricao as status_campanha, ");
        query.append("\t u.nome as usuario, ");
        query.append("\t c.data_alteracao as data_alteracao, ");
        query.append("\t e.nome ");
        query.append("from campanha c ");
        query.append("\tjoin status_campanha s on (c.status_campanha = s.id) ");
        query.append("  join usuario u on (c.usuario_alteracao = u.id) ");
        query.append("\tjoin empresa e on c.empresa = e.id ");
        query.append("where e.id = :empresa ");

        parametros.put("empresa", campanha.getEmpresa().getId());

        if (campanha != null) {

            if (campanha.getDescricao() != null && !campanha.getDescricao().isEmpty()) {
                query.append("and upper(c.descricao) like :descricao ");
                parametros.put("descricao", "%" + campanha.getDescricao().toUpperCase() + "%");
            }

            if (campanha.getTipoCampanha() != null) {
                query.append("\tand c.tipo_campanha = :tipoCampanha ");
                parametros.put("tipoCampanha", campanha.getTipoCampanha().name());
            }

            if (campanha.getFila() != null && campanha.getFila().getId() != null) {

                query.append("\tand c.fila = :fila ");
                parametros.put("fila", campanha.getFila().getId());
            }

            if (campanha.getStatus() != null && campanha.getStatus().getId() != null) {
                query.append("\tand s.id = :statusCampanha ");
                parametros.put("statusCampanha", campanha.getStatus().getId());
            }

            if (dataInicio != null && dataFim != null) {

                query.append("\tand c.data_alteracao >= :dataInicio and c.data_alteracao < :dataFim ");
                parametros.put("dataInicio", dataInicio);
                parametros.put("dataFim", dataFim);

            } else if (dataInicio != null && dataFim == null) {

                query.append("\tand c.data_alteracao >= :dataInicio ");
                parametros.put("dataInicio", dataInicio);

            } else if (dataInicio == null && dataFim != null) {

                query.append("\tand c.data_alteracao < :dataFim ");
                parametros.put("dataFim", dataFim);

            }
        }

        query.append("order by c.data_alteracao desc, e.nome, c.descricao ");

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }

    public Campanha pesquisarCampanhaAtivaPorUsuario(Long idUsuario) {

        StringBuilder query = new StringBuilder();

        query.append("select c.id, c.descricao, c.consulta_saque, c.valor_saque, c.valor_saque_maximo ");
        query.append("from ( ");
        query.append("  select distinct c.id, c.descricao, c.consulta_saque, c.valor_saque, c.valor_saque_maximo ");
        query.append("  from campanha c ");
        query.append("\t  \tjoin campanha_equipe ce on (c.id = ce.campanha) ");
        query.append("\t  \tjoin usuario u on (ce.equipe = u.equipe) ");
        query.append("\t  \tjoin status_campanha sc on (sc.id = c.status_campanha) ");
        query.append("\t  \tjoin atendimento a on (a.campanha = c.id) ");
        query.append("\t  \tleft join status_atendimento sa on (sa.id = a.status) ");
        query.append("\t  \tleft join importacao i on (i.campanha = c.id) ");
        query.append("  where c.tipo_campanha not in ('RECEPTIVA','PREDITIVA') ");
        query.append("\t\tand u.id = :usuario  ");
        query.append("\t\tand sc.acao = 'LIBERAR' ");
        query.append("\t\tand a.usuario_em_atendimento is null ");

        query.append("\t\tand (c.data_inicio_campanha is null or now() >= c.data_inicio_campanha) ");
        query.append("\t\tand (c.data_fim_campanha is null or now() <= c.data_fim_campanha) ");
        query.append("\t\tand (i.id is null or i.status_importacao = 'IMPORTADA') ");
        query.append("\t\tand (a.atender is null or a.atender = 'SIM') ");
        query.append("\t\tand (a.status is null or sa.acao in ('NONE', 'FIM_FILA')) ");
        query.append("\t\tand (c.ativacao_inicio is null or c.ativacao_inicio <= localtime) ");
        query.append("\t\tand (c.ativacao_fim is null or c.ativacao_fim >= localtime)) c ");
        query.append("order by random() ");
        query.append("limit 1 ");

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("usuario", idUsuario);

        Object[] resultado = (Object[]) searchEntidade(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

        if (resultado == null)
            return null;

        Long codCamp = resultado[0] == null ? null : Long.valueOf((Integer) resultado[0]);

        return (resultado == null) ? null
                : new Campanha(codCamp, (resultado[1] == null) ? null : resultado[1].toString(),
                (resultado[2] == null) ? null : (resultado[2].equals("true")),
                (resultado[3] == null) ? null : (BigDecimal) resultado[3],
                (resultado[4] == null) ? null : (BigDecimal) resultado[4]);
    }

    public List<Campanha> pesquisarCampanhasAtivas() {
        // TODO Auto-generated method stub
        StringBuilder query = new StringBuilder();
        query.append("select c.* ");
        query.append("from campanha c ");
        query.append("\tjoin status_campanha s on c.status_campanha = s.id ");
        query.append("where s.acao = :acao ");
        query.append("\tand c.tipo_campanha <> :tipo ");

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("acao", AcaoCampanhaEnum.LIBERAR.name());
        parametros.put("tipo", TipoCampanhaEnum.RECEPTIVA.name());

        return searchEntidades(DaoEnum.NATIVE_CLASSE, query.toString(), parametros);
    }

    public List<SegmentoEnum> pesquisarSegmentosUtilizadosPorEmpresa(Empresa empresa, TipoCampanhaEnum tipo) {

        StringBuilder sql = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        sql.append("select distinct c.segmento ");
        sql.append("from Campanha c ");
        sql.append("where c.empresa = :empresa   ");
        sql.append("  and c.tipoCampanha = :tipoCampanha ");
        sql.append("  and c.status.acao = :acao ");

        parametros.put("empresa", empresa);
        parametros.put("tipoCampanha", tipo);
        parametros.put("acao", AcaoCampanhaEnum.LIBERAR);

        return searchEntidades(DaoEnum.HQL_QUERRY, sql.toString(), parametros);

    }

    public List<?> pesquisarCampanhaOperacaoPorUsuario(Long idUsuario) {

        StringBuilder query = new StringBuilder();

        query.append("select ");
        query.append("\tc.id, ");
        query.append("\tc.descricao  ");
        query.append("from campanha c ");
        query.append("\tjoin status_campanha sc on sc.id = c.status_campanha ");
        query.append("\tjoin campanha_equipe ce on ce.campanha = c.id ");
        query.append("\tjoin usuario u on u.equipe = ce.equipe ");
        query.append("where u.id = :usuario ");
        query.append("\tand sc.acao = 'LIBERAR' ");
        query.append("\tand c.tipo_campanha not in ('RECEPTIVA','PREDITIVA') ");
        query.append("order by c.descricao ");
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("usuario", idUsuario);

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);
    }

    public List<Campanha> pesquisarCampanhasPorEmpresa(Long idEmpresa, Long statusCampanhaId, TipoCampanhaEnum tipoCampanha) {

        HashMap<String, Object> parametros = new HashMap<>();

        StringBuilder query = new StringBuilder();

        query.append("select c.id, c.descricao ");
        query.append("from campanha c ");
        query.append("\tjoin empresa e on c.empresa = e.id ");
        query.append("where (e.id = :empresa or e.matriz = :empresa) ");
        parametros.put("empresa", idEmpresa);

        if (statusCampanhaId != null) {

            query.append("\tand c.status_campanha = :statusCampanha ");
            parametros.put("statusCampanha", statusCampanhaId);
        }

        if (tipoCampanha != null) {

            query.append("\tand c.tipo_campanha = :tipoCampanha ");
            parametros.put("tipoCampanha", tipoCampanha.name());

        }

        return ConverterUtilList.converterCampanha(searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros));
    }

    public List<Campanha> pesquisarCampanhaPorSupervidor(Long idUsuario, Long idEmpresa) {


        StringBuilder query = new StringBuilder();

        query.append("select distinct c.id, c.descricao ");
        query.append("from campanha c ");
        query.append("\tjoin campanha_equipe ce on ce.campanha = c.id ");
        query.append("\tjoin equipe_supervisor es on es.equipe = ce.equipe ");
        query.append("where es.supervisor = :supervisor ");
        query.append("\tand c.empresa = :empresa ");
        query.append("order by c.descricao");

        HashMap<String, Object> parametros = new HashMap<>();

        parametros.put("supervisor", idUsuario);
        parametros.put("empresa", idEmpresa);

        return ConverterUtilList.converterCampanha(searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros));

    }

    public Campanha pesquisarCampanhaComFila(Long campanha) {
        // TODO Auto-generated method stub

        StringBuilder query = new StringBuilder();
        query.append("select c ");
        query.append("from Campanha c ");
        query.append(" left join fetch c.fila ");
        query.append("where c.id = :campanha ");
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("campanha", campanha);
        return (Campanha) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);


    }

    public List<Object[]> pesquisarCampanhasStatusTipoEquipe(Long idEmpresa, Long idCampanha,
                                                             TipoCampanhaEnum tipoCampanha, Long statusCampanha, Long equipeCampanha, Long idUsuario) {

        HashMap<String, Object> parametros = new HashMap<>();
        StringBuilder query = new StringBuilder();
        query.append(" SELECT c.descricao as campanha, ");
        query.append(" \t\t  c.tipo_campanha, ");
        query.append(" \t\t  s.descricao as status, ");
        query.append(" \t\t  e.nome as equipe  ");
        query.append(" FROM campanha c ");
        query.append(" \t\tJOIN status_campanha s on c.status_campanha = s.id ");
        query.append(" \t\tJOIN campanha_equipe ce on ce.campanha = c.id ");
        query.append(" \t\tJOIN equipe e on ce.equipe = e.id ");

        if (idUsuario != null)
            query.append(" \tJOIN equipe_supervisor es on es.equipe = e.id ");

        query.append(" WHERE c.empresa = :empresa ");
        parametros.put("empresa", idEmpresa);

        if (idCampanha != null) {

            query.append(" AND c.id = :campanha ");
            parametros.put("campanha", idCampanha);
        }

        if (tipoCampanha != null) {
            query.append(" AND c.tipo_campanha = :tipoCampanha ");
            parametros.put("tipoCampanha", tipoCampanha.name());
        }

        if (statusCampanha != null) {
            query.append(" AND s.id = :statusCampanha ");
            parametros.put("statusCampanha", statusCampanha);
        }

        if (equipeCampanha != null) {
            query.append(" AND e.id = :equipe ");
            parametros.put("equipe", equipeCampanha);
        }

        if (idUsuario != null) {
            query.append(" AND es.supervisor = :supervisor ");
            parametros.put("supervisor", idUsuario);
        }

        query.append(" ORDER BY c.descricao, s.descricao, e.nome ");
        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }

    public void limparEnviadosCampanhas() {


        StringBuilder query = new StringBuilder();

        query.append("UPDATE atendimento 	set enviado = null   ");
        query.append("\t\t  FROM (  ");
        query.append("\t\t  SELECT atendimento.ID ,status_campanha.acao,atendimento.enviado FROM atendimento ");
        query.append("\t\t LEFT JOIN status_atendimento ON atendimento.status = status_atendimento.id ");
        query.append("\t\t LEFT JOIN campanha ON atendimento.campanha = campanha.ID ");
        query.append("\t\t LEFT JOIN status_campanha ON campanha.status_campanha = status_campanha.ID  ");
        query.append("\t\t WHERE  ");
        query.append("\t\t atendimento.usuario_em_atendimento IS NULL  ");
        query.append("\t\t AND ( atendimento.status IS NULL OR status_atendimento.acao IN ( 'NONE', 'FIM_DE_FILA' ) ) ");
        query.append("\t\t AND ( atendimento.atender IS NULL OR atendimento.atender = 'SIM' )");
        query.append("\t\t AND status_campanha.acao = 'LIBERAR'");
        query.append("\t\t   )");
        query.append("\t\t as sb WHERE atendimento.id = sb.id  ");

        executarSql(DaoEnum.NATIVE_OBJECT, query.toString(), null);


    }

    public List<Object[]> pesquisarCampanhasPorCpf(String cpf, Long idUsuario, Long idEmpresa) {

        StringBuilder query = new StringBuilder();

        query.append("select distinct c.id, ");
        query.append("\tc.descricao,c.segmento ");
        query.append("from campanha c ");
        query.append("\tjoin atendimento a on c.id = a.campanha  ");
        query.append("\tjoin status_campanha sc on sc.id = c.status_campanha ");
        query.append("\tjoin campanha_equipe ce on c.id = ce.campanha ");
        query.append("\tjoin usuario u on u.equipe = ce.equipe ");
        query.append("where c.empresa = :empresa ");
        query.append("\tand u.id = :usuario ");
        query.append("  and a.cpf = :cpf  ");
        query.append("  and sc.acao = 'LIBERAR' and c.tipo_campanha <> 'RECEPTIVA' ");
        query.append("order by c.descricao ");

        HashMap<String, Object> parametros = new HashMap<>();

        parametros.put("empresa", idEmpresa);
        parametros.put("usuario", idUsuario);
        parametros.put("cpf", cpf);

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }

    public List<Object[]> pesquisarCampanhasPorTelefone(String telefone, Long idUsuario, Long idEmpresa) {

        StringBuilder query = new StringBuilder();

        query.append("select distinct c.id, ");
        query.append("\tc.descricao,c.segmento ");
        query.append("from campanha c ");

        query.append("\tjoin atendimento a on c.id = a.campanha  ");

        query.append("\tjoin status_campanha sc on sc.id = c.status_campanha ");

        query.append("\tjoin campanha_equipe ce on c.id = ce.campanha ");

        query.append("\tjoin telefone t on a.id = t.atendimento ");

        query.append("\tjoin usuario u on u.equipe = ce.equipe ");

        query.append("where c.empresa = :empresa ");

        query.append("\tand u.id = :usuario ");

        query.append("\tand cast(t.ddd as varchar(2))||t.numero = :telefone ");


        query.append("  and sc.acao = 'LIBERAR' and c.tipo_campanha <> 'RECEPTIVA' ");

        query.append("order by c.descricao ");

        HashMap<String, Object> parametros = new HashMap<>();

        parametros.put("empresa", idEmpresa);
        parametros.put("usuario", idUsuario);
        parametros.put("telefone", telefone.replaceAll("\\D+", "").replaceAll(" ", ""));


        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }

    public List<Object[]> pesquisarCampanhasProspect(Long idUsuario, Long idEmpresa) {

        StringBuilder query = new StringBuilder();

        query.append("select distinct c.id, ");
        query.append("\tc.descricao,c.segmento ");
        query.append("from campanha c ");
        query.append("\tjoin status_campanha sc on sc.id = c.status_campanha ");
        query.append("\tjoin campanha_equipe ce on c.id = ce.campanha ");
        query.append("\tjoin usuario u on u.equipe = ce.equipe ");
        query.append("where c.empresa = :empresa ");
        query.append("\tand u.id = :usuario ");
        query.append("  and c.tipo_campanha = 'RECEPTIVA' ");
        query.append("  and sc.acao = 'LIBERAR' ");
        query.append("order by c.descricao ");

        HashMap<String, Object> parametros = new HashMap<>();

        parametros.put("empresa", idEmpresa);
        parametros.put("usuario", idUsuario);

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }

    public List<?> pesquisarCampanhaPorUsuario(Long idUsuario) {

        StringBuilder query = new StringBuilder();

        query.append("select ");
        query.append("\tc.descricao as nome_campanha, ");
        query.append("\tsc.descricao as status_campanha ");
        query.append("from campanha c ");
        query.append("\tjoin status_campanha sc on sc.id = c.status_campanha ");
        query.append("\tjoin campanha_equipe ce on ce.campanha = c.id ");
        query.append("\tjoin usuario u on u.equipe = ce.equipe ");
        query.append("where u.id = :usuario ");
        query.append("order by sc.descricao, c.descricao ");

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("usuario", idUsuario);

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }

    public void atualizarCampanhaImportacaoPowerDialer(Campanha campanha) {


        String query = "update campanha set cod_importacao_pwd = :codigoPowerDialer where id = :id";
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("id", campanha.getId());
        parametros.put("codigoPowerDialer", campanha.getCodImportacaoPwd());
        executarSql(DaoEnum.NATIVE_OBJECT, query, parametros);


    }

    public List<Campanha> pesquisarCampanhasConsulta() {
        // TODO Auto-generated method stub

        StringBuilder query = new StringBuilder();
        query.append("select c ");
        query.append("from Campanha c ");
        query.append("\tjoin fetch c.listImportacao im ");
        query.append("\tjoin fetch c.status s ");

        query.append(" where s.acao = 'LIBERAR' ");
        query.append(" and c.agendarConsulta = :consulta and c.consultaRealizada = :realizada ");

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("consulta", Boolean.TRUE);
        parametros.put("realizada", Boolean.FALSE);


        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

    public List<Long> pesquisarIdsCampanhasConsulta() {
        // TODO Auto-generated method stub

        StringBuilder query = new StringBuilder();
        query.append("SELECT DISTINCT c.id from campanha c ");
        query.append("  JOIN  importacao im on im.campanha = c.id ");
        query.append("\t JOIN status_campanha sc on  sc.id = c.status_campanha ");

        query.append(" where sc.acao = 'LIBERAR' ");
        query.append(" and c.agendar_consulta = :consulta and c.consulta_realizada = :realizada ");

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("consulta", Boolean.TRUE);
        parametros.put("realizada", Boolean.FALSE);


        List<Long> listIds = Utils.converterLong(searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros));

        return listIds;
    }

    public void atualizarCampanhaAgendamento(Campanha campanha) {

        String query = "update campanha set consulta_realizada = true, agendar_consulta = false  where id = :id";

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("id", campanha.getId());

        executarSql(DaoEnum.NATIVE_OBJECT, query, parametros);

    }

}
