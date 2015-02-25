package com.pernix.services.workers;

import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfLogger;
import com.pernix.pojos.FolderResponse;
import com.pernix.util.DctmOperation;
import com.pernix.util.DfcConstants;
import com.pernix.util.DfcUtil;

public class FolderWorker extends DctmOperation {
	private String folderName;
	private FolderResponse response;
	
	public FolderWorker(String folderName, FolderResponse response) {
		this.response = response;
		this.folderName = folderName;
	}

	@Override
	protected void executeImp() throws DfException {
		IDfCollection results = null;
		results = DfcUtil.getFolderByName(getSession(), folderName);
		if(results!=null) {
			if(results.next()) {
				response.setObjectId(results.getString("r_object_id"));
				response.setObjectName(results.getString(DfcConstants.OBJECT_NAME));
				response.setOwnerName(results.getString("owner_name"));
				response.setObjectType(results.getString("r_object_type"));
			} else {
				//response = "There is no results";
			}
			try {
				results.close();
			} catch (DfException e) {
				DfLogger.error(this, "Failed closing collection results", null, e);
			}
		}
	}
}
