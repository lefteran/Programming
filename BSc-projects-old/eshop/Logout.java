package logout;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import database .Database;

/**
 *
 * @author johnfouf
 */
public class Logout extends HttpServlet {
   
         private
        HttpSession session;
        PrintWriter out;
        Database db;
     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)

    throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
           session = request.getSession(true);
          /* if (session == null){

           }*/
       out = response.getWriter();
       //######################## Automati eisodos #############################################
       db = new Database();
       db.connect();
       String query2 = "UPDATE users SET login=0 WHERE username=\""+session.getAttribute( "theName" )+"\" ";

        db.updatequery(query2);
//##############################################################################

           session = request.getSession(true);
           session.invalidate();
            
        db.close();
    
             out.println("<script language=\"javascript\"> location=\"index.htm\"; </script>");
       
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
