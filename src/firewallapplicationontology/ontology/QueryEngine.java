/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firewallapplicationontology.ontology;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.swrlapi.factory.SWRLAPIFactory;
import org.swrlapi.sqwrl.SQWRLQueryEngine;

/**
 *
 * @author Prof. Ronaldo
 */
public class QueryEngine {
    public static SQWRLQueryEngine get() throws OWLOntologyCreationException{
        // Instanciando OWLAPI
        OWLOntologyManager ontologyManager = OWLManager.createOWLOntologyManager();
        //Carregando Ontologia
        URL url = new Object() { }.getClass().getResource("ApplicationSecurityV3.owl");
        File file = new File(url.getPath());
        OWLOntology ontology 
          = ontologyManager.loadOntologyFromOntologyDocument(
                  file
          );
        
                
        
        //Cria motor de busca SQWRL API
        SQWRLQueryEngine queryEngine = SWRLAPIFactory.createSQWRLQueryEngine(ontology);
        //Iniciando Inferencia
        
        return queryEngine;
    }
}
