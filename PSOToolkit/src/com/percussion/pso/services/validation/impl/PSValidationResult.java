package com.percussion.pso.services.validation.impl;

import java.util.ArrayList;
import java.util.List;

import com.percussion.pso.services.validation.impl.PSValidationContext.Event;

public class PSValidationResult  {
	private List<PSValidationError>  errors = new ArrayList<PSValidationError>();
	private boolean continueOnError = false;
	private Event event = Event.UNKNOWN;
	

	public PSValidationResult(PSValidationContext context) {
		this.event=context.getEvent();
	}

	/**
	 * @return the errors
	 */
	public List<PSValidationError> getErrors() {
		return errors;
	}

	/**
	 * @param errors the errors to sets
	 */
	public void setErrors(List<PSValidationError> errors) {
		this.errors = errors;
	}
	
	/**
	 * @param message the error message
	 */
	public void addError(String message) {
		PSValidationError error = new PSValidationError(message,event);
		addError(error);
	}

	/**
	 * @param error the PSValidationError to add
	 */
	private void addError(PSValidationError error) {
		if (this.errors==null) this.errors=new ArrayList<PSValidationError>();
		this.errors.add(error);
	}
	

	public void addErrors(List<PSValidationError> errors) {
		if (this.errors==null) this.errors=new ArrayList<PSValidationError>();
		this.errors.addAll(errors);
	}
	/**
	 * Does this result contain errors
	 */
	public boolean hasError() {
		return errors!=null && errors.size()>0;
	}

	/**
	 * Should be set to false by script if we should continue validating even if there were errors
	 * @return the continueOnError
	 */
	public boolean isContinueValidation() {
		return continueOnError;
	}

	/**
	 * @param continueOnError the continueOnError value to set
	 */
	public void setContinueValidation(boolean continueValidation) {
		this.continueOnError = continueValidation;
	}


	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PSValidationResult [errors=" + errors + ", continueOnError="
				+ continueOnError + "]";
	}
	
	
	public class PSValidationError {
		private String errorMessage;
		private Event errorEvent;

		/**
		 * @return the errorEvent
		 */
		public Event getErrorEvent() {
			return errorEvent;
		}

		/**
		 * @param errorEvent the errorEvent to set
		 */
		public void setErrorEvent(Event errorEvent) {
			this.errorEvent = errorEvent;
		}

		public PSValidationError(String message, Event event) {
			this.setErrorMessage(message);
			this.setErrorEvent(event);
		}

		/**
		 * @return the errorMessage
		 */
		public String getErrorMessage() {
			return errorMessage;
		}
		
		

		/**
		 * @param errorMessage the errorMessage to set
		 */
		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "PSValidationError [errorMessage=" + errorMessage
					+ ", errorEvent=" + errorEvent + "]";
		}
		
	}


	
}
