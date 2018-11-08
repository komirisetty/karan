
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nagesh
 */


public class delete extends HttpServlet {

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
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String finame = request.getParameter("fname");
        //String pdc = null;
        String co="idele";
       /* Class.forName("com.mysql.jdbc.Driver");
     Connection con = DriverManager.getConnection("jdbc:mysql://localhost/nageshdb","root","nagesh");   
     Statement st = con.createStatement();
     String pc="deleted sucessfully";
     //ResultSet rs = st.executeQuery("select * from usignid");
     ResultSet rs = st.executeQuery("select * from ins");
      while(rs.next())
     {
         String dfn = rs.getString("filename");
         if(finame.equals(dfn))
         {
             pdc = rs.getString("datacenter"); 
         }
         st.executeQuery("update ins set filename='null' where filename='pdc' ");
     }
        request.setAttribute("dac",pdc);
        request.setAttribute("fn",finame);
        request.setAttribute("coun",co);
        request.setAttribute("dele",pc);
        request.getRequestDispatcher("uaccount.jsp").forward(request, response);
        File file = new File("D:/upload/"+finame+"");*/
        try {
            request.setAttribute("state",co);
                              
                              request.setAttribute("filename",finame);
                              request.getRequestDispatcher("/ins").forward(request, response);

           // file.delete();
            /* TODO output your page here. You may use following sample code. */
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
        } catch (SQLException ex) {
            Logger.getLogger(delete.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(delete.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(delete.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(delete.class.getName()).log(Level.SEVERE, null, ex);
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
