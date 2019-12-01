/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firewallapplicationontology.parser;

import firewallapplicationontology.domain.Method;
import firewallapplicationontology.domain.Input;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Prof. Ronaldo
 */
public class ParserInputs {
    
    public static List<Input> getInputs(String filePath, List<Method> methods) throws FileNotFoundException, IOException{
        List<Input> inputs = new ArrayList<>();
        
        String texto = "";
        int i;
        FileReader fr = new FileReader(filePath);
        
        while((i = fr.read())!=-1){
            if (i <= 256)
               texto+=(char)i;
        }
        
        texto = texto.replace("\n ", "");
        texto = texto.replace("\n\n","\n");
        
        String tmp[] = texto.split("\n");
        
        for(String s: tmp){
            String fields[] = s.split("\\] \\[");
            String field = fields[8];
            if (field.split(":")[0].split("\\[ ")[1].equals("Método")){
                String metodo = field.split(":")[1];
                String parametro = field.split(":")[2].split("\\]\\]")[0];
                Input input = new Input();
                input.setInput(parametro);
                
                Method method = new Method(
                   ((Method)methods.stream().filter(m->m.getNome().equals(metodo)).toArray()[0]).getNome(),
                   ((Method)methods.stream().filter(m->m.getNome().equals(metodo)).toArray()[0]).getTipo()
                );
                input.setApplicationMethod(method);
                input.setInputArray(convertInputToArray(parametro));
                inputs.add(input);
            }
        }
        
        return inputs;
    }
    
    private static String[] convertInputToArray(String input){
        String[] array = null;
        array = input.split(" ");
        return array;
    }
}
