package com.pernix.services.workers;

import static org.junit.Assert.*;

import org.junit.Test;

import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfFolder;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfLogger;
import com.pernix.pojos.FolderResponse;
import com.pernix.util.DfcBaseTest;
import com.pernix.util.DfcUtil;

public class FolderWorkerTest extends DfcBaseTest {

	@Test
	public void getFolderTest() {
		String cabinetName = "cabineName";
		String folderName = "folderName";
		IDfFolder cabinet = null;
		IDfFolder folder = null;
		IDfCollection results = null;
		try {
			cabinet = DfcUtil.createCabinet(connection.getSession(), cabinetName);
			assertNotNull(cabinet);
			folder = DfcUtil.createFolder(connection.getSession(), cabinetName, folderName);
			assertNotNull("folder is null", folder);
			
			FolderResponse response = new FolderResponse();
	    	FolderWorker worker = new FolderWorker(folderName, response);
	    	worker.execute();
	    	
	    	assertNotNull(response);
	    	assertEquals(response.getObjectName(), folderName);
	    	assertEquals(response.getObjectType(), "dm_folder");
	    	assertEquals(response.getOwnerName(), "dmadmin");
	    	assertNotNull(response.getObjectId());
			
		} catch (DfException e) {
			DfLogger.error(this, "Failed creating/getting folder", null, e);
			fail("Failed creating/getting folder");
		} finally {
			closeCollectionResults(results);
			deleteObject(folder);
			deleteObject(cabinet);
		}
	}

}
