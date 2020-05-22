package com.papershare.papershare.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.papershare.papershare.service.ScientificPaperService;

@RestController
@RequestMapping("/sciPapers")
@CrossOrigin
public class ScientificPaperController {
	
	private ScientificPaperService sciPaperService;
	
	public ScientificPaperController(ScientificPaperService sciPaperService) {
		this.sciPaperService = sciPaperService;
	}
	
	@GetMapping(value = "/{name}")
	public void getSciPaperHTML(@PathVariable("name") String name) {
		String result = sciPaperService.convertXMLtoHTML(name);
		System.out.println(result);
	}

}
