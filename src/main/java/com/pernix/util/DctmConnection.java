package com.pernix.util;

import com.documentum.com.DfClientX;
import com.documentum.com.IDfClientX;
import com.documentum.fc.client.IDfClient;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSessionManager;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.IDfLoginInfo;

public class DctmConnection {
	private IDfSessionManager sessionManager;
	private IDfSession session;

	public DctmConnection() {
		this.sessionManager = null;
		this.session = null;
	}
	
	public void createConnection(String user, String password,
			String domain, String repository) throws DfException{
		IDfSessionManager sessionManager = getIDfSessionManager(user, password, domain, repository);
		setSessionManager(sessionManager);
		setSession(sessionManager.getSession(repository));
	}
	
	public void releaseConnection() {
		if (getSessionManager() != null && getSession() != null)
			getSessionManager().release(getSession());
	}

	public IDfSessionManager getSessionManager() {
		return sessionManager;
	}

	public IDfSession getSession() {
		return session;
	}

	private void setSessionManager(IDfSessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	private void setSession(IDfSession session) {
		this.session = session;
	}

	private IDfSessionManager getIDfSessionManager(String user, String password,
			String domain, String repository) throws DfException {
		IDfClientX clientX = new DfClientX(); 
		IDfClient client = clientX.getLocalClient();
		
		IDfSessionManager sessionManager = client.newSessionManager();
		IDfLoginInfo loginInfo = clientX.getLoginInfo();
		loginInfo.setUser(user);
		loginInfo.setPassword(password);
		loginInfo.setDomain(domain);
		
		sessionManager.setIdentity(repository, loginInfo);
		
		return sessionManager;
	}
}