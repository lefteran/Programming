package myspace;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import database.*;


public class Myspace extends HttpServlet {
    private
        HttpSession session;
        PrintWriter out;
        Database db;
        File fp;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
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
            //////////////////////////////////cookies////////////////////////////////
          db = new Database();
        db.connect();
Cookie[] cookies = request.getCookies();
boolean foundcookie = false;
if(cookies!=null){
for(int i = 0; i < cookies.length; i++) {
    Cookie c = cookies[i];
    if (c.getName().equals("storagecloud")) {
        //out.println("<script language=\"javascript\"> alert(\"Value is " + cookievalue + " \"); </script>");

        foundcookie = true;
        Integer size = c.getValue().length();

        String query = "select login from users where username= \""+c.getValue()+"\"";
        ResultSet rs = db.selectquery(query);
        try{
            rs.next();

        Integer check = rs.getInt("login");
        rs.close();

        if(check==1){
            session.setAttribute("theName", c.getValue());

        }
        }
                catch (SQLException ex) {
                    Logger.getLogger(Myspace.class.getName()).log(Level.SEVERE, null, ex);
                }    }
}
}
/////////////////////////////////////////////////////////////////////////////////////////
if((session.getAttribute( "theName" )== null) ){
    out.println("<script language=\"javascript\"> location=\"login.jsp\"; </script>");
return;
}


            try {

              
                String dir="";
                String folder= "";
                String user="";
                String file="";
                Integer curaccess=-1;
                String filename="";
                Integer price=-1;
                Integer delete=-1;

                Enumeration paramNames = request.getParameterNames();
                while(paramNames.hasMoreElements()) {
                    String paramName = (String)paramNames.nextElement();

                    if(paramName.equals("dir")   ){
                        String[] paramValues = request.getParameterValues(paramName);
                        dir=paramValues[0];
                    }
                    else if(paramName.equals("user")   ){
                        String[] paramValues = request.getParameterValues(paramName);
                        user=paramValues[0];
                    }
                    else if(paramName.equals("delete")   ){
                        String[] paramValues = request.getParameterValues(paramName);
                        delete=Integer.parseInt(paramValues[0]);
                    }
                    else if(paramName.equals("folder")   ){
                        String[] paramValues = request.getParameterValues(paramName);
                        folder=paramValues[0];
                    }
                    else if(paramName.equals("curaccess")   ){
                        String[] paramValues = request.getParameterValues(paramName);
                        curaccess=Integer.parseInt(paramValues[0]);
                    }

                    else if(paramName.equals("prosvasi")   ){
                        String[] paramValues = request.getParameterValues(paramName);
                        filename=paramValues[0];
                    }
                    else if(paramName.equals("file")   ){
                        String[] paramValues = request.getParameterValues(paramName);
                        file=paramValues[0];
                    }
                    else if(paramName.equals("price")   ){
                        String[] paramValues = request.getParameterValues(paramName);
                        try {
                            price=Integer.parseInt(paramValues[0]);
                        }
                        catch(NumberFormatException nFE){
                            out.println("<script language=\"javascript\"> alert('Not an Integer'); history.back(); </script>");
                        }
                    }
                }


               if(!user.equals("") && !folder.equals("")    ){
                   fp = new File();
                   fp.setfilename("");
                   fp.setdir(folder);
                   fp.setuser(user);
                    fp.setcuraccess(0);
                    fp.deleteFile(db);
                }

                if(!user.equals("") && folder.equals("") && delete==1 && !file.equals("") && !dir.equals("") ){
                    fp = new File();
                    fp.setfilename(file);
                    fp.setdir(dir);
                    fp.setuser(user);
                     fp.setcuraccess(0);
                    fp.deleteFile(db);
                }
                if(((user.equals("") || dir.equals("") || filename.equals("")) && (curaccess==1 || curaccess==0))   ){
                    out.println("<script language=\"javascript\">   alert(\"Mistaken parameters given\"); location=\"Myspace\" ;</script>");
                    return;
                }
                else if(((!user.equals("") && !dir.equals("") && !filename.equals("")) && (curaccess==1 || curaccess==0))   ){
                       fp = new File();
                    fp.setfilename(filename);
                    fp.setdir(dir);
                    fp.setuser(user);
                    fp.setcuraccess(curaccess);
                    if(user.equals(session.getAttribute( "theName" ))){
                    Integer i = fp.changecuraccess(db);
                    if(i==-1 ){
                      out.println("<script language=\"javascript\"> history.back(); alert(\"Your card has expired, so you can't have public space\");</script>");
                    }
                    }
                    else {
                      out.println("<script language=\"javascript\">  alert(\"This file belongs to another user\");</script>");
                    }
                }

                printoutput();
                printfiles(dir,user,price,file);
                out.println("</table></td></tr></table></body></html>");
            }
            
