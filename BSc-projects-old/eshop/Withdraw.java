/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package withdraw;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import database.Database;

public class Withdraw extends HttpServlet {
        private
        HttpSession session;
        PrintWriter out;
        Database db;
        Integer amount;

    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response)
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

      
        Integer mysqlok=-1;
        Integer one=0;

        while(paramNames.hasMoreElements()) {
            one++;
            String paramName = (String)paramNames.nextElement();


            if  ( paramName.equals("submit")   ){
                String[] paramValues5 = request.getParameterValues(paramName);
                register = paramValues5[0];
            }
            else if(paramName.equals("amount")   ){
                        String[] paramValues = request.getParameterValues(paramName);
                        try {
                            amount=Integer.parseInt(paramValues[0]);
                        }
                        catch(NumberFormatException nFE){
                            out.println("<script language=\"javascript\"> alert('Not an Integer'); history.back(); </script>");
                            return ; 
                        }
                    }

        }
        if(one==0)
            out.println("<script language=\"javascript\"> location=\"status.jsp\"; </script>");
        else if(register!=null){
            analipsi();
        }


    }

    protected void analipsi()
          throws ServletException, IOException {



    try {
        db = new Database();
        db.connect();


        ResultSet rs = null;
            java.util.Date d1 = new java.util.Date();



        ResultSet rs2 = null;
	String query = "SELECT * FROM cards,users WHERE users.username=cards.username AND users.username=\"" + session.getAttribute( "theName" ) + "\"";
	rs2 = db.selectquery(query);
	rs2.next();

        String cardno = rs2.getString("card");
        String date =  rs2.getString("date");
        String cvv = rs2.getString("cvv");
        Integer mymoney = rs2.getInt("mymoney");
        if(mymoney<amount){
           out.println("<script language=\"javascript\"> history.back(); alert(\"Not enough funds to withdraw\");</script>");
           return;
        }

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
        

        String query16 = "UPDATE users SET mymoney=mymoney-"+amount+" WHERE username=\""+session.getAttribute( "theName" )+"\" ";
        db.updatequery(query16);
        out.println("<script language=\"javascript\">  alert(\"We just deposit "+amount+"$ in your credit card!\"); location=\"status.jsp\"</script>");

    }

    catch (SQLException e) {
      throw new ServletException("Servlet Could not display records.", e);
      }
    finally{
        db.close();
    }
  }

   

}
