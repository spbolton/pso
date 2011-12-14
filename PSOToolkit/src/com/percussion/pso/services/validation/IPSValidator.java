package com.percussion.pso.services.validation;

import java.util.List;

import com.percussion.pso.services.validation.IPSValidationContext.EVENT;
import com.percussion.pso.services.validation.exceptions.PSValidationException;

public interface IPSValidator {
	public List<EVENT> getEvents();
	public void setEvents(List<EVENT> events);
	public void validate(IPSValidationContext context) throws PSValidationException;
}
