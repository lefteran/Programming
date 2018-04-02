<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="java.io.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="java.servlet.http.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
String connectionURL = "jdbc:mysql://127.0.0.1/storage_cloud?requireSSL=false&useUnicode=true&characterEncoding=utf8&connectionCollation=utf8_general_ci";
Connection connection = null;
Class.forName("com.mysql.jdbc.Driver").newInstance();
connection = DriverManager.getConnection(connectionURL, "root", "121084");


Statement stat20 = connection.createStatement();
       String query20 = "SET NAMES utf8" ;
       ResultSet result20 = stat20.executeQuery(query20);
Statement stat30 = connection.createStatement();
       String query30 = "SET CHARSET utf8" ;
       ResultSet result30 = stat30.executeQuery(query30);
%>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Untitled Document</title>
<link rel="STYLESHEET" href="menu.css" type="text/css">
</head>





<body background="images/deigma180.jpg" title="signup">
<table width="100%" height="100%" border="0" align="center">

	<tr>

	    <td width="24%" height="17%" valign="top" align="center">
	    	<table width="100%" height="100%">
	    		<tr>
	    			<td colspan="2" align="center">
	    				<img src="images/SynkBackup.png" height="80" width="80">
	    			</td>
	    			<td>
	    				&nbsp;
	    			</td>
					<td>
						&nbsp;
	    			</td>
	    		</tr>
	    	</table>
	    </td>

	    <td width="49%" align="center" valign="top"><font size="6"><b><br> Storage cloud</b></font></td>

    	
</tr>
<tr>
	<td>&nbsp;</td>
    <td height="56" align="center" valign="top">

    <table align="center" border="0">
   		<tr>
    	<td align="center" valign="middle">
		<div id="tabsF">
  			<ul>
   			<li><a href="#" title="Link 1"><span>Αρχική</span></a></li>
    		<li><a href="signup.jsp" title="Link 3"><span>O χώρος μου</span></a></li>
    		<li><a href="#" title="Link 4"><span>Πληροφορίες</span></a></li>
    		<li><a href="upload.jsp" title="Link 5"><span>Uploads</span></a></li>
    		<li><a href="search.jsp" title="Link 6"><span><font color="EE2C2C">Αναζήτηση</font></span></a></li>
  			</ul>
		</div>
    	</td>
 	 </tr>
	</table>
    </td>
</tr>
  <tr>

    <td>&nbsp;</td>
<th align="left">
<table width="100%" border="0" bgcolor = "silver" align="left">

   	<%
		   response.setContentType("text/html");
   request.setCharacterEncoding("UTF-8"); 
    Enumeration paramNames = request.getParameterNames();

String user = null;
String file = null;
String register = null;



 while(paramNames.hasMoreElements()) {
      String paramName = (String)paramNames.nextElement();
      
	if(paramName.equals("user")   ){

	String[] paramValues = request.getParameterValues(paramName);
      user = paramValues[0];
     
     
	}
	else if ( paramName.equals("file")   ){
	
      String[] paramValues2 = request.getParameterValues(paramName);
      file = paramValues2[0];

      
	}

	 else if ( paramName.equals("register") ){
	
      String[] paramValues3 = request.getParameterValues(paramName);
      register = paramValues3[0];

      
	
	}	
      }

if(register==null){
out.println("<script language=\"javascript\"> location=\"search.jsp\"; </script>");
return;
}
else {
if(file.equals("") && user.equals("")){
Statement statement6 = null;
ResultSet rs6 = null;
statement6 = connection.createStatement();
String query6 = "SELECT * FROM files";
rs6 = statement6.executeQuery(query6);


while (rs6.next()) {
out.println("<tr><td align=\"left\"><font size=\"2\">"+ rs6.getString("name") +"</font></td></tr>");
}

rs6.close();
//anazitisi me vasi to xristi

}
else {

if(file.equals("") && !user.equals("")){ // anazitisi me onoma xristi
Statement statement = null;
ResultSet rs = null;
statement = connection.createStatement();
String query = "SELECT * FROM files WHERE username LIKE '%"+user+"%'";
rs = statement.executeQuery(query);


while (rs.next()) {
out.println("<tr><td align=\"left\"><font size=\"2\">"+ rs.getString("name") +" </font></td></tr>");
}

rs.close();
//anazitisi me vasi to xristi

}
else if(user.equals("") && !file.equals("")){//anazitisi me arxeio
Statement statement2 = null;
ResultSet rs2 = null;
statement2 = connection.createStatement();
String query4 = null;

if(file.equals("home")||file.equals("hom")||file.equals("ho")||file.equals("h")||file.equals("o")||file.equals("m")||file.equals("e")||file.equals("me")||file.equals("ome")||file.equals("om")){
query4 = "SELECT * FROM files WHERE name LIKE '%"+file+"%'";
}
else {
query4 = "SELECT * FROM files WHERE name LIKE '%"+file+"%' OR directory LIKE '%"+file+"%'";
}
rs2 = statement2.executeQuery(query4);

while (rs2.next()) {
out.println("<tr><td align=\"left\"><font size=\"2\">"+ rs2.getString("name") +" </font></td></tr>");
}

rs2.close();
}
else {
Statement statement3 = null;
ResultSet rs3 = null;
statement3 = connection.createStatement();
String query5;
if(file.equals("home")||file.equals("hom")||file.equals("ho")||file.equals("h")||file.equals("o")||file.equals("m")||file.equals("e")||file.equals("me")||file.equals("ome")||file.equals("om")){
query5 = "SELECT * FROM files WHERE name LIKE '%"+file+"%' AND username LIKE '%" +user+"%'";
}
else {
query5 = "SELECT * FROM files WHERE name LIKE '%"+file+"%' OR directory LIKE '%"+file+"%' AND username LIKE '%" +user+"%'" ;
}

rs3 = statement3.executeQuery(query5);

while (rs3.next()) {
out.println("<tr><td align=\"left\"><font size=\"2\">"+ rs3.getString("name") +" </font></td></tr>");
}

rs3.close();
//anazitisi kai me ta duo

}

}






}




	%> </table>
    </th>
    
    <td>&nbsp;</td>
 </tr>


<tr></tr>
</table>
</body>
</html>

