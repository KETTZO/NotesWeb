/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.notesweb.notesweb.proyecto;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author misagamer
 */
@WebServlet(name = "TraerNotas_Servlet", urlPatterns = {"/TraerNotas_Servlet"})
public class TraerNotas_Servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TraerNotas_Servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TraerNotas_Servlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String usuario;
        String id = "";
        ArrayList<Notas> ListaNotas = new ArrayList<Notas>();
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con1 = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/notesweb?useSSL=false&allowPublicKeyRetrieval=true","root","dcRZk2ez");
            Statement stmt1 = con1.createStatement();
            ResultSet rs = stmt1.executeQuery("Select * FROM Usuario");
            
            while(rs.next()){
                usuario = rs.getString("usuario");
                
                if((request.getSession().getAttribute("usuario").equals(usuario))){
                    id = rs.getString("idUsuario");
                }
            }
            con1.close();
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/notesweb?useSSL=false&allowPublicKeyRetrieval=true","root","dcRZk2ez");
            Statement stmt = con.createStatement();
            ResultSet rs2 = stmt.executeQuery("Select * FROM notas");
            
            String us;
            
            while(rs2.next()){
                us = rs2.getString("usuario");
                if(id.equals(us)){
                    Notas nota = new Notas();
                    nota.ID = rs2.getInt("idNotas");
                    nota.texto = rs2.getString("texto");
                    nota.usuario = rs2.getInt("usuario");
                    nota.creacion = rs2.getString("creacion");
                    
                    ListaNotas.add(nota);
                }
            }
            
            con.close();
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        
        request.setAttribute("ListaNotas", ListaNotas);
        request.getRequestDispatcher("Notas.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
