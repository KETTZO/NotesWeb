/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notesweb.notesweb.proyecto;

import java.sql.Blob;

/**
 *
 * @author misagamer
 */
public class Usuario {
    public int ID;
        public String Nombres;
        public String Apellidos;
        public String Correo;
        public String Usuario;
        public String Contra;
        public String Contra2;
        public String Foto;
        public String FechaN;
        public String Creacion;
        public boolean Estatus;
                
        public Usuario(String Nombres,String Apellidos, String Correo,String Usuario,String Contra,String Contra2,String Foto,String FechaN){
                    
                this.Nombres =Nombres;
                this.Apellidos =Apellidos;
                this.Correo =Correo;
                this.Usuario=Usuario;
                this.Contra=Contra;
                this.Contra2=Contra2;
                this.Foto=Foto;
                this.FechaN=FechaN;
                    
        }
                
        public Usuario(int ID,String Nombres,String Apellidos, String Correo,String Usuario,String Contra,String Foto, String FechaN, String Creacion, boolean Estatus){
                    
                this.ID =ID;
                this.Nombres =Nombres;
                this.Apellidos =Apellidos;
                this.Correo =Correo;
                this.Usuario=Usuario;
                this.Contra=Contra;
                this.Foto=Foto;
                this.FechaN=FechaN;
                this.Creacion=Creacion;
                //this.Eliminado=Eliminado;
                    
        }
        
        public Usuario(String Usuario,String Contra){
                this.Usuario=Usuario;
                this.Contra=Contra;            
        }
        
        public Usuario(){
                         
        }
                
        @Override
        public String toString(){
            return ID + " - " + Nombres + " - " + Apellidos + " - " + Correo + " - " + Usuario + " - " + Contra + " - " + FechaN + " - " + Creacion;
        }
}
