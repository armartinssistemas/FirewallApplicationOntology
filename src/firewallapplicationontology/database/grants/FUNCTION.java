/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firewallapplicationontology.database.grants;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Prof. Ronaldo
 */
public class FUNCTION {
    private static String[] vals = new String[]{"ALTER ROUTINE", "EXECUTE", "CREATE ROUTINE"};
    public static List<String> grants = Arrays.asList(vals);
    
}
