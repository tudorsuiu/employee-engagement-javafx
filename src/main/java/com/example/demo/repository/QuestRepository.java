package com.example.demo.repository;

import com.example.demo.domain.models.Department;
import com.example.demo.domain.models.Quest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        String sql = "insert into quests (id, name, details, prize_points, creator_id, employee_id, deadline, department, approval, link) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            ps.setBoolean(9, entity.getApproval());
            ps.setString(10, entity.getLink());

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
                Boolean approval = resultSet.getBoolean("approval");
                String link = resultSet.getString("link");

                Quest quest = new Quest(id,name,details,department,prizePoints,creatorId,employeeId,deadline, approval, link);
                quests.add(quest);
            }
            return quests;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quests;
    }

    @Override
    public Quest read(int id) {
        String sql = "SELECT * from quests q WHERE q.id = (?)";
        Quest quest = new Quest();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setInt(1,id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String details = resultSet.getString("details");
                Department department = Department.valueOf(resultSet.getString("department"));
                Integer prizePoints = resultSet.getInt("prize_points");
                Integer creatorId = resultSet.getInt("creator_id");
                Integer employeeId = resultSet.getInt("employee_id");
                Date deadline = resultSet.getDate("deadline");
                Boolean approval = resultSet.getBoolean("approval");
                String link = resultSet.getString("link");

                quest = new Quest(id, name, details, department, prizePoints, creatorId, employeeId, deadline, approval, link);
                return quest;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Quest oldEntity, Quest newEntity) {
        String sql = "UPDATE quests SET name = (?), details = (?), department = (?), prize_points = (?), creator_id = (?), employee_id = (?), deadline = (?), approval = (?), link = (?) WHERE id = (?)";
        try(Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1,newEntity.getName());
            ps.setString(2, newEntity.getDetails());
            ps.setString(3, newEntity.getDepartment().toString());
            ps.setInt(4,newEntity.getPrizePoints());
            ps.setInt(5, newEntity.getCreatorId());
            ps.setInt(6, newEntity.getEmployeeId());
            ps.setDate(7, newEntity.getDeadline());
            ps.setBoolean(8, newEntity.getApproval());
            ps.setString(9, newEntity.getLink());
            ps.setInt(10, oldEntity.getId());

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
