package com.pernix.util;

import static org.junit.Assert.*;

import org.junit.Test;

import com.documentum.fc.common.DfException;

public class DctmConnectionTest {
	protected static String USER = "dmadmin";
	protected static String PASSWORD = "fsadmin73";
	protected static String DOMAIN = ""; 
	protected static String REPOSITORY = "repo1";

	@Test
	public void test() {
		DctmConnection connection = new DctmConnection();
		try {
			connection.createConnection(USER, PASSWORD, DOMAIN, REPOSITORY);
			assertNotNull(connection.getSession());
		} catch (DfException e) {
			fail(e.getMessage());
		}
	}

}
