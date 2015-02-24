package com.pernix.util;

import com.documentum.com.DfClientX;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfFolder;
import com.documentum.fc.client.IDfQuery;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.common.DfException;

public class DfcUtil {
	
	public static IDfFolder createCabinet(IDfSession session, String cabinetName) throws DfException {
		IDfFolder myCabinet;
		myCabinet = (IDfFolder)session.newObject(DfcConstants.DM_CABINET);
		myCabinet.setObjectName(cabinetName);
		myCabinet.save();
		return myCabinet;
	}

	public static IDfFolder createFolder(IDfSession session, String parentName, String folderName)
			throws DfException {
		IDfFolder myFolder;
		myFolder = (IDfFolder)session.newObject(DfcConstants.DM_FOLDER);
		myFolder.setObjectName(folderName);
		myFolder.link("/" + parentName);
		myFolder.save();
		return myFolder;
	}

	public static IDfSysObject createDocument(IDfSession session, String documentLocation, String contentType, 
			String documentName, String filePath) throws DfException {
		
		IDfSysObject myDocument = (IDfSysObject)session.newObject(DfcConstants.DM_DOCUMENT);
		myDocument.setObjectName(documentName );
		myDocument.setContentType(contentType);
		myDocument.setFile(filePath);
		myDocument.link("/"+ documentLocation);
		myDocument.save();
		return myDocument;
	}

	public static void deleteObject(IDfSession session, IDfSysObject object) throws DfException {
		if (object != null)
			object.destroyAllVersions();
	}

	public static IDfCollection executeQuery(IDfSession session, String statement)
			throws DfException {
		IDfCollection results;
		IDfQuery query = new DfClientX().getQuery();
		query.setDQL(statement);
		results = query.execute(session, IDfQuery.DF_READ_QUERY );
		return results;
	}
}
