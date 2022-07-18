package pl.coderslab.web;



import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "AddRecipeForm", value = "/app/recipe/add")
public class AddRecipeForm extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/AddRecipeForm.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Recipe recipe = new Recipe();
        RecipeDao recipeDao = new RecipeDao();


        //recipe.setNameId(Integer.parseInt(request.getParameter("id")));
        recipe.setName(request.getParameter("name"));
        recipe.setIngredients(request.getParameter("ingredients"));
        recipe.setDescription(request.getParameter("description"));
        recipe.setCreated(request.getParameter("created"));
        //recipe.setUpdate(request.getParameter("updated"));
        recipe.setPreparationTime(Integer.parseInt(request.getParameter("preparationTime")));
        recipe.setPreparation(request.getParameter("preparation"));
        //recipe.setAdminId(Integer.parseInt(request.getParameter("admin_id")));
        HttpSession session = request.getSession();
        int adminId = Integer.parseInt(session.getAttribute("adminId").toString());
        recipe.setAdminId(adminId);
        recipe.setUpdate("2018-10-17 14:24:44");

        recipeDao.create(recipe);
        response.sendRedirect("/app/recipe/add");


    }
}



