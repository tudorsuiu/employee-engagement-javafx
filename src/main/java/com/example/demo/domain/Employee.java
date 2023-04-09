package com.example.demo.domain;

public class Employee extends User {
    private Department department;
    private Integer collectedPoints;
    private Badge badge;

    public Employee() {

    }

    public Employee(Integer id, String firstName, String lastName, Integer age, String email, String password, Department department, Integer collectedPoints, Badge badge) {
        super(id, firstName, lastName, age, email, password);
        this.department = department;
        this.collectedPoints = collectedPoints;
        this.badge = badge;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Integer getCollectedPoints() {
        return collectedPoints;
    }

    public void setCollectedPoints(Integer collectedPoints) {
        this.collectedPoints = collectedPoints;
    }

    public Badge getBadge() {
        return badge;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", department='" + department.toString() + '\'' +
                ", collectedPoints=" + collectedPoints +
                '}';
    }
}
