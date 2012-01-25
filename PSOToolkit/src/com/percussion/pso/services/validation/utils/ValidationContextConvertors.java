package com.percussion.pso.services.validation.utils;

import javax.jcr.RepositoryException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.percussion.pso.services.validation.Node;
import com.percussion.pso.services.validation.impl.PSValidationContext;
import com.percussion.pso.services.validation.impl.PSValidationContext.Event;
import com.percussion.pso.services.validation.impl.PSValidationResult;
import com.percussion.pso.services.validation.impl.PSValidationResult.PSValidationError;
import com.percussion.server.IPSRequestContext;
import com.percussion.server.PSRequest;
import com.percussion.util.PSItemErrorDoc;
import com.percussion.utils.jsr170.PSProperty;
import com.percussion.xml.PSXmlDocumentBuilder;
import com.percussion.xml.PSXmlTreeWalker;

public class ValidationContextConvertors {

	/**
	 * Logger for this class
	 */
	private static final Log log = LogFactory.getLog(ValidationContextConvertors.class);


	public static PSValidationContext getContextFromReqest(PSRequest request) {
		PSValidationContext context = new PSValidationContext();
		return context;
	}

	public static PSValidationContext getContextFromReqest(PSRequest request,Event event) {
		PSValidationContext context = new PSValidationContext();
		return context;
	}

	public static PSValidationContext getContextFromReqest(Object[] params,IPSRequestContext request,Document resultDoc,Event event) {
		PSValidationContext context = new PSValidationContext();
		
	
			Node node;
			try {
				node = convertXmlToNode(resultDoc);
				context.setNode(node);
			} catch (RepositoryException e) {
				log.debug("Cannot convert xml to node",e);
			}
			context.setEvent(event);
			
		return context;
	}

	public static Document getErrorDocFromResult(Document resultDoc, PSValidationResult result) {
		log.debug("GetErrorDocFromResult "+result);
		Document errorDoc = PSXmlDocumentBuilder.createXmlDocument();
		if (result.hasError()) {
			for (PSValidationError  error : result.getErrors()) {
			
				log.debug("getErrorDocFromResult adding error "+ error);
				//PSItemErrorDoc.addError(errorDoc, "sys_title", "sys_title", "Validation Error for event", new Object[]{"sys_title"});
				PSItemErrorDoc.addError(errorDoc, error.getErrorEvent().toString(),error.getErrorEvent().toString() , "Validation Error : " + error.getErrorMessage(), new Object[]{});

			}
		}
		return errorDoc;
	}

	public static Node convertXmlToNode(Document resultDoc) throws RepositoryException {
		Node node = new Node("test");
	
		 PSXmlTreeWalker fieldWalker = new PSXmlTreeWalker(resultDoc.getDocumentElement());
	      fieldWalker.getNextElement("ItemContent",PSXmlTreeWalker.GET_NEXT_ALLOW_CHILDREN); 
	      Element field = fieldWalker.getNextElement("DisplayField", PSXmlTreeWalker.GET_NEXT_ALLOW_CHILDREN); 
	      while(field != null)
	      {
	         PSXmlTreeWalker fw = new PSXmlTreeWalker(field); 
	         Element control = fw.getNextElement("Control", PSXmlTreeWalker.GET_NEXT_ALLOW_CHILDREN);
	         if(control != null)
	         {
	        	 
	         String dim = control.getAttribute("dimension");
	          if(StringUtils.isNotBlank(dim) && dim.equals("array"))
	          {
	            log.debug("Multi Valued Field");
	          } else {
	          
	       
	            String fld = control.getAttribute("paramName");
	            if(StringUtils.isNotBlank(fld))
	            {  
	            	 
	            	 Element c = fw.getNextElement("Value", PSXmlTreeWalker.GET_NEXT_ALLOW_CHILDREN);
	                 if(c == null)
	                 {
	                	 log.debug("Found empty field "+fld);
	                	 node.addProperty(new PSProperty(fld,node,""));
	                 } else {
	                 String val = fw.getElementData();
	                 //TODO: add correct property type
	                 log.debug("Found field "+fld + "with val "+val);
	                 	node.addProperty(new PSProperty("rx:"+fld,node,val));
	                 	
 	                 }
	            	
	          
	            }
	         }
	         }
	         field = fieldWalker.getNextElement("DisplayField", PSXmlTreeWalker.GET_NEXT_ALLOW_SIBLINGS); 
	      }
	      return node;
	}
	
}


