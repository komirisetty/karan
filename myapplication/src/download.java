
import java.io.*;  
import javax.servlet.ServletException;  
import javax.servlet.http.*; 
import java.sql.*;
  
public class download extends HttpServlet {  
    private static final long serialVersionUID = 1L;
  
@Override
public void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException { 
    String finame = request.getParameter("fname");
  
response.setContentType("text/html");  
PrintWriter out = response.getWriter(); 
String co = "idown";
//String pdc = null;
    try {
        request.setAttribute("state",co);
                              
                              request.setAttribute("filename",finame);
                              request.getRequestDispatcher("/ins").forward(request, response);

        /* Class.forName("com.mysql.jdbc.Driver");
     Connection con = DriverManager.getConnection("jdbc:mysql://localhost/nageshdb","root","nagesh");   
     Statement st = con.createStatement();
     //ResultSet rs = st.executeQuery("select * from usignid");
     ResultSet rs = st.executeQuery("select * from ins");
     //ResultSet rsr = st.executeQuery("select * from dins");
     while(rs.next())
     {
         String dfn = rs.getString("filename");
         if(finame.equals(dfn))
         {
             pdc = rs.getString("datacenter"); 
         }
     }
     request.setAttribute("dac",pdc);
     request.getRequestDispatcher("/datacenter").forward(request, response);
String filepath = "D:/upload/";   
//request.setAttribute("fname", finame);
response.setContentType("APPLICATION/OCTET-STREAM");   
response.setHeader("Content-Disposition","attachment; filename=\"" + finame + "\"");   
  
FileInputStream fileInputStream = new FileInputStream(filepath + finame);  
            
int i;   
while ((i=fileInputStream.read()) != -1) {  
out.write(i);   
} 

fileInputStream.close();   
out.close(); */ 
    }
    catch(Exception e)
    {
        System.out.println(e);
    }
 
}  
  
}  
