<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="java.io.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="java.servlet.http.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>

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
         

    response.setContentType("text/html");
   
    Enumeration paramNames = request.getParameterNames();

int elegxos = 0;
String register = null;
String cardname = "nothing";
String cardno = "00";
String cardsurname = "nothing";
String cardcvv = "00";
int mysqlok=-1;

 while(paramNames.hasMoreElements()) {
      String paramName = (String)paramNames.nextElement();
      


      if ( paramName.equals("cardholder") ){
	
      String[] paramValues9 = request.getParameterValues(paramName);
      cardname = paramValues9[0];
 
     
	}
     else if ( paramName.equals("cardsurname") ){
	
      String[] paramValues9 = request.getParameterValues(paramName);
      cardsurname = paramValues9[0];
 
      
	}
else if  ( paramName.equals("register")   ){
	
      String[] paramValues5 = request.getParameterValues(paramName);
      register = paramValues5[0];
    
      
	}

else if ( paramName.equals("cardnumber") ){
	
      String[] paramValues9 = request.getParameterValues(paramName);
      cardno = paramValues9[0];
 
     
	}
else if ( paramName.equals("cvv") ){
	
      String[] paramValues9 = request.getParameterValues(paramName);
      String cvv = paramValues9[0];
 
      cardcvv = cvv;
	}

      }

if(  (cardcvv.equals("") || cardsurname.equals("") || cardname.equals("") || cardno.equals("")) && register!=null  ){
elegxos=1;
}
else if(register!=null){
Statement stat15 = connection.createStatement();
String query15 = "SELECT * FROM space WHERE username=\""+session.getAttribute( "theName" )+"\" ";
ResultSet result15 = stat15.executeQuery(query15);
result15.next();
long limit = Integer.parseInt(result15.getString("mylimit"));
long myprivate = Integer.parseInt(result15.getString("private_size"));
limit = limit+(1024*1024);
myprivate = myprivate + (1024*1024);

result15.close();
Statement statement16 = null;
statement16 = connection.createStatement();
String query16 = "UPDATE space SET mylimit="+limit+" WHERE username=\""+session.getAttribute( "theName" )+"\" ";
statement16.execute(query16);
Statement statement17 = null;
statement17 = connection.createStatement();
String query17 = "UPDATE space SET private_size="+myprivate+" WHERE username=\""+session.getAttribute( "theName" )+"\" ";
statement17.execute(query17);
mysqlok=1;
}

%>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Untitled Document</title>
<link rel="STYLESHEET" href="menu.css" type="text/css">
</head>





<body background="images/deigma180.jpg" title="Αγορά χώρου">
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
    	<form name="information"  action="buyspace.jsp" method="post" >
    		<table width="60%" border="0" align="center" bgcolor="silver" frame="border">
    		<tr>
	    		<td align="center" colspan="2"><font size="5"><b>Αγορά επιπλέον χώρου</b><br>1GB (15$)</font><% if(elegxos==1){ out.println("<br><font size=2 color = red>*Δεν έχετε συμπληρώσει όλα τα στοιχεία σας</font><br>"); } 
if(mysqlok==1){ out.println("<br><font size=2 color = red>*Αυξήσατε επιτυχώς τον χώρο σας κατά 1GB!</font><br>"); }%></td> 
 			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr> 
			
	
			 <tr>
        <td align="center" colspan="2"><font size="3"><b>Λεπτομέρειες πληρωμής</b> </font></td>
	 </tr>
      <tr>
        <td><font size="2"><b> &nbsp;</b> </font></td>
        <td align="center" ><img src="images/cards.png"></td>

	 </tr>

      <tr>
        <td><font size="2"><b> Όνομα κατόχου </b> </font></td>
		<td align="center"><input type="text" name="cardholder" maxlength="50" size="25"> </td>
      </tr>
	 <tr>
        <td><font size="2"><b> Επώνυμο κατόχου </b> </font></td>
        <td align="center"><input type="text" name="cardsurname" maxlength="50" size="25"> </td>
      </tr>
      <tr>
	
        <td><font size="2"><b> Αριθμός κάρτας </b> </font></td>
        <td align="center"><input type="text" name="cardnumber"  maxlength="50" size="25"> </td>
      </tr>
      <tr>
        <td><font size="2"><b> CVV </b> </font></td>

        <td align="center"><input type="text" name="cvv"  maxlength="50" size="25"> </td>
      </tr>
     
			
   			</tr>
    		<tr>  
				<td colspan="2" align="center"><br><input type="submit" align="middle" name="register" value="Αγορά" size="4"></td>
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

