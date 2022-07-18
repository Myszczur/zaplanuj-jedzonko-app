package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Book;
import pl.coderslab.model.RecipePlan;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipePlanDAO {

    private static final String CREATE_RECIPEPLAN_QUERY = "INSERT INTO recipe_plan(id,recipe_id,meal_name,display_order,day_name_id,plan_id) VALUES (?,?,?,?,?,?);";
    private static final String DELETE_RECIPEPLAN_QUERY = "DELETE FROM recipe_plan where id = ?;";
    private static final String FIND_ALL_RECIPEPLANS_QUERY = "SELECT * FROM recipe_plan;";
    private static final String READ_RECIPEPLAN_QUERY = "SELECT * from recipe_plan where id = ?;";
    private static final String UPDATE_RECIPEPLAN_QUERY = "UPDATE recipe_plan SET recipe_id = ? , meal_name = ?, display_order = ?, day_name_id = ?, plan_id = ? WHERE	id = ?;";


    public RecipePlan read(Integer recipePlanId) {
        RecipePlan recipePlan = new RecipePlan();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_RECIPEPLAN_QUERY)
        ) {
            statement.setInt(1, recipePlanId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    recipePlan.setId(resultSet.getInt("id"));
                    recipePlan.setRecipeID(resultSet.getInt("recipe_id"));
                    recipePlan.setMealName(resultSet.getString("meal_name"));
                    recipePlan.setDisplayOrder(resultSet.getInt("display_order"));
                    recipePlan.setDisplayOrder(resultSet.getInt("day_name_id"));
                    recipePlan.setDisplayOrder(resultSet.getInt("plan_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipePlan;

    }

    public List<RecipePlan> findAll() {
        List<RecipePlan> recipePlan = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_RECIPEPLANS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                RecipePlan recipePlans = new RecipePlan();
                recipePlans.setId(resultSet.getInt("id"));
                recipePlans.setRecipeID(resultSet.getInt("recipe_id"));
                recipePlans.setMealName(resultSet.getString("meal_name"));
                recipePlans.setDisplayOrder(resultSet.getInt("display_order"));
                recipePlans.setDisplayOrder(resultSet.getInt("day_name_id"));
                recipePlans.setDisplayOrder(resultSet.getInt("plan_id"));
                recipePlan.add(recipePlans);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipePlan;

    }

    public RecipePlan create(RecipePlan recipePlan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_RECIPEPLAN_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setInt(1, recipePlan.getId());
            insertStm.setInt(2, recipePlan.getRecipeID());
            insertStm.setString(3, recipePlan.getMealName());
            insertStm.setInt(4, recipePlan.getDisplayOrder());
            insertStm.setInt(5, recipePlan.getDayNameId());
            insertStm.setInt(6, recipePlan.getPlanId());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    recipePlan.setId(generatedKeys.getInt(1));
                    return recipePlan;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Integer recipePlan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RECIPEPLAN_QUERY)) {
            statement.setInt(1, recipePlan);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Product not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(RecipePlan recipePlan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_RECIPEPLAN_QUERY)) {
            statement.setInt(6, recipePlan.getId());
            statement.setInt(1, recipePlan.getRecipeID());
            statement.setString(2, recipePlan.getMealName());
            statement.setInt(3, recipePlan.getDisplayOrder());
            statement.setInt(4, recipePlan.getDayNameId());
            statement.setInt(5, recipePlan.getPlanId());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}