/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firewallapplicationontology.parser;

import firewallapplicationontology.domain.Method;
import firewallapplicationontology.domain.TIPO_METHOD;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Prof. Ronaldo
 */
public class ParserMethods {
    public static List<Method> getInputs(String filePath){
        List<Method> methods = new ArrayList<Method>();
        methods.add(new Method("putCoordenada", TIPO_METHOD.InputURLVulnerabilityStringImp));
        methods.add(new Method("getOcorrenciasPorOperador", TIPO_METHOD.InputURLVulnerabilityIntegerImp));//Não
        methods.add(new Method("alterSituacaoOcorrencia", TIPO_METHOD.InputURLVulnerabilityStringImp));//Não
        methods.add(new Method("putDiaTrabalho", TIPO_METHOD.InputURLVulnerabilityStringImp));
        methods.add(new Method("putProjeto", TIPO_METHOD.InputURLVulnerabilityStringImp));
        methods.add(new Method("putTag", TIPO_METHOD.InputURLVulnerabilityStringImp));
        methods.add(new Method("putOcorrencia", TIPO_METHOD.InputURLVulnerabilityStringImp));
        
        return methods;
    }
}
