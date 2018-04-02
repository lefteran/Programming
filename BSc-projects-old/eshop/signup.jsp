<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="java.io.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="java.servlet.http.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Untitled Document</title>
<link rel="STYLESHEET" href="menu.css" type="text/css">
</head>
<%
         
    response.setContentType("text/html");
   
    Enumeration paramNames = request.getParameterNames();
int check = 0;
int xwros = 0;
int lathi = 0;
int elegxos = 0;
int apodoxi = 0;
int cardcheck = 0;
int wrongemail = 0;
String user = "wronguser";
String oroi = "NULL";
String kwdikos = "1111";
String kwdikos2 = "1111";
String myemail = "nothing";
String myregister = "ok";
String onoma = "nothing";
String epitheto = "nothing";
String cardname = "nothing";
String cardno = "00";
String cardsurname = "nothing";
String cardcvv = "00";

 while(paramNames.hasMoreElements()) {
      String paramName = (String)paramNames.nextElement();
      
	if(paramName.equals("capacity")   ){

	String[] paramValues = request.getParameterValues(paramName);
      xwros = Integer.parseInt(paramValues[0]);
     
     
	}
	else if ( paramName.equals("username")   ){
	
      String[] paramValues = request.getParameterValues(paramName);
      user = paramValues[0];

      
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
 
      
	}
	else if ( paramName.equals("surname") ){
	
      String[] paramValues8 = request.getParameterValues(paramName);
      epitheto = paramValues8[0];
    
      
	}
     else if ( paramName.equals("cardholder") ){
	
      String[] paramValues9 = request.getParameterValues(paramName);
      cardname = paramValues9[0];
 
     
	}
     else if ( paramName.equals("cardsurname") ){
	
      String[] paramValues9 = request.getParameterValues(paramName);
      cardsurname = paramValues9[0];
 
      
	}
else if ( paramName.equals("email") ){
	
      String[] paramValues9 = request.getParameterValues(paramName);
      myemail = paramValues9[0];
 
     
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
int mysqlok=-1;
int mysqluser=-1;
int mysqlemail=-1;
int coderror = -1;
int codsize = -1;
if(user.equals("") || epitheto.equals("") || onoma.equals("") || myemail.equals("") || myregister.equals("ok") || kwdikos.equals("") || kwdikos2.equals(""))
{
elegxos = 1;lathi=1;
}
if(  cardcvv.equals("") || cardsurname.equals("") || cardname.equals("") || cardno.equals("")  ){
elegxos=1;lathi=1;
}

if(myemail.indexOf('@')==-1 && !myemail.equals("")){
wrongemail=1;lathi=1;
}
if(!kwdikos.equals(kwdikos2) && elegxos == 0){
coderror=1;lathi=1;
}
if(kwdikos.length()<8 && !kwdikos.equals("") && coderror==-1){
codsize=1;lathi=1;
}
if(oroi.equals("NULL")&& lathi==0){
apodoxi = 1;lathi=1;
}
if(myregister.equals("Sign up") && lathi==0){


String connectionURL = "jdbc:mysql://127.0.0.1/storage_cloud";
Connection connection = null;
Statement statement = null;
ResultSet rs = null;
Class.forName("com.mysql.jdbc.Driver").newInstance();
connection = DriverManager.getConnection(connectionURL, "root", "121084");

Statement stat2 = connection.createStatement();
       String query2 = "SET NAMES utf8" ;
       ResultSet result2 = stat2.executeQuery(query2);
Statement stat3 = connection.createStatement();
       String query3 = "SET CHARSET utf8" ;
       ResultSet result3 = stat3.executeQuery(query3);

Statement stat4 = connection.createStatement();
String query1 = "SELECT COUNT(*) FROM users WHERE username=\""+user+"\"";
rs = stat4.executeQuery(query1);
rs.next();
if(rs.getString("COUNT(*)").equals("1")){
mysqluser=1;             //xristis uparxei hdh 
rs.close();
}
ResultSet rs2 = null;
 Statement stat6 = connection.createStatement();
String query6 = "SELECT COUNT(*) FROM users WHERE email=\""+myemail+"\"";
rs2 = stat6.executeQuery(query6);
rs2.next();
if(rs2.getString("COUNT(*)").equals("1")){
mysqlemail=1;             //email uparxei hdh 
rs2.close();
}

if(mysqlemail!=1 && mysqluser!=1){                   //swsti periptwsi
rs.close();
rs2.close();
statement = connection.createStatement();
String query = "INSERT INTO users VALUES(\""+user+"\",\""+ onoma  +"\",\""+ epitheto +"\",\""+ myemail  +"\",\""+ kwdikos +"\",0)";
mysqlok=1;
statement.execute(query);

}
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
    		<li><a href="signup.jsp" title="Link 3"><span><font color="EE2C2C">Εγγραφή</font></span></a></li>
    		<li><a href="#" title="Link 4"><span>Πληροφορίες</span></a></li>
    		<li><a href="login.jsp" title="Link 5"><span>Σύνδεση</span></a></li>
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
    <th align="center"><form name="information"  action="signup.jsp" method="post" onSubmit="return checksignup(document.stoixeia);">
    	<table width="60%" border="0" bgcolor = "silver" align="center">
      <tr>
 <td align="center" colspan = "2"><font size="5"><b>Εγγραφή<br></b></font><% if(elegxos==1 && myregister.equals("Sign up")){ out.println("<font size=2 color = red>*Δεν έχετε συμπληρώσει όλα τα στοιχεία σας</font><br>"); }
if(cardcheck==1 && myregister.equals("Sign up")){ out.println("<font size=2 color = red>*Δεν έχετε συμπληρώσει πλήρως τις λεπτομέρειες πληρωμής</font><br>"); }
 if(apodoxi==1 && myregister.equals("Sign up")){ out.println("<font size=2 color = red>*Θα πρέπει να αποδεχτείτε τους όρους</font><br>"); }
if(coderror==1 && myregister.equals("Sign up")){ out.println("<font size=2 color = red>*Οι κωδικοί που δώσατε δεν συμπίπτουν</font><br>"); }   %></td>
              
	
      </tr>
    <tr>
				<td>&nbsp;</td>
			</tr> 
      <tr>
        <!-- <td colspan="2"><font size="2">Τα πεδία με <font color="#FF0000">*</font> είναι υποχρεωτικό να συμπληρωθούν</font></td>-->
      </tr>
      <tr>
        <td><font size="2"><b> Όνομα </b> </font></td>
        <td align="center"><input type="text" name="name" value = "<%  if(myregister.equals("Sign up")){ out.println(onoma);  } %>"maxlength="50" size="25"> </td>
      </tr>
      <tr>
        <td><font size="2"><b> Επίθετο</b> </font></td>
        <td align="center"><input type="text" name="surname"  value = "<%  if(myregister.equals("Sign up")){ out.println(epitheto);  } %>" maxlength="50" size="25"> </td>
      </tr>
      <tr>
        <td><font size="2"><b> Όνομα χρήστη</b> </font></td>
        <td align="center"><input type="text" name="username"  value = "<%  if(myregister.equals("Sign up")){ out.println(user);  } %>" maxlength="50" size="25"> <% if(mysqluser==1 && myregister.equals("Sign up")){ out.println("<font size=1 color = red>*Μη διαθέσιμο όνομα</font><br>"); }  %></td>
      </tr>
      <tr>
        <td><font size="2"><b> Κωδικός </b> </font></td>
        <td align="center"><input type="password" name="pass1" maxlength="50" size="25"><% if(codsize==1 && myregister.equals("Sign up")){ out.println("<font size=1 color = red>*Μη ισχυρός κωδικός</font><br>"); }  %> </td>
      </tr>
      <tr>
        <td><font size="1" color="EE2C2C"><b> &nbsp;</b> </font></td>
        <td  align="center"><font size="1" color="515151"><b> Τουλάχιστον 8 χαρακτήρες</b> </font></td>
      </tr>
      <tr>
        <td><font size="2"><b>	Επανάληψη κωδικού </b> </font></td>
        <td align="center"><input type="password" name="pass2" maxlength="50" size="25"> </td>
      </tr>
      <tr>
        <td><font size="2"><b> Email</b> </font></td>
		<td align="center"><input type="text" name="email"  value = "<%  if(myregister.equals("Sign up")){ out.println(myemail);  } %>" maxlength="50" size="25"><% if(mysqlemail==1 && myregister.equals("Sign up")){ out.println("<font size=1 color = red>*Μη διαθέσιμο email</font><br>"); } if(wrongemail==1 && myregister.equals("Sign up"))out.println("<font size=1 color = red>*Μη αποδεκτή μορφή</font><br>"); %> </td>
	 </tr>
      <tr>
        <td><font size="2"><b> Χωρητικότητα</b> </font></td>
		<td align="center">
			<input type="radio" checked="checked" name="capacity" value="10"> <font size="1"><b>10GB (30$)</b> </font>
			<input type="radio" name="capacity" value="100"> <font size="1"><b>100GB(100$)</b> </font>
			<input type="radio" name="capacity" value="500"> <font size="1"><b>500GB(300$)</b> </font>
		</td>
	 </tr>

     <tr>
        <td><font size="2"><b> &nbsp;</b> </font></td>
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
		<td align="center"><input type="text" name="cardholder" value = "<%  if(myregister.equals("Sign up")){ out.println(cardname);  } %>"maxlength="50" size="25"> </td>
      </tr>
	 <tr>
        <td><font size="2"><b> Επώνυμο κατόχου </b> </font></td>
        <td align="center"><input type="text" name="cardsurname" value = "<%  if(myregister.equals("Sign up")){ out.println(cardsurname);  } %>"maxlength="50" size="25"> </td>
      </tr>
      <tr>
	
        <td><font size="2"><b> Αριθμός κάρτας </b> </font></td>
        <td align="center"><input type="text" name="cardnumber" value = "<%  if(myregister.equals("Sign up")){ out.println(cardno);  } %>" maxlength="50" size="25"> </td>
      </tr>
      <tr>
        <td><font size="2"><b> CVV </b> </font></td>

        <td align="center"><input type="text" name="cvv" value = "<%  if(myregister.equals("Sign up")){ out.println(cardcvv);  } %>" maxlength="50" size="25"> </td>
      </tr>
     

      
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
    <td align="center" colspan="2"><font size="2"><b>Terms of use</b></font>&nbsp;</td>
  </tr>
  <tr>
    <td align="center" colspan="2"><textarea rows="5" cols="35" readonly>You must be a registered user to access the Service. You are responsible for keeping your password secure. You will be solely responsible and liable for any activity that occurs under your user name. If you lose your password or the encryption key for your account, you may not be able to access your data.</textarea></td>
  </tr>
  <tr>
     <td colspan="2" align="center"><input name="terms" value ="1" type="checkbox"> <font size="2">I agree with the terms<font></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><br><input type="submit" align="middle" name="register" value="Sign up"></td>
  </tr>
    </table>
    </form></th>
    <td>&nbsp;</td>
 </tr>


<tr></tr>
</table>
</body>
</html>

