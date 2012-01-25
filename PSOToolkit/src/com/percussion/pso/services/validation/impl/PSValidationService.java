package com.percussion.pso.services.validation.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.percussion.pso.services.validation.IPSValidationService;
import com.percussion.pso.services.validation.IPSValidator;
import com.percussion.pso.services.validation.exceptions.PSValidationException;
import com.percussion.pso.services.validation.impl.PSValidationContext.Event;




public class PSValidationService implements IPSValidationService {
	/**
	 * Logger for this class
	 */
	private static final Log log = LogFactory.getLog(PSValidationService.class);
	
	private Map<Event,List<IPSValidator>> validators = new HashMap<Event,List<IPSValidator>>();
	@Override
	public List<IPSValidator> getValidators() {
		log.debug("getting validators");
		List<IPSValidator> retValidators = new ArrayList<IPSValidator>();
		if (validators != null) {
			for (Entry<Event, List<IPSValidator>> entry: validators.entrySet()) {
				retValidators.addAll(entry.getValue());
			}
		}
		return retValidators;
	}

	@Override
	public void setValidators(List<IPSValidator> validators) {
		log.debug("Setting validators :" + validators.toString());
			for (IPSValidator validator : validators) {
				for (Event event : validator.getEvents()) {
				
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
	public PSValidationResult validate(PSValidationContext context)
			throws PSValidationException {
		log.debug("Validating with context :"+context);
		
				PSValidationResult fullResult = new PSValidationResult(context);
				List<IPSValidator> validatorsForEvent = this.validators.get(context.getEvent());
				if (validatorsForEvent != null) {
					for (IPSValidator validator : this.validators.get(context.getEvent())) {
						log.debug("Running validator "+validator);
						PSValidationResult result = validator.validate(context);
						fullResult.addErrors(result.getErrors());
						if (!result.isContinueValidation()) break;
					}
				}
	
		return fullResult;
	}

}
