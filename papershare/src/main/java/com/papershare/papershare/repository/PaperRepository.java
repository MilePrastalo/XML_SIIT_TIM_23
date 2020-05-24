package com.papershare.papershare.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import com.papershare.papershare.database.ExistManager;

@Repository
public class PaperRepository {

	@Autowired
	private ExistManager existMenager;

	private String collectionId = "/db/paperShare/ScientificPapers";

	public Document findScientificPaper(String name) {
		Document document = null;
		try {
			XMLResource xmlResource = existMenager.load(collectionId, name);
			document = (Document) xmlResource.getContentAsDOM();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return document;
	}

	public String save(String xmlEntity, String title)
			throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		existMenager.store(collectionId, title, xmlEntity);

		return "OK";
	}

}
