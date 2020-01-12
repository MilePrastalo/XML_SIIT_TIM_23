package com.papershare.papershare.DTO;

public class StoreDTO {
	private String collectionId;
	private String name;
	private String path;

	public StoreDTO() {
	}

	public StoreDTO(String collectionId, String name, String path) {
		this.collectionId = collectionId;
		this.name = name;
		this.path = path;
	}

	public String getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(String collectionId) {
		this.collectionId = collectionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
