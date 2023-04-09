package com.example.demo;

import com.example.demo.domain.*;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.QuestRepository;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
        Employee employee = new Employee(1, "f", "l", 10, "e", "p", Department.Project_Mangement, 0, Badge.Potential);
        EmployeeRepository.getInstance().create(employee);
        System.out.println(EmployeeRepository.getInstance().read());
        System.out.println(EmployeeRepository.getInstance().read(1));
        EmployeeRepository.getInstance().delete(employee);

    }

    public static void main(String[] args) {
        launch();
    }
}