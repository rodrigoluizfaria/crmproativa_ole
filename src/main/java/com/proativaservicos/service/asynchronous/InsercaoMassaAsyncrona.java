package com.proativaservicos.service.asynchronous;

import com.proativaservicos.model.BlackListCpf;
import com.proativaservicos.model.Empresa;
import com.proativaservicos.model.TelefoneBlackList;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.service.BlackListService;
import com.proativaservicos.service.BlackListTelefoneService;
import jakarta.annotation.Resource;
import jakarta.ejb.*;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.UserTransaction;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@TransactionManagement(TransactionManagementType.BEAN)
@Stateless
public class InsercaoMassaAsyncrona implements Serializable {

    @Resource
    private UserTransaction transaction;

    @Inject
    private BlackListTelefoneService serviceTelefoneBlackList;

    @Inject
    private BlackListService serviceCpfBlackList;

    public void inserirTelefonesListaNegra(List<String> listTelefones, Empresa empresaMatriz) {

        try {

            System.out.println("Inicio importação black list telefone.");

            int totalAtualizado = 0;

            if (CollectionUtils.isNotEmpty(listTelefones) && empresaMatriz != null) {

                Set<String> listBlackListManual = this.serviceTelefoneBlackList.pesquisarTelefonesBlackList(empresaMatriz.getId());

                this.transaction.begin();

                for (int i = 0; i < listTelefones.size(); i++) {

                    String telefone = listTelefones.get(i);

                    if (StringUtils.isNotBlank(telefone)) {

                        String telefoneFormatado = StringUtils.stripStart(telefone.replaceAll("\\D+", "").trim(), "0");

                        if (listBlackListManual != null && listBlackListManual.add(telefoneFormatado)) {

                            this.serviceTelefoneBlackList.inserirBatch(new TelefoneBlackList(telefoneFormatado, empresaMatriz), Integer.valueOf(i));
                            totalAtualizado += 1;

                            if (i > 0 && i % 50 == 0) {

                                this.transaction.commit();
                                this.transaction.begin();
                            }

                        }


                    }

                }

                System.out.println("TOTAL: " + listTelefones.size() + " | ATUALIZADO: " + totalAtualizado);

                this.transaction.commit();
            }

            System.out.println("termino importação black list telefones");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inserirCpfsListaNegra(ArrayList<String> listCpf, Empresa empresa, Usuario usuario) {

        try {

            System.out.println("Inicio importação black list cpf.");

            if (CollectionUtils.isNotEmpty(listCpf) && empresa != null) {

                Set<String> listBlackListManual = this.serviceCpfBlackList.pesquisarListaNegra(empresa.getId());

                this.transaction.begin();
                int totalAtualizado = 0;
                for (int i = 0; i < listCpf.size(); i++) {

                    String cpf = listCpf.get(i);

                    if (StringUtils.isNotBlank(cpf)) {

                        String cpfFormatado = StringUtils.leftPad(cpf.replaceAll("\\D+", "").trim(), 11, "0");

                        if (listBlackListManual != null && listBlackListManual.add(cpfFormatado)) {

                            this.serviceCpfBlackList.inserirBatch(new BlackListCpf(cpfFormatado, empresa, usuario, new Date(System.currentTimeMillis())), Integer.valueOf(i));
                            totalAtualizado += 1;
                            if (i > 0 && i % 50 == 0) {

                                this.transaction.commit();
                                this.transaction.begin();
                            }

                        }

                    }

                }

                System.out.println("TOTAL: " + listCpf.size() + " | ATUALIZADO: " + totalAtualizado);
                this.transaction.commit();
            }

            System.out.println("termino importação black list cpf");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
