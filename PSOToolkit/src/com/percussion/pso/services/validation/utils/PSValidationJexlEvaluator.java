package com.percussion.pso.services.validation.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.jexl.Expression;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.percussion.pso.services.validation.impl.PSValidationContext;
import com.percussion.pso.services.validation.impl.PSValidationResult;
import com.percussion.services.assembly.impl.PSAssemblyJexlEvaluator;
import com.percussion.services.utils.jexl.PSServiceJexlEvaluatorBase;
import com.percussion.utils.jexl.PSJexlEvaluator;

public class PSValidationJexlEvaluator extends  PSServiceJexlEvaluatorBase {
	 private static Log ms_log1 = LogFactory.getLog(PSAssemblyJexlEvaluator.class);
	   public static final String CONTEXT_VAR = "$validationContext";
	   public static final String RESULT_VAR = "$validationResult";
	   private static Expression ms_rx = null;
	   private static Expression ms_user = null;
	   
	   /**
	    * Default ctor, prebind necessary assembly methods here
	    * 
	    * @param work the work item passed to the assembly, never <code>null</code>
	    */
	   
	   
	   public PSValidationJexlEvaluator (PSValidationContext context, PSValidationResult result) {
	      super(false);
	      
	      if (context == null)
	      {
	         throw new IllegalArgumentException("validation context may not be null");
	      }
	      
	      if (result== null)
	      {
	         throw new IllegalArgumentException("validation result may not be null");
	      }
	      
	      synchronized(PSAssemblyJexlEvaluator.class)
	      {
	         if (ms_rx == null)
	         {
	            try
	            {
	               ms_rx = PSJexlEvaluator.createExpression("$rx");
	               ms_user = PSJexlEvaluator.createExpression("$user");
	            }
	            catch (Exception e)
	            {
	               ms_log1.error("problem creating expressions ", e);
	            }
	         }
	      }
	      
	      
	      Map<String,Object> bindings = new HashMap<String,Object>();
	      bindings.put(CONTEXT_VAR, context);
	      bindings.put(RESULT_VAR, result);
	      /*
	       * Copy initial data from any existing bindings
	       */
	      setValues(bindings);

	      /**
	       * Setup function bindings
	       */
	      try
	      {
	         add("$rx", ms_rx, getJexlFunctions(SYS_CONTEXT));
	         add("$user", ms_user, getJexlFunctions(USER_CONTEXT));
	         bind("$tools", getVelocityToolBindings());
	      }
	      catch (Exception e)
	      {
	         throw new RuntimeException(e);
	      }
	      	
	     
	   }
	   
	   public PSValidationResult getResult() {
		   return (PSValidationResult)getContext().getVars().get(PSValidationJexlEvaluator.RESULT_VAR);  
	   }
}
