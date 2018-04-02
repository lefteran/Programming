
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;


public class Database  {
   private PrintWriter out;
   private Connection con;
   Statement statement;
   ResultSet rs;
   String root;
   String pass;

public  void connect() throws ServletException {
    con = null;
    root = "root";
    pass = "121084";
   
    try {
        Class.forName("com.mysql.jdbc.Driver");
        con =DriverManager.getConnection ("jdbc:mysql://127.0.0.1/storage_cloud?requireSSL=false&useUnicode=true&characterEncoding=utf8&connectionCollation=utf8_general_ci",root, pass);
                         
    }
    catch (SQLException e) {
                throw new ServletException("Servlet Could not display records.", e);
            }
            catch (ClassNotFoundException e) {
                throw new ServletException("JDBC Driver not found.", e);
            }
      }


public void updatequery(String query) throws ServletException {

    try {
          statement = con.createStatement();

                    statement.executeUpdate(query);


       

    }
    catch (SQLException e) {
                throw new ServletException("Servlet Could not display records.", e);
            }

      }



public ResultSet selectquery(String query) throws ServletException {
 try{

     
          statement = con.createStatement();

                  
                    rs = statement.executeQuery(query);


          return rs;
    }
    catch (SQLException e) {
                throw new ServletException("Servlet Could not display records.", e);
            }

      }
public void close() throws ServletException {
        try {
            this.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
}


}
