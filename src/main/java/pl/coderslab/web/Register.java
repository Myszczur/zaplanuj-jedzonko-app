package pl.coderslab.web;

import pl.coderslab.dao.AdminDAO;
import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Registration", value = "/register")
public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/registration.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin admin = new Admin();
        admin.setFirstName(request.getParameter("name"));
        admin.setLastName(request.getParameter("surname"));
        admin.setEmail(request.getParameter("email"));
        admin.setPassword(request.getParameter("password"));

        AdminDAO dao = new AdminDAO();
        dao.create(admin);
        response.sendRedirect("/login");
    }

}
