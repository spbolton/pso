package com.percussion.pso.services.validation.impl;

import com.percussion.services.contentmgr.IPSNode;

public class PSValidationContext {

	public static enum Event {
		PRE_NEW_ITEM,
		UPDATE_ITEM,
		UPDATE_FOLDER,
		MOVE_ITEM,
		MOVE_FOLDER,
		REMOVE_FOLDER_FROM_FOLDER,
		REMOVE_ITEM_FROM_FOLDER,
		PURGE_ITEM,
		ADD_ITEM_TO_FOLDER,
		LINK_ITEM_TO_FOLDER,
		TRANSITION_ITEM,
		CHECKIN_ITEM,
		CHECKOUT_ITEM};
	
	private Event event;
	private IPSNode node;
	private int folderId;
	/**
	 * @param sourceFolderId the sourceFolderId to set
	 */
	public void setSourceFolderId(int sourceFolderId) {
		this.sourceFolderId = sourceFolderId;
	}

	private int sourceFolderId;
	
	public PSValidationContext() {
		super();
	}

	/**
	 * @return the event
	 */
	public Event getEvent() {
		return event;
	}

	/**
	 * @param event the event to set
	 */
	public void setEvent(Event event) {
		this.event = event;
	}

	/**
	 * @return the node
	 */
	public IPSNode getNode() {
		return node;
	}

	/**
	 * @param node the node to set
	 */
	public void setNode(IPSNode node) {
		this.node = node;
	}

	/**
	 * @return the folderId
	 */
	public int getFolderId() {
		return folderId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PSValidationContext [event=" + event + ", node=" + node
				+ ", folderId=" + folderId + ", sourceFolderId="
				+ sourceFolderId + "]";
	}

	/**
	 * @param folderId the folderId to set
	 */
	public void setFolderId(int folderId) {
		this.folderId = folderId;
	}

	/**
	 * @return the sourceFolderId
	 */
	public int getSourceFolderId() {
		return sourceFolderId;
	}


}
