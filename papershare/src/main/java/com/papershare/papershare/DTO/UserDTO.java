package com.papershare.papershare.DTO;

public class UserDTO {

	private String username;
	private String password;
	private String email;
	private String firstName;
	private String lastNAme;
	private String title;
	
	public UserDTO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public UserDTO(String username, String password, String email, String firstName, String lastNAme, String title) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastNAme = lastNAme;
		this.title = title;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastNAme() {
		return lastNAme;
	}

	public void setLastNAme(String lastNAme) {
		this.lastNAme = lastNAme;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
}