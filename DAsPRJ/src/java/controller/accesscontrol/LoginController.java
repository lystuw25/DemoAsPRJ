/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.accesscontrol;

import dal.AccountDBContext;
import entity.accesscontrol.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author A A
 */
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("username");
        String pass = req.getParameter("password");
        
        AccountDBContext db = new AccountDBContext();
        User account = db.Login(user, pass);
        
        if(account!=null){
            req.getSession().setAttribute("account", account);
            resp.sendRedirect(req.getContextPath()+"/home");
        } else {
            // If login fails, set an error message and stay on the login page
            req.setAttribute("errorMessage", "Your username or password is wrong.");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
        
//        else resp.getWriter().println("login failed!");
//        
//        String url = this.getInitParameter("url");
//        resp.getWriter().println(url);
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //pre-processing
        req.getRequestDispatcher("login.jsp").forward(req, resp);
        //post-processing
    }
    
}
