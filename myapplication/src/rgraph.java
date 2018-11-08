/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 *
 * @author nagesh
 */
public class rgraph extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * 
     */
    int[] d0 = {0,1,2,3,4,5};
    int[] d50 = {0,2,4,6,8,10};
    int[] d100 = {0,3,6,9,12,15};
    
    final int PAD = 20;
    protected void paintComponent(Graphics g) {
        //super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth();
        int h = getHeight();
        // Draw ordinate.
        g2.draw(new Line2D.Double(PAD, PAD, PAD, h-PAD));
        // Draw abcissa.
        g2.draw(new Line2D.Double(PAD, h-PAD, w-PAD, h-PAD));
        // Draw labels.
        Font font = g2.getFont();
        FontRenderContext frc = g2.getFontRenderContext();
        LineMetrics lm = font.getLineMetrics("0", frc);
        float sh = lm.getAscent() + lm.getDescent();
        // Ordinate label.
        String s = "Latency";
        float sy = PAD + ((h - 2*PAD) - s.length()*sh)/2 + lm.getAscent();
        for(int i = 0; i < s.length(); i++) {
            String letter = String.valueOf(s.charAt(i));
            float sw = (float)font.getStringBounds(letter, frc).getWidth();
            float sx = (PAD - sw)/2;
            g2.drawString(letter, sx, sy);
            sy += sh;
        }
        // Abcissa label.
        s = "number of files";
        sy = h - PAD + (PAD - sh)/2 + lm.getAscent();
        float sw = (float)font.getStringBounds(s, frc).getWidth();
        float sx = (w - sw)/2;
        g2.drawString(s, sx, sy);
        g2.drawString("blue : 100%ddr",30, 150);
        g2.drawString("yellow : 50%ddr",30, 170);
        g2.drawString("green : 0%ddr",30,190);
        // Draw lines.
        double xInc = (double)(w - 2*PAD)/(d100.length-1);
        double scale = (double)(h - 2*PAD)/getMax(d100);
        double xInc50 = (double)(w - 2*PAD)/(d50.length-1);
        double scale50 = (double)(h - 2*PAD)/getMax(d50);
        double xInc0 = (double)(w - 2*PAD)/(d0.length-1);
        double scale0 = (double)(h - 2*PAD)/getMax(d0);
        g2.setPaint(Color.green.darker());
        for(int i = 0; i < d100.length-1; i++) {
            double x1 = PAD + i*xInc;
            double y1 = h - PAD - scale*d100[i];
            double x2 = PAD + (i+1)*xInc;
            double y2 = h - PAD - scale*d100[i+1];
            g2.draw(new Line2D.Double(x1, y1, x2, y2));
        }
        g2.setPaint(Color.yellow);
        //g2.drawString("HELLO",30, 350);
        for(int i = 0; i < d50.length-1; i++) { 
            double x1 = PAD + i*xInc;
            double y1 = h - PAD - scale*d50[i];
            double x2 = PAD + (i+1)*xInc;
            double y2 = h - PAD - scale*d50[i+1];
            g2.draw(new Line2D.Double(x1, y1, x2, y2));
        }
        g2.setPaint(Color.green.blue);
       for(int i = 0; i < d0.length-1; i++) { 
            double x1 = PAD + i*xInc;
            double y1 = h - PAD - scale*d0[i];
            double x2 = PAD + (i+1)*xInc;
            double y2 = h - PAD - scale*d0[i+1];
            g2.draw(new Line2D.Double(x1, y1, x2, y2));
        }
        /*for(int i = 0; i < d65.length-1; i++) { 
            double x1 = PAD + i*xInc65;
            double y1 = h - PAD - scale100*d65[i];
            double x2 = PAD + (i+1)*xInc65;
            double y2 = h - PAD - scale*d65[i+1];
            g2.draw(new Line2D.Double(x1, y1, x2, y2));
        }
        for(int i = 0; i < d60.length-1; i++) { 
            double x1 = PAD + i*xInc60;
            double y1 = h - PAD - scale75*d60[i];
            double x2 = PAD + (i+1)*xInc60;
            double y2 = h - PAD - scale*d60[i+1];
            g2.draw(new Line2D.Double(x1, y1, x2, y2));
        }
        for(int i = 0; i < d45.length-1; i++) { 
            double x1 = PAD + i*xInc45;
            double y1 = h - PAD - scale100*d45[i];
            double x2 = PAD + (i+1)*xInc45;
            double y2 = h - PAD - scale*d45[i+1];
            g2.draw(new Line2D.Double(x1, y1, x2, y2));
        }*/
            
        // Mark data points.
        g2.setPaint(Color.red);
        for(int i = 0; i < d100.length; i++) {
            double x = PAD + i*xInc;
            double y = h - PAD - scale*d100[i];
            g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
        }
         //g2.setPaint(Color.blue);
        for(int i = 0; i < d50.length; i++) {
            double x = PAD + i*xInc;
            double y = h - PAD - scale*d50[i];
            g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
        }
        // g2.setPaint(Color.black);
        for(int i = 0; i < d0.length; i++) {
            double x = PAD + i*xInc;
            double y = h - PAD - scale*d0[i];
            g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
        }
         /*g2.setPaint(Color.blue);
        for(int i = 0; i < d65.length; i++) {
            double x = PAD + i*xInc65;
            double y = h - PAD - scale65*d65[i];
            g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
        }
        g2.setPaint(Color.orange);
        for(int i = 0; i < d60.length; i++) {
            double x = PAD + i*xInc60;
            double y = h - PAD - scale60*d60[i];
            g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
        }
         g2.setPaint(Color.pink);
        for(int i = 0; i < d45.length; i++) {
            double x = PAD + i*xInc45;
            double y = h - PAD - scale45*d45[i];
            g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
        }*/
    }
     private int getMax(int[] array) {
        int max = -Integer.MAX_VALUE;
        for(int i = 0; i < array.length; i++) {
            if(array[i] > max)
                max = (int) array[i];
        }
        return max;
    }
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new grap());
        f.setSize(400,400);
        f.setLocation(200,200);
        f.setVisible(true);
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
        processRequest(request, response);
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
        processRequest(request, response);
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

    private int getWidth() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int getHeight() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
