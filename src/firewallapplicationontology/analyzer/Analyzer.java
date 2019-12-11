/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firewallapplicationontology.analyzer;

import com.mysql.cj.xdevapi.DatabaseObject;
import firewallapplicationontology.database.ObjectDataBase;
import firewallapplicationontology.database.ObjectMySql;
import firewallapplicationontology.domain.ATACKSQL_INJECTION;
import firewallapplicationontology.domain.Input;
import firewallapplicationontology.domain.Method;
import firewallapplicationontology.domain.TIPO_METHOD;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.StreamDocumentTarget;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.swrlapi.factory.SWRLAPIFactory;
import org.swrlapi.parser.SWRLParseException;
import org.swrlapi.sqwrl.SQWRLQueryEngine;
import org.swrlapi.sqwrl.SQWRLResult;
import org.swrlapi.sqwrl.exceptions.SQWRLException;
import org.swrlapi.sqwrl.values.SQWRLNamedIndividualResultValue;

/**
 *
 * @author Prof. Ronaldo
 */
public class Analyzer {
    public static List<Result> analyzerResult(SQWRLQueryEngine queryEngine, List<ObjectMySql> objects, List<Input> inputs, List<Method> methods)
    throws OWLOntologyCreationException, OWLOntologyStorageException, SWRLParseException, SQWRLException{
       List<Result> analyzers = new ArrayList<Result>();
        /**/
       
       for(Input i: inputs){
           queryEngine.deleteSWRLRule("var");
           String temp = "hasSqlInjection("+i.getApplicationMethod().getTipo()+",?atack) ^"
                   + " regex(?atack,?regx)"
                   + "->sqwrl:select(?regx, ?atack)";
           SQWRLResult result = queryEngine.runSQWRLQuery("var",temp);
           boolean malicious = false;
           while (result.next()){
               String regex = result.getLiteral(0).getValue();
               String atack = result.getNamedIndividual(1).getShortName().toString().replace(":", "");
               ATACKSQL_INJECTION injectatack = ATACKSQL_INJECTION.getATACKSQL_INJECTION(atack);
               if (injectatack == ATACKSQL_INJECTION.AtackSQLInjectionNumerico){
                   if (!i.getInput().matches(regex)){
                       malicious = true;
                   }
                   
               }else{
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(i.getInput());
                    if (matcher.find()){
                        //Busca Permissões
                        queryEngine.deleteSWRLRule("var");
                        SQWRLResult result2 = queryEngine.runSQWRLQuery("var",
                                                        "hasPermissionDataBase("+atack+", ?p) ^"+
                                                        "hasPermission(?p, ?lp) "+
                                                        "->sqwrl:select(?lp)");
                        while(result2.next()){
                            String perm = result2.getLiteral(0).getValue();
                            String objStr = injectatack.getTabela(regex, i.getInput());
                            Iterator it = objects.stream().filter(x->x.getNomeObjeto().equals(objStr)).collect(Collectors.toList()).iterator();
                            ObjectMySql objBD = null;
                            if (it.hasNext()) objBD = (ObjectMySql) it.next();
                            if (objBD!=null){
                                malicious = true;  
                                Iterator itPerm =  objBD.getPermissoes().stream().filter(x->x.equals(perm)).collect(Collectors.toList()).iterator();
                                if (itPerm.hasNext())
                                   malicious = true;
                            }
                        }
                    }   
               }
           }
           analyzers.add(new Result(i, malicious));
           
       }
       
       return analyzers;
    }
    
    public static Summary analyzerSummary(List<Result> analyzers){
        Summary s = new Summary();
        
        for(Result r: analyzers){
            SummaryItem si = s.findSummaryItem(r.getInput().getApplicationMethod());
            if (r.isMalicious()){
                si.addMalicious();
            }else{
                si.addNoMalicious();
            }
            
            //O problema está aqui, está sempre adicionando
            s.add(si);
        }
        
        
        return s;
    }
}
