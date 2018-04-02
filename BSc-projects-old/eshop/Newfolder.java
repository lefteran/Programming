package newfolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import database.Database;


public class Newfolder extends HttpServlet {
    private
        HttpSession session;
        PrintWriter out;
        Database db;

    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response)
      throws ServletException, IOException {
        session = request.getSession(true);
        System.out.println("<meta http-equiv=\"Content-Type\" content=\"text/html\" charset=\"UTF-8\" />");
        System.out.println("<%@page contentType=\"text/html\" pageEncoding=\"UTF-8\"%>");
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        out = response.getWriter();

        if(session.getAttribute( "theName" )== null){
            out.println("<script language=\"javascript\"> location=\"login.jsp\"; </script>");
            return;
        }



        Enumeration paramNames = request.getParameterNames();
        String name="";
        String register=null;
       

        Integer one=0;
        while(paramNames.hasMoreElements()) {
            one++;
            String paramName = (String)paramNames.nextElement();

            if(paramName.equals("name")   ){
                String[] paramValues = request.getParameterValues(paramName);
                name = paramValues[0];
                if(name.length()>30){
                                    out.println("<script language=\"javascript\"> history.back(); alert(\"Too big directory name!\");</script>");
                                    return;
                                }
            }
            else if ( paramName.equals("register") ){
                String[] paramValues6 = request.getParameterValues(paramName);
                register = paramValues6[0];
            }
        }

    if(one==0)
        out.println("<script language=\"javascript\"> location=\"newfolder.jsp\"; </script>");
    else if( name.equals("") && register!=null){
        out.println("<script language=\"javascript\"> history.back(); alert(\"Please complete the name!\");</script>");
        return;
    }
    else {
        newdir(request,response,name);
    }


  }

  protected void newdir(HttpServletRequest request,HttpServletResponse response,String name)
          throws ServletException, IOException {


    Integer wrongname=-1;
  


    try {

        db = new Database();
        db.connect();
        ResultSet rs = null;

       
        String query12 = "SELECT COUNT(*) FROM directories WHERE username=\""+session.getAttribute( "theName" )+"\" AND name=\""+name+"\"";
        ResultSet result12 = db.selectquery(query12);
        result12.next();
        if(result12.getString("COUNT(*)").equals("1")){
            wrongname=1;             //fakelos uparxei hdh
            result12.close();
            out.println("<script language=\"javascript\"> history.back(); alert(\"Folder already exists!\");</script>");
        }
        else{
           
            String query7 = "INSERT INTO directories VALUES(\""+name+"\",\""+ session.getAttribute( "theName" )  +"\")";
            db.updatequery(query7);
            out.println("<script language=\"javascript\"> history.back(); alert(\"You've just created a new folder!\");</script>");
        }
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
