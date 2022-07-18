package pl.coderslab.dao;


import pl.coderslab.model.Admin;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import pl.coderslab.utils.DbUtil;

public class AdminDAO extends Admin {
    private static final String CREATE_ADMIN_QUERY =
            "INSERT INTO admins(first_name, last_name,  email, password, superadmin, enable) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String READ_ADMIN_QUERY =
            "SELECT * FROM admins where id =(?)";

    private static final String READ_ADMIN_BY_EMAIL_QUERY =
            "SELECT * FROM admins where email like '(?)'";

    private static final String UPDATE_ADMIN_DATA_QUERY =
            "UPDATE admins SET first_name = ?, last_name = ?, email = ? where id = ?";

    private static final String UPDATE_ADMIN_PASSWORD_QUERY =
            "UPDATE admins SET  password = ? where id = ?";

    private static final String BLOCK_ADMIN_QUERY =
            "UPDATE admins SET  enable = 0 where id = ?";

    private static final String DELETE_QUERY =
            "DELETE FROM admins where id = ?";

    private static final String READALL_QUERY =
            "SELECT * FROM admins";

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    public boolean checkPassword(String password, String hashedPassword) { return BCrypt.checkpw(password,hashedPassword);}

    public Admin create(Admin admin) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_ADMIN_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, admin.getFirstName());
            statement.setString(2, admin.getLastName());
            statement.setString(3, admin.getEmail());
            statement.setString(4, hashPassword(admin.getPassword()));
            statement.setString(5, "0");            //tutaj mysle, zeby zrobic tak, ze domyslnie nie dajemy superadmina i a enable na 1
            statement.setString(6, "1");
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                admin.setId(resultSet.getInt(1));
            }
            return admin;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Admin read(int adminID) {
        Admin admin = new Admin();
        try (Connection conn = DbUtil.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(READ_ADMIN_QUERY.replace("(?)", String.valueOf(adminID)));
            if (resultSet.next()) {
                admin.setId(resultSet.getInt("id"));
                admin.setFirstName(resultSet.getString("first_name"));
                admin.setLastName(resultSet.getString("last_name"));
                admin.setEmail(resultSet.getString("email"));
                admin.setPassword(resultSet.getString("password"));
                admin.setSuperadmin(resultSet.getInt("superadmin"));
                admin.setEnable(resultSet.getInt("enable"));
                return admin;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Admin readAdminByEmail(String email) {
        Admin admin = new Admin();
        try (Connection conn = DbUtil.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(READ_ADMIN_BY_EMAIL_QUERY.replace("(?)", email));
            if (resultSet.next()) {
                admin.setId(resultSet.getInt("id"));
                admin.setFirstName(resultSet.getString("first_name"));
                admin.setLastName(resultSet.getString("last_name"));
                admin.setEmail(resultSet.getString("email"));
                admin.setPassword(resultSet.getString("password"));
                admin.setSuperadmin(resultSet.getInt("superadmin"));
                admin.setEnable(resultSet.getInt("enable"));
                return admin;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkEmailVsPassword(String email, String password) {
        try (Connection conn = DbUtil.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(READ_ADMIN_BY_EMAIL_QUERY.replace("(?)", email));
            if (resultSet.next()) {
                String savedEmail = resultSet.getString("email");
                String savedPassword = resultSet.getString("password");
                if (savedEmail.equals(email) && checkPassword(password, savedPassword)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateData(Admin admin) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_ADMIN_DATA_QUERY);
            preparedStatement.setInt(4, admin.getId());
            preparedStatement.setString(1, admin.getFirstName());
            preparedStatement.setString(2, admin.getLastName());
            preparedStatement.setString(3, admin.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePassword(Admin admin) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_ADMIN_PASSWORD_QUERY);
            preparedStatement.setInt(2, admin.getId());
            preparedStatement.setString(1, this.hashPassword(admin.getPassword()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void block(Admin admin) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(BLOCK_ADMIN_QUERY);
            preparedStatement.setInt(1, admin.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int adminID) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(DELETE_QUERY);
            preparedStatement.setInt(1, adminID);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Admin> findAll() {
        List<Admin> allAdminsList = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(READALL_QUERY);
            while (resultSet.next()) {
                Admin nextAdmin = new Admin();
                nextAdmin.setId(resultSet.getInt("id"));
                nextAdmin.setFirstName(resultSet.getString("first_name"));
                nextAdmin.setLastName(resultSet.getString("last_name"));

                allAdminsList.add(nextAdmin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return allAdminsList;
    }

}
