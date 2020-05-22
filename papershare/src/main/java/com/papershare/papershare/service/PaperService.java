package com.papershare.papershare.service;

import org.springframework.stereotype.Service;

import com.papershare.papershare.DTO.PaperUploadDTO;
import org.w3c.dom.Document;

import com.papershare.papershare.dom.XSLTransformer;
import com.papershare.papershare.repository.PaperRepository;


@Service
public class PaperService {
	
	private final String scientificPublicatonXSL = "src/main/resources/data/xsl/scientificPaper.xsl";
	
	private PaperRepository paperRepository;
	private XSLTransformer xslTransformer;
	
	public PaperService(XSLTransformer xslTransformer, PaperRepository sciPaperRepository) {
		this.paperRepository = sciPaperRepository;
		this.xslTransformer = xslTransformer;
	}
	
	public String convertXMLtoHTML(String name) {
		Document xml = paperRepository.findScientificPaper(name);
		return xslTransformer.convertXMLtoHTML(scientificPublicatonXSL, xml);
	}
	public void savePaper(PaperUploadDTO dto) {
		
	}
}
