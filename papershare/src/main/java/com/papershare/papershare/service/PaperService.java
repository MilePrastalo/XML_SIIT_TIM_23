package com.papershare.papershare.service;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.stereotype.Service;

import com.papershare.papershare.DTO.PaperUploadDTO;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

import com.papershare.papershare.dom.DOMParser;
import com.papershare.papershare.dom.XSLTransformer;
import com.papershare.papershare.repository.PaperRepository;

@Service
public class PaperService {

	private final String scientificPublicatonXSL = "src/main/resources/data/xsl/scientificPaper.xsl";
	private final String paperSchema = "src/main/resources/data/scientificPaper.xsd";
	private DOMParser domParser;

	private PaperRepository paperRepository;
	private XSLTransformer xslTransformer;

	public PaperService(XSLTransformer xslTransformer, PaperRepository sciPaperRepository, DOMParser domParser) {
		this.paperRepository = sciPaperRepository;
		this.xslTransformer = xslTransformer;
		this.domParser = domParser;
	}

	public String convertXMLtoHTML(String name) {
		Document xml = paperRepository.findScientificPaper(name);
		return xslTransformer.convertXMLtoHTML(scientificPublicatonXSL, xml);
	}

	public void savePaper(PaperUploadDTO dto)
			throws ParserConfigurationException, SAXException, IOException, TransformerException,
			ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		Document document = domParser.buildDocumentFromText(dto.getText());
		NodeList nodeList = document.getElementsByTagName("ScientificPaper");
		Element sp = (Element) nodeList.item(0);
		NodeList ndTitle = document.getElementsByTagName("sci:title");
		String title = ndTitle.item(0).getTextContent();
		Element chaptersMain = (Element) (document.getElementsByTagName("Chapters")).item(0);
		NodeList chapters = chaptersMain.getElementsByTagName("sci:Chapter");
		Long currentMilli = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		for (int i = 0; i < chapters.getLength(); i++) {
			Element chapter = (Element) chapters.item(i);
			chapter.setAttribute("id", currentMilli + "" + i);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String recievedDate = sdf.format(new Date());
		sp.setAttribute("recieved_date", recievedDate);

		StringWriter sw = new StringWriter();
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

		transformer.transform(new DOMSource(document), new StreamResult(sw));

		paperRepository.save(sw.toString(), title);
		
		String coverLetter = "<coverLetter><authorUsername></authorUsername><Content>"+dto.getCoverLetter()+"</Content><title>"+title+"</title></coverLetter>";
		paperRepository.saveCoverLetter(coverLetter);
	}
}
