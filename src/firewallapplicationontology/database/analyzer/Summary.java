/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firewallapplicationontology.database.analyzer;

import firewallapplicationontology.domain.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Prof. Ronaldo
 */
public class Summary {
    private List<SummaryItem> itens = new ArrayList<SummaryItem>();

    public void add(SummaryItem item){
        if (!itens.contains(item))
            this.itens.add(item);
    }

    public List<SummaryItem> getItens() {
        return itens;
    }
    
    public SummaryItem findSummaryItem(Method method){
        SummaryItem si = null;
        Iterator i = this.itens.stream().filter(p->p.getMethod().equals(method)).collect(Collectors.toList()).iterator();
        if (i.hasNext())
            si = (SummaryItem)i.next();
        else
            si = new SummaryItem(method);
        return si;
    }
    
}
