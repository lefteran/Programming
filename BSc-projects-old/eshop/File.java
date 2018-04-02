package myspace;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.*;
import javax.servlet.*;
import database.*;



/**
 *
 * @author johnfouf
 */
public class File implements java.io.Serializable{
   private
           String dir;
           String user;
           String filename;
           Integer curaccess;

           
           protected void setdir(String directory){
               this.dir = directory;
           }

           protected void setuser(String username){
               this.user = username;
           }

            protected void setfilename(String file){
               this.filename = file;
           }
            protected void setcuraccess(Integer curaccess){
               this.curaccess = curaccess;
           }

   protected Integer changecuraccess(Database db)
    throws ServletException, IOException {
           

    try {


            java.util.Date d1 = new java.util.Date();
            
                if(curaccess==0){




            ResultSet rs2 = null;
            String query45 = "SELECT * FROM cards WHERE username=\"" + user + "\"";
            rs2 = db.selectquery(query45);
            rs2.next();

            String cardno = rs2.getString("card");
            String date =  rs2.getString("date");
            String cvv = rs2.getString("cvv");
            rs2.close();
            Integer index = date.indexOf('/');
            String mymonth = date.substring(0,index);
            String myyear = date.substring(index+1);
            if( Integer.parseInt(myyear) < (d1.getYear()+1900)){
                
                return -1;

            }
            else if(Integer.parseInt(myyear) == (d1.getYear()+1900) && Integer.parseInt(mymonth) <= d1.getMonth()){
                
                return -1;
            }
            else{

                    String query = "update files set is_public=1 , wra=\""+d1.getTime()+"\" where directory=\"" + dir +"\" and username=\"" + user +"\"and name=\"" + filename +"\"";
                    db.updatequery(query);

                }
                }
                else if(curaccess==1){

                    String query = "update files set is_public=0 where directory=\"" + dir +"\" and username=\"" + user +"\"and name=\"" + filename +"\"";
                    db.updatequery(query);

                }


                String query15 = "SELECT SUM(size) FROM files WHERE is_public=1 and username=\""+user+"\" ";
                ResultSet result15 = db.selectquery(query15);
                result15.next();
                long public_size=0;

                if(result15.getString("SUM(size)")!=null){
                    public_size = Integer.parseInt(result15.getString("SUM(size)"));
                }

                result15.close();


                String query16 = "UPDATE space SET public_size="+public_size+" WHERE username=\""+user+"\" ";
                db.updatequery(query16);


            
        
            return 0;
        }

   catch (SQLException e) {
      throw new ServletException("Servlet Could not display records.", e);
      }

    }

   protected void deleteFile( Database db )
    throws ServletException, IOException {
     try {


                if(!filename.equals("")){

                    String query = "delete from files where directory=\"" + dir +"\" and username=\"" + user +"\"and name=\"" + filename +"\"";
                    db.updatequery(query);
                }
                 else if(!dir.equals("") && filename.equals("")){

                    String query = "delete from directories where name=\"" + dir +"\" and username=\"" + user +"\"";
                    db.updatequery(query);
                }

                String query15 = "SELECT SUM(size) FROM files WHERE username=\""+user+"\" ";
                ResultSet result15 = db.selectquery(query15);
                result15.next();
                long size=0;

                if(result15.getString("SUM(size)")!=null){
                    size = Integer.parseInt(result15.getString("SUM(size)"));
                }



                String query16 = "UPDATE space SET current="+size+" WHERE username=\""+user+"\" ";
                db.updatequery(query16);


                String query17 = "SELECT SUM(size) FROM files WHERE is_public=1 and username=\""+user+"\" ";
                ResultSet result17 = db.selectquery(query17);
                result17.next();
                long public_size=0;
                if(result17.getString("SUM(size)")!=null){
                    public_size = Integer.parseInt(result17.getString("SUM(size)"));
                }



                String query18 = "UPDATE space SET public_size="+public_size+" WHERE username=\""+user+"\" ";
                db.updatequery(query18);



     }
     catch (SQLException e) {
      throw new ServletException("Servlet Could not display records.", e);
     }

   }
}
