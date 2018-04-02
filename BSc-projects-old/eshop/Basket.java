package basket;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import database.Database;
import kalathi.*;

/**
 *
 * @author johnfouf
 */
public class Basket extends HttpServlet {

    private
        HttpSession session;
        PrintWriter out;
        Mybasket basket;
        Database db;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        session = request.getSession(true);
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        out = response.getWriter();

        basket = new Mybasket(session);
            
        printoutput(response);
        Enumeration paramNames = request.getParameterNames();

        String user=null;
        String myfile=null;
        String dir=null;


        while(paramNames.hasMoreElements()) {
            String paramName = (String)paramNames.nextElement();

            if ( paramName.equals("myfile")   ){
                String[] paramValues2 = request.getParameterValues(paramName);
                myfile = paramValues2[0];
            }
            else if ( paramName.equals("dir")   ){
                String[] paramValues2 = request.getParameterValues(paramName);
                dir = paramValues2[0];
            }
            else if ( paramName.equals("user")   ){
                String[] paramValues2 = request.getParameterValues(paramName);
                user = paramValues2[0];
            }
        }

            db = new Database();
            db.connect();
            if(dir!=null && myfile!=null && user!=null){
                removefrombasket(myfile,dir,user);
            }

            if(basket.size()==0){
                out.println("<table border=\"0\" align=\"center\">" +
                "<tr><td colspan=\"6\"  align=\"center\">" +
                //"Total space: "+limit+" GB Free space: "+free+" MB  Used space: "+used+" MB  Public Space: "+mypublic+
                "<tr>" +
                "<td width=\"400\" align=\"center\"><font size=\"3\" >Your basket is empty</td></tr></td></tr>");
            }
            else
             content();

