<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="java.io.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="java.servlet.http.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Login</title>
<link rel="STYLESHEET" href="menu.css" type="text/css">
</head>



<%
         
    response.setContentType("text/html");
   
    Enumeration paramNames = request.getParameterNames();

String user = "wronguser";
String kwdikos = "1111";
String register = "nothing";
int elegxos=0;
int mysqlok=-1;
int mysqlerror=-1;


 while(paramNames.hasMoreElements()) {
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
if(user.equals("")  || kwdikos.equals("") || !register.equals("Login")){
elegxos=1;
}
else {
//mysql code
String connectionURL = "jdbc:mysql://127.0.0.1/storage_cloud";
Connection connection = null;
Statement statement = null;
ResultSet rs = null;
Class.forName("com.mysql.jdbc.Driver").newInstance();
connection = DriverManager.getConnection(connectionURL, "root", "121084");
statement = connection.createStatement();
String query = "SELECT COUNT(*) FROM users WHERE username=\""+user+"\" AND password=\""+kwdikos+"\"";
rs = statement.executeQuery(query);
rs.next();
if(rs.getString("COUNT(*)").equals("1")){
session.setAttribute( "theName", user );
mysqlok=1;             
rs.close();
}
else 
mysqlerror=1;
rs.close();
}


        %>



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
    		<li><a href="signup.jsp" title="Link 3"><span>Εγγραφή</span></a></li>
    		<li><a href="#" title="Link 4"><span>Πληροφορίες</span></a></li>
    		<li><a href="login.jsp" title="Link 5"><span><font color="EE2C2C">Σύνδεση</font></span></a></li>
    		<li><a href="#" title="Link 6"><span>Αναζήτηση</span></a></li>
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
    	<form name="information"  action="" method="post" onSubmit="return checksignup(document.stoixeia);">
    		<table width="60%" border="0" align="center" bgcolor="silver" frame="border">
    		<tr>
	    		<td align="center" colspan="2"><font size="5"><b>Σύνδεση</b><br></font><% if(elegxos==1 && register.equals("Login")){ out.println("<font size=2 color = red>*Δεν έχετε συμπληρώσει όλα τα στοιχεία σας</font><br>"); }
if(mysqlerror==1 && register.equals("Login")){ out.println("<font size=2 color = red>*Έχετε εισάγει λάθος στοιχεία</font><br>"); } %></td> 
 			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr> 
			<tr>
				<td align="right"><font size="3"><b>Όνομα χρήστη: </b> </font></td>
				<td align="center"><input type="text" name="username" maxlength="50" size="25"> </td>
			</tr>
			<tr>
				<td align="right"><font size="3"><b>Κωδικός: </b> </font></td>
				<td align="center"><input type="password" name="password" maxlength="50" size="25"> </td>
   			</tr>
    		<tr>  
				<td colspan="2" align="center"><br><input type="submit" align="middle" name="register" value="Login" size="4"></td>
    		</tr>
    		<tr>
   	  			<td colspan="2" align="center"><font size="1"><a href="#">Ξεχάσατε το συνθηματικό σας?</a></font></td>
  			</tr>
    		<tr>
	    		<td colspan="2"><font size="1">Δεν έχετε λογαριασμό; <a href="signup.jsp">Εγγραφή τώρα</a></font></td>
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

