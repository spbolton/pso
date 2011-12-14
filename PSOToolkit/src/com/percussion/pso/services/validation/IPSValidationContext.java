package com.percussion.pso.services.validation;

import com.percussion.services.contentmgr.IPSNode;

public interface IPSValidationContext {
	public static enum EVENT {
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
	public EVENT getEvent();
	public IPSNode getNode();
	public void setNode(IPSNode node);
	public int getFolderId();
	public void setFolderId(int folderId);
	// for move,  folder id we are moving from.
	public int getSourceFolderId();
	public void getSourceFolderId(int sourceFolderId);
}
