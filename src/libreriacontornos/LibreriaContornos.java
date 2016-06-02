/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreriacontornos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author Adrián
 */
public class LibreriaContornos {

    /**
     * @param args the command line arguments
     */
    
    Connection con=null;
    Statement cmd = null;
    
    
    /**
     * When to use this method , you can connect your database with netbeans , for her you need insert on parameters :
     * @param url ,adrees for your database
     * @param user ,name of the propietor of the database
     * @param pass ,password of the user
     */
    public void Connect(String url, String user, String pass){
       
        try{
            Class.forName("org.gjt.mm.mysql.Driver");
            con= (Connection) DriverManager.getConnection(url,user,pass);
        }catch(Exception ex){
            System.out.println("Can't connect to database "+ex.getLocalizedMessage());        
        }
      
    }
    /**
     * This method to allow insert in you database from netbeans , but you need write the input parameters:
     * @param nameTable,name for the table for your database
     * @param numColumns,number of columns for your table
     * @param values ,values to insert in your fields
     */
    public void Insert(String nameTable,String numColumns, String values){
        try {
            PreparedStatement st= con.prepareStatement("INSERT INTO "+nameTable+" ("+numColumns+") VALUES("+values+")");
       
       st.executeUpdate();
       
        } catch (SQLException ex) {
            System.out.println("Exception on the insert"+ex.getMessage());
        }
    }
    /**
     * With search method you can show a determinate value 
     * @param nameTable,name for the table for your database
     * @param numColumns,number of columns for your table
     * @param showDate,data to seek 
     * @return the data you search on the variable ac
     */
    //this method may not work , because the String array can be fail .
    public String search(String nameTable,int numColumns, String showDate){
       String ac="";
        try {
            Statement st= con.createStatement();
               ResultSet rs= st.executeQuery("SELECT "+showDate+" FROM "+nameTable);
              String [] data= new String[numColumns-1];
        while(rs.next()){
          
            for (int i = 0; i < data.length; i++) {
                data[i]=rs.getString(i+1);
                 ac= ac +" "+data[i];
                
            }
           
              }
                   
        } catch (SQLException ex) {
            System.out.println("Exception on the search method , can be failing , on the String array"+ex.getMessage());
        }
        return ac;
    }
    /**
     * This method change a file for your database , you need introduce new values for all fields
     * @param nameTable,name for the table for your database
     * @param PrimaryKey,the most important value , because this is the identifier of the row 
     * @param oldUpdate,original values of the field you want change
     * @param newUpdate ,new values you want show
     */
    public void update(String nameTable, String PrimaryKey,String oldUpdate,String newUpdate){
        try {
            PreparedStatement st= con.prepareStatement("UPDATE "+ nameTable+" SET "+oldUpdate+"='"+newUpdate+"' WHERE ID='"+PrimaryKey+"'");
            st.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Exception on the update "+ex.getMessage() );
        }
    }
    /**
     * This method remove , or delete all fields for a file , you introduce the primary key , and all fields asociated to primary key
     * @param nameTable,name for the table for your database
     * @param PrimaryKey ,,the most important value , because this is the identifier of the row 
     */
    public void delete(String nameTable, int PrimaryKey){
         try {
            PreparedStatement st=con.prepareStatement("DELETE FROM " +nameTable+" WHERE ID="+PrimaryKey);
       st.executeUpdate();
       
        
        } catch (SQLException ex) {
            System.out.println("Exception on the delete "+ex.getMessage());
        }
    }
    //this method can isnt work , because the String array can be fail .
    
    
}
    
    
    
    
    
    
    
    

