<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="java.io.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="java.servlet.http.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="org.apache.commons.fileupload.*, org.apache.commons.fileupload.servlet.ServletFileUpload, org.apache.commons.fileupload.disk.DiskFileItemFactory, org.apache.commons.io.FilenameUtils, java.util.*, java.io.File, java.lang.Exception" %>
<% 
if(session.getAttribute( "theName" )== null){
out.println("<script language=\"javascript\"> location=\"login.jsp\"; </script>");
return;
}
%>
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

<%
String name="qqqqqqq";
String register=null;
int noname=-1;
int wrongname=-1;
       request.setCharacterEncoding("UTF-8");  
    response.setContentType("text/html");
   
    Enumeration paramNames = request.getParameterNames();


 while(paramNames.hasMoreElements()) {
      String paramName = (String)paramNames.nextElement();
      
	if(paramName.equals("name")   ){

	String[] paramValues = request.getParameterValues(paramName);
      name = paramValues[0];
     
     
	}
	
	 else if ( paramName.equals("register") ){
	
      String[] paramValues6 = request.getParameterValues(paramName);
      register = paramValues6[0];

      
	
	}	
      }

if( name.equals("") && register!=null){
noname=1;
}

else {
Statement stat12 = connection.createStatement();
String query12 = "SELECT COUNT(*) FROM directories WHERE username=\""+session.getAttribute( "theName" )+"\" AND name=\""+name+"\"";
ResultSet result12 = stat12.executeQuery(query12);
result12.next();
if(result12.getString("COUNT(*)").equals("1")){
wrongname=1;             //fakelos uparxei hdh 
result12.close();
}
else{
if(!name.equals("qqqqqqq")){
Statement statement = connection.createStatement();
String query7 = "INSERT INTO directories VALUES(\""+name+"\",\""+ session.getAttribute( "theName" )  +"\")";
statement.execute(query7);
}

}



}



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
    		<li><a href="signup.jsp" title="Link 3"><span><font color="EE2C2C">O χώρος μου</font></span></a></li>
    		<li><a href="#" title="Link 4"><span>Πληροφορίες</span></a></li>
    		<li><a href="upload.jsp" title="Link 5"><span>Uploads</span></a></li>
    		<li><a href="search.jsp" title="Link 6"><span>Αναζήτηση</span></a></li>
  			</ul>
		</div>
    	</td>
 	 </tr>
	</table>
    </td>
</tr>
  <tr>

    <td>&nbsp;</td>
<th align="center">
    	<form name="information"  action="newfolder.jsp" method="post" >
    		<table width="60%" border="0" align="center" bgcolor="silver" frame="border">
    		<tr>
	    		<td align="center" colspan="2"><font size="5"><b>Δημιουργία νέου φακέλου</b><br></font><% if(noname==1 && register!=null){ out.println("<font size=2 color = red>*Δεν έχετε συμπληρώσει όνομα για τον φάκελο</font><br>"); } 
if(wrongname==1 && register.equals("Δημιουργία")){ out.println("<font size=2 color = red>*Έχετε ήδη φάκελο με αυτό το όνομα</font><br>"); }%></td> 
 			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr> 
			
	
			<tr>
				<td align="right"><font size="3"><b>Επιθυμητό όνομα: </b> </font></td>
				<td align="center"><input type="text" name="name"> </td>
   			</tr>
			
   			</tr>
    		<tr>  
				<td colspan="2" align="center"><br><input type="submit" align="middle" name="register" value="Δημιουργία" size="4"></td>
    		</tr>
    		<tr>
   	  			
  			</tr>
    		<tr>
	    		
    		</tr>
    		</table>
    	</form>
    </th>
    
    <td>&nbsp;</td>
 </tr>


<tr></tr>
</table>
</body>
</html>

