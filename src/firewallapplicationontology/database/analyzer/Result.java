/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firewallapplicationontology.database.analyzer;

import firewallapplicationontology.domain.Input;

/**
 *
 * @author Prof. Ronaldo
 */
public class Result {
    private Input input;
    private boolean malicious;

    public Result(){
        
    }
    
    public Result(Input input, boolean malicious){
        this.input = input;
        this.malicious = malicious;
    }
    
    public void setInput(Input input) {
        this.input = input;
    }

    public void setMalicious(boolean malicious) {
        this.malicious = malicious;
    }

    public Input getInput() {
        return input;
    }

    public boolean isMalicious() {
        return malicious;
    }
    
    
}
