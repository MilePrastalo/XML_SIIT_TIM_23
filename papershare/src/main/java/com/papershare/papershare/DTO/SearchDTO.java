package com.papershare.papershare.DTO;


public class SearchDTO {

	private String title;
	private String authors;
	private String keywords;
	private String date;
	private String language;
	private boolean forUser;
	
	public SearchDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
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

	public boolean isForUser() {
		return forUser;
	}

	public void setForUser(boolean forUser) {
		this.forUser = forUser;
	}
	
	
}
