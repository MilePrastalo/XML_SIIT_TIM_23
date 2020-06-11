package com.papershare.papershare.service;

import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.exist.http.NotFoundException;
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
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.papershare.papershare.DTO.AddReviewDTO;
import com.papershare.papershare.DTO.ReviewDTO;
import com.papershare.papershare.DTO.SendReviewDTO;
import com.papershare.papershare.database.ExistManager;
import com.papershare.papershare.dom.DOMParser;
import com.papershare.papershare.dom.XSLTransformer;
import com.papershare.papershare.model.TUser;
import com.papershare.papershare.model.review.Review;
import com.papershare.papershare.repository.PaperRepository;
import com.papershare.papershare.repository.ReviewRepository;
import com.papershare.papershare.repository.UserRepository;

@Service
public class ReviewService {
	private final String reviewXSL = "src/main/resources/data/xsl/review.xsl";

	private ReviewRepository reviewRepository;
	private XSLTransformer xslTransformer;
	private UserRepository userRepository;
	private PaperRepository paperRepository;
	private ExistManager existManager;
	private DOMParser domParser;

	public ReviewService(ReviewRepository repository, XSLTransformer xslTransformer, UserRepository userRepository,
			PaperRepository paperRepository, ExistManager existManager, DOMParser domParser) {
		this.reviewRepository = repository;
		this.xslTransformer = xslTransformer;
		this.userRepository = userRepository;
		this.paperRepository = paperRepository;
		this.existManager = existManager;
		this.domParser = domParser;
	}

	public Review findById(String id) {
		return reviewRepository.findById(id);
	}

	public void addReview(AddReviewDTO dto) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, XMLDBException, NotFoundException {

		TUser user = userRepository.findOneByUsername(dto.getUsername());
		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", dto.getUsername()));
		}

		Document xml = paperRepository.findScientificPaper(dto.getPublicationName());
		if (xml == null) {
			throw new NotFoundException("Scientific paper with name: " + dto.getPublicationName() + " not found.");
		}

		long submissionDate = System.currentTimeMillis();
		String id = "rev" + submissionDate + ".xml";

		String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		String review = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<review xmlns=\"https://github.com/MilePrastalo/XML_SIIT_TIM_23\" xmlns:rv=\"https://github.com/MilePrastalo/XML_SIIT_TIM_23\">\r\n"
				+ "    <metadata>\r\n" + "     	<reviewer>" + dto.getUsername() + "</reviewer>\r\n"
				+ "        <publicationName>" + dto.getPublicationName() + "</publicationName>\r\n"
				+ "        <submissionDate>" + today + "</submissionDate>\r\n" + "    </metadata>\r\n"
				+ "    <body>\r\n" + "        <criteriaEvaluation>\r\n"
				+ "            <relevanceOfResearchProblem></relevanceOfResearchProblem>\r\n"
				+ "            <introduction></introduction>\r\n"
				+ "            <conceptualQuality></conceptualQuality>\r\n"
				+ "            <methodologicalQuality></methodologicalQuality>\r\n"
				+ "            <results></results>\r\n" + "            <discussion></discussion>\r\n"
				+ "            <readability></readability>\r\n" + "        </criteriaEvaluation>\r\n"
				+ "        <overallEvaluation></overallEvaluation>\r\n" + "        <commentsToAuthor>\r\n"
				+ "        </commentsToAuthor>\r\n" + "        <commentsToEditor></commentsToEditor>\r\n"
				+ "    </body>\r\n" + "</review>";
		reviewRepository.save(review, id);

		String xmlPatch = "reviewing";

		existManager.update(0, paperRepository.getCollectionId(), dto.getPublicationName() + ".xml",
				"/ScientificPaper/status", xmlPatch);
	}

	public ArrayList<ReviewDTO> findReviewsByUser() throws XMLDBException {
		String username = getLoggedUser();
		String xPathExpression = String.format("/review[metadata/reviewer='%s']", username);
		ResourceSet result = reviewRepository.findReviews(xPathExpression);
		System.out.println(result.getSize());
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
				NodeList publicationName = document.getElementsByTagName("publicationName");
				NodeList reviewer = document.getElementsByTagName("reviewer");
				NodeList submissionDate = document.getElementsByTagName("submissionDate");
				reviews.add(new ReviewDTO(resource.getDocumentId() ,publicationName.item(0).getTextContent(), reviewer.item(0).getTextContent(),
						submissionDate.item(0).getTextContent()));
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

	public void acceptReview(String reviewId) {
		
	}
	
	public void rejectReview(String reviewId) {
		
	}

	private String getLoggedUser() {
		String username = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			username = authentication.getName();
		}
		return username;
	}
	
	//Reviewer sends changed review
	public void sendReview(SendReviewDTO review) throws ParserConfigurationException, SAXException, IOException, TransformerException, ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		Document document = domParser.buildDocumentFromText(review.getText());
		NodeList nodeList = document.getElementsByTagName("metadata");
		Element metadata = (Element) nodeList.item(0);
		Element status = (Element) metadata.getElementsByTagName("status");
		status.setTextContent("submitted");
		
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
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

		transformer.transform(new DOMSource(xml), new StreamResult(sw));
		return sw.toString();
	}

}
