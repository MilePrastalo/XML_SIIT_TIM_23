package com.papershare.papershare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.xmldb.api.base.XMLDBException;

@SpringBootApplication
public class PapershareApplication {
	

	public static void main(String[] args) throws XMLDBException {
		SpringApplication.run(PapershareApplication.class, args);
	}

}