package com.papershare.papershare.service;

import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.exist.http.NotFoundException;
import org.springframework.mail.MailException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.papershare.papershare.DTO.AddReviewDTO;
import com.papershare.papershare.DTO.ReviewDTO;
import com.papershare.papershare.DTO.SendReviewDTO;
import com.papershare.papershare.database.ExistManager;
import com.papershare.papershare.dom.DOMParser;
import com.papershare.papershare.dom.XSLTransformer;
import com.papershare.papershare.exception.UserAlreadyAssignedException;
import com.papershare.papershare.model.TUser;
import com.papershare.papershare.model.review.Review;
import com.papershare.papershare.repository.PaperRepository;
import com.papershare.papershare.repository.ReviewRepository;
import com.papershare.papershare.repository.UserRepository;

@Service
public class ReviewService {
	private final String reviewXSL = "src/main/resources/data/xsl/review.xsl";
	private final String unitedReviewXSL = "src/main/resources/data/xsl/unitedReview.xsl";

	private ReviewRepository reviewRepository;
	private XSLTransformer xslTransformer;
	private UserRepository userRepository;
	private PaperRepository paperRepository;
	private ExistManager existManager;
	private DOMParser domParser;
	private EmailService emailService;
	private PaperService paperService;

	public ReviewService(ReviewRepository repository, XSLTransformer xslTransformer, UserRepository userRepository,
			PaperRepository paperRepository, ExistManager existManager, DOMParser domParser, EmailService emailService, PaperService paperService) {
		this.reviewRepository = repository;
		this.xslTransformer = xslTransformer;
		this.userRepository = userRepository;
		this.paperRepository = paperRepository;
		this.existManager = existManager;
		this.domParser = domParser;
		this.emailService = emailService;
		this.paperService = paperService;
	}

	public Review findById(String id) {
		return reviewRepository.findById(id);
	}

	public void addReview(AddReviewDTO dto) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, XMLDBException, NotFoundException, MailException, InterruptedException {

		if (!dto.getPublicationName().endsWith(".xml")) {
			dto.setPublicationName(dto.getPublicationName() + ".xml");
		}

		TUser user = userRepository.findOneByUsername(dto.getUsername());
		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", dto.getUsername()));
		}

		Document xml = paperRepository.findScientificPaper(dto.getPublicationName());
		if (xml == null) {
			throw new NotFoundException("Scientific paper with name: " + dto.getPublicationName() + " not found.");
		}

		// Checks if given user is already assigned for the same scientific paper.
		String xPathExpression = String.format("/review[metadata/paperName='%s' and metadata/reviewer='%s']",
				dto.getPublicationName(), dto.getUsername());
		ResourceSet alreadyAssigned = findReviews(xPathExpression);
		if (alreadyAssigned.getSize() > 0) {
			throw new UserAlreadyAssignedException("User with username: " + dto.getUsername()
					+ " is already assigned for review on paper: " + dto.getPublicationName());
		}

		long submissionDate = System.currentTimeMillis();
		String id = "rev" + submissionDate + ".xml";

		String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		String review = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<review xmlns=\"https://github.com/MilePrastalo/XML_SIIT_TIM_23\" xmlns:rv=\"https://github.com/MilePrastalo/XML_SIIT_TIM_23\">\r\n"
				+ "    <metadata>\r\n" + "     	<reviewer>" + dto.getUsername() + "</reviewer>\r\n"
				+ "        <paperName>" + dto.getPublicationName() + "</paperName>\r\n"
				+ "		   <status>created</status>\r\n" + "        <submissionDate>" + today + "</submissionDate>\r\n"
				+ "    </metadata>\r\n" + "    <body>\r\n" + "        <criteriaEvaluation>\r\n"
				+ "            <abstract> </abstract>\r\n" + "            <relevance> </relevance>\r\n"
				+ "            <readability> </readability>\r\n" + "            <methodology> </methodology>\r\n"
				+ "            <results> </results>\r\n" + "        </criteriaEvaluation>\r\n"
				+ "        <overallEvaluation></overallEvaluation>\r\n" + "        <chapterReviews>\r\n"
				+ "        </chapterReviews>\r\n" + "        <commentsToEditor> </commentsToEditor>\r\n"
				+ "    </body>\r\n" + "</review>";
		reviewRepository.save(review, id);

		paperRepository.modifyPaper(dto.getPublicationName(), "/ScientificPaper/status", "reviewing");
		
