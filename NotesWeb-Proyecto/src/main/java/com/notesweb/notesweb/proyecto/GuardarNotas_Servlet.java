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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author misagamer
 */
@WebServlet(name = "GuardarNotas_Servlet", urlPatterns = {"/GuardarNotas_Servlet"})
public class GuardarNotas_Servlet extends HttpServlet {

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
            out.println("<title>Servlet GuardarNotas_Servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GuardarNotas_Servlet at " + request.getContextPath() + "</h1>");
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
       
        String Nota = request.getParameter("Nota");
        String usuario;
        String id = "";
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con1 = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/notesweb?useSSL=false&allowPublicKeyRetrieval=true","root","dcRZk2ez");
            Statement stmt1 = con1.createStatement();
            ResultSet rs = stmt1.executeQuery("Select * FROM Usuario");
            
            while(rs.next()){
                usuario = rs.getString("usuario");
                
                if((request.getSession().getAttribute("usuario").equals(usuario))){
                        //CASO DE QUE EL USUARIO YA EXISTE
                        id = rs.getString("idUsuario");
                }
            }
            con1.close();
            
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/notesweb?useSSL=false&allowPublicKeyRetrieval=true","root","dcRZk2ez");
            //Blob foto = con.createBlob();
            //foto.setBytes(1, request.getParameter("Foto").getBytes("UTF-8"));
            PreparedStatement stmt = con.prepareStatement("INSERT INTO notas(texto, usuario)VALUES(?, ?)");
            stmt.setString(1, Nota);
            stmt.setInt(2, Integer.parseInt(id));
            stmt.execute();

            
            con.close();
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        
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
