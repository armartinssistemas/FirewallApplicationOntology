/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firewallapplicationontology.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Prof. Ronaldo
 */
public enum ATACKSQL_INJECTION {
    AtackSQLInjectionNumerico("AtackSQLInjectionNumerico"),
    AtackSQLInjectionTextDELETE("AtackSQLInjectionTextDELETE"),
    AtackSQLInjectionTextDROP("AtackSQLInjectionTextDROP"),
    AtackSQLInjectionTextUPDATE("AtackSQLInjectionTextUPDATE");
    
    private final String val;
    
    public static ATACKSQL_INJECTION getATACKSQL_INJECTION(String val){
        if (val.equals("AtackSQLInjectionNumerico")) return AtackSQLInjectionNumerico;
        else if (val.equals("AtackSQLInjectionTextDELETE")) return AtackSQLInjectionTextDELETE;
        else if (val.equals("AtackSQLInjectionTextDROP")) return AtackSQLInjectionTextDROP;
        else if (val.equals("AtackSQLInjectionTextUPDATE")) return AtackSQLInjectionTextUPDATE;
        else return null;
    }
    
    ATACKSQL_INJECTION(String val){
        this.val = val;
    }
    
    public String getTabela(String regex, String param){
        String espaco_or_comentatio="((\\/\\*.{0,}\\*\\/)|(\\s))";
        String tabela = "";
        if (val.equals("AtackSQLInjectionNumerico")){
            tabela = null;
        }else if (val.equals("AtackSQLInjectionTextDELETE")){
        
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(param);
            while (matcher.find())
            {
                String injection = matcher.group().replaceAll(espaco_or_comentatio+"{1,}", " ");
                tabela = injection.split(" ")[1];
            }
        }else if (val.equals("AtackSQLInjectionTextDROP")){      
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(param);
            while (matcher.find())
            {
                String injection = matcher.group().replaceAll(espaco_or_comentatio+"+", " ");
                tabela = injection.split(" ")[1];
            }
        }else if (val.equals("AtackSQLInjectionTextUPDATE")){
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(param);
            while (matcher.find())
            {
                String injection = matcher.group().replaceAll(espaco_or_comentatio+"{1,}", "");
                tabela = injection.split(" ")[1];
            }
            
        }
        return tabela;
    }
}
