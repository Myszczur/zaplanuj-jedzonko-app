package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Recipe;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.lang.String.*;


public class    RecipeDao {

    private static final String CREATE_RECIPE = "INSERT INTO scrumlab.recipe (name, ingredients, description, created, updated, preparation_time, preparation, admin_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String READ_RECIPE = "SELECT * FROM recipe where id = ?;";
    private static final String UPDATE_RECIPE = "UPDATE recipe SET name = ?, ingredients = ?, description = ?, updated = ?, preparation_time = ?, preparation = ?, admin_id = ? WHERE id = ?;";
    private static final String DELETE_RECIPE = "DELETE FROM recipe where id = ?;";
    private static final String FIND_ALL_RECIPE = "SELECT * FROM recipe;";
    private static final String COUNT_OF_RECIPES_BY_USER = "SELECT count(recipe.id) from recipe join admins a on a.id = recipe.admin_id where admin_id = '(?)'";
    private static final String FIND_ALL_RECIPE_BY_ADMINID = "SELECT * FROM recipe WHERE admin_id = ?;";


    public Recipe create(Recipe recipe){
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_RECIPE,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, recipe.getName());
            insertStm.setString(2, recipe.getIngredients());
            insertStm.setString(3, recipe.getDescription());
            //   insertStm.setString(4, recipe.getCreated(LocalTime.now());
            insertStm.setString(4, recipe.getCreated());
            insertStm.setString(5, recipe.getUpdate());
            insertStm.setInt(6, recipe.getPreparationTime());
            insertStm.setString(7, recipe.getPreparation());
            insertStm.setInt(8, recipe.getAdminId());

            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    recipe.setNameId(generatedKeys.getInt(1));
                    return recipe;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Recipe read(Integer recipeId) {
        Recipe recipe = new Recipe();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_RECIPE)
        ) {
            statement.setInt(1, recipeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    recipe.setNameId(resultSet.getInt("id"));
                    recipe.setName(resultSet.getString("name"));
                    recipe.setIngredients(resultSet.getString("ingredients"));
                    recipe.setDescription(resultSet.getString("description"));
                    recipe.setCreated(resultSet.getString("created"));
                    recipe.setUpdate(resultSet.getString("updated"));
                    recipe.setPreparationTime(resultSet.getInt("preparation_time"));
                    recipe.setPreparation(resultSet.getString("preparation"));
                    recipe.setAdminId(resultSet.getInt("admin_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipe;

    }


    public void update(Recipe recipe) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_RECIPE)) {

            statement.setInt(8, recipe.getNameId());
            statement.setString(1,recipe.getName());
            statement.setString(2,recipe.getIngredients());
            statement.setString(3,recipe.getDescription());
            statement.setString(4,recipe.getUpdate());
            statement.setInt(5,recipe.getPreparationTime());
            statement.setString(6,recipe.getPreparation());
            statement.setInt(7,recipe.getAdminId());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void delete(Integer recipeId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RECIPE)) {
            statement.setInt(1, recipeId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Product not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Recipe> findAll() {
        List<Recipe> recipeList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_RECIPE);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Recipe recipeToAdd = new Recipe();
                recipeToAdd.setNameId(resultSet.getInt("id"));
                recipeToAdd.setName(resultSet.getString("name"));
                recipeToAdd.setIngredients(resultSet.getString("ingredients"));
                recipeToAdd.setDescription(resultSet.getString("description"));
                recipeToAdd.setCreated(resultSet.getString("created"));
                recipeToAdd.setUpdate(resultSet.getString("updated"));
                recipeToAdd.setPreparationTime(resultSet.getInt("preparation_time"));
                recipeToAdd.setPreparation(resultSet.getString("preparation"));
                recipeToAdd.setAdminId(resultSet.getInt("admin_id"));

                recipeList.add(recipeToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeList;
    }

    public int countRecipies(int adminId) {
        int countOfRecipies = 0;
        Admin admin = new Admin();
        try (Connection conn = DbUtil.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(COUNT_OF_RECIPES_BY_USER.replace("(?)", String.valueOf(adminId)));
            if (resultSet.next()) {
                countOfRecipies = resultSet.getInt("count(recipe.id)");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countOfRecipies;
    }
    public List<Recipe> findAllbyADmin(int adminId) {
        List<Recipe> recipeList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_RECIPE_BY_ADMINID)) {

            statement.setInt(1, adminId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Recipe recipeToAdd = new Recipe();
                recipeToAdd.setNameId(resultSet.getInt("id"));
                recipeToAdd.setName(resultSet.getString("name"));
                recipeToAdd.setIngredients(resultSet.getString("ingredients"));
                recipeToAdd.setDescription(resultSet.getString("description"));
                recipeToAdd.setCreated(resultSet.getString("created"));
                recipeToAdd.setUpdate(resultSet.getString("updated"));
                recipeToAdd.setPreparationTime(resultSet.getInt("preparation_time"));
                recipeToAdd.setPreparation(resultSet.getString("preparation"));
                recipeToAdd.setAdminId(resultSet.getInt("admin_id"));
                recipeList.add(recipeToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeList;
    }
}


