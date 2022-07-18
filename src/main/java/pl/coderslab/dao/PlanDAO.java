package pl.coderslab.dao;

import pl.coderslab.model.Admin;
import pl.coderslab.model.Book;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlanDAO {
    private static final String CREATE_PLAN = "INSERT INTO scrumlab.plan (name, description, created, admin_id) VALUES (?, ?, ?, ?);";
    private static final String READ_PLAN = "SELECT * FROM plan where id = (?);";
    private static final String UPDATE_PLAN = "UPDATE plan SET name = ?, description = ?, WHERE id = ?;";
    private static final String DELETE_PLAN = "DELETE FROM plan where id = ?;";
    private static final String FIND_ALL_PLAN = "SELECT * FROM plan";
    private static final String COUNT_PLANS = "SELECT COUNT(*) FROM plan WHERE admin_id = ?";
    private static final String FIND_ALL_PLAN_BY_ADMINID = "SELECT * FROM plan WHERE admin_id = ?;";


    public Plan create(Plan plan) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_PLAN, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, plan.getName());
            statement.setString(2, plan.getDescription());
            statement.setString(3, plan.getCreated());  //POWINNO SIĘ GENEROWAĆ Z AUTOMATU
            statement.setInt(4, plan.getAdminId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                plan.setPlanId(resultSet.getInt(1));
            }
            return plan;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
        public Plan read (int id) {
        Plan plan = new Plan();
        try (Connection conn = DbUtil.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(READ_PLAN.replace("(?)", String.valueOf(id)));
            if (resultSet.next()) {
                plan.setPlanId(resultSet.getInt("id"));
                plan.setName(resultSet.getString("name"));
                plan.setDescription(resultSet.getString("description"));
                plan.setCreated(resultSet.getString("created"));
                plan.setAdminId(resultSet.getInt("admin_id"));
                return plan;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updatePlan (Plan plan) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_PLAN);
            preparedStatement.setString(1, plan.getName());
            preparedStatement.setString(2, plan.getDescription());
            preparedStatement.setString(3, plan.getCreated());    // TO NIE POWINNO BYĆ EDYTOWALNE CHYBA, ŻE MA SIĘ NADPISYWAĆ DATA MODYFIKACJI
            preparedStatement.setInt(4, plan.getAdminId());       // TO NIE POWINNO BYĆ EDYTOWALNE
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePlan (int planId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(DELETE_PLAN);
            preparedStatement.setInt(1, planId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Plan> findAll() {
        List<Plan> planList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_PLAN);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Plan plan = new Plan();
                plan.setPlanId(resultSet.getInt("id"));
                plan.setName(resultSet.getString("name"));
                plan.setDescription(resultSet.getString("description"));
                plan.setCreated(resultSet.getString("created"));
                plan.setAdminId(resultSet.getInt("admin_id"));
                planList.add(plan);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planList;

    }

    public int countPlans(Admin admin) {
        int count = 0;
        try (Connection connection = DbUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(COUNT_PLANS);) {
            statement.setInt(1, admin.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    count = resultSet.getInt("COUNT(*)");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
    public List<Plan> findAllbyAdmin(int adminId) {
        List<Plan> planList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_PLAN_BY_ADMINID)) {

            statement.setInt(1, adminId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Plan planToAdd = new Plan();
                planToAdd.setPlanId(resultSet.getInt("id"));
                planToAdd.setName(resultSet.getString("name"));
                planToAdd.setDescription(resultSet.getString("description"));
                planToAdd.setCreated(resultSet.getString("created"));
                planToAdd.setAdminId(resultSet.getInt("admin_id"));
                planList.add(planToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planList;
    }
 }