            finally {
               
        db.close();

                out.close();
            }
    }


 protected void printfiles(String dir,String user,Integer price,String file)
    throws ServletException, IOException {
     try {
         Integer count2=0;
           if(dir.equals("") || dir.equals("home") ){
           if(dir.equals("")) dir="home";

                                
                                ResultSet rs0 = null;
				String query0 = "SELECT * FROM directories WHERE name!=\"home\" and username=\"" + session.getAttribute( "theName" ) + "\" order by name";
                                rs0 = db.selectquery(query0);
                                while (rs0.next()){
                                    count2++;

                                    out.println("<tr>");
                                   
                                    out.println("<td align=\"right\"");
                                    out.println("<a href=\"Myspace?dir="+rs0.getString("name")+"\"  ><img src=\"images/dossier.gif\" height=\"25\" width=\"25\" border=\"0\"></a>");
                                    out.println("</td>");

                                    out.println("<td align=\"left\"  width=\"180\"> <font size=\"2\" >");
                                    out.println("<a href=\"Myspace?dir="+rs0.getString("name")+"\" color=\"black\" >"+rs0.getString("name")+"</a>");
                                    out.println("</td> </font>");
                                    out.println("<td align=\"center\"> <font size=\"2\" >");
                                    out.println("</td> </font>");
                                    out.println("<td align=\"center\"> <font size=\"2\" >");
                                    out.println("</td> </font>");
                                    out.println("<td align=\"center\"> <font size=\"2\" >");
                                    out.println("</td> </font>");
                                    out.println("<td align=\"center\"> <font size=\"2\" >");
                                    out.println("</td> </font>");
                                    out.println("<td align=\"center\"> <font size=\"2\" >");
                                    out.println("<td align=\"center\"> </font>");
                                    out.println("<a href=\"javascript:\" onClick=\"if(confirm('Are you sure you want to delete "+ rs0.getString("name") +" ?'))  location='Myspace?folder="+rs0.getString("name")+"&user="+rs0.getString("username")+"'; \";><img src=\"images/delete.gif\" border=\"0\"></a>");
                                    out.println("</td>");
                                    out.println("</tr>");


                                }
                                rs0.close();
           }

                                

				ResultSet rs1 = null;
				String query1 = "SELECT * FROM files WHERE username=\"" + session.getAttribute( "theName" ) + "\" && directory=\""+dir+"\" order by name";
                                rs1 = db.selectquery(query1);
                                Integer count=0;
                                while (rs1.next()){
                                    count++;
                                    out.println("<tr>");
                                    out.println("<td align=\"right\"");
                                    out.println("<a href=\"javascript:\" onclick=\"alert('You have just downloaded "+rs1.getString("name")+" on your pc')\" title=\"download file\" ><img src=\"images/download.jpg\" height=\"25\" width=\"25\" border=\"0\"></a>");
                                    out.println("</td>");

                                    out.println("<td  width=\"180\" align=\"left\"> <font size=\"2\" >");
                                    out.println(" <div style=\"width: 180px; overflow:hidden;\">"+rs1.getString("name")+"</div>");
                                    out.println("</td> </font>");
                                    out.println("<td align=\"center\"> <font size=\"2\" >");
                                    out.println(rs1.getString("size")+" kb");
                                    out.println("</td> </font>");
                                    out.println("<td align=\"center\"> <font size=\"2\" >");
                                    if(user.equals(session.getAttribute( "theName" ))&&dir.equals(rs1.getString("directory")) && file.equals(rs1.getString("name")) && price==-1){
                                       out.println("<form action=\"Myspace?dir="+rs1.getString("directory")+"&user="+session.getAttribute( "theName" )+"&file="+rs1.getString("name")+"\" method=post><input type=text name=\"price\" size=4><input type=submit name=submit value=ok>");
                                    }

                                    else {
                                        if(user.equals(session.getAttribute( "theName" ))&&dir.equals(rs1.getString("directory")) && file.equals(rs1.getString("name")) && price!=-1){
                                            
                                            String query31 = "UPDATE files SET price="+price+" WHERE username=\""+session.getAttribute( "theName" )+"\" and name= \""+rs1.getString("name") +"\" and directory= \""+rs1.getString("directory")+"\""  ;
                                            db.updatequery(query31);
                                            out.println("<script language=\"javascript\"> location=\"Myspace?dir="+dir+"\" ; </script>");
                                            return;
                                        }
                                        out.println(rs1.getString("price")+"$ &nbsp&nbsp&nbsp <a href=\"Myspace?user="+rs1.getString("username")+"&file="+rs1.getString("name") +"&dir="+rs1.getString("directory")+" \"><font  face=\"arial, helvetica\"><img src=\"images/edit.gif\" align=\"center\" border=\"0\"></font></a>");
                                    }

                                    out.println("</td> </font>");
                                    out.println("<td align=\"center\"> <font size=\"2\" >");
                                    out.println(rs1.getString("popularity"));
                                    out.println("</td> </font>");
                                    out.println("<td align=\"center\"> <font size=\"2\" >");

                                    String access=rs1.getString("is_public");
                                    if(access.equals("0"))
                                        out.println("<a href=\"Myspace?prosvasi="+rs1.getString("name")+"&curaccess=0&dir="+rs1.getString("directory")+"&user="+rs1.getString("username")+"\"><img src=\"images/invisible.gif\" border=\"0\"></a>");
                                    else
                                        out.println("<a href=\"Myspace?prosvasi="+rs1.getString("name")+"&curaccess=1&dir="+rs1.getString("directory")+"&user="+rs1.getString("username")+"\"><img src=\"images/visible.gif\" border=\"0\"></a>");
                                    out.println("<td align=\"center\"> <font size=\"2\" >");
                                    out.println(rs1.getString("apodosi"));
                                    out.println("</td> </font>");
                                    out.println("<td align=\"center\"> </font>");
                                    out.println("<a href=\"javascript:\" onClick=\"if(confirm('Are you sure you want to delete " + rs1.getString("name") +" ?'))  location='Myspace?delete=1&file="+rs1.getString("name")+"&dir="+rs1.getString("directory")+"&user="+rs1.getString("username")+"'; \";><img src=\"images/delete.gif\" border=\"0\"></a>");
                                    out.println("</td>");
                                    out.println("</tr>");


                                }
                                rs1.close();
                                if(count==0 && !dir.equals("home") || count==0 && dir.equals("home") && count2==0){
                                    out.println("<tr>");
                                    out.println("<td>");
                                    out.println("</td>");

                                    out.println("<td align=\"center\"> <font size=\"2\" >");
                                    out.println("There are no files yet in this folder");
                                    out.println("</td> </font>");
                                    out.println("<td>");
                                    out.println("</td>");
                                    out.println("<td>");
                                    out.println("</td>");
                                    out.println("<td>");
                                    out.println("</td>");
                                    out.println("<td>");
                                    out.println("</td>");
                                    out.println("<td>");
                                    out.println("</td>");
                                    out.println("</tr>");


                                }
                                out.println("<tr><td><br><br><br></td></tr>");

     }
     catch(SQLException e){
         throw new ServletException("Servlet Could not display records.", e);
     }
 }

  protected void printoutput()
    throws ServletException, IOException {
 
   
                 out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />"+
            "<script language=\"JavaScript\" src=\"clock.js\">"+
"</script> <title>myspace</title><link rel=\"STYLESHEET\" href=\"menu.css\" type=\"text/css\">"+
            "<style type=\"text/css\">th{}th.margin{margin-left:50px;}</style>"+
            "<script type=\"text/javascript\" src=\"simpletreemenu.js\"></script>"+
            "<link rel=\"stylesheet\" type=\"text/css\" href=\"simpletree.css\" /></head>"+

            " <body background=\"images/deigma180.jpg\" onLoad=\"UpdateClocks()\">"+
"<table width=\"100%\" height=\"100%\" border=\"0\" align=\"center\">"+

	"<tr>"+

	    "<td width=\"24%\" height=\"17%\" valign=\"top\" align=\"center\">"+
	    	"<table width=\"100%\" height=\"100%\">"+
	    		"<tr>"+
	    			"<td colspan=\"2\" align=\"center\">"+
	    				"<img src=\"images/SynkBackup.png\" height=\"80\" width=\"80\">"+
	    			"</td><td>&nbsp;</td><td>&nbsp;</td></tr></table></td>"+

	    "<td width=\"49%\" align=\"center\" valign=\"top\">"+
                "<font size=\"6\"><b><br> Storage cloud</b></font>"+
            "</td>"+
            "<td ID=\"Clock0\" align=\"center\">"+
                "&nbsp;</td></tr><tr><td>&nbsp;</td>"+
   " <td height=\"56\" align=\"center\" valign=\"top\">"+

    "<table align=\"center\" border=\"0\">"+
   		"<tr>"+
    	"<td align=\"center\" valign=\"middle\">"+


            "<div id=\"tabsF\">" +
            "<ul><li><a href=\"index.htm\" title=\"Home\"><span>Home</span></a></li>" +
            "<li><a href=\"Myspace\" title=\"Myspace\"><span><font color=\"#EE2C2C\">My space</font></span></a></li>" +
            "<li><a href=\"info.jsp\" title=\"Info\"><span>Info</span></a></li>" +
            "<li><a href=\"search.jsp\" title=\"Search\"><span>Search</span></a></li>" +
            "<li><a href=\"Logout\" title=\"Logout\"><span>Log out</span></a></li>" +
            "</ul></div></td></tr></table></td></tr></table>" +
         "   <table><tr>"+
    "<td width=\"230\" rowspan=\"40\" align=\"left\" valign=\"top\">"+
        "<h2 class=\"hide\">Menu:</h2>"+
        "<ul class=\"avmenu\">"+
            "<li><a href=\"Myspace\"><font color=\"#4876FF\">My account</font></a></li>"+
            "<li><a href=\"newfolder.jsp\">Create folder</a></li>"+
            "<li><a href=\"buyspace.jsp\">Extend capacity</a></li>"+
            "<li><a href=\"upload.jsp\">Upload</a></li>"+
            "<li><a href=\"changecard.jsp\">Change card</a></li>"+
            "<li><a href=\"status.jsp\">Status</a></li>"+
        "</ul>"+
   " </td>"+
"</tr>"+







"<th align=\"left\" valign=\"top\">"+
    	
    		"<table width=\"100%\" border=\"0\" align=\"center\"  frame=\"border\">"+
    		
			

			 "<tr>"+
      "<td width=\"20\" align=\"center\"> " +
            "<a href=\"Myspace?dir=home\" title=\"home\"><img src=\"images/parent.gif\" border=\"0\" height=\"25\" width=\"25\"></a></td>" +
            "<td width=\"180\" align=\"center\"><font size=\"4\" color=\"#0000CD\">Title</font></td>" +
            "<td width=\"100\" align=\"center\"><font size=\"4\" color=\"#0000CD\">Size</font></td>" +
            "<td width=\"100\" align=\"center\"><font size=\"4\" color=\"#0000CD\">Charge</font></td>" +
            "<td width=\"130\" align=\"center\"><font size=\"4\" color=\"#0000CD\">Downloads</font></td>" +
            "<td width=\"130\" align=\"center\"><font size=\"4\" color=\"#0000CD\">Access</font></td>" +
            "<td width=\"130\" align=\"center\"><font size=\"4\" color=\"#0000CD\">Income</font></td>" +
            "<td width=\"100\" align=\"center\"><font size=\"4\" color=\"#0000CD\">Delete</font></td></tr></td>"+
	 "</tr>");
     
    		

         
            
           

   
  }

    

 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
