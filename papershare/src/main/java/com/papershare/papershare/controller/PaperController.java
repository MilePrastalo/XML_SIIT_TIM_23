package com.papershare.papershare.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.papershare.papershare.DTO.PaperUploadDTO;
import com.papershare.papershare.service.PaperService;

@RestController()
@RequestMapping(value = "api/papers")
@CrossOrigin()
public class PaperController {
	private PaperService paperService;

	public PaperController(PaperService paperService) {
		super();
		this.paperService = paperService;
	}

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<Void> uploadPaper(@RequestBody PaperUploadDTO dto) {
		paperService.savePaper(dto);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

}
