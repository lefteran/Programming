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
<th align="center">
    	<form name="information"  action="results.jsp" method="post" >
    		<table width="60%" border="0" align="center" bgcolor="silver" frame="border">
    		<tr>
	    		<td align="center" colspan="2"><font size="5"><b>Αναζήτηση αρχείου</b><br></font></td> 
 			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr> 
			
			<tr>
				<td align="right"><font size="3"><b>Με βάση το όνομα του αρχείου: </b> </font></td>
				<td align="center"><input type="text" name="file"> </td>
   			</tr>
			<tr>
				<td align="right"><font size="3"><b>Με βάση το όνομα χρήστη: </b> </font></td>
				<td align="center"><input type="text" name="user"> </td>
   			</tr>
			
   			</tr>
    		<tr>  
				<td colspan="2" align="center"><br><input type="submit" align="middle" name="register" value="Αναζήτηση" size="4"></td>
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

