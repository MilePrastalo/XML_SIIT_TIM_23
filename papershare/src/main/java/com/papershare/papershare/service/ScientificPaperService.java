package com.papershare.papershare.service;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import com.papershare.papershare.dom.XSLTransformer;
import com.papershare.papershare.repository.ScientificPaperRepository;

@Service
public class ScientificPaperService {
	
	private static String scientificPublicatonXSL = "src/main/resources/data/xsl/scientificPaper.xsl";
	
	private ScientificPaperRepository sciPaperRepository;
	private XSLTransformer xslTransformer;
	
	public ScientificPaperService(XSLTransformer xslTransformer, ScientificPaperRepository sciPaperRepository) {
		this.sciPaperRepository = sciPaperRepository;
		this.xslTransformer = xslTransformer;
	}
	
	public String convertXMLtoHTML(String name) {
		Document xml = sciPaperRepository.findScientificPaper(name);
		return xslTransformer.convertXMLtoHTML(scientificPublicatonXSL, xml);
	}
}
