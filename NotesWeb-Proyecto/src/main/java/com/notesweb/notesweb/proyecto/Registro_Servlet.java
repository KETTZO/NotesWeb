/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.notesweb.notesweb.proyecto;

import static com.sun.tools.doclint.Entity.part;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


/**
 *
 * @author misagamer
 */
@WebServlet(name = "Registro_Servlet", urlPatterns = {"/Registro_Servlet"})

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)

public class Registro_Servlet extends HttpServlet {

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
            out.println("<title>Servlet Registro_Servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Registro_Servlet at " + request.getContextPath() + "</h1>");
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
        
        //Usuario newUsuario = new Usuario(request.getParameter("nombre"), request.getParameter("apellido"), request.getParameter("correo"), request.getParameter("usuario"), String contra = request.getParameter("contra"), request.getParameter("contra2"), request.getParameter("Foto"), request.getParameter("fecha"));
        EjecucionesUsuario ejec = new EjecucionesUsuario();
        Usuario newUsuario = new Usuario(request.getParameter("nombre"),request.getParameter("apellido"), request.getParameter("correo"), request.getParameter("usuario"), request.getParameter("contra"), request.getParameter("contra2"),request.getParameter("Foto"),request.getParameter("fecha"));
        
        if(ejec.ValidarP(newUsuario)){
        
        
        
        
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String correo = request.getParameter("correo");
        String contra2 = request.getParameter("contra2");
        String usuario = request.getParameter("usuario");
        String contra = request.getParameter("contra");
        Date fecha = java.sql.Date.valueOf(request.getParameter("fecha"));
        Part part = request.getPart("image");
        InputStream inputstream = part.getInputStream();

        
        boolean valor = false;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/notesweb?useSSL=false&allowPublicKeyRetrieval=true","root","dcRZk2ez");
            //Blob foto = con.createBlob();
            //foto.setBytes(1, request.getParameter("Foto").getBytes("UTF-8"));
            PreparedStatement stmt = con.prepareStatement("UPDATE usuario(nombre, apellidos, nacimiento, email, foto, usuario, contra)VALUES(?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setDate(3, fecha);
            stmt.setString(4, correo);
            stmt.setBlob(5, inputstream);
            stmt.setString(6, usuario);
            stmt.setString(7, contra);
            stmt.execute();

            
            con.close();
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        
        response.sendRedirect("confirmacion.html"); 
        
        }
        else{
            response.sendRedirect("ErrorJSP.jsp"); 
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


