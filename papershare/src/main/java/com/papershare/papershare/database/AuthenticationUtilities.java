package com.papershare.papershare.database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Utilities to support and simplify examples.
 */
@Component
public class AuthenticationUtilities {


	@Value("${conn.host}")
	public String host;
	@Value("${conn.port}")
	public int port;
	@Value("${conn.user}")
	public String user;
	@Value("${conn.password}")
	public String password;
	@Value("${conn.driver}")
	public String driver;
	@Value("${conn.uri}")
	public String uri;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}


}
