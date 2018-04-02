/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.sql.*;
import database.Database;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
/**
 *
 * @author johnfouf
 */
public class loginBean implements java.io.Serializable
{
   Database db;

    public UserBean getUser(String username, String passwd)
    {

       db = new Database();
        try {
            db.connect();
        } catch (ServletException ex) {
            Logger.getLogger(loginBean.class.getName()).log(Level.SEVERE, null, ex);
        }

       ResultSet rs = null;
       UserBean user = new UserBean();
       try
       {

           String sql = "select * from users where "+
                                  "username='"+username+"' and "+
                                  "password='"+passwd+"'";
           rs = db.selectquery(sql);
           user.setUsername(null);
           user.setPassword(null);
           while(rs.next())
           {
               user.setUsername(rs.getString(1));
               user.setPassword(rs.getString(2));
           }
       }
       catch(SQLException e)
       {
           System.err.println("SQLException: "+e.getMessage());
       }
       finally
       {
           try
           {
               if(rs!=null)
                   rs.close();
             
           }
           catch(Exception e)
           {}
           return user;
       }
   }
}