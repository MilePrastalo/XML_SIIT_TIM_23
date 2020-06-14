package com.papershare.papershare.DTO;

import java.util.List;

public class SearchDTO {

	private String title;
	private List<String> authors;
	private List<String> keywords;
	private String date;
	private String language;
	
	public SearchDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> words) {
		this.keywords = words;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	
}
