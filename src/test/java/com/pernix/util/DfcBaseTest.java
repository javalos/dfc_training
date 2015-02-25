package com.pernix.util;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfLogger;

public class DfcBaseTest {
	protected static String USER = "dmadmin";
	protected static String PASSWORD = "fsadmin73";
	protected static String DOMAIN = ""; 
	protected static String REPOSITORY = "repo1";
	protected static DctmConnection connection;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		connection = new DctmConnection();
		connection.createConnection(USER, PASSWORD, DOMAIN, REPOSITORY);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		connection.releaseConnection();
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
