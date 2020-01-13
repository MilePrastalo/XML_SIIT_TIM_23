package com.papershare.papershare.repository;

import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.modules.XMLResource;

import com.papershare.papershare.database.ExistManager;
import com.papershare.papershare.model.TUser;

@Repository
public class UserRepository {

	@Autowired
	private ExistManager existMenager;

	private String collectionId = "/db/sample/library";

	public TUser findOneByUsername(String username) {
		
		String xPathExpression = String.format("/Users/user[username='%s']", username);
		TUser foundUser = null;
		try {
			ResourceSet result = existMenager.retrieve(collectionId, xPathExpression);
			if (result == null) {
				return null;
			}
			ResourceIterator i = result.getIterator();
			XMLResource resource = null;
			while (i.hasMoreResources()) {
				try {
					resource = (XMLResource) i.nextResource();
					foundUser = unmarshal(resource);

				} finally {
					try {
						((EXistResource) resource).freeResources();
					} catch (XMLDBException xe) {
						xe.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return foundUser;
	}

	public void store(String documentId, String filePath)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		existMenager.store(collectionId, documentId, filePath);
	}

	private TUser unmarshal(XMLResource resource) {
		TUser user = null;
		try {
			JAXBContext context = JAXBContext.newInstance(TUser.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			user = (TUser) unmarshaller.unmarshal(resource.getContentAsDOM());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

}
