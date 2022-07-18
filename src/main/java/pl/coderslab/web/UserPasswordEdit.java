package pl.coderslab.web;

import pl.coderslab.dao.AdminDAO;
import pl.coderslab.dao.PlanDAO;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserPasswordEdit", value = "/app/passwordedit")
public class UserPasswordEdit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/editpassword.jsp");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin adminToUpdate = new Admin();
        HttpSession session = request.getSession();
        int adminId = Integer.parseInt(String.valueOf(session.getAttribute("adminId")));
        adminToUpdate.setId(adminId);
        adminToUpdate.setPassword(request.getParameter("password"));
        AdminDAO dao = new AdminDAO();
        dao.updatePassword(adminToUpdate);
        response.sendRedirect("/app/dashboard");
    }
}
