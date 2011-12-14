package com.percussion.pso.services.validation.impl;

import com.percussion.pso.services.validation.IPSValidationContext;
import com.percussion.pso.services.validation.PSAbstractValidator;
import com.percussion.pso.services.validation.exceptions.PSValidationException;

public class PSJexlValidator extends PSAbstractValidator {
	
	private String validationJexl;
	private String errorJexl;
	
	
	public String getValidationJexl() {
		return validationJexl;
	}

	public void setValidationJexl(String jexl) {
		this.validationJexl=jexl;
		
	}


	public String getErrorJexl() {
		return errorJexl;
	}

	public void setErrorJexl(String jexl) {
		this.errorJexl=jexl;	
	}

	@Override
	public void validate(IPSValidationContext context)
			throws PSValidationException {
		// TODO Auto-generated method stub
		
	}

}
