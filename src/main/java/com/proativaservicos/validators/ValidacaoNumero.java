package com.proativaservicos.validators;


import com.proativaservicos.util.PhoneNumberUtil;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Messages;


public class ValidacaoNumero implements Validator {


    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {


        if ((value == null || value.toString().trim().isEmpty() || value.toString().equalsIgnoreCase("[]"))) {

            throw new ValidatorException(Messages.createError("Obrigatório.", new Object[0]));

        } else if (!StringUtils.isNumeric(value.toString()) && !PhoneNumberUtil.isTelefone(value.toString())) {
            throw new ValidatorException(Messages.createError("Número informado não é valido.", new Object[0]));
        }


    }

}
