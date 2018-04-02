/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;
import database.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
/**
 *
 * @author johnfouf
 */
public class RegisterBean {
     Database db;

    public Integer regUser(RegBean user)
    {

       db = new Database();
        try {
            db.connect();
        } catch (ServletException ex) {
            Logger.getLogger(loginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        Integer mysqlok=-1;
        Integer mysqluser=-1;
        Integer mysqlemail=-1;
        ResultSet rs;
        try {

        String query1 = "SELECT COUNT(*) FROM users WHERE username=\""+user.getUsername()+"\"";
        rs = db.selectquery(query1);
        rs.next();
        if(rs.getString("COUNT(*)").equals("1")){
            mysqluser=1;             //xristis uparxei hdh
            rs.close();
            //
            return -1;
        }
        ResultSet rs2 = null;

        String query6 = "SELECT COUNT(*) FROM users WHERE email=\""+user.getEmail()+"\"";
        rs2 = db.selectquery(query6);
        rs2.next();
        if(rs2.getString("COUNT(*)").equals("1")){
            mysqlemail=1;             //email uparxei hdh
            rs2.close();
           
            return -2;
        }

        if(mysqlemail!=1 && mysqluser!=1){                   //swsti periptwsi
            rs.close();
            rs2.close();

            java.util.Date d1 = new java.util.Date();


            String query = "INSERT INTO users VALUES(\""+user.getUsername()+"\",\""+ user.getName()  +"\",\""+ user.getSurname() +"\",\""+ user.getEmail()  +"\",\""+ user.getPassword() +"\",0,\""+d1.getTime()+"\",0,0)";
            mysqlok=1;
            db.updatequery(query);

            String query2 = "INSERT INTO cards VALUES(\""+user.getCardno()+"\",\""+ user.getDate()  +"\",\""+ user.getCvv() +"\",\""+ user.getUsername()  +"\")";
            mysqlok=1;
            db.updatequery(query2);


            String query7 = "INSERT INTO directories VALUES(\""+"home"+"\",\""+ user.getUsername()  +"\")";
            db.updatequery(query7);


            long space = (long)(user.getCapacity() * 1024 * 1024);
            String query8 = "INSERT INTO space VALUES("+space+",0,\""+user.getUsername()+"\",0,0)";
            db.updatequery(query8);
           
        }
    }
        catch (ServletException ex) {
            Logger.getLogger(RegisterBean.class.getName()).log(Level.SEVERE, null, ex);
        }     catch (SQLException e) {
            try {
                throw new ServletException("Servlet Could not display records.", e);
            } catch (ServletException ex) {
                Logger.getLogger(RegisterBean.class.getName()).log(Level.SEVERE, null, ex);
            }
      }  finally{
            try {
                db.close();
            } catch (ServletException ex) {
                Logger.getLogger(RegisterBean.class.getName()).log(Level.SEVERE, null, ex);
            }
    }


      return 0;
   }

}
