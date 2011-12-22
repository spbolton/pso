package com.percussion.pso.services.validation;

import java.util.List;

import com.percussion.pso.services.validation.exceptions.PSValidationException;
import com.percussion.pso.services.validation.impl.PSValidationContext;
import com.percussion.pso.services.validation.impl.PSValidationContext.Event;
import com.percussion.pso.services.validation.impl.PSValidationResult;

public interface IPSValidator {
	public List<Event> getEvents();
	public void setEvents(List<Event> events);
	public PSValidationResult validate(PSValidationContext context) throws PSValidationException;
}
