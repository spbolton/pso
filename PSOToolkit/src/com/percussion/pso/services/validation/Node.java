package com.percussion.pso.services.validation;

import javax.jcr.RepositoryException;

import com.percussion.cms.objectstore.PSComponentSummary;
import com.percussion.services.contentmgr.data.PSContentNode;
import com.percussion.services.contentmgr.impl.legacy.PSTypeConfiguration;
import com.percussion.services.contentmgr.impl.legacy.PSTypeConfiguration._e;
import com.percussion.utils.guid.IPSGuid;
import com.percussion.utils.jsr170.PSProperty;

public class Node extends PSContentNode {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public Node(String name)
			throws RepositoryException {
		super(null, name, null, null, null, null);
		addProperty(new PSProperty("rx:testprop", this, "TestValue"));
	}

	@Override
	public String toString() {
		try {
			return "Node [getProperties()=" + getProperties()
					+ ", getPrimaryNodeType()=" + getPrimaryNodeType()
					+ ", getName()="
					+ getName() + ", getDepth()=" + getDepth() + "]";
		} catch (RepositoryException e) {
			throw new RuntimeException("toString for node threw exception ",e);
		}
	}

	public Node(IPSGuid guid, String name, javax.jcr.Node parent,
			PSTypeConfiguration config, PSComponentSummary summary, _e instance)
			throws RepositoryException {
		super(guid, name, parent, config, summary, instance);
		if (summary==null ) setSummary(new PSComponentSummary());
		addProperty(new PSProperty("testprop", this, "TestValue"));
	}
	
	

}
