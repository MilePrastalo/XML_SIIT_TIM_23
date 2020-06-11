package com.papershare.papershare.repository;

import java.util.ArrayList;
import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import com.papershare.papershare.database.ExistManager;
import com.papershare.papershare.model.Chapter;
import com.papershare.papershare.model.review.Review;
import com.papershare.papershare.model.review.ReviewChapter;
import com.papershare.papershare.service.UserService;

@Repository
public class ReviewRepository {

	@Autowired
	private ExistManager existMenager;
	@Autowired
	private UserService userService;

	private String collectionId = "/db/paperShare/reviews";

	public String getCollectionId() {
		return collectionId;
	}

	public Review findById(String id) {

		String xPathExpression = String.format("/reviews/Review[@id='%s']", id);
		Review foundReview = new Review();
		String reviewer;
		String title = null;
		String author = "";
		String paperId = "";
		String summary = null;
		String date = "";
		Boolean done = false;
		ArrayList<ReviewChapter> chapters = new ArrayList<ReviewChapter>();
		try {
			ResourceSet result = existMenager.retrieve(collectionId, xPathExpression);
			if (result == null) {
				return null;
			}
			ResourceIterator i = result.getIterator();
			XMLResource resource = (XMLResource) result.getResource(0);
			while (i.hasMoreResources()) {
				try {
					resource = (XMLResource) i.nextResource();
					Node reviewNode = resource.getContentAsDOM();
					NamedNodeMap attributes = reviewNode.getAttributes();
					Node dateNode = attributes.getNamedItem("date");
					date = dateNode.getTextContent();
					Node doneNode = attributes.getNamedItem("done");
					done = Boolean.parseBoolean(doneNode.getTextContent());
					NodeList nodes = reviewNode.getChildNodes();
					for (int j = 0; j < nodes.getLength(); j++) {
						Node n = nodes.item(j);
						String name = n.getNodeName();
						if (name.equals("Reviewer")) {
							reviewer = n.getTextContent();
						} else if (name.equals("ReviewPaper")) {
							NamedNodeMap attr = n.getAttributes();
							Node paperIdNodes = attr.getNamedItem("paperId");
							paperId = paperIdNodes.getTextContent();
							NodeList paperNodes = n.getChildNodes();
							for (int k = 0; k < paperNodes.getLength(); k++) {
								Node paperN = paperNodes.item(k);
								String paperNName = paperN.getNodeName();
								if (paperNName.equals("paperTitle")) {
									title = paperN.getTextContent();
								} else if (paperNName.equals("paperAuthorUsername")) {
									author = paperN.getTextContent();
								} else if (paperNName.equals("chapters")) {
									NodeList chapterNodes = paperN.getChildNodes();
									for (int ii = 0; ii < chapterNodes.getLength(); ii++) {
										Node chapterNode = chapterNodes.item(ii);
										String chapterName = chapterNode.getNodeName();
										if (chapterName.equals("Chapter")) {
											Chapter c = new Chapter();
											c.setId(chapterNode.getChildNodes().item(0).getTextContent());
											ReviewChapter reviewChapter = new ReviewChapter();
											reviewChapter.setChapter(c);
											reviewChapter.setChapterReview(
													chapterNode.getChildNodes().item(0).getTextContent());
											chapters.add(reviewChapter);
										}
									}

								} else if (paperNName.equals("summary")) {
									summary = paperN.getNodeName();
								}
							}
						}
					}
					foundReview.setPaperTitle(title);
					foundReview.setReviewChapters(chapters);
					foundReview.setSummary(summary);
					foundReview.setUser(userService.findOneByUsername(author));
					foundReview.setDate(date);
					foundReview.setDone(done);
					foundReview.setPaperId(paperId);

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
		return foundReview;
	}

	public Document findByName(String name) {
		Document document = null;
		try {
			XMLResource xmlResource = existMenager.load(collectionId, name);
			document = (Document) xmlResource.getContentAsDOM();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return document;
	}

	public ResourceSet findReviews(String xPathExpression) {
		ResourceSet result = null;
		try {
			result = existMenager.retrieve(collectionId, xPathExpression);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String save(String xmlEntity, String title)
			throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		existMenager.storeXMLFromText(collectionId, title, xmlEntity);
		return "OK";
	}
	
	//Will replace review with new
	public String updateReview(String xmlEntity, String title)
			throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		existMenager.update(0, collectionId, title, "/", xmlEntity);
		return "OK";
	}
}
