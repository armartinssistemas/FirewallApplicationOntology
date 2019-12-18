/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firewallapplicationontology.analyzer;

import firewallapplicationontology.domain.Input;

/**
 *
 * @author Prof. Ronaldo
 */
public class Result {
    private Input input;
    private boolean malicious;
    private boolean atack;

    public Result(){
        
    }
    
    public Result(Input input, boolean malicious, boolean atack){
        this.input = input;
        this.malicious = malicious;
        this.atack = atack;
    }
    
    public void setInput(Input input) {
        this.input = input;
    }

    public void setMalicious(boolean malicious) {
        this.malicious = malicious;
    }

    public void setAtack(boolean atack) {
        this.atack = atack;
    }
    
    

    public Input getInput() {
        return input;
    }

    public boolean isMalicious() {
        return malicious;
    }

    public boolean isAtack() {
        return atack;
    }
    
    
}
