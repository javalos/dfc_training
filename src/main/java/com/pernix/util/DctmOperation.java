package com.pernix.util;

import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfLogger;

public abstract class DctmOperation {
	protected static String USER = "dmadmin";
	protected static String PASSWORD = "fsadmin73";
	protected static String DOMAIN = ""; 
	protected static String REPOSITORY = "repo1";
	private DctmConnection connection;
	
	protected abstract void executeImp() throws DfException;
	
	public DctmOperation() {
		connection = new DctmConnection();
	}
	
	public void execute() {
		try {
			createSession();
			executeImp();
		} catch (DfException e) {
			DfLogger.error(this, "Failed executing operation", null, e);
			//TODO: Add error handling in response response = "Something wrong happened" + e.getMessage();
		} finally {
			releaseSession();
		}
	}

	private void releaseSession() {
		connection.releaseConnection();
	}

	private void createSession() throws DfException {
		connection.createConnection(USER, PASSWORD, DOMAIN, REPOSITORY);
	}

	public IDfSession getSession() {
		return connection.getSession();
	}
}
