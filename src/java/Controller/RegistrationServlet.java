/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import BusinessLogic.RegistrationService;
import DTO.Registration;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Chathuranga
 */
@WebServlet(name = "RegistrationServlet", urlPatterns = {"/RegistrationServlet"})
public class RegistrationServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) 
        {
            HttpSession session = request.getSession();
            
            String password = request.getParameter("password");
            String repassword = request.getParameter("reenterpassword");
            String firstName = request.getParameter("firstname");
            String lastName = request.getParameter("lastname");
            String address = request.getParameter("address");
            String dob = request.getParameter("dob");
            
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                Date to = new Date();
                try {
                    to = sdf.parse(dob);

                } catch (ParseException e) {
                // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            
            List list = new ArrayList();
            list.add(new Cookie("firstname",firstName));
            list.add(new Cookie("lastname",lastName));
            list.add(new Cookie("address",URLEncoder.encode(address, "UTF-8")));
            list.add(new Cookie("dob",dob));
            for(Object e:list){
                ((Cookie)e).setMaxAge(20*60);
                response.addCookie((Cookie)e);
            }
            
            if(password.equals(repassword)){
                Registration newUser = new Registration(password,firstName,lastName,address,to);

                RegistrationService register = new RegistrationService();
                try{
                    if(register.checkExist(newUser.getID())){
                        String error = "It seems user already exist. Contact our helpdesk for assistance.";
                        session.setAttribute("error", error);
                        response.sendRedirect("/Web_App");
                    }else{
                        try{
                            String registerResult;
                            registerResult = register.RegisterUser(newUser);
                            session.setAttribute("success", registerResult);
                            response.sendRedirect("/Web_App");
                        }catch (SQLException se){
                            String error = "There seem to be a problem registering your details. Try again later.";
                            session.setAttribute("error", error);
                            response.sendRedirect("/Web_App");
                        } 
                        catch (IOException e){
                            String error = "Address entered is invalid. Please try again later";
                            session.setAttribute("error", error);
                            response.sendRedirect("/Web_App");
                        }
                    }
                }catch(SQLException se){
                    String error = "Error establishing connection with database. Try again later.";
                    session.setAttribute("error", error);
                    response.sendRedirect("/Web_App");
                }
            }else{
                String error = "Password not identical,try again.";
                session.setAttribute("error", error);
                response.sendRedirect("/Web_App");
            }
        }
    }



}
