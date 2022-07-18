package pl.coderslab.web;

import pl.coderslab.dao.AdminDAO;
import pl.coderslab.dao.PlanDAO;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserEdit", value = "/app/useredit")
public class UserEdit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/edituser.jsp");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin adminToUpdate = new Admin();
        HttpSession session = request.getSession();
        int adminId = Integer.parseInt(String.valueOf(session.getAttribute("adminId")));
        adminToUpdate.setId(adminId);
        adminToUpdate.setFirstName(request.getParameter("firstName"));
        adminToUpdate.setLastName(request.getParameter("lastName"));
        adminToUpdate.setEmail(request.getParameter("email"));
        AdminDAO dao = new AdminDAO();
        dao.updateData(adminToUpdate);
        response.sendRedirect("/app/dashboard");
    }
}
