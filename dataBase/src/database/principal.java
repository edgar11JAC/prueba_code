/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.File;
import java.sql.*;

/**
 *
 * @author Bienvenido
 */
public class principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        principal cr=new principal();
        cr.CrearDB();
        //cr.insert();
        cr.select("Pedro");
        //cr.close();
        
    }
    public void CrearDB(){
        //carpeta para la base de datos
        Connection con;
        String barra=File.separator;
        String proyecto = System.getProperty("user.dir")+barra+"Resgitros";
        File url=new File(proyecto);
        //comprobar si la base de datos existe
        if(url.exists()){
            System.out.println("Base de datos existe");
        }else{
            //crear carpeta de base de datos y la base de datos
            try {
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                String db = "jdbc:derby:"+proyecto+";create=true";
                con = DriverManager.getConnection(db);
                
                String tabla = "create table Usuarios(Nombre varchar(50), Contrasena varchar(50), Permiso varchar(50) )";
                PreparedStatement ps=con.prepareStatement(tabla);
                ps.execute();
                ps.close();
                con.close();
                System.out.println("Base de datos creada");
                
            } catch (ClassNotFoundException | SQLException ex) {
                System.out.println("Error: "+ex);
            }
            
        }    
    }
    
    public void insert(){
        
        Connection con;
        String barra=File.separator;
        String proyecto = System.getProperty("user.dir")+barra+"Resgitros";
       
        
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            String db = "jdbc:derby:"+proyecto;
            con=DriverManager.getConnection(db);
            
            System.out.println("Base de datos cargada");
            if(con!=null){
                String c="Insert Into Usuarios(Nombre,Contrasena,Permiso) Values('Pedro','45','user')";
                PreparedStatement ps=con.prepareStatement(c);
                ps.executeUpdate();
                ps.close();
                con.close();
                System.out.println("Registro Creado");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error: "+ex);
        } 
    }
    public void close(){
        String barra=File.separator;
        String proyecto = System.getProperty("user.dir")+barra+"Resgitros";
        String db2 = "jdbc:derby:"+proyecto+";shutdown=true";
        try {
            DriverManager.getConnection(db2);
        } catch (SQLException ex) {
            System.out.println("Error: "+ex);
        }
    }
    public void select(String v){
        Connection con;
        String barra=File.separator;
        String proyecto = System.getProperty("user.dir")+barra+"Resgitros";
       
        
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            String db = "jdbc:derby:"+proyecto;
            con=DriverManager.getConnection(db);
            
            System.out.println("Base de datos cargada");
            if(con!=null){
                //String c="select * from Usuarios where Nombre="+v;
                String c="select * from Usuarios";
                System.out.println("Se creo la consulta");
                Statement ps=con.createStatement();
                System.out.println("Se creo el statement");
                ResultSet r=ps.executeQuery(c);
                System.out.println("Se ejecuto");
                boolean st=false;
                while(r.next()){
                    if("Timo".equals(r.getString(1))){
                        st=true;
                    }
                    if(st){
                        System.out.println("Consulta realizada, se obtuvo "+r.getString(1));
                    }
                    
                }
                ps.close();
                con.close();
                //System.out.println("Consulta realizada, se obtuvo "+r.getString(1));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error: "+ex);
        } 
    }
    
}
