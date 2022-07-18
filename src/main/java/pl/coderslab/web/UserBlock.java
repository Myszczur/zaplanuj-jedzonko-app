package pl.coderslab.web;

import pl.coderslab.dao.AdminDAO;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserBlock", value = "/app/userblock")
public class UserBlock extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int adminId = Integer.parseInt(request.getParameter("id"));
        Admin admin = new Admin();
        AdminDAO dao = new AdminDAO();
        admin.setId(adminId);
        dao.block(admin);

        request.getServletContext().getRequestDispatcher("/app/userlist").forward(request, response);

    }

}
