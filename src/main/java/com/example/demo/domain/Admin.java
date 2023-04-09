package com.example.demo.domain;

public class Admin extends User {
    private Department department;

    public Admin() {

    }

    public Admin(Integer id, String firstName, String lastName, Integer age, String email, String password, Department department) {
        super(id, firstName, lastName, age, email, password);
        this.department = department;
    }

    public Admin(User user, Department department) {
        super(user);
        this.department = department;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age='" + age + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                "department='" + department +
                '}';
    }
}
