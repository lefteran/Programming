/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package passwordreminder;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import database.Database;

public class Remindpass extends HttpServlet {
        private
        PrintWriter out;
        Database db;
        String email;

    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response)
      throws ServletException, IOException {
        System.out.println("<meta http-equiv=\"Content-Type\" content=\"text/html\" charset=\"UTF-8\" />");
        System.out.println("<%@page contentType=\"text/html\" pageEncoding=\"UTF-8\"%>");
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        out = response.getWriter();



        Enumeration paramNames = request.getParameterNames();

        Integer elegxos = 0;
        String register = null;


        Integer mysqlok=-1;
        Integer one=0;

        while(paramNames.hasMoreElements()) {
            one++;
            String paramName = (String)paramNames.nextElement();


            if  ( paramName.equals("email")   ){
                String[] paramValues5 = request.getParameterValues(paramName);
                email = paramValues5[0];
            }

        }
        if(one==0)
            out.println("<script language=\"javascript\"> location=\"forgot.jsp\"; </script>");

        else
            send();



    }

    protected void send()
          throws ServletException, IOException {



    try {
        db = new Database();
        db.connect();


        ResultSet rs = null;
          



        ResultSet rs2 = null;
	String query = "SELECT * FROM users WHERE email=\"" + email + "\"";
	rs2 = db.selectquery(query);
        if(rs2.next()){
            
            out.println("<script language=\"javascript\"> location=\"login.jsp\" alert(\"You've just receiven an email wth your password!\");</script>");
        }
        else out.println("<script language=\"javascript\"> history.back(); alert(\"Your email doesn't exist in our database!\");</script>");

        

    }

    catch (SQLException e) {
      throw new ServletException("Servlet Could not display records.", e);
      }
     finally{
        db.close();
    }
  }

    @Override
  protected void doPost(HttpServletRequest request,
          HttpServletResponse response)
          throws ServletException, IOException {
      doGet(request, response);
  }


}
