package com.papershare.papershare.model.review;

import com.papershare.papershare.model.Chapter;

public class ReviewChapter {
	private Chapter chapter;
	private String chapterReview;
	public ReviewChapter() {
		super();
	}
	public ReviewChapter(Chapter chapter, String chapterReview) {
		super();
		this.chapter = chapter;
		this.chapterReview = chapterReview;
	}
	public Chapter getChapter() {
		return chapter;
	}
	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}
	public String getChapterReview() {
		return chapterReview;
	}
	public void setChapterReview(String chapterReview) {
		this.chapterReview = chapterReview;
	}
	

}
