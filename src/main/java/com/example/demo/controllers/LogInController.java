package com.example.demo.controllers;

import com.example.demo.Application;
import com.example.demo.domain.instances.CurrentAdmin;
import com.example.demo.domain.instances.CurrentEmployee;
import com.example.demo.domain.instances.CurrentStage;
import com.example.demo.domain.models.Admin;
import com.example.demo.domain.models.Employee;
import com.example.demo.service.AdminService;
import com.example.demo.service.EmployeeService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LogInController {
    private final EmployeeService  employeeService = EmployeeService.getInstance();
    private final AdminService adminService = AdminService.getInstance();
    @FXML
    TextField textFieldEmail;

    @FXML
    PasswordField passwordField;

    @FXML
    Label labelCreateAccount;

    @FXML
    Label labelErrors;

    @FXML
    void logIn() {
        try {
            String email = textFieldEmail.getText();
            String password = passwordField.getText();
            if(email.contains("@admin.blue.com")) {
                Admin admin = adminService.validateCredentials(email, password);
                CurrentAdmin.getInstance().setCurrentAdmin(admin);
                Stage stage = CurrentStage.getInstance().getCurrentStage();
                FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("admin-page-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setTitle("Blue's Team Management App");
                stage.getIcons().add(new Image("D:\\Proiecte\\demo\\src\\main\\resources\\com\\example\\demo\\assets\\blue_icon.png"));
                stage.setScene(scene);
                stage.setResizable(false);
            }
            else {
                Employee employee = employeeService.validateCredentials(email, password);
                CurrentEmployee.getInstance().setCurrentEmployee(employee);
                Stage stage = CurrentStage.getInstance().getCurrentStage();
                FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("employee-page-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setTitle("Blue's Team Management App");
                stage.getIcons().add(new Image("D:\\Proiecte\\demo\\src\\main\\resources\\com\\example\\demo\\assets\\blue_icon.png"));
                stage.setScene(scene);
                stage.setResizable(false);
            }
        }
        catch(Exception e) {
            textFieldEmail.clear();
            passwordField.clear();
            System.out.println(e.getMessage());
            labelErrors.setText(e.getMessage());
        }
    }

    @FXML
    private void createAccount() {
        try {
            Stage stage = CurrentStage.getInstance().getCurrentStage();
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("create-account-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Create Account");
            stage.getIcons().add(new Image("D:\\Proiecte\\demo\\src\\main\\resources\\com\\example\\demo\\assets\\blue_icon.png"));
            stage.setScene(scene);
            stage.setResizable(false);
        }
        catch (Exception e) {
            labelErrors.setText(e.getMessage());
            System.out.println(e.getMessage());
        }
    }
}
