package com.papershare.papershare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.papershare.papershare.DTO.PaperUploadDTO;
import com.papershare.papershare.service.ReviewService;
@RestController
@RequestMapping(value="api/reviews")
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	@GetMapping(value = "/testReview")
	public void review() {
		reviewService.findById("1");
	}
	
	@PostMapping(value = "",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addReview(@RequestBody PaperUploadDTO dto) {
		
	}
}
