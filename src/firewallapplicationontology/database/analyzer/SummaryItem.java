/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firewallapplicationontology.database.analyzer;

import firewallapplicationontology.domain.Method;

/**
 *
 * @author Prof. Ronaldo
 */
public class SummaryItem {
    private Method method;
    private int malicious;
    private int nomalicious;
    
    public SummaryItem (Method method){
        this.method = method;
    }
    
    public void addMalicious(){
        this.malicious++;
    }
    
    public void addNoMalicious(){
        this.nomalicious++;
    }

    public Method getMethod() {
        return method;
    }

    public int getMalicious() {
        return malicious;
    }

    public int getNomalicious() {
        return nomalicious;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        
        SummaryItem s = (SummaryItem) o;
        
        return ((this.method.getNome()).equals(s.getMethod().getNome()));
    }
    
    
    
    
}
