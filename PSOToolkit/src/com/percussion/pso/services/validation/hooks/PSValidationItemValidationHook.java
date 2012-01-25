package com.percussion.pso.services.validation.hooks;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.percussion.extension.IPSExtensionDef;
import com.percussion.extension.IPSItemValidator;
import com.percussion.extension.IPSResultDocumentProcessor;
import com.percussion.extension.PSExtensionException;
import com.percussion.extension.PSExtensionProcessingException;
import com.percussion.extension.PSParameterMismatchException;
import com.percussion.pso.services.validation.IPSValidationService;
import com.percussion.pso.services.validation.PSValidationServiceLocator;
import com.percussion.pso.services.validation.exceptions.PSValidationException;
import com.percussion.pso.services.validation.impl.PSValidationContext;
import com.percussion.pso.services.validation.impl.PSValidationContext.Event;
import com.percussion.pso.services.validation.impl.PSValidationResult;
import com.percussion.pso.services.validation.utils.ValidationContextConvertors;
import com.percussion.pso.validation.PSOItemXMLSupport;
import com.percussion.server.IPSRequestContext;
import com.percussion.util.PSItemErrorDoc;
import com.percussion.xml.PSXmlTreeWalker;

public class PSValidationItemValidationHook 
extends PSOItemXMLSupport
implements
   IPSItemValidator,
   IPSResultDocumentProcessor {
	
	 /**
	    * Logger for this class
	    */
	 private static final Log log = LogFactory.getLog(PSValidationItemValidationHook.class);


	   /**
	    * @see com.percussion.extension.IPSResultDocumentProcessor#canModifyStyleSheet()
	    */
	   public boolean canModifyStyleSheet()
	   {
	      return false;
	   }
	   /**
	    * @see com.percussion.extension.IPSResultDocumentProcessor#processResultDocument(java.lang.Object[], com.percussion.server.IPSRequestContext, org.w3c.dom.Document)
	    */
	   public Document processResultDocument(Object[] params,
	         IPSRequestContext request, Document resultDoc)
	         throws PSParameterMismatchException, PSExtensionProcessingException
	   {
		   // temp to only run on insert or update
//		   String actionType = request.getParameter("DBActionType");
//	        if(actionType == null || 
//	           !(actionType.equals("INSERT") || actionType.equals("UPDATE")))
//	           return resultDoc;
//		  
		  PSValidationContext context = ValidationContextConvertors.getContextFromReqest(params, request, resultDoc,Event.TRANSITION_ITEM);
		  // Temp to reload jexl changes, should not happen every time.
		  PSValidationServiceLocator.refresh();
		  IPSValidationService validationService = PSValidationServiceLocator.getPSValidationService();
		  PSValidationResult result;
		try {
			result = validationService.validate(context);
		} catch (PSValidationException e) {
			throw new PSExtensionProcessingException("PSValidationItemValidationHook",e);
		}
		  Document errorDoc = ValidationContextConvertors.getErrorDocFromResult(resultDoc,result);

		  if(hasErrors(errorDoc))
	      {
	         log.debug("validation errors found"); 
	         return errorDoc;
	      }
	      log.debug("validation successful"); 
	      return resultDoc;    
	
	   }
	   
	  
	   /**
	    * @see com.percussion.extension.IPSExtension#init(com.percussion.extension.IPSExtensionDef, java.io.File)
	    */
	   public void init(IPSExtensionDef def, File codeRoot)
	         throws PSExtensionException
	   {
	   }
	   
	   /**
	    * Determines if an error document contains errors. 
	    * @param errorDoc the error document 
	    * @return <code>true</code> if there are any errors. 
	    */
	   protected boolean hasErrors(Document errorDoc)
	   {
	      Element root = errorDoc.getDocumentElement();
	      if(root == null)
	      {
	         return false;
	      }
	      PSXmlTreeWalker w = new PSXmlTreeWalker(root);
	      Element e = w.getNextElement(PSItemErrorDoc.ERROR_FIELD_SET_ELEM, PSXmlTreeWalker.GET_NEXT_ALLOW_CHILDREN);
	      if(e == null)
	      {
	         return false;
	      }
	      e = w.getNextElement(PSItemErrorDoc.ERROR_FIELD_ELEM, PSXmlTreeWalker.GET_NEXT_ALLOW_CHILDREN);
	      if(e == null)
	      {
	         return false;
	      }
	      return true; 
	   }

	  

}
