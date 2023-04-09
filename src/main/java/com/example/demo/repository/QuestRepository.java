package com.example.demo.repository;

import com.example.demo.domain.Badge;
import com.example.demo.domain.Department;
import com.example.demo.domain.Employee;
import com.example.demo.domain.Quest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QuestRepository implements Repository<Quest> {
    private String url = "jdbc:postgresql://localhost:5432/bluedb";
    private String username = "postgres";
    private String password = "admin";
    private static QuestRepository instance = null;
    private QuestRepository(){

    }

    public static QuestRepository getInstance() {
        if(instance == null) {
            instance = new QuestRepository();
        }
        return instance;
    }

    @Override
    public void create(Quest entity) {
        String sql = "insert into quests (id, name, details, prize_points, creator_id, employee_id, deadline, department) values (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1,entity.getId());
            ps.setString(2, entity.getName());
            ps.setString(3, entity.getDetails());
            ps.setInt(4, entity.getPrizePoints());
            ps.setInt(5, entity.getCreatorId());
            ps.setInt(6,entity.getEmployeeId());
            ps.setDate(7, entity.getDeadline());
            ps.setString(8,entity.getDepartment().toString());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Quest> read() {
        List<Quest> quests = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from quests");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String details = resultSet.getString("details");
                Department department = Department.valueOf(resultSet.getString("department"));
                Integer prizePoints = resultSet.getInt("prize_points");
                Integer creatorId = resultSet.getInt("creator_id");
                Integer employeeId = resultSet.getInt("employee_id");
                Date deadline = resultSet.getDate("deadline");

                Quest quest = new Quest(id,name,details,department,prizePoints,creatorId,employeeId,deadline);
                quests.add(quest);
            }
            return quests;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quests;
    }

    @Override
    public Optional<Quest> read(int index) {
        String sql = "SELECT * from quests q WHERE q.id = (?)";
        Quest quest = new Quest();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setInt(1,index);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String details = resultSet.getString("details");
                Department department = Department.valueOf(resultSet.getString("department"));
                Integer prizePoints = resultSet.getInt("prize_points");
                Integer creatorId = resultSet.getInt("creator_id");
                Integer employeeId = resultSet.getInt("employee_id");
                Date deadline = resultSet.getDate("deadline");

                quest = new Quest(index, name, details, department, prizePoints, creatorId, employeeId, deadline);
                return Optional.of(quest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void update(Quest oldEntity, Quest newEntity) {
        String sql = "UPDATE quests SET name = (?), details = (?), department = (?), prize_points = (?), creator_id = (?), employee_id = (?), deadline = (?) WHERE id = (?)";
        try(Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1,newEntity.getName());
            ps.setString(2, newEntity.getDetails());
            ps.setString(3, newEntity.getDepartment().toString());
            ps.setInt(4,newEntity.getPrizePoints());
            ps.setInt(5, newEntity.getCreatorId());
            ps.setInt(6, newEntity.getEmployeeId());
            ps.setDate(7, newEntity.getDeadline());

            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Quest entity) {
        String sql = "DELETE FROM quests q WHERE q.id = (?)";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1,entity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
