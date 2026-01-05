package com.proativaservicos.dao.implemets;

import com.proativaservicos.model.*;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.Util;
import com.proativaservicos.util.Utils;
import com.proativaservicos.util.constantes.*;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

@SuppressWarnings("unchecked")
@Dependent
public class DaoAtendimentoImp extends GenericDao<Atendimento> implements Serializable {

    @PersistenceContext
    private EntityManager entityManager;

    private static final long serialVersionUID = 1L;

    public GenericAtendimento pesquisarAtendimentoPorCpf(String cpf, Long id) {

        StringBuilder query = new StringBuilder();

        query.append("select a ");
        query.append("from Atendimento a ");
        query.append("\tjoin fetch a.campanha c ");
        query.append("\tleft join fetch c.fila ");
        query.append("\tleft join fetch a.status sa ");
        query.append("\tleft join fetch a.usuarioOcupado o ");
        query.append("\tleft join fetch a.formaPagamento tp ");
        query.append("\tleft join fetch a.produto pe ");
        query.append("\tleft join fetch a.contrato ");
        query.append("\tleft join fetch a.loja ");
        query.append("\tleft join fetch a.equipe ");
        query.append("where a.empresa.id = :empresa ");
        query.append(" and a.cpf = :cpf ");

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("cpf", StringUtils.leftPad(cpf.replaceAll("\\D+", "").replaceAll(" ", ""), 11, "0"));
        parametros.put("empresa", id);

        return (GenericAtendimento) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros, 0, 1);

    }

    public Object[] pesquisarEsquemaPorAtendimentoPorCpf(String cpf, Long idEmpresa) {

        StringBuilder query = new StringBuilder();

        query.append("select * from ( ");
        query.append("  select a1.id, 'public' as esquema, a1.data_alteracao ");
        query.append("  from public.atendimento a1 ");
        query.append("\t\tjoin empresa e1 on a1.empresa = e1.id ");
        query.append("  where a1.cpf = :cpf ");
        query.append("\t\tand (e1.id = :empresa or e1.matriz = :empresa) ) a  ");

        query.append("   order by a.data_alteracao desc ");

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("cpf", cpf.replaceAll("\\D+", "").replaceAll(" ", ""));
        parametros.put("empresa", idEmpresa);

        return (Object[]) searchEntidade(DaoEnum.NATIVE_OBJECT, query.toString(), parametros, 0, 1);

    }

    public void deletarAtendimentosPorImportacao(Long id) {
        // TODO Auto-generated method stub

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("importacao", id);

        String query = "delete from endereco where atendimento in (select id from atendimento where importacao = :importacao); ";
        executarSql(DaoEnum.NATIVE_OBJECT, query, parametros);

        query = "delete from dados_bancarios where atendimento in (select id from atendimento where importacao = :importacao); ";
        executarSql(DaoEnum.NATIVE_OBJECT, query, parametros);

        query = "delete from telefone where atendimento in (select id from atendimento where importacao = :importacao); ";
        executarSql(DaoEnum.NATIVE_OBJECT, query, parametros);

        query = "delete from cartao_credito where atendimento in (select id from atendimento where importacao = :importacao); ";
        executarSql(DaoEnum.NATIVE_OBJECT, query, parametros);

        query = "delete from email where atendimento in (select id from atendimento where importacao = :importacao); ";
        executarSql(DaoEnum.NATIVE_OBJECT, query, parametros);

        query = "delete from contrato_efetivado where atendimento in (select id from atendimento where importacao = :importacao); ";
        executarSql(DaoEnum.NATIVE_OBJECT, query, parametros);

        query = "delete from logatendimento where atendimento in (select id from atendimento where importacao = :importacao); ";
        executarSql(DaoEnum.NATIVE_OBJECT, query, parametros);

        query = "delete from atendimento where importacao = :importacao ; ";
        executarSql(DaoEnum.NATIVE_OBJECT, query, parametros);

    }

    public List<Atendimento> pesquisarAtendimentosPorCampanha(Long idCampanha, Long idImportacao, boolean possuiErro) {

        StringBuilder query = new StringBuilder();

        query.append("select a ");
        query.append("from Atendimento a ");
        query.append("\tjoin fetch a.empresa ");
        query.append("\tjoin fetch a.campanha c ");
        query.append("\tjoin a.importacao i ");
        query.append("\tleft join fetch c.fila ");
        query.append("where a.campanha.id = :campanha ");

        if (possuiErro) {

            query.append("\tand (a.observacao is null or  ");
            query.append("\t upper(a.observacao) like 'SERVIDOR ESTÁ FORA DO AR%' or ");
            query.append("\t upper(a.observacao) like 'SERVIDOR ESTÁ INOPERANTE%' or ");
            query.append("\t upper(a.observacao) like 'WEBSERVICE INDISPONIVEL%' or ");
            query.append("\t upper(a.observacao) like '%OCORREU ERRO%' or ");
            query.append("\t upper(a.observacao) like '%OCORREU ERRO%' or ");
            query.append("\t upper(a.observacao) like 'ERRO WEBSERVICES%' or ");
            query.append("\t upper(a.observacao) like 'LINK INOPERANTE%' or ");
            query.append("\t upper(a.observacao) like 'NENHUM RETORNO%' or ");
            query.append("\t upper(a.observacao) like 'CONSULTA NÃO FOI%' or ");
            query.append("\t  upper(a.observacao) like '%<!DOCTYPE HTML PUBLIC%' ) ");

        } else {

            query.append("\tand a.observacao is null ");
        }

        query.append("\tand i.id = :importacao ");
        query.append("\tand i.statusImportacao not in (:statusImportacao) ");
        query.append("order by a.id ");

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("campanha", idCampanha);
        parametros.put("importacao", idImportacao);
        parametros.put("statusImportacao", Arrays.asList(new StatusImportacaoEnum[]{StatusImportacaoEnum.IMPORTADA, StatusImportacaoEnum.NAO_IMPORTADA}));

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros, 0, 10000);
    }

    public List<Long> pesquisarAtendimentosPorCampanha(Campanha campanha, Object ordenacao, boolean limiteConsulta) {

        Map<String, Object> parametros = new HashMap<>();
        StringBuilder query = new StringBuilder();

        query.append("select atendimento.id ");
        query.append("from atendimento ");
        query.append("\tleft join status_atendimento on atendimento.status = status_atendimento.id ");
        query.append("where atendimento.campanha = :campanha ");
        query.append("\tand atendimento.usuario_em_atendimento is null ");
        query.append("\tand (atendimento.enviado is null or atendimento.enviado = false) ");
        query.append("  and (atendimento.status is null or status_atendimento.acao in ('NONE', 'FIM_FILA')) ");
        query.append("  and (atendimento.atender is null or atendimento.atender = 'SIM') ");

        if (Boolean.TRUE.equals(campanha.getConsultaSaque())) {

            if (campanha.getValorSaque() != null && campanha.getValorSaqueMaximo() != null) {

                query.append("\tand atendimento.valor_max_operacao >= :valorSaqueMin and atendimento.valor_max_operacao <= :valorSaqueMax ");
                parametros.put("valorSaqueMin", campanha.getValorSaque());
                parametros.put("valorSaqueMax", campanha.getValorSaqueMaximo());

            } else if (campanha.getValorSaque() != null) {

                query.append("\tand atendimento.valor_max_operacao >= :valorSaque ");
                parametros.put("valorSaque", campanha.getValorSaque());

            } else if (campanha.getValorSaqueMaximo() != null) {

                query.append("\tand atendimento.valor_max_operacao <= :valorSaque ");
                parametros.put("valorSaque", campanha.getValorSaqueMaximo());

            }

        } else if (Boolean.TRUE.equals(campanha.getConsultaSeguro()) && campanha.getIntegrarWs().getTipoIntegracao().equals(TipoIntegracaoEnum.BMG_SEGURO)) {

            if (campanha.getValorSaque() != null && campanha.getValorSaqueMaximo() != null) {

                query.append("\tand atendimento.seguro >= :valorSaqueMin and atendimento.seguro <= :valorSaqueMax ");
                parametros.put("valorSaqueMin", campanha.getValorSaque());
                parametros.put("valorSaqueMax", campanha.getValorSaqueMaximo());

            } else if (campanha.getValorSaque() != null) {

                query.append("\tand atendimento.seguro >= :valorSaque ");
                parametros.put("valorSaque", campanha.getValorSaque());

            } else if (campanha.getValorSaqueMaximo() != null) {

                query.append("\tand atendimento.seguro <= :valorSaque ");
                parametros.put("valorSaque", campanha.getValorSaqueMaximo());

            }

        }
        // ORDENACAO...
        query.append("order by status_atendimento.acao desc, coalesce(atendimento.peso_carteira, 0), atendimento.id ");

        if (limiteConsulta) {
            query.append("limit 50 ");
        }

        parametros.put("campanha", campanha.getId());

        return (List<Long>) Utils.converterLong(searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros));

    }

    public List<Atendimento> pesquisarAtendimentosPorId(List<Long> listIdAtendimentos) {
        // TODO Auto-generated method stub
        StringBuilder query = new StringBuilder();

        query.append("select a ");
        query.append("from Atendimento a ");
        query.append("\tjoin fetch a.listTelefones ");
        query.append("where a.id in :ids ");
        query.append("order by a.id ");

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("ids", listIdAtendimentos);

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

    public Integer pesquisarQuantidadeImportadosSaque(Long idImportacao) {
        // TODO Auto-generated method stub
        String query = "select count(id) from atendimento where importacao = :importacao and observacao is not null";

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("importacao", idImportacao);

        BigInteger resultado = (BigInteger) searchEntidade(DaoEnum.NATIVE_OBJECT, query, parametros);

        return (resultado == null) ? 0 : resultado.intValue();

    }

    public void atualizarAtendimentoSaqueComplementar(Long idAtendimento, BigDecimal valorMaxOperacao,
                                                      BigDecimal margem, BigDecimal limite, BigDecimal seguro, String beneficioPrincipal, String observacao) {

        String query = "update atendimento set valor_max_operacao = " + valorMaxOperacao + ", margem = " + margem
                + ", limite = " + limite + ", seguro = " + seguro
                + ", observacao = :observacao, beneficio_principal = :beneficioPrincipal where id = :id";

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("id", idAtendimento);
        parametros.put("observacao", observacao);
        parametros.put("beneficioPrincipal", beneficioPrincipal);
        executarSql(DaoEnum.NATIVE_OBJECT, query, parametros);

    }

    public void atualizarAtendimentoSeguroPapCard(Long idAtendimento, BigDecimal valorMaxOperacao, BigDecimal margem, BigDecimal limite, BigDecimal seguro, String beneficioPrincipal, Date dataNascimento, Integer rating, String observacao) {

        String query = "update atendimento set valor_max_operacao = " + valorMaxOperacao + ", margem = " + margem + ", limite = " + limite + ", seguro = " + seguro + " , data_nascimento = '"
                + DateUtil.builder(dataNascimento).formatarDataParaString("yyyy-MM-dd HH:mm:ss").getDataTexto() + "' "
                + ", observacao = :observacao, beneficio_principal = :beneficioPrincipal, rating = " + rating + " where id = :id";

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("id", idAtendimento);
        parametros.put("observacao", observacao);

        parametros.put("beneficioPrincipal", beneficioPrincipal);

        executarSql(DaoEnum.NATIVE_OBJECT, query, parametros);

    }

    public void atualizarAtendimentoResultsVonix(Long idAtendimento, Long status, Date dataAlteracao) {

        String query = "update atendimento set status = :status , data_alteracao = '" + DateUtil.builder(dataAlteracao).formatarDataParaString("yyyy-MM-dd HH:mm:ss").getDataTexto() + "' where id = :id";

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("id", idAtendimento);
        parametros.put("status", status);

        executarSql(DaoEnum.NATIVE_OBJECT, query, parametros);

    }

    public void atualizarAtendimentoResultsVonix(Long idAtendimento, Long status, Date dataAlteracao, Integer quantidadeAtendimento) {

        String query = "update atendimento set status = :status, quantidade_discagem = :qtdadeAtn ,discou = :discou ,data_alteracao = '" + DateUtil.builder(dataAlteracao).formatarDataParaString("yyyy-MM-dd HH:mm:ss").getDataTexto() + "' where id = :id";

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("id", idAtendimento);
        parametros.put("status", status);
        parametros.put("qtdadeAtn", quantidadeAtendimento.intValue());
        parametros.put("discou", true);

        executarSql(DaoEnum.NATIVE_OBJECT, query, parametros);

    }

    public void atualizarAtendimentoRefin(Long idAtendimento, BigDecimal valorMaxOperacao, BigDecimal margem, BigDecimal limite, BigDecimal taxa, BigDecimal valorLiberado, Integer quantidadeParcelas,
                                          String informacoesComplementares, String observacao) {

        String query = "";

        if (quantidadeParcelas != null) {

            query = "update atendimento set valor_max_operacao = " + valorMaxOperacao + ", margem = " + margem
                    + " , limite = " + limite + " , taxa = " + taxa
                    + " , informacoes_complementares = :informacoes_complementares, observacao = :observacao, quantidade_parcelas = '"
                    + quantidadeParcelas + "' where id = :id";

        } else {

            query = "update atendimento set valor_max_operacao = " + valorMaxOperacao + ", margem = " + margem
                    + " , limite = " + limite + " , taxa = " + taxa + ", valor_liberado = " + valorLiberado
                    + " ,valor_liberado_emp = " + valorLiberado
                    + " ,informacoes_complementares = :informacoes_complementares, observacao = :observacao where id = :id";

        }

        HashMap<String, Object> parametros = new HashMap<>();

        parametros.put("id", idAtendimento);

        parametros.put("informacoes_complementares", informacoesComplementares);

        parametros.put("observacao", observacao);

        executarSql(DaoEnum.NATIVE_OBJECT, query, parametros);

    }

    @Transactional
    public void atualizarAtendimentoMaster(Long idAtendimento, BigDecimal valorMaxOperacao, BigDecimal margem, BigDecimal limite, String matricula,
                                           String informacoesComplementares, String observacao) {

        String query = "";


        query = "update atendimento set valor_max_operacao = " + valorMaxOperacao + ", margem = " + margem
                + " , limite = " + limite + " , beneficio_principal =  :matricula"
                + " , informacoes_complementares = :informacoes_complementares, observacao = :observacao where id = :id";


        HashMap<String, Object> parametros = new HashMap<>();

        parametros.put("id", idAtendimento);
        parametros.put("matricula", matricula);

        parametros.put("informacoes_complementares", informacoesComplementares);

        parametros.put("observacao", observacao);

        executarSql(DaoEnum.NATIVE_OBJECT, query, parametros);

    }

    public void atualizarAtendimentoSaquePan(Long idAtendimento, BigDecimal valorMaxOperacao, BigDecimal margem, BigDecimal limite, BigDecimal taxa, BigDecimal valorLiberado, Integer quantidadeParcelas, BigDecimal valorParcela,
                                             String informacoesComplementares, String observacao) {

        String query = "";

        if (quantidadeParcelas != null) {

            query = "update atendimento set valor_max_operacao = " + valorMaxOperacao + ", margem = " + margem
                    + " , limite = " + limite + " , taxa = " + taxa
                    + " , informacoes_complementares = :informacoes_complementares, observacao = :observacao, quantidade_parcelas = '"
                    + quantidadeParcelas + "' ,valor_parcela = :valorparcela   where id = :id";

        } else {

            query = "update atendimento set valor_max_operacao = " + valorMaxOperacao + ", margem = " + margem
                    + " , limite = " + limite + " , taxa = " + taxa + ", valor_liberado = " + valorLiberado
                    + " ,valor_liberado_emp = " + valorLiberado
                    + " ,informacoes_complementares = :informacoes_complementares, observacao = :observacao, valor_parcela = :valorparcela where id = :id";

        }

        HashMap<String, Object> parametros = new HashMap<>();

        parametros.put("id", idAtendimento);

        parametros.put("informacoes_complementares", informacoesComplementares);

        parametros.put("observacao", observacao);
        parametros.put("valorparcela", valorParcela == null ? Double.valueOf(0).doubleValue() : valorParcela.doubleValue());

        executarSql(DaoEnum.NATIVE_OBJECT, query, parametros);

    }


    public void atualizarValidaCartaoBeneficio(Long idAtendimento, String observacao) {

        String query = "";

        query = "update atendimento set  observacao = :observacao  where id = :id";

        HashMap<String, Object> parametros = new HashMap<>();

        parametros.put("id", idAtendimento);

        parametros.put("observacao", observacao.trim());

        executarSql(DaoEnum.NATIVE_OBJECT, query, parametros);

    }

    public GenericAtendimento pesquisarAtendimentoPorId(Long idAtendimento) {
        StringBuilder query = new StringBuilder();

        query.append("select a ");
        query.append("from Atendimento a ");
        query.append("\tjoin fetch a.campanha c ");
        query.append("\tleft join fetch c.fila ");
        query.append("\tleft join fetch a.status sa ");
        query.append("\tleft join fetch a.usuarioOcupado o ");
        query.append("\tleft join fetch a.formaPagamento tp ");
        query.append("\tleft join fetch a.produto pe ");
        query.append("\tleft join fetch a.contrato ");
        query.append("\tleft join fetch a.loja ");
        query.append("\tleft join fetch a.equipe ");
        query.append("\tleft join fetch a.cliente c ");
        query.append("where a.id = :id ");

        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("id", idAtendimento);

        return (Atendimento) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);

    }

    public GenericAtendimento pesquisarAtendimentoPorBkoId(Long idAtendimento) {
        StringBuilder query = new StringBuilder();

        query.append("select a ");
        query.append("from Atendimento a ");
        query.append("\t left join fetch a.campanha c ");

        query.append("\tleft join fetch a.status sa ");
        query.append("\tleft join fetch a.usuarioOcupado o ");
        query.append("\tleft join fetch a.formaPagamento tp ");
        query.append("\tleft join fetch a.produto pe ");
        query.append("\tleft join fetch a.contrato ");
        query.append("\tleft join fetch a.loja ");
        query.append("\tleft join fetch a.equipe ");
        query.append("where a.id = :id ");

        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("id", idAtendimento);

        return (Atendimento) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);

    }

    public GenericAtendimento pesquisarAtendimentoPorIdSemDetalhar(Long idAtendimento) {

        StringBuilder query = new StringBuilder();

        query.append("select a ");
        query.append("from Atendimento a ");
        query.append("\tjoin fetch a.campanha c ");
        query.append("\tleft join fetch c.fila ");
        query.append("\tleft join fetch a.status sa ");

        query.append("where a.id = :id ");

        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("id", idAtendimento);

        return (Atendimento) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);

    }

    public boolean verificarExistenciaAtendimento(Long idAtendimento) {
        // TODO Auto-generated method stub
        String query = "select id from atendimento where id = :atendimento";

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("atendimento", idAtendimento);

        BigInteger resultado = (BigInteger) searchEntidade(DaoEnum.NATIVE_OBJECT, query, parametros);

        return (resultado != null);
    }

    public boolean verificarTicketAtendimento(String tiket, Long idAtendimento) {

        StringBuilder query = new StringBuilder("select id from atendimento where tiket = :tiket  ");
        Map<String, Object> parametros = new HashMap<>();

        if (idAtendimento != null) {
            query.append("  and id <> :idAtendimento");
            parametros.put("idAtendimento", idAtendimento);
        }

        parametros.put("tiket", tiket);

        BigInteger resultado = (BigInteger) searchEntidade(DaoEnum.NATIVE_OBJECT, query.toString(), parametros, Integer.valueOf(0), Integer.valueOf(1));

        return (resultado != null);
    }

    public void atualizarAtendimentoOcupado(Long idAtendimento, Long idUsr) {

        String query = "update atendimento set usuario_em_atendimento = :usuario, usuario_alteracao = :usuarioAlteracao, data_alteracao = :data  where id = :id";

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("id", idAtendimento);
        parametros.put("usuario", idUsr);
        parametros.put("usuarioAlteracao", idUsr);
        parametros.put("data", new Date(System.currentTimeMillis()));

        executarSql(DaoEnum.NATIVE_OBJECT, query, parametros);

    }

    public Long pesquisarProximoAtendimentoAgendamento(Long idUsuario) {

        StringBuilder query = new StringBuilder();

        query.append(" select a.id ");
        query.append(" from atendimento a ");
        query.append("  join ( ");
        query.append("      select a.id, h.agendamento, a.campanha ");
        query.append("      from historico_atendimento h ");
        query.append("         join status_atendimento sa on (h.status_atendimento = sa.id) ");
        query.append("         join atendimento a on a.id = h.atendimento ");
        query.append("      where (h.usuario = :usuario or sa.acao = 'AGENDAR_GLOBAL') ");
        query.append("         and sa.acao like 'AGENDAR%' ");
        query.append("         and h.data_visualizado is null ");
        query.append("         and a.campanha in ( ");
        query.append("              select distinct ce.campanha ");
        query.append("              from campanha_equipe ce ");
        query.append("                join campanha c on (ce.campanha = c.id) ");
        query.append("                join status_campanha sc on (c.status_campanha = sc.id) ");
        query.append("                join usuario u on (ce.equipe = u.equipe) ");
        query.append("              where u.id = :usuario ");
        query.append("                and sc.acao = 'LIBERAR' ");
        query.append("              union all ");
        query.append("              select max(c.id) ");
        query.append("              from campanha c ");
        query.append("                join campanha_equipe ce on (c.id = ce.campanha) ");
        query.append("                join status_campanha sc on (c.status_campanha = sc.id) ");
        query.append("                join usuario u on (ce.equipe = u.equipe) ");
        query.append("              where u.id = :usuario ");
        query.append("                and c.tipo_campanha not in ('RECEPTIVA','PREDITIVA') ");
        query.append("                and sc.acao = 'FINALIZAR' ");
        query.append("                and now() BETWEEN c.data_inicio_agendamento and c.data_fim_agendamento) ");
        query.append("   \t  order by h.agendamento) r on (a.campanha = r.campanha and a.id = r.id) ");
        query.append("   join campanha c on c.id = a.campanha ");
        query.append("   join status_campanha sc on sc.id = c.status_campanha ");
        query.append("where r.agendamento <= now() ");
        query.append(
                "   and (sc.acao = 'LIBERAR' or (sc.acao = 'FINALIZAR' and r.agendamento between c.data_inicio_agendamento and c.data_fim_agendamento)) ");
        query.append("limit 1 ");

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("usuario", idUsuario);

        BigInteger resultado = (BigInteger) searchEntidade(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

        return (resultado == null) ? null : Long.valueOf(resultado.longValue());
    }

    public Long pesquisarProximoAtendimentoOcupado(Long idUsuario) {
        StringBuilder query = new StringBuilder();

        query.append("select a.id ");
        query.append("from atendimento a ");
        query.append("where a.usuario_em_atendimento = :usuario ");
        query.append("\tand a.campanha in ( ");
        query.append("\t\tselect c.id ");
        query.append("\t\tfrom campanha c ");
        query.append("\t\t\tjoin status_campanha s on (s.id = c.status_campanha) ");
        query.append("\t\twhere c.tipo_campanha not in ('RECEPTIVA','PREDITIVA') ");
        query.append("\t\t\tand s.acao = 'LIBERAR') ");
        query.append("limit 1 ");

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("usuario", idUsuario);

        BigInteger resultado = (BigInteger) searchEntidade(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

        return (resultado == null) ? null : Long.valueOf(resultado.longValue());
    }

    public void atualizarAtendimentosEnviado(List<Long> listAtendimentos) {

        if (CollectionUtils.isEmpty(listAtendimentos)) {
            return;
        }

        Map<String, Object> parametros = new HashMap<>();
        StringBuilder query = new StringBuilder();

        query.append("update atendimento ");
        query.append("set enviado = true ");
        query.append("where id in (:listAtendimentos) ");
        parametros.put("listAtendimentos", listAtendimentos);

        executarSql(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }

    public void resetarAtendimentosEnviados(Long idCampanha) {

        StringBuilder query = new StringBuilder();

        query.append("update atendimento ");
        query.append("set enviado = false ");
        query.append("where enviado = true ");
        query.append(" and status is null ");

        if (idCampanha != null) {
            query.append("\tand campanha = :campanha ");

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("campanha", idCampanha);

            executarSql(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

        } else {
            query.append(" and campanha in (select c.id from campanha c join status_campanha sc on c.status_campanha = sc.id where sc.acao = 'LIBERAR') ");

            executarSql(DaoEnum.NATIVE_OBJECT, query.toString());
        }

    }

    private void executarSql(DaoEnum daoEum, String querry) {
        executarSql(daoEum, querry, null, null, null);
    }

    public boolean verificarJaFoiAtendido(Long idAtendimento) {
        StringBuilder query = new StringBuilder();

        query.append(" select a.id ");
        query.append(" from atendimento a ");
        query.append("\tjoin status_atendimento s on a.status = s.id ");
        query.append(" where a.id = :id ");
        query.append("  and s.acao <> 'FIM_FILA' ");

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("id", idAtendimento);

        Object resultado = searchEntidade(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

        return (resultado != null);
    }

    public int pesquisarQuantidadeImportados(Long idImportacao) {
        String query = "select count(id) from atendimento where importacao = :importacao";

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("importacao", idImportacao);

        BigInteger resultado = (BigInteger) searchEntidade(DaoEnum.NATIVE_OBJECT, query, parametros);

        return Integer.valueOf((resultado == null) ? 0 : resultado.intValue());
    }

    public List<?> pesquisarAtendimentosAlerta(Long idUsuario) {

        StringBuilder query = new StringBuilder();
        query.append("SELECT ");
        query.append("    a.id, ");
        query.append("    c.descricao as campanha_descricao, ");
        query.append("    a.cpf, ");
        query.append("    a.nome, ");
        query.append("    sa.descricao, ");
        query.append("    to_char(h.agendamento, 'dd/mm/yyyy HH24:mi:ss') as agendamento, ");
        query.append("    to_char(h.data_cadastro, 'dd/mm/yyyy HH24:mi:ss') as cadastro, ");
        query.append("    h.observacao ");
        query.append("FROM atendimento a ");
        query.append("    join historico_atendimento h on a.id = h.atendimento ");
        query.append("\t  join usuario u on h.usuario = u.id ");
        query.append("    join campanha c on a.campanha = c.id ");
        query.append("    join status_campanha sc on c.status_campanha = sc.id ");
        query.append("    join status_atendimento sa on a.status = sa.id ");
        query.append("where h.data_visualizado is null ");

        query.append("\t  and u.equipe is not null ");
        query.append("    and h.usuario = :usuario ");
        query.append("    and (sc.acao = 'LIBERAR' OR (sc.acao = 'FINALIZAR' ");
        query.append("    \t   and :dtagendamento BETWEEN c.data_inicio_agendamento and c.data_fim_agendamento ");
        query.append("    \t   and h.agendamento BETWEEN c.data_inicio_agendamento and c.data_fim_agendamento )) ");
        query.append("    and sa.acao like 'AGENDAR%' ");
        query.append("    and sa.acao <> 'AGENDAR_GLOBAL' ");
        query.append("    and :dtagendamento >= h.agendamento  ");
        query.append("order by h.agendamento desc ");

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("usuario", idUsuario);

        parametros.put("dtagendamento", new Date());

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);
    }

    public List<?> pesquisarAtendimentosPendentes(Long idUsuario, List<String> listTiposCampanha) {

        StringBuilder query = new StringBuilder();
        query.append("select a.id, ");
        query.append("       c.descricao, ");
        query.append("       a.nome, ");
        query.append("       a.cpf, ");
        query.append("       to_char(a.data_alteracao, 'dd/mm/yyyy HH24:mi:ss') as data_alteracao ");
        query.append("from atendimento a ");
        query.append("\tleft join status_atendimento s on a.status = s.id ");
        query.append("\tjoin campanha c on a.campanha = c.id ");
        query.append("\tjoin status_campanha sc on c.status_campanha = sc.id ");
        query.append("\tjoin usuario u on a.usuario_em_atendimento = u.id ");
        query.append("where a.usuario_em_atendimento = :usuario ");
        query.append("\tand u.equipe is not null ");
        query.append("\tand c.tipo_campanha in (:tipoCampanhas) ");
        query.append("  and (a.status is null or s.acao like 'AGENDAR%') ");
        query.append("\tand sc.acao <> 'SUSPENDER' ");
        query.append("order by a.data_alteracao, c.descricao, a.nome ");

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("usuario", idUsuario);
        parametros.put("tipoCampanhas", listTiposCampanha);

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);
    }

    public int pesquisarQuantidadeAtendimentosAlerta(Long idUsuario) {

        StringBuilder query = new StringBuilder();
        query.append("SELECT count(a.id) ");

        query.append("FROM atendimento a ");
        query.append("    join historico_atendimento h on a.id = h.atendimento ");
        query.append("\t  join usuario u on h.usuario = u.id ");
        query.append("    join campanha c on a.campanha = c.id ");
        query.append("    join status_campanha sc on c.status_campanha = sc.id ");
        query.append("    join status_atendimento sa on a.status = sa.id ");
        query.append("where h.data_visualizado is null ");

        query.append("\t  and u.equipe is not null ");
        query.append("    and h.usuario = :usuario ");
        query.append("    and (sc.acao = 'LIBERAR' OR (sc.acao = 'FINALIZAR' ");
        query.append("    \t   and :dtagendamento BETWEEN c.data_inicio_agendamento and c.data_fim_agendamento ");
        query.append("    \t   and h.agendamento BETWEEN c.data_inicio_agendamento and c.data_fim_agendamento )) ");
        query.append("    and sa.acao like 'AGENDAR%' ");
        query.append("    and sa.acao <> 'AGENDAR_GLOBAL' ");
        query.append("    and :dtagendamento >= h.agendamento  ");

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("usuario", idUsuario);

        parametros.put("dtagendamento", new Date());

        BigInteger resultado = (BigInteger) searchEntidade(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

        return Integer.valueOf((resultado == null) ? 0 : resultado.intValue());
    }

    public int pesquisarQuantidadePendentes(Long idUsuario, List<String> listTiposCampanha) {

        StringBuilder query = new StringBuilder();
        query.append("select count(a.id) ");

        query.append("from atendimento a ");
        query.append("\tleft join status_atendimento s on a.status = s.id ");
        query.append("\tjoin campanha c on a.campanha = c.id ");
        query.append("\tjoin status_campanha sc on c.status_campanha = sc.id ");
        query.append("\tjoin usuario u on a.usuario_em_atendimento = u.id ");
        query.append("where a.usuario_em_atendimento = :usuario ");
        query.append("\tand u.equipe is not null ");
        query.append("\tand c.tipo_campanha in (:tipoCampanhas) ");
        query.append("  and (a.status is null or s.acao like 'AGENDAR%') ");
        query.append("\tand sc.acao <> 'SUSPENDER' ");

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("usuario", idUsuario);
        parametros.put("tipoCampanhas", listTiposCampanha);

        BigInteger resultado = (BigInteger) searchEntidade(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

        return Integer.valueOf((resultado == null) ? 0 : resultado.intValue());
    }

    public boolean validarProposta(Long idAtendimento, String adesao, InstituicaoFinanceiraEnum instituicaoFinanceiraEnum, Long idEmpresa) {

        HashMap<String, Object> parametros = new HashMap<>();

        StringBuilder query = new StringBuilder();

        query.append("select distinct p.adesao ");
        query.append("from propostas_realizadas p ");
        query.append("\tjoin campanha c on p.campanha = c.id ");
        query.append("\tjoin status_atendimento s on p.status = s.id ");

        query.append("where p.empresa = :empresa ");
        query.append("\tand p.adesao = :adesao ");
        query.append("\tand s.acao = :acao ");
        query.append("\tand c.instituicao_financeira = :instituicaoFinanceira ");

        if (idAtendimento != null) {

            query.append("\tand p.id <> :atendimento ");
            parametros.put("atendimento", idAtendimento);

        }

        parametros.put("empresa", idEmpresa);
        parametros.put("adesao", adesao.trim());
        parametros.put("instituicaoFinanceira", instituicaoFinanceiraEnum.name());
        parametros.put("acao", AcaoStatusAtendimentoEnum.PROPOSTA_EFETIVADA.name());

        boolean tem = (searchEntidade(DaoEnum.NATIVE_OBJECT, query.toString(), parametros, Integer.valueOf(0), Integer.valueOf(1)) != null);

        return !tem;

    }

    public List<Object[]> pesquisarAtendimentosPorNomeCpf(String cpf, String nome, String adesao, String protocolo,
                                                          Long campanha, List<Long> equipes, List<Long> operador, List<Long> statusAtendimentos, Long statusContrato, Date dataInicio,
                                                          Date dataAFim, Usuario usuario, Long produto, String tiket, Long idEmpresa, Long idMotivo, Long idSubmotivo) {


        StringBuilder query = new StringBuilder();
        query.append("SELECT ");
        query.append("    a.id, ");
        query.append("    c.descricao AS campanha_descricao, ");
        query.append("    cl.nome, ");
        query.append("    cl.cpf, ");
        query.append("    s.descricao AS status, ");
        query.append("    a.data_alteracao, ");
        query.append("    u.nome AS operador, ");
        query.append("    COALESCE(a.adesao, array_to_string(a.adesoes, ', ')) AS adesao, ");
        query.append("    a.quantidade_parcelas, ");
        query.append("    a.valor_parcela, ");
        query.append("    a.valor_liberado, ");
        query.append("    a.protocolo, ");
        query.append("    sc.descricao AS status_contrato, ");
        query.append("    a.valor_liberado_emp, ");
        query.append("    a.entidade_principal, ");
        query.append("    a.entidade_secundaria, ");
        query.append("    a.orgao_principal, ");
        query.append("    a.orgao_secundario, ");
        query.append("    '\"'||a.outras_informacoes||'\"' AS outras_informacoes, ");
        query.append("    p.descricao AS produto, ");
        query.append("    a.tempo_pos_atendimento, ");
        query.append("    a.tiket, ");
        query.append("    to_char(a.data_nascimento, 'DD/MM/YYYY') AS nascimento, ");
        query.append("    eq.nome AS equipe, ");
        query.append("    m.descricao AS motivo, ");
        query.append("    sm.descricao AS submotivo ");
        query.append("FROM atendimento a ");
        query.append("    INNER JOIN empresa e ON a.empresa = e.id ");
        query.append("    INNER JOIN campanha c ON c.id = a.campanha ");
        query.append("    INNER JOIN cliente cl ON cl.id = a.cliente ");
        query.append("    LEFT JOIN status_atendimento s ON s.id = a.status ");
        query.append("    LEFT JOIN motivo m ON m.id = a.motivo ");
        query.append("    LEFT JOIN submotivo sm ON sm.id = a.submotivo ");
        query.append("    LEFT JOIN produto p ON p.id = a.produto ");
        query.append("    LEFT JOIN equipe eq ON eq.id = a.equipe ");
        query.append("    LEFT JOIN usuario u ON a.usuario_alteracao = u.id ");
        query.append("    LEFT JOIN contrato ct ON a.contrato = ct.id ");
        query.append("    LEFT JOIN status_contrato sc ON ct.status_contrato = sc.id ");
        query.append("WHERE (e.id = :empresa OR e.matriz = :empresa) ");
        query.append("  AND a.status IS NOT NULL ");
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("empresa", idEmpresa);

        if (PerfilUsuarioEnum.SUPERVISOR.equals(usuario.getPerfil())) {

            query.append("\tand c.id in ( ");
            query.append("\t\tselect distinct c.id ");
            query.append("\t\tfrom campanha c ");
            query.append("\t\t\tjoin campanha_equipe ce on ce.campanha = c.id ");
            query.append("\t\t\tjoin equipe_supervisor es on es.equipe = ce.equipe ");
            query.append("\t\twhere es.supervisor = :supervisor) ");
            query.append("\tand a.usuario_alteracao in ( ");
            query.append("\t\tselect distinct u.id ");
            query.append("\t\tfrom usuario u ");
            query.append("\t\t\tjoin equipe_supervisor es on es.equipe = u.equipe  ");
            query.append("\t\twhere es.supervisor = :supervisor) ");
            parametros.put("supervisor", usuario.getId());
        }

        if (cpf != null && !cpf.isEmpty()) {
            query.append("and a.cpf = :cpf ");
            parametros.put("cpf", StringUtils.leftPad(cpf.replaceAll("\\D+", "").replaceAll(" ", ""), 11, "0"));
        }

        if (nome != null && !nome.isEmpty()) {
            query.append("and upper(a.nome) like :nome ");
            parametros.put("nome", "%" + nome.toUpperCase() + "%");
        }

        if (adesao != null && !adesao.isEmpty()) {

            query.append("and (a.adesao = :adesao or a.adesoes @> ARRAY[:adesao]) ");
            parametros.put("adesao", adesao);

        }

        if (StringUtils.isNotBlank(tiket)) {

            query.append("and a.tiket = :tiket  ");
            parametros.put("tiket", tiket);
        }

        if (protocolo != null && !protocolo.isEmpty()) {
            query.append("and a.protocolo = :protocolo ");
            parametros.put("protocolo", protocolo);
        }

        if (campanha != null) {
            query.append("\tand a.campanha = :campanha ");
            parametros.put("campanha", campanha);
        }

        if (produto != null) {
            query.append("\tand a.produto = :produto ");
            parametros.put("produto", produto);
        }

        if (idMotivo != null) {
            query.append("\tand a.motivo = :idMotivo ");
            parametros.put("idMotivo", idMotivo);
        }

        if (idSubmotivo != null) {
            query.append("\tand a.motivo = :idSubmotivo ");
            parametros.put("idSubmotivo", idSubmotivo);
        }


        if (CollectionUtils.isNotEmpty(equipes) && CollectionUtils.isEmpty(operador)) {

            query.append("\tand a.equipe in  " + sqlFormatedList(equipes));

        }


        if (CollectionUtils.isNotEmpty(operador)) {
            query.append("and ( a.usuario_alteracao IN (:operador)  ) ");
            parametros.put("operador", operador);
        }


        if (CollectionUtils.isNotEmpty(statusAtendimentos)) {
            query.append("and a.status in " + sqlFormatedList(statusAtendimentos));

        }

        if (statusContrato != null) {
            query.append("and sc.id = :statusContrato ");
            parametros.put("statusContrato", statusContrato);
        }

        if (dataInicio != null && dataAFim != null) {

            query.append("and date(a.data_alteracao) BETWEEN date(:periodoInicio) and date(:periodoFim) ");
            parametros.put("periodoInicio", dataInicio);
            parametros.put("periodoFim", dataAFim);

        } else if (dataInicio != null) {

            query.append("and date(a.data_alteracao) >= date(:periodoInicio) ");
            parametros.put("periodoInicio", dataInicio);

        } else if (dataAFim != null) {

            query.append("and date(a.data_alteracao) <= date(:periodoFim) ");
            parametros.put("periodoFim", dataAFim);
        }

        query.append(" order by a.data_alteracao desc ");

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);
    }

    public List<Object[]> pesquisarAtendimentosPorNomeCpf(String cpf, String nome, String adesao, String protocolo,
                                                          Long campanha, Long equipe, Long operador, Long statusAtendimento, Long statusContrato, Date dataInicio,
                                                          Date dataAFim, Usuario usuario, Long produto, Long idEmpresa, Long idMotivo, Long idSubmotivo) {


        StringBuilder query = new StringBuilder();
        query.append("SELECT ");
        query.append("    a.id, ");
        query.append("    c.descricao as campanha_descricao, ");
        query.append("    cl.nome, ");
        query.append("    cl.cpf, ");
        query.append("    s.descricao as status, ");
        query.append("    a.data_alteracao, ");
        query.append("    u.nome as operador, ");
        query.append("    coalesce(a.adesao, array_to_string(a.adesoes, ', ')) as adesao, ");
        query.append("    a.quantidade_parcelas, ");
        query.append("    a.valor_parcela, ");
        query.append("    a.valor_liberado, ");
        query.append("    a.protocolo, ");
        query.append("    sc.descricao as status_contrato, ");
        query.append("    a.valor_liberado_emp, ");
        query.append("    a.entidade_principal, ");
        query.append("    a.entidade_secundaria, ");
        query.append("    a.orgao_principal, ");
        query.append("    a.orgao_secundario, ");
        query.append("    '\"'||a.outras_informacoes||'\"', ");
        query.append("    p.descricao as produto_descricao, ");
        query.append("    a.tempo_pos_atendimento, ");
        query.append("    a.tiket, ");
        query.append("    m.descricao as motivo, ");
        query.append("    sm.descricao as submotivo ");
        query.append("FROM atendimento a ");
        query.append("    inner join empresa e on a.empresa = e.id ");
        query.append("    inner join campanha c on (c.id = a.campanha) ");
        query.append("    inner join cliente cl on (cl.id = a.cliente) ");
        query.append("    left join status_atendimento s on (s.id = a.status) ");
        query.append("    left join motivo m on (m.id = a.motivo) ");
        query.append("    left join submotivo sm on (sm.id = a.submotivo) ");
        query.append("    left join produto p on (p.id = a.produto) ");
        query.append("    left join usuario u on (a.usuario_alteracao = u.id) ");
        query.append("    left join contrato ct on a.contrato = ct.id ");
        query.append("    left join status_contrato sc on ct.status_contrato = sc.id ");
        query.append("WHERE (e.id = :empresa OR e.matriz = :empresa) ");
        query.append("  AND a.status IS NOT NULL ");
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("empresa", idEmpresa);

        if (PerfilUsuarioEnum.SUPERVISOR.equals(usuario.getPerfil())) {

            query.append("\tand c.id in ( ");
            query.append("\t\tselect distinct c.id ");
            query.append("\t\tfrom campanha c ");
            query.append("\t\t\tjoin campanha_equipe ce on ce.campanha = c.id ");
            query.append("\t\t\tjoin equipe_supervisor es on es.equipe = ce.equipe ");
            query.append("\t\twhere es.supervisor = :supervisor) ");
            query.append("\tand a.usuario_alteracao in ( ");
            query.append("\t\tselect distinct u.id ");
            query.append("\t\tfrom usuario u ");
            query.append("\t\t\tjoin equipe_supervisor es on es.equipe = u.equipe  ");
            query.append("\t\twhere es.supervisor = :supervisor) ");
            parametros.put("supervisor", usuario.getId());
        }

        if (cpf != null && !cpf.isEmpty()) {
            query.append("and a.cpf = :cpf ");
            parametros.put("cpf", StringUtils.leftPad(cpf.replaceAll("\\D+", "").replaceAll(" ", ""), 11, "0"));
        }

        if (nome != null && !nome.isEmpty()) {
            query.append("and upper(a.nome) like :nome ");
            parametros.put("nome", "%" + nome.toUpperCase() + "%");
        }

        if (adesao != null && !adesao.isEmpty()) {

            query.append("and (a.adesao = :adesao or a.adesoes @> ARRAY[:adesao] or a.protocolo = :protocolo ) ");
            parametros.put("adesao", adesao);
            parametros.put("protocolo", adesao.trim());

        }

        if (protocolo != null && !protocolo.isEmpty()) {
            query.append("and a.protocolo = :protocolo ");
            parametros.put("protocolo", protocolo);
        }

        if (campanha != null) {
            query.append("\tand a.campanha = :campanha ");
            parametros.put("campanha", campanha);
        }

        if (produto != null) {
            query.append("\tand a.produto = :produto ");
            parametros.put("produto", produto);
        }

        if (equipe != null && operador == null) {
            query.append("\tand a.equipe = :equipe ");
            parametros.put("equipe", equipe);
        }

        if (operador != null) {
            query.append("and ( a.usuario_alteracao = :operador)");
            parametros.put("operador", operador);
        }

        if (statusAtendimento != null) {
            query.append("and a.status = :status ");
            parametros.put("status", statusAtendimento);
        }

        if (idMotivo != null) {
            query.append(" and a.motivo = :motivo ");
            parametros.put("motivo", idMotivo);

        }

        if (idSubmotivo != null) {
            query.append(" and a.submotivo = :idSubmotivo ");
            parametros.put("idSubmotivo", idSubmotivo);

        }

        if (statusContrato != null) {
            query.append("and sc.id = :statusContrato ");
            parametros.put("statusContrato", statusContrato);
        }

        if (dataInicio != null && dataAFim != null) {

            query.append("and date(a.data_alteracao) BETWEEN date(:periodoInicio) and date(:periodoFim) ");
            parametros.put("periodoInicio", dataInicio);
            parametros.put("periodoFim", dataAFim);

        } else if (dataInicio != null) {

            query.append("and date(a.data_alteracao) >= date(:periodoInicio) ");
            parametros.put("periodoInicio", dataInicio);

        } else if (dataAFim != null) {

            query.append("and date(a.data_alteracao) <= date(:periodoFim) ");
            parametros.put("periodoFim", dataAFim);
        }

        query.append(" order by a.data_alteracao desc ");

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);
    }


    //PESQUISA POR FILTRO MODEL LAZYMODEL
    public List<Object[]> pesquisarAtendimentosPorNomeCpf(String cpf, String nome, String adesao, String protocolo,
                                                          Long campanha, Long equipe, Long operador, Long statusAtendimento, Long statusContrato, Date dataInicio,
                                                          Date dataAFim, Usuario usuario, Long produto, Long idEmpresa, FiltroModel filtro, boolean isCount) {

        Map<String, Object> parametros = new HashMap<>();

        String query = pesquisarAtendimentoLazy(cpf, nome, adesao, protocolo, campanha, equipe, operador, statusAtendimento, statusContrato, dataInicio, dataAFim, usuario, produto, idEmpresa, filtro, isCount, parametros);

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros, Integer.valueOf(filtro.getFistResult()), Integer.valueOf(filtro.getMaxResult()));


    }


    //PESQUISA POR FILTRO MODEL LAZYMODEL PACOTE
    public List<Object[]> pesquisarAtendimentosPacotes(Long campanha, Long equipe, Long statusAtendimento, Date dataInicio, Date dataAFim, Usuario usuario, Long produto, Long idEmpresa, FiltroModel filtro, boolean isCount) {

        Map<String, Object> parametros = new HashMap<>();

        String query = pesquisarAtendimentoPacoteLazy(campanha, equipe, statusAtendimento, dataInicio, dataAFim, usuario, produto, idEmpresa, filtro, isCount, parametros);

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros, Integer.valueOf(filtro.getFistResult()), Integer.valueOf(filtro.getMaxResult()));


    }

    //PESQUISA POR FILTRO MODEL LAZYMODEL E LISTA DE USUARIO
    public List<Object[]> pesquisarAtendimentosPorNomeCpf(String cpf, String nome, String adesao, String protocolo,
                                                          Long campanha, List<Long> equipes, List<Long> operador, List<Long> statusAtendimentos, Long statusContrato, Date dataInicio,
                                                          Date dataAFim, Usuario usuario, Long produto, String tiket, Long idEmpresa, FiltroModel filtro, boolean isCount) {

        Map<String, Object> parametros = new HashMap<>();

        String query = pesquisarAtendimentoLazy(cpf, nome, adesao, protocolo, campanha, equipes, operador, statusAtendimentos, statusContrato, dataInicio, dataAFim, usuario, produto, tiket, idEmpresa, filtro, isCount, parametros);

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros, Integer.valueOf(filtro.getFistResult()), Integer.valueOf(filtro.getMaxResult()));

    }

    //PESQUISA  QUANTIDADEW POR FILTRO MODEL LAZY MODEL
    public List<Object[]> pesquisarQuantidadeAtendimentosPorNomeCpf(String cpf, String nome, String adesao, String protocolo, Long campanha, Long equipe, Long operador, Long statusAtendimento, Long statusContrato, Date dataInicio,
                                                                    Date dataAFim, Usuario usuario, Long produto, Long idEmpresa) {

        // TODO Auto-generated method stub

        Map<String, Object> parametros = new HashMap<>();

        String query = pesquisarAtendimentoLazy(cpf, nome, adesao, protocolo, campanha, equipe, operador, statusAtendimento, statusContrato, dataInicio, dataAFim, usuario, produto, idEmpresa, null, true, parametros);

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query, parametros);


    }

    //PESQUISA  QUANTIDADEW POR FILTRO MODEL LAZY MODEL LIST USUARIO
    public List<Object[]> pesquisarQuantidadeAtendimentosPorNomeCpf(String cpf, String nome, String adesao, String protocolo, Long campanha, List<Long> equipes, List<Long> operador, List<Long> statusAtendimentos, Long statusContrato, Date dataInicio,
                                                                    Date dataAFim, Usuario usuario, Long produto, String tiket, Long idEmpresa) {


        Map<String, Object> parametros = new HashMap<>();

        String query = pesquisarAtendimentoLazy(cpf, nome, adesao, protocolo, campanha, equipes, operador, statusAtendimentos, statusContrato, dataInicio, dataAFim, usuario, produto, tiket, idEmpresa, null, true, parametros);

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query, parametros);

    }


    //PESQUISA  QUANTIDADEW POR FILTRO MODEL LAZY PACOTE
    public Object pesquisarQuantidadeAtendimentosFiltroPacote(Long campanha, Long equipe, Long statusAtendimento, Date dataInicio, Date dataAFim, Usuario usuario, Long produto, Long idEmpresa) {

        Map<String, Object> parametros = new HashMap<>();

        String query = pesquisarAtendimentoPacoteLazy(campanha, equipe, statusAtendimento, dataInicio, dataAFim, usuario, produto, idEmpresa, null, true, parametros);

        return searchEntidade(DaoEnum.NATIVE_OBJECT, query, parametros);

    }

    //PESQUISA  QUANTIDADEW POR FILTRO QUANTIDADE MAXIMA TEL MODEL LAZY PACOTE
    public Object[] pesquisarQuantidadeMaximaTelefoneAtendimentosFiltroPacote(Long campanha, Long equipe, Long statusAtendimento, Date dataInicio, Date dataAFim, Usuario usuario, Long produto, Long idEmpresa) {

        Map<String, Object> parametros = new HashMap<>();

        String query = pesquisarQuantidadeMaximaTelefonestendimentoPacoteLazy(campanha, equipe, statusAtendimento, dataInicio, dataAFim, usuario, produto, idEmpresa, null, parametros);

        return (Object[]) searchEntidade(DaoEnum.NATIVE_OBJECT, query, parametros);

    }

    //RETORNA A QUERY LAZY
    private String pesquisarAtendimentoLazy(String cpf, String nome, String adesao, String protocolo, Long campanha,
                                            Long equipe, Long operador, Long statusAtendimento, Long statusContrato, Date dataInicio, Date dataAFim,
                                            Usuario usuario, Long produto, Long idEmpresa, FiltroModel filtro, boolean isCount, Map<String, Object> parametros) {

        StringBuilder query = new StringBuilder();

        query.append("SELECT ");

        if (isCount) {
            query.append(" count( a.id) , ");
            query.append(" SUM(a.valor_liberado) as total_valor  ");

        } else {
            query.append("    a.id, ");
            query.append("    c.descricao as campanha_descricao, ");
            query.append("    a.nome, ");
            query.append("    a.cpf, ");
            query.append("    s.descricao as status, ");
            query.append("    a.data_alteracao, ");
            query.append("    u.nome as operador, ");
            query.append("    coalesce(a.adesao, array_to_string(a.adesoes, ', ')) as adesao, ");
            query.append("    a.quantidade_parcelas, ");
            query.append("    a.valor_parcela, ");
            query.append("    a.valor_liberado, ");
            query.append("    a.protocolo, ");
            query.append("\t  sc.descricao as status_contrato, ");
            query.append("\t  a.valor_liberado_emp, ");
            query.append("\t  a.entidade_principal, ");
            query.append("\t  a.entidade_secundaria, ");
            query.append("\t  a.orgao_principal, ");
            query.append("\t  a.orgao_secundario, ");
            query.append("\t  '\"'||a.outras_informacoes||'\"' ");
            query.append("\t  , p.descricao ");
            query.append("\t  , a.tempo_pos_atendimento ");
        }

        query.append("FROM atendimento a ");
        query.append("\t  inner join empresa e on a.empresa = e.id ");
        query.append("    inner join campanha c on (c.id = a.campanha) ");
        query.append("    left join status_atendimento s on (s.id = a.status) ");
        query.append("    left join produto p on (p.id = a.produto) ");
        query.append("    left join usuario u on (a.usuario_alteracao = u.id) ");
        query.append("\t  left join contrato ct on a.contrato = ct.id ");
        query.append("\t  left join status_contrato sc on ct.status_contrato = sc.id ");
        query.append("where (e.id = :empresa or e.matriz = :empresa) ");
        query.append("\tand a.status is not null ");

        parametros.put("empresa", idEmpresa);

        if (PerfilUsuarioEnum.SUPERVISOR.equals(usuario.getPerfil())) {

            query.append("\tand c.id in ( ");
            query.append("\t\tselect distinct c.id ");
            query.append("\t\tfrom campanha c ");
            query.append("\t\t\tjoin campanha_equipe ce on ce.campanha = c.id ");
            query.append("\t\t\tjoin equipe_supervisor es on es.equipe = ce.equipe ");
            query.append("\t\twhere es.supervisor = :supervisor) ");
            query.append("\tand a.usuario_alteracao in ( ");
            query.append("\t\tselect distinct u.id ");
            query.append("\t\tfrom usuario u ");
            query.append("\t\t\tjoin equipe_supervisor es on es.equipe = u.equipe  ");
            query.append("\t\twhere es.supervisor = :supervisor) ");
            parametros.put("supervisor", usuario.getId());
        }

        if (cpf != null && !cpf.isEmpty()) {
            query.append("and a.cpf = :cpf ");
            parametros.put("cpf", StringUtils.leftPad(cpf.replaceAll("\\D+", "").replaceAll(" ", ""), 11, "0"));
        }

        if (nome != null && !nome.isEmpty()) {
            query.append("and upper(a.nome) like :nome ");
            parametros.put("nome", "%" + nome.toUpperCase() + "%");
        }

        if (adesao != null && !adesao.isEmpty()) {

            query.append("and (a.adesao = :adesao or a.adesoes @> ARRAY[:adesao]) ");
            parametros.put("adesao", adesao);

        }

        if (protocolo != null && !protocolo.isEmpty()) {
            query.append("and a.protocolo = :protocolo ");
            parametros.put("protocolo", protocolo);
        }

        if (campanha != null) {
            query.append("\tand a.campanha = :campanha ");
            parametros.put("campanha", campanha);
        }

        if (produto != null) {
            query.append("\tand a.produto = :produto ");
            parametros.put("produto", produto);
        }


        if (equipe != null && operador == null) {
            query.append("\tand a.equipe = :equipe ");
            parametros.put("equipe", equipe);
        }


        if (operador != null) {
            query.append("and ( a.usuario_alteracao = :operador)");
            parametros.put("operador", operador);
        }

        if (statusAtendimento != null) {
            query.append("and a.status = :status ");
            parametros.put("status", statusAtendimento);
        }

        if (statusContrato != null) {
            query.append("and sc.id = :statusContrato ");
            parametros.put("statusContrato", statusContrato);
        }

        if (dataInicio != null && dataAFim != null) {

            query.append("and date(a.data_alteracao) BETWEEN date(:periodoInicio) and date(:periodoFim) ");
            parametros.put("periodoInicio", dataInicio);
            parametros.put("periodoFim", dataAFim);

        } else if (dataInicio != null) {

            query.append("and date(a.data_alteracao) >= date(:periodoInicio) ");
            parametros.put("periodoInicio", dataInicio);

        } else if (dataAFim != null) {

            query.append("and date(a.data_alteracao) <= date(:periodoFim) ");
            parametros.put("periodoFim", dataAFim);
        }

        if (!isCount) {

            if (filtro != null && StringUtils.isNotBlank(filtro.getPropriedadeOrdenacao())) {

                query.append(" order by  ");
                query.append(filtro.getPropriedadeOrdenacao());

                if (!filtro.isAscendente())
                    query.append(" desc");

            } else {
                query.append(" order by a.data_alteracao desc ");
            }

        }

        return query.toString();

    }

    //RETORNA A QUERY LAZY LISTA USUARIO
    private String pesquisarAtendimentoLazy(String cpf, String nome, String adesao, String protocolo, Long campanha,
                                            List<Long> equipes, List<Long> operadores, List<Long> statusAtendimentos, Long statusContrato, Date dataInicio, Date dataAFim,
                                            Usuario usuario, Long produto, String tiket, Long idEmpresa, FiltroModel filtro, boolean isCount, Map<String, Object> parametros) {

        StringBuilder query = new StringBuilder();

        query.append("SELECT ");

        if (isCount) {
            query.append(" count( a.id) , ");
            query.append(" SUM(a.valor_liberado) as total_valor  ");

        } else {
            query.append("    a.id, ");
            query.append("    c.descricao as campanha_descricao, ");
            query.append("    a.nome, ");
            query.append("    a.cpf, ");
            query.append("    s.descricao as status, ");
            query.append("    a.data_alteracao, ");
            query.append("    u.nome as operador, ");
            query.append("    coalesce(a.adesao, array_to_string(a.adesoes, ', ')) as adesao, ");
            query.append("    a.quantidade_parcelas, ");
            query.append("    a.valor_parcela, ");
            query.append("    a.valor_liberado, ");
            query.append("    a.protocolo, ");
            query.append("\t  sc.descricao as status_contrato, ");
            query.append("\t  a.valor_liberado_emp, ");
            query.append("\t  a.entidade_principal, ");
            query.append("\t  a.entidade_secundaria, ");
            query.append("\t  a.orgao_principal, ");
            query.append("\t  a.orgao_secundario, ");
            query.append("\t  '\"'||a.outras_informacoes||'\"' ");
            query.append("\t  , p.descricao ");
            query.append("\t  , a.tempo_pos_atendimento ");
        }

        query.append("FROM atendimento a ");
        query.append("\t  inner join empresa e on a.empresa = e.id ");
        query.append("    inner join campanha c on (c.id = a.campanha) ");
        query.append("    left join status_atendimento s on (s.id = a.status) ");
        query.append("    left join produto p on (p.id = a.produto) ");
        query.append("    left join usuario u on (a.usuario_alteracao = u.id) ");
        query.append("\t  left join contrato ct on a.contrato = ct.id ");
        query.append("\t  left join status_contrato sc on ct.status_contrato = sc.id ");
        query.append("where (e.id = :empresa or e.matriz = :empresa) ");
        query.append("\tand a.status is not null ");

        parametros.put("empresa", idEmpresa);

        if (PerfilUsuarioEnum.SUPERVISOR.equals(usuario.getPerfil())) {

            query.append("\tand c.id in ( ");
            query.append("\t\tselect distinct c.id ");
            query.append("\t\tfrom campanha c ");
            query.append("\t\t\tjoin campanha_equipe ce on ce.campanha = c.id ");
            query.append("\t\t\tjoin equipe_supervisor es on es.equipe = ce.equipe ");
            query.append("\t\twhere es.supervisor = :supervisor) ");
            query.append("\tand a.usuario_alteracao in ( ");
            query.append("\t\tselect distinct u.id ");
            query.append("\t\tfrom usuario u ");
            query.append("\t\t\tjoin equipe_supervisor es on es.equipe = u.equipe  ");
            query.append("\t\twhere es.supervisor = :supervisor) ");
            parametros.put("supervisor", usuario.getId());
        }

        if (cpf != null && !cpf.isEmpty()) {
            query.append("and a.cpf = :cpf ");
            parametros.put("cpf", StringUtils.leftPad(cpf.replaceAll("\\D+", "").replaceAll(" ", ""), 11, "0"));
        }

        if (nome != null && !nome.isEmpty()) {
            query.append("and upper(a.nome) like :nome ");
            parametros.put("nome", "%" + nome.toUpperCase() + "%");
        }

        if (adesao != null && !adesao.isEmpty()) {

            query.append("and (a.adesao = :adesao or a.adesoes @> ARRAY[:adesao]) ");
            parametros.put("adesao", adesao);

        }

        if (protocolo != null && !protocolo.isEmpty()) {
            query.append("and a.protocolo = :protocolo ");
            parametros.put("protocolo", protocolo);
        }

        if (campanha != null) {
            query.append("\tand a.campanha = :campanha ");
            parametros.put("campanha", campanha);
        }

        if (produto != null) {
            query.append("\tand a.produto = :produto ");
            parametros.put("produto", produto);
        }

        if (StringUtils.isNotBlank(tiket)) {
            query.append(" and a.tiket = :tiket ");
            parametros.put("tiket", tiket);
        }

        if (CollectionUtils.isNotEmpty(equipes) && CollectionUtils.isEmpty(operadores)) {
            query.append("\tand a.equipe in  " + sqlFormatedList(equipes));
        }


        if (CollectionUtils.isNotEmpty(operadores)) {
            query.append("and ( a.usuario_alteracao IN (:operador)  ) ");
            parametros.put("operador", operadores);
        }

        if (CollectionUtils.isNotEmpty(statusAtendimentos)) {
            query.append("and a.status in " + sqlFormatedList(statusAtendimentos));

        }

        if (statusContrato != null) {
            query.append("and sc.id = :statusContrato ");
            parametros.put("statusContrato", statusContrato);
        }


        if (dataInicio != null && dataAFim != null) {

            query.append("and date(a.data_alteracao) BETWEEN date(:periodoInicio) and date(:periodoFim) ");
            parametros.put("periodoInicio", dataInicio);
            parametros.put("periodoFim", dataAFim);

        } else if (dataInicio != null) {

            query.append("and date(a.data_alteracao) >= date(:periodoInicio) ");
            parametros.put("periodoInicio", dataInicio);

        } else if (dataAFim != null) {

            query.append("and date(a.data_alteracao) <= date(:periodoFim) ");
            parametros.put("periodoFim", dataAFim);
        }

        if (!isCount) {

            if (filtro != null && StringUtils.isNotBlank(filtro.getPropriedadeOrdenacao())) {

                query.append(" order by  ");
                query.append(filtro.getPropriedadeOrdenacao());

                if (!filtro.isAscendente())
                    query.append(" desc");

            } else {
                query.append(" order by a.data_alteracao desc ");
            }

        }
        // System.out.println(query.toString());
        return query.toString();

    }


    //RETORNA A QUERY LAZY PACOTES
    private String pesquisarAtendimentoPacoteLazy(Long campanha, Long equipe, Long statusAtendimento, Date dataInicio, Date dataAFim,
                                                  Usuario usuario, Long produto, Long idEmpresa, FiltroModel filtro, boolean isCount, Map<String, Object> parametros) {

        StringBuilder query = new StringBuilder();

        query.append("SELECT ");

        if (isCount) {
            query.append(" count( a.id)  ");

        } else {
            query.append("    a.id, ");
            query.append("    c.descricao as campanha_descricao, ");
            query.append("    a.nome, ");
            query.append("    a.cpf, ");
            query.append("    s.descricao as status, ");
            query.append("    a.data_alteracao, ");
            query.append("    u.nome as operador, ");
            query.append("    coalesce(a.adesao, array_to_string(a.adesoes, ', ')) as adesao, ");
            query.append("    a.quantidade_parcelas, ");
            query.append("    a.valor_parcela, ");
            query.append("    a.valor_liberado, ");
            query.append("    a.protocolo, ");
            query.append("\t  sc.descricao as status_contrato, ");
            query.append("\t  a.valor_liberado_emp, ");
            query.append("\t  a.entidade_principal, ");
            query.append("\t  a.entidade_secundaria, ");
            query.append("\t  a.orgao_principal, ");
            query.append("\t  a.orgao_secundario, ");
            query.append("\t  '\"'||a.outras_informacoes||'\"' ");
            query.append("\t  , p.descricao ");
            query.append("\t  , a.tempo_pos_atendimento ");
            query.append("\t  , a.tiket ");
        }

        query.append("FROM atendimento a ");
        query.append("\t  inner join empresa e on a.empresa = e.id ");
        query.append("    inner join campanha c on (c.id = a.campanha) ");
        query.append("    left join status_atendimento s on (s.id = a.status) ");
        query.append("    left join produto p on (p.id = a.produto) ");
        query.append("    left join usuario u on (a.usuario_alteracao = u.id) ");
        query.append("\t  left join contrato ct on a.contrato = ct.id ");
        query.append("\t  left join status_contrato sc on ct.status_contrato = sc.id ");
        query.append("where (e.id = :empresa or e.matriz = :empresa) ");
        query.append("\tand a.status is not null ");

        parametros.put("empresa", idEmpresa);

        if (PerfilUsuarioEnum.SUPERVISOR.equals(usuario.getPerfil())) {

            query.append("\tand c.id in ( ");
            query.append("\t\tselect distinct c.id ");
            query.append("\t\tfrom campanha c ");
            query.append("\t\t\tjoin campanha_equipe ce on ce.campanha = c.id ");
            query.append("\t\t\tjoin equipe_supervisor es on es.equipe = ce.equipe ");
            query.append("\t\twhere es.supervisor = :supervisor) ");
            query.append("\tand a.usuario_alteracao in ( ");
            query.append("\t\tselect distinct u.id ");
            query.append("\t\tfrom usuario u ");
            query.append("\t\t\tjoin equipe_supervisor es on es.equipe = u.equipe  ");
            query.append("\t\twhere es.supervisor = :supervisor) ");
            parametros.put("supervisor", usuario.getId());
        }


        if (campanha != null) {
            query.append("\tand a.campanha = :campanha ");
            parametros.put("campanha", campanha);
        }

        if (produto != null) {
            query.append("\tand a.produto = :produto ");
            parametros.put("produto", produto);
        }

        if (statusAtendimento != null) {
            query.append("and a.status = :status ");
            parametros.put("status", statusAtendimento);
        }

        if (dataInicio != null && dataAFim != null) {

            query.append("and date(a.data_alteracao) BETWEEN date(:periodoInicio) and date(:periodoFim) ");
            parametros.put("periodoInicio", dataInicio);
            parametros.put("periodoFim", dataAFim);

        } else if (dataInicio != null) {

            query.append("and date(a.data_alteracao) >= date(:periodoInicio) ");
            parametros.put("periodoInicio", dataInicio);

        } else if (dataAFim != null) {

            query.append("and date(a.data_alteracao) <= date(:periodoFim) ");
            parametros.put("periodoFim", dataAFim);
        }

        return query.toString();

    }


    //RETORNA A QUERY LAZY QUANTIDADE MAXIMA TEL PACOTES
    private String pesquisarQuantidadeMaximaTelefonestendimentoPacoteLazy(Long campanha, Long equipe, Long statusAtendimento, Date dataInicio, Date dataAFim,
                                                                          Usuario usuario, Long produto, Long idEmpresa, FiltroModel filtro, Map<String, Object> parametros) {

        StringBuilder query = new StringBuilder();

        query.append("SELECT ");
        query.append("  a.id, count(t.id)  ");
        query.append("FROM atendimento a ");
        query.append("\t  inner join telefone t on t.atendimento = a.id ");
        query.append("\t  inner join empresa e on a.empresa = e.id ");
        query.append("where (e.id = :empresa or e.matriz = :empresa) ");
        query.append("\tand a.status is not null ");

        parametros.put("empresa", idEmpresa);

        if (PerfilUsuarioEnum.SUPERVISOR.equals(usuario.getPerfil())) {

            query.append("\tand c.id in ( ");
            query.append("\t\tselect distinct c.id ");
            query.append("\t\tfrom campanha c ");
            query.append("\t\t\tjoin campanha_equipe ce on ce.campanha = c.id ");
            query.append("\t\t\tjoin equipe_supervisor es on es.equipe = ce.equipe ");
            query.append("\t\twhere es.supervisor = :supervisor) ");
            query.append("\tand a.usuario_alteracao in ( ");
            query.append("\t\tselect distinct u.id ");
            query.append("\t\tfrom usuario u ");
            query.append("\t\t\tjoin equipe_supervisor es on es.equipe = u.equipe  ");
            query.append("\t\twhere es.supervisor = :supervisor) ");
            parametros.put("supervisor", usuario.getId());
        }

        if (campanha != null) {
            query.append("\tand a.campanha = :campanha ");
            parametros.put("campanha", campanha);
        }

        if (produto != null) {
            query.append("\tand a.produto = :produto ");
            parametros.put("produto", produto);
        }

        if (statusAtendimento != null) {
            query.append("and a.status = :status ");
            parametros.put("status", statusAtendimento);
        }

        if (dataInicio != null && dataAFim != null) {

            query.append("and date(a.data_alteracao) BETWEEN date(:periodoInicio) and date(:periodoFim) ");
            parametros.put("periodoInicio", dataInicio);
            parametros.put("periodoFim", dataAFim);

        } else if (dataInicio != null) {

            query.append("and date(a.data_alteracao) >= date(:periodoInicio) ");
            parametros.put("periodoInicio", dataInicio);

        } else if (dataAFim != null) {

            query.append("and date(a.data_alteracao) <= date(:periodoFim) ");
            parametros.put("periodoFim", dataAFim);
        }
        query.append("\tGROUP BY  a.id ORDER   BY count(t.id) DESC limit 1");

        return query.toString();

    }


    public List<?> pesquisarAtendimentosNaoTrabalhados(String cpf, Long idEmpresa, Long equipe) {

        StringBuilder query = new StringBuilder();

        query.append("select a.id, c.descricao, a.nome, a.cpf ");
        query.append("from atendimento a ");
        query.append("\tjoin campanha c on a.campanha = c.id ");
        query.append("\t join campanha_equipe ce ON ce.campanha = c.id  ");

        query.append("where a.usuario_em_atendimento is null ");
        query.append("\tand a.status is null ");
        query.append("\tand ce.equipe = :equipe  ");
        query.append("\tand a.cpf = :cpf ");
        query.append("\tand a.empresa = :empresa ");
        query.append("order by c.descricao, a.nome ");

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("empresa", idEmpresa);
        parametros.put("equipe", equipe);
        parametros.put("cpf", StringUtils.leftPad(cpf.replaceAll("\\D+", "").replaceAll(" ", ""), 11, ""));

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);
    }

    public List<?> pesquisarAtendimentosNaoTrabalhadosPorTelefone(String telefone, Long idEmpresa, Long idEquipe) {

        StringBuilder query = new StringBuilder();

        query.append("select distinct a.id, c.descricao, a.nome, a.cpf ");
        query.append("from atendimento a ");
        query.append("\tjoin campanha c on a.campanha = c.id ");
        query.append("\t join campanha_equipe ce ON ce.campanha = c.id ");

        query.append("\tjoin status_campanha sc ON c.status_campanha = sc.id ");

        query.append("\tjoin telefone t on a.id = t.atendimento ");

        query.append("where a.usuario_em_atendimento is null ");

        query.append("\t and a.status is null ");
        query.append("\t and sc.acao = 'LIBERAR' ");
        query.append("\t and ce.equipe = :equipe ");
        query.append("\t and a.empresa = :empresa ");
        query.append("\t and CONCAT(t.ddd,t.numero) like :telefone ");
        query.append("order by c.descricao, a.nome ");

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("empresa", idEmpresa);
        parametros.put("equipe", idEquipe);
        parametros.put("telefone", "%" + telefone.replaceAll("\\D+", "").replaceAll(" ", "") + "%");

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);
    }

    /*
     * public List<?> pesquisarAtendimentosNaoTrabalhadosPorTelefone(String
     * telefone, Long idEmpresa) {
     *
     * StringBuilder query = new StringBuilder();
     *
     * query.append("select distinct a.id, c.descricao, a.nome, a.cpf ");
     * query.append("from atendimento a ");
     * query.append("\tjoin campanha c on a.campanha = c.id ");
     *
     * query.append("\tjoin status_campanha sc ON c.status_campanha = sc.id ");
     *
     * query.append("\tjoin telefone t on a.id = t.atendimento ");
     *
     * query.append("where a.usuario_em_atendimento is null ");
     *
     * query.append("\tand a.status is null ");
     * query.append("\tand sc.acao = 'LIBERAR' ");
     *
     * query.append("\tand a.empresa = :empresa ");
     * query.append("\tand CONCAT(t.ddd,t.numero) like :telefone ");
     * query.append("order by c.descricao, a.nome ");
     *
     * Map<String, Object> parametros = new HashMap<>(); parametros.put("empresa",
     * idEmpresa); parametros.put("telefone", "%"+telefone.replaceAll("\\D+",
     * "").replaceAll(" ", "")+"%");
     *
     * return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);
     * }
     */
    public List<Object[]> pesquisarAtendimentosPorNomeCpfProduto(String cpf, String nome, String adesao, String protocolo,
                                                                 Long campanha, Long equipe, Long operador, Long statusAtendimento, Long statusContrato, Date dataInicio,
                                                                 Date dataAFim, Usuario usuario, Long idProduto, Long idEmpresa) {

        // TODO Auto-generated method stub

        StringBuilder query = new StringBuilder();
        query.append("SELECT ");
        query.append("    a.id, ");
        query.append("    c.descricao as campanha_descricao, ");
        query.append("    a.nome, ");
        query.append("    a.cpf, ");
        query.append("    s.descricao as status, ");
        query.append("    a.data_alteracao, ");
        query.append("    u.nome as operador, ");
        query.append("    coalesce(a.adesao, array_to_string(a.adesoes, ', ')) as adesao, ");
        query.append("    a.quantidade_parcelas, ");
        query.append("    a.valor_parcela, ");
        query.append("    a.valor_liberado, ");
        query.append("    a.protocolo, ");
        query.append("\t  sc.descricao as status_contrato, ");
        query.append("\t  a.valor_liberado_emp, ");
        query.append("\t  a.entidade_principal, ");
        query.append("\t  a.entidade_secundaria, ");
        query.append("\t  a.orgao_principal, ");
        query.append("\t  a.orgao_secundario, ");
        query.append("\t  '\"'||a.outras_informacoes||'\"'  ");
        query.append("\t  , p.descricao  ");
        query.append("FROM propostas_realizadas a ");
        query.append("\t  inner join empresa e on a.empresa = e.id ");
        query.append("    inner join campanha c on (c.id = a.campanha) ");
        query.append("    left join status_atendimento s on (s.id = a.status) ");
        query.append("    left join usuario u on (a.usuario_alteracao = u.id) ");
        query.append("\t  left join contrato ct on a.contrato = ct.id ");
        query.append("\t  left join status_contrato sc on ct.status_contrato = sc.id ");
        query.append("\t  left join produto p on a.produto = p.id ");
        query.append("where (e.id = :empresa or e.matriz = :empresa) ");
        query.append("\tand a.status is not null ");
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("empresa", idEmpresa);

        if (PerfilUsuarioEnum.SUPERVISOR.equals(usuario.getPerfil())) {

            query.append("\tand c.id in ( ");
            query.append("\t\tselect distinct c.id ");
            query.append("\t\tfrom campanha c ");
            query.append("\t\t\tjoin campanha_equipe ce on ce.campanha = c.id ");
            query.append("\t\t\tjoin equipe_supervisor es on es.equipe = ce.equipe ");
            query.append("\t\twhere es.supervisor = :supervisor) ");
            query.append("\tand a.usuario_alteracao in ( ");
            query.append("\t\tselect distinct u.id ");
            query.append("\t\tfrom usuario u ");
            query.append("\t\t\tjoin equipe_supervisor es on es.equipe = u.equipe  ");
            query.append("\t\twhere es.supervisor = :supervisor) ");
            parametros.put("supervisor", usuario.getId());
        }

        if (cpf != null && !cpf.isEmpty()) {
            query.append("and a.cpf = :cpf ");
            parametros.put("cpf", StringUtils.leftPad(cpf.replaceAll("\\D+", "").replaceAll(" ", ""), 11, "0"));
        }

        if (nome != null && !nome.isEmpty()) {
            query.append("and upper(a.nome) like :nome ");
            parametros.put("nome", "%" + nome.toUpperCase() + "%");
        }

        if (adesao != null && !adesao.isEmpty()) {

            query.append("and (a.adesao = :adesao or a.adesoes @> ARRAY[:adesao]) ");
            parametros.put("adesao", adesao);

        }

        if (protocolo != null && !protocolo.isEmpty()) {
            query.append("and a.protocolo = :protocolo ");
            parametros.put("protocolo", protocolo);
        }

        if (campanha != null) {
            query.append("\tand a.campanha = :campanha ");
            parametros.put("campanha", campanha);
        }

        if (equipe != null && operador == null) {
            query.append("\tand a.equipe = :equipe ");
            parametros.put("equipe", equipe);
        }

        if (operador != null) {
            query.append("and ( a.usuario_alteracao = :operador)");
            parametros.put("operador", operador);
        }

        if (statusAtendimento != null) {
            query.append("and a.status = :status ");
            parametros.put("status", statusAtendimento);
        }

        if (statusContrato != null) {
            query.append("and sc.id = :statusContrato ");
            parametros.put("statusContrato", statusContrato);
        }

        if (idProduto != null) {
            query.append("and a.produto = :produto ");
            parametros.put("produto", idProduto);
        }

        if (dataInicio != null && dataAFim != null) {

            query.append("and date(a.data_alteracao) BETWEEN date(:periodoInicio) and date(:periodoFim) ");
            parametros.put("periodoInicio", dataInicio);
            parametros.put("periodoFim", dataAFim);

        } else if (dataInicio != null) {

            query.append("and date(a.data_alteracao) >= date(:periodoInicio) ");
            parametros.put("periodoInicio", dataInicio);

        } else if (dataAFim != null) {

            query.append("and date(a.data_alteracao) <= date(:periodoFim) ");
            parametros.put("periodoFim", dataAFim);
        }

        query.append(" order by a.data_alteracao desc ");

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);
    }


    public GenericAtendimento pesquisarAtendimentoContrato(Long idAtendimento) {
        // TODO Auto-generated method stub

        StringBuilder query = new StringBuilder();

        query.append("select a ");
        query.append("from Atendimento a ");
        query.append("\tjoin fetch a.campanha c ");
        query.append("\tjoin fetch a.empresa ");
        query.append("\tleft join fetch a.status sa ");
        query.append("\tleft join fetch a.usuarioOcupado o ");
        query.append("\tleft join fetch a.usuarioAlteracao u ");
        query.append("\tleft join fetch a.formaPagamento tp ");
        query.append("\tleft join fetch a.produto pe ");
        query.append("\tleft join fetch a.contrato ct ");
        query.append("\tleft join fetch ct.statusContrato ");
        query.append("\tleft join fetch a.loja ");
        query.append("where a.id = :id ");

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("id", idAtendimento);

        return (GenericAtendimento) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

    public List<?> pesquisarPendencias(Long idEquipe, Long idUsuario, Usuario usuario, Long idEmpresa) {

        HashMap<String, Object> parametros = new HashMap<>();

        StringBuilder query = new StringBuilder();
        query.append("select a.id, a.nome, sum(a.total) as total ");
        query.append("from ( ");
        query.append("    select u.id, u.nome, count(a.id) as total ");
        query.append("    from public.atendimento a ");
        query.append("        join usuario u on u.id = a.usuario_em_atendimento ");
        query.append("    where a.empresa = :empresa ");
        query.append("        and a.status is null ");

        if (idUsuario != null && idEquipe != null) {

            query.append("\tand a.usuario_em_atendimento = :operador ");
            query.append("\tand a.usuario_em_atendimento in ( ");
            query.append("    select u.id ");
            query.append("\t  from usuario u ");
            query.append("    where u.equipe = :equipe ) ");
            parametros.put("equipe", idEquipe);
            parametros.put("operador", idUsuario);

        } else if (idUsuario != null) {

            query.append("\tand a.usuario_em_atendimento = :operador ");
            parametros.put("operador", idUsuario);

        } else if (idEquipe != null) {

            query.append("\tand a.usuario_em_atendimento in ( ");
            query.append("    select u.id ");
            query.append("\t  from usuario u ");
            query.append("    where u.equipe = :equipe) ");
            parametros.put("equipe", idEquipe);

        }
        if (usuario.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

            query.append("\tand u.id in ( ");
            query.append("\t\tselect distinct u.id ");
            query.append("\t\tfrom usuario u ");
            query.append("\t\t\tjoin equipe e on u.equipe = e.id ");
            query.append("\t\t\tjoin equipe_supervisor es on es.equipe = e.id ");
            query.append("\t\twhere es.supervisor = :supervisor) ");
            parametros.put("supervisor", usuario.getId());

        }

        query.append("    group by u.id, u.nome ) a ");
        query.append("group by a.id, a.nome ");
        query.append("order by total desc, a.nome");
        parametros.put("empresa", idEmpresa);

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }

    public List<Atendimento> pesquisarPendenciasPorOperadores(List<Long> listOperadores) {

        StringBuilder query = new StringBuilder();

        HashMap<String, Object> parametros = new HashMap<>();

        query.append("select a ");
        query.append("from Atendimento a ");
        query.append("where a.usuarioOcupado.id in :operadores ");
        query.append("\tand a.status is null ");
        parametros.put("operadores", listOperadores);

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

    public List<?> pesquisarMonitoramentoAtendimentos(Long idEmpresa) {

        StringBuilder query = new StringBuilder();

        query.append("select u.nome as usuario, ");
        query.append("\t\t e.nome as equipe, ");
        query.append("       c.descricao as campanha, ");
        query.append("\t\t case  ");
        query.append("\t\t \twhen ((EXTRACT(EPOCH FROM now() - a.data_alteracao)) / 60) >= ep.meta_tma then '[ ALERTA ]' ");
        query.append("          when ((EXTRACT(EPOCH FROM now() - a.data_alteracao)) / 60) >= (ep.meta_tma/2) then '[ ATENCAO ] ' ");
        query.append("\t\t\telse '[ OK ]' ");
        query.append("\t\t end as status ");
        query.append("from atendimento a ");
        query.append("\t  join campanha c on a.campanha = c.id ");
        query.append("    join usuario u on a.usuario_em_atendimento = u.id ");
        query.append("\t  join equipe e on u.equipe = e.id ");
        query.append("    join empresa ep on c.empresa = ep.id ");
        query.append("where a.status is null ");
        query.append("\tand date(a.data_alteracao) = date(now()) ");
        query.append("\tand a.empresa = :empresa ");
        query.append("order by status, equipe, usuario ");

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("empresa", idEmpresa);

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);
    }

    public List<?> pesquisarQuantidadeMonitorCampanhaCarga(Long idEmpresa, Long idCampanha, Date inicio, Date fim) {

        Map<String, Object> parametros = new HashMap<>();
        StringBuilder query = new StringBuilder();

        query.append("select ");
        query.append("  coalesce(r.total,0) as qTotal, ");
        query.append("  coalesce(r.realizados,0) as qRealizada, ");
        query.append("  coalesce(r.finalizados,0) as qFinalizado, ");
        query.append("  CASE ");
        query.append("      WHEN r.total = 0 THEN 0 ");
        query.append("      ELSE coalesce((cast(r.realizados as numeric) / cast(r.total as numeric)),0) ");
        query.append("  END as pRealizado, ");
        query.append("  CASE ");
        query.append("      WHEN r.total = 0 THEN 0 ");
        query.append("      ELSE coalesce((cast(r.finalizados as numeric) / cast(r.total as numeric)),0) ");
        query.append("  END as pFinalizado, ");
        query.append("  coalesce((r.total - r.finalizados),0) as qRestanteRealizado ");
        query.append("from ( ");
        query.append("  \tselect sum(x.total) as total, ");
        query.append("    \t       sum(x.realizados) as realizados, ");
        query.append("             sum(x.finalizados) as finalizados ");
        query.append("    from ( ");
        query.append("      (select ");
        query.append("          sum(case when c.consulta_saque then ");
        query.append(
                " \t\t\t\t\tcase when a1.valor_max_operacao >= coalesce(c.valor_saque,1) and a1.valor_max_operacao <= coalesce(c.valor_saque_maximo,10000000) then 1 else 0 end ");
        query.append("  \t \t\telse 1 end) as total, ");
        query.append("                   count(DISTINCT CASE when(a1.status IS NOT NULL) THEN CASE ");
        query.append(
                "                                                                            WHEN c.consulta_saque THEN CASE ");
        query.append(
                "                                                                                                           WHEN a1.valor_max_operacao >= coalesce(c.valor_saque,1) ");
        query.append(
                "                                                                                                                AND a1.valor_max_operacao <= coalesce(c.valor_saque_maximo,10000000) THEN a1.id ");
        query.append(
                "                                                                                                           ELSE NULL ");
        query.append(
                "                                                                                                       END ");
        query.append("                                                                            ELSE a1.id ");
        query.append("                                                                        END ");
        query.append("                                      ELSE NULL ");
        query.append("                                  END) AS realizados, ");
        query.append(
                "                   count(DISTINCT CASE when(s1.acao IN ('PROPOSTA_EFETIVADA', 'PROPOSTA_PARCIAL', 'CONCLUIR', 'CONTATO', 'SEM_ACAO', 'BLOQUEAR_CPF')) THEN CASE ");
        query.append(
                "                                                                                                                                                               WHEN c.consulta_saque THEN CASE ");
        query.append(
                "                                                                                                                                                                                              WHEN a1.valor_max_operacao >= coalesce(c.valor_saque,1) ");
        query.append(
                "                                                                                                                                                                                                   AND a1.valor_max_operacao <= coalesce(c.valor_saque_maximo,10000000) THEN a1.id ");
        query.append(
                "                                                                                                                                                                                              ELSE NULL ");
        query.append(
                "                                                                                                                                                                                          END ");
        query.append(
                "                                                                                                                                                               ELSE a1.id ");
        query.append(
                "                                                                                                                                                           END ");
        query.append("                                      ELSE NULL ");
        query.append("                                  END) AS finalizados ");
        query.append("      from campanha c ");
        query.append("           join public.atendimento a1 on c.id = a1.campanha \t\t");
        query.append("           left join status_atendimento s1 on a1.status = s1.id \t");
        query.append("      WHERE c.id = :campanha ");

        if (inicio != null) {

            query.append("\t\tAND a1.data_alteracao >= :inicio ");
            parametros.put("inicio", inicio);

        }
        if (fim != null) {

            query.append("\t\tAND a1.data_alteracao <= :fim ");
            parametros.put("fim", fim);

        }

//bkp
        if (inicio != null) {

            query.append("\t\tAND a2.data_alteracao >= :inicio ");
            parametros.put("inicio", inicio);

        }

        if (fim != null) {

            query.append("\t\tAND a2.data_alteracao <= :fim ");

            parametros.put("fim", fim);

        }

        query.append("        and c.empresa = :empresa))x ) r ");

        parametros.put("campanha", idCampanha);
        parametros.put("empresa", idEmpresa);

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }

    public Integer pesquisarQuantidadeTelefonePorCampanha(Long idEmpresa, Long idCampanha) {
        // TODO Auto-generated method stub
        Map<String, Object> parametros = new HashMap<>();
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT Sum(x.qtd) ");
        sql.append("FROM   (SELECT Count(DISTINCT t1) AS qtd ");
        sql.append("        FROM   PUBLIC.telefone t1 ");
        sql.append("               INNER JOIN PUBLIC.atendimento a ");
        sql.append("                       ON a.id = t1.atendimento ");
        sql.append("               INNER JOIN campanha c ");
        sql.append("                       ON a.campanha = c.id ");
        sql.append("        WHERE  a.empresa = :empresa ");
        sql.append("               AND c.id = :campanha ");
        sql.append("               AND ( c.consulta_saque IS NULL ");
        sql.append("                      OR c.consulta_saque = false ");
        sql.append("                      OR ( c.consulta_saque = true ");
        sql.append("                           AND a.valor_max_operacao >= COALESCE(c.valor_saque, 1) ");
        sql.append("                           AND a.valor_max_operacao <= COALESCE(c.valor_saque_maximo,10000000) ");
        sql.append("                         )  ");
        sql.append("                    )       ");
        sql.append("        GROUP  BY t1.id ) x");

        parametros.put("empresa", idEmpresa);
        parametros.put("campanha", idCampanha);

        BigDecimal retorno = (BigDecimal) searchEntidade(DaoEnum.NATIVE_OBJECT, sql.toString(), parametros);

        if (retorno != null)
            return Integer.valueOf(retorno.intValue());

        return Integer.valueOf(0);
    }

    public List<?> pesquisarQuantidadeStatusAtendimentoPorCampanha(Long idEmpresa, Long idCampanha, Date dataInicio,

                                                                   Date dataFim, Long[] listStatusSelect) {

        Map<String, Object> parametros = new HashMap<>();

        StringBuilder query = new StringBuilder();

        query.append("select a.descricao, ");
        query.append(" \t   a.quantidade, ");
        query.append("\t   coalesce((cast(a.quantidade as numeric) / cast(b.total as numeric)), 0) as porcentagem ");
        query.append("from ");
        query.append("\t(select b.campanha, b.descricao, sum(b.quantidade) as quantidade ");
        query.append("     from ( ");
        query.append("         select a.campanha as campanha, ");
        query.append("                sa.descricao as descricao, ");
        query.append("                count(a.id) as quantidade ");
        query.append("         from public.atendimento a ");
        query.append("             join public.status_atendimento sa on sa.id = a.status ");
        query.append("             join public.campanha c on c.id = a.campanha ");
        query.append("         where a.campanha = :campanha ");

        if (listStatusSelect != null && listStatusSelect.length > 0) {
            query.append("           and sa.id in (:status) ");
            parametros.put("status", Arrays.asList(listStatusSelect));
        }

        query.append("           and a.empresa = :empresa ");

        if (dataInicio != null) {
            query.append("           and date(a.data_alteracao) >= date(:inicio) ");
            parametros.put("inicio", dataInicio);
        }

        if (dataFim != null) {
            query.append("           and date(a.data_alteracao) <= date(:fim) ");
            parametros.put("fim", dataFim);
        }
        query.append("\tand (                                  \t\t\t\t\t\t\t\t\t\t");
        query.append("\t      c.consulta_saque is null or c.consulta_saque = false or      \t\t\t");
        query.append("\t      (                                \t\t\t\t\t\t  \t\t\t\t");
        query.append("\t        c.consulta_saque = true        \t\t\t\t\t\t  \t\t\t\t");
        query.append("\t        and a.valor_max_operacao >= coalesce(c.valor_saque,1) \t\t\t\t");
        query.append("\t        and a.valor_max_operacao <= coalesce(c.valor_saque_maximo,10000000) ");
        query.append("\t      )                                \t\t\t\t\t\t\t\t\t\t");
        query.append("\t    )                                  \t\t\t\t\t\t\t\t\t\t");

        /*
         * query.append("         union all ");
         *
         * query.append("         select a.campanha as campanha, ");
         * query.append("                sa.descricao as descricao, ");
         * query.append("                count(a.id) as quantidade ");
         * query.append("         from backup.atendimento a "); query.
         * append("             join public.status_atendimento sa on sa.id = a.status "
         * ); query.append("             join public.campanha c on c.id = a.campanha ");
         * query.append("         where a.campanha = :campanha ");
         *
         * if (listStatusSelect != null && listStatusSelect.length > 0) {
         *
         * query.append("           and sa.id in (:status) "); parametros.put("status",
         * Arrays.asList(listStatusSelect));
         *
         * }
         *
         * query.append("           and a.empresa = :empresa ");
         *
         * if (dataInicio != null)
         * query.append("           and date(a.data_alteracao) >= date(:inicio) ");
         *
         * if (dataFim != null)
         * query.append("           and date(a.data_alteracao) <= date(:fim) ");
         *
         * query.append("\tand (                                  \t\t\t\t\t\t\t\t\t\t"
         * ); query.
         * append("\t      c.consulta_saque is null or c.consulta_saque = false or      \t\t\t"
         * ); query.
         * append("\t      (                                \t\t\t\t\t\t  \t\t\t\t");
         * query.
         * append("\t        c.consulta_saque = true        \t\t\t\t\t\t  \t\t\t\t");
         * query.
         * append("\t        and a.valor_max_operacao >= coalesce(c.valor_saque,1) \t\t\t\t"
         * ); query.
         * append("\t        and a.valor_max_operacao <= coalesce(c.valor_saque_maximo,10000000) "
         * );
         * query.append("\t      )                                \t\t\t\t\t\t\t\t\t\t"
         * );
         */

        query.append("         group by a.campanha, sa.descricao) b ");
        query.append("      group by b.campanha, b.descricao ");

        query.append("     ) a ");

        query.append(" JOIN ");

        query.append("\t(select x.campanha, ");
        query.append("    \t\tsum(x.total) as total ");
        query.append("     from ( ");
        query.append("    \t select a.campanha as campanha, ");
        query.append("\t\t        count(distinct a.id) as total ");
        query.append("         from public.atendimento a ");
        query.append("         where a.campanha = :campanha ");
        query.append("           and a.empresa = :empresa ");
        query.append("           and a.status is not null ");

        /*
         * query.append("         union all ");
         *
         * query.append("         select a.campanha as campanha, ");
         * query.append("\t\t        count(distinct a.id) as total ");
         * query.append("         from backup.atendimento a ");
         * query.append("         where a.campanha = :campanha ");
         * query.append("           and a.empresa = :empresa ");
         * query.append("           and a.status is not null ");
         */

        query.append("         group by a.campanha) x ");

        query.append("      group by x.campanha ) b on b.campanha = a.campanha ");

        parametros.put("campanha", idCampanha);
        parametros.put("empresa", idEmpresa);
        // System.out.println(query.toString());
        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }

    public List<?> pesquisarQuantidadeFinalizadosPorDia(Long idCampanha, Date dataInicio, Date dataFim) {

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("campanha", idCampanha);
        StringBuilder query = new StringBuilder();
        query.append("select   ");
        query.append("\tb.data,  ");
        query.append("\tcount(b.id) as realizados  ");
        query.append("from   ");
        query.append("\t(\tselect a.id, to_char(a.data_alteracao, 'MM/yyyy') as data from atendimento a ");
        query.append("\t\tINNER JOIN status_atendimento s ON s.id = a.status  ");
        query.append("\t\tINNER JOIN campanha c ON c.id = a.campanha ");
        query.append("\t\twhere  ");
        query.append("\t\tc.id = :campanha ");

        if (dataInicio != null) {

            query.append("\t\tAND date(a.data_alteracao) >= date(:inicio) ");
            parametros.put("inicio", dataInicio);

        }

        if (dataFim != null) {

            query.append("\t\tAND date(a.data_alteracao) <= date(:fim) ");
            parametros.put("fim", dataFim);

        }

        query.append(
                "\t\tAND s.acao in ('PROPOSTA_EFETIVADA', 'PROPOSTA_PARCIAL', 'CONCLUIR', 'CONTATO', 'SEM_ACAO', 'BLOQUEAR_CPF')  ");
        query.append("\tand (                                  \t\t\t\t\t\t\t\t\t\t");
        query.append("\t      c.consulta_saque is null or c.consulta_saque = false or      \t\t\t");
        query.append("\t      (                                \t\t\t\t\t\t  \t\t\t\t");
        query.append("\t        c.consulta_saque = true        \t\t\t\t\t\t  \t\t\t\t");
        query.append("\t        and a.valor_max_operacao >= coalesce(c.valor_saque,1) \t\t\t\t");
        query.append("\t        and a.valor_max_operacao <= coalesce(c.valor_saque_maximo,10000000) ");
        query.append("\t      )                                \t\t\t\t\t\t\t\t\t\t");
        query.append("\t    )                                  \t\t\t\t\t\t\t\t\t\t");

        /*
         * query.append("\t\tunion all "); query.
         * append("\t\tselect c.id, to_char(c.data_alteracao, 'MM/yyyy') as data from backup.atendimento c "
         * );
         * query.append("\t\tINNER JOIN status_atendimento s1 ON s1.id = c.status  ");
         * query.append("\t\tINNER JOIN campanha cc ON cc.id = c.campanha ");
         * query.append("\t\twhere  "); query.append("\t\tcc.id = :campanha "); query.
         * append("\t\tAND s1.acao in ('PROPOSTA_EFETIVADA', 'PROPOSTA_PARCIAL', 'CONCLUIR', 'CONTATO', 'SEM_ACAO', 'BLOQUEAR_CPF')  "
         * );
         *
         *
         * if (inicio != null) {
         *
         * query.append("\t\tAND date(c.data_alteracao) >= date(:inicio) ");
         * parametros.put("inicio", inicio); }
         *
         * if (fim != null) {
         *
         * query.append("\t\tAND date(c.data_alteracao) <= date(:fim) ");
         * parametros.put("fim", fim); }
         * query.append("\tand (                                  \t\t\t\t\t\t\t\t\t\t "
         * ); query.
         * append("\t      cc.consulta_saque is null or cc.consulta_saque = false or      \t\t "
         * ); query.
         * append("\t      (                                \t\t\t\t\t\t  \t\t\t\t ");
         * query.
         * append("\t        cc.consulta_saque = true        \t\t\t\t\t\t  \t\t\t ");
         * query.
         * append("\t        and c.valor_max_operacao >= coalesce(cc.valor_saque,1) \t\t\t\t "
         * ); query.
         * append("\t        and c.valor_max_operacao <= coalesce(cc.valor_saque_maximo,10000000) "
         * );
         * query.append("\t      )                                \t\t\t\t\t\t\t\t\t\t "
         * ); query.append("\t ) \t\t\t\t\t\t\t\t\t\t ");
         */
        query.append("\t) b ");

        query.append("group by data ");
        query.append("order by concat(substring(data from 4 for 7),substring(data from 1 for 2)) desc ");

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }

    public List<?> pesquisarQuantidadeStatusTelefonePorCampanha(Long idEmpresa, Long idCampanha, Date dataInicio,
                                                                Date dataFim, Long[] listStatusSelect) {

        Map<String, Object> parametros = new HashMap<>();
        StringBuilder query = new StringBuilder();
        query.append("select a.descricao, ");
        query.append(" \t   a.quantidade ");
        query.append("from ");
        query.append("\t(select b.campanha, b.descricao, sum(b.quantidade) as quantidade ");
        query.append("     from ( ");
        query.append("         select a.campanha as campanha, ");
        query.append("                st.descricao as descricao, ");
        query.append("                count(a.id) as quantidade ");
        query.append("         from public.atendimento a ");
        query.append("             join public.campanha c on c.id = a.campanha ");
        query.append("             join public.telefone t on a.id = t.atendimento ");
        query.append("             join public.status_telefone st on st.id = t.status_telefone ");
        query.append("             join public.status_atendimento sa on sa.id = a.status      ");
        query.append("         where c.id = :campanha ");

        if (listStatusSelect != null && listStatusSelect.length > 0) {

            query.append("           and sa.id in (:status) ");
            parametros.put("status", Arrays.asList(listStatusSelect));

        }

        query.append("           and a.empresa = :empresa ");

        if (dataInicio != null) {

            query.append("           and date(a.data_alteracao) >= date(:inicio) ");
            parametros.put("inicio", dataInicio);

        }

        if (dataFim != null) {

            query.append("           and date(a.data_alteracao) <= date(:fim) ");
            parametros.put("fim", dataFim);

        }

        query.append("\tand (                                  \t\t\t\t\t\t\t\t\t\t");
        query.append("\t      c.consulta_saque is null or c.consulta_saque = false or      \t\t\t");
        query.append("\t      (                                \t\t\t\t\t\t  \t\t\t\t");
        query.append("\t        c.consulta_saque = true        \t\t\t\t\t\t  \t\t\t\t");
        query.append("\t        and a.valor_max_operacao >= coalesce(c.valor_saque,1) \t\t\t\t");
        query.append("\t        and a.valor_max_operacao <= coalesce(c.valor_saque_maximo,10000000) ");
        query.append("\t      )                                \t\t\t\t\t\t\t\t\t\t");
        query.append("\t    )                                  \t\t\t\t\t\t\t\t\t\t");
        query.append("         group by a.campanha, st.descricao ");

        /*
         * query.append("         union all ");
         * query.append("         select a.campanha as campanha, ");
         * query.append("                st.descricao as descricao, ");
         * query.append("                count(a.id) as quantidade ");
         * query.append("         from backup.atendimento a ");
         * query.append("             join public.campanha c on c.id = a.campanha ");
         * query.append("             join backup.telefone t on t.atendimento = a.id ");
         * query.
         * append("             join public.status_telefone st on st.id = t.status ");
         * query.
         * append("             join public.status_atendimento sa on sa.id = a.status      "
         * ); query.append("         where c.id = :campanha ");
         *
         * if (listStatusSelect != null && listStatusSelect.length > 0) {
         *
         * query.append("           and sa.id in (:status) "); parametros.put("status",
         * Arrays.asList(listStatusSelect));
         *
         * }
         *
         * query.append("           and a.empresa = :empresa ");
         *
         * if (dataInicio != null)
         * query.append("           and date(a.data_alteracao) >= date(:inicio) ");
         *
         * if (dataFim != null)
         * query.append("           and date(a.data_alteracao) <= date(:fim) ");
         *
         * query.append("\tand (                                  \t\t\t\t\t\t\t\t\t\t"
         * ); query.
         * append("\t      c.consulta_saque is null or c.consulta_saque = false or      \t\t\t"
         * ); query.
         * append("\t      (                                \t\t\t\t\t\t  \t\t\t\t");
         * query.
         * append("\t        c.consulta_saque = true        \t\t\t\t\t\t  \t\t\t\t");
         * query.
         * append("\t        and a.valor_max_operacao >= coalesce(c.valor_saque,1) \t\t\t\t"
         * ); query.
         * append("\t        and a.valor_max_operacao <= coalesce(c.valor_saque_maximo,10000000) "
         * );
         * query.append("\t      )                                \t\t\t\t\t\t\t\t\t\t"
         * );
         * query.append("\t    )                                  \t\t\t\t\t\t\t\t\t\t"
         * );
         */
        query.append("         ) b ");
        query.append("      group by b.campanha, b.descricao ");
        query.append("     ) a ");

        parametros.put("campanha", idCampanha);
        parametros.put("empresa", idEmpresa);

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }

    public List<?> pesquisarMonitorProducao(Long idEmpresa, Usuario usuario, PeriodoEnum periodo) {

        Map<String, Object> parametros = new HashMap<>();
        StringBuilder query = new StringBuilder();

        query.append("select ");
        query.append("\tu.nome, ");
        query.append("\tCOALESCE(count(a.id), 0) as qtde_atendimento, ");
        query.append("\tCOALESCE(sum(a.valor_liberado), 0) as valor_total ");
        query.append("from propostas_realizadas a ");
        query.append("\tjoin campanha c on (a.campanha = c.id) ");
        query.append("\tjoin usuario u on (a.usuario_alteracao = u.id) ");
        query.append("\tjoin status_atendimento s on (a.status = s.id) ");
        query.append("\tjoin empresa e on a.empresa = e.id ");
        query.append("where (e.id = :empresa or e.matriz = :empresa) ");

        if (periodo.equals(PeriodoEnum.DIARIO)) {

            query.append("\tand date(a.data_alteracao) = date(:data) ");
            parametros.put("data", new Date(System.currentTimeMillis()));

        } else {

            query.append("\tand date(a.data_alteracao) between date(:inicial) and date(:final) ");
            parametros.put("inicial", DateUtil.builder().retornarDataPrimeiroDiaMes().getData());
            parametros.put("final", DateUtil.builder().retornarDataUltimoDiaMes().getData());

        }

        query.append("  and a.status is not null ");
        query.append("\tand s.acao = 'PROPOSTA_EFETIVADA' ");

        if (usuario != null && usuario.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

            query.append("  and a.equipe in ( ");
            query.append("\t\tselect distinct es.equipe ");
            query.append("\t\tfrom equipe_supervisor es ");
            query.append("\t\twhere es.supervisor = :supervisor) ");
            parametros.put("supervisor", usuario.getId());

        }

        query.append("group by u.nome ");
        query.append("order by valor_total desc, qtde_atendimento, u.nome ");
        parametros.put("empresa", Long.valueOf(idEmpresa));

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }

    public List<?> pesquisarMonitorProjecaoAtendimento(Date dataInicio, Date dataFim, Usuario usuario, Empresa empresa,
                                                       Meta meta) {

        Map<String, Object> parametros = new HashMap<>();

        StringBuilder query = new StringBuilder();
        query.append("select u.nome, ");
        query.append("\tsum(case when ");
        query.append("\t\ts.acao = '" + AcaoStatusAtendimentoEnum.AGENDAR + "' ");
        query.append(" \t\t\tor s.acao = '" + AcaoStatusAtendimentoEnum.AGENDAR_GLOBAL + "' ");
        query.append(" \t\t\tor s.acao = '" + AcaoStatusAtendimentoEnum.AGENDAR_DUAS_HORAS + "' ");
        query.append(" \t\t\tor s.acao = '" + AcaoStatusAtendimentoEnum.AGENDAR_QUATRO_HORAS + "' ");
        query.append(" \t\t\tor s.acao = '" + AcaoStatusAtendimentoEnum.AGENDAR_SEIS_HORAS + "' ");
        query.append("\t\tthen 1 else 0 end) agendamentos, ");
        query.append(
                "\tsum(case when s.acao = '" + AcaoStatusAtendimentoEnum.FIM_FILA + "' then 1 else 0 end) fim_fila, ");
        query.append("\tsum(case when s.acao = '" + AcaoStatusAtendimentoEnum.PROPOSTA_EFETIVADA
                + "' then 1 else 0 end) proposta_efetivada, ");
        query.append("\tsum(case when ");
        query.append("\t\ts.acao = '" + AcaoStatusAtendimentoEnum.CONCLUIR + "' ");
        query.append("\t\t\tor s.acao = '" + AcaoStatusAtendimentoEnum.CONTATO + "' ");
        query.append("\t\t\tor s.acao = '" + AcaoStatusAtendimentoEnum.SEM_ACAO + "' ");
        query.append("\t\t\tor s.acao = '" + AcaoStatusAtendimentoEnum.BLOQUEAR_CPF + "' ");
        query.append("\t\tthen 1 else 0 end) outros, ");
        query.append("\tcount(a.id) as total_atendimento, ");
        query.append(
                "\tcast (((360*(date(:dataFinal) - date(:dataInicial)+1)) - sum((EXTRACT(EPOCH FROM a.data_fim_atendimento - a.data_inicio_atendimento))/60))/:tma + count(a.id) as int) projecao, ");
        query.append(
                "\tcast (cast((sum((EXTRACT(EPOCH FROM a.data_fim_atendimento - a.data_inicio_atendimento)))/ count(a.id)) as int) * INTERVAL '1 second' as varchar ) media_atendimento ");
        query.append("from atendimento a ");
        query.append("\tjoin usuario u on a.usuario_alteracao = u.id ");
        query.append("\tjoin status_atendimento s on a.status = s.id ");
        query.append("\tjoin empresa e on a.empresa = e.id ");
        query.append("where a.empresa = :empresa ");
        query.append("\tand date(a.data_alteracao) >= date(:dataInicial) ");
        query.append("\tand date(a.data_alteracao) <= date(:dataFinal) ");
        query.append("\tand a.status is not null ");
        query.append("  and a.data_inicio_atendimento is not null ");
        query.append("\tand a.data_fim_atendimento is not null ");

        if (usuario != null && usuario.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

            query.append("  and a.equipe in ( ");
            query.append("\t  select distinct es.equipe ");
            query.append("\t  from equipe_supervisor es ");
            query.append("\t\twhere es.supervisor = :supervisor) ");
            parametros.put("supervisor", usuario.getId());

        }

        query.append("group by u.nome ");
        query.append("order by u.nome ");
        parametros.put("tma",
                Double.valueOf((meta == null || meta.getTma() == null || meta.getTma().doubleValue() < 1.0D) ? 10.0D
                        : meta.getTma().doubleValue()));
        parametros.put("empresa", empresa);
        parametros.put("dataInicial", dataInicio);
        parametros.put("dataFinal", dataFim);

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }

    public List<Object[]> pesquisarQuantidadeMonitorDiscagem(Long campanhaLong, Long equipeLong,
                                                             Long statusCampanhaLong, Long usuarioLong, Long empresaLong, Date dataInicio, Date dataFim) {

        Map<String, Object> parametros = new HashMap<>();
        StringBuilder query = new StringBuilder();
        StringBuilder where = new StringBuilder();

        if (campanhaLong != null) {

            where.append("\tand a.campanha = :campanha ");
            parametros.put("campanha", campanhaLong);

        }

        if (dataInicio != null) {

            where.append("\tand date(a.data_alteracao) >= date(:inicio) ");
            parametros.put("inicio", dataInicio);

        }

        if (dataFim != null) {

            where.append("\tand date(a.data_alteracao) <= date(:fim) ");
            parametros.put("fim", dataFim);

        }

        query.append("select c.descricao as campanha,  ");
        query.append("       sum(y.total) as total, ");
        query.append("       sum(y.discado) discado, ");
        query.append("       sum(y.nao_discado) nao_discado, ");
        query.append("       u.nome as usuario,  ");
        query.append("       sum(y.usuario_discado) as usuario_discado, ");
        query.append("       sum(y.usuario_nao_discado) as usuario_nao_discado ");
        query.append("from ( ");
        query.append("    select a.campanha,  ");
        query.append("           count(a.id) as total, ");
        query.append("           sum(case when a.discou then 1 else 0 end) discado, ");
        query.append("           sum(case when (a.discou is null or not a.discou) then 1 else 0 end) nao_discado, ");
        query.append("           x.usuario_alteracao,  ");
        query.append("           x.usuario_discado, ");
        query.append("           x.usuario_nao_discado ");
        query.append("    from public.atendimento a ");
        query.append("        join ( ");
        query.append("            select a.campanha,  ");
        query.append("                   a.usuario_alteracao, ");
        query.append("                   sum(case when a.discou then 1 else 0 end) usuario_discado, ");
        query.append(
                "                   sum(case when (a.discou is null or not a.discou) then 1 else 0 end) usuario_nao_discado ");
        query.append("            from public.atendimento a ");
        query.append("            where a.status is not null ");
        query.append("                and a.empresa = :empresa ");
        query.append(where.toString());
        query.append("            group by a.campanha, a.usuario_alteracao) x on a.campanha = x.campanha ");
        query.append("   where a.status is not null ");
        query.append("     and a.empresa = :empresa ");
        query.append(where.toString());
        query.append("    group by a.campanha, x.usuario_alteracao, x.usuario_discado, x.usuario_nao_discado ");

        query.append("   ) y  ");
        query.append("    \tjoin campanha c on y.campanha = c.id  ");
        query.append("    \tjoin usuario u on y.usuario_alteracao = u.id  ");

        if (usuarioLong != null || equipeLong != null)
            query.append("\tjoin campanha_equipe ce on ce.campanha = y.campanha and u.equipe = ce.equipe ");

        if (usuarioLong != null)
            query.append("\tjoin equipe_supervisor es on es.equipe = ce.equipe ");
        query.append("\t  where 1 = 1 ");

        if (statusCampanhaLong != null) {
            query.append("\t\tand c.status_campanha = :statusCampanha ");
            parametros.put("statusCampanha", statusCampanhaLong);
        }

        if (usuarioLong != null) {
            query.append("\t\tand es.supervisor = :supervisor ");
            parametros.put("supervisor", usuarioLong);
        }

        if (equipeLong != null) {
            query.append("\t\tand ce.equipe = :equipe ");
            parametros.put("equipe", equipeLong);
        }

        query.append("group by c.descricao, u.nome  ");
        query.append("order by c.descricao, u.nome  ");
        parametros.put("empresa", empresaLong);

        return extrairResultadoMonitorDiscagem(searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros));

    }

    @SuppressWarnings("rawtypes")
    private List<Object[]> extrairResultadoMonitorDiscagem(List<Object[]> resultados) {

        List<Object[]> listaRelatorio = new ArrayList();

        if (!resultados.isEmpty()) {

            String campanha = ((Object[]) resultados.get(0))[0].toString();
            List<Object[]> listaSubRelatorio = new ArrayList();
            Object[] relatorio = new Object[5];
            relatorio[0] = ((Object[]) resultados.get(0))[0];
            relatorio[1] = ((Object[]) resultados.get(0))[1];
            relatorio[2] = ((Object[]) resultados.get(0))[2];
            relatorio[3] = ((Object[]) resultados.get(0))[3];
            relatorio[4] = listaSubRelatorio;
            listaRelatorio.add(relatorio);
            for (int index = 0; index < resultados.size(); index++) {
                Object[] resultado = resultados.get(index);
                if (campanha.equals(resultado[0].toString())) {
                    Object[] subRelatorio = new Object[3];
                    subRelatorio[0] = resultado[4];
                    subRelatorio[1] = resultado[5];
                    subRelatorio[2] = resultado[6];
                    listaSubRelatorio.add(subRelatorio);
                } else {
                    listaSubRelatorio = new ArrayList();
                    relatorio = new Object[5];
                    relatorio[0] = ((Object[]) resultados.get(index))[0];
                    relatorio[1] = ((Object[]) resultados.get(index))[1];
                    relatorio[2] = ((Object[]) resultados.get(index))[2];
                    relatorio[3] = ((Object[]) resultados.get(index))[3];
                    relatorio[4] = listaSubRelatorio;
                    listaRelatorio.add(relatorio);
                    Object[] subRelatorio = new Object[3];
                    subRelatorio[0] = resultado[4];
                    subRelatorio[1] = resultado[5];
                    subRelatorio[2] = resultado[6];
                    listaSubRelatorio.add(subRelatorio);
                    campanha = resultado[0].toString();
                }
            }
        }
        return listaRelatorio;
    }

    public List<?> pesquisarProdutividadeAtendimento(List<Long> campanhaLongs, Long statusCampanhaLong, TipoCampanhaEnum tipoCampanha, List<Long> equipeLong, List<Long> usuarioLong, Long idEmpresa, Long produtoLong, Usuario usurioLogado, Date dataInicio, Date dataFim, TipoVisualizacaoEnum tipoVisualizacao) {

        Map<String, Object> parametros = new HashMap<>();
        StringBuilder where = new StringBuilder();

        if (CollectionUtils.isNotEmpty(usuarioLong)) {
            where.append("\tand h.usuario in (:usuario) ");
            parametros.put("usuario", usuarioLong);
        }
        if (CollectionUtils.isNotEmpty(equipeLong)) {
            where.append("\tand h.usuario in (select id from usuario where equipe in (:equipe)) ");
            parametros.put("equipe", equipeLong);
        }
        if (produtoLong != null) {
            where.append("\tand a.produto = :produto ");
            parametros.put("produto", produtoLong);
        }

        if (CollectionUtils.isNotEmpty(campanhaLongs)) {
            where.append(" and c.id in  (:campanha) ");
            parametros.put("campanha", campanhaLongs);
        }
        if (statusCampanhaLong != null) {
            where.append("\tand c.status_campanha = :statusCampanha ");
            parametros.put("statusCampanha", statusCampanhaLong);
        }
        if (tipoCampanha != null) {
            where.append("\tand c.tipo_campanha = :tipoCampanha ");
            parametros.put("tipoCampanha", tipoCampanha.name());
        }

        String field = "";
        String join = "";

        if (TipoVisualizacaoEnum.CAMPANHA.equals(tipoVisualizacao)) {

            field = "c.descricao ";

        } else if (TipoVisualizacaoEnum.EQUIPE.equals(tipoVisualizacao)) {

            field = "e.nome ";
            join = "join equipe e on a.equipe = e.id ";

        } else if (TipoVisualizacaoEnum.USUARIO.equals(tipoVisualizacao)) {

            field = "u.nome ";
            join = " join usuario u on h.usuario = u.id ";

        } else if (TipoVisualizacaoEnum.PRODUTO.equals(tipoVisualizacao)) {

            field = "p.descricao ";
            join = "left join produto p on a.produto = p.id ";
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
        query.append("\tfrom ( ");
        query.append("\t\tselect " + field + " as visualizacao, ");
        query.append("\t\t\t   count(distinct a.cpf) as qtde_cpf, ");
        query.append("\t\t\t   count(distinct h.id) as qtde_atendimento, ");
        query.append("\t\t\t   sum(case when s.acao = 'PROPOSTA_EFETIVADA' then 1 else 0 end) as qtde_proposta, ");
        query.append("\t\t\t   sum(case when (s.acao = 'PROPOSTA_EFETIVADA' and sc.acao = 'CONCLUIDA') then 1 else 0 end) as qtde_contrato, ");
        query.append("\t\t\t   coalesce(sum(case when s.acao = 'PROPOSTA_EFETIVADA' then a.valor_liberado else 0 end), 0) as valor_total_vendido, ");
        query.append("\t\t\t   coalesce(sum(case when (s.acao = 'PROPOSTA_EFETIVADA' and sc.acao = 'CONCLUIDA') then a.valor_liberado else 0 end), 0) as valor_total_pago ");
        query.append("\tfrom public.historico_atendimento h ");
        query.append("\t\tjoin public.atendimento a on h.atendimento = a.id ");
        query.append("\t\tjoin campanha c on a.campanha = c.id ");
        query.append("\t\tjoin status_atendimento s on h.status_atendimento = s.id ");
        query.append("\t\tleft join contrato ct on a.contrato = ct.id ");
        query.append("\t\tleft join status_contrato sc on ct.status_contrato = sc.id ");
        query.append("\t\tjoin empresa em on a.empresa = em.id ");
        query.append(join);
        query.append("\twhere date(h.data_cadastro) between date(:dataInicial) and date(:dataFinal) ");
        query.append("\t \tand (em.id = :empresa or em.matriz = :empresa) ");
        query.append(where.toString());
        query.append("\tgroup by " + field);

        query.append(") a ");
        query.append(" GROUP BY a.visualizacao ");
        query.append(" ORDER BY a.visualizacao ");
        parametros.put("dataInicial", dataInicio);
        parametros.put("dataFinal", dataFim);
        parametros.put("empresa", idEmpresa);

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }

    public Map<String, Object[]> pesquisarDashBoardAtendimento(Long idEmpresa, Long[] arrayEquipesSelecionadas,
                                                               Long[] arrayUsuariosSelecionados, Date dateInicial, Date dataFinal) {


        Map<String, Object> parametros = new HashMap<>();

        parametros.put("empresa", idEmpresa);
        parametros.put("dataInicial", dateInicial);
        parametros.put("dataFinal", dataFinal);

        StringBuilder where = new StringBuilder();

        where.append("where a.empresa = :empresa ");
        where.append("\tand date(a.data_alteracao) between date(:dataInicial) and date(:dataFinal) ");

        if (arrayEquipesSelecionadas != null && arrayEquipesSelecionadas.length > 0) {

            where.append("\tand a.equipe in (:equipes) ");

            parametros.put("equipes", Arrays.asList(arrayEquipesSelecionadas));
        }

        if (arrayUsuariosSelecionados != null && arrayUsuariosSelecionados.length > 0) {

            where.append("\tand a.usuario_alteracao in (:usuarios) ");

            parametros.put("usuarios", Arrays.asList(arrayUsuariosSelecionados));
        }

        StringBuilder query = new StringBuilder();
        query.append("(select date(a.data_alteracao) as data, ");
        query.append("\t     count(a.id) as total_atendimento, ");
        query.append("       sum(case when s.acao = 'PROPOSTA_EFETIVADA' then 1 else 0 end) as total_proposta ");
        query.append("from atendimento a ");
        query.append("\tjoin status_atendimento s on a.status = s.id ");
        query.append(where);
        query.append("group by date(a.data_alteracao) ");
        query.append("order by date(a.data_alteracao)) ");

        List<Object[]> resultados = searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

        Map<String, Object[]> listaAgrupada = new HashMap<>();

        resultados.stream().forEach(a -> {
            listaAgrupada.put(DateUtil.builder((Date) a[0]).formatarDataParaString("dd/MM/yyyy").getDataTexto(), a);
        });

        return listaAgrupada;
    }

    public Atendimento pesquisarAtendimentoValidacaoPreditivo(Long idAtendimento) {

        StringBuilder query = new StringBuilder();
        query.append("select a ");
        query.append("from Atendimento a ");
        query.append("\tleft join fetch a.status sa ");
        query.append("where a.id = :id ");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("id", idAtendimento);

        return (Atendimento) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

    public String pesquisarMaiorInformacoesComplementares(Long campanha) {

        StringBuilder query = new StringBuilder();
        query.append("select informacoes_complementares, max(length(informacoes_complementares)) as tamanho ");
        query.append("from atendimento ");
        query.append("where informacoes_complementares is not null ");
        query.append("\tand campanha = :campanha ");
        query.append("group by informacoes_complementares ");
        query.append("order by tamanho desc ");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("campanha", campanha);
        Object[] resultado = (Object[]) searchEntidade(DaoEnum.NATIVE_OBJECT, query.toString(), parametros,
                Integer.valueOf(0), Integer.valueOf(1));

        if (resultado == null)
            return null;

        return (String) resultado[0];
    }

    public List<?> pesquisarAtendimentosPorCampanhaPlan(Long campanha) {

        StringBuilder query = new StringBuilder();
        query.append("select distinct ");
        query.append("\t\ta.id as id, ");
        query.append("\t\ta.data_cadastro as data_cadastro, ");
        query.append("\t\ta.nome as cliente, ");
        query.append("\t    a.cpf as cpf, ");
        query.append("\t\tto_char(a.data_nascimento, 'DD/MM/YYYY') as nascimento, ");
        query.append("\t\ta.numero_documento as documento, ");
        query.append("\t\ta.orgao_doc as orgao_documento, ");
        query.append("\t\ta.nome_mae as nome_mae, ");
        query.append("\t\ts.descricao as status, ");
        query.append("\t\tu.nome as usuario, ");

        query.append("\t\tp.descricao as produto, ");
        query.append("\t\ta.beneficio_principal, ");
        query.append("\t\ta.beneficio_secundario, ");
        query.append("\t\ta.entidade_principal as entidade_principal, ");
        query.append("\t\ta.entidade_secundaria as entidade_secundaria, ");
        query.append("\t\ta.orgao_principal as orgao_principal, ");
        query.append("\t\ta.orgao_secundario as orgao_secundario, ");
        query.append("\t\tcast(a.margem as varchar), ");
        query.append("\t\tcast(a.salario_cliente as varchar), ");
        query.append("\t\tcast(a.limite as varchar), ");
        query.append("\t\tcast(a.desconto_compulsorio as varchar), ");
        query.append("\t\tcast(a.desconto_facultativo as varchar), ");
        query.append("\t\tcast(a.valor_max_operacao as varchar), ");
        query.append("\t\tcast(a.seguro as varchar), ");

        query.append("\t\ta.observacao, ");
        query.append("\t\tcast(a.valor_parcela as varchar), ");
        query.append("\t\tcast(a.valor_liberado as varchar), ");
        query.append("\t\tcast(a.adesao as varchar), ");
        query.append("\t\tcast(a.taxa as varchar), ");
        query.append("\t\tcast(a.saldo_devedor as varchar), ");
        query.append("\t\tcast(a.valor_total as varchar), ");
        query.append("\t\tto_char(a.data_inicio_atendimento, 'DD/MM/YYYY HH24:MI:SS') as data_inicio, ");
        query.append("\t\tto_char(a.data_fim_atendimento, 'DD/MM/YYYY HH24:MI:SS') as data_fim, ");
        query.append("\t\tcase when a.discou then 'SIM' else 'Não' end, ");
        query.append("\t\ta.outras_informacoes, ");
        query.append("\t\tCOALESCE(CAST(rating AS TEXT), ''), ");
        query.append("\t\ta.informacoes_complementares ");
        query.append("from atendimento a ");
        query.append("\tleft join status_atendimento s on s.id = a.status ");
        query.append("\tleft join usuario u on u.id = a.usuario_alteracao ");
        query.append("\tleft join produto p on a.produto = p.id ");
        query.append("where a.campanha = :campanha ");

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("campanha", campanha);
        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);
    }

    public List<?> pesquisarAtendimentosEnvio3c(Long campanha) {

        StringBuilder query = new StringBuilder();
        query.append("select distinct ");
        query.append("\t\ta.id as id, ");
        query.append("\t\ta.nome as cliente, ");
        query.append("\t    a.cpf as cpf, ");
        query.append("\t\tto_char(a.data_nascimento, 'DD/MM/YYYY') as nascimento, ");
        query.append("\t\ta.nome_mae as nome_mae, ");
        query.append("\t\ta.beneficio_principal, ");
        query.append("\t\ta.beneficio_secundario, ");
        query.append("\t\ta.entidade_principal as entidade_principal, ");
        query.append("\t\ta.entidade_secundaria as entidade_secundaria, ");
        query.append("\t\ta.orgao_principal as orgao_principal, ");
        query.append("\t\ta.orgao_secundario as orgao_secundario, ");
        query.append("\t\tcast(a.margem as varchar), ");
        query.append("\t\ta.outras_informacoes, ");
        query.append("\t\ta.informacoes_complementares ");
        query.append("from atendimento a ");
        query.append("where a.campanha = :campanha ");

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("campanha", campanha);
        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);
    }

    public void retrabalharAtendimentosAtivo(Long idCampanha, List<Long> listIds, boolean retrabalharAtendimentos) {
        Map<String, Object> parametros = new HashMap<>();

        StringBuilder query = new StringBuilder();
        query.append("update atendimento set status = null  ");
        query.append("\tFROM(SELECT atendimento.id FROM	atendimento  ");
        query.append("\tLEFT JOIN status_atendimento ON atendimento.status = status_atendimento.id  ");
        query.append("\tLEFT JOIN campanha ON atendimento.campanha = campanha.id   ");
        query.append("\tLEFT JOIN status_campanha ON campanha.status_campanha = status_campanha.id  ");

        query.append("\twhere  ");
        query.append("\t atendimento.usuario_em_atendimento IS NULL   ");

        if (!retrabalharAtendimentos) {
            query.append("\tAND ( atendimento.status IS NULL OR status_atendimento.id IN  :ids    )  ");
            parametros.put("ids", listIds);

        } else {
            query.append(
                    "\tAND ( atendimento.status IS NULL OR status_atendimento.acao IN ( 'NONE', 'FIM_FILA' )    )  ");

        }
        query.append("\tAND ( atendimento.atender IS NULL OR atendimento.atender = 'SIM' )   ");

        query.append(
                "\t	AND status_campanha.acao = 'LIBERAR' and atendimento.campanha = :campanha) as sb WHERE atendimento.id = sb.id ");


        parametros.put("campanha", idCampanha);

        executeSqlUpdate(DaoEnum.NATIVE_CLASSE, query.toString(), parametros);

    }

    public List<Atendimento> pesquisarAtendimentosComTelefones(Campanha campanha) {

        StringBuilder query = new StringBuilder();
        query.append("select distinct a ");
        query.append("from Atendimento a ");
        query.append(" join fetch a.campanha c");
        query.append(" join fetch c.fila f");
        query.append(" join fetch a.listTelefones ");
        query.append("where c.id = :campanha ");
        query.append("order by a.id ");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("campanha", campanha.getId());
        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

    public List<Atendimento> pesquisarAtendimentosFimFilaComTelefones(Campanha campanha, List<Long> idsStatus, boolean retrabalharFimFila) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        query.append("select distinct a ");
        query.append("from Atendimento a ");
        query.append(" join fetch a.campanha c");
        query.append(" left join fetch a.status s");
        query.append(" join fetch c.fila f");
        query.append(" join fetch a.listTelefones ");
        query.append("where c.id = :campanha ");

        if (!retrabalharFimFila) {

            query.append("  and  a.status.id in :ids   ");
            parametros.put("ids", idsStatus);

        } else {

            query.append("  and (a.status is null or s.acao in ( 'NONE', 'FIM_FILA' ) )  ");
        }

        if (Boolean.TRUE.equals(campanha.getConsultaSaque())) {

            if (campanha.getValorSaque() != null && campanha.getValorSaqueMaximo() != null) {

                query.append("\tand a.valorMaxOperacao >= :valorSaqueMin and a.valorMaxOperacao <= :valorSaqueMax ");
                parametros.put("valorSaqueMin", campanha.getValorSaque());
                parametros.put("valorSaqueMax", campanha.getValorSaqueMaximo());

            } else if (campanha.getValorSaque() != null) {

                query.append("\tand a.valorMaxOperacao >= :valorSaque ");
                parametros.put("valorSaque", campanha.getValorSaque());

            } else if (campanha.getValorSaqueMaximo() != null) {

                query.append("\tand a.valorMaxOperacao <= :valorSaque ");
                parametros.put("valorSaque", campanha.getValorSaqueMaximo());

            }

        } else if (Boolean.TRUE.equals(campanha.getConsultaSeguro())) {

            if (campanha.getValorSaque() != null && campanha.getValorSaqueMaximo() != null) {

                query.append("\tand a.seguro >= :valorSaqueMin and a.seguro <= :valorSaqueMax ");
                parametros.put("valorSaqueMin", campanha.getValorSaque());
                parametros.put("valorSaqueMax", campanha.getValorSaqueMaximo());

            } else if (campanha.getValorSaque() != null) {

                query.append("\tand a.seguro >= :valorSaque ");
                parametros.put("valorSaque", campanha.getValorSaque());

            } else if (campanha.getValorSaqueMaximo() != null) {

                query.append("\tand a.seguro <= :valorSaque ");
                parametros.put("valorSaque", campanha.getValorSaqueMaximo());

            }

        }

        query.append("    order by a.id ");

        parametros.put("campanha", campanha.getId());

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }


    public List<Atendimento> pesquisarAtendimentosFimFilaComTelefones(Campanha campanha, List<Long> idsStatus, List<Long> importacaoes, boolean retrabalharFimFila) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        query.append("select distinct a ");
        query.append("from Atendimento a ");
        query.append(" join fetch a.campanha c");
        query.append(" left join fetch a.status s");
        query.append(" left join fetch c.fila f");
        query.append(" join fetch a.listTelefones ");
        query.append("where c.id = :campanha ");

        if (!retrabalharFimFila) {

            query.append("  and  a.status.id in :ids   ");
            parametros.put("ids", idsStatus);

        } else {

            query.append("  and (a.status is null or s.acao in ( 'NONE', 'FIM_FILA' ) )  ");
        }

        if (Boolean.TRUE.equals(campanha.getConsultaSaque())) {

            if (campanha.getValorSaque() != null && campanha.getValorSaqueMaximo() != null) {

                query.append("\tand a.valorMaxOperacao >= :valorSaqueMin and a.valorMaxOperacao <= :valorSaqueMax ");
                parametros.put("valorSaqueMin", campanha.getValorSaque());
                parametros.put("valorSaqueMax", campanha.getValorSaqueMaximo());

            } else if (campanha.getValorSaque() != null) {

                query.append("\tand a.valorMaxOperacao >= :valorSaque ");
                parametros.put("valorSaque", campanha.getValorSaque());

            } else if (campanha.getValorSaqueMaximo() != null) {

                query.append("\tand a.valorMaxOperacao <= :valorSaque ");
                parametros.put("valorSaque", campanha.getValorSaqueMaximo());

            }

        } else if (Boolean.TRUE.equals(campanha.getConsultaSeguro())) {

            if (campanha.getValorSaque() != null && campanha.getValorSaqueMaximo() != null) {

                query.append("\tand a.seguro >= :valorSaqueMin and a.seguro <= :valorSaqueMax ");
                parametros.put("valorSaqueMin", campanha.getValorSaque());
                parametros.put("valorSaqueMax", campanha.getValorSaqueMaximo());

            } else if (campanha.getValorSaque() != null) {

                query.append("\tand a.seguro >= :valorSaque ");
                parametros.put("valorSaque", campanha.getValorSaque());

            } else if (campanha.getValorSaqueMaximo() != null) {

                query.append("\tand a.seguro <= :valorSaque ");
                parametros.put("valorSaque", campanha.getValorSaqueMaximo());

            }

        }

        if (CollectionUtils.isNotEmpty(importacaoes)) {

            query.append("\tand a.importacao in  " + sqlFormatedList(importacaoes));

            importacaoes.forEach(System.out::println);

        }

        query.append("    order by a.id ");

        parametros.put("campanha", campanha.getId());

        System.out.println(query.toString());


        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }


    public List<Long> pesquisarAtendimentosPorCampanha(Campanha campanha) {

        Map<String, Object> parametros = new HashMap<>();
        StringBuilder query = new StringBuilder();

        query.append("select atendimento.id ");
        query.append("from atendimento ");

        query.append("where atendimento.campanha = :campanha ");

        parametros.put("campanha", campanha.getId());

        List<Long> listIds = Utils.converterLong(searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros));

        return listIds;

    }

    public List<Object[]> pesquisarRelatorioAgendamentoDiarioOperador(Long idCampanha, Long idStatusCampanha,
                                                                      TipoCampanhaEnum tipoCampanha, Long usuario, Long statusAtendimento, Date dataInicio, Date dataFim,
                                                                      Long idEmpresa) {

        Map<String, Object> parametros = new HashMap<>();

        StringBuilder where = new StringBuilder();

        if (idCampanha != null) {

            where.append("\t\tand a.campanha = :campanha ");

            parametros.put("campanha", idCampanha);

        }

        if (idStatusCampanha != null) {

            where.append("\t\tand camp.status_campanha = :statusCampanha ");

            parametros.put("statusCampanha", idStatusCampanha);
        }

        if (tipoCampanha != null) {

            where.append("\t\tand camp.tipo_campanha = :tipoCampanha ");

            parametros.put("tipoCampanha", tipoCampanha.name());

        }

        if (usuario != null) {

            where.append("\t\tand u.id = :usuario ");

            parametros.put("usuario", usuario);
        }

        if (statusAtendimento != null) {

            where.append("\t\tand s.id = :statusAtendimento ");

            parametros.put("statusAtendimento", statusAtendimento);

        }

        if (dataInicio != null && dataFim != null) {

            where.append("\t\tand date(h.data_cadastro) between date(:dataInicial) and date(:dataFinal) ");

            parametros.put("dataInicial", dataInicio);

            parametros.put("dataFinal", dataFim);

        } else if (dataInicio != null) {

            where.append("\t\tand date(h.data_cadastro) >= date(:dataInicial) ");
            parametros.put("dataInicial", dataInicio);

        } else if (dataFim != null) {

            where.append("\t\tand date(h.data_cadastro) <= date(:dataFinal) ");

            parametros.put("dataFinal", dataFim);
        }

        StringBuilder query = new StringBuilder();
        query.append("select a.data, ");
        query.append("\t     a.usuario, ");
        query.append("       a.tipo_campanha, ");
        query.append("       sum(a.total_geral) as total_geral, ");
        query.append("       a.status_atendimento, ");
        query.append("       sum(a.total_status) as total_status, ");
        query.append(
                "       round(cast(sum(a.total_status) as numeric) / cast(sum(a.total_geral) as numeric) * 100, 2) as porcentagem ");
        query.append("from ( ");
        query.append("select distinct a.data, ");
        query.append("\t     a.usuario, ");
        query.append("       a.total_geral, ");
        query.append("       b.status_atendimento, ");
        query.append("       b.total_status, ");
        query.append("       a.tipo_campanha ");
        query.append("from ( ");
        query.append("\t  select distinct to_char(h.data_cadastro, 'DD/MM/YYYY') as data,  ");
        query.append("\t\t\t u.nome as usuario, ");
        query.append("\t\t\t u.id as codigo, ");
        query.append("\t\t\t count(h.id) as total_geral, ");
        query.append("\t\t\t camp.tipo_campanha as tipo_campanha ");
        query.append("\t  from public.historico_atendimento h ");
        query.append("\t\t  join usuario u on (h.usuario = u.id) ");
        query.append("\t\t  join status_atendimento s on (h.status_atendimento = s.id) ");
        query.append("        join public.atendimento a on h.atendimento = a.id ");
        query.append("\t\t  join public.empresa e on (a.empresa = e.id) ");
        query.append("\t\t  join public.campanha camp on (a.campanha = camp.id) ");
        query.append("\t  where s.acao like 'AGENDAR%' ");
        query.append("\t\tand (e.id = :empresa or e.matriz = :empresa) ");
        query.append(where);
        query.append("\t  group by to_char(h.data_cadastro, 'DD/MM/YYYY'), u.nome, u.id, camp.tipo_campanha) a ");
        query.append("join ( ");
        query.append("\t  select distinct to_char(h.data_cadastro, 'DD/MM/YYYY') as data,  ");
        query.append("\t\t\t u.id as id,  ");
        query.append("\t\t\t s.descricao as status_atendimento,  ");
        query.append("\t\t\t count(h.id) as total_status ");
        query.append("\t  from public.historico_atendimento h ");
        query.append("\t\t  join usuario u on (h.usuario = u.id) ");
        query.append("\t\t  join status_atendimento s on (h.status_atendimento = s.id) ");
        query.append("        join public.atendimento a on h.atendimento = a.id ");
        query.append("\t\t  join public.empresa e on (a.empresa = e.id) ");
        query.append("\t\t  join campanha camp on a.campanha = camp.id ");
        query.append("\t  where s.acao like 'AGENDAR%' ");
        query.append("\t\tand (e.id = :empresa or e.matriz = :empresa) ");
        query.append(where);
        query.append("\t  group by to_char(h.data_cadastro, 'DD/MM/YYYY'), u.id, s.descricao) b on (a.data = b.data and a.codigo = b.id) ");

        /*
         * query.append("union all "); query.append("select distinct a.data, ");
         * query.append("\t     a.usuario, "); query.append("       a.total_geral, ");
         * query.append("       b.status_atendimento, ");
         * query.append("       b.total_status, ");
         * query.append("       a.tipo_campanha "); query.append("from ( ");
         * query.append("\t  select to_char(h.data_cadastro, 'DD/MM/YYYY') as data,  ");
         * query.append("\t\t\t u.nome as usuario,  ");
         * query.append("\t\t\t u.id as id,  ");
         * query.append("\t\t\t count(h.id) as total_geral, ");
         * query.append("\t\t\t camp.tipo_campanha as tipo_campanha ");
         * query.append("\t  from backup.historico h ");
         * query.append("\t\t  join usuario u on (h.usuario = u.id) ");
         * query.append("\t\t  join status_atendimento s on (h.status = s.id) ");
         * query.append("        join backup.atendimento a on h.atendimento = a.id " );
         * query.append("\t\t  join public.campanha camp on (a.campanha = camp.id) " );
         * query.append("\t  where s.acao like 'AGENDAR%' ");
         * query.append("\t\tand a.empresa = :empresa "); query.append(where); query.
         * append("\t  group by to_char(h.data_cadastro, 'DD/MM/YYYY'), u.nome, u.id,camp.tipo_campanha) a "
         * ); query.append("join ( "); query.
         * append("\t  select distinct to_char(h.data_cadastro, 'DD/MM/YYYY') as data,  "
         * ); query.append("\t\t\t u.id as id,  ");
         * query.append("\t\t\t s.descricao as status_atendimento,  ");
         * query.append("\t\t\t count(h.id) as total_status ");
         * query.append("\t  from backup.historico h ");
         * query.append("\t\t  join usuario u on (h.usuario = u.id) ");
         * query.append("\t\t  join status_atendimento s on (h.status = s.id) ");
         * query.append("        join backup.atendimento a on h.atendimento = a.id " );
         * query.append("\t\t  join campanha camp on a.campanha = camp.id ");
         * query.append("\t  where s.acao like 'AGENDAR%' ");
         * query.append("\t\tand a.empresa = :empresa "); query.append(where); query.
         * append("\t  group by to_char(h.data_cadastro, 'DD/MM/YYYY'), u.id, s.descricao) b on (a.data = b.data and a.id = b.id) "
         * );
         */

        query.append(") a group by a.data, a.usuario, a.status_atendimento, a.tipo_campanha ");
        query.append("order by to_date(a.data, 'DD/MM/YYYY'), a.usuario,a.tipo_campanha, a.status_atendimento ");
        parametros.put("empresa", idEmpresa);


        return extrairResultadoRelatorioData(searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros));
    }

    @SuppressWarnings("rawtypes")
    private List<Object[]> extrairResultadoRelatorioData(List<Object[]> resultados) {

        List<Object[]> listaRelatorio = new ArrayList();

        if (!resultados.isEmpty()) {

            String dataResultado = ((Object[]) resultados.get(0))[0].toString();
            String usuarioResultado = ((Object[]) resultados.get(0))[1].toString();
            String tipoCampanhaResultado = ((Object[]) resultados.get(0))[2].toString();

            List<Object[]> listaSubRelatorio = new ArrayList();

            Object[] relatorio = new Object[5];

            relatorio[0] = ((Object[]) resultados.get(0))[0];
            relatorio[1] = ((Object[]) resultados.get(0))[1];
            relatorio[2] = ((Object[]) resultados.get(0))[2];
            relatorio[3] = ((Object[]) resultados.get(0))[3];
            relatorio[4] = listaSubRelatorio;
            listaRelatorio.add(relatorio);

            for (int index = 0; index < resultados.size(); index++) {

                Object[] resultado = resultados.get(index);

                if (dataResultado.equals(resultado[0].toString()) && usuarioResultado.equals(resultado[1].toString())
                        && tipoCampanhaResultado.equals(resultado[2].toString())) {

                    Object[] subRelatorio = new Object[3];
                    subRelatorio[0] = resultado[4];
                    subRelatorio[1] = resultado[5];
                    subRelatorio[2] = resultado[6];
                    listaSubRelatorio.add(subRelatorio);

                } else {

                    listaSubRelatorio = new ArrayList();
                    relatorio = new Object[5];
                    relatorio[0] = resultado[0];
                    relatorio[1] = resultado[1];
                    relatorio[2] = resultado[2];
                    relatorio[3] = resultado[3];
                    relatorio[4] = listaSubRelatorio;
                    listaRelatorio.add(relatorio);
                    Object[] subRelatorio = new Object[3];
                    subRelatorio[0] = resultado[4];
                    subRelatorio[1] = resultado[5];
                    subRelatorio[2] = resultado[6];
                    listaSubRelatorio.add(subRelatorio);
                    dataResultado = resultado[0].toString();
                    usuarioResultado = resultado[1].toString();
                    tipoCampanhaResultado = resultado[2].toString();
                }

            }

        }

        return listaRelatorio;
    }

    public void atualizarAtendimentoProposta(Long idAtendimento, String adesao, Long idUsuario, String observacao) {

        String query = "update atendimento set adesao = :adesao, usuario_alteracao = :usuarioAlteracao, data_alteracao = :data, observacao = :observacao where id = :id";
        HashMap<String, Object> parametros = new HashMap<>();

        parametros.put("id", idAtendimento);
        parametros.put("adesao", adesao);
        parametros.put("usuarioAlteracao", idUsuario);
        parametros.put("data", new Date(System.currentTimeMillis()));
        parametros.put("observacao", observacao);
        executarSql(DaoEnum.NATIVE_OBJECT, query, parametros);

    }

    public List<Object[]> pesquisarRelatorioStatusAtendimentoOperador(Long idCampanha, Long idStatusCampanha,
                                                                      TipoCampanhaEnum tipoCampanha, Long idEquipe, Long idUsuario, Date dataInicial, Date dataFinal,
                                                                      Long idEmpresa) {

        Map<String, Object> parametros = new HashMap<>();
        StringBuilder where = new StringBuilder();
        where.append("\t  where (e.id = :empresa or e.matriz = :empresa) ");
        parametros.put("empresa", idEmpresa);

        if (idCampanha != null) {
            where.append("\t\tand a.campanha = :campanha ");
            parametros.put("campanha", idCampanha);
        }

        if (idStatusCampanha != null) {
            where.append("\t\tand c.status_campanha = :statusCampanha ");
            parametros.put("statusCampanha", idStatusCampanha);
        }

        if (tipoCampanha != null) {
            where.append("\t\tand c.tipo_campanha = :tipoCampanha ");
            parametros.put("tipoCampanha", tipoCampanha.name());
        }

        if (idEquipe != null) {
            where.append("\t\tand u.equipe = :equipe ");
            parametros.put("equipe", idEquipe);
        }

        if (idUsuario != null) {
            where.append("\t\tand u.id = :usuario ");
            parametros.put("usuario", idUsuario);
        }

        if (dataInicial != null && dataFinal != null) {

            where.append("\t\tand date(h.data_cadastro) between date(:dataInicial) and date(:dataFinal) ");
            parametros.put("dataInicial", dataInicial);
            parametros.put("dataFinal", dataFinal);

        } else if (dataInicial != null) {

            where.append("\t\tand date(h.data_cadastro) >= date(:dataInicial) ");
            parametros.put("dataInicial", dataInicial);

        } else if (dataFinal != null) {

            where.append("\t\tand date(h.data_cadastro) <= date(:dataFinal) ");
            parametros.put("dataFinal", dataFinal);

        }

        StringBuilder query = new StringBuilder();
        query.append("select a.nome, ");
        query.append("\t   \t a.tipo_campanha, ");
        query.append("       sum(a.total_geral) as total_geral, ");
        query.append("\t     round(coalesce(sum(a.total_status_proposta) / sum(a.total_geral) * 100, 0), 2) as taxa_conversao, ");
        query.append("       sum(a.total_status_proposta) as total_status_proposta, ");
        query.append("       sum(a.total_status_agendamento) as total_status_agendamento, ");
        query.append("       sum(a.alos) as alos, ");
        query.append("       sum(a.cpf_trabalhado) as cpf_trabalhado, ");
        query.append("       b.status_atendimento, ");
        query.append("       sum(b.total_status) as total_status,  ");
        query.append("       round(cast(sum(b.total_status) as numeric) / cast(sum(a.total_geral) as numeric) * 100, 2) as porcentagem, ");
        query.append("       round(case when sum(a.total_geral) <> 0 then (cast (sum(a.alos) AS NUMERIC) / cast(sum(a.total_geral) AS NUMERIC)) else 0 end, 2) as cpc  ");
        query.append("from ( ");
        query.append("    select h.nome, ");
        query.append("    h.tipo_campanha as tipo_campanha, ");
        query.append("    count(distinct h.historico) as total_geral,  ");
        query.append("    sum(case when h.acao_status_atendimento = 'PROPOSTA_EFETIVADA' then 1 else 0 end) total_status_proposta, ");
        query.append("    sum(case when h.acao_status_atendimento like 'AGENDAR%' then 1 else 0 end) total_status_agendamento, ");
        query.append("    coalesce(sum(h.alos), 0) alos, ");
        query.append("    count(distinct h.atendimento) cpf_trabalhado ");
        query.append("    from (  ");
        query.append("        select distinct  ");
        query.append("        \t   h.id as historico,  ");
        query.append("        \t   u.nome, ");
        query.append("             c.tipo_campanha, ");
        query.append("\t\t\t   a.id as atendimento, ");
        query.append("        \t   s.acao as acao_status_atendimento, ");
        query.append("             x.alos ");
        query.append("\tfrom public.historico_atendimento h  ");
        query.append("\t\t  join status_atendimento s on (h.status_atendimento = s.id) ");
        query.append("\t\t  join public.atendimento a on h.atendimento = a.id ");
        query.append("\t\t  join campanha c on (a.campanha = c.id) ");
        query.append("        join empresa e on (a.empresa = e.id) ");
        query.append("        join usuario u on h.usuario = u.id ");
        query.append("\t\t  left join ( ");
        query.append("\t\t  select h.atendimento, ");
        query.append("\t\t  sum(case when st.parametro = any ('{CONTATO_CLIENTE, CONTATO_TERCEIRO, CONTATO_NAO_LOCALIZADO}') then 1 else 0 end) as alos ");
        query.append("\t\t  from public.historico_atendimento h ");
        query.append("\t\t  join public.atendimento a on h.atendimento = a.id ");
        query.append("\t\t  join campanha c on (a.campanha = c.id) ");
        query.append("\t\t  join public.telefone t on t.atendimento = a.id ");
        query.append("\t\t  join status_telefone st on t.status_telefone = st.id ");
        query.append("\t\t  join empresa e on a.empresa = e.id ");
        query.append("        JOIN usuario u ON h.usuario = u.id ");
        query.append(where);
        query.append("        group by h.atendimento) x on x.atendimento = a.id ");

        query.append(where);

        /*
         * query.append("        union all ");
         * query.append("        select distinct  ");
         * query.append("        \t\th.id as historico,  ");
         * query.append("        \t\tu.nome, ");
         * query.append("              c.tipo_campanha, ");
         * query.append("\t\t\t\ta.id as atendimento, ");
         * query.append("\t\t\t\ts.acao as acao_status_atendimento, ");
         * query.append("\t\t\t\tx.alos  ");
         * query.append("\t\t  from historico_atendimento h  ");
         * query.append("\t\t    join status_atendimento s on (h.status = s.id)  ");
         * query.append("\t\t  \tjoin atendimento a on h.atendimento = a.id ");
         * query.append("        \tjoin campanha c on (a.campanha= c.id) ");
         * query.append("          join empresa e on (a.empresa = e.id) ");
         * query.append("\t\t  \tjoin usuario u on h.usuario = u.id ");
         * query.append("\t\t  \tleft join ( ");
         * query.append("\t\t  \tselect h.atendimento,  "); query.append(
         * "\t\t  \tsum(case when st.parametro = any ('{CONTATO_CLIENTE, CONTATO_TERCEIRO, CONTATO_NAO_LOCALIZADO}') then 1 else 0 end) as alos "
         * ); query.append("\t\t  \tfrom historico_atendimento h ");
         * query.append("\t\t  \tjoin atendimento a on h.atendimento = a.id ");
         * query.append("\t\t    join campanha c on (a.campanha = c.id) ");
         * query.append("\t\t  \tjoin telefone t on t.atendimento = a.id ");
         * query.append("\t\t  \tjoin status_telefone st on t.status_telefone = st.id "
         * ); query.append("\t\t  \tjoin empresa e on a.empresa = e.id ");
         * query.append("        JOIN usuario u ON h.usuario = u.id ");
         * query.append(where);
         * query.append("        group by h.atendimento) x on x.atendimento = a.id ");
         */
        // query.append(where);
        query.append("    ) h ");

        query.append(" group by h.nome, h.tipo_campanha) a  ");
        query.append("      join (  ");
        query.append("      select h.nome, ");
        query.append("\t\th.descricao as status_atendimento,   ");
        query.append("\t\th.tipo_campanha as tipo_campanha, ");
        query.append("\t\tcount(distinct h.id) as total_status ");
        query.append("\t\tfrom (   ");
        query.append("\t\t  select h.id, ");
        query.append("\t\t  u.nome, ");
        query.append("        c.tipo_campanha, ");
        query.append("\t\t  s.descricao ");
        query.append("\t\t  from historico_atendimento h  ");
        query.append("\t  join status_atendimento s on (h.status_atendimento = s.id)  ");
        query.append("\t  join public.atendimento a on h.atendimento = a.id  ");
        query.append("    join campanha c on (a.campanha = c.id) ");
        query.append("    join empresa e on (a.empresa = e.id)  ");
        query.append("\t  join usuario u on h.usuario = u.id  ");
        query.append(where);
        /*
         * query.append("\t  union all "); query.append("\t    select h.id, ");
         * query.append("\t    u.nome, "); query.append("\t\tc.tipo_campanha, ");
         * query.append("\t\ts.descricao "); query.append("        from historico h  ");
         * query.
         * append("\t\t  join status_atendimento s on (h.status_atendimento = s.id)  ");
         * query.append("\t\t  join atendimento a on h.atendimento = a.id  ");
         * query.append("\t\t  join campanha c on (a.campanha = c.id)   ");
         * query.append("\t\t  join empresa e on (a.empresa = e.id)  ");
         * query.append("\t\t  join usuario u on h.usuario = u.id   ");
         * query.append(where);
         */
        query.append("\t) h ");
        query.append("\tgroup by h.nome, h.descricao, h.tipo_campanha) b on (a.nome = b.nome and a.tipo_campanha = b.tipo_campanha) ");
        query.append(" group by a.nome, b.status_atendimento, a.tipo_campanha  ");
        query.append(" order by a.nome, a.tipo_campanha, b.status_atendimento ");


        return extrairResultadoRelatorio(searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros));

    }


    public List<Object[]> pesquisarRelatorioStatusAtendimentoData(Long idCampanha, Long idStatusCampanha,
                                                                  TipoCampanhaEnum tipoCampanha, Long idEquipe, Long idUsuario, Date dataInicial, Date dataFinal,
                                                                  Long idEmpresa) {

        Map<String, Object> parametros = new HashMap<>();

        StringBuilder where = new StringBuilder();

        where.append("\t  where (e.id = :empresa or e.matriz = :empresa) ");
        parametros.put("empresa", idEmpresa);

        if (idCampanha != null) {
            where.append("\t\tand a.campanha = :campanha ");
            parametros.put("campanha", idCampanha);
        }

        if (idStatusCampanha != null) {
            where.append("\t\tand c.status_campanha = :statusCampanha ");
            parametros.put("statusCampanha", idStatusCampanha);
        }

        if (tipoCampanha != null) {
            where.append("\t\tand c.tipo_campanha = :tipoCampanha ");
            parametros.put("tipoCampanha", tipoCampanha.name());
        }

        if (idEquipe != null) {
            where.append("\t\tand u.equipe = :equipe ");
            parametros.put("equipe", idEquipe);
        }

        if (idUsuario != null) {
            where.append("\t\tand u.id = :usuario ");
            parametros.put("usuario", idUsuario);
        }
        if (dataInicial != null && dataFinal != null) {
            where.append("\t\tand date(h.data_cadastro) between date(:dataInicial) and date(:dataFinal) ");
            parametros.put("dataInicial", dataInicial);
            parametros.put("dataFinal", dataFinal);

        } else if (dataInicial != null) {
            where.append("\t\tand date(h.data_cadastro) >= date(:dataInicial) ");
            parametros.put("dataInicial", dataInicial);

        } else if (dataFinal != null) {

            where.append("\t\tand date(h.data_cadastro) <= date(:dataFinal) ");
            parametros.put("dataFinal", dataFinal);
        }

        StringBuilder query = new StringBuilder();
        query.append("select a.data, ");
        query.append("       a.tipo_campanha, ");
        query.append("       sum(a.total_geral) as total_geral, ");
        query.append("\t     round(coalesce(sum(a.total_status_proposta) / sum(a.total_geral) * 100, 0), 2) as taxa_conversao, ");
        query.append("       sum(a.total_status_proposta) as total_status_proposta,  ");
        query.append("       sum(a.total_status_agendamento) as total_status_agendamento, ");
        query.append("       sum(a.alos) as alos,  ");
        query.append("       sum(a.cpf_trabalhado) as cpf_trabalhado,    ");
        query.append("       b.status_atendimento, ");
        query.append("       sum(b.total_status) as total_status, ");
        query.append("      round(cast(sum(b.total_status) as numeric) / cast(sum(a.total_geral) as numeric) * 100, 2) as porcentagem, ");
        query.append("       round(case when sum(a.total_geral) <> 0 then (cast (sum(a.alos) AS NUMERIC) / cast(sum(a.total_geral) AS NUMERIC)) else 0 end , 2) as cpc  ");
        query.append("from ( ");
        query.append("\t   select to_char(h.data_cadastro, 'DD/MM/YYYY') as data,   ");
        query.append("          h.tipo_campanha as tipo_campanha,  ");
        query.append("\t\t\tcount(distinct h.historico) as total_geral,  ");
        query.append("\t\t\tsum(case when h.acao_status_atendimento = 'PROPOSTA_EFETIVADA' then 1 else 0 end) total_status_proposta, ");
        query.append("\t\t\tsum(case when h.acao_status_atendimento like 'AGENDAR%' then 1 else 0 end) total_status_agendamento, ");
        query.append("          coalesce(sum(h.alos), 0) alos,");
        query.append("          count(distinct h.atendimento) cpf_trabalhado  ");
        query.append("from ( select ");
        query.append("\t  \t   distinct ");
        query.append("\t\t   h.id as historico,  ");
        query.append("\t\t   h.data_cadastro,  ");
        query.append("\t\t   c.tipo_campanha,  ");
        query.append("\t\t   a.id as atendimento, ");
        query.append("\t\t   s.acao as acao_status_atendimento,  ");
        query.append("\t\t   x.alos  ");
        query.append("from  public.historico_atendimento h ");
        query.append("\t  join status_atendimento s on (h.status_atendimento = s.id) ");
        query.append("    join public.atendimento a on h.atendimento = a.id  ");
        query.append("\t  join campanha c on (a.campanha = c.id)  ");
        query.append("\t  join empresa e on (a.empresa = e.id)  ");
        query.append("    join usuario u on h.usuario = u.id   ");
        query.append("\t  left join ( ");
        query.append("\t  select h.atendimento,   ");
        query.append("\t  sum(case when st.parametro = any ('{CONTATO_CLIENTE, CONTATO_TERCEIRO, CONTATO_NAO_LOCALIZADO}') then 1 else 0 end) as alos ");
        query.append("\t  from public.historico_atendimento h   ");
        query.append("\t  join public.atendimento a on h.atendimento = a.id   ");
        query.append("\t  join public.telefone t on t.atendimento = a.id   ");
        query.append("    JOIN campanha c ON (a.campanha= c.id) ");
        query.append("\t  join status_telefone st on t.status_telefone = st.id   ");
        query.append("\t  join empresa e on a.empresa = e.id   ");
        query.append("    JOIN usuario u ON h.usuario = u.id   ");
        query.append(where);
        query.append("\t  group by h.atendimento) x on x.atendimento = a.id   ");

        query.append(where);

        /*
         * query.append("union all "); query.append("select distinct  ");
         * query.append("       h.id as historico, ");
         * query.append("       h.data_cadastro, ");
         * query.append("       c.tipo_campanha, ");
         * query.append("       a.id as atendimento, ");
         * query.append("       s.acao as acao_status_atendimento,  ");
         * query.append("       x.alos  "); query.append("from backup.historico h  ");
         * query.append("\t  join status_atendimento s on (h.status = s.id)  ");
         * query.append("    join backup.atendimento a on h.atendimento = a.id  ");
         * query.append("\t  join campanha c on (a.campanha= c.id)  ");
         * query.append("\t  join empresa e on (a.empresa = e.id) ");
         * query.append("\t  join usuario u on h.usuario = u.id  ");
         * query.append("    left join (  ");
         * query.append("    select h.atendimento,   "); query.
         * append("     sum(case when st.parametro = any ('{CONTATO_CLIENTE, CONTATO_TERCEIRO, CONTATO_NAO_LOCALIZADO}') then 1 else 0 end) as alos  "
         * ); query.append("    from backup.historico h   ");
         * query.append("    join backup.atendimento a on h.atendimento = a.id   ");
         * query.append("    join backup.telefone t on t.atendimento = a.id  ");
         * query.append("    JOIN campanha c ON (a.campanha= c.id) ");
         * query.append("    join status_telefone st on t.status = st.id  ");
         * query.append("    join empresa e on a.empresa = e.id  ");
         * query.append("    JOIN usuario u ON h.usuario = u.id   ");
         *
         * query.append(where);
         *
         * query.append("   group by h.atendimento) x on x.atendimento = a.id  ");
         *
         * query.append(where);
         */
        query.append("   ) h  ");

        query.append("   group by to_char(h.data_cadastro, 'DD/MM/YYYY'), h.tipo_campanha) a  ");
        query.append("\t  join (  ");
        query.append("\t  select to_char(h.data_cadastro, 'DD/MM/YYYY') as data,  ");
        query.append("    h.descricao as status_atendimento, ");
        query.append("    h.tipo_campanha as tipo_campanha,  ");
        query.append("    count(distinct h.id) as total_status   ");
        query.append("    from (    ");
        query.append("     select h.id,     ");
        query.append("\t   h.data_cadastro, ");
        query.append("     c.tipo_campanha,     ");
        query.append("\t   s.descricao ");
        query.append("\t  from public.historico_atendimento h ");
        query.append("     join status_atendimento s on (h.status_atendimento = s.id)   ");
        query.append("\t  join public.atendimento a on h.atendimento = a.id    ");
        query.append("\t  join campanha c on (a.campanha = c.id)  ");
        query.append("    join empresa e on (a.empresa = e.id)  ");
        query.append("\t   join usuario u on h.usuario = u.id ");
        query.append(where);

        /*
         * query.append(" union all "); query.append("\t  select h.id, ");
         * query.append("\t  h.data_cadastro, "); query.append("\t  c.tipo_campanha, ");
         * query.append("\t  s.descricao ");
         * query.append("\t  from backup.historico h  ");
         * query.append("\t  join status_atendimento s on (h.status = s.id)  ");
         * query.append("    join backup.atendimento a on h.atendimento = a.id ");
         * query.append("\t  join campanha c on (a.campanha = c.id)  ");
         * query.append("\t  join empresa e on (a.empresa = e.id) ");
         * query.append("\t  join usuario u on h.usuario = u.id ");
         */

        // query.append(where);

        query.append("\t  ) h ");
        query.append("\t   group by to_char(h.data_cadastro, 'DD/MM/YYYY'), h.descricao, h.tipo_campanha) b on (a.data = b.data and a.tipo_campanha = b.tipo_campanha)  ");
        query.append("\t  group by a.data, b.status_atendimento, a.tipo_campanha ");
        query.append("\t  order by to_date(a.data, 'DD/MM/YYYY'), a.tipo_campanha, b.status_atendimento  ");

        // System.out.println(query.toString());

        return extrairResultadoRelatorio(searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros));


    }

    public List<?> pesquisarRelatorioTimeOutSemana(List<Long> listUsuarios, Long idSupervisor, Long idEquipe,
                                                   Long idCoordenador, Long idEmpresa, Date dataInicial, Date dataFinal) {

        Map<String, Object> parametros = new HashMap<>();

        StringBuilder query = new StringBuilder();

        query.append("select u.nome ,");
        query.append("\t retornar_mes_string(CAST(date_part('Month',h.data_cadastro) as integer)) as semana ,");
        query.append("\t avg(h.tempo_pos_atendimento)  as media_tempo_pos_atendimento , ");
        query.append("\t COUNT ( h.data_cadastro ) as total_time_out  ");
        query.append("\t from historico_atendimento as h ");
        query.append("\t INNER JOIN status_atendimento AS s ON s.ID = h.status_atendimento ");
        query.append("\t INNER JOIN usuario as u on u.id = h.time_out_operador  ");

        query.append("\t where ");
        query.append("\t upper(s.descricao) = 'ENCERRADO POR TIMEOUT' ");

        if (CollectionUtils.isNotEmpty(listUsuarios)) {

            query.append("\t and h.time_out_operador in :usuarios ");
            parametros.put("usuarios", listUsuarios);
        }


        if (dataInicial != null && dataFinal != null) {

            query.append("\t\tand date(h.data_cadastro) between date(:dataInicial) and date(:dataFinal) ");
            parametros.put("dataInicial", dataInicial);
            parametros.put("dataFinal", dataFinal);

        } else if (dataInicial != null) {

            query.append("\t\tand date(h.data_cadastro) >= date(:dataInicial) ");
            parametros.put("dataInicial", dataInicial);

        } else if (dataFinal != null) {

            query.append("\t\tand date(h.data_cadastro) <= date(:dataFinal) ");
            parametros.put("dataFinal", dataFinal);
        }

        query.append("\tGROUP BY	semana, u.nome ");

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);


    }


    public List<?> pesquisarRelatorioTimeOut(List<Long> listUsuarios, Long idSupervisor, Long idEquipe, Long idCoordenador, Long idEmpresa, String filtro, Date dataInicial, Date dataFinal) {

        Map<String, Object> parametros = new HashMap<>();

        StringBuilder query = new StringBuilder();

        query.append("select u.nome ,");

        if (filtro.equals("MES"))
            query.append("\t upper(retornar_mes_string(CAST(date_part('Month',h.data_cadastro) as integer))) AS semana,");

        else if (filtro.equals("DIA"))
            query.append("\t to_char(date_trunc('day', (h.data_cadastro )), 'DD/MM/YYYY') as semana ,");

        else if (filtro.equals("SEMANA"))
            query.append("\t date_part( 'week', h.data_cadastro ) AS semana,");

        query.append("\t avg(h.tempo_pos_atendimento)  as media_tempo_pos_atendimento , ");

        query.append("\t COUNT ( h.data_cadastro ) as total_time_out  ");

        if (filtro.equals("DIA")) {
            query.append(", date_trunc('day', (h.data_cadastro )) as da");

        }

        query.append("\t from historico_atendimento as h ");
        query.append("\t INNER JOIN status_atendimento AS s ON s.ID = h.status_atendimento ");
        query.append("\t INNER JOIN usuario as u on u.id = h.time_out_operador  ");

        query.append("\t where ");
        query.append("\t upper(s.descricao) = 'ENCERRADO POR TIMEOUT' ");

        if (CollectionUtils.isNotEmpty(listUsuarios)) {

            query.append("\t and h.time_out_operador in :usuarios ");
            parametros.put("usuarios", listUsuarios);
        }


        if (dataInicial != null && dataFinal != null) {

            query.append("\t\tand date(h.data_cadastro) between date(:dataInicial) and date(:dataFinal) ");
            parametros.put("dataInicial", dataInicial);
            parametros.put("dataFinal", dataFinal);

        } else if (dataInicial != null) {

            query.append("\t\tand date(h.data_cadastro) >= date(:dataInicial) ");
            parametros.put("dataInicial", dataInicial);

        } else if (dataFinal != null) {

            query.append("\t\tand date(h.data_cadastro) <= date(:dataFinal) ");
            parametros.put("dataFinal", dataFinal);
        }


        if (filtro.equals("DIA")) {
            query.append("\tGROUP BY	semana, u.nome,da ");
            query.append("\tORDER BY da");

        } else {
            query.append("\tGROUP BY	semana, u.nome  ");
            query.append("\tORDER BY semana");
        }


        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);


    }


    @SuppressWarnings("rawtypes")
    private List<Object[]> extrairResultadoRelatorio(List resultados) {

        List<Object[]> listaRelatorio = new ArrayList();

        if (!resultados.isEmpty()) {

            String usuarioResultado = ((Object[]) resultados.get(0))[0].toString();

            String tipoCampanha = ((Object[]) resultados.get(0))[1].toString();

            List<Object[]> listaSubRelatorio = new ArrayList();

            Object[] relatorio = new Object[10];

            relatorio[0] = ((Object[]) resultados.get(0))[0];
            relatorio[1] = ((Object[]) resultados.get(0))[1];
            relatorio[2] = ((Object[]) resultados.get(0))[2];
            relatorio[3] = ((Object[]) resultados.get(0))[3];
            relatorio[4] = ((Object[]) resultados.get(0))[4];
            relatorio[5] = ((Object[]) resultados.get(0))[5];
            relatorio[6] = ((Object[]) resultados.get(0))[6];
            relatorio[7] = ((Object[]) resultados.get(0))[7];
            relatorio[8] = ((Object[]) resultados.get(0))[11];
            relatorio[9] = listaSubRelatorio;
            listaRelatorio.add(relatorio);

            for (int index = 0; index < resultados.size(); index++) {

                Object[] resultado = (Object[]) resultados.get(index);

                if (usuarioResultado.equals(resultado[0].toString()) && tipoCampanha.equals(resultado[1].toString())) {
                    Object[] subRelatorio = new Object[3];
                    subRelatorio[0] = resultado[8];
                    subRelatorio[1] = resultado[9];
                    subRelatorio[2] = resultado[10];
                    listaSubRelatorio.add(subRelatorio);
                } else {
                    listaSubRelatorio = new ArrayList();
                    relatorio = new Object[10];
                    relatorio[0] = resultado[0];
                    relatorio[1] = resultado[1];
                    relatorio[2] = resultado[2];
                    relatorio[3] = resultado[3];
                    relatorio[4] = resultado[4];
                    relatorio[5] = resultado[5];
                    relatorio[6] = resultado[6];
                    relatorio[7] = resultado[7];
                    relatorio[8] = resultado[11];
                    relatorio[9] = listaSubRelatorio;
                    listaRelatorio.add(relatorio);
                    Object[] subRelatorio = new Object[3];
                    subRelatorio[0] = resultado[8];
                    subRelatorio[1] = resultado[9];
                    subRelatorio[2] = resultado[10];
                    listaSubRelatorio.add(subRelatorio);
                    usuarioResultado = resultado[0].toString();
                    tipoCampanha = resultado[1].toString();
                }
            }
        }

        for (Object[] objects : listaRelatorio) {

            objects[2] = Integer.valueOf(((ArrayList<Object[]>) objects[9]).stream().mapToInt(obj -> Integer.parseInt(obj[1].toString())).sum());


        }

        return listaRelatorio;
    }

    public List<Object[]> pesquisarAtendimentosPorTelefone(String numeroTelefone, Long idEmpresa) {

        StringBuilder query = new StringBuilder();

        query.append("  select a1.nome, a1.cpf, cast(t1.ddd as varchar(2))||t1.numero as telefone  ");

        query.append("  from public.atendimento a1 ");

        query.append("  \tjoin public.telefone t1 on a1.id = t1.atendimento ");

        query.append("\t\tjoin empresa e1 on a1.empresa = e1.id ");

        query.append("  where cast(t1.ddd as varchar(2))||t1.numero = :telefone ");

        query.append("\t\tand (e1.id = :empresa or e1.matriz = :empresa) ");

        query.append("order by a1.cpf, a1.nome ");

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("telefone", numeroTelefone);
        parametros.put("empresa", idEmpresa);


        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }

    public List<Object[]> pesquisarAtendimentosPorTelefone(String numeroTelefone, Long idCampanha, Long idEmpresa) {

        StringBuilder query = new StringBuilder();
        HashMap<String, Object> parametros = new HashMap<>();

        query.append("  select a1.nome, a1.cpf, cast(t1.ddd as varchar(2))||t1.numero as telefone ,a1.id  ");

        query.append("  from public.atendimento a1 ");

        query.append("  \tjoin public.telefone t1 on a1.id = t1.atendimento ");

        query.append("\t\tjoin empresa e1 on a1.empresa = e1.id ");

        query.append("  where cast(t1.ddd as varchar(2))||t1.numero = :telefone ");

        if (idCampanha != null) {

            query.append("\t\tand a1.campanha = :campanha ");
            parametros.put("campanha", idCampanha);

        }
        query.append("\t\tand (e1.id = :empresa or e1.matriz = :empresa) ");

        query.append("order by a1.cpf, a1.nome ");

        parametros.put("telefone", numeroTelefone);
        parametros.put("empresa", idEmpresa);

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }

    public Object[] pesquisarAtendimentoPorTelefone(String numeroTelefone, String cpf, Long idCampanhaPropect, Long idEmpresa) {

        HashMap<String, Object> parametros = new HashMap<>();

        StringBuilder query = new StringBuilder();

        query.append("  select a1.id, cast('public' as varchar) as esquema, a1.data_alteracao ");

        query.append("  from public.atendimento a1 ");

        query.append("  \tjoin public.telefone t1 on a1.id = t1.atendimento ");

        query.append("\t\tjoin empresa e1 on a1.empresa = e1.id ");

        query.append("  where cast(t1.ddd as varchar(2))||t1.numero = :telefone ");

        query.append("\t\tand (e1.id = :empresa or e1.matriz = :empresa) ");

        if (StringUtils.isNotBlank(cpf)) {

            query.append("\tand a1.cpf = :cpf ");
            parametros.put("cpf", StringUtils.leftPad(cpf.replaceAll("\\D+", "").replaceAll(" ", ""), 11, "0"));

        }

        if (idCampanhaPropect != null) {

            query.append("\t\tand a1.campanha = :campanha ");
            parametros.put("campanha", idCampanhaPropect);
        }


        query.append("  order by a1.data_alteracao desc ");
        parametros.put("telefone", numeroTelefone);
        parametros.put("empresa", idEmpresa);


        return (Object[]) searchEntidade(DaoEnum.NATIVE_OBJECT, query.toString(), parametros, Integer.valueOf(0), Integer.valueOf(1));
    }

    public List<?> pesquisarAtendimentosConciliarAnexo(Long idEmpresa, Boolean conciliado) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        query.append("select distinct ");
        query.append("\t\ta.id as id, ");
        query.append("\t\ta.data_cadastro as data_cadastro, ");
        query.append("\t\ta.nome as cliente, ");
        query.append("\t    a.cpf as cpf, ");
        query.append("\t\ta.adesao, ");
        query.append("\t\ts.descricao as status, ");
        query.append("\t\tu.nome as usuario, ");

        query.append("\t\tp.descricao as produto, ");
        query.append("\t\ta.entidade_principal as entidade_principal, ");
        query.append("\t\ta.entidade_secundaria as entidade_secundaria, ");
        query.append("\t\ta.orgao_principal as orgao_principal, ");
        query.append("\t\ta.orgao_secundario as orgao_secundario, ");

        query.append("\t\tcast(a.valor_max_operacao as varchar), ");
        query.append("\t\ta.valor_liberado , ");
        query.append("\t\tcast(a.seguro as varchar), ");


        query.append("\t\tto_char(a.data_inicio_atendimento, 'DD/MM/YYYY HH24:MI:SS') as data_inicio, ");
        query.append("\t\tto_char(a.data_fim_atendimento, 'DD/MM/YYYY HH24:MI:SS') as data_fim, ");

        query.append("\t\tcase when a.conciliado then 'SIM' else 'Não' end ");


        query.append("from atendimento a ");
        query.append("\tinner join conciliar_audio_anexo ca on ca.atendimento = a.id ");
        query.append("\tleft join status_atendimento s on s.id = a.status ");
        query.append("\tleft join usuario u on u.id = a.usuario_alteracao ");
        query.append("\tleft join produto p on a.produto = p.id ");

        query.append("where a.empresa = :empresa ");

        if (conciliado != null && Boolean.TRUE.equals(conciliado)) {

            query.append(" and a.conciliado =  :conciliado");
            parametros.put("conciliado", conciliado);
        }

        parametros.put("empresa", idEmpresa);
        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);
    }

    public List<?> pesquisarAtendimentosConciliarAnexo(Long idEmpresa, Loja loja, Boolean conciliado) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        query.append("select distinct ");
        query.append("\t\ta.id as id, ");
        query.append("\t    a.cpf as cpf, ");
        query.append("\t\ta.adesao, ");
        query.append("\t\tp.tipo_produto as produto, ");

        query.append("\t\tto_char(a.data_inicio_atendimento, 'DD/MM/YYYY HH24:MI:SS') as data_inicio, ");

        query.append("\t\ta.conciliado ");

        query.append("from propostas_realizadas a ");
        query.append("\tinner join conciliar_audio_anexo ca on ca.atendimento = a.id ");
        query.append("\tinner join loja l on a.loja = l.id ");
        query.append("\tleft join status_atendimento s on s.id = a.status ");
        query.append("\tleft join produto p on a.produto = p.id ");

        query.append("where a.empresa = :empresa ");
        parametros.put("empresa", idEmpresa);

        query.append(" and p.envia_gravacao = true ");


        if (conciliado != null) {

            query.append(" and a.conciliado =  :conciliado");
            parametros.put("conciliado", conciliado);
        }

        if (loja != null) {
            query.append(" and a.loja =  :loja");
            parametros.put("loja", loja.getId());
        }


        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);
    }

    public List<?> pesquisarAtendimentosConciliarAnexo(Long idEmpresa, Boolean conciliar, Date dataInicio, Date dataFim) {


        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        query.append("select distinct ");
        query.append("\t\ta.id as id, ");
        query.append("\t\ta.data_cadastro as data_cadastro, ");
        query.append("\t\ta.nome as cliente, ");
        query.append("\t    a.cpf as cpf, ");
        query.append("\t\ta.adesao, ");
        query.append("\t\ts.descricao as status, ");
        query.append("\t\tu.nome as usuario, ");

        query.append("\t\tp.descricao as produto, ");
        query.append("\t\ta.entidade_principal as entidade_principal, ");
        query.append("\t\ta.entidade_secundaria as entidade_secundaria, ");
        query.append("\t\ta.orgao_principal as orgao_principal, ");
        query.append("\t\ta.orgao_secundario as orgao_secundario, ");

        query.append("\t\tcast(a.valor_max_operacao as varchar), ");
        query.append("\t\ta.valor_liberado , ");
        query.append("\t\tcast(a.seguro as varchar), ");


        query.append("\t\tto_char(a.data_inicio_atendimento, 'DD/MM/YYYY HH24:MI:SS') as data_inicio, ");
        query.append("\t\tto_char(a.data_fim_atendimento, 'DD/MM/YYYY HH24:MI:SS') as data_fim, ");

        query.append("\t\tcase when a.conciliado then 'SIM' else 'Não' end ");


        query.append("from propostas_realizadas a ");
        query.append("\tinner join conciliar_audio_anexo ca on ca.atendimento = a.id ");
        query.append("\tleft join status_atendimento s on s.id = a.status ");
        query.append("\tleft join usuario u on u.id = a.usuario_alteracao ");
        query.append("\tleft join produto p on a.produto = p.id ");

        query.append("where a.empresa = :empresa ");

        if (conciliar != null && Boolean.TRUE.equals(conciliar)) {

            query.append(" and a.conciliado =  :conciliado");
            parametros.put("conciliado", conciliar.booleanValue());
        }

        if (dataInicio != null && dataFim != null) {

            query.append("\t\tand date(a.data_inicio_atendimento) between date(:dataInicial) and date(:dataFinal) ");
            parametros.put("dataInicial", dataInicio);
            parametros.put("dataFinal", dataFim);

        } else if (dataInicio != null) {

            query.append("\t\tand date(a.data_inicio_atendimento) >= date(:dataInicial) ");
            parametros.put("dataInicial", dataInicio);

        } else if (dataFim != null) {

            query.append("\t\tand date(a.data_inicio_atendimento) <= date(:dataFinal) ");
            parametros.put("dataFinal", dataFim);
        }

        parametros.put("empresa", idEmpresa);


        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);
    }


    public List<?> pesquisarAtendimentosConciliarAnexo(Long idEmpresa, Boolean conciliar, String adesao, String cpf, Long idProduto, Date dataInicio, Date dataFim) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        query.append("select distinct ");
        query.append("\t\ta.id as id, ");
        query.append("\t\ta.data_cadastro as data_cadastro, ");
        query.append("\t\ta.nome as cliente, ");
        query.append("\t    a.cpf as cpf, ");
        query.append("\t\ta.adesao, ");
        query.append("\t\ts.descricao as status, ");
        query.append("\t\tu.nome as usuario, ");
        query.append("\t\tp.descricao as produto, ");
        query.append("\t\ta.entidade_principal as entidade_principal, ");
        query.append("\t\ta.entidade_secundaria as entidade_secundaria, ");
        query.append("\t\ta.orgao_principal as orgao_principal, ");
        query.append("\t\ta.orgao_secundario as orgao_secundario, ");
        query.append("\t\tcast(a.valor_max_operacao as varchar), ");
        query.append("\t\ta.valor_liberado , ");
        query.append("\t\tcast(a.seguro as varchar), ");
        query.append("\t\tto_char(a.data_inicio_atendimento, 'DD/MM/YYYY HH24:MI:SS') as data_inicio, ");
        query.append("\t\tto_char(a.data_fim_atendimento, 'DD/MM/YYYY HH24:MI:SS') as data_fim, ");
        query.append("\t\tcase when a.conciliado then 'Sim' else 'Não' end ");
        query.append("from propostas_realizadas a ");
        query.append("\tinner join conciliar_audio_anexo ca on ca.atendimento = a.id ");
        query.append("\tleft join status_atendimento s on s.id = a.status ");
        query.append("\tleft join usuario u on u.id = a.usuario_alteracao ");
        query.append("\tleft join produto p on a.produto = p.id ");

        query.append("where a.empresa = :empresa ");

        if (Boolean.FALSE.equals(conciliar)) {

            query.append(" and a.conciliado =  :conciliado");
            parametros.put("conciliado", conciliar);
        }


        if (StringUtils.isNotBlank(adesao)) {
            query.append(" and a.adesao =  :adesao");
            parametros.put("adesao", adesao.trim());
        }

        if (StringUtils.isNotBlank(cpf)) {
            query.append(" and a.cpf =  :cpf");
            parametros.put("cpf", StringUtils.leftPad(cpf.trim(), 11, "0"));
        }

        if (idProduto != null) {
            query.append(" and a.produto =  :idProduto");
            parametros.put("idProduto", idProduto);

        }

        if (dataInicio != null && dataFim != null) {

            query.append("\t\tand date(a.data_inicio_atendimento) between date(:dataInicial) and date(:dataFinal) ");
            parametros.put("dataInicial", dataInicio);
            parametros.put("dataFinal", dataFim);

        } else if (dataInicio != null) {

            query.append("\t\tand date(a.data_inicio_atendimento) >= date(:dataInicial) ");
            parametros.put("dataInicial", dataInicio);

        } else if (dataFim != null) {

            query.append("\t\tand date(a.data_inicio_atendimento) <= date(:dataFinal) ");
            parametros.put("dataFinal", dataFim);
        }

        parametros.put("empresa", idEmpresa);

        return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);
    }

    public void atualizarAtendimentosConciliados(List<Long> idsAtendimentos, boolean conciliado) {

        String query = "update atendimento set conciliado = :conciliado  where id in :ids";

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("conciliado", conciliado);
        parametros.put("ids", idsAtendimentos);
        executarSql(DaoEnum.NATIVE_OBJECT, query, parametros);

    }

    public void atualizarAtendimentoConciliado(Long idAtendimento, boolean conciliado) {

        String query = "update atendimento set conciliado = :conciliado  where id = :id";

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("conciliado", conciliado);
        parametros.put("id", idAtendimento);
        executarSql(DaoEnum.NATIVE_OBJECT, query, parametros);

    }

    public BigDecimal pesquisarSpinPorCampanha(Long idCampanha) {

        String query = "SELECT  coalesce(trunc( (CAST(SUM(quantidade_discagem) as Numeric(8,2) ) /  COUNT(*) ) ,2 ),0 ) from atendimento WHERE campanha = :idCampanha";
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("idCampanha", idCampanha);
        return (BigDecimal) searchEntidade(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);
    }


    public Object[] pesquisarAnsaliseCampanha(Long campanha) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        query.append("SELECT x.descricao,");
        query.append("\t MIN ( x.valor_minimo ) AS valor_minimo ");
        query.append("\t,MAX (coalesce( x.valor_maximo, 0) ) AS valor_maximo ");
        query.append("\t,SUM ( coalesce(x.total_disponivel_saque,0) ) AS total_disponivel_saque ");
        query.append("\t,x.ticket_medio_saque ");
        query.append(" ,coalesce(cast(sum(x.qtde_proposta) as numeric) / cast(sum(x.qtde_atendimento) as numeric) , 0) as porcentagem_proposta ");
        query.append(" ,x.valor_total_vendido ");
        query.append(" ,coalesce(CAST( (SUM(x.ticket_medio_produzido)/COUNT(x.qtde_proposta)) AS NUMERIC ( 7, 2 ) ) ,0) AS ticket_medio_produzido");//7
        query.append(" ,sum(x.qtde_proposta) as qtde_proposta ");//8
        query.append(" , x.qtde_agendamento ");//9
        query.append(" ,  x.qtde_fim_fila ");//10
        query.append(" , x.efetivo ");//11
        query.append(" ,x.target ");//12
        query.append(" ,x.alo ");//13
        query.append(" ,x.qtdade_sem_interesse ");//14
        query.append(" ,coalesce(cast(sum(x.qtde_agendamento) as numeric) / cast(sum(x.qtde_atendimento) as numeric) , 0) as porcentagem_agendamento "); //15
        query.append(" ,coalesce(cast(sum(x.qtdade_sem_interesse) as numeric) / cast(sum(x.qtde_atendimento) as numeric) , 0) as porcentagem_sem_interesse ");//16
        query.append(" ,coalesce(cast(sum(x.qtde_fim_fila) as numeric) / cast(sum(x.qtde_atendimento) as numeric) , 0) as porcentagem_fim_fila ");//17
        query.append(" ,coalesce(cast(sum(x.efetivo) as numeric) / cast(sum(x.qtde_atendimento) as numeric) , 0) as porcentagem_efetivo ");//18
        query.append(" ,coalesce(cast(sum(x.target) as numeric) / cast(sum(x.qtde_atendimento) as numeric) , 0) as porcentagem_target ");
        query.append(" ,coalesce(cast(sum(x.target) as numeric) / cast(sum(x.qtde_atendimento) as numeric) , 0) as porcentagem_alo ");


        query.append("\t FROM( ");
        query.append("SELECT c.descricao, ");
        query.append(" MIN ( A.valor_max_operacao ) AS valor_minimo, ");
        query.append(" MAX ( A.valor_max_operacao ) AS valor_maximo, ");
        query.append(" SUM ( coalesce(valor_max_operacao,0) ) AS total_disponivel_saque, ");
        query.append(" CAST( ( SUM ( valor_max_operacao ) / COUNT ( valor_max_operacao ) ) AS NUMERIC ( 7, 2 ) ) AS ticket_medio_saque, ");
        query.append(" SUM(a.valor_liberado) AS Valor_Liberado, ");
        query.append(" coalesce(	CAST( (SUM(a.valor_liberado)/COUNT(a.adesao)) AS NUMERIC ( 7, 2 ) ) ,0) AS ticket_medio_produzido, ");
        query.append(" coalesce(sum(case when s.acao = 'PROPOSTA_EFETIVADA' then a.valor_liberado else 0 end), 0) as valor_total_vendido, ");
        query.append(" sum(case when s.acao = 'PROPOSTA_EFETIVADA' then 1 else 0 end) as qtde_proposta, ");
        query.append(" count(distinct a.cpf) as qtde_cpf, ");
        query.append(" count(distinct h.id) as qtde_atendimento, ");

        query.append(" sum(case when s.acao = 'AGENDAR' then 1 else 0 end) as qtde_agendamento, ");
        query.append(" sum(case when s.acao = 'FIM_FILA' then 1 else 0 end) as qtde_fim_fila, ");
        query.append(" 	sum(case when s.sem_interesse = true then 1 else 0 end) as qtdade_sem_interesse, ");
        query.append(" sum(case when s.target = true  then 1 else 0 end) as target, ");
        query.append(" sum(case when s.efetivo = true  then 1 else 0 end) as efetivo, ");
        query.append(" sum(case when s.alo = true  then 1 else 0 end) as alo ");

        query.append(" FROM ");
        query.append(" public.historico_atendimento h  ");
        query.append(" join public.atendimento a on h.atendimento = a.id ");

        query.append(" INNER JOIN campanha C ON A.campanha = C.id ");
        query.append(" INNER JOIN status_atendimento s on s.id = a.status ");
        query.append(" WHERE  ");
        query.append(" a.campanha = :campanha and (c.consulta_saque is null or c.consulta_saque = false or ( c.consulta_saque = true and a.valor_max_operacao >= coalesce(c.valor_saque,1)   ) ) ");
        query.append("  	GROUP BY C.descricao ) ");
        query.append(" x group by x.descricao,x.ticket_medio_saque,x.valor_total_vendido,x.qtde_agendamento,x.qtde_fim_fila,x.target,x.efetivo,x.alo,x.qtdade_sem_interesse,x.qtde_atendimento ");
        parametros.put("campanha", campanha);

        return (Object[]) searchEntidade(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

    }

    public Atendimento pesquisarAtendimentoPorAdesao(String adesao) {
        //	public PropostasEfetivadas pesquisarPropostasPorAdesao(String adesao) {

        StringBuilder query = new StringBuilder();
        query.append("select p ");
        query.append(" from Atendimento p  ");
        query.append(" where p.adesao = :adesao ");

        Map<String, Object> parametros = new HashedMap<String, Object>();

        parametros.put("adesao", adesao.replaceAll("\\D+", "").replaceAll(" ", ""));

        return (Atendimento) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros, Integer.valueOf(0), Integer.valueOf(1));
    }

    public void removerObservacaoValorMaximo(Long importacao, Long campanha) {

        String query = "update atendimento set observacao =  null ,valor_max_operacao =null  where importacao = :importacao and campanha = :campanha";

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("importacao", importacao);
        parametros.put("campanha", campanha);

        executarSql(DaoEnum.NATIVE_OBJECT, query, parametros);

    }

    public List<Atendimento> pesquisarAtendimentosConsulta(String cpf, Long empresa) {

        StringBuilder query = new StringBuilder();
        query.append("select distinct a ");
        query.append("from Atendimento a ");
        query.append("\t join fetch a.campanha c ");
        query.append("\t join fetch c.status sc ");
        query.append("\t left join fetch a.listPortabilidades lp ");
        query.append("where c.tipoCampanha = 'CONSULTA' ");
        query.append(" and sc.acao = 'LIBERAR' ");
        query.append(" and a.cpf = :cpf ");

        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("cpf", StringUtils.leftPad(cpf.trim(), 11, "0"));

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);

    }

    public Atendimento pesquisarAtendimentosConsulta(String cpf, SegmentoEnum segmentoEnum, Long empresa, boolean consulta) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<String, Object>();
        query.append("select distinct a ");
        query.append("from Atendimento a ");
        query.append("\t join fetch a.campanha c ");
        query.append("\t join fetch c.status sc ");
        query.append("\t  join fetch a.listPortabilidades lp ");

        query.append(" where sc.acao = 'LIBERAR' ");

        if (consulta)
            query.append(" and c.tipoCampanha = 'CONSULTA' ");

        if (segmentoEnum != null) {

            query.append(" and c.segmento = :segmento ");
            parametros.put("segmento", segmentoEnum);
        }
        query.append(" and a.cpf = :cpf ");
        query.append(" ORDER BY a.dataCadastro desc ");
        parametros.put("cpf", StringUtils.leftPad(cpf.trim(), 11, "0"));
        System.out.println(query.toString());
        return (Atendimento) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros, 0, 1);

    }

    public List<Atendimento> pesquisarAtendimentosSacPorCpf(String cpf) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        query.append("select distinct a ");
        query.append("from Atendimento a ");
        query.append(" join fetch a.campanha c ");
        query.append(" join fetch a.cliente cli ");
        query.append(" left join fetch a.status s ");
        query.append(" join fetch a.motivo m ");
        query.append(" join fetch a.subMotivo sm ");
        query.append(" join fetch a.usuarioAlteracao u ");
        query.append(" where cli.cpf = :cpf and c.tipoCampanha = 'SAC'");

        parametros.put("cpf", StringUtils.leftPad(cpf.trim(), 11, "0"));

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

    public Atendimento pesquisarAtendimentoSacPorCodigo(Long idAtendimento) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        query.append("select distinct a ");
        query.append("from Atendimento a ");
        query.append(" join fetch a.campanha c ");
        query.append(" join fetch a.cliente cli ");
        query.append(" left join fetch a.status s ");
        query.append(" left join fetch a.motivo m ");
        query.append(" left join fetch a.subMotivo sm ");
        query.append(" left join fetch a.usuarioAlteracao u ");
        query.append(" left join fetch a.responsavelN2 n2 ");
        query.append(" left join fetch a.usuarioOcupado o ");
        query.append(" left join fetch a.formaPagamento tp ");
        query.append(" left join fetch a.produto pe ");
        query.append(" left join fetch a.departamentoDerivado dp ");
        query.append(" left join fetch a.contrato ct ");
        query.append(" left join fetch ct.statusContrato sc ");
        query.append(" where a.id = :id and c.tipoCampanha = 'SAC'");


        parametros.put("id", idAtendimento);

        return (Atendimento) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }


    public List<Atendimento> pesquisarAtendimentosSacPorCliente(Long codigoCliente) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        query.append("select distinct a ");
        query.append("from Atendimento a ");
        query.append(" join fetch a.campanha c ");
        query.append(" join fetch a.cliente cli ");
        query.append(" left join fetch a.status s ");
        query.append(" left join fetch a.motivo m ");
        query.append(" left join fetch a.subMotivo sm ");
        query.append(" left join fetch a.usuarioAlteracao u ");
        query.append(" where cli.id = :codigoCliente and c.tipoCampanha = 'SAC'");

        parametros.put("codigoCliente", codigoCliente);

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

    @Transactional
    public void deletarAtendimentoSemClassificacao(Long idCliente, String protocolo) {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        sql.append("DELETE FROM atendimento ");
        sql.append("WHERE cliente = :idCliente ");
        sql.append("AND motivo IS NULL ");
        sql.append("AND submotivo IS NULL ");
        sql.append("AND protocolo_pai = :protocolo");

        parametros.put("idCliente", idCliente);
        parametros.put("protocolo", protocolo);

        executeSqlUpdate(DaoEnum.NATIVE_CLASSE, sql.toString(), parametros);
    }

    public Long buscarQuantidadeClientesAtendidosDiario(Long idUsuario, String protocoloAtual) {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        sql.append("SELECT COUNT(DISTINCT cliente) ");
        sql.append("FROM atendimento ");
        sql.append("WHERE usuario_alteracao = :idUsuario ");
        sql.append("AND DATE(data_cadastro) = CURRENT_DATE ");

        if (StringUtils.isNotBlank(protocoloAtual)) {
            sql.append("AND protocolo <> :protocolo ");
            parametros.put("protocolo", protocoloAtual);
        }

        parametros.put("idUsuario", idUsuario);

        Number resultado = (Number) searchEntidade(DaoEnum.NATIVE_OBJECT, sql.toString(), parametros);

        return resultado != null ? resultado.longValue() : 0L;
    }

    public List<Atendimento> pesquisarAtendimentosDerivados() {
        StringBuilder query = new StringBuilder();

        query.append("select distinct a ");
        query.append("from Atendimento a ");
        query.append(" join fetch a.campanha c ");
        query.append(" join fetch a.cliente cli ");
        query.append(" left join fetch a.status s ");
        query.append(" left join fetch a.motivo m ");
        query.append(" left join fetch a.subMotivo sm ");
        query.append(" left join fetch a.usuarioAlteracao u ");
        query.append(" where a.enviarN2 is not null and a.enviarN2 = true and c.tipoCampanha = 'SAC'");

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), null);
    }

    public List<Atendimento> pesquisarAtendimentosSacFiltros(String filtroProtocolo, String filtroCpf, Boolean encerrado, Long idDepartamento) {
        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        query.append("select distinct a ");
        query.append("from Atendimento a ");
        query.append(" join fetch a.campanha c ");
        query.append(" join fetch a.cliente cli ");
        query.append(" left join fetch a.status s ");
        query.append(" left join fetch a.motivo m ");
        query.append(" left join fetch a.subMotivo sm ");
        query.append(" left join fetch a.usuarioAlteracao u ");
        query.append(" left join fetch a.departamentoDerivado dpo ");
        query.append(" where a.enviarN2 is not null and a.enviarN2 = true and c.tipoCampanha = 'SAC' ");

        if (StringUtils.isNotBlank(filtroProtocolo)) {
            query.append(" and a.protocolo = :protocolo");
            parametros.put("protocolo", filtroProtocolo);
        }

        if (StringUtils.isNotBlank(filtroCpf)) {
            query.append(" and a.cpf = :cpf");
            parametros.put("cpf", StringUtils.leftPad(filtroCpf.trim(), 11, "0").replaceAll("\\D", ""));
        }

        if (idDepartamento != null) {
            query.append(" and a.departamentoDerivado.id = :dpo ");
            parametros.put("dpo", idDepartamento);
        }

        if (Boolean.FALSE.equals(encerrado)) {
            query.append(" and (a.demandaEncerrada is null or a.demandaEncerrada = false)");
        } else if (Boolean.TRUE.equals(encerrado)) {
            query.append(" and a.demandaEncerrada = true");
        }


        query.append(" order by a.dataCadastro");

        System.out.println(query.toString());

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

    public List<Atendimento> pesquisarAtendimentosSacFiltros(String filtroProtocolo, String filtroCpf, List<Long> listDpo) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        query.append("select distinct a ");
        query.append("from Atendimento a ");
        query.append(" join fetch a.campanha c ");
        query.append(" join fetch a.cliente cli ");
        query.append(" join fetch a.motivo m ");
        query.append(" join fetch a.subMotivo sm ");
        query.append(" left join fetch a.status s ");
        query.append(" left join fetch a.usuarioAlteracao u ");
        query.append(" left join fetch a.responsavelN2 n2 ");
        query.append(" left join fetch a.departamentoDerivado dpo ");
        query.append(" where c.tipoCampanha = 'SAC' ");


        if (StringUtils.isNotBlank(filtroProtocolo)) {
            query.append(" and a.protocolo = :protocolo");
            parametros.put("protocolo", filtroProtocolo);
        }

        if (StringUtils.isNotBlank(filtroCpf)) {
            query.append(" and a.cpf = :cpf");
            parametros.put("cpf", StringUtils.leftPad(filtroCpf.trim(), 11, "0").replaceAll("\\D", ""));
        }

        if (CollectionUtils.isNotEmpty(listDpo)) {
            query.append(" and a.departamentoDerivado.id in  ").append(sqlFormatedList(listDpo));

        }

        query.append(" order by a.dataCadastro");

        System.out.println(query.toString());

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

    public List<Atendimento> pesquisarAtendimentosSacFiltrosDepartamentos(String filtroProtocolo, String filtroCpf, Boolean encerrado, List<Long> departamentos, Long idDepartamento) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        query.append("select distinct a ");
        query.append("from Atendimento a ");
        query.append(" join fetch a.campanha c ");
        query.append(" join fetch a.cliente cli ");
        query.append(" left join fetch a.status s ");
        query.append(" left join fetch a.motivo m ");
        query.append(" left join fetch a.subMotivo sm ");
        query.append(" left join fetch a.usuarioAlteracao u ");
        query.append(" left join fetch a.departamentoDerivado dpo ");
        query.append(" where a.enviarN2 is not null and a.enviarN2 = true and c.tipoCampanha = 'SAC' ");

        if (StringUtils.isNotBlank(filtroProtocolo)) {

            query.append(" and a.protocolo = :protocolo");
            parametros.put("protocolo", filtroProtocolo.trim());
        }

        if (StringUtils.isNotBlank(filtroCpf)) {

            String cpfLimpo = filtroCpf.replaceAll("\\D", "");
            query.append(" and a.cpf = :cpf");
            parametros.put("cpf", cpfLimpo);
        }


        if (idDepartamento != null) {
            query.append(" and a.departamentoDerivado.id = :idDepartamento ");
            parametros.put("idDepartamento", idDepartamento);

        } else if (CollectionUtils.isNotEmpty(departamentos)) {

            query.append(" and a.departamentoDerivado.id in ").append(sqlFormatedList(departamentos));
        }

        if (Boolean.FALSE.equals(encerrado)) {
            query.append(" and (a.demandaEncerrada is null or a.demandaEncerrada = false)");
        } else if (Boolean.TRUE.equals(encerrado)) {
            query.append(" and a.demandaEncerrada = true");
        }

        query.append(" order by a.dataCadastro");

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

    public List<?> listarQuantidadeResumoDerivadosSac(Date dataInicio, Date dataFim) {

        StringBuilder sql = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        sql.append("SELECT ");
        sql.append("SUM(CASE WHEN a.demanda_encerrada = true OR s.acao LIKE 'CONCLUIR%' THEN 1 ELSE 0 END) AS qtd_finalizados, ");
        sql.append("SUM(CASE WHEN a.enviar_n2 = true OR s.acao = 'DERIVAR' THEN 1 ELSE 0 END) AS qtd_escalados_n2, ");
        sql.append("SUM(CASE WHEN a.enviar_n2 = true AND (a.status IS NULL OR s.acao <> 'CONCLUIR') THEN 1 ELSE 0 END) AS pendentes, ");
        sql.append("SUM(CASE WHEN (a.enviar_n2 IS NULL OR a.enviar_n2 = false) AND s.acao = 'CONCLUIR_N1' THEN 1 ELSE 0 END) AS qtd_finalizados_n1, ");
        sql.append("SUM(CASE WHEN a.enviar_n2 = true AND s.acao = 'CONCLUIR' THEN 1 ELSE 0 END) AS qtd_finalizados_n2 ");
        sql.append("FROM atendimento a ");
        sql.append("JOIN campanha c ON a.campanha = c.id ");
        sql.append("LEFT JOIN status_atendimento s ON a.status = s.id ");
        sql.append("WHERE c.tipo_campanha = 'SAC' ");

        if (dataInicio != null && dataFim != null) {
            sql.append("AND a.data_cadastro >= :dataInicio AND a.data_cadastro <= :dataFim ");
            parametros.put("dataInicio", atStartOfDay(dataInicio));
            parametros.put("dataFim", atEndOfDay(dataFim));
        }
        System.out.println(sql.toString());
        List<?> list = searchEntidades(DaoEnum.NATIVE_OBJECT, sql.toString(), parametros);
        return CollectionUtils.isNotEmpty(list) ? list : Collections.emptyList();


    }

    public List<Object[]> buscarQuantidadePorMotivo(Date dataInicio, Date dataFim) {

        StringBuilder sql = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        sql.append("SELECT m.descricao, m.cor, COUNT(a.id) ");
        sql.append("FROM atendimento a ");
        sql.append("JOIN campanha c ON a.campanha = c.id ");
        sql.append("JOIN motivo m ON a.motivo = m.id ");

        sql.append("WHERE c.tipo_campanha = 'SAC' ");

        if (dataInicio != null && dataFim != null) {
            sql.append("AND a.data_cadastro >= :dataInicio AND a.data_cadastro <= :dataFim ");
            parametros.put("dataInicio", atStartOfDay(dataInicio));
            parametros.put("dataFim", atEndOfDay(dataFim));
        }

        sql.append("GROUP BY m.descricao,m.cor ");

        List<Object[]> list = searchEntidades(DaoEnum.NATIVE_OBJECT, sql.toString(), parametros);
        return CollectionUtils.isNotEmpty(list) ? list : Collections.emptyList();
    }

    public List<Object[]> buscarQuantidadePorSubMotivo(Date dataInicio, Date dataFim) {

        StringBuilder sql = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        sql.append("SELECT m.descricao, m.cor, COUNT(a.id) ");
        sql.append("FROM atendimento a ");
        sql.append("JOIN campanha c ON a.campanha = c.id ");
        sql.append("JOIN submotivo sm ON a.submotivo = sm.id ");

        sql.append("WHERE c.tipo_campanha = 'SAC' ");

        if (dataInicio != null && dataFim != null) {
            sql.append("AND a.data_cadastro >= :dataInicio AND a.data_cadastro <= :dataFim ");
            parametros.put("dataInicio", atStartOfDay(dataInicio));
            parametros.put("dataFim", atEndOfDay(dataFim));
        }

        sql.append("GROUP BY sm.descricao,sm.cor ");

        List<Object[]> list = searchEntidades(DaoEnum.NATIVE_OBJECT, sql.toString(), parametros);
        return CollectionUtils.isNotEmpty(list) ? list : Collections.emptyList();
    }

    public List<Object[]> buscarTop10Usuarios(Date dataInicio, Date dataFim) {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        sql.append("SELECT u.nome, COUNT(DISTINCT a.protocolo) AS qtd_protocolos ");
        sql.append("FROM atendimento a ");
        sql.append("JOIN campanha c ON a.campanha = c.id ");
        sql.append("JOIN usuario u ON a.usuario_cadastro = u.id ");
        sql.append("WHERE c.tipo_campanha = 'SAC' ");

        if (dataInicio != null && dataFim != null) {
            sql.append("AND a.data_cadastro >= :dataInicio AND a.data_cadastro <= :dataFim ");
            parametros.put("dataInicio", atStartOfDay(dataInicio));
            parametros.put("dataFim", atEndOfDay(dataFim));
        }

        sql.append("GROUP BY u.nome ");
        sql.append("ORDER BY qtd_protocolos DESC ");
        sql.append("LIMIT 10");

        List<Object[]> list = searchEntidades(DaoEnum.NATIVE_OBJECT, sql.toString(), parametros);
        return CollectionUtils.isNotEmpty(list) ? list : Collections.emptyList();
    }


    public String buscarTmaAtendimentosSac(Date dataInicio, Date dataFim) {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        sql.append("SELECT ");
        sql.append("TO_CHAR(AVG(a.data_fim_atendimento - a.data_inicio_atendimento), 'HH24:MI:SS') AS tma_formatado ");
        sql.append("FROM atendimento a ");
        sql.append("JOIN campanha c ON a.campanha = c.id ");
        sql.append("WHERE c.tipo_campanha = 'SAC' ");
        sql.append("AND a.data_inicio_atendimento IS NOT NULL ");
        sql.append("AND a.data_fim_atendimento IS NOT NULL ");

        if (dataInicio != null && dataFim != null) {
            sql.append("AND a.data_cadastro >= :dataInicio AND a.data_cadastro <= :dataFim ");
            parametros.put("dataInicio", atStartOfDay(dataInicio));
            parametros.put("dataFim", atEndOfDay(dataFim));
        }

        // agora esperamos uma lista de Strings
        List<String> list = searchEntidades(DaoEnum.NATIVE_OBJECT, sql.toString(), parametros);

        if (CollectionUtils.isNotEmpty(list)) {
            String tma = list.get(0);
            // se vier com frações de segundos, corta
            if (tma != null && tma.contains(".")) {
                tma = tma.substring(0, tma.indexOf("."));
            }
            return tma;
        }

        return "00:00:00";
    }


    public List<Object[]> buscarQuantidadePorStatus(Date dataInicio, Date dataFim) {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        sql.append("SELECT s.descricao, COUNT(a.id) ");
        sql.append("FROM atendimento a ");
        sql.append("JOIN campanha c ON a.campanha = c.id ");
        sql.append("JOIN status_atendimento s ON a.status = s.id ");
        sql.append("WHERE c.tipo_campanha = 'SAC' ");

        if (dataInicio != null && dataFim != null) {
            sql.append("AND a.data_cadastro >= :dataInicio AND a.data_cadastro <= :dataFim ");
            parametros.put("dataInicio", atStartOfDay(dataInicio));
            parametros.put("dataFim", atEndOfDay(dataFim));
        }

        sql.append("GROUP BY s.descricao");

        List<Object[]> list = searchEntidades(DaoEnum.NATIVE_OBJECT, sql.toString(), parametros);
        return CollectionUtils.isNotEmpty(list) ? list : Collections.emptyList();
    }

    @Transactional
    public void atualizarProtocoloDataFimAtendimento(Long idCliente, String protocoloPai, Date dataFim) {

        if (idCliente == null || StringUtils.isBlank(protocoloPai) || dataFim == null) {
            return;
        }

        String sql = "UPDATE atendimento " +
                "SET data_fim_atendimento = :dataFim " +
                "WHERE cliente = :clienteId AND protocolo = :protocoloPai";

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("clienteId", idCliente);
        parametros.put("dataFim", dataFim);
        parametros.put("protocoloPai", protocoloPai);
        executeSqlUpdate(DaoEnum.NATIVE_CLASSE, sql, parametros);
    }

    public Long pesquisarIdAtendimentoSacPorProtocolo(String protocolo) {

        if (StringUtils.isBlank(protocolo)) {
            return null;
        }

        String sql = "SELECT id FROM atendimento WHERE protocolo = :protocolo";

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("protocolo", protocolo);

        Number resultado = (Number) searchEntidade(DaoEnum.NATIVE_OBJECT, sql, parametros);

        return (resultado == null) ? null : resultado.longValue();
    }

    public List<Atendimento> pesquisarAtendimentosFilhos(String protocoloPai) {

        if(StringUtils.isBlank(protocoloPai))
            return null;

        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();

        query.append("select DISTINCT  a ");
        query.append("from Atendimento a ");
        query.append(" join fetch a.campanha c ");
        query.append(" join fetch a.cliente cli ");
        query.append(" left join fetch a.status s ");
        query.append(" left join fetch a.motivo m ");
        query.append(" left join fetch a.subMotivo sm ");
        query.append(" left join fetch a.usuarioAlteracao u ");
        query.append(" left join fetch a.responsavelN2 n2 ");
        query.append(" left join fetch a.departamentoDerivado dpo ");
        query.append(" where c.tipoCampanha = 'SAC' AND a.atendimentoPai = false");

        query.append(" and a.protocoloPai = :protocoloPai");
        parametros.put("protocoloPai", protocoloPai);

        query.append(" order by a.dataCadastro");


        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);

    }
}
