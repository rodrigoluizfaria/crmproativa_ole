import com.proativaservicos.service.asynchronous.produtoseguros.*;

import java.net.URL;

public class TesteSeguro {

    public static void main(String[] args) {
        ListaPlanosSeguroReturn listaPlanosSeguro = null;

        String errorMsg = null;

        ProdutoSeguroWebService servico = null;
        try {

            PlanosSeguroParameter planos = new PlanosSeguroParameter();
            planos.setLogin("robo.52586");
            planos.setSenha("Consulta2025@");

            planos.setNumeroInternoConta(22836427);

            planos.setCodigoProdutoSeguro(1007);
            planos.setEntidade("1581");
           planos.setTipoPagamentoSeguro(0);


           // planos.setTipoPagamentoSeguro(2);
            planos.setRenda(5061.0);

            servico = (new ProdutoSeguroWebServiceServiceLocator()).getProdutoSeguroWebService(new URL("https://ws1.bmgconsig.com.br/webservices/ProdutoSeguroWebService?wsdl"));

            ((org.apache.axis.client.Stub) servico).setTimeout(10000);
            System.out.println(planos.getTipoPagamentoSeguro());
            listaPlanosSeguro = servico.listaPlanos(planos);

            System.out.println(listaPlanosSeguro.getMensagemDeErro());
            System.out.println(listaPlanosSeguro.getPlanos()[0].getNomePlano());


        }catch (Exception e) {
            // TODO: handle exception
        }



    }
}

