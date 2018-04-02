<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="java.io.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="java.servlet.http.*" %>
<%@ page import="java.util.*" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Untitled Document</title>
<link rel="STYLESHEET" href="menu.css" type="text/css">
</head>
<%
         {
    response.setContentType("text/html");

    Enumeration paramNames = request.getParameterNames();
int check = 0;
int xwros;
String user;
String oroi = "NULL";
String kwdikos;
String kwdikos2;
String myemail;
String myregister;

 while(paramNames.hasMoreElements()) {
      String paramName = (String)paramNames.nextElement();
      if(paramName.equals("capacity")   ){

	String[] paramValues = request.getParameterValues(paramName);
      int capacity = Integer.parseInt(paramValues[0]);
      out.println("capacity: " + capacity + "<br>");
      xwros = capacity;
	}
	else if ( paramName.equals("username")   ){

      String[] paramValues = request.getParameterValues(paramName);
      String username = paramValues[0];
      out.println("username: " + username + "<br>");
      user = username;
	}

	else if (paramName.equals("pass1")  ){

      String[] paramValues = request.getParameterValues(paramName);
      String pass1 = paramValues[0];
      out.println("pass1: " + pass1 + "<br>");
      kwdikos = pass1;
	}

	else if ( paramName.equals("pass2")  ){

      String[] paramValues4 = request.getParameterValues(paramName);
      String pass2 = paramValues4[0];
      out.println("pass2: " + pass2 + "<br>");
      kwdikos2 = pass2;
	}
	else if  ( paramName.equals("register")   ){

      String[] paramValues5 = request.getParameterValues(paramName);
      String register = paramValues5[0];
      out.println("register: " + register + "<br>");
      myregister = register;
	}
      else if ( paramName.equals("terms") ){
	check = 1;
      String[] paramValues6 = request.getParameterValues(paramName);
      String terms = paramValues6[0];
      out.println("terms: " + terms + "<br>");
      oroi = terms;
	}



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

    	<td width="27%" align="center" valign="top"><p> <font size="2"> &nbsp; &nbsp; &nbsp; &nbsp;<a href="#"><img src="images/uk-flag.jpg" alt="select language" width="31" height="18" align="top"/></a> &nbsp; &nbsp; &nbsp;<a href="javascript:void(0)"onclick="window.open	('help.html','linkname','height=480, width=450')"><strong>link1</strong></a> &nbsp; &nbsp; &nbsp;<strong><a href="#">link2</a></strong></td>
</tr>
<tr>
	<td>&nbsp;</td>
    <td height="56" align="center" valign="top">

    <table align="center" border="0">
   		<tr>
    	<td align="center" valign="middle">
		<div id="tabsF">
  			<ul>
   			<li><a href="#" title="Link 1"><span>Home</span></a></li>
    		<li><a href="#" title="Link 2"><span>Products</span></a></li>
    		<li><a href="#" title="Link 3"><span><font color="EE2C2C">Sign up</font></span></a></li>
    		<li><a href="#" title="Link 4"><span>About</span></a></li>
    		<li><a href="#" title="Link 5"><span>Log in</span></a></li>
    		<li><a href="#" title="Link 6"><span>Search</span></a></li>
  			</ul>
		</div>
    	</td>
 	 </tr>
	</table>
    </td>
</tr>
  <tr>

    <td>&nbsp;</td>

    <td align="center" ><font size="5"><b> Sign up</b></font></td>

    <td>&nbsp;</td>

  </tr>

  <tr>
    <td>&nbsp;</td>
    <td align="center"><form name="information"  action="signup.jsp" method="post" onSubmit="return checksignup(document.stoixeia);">
    	<table width="60%" border="0" align="center">
      <tr>
        <td width="45%">&nbsp;</td>
        <td width="55%">&nbsp;</td>
      </tr>
      <tr>
        <!-- <td colspan="2"><font size="2">Τα πεδία με <font color="#FF0000">*</font> είναι υποχρεωτικό να συμπληρωθούν</font></td>-->
      </tr>
      <tr>
        <td><font size="2"><b> Όνομα </b> </font></td>
        <td align="center"><input type="text" name="username" maxlength="50" size="25"> </td>
      </tr>
      <tr>
        <td><font size="2"><b> Κωδικός </b> </font></td>
        <td align="center"><input type="password" name="pass1" maxlength="50" size="25"> </td>
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
		<td align="center"><input type="text" name="email" maxlength="50" size="25"> </td>
	 </tr>
      <tr>
        <td><font size="2"><b>????????????</b> </font></td>
		<td align="center">
			<input type="radio" checked="checked" name="capacity" value="10"> <font size="1"><b>10GB</b> </font>
			<input type="radio" name="capacity" value="100"> <font size="1"><b>100GB</b> </font>
			<input type="radio" name="capacity" value="500"> <font size="1"><b>500GB</b> </font>
		</td>
	 </tr>

     <tr>
        <td><font size="2"><b> &nbsp;</b> </font></td>
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
    </form></td>
    <td>&nbsp;</td>
 </tr>


<tr></tr>
</table>
</body>
</html>


