/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firewallapplicationontology.analyzer;

import firewallapplicationontology.domain.Input;

public class Result implements Comparable<Result>{
    private Input input;
    private TypeInput typeInput;

    public Result(){
        
    }
    
    public Result(Input input, TypeInput typeInput){
        this.input = input;
        this.typeInput = typeInput;
    }
    
    public void setInput(Input input) {
        this.input = input;
    }

    public void setTypeInput(TypeInput typeInput) {
        this.typeInput = typeInput;
    }

    public TypeInput getTypeInput() {
        return typeInput;
    }
    
    public Input getInput() {
        return input;
    }

    @Override
    public int compareTo(Result o) {
        return this.input.getApplicationMethod().getNome().compareTo(o.getInput().getApplicationMethod().getNome());
    }
}
