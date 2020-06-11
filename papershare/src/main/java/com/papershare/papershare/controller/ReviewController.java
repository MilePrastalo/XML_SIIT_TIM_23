package com.papershare.papershare.controller;

import org.springframework.http.MediaType;

import java.util.List;

import org.exist.http.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xmldb.api.base.XMLDBException;

import com.papershare.papershare.DTO.AddReviewDTO;
import com.papershare.papershare.DTO.ReviewDTO;
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
	public ResponseEntity<String> addReview(@RequestBody AddReviewDTO dto) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException, XMLDBException, NotFoundException {
		reviewService.addReview(dto);
		return new ResponseEntity<String>(
				"Successfully assigned review for: " + dto.getPublicationName() + " to: " + dto.getUsername(),
				HttpStatus.OK);
	}

	@GetMapping(value = "/userReviews")
	public ResponseEntity<List<ReviewDTO>> userPapers() throws XMLDBException {
		List<ReviewDTO> reviews = reviewService.findReviewsByUser();

		return new ResponseEntity<>(reviews, HttpStatus.OK);
	}

	@GetMapping(value = "/{name}")
	public ResponseEntity<String> getReviewHTML(@PathVariable("name") String name) {
		String result = reviewService.convertXMLtoHTML(name);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	@GetMapping(value="accept/{reviewId}")
	public void accept_review() {
		
	}
	@GetMapping(value="reject/{reviewId}")
	public void reject_review() {
		
	}
}
