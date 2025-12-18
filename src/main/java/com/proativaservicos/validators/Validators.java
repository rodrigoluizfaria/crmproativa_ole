package com.proativaservicos.validators;



import com.proativaservicos.model.Empresa;
import com.proativaservicos.service.EmpresaService;
import jakarta.enterprise.inject.Model;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Inject;

@Model
public class Validators {

	@Inject
	private EmpresaService serviceEmpresa;

	public void validateNomeEmpresa(FacesContext context, UIComponent component, Object value)	throws ValidatorException {

		System.out.println("VALIDATOR " + value);
		String nomeEmpresa = (String) value;
		Empresa empresa = new Empresa();
		empresa.setNome(nomeEmpresa);
		

		if (serviceEmpresa.persquisarCriteria(empresa) != null) {

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nome Empresa já existe.", "");
			throw new ValidatorException(message);

		}

	}

}
