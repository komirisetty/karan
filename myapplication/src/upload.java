
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
 
/**
13
 * Servlet to handle File upload request from Client
14
 * @author Javin Paul
15
 */
public class upload extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final String UPLOAD_DIRECTORY = "D:/upload";
    private Object item;
    String name;
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //process only if its multipart content
        if(ServletFileUpload.isMultipartContent(request)){
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                                         new DiskFileItemFactory()).parseRequest(request);
               
                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        name = new File(item.getName()).getName();
                        item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
                       request.setAttribute("fname",name);
                    }
                }
               // String fname;
             //fname = name;
               //File uploaded successfully
                String FILENAME = "D:/upload/"+name+"";
                String md5c = MD5(FILENAME);
                String sta = "iup";
              request.setAttribute("filename",name);
              request.setAttribute("md5h",md5c);
              request.setAttribute("state",sta);
               request.getRequestDispatcher("/ins").forward(request, response);  
               //out.println("<h1>Servlet datacenter at " + cam + "</h1>");
            /*   out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet upload at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");*/
            } catch (Exception ex) {
               request.setAttribute("message", "File Upload Failed due to " + ex);
               request.getRequestDispatcher("uaccount.jsp").forward(request, response);
            }         
          
        }else{
            request.setAttribute("message",
                                 "Sorry this Servlet only handles file upload request");
            request.getRequestDispatcher("uaccount.jsp").forward(request, response);
        }
     
      
    }
    public String MD5(String finame) throws NoSuchAlgorithmException, FileNotFoundException, IOException
    {
        MessageDigest md = MessageDigest.getInstance("MD5");
        FileInputStream fis = new FileInputStream(finame);

        byte[] dataBytes = new byte[1024];

        int nread = 0;
        while ((nread = fis.read(dataBytes)) != -1) {
          md.update(dataBytes, 0, nread);
        }
        byte[] mdbytes = md.digest();

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mdbytes.length; i++) {
          sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

   
}
