package com.pernix.services.workers;

import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfLogger;
import com.pernix.util.DctmOperation;
import com.pernix.util.DfcConstants;
import com.pernix.util.DfcUtil;

public class FolderWorker extends DctmOperation {
	private String folderName;
	
	public FolderWorker(String folderName){
		this.folderName = folderName;
	}

	@Override
	protected String executeImp() throws DfException {
		IDfCollection results = null;
		String response = "NO RESPONSE";
		results = DfcUtil.getFolderByName(getSession(), folderName);
		if(results!=null) {
			if(results.next()) {
				response = "FOLDER FETCHED CORRECTLY: " +
						"r_object_id: " + results.getString("r_object_id") + ", " +
						"object_name: " + results.getString(DfcConstants.OBJECT_NAME) + ", " +
						"owner_name: " + results.getString("owner_name") + ", " +
						"r_object_type: " + results.getString("r_object_type");
			} else {
				response = "There is no results";
			}
			try {
				results.close();
			} catch (DfException e) {
				DfLogger.error(this, "Failed closing collection results", null, e);
			}
		}
		return response;
	}
}
