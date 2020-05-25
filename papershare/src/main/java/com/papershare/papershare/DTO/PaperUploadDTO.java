package com.papershare.papershare.DTO;

public class PaperUploadDTO {
	private String text;
	private String coverLetter;

	public PaperUploadDTO() {
		super();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCoverLetter() {
		return coverLetter;
	}

	public void setCoverLetter(String coverLetter) {
		this.coverLetter = coverLetter;
	}
	
	

}
