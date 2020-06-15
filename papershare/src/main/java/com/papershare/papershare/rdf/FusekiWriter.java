package com.papershare.papershare.rdf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;

import com.papershare.papershare.rdf.FusekiAuthenticationUtilities.ConnectionProperties;

public class FusekiWriter {
	
	private static final String RDF_FILEPATH = "src/main/resources/data/rdf/paper_metadata.rdf";
	private static final String PAPERS_METADATA_GRAPH_URI = "/papersMetadata";
	
	public static void saveRDF() throws IOException {
		
		System.out.println("[INFO] Loading triples from an RDF/XML to a model...");
		ConnectionProperties conn = FusekiAuthenticationUtilities.loadProperties();
		
		// Creates a default model
		Model model = ModelFactory.createDefaultModel();
		model.read(RDF_FILEPATH);

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		model.write(out, SparqlUtil.NTRIPLES);

		System.out.println("[INFO] Rendering model as RDF/XML...");
		model.write(System.out, SparqlUtil.RDF_XML);

		/*
		 * Create UpdateProcessor, an instance of execution of an UpdateRequest.
		 * UpdateProcessor sends update request to a remote SPARQL update service.
		 */
		
		UpdateRequest request = UpdateFactory.create() ;
		
		//TODO Remove this later
		// This will delete all of the triples in all of the named graphs 
		// request.add(SparqlUtil.dropAll()); 
		System.out.println("EEEJ => " + conn.updateEndpoint);
		UpdateProcessor processor = UpdateExecutionFactory.createRemote(request, conn.updateEndpoint);
		processor.execute();

		// Creating the first named graph and updating it with RDF data
		System.out.println("[INFO] Writing the triples to a named graph \"" + PAPERS_METADATA_GRAPH_URI + "\".");
		String sparqlUpdate = SparqlUtil.insertData(conn.dataEndpoint + PAPERS_METADATA_GRAPH_URI,
				new String(out.toByteArray()));
		System.out.println(sparqlUpdate);

		// UpdateRequest represents a unit of execution
		UpdateRequest update = UpdateFactory.create(sparqlUpdate);

		processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
		processor.execute();
	}
}
