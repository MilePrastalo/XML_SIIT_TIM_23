package com.papershare.papershare.DTO;

public class AddReviewDTO {
	private String username;
	private String publicationName;

	public AddReviewDTO(String username, String publicationName) {
		this.username = username;
		this.publicationName = publicationName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPublicationName() {
		return publicationName;
	}

	public void setPublicationName(String publicationName) {
		this.publicationName = publicationName;
	}

}
