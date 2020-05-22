package com.papershare.papershare.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.papershare.papershare.DTO.PaperUploadDTO;
import com.papershare.papershare.service.PaperService;

@RestController(value = "api/papers")
public class PaperController {
	
	private PaperService paperService;
	
	public PaperController(PaperService paperService) {
		this.paperService = paperService;
	}

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> uploadPaper(@RequestBody PaperUploadDTO dto) {

		return new ResponseEntity<Void>(HttpStatus.OK);

	}
	
	@GetMapping(value = "/{name}")
	public void getSciPaperHTML(@PathVariable("name") String name) {
		String result = paperService.convertXMLtoHTML(name);
		System.out.println(result);
	}


}
