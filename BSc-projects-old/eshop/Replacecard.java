package replacecard;


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import database.Database;



public class Replacecard extends HttpServlet {
    private

        String cardno1 ;
        String cardno2 ;
        String cardno3 ;
        String cardno4 ;
        String cardcvv ;
        PrintWriter out;
        Database db;
        HttpSession session;
        String month;
        String year;


    @Override
 protected void doGet(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
    session = request.getSession(true);
    System.out.println("<meta http-equiv=\"Content-Type\" content=\"text/html\" charset=\"UTF-8\" />");
    System.out.println("<%@page contentType=\"text/html\" pageEncoding=\"UTF-8\"%>");
    response.setContentType("text/html");
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    out = response.getWriter();

    Enumeration paramNames = request.getParameterNames();

    year="";
    month="";
    cardno1 = "";
    cardno2 = "";
    cardno3 = "";
    cardno4 = "";
    cardcvv = "";
    Integer one=0;

    while(paramNames.hasMoreElements()) {
        one++;
        String paramName = (String)paramNames.nextElement();

        if ( paramName.equals("month") ){
            String[] paramValues8 = request.getParameterValues(paramName);
            month = paramValues8[0];
	}
        else if ( paramName.equals("year") ){
            String[] paramValues8 = request.getParameterValues(paramName);
            year = paramValues8[0];
	}
        else if ( paramName.equals("cardnumber1") ){
            String[] paramValues9 = request.getParameterValues(paramName);
            cardno1 = paramValues9[0];
	}
        else if ( paramName.equals("cardnumber2") ){
            String[] paramValues9 = request.getParameterValues(paramName);
            cardno2 = paramValues9[0];
	}
        else if ( paramName.equals("cardnumber3") ){
            String[] paramValues9 = request.getParameterValues(paramName);
            cardno3 = paramValues9[0];
	}
        else if ( paramName.equals("cardnumber4") ){
            String[] paramValues9 = request.getParameterValues(paramName);
            cardno4 = paramValues9[0];
	}
        else if ( paramName.equals("cvv") ){
            String[] paramValues9 = request.getParameterValues(paramName);
            cardcvv  = paramValues9[0];
	}
    }




    java.util.Date d1 = new java.util.Date();
 try {
    if(one==0)
        out.println("<script language=\"javascript\"> location=\"register.jsp\"; </script>");

    else if(  cardcvv.equals("") || year.equals("") || cardno1.equals("") || month.equals("") || cardno2.equals("") || cardno3.equals("") || cardno4.equals("")){

        out.println("<script language=\"javascript\"> history.back(); alert(\"Please complete the form!\");</script>");
    }


    else if(  Integer.parseInt(cardcvv) > 999 ||  Integer.parseInt(cardcvv) < 0  ||  cardcvv.length() !=3 ){

        out.println("<script language=\"javascript\"> history.back(); alert(\"Wrong cvv was given!\");</script>");
    }
    else if(Integer.parseInt(cardno4) > 9999 ||  Integer.parseInt(cardno4) < 0  ||Integer.parseInt(cardno2) > 9999 ||  Integer.parseInt(cardno2) < 0  || Integer.parseInt(cardno3) > 9999 ||  Integer.parseInt(cardno3) < 0  || Integer.parseInt(cardno1) > 9999 ||  Integer.parseInt(cardno1) < 0  || cardno1.length() !=4 || cardno2.length() !=4 || cardno3.length() !=4 || cardno4.length() !=4  ){

        out.println("<script language=\"javascript\"> history.back(); alert(\"Wrong card number was given!\");</script>");
    }
     else if( Integer.parseInt(year) < (d1.getYear()+1900)){

        out.println("<script language=\"javascript\"> history.back(); alert(\"Your card has already expired!\");</script>");
    }
    else if( Integer.parseInt(month) < 1 || Integer.parseInt(month) > 12){

        out.println("<script language=\"javascript\"> history.back(); alert(\"Wrong month was given!\");</script>");
    }
     else if(Integer.parseInt(year) == (d1.getYear()+1900) && Integer.parseInt(month) <= d1.getMonth()){

        out.println("<script language=\"javascript\"> history.back(); alert(\"Your card has already expired!\");</script>");
    }
     else {
        submitcard();
    }

     } catch(NumberFormatException nFE){
                            out.println("<script language=\"javascript\"> alert('Wrong credit card details (Complete with numeric values)'); history.back(); </script>");
                        }

    }

   protected void submitcard()
          throws ServletException, IOException {

        Integer mysqlok=-1;
        Integer mysqluser=-1;
        Integer mysqlemail=-1;
   
        db = new Database();
        db.connect();
        String cardno=cardno1+cardno2+cardno3+cardno4;
        String date = month+"/"+year;
          
        String query = "UPDATE cards SET card=\""+cardno+"\" , date=\""+date+"\" , cvv=\""+cardcvv+"\" WHERE username=\""+session.getAttribute( "theName" )+"\"";
        db.updatequery(query);
         
        out.println("<script language=\"javascript\"> location=\"Myspace\"; alert(\"Your card has been replaced !\");</script>");

        db.close();

    
    
  }


    @Override
  protected void doPost(HttpServletRequest request,
          HttpServletResponse response)
          throws ServletException, IOException {
      doGet(request, response);
  }


}
