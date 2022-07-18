package pl.coderslab.web;

import pl.coderslab.dao.*;
import pl.coderslab.model.RecipePlan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/app/recipe/plan/add")
public class AddPlanRecipe extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        int adminId = Integer.parseInt(session.getAttribute("adminId").toString());

        PlanDAO planDAO = new PlanDAO();
        request.setAttribute("planDAO", planDAO.findAllbyAdmin(adminId));

        RecipeDao recipeDao = new RecipeDao();
        request.setAttribute("recipeDao", recipeDao.findAllbyADmin(adminId));

        DayNameDAO dayNameDAO = new DayNameDAO();
        request.setAttribute("dayNameDAO", dayNameDAO.findAll());

        getServletContext().getRequestDispatcher("/addPlanRecipe.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RecipePlan recipePlan = new RecipePlan();
        RecipePlanDAO recipePlanDAO = new RecipePlanDAO();

        int chosePlan = Integer.parseInt(request.getParameter("choosePlan"));
        String mealName = request.getParameter("name");
        int displayOrder = Integer.parseInt(request.getParameter("number"));
        int recipeId = Integer.parseInt((request.getParameter("recipe")));
        int dayId = Integer.parseInt(request.getParameter("day"));

        recipePlan.setRecipeID(recipeId);
        recipePlan.setMealName(mealName);
        recipePlan.setDisplayOrder(displayOrder);
        recipePlan.setDayNameId(dayId);
        recipePlan.setPlanId(chosePlan);
        recipePlanDAO.create(recipePlan);
        response.sendRedirect(request.getContextPath() + "/app/recipe/plan/add");

    }
}
