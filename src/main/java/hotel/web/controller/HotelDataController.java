/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.web.controller;

import hotel.web.model.Hotel;
import hotel.web.model.HotelService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author Mike
 */
//@WebServlet(name = "HotelDataController", urlPatterns = {"/hdc"})
public class HotelDataController extends HttpServlet {

    private static final String RESULT_PAGE = "index.jsp";

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
//            final AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
            RequestDispatcher view
                    = request.getRequestDispatcher(RESULT_PAGE);
            WebApplicationContext ctx
                = WebApplicationContextUtils
                .getWebApplicationContext(this.getServletContext());
            HotelService hs = (HotelService) ctx.getBean("HotelService");
            String op = request.getParameter("op");
            Hotel h = null;
            int id = 0;
            if (op != null && op.equals("retrieve")) {
                id = Integer.parseInt(request.getParameter("id"));
                System.out.println(id);
                h = hs.getHotelByID(id);
                System.out.println(h);
                request.setAttribute("selectedHotel", h);

//            Hotel h = new Hotel(id,(String)request.getParameter("name"),(String)request.getParameter("address"),(String)request.getParameter("city"),(String)request.getParameter("state"),(String)request.getParameter("zip"),(String)request.getParameter("note"));
            }
            else if(op != null && op.equals("update")){
                id = Integer.parseInt(request.getParameter("id"));
                h = new Hotel(id,(String)request.getParameter("name"),(String)request.getParameter("address"),(String)request.getParameter("city"),(String)request.getParameter("state"),(String)request.getParameter("zip"),(String)request.getParameter("note"));
                try {
                    hs.updateHotel(h, "hotel_id", id+"");
                } catch (SQLException ex) {
                    Logger.getLogger(HotelDataController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(op != null && op.equals("create")){
                h = new Hotel(0,(String)request.getParameter("name"),(String)request.getParameter("address"),(String)request.getParameter("city"),(String)request.getParameter("state"),(String)request.getParameter("zip"),(String)request.getParameter("note"));
                try{
                    hs.addHotel(h);
                } catch (SQLException ex) {
                    Logger.getLogger(HotelDataController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(op != null && op.equals("delete")){
                id = Integer.parseInt(request.getParameter("id"));
                try{
                    hs.removeHotel("hotel_id", id+"");
                } catch (SQLException ex) {
                    Logger.getLogger(HotelDataController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            List result = hs.getAllHotels();
            
        // Parameters are read only Request object properties, but attributes
            // are read/write. We can use attributes to store data for use on
            // another page.
            request.setAttribute("hotels", result);

        // This object lets you forward both the request and response
            // objects to a destination page
            view.forward(request, response);

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

}
