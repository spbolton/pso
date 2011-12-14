package com.percussion.pso.services.validation;

import java.util.List;

import com.percussion.pso.services.validation.IPSValidationContext.EVENT;
import com.percussion.pso.services.validation.exceptions.PSValidationException;

public abstract class PSAbstractValidator implements IPSValidator {

	private List<EVENT> events;
	
	@Override
	public List<EVENT> getEvents() {
		return this.events;
	}

	@Override
	public void setEvents(List<EVENT> events) {
		this.events=events;

	}


}
