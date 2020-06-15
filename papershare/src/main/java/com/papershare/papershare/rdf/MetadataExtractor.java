package com.papershare.papershare.rdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;


/**
 * 
 * Primer demonstrira ekstrakciju RDFa metapodataka iz 
 * XML dokumenta primenom GRDDL (Gleaning Resource Descriptions 
 * from Dialects of Languages) transformacije.
 * 
 */
@Component
public class MetadataExtractor {
	
	private TransformerFactory transformerFactory;

	private static final String XSLT_FILE = "src/main/resources/xsl/grddl.xsl";
	private static final String RDF_FILE = "src/main/resources/rdf/paper_metadata.rdf";
	
	public MetadataExtractor() throws SAXException, IOException {
		
		// Setup the XSLT transformer factory
		transformerFactory = new TransformerFactoryImpl();
	}

	/**
	 * Generates RDF/XML based on RDFa metadata from an XML containing 
	 * input stream by applying GRDDL XSL transformation.
	 *  
	 * @param in XML containing input stream
	 * @param out RDF/XML output stream
	 */
	public void extractMetadata(String xmlString) throws FileNotFoundException, TransformerException {
		
		OutputStream out = new FileOutputStream(RDF_FILE);
		StreamSource source = new StreamSource(new StringReader(xmlString));
		
		// Create transformation source
		StreamSource transformSource = new StreamSource(new File(XSLT_FILE));
		
		// Initialize GRDDL transformer object
		Transformer grddlTransformer = transformerFactory.newTransformer(transformSource);
		
		// Set the indentation properties
		grddlTransformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
		grddlTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
		
		// Initialize result stream
		StreamResult result = new StreamResult(out);
		
		// Trigger the transformation
		grddlTransformer.transform(source, result);
		
	}

}
