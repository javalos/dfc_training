package com.pernix.util;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfFolder;
import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfLogger;

public class DfcUtilTest {
	protected static String USER = "dmadmin";
	protected static String PASSWORD = "fsadmin73";
	protected static String DOMAIN = ""; 
	protected static String REPOSITORY = "repo1";
	private static DctmConnection connection;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		connection = new DctmConnection();
		connection.createConnection(USER, PASSWORD, DOMAIN, REPOSITORY);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		connection.releaseConnection();
	}

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
	
	protected void deleteObject(IDfSysObject object) {
		try {
			DfcUtil.deleteObject(connection.getSession(), object);
		} catch (DfException e) {
			DfLogger.error(this, "Failed deleting object", null, e);
			fail("Failed deleting object");
		}
	}
	
	protected void closeCollectionResults(IDfCollection results) {
		if(results != null) {
			try {
				results.close();
			} catch (DfException e) {
				DfLogger.error(this, "Failed closing collection results", null, e);
				fail("Failed closing collection results");
			}
		}
	}

}
