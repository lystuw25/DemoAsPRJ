/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.accesscontrol;

import dal.PlanDBContext;
import entity.assignment.PlanCampaign;
import entity.assignment.Product;
import entity.assignment.SchedualCampaign;
import entity.assignment.SchedualEmployee;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author A A
 */
@WebServlet(name="ListProductPlanController", urlPatterns={"/productionplan/list/product"})
public class ListProductPlanController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListProductPlanController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListProductPlanController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String raw_id = request.getParameter("id");
        String date = request.getParameter("date");
        PlanDBContext planDB = new PlanDBContext();
        PlanCampaign planTimelines = new PlanCampaign();
        planTimelines = planDB.getTimePlansByID(Integer.parseInt(raw_id));
        List<Date> dateTimes = utils.DateTimeHelper.getDatesBetween(planTimelines.getPlan().getStart(), planTimelines.getPlan().getEnd());
        List<SchedualCampaign> scQuantity = planDB.getInfoScById(Integer.parseInt(raw_id));
        if (scQuantity.size() > 0) {
            List<AbstractMap.SimpleEntry<Date, String>> dates = utils.DateTimeHelper.compareAndFormatDates(dateTimes, scQuantity);
            request.setAttribute("dates", dates);
        } else {
            request.setAttribute("dateTimes", dateTimes);
        }
        Product product = planDB.getProductName(Integer.parseInt(raw_id));
        if (date != null) {
            ArrayList<SchedualEmployee> ses = new ArrayList<>();
            ses = planDB.getEmployeesFromSC(Integer.parseInt(raw_id), Date.valueOf(date));
            request.setAttribute("ses", ses);
            String raw_ScheEm = request.getParameter("ScheEm");
            if (raw_ScheEm != null) {
                SchedualEmployee eInfo = new SchedualEmployee();
                eInfo = planDB.getEmInfoByDay(Integer.parseInt(raw_ScheEm));
                request.setAttribute("eInfo", eInfo);

            }
        }
        request.setAttribute("product", product);
        request.setAttribute("planTimelines", planTimelines);
        request.getRequestDispatcher("../../view/plan/viewPlanProduct.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String[] pids = request.getParameterValues("pid");
        PlanDBContext planDB = new PlanDBContext();
        if (pids != null) {
            String planCamID = request.getParameter("pcId");
            if (planCamID != null) {
                for (String pid : pids) {
                    String raw_sone = request.getParameter("sone" + pid);
                    String raw_stwo = request.getParameter("stwo" + pid);
                    String raw_sthree = request.getParameter("sthree" + pid);
                    String date = request.getParameter("dateChange"+pid);
                    planDB.insertSC(Integer.parseInt(planCamID), Date.valueOf(date), 1, Integer.parseInt(raw_sone));
                    planDB.insertSC(Integer.parseInt(planCamID), Date.valueOf(date), 1, Integer.parseInt(raw_stwo));
                    planDB.insertSC(Integer.parseInt(planCamID), Date.valueOf(date), 1, Integer.parseInt(raw_sthree));
                }
            }
        }
        response.sendRedirect(request.getContextPath()+"/productionPlan/list");
    }

}
