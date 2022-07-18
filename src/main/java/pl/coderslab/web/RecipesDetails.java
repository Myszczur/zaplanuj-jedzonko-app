package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/recipesDetails")
public class RecipesDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int recipeId = Integer.parseInt(request.getParameter("id"));

        RecipeDao recipeDao = new RecipeDao();
        Recipe recipe = recipeDao.read(recipeId);

        request.setAttribute("recipe", recipe);
        request.getServletContext().getRequestDispatcher("/recipesDetails.jsp").forward(request, response);

    }

}
