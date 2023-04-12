package com.example.demo.domain.instances;

import com.example.demo.domain.models.Employee;

public class CurrentEmployee {
    private Employee currentEmployee;
    private static CurrentEmployee instance = null;
    private CurrentEmployee() {

    }

    public static CurrentEmployee getInstance() {
        if(instance == null) {
            instance = new CurrentEmployee();
        }
        return instance;
    }

    public Employee getCurrentEmployee() {
        return currentEmployee;
    }

    public void setCurrentEmployee(Employee currentEmployee) {
        this.currentEmployee = currentEmployee;
    }
}
