package com.proativaservicos.validators;



import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

@FacesValidator("obrigatorioValidator")
public class ValidacaoObrigatorio implements Validator {

	public static final String ATRIBUTO_FACES = "OBRIGATORIO_VALIDATOR";
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		String facesValidator = Faces.getRequestParameterMap().get(ATRIBUTO_FACES);
		
		if( (facesValidator == null  || !Boolean.parseBoolean(facesValidator) )  && ( value == null || value.toString().trim().isEmpty() || value.toString().equalsIgnoreCase("[]") ) ) {
			
			throw new ValidatorException(Messages.createError("Obrigatório.", new Object[0]));
			
		}
		
	}

}
