/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firewallapplicationontology.database;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Prof. Ronaldo
 */
	

public class ObjectMySql implements ObjectDataBase{
    private String nomeObjeto;
    private TIPO_OBJETO_BANCODADOS tipoObjeto;
    private String dataBase;
    private List<String> permissoes;

    public ObjectMySql(){
        permissoes = new ArrayList<>();
    }

    public void setNomeObjeto(String nomeObjeto) {
        this.nomeObjeto = nomeObjeto;
    }
    
    public void setDataBase(String dataBase) {
        this.dataBase = dataBase;
    }

    public void setTipoObjeto(TIPO_OBJETO_BANCODADOS tipoObjeto) {
        this.tipoObjeto = tipoObjeto;
    }

    public String getDataBase() {
        return dataBase;
    }

    public List<String> getPermissoes() {
        return permissoes;
    }

    public TIPO_OBJETO_BANCODADOS getTipoObjeto() {
        return tipoObjeto;
    }
    
    public void addPermissao(String permissao){
        this.permissoes.add(permissao);
    }

    public String getNomeObjeto() {
        return nomeObjeto;
    }
    
    @Override
    public String toString() {
        return getNomeObjeto();
    }
    
    
}
