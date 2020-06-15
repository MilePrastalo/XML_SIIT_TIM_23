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
	


	@GetMapping(value = "/saveRDF")
	public void saveRdf() throws IOException {
		FusekiWriter.saveRDF();
	}
	
}
