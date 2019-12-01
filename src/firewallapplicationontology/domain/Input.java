/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firewallapplicationontology.domain;

/**
 *
 * @author Prof. Ronaldo
 */
public class Input {
    private String input;
    private String[] inputArray;
    private Method applicationMethod;
    
    public Input(){
        
    }
    
    public Input(String input, Method applicationMethod){
        this.input = input;
        this.applicationMethod = applicationMethod;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public void setApplicationMethod(Method applicationMethod) {
        this.applicationMethod = applicationMethod;
    }

    public void setInputArray(String[] inputArray) {
        this.inputArray = inputArray;
    }

    public String getInput() {
        return input;
    }

    public Method getApplicationMethod() {
        return applicationMethod;
    }

    public String[] getInputArray() {
        return inputArray;
    }

    @Override
    public String toString() {
        return this.input;
    }
}
