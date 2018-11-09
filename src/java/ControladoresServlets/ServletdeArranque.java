/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresServlets;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nicolasgutierrez
 */
public class ServletdeArranque extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    ServletContext context = null;
    private static Propiedades propIns = Propiedades.getInstance();
    
    @Override
    public void init() throws ServletException {

        super.init(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
     @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private Properties levantarProperties(String path) {
        InputStream in = null;
        Properties prop = new Properties();
        System.out.println("levantarProperties inicio...");
        String dirWsu = null, dirWsp = null, dirWsc = null;
        String pathP = generaPathProperties(path);
        try {

            in = new FileInputStream(dameArchivo(path));
            prop.load(in);
            dirWsu = "http://" + prop.getProperty("wsuip") + ":" + prop.getProperty("wsupuerto") + "/" + prop.getProperty("wsuname");
            dirWsp = "http://" + prop.getProperty("wspip") + ":" + prop.getProperty("wsppuerto") + "/" + prop.getProperty("wspname");
            dirWsc = "http://" + prop.getProperty("wscip") + ":" + prop.getProperty("wscpuerto") + "/" + prop.getProperty("wscname");
            propIns.setWsU(dirWsu);
            propIns.setWsP(dirWsp);
            propIns.setWsC(dirWsc);
            Iterator it = prop.keySet().iterator();
            while (it.hasNext()) {
                String key = (String) it.next();
                System.out.println("Properties claves cargadas: ");
                System.out.println(key);

            }

            System.out.println("carga finalizada");
        } catch (Exception e) {
            System.err.println("levantarProperties: " + e.getMessage());
        }

        return prop;
    }
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            context = request.getSession().getServletContext();

            System.out.println(context.getResource("").getPath() + " getResources");
            //  System.out.println(context.getContextPath()+" getContextPath");
            //  System.out.println(context.getResource("")+" getResource");
            levantarProperties(context.getResource("").getPath());
            
            response.sendRedirect("Login");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
            
        }
    
      private String generaPathProperties(String cadena) {
        String path = "/";
        try {
            String[] subCadena = cadena.split("/");
            int largo = subCadena.length;
            for (int i = 1; i < subCadena.length - 1; i++) {
                path += subCadena[i] + "/";
                System.out.println(subCadena[i]);
                System.out.println(path);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return path;
    }

    private File dameArchivo(String path) {
        String ruta = generaPathProperties(path);
        File retorno = caminar(new File(path));
        if (retorno != null) {
            return retorno;
        }
        return dameArchivo(ruta);
    }

    private File caminar(File dir) {
        File listFile[] = dir.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (int i = 0; i < listFile.length; i++) {
                if (listFile[i].isDirectory()) {
                    caminar(listFile[i]);
                }
                if (listFile[i].isFile()) {
                    if (listFile[i].getName().equals("configuracionweb.properties")) {
                        return (File) listFile[i];
                    }
                }
            }
        }
        return null;
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
    // </editor-fold>

}
