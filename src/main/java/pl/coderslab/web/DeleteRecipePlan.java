package pl.coderslab.web;

import pl.coderslab.dao.Plan;
import pl.coderslab.dao.PlanDAO;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDAO;
import pl.coderslab.model.Recipe;
import pl.coderslab.model.RecipePlan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/app/recipe/plan/delete")
public class DeleteRecipePlan extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String recipePlanIdValue = request.getParameter("id");
        String planIdValue = request.getParameter("planId");
        String recipeIdValue = request.getParameter("recipeId");

        RecipeDao recipeDao = new RecipeDao();
        Recipe recipe = recipeDao.read(Integer.parseInt(recipeIdValue));
        request.setAttribute("recipeName", recipe.getName());

        PlanDAO planDAO = new PlanDAO();
        Plan plan = planDAO.read(Integer.parseInt(planIdValue));
        request.setAttribute("planName", plan.getName());

        request.setAttribute("recipePlanId", recipePlanIdValue);
        request.setAttribute("planId", planIdValue);
        getServletContext().getRequestDispatcher("/deleterecipePlan.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String recipePlanIdValue = request.getParameter("recipePlanId");
        String planIdValue = request.getParameter("planId");
        int recipePlanId = Integer.parseInt(recipePlanIdValue);
        int planId = Integer.parseInt(planIdValue);
        RecipePlanDAO recipePlanDAO = new RecipePlanDAO();
        recipePlanDAO.delete(recipePlanId);

        response.sendRedirect(request.getContextPath() + "/app/plan/details?planId=" /*+ planId*/);

    }
}
