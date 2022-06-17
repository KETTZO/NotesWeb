/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.notesweb.notesweb.proyecto;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author misagamer
 */
@WebServlet(name = "EditarEmpleado_Servlet", urlPatterns = {"/EditarEmpleado_Servlet"})
public class EditarEmpleado_Servlet extends HttpServlet {

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
            out.println("<title>Servlet EditarEmpleado_Servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditarEmpleado_Servlet at " + request.getContextPath() + "</h1>");
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
        
        EjecucionesUsuario ejec = new EjecucionesUsuario();
        Usuario newUsuario = new Usuario(request.getParameter("nombre"),request.getParameter("apellido"), request.getParameter("correo"), "0", request.getParameter("contra"), request.getParameter("contra2"),request.getParameter("Foto"),request.getParameter("fecha"));
        
        if(ejec.ValidarP(newUsuario)){
        
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String correo = request.getParameter("correo");
        String contra2 = request.getParameter("contra2");
        String usuario = request.getParameter("usuario");
        String contra = request.getParameter("contra");
        Date fecha = java.sql.Date.valueOf(request.getParameter("fecha"));
        
        boolean valor = false;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/notesweb?useSSL=false&allowPublicKeyRetrieval=true","root","dcRZk2ez");
            //Blob foto = con.createBlob();
            //foto.setBytes(1, request.getParameter("Foto").getBytes("UTF-8"));
            PreparedStatement stmt = con.prepareStatement("UPDATE usuario SET nombre = ?, apellidos = ?, nacimiento = ?, email = ?, contra = ? WHERE usuario = ?");
            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setDate(3, fecha);
            stmt.setString(4, correo);
            stmt.setString(5, contra);
            stmt.setString(6, request.getSession().getAttribute("usuario").toString());
            stmt.execute();

            
            con.close();
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        
        response.sendRedirect("Perfil.jsp"); 
        
        }
        else{
            
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
