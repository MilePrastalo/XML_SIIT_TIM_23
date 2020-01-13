package com.papershare.papershare.controller;

import org.exist.xquery.functions.util.GetNodeById;
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

import com.papershare.papershare.DTO.StoreDTO;
import com.papershare.papershare.DTO.UpdateDTO;
import com.papershare.papershare.DTO.XPathRetrieveDTO;
import com.papershare.papershare.database.ExistManager;
import com.papershare.papershare.model.TUser;
import com.papershare.papershare.service.UserService;

@RestController
public class UserController {

	@Autowired
	public ExistManager existManager;
	
	@Autowired
	public UserService userService;

	@RequestMapping(value = "/store", method = RequestMethod.POST)
	@CrossOrigin
	public void store(@RequestBody StoreDTO dto)
			throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		existManager.store(dto.getCollectionId(), dto.getName(), dto.getPath());
	}

	@RequestMapping(value = "/get", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public void get(@RequestBody XPathRetrieveDTO dto)
			throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		existManager.retrieve(dto.getCollectionId(), dto.getXpath());
	}

	@RequestMapping(value = "/remove", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public void remove(@RequestBody XPathRetrieveDTO dto)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		existManager.remove(dto.getCollectionId(), dto.getXpath());
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public void update(@RequestBody UpdateDTO dto)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		existManager.update(dto.getCollectionId(), dto.getDocumentId(), dto.getContextXPath(), dto.getPatch());
	}
	
	@RequestMapping(value = "/testFindByUsername", method = RequestMethod.GET)
	@CrossOrigin
	public void test() {
		String username = "mira";
		TUser user = userService.findOneByUsername(username);
		if (user == null) {
			System.out.println("USER DOESN'T EXIST");	
		}else {
			System.out.println("\tFIRST NAME = " + user.getFirstName());
			System.out.println("\tLAST NAME = " + user.getLastName());
		}
	}
}
