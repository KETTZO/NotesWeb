/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notesweb.notesweb.proyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author misagamer
 */
public class EjecucionesUsuario {
    public boolean ValidarP(Usuario usuario){
        boolean valor = true;
         try{
            Matcher matcher = null;
        
            String nombreregex = "^[a-zA-ZÀ-ÿ\u00f1\u00d1]+(\\s*[a-zA-ZÀ-ÿ\u00f1\u00d1]*)*[a-zA-ZÀ-ÿ\u00f1\u00d1]+$";
            Pattern nombrePattern = Pattern.compile(nombreregex);
            matcher = nombrePattern.matcher(usuario.Nombres);
             if(matcher.matches()==false){
                //ERROR EN NOMBRE
                valor = false;
                //throw new RuntimeException("Formato de nombre incorrecto");
            }
            String apellidosregex = "^[a-zA-ZÀ-ÿ\\u00f1\\u00d1]+(\\s*[a-zA-ZÀ-ÿ\\u00f1\\u00d1]*){2}$";
            Pattern apellidosPattern = Pattern.compile(apellidosregex);
            matcher = apellidosPattern.matcher(usuario.Apellidos);
            if(matcher.matches()==false){
                //ERROR EN APELLIDOP
                valor = false;
                //throw new RuntimeException("Formato de apellido Materno incorrecto");
            }

            String mailregex = "\\w{3,20}@\\w+\\.(com|org|cn|net|gov)";
            Pattern mailPattern = Pattern.compile(mailregex);
            matcher = mailPattern.matcher(usuario.Correo);
            if(matcher.matches()==false){
                //ERROR EN EMAIL
                valor = false;
                //throw new RuntimeException("Formato de correo electrónico incorrecto");
            }
            
            String contraregex = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}";
            Pattern contraPattern = Pattern.compile(contraregex);
            matcher = contraPattern.matcher(usuario.Contra);
            if(matcher.matches()==false){
                //ERROR EN CONTRASEÑA1
                valor = false;
                //throw new RuntimeException("Formato de contraseña incorrecto");
            }
        
            String usuregex = "^[a-zA-ZÀ-ÿ\u00f1\u00d1].{5,20}$";
            Pattern usuPattern = Pattern.compile(usuregex);
            matcher = usuPattern.matcher(usuario.Usuario);
            if(!(usuario.Usuario == "0")){
            if(matcher.matches()==false){
                //ERROR EN USUARIO
                valor = false;
                //throw new RuntimeException("Formato de usuario incorrecto");
            }
            }
            if (!(usuario.Contra.equals(usuario.Contra2))) {
                //CASO DE QUE LAS CONTRASEÑAS NO COINCIDEN
                valor = false;
                //throw new RuntimeException("Las contraseñas deben de ser iguales");
            }
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/notesweb?useSSL=false&allowPublicKeyRetrieval=true","root","dcRZk2ez");
            Statement stmt = con.createStatement();
            ResultSet rs =stmt.executeQuery("select * from Usuario");
             
             while (rs.next()){
                 
                String Us = rs.getString("Usuario");
                //System.out.println(Us + " " + persona.Usuario);
                if(Us.equals(usuario.Usuario)){
                    //CASO DE QUE EL USUARIO YA EXISTE
                    valor = false;
                    throw new RuntimeException("El usuario ingresado ya existe, elija otro por favor");
                }
            }
            con.close();
                
        }catch(Exception ex){
            System.out.println(ex);
        }
         return valor;
    }
    
    public void ConsultaP(){
        try{
            
             Class.forName("com.mysql.cj.jdbc.Driver");
             Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/notesweb","root","dcRZk2ez");
             Statement stmt = con.createStatement();
             ResultSet rs =stmt.executeQuery("select * from Usuario");
             
             while (rs.next()){
                 
                 Usuario persona = new Usuario(
                         rs.getInt("idUsuario"),
                         rs.getString("nombre"),
                         rs.getString("apellidos"),
                         rs.getString("email"),
                         rs.getString("usuario"),
                         rs.getString("Contra"),
                         //rs.getString("Contra2"),
                         rs.getString("foto"),
                         rs.getString("nacimiento"),
                         rs.getString("creacion"),
                         rs.getBoolean("Eliminado")
                         
                );
                   System.out.println(persona);
             }
             con.close();
             
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    
    public void InsertarP(Usuario persona){
        
         try{
            
             Class.forName("com.mysql.cj.jdbc.Driver");
             Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/notesweb","root","dcRZk2ez");
             PreparedStatement stmt = con.prepareStatement("insert into Usuario (nombre, apellidos, email, usuario, contra, foto, nacimiento) values(?,?,?,?,?,?,?)");
             stmt.setString(1, persona.Nombres);
             stmt.setString(2, persona.Apellidos);
             stmt.setString(3, persona.Correo);
             stmt.setString(4, persona.Usuario);
             stmt.setString(5, persona.Contra);
             //stmt.setString(7, persona.Contra2);
             stmt.setString(6, persona.Foto);
             stmt.setString(7, persona.FechaN);
             stmt.execute();
             con.close();
             
        }catch(Exception ex){
            System.out.println(ex);
        }
        
    }
    
    public int Sesion(Usuario usuario){
        int valor = 0;
        try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/notesweb","root","dcRZk2ez");
                Statement stmt = con.createStatement();
                ResultSet rs =stmt.executeQuery("select * from Usuario");
             
                while (rs.next()){
                 
                    String Us = rs.getString("Usuario");
                    String contra = rs.getString("contra");
                    if((Us.equals(usuario.Usuario) && contra.equals(usuario.Contra))){
                        //CASO DE QUE EL USUARIO YA EXISTE
                        valor = 1;
                    }
                }
                con.close();
                System.out.println("se ha iniciado sesión");
        }catch(Exception ex){
            System.out.println(ex);
        }
        return valor;
    }
}
