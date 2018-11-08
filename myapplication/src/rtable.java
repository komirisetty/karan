/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nagesh
 */
public class rtable extends HttpServlet {

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
            int dda[] = new int[20];
            int ttf[] = new int[20];
            int dtf[] = new int[20];
            float lat[] = new float[20];
            int cu=0;
             Class.forName("com.mysql.jdbc.Driver");
     Connection con = DriverManager.getConnection("jdbc:mysql://localhost/nageshdb","root","nagesh");   
     Statement st = con.createStatement();
      ResultSet rsl = st.executeQuery("select * from mycsr");
       while(rsl.next())
     {
       int da = rsl.getInt("ddrate");
       float dl = rsl.getFloat("latency");
       int tf = rsl.getInt("tfiles");
       int df = rsl.getInt("dfiles");
       dda[cu] = da;
       ttf[cu] = tf;
       //dtf[cu] = df;
       lat[cu] = dl;
       cu=cu+1;
     }
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet rtable</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>List of Latency values with respect to De_duplication rate</h1>");
            out.println("<table>");
            out.println("<tr>");
             out.println("<th>tfiles</th>");
            //out.println("<th>dfiles</th>");
            out.println("<th>ddr</th>");
            out.println("<th>latency</th>");
            out.println("</tr>");
            for(int m=0;m<20;m++)
            {
                if(dda[m] != 0)
                {
                   out.println("<tr>");
                   out.println("<td>" + ttf[m] +"</td>");
            //out.println("<td>"+dtf[m]+"</td>");
            out.println("<td>" + dda[m] +"</td>");
            out.println("<td>"+lat[m]+"</td>");
            out.println("</tr>"); 
                }
            }
            out.println("</table>");
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
            Logger.getLogger(rtable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(rtable.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(rtable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(rtable.class.getName()).log(Level.SEVERE, null, ex);
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
