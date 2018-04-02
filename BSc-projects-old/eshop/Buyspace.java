package buyspace;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import database.Database;

public class Buyspace extends HttpServlet {
        private
        HttpSession session;
        PrintWriter out;
        Database db;
        String cardcvv;

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

        Integer elegxos = 0;
        String register = null;
        
       cardcvv = "00";
        Integer mysqlok=-1;
        Integer one=0;

        while(paramNames.hasMoreElements()) {
            one++;
            String paramName = (String)paramNames.nextElement();

          
            if  ( paramName.equals("register")   ){
                String[] paramValues5 = request.getParameterValues(paramName);
                register = paramValues5[0];
            }
            else if ( paramName.equals("cvv") ){
                String[] paramValues9 = request.getParameterValues(paramName);
                cardcvv = paramValues9[0];
            }

        }
        if(one==0)
            out.println("<script language=\"javascript\"> location=\"buyspace.jsp\"; </script>");
        else if(  cardcvv.equals("")  && register!=null  ){
            out.println("<script language=\"javascript\"> history.back(); alert(\"Please complete your cvv!\");</script>");
        }
        else if(register!=null){
            Buy();
        }


    }

    protected void Buy()
          throws ServletException, IOException {



    try {
        db = new Database();
        db.connect();
       

        ResultSet rs = null;
            java.util.Date d1 = new java.util.Date();

	

        ResultSet rs2 = null;
	String query = "SELECT * FROM cards WHERE username=\"" + session.getAttribute( "theName" ) + "\"";
	rs2 = db.selectquery(query);
	rs2.next();

        String cardno = rs2.getString("card");
        String date =  rs2.getString("date");
        String cvv = rs2.getString("cvv");
        rs2.close();
        Integer index = date.indexOf('/');
        String month = date.substring(0,index);
        String year = date.substring(index+1);
        if( Integer.parseInt(year) < (d1.getYear()+1900)){
            out.println("<script language=\"javascript\"> history.back(); alert(\"Your card has expired! Please replace your card\");</script>");
            return;
        }
         else if(Integer.parseInt(year) == (d1.getYear()+1900) && Integer.parseInt(month) <= d1.getMonth()){
            out.println("<script language=\"javascript\"> history.back(); alert(\"Your card has expired! Please replace your card\");</script>");
            return;
         }
        if(!cardcvv.equals(cvv)){
            out.println("<script language=\"javascript\"> history.back(); alert(\"Wrong cvv!\");</script>");
            return;
        }

        
        String query15 = "SELECT * FROM space WHERE username=\""+session.getAttribute( "theName" )+"\" ";
        ResultSet result15 = db.selectquery(query15);
        result15.next();
        long limit = Integer.parseInt(result15.getString("mylimit"));
        limit = limit+(1024*1024);

        result15.close();
        
        String query16 = "UPDATE space SET mylimit="+limit+" WHERE username=\""+session.getAttribute( "theName" )+"\" ";
        db.updatequery(query16);
     

        out.println("<script language=\"javascript\"> location=\"Myspace\"; alert(\"You've just bought 1GB!\");</script>");

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
