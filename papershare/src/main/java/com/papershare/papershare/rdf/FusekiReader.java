package com.papershare.papershare.rdf;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.text.StringSubstitutor;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.RDFNode;

import com.papershare.papershare.rdf.FusekiAuthenticationUtilities.ConnectionProperties;


public class FusekiReader {
	
	private static final String QUERY_FILEPATH = "src/main/resources/rdf/searchQuery.rq";
	
	private FusekiReader() {}

	public static void run( Map<String, String> params) throws IOException {
		
		ConnectionProperties conn = FusekiAuthenticationUtilities.loadProperties();
		
		String sparqlQueryTemplate = readFile(QUERY_FILEPATH, StandardCharsets.UTF_8);
		System.out.println("Query: " + sparqlQueryTemplate);
		String sparqlQuery = StringSubstitutor.replace(sparqlQueryTemplate, params, "{{", "}}");
		System.out.println("Query: " + sparqlQuery);
		
		// Create a QueryExecution that will access a SPARQL service over HTTP
		QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
		// Query the SPARQL endpoint, iterate over the result set...
		ResultSet results = query.execSelect();
		
		String varName;
		RDFNode varValue;
		
		while(results.hasNext()) {
		    
			// A single answer from a SELECT query
			QuerySolution querySolution = results.next() ;
			Iterator<String> variableBindings = querySolution.varNames();
			
			// Retrieve variable bindings
		    while (variableBindings.hasNext()) {
		   
		    	varName = variableBindings.next();
		    	varValue = querySolution.get(varName);
		    	
		    	System.out.println(varName + ": " + varValue);
		    }
		    System.out.println();
		}
		
	    ResultSetFormatter.outputAsXML(System.out, results);
		
		query.close() ;
		
		System.out.println("[INFO] SPARQL Query End.");
	}
	
	public static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
}
