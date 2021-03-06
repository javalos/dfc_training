package com.pernix.util;

import static org.junit.Assert.*;

import org.junit.Test;

import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfFolder;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfLogger;

public class DfcUtilTest extends DfcBaseTest {

	@Test
	public void testCreateFolderAndReferenceByName(){
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
			
			results = DfcUtil.getFolderByName(connection.getSession(), folderName);
			assertNotNull(results);
			assertTrue(results.next());
			assertEquals(folderName, results.getString(DfcConstants.OBJECT_NAME));
			
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
