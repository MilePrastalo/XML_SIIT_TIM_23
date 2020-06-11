package com.papershare.papershare.DTO;

public class SendReviewDTO {
	private String text;
	private String reviewName;

	public SendReviewDTO(String text, String reviewName) {
		super();
		this.text = text;
		this.reviewName = reviewName;
	}

	public SendReviewDTO() {
		super();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getReviewName() {
		return reviewName;
	}

	public void setReviewName(String reviewName) {
		this.reviewName = reviewName;
	}
	
	

	
	
	

}
