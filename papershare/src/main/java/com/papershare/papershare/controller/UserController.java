package com.papershare.papershare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xmldb.api.base.XMLDBException;

import com.papershare.papershare.database.ExistManager;

@RestController
public class UserController {

	@Autowired
	public ExistManager existManager;

	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	@CrossOrigin
	public void store() throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		System.out.println(existManager);
		existManager.store("/db/sample/library", "instance1.xml", "./src/main/resources/data/instance1.xml");
	}

}
