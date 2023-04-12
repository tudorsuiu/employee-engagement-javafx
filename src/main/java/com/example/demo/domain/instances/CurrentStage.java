package com.example.demo.domain.instances;

import javafx.stage.Stage;

public class CurrentStage {
    private Stage currentStage;

    private static CurrentStage instance = null;

    private CurrentStage() {

    }

    public static CurrentStage getInstance() {
        if(instance == null) {
            instance = new CurrentStage();
        }
        return instance;
    }

    public Stage getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }
}
