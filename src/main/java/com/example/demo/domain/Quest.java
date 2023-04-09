package com.example.demo.domain;

import java.sql.Date;

public class Quest extends Entity {
    private String name;
    private String details;
    private Department department;
    private Integer prizePoints;
    private Integer creatorId;
    private Integer employeeId;
    private Date deadline;

    public Quest(Integer id, String name, String details, Department department, Integer prizePoints, Integer creatorId, Integer employeeId, Date deadline) {
        super(id);
        this.name = name;
        this.details = details;
        this.department = department;
        this.prizePoints = prizePoints;
        this.creatorId = creatorId;
        this.employeeId = employeeId;
        this.deadline = deadline;
    }

    public Quest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Integer getPrizePoints() {
        return prizePoints;
    }

    public void setPrizePoints(Integer prizePoints) {
        this.prizePoints = prizePoints;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "Quest{" +
                ", id=" + id + '\'' +
                ", name='" + name + '\'' +
                ", department='" + department.toString() + '\'' +
                ", details='" + details + '\'' +
                ", prizePoints=" + prizePoints + '\'' +
                ", creatorId=" + creatorId + '\'' +
                ", employeeId=" + employeeId +
                '}';
    }
}