        db.close();
    

    }

    protected void removefrombasket(String myfile, String dir,String user)
          throws ServletException, IOException {
            ResultSet result = null;
       
            try{
                
                String myquery = "SELECT * FROM files WHERE is_public=1 AND name=\""+myfile+"\" AND username=\""+user+"\"AND directory=\""+dir+"\"";
                result = db.selectquery(myquery);
                if(result.next()){
                   basket.remove_item(myfile, dir, user);
                }
                result.close();
            }

            catch (SQLException e) {
                throw new ServletException("Servlet Could not display records.", e);
            }
  }

   protected void content()
        throws ServletException, IOException {

            out.println("<table border=\"0\" align=\"center\">" +
                "<tr><td colspan=\"6\"  align=\"center\">" +
                //"Total space: "+limit+" GB Free space: "+free+" MB  Used space: "+used+" MB  Public Space: "+mypublic+
                "<tr>" +
                "<td width=\"180\" align=\"center\"><font size=\"4\" color=\"#0000CD\">Title</font></td>" +
                "<td width=\"100\" align=\"center\"><font size=\"4\" color=\"#0000CD\">Size</font></td>" +
                "<td width=\"100\" align=\"center\"><font size=\"4\" color=\"#0000CD\">Price</font></td>" +
                "<td width=\"100\" align=\"center\"><font size=\"4\" color=\"#0000CD\">Downloads</font></td>" +
                "<td width=\"130\" align=\"center\"><font size=\"4\" color=\"#0000CD\">Owner</font></td>" +
                "<td width=\"180\" align=\"center\"><font size=\"4\" color=\"#0000CD\">Remove</font></td></tr></td></tr>");
            try{
                Integer price=0;
                long size=0;
                for(Integer i=0; i<basket.size(); i++){
                    ResultSet rs = null;
                    ResultSet rs6 = null;
                    String query6 = "SELECT * FROM files WHERE is_public=1 AND name=\""+ basket.mybasket(i,0)+"\" AND directory=\""+ basket.mybasket(i,1)+"\" AND username=\""+ basket.mybasket(i,2)+"\"";
                    rs6 = db.selectquery(query6);
                    if(rs6.next()){
                        price+=rs6.getInt("price");
                        size+=rs6.getLong("size");
                        out.println("<tr><td align=\"center\"><font size=\"2\">"+ rs6.getString("name") +" </font></td><td align=\"center\"><font size=\"2\">"+ rs6.getString("size") +" KB </font></td>");
                        if(rs6.getInt("price")==0)
                            out.println("<td align=\"center\"><font size=\"2\"> free </font></td>");
                        else
                            out.println("<td align=\"center\"><font size=\"2\">"+ rs6.getInt("price") +"$</font></td>");

                        out.println("<td align=\"center\"><font size=\"2\">"+ rs6.getString("popularity") +" </font></td><td align=\"center\"><font size=\"2\">"+ rs6.getString("username") +" </font></td><td align=\"center\">");
                        out.println("<a href=\"Basket?user="+rs6.getString("username")+"&myfile="+rs6.getString("name") +"&dir="+rs6.getString("directory")+" \"><img src=\"images/removebasket.jpg\"border=\"0\" height=20% width=20%></a></td></tr>");
                    }
                    rs6.close();
                }
                out.println("<tr><td align=\"center\"><br></td></tr>");
                out.println("<tr><td align=\"center\"><font size=\"2\"> Summary("+basket.size()+" items)  </font></td><td align=\"center\"><font size=\"2\">"+ size +" KB </font></td>");
                out.println("<td align=\"center\"><font size=\"2\">"+ price +"$</font></td>");
                out.println("<td align=\"center\"></td><td align=\"center\"></td><td align=\"center\">");
                session.setAttribute( "price", price );
                if(price!=0)out.println("<form action=\"checkout.jsp\" ><input type=\"submit\" value=\"Check out\"></form></td></tr>");
               else out.println("<form action=\"Checkout\" ><input type=\"submit\" value=\"Check out\"></form></td></tr>");
            }
            catch (SQLException e) {
                throw new ServletException("Servlet Could not display records.", e);
            }
   }




   protected void printoutput(HttpServletResponse response)
          throws ServletException, IOException {

            out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />"+
            "<style type=\"text/css\"><!-- A:link {text-decoration:none} A:visited {text-decoration:none} A:active {text-decoration:none} A:hover {text-decoration:underline}  --></style>"+
            "<title>myspace</title>"+"<link rel=\"STYLESHEET\" href=\"menu.css\" type=\"text/css\">"+
            "<style type=\"text/css\">th{}th.margin{margin-left:50px;}</style>"+
            "<script type=\"text/javascript\" src=\"simpletreemenu.js\"></script>"+
            "<link rel=\"stylesheet\" type=\"text/css\" href=\"simpletree.css\" /></head>"+
            "<body background=\"images/deigma180.jpg\">"+
            "<table width=\"100%\" height=\"100%\" border=\"0\" align=\"center\">"+
            "<tr><td width=\"24%\" height=\"17%\" valign=\"top\">"+
            "<table width=\"100%\" height=\"100%\">"+
            "<tr><td colspan=\"2\" align=\"center\">"+
            "<img src=\"images/SynkBackup.png\" height=\"80\" width=\"80\"></td>"+
            "<td>&nbsp;</td><td>&nbsp;</td></tr></table></td>"+
            "<td width=\"49%\" align=\"center\" valign=\"top\"><font size=\"6\">"+
            "<b><br> Storage cloud</b></font></td>" +
            "<td width=\"27%\" align=\"center\" valign=\"bottom\"><p> " +
            "<font  size=\"2\">" +
           "<a href=\"Basket\" style=\"\"><img src=\"images/kalathi.jpg\" border=\"0\" height=\"16%\" width=\"16%\"><br>My basket</a>"+
            "</td></tr>" +
            "<tr> <td>&nbsp;</td><td height=\"56\"  align=\"center\" valign=\"top\">" +
            "<table align=\"center\" border=\"0\">" +
            "<tr><td align=\"center\" valign=\"middle\">");

            if(session.getAttribute( "theName" )!= null){
                out.println("<div id=\"tabsF\">" +
                "<ul><li><a href=\"index.htm\" title=\"Home\"><span>Home</span></a></li>" +
                "<li><a href=\"Myspace\" title=\"Myspace\"><span>My space</span></a></li>" +
                "<li><a href=\"info.jsp\" title=\"Info\"><span>Info</span></a></li>" +
                "<li><a href=\"search.jsp\" title=\"Link 5\"><span><font color=\"#EE2C2C\">Search</font></span></a></li>" +
                "<li><a href=\"Logout\" title=\"Logout\"><span>Log out</span></a></li>" +
                "</ul></div></td></tr></table></td></tr>" +
                "<tr><td colspan=\"3\">");
            }
            else
                out.println( "<div id=\"tabsF\">" +
                "<ul><li><a href=\"index.htm\" title=\"Home\"><span>Home</span></a></li>" +
                "<li><a href=\"register.jsp\" title=\"signup\"><span>Signup</font></span></a></li>" +
                "<li><a href=\"info.jsp\" title=\"Info\"><span>Info</span></a></li>" +
                "<li><a href=\"search.jsp\" title=\"Search\"><span><font color=\"#EE2C2C\">Search</span></a></li>" +
                "<li><a href=\"login.jsp\" title=\"Login\"><span>Log in</span></a></li>" +
                "</ul></div></td></tr></table></td></tr>" +
                "<tr><td colspan=\"3\">");

  }

    @Override
   protected void doPost(HttpServletRequest request,
          HttpServletResponse response)
          throws ServletException, IOException {
      doGet(request, response);
  }


}
