package com.papershare.papershare.model;

import java.util.ArrayList;

public class ChapterContent extends AbstractChapter {
	private ArrayList<String> paragraphs;

	public ChapterContent() {
		super();
	}

	public ArrayList<String> getParagraphs() {
		return paragraphs;
	}

	public void setParagraphs(ArrayList<String> paragraphs) {
		this.paragraphs = paragraphs;
	}
	

}
