package com.papershare.papershare.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
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

import com.papershare.papershare.DTO.PaperUploadDTO;
import com.papershare.papershare.DTO.PaperViewDTO;
import com.papershare.papershare.DTO.SearchDTO;
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
	public ResponseEntity<PaperUploadDTO> uploadPaper(@RequestBody PaperUploadDTO dto) throws Exception {
		paperService.savePaper(dto);
		return new ResponseEntity<>(dto,HttpStatus.OK);

	}
	
	@GetMapping(value = "/{name}", produces = MediaType.TEXT_HTML_VALUE)
	public ResponseEntity<String> getSciPaperHTML(@PathVariable("name") String name) {
		String result = paperService.convertXMLtoHTML(name);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@GetMapping(value="/{name}/pdf", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Object> getPdf(@PathVariable("name") String name) throws Exception{
		Resource resource = paperService.getPdf(name);
		 return ResponseEntity.ok()
                 .contentType(MediaType.APPLICATION_OCTET_STREAM)
                 .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                 .body(resource);
	}
	@GetMapping(value = "/userPapers")
	public ResponseEntity<List<PaperViewDTO>> userPapers() {
		List<PaperViewDTO> paperList = paperService.findPapersByUser();
		return new ResponseEntity<List<PaperViewDTO>>(paperList, HttpStatus.OK);
	}
	
	@GetMapping(value = "/completedPapers")
	public ResponseEntity<List<PaperViewDTO>> completedPapers() {
		List<PaperViewDTO> paperList = paperService.findCompletedPapers();
		return new ResponseEntity<List<PaperViewDTO>>(paperList, HttpStatus.OK);
	}
	
	@GetMapping(value = "acceptPaper/{name}")
	public ResponseEntity<Boolean> acceptPaper(@PathVariable("name") String name) {
		paperService.changePaperStatus(name, "published");
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	@GetMapping(value = "rejectPaper/{name}")
	public ResponseEntity<Boolean> rejectPaper(@PathVariable("name") String name) {
		paperService.changePaperStatus(name, "rejected");
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	@GetMapping(value = "/publishedPapers")
	public ResponseEntity<List<PaperViewDTO>> getPublishedPapers() {
		List<PaperViewDTO> result = paperService.getPublishedPapers();
		return new ResponseEntity<List<PaperViewDTO>>(result, HttpStatus.OK);
	}
	
	@PostMapping(value = "/searchByMetadata")
	public ResponseEntity<List<PaperViewDTO>> searchByMetadata(@RequestBody SearchDTO dto) throws IOException {
		List<PaperViewDTO> result = paperService.searhByMetadata(dto);
		return new ResponseEntity<List<PaperViewDTO>>(result, HttpStatus.OK);
	}
}

	

