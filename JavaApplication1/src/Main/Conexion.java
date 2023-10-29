/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

public class Conexion {

    Connection conectar = null;
    
    String usuario = "root"; // usuario de mi SQL
    
    String contrasena = "root"; // password
    
    String db = "tareas"; // nombre de la base de datos
    
    String ip = "localhost"; // la IP la cual me conecto
    
    String puerto = "3306"; // el puerto
    
    String cadena = "jdbc:mysql://" + ip + ":" + puerto + "/" + db; // armo la cadena para pasar al driver
    
    Statement st = null;
    
    ResultSet rs = null;
    
    public Connection establecerConeccion(){
        
        try{
            
            conectar = DriverManager.getConnection(cadena, usuario, contrasena);
           
            
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Se producido un error" + e.toString());
        }
        return conectar;
        
        
    }
    public void insertarDatos(){
       Connection con = establecerConeccion();  // declaramos el objeto con y establecemos conexion
    
       if (con != null){
           try{
           
           Statement datament  = con.createStatement();
           
           
           String nuevoDato = JOptionPane.showInputDialog("Ingrese el nombre de la tarea");
            String nuevoDatoTwo = JOptionPane.showInputDialog("Ingrese la descripcion de la tarea : ");
           
           String query = "INSERT INTO tarea(nombre_tarea, descripcion_tarea) VALUES('" + nuevoDato + "', '" + nuevoDatoTwo + "')";
           datament.executeUpdate(query);
           JOptionPane.showMessageDialog(null, "Se inserto la tarea correctamente");
           
           
           datament.close();
           
           
           con.close();
           }catch(Exception e){
           e.printStackTrace();
           }
           
       }
       
    }
    
    public void actualizarDatos(){
        Connection con = establecerConeccion(); 
        
        
        if (con != null){
           try{
               
               Statement datament  = con.createStatement();
               
              String nuevoDato = JOptionPane.showInputDialog("Ingrese el ID que quiera modificar");
              String nuevoDatoTwo = JOptionPane.showInputDialog("Ingrese la nueva tarea : ");
              String nuevoDatoThre = JOptionPane.showInputDialog("Ingrese la nueva descripcion");

               String consultaSQL = "UPDATE tarea " +
                             "SET nombre_tarea = '" + nuevoDatoTwo + "', descripcion_tarea = '" + nuevoDatoThre + "' " +
                             "WHERE tarea_id = " + nuevoDato;


               datament.executeUpdate(consultaSQL);
               
               datament.close();
               
               con.close();
                JOptionPane.showMessageDialog(null, "Se modifico la tarea correctamente");
               
           }catch(Exception e){
               
           }
        }
    
    }
    
    public void consultarDatos(){
        Connection con = establecerConeccion(); 
        
        
        if (con != null){
           try{
               
               Statement datament  = con.createStatement();
               
                rs = datament.executeQuery("SELECT * FROM tarea");
            
            while(rs.next()){
                int idCarrera = rs.getInt(1);
                String nombreCarrera = rs.getString(2);
                String descripcionTarea = rs.getString(3);
                System.out.println("ID DE LA TAREA  " + idCarrera);
                System.out.println("Nombre de la tarea : " + nombreCarrera);
                System.out.println("DESCRIPCION DE LA TAREA : " + descripcionTarea);
                
            }
            datament.close();
            
            con.close();
               
               
               
               
           }catch(Exception e){
               
           }
        }
               
           }
    
    public void eliminarDatos(){
             Connection con = establecerConeccion(); 
        
        
            if (con != null){
             try{
               String nuevoDato = JOptionPane.showInputDialog("Ingrese el ID que quiera eliminar");
               
               Statement datament  = con.createStatement();
               
               String query = "DELETE FROM tarea WHERE tarea_id = " + nuevoDato;
               
               datament.executeUpdate(query);
               
               JOptionPane.showMessageDialog(null, "Se elimino correctamente la tarea");
               
               datament.close();
               
               con.close();
               
    }catch(Exception e){
               System.out.println("Ocurrio un error");
           }
    
    }
    
    
    }
    
}
    
