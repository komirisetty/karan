
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author nagesh
 */


public class ins extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String UPLOAD_DIRECTORY = "D:/upload";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String filenam = (String) request.getAttribute("filename");
       
        String act = (String) request.getAttribute("state");
        try {
            int i=0,j=0,m=0,n=0,o=0,p=0;
            String aup="iup";
            String adown="idown";
            String adele="idele";
            int r=0,s=0;
           // String co = "iup";
            String usid = null;
            int crate = 0;
            Class.forName("com.mysql.jdbc.Driver"");
     Connection con = DriverManager.getConnection("jdbc:mysql://localhost/nageshdb","root","nagesh");   
     Statement st = con.createStatement();
     ResultSet rsu = st.executeQuery("select * from usignid");
   while(rsu.next())
   {
       usid = rsu.getString("id");
   }
    ResultSet rsm = st.executeQuery("select * from ddr");
   while(rsm.next())
   {
       crate = rsm.getInt("rate");
   }
     ResultSet rs = st.executeQuery("select * from ins");
      File fils = new File("D:/upload/"+filenam+"");
         double fsd = fils.length();
     if(act.equals("iup"))
     {
        
         int nr=0;
          String md5hash = (String) request.getAttribute("md5h");
      while(rs.next())
      {
          String md5ds = rs.getString("md5checksum");
          if(md5hash.equals(md5ds))
          {
          i=i+1;
        
      }
          else
          {
                nr=nr+1;
          }
          
      }
      while(rs.next())
      {
          String filonam = rs.getString("filename");
          if(filenam.equals(filonam))
          {
          j=j+1;
      }
      }
      if(i == 0)
      {
          String faci = "ndup";
          String mess = "successfully uploaded";
       st.executeQuery("insert into ins values('"+ md5hash +"','"+ filenam +"','"+ usid +"')");  
       request.setAttribute("namef",filenam);
        request.setAttribute("astate",aup);
         request.setAttribute("fai",faci);
         request.setAttribute("stm",mess);
         request.setAttribute("fsiz", fsd);
        // out.println("<!DOCTYPE html>");
          //  out.println("<html>");
            //out.println("<head>");
            //out.println("<title>Servlet ins</title>");            
            //out.println("</head>");
            //out.println("<body>");
            //out.println("<h1>Servlet ins at " + nr + "</h1>");
            //out.println("<h2>uploaded file is: " + usid + "</h2>");
            //out.println("</body>");
            //out.println("</html>");
            File source = new File("D:/upload/"+filenam+"");
File dest = new File("C:/Users/nagesh/OneDrive/myupload/"+filenam+"");
    FileUtils.copyFile(source, dest);
      request.getRequestDispatcher("/dccc").forward(request, response);
      }
      else
      {
          if(crate > 50 )
          {
        if(j == 0)
        {
             String faci = "dup";
          st.executeQuery("insert into dins values('"+ filenam +"','"+ usid +"')");
           request.setAttribute("astate",aup);
         request.setAttribute("fai",faci);
         String mess = "successfully uploaded";
           request.setAttribute("stm",mess);
           request.setAttribute("fsiz", fsd);
          request.getRequestDispatcher("/dccc").forward(request, response);
          }
        else
        {
            String faci = "dup";
              request.setAttribute("astate",aup);
         request.setAttribute("fai",faci);
          String mess = "your uploaded file already existed";
           request.setAttribute("stm",mess);
         request.getRequestDispatcher("/dccc").forward(request, response);
         request.setAttribute("fsiz", fsd);
        }
      }
          else
          {
            String faci = "ndup";
          String mess = "successfully uploaded";
       st.executeQuery("insert into ins values('"+ md5hash +"','"+ filenam +"','"+ usid +"')");  
       request.setAttribute("namef",filenam);
        request.setAttribute("astate",aup);
         request.setAttribute("fai",faci);
         request.setAttribute("stm",mess);
         request.setAttribute("fsiz", fsd);
         File source = new File("D:/upload/"+filenam+"");
File dest = new File("C:/Users/nagesh/OneDrive/myupload/"+filenam+"");
    FileUtils.copyFile(source, dest);

       request.getRequestDispatcher("/dccc").forward(request, response);   
          }
      }
     }
     else
     {
         if(act.equals("idown"))
         {
          while(rs.next())
      {
          String useid = rs.getString("userid");
          if(usid.equals(useid))
          {
              n=n+1;
              String foname = rs.getString("filename");
          if(filenam.equals(foname))
          {
              m=m+1;
                
          }
          
      }
      } 
          ResultSet rsd = st.executeQuery("select * from dins");  
              while(rsd.next())
      {
          String useid = rsd.getString("userid");
          if(usid.equals(useid))
          {
              p=p+1;
              String foname = rsd.getString("filename");
          if(filenam.equals(foname))
          {
              o=o+1;
          }
          }
      }
              if(n == 0 && p==0)
              {
               String faci = "ndow";
              request.setAttribute("astate",adown);
         request.setAttribute("fai",faci);
          String mess = "down failed due to you are not upload any files";
           request.setAttribute("stm",mess);
           request.setAttribute("fsiz", fsd);
           request.setAttribute("namef",filenam);
           request.getRequestDispatcher("/dccc").forward(request, response);   
              }
              else
              {
                  if(m > 0)
                  {
                    request.setAttribute("namef",filenam);
               String faci = "dow";
              request.setAttribute("astate",adown);
         request.setAttribute("fai",faci);
          String mess = "successfully download";
           request.setAttribute("stm",mess);
           request.setAttribute("fsiz", fsd);
            request.getRequestDispatcher("/dccc").forward(request, response);  
                  }
                  else
                  {
                      if(o > 0)
                      {
                        o=o+1;
              request.setAttribute("namef",filenam);
              String faci = "dow";
              request.setAttribute("astate",adown);
         request.setAttribute("fai",faci);
          String mess = "successfully download";
           request.setAttribute("stm",mess);
           request.setAttribute("fsiz", fsd);
            request.getRequestDispatcher("/dccc").forward(request, response);     
                      }
                      else
                      {
                        String faci = "ndow";
              request.setAttribute("astate",adown);
         request.setAttribute("fai",faci);
          String mess = "no file existed with this name for download";
           request.setAttribute("stm",mess);
           request.setAttribute("fsiz", fsd);
           request.setAttribute("namef",filenam);
            request.getRequestDispatcher("/dccc").forward(request, response);   
                      }
                  }
              }
          
          
         }
         else
         {
              while(rs.next())
      {
          String useid = rs.getString("userid");
          if(usid.equals(useid))
          {
              n=n+1;
              String foname = rs.getString("filename");
          if(filenam.equals(foname))
          {
              m=m+1;
                
          }
          
      }
      } 
          ResultSet rsd = st.executeQuery("select * from dins");  
              while(rsd.next())
      {
          String useid = rsd.getString("userid");
          if(usid.equals(useid))
          {
              p=p+1;
              String foname = rsd.getString("filename");
          if(filenam.equals(foname))
          {
              o=o+1;
          }
          }
      }
              if(n == 0 && p==0)
              {
               String faci = "ndel";
              request.setAttribute("astate",adele);
         request.setAttribute("fai",faci);
          String mess = "you are not delete any files since you are not upload any files";
           request.setAttribute("stm",mess);
           request.setAttribute("fsiz", fsd);
           request.getRequestDispatcher("/dccc").forward(request, response);  
              }
              else
              {
                  if(m > 0)
                  {
                    request.setAttribute("namef",filenam);
              String faci = "del";
              request.setAttribute("astate",adele);
         request.setAttribute("fai",faci);
         st.executeQuery("UPDATE ins SET filename='Null',userid='Null' WHERE filename='"+ filenam +"'");
          String mess = "delete successfully";
           request.setAttribute("stm",mess);
           request.setAttribute("fsiz", fsd);
            request.getRequestDispatcher("/dccc").forward(request, response);    
                  }
                  else
                  {
                      if(o > 0)
                      {
                       request.setAttribute("namef",filenam);
              String faci = "del";
              request.setAttribute("astate",adele);
         request.setAttribute("fai",faci);
         st.executeQuery("UPDATE dins SET filename='Null',userid='Null' WHERE filename='"+ filenam +"'");
          String mess = "delete successfully";
           request.setAttribute("stm",mess);
           request.setAttribute("fsiz", fsd);
            request.getRequestDispatcher("/dccc").forward(request, response);  
                      }
                      else
                      {
                       String faci = "ndel";
              request.setAttribute("astate",adele);
         request.setAttribute("fai",faci);
          String mess = "no file excited with this name for delete";
           request.setAttribute("stm",mess);
           request.setAttribute("fsiz", fsd);
            request.getRequestDispatcher("/dccc").forward(request, response); 
                      }
                  }
              }
         
         }
           /*while(rs.next())
      {
          String useid = rs.getString("userid");
          if(usid.equals(useid))
          {
              n=n+1;
              String foname = rs.getString("filename");
          if(filenam.equals(foname))
          {
             
              m=m+1;
              r=r+1;
               
          }
          
      }
      } if(r > 0)
      {
       
      }
          if(m == 0 && n != 0)
          {
              String faci = "ndel";
              request.setAttribute("astate",adele);
         request.setAttribute("fai",faci);
          String mess = "no file exited with this name for delete";
           request.setAttribute("stm",mess);
            request.getRequestDispatcher("/datacenter").forward(request, response);
            //send to client from dc as no file existed with this name :: delete
            //send identification = non retreive file
          }
          else
          {
             ResultSet rsd = st.executeQuery("select * from dins");  
              while(rsd.next())
      {
          String useid = rsd.getString("userid");
          if(usid.equals(useid))
          {
              p=p+1;
              String foname = rs.getString("filename");
          if(filenam.equals(foname))
          {
              o=o+1;
              s=s+1;
               
          }
          
      }
      } 
              if(s > 0)
              {
                 
              }
               if(o == 0 && p != 0)
          {
              
            //send to client from dc as no file existed with this name :: delete
            
            //send identification = non retreive file
          }
          else
          {
              
            //send to client from dc as you are not upload any files in dc
            //send identification = non retreive file   
          }
          }
          
            
         
         }
     }
      
      //st.executeQuery("delete usignid");
            /* TODO output your page here. You may use following sample code. */
           // String filenam = (String) request.getAttribute("fname");
            /* String FILENAME = "D:/upload/"+filenam+"";
             File filel = new File(FILENAME);
             double bytes = filel.length();
             String bytex = Double.toString(bytes);
             String md5c = MD5(FILENAME);
            // BufferedReader br = new BufferedReader(new FileReader(FILENAME));
            // String sCurrentLine;
             
                      ResultSet rss = st.executeQuery("select * from ins");
                     
                      while(rss.next())
                      {
                          String dmd5 = rss.getString("md5checksum");
                          String dfname = rss.getString("filename");
                          String duid = rss.getString("userid");
                          if(md5c != dmd5)
                          {
                               ResultSet rsm = st.executeQuery("select * from dcount");
                              while(rsm.next())
                              {
                                  int dc1 = rsm.getInt("DC_1");
                                  int dc2 = rsm.getInt("DC_2");
                                  int dc3 = rsm.getInt("DC_3");
                                  int dc4 = rsm.getInt("DC_4");
                                  if(dc1==dc2 || dc1<dc2)
                                  {
                                      mdc = "dc_1";
                                      st.executeQuery("update dcount set DC_1=DC_1+1");
                                  }
                                  else
                                  {
                                      if(dc2==dc3 || dc2<dc3)
                                      {
                                          mdc="dc_2";
                                          st.executeQuery("update dcount set DC_2=DC_2+1");
                                      }
                                      else
                                      {
                                        if(dc3==dc4 || dc3<dc4)
                                      {
                                          mdc="dc_3";
                                          st.executeQuery("update dcount set DC_3=DC_3+1");
                                      } 
                                        else
                                        {
                                            mdc="dc_4";
                                            st.executeQuery("update dcount set DC_4=DC_4+1");
                                        }
                                      }
                                  }
                              }
                              String dc= mdc;//predicted datacenter
                              st.executeQuery("insert into ins values('"+ md5c +"','"+ filenam +"','"+ uid +"','"+ dc +"')");
                              request.setAttribute("coun",co);
                              request.setAttribute("dac",dc);
                              request.setAttribute("fn",filenam);
                              request.setAttribute("fsiz",bytex);
                              request.getRequestDispatcher("/datacenter").forward(request, response);
                           //1.predict datacenter
                            //2.stored in datacenters
                           //3.update the ins tablem
                            //4.success to client       
                          }
                          else
                          {
                              if(uid != duid)
                              {
                               st.executeQuery("insert into dins values('"+ md5c +"','"+ filenam +"','"+ uid +"')"); 
                               String mes="upload successfully";
                               request.getSession().setAttribute("mes", mes);
                               response.sendRedirect("uaccount.jsp");  
                              }
                              else
                              {
                                  if(filenam != dfname)
                                  {
                                   st.executeQuery("insert into dins values('"+ md5c +"','"+ filenam +"','"+ uid +"')"); 
                                   String mes="you are already upload the same content with another file name";
                               request.getSession().setAttribute("mes", mes);
                               response.sendRedirect("uaccount.jsp"); 
                                  }
                                  else
                                  {
                                      String mes="this file already existed";
                               request.getSession().setAttribute("mes", mes);
                               response.sendRedirect("uaccount.jsp"); 
                                      //this file already existed and come back to account page
                                  }
                              }
                          }*/
                      }
 
          /*  out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ins</title>");            
            out.println("</head>");
            out.println("<body>"); 
            out.println("<h1>Servlet ins at " + request.getContextPath() + "</h1>");
            out.println("<h2>uploaded file is: " + filenam + "</h2>");
            //while ((sCurrentLine = br.readLine()) != null)
            //{
           // out.println("<p>" + sCurrentLine + "</p>");
           // }
            //out.println("<p> MD5 checksum:" + MD5(FILENAME) + "<p>");
            out.println("</body>");
            out.println("</html>");*/
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ins.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ins.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ins.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ins.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ins.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ins.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
