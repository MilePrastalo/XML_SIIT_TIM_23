package com.papershare.papershare.controller;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

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

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<PaperUploadDTO> uploadPaper(@RequestBody PaperUploadDTO dto) throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, TransformerException, XMLDBException {
		paperService.savePaper(dto);
		return new ResponseEntity<>(dto,HttpStatus.OK);

	}
	
	@GetMapping(value = "/{name}", produces = MediaType.TEXT_HTML_VALUE)
	public ResponseEntity<String> getSciPaperHTML(@PathVariable("name") String name) {
		String result = paperService.convertXMLtoHTML(name);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
}

	

