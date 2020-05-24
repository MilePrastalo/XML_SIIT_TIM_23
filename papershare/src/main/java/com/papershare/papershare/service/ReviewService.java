package com.papershare.papershare.service;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import com.papershare.papershare.dom.XSLTransformer;
import com.papershare.papershare.model.review.Review;
import com.papershare.papershare.repository.ReviewRepository;

@Service
public class ReviewService {

	private ReviewRepository repository;
	private XSLTransformer xslTransformer;

	private final String reviewXSL = "src/main/resources/data/xsl/review.xsl";

	public ReviewService(ReviewRepository repository, XSLTransformer xslTransformer) {
		this.repository = repository;
		this.xslTransformer = xslTransformer;
	}

	public Review findById(String id) {
		return repository.findById(id);
	}

	public String convertXMLtoHTML(String id) {
		Document xml = repository.findByName(id);
		return xslTransformer.convertXMLtoHTML(reviewXSL, xml);
	}

}
