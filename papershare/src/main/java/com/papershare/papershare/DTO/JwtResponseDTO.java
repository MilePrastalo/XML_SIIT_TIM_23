package com.papershare.papershare.DTO;

public class JwtResponseDTO {
	private String token;

	public JwtResponseDTO() {
	}

	public JwtResponseDTO(String jwttoken) {
		this.token = jwttoken;
	}

	public String getToken() {
		return this.token;
	}
}
