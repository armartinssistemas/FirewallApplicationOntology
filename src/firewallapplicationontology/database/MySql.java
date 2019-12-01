/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firewallapplicationontology.database;



import java.sql.*;  
import java.util.*;
import java.util.logging.*;
import firewallapplicationontology.database.grants.*;

/**
 *
 * @author Prof. Ronaldo
 */
public class MySql implements DataBase{
   //static final String jdbc_driver = "com.mysql.jdbc.Driver"; 
   static final String jdbc_driver = "com.mysql.cj.jdbc.Driver";
   static final String address = "localhost";
   static final String database = "world";
   static final String DB_URL = "jdbc:mysql://"+address+"/"+database+"?useSSL=true&useTimezone=true&serverTimezone=UTC";

   //  Database credentials
   static final String user = "root";
   static final String pass = "123456";    
   
   public static List<ObjectMySql> getObjects(){
       Connection conn = null;
       Statement stmt = null;
       List<ObjectMySql> objetos = new ArrayList<ObjectMySql>();
       
       try{
            //Registra Driver JDBC   
            Class.forName(jdbc_driver);
            //Cria conexão com o banco de dados
            conn = DriverManager.getConnection(DB_URL,user,pass);

            stmt = conn.createStatement();

            getTables(stmt, objetos);

            getFunctions(stmt, objetos, database);

            getProcedures(stmt, objetos, database);
            
            getPermissions(stmt, objetos, database);
            
            conn.close();
        
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex) {
           Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       return objetos;
   }
   
   private static void getTables(Statement stmt, List<ObjectMySql> objetos) throws SQLException{
        ResultSet rs = stmt.executeQuery("show full tables");
        
        while (rs.next()){
            ObjectMySql o = new ObjectMySql();
            o.setNomeObjeto(rs.getString(1));
            if (rs.getString(2).equals("BASE TABLE")){
               o.setTipoObjeto(TIPO_OBJETO_BANCODADOS.TABLE);
            }else if (rs.getString(2).equals("VIEW")){
               o.setTipoObjeto(TIPO_OBJETO_BANCODADOS.VIEW);
            }
            objetos.add(o);
        }
   }
   
   private static void getFunctions(Statement stmt, List objetos, String database) throws SQLException{
        ResultSet rs = stmt.executeQuery("SHOW FUNCTION STATUS WHERE db = '"+database+"';");
        while (rs.next()){
            ObjectMySql o = new ObjectMySql();
            o.setNomeObjeto(rs.getString(2));
            o.setTipoObjeto(TIPO_OBJETO_BANCODADOS.FUNCTION);
            objetos.add(o);
        }   
   }
   
   private static void getProcedures(Statement stmt, List objetos, String database) throws SQLException{
        ResultSet rs = stmt.executeQuery("SHOW PROCEDURE STATUS WHERE db = '"+database+"';");
        while (rs.next()){
            ObjectMySql o = new ObjectMySql();
            o.setNomeObjeto(rs.getString(2));
            o.setTipoObjeto(TIPO_OBJETO_BANCODADOS.PROCEDURE);
            objetos.add(o);
        }
   }  
   
   private static void getPermissions(Statement stmt, List<ObjectMySql> objetos, String database) throws SQLException{
       ResultSet rs = stmt.executeQuery("SHOW GRANTS FOR CURRENT_USER");
       while(rs.next()){
           String linha = rs.getString(1);
           
           String tempA[] = linha.split(" ON ")[0].split("GRANT")[1].trim().split(",");
           
           List<String> permissoes = Arrays.asList(tempA);
           
           String tempS = linha.split(" ON ")[1].split("TO")[0].replace("`", "").replace("\'", "").trim();
           
           if (!tempS.equals("@")){
           
            String tabela = tempS.split("\\.")[1].trim();

            String databasebusca = tempS.split("\\.")[0].trim();

            if (databasebusca.equals("*")){
                for(ObjectDataBase o: objetos){
                    ObjectMySql oo = (ObjectMySql) o;
                    for(String p: permissoes){
                        p = p.trim();
                        if (!oo.getPermissoes().contains(p.trim())){
                           if (
                                 ((oo.getTipoObjeto() == TIPO_OBJETO_BANCODADOS.FUNCTION) && (FUNCTION.grants.contains(p))) ||
                                 ((oo.getTipoObjeto() == TIPO_OBJETO_BANCODADOS.PROCEDURE) && (PROCEDURE.grants.contains(p))) || 
                                 ((oo.getTipoObjeto() == TIPO_OBJETO_BANCODADOS.TABLE) && (TABLE.grants.contains(p))) ||
                                 ((oo.getTipoObjeto() == TIPO_OBJETO_BANCODADOS.VIEW) && (VIEW.grants.contains(p)))
                              )
                             oo.addPermissao(p);
                        }
                    }
                }
            }else if (databasebusca.equals(database)){
                for(ObjectDataBase o: objetos){
                    ObjectMySql oo = (ObjectMySql) o;
                    for(String p: permissoes){
                        if (!oo.getPermissoes().contains(p.trim()))
                           if (
                                 ((oo.getTipoObjeto() == TIPO_OBJETO_BANCODADOS.FUNCTION) && (FUNCTION.grants.contains(p))) ||
                                 ((oo.getTipoObjeto() == TIPO_OBJETO_BANCODADOS.PROCEDURE) && (PROCEDURE.grants.contains(p))) || 
                                 ((oo.getTipoObjeto() == TIPO_OBJETO_BANCODADOS.TABLE) && (TABLE.grants.contains(p))) ||
                                 ((oo.getTipoObjeto() == TIPO_OBJETO_BANCODADOS.VIEW) && (VIEW.grants.contains(p)))
                              )
                             oo.addPermissao(p);
                    }
                }
            }  
         }  
       }
   }
}
