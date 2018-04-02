/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package login;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import database.Database;
import java.util.Calendar;

/**
 *
 * @author johnfouf
 */
public class Card  implements java.io.Serializable{
    private Database db;
  protected Integer elegxos(String username){
       try {
       db = new Database();
       db.connect();
        ResultSet rs = null;
        ResultSet result = null;
        java.util.Date d1 = new java.util.Date();


            ResultSet rs2 = null;
            String query23 = "SELECT * FROM cards WHERE username=\"" + username + "\"";
            rs2 = db.selectquery(query23);
            rs2.next();

            String cardno = rs2.getString("card");
            String date =  rs2.getString("date");
            String cvv = rs2.getString("cvv");
            rs2.close();
            Integer index = date.indexOf('/');
            String mymonth = date.substring(0,index);
            String myyear = date.substring(index+1);
            if( Integer.parseInt(myyear) < (d1.getYear()+1900))
              return -1; 
            else if(Integer.parseInt(myyear) == (d1.getYear()+1900) && Integer.parseInt(mymonth) <= d1.getMonth())
              return -2; // 

            Calendar cal = Calendar.getInstance();
            if(Integer.parseInt(myyear) == (d1.getYear()+1900) && Integer.parseInt(mymonth) == d1.getMonth()+1 && cal.get(Calendar.DAY_OF_MONTH) >=27)
               return -3;// 

        rs2.close();
        }
        catch (ServletException ex) {
            Logger.getLogger(Card.class.getName()).log(Level.SEVERE, null, ex);
        }     catch (SQLException e) {
            try {
                throw new ServletException("Servlet Could not display records.", e);
            } catch (ServletException ex) {
                Logger.getLogger(Card.class.getName()).log(Level.SEVERE, null, ex);
            }
      }
     finally{
            try {
                db.close();
            } catch (ServletException ex) {
                Logger.getLogger(Card.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    return 0;
  }
}
