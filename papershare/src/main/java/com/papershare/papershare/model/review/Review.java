package com.papershare.papershare.model.review;

import java.util.ArrayList;

import com.papershare.papershare.model.TUser;

public class Review {
	private String paperTitle;
	private String paperId;
	private TUser user;
	private ArrayList<ReviewChapter> reviewChapters;
	private String summary;
	private Boolean done;
	private String date;

	public Review() {
		super();
	}

	public Review(String paperTitle, String paperId, TUser user, ArrayList<ReviewChapter> reviewChapters,
			String summary) {
		super();
		this.paperTitle = paperTitle;
		this.paperId = paperId;
		this.user = user;
		this.reviewChapters = reviewChapters;
		this.summary = summary;
	}

	public String getPaperTitle() {
		return paperTitle;
	}

	public void setPaperTitle(String paperTitle) {
		this.paperTitle = paperTitle;
	}

	public String getPaperId() {
		return paperId;
	}

	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}

	public TUser getUser() {
		return user;
	}

	public void setUser(TUser user) {
		this.user = user;
	}

	public ArrayList<ReviewChapter> getReviewChapters() {
		return reviewChapters;
	}

	public void setReviewChapters(ArrayList<ReviewChapter> reviewChapters) {
		this.reviewChapters = reviewChapters;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	

}
