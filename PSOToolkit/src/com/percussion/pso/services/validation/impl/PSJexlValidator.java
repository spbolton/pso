package com.percussion.pso.services.validation.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.percussion.pso.services.validation.IPSValidator;
import com.percussion.pso.services.validation.exceptions.PSValidationException;
import com.percussion.pso.services.validation.impl.PSValidationContext.Event;
import com.percussion.pso.services.validation.utils.PSValidationJexlEvaluator;
import com.percussion.utils.jexl.PSJexlEvaluator;

public class PSJexlValidator implements IPSValidator   {
	
	/**
	    * Logger for this class
	    */
	 private static final Log log = LogFactory.getLog(PSJexlValidator.class);


	private String validationJexl;
	
	private List<Event> events;
	
	public PSJexlValidator() {
	
	}
	
	public String getValidationJexl() {
		return validationJexl;
	}

	public void setValidationJexl(String jexl) {
		this.validationJexl=jexl;

	}


	@Override
	public List<Event> getEvents() {
		return this.events;
	}

	@Override
	public void setEvents(List<Event> events) {
		this.events=events;

	}


	
	public PSValidationResult validate(PSValidationContext context)
			throws PSValidationException {
		log.debug("Calling PSJexlValidator.validate with context "+context.toString());
		PSValidationResult result = new PSValidationResult(context);
		PSValidationJexlEvaluator eval = new PSValidationJexlEvaluator(context,result);
		try{
			Object v = eval.evaluate(PSJexlEvaluator.createScript(this.getValidationJexl()));
			String resultString = v.toString();
			result = eval.getResult();
			log.debug("Evaluation result is "+resultString +" : "+result.toString());
			

		}		
		catch (Exception e)
		{
			log.error("Exception Failed to evaluate validation jexl",e);
			result.addError("Failed to evaluate validation jexl for "+this.getValidationJexl());
			result.setContinueValidation(false);
			return result;
		}

		return result;
	}

	@Override
	public String toString() {
		return "PSJexlValidator [validationJexl=" + validationJexl
				+ ", events=" + events + "]";
	}
	
	
}
