package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoBotImp;
import com.proativaservicos.dao.implemets.DaoCliente;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.Bot;
import com.proativaservicos.model.Cliente;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class ClienteService extends GenericProService<Cliente> {

    @Inject
    private DaoCliente dao;

    @Inject
    private EnderecoService enderecoService;

    @Inject
    private EmailService emailService;

    @Inject
    private DadosBancariosService dadosBancariosService;


    @Inject
    private AtendimentoService atendimentoService;

    @Inject
    private CartaoCreditoService cartaoCreditoService;

    @Override
    public GenericDao<Cliente> getDAO() {

        return (GenericDao<Cliente>) this.dao;
    }

    public Cliente pesquisarClienteComAtendimentosSacPorCpf(String cpf, boolean carregarListas) {

        Cliente cli = this.dao.pesquisarClienteComAtendimentosSacPorCpf(cpf);
        if (carregarListas) {
            carregarListas(cli);
        }
        return cli;
    }

    public Cliente pesquisarClienteComAtendimentosSacPorId(Long idCliente, boolean carregarListas) {

        Cliente cli = this.dao.pesquisarClienteComAtendimentosSacPorId(idCliente);

        if (carregarListas) {
            carregarListas(cli);
        }

        return cli;
    }
    public Cliente pesquisarClienteAtendimentoSacPorId(Long idCliente) {

        Cliente cli = this.dao.pesquisarClienteComAtendimentosSacPorId(idCliente);

        if (cli !=null) {
            cli.setListCartoesCreditos(
                    new ArrayList<>(this.cartaoCreditoService.pesquisarCartaoCreditoPorCliente(cli.getId()))
            );
            cli.setListEnderecos(
                    new ArrayList<>(this.enderecoService.pesquisarEnderecoPorCliente(cli.getId()))
            );
            cli.setListEmails(
                    new ArrayList<>(this.emailService.pesquisarEmailPorCliente(cli.getId()))
            );
            cli.setListDadosBancarios(this.dadosBancariosService.pesquisarDadosBancariosPorCliente(cli.getId()));
        }

        return cli;
    }

    private void carregarListas(Cliente cli) {

        if (cli != null) {

            cli.setListAtendimentos(
                    new ArrayList<>(this.atendimentoService.pesquisarAtendimentosSacPorCliente(cli.getId()))
            );

            cli.setListCartoesCreditos(
                    new ArrayList<>(this.cartaoCreditoService.pesquisarCartaoCreditoPorCliente(cli.getId()))
            );
            cli.setListEnderecos(
                    new ArrayList<>(this.enderecoService.pesquisarEnderecoPorCliente(cli.getId()))
            );
            cli.setListEmails(
                    new ArrayList<>(this.emailService.pesquisarEmailPorCliente(cli.getId()))
            );
        }

    }

    public void atualizarNomeCliente(String nome,Long idCliente) {
        this.dao.atualizarNomeCliente(nome,idCliente);
    }
}
