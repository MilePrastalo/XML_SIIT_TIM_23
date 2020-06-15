package com.papershare.papershare.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.papershare.papershare.rdf.FusekiWriter;

@RestController
@RequestMapping("/api/fuseki")
@CrossOrigin
public class FusekiController {
	
	private FusekiWriter fusekiWriter;
	
	public FusekiController(FusekiWriter fusekiWriter) {
		this.fusekiWriter = fusekiWriter;
	}

	@GetMapping(value = "/saveRDF")
	public void saveRdf() throws IOException {
		fusekiWriter.saveRDF();
	}
	
}
