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

public class Method {

    private String nome;
    private TIPO_METHOD tipo;
    
    public Method(){
        
    }
    
    public Method(String nome, TIPO_METHOD tipo){
        this.nome = nome;
        this.tipo = tipo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(TIPO_METHOD tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public TIPO_METHOD getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return this.nome;
    }

    @Override
    public boolean equals(Object obj) {
       if (obj == null) return false;
         
        Method m = (Method) obj;
        return ((this.nome).equals(m.getNome()));
    }
    
}
