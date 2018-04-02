package login;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import database.Database;
import beans.*;


public class Login extends HttpServlet {
     private
        HttpSession session;
        PrintWriter out;
        Database db;
        String user;
        String kwdikos;
        loginBean login;
        UserBean myuser;
      
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

        Enumeration paramNames = request.getParameterNames();

        user = "";
        kwdikos = "";
        String register = "";
        Integer elegxos=0;
        Integer mysqlok=-1;
        Integer mysqlerror=-1;
        Integer one=0;
      
        while(paramNames.hasMoreElements()) {
            one++;
            String paramName = (String)paramNames.nextElement();

            if(paramName.equals("username")   ){
                String[] paramValues = request.getParameterValues(paramName);
                user = paramValues[0];
            }
            else if ( paramName.equals("password")   ){
                String[] paramValues = request.getParameterValues(paramName);
                kwdikos = paramValues[0];
            }
            else if ( paramName.equals("register") ){
                String[] paramValues6 = request.getParameterValues(paramName);
                register = paramValues6[0];
            }
        }
        if(one==0)
            out.println("<script language=\"javascript\"> location=\"login.jsp\"; </script>");
        else if(user.equals("")  || kwdikos.equals("") || !register.equals("Login")){
            out.println("<script language=\"javascript\"> history.back(); alert(\"Please complete username and password!\");</script>");
        }
        else {
            login = new loginBean();
            myuser =  login.getUser(user,kwdikos);
            if(myuser.getUsername()!=null){
            session.setAttribute( "theName", myuser.getUsername() );
         //######################## Cookies #############################################
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
        boolean foundcookie = false;
        String cookievalue=user;
        db = new Database();
        db.connect();
        String query2 = "UPDATE users SET login=1 WHERE username=\""+session.getAttribute( "theName" )+"\" ";

        db.updatequery(query2);
        for(int i = 0; i < cookies.length; i++) {
            Cookie c = cookies[i];
            if (c.getName().equals("storagecloud")) {
                foundcookie = true;
            }
        }
        if (!foundcookie) {
            Cookie c = new Cookie("storagecloud", cookievalue);
            c.setMaxAge(24*60*60*100);
            response.addCookie(c);
        }
        }
//##############################################################################
        Bonus bonus = new Bonus();
        Integer xwros = bonus.setbonus(myuser.getUsername());
        if(xwros!=0)
           out.println("<script language=\"javascript\"> alert(\"You have only "+xwros+" MB left! You may buy more space\");</script>");
        Card card = new Card();
        Integer i = card.elegxos(myuser.getUsername());
        if(i==-1)
            out.println("<script language=\"javascript\"> alert(\"Your card has expired, so you don't have any public space! Please replace your card\");</script>");
        else if(i==-2)
            out.println("<script language=\"javascript\"> alert(\"Your card has expired, so you don't have any public space! Please replace your card\");</script>");
        else if(i==-3)
            out.println("<script language=\"javascript\"> alert(\"Your card expires this month please replace it or else you 'll loss the opportunity to have public files\");</script>");
            out.println("<script language=\"javascript\"> location=\"Myspace\"; </script>");
           
            }
            else {
               out.println("<script language=\"javascript\"> history.back(); alert(\"Wrong username or password\"); </script> ");
            }
        }



    }

    

    @Override
  protected void doGet(HttpServletRequest request,
          HttpServletResponse response)
          throws ServletException, IOException {
      doPost(request, response);
  }


}
