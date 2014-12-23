package br.com.synchro.web.view.validator;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.primefaces.validate.ClientValidator;

import br.com.synchro.web.util.StringUtil;

/**
 * 
 * RepeatedNameValidator.java
 * Criado em Sep 28, 2014
 * @author Ciro S. Santos
 * @version 1.0
 */
@FacesValidator("custom.repeteadNamelValidator")
public class RepeatedNameValidator implements Validator, ClientValidator {

	@Override
	public Map<String, Object> getMetadata() {
		return null;
	}

	@Override
	public String getValidatorId() {
		return "custom.repeteadNamelValidator";
	}

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value == null) {
			return;
		}
		
		boolean check = StringUtil.checkRepeatedStrings(value.toString().toLowerCase());
		
		if (check) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro Validacao", value + " Nao pode existir nomes repetidos;"));
		}
	}

}
