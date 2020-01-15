package com.papershare.papershare.model;

import java.util.ArrayList;

public class Chapter extends AbstractChapter {
	private String id;
	private String chapterName;
	private ArrayList<AbstractChapter> chapterBody;
	public Chapter() {
		super();
	}
	public Chapter(String id, String chapterName, ArrayList<AbstractChapter> chapterBody) {
		super();
		this.id = id;
		this.chapterName = chapterName;
		this.chapterBody = chapterBody;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getChapterName() {
		return chapterName;
	}
	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}
	public ArrayList<AbstractChapter> getChapterBody() {
		return chapterBody;
	}
	public void setChapterBody(ArrayList<AbstractChapter> chapterBody) {
		this.chapterBody = chapterBody;
	}
	

}
