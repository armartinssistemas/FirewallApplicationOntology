/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firewallapplicationontology.report;

import firewallapplicationontology.analyzer.Result;
import firewallapplicationontology.analyzer.Summary;
import firewallapplicationontology.analyzer.SummaryItem;
import firewallapplicationontology.analyzer.TypeInput;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Prof. Ronaldo
 */
public class Report {
    public static void generateChartReport(Summary summary, String filePath){
        //Cria um Objeto JSON  
        FileWriter writeFile = null;
         
        JSONArray States = new JSONArray();
        
        
        //Aqui existe um Erro
        for(SummaryItem i: summary.getItens()){
            JSONObject jsonObjectMethods = new JSONObject();
            jsonObjectMethods.put("State", i.getMethod().getNome());

            JSONObject freq = new JSONObject();
            
            freq.put("Malicious", i.getMalicious());
            freq.put("Inputs", i.getAll());
            freq.put("Atacks", i.getAtack());
            
            jsonObjectMethods.put("freq", freq);

            States.add(jsonObjectMethods);
        }
        
        
        
         
        try{
            System.out.println(States.toString());
            writeFile = new FileWriter(filePath);
            //Escreve no arquivo conteudo do Objeto JSON
            writeFile.write(States.toJSONString());
            writeFile.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    } 
    
    public static void generateItensReport(List<Result> results, String filePath){
        //Cria um Objeto JSON  
        FileWriter writeFile = null;
         
        JSONArray resultsItens = new JSONArray();
        
        Collections.sort(results);
        
        for(Result r: results){
            if ((r.getTypeInput() == TypeInput.ATACK) || (r.getTypeInput() == TypeInput.MALICIOUS) ||
                    (r.getTypeInput() == TypeInput.NORMAL) ){
                JSONObject jsonItem = new JSONObject();
                jsonItem.put("Method", r.getInput().getApplicationMethod().getNome());
                jsonItem.put("Input", r.getInput().getInput());
                jsonItem.put("Type", 
                        (r.getTypeInput()==TypeInput.ATACK?"ATACK":r.getTypeInput()==TypeInput.MALICIOUS?"MALICIOUS":"NORMAL"));
                resultsItens.add(jsonItem);
            }
        }
        
        try{
            System.out.println(resultsItens.toString());
            writeFile = new FileWriter(filePath);
            //Escreve no arquivo conteudo do Objeto JSON
            writeFile.write(resultsItens.toJSONString());
            writeFile.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }        
    }
}
