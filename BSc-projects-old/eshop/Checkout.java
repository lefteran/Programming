package checkout;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import database.Database;
import kalathi.*;


public class Checkout extends HttpServlet {
    private
        HttpSession session;
        PrintWriter out;
        Database db;
        Mybasket basket;
        Integer price;
        String cardno1 ;
        String cardno2 ;
        String cardno3 ;
        String cardno4 ;
        String cardcvv ;
        String month;
        String year;
        String cardname;
        
    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response)
      throws ServletException, IOException {
        session = request.getSession(true);
        System.out.println("<meta http-equiv=\"Content-Type\" content=\"text/html\" charset=\"UTF-8\" />");
        System.out.println("<%@page contentType=\"text/html\" pageEncoding=\"UTF-8\"%>");
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        out = response.getWriter();
        Enumeration paramNames = request.getParameterNames();

 if(session.getAttribute( "theName" )== null){
        

        Integer elegxos = 0;
        String register = null;
        cardname="00";
        year="";
        month="";
        cardno1 = "";
        cardno2 = "";
        cardno3 = "";
        cardno4 = "";
        cardcvv = "";
        Integer one=0;
        if(session.getAttribute("price")!=null){
        price = (Integer)session.getAttribute("price");
        if(price==0)
            Buy();
        }
        while(paramNames.hasMoreElements()) {
            one++;
            String paramName = (String)paramNames.nextElement();

            if ( paramName.equals("name") ){
                String[] paramValues9 = request.getParameterValues(paramName);
                cardname = paramValues9[0];
            }

            else if  ( paramName.equals("checkout")   ){
                String[] paramValues5 = request.getParameterValues(paramName);
                register = paramValues5[0];
            }
            else if ( paramName.equals("month") ){
                String[] paramValues8 = request.getParameterValues(paramName);
                month = paramValues8[0];
            }
             else if ( paramName.equals("cardnumber1") ){
                String[] paramValues9 = request.getParameterValues(paramName);
                cardno1 = paramValues9[0];
            }
            else if ( paramName.equals("cardnumber2") ){
                String[] paramValues9 = request.getParameterValues(paramName);
                cardno2 = paramValues9[0];
            }
            else if ( paramName.equals("cardnumber3") ){
                String[] paramValues9 = request.getParameterValues(paramName);
                cardno3 = paramValues9[0];
            }
            else if ( paramName.equals("cardnumber4") ){
                String[] paramValues9 = request.getParameterValues(paramName);
                cardno4 = paramValues9[0];
            }
            else if ( paramName.equals("cvv") ){
                String[] paramValues9 = request.getParameterValues(paramName);
                cardcvv  = paramValues9[0];
            }
            else if ( paramName.equals("year") ){
                String[] paramValues9 = request.getParameterValues(paramName);
                year  = paramValues9[0];
            }
          
        }
                
        java.util.Date d1 = new java.util.Date();

        try {
       
            if(one==0)
                out.println("<script language=\"javascript\"> location=\"checkout.jsp\"; </script>");
           
            else if( cardname.equals("") || cardcvv.equals("") || year.equals("") || cardno1.equals("") || month.equals("") || cardno2.equals("") || cardno3.equals("") || cardno4.equals("")){
                 out.println("<script language=\"javascript\"> history.back(); alert(\"Please complete the form!\");</script>");
            }

            else if(  Integer.parseInt(cardcvv) > 999 ||  Integer.parseInt(cardcvv) < 0  ||  cardcvv.length() !=3 ){
                out.println("<script language=\"javascript\"> history.back(); alert(\"Wrong cvv was given!\");</script>");
            }
            else if(Integer.parseInt(cardno4) > 9999 ||  Integer.parseInt(cardno4) < 0  ||Integer.parseInt(cardno2) > 9999 ||  Integer.parseInt(cardno2) < 0  || Integer.parseInt(cardno3) > 9999 ||  Integer.parseInt(cardno3) < 0  || Integer.parseInt(cardno1) > 9999 ||  Integer.parseInt(cardno1) < 0  || cardno1.length() !=4 || cardno2.length() !=4 || cardno3.length() !=4 || cardno4.length() !=4  ){
                out.println("<script language=\"javascript\"> history.back(); alert(\"Wrong card number was given!\");</script>");
            }
            else if( Integer.parseInt(year) < (d1.getYear()+1900)){
                out.println("<script language=\"javascript\"> history.back(); alert(\"Your card has already expired!\");</script>");
            }
            else if( Integer.parseInt(month) < 1 || Integer.parseInt(month) > 12){
                out.println("<script language=\"javascript\"> history.back(); alert(\"Wrong month was given!\");</script>");
            }
            else if(Integer.parseInt(year) == (d1.getYear()+1900) && Integer.parseInt(month) <= d1.getMonth()){
                out.println("<script language=\"javascript\"> history.back(); alert(\"Your card has already expired!\");</script>");
            }
            else if(register!=null){
                Buy();
            }
     } catch(NumberFormatException nFE){
                            out.println("<script language=\"javascript\"> alert('Wrong payment details (Complete with numeric values)'); history.back(); </script>");
                        }
 }
 else {


        Integer elegxos = 0;
        String register = null;

        cardcvv = "00";
        Integer mysqlok=-1;
        Integer one=0;
        price = (Integer)session.getAttribute("price");
        if(price==0)
            Buy();
        while(paramNames.hasMoreElements()) {
            one++;
            String paramName = (String)paramNames.nextElement();


            if  ( paramName.equals("register")   ){
                String[] paramValues5 = request.getParameterValues(paramName);
                register = paramValues5[0];
            }
            else if ( paramName.equals("cvv") ){
                String[] paramValues9 = request.getParameterValues(paramName);
                cardcvv = paramValues9[0];
            }

        }
        if(one==0)
            out.println("<script language=\"javascript\"> location=\"checkout.jsp\"; </script>");
        else if(  cardcvv.equals("")  && register!=null  ){
            out.println("<script language=\"javascript\"> history.back(); alert(\"Please complete your cvv!\");</script>");
        }
        else if(register!=null){
            Buy();
        }



 }
         
      


    }

    protected void Buy()
          throws ServletException, IOException {

     basket = new Mybasket(session);
    
  
   
    if(basket.size()==0){
        out.println("<script language=\"javascript\"> history.back(); alert(\"Your basket is empty - Nothing to do!\");</script>");
        return;
    }
    try {

       db = new Database();
       db.connect();
        if(session.getAttribute( "theName" )!= null){
            price = (Integer)session.getAttribute("price");
        if(price!=0){

            java.util.Date d1 = new java.util.Date();

           

            ResultSet rs2 = null;
            String query = "SELECT * FROM cards WHERE username=\"" + session.getAttribute( "theName" ) + "\"";
            rs2 = db.selectquery(query);
            rs2.next();

            String cardno = rs2.getString("card");
            String date =  rs2.getString("date");
            String cvv = rs2.getString("cvv");
            rs2.close();
            Integer index = date.indexOf('/');
            String mymonth = date.substring(0,index);
            String myyear = date.substring(index+1);
            if( Integer.parseInt(myyear) < (d1.getYear()+1900)){
                out.println("<script language=\"javascript\"> history.back(); alert(\"Your card has expired! Please replace your card\");</script>");
                return;
            }
            else if(Integer.parseInt(myyear) == (d1.getYear()+1900) && Integer.parseInt(mymonth) <= d1.getMonth()){
                out.println("<script language=\"javascript\"> history.back(); alert(\"Your card has expired! Please replace your card\");</script>");
                return;
            }
        if(!cardcvv.equals(cvv)){
            out.println("<script language=\"javascript\"> history.back(); alert(\"Wrong cvv!\");</script>");
            return;
        }


        }
        }
       
        for(Integer i=0; i<basket.size(); i++){

           
            ResultSet rs6 = null;
            
            String query6 = "SELECT * FROM files WHERE is_public=1 AND name=\""+ basket.mybasket(i,0)+"\" AND directory=\""+ basket.mybasket(i,1)+"\" AND username=\""+ basket.mybasket(i,2)+"\"";
            rs6 = db.selectquery(query6);

            if(rs6.next()){


            
            String query8="UPDATE files SET apodosi=apodosi+"+rs6.getInt("price")+" WHERE is_public=1 AND name=\""+ basket.mybasket(i,0)+"\" AND directory=\""+ basket.mybasket(i,1)+"\" AND username=\""+ basket.mybasket(i,2)+"\"";
            db.updatequery(query8);

            String query9="UPDATE users SET mymoney=mymoney+"+rs6.getInt("price")+" WHERE username=\""+ basket.mybasket(i,2)+"\"";
            db.updatequery(query9);


            }
            String query7="UPDATE files SET popularity=popularity+1 WHERE is_public=1 AND name=\""+ basket.mybasket(i,0)+"\" AND directory=\""+ basket.mybasket(i,1)+"\" AND username=\""+ basket.mybasket(i,2)+"\"";
            db.updatequery(query7);
            
           
            rs6.close();

        }
       
        if(price!=0)
            out.println("<script language=\"javascript\"> location=\"index.htm\"; alert(\"Transaction was successful! You 've just downloaded  "+ basket.size() +" files for "+ price +"$ \");</script>");
        else
            out.println("<script language=\"javascript\"> location=\"index.htm\"; alert(\"Transaction was successful! You 've just downloaded  "+ basket.size() +" files for free\");</script>");
        if(session.getAttribute( "theName" )== null){
               session.invalidate();
            }
        else {
            session.removeAttribute( "basket" );
        }
        
    }

    catch (SQLException e) {
      throw new ServletException("Servlet Could not display records.", e);
      }
      finally{
        db.close();
    }
  }

    @Override
  protected void doPost(HttpServletRequest request,
          HttpServletResponse response)
          throws ServletException, IOException {
      doGet(request, response);
  }


}
