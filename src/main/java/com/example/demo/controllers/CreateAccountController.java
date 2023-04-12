package com.example.demo.controllers;

import com.example.demo.Application;
import com.example.demo.domain.instances.CurrentStage;
import com.example.demo.domain.models.Admin;
import com.example.demo.domain.models.Badge;
import com.example.demo.domain.models.Department;
import com.example.demo.domain.models.Employee;
import com.example.demo.domain.validators.ValidationException;
import com.example.demo.service.AdminService;
import com.example.demo.service.EmployeeService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class CreateAccountController implements Initializable {
    private final EmployeeService employeeService = EmployeeService.getInstance();
    private final AdminService adminService = AdminService.getInstance();
    @FXML
    TextField textFieldAdminCode;
    @FXML
    TextField textFieldFirstName;
    @FXML
    TextField textFieldLastName;
    @FXML
    TextField textFieldAge;
    @FXML
    TextField textFieldEmail;
    @FXML
    PasswordField passwordField;
    @FXML
    Label labelErrors;
    @FXML
    private ChoiceBox<String> choiceBoxDepartment;
    private final String[] departments = {"Project Management", "Software Development", "Quality Assurance", "Technical Support", "Information Security"};

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBoxDepartment.getItems().addAll(departments);
    }

    @FXML
    public void onCreateAccountButton() {
        try {
            labelErrors.setText("");
            String firstName = textFieldFirstName.getText();
            String lastName = textFieldLastName.getText();
            Integer age = Integer.valueOf(textFieldAge.getText());
            String email = textFieldEmail.getText();
            String password = passwordField.getText();
            String stringDepartment = choiceBoxDepartment.getSelectionModel().getSelectedItem();
            Department department = null;
            if(Objects.equals(stringDepartment, "Project Management")) {
                department = Department.Project_Management;
            }
            else if(Objects.equals(stringDepartment, "Software Development")) {
                department = Department.Software_Development;
            }
            else if(Objects.equals(stringDepartment, "Technical Support")) {
                department = Department.Technical_Support;
            }
            else if(Objects.equals(stringDepartment, "Information Security")) {
                department = Department.Information_Security;
            }
            else if(Objects.equals(stringDepartment, "Quality Assurance")) {
                department = Department.Quality_Assurance;
            }

            if(textFieldAdminCode.getText() == null || Objects.equals(textFieldAdminCode.getText(), "")) {
                Employee employee = new Employee(employeeService.idGenerator(), firstName, lastName, age, email, password, department, 0, Badge.Potential);
                employeeService.create(employee);
            }
            else if(Objects.equals(textFieldAdminCode.getText(), "blue@dmin")) {
                Admin admin = new Admin(adminService.idGenerator(), firstName, lastName, age, email, password, department);
                adminService.create(admin);
            }
            else {
                throw new ValidationException("Invalid admin code.");
            }

            Stage stage = CurrentStage.getInstance().getCurrentStage();
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("log-in-page-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Log In");
            stage.getIcons().add(new Image("D:\\Proiecte\\demo\\src\\main\\resources\\com\\example\\demo\\assets\\blue_icon.png"));
            stage.setScene(scene);
            stage.setResizable(false);
        }
        catch (Exception e) {
            labelErrors.setText(e.getMessage());
        }
    }
}
