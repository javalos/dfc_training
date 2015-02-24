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
		results = DfcUtil.executeQuery(getSession(), 
				String.format("select * from dm_folder where object_name = '%s'", folderName));
		if(results!=null) {
			if(results.next()) {
				response = "FOLDER FETCHED CORRECTLY: " + results.getString(DfcConstants.OBJECT_NAME);
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
