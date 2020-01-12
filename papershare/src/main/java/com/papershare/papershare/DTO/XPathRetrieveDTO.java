package com.papershare.papershare.DTO;

public class XPathRetrieveDTO {
	private String collectionId;
	private String xpath;

	public XPathRetrieveDTO() {
	}

	public XPathRetrieveDTO(String collectionId, String xpath) {
		this.collectionId = collectionId;
		this.xpath = xpath;
	}

	public String getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(String collectionId) {
		this.collectionId = collectionId;
	}

	public String getXpath() {
		return xpath;
	}

	public void setXpath(String xpath) {
		this.xpath = xpath;
	}

}
