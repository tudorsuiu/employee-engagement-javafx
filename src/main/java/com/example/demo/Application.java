package com.example.demo;

import com.example.demo.domain.instances.CurrentStage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        CurrentStage.getInstance().setCurrentStage(stage);
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("log-in-page-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Log In Page");
        stage.getIcons().add(new Image("D:\\Proiecte\\demo\\src\\main\\resources\\com\\example\\demo\\assets\\blue_icon.png"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}