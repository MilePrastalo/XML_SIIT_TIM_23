package com.papershare.papershare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.papershare.papershare.model.review.Review;
import com.papershare.papershare.repository.ReviewRepository;
@Service
public class ReviewService {
	@Autowired
	ReviewRepository repository;
	
	public Review findById(String id) {
		return repository.findById(id);
	}

}