		emailService.assignReviewForPaper(dto.getUsername(), dto.getPublicationName(), user.getEmail());
	}

	public ArrayList<ReviewDTO> findReviewsByUser() throws XMLDBException {
		String username = getLoggedUser();
		String xPathExpression = String.format("/review[metadata/reviewer='%s']", username);
		ResourceSet result = reviewRepository.findReviews(xPathExpression);
		System.out.println(result.getSize());
		ArrayList<ReviewDTO> reviews = extractDataFromReviews(result);

		return reviews;
	}

	public ArrayList<ReviewDTO> findSubmittedReviews() throws XMLDBException {
		String xPathExpression = String.format("/review[metadata/status='submitted']");
		ResourceSet result = reviewRepository.findReviews(xPathExpression);
		System.out.println(result.getSize());
		ArrayList<ReviewDTO> reviews = extractDataFromReviews(result);

		return reviews;
	}

	public ArrayList<ReviewDTO> findReviewsByPaper(String publicationName) throws XMLDBException {
		String xPathExpression = String.format("/review[metadata/paperName='%s']", publicationName);
		ResourceSet result = reviewRepository.findReviews(xPathExpression);
		ArrayList<ReviewDTO> reviews = extractDataFromReviews(result);
		return reviews;
	}

	private ArrayList<ReviewDTO> extractDataFromReviews(ResourceSet resourceSet) {
		ArrayList<ReviewDTO> reviews = new ArrayList<ReviewDTO>();
		ResourceIterator i;
		try {
			i = resourceSet.getIterator();
			while (i.hasMoreResources()) {
				XMLResource resource = (XMLResource) i.nextResource();
				Document document = domParser.buildDocumentFromText(resource.getContent().toString());
				NodeList publicationName = document.getElementsByTagName("paperName");
				NodeList reviewer = document.getElementsByTagName("reviewer");
				NodeList status = document.getElementsByTagName("status");
				NodeList submissionDate = document.getElementsByTagName("submissionDate");
				reviews.add(new ReviewDTO(resource.getDocumentId(), publicationName.item(0).getTextContent(),
						reviewer.item(0).getTextContent(), submissionDate.item(0).getTextContent(),
						status.item(0).getTextContent()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reviews;
	}

	public ResourceSet findReviews(String xPathExpression) {
		ResourceSet result = null;
		try {
			result = existManager.retrieve(reviewRepository.getCollectionId(), xPathExpression);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String convertXMLtoHTML(String id) {
		Document xml = reviewRepository.findByName(id);
		return xslTransformer.convertXMLtoHTML(reviewXSL, xml);
	}
	
	public String convertUnitedReviewToHTML(String paperName) {
		if (!paperName.endsWith(".xml")) {
			paperName = paperName + ".xml";
		}
		
		Document xml = reviewRepository.findByName(paperName);
		return xslTransformer.convertXMLtoHTML(unitedReviewXSL, xml);
	}

	public void acceptReview(String name) throws MailException, InterruptedException {
		if (!name.endsWith(".xml")) {
			name = name + ".xml";
		}

		String targetElement = "/review/metadata/status";
		String xmlFragmet = "accepted";
		reviewRepository.modifyReview(name, targetElement, xmlFragmet);
		
		String reviewer = getLoggedUser();
		this.emailService.acceptReviewForPaper(reviewer, name);
	}

	public void rejectReview(String name)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			XMLDBException, MailException, InterruptedException {
		if (!name.endsWith(".xml")) {
			name = name + ".xml";
		}
		reviewRepository.removeReview(name);
		
		String reviewer = getLoggedUser();
		emailService.rejectReviewForPaper(reviewer, name);

	}

	public void publishReview(String reviewId) throws MailException, InterruptedException {

		if (!reviewId.endsWith(".xml")) {
			reviewId = reviewId + ".xml";
		}

		String targetElement = "/review/metadata/status";
		String xmlFragmet = "submitted";
		reviewRepository.modifyReview(reviewId, targetElement, xmlFragmet);
		
		String reviewer = getLoggedUser();
		emailService.finisheddReviewForPaper(reviewer, reviewId);
	}

	private String getLoggedUser() {
		String username = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			username = authentication.getName();
		}
		return username;
	}

	// Reviewer sends changed review
	public void sendReview(SendReviewDTO review)
			throws ParserConfigurationException, SAXException, IOException, TransformerException,
			ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		System.out.println(review.getText());
		Document document = domParser.buildDocumentFromText(review.getText());
		StringWriter sw = new StringWriter();
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

		transformer.transform(new DOMSource(document), new StreamResult(sw));

		reviewRepository.updateReview(sw.toString(), review.getReviewName());
	}

	public String getReviewAsText(String reviewName) throws TransformerException {
		Document xml = reviewRepository.findByName(reviewName);
		StringWriter sw = new StringWriter();
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

		transformer.transform(new DOMSource(xml), new StreamResult(sw));
		System.out.println(sw.toString());
		return sw.toString();
	}

	public void sendReviewsToAuthor(String paperName) throws MailException, InterruptedException {
		String xPathExpression = String.format("/review[metadata/paperName='%s']", paperName);
		ResourceSet result = reviewRepository.findReviews(xPathExpression);
		String reviews = "";
		ResourceIterator i;
		try {
			i = result.getIterator();
			while (i.hasMoreResources()) {
				XMLResource resource = (XMLResource) i.nextResource();
				Document document = domParser.buildDocumentFromText(resource.getContent().toString());
				String status = document.getElementsByTagName("status").item(0).getTextContent();
				NodeList body = document.getElementsByTagName("body");
				
				String unitedReview = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
						+ "<unitedReviews xmlns=\"https://github.com/MilePrastalo/XML_SIIT_TIM_23\" xmlns:rv=\"https://github.com/MilePrastalo/XML_SIIT_TIM_23\">\r\n"
						+ "<paperName>" + paperName + "</paperName>\r\n"
						+ "<reviews>\r\n"   
						+ "</reviews>\r\n"
						+ "</unitedReviews>";
				reviewRepository.save(unitedReview, paperName);
				
				if (status.equals("submitted")) {
					StringWriter sw = new StringWriter();
					Transformer serializer = TransformerFactory.newInstance().newTransformer();
					serializer.transform(new DOMSource(body.item(0)), new StreamResult(sw));
					reviews += sw.toString().replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "") + "\r\n";
					sw.close();
				}
				
				reviewRepository.removeReview(resource.getDocumentId());
			}
	
			reviewRepository.uniteReviews(paperName, reviews);
			
			paperRepository.modifyPaper(paperName, "/ScientificPaper/status", "revision");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<String> authorsUsernames = paperService.findAuthorsByPaper(paperName);

		for (String username : authorsUsernames) {

			TUser user = userRepository.findOneByUsername(username);
			emailService.unitedReviewForPaper(paperName, user.getEmail());
		}
	}

}
