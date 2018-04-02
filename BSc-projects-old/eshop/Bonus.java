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


/**
 *
 * @author johnfouf
 */
public class Bonus  implements java.io.Serializable {
    protected int mybonus;
    protected Database db;
    protected int setbonus(String username){

        try{
            db = new Database();
            db.connect();
            java.util.Date d1 = new java.util.Date();
            long wra_twra = d1.getTime();

            ResultSet rs = null;
            String query = "SELECT * FROM space,users WHERE users.username=space.username AND users.username=\"" + username + "\"";
            rs = db.selectquery(query);
            rs.next();
            long wra_eggrafis = rs.getLong("wra");
            long bonus = rs.getInt("bonus");

            long bonus2 = (wra_twra-wra_eggrafis)/(24*60*60*1000);
            rs.close();
            if(bonus2!=bonus){
                String query15 = "SELECT * FROM space WHERE username=\""+username+"\" ";
                ResultSet result15 = db.selectquery(query15);
                result15.next();
                long limit = Integer.parseInt(result15.getString("mylimit"));
                limit = limit+((bonus2-bonus)*1024);
                result15.close();
                String query16 = "UPDATE space SET mylimit="+limit+" WHERE username=\""+username+"\" ";
                db.updatequery(query16);
                String query18 = "UPDATE space SET bonus="+bonus2+" WHERE username=\""+username+"\" ";
                db.updatequery(query18);

            }


            String query22 = "SELECT * FROM space WHERE username=\""+username+"\" ";
            ResultSet result22 = db.selectquery(query22);
            result22.next();
            long xwros = Integer.parseInt(result22.getString("mylimit")) - Integer.parseInt(result22.getString("current"));

            if(xwros<=50*1024){
                xwros=xwros/1024;
                rs.close();
                return (int)xwros;
            }
            rs.close();

            
            
        }
        catch (ServletException ex) {
            Logger.getLogger(Bonus.class.getName()).log(Level.SEVERE, null, ex);
        }


     catch (SQLException e) {
            try {
                throw new ServletException("Servlet Could not display records.", e);
            } catch (ServletException ex) {
                Logger.getLogger(Bonus.class.getName()).log(Level.SEVERE, null, ex);
            }
      }
     finally{
            try {
                db.close();
                return 0;
            } catch (ServletException ex) {
                Logger.getLogger(Bonus.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
     return 0;
    }

}
