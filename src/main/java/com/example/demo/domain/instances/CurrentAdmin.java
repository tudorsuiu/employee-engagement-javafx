package com.example.demo.domain.instances;

import com.example.demo.domain.models.Admin;

public class CurrentAdmin {
    private Admin currentAdmin;
    private static CurrentAdmin instance = null;
    private CurrentAdmin() {

    }

    public static CurrentAdmin getInstance() {
        if(instance == null) {
            instance = new CurrentAdmin();
        }
        return instance;
    }

    public Admin getCurrentAdmin() {
        return currentAdmin;
    }

    public void setCurrentAdmin(Admin currentAdmin) {
        this.currentAdmin = currentAdmin;
    }
}
