package com.papershare.papershare.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.papershare.papershare.DTO.PaperUploadDTO;

@RestController(value = "api/papers")
public class PaperController {

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> uploadPaper(@RequestBody PaperUploadDTO dto) {

		return new ResponseEntity<Void>(HttpStatus.OK);

	}

}
