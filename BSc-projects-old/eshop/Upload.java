package upload;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.FileUploadBase.*;
import database.Database;

public class Upload extends HttpServlet {
     private
        HttpSession session;
        PrintWriter out;
        Database db;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
            session = request.getSession(true);
            System.out.println("<meta http-equiv=\"Content-Type\" content=\"text/html\" charset=\"UTF-8\" />");
            System.out.println("<%@page contentType=\"text/html\" pageEncoding=\"UTF-8\"%>");
            response.setContentType("text/html");
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            out = response.getWriter();

            if(session.getAttribute( "theName" )== null){
                out.println("<script language=\"javascript\"> location=\"login.jsp\"; </script>");
                return;
            }


            Integer mistake=-1;
            Integer automata = -1;
            Integer nospace=0;
            String folder = null;

            if (ServletFileUpload.isMultipartContent(request)){
                request.setCharacterEncoding("UTF-8");
                ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
                try{
                    List fileItemsList = servletFileUpload.parseRequest(request);
                    String optionalFileName = "";
                    FileItem fileItem = null;
                    Iterator it = fileItemsList.iterator();

                    while (it.hasNext()){
                        FileItem fileItemTemp = (FileItem)it.next();
                        if (fileItemTemp.isFormField()){
                            if(fileItemTemp.getFieldName().equals("automata")){
                                automata = 1;
                            }
                            if(fileItemTemp.getFieldName().equals("folder")){
                                folder = fileItemTemp.getString();
                            }
                            if (fileItemTemp.getFieldName().equals("filename"))
                                optionalFileName = fileItemTemp.getString();
                               
                            }
                        else
                            fileItem = fileItemTemp;
                    }
                    if(automata!=1){
                        if(folder.equals("")){
                            folder = "home";
                        }
                    }

                    if (fileItem.getSize()!=0){
                       Uploading(fileItem,automata,folder);
                }
                else{
                    out.println("<script language=\"javascript\"> history.back(); alert(\"You didn't choose a file or your file is empty!\");</script>");
                }
            }
            catch(FileUploadException ex){
                throw new ServletException("Servlet Could not display records.", ex);
            }
        }
        else
            out.println("<script language=\"javascript\"> location=\"upload.jsp\"; </script>");

    }

    
  protected void Uploading(FileItem fileItem,Integer automata,String folder )
          throws ServletException, IOException {
                        Integer mysqlok=0;
                        Integer mysqlfile;
                        String fileName = fileItem.getName();
                         if(fileName.length()>100){
                                    out.println("<script language=\"javascript\"> history.back(); alert(\"Too big file name!\");</script>");
                                    return;
                                }
                        long fileSize = fileItem.getSize();
                     
                     
                        try {
                            db = new Database();
                            db.connect();
                            

                            ResultSet rs = null;
                         
                            String query17 = "SELECT * FROM space WHERE username=\""+session.getAttribute( "theName" )+"\" ";
                            ResultSet result17 = db.selectquery(query17);
                            result17.next();
                            long current2 = Integer.parseInt(result17.getString("current"));
                            long mylimit = Integer.parseInt(result17.getString("mylimit"));
                            if((mylimit-current2)<(fileSize/1024)){
                                out.println("<script language=\"javascript\"> alert(\"Not enough space to upload this file! You may buy some space\");history.back();</script>");
                                result17.close();
                            }
                            else{
                                if(automata==1){
                                Integer index = fileName.lastIndexOf('.');
                                String filetype = fileName.substring(index+1);

                               
                                String query9 = "SELECT COUNT(*) FROM directories WHERE username=\""+session.getAttribute( "theName" )+"\" AND name=\""+filetype+"\"";
                                ResultSet newresult = db.selectquery(query9);
                                newresult.next();
                                if(newresult.getString("COUNT(*)").equals("1")){
                                    folder = filetype;
                                    
                                    String query12 = "SELECT COUNT(*) FROM files WHERE username=\""+session.getAttribute( "theName" )+"\" AND name=\""+fileName+"\" AND directory=\""+folder+"\"";
                                    ResultSet result12 = db.selectquery(query12);
                                    result12.next();

                                    if(result12.getString("COUNT(*)").equals("1")){
                                        mysqlfile=1;             //arxeio uparxei hdh
                                        out.println("<script language=\"javascript\"> history.back(); alert(\"File already exists!!\");</script>");
                                        result12.close();
                                    }
                                    else{
                                        
                                        mysqlok=1;
                                        String query13 = "INSERT INTO files VALUES(\""+ fileName +"\","+  fileSize/1024 +","+ "0" +","+ "0"  +","+ "0" +",\""+folder+"\",\""+session.getAttribute( "theName" )+"\",0,0)";
                                        db.updatequery(query13);
                                        out.println("<script language=\"javascript\"> history.back(); alert(\"Upload succeded!\");</script>");
                                    }
// eisodos me folder=filetype kai me
                                    newresult.close();
                                    }
                                else{
                                    folder = "home";
                                    
                                    String query11 = "SELECT COUNT(*) FROM files WHERE username=\""+session.getAttribute( "theName" )+"\" AND name=\""+fileName+"\" AND directory=\""+folder+"\"";
                                    ResultSet result5 = db.selectquery(query11);
                                    result5.next();
                                    if(result5.getString("COUNT(*)").equals("1")){
                                        mysqlfile=1;             //arxeio uparxei hdh
                                        out.println("<script language=\"javascript\"> history.back(); alert(\"File already exists!!\");</script>");
                                        result5.close();
                                    }
                                    else{
                                        mysqlok=1;
                                        
                                        String query8 = "INSERT INTO files VALUES(\""+ fileName +"\","+  fileSize/1024 +","+ "0" +","+ "0"  +","+ "0" +",\""+folder+"\",\""+session.getAttribute( "theName" )+"\",0,0)";
                                        db.updatequery(query8);
                                        out.println("<script language=\"javascript\"> history.back(); alert(\"Upload succeded!\");</script>");
                                    }
                                }
                            }
                            else{
                                
                                String query1 = "SELECT COUNT(*) FROM files WHERE username=\""+session.getAttribute( "theName" )+"\" AND name=\""+fileName+"\" AND directory=\""+folder+"\"";
                                ResultSet result = db.selectquery(query1);
                                result.next();
                                if(result.getString("COUNT(*)").equals("1")){
                                    mysqlfile=1;             //arxeio uparxei hdh
                                    result.close();
                                    out.println("<script language=\"javascript\"> history.back(); alert(\"File already exists!\");</script>");
                                }
                                else{
                                    mysqlok=1;
                                    
                                    String query7 = "INSERT INTO files VALUES(\""+ fileName +"\","+  fileSize/1024 +","+ "0" +","+ "0"  +","+ "0" +",\""+folder+"\",\""+session.getAttribute( "theName" )+"\",0,0)";
                                    db.updatequery(query7);
                                    out.println("<script language=\"javascript\"> history.back(); alert(\"Upload succeded!\");</script>");
                                }
                            }
                            if(mysqlok==1){
                                
                                String query15 = "SELECT SUM(size) FROM files WHERE username=\""+session.getAttribute( "theName" )+"\" ";
                                ResultSet result15 = db.selectquery(query15);
                                result15.next();
                                long size = Integer.parseInt(result15.getString("SUM(size)"));
                                result15.close();
                        
                                
                                String query16 = "UPDATE space SET current="+size+" WHERE username=\""+session.getAttribute( "theName" )+"\" ";
                                db.updatequery(query16);
                            }
                        }
                    }
                    catch (SQLException e) {
                        throw new ServletException("Servlet Could not display records.", e);
                    } finally{
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
