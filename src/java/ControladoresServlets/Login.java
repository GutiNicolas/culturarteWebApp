/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresServlets;

import Logica.ContUsuario;
import Logica.dtUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nicolasgutierrez
 */
public class Login extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            ContUsuario cU = ContUsuario.getInstance();
            String usuario = request.getParameter("nick");
            String password = request.getParameter("pass");
            if(session.getAttribute("rol")==null){ 
            if (usuario != null) {
                dtUsuario dtu = cU.usuarioLoginApp(usuario);
                if (dtu != null) {
                    if (dtu.getPass().equals(password)) {
                        session.setAttribute("nickusuario", dtu.getNickname());
                        session.setAttribute("rol", dtu.getRol()); 
                        response.sendRedirect("PRESENTACIONES/listadopropuestas.jsp");
                           //request.getRequestDispatcher("Propuesta").forward(request, response);
                       
                    } else {//contrasenia erronea                  
                        request.getRequestDispatcher("PRESENTACIONES/login.jsp?error=pm").forward(request, response);
                    }

                } else {  //no existe el usuario             
                    request.getRequestDispatcher("PRESENTACIONES/login.jsp?error=nu").forward(request, response);
                }
            } else {             
                request.getRequestDispatcher("PRESENTACIONES/login.jsp").forward(request, response);
            }
        }else{
                response.sendRedirect("PRESENTACIONES/listadopropuestas.jsp");
                //request.getRequestDispatcher("Propuesta").forward(request, response);
                }
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
        processRequest(request, response);
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
