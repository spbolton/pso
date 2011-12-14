package com.percussion.pso.services.validation;

import java.util.List;

import com.percussion.pso.services.validation.exceptions.PSValidationException;

public interface IPSValidationService {
	public List<IPSValidator> getValidators();
	public void setValidators(List<IPSValidator> validators);
	public void validate(IPSValidationContext context) throws PSValidationException;
}
