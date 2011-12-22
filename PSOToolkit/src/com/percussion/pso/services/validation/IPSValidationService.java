package com.percussion.pso.services.validation;

import java.util.List;

import com.percussion.pso.services.validation.exceptions.PSValidationException;
import com.percussion.pso.services.validation.impl.PSValidationContext;
import com.percussion.pso.services.validation.impl.PSValidationResult;

public interface IPSValidationService {
	public List<IPSValidator> getValidators();
	public void setValidators(List<IPSValidator> validators);
	public PSValidationResult validate(PSValidationContext context) throws PSValidationException;
}
