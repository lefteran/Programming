package results;

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
import java.lang.Math.*;
import database.*;
import kalathi.*;
/**
 *
 * @author johnfouf
 */
public class Results extends HttpServlet {

    private
        HttpSession session;
        PrintWriter out;
        Database db;
        Mybasket basket;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        session = request.getSession(true);
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        out = response.getWriter();
        basket = new Mybasket(session);
        String order = "name asc";
        
        printoutput();
        Enumeration paramNames = request.getParameterNames();

        String search = null;
        String register = null;
        String name= null;
        String user=null;
        String myfile=null;
        String dir=null;
        Integer screen=-1;
        Integer start;
        Integer free = -1;
        Integer size=-1;
        Integer orioA = -1;
        Integer orioB = -1;

        while(paramNames.hasMoreElements()) {
            String paramName = (String)paramNames.nextElement();

            if(paramName.equals("search")   ){
                String[] paramValues = request.getParameterValues(paramName);
                search = paramValues[0];
            }
            else if(paramName.equals("size")   ){
                String[] paramValues = request.getParameterValues(paramName);
                size = Integer.parseInt(paramValues[0]);
            }
             else if(paramName.equals("order")   ){
                String[] paramValues = request.getParameterValues(paramName);
                order = paramValues[0];
            }
            else if ( paramName.equals("file")   ){
                String[] paramValues2 = request.getParameterValues(paramName);
                name = paramValues2[0];
            }
            else if ( paramName.equals("free")   ){
                String[] paramValues2 = request.getParameterValues(paramName);
                free = Integer.parseInt(paramValues2[0]);
            }
            if(paramName.equals("screen")   ){
                String[] paramValues = request.getParameterValues(paramName);
                screen = Integer.parseInt(paramValues[0]);
            }
            else if ( paramName.equals("register") ){
                String[] paramValues3 = request.getParameterValues(paramName);
                register = paramValues3[0];
            }
            else if ( paramName.equals("myfile")   ){
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

        if(register==null){
            out.println("<script language=\"javascript\"> location=\"search.jsp\"; </script>");
            return;
        }

        else{
            if(size==2){
                orioA=50*1024;
                orioB=100*1024;
            }
            if(size==1){
                orioA=0*1024;
                orioB=50*1024;
            }
            if(size==3){
                orioA=100*1024;
            }
 
            try {
               db = new Database();
               db.connect();

                if(dir!=null && myfile!=null && user!=null)
                    addtobasket(myfile,dir,user);

                Integer rows_per_page=10;
                if(screen==-1) screen=0;
                start = screen * rows_per_page;
                ResultSet rs = null;
                ResultSet rs2 = null;
                String query=null;
                String query2=null;
    
               if(size==0){
                if(name.equals("")){
                    if(free==1){
                        query2 =  "SELECT * FROM files WHERE is_public=1 AND price=0 ORDER BY "+ order;
                        query = "SELECT * FROM files WHERE is_public=1 AND price=0 ORDER BY "+ order+" LIMIT "+start+","+ rows_per_page;
                    }
                    else{
                        query2 =  "SELECT * FROM files WHERE is_public=1 ORDER BY "+ order;
                        query = "SELECT * FROM files WHERE is_public=1 ORDER BY "+ order+" LIMIT "+start+","+ rows_per_page;
                    }
                }
                else if(search.equals("author") && !name.equals("")){ // anazitisi me onoma xristi
                    if(free==1){
                     query2 =  "SELECT * FROM files WHERE username LIKE '%"+name+"%' AND is_public=1 AND price=0 ORDER BY "+ order+" ";
                    query = "SELECT * FROM files WHERE username LIKE '%"+name+"%' AND is_public=1 AND price=0 ORDER BY "+ order+" LIMIT "+start+","+ rows_per_page;
                    }
                    else{
                    query2 =  "SELECT * FROM files WHERE username LIKE '%"+name+"%' AND is_public=1 ORDER BY "+ order+" ";
                    query = "SELECT * FROM files WHERE username LIKE '%"+name+"%' AND is_public=1 ORDER BY "+ order+" LIMIT "+start+","+ rows_per_page;
                    }
                }

                else if (search.equals("file") && !name.equals("")){//anazitisi me arxeio
                    if(free==1){
                      if(name.equals("home")||name.equals("hom")||name.equals("ho")||name.equals("h")||name.equals("o")||name.equals("m")||name.equals("e")||name.equals("me")||name.equals("ome")||name.equals("om")){
                        query2 =  "SELECT * FROM files WHERE name LIKE '%"+name+"%' AND is_public=1 AND price=0 ORDER BY "+ order;
                        query = "SELECT * FROM files WHERE name LIKE '%"+name+"%' AND is_public=1 AND price=0 ORDER BY "+ order+" LIMIT "+start+","+ rows_per_page;
                    }
                    else {
                        query2 =  "SELECT * FROM files WHERE is_public=1 AND price=0 AND name LIKE '%"+name+"%' ORDER BY "+ order+" ";
                        query = "SELECT * FROM files WHERE is_public=1 AND price=0 AND name LIKE '%"+name+"%' ORDER BY "+ order+" LIMIT "+start+", "+ rows_per_page;
                    }
                    }
                    else{
                    if(name.equals("home")||name.equals("hom")||name.equals("ho")||name.equals("h")||name.equals("o")||name.equals("m")||name.equals("e")||name.equals("me")||name.equals("ome")||name.equals("om")){
                        query2 =  "SELECT * FROM files WHERE name LIKE '%"+name+"%' AND is_public=1 ORDER BY "+ order+" ";
                        query = "SELECT * FROM files WHERE name LIKE '%"+name+"%' AND is_public=1 ORDER BY "+ order+" LIMIT "+start+","+ rows_per_page;
                    }
                    else {
                        query2 =  "SELECT * FROM files WHERE is_public=1 AND name LIKE '%"+name+"%' ORDER BY "+ order+" ";
                        query = "SELECT * FROM files WHERE is_public=1 AND name LIKE '%"+name+"%' ORDER BY "+ order+" LIMIT "+start+", "+ rows_per_page;
                    }
                    }
                }
               }



                else if(size==1 || size==2){
                    if(name.equals("")){
                    if(free==1){
                        query2 =  "SELECT * FROM files WHERE is_public=1 AND price=0 AND size <="+orioB+" AND size >= "+orioA+" ORDER BY "+ order;
                        query = "SELECT * FROM files WHERE is_public=1 AND price=0 AND size <="+orioB+" AND size >="+orioA+" ORDER BY "+ order+" LIMIT "+start+","+ rows_per_page;
                    }
                    else{
                        query2 =  "SELECT * FROM files WHERE is_public=1  AND size <= "+orioB+" AND size >="+orioA+" ORDER BY "+ order;
                        query = "SELECT * FROM files WHERE is_public=1  AND size <="+orioB+" AND size >="+orioA+" ORDER BY "+ order+" LIMIT "+start+","+ rows_per_page;
                    }
                }
                else if(search.equals("author") && !name.equals("")){ // anazitisi me onoma xristi
                    if(free==1){
                     query2 =  "SELECT * FROM files WHERE username LIKE '%"+name+"%' AND size <="+orioB+" AND size >= "+orioA+"  AND is_public=1 AND price=0 ORDER BY "+ order;
                    query = "SELECT * FROM files WHERE username LIKE '%"+name+"%' AND size <="+orioB+" AND size >= "+orioA+"  AND is_public=1 AND price=0 ORDER BY "+ order+" LIMIT "+start+","+ rows_per_page;
                    }
                    else{
                    query2 =  "SELECT * FROM files WHERE username LIKE '%"+name+"%'  AND size <="+orioB+" AND size >= "+orioA+" AND is_public=1 ORDER BY "+ order;
                    query = "SELECT * FROM files WHERE username LIKE '%"+name+"%'  AND size <="+orioB+" AND size >= "+orioA+" AND is_public=1 ORDER BY "+ order+" LIMIT "+start+","+ rows_per_page;
                    }
                }

                else if (search.equals("file") && !name.equals("")){//anazitisi me arxeio
                    if(free==1){
                      if(name.equals("home")||name.equals("hom")||name.equals("ho")||name.equals("h")||name.equals("o")||name.equals("m")||name.equals("e")||name.equals("me")||name.equals("ome")||name.equals("om")){
                        query2 =  "SELECT * FROM files WHERE name LIKE '%"+name+"%'  AND size <="+orioB+" AND size >= "+orioA+" AND is_public=1 AND price=0 ORDER BY "+ order;
                        query = "SELECT * FROM files WHERE name LIKE '%"+name+"%'  AND size <="+orioB+" AND size >= "+orioA+" AND is_public=1 AND price=0 ORDER BY "+ order+" LIMIT "+start+","+ rows_per_page;
                    }
                    else {
                        query2 =  "SELECT * FROM files WHERE is_public=1  AND size <="+orioB+" AND size >= "+orioA+" AND price=0 AND name LIKE '%"+name+"%' ORDER BY "+ order;
                        query = "SELECT * FROM files WHERE is_public=1  AND size <="+orioB+" AND size >= "+orioA+" AND price=0 AND name LIKE '%"+name+"%' ORDER BY "+ order+" LIMIT "+start+", "+ rows_per_page;
                    }
                    }
                    else{
                    if(name.equals("home")||name.equals("hom")||name.equals("ho")||name.equals("h")||name.equals("o")||name.equals("m")||name.equals("e")||name.equals("me")||name.equals("ome")||name.equals("om")){
                        query2 =  "SELECT * FROM files WHERE name LIKE '%"+name+"%'  AND size <="+orioB+" AND size >= "+orioA+" AND is_public=1 ORDER BY "+ order;
                        query = "SELECT * FROM files WHERE name LIKE '%"+name+"%'  AND size <="+orioB+" AND size >= "+orioA+" AND is_public=1 ORDER BY "+ order+" LIMIT "+start+","+ rows_per_page;
                    }
                    else {
                        query2 =  "SELECT * FROM files WHERE is_public=1  AND size <="+orioB+" AND size >= "+orioA+" AND name LIKE '%"+name+"%' ORDER BY "+ order;
                        query = "SELECT * FROM files WHERE is_public=1  AND size <="+orioB+" AND size >= "+orioA+" AND name LIKE '%"+name+"%' ORDER BY "+ order+" LIMIT "+start+", "+ rows_per_page;
                    }
                    }
                }
                }




                else if(size==3){
                    if(name.equals("")){
                    if(free==1){
                        query2 =  "SELECT * FROM files WHERE is_public=1 AND price=0 AND size >= "+orioA+" ORDER BY "+ order;
                        query = "SELECT * FROM files WHERE is_public=1 AND price=0 AND size >= "+orioA+"  ORDER BY "+ order+" LIMIT "+start+","+ rows_per_page;
                    }
                    else{
                        query2 =  "SELECT * FROM files WHERE is_public=1 AND size >= "+orioA+" ORDER BY "+ order;
                        query = "SELECT * FROM files WHERE is_public=1 AND size >= "+orioA+" ORDER BY "+ order+" LIMIT "+start+","+ rows_per_page;
                    }
                }
                else if(search.equals("author") && !name.equals("")){ // anazitisi me onoma xristi
                    if(free==1){
                     query2 =  "SELECT * FROM files WHERE username LIKE '%"+name+"%' AND size >= "+orioA+" AND is_public=1 AND price=0 ORDER BY "+ order;
                    query = "SELECT * FROM files WHERE username LIKE '%"+name+"%' AND size >= "+orioA+"  AND is_public=1 AND price=0 ORDER BY "+ order+" LIMIT "+start+","+ rows_per_page;
                    }
                    else{
                    query2 =  "SELECT * FROM files WHERE username LIKE '%"+name+"%' AND size >= "+orioA+"  AND is_public=1 ORDER BY "+ order;
                    query = "SELECT * FROM files WHERE username LIKE '%"+name+"%' AND size >= "+orioA+"  AND is_public=1 ORDER BY "+ order+" LIMIT "+start+","+ rows_per_page;
                    }
                }

                else if (search.equals("file") && !name.equals("")){//anazitisi me arxeio
                    if(free==1){
                      if(name.equals("home")||name.equals("hom")||name.equals("ho")||name.equals("h")||name.equals("o")||name.equals("m")||name.equals("e")||name.equals("me")||name.equals("ome")||name.equals("om")){
                        query2 =  "SELECT * FROM files WHERE name LIKE '%"+name+"%' AND size >= "+orioA+"  AND is_public=1 AND price=0 ORDER BY "+ order;
                        query = "SELECT * FROM files WHERE name LIKE '%"+name+"%' AND size >= "+orioA+"  AND is_public=1 AND price=0 ORDER BY "+ order+" LIMIT "+start+","+ rows_per_page;
                    }
                    else {
                        query2 =  "SELECT * FROM files WHERE is_public=1 AND size >= "+orioA+"  AND price=0 AND name LIKE '%"+name+"%' ORDER BY "+ order;
                        query = "SELECT * FROM files WHERE is_public=1 AND size >= "+orioA+"  AND price=0 AND name LIKE '%"+name+"%' ORDER BY "+ order+" LIMIT "+start+", "+ rows_per_page;
                    }
                    }
                    else{
                    if(name.equals("home")||name.equals("hom")||name.equals("ho")||name.equals("h")||name.equals("o")||name.equals("m")||name.equals("e")||name.equals("me")||name.equals("ome")||name.equals("om")){
                        query2 =  "SELECT * FROM files WHERE name LIKE '%"+name+"%'  AND size >= "+orioA+" AND is_public=1 ORDER BY "+ order;
                        query = "SELECT * FROM files WHERE name LIKE '%"+name+"%' AND size >= "+orioA+"  AND is_public=1 ORDER BY "+ order+" LIMIT "+start+","+ rows_per_page;
                    }
                    else {
                        query2 =  "SELECT * FROM files WHERE is_public=1  AND size >= "+orioA+" AND name LIKE '%"+name+"%' ORDER BY "+ order;
                        query = "SELECT * FROM files WHERE is_public=1  AND size >= "+orioA+" AND name LIKE '%"+name+"%' ORDER BY "+ order+" LIMIT "+start+", "+ rows_per_page;
                    }
                    }
                }
                }
                rs = db.selectquery(query);
                rs2 = db.selectquery(query2);
                rs2.last();
                Integer results = rs2.getRow();

                Integer pages = (int)(Math.ceil((double)results/(double)rows_per_page));
                rs2.close();

                if(rs.next()){
                    content(rs,screen,name,search,pages,size,free,order);
                    rs.close();
                }
                else {
                    out.println("<table border=\"0\" align=\"center\">" +
                    "<tr><td colspan=\"6\"  align=\"center\">" +
                    //"Total space: "+limit+" GB Free space: "+free+" MB  Used space: "+used+" MB  Public Space: "+mypublic+
                    "<tr>" +
                    "<td width=\"400\" align=\"center\"><font size=\"3\" >There are no results please search again</td></tr></td></tr>");
                }
            }
            catch (SQLException e) {
                throw new ServletException("Servlet Could not display records.", e);
            }
             finally{
        db.close();
    }
        }

  }

  protected void addtobasket(String myfile,String dir,String user)
    throws ServletException, IOException {
        ResultSet result = null;
     

        try{
          
            String myquery = "SELECT * FROM files WHERE is_public=1 AND name=\""+myfile+"\" AND username=\""+user+"\"AND directory=\""+dir+"\"";
            result = db.selectquery(myquery);
            if(result.next()){
              basket.add_item(myfile,dir,user);
            }
            result.close();
        }
        catch (SQLException e) {
            throw new ServletException("Servlet Could not display records.", e);
        }
  }



  protected void content(ResultSet rs,Integer screen, String name,String search,Integer pages,Integer size,Integer free,String order)
          throws ServletException, IOException {

            out.println("<table border=\"0\" align=\"center\">" +
            "<tr><td colspan=\"6\"  align=\"center\">" +
            //"Total space: "+limit+" GB Free space: "+free+" MB  Used space: "+used+" MB  Public Space: "+mypublic+
                  "<tr align=\"center\"><td align=\"center\"><font size=2></font><form action=\"Results?screen="+screen+"&size="+size+"&free="+free+"&file="+name+"&register=Search&search="+search+"\"  method=post><font size=4> Results &nbsp;&nbsp;&nbsp;</font><font size=2>Order by </font><select name=\"order\"   >"+
                   
                  "<option ");if(order.equals("name asc"))out.println("selected=\"selected\""); out.println("value=\"name asc\" >name (ascending)</option>"+
                  
                                               " <option");if(order.equals("name desc")) out.println("selected=\"selected\""); out.println(" value=\"name desc\" >name (descending)</option>"+
                                               " <option");if(order.equals("price asc")) out.println("selected=\"selected\""); out.println(" value=\"price asc\" >price (ascending) </option>"+
                                               " <option");if(order.equals("price desc")) out.println("selected=\"selected\""); out.println(" value=\"price desc\" >price (descending)</option>"+
                                                 " <option");if(order.equals("popularity asc")) out.println("selected=\"selected\""); out.println(" value=\"popularity asc\" >popularity (ascending)</option>"+
                                                " <option");if(order.equals("popularity desc")) out.println("selected=\"selected\""); out.println(" value=\"popularity desc\" >popularity (descending)</option>"+
                                                " <option");if(order.equals("size asc")) out.println("selected=\"selected\""); out.println(" value=\"size asc\" >size (ascending) </option>"+
                                                " <option");if(order.equals("size desc")) out.println("selected=\"selected\""); out.println(" value=\"size desc\" >size (descending) </option>"+
                                            "</select>&nbsp; &nbsp;<input type=\"submit\" value=\"ok\"></form></td></tr></table><table border=\"0\" align=\"center\">" +
            "<tr><td colspan=\"6\"  align=\"center\">" +
            "<tr>" +
            "<td width=\"180\" align=\"center\"><font size=\"4\" color=\"#0000CD\">Title</font></td>" +
            "<td width=\"100\" align=\"center\"><font size=\"4\" color=\"#0000CD\">Size</font></td>" +
            "<td width=\"100\" align=\"center\"><font size=\"4\" color=\"#0000CD\">Price</font></td>" +
            "<td width=\"100\" align=\"center\"><font size=\"4\" color=\"#0000CD\">Downloads</font></td>" +
            "<td width=\"130\" align=\"center\"><font size=\"4\" color=\"#0000CD\">Owner</font></td>" +
            "<td width=\"180\" align=\"center\"><font size=\"4\" color=\"#0000CD\">Add to basket</font></td></tr></td></tr>");

            try{
                java.util.Date d1 = new java.util.Date();
                do {
                    out.println("<tr><td align=\"center\"><font size=\"2\">"+ rs.getString("name") +"</font></td><td align=\"center\"><font size=\"2\">"+ rs.getString("size") +" KB </font></td>");
                    if(rs.getInt("price")==0){
                        out.println("<td align=\"center\"><font size=\"2\"> free </font></td>");
                    }
                    else
                        out.println("<td align=\"center\"><font size=\"2\">"+ rs.getInt("price") +"$</font></td>");
                    out.println("<td align=\"center\"><font size=\"2\">"+ rs.getString("popularity") +" </font></td><td align=\"center\"><font size=\"2\">"+ rs.getString("username") +" </font></td><td align=\"center\">");
                    ArrayList<String> myeps = new ArrayList<String>();
                    myeps.add(rs.getString("name"));
                    myeps.add(rs.getString("directory"));
                    myeps.add(rs.getString("username"));
                    if(!basket.contain(myeps))
                        out.println("<a href=\"Results?screen="+screen+"&register=Search&size="+size+"&free="+free+"&file="+name+"&search="+search+"&user="+rs.getString("username")+"&order="+order+"&myfile="+rs.getString("name") +"&dir="+rs.getString("directory")+" \"><img src=\"images/basket.jpg\"border=\"0\" height=20% width=20%></a></td></tr>");
                    else
                        out.println("<font size=2>Added</font></td></tr>");
                }
                while (rs.next());

                out.println ("<table border=\"0\" align=\"center\">" +
                "<tr><td align=\"center\" width=100%><br><p><hr></p>\n");

                Integer myscreen;
                String url=null;
                if(pages>1)
                    out.println( "<font size=2>There are "+pages+" pages:</font><br>") ;
                if(pages>1 && pages<10){
                    if (screen > 0) {
                        myscreen = screen-1;
                        url = "Results?screen="+myscreen+"&order="+order+"&size="+size+"&free="+free+"&file="+name+"&register=Search&search="+search;
                        out.println( "<a href=\""+url+"\"><font size=2><<</font></a>\n");
                    }
                    for (Integer i = 1; i <= pages; i++) {
                        if(i-1!=screen){ Integer p = i-1;
                            url = "Results?screen="+p+"&order="+order+"&size="+size+"&free="+free+"&file="+name+"&register=Search&search="+search;
                            out.println(" | <a href=\""+url+"\"><font size=2>"+i+"</font></a> | ");
                        }
                        else {
                            Integer p = i-1;
                            url = "Results?screen="+p+"&order="+order+"&size="+size+"&free="+free+"&file="+name+"&register=Search&search="+search;
                            out.println(" | <a href=\""+url+"\"><b><font size=4>"+i+"</font></b></a> | ");
                        }
                    }
                }

                if(pages>10){
                    if (screen > 0) {
                        myscreen = screen-1;
                        url = "Results?screen="+myscreen+"&order="+order+"&size="+size+"&free="+free+"&file="+name+"&register=Search&search="+search;
                        out.println( "<a href=\""+url+"\"><font size=2><<</font></a>\n");
                    }
                    if(screen<5)
                        for (Integer i = 1; i <= 10; i++) {
                            if(i-1!=screen){ Integer p = i-1;
                                url = "Results?screen="+p+"&order="+order+"&size="+size+"&free="+free+"&file="+name+"&register=Search&search="+search;
                                out.println(" | <a href=\""+url+"\"><font size=2>"+i+"</font></a> | ");
                            }
                            else {
                                Integer p = i-1;
                                url = "Results?screen="+p+"&order="+order+"&size="+size+"&free="+free+"&file="+name+"&register=Search&search="+search;
                                out.println(" | <a href=\""+url+"\"><b><font size=4>"+i+"</font></b></a> | ");
                            }
                        }
                    if(screen>=5 && screen<pages-5)
                        for (Integer i = screen-3; i<=pages && i <= screen+6; i++) {
                            if(i-1!=screen){ Integer p = i-1;
                                url = "Results?screen="+p+"&order="+order+"&size="+size+"&free="+free+"&file="+name+"&register=Search&search="+search;
                                out.println(" | <a href=\""+url+"\"><font size=2>"+i+"</font></a> | ");
                            }
                            else {
                                Integer p = i-1;
                                url = "Results?screen="+p+"&order="+order+"&size="+size+"&free="+free+"&file="+name+"&register=Search&search="+search;
                                out.println(" | <a href=\""+url+"\"><b><font size=4>"+i+"</font></b></a> | ");
                            }
                        }
                    if(screen>=pages-5)
                        for (Integer i = pages-9; i<=pages; i++) {
                            if(i-1!=screen){ Integer p = i-1;
                                url = "Results?screen="+p+"&order="+order+"&size="+size+"&free="+free+"&file="+name+"&register=Search&search="+search;
                                out.println(" | <a href=\""+url+"\"><font size=2>"+i+"</font></a> | ");
                            }
                            else {
                                Integer p = i-1;
                                url = "Results?screen="+p+"&order="+order+"&size="+size+"&free="+free+"&file="+name+"&register=Search&search="+search;
                                out.println(" | <a href=\""+url+"\"><b><font size=4>"+i+"</font></b></a> | ");
                            }
                        }
                }
                if (screen < pages-1) {
                    myscreen = screen+1;
                    url = "Results?screen="+myscreen+"&order="+order+"&size="+size+"&free="+free+"&file="+name+"&register=Search&search="+search;
                    out.println("<a href=\""+url+"\"><font size=2>>></font></a>\n");
                }
                out.println("</td></tr>");
            }
            catch (SQLException e) {
                throw new ServletException("Servlet Could not display records.", e);
            }
  }

  protected void printoutput()
    throws ServletException, IOException {
        out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />"+
            "<style type=\"text/css\"><!-- A:link {text-decoration:none} A:visited {text-decoration:none} A:active {text-decoration:none} A:hover {text-decoration:underline}  --></style>"+
            "<title>results</title>"+"<link rel=\"STYLESHEET\" href=\"menu.css\" type=\"text/css\">"+
            "<style type=\"text/css\">th{}th.margin{margin-left:50px;}</style>"+
            "<script type=\"text/javascript\" src=\"simpletreemenu.js\"></script>"+
            "<script type=\"text/javascript\">function submit_value(pid){window.location=pid}</script>"+
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
            if(session.getAttribute( "theName" )!=null){
                out.println("<div id=\"tabsF\">" +
            "<ul><li><a href=\"index.htm\" title=\"Home\"><span>Home</span></a></li>" +
            "<li><a href=\"Myspace\" title=\"Myspace\"><span>My space</span></a></li>" +
            "<li><a href=\"info.jsp\" title=\"Info\"><span>Info</span></a></li>" +
            "<li><a href=\"search.jsp\" title=\"Search\"><span><font color=\"#EE2C2C\">Search</font></span></a></li>" +
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