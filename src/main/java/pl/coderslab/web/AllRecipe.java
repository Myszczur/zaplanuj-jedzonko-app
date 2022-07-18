package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@WebServlet("/recipe")
public class AllRecipe extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RecipeDao recipeDao = new RecipeDao();
        List<Recipe> recipes = recipeDao.findAll();
        Collections.reverse(recipes);
        request.setAttribute("recipeDao", recipes);
        getServletContext().getRequestDispatcher("/recipe.jsp").forward(request, response);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String searchPhrase = request.getParameter("recipeName");
            Pattern compiledPattern = Pattern.compile(searchPhrase, Pattern.CASE_INSENSITIVE);

            RecipeDao recipeDao = new RecipeDao();
            List<Recipe> recipes = recipeDao.findAll();
            List<Recipe> searchRecipes = new ArrayList<>();
            for (Recipe recipe : recipes) {
                Matcher matcher = compiledPattern.matcher(recipe.getName());
                if (matcher.find()) {
                    searchRecipes.add(recipe);
                }
            }
            request.setAttribute("recipeDao", searchRecipes);
            request.getServletContext().getRequestDispatcher("/recipe.jsp").forward(request, response);
        }catch (StringIndexOutOfBoundsException e){
            response.sendRedirect("/recipe");
        }
    }
}