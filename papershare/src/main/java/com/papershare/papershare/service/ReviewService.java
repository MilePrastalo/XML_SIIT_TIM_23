package com.papershare.papershare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;

import com.papershare.papershare.database.ExistManager;
import com.papershare.papershare.model.review.Review;
import com.papershare.papershare.repository.ReviewRepository;
@Service
public class ReviewService {
	private ReviewRepository repository;
		
	public ReviewService(ReviewRepository repository) {
		super();
		this.repository = repository;
	}

	public Review findById(String id) {
		return repository.findById(id);
	}
	
	public void addReview(String review) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		repository.save(review);
	}

}
