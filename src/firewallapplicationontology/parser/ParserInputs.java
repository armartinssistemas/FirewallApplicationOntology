/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firewallapplicationontology.parser;

import firewallapplicationontology.domain.Method;
import firewallapplicationontology.domain.Input;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Prof. Ronaldo
 */
public class ParserInputs {
    
    public static List<Input> getInputs(String filePath, List<Method> methods) throws FileNotFoundException, IOException{
        List<Input> inputs = new ArrayList<>();
        
        /*String texto = "";
        int i;
        FileReader fr = new FileReader(filePath);
        
        while((i = fr.read())!=-1){
            //if (i <= 256)
               texto+=(char)i;
        }
        
        texto = texto.replace("\n ", "");
        texto = texto.replace("\n\n","\n");
        
        String tmp[] = texto.split("\n");*/
        
        Scanner scanner = new Scanner(new File(filePath) );
        scanner.useDelimiter("\n\n");
        List<String> tmp = new ArrayList<String>();
        while(scanner.hasNext()){
            tmp.add(scanner.next());
        }
        
        int x = 0;
        for(String s: tmp){
            s = s.replace("\n", "");
            String fields[] = s.split("\\] \\[");
            if (fields.length == 9){
                String field = fields[8];
                if (field.split(":")[0].split("\\[ ")[1].trim().equals("Método")){
                    try{
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
                    }catch(Exception ex){}
                }
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
