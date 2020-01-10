package com.papershare.papershare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xmldb.api.base.XMLDBException;

import com.papershare.papershare.database.ExistManager;

import DTO.XPathRetrieveDTO;

@RestController
public class UserController {

	@Autowired
	public ExistManager existManager;

	@RequestMapping(value = "/storeUser", method = RequestMethod.GET)
	@CrossOrigin
	public void store() throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		existManager.store("/db/sample/library", "instance1.xml", "./src/main/resources/data/instance1.xml");
	}

	@RequestMapping(value = "/getUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public void get(@RequestBody XPathRetrieveDTO dto)
			throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		existManager.retrieve(dto.getCollectionId(), dto.getXpath());
	}
}
