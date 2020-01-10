package com.papershare.papershare.database;

import java.io.File;
import java.util.Scanner;

import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;

@Service
public class ExistManager {

	private static final String TARGET_NAMESPACE = "https://github.com/MilePrastalo/XML_SIIT_TIM_23";

	@Autowired
	AuthenticationUtilities authUtil;

	public void createConnection()
			throws ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
		Class<?> cl = Class.forName(authUtil.getDriver());

		// encapsulation of the database driver functionality
		Database database = (Database) cl.newInstance();
		database.setProperty("create-database", "true");

		// entry point for the API which enables you to get the Collection reference
		DatabaseManager.registerDatabase(database);
	}

	public void closeConnection(Collection col, XMLResource res) {
		// don't forget to cleanup
		if (res != null) {
			try {
				((EXistResource) res).freeResources();
			} catch (XMLDBException xe) {
				xe.printStackTrace();
			}
		}

		if (col != null) {
			try {
				col.close();
			} catch (XMLDBException xe) {
				xe.printStackTrace();
			}
		}
	}

	public void store(String collectionId, String documentId, String filePath)
			throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		createConnection();

		Collection col = null;
		XMLResource res = null;
		try {

			System.out.println("[INFO] Retrieving the collection: " + collectionId);
			col = getOrCreateCollection(collectionId, 0);

			/*
			 * create new XMLResource with a given id an id is assigned to the new resource
			 * if left empty (null)
			 */
			System.out.println("[INFO] Inserting the document: " + documentId);
			res = (XMLResource) col.createResource(documentId, XMLResource.RESOURCE_TYPE);

			File f = new File(filePath);

			if (!f.canRead()) {
				System.out.println("[ERROR] Cannot read the file: " + filePath);
				return;
			}

			res.setContent(f);
			System.out.println("[INFO] Storing the document: " + res.getId());

			col.storeResource(res);
			System.out.println("[INFO] Done.");

		} finally {

			closeConnection(col, res);
		}
	}

	public void retrieve(String collectionUri, String xpathExp)
			throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		createConnection();
		
		Collection col = null;
		try {
			col = DatabaseManager.getCollection(authUtil.getUri() + collectionUri);

			// get an instance of xpath query service
			XPathQueryService xpathService = (XPathQueryService) col.getService("XPathQueryService", "1.0");
			xpathService.setProperty("indent", "yes");

			// make the service aware of namespaces, using the default one
			//xpathService.setNamespace("", TARGET_NAMESPACE);

			// execute xpath expression
			System.out.println("[INFO] Invoking XPath query service for: " + xpathExp);
			ResourceSet result = xpathService.query(xpathExp);

			// handle the results
			System.out.println("[INFO] Handling the results... ");

			ResourceIterator i = result.getIterator();
			Resource res = null;

			while (i.hasMoreResources()) {

				try {
					res = i.nextResource();
					System.out.println(res.getContent());

				} finally {

					// don't forget to cleanup resources
					try {
						((EXistResource) res).freeResources();
					} catch (XMLDBException xe) {
						xe.printStackTrace();
					}
				}
			}
			// Done
			System.out.println("[INFO] Done! ");

		} finally {

			// don't forget to cleanup
			if (col != null) {
				try {
					col.close();
				} catch (XMLDBException xe) {
					xe.printStackTrace();
				}
			}
		}
	}

	private Collection getOrCreateCollection(String collectionUri, int pathSegmentOffset) throws XMLDBException {

		Collection col = DatabaseManager.getCollection(authUtil.getUri() + collectionUri, authUtil.getUser(),
				authUtil.getPassword());

		// create the collection if it does not exist
		if (col == null) {

			if (collectionUri.startsWith("/")) {
				collectionUri = collectionUri.substring(1);
			}

			String pathSegments[] = collectionUri.split("/");

			if (pathSegments.length > 0) {
				StringBuilder path = new StringBuilder();

				for (int i = 0; i <= pathSegmentOffset; i++) {
					path.append("/" + pathSegments[i]);
				}

				Collection startCol = DatabaseManager.getCollection(authUtil.getUri() + path, authUtil.getUser(),
						authUtil.getPassword());

				if (startCol == null) {

					// child collection does not exist

					String parentPath = path.substring(0, path.lastIndexOf("/"));
					Collection parentCol = DatabaseManager.getCollection(authUtil.getUri() + parentPath,
							authUtil.getUser(), authUtil.getPassword());

					CollectionManagementService mgt = (CollectionManagementService) parentCol
							.getService("CollectionManagementService", "1.0");

					System.out.println("[INFO] Creating the collection: " + pathSegments[pathSegmentOffset]);
					col = mgt.createCollection(pathSegments[pathSegmentOffset]);

					col.close();
					parentCol.close();

				} else {
					startCol.close();
				}
			}
			return getOrCreateCollection(collectionUri, ++pathSegmentOffset);
		} else {
			return col;
		}
	}

}
