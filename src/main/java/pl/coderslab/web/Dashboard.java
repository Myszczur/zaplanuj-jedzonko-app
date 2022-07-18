package pl.coderslab.web;

import pl.coderslab.dao.AdminDAO;
import pl.coderslab.dao.PlanDAO;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Dashboard", value = "/app/dashboard")
public class Dashboard extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("adminId") != null) {
            Admin admin = new Admin();
            AdminDAO dao = new AdminDAO();
            PlanDAO planDAO = new PlanDAO();
            RecipeDao recipeDao = new RecipeDao();
            admin.setId(Integer.parseInt(String.valueOf(session.getAttribute("adminId"))));
            admin = dao.read(Integer.parseInt(String.valueOf(session.getAttribute("adminId"))));
            session.setAttribute("planCount", planDAO.countPlans(admin));
            session.setAttribute("recipeCount", recipeDao.countRecipies(admin.getId()));
            session.setAttribute("adminName", admin.getFirstName());
            session.setAttribute("superAdmin", admin.getSuperadmin());
            session.setAttribute("adminObject", admin);
            getServletContext().getRequestDispatcher("/dashboard.jsp")
                    .forward(request, response);


        } else {
            String msg = "Tylko zalogowani uzytkownicy maja dostep do Dashboad!";
            request.setAttribute("msgText", msg);
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
