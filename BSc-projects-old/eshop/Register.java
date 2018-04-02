package register;


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import beans.*;



public class Register extends HttpServlet {
    private
        Integer xwros;
        String user;
        String oroi;
        String kwdikos;
        String kwdikos2;
        String myemail;
        String myregister;
        String onoma ;
        String epitheto ;
        String cardno1 ;
        String cardno2 ;
        String cardno3 ;
        String cardno4 ;
        String cardcvv ;
        PrintWriter out;
        String month;
        String year;


    @Override
 protected void doGet(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
    System.out.println("<meta http-equiv=\"Content-Type\" content=\"text/html\" charset=\"UTF-8\" />");
    System.out.println("<%@page contentType=\"text/html\" pageEncoding=\"UTF-8\"%>");
    response.setContentType("text/html");
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    out = response.getWriter();

    Enumeration paramNames = request.getParameterNames();
    Integer check = 0;
    Integer lathi = 0;
    Integer elegxos = 0;
    Integer apodoxi = 0;
    Integer cardcheck = 0;
    Integer wrongemail = 0;
    xwros = 0;
    user = "";
    oroi = "NULL";
    kwdikos = "";
    kwdikos2 = "";
    myemail = "";
    myregister = "";
    onoma = "";
    epitheto = "";
    year="";
    month="";
    cardno1 = "";
    cardno2 = "";
    cardno3 = "";
    cardno4 = "";
    cardcvv = "";
    Integer one=0;
    RegBean myuser;

    while(paramNames.hasMoreElements()) {
        one++;
        String paramName = (String)paramNames.nextElement();

	if(paramName.equals("capacity")   ){
            String[] paramValues = request.getParameterValues(paramName);
            xwros = Integer.parseInt(paramValues[0]);
	}
	else if ( paramName.equals("username")   ){
            String[] paramValues = request.getParameterValues(paramName);
            user = paramValues[0];
            if(user.length()>45){
                                    out.println("<script language=\"javascript\"> history.back(); alert(\"Too big user name!\");</script>");
                                    return;
                                }
	}
	else if (paramName.equals("pass1")  ){
            String[] paramValues = request.getParameterValues(paramName);
            kwdikos= paramValues[0];
	}
	else if ( paramName.equals("pass2")  ){
            String[] paramValues4 = request.getParameterValues(paramName);
            kwdikos2 = paramValues4[0];
	}
	else if  ( paramName.equals("register")   ){
            String[] paramValues5 = request.getParameterValues(paramName);
            myregister = paramValues5[0];
	}
        else if ( paramName.equals("terms") ){
            check = 1;
            String[] paramValues6 = request.getParameterValues(paramName);
            oroi = paramValues6[0];
	}
	else if ( paramName.equals("name") ){
            String[] paramValues7 = request.getParameterValues(paramName);
            onoma = paramValues7[0];
            if(onoma.length()>45){
                                    out.println("<script language=\"javascript\"> history.back(); alert(\"Too big name!\");</script>");
                                    return;
                                }
	}
	else if ( paramName.equals("surname") ){
            String[] paramValues8 = request.getParameterValues(paramName);
            epitheto = paramValues8[0];
            if(epitheto.length()>45){
                                    out.println("<script language=\"javascript\"> history.back(); alert(\"Too big surname!\");</script>");
                                    return;
                                }
	}
        else if ( paramName.equals("month") ){
            String[] paramValues8 = request.getParameterValues(paramName);
            month = paramValues8[0];
	}
        else if ( paramName.equals("year") ){
            String[] paramValues8 = request.getParameterValues(paramName);
            year = paramValues8[0];
	}
        else if ( paramName.equals("email") ){
            String[] paramValues9 = request.getParameterValues(paramName);
            myemail = paramValues9[0];
            if(myemail.length()>45){
                                    out.println("<script language=\"javascript\"> history.back(); alert(\"Too big email!\");</script>");
                                    return;
                                }
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


   
    Integer coderror = -1;
    Integer codsize = -1;
    java.util.Date d1 = new java.util.Date();
 try {
    if(one==0)
        out.println("<script language=\"javascript\"> location=\"register.jsp\"; </script>");
    else if(user.equals("") || epitheto.equals("") || onoma.equals("") || myemail.equals("") || myregister.equals("ok") || kwdikos.equals("") || kwdikos2.equals("")){
        elegxos = 1;lathi=1;
        out.println("<script language=\"javascript\"> history.back(); alert(\"Please complete the form!\");</script>");
    }
    else if(  cardcvv.equals("") || year.equals("") || cardno1.equals("") || month.equals("") || cardno2.equals("") || cardno3.equals("") || cardno4.equals("")){
        elegxos=1;lathi=1;
        out.println("<script language=\"javascript\"> history.back(); alert(\"Please complete the form!\");</script>");
    }


    else if(  Integer.parseInt(cardcvv) > 999 ||  Integer.parseInt(cardcvv) < 0  ||  cardcvv.length() !=3 ){
        elegxos=1;lathi=1;
        out.println("<script language=\"javascript\"> history.back(); alert(\"Wrong cvv was given!\");</script>");
    }
    else if(Integer.parseInt(cardno4) > 9999 ||  Integer.parseInt(cardno4) < 0  ||Integer.parseInt(cardno2) > 9999 ||  Integer.parseInt(cardno2) < 0  || Integer.parseInt(cardno3) > 9999 ||  Integer.parseInt(cardno3) < 0  || Integer.parseInt(cardno1) > 9999 ||  Integer.parseInt(cardno1) < 0  || cardno1.length() !=4 || cardno2.length() !=4 || cardno3.length() !=4 || cardno4.length() !=4  ){
        elegxos=1;lathi=1;
        out.println("<script language=\"javascript\"> history.back(); alert(\"Wrong card number was given!\");</script>");
    }
     else if( Integer.parseInt(year) < (d1.getYear()+1900)){
        elegxos=1;lathi=1;
        out.println("<script language=\"javascript\"> history.back(); alert(\"Your card has already expired!\");</script>");
    }
    else if( Integer.parseInt(month) < 1 || Integer.parseInt(month) > 12){
        elegxos=1;lathi=1;
        out.println("<script language=\"javascript\"> history.back(); alert(\"Wrong month was given!\");</script>");
    }
     else if(Integer.parseInt(year) == (d1.getYear()+1900) && Integer.parseInt(month) <= d1.getMonth()){
        elegxos=1;lathi=1;
        out.println("<script language=\"javascript\"> history.back(); alert(\"Your card has already expired!\");</script>");
    }
    else if(myemail.indexOf('@')==-1 && !myemail.equals("")){
        wrongemail=1;lathi=1;
        out.println("<script language=\"javascript\"> history.back(); alert(\"Wrong email!\");</script>");
    }
    else if(!kwdikos.equals(kwdikos2) && elegxos == 0){
        coderror=1;lathi=1;
        out.println("<script language=\"javascript\"> history.back(); alert(\"The passwords don't match!\");</script>");
    }
    else if(kwdikos.length()<8 && !kwdikos.equals("") && coderror==-1){
        codsize=1;lathi=1;
        out.println("<script language=\"javascript\"> history.back(); alert(\"Weak password!\");</script>");
    }
    else if(oroi.equals("NULL")&& lathi==0){
        apodoxi = 1;lathi=1;
        out.println("<script language=\"javascript\"> history.back(); alert(\"Please accept the terms!\");</script>");
    }
    else if(myregister.equals("Sign up") && lathi==0){
        myuser = new RegBean();
        String cardno=cardno1+cardno2+cardno3+cardno4;
        String date = month+"/"+year;
        myuser.setCapacity(xwros);
        myuser.setCardno(cardno);
        myuser.setCvv(cardcvv);
        myuser.setDate(date);
        myuser.setSurname(epitheto);
        myuser.setEmail(myemail);
        myuser.setUsername(user);
        myuser.setPassword(kwdikos);
        myuser.setName(onoma);
        RegisterBean reg = new RegisterBean();
       Integer i = reg.regUser(myuser);
       if(i==-1)
           out.println("<script language=\"javascript\"> history.back(); alert(\"Username already exists!\");</script>");
       else if(i==-2)
           out.println("<script language=\"javascript\"> history.back(); alert(\"Email already exists!\");</script>");
       else if(i==0)
           out.println("<script language=\"javascript\"> location=\"login.jsp\"; alert(\"Registration succeded!\");</script>");
    }
     } catch(NumberFormatException nFE){
                            out.println("<script language=\"javascript\"> alert('Wrong payment details (Complete with numeric values)'); history.back(); </script>");
                        }

    }


    @Override
  protected void doPost(HttpServletRequest request,
          HttpServletResponse response)
          throws ServletException, IOException {
      doGet(request, response);
  }


}
