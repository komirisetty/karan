import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;

/**
 *
 * @author nagesh
 */


public class uregistration extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        int i=0;
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String Name = request.getParameter("name");
        String Id = request.getParameter("id");
        String Pwd = request.getParameter("pwd");
        String Cpwd = request.getParameter("cpwd");
        String Pnumber = request.getParameter("pnumber");
        String Email = request.getParameter("email");
        String Dob = request.getParameter("dob");
        String Country = request.getParameter("country");
        String Msex = request.getParameter("msex");
        String Fsex = request.getParameter("fsex");
        String En = request.getParameter("en");
        String Nonen = request.getParameter("nonen");
        try {
            /* TODO output your page here. You may use following sample code. */
            Class.forName("com.mysql.jdbc.Driver");
     Connection con = DriverManager.getConnection("jdbc:mysql://localhost/nageshdb","root","nagesh");   
     Statement st = con.createStatement();
     ResultSet rs = st.executeQuery("select * from regd");
     while(rs.next())
     {
         String did = rs.getString("id");
         if(Id.equals(did))
         {
             i=i+1;
             
         }
     }
     if(i>0)
     {
         String message = "This ID is already existed please choose another";
request.getSession().setAttribute("message", message);
response.sendRedirect("usignup.jsp");
     }
     else
     {
         if(!(Pwd.equals(Cpwd)))
         {
 String message = "the password did not match";
             request.getSession().setAttribute("message", message);
response.sendRedirect("usignup.jsp"); 
         }
         else
         {
             if(Pnumber.length() > 10 || Pnumber.length() < 10)
             {
                 String message = "mobile number is wrong please re enter";
                 request.getSession().setAttribute("message", message);
                 response.sendRedirect("usignup.jsp");   
             }
             else
             {
                 String is = "insert into regd values('"+Id+"','"+Name+"','"+Pwd+"','"+Pnumber+"','"+Email+"','"+Dob+"','"+Country+"','"+Msex+"','"+Fsex+"','"+En+"','"+Nonen+"')";
                 st.executeQuery(is);
                 String mess = "registration completed success fully";
                 request.getSession().setAttribute("mess", mess);
                 response.sendRedirect("index.jsp");
                 
             }
         }
     }
     }
            
            
        finally {
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(uregistration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(uregistration.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(uregistration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(uregistration.class.getName()).log(Level.SEVERE, null, ex);
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
