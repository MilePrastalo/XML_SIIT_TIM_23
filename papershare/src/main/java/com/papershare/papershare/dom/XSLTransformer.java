package com.papershare.papershare.dom;

import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

@Component
public class XSLTransformer {

	public void convertXMLtoHTML(Document xml, String xmlFileName) {
		
		String xslFileName = XMLTOString(xml);		
		TransformerFactory factory = TransformerFactory.newInstance();
		StreamSource xslStream = new StreamSource(xslFileName);
		
		StreamSource in = new StreamSource(xmlFileName);
		StreamResult out = new StreamResult(System.out);
		
		try {
			Transformer transformer = factory.newTransformer(xslStream);
			transformer.transform(in, out);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		
	}
	
	private String XMLTOString(Document doc) {
		try {
            StringWriter sw = new StringWriter();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            transformer.transform(new DOMSource(doc), new StreamResult(sw));
            return sw.toString();
        } catch (Exception ex) {
            throw new RuntimeException("Error converting to String", ex);
        }
	}
}
