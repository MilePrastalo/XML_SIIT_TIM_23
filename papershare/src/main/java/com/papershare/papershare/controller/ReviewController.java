package com.papershare.papershare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.papershare.papershare.DTO.PaperUploadDTO;
import com.papershare.papershare.service.ReviewService;

@RestController
@RequestMapping("/api/review")
@CrossOrigin
public class ReviewController {

	private ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	@GetMapping(value = "/testReview")
	public void review() {
		reviewService.findById("1");
	}

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addReview(@RequestBody PaperUploadDTO dto) {

	}

	@GetMapping(value = "/{name}")
	public ResponseEntity<String> getReviewHTML(@PathVariable("name") String name) {
		String result = reviewService.convertXMLtoHTML(name);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
}
