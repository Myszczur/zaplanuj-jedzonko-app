package pl.coderslab.web;

import pl.coderslab.dao.Plan;
import pl.coderslab.dao.PlanDAO;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;
import pl.coderslab.model.RecipePlan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PlanDetails", value = "/app/plan/details/")
public class PlanDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int planId = Integer.parseInt(request.getParameter("id"));;
        PlanDAO planDAO = new PlanDAO();
        Plan plan = planDAO.read(planId);
        request.setAttribute("plan", plan);
        request.getServletContext().getRequestDispatcher("/appPlanDetails.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
