package com.pernix.pojos;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FolderResponse extends BaseResponse {
	private String objectId;
	private String objectName;
	private String ownerName;
	private String objectType;
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getObjectType() {
		return objectType;
	}
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
}
