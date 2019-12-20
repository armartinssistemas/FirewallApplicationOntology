/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firewallapplicationontology.example;

import firewallapplicationontology.database.MySql;
import firewallapplicationontology.database.ObjectDataBase;
import firewallapplicationontology.database.ObjectMySql;
import firewallapplicationontology.analyzer.Analyzer;
import firewallapplicationontology.analyzer.Result;
import firewallapplicationontology.analyzer.Summary;
import firewallapplicationontology.domain.Input;
import firewallapplicationontology.domain.Method;
import firewallapplicationontology.ontology.QueryEngine;
import firewallapplicationontology.parser.ParserInputs;
import firewallapplicationontology.parser.ParserMethods;
import firewallapplicationontology.report.Report;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;
import org.swrlapi.factory.SWRLAPIFactory;
import org.swrlapi.parser.SWRLParseException;
import org.swrlapi.sqwrl.SQWRLQueryEngine;
import org.swrlapi.sqwrl.exceptions.SQWRLException;

/**
 *
 * @author Prof. Ronaldo
 */
public class Main {
    public static void main(String args[]) throws FileNotFoundException, IOException, OWLOntologyCreationException, OWLOntologyStorageException, OWLOntologyStorageException, SWRLParseException, SQWRLException{
        
        SQWRLQueryEngine queryEngine = QueryEngine.get();
        queryEngine.infer();        
        
        
        //Objetos e Permissões do Banco de Dados
        List<ObjectMySql> databaseObjects = MySql.getObjects();
        
        //Recupera métodos existentes da aplicação
        System.out.println("ParserMethods.getInputs");
        List<Method> methods = ParserMethods.getInputs("D:\\\\Documentos\\\\Mestrado\\\\InteligenciaArtifical\\\\Ontologia\\\\TrabalhoFinal\\\\methods.xml");
        
        //Recupera métodos e entradas da aplicação WEB
        //List<Input> inputs = ParserInputs.getInputs("D:\\\\Documentos\\\\Mestrado\\\\InteligenciaArtifical\\\\Ontologia\\\\TrabalhoFinal\\\\server.min.log_2019-07-05T16-20-03",methods);
        System.out.println("ParserInputs.getInputs");
        List<Input> inputs = ParserInputs.getInputs("D:\\\\Documentos\\\\Mestrado\\\\InteligenciaArtifical\\\\Ontologia\\\\TrabalhoFinal\\\\server.log_2019-07-05T16-20-03",methods);
                                                                                                                                            
        
        //Analiza e retorna Resultado e Resumo
        System.out.println("Analyzer.analyzerResult");
        List<Result> results = Analyzer.analyzerResult(queryEngine, databaseObjects, inputs, methods);
        
        System.out.println("Analyzer.analyzerSummary");
        Summary summary = Analyzer.analyzerSummary(results);
        
        //Gera dados para visualização da informação
        Report.generateChartReport(summary, "C:\\wamp64\\www\\dashboard\\chart.json");
        
        //Gera itens detectados
        Report.generateItensReport(results, "C:\\wamp64\\www\\dashboard\\itens.json");
    }
}
