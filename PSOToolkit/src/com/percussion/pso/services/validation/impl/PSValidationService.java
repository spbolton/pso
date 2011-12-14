package com.percussion.pso.services.validation.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.percussion.pso.services.validation.IPSValidationContext;
import com.percussion.pso.services.validation.IPSValidationContext.EVENT;
import com.percussion.pso.services.validation.IPSValidationService;
import com.percussion.pso.services.validation.IPSValidator;
import com.percussion.pso.services.validation.exceptions.PSValidationException;

public class PSValidationService implements IPSValidationService {
	private Map<EVENT,List<IPSValidator>> validators;
	@Override
	public List<IPSValidator> getValidators() {
		List<IPSValidator> retValidators = new ArrayList<IPSValidator>();
		for (Entry<EVENT, List<IPSValidator>> entry: validators.entrySet()) {
			retValidators.addAll(entry.getValue());
		}
		return retValidators;
	}

	@Override
	public void setValidators(List<IPSValidator> validators) {
		for (IPSValidator validator : validators) {
			for (EVENT event : validator.getEvents()) {
				
				List<IPSValidator> validatorList =  this.validators.get(event);
				if (validatorList == null) { 
					validatorList = new ArrayList<IPSValidator>();
					this.validators.put(event, validatorList);
				}
				validatorList.add(validator);	
			}
			
		}
	}

	@Override
	public void validate(IPSValidationContext context)
			throws PSValidationException {
			for (IPSValidator validator : this.validators.get(context.getEvent())) {
						validator.validate(context);
			}
		
	}

}
