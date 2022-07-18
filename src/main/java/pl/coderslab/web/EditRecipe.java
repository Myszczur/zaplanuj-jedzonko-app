package pl.coderslab.web;

import pl.coderslab.dao.AdminDAO;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/app/recipe/edit")
public class EditRecipe extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int recipeId = Integer.parseInt(request.getParameter("id"));
        RecipeDao recipeDao = new RecipeDao();
        Recipe recipe = recipeDao.read(recipeId);
        request.setAttribute("recipe", recipe);
        getServletContext().getRequestDispatcher("/recipeEdit.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int recipeId = Integer.parseInt(request.getParameter("id"));

        Recipe recipe = new Recipe();
        RecipeDao recipeDao = new RecipeDao();
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        HttpSession session = request.getSession();
        int adminId = Integer.parseInt(session.getAttribute("adminId").toString());

        recipe.setNameId(recipeId);
        recipe.setName(request.getParameter("recipeName"));
        recipe.setIngredients(request.getParameter("recipeIngredients"));
        recipe.setDescription(request.getParameter("recipeDescription"));
        recipe.setUpdate(timeStamp);
        recipe.setPreparationTime(Integer.parseInt(request.getParameter("recipeTime")));
        recipe.setPreparation(request.getParameter("recipePreparation"));
        recipe.setAdminId(adminId);
        recipeDao.update(recipe);

        response.sendRedirect(request.getContextPath() + "/app/recipe/list");


    }
}
