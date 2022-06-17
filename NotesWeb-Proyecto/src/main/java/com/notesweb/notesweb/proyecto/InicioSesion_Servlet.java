/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.notesweb.notesweb.proyecto;

import com.mysql.cj.xdevapi.Result;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import static java.time.LocalDate.now;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.YEARS;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author misagamer
 */
@WebServlet(name = "InicioSesion_Servlet", urlPatterns = {"/InicioSesion_Servlet"})
public class InicioSesion_Servlet extends HttpServlet {

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
            out.println("<title>Servlet InicioSesion_Servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InicioSesion_Servlet at " + request.getContextPath() + "</h1>");
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
        
        String usuario = request.getParameter("usuario");
        String contra = request.getParameter("contra");
        Usuario usuario2 = new Usuario();
        String fecha = "";
        boolean valor = false;
        try{
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/notesweb?useSSL=false&allowPublicKeyRetrieval=true","root","dcRZk2ez");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select * FROM Usuario");
            
            while(rs.next()){
                usuario2.Usuario = rs.getString("usuario");
                usuario2.Contra = rs.getString("contra");
                usuario2.Apellidos = rs.getString("apellidos");
                usuario2.Nombres = rs.getString("nombre");
                usuario2.Correo = rs.getString("email");
                fecha = rs.getString("nacimiento");
                if((usuario.equals(usuario2.Usuario) && contra.equals(usuario2.Contra))){
                        //CASO DE QUE EL USUARIO YA EXISTE
                        valor = true;
                    }
                if(valor)
                    break;
            }
            con.close();
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        if(valor){
            
            HttpSession miSesion = request.getSession();
            miSesion.setAttribute("usuario",usuario);
            miSesion.setAttribute("password", contra);
            
            LocalDate myDate = LocalDate.parse(fecha);

            LocalDate currentDate = LocalDate.now();

            long años = YEARS.between(myDate, currentDate);
            
            request.setAttribute("nombreusuario", usuario2.Nombres);
            //request.setAttribute("usuario", usuario2.Usuario);
            request.setAttribute("ApellidoU", usuario2.Apellidos);
            request.setAttribute("usuario", usuario);
            request.setAttribute("CorreoU", usuario2.Correo);
            request.setAttribute("ContraseñaU", usuario2.Contra);
            request.setAttribute("edad", años);
            request.setAttribute("fecha", fecha);
            //response.sendRedirect(request.getContextPath() + "/Perfil.jsp");
            RequestDispatcher rd = request.getRequestDispatcher("/Perfil.jsp");
            rd.forward(request, response);
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
