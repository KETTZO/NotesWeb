/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notesweb.notesweb.proyecto;

/**
 *
 * @author misagamer
 */
public class Notas {
    public int ID;
    public String texto;
    public int usuario;
    public boolean status;
    public String creacion;
    
    public Notas( String texto, int usuario){
        this.texto = texto;
        this.usuario = usuario;
    }
    
    public Notas(int ID, String texto, int usuario){
        this.ID = ID;
        this.texto = texto;
        this.usuario = usuario;
    }
    
    public Notas(int ID, String texto, int usuario, String creacion){
        this.ID = ID;
        this.texto = texto;
        this.usuario = usuario;
        this.creacion = creacion;
    }
    
    public Notas(int ID, int usuario){
        this.ID = ID;
        this.usuario = usuario;
    }
    
    public Notas(){
        this.ID = ID;
        this.usuario = usuario;
    }
    
    @Override
    public String toString(){
        return ID + " " + texto + " " + usuario + " " + creacion;
    }
}
