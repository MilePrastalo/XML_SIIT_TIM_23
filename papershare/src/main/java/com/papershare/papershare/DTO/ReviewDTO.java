package com.papershare.papershare.DTO;

public class ReviewDTO {
	private String reviewName;
	private String publicationName;
	private String reviewer;
	private String submissionDate;

	public ReviewDTO() {
	}

	public ReviewDTO(String reviewName, String publicationName, String reviewer, String submissionDate) {
		this.reviewName = reviewName;
		this.publicationName = publicationName;
		this.reviewer = reviewer;
		this.submissionDate = submissionDate;
	}

	public String getReviewName() {
		return reviewName;
	}

	public void setReviewName(String reviewName) {
		this.reviewName = reviewName;
	}

	public String getPublicationName() {
		return publicationName;
	}

	public void setPublicationName(String publicationName) {
		this.publicationName = publicationName;
	}

	public String getReviewer() {
		return reviewer;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	public String getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(String submissionDate) {
		this.submissionDate = submissionDate;
	}

}
