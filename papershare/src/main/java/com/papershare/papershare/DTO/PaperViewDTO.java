package com.papershare.papershare.DTO;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.NodeList;

public class PaperViewDTO {

	private String id;
	private String title;
	private List<String> authors = new ArrayList<String>();
	private String status;
	
	public PaperViewDTO() {
	}
	
	public PaperViewDTO(NodeList authors, NodeList title, NodeList status, String id) {
		for (int idx = 0, len = authors.getLength(); idx < len; idx++) {
			this.authors.add(authors.item(idx).getTextContent());
		}
		this.title = title.item(0).getTextContent();
		this.status = status.item(0).getTextContent();
		this.id = id;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	
}