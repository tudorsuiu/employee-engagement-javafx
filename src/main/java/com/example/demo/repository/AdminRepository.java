package com.example.demo.repository;

import com.example.demo.domain.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdminRepository implements Repository<Admin> {
    private String url = "jdbc:postgresql://localhost:5432/bluedb";
    private String username = "postgres";
    private String password = "admin";

    private static AdminRepository instance = null;
    private AdminRepository() {

    }

    public static AdminRepository getInstance() {
        if(instance == null) {
            instance = new AdminRepository();
        }
        return instance;
    }

    @Override
    public void create(Admin entity) {
        String sql = "insert into admins (id, first_name, last_name, age, email, password, department) values (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1,entity.getId());
            ps.setString(2, entity.getFirstName());
            ps.setString(3, entity.getLastName());
            ps.setInt(4, entity.getAge());
            ps.setString(5, entity.getEmail());
            ps.setString(6,entity.getPassword());
            ps.setString(7, entity.getDepartment().toString());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Admin> read() {
        List<Admin> admins = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from admins");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                Integer age = resultSet.getInt("age");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                Department department = Department.valueOf(resultSet.getString("department"));

                Admin admin = new Admin(id, firstName, lastName, age, email, password, department);
                admins.add(admin);
            }
            return admins;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }

    @Override
    public Optional<Admin> read(int index) {
        String sql = "SELECT * from admins a WHERE a.id == (?)";
        Admin admin = new Admin();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setInt(1,index);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                Integer age = resultSet.getInt("age");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                Department department = Department.valueOf(resultSet.getString("department"));

                admin = new Admin(index, firstName, lastName, age, email, password, department);
                return Optional.of(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void update(Admin oldEntity, Admin newEntity) {
        String sql = "UPDATE employees SET first_name = (?), last_name = (?), age = (?), email = (?), password = (?), department = (?) WHERE id = (?)";
        try(Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1,newEntity.getFirstName());
            ps.setString(2,newEntity.getLastName());
            ps.setInt(3,newEntity.getAge());
            ps.setString(4,newEntity.getEmail());
            ps.setString(5,newEntity.getPassword());
            ps.setString(6,newEntity.getDepartment().toString());
            ps.setInt(7,oldEntity.getId());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Admin entity) {
        String sql = "DELETE FROM admins a WHERE a.id = (?)";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1,entity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
