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
	
	protected abstract String executeImp() throws DfException;
	
	public DctmOperation() {
		connection = new DctmConnection();
	}
	
	public String execute() {
		String response = "";
		try {
			createSession();
			response = executeImp();
		} catch (DfException e) {
			DfLogger.error(this, "Failed executing operation", null, e);
			response = "Something wrong happened" + e.getMessage();
		} finally {
			releaseSession();
		}
		return response;
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
