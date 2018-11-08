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


public class adaccount extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            int i=0,j=0,h = 0,dh;
            float bl = 0;
            //float sl = k*92;
            /* TODO output your page here. You may use following sample code. */
            Class.forName("com.mysql.jdbc.Driver");
     Connection con = DriverManager.getConnection("jdbc:mysql://localhost/nageshdb","root","nagesh");   
     Statement st = con.createStatement();
     
     ResultSet rsr = st.executeQuery("select * from ins");
     
      while(rsr.next())
     {
         j=j+1;
     }
      ResultSet rsy = st.executeQuery("select * from dins");
       while(rsy.next())
     {
         i=i+1;
     }
       ResultSet rsz = st.executeQuery("select * from ddr");
       while(rsz.next())
     {
       h = rsz.getInt("rate");
     }
        ResultSet rsl = st.executeQuery("select * from csr");
       while(rsl.next())
     {
                dh = rsl.getInt("ddrate");
                if(h == dh)
                {
                 bl = rsl.getFloat("latency");
                }
     }
      
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet adaccount</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>INS CLOUD STORAGE DE-DUPLICATION SYSTEM</h1><br/><br/><br/>");
             out.println("<h2>total files:" +(i+j)+ "</h2><br/><br/>");
             out.println("<h2> Duplicate files :" +i+ "</h2><br/><br/>");
             out.println("<h2>current De_duplication rate:" +h+ "</h2><br/><br/>");
             out.println("<h2> Latency :" +bl+ "</h2><br/><br/>");
            out.println("<a href=\"setdd.jsp\">Set De_duplication rate</a><br/><br/><br/>");
            out.println("<a href=\"rtable\">Get list : De_duplication and Latency</a><br/><br/><br/>");
            out.println("<a href=\"rgraph\">Results plotted on the graph</a><br/><br/><br/>");
            
            out.println("</body>");
            out.println("</html>");
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(adaccount.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(adaccount.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(adaccount.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(adaccount.class.getName()).log(Level.SEVERE, null, ex);
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
