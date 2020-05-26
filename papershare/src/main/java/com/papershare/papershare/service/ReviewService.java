package com.papershare.papershare.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.exist.http.NotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;
import org.w3c.dom.Document;

import com.papershare.papershare.DTO.AddReviewDTO;
import com.papershare.papershare.dom.XSLTransformer;
import com.papershare.papershare.model.TUser;
import com.papershare.papershare.model.review.Review;
import com.papershare.papershare.repository.PaperRepository;
import com.papershare.papershare.repository.ReviewRepository;
import com.papershare.papershare.repository.UserRepository;

@Service
public class ReviewService {
	private final String reviewXSL = "src/main/resources/data/xsl/review.xsl";

	private ReviewRepository repository;
	private XSLTransformer xslTransformer;
	private UserRepository userRepository;
	private PaperRepository paperRepository;

	public ReviewService(ReviewRepository repository, XSLTransformer xslTransformer, UserRepository userRepository,
			PaperRepository paperRepository) {
		this.repository = repository;
		this.xslTransformer = xslTransformer;
		this.userRepository = userRepository;
		this.paperRepository = paperRepository;
	}

	public Review findById(String id) {
		return repository.findById(id);
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
		repository.save(review, id);
	}

	public String convertXMLtoHTML(String id) {
		Document xml = repository.findByName(id);
		return xslTransformer.convertXMLtoHTML(reviewXSL, xml);
	}

}
