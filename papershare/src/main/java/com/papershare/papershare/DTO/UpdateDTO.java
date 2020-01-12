package com.papershare.papershare.DTO;

public class UpdateDTO {
	private String collectionId;
	private String documentId;
	private String contextXPath;
	private String patch;

	public UpdateDTO() {
	}

	public UpdateDTO(String collectionId, String documentId, String contextXPath, String patch) {
		this.collectionId = collectionId;
		this.documentId = documentId;
		this.contextXPath = contextXPath;
		this.patch = patch;
	}

	public String getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(String collectionId) {
		this.collectionId = collectionId;
	}

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public String getContextXPath() {
		return contextXPath;
	}

	public void setContextXPath(String contextXPath) {
		this.contextXPath = contextXPath;
	}

	public String getPatch() {
		return patch;
	}

	public void setPatch(String patch) {
		this.patch = patch;
	}

}
