package com.example.demo.repository;

import com.example.demo.domain.Badge;
import com.example.demo.domain.Department;
import com.example.demo.domain.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRepository implements Repository<Employee> {
    private String url = "jdbc:postgresql://localhost:5432/bluedb";
    private String username = "postgres";
    private String password = "admin";
    private static EmployeeRepository instance = null;
    private EmployeeRepository() {

    }
    public static EmployeeRepository getInstance() {
        if(instance == null) {
            instance = new EmployeeRepository();
        }
        return instance;
    }

    @Override
    public void create(Employee entity) {
        String sql = "insert into employees (id, first_name, last_name, age, email, password, department, collected_points, badge) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1,entity.getId());
            ps.setString(2, entity.getFirstName());
            ps.setString(3, entity.getLastName());
            ps.setInt(4, entity.getAge());
            ps.setString(5, entity.getEmail());
            ps.setString(6,entity.getPassword());
            ps.setString(7,entity.getDepartment().toString());
            ps.setInt(8,entity.getCollectedPoints());
            ps.setString(9,entity.getBadge().toString());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> read() {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from employees");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                Integer age = resultSet.getInt("age");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                Department department = Department.valueOf(resultSet.getString("job_title"));
                Integer collectedPoints = resultSet.getInt("collected_points");
                String badgeString = resultSet.getString("badge");
                Badge badge = Badge.valueOf(badgeString);

                Employee employee = new Employee(id, firstName, lastName, age, email, password, department, collectedPoints, badge);
                employees.add(employee);
            }
            return employees;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public Optional<Employee> read(int index) {
        String sql = "SELECT * from employees e WHERE e.id == (?)";
        Employee employee = new Employee();
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
                Integer collectedPoints = resultSet.getInt("collected_points");
                String badgeString = resultSet.getString("badge");
                Badge badge = Badge.valueOf(badgeString);

                employee = new Employee(index, firstName, lastName, age, email, password, department, collectedPoints, badge);
                return Optional.of(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void update(Employee oldEntity, Employee newEntity) {
        String sql = "UPDATE employees SET first_name = (?), last_name = (?), age = (?), email = (?), password = (?), department = (?), collected_points = (?), badge = (?) WHERE id = (?)";
        try(Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1,newEntity.getFirstName());
            ps.setString(2,newEntity.getLastName());
            ps.setInt(3,newEntity.getAge());
            ps.setString(4,newEntity.getEmail());
            ps.setString(5,newEntity.getPassword());
            ps.setString(6,newEntity.getDepartment().toString());
            ps.setInt(7,newEntity.getCollectedPoints());
            ps.setString(8,newEntity.getBadge().toString());
            ps.setInt(9, oldEntity.getId());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Employee entity) {
        String sql = "DELETE FROM employees e WHERE e.id = (?)";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1,entity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
