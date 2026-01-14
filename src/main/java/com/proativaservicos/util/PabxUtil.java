package com.proativaservicos.util;

import java.io.Serializable;
import java.net.URL;


import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.lang3.StringUtils;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.PontoAtendimento;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.service.AtendimentoAudiosService;
import com.proativaservicos.service.asynchronous.vsphone.OriginaChamadaRequest;
import com.proativaservicos.service.asynchronous.vsphone.OriginaChamadaResponse;
import com.proativaservicos.service.asynchronous.vsphone.VSPhone;
import com.proativaservicos.service.asynchronous.vsphone.VSPhoneRPCLocator;
import com.proativaservicos.util.constantes.TipoPabxEnum;


@Named
public class PabxUtil implements Serializable {

    private static final long serialVersionUID = 1L;


    @Inject
    private AtendimentoAudiosService serviceAtendimentoAudio;


    public static String discar(Usuario usuario, Long idAtendimento, String destino, String callerId, PontoAtendimento ponto) {


        if (ponto != null && ponto.getPabx() != null && ponto.getPabx().getUrl() != null && StringUtils.isNotBlank(destino)) {


            if (ponto.getPabx().getTipo().equals(TipoPabxEnum.PST_PHONE)) {


                PstPhoneUtil pstPhone = new PstPhoneUtil().build("default", destino, ponto.getRamal(), StringUtils.isBlank(callerId) ? "NÃO INFORMADO" : callerId, false, ponto.getPabx().getUrl());

                if (pstPhone != null) {

                    if (StringUtils.isNotBlank(pstPhone.getIdChannel())) {

                        //this.serviceAtendimentoAudio.salvarAtendimentoAudio(pstPhone.getIdChannel(), idAtendimento, ponto.getPabx().getTipo(), ponto.getRamal(), new Date(), "", destino);

                    }

                    return pstPhone.getIdChannel();
                } else {


                }

            }

        }
        return callerId;


    }

    public static String entarEmPausaPstPhone(String url, String ramal, String descPausa, Integer codPausa) {

        try {

            PstPhoneUtil pstPhoneUtil = new PstPhoneUtil();
            return pstPhoneUtil.entrarEmPausa(url, ramal, descPausa, codPausa);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return JsonUtil.criarJson("erro", "Erro ao enviar pausa");
    }

    public static String sairEmPausaPstPhone(String url, String ramal) {

        try {

            PstPhoneUtil pstPhoneUtil = new PstPhoneUtil();
            return pstPhoneUtil.sairPausa(url, ramal);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return JsonUtil.criarJson("erro", "Erro ao enviar pausa");
    }


    public static String logarPstPhone(String url, String ramal) {

        try {

            PstPhoneUtil pstPhoneUtil = new PstPhoneUtil();

            return pstPhoneUtil.logar(url, ramal);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return JsonUtil.criarJson("erro", "Erro ao enviar pausa");
    }

    public static String logoutPstPhone(String url, String ramal) {

        try {

            PstPhoneUtil pstPhoneUtil = new PstPhoneUtil();
            return pstPhoneUtil.logout(url, ramal);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return JsonUtil.criarJson("erro", "Erro ao enviar pausa");
    }

    public static String discarPstPhone(String destino, String origem, String identificacao, String urlResource) {

        PstPhoneUtil pstPhone = new PstPhoneUtil().build("default", destino, origem, StringUtils.isBlank(identificacao) ? "NÃO INFORMADO" : identificacao, false, urlResource);

        try {

            pstPhone.discar();

        } catch (ProativaException e) {

            e.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        }

        return pstPhone.getIdChannel();

    }


    public static String discarChamada(TipoPabxEnum tipoPabx, String urlServico, String usuarioServico, String senhaServico, String origem, String destino, String nome, String grupo, String identificadorChamada, String usuario, String senha) throws ProativaException {

        switch (tipoPabx) {

            case VSPHONE:
                return realizarChamadaVsPhone(urlServico, usuarioServico, senhaServico, origem, destino, nome);
            case PST_PHONE:
                return discarPstPhone(destino, origem, nome, urlServico);


            default:
                break;
        }
        return null;

    }


    private static String realizarChamadaVsPhone(String url, String usr, String psw, String origem, String destino, String nome) throws ProativaException {

        try {

            VSPhone service = retornarVsphoneService(url, usr, psw);

            OriginaChamadaRequest request = new OriginaChamadaRequest();

            request.setOrigem(origem);
            request.setDestino(destino);
            request.setIdentificacao(nome);

            OriginaChamadaResponse response = service.originaChamada(request);

            if (response == null || !response.isSucesso()) {

                throw new ProativaException("Erro ao discar no PABX - VSPHONE");
            }


            service.logout();

            return response.getIdentificacaoChamada();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return nome;

    }

    private static VSPhone retornarVsphoneService(String url, String usr, String psw) throws ProativaException {

        try {

            VSPhoneRPCLocator vspLocator = new VSPhoneRPCLocator();

            vspLocator.setMaintainSession(true);

            VSPhone vsphone = vspLocator.getVSPhoneRPCPort(new URL(url));

            if (!vsphone.login(usr, psw)) {

                throw new ProativaException("Não foi possivel logar no pabx, verifique usuario e senha.");

            }

            return vsphone;

        } catch (Exception e) {

            e.printStackTrace();
            throw new ProativaException("Erro ao conectar no Vsphone.");

        }
    }

    public static void main(String[] args) throws ProativaException {

        PabxUtil.discarPstPhone("31999631311", "9020", "", "localhost:8180");


    }

}
