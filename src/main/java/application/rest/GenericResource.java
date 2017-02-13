/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.rest;

import java.util.Arrays;
import java.util.Optional;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import org.jnosql.artemis.demo.document.DocumentDatabase;
import org.jnosql.artemis.demo.document.DocumentDatabaseType;
import org.jnosql.artemis.demo.document.Person;

import org.jnosql.artemis.document.DocumentRepository;
import org.jnosql.diana.api.document.Document;
import org.jnosql.diana.api.document.DocumentCondition;
import org.jnosql.diana.api.document.DocumentQuery;

/**
 * REST Web Service
 *
 * @author fabio
 */
@Path("generic")
public class GenericResource {

   @Inject
   @DocumentDatabase(value = DocumentDatabaseType.MONGODB )
   //@DocumentDatabase(value = DocumentDatabaseType.COUCHBASE )
   private DocumentRepository repository;

   private static final Person PERSON = Person.builder().
            withPhones("987654321")
            .withName("Joao")
            .withId("2")
            .withIgnore("Just Ignore").build();

  
    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }
 
    /**
     * Retrieves representation of an instance of application.rest.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getText() {
        //TODO return proper representation object
        return "hello microProfile.io";
    }
    
    
    @GET
    @Path("diana")
    @Produces(MediaType.TEXT_PLAIN)
    public String getDiana() {
        //TODO return proper representation object
        return teste();
    }
    
    @GET
    @Path("jnosql/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getData(@PathParam("id") int id) {
       // Person saved = repository.save(PERSON);
      
        DocumentQuery query = DocumentQuery.of("Person");
        query.and(DocumentCondition.eq(Document.of("_id", id)));
        
        Optional<Person> person = repository.singleResult(query);
        return "Entity found: " + person;
    }
    
    

    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    public void putText(String content) {
    }
    
      private String teste() {

//       Person saved = repository.save(PERSON);
//       System.out.println("Person saved" + saved);

        DocumentQuery query = DocumentQuery.of("Person");
        query.and(DocumentCondition.eq(Document.of("_id", 1L)));

        Optional<Person> person = repository.singleResult(query);
        System.out.println("Entity found: " + person);
        return "Entity found: " + person;
        
     
        
    }
}
