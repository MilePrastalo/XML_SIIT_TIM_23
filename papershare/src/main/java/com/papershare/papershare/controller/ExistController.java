package com.papershare.papershare.controller;

import java.io.IOException;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xmldb.api.base.XMLDBException;

import com.papershare.papershare.DTO.StoreDTO;
import com.papershare.papershare.DTO.UpdateDTO;
import com.papershare.papershare.DTO.XPathRetrieveDTO;
import com.papershare.papershare.database.ExistManager;

@RestController
@RequestMapping("/api/exist")
@CrossOrigin
public class ExistController {
	@Autowired
	public ExistManager existManager;

	@Value("${abs.path}")
	private String absolutePath;

	@RequestMapping(value = "/store", method = RequestMethod.POST)
	public void store(@RequestBody StoreDTO dto)
			throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		existManager.storeXML(dto.getCollectionId(), dto.getName(), dto.getPath());
	}

	@RequestMapping(value = "/get", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void get(@RequestBody XPathRetrieveDTO dto)
			throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		existManager.retrieve(dto.getCollectionId(), dto.getXpath());
	}

	@RequestMapping(value = "/remove", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void remove(@RequestBody XPathRetrieveDTO dto)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		existManager.remove(dto.getCollectionId(), dto.getXpath());
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody UpdateDTO dto)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		existManager.update(0, dto.getCollectionId(), dto.getDocumentId(), dto.getContextXPath(), dto.getPatch());
	}

	@GetMapping(value = "/initiateData")
	public void initiateDate()
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException, IOException {
		Resource resource = new ClassPathResource("data");
		URI a = resource.getURI();

		existManager.storeXML("/db/paperShare/users", "Users.xml", a.getPath() + "/Users.xml");
		existManager.storeXML("/db/paperShare/CoverLetters", "CoverLetters.xml", a.getPath() + "/CoverLetters.xml");
		existManager.store("/db/paperShare/reviews", "rev1.xml", a.getPath() + "/data/rev1.xml");
		existManager.store("/db/paperShare/reviews", "rev2.xml", a.getPath() + "/data/rev2.xml");
		existManager.store("/db/paperShare/reviews", "rev3.xml", a.getPath() + "/data/rev3.xml");
	}
}
