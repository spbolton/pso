package com.percussion.pso.services.validation;

import org.springframework.context.support.AbstractApplicationContext;

import com.percussion.pso.services.validation.impl.PSValidationService;
import com.percussion.services.PSBaseServiceLocator;


/**
 * Locator for the PSValidationService bean. 
 *
 * @author Stephen Bolton
 * @see PSValidationService
 */
public class  PSValidationServiceLocator extends PSBaseServiceLocator
{
   /**
    * Gets the Validation Service bean. 
    * @return the Validation Service bean. 
    */
   public static IPSValidationService getPSValidationService()
   {
      return (IPSValidationService) PSBaseServiceLocator.getBean(PS_VALIDATION_SERVICE_BEAN); 
   }
   
   public static void refresh() {
	   AbstractApplicationContext ctx = (AbstractApplicationContext)PSBaseServiceLocator.getCtx();
	   ctx.refresh();
   }
   
   public static final String PS_VALIDATION_SERVICE_BEAN = "psValidators";
}
