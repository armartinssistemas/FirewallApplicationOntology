/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firewallapplicationontology.report;

import firewallapplicationontology.analyzer.Result;
import firewallapplicationontology.analyzer.Summary;
import firewallapplicationontology.analyzer.SummaryItem;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Prof. Ronaldo
 */
public class Report {
    public static void generateReport(Summary summary, String filePath){
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
}
