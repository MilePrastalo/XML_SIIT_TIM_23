package com.papershare.papershare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.papershare.papershare.service.ReviewService;
@RestController
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	@GetMapping(value = "/testReview")
	public void review() {
		reviewService.findById("1");
	}
}
