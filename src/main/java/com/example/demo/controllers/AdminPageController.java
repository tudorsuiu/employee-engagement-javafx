package com.example.demo.controllers;

import com.example.demo.domain.instances.CurrentAdmin;
import com.example.demo.domain.instances.CurrentEmployee;
import com.example.demo.domain.models.*;
import com.example.demo.service.AdminService;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.QuestService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class AdminPageController implements Initializable {
    private Admin loggedUser = CurrentAdmin.getInstance().getCurrentAdmin();
    private final QuestService questService = QuestService.getInstance();
    private final EmployeeService employeeService = EmployeeService.getInstance();
    private final AdminService adminService = AdminService.getInstance();
    private ObservableList<EmployeeQuest> employeeQuestModel;
    private ObservableList<Employee> employeeModel;

    @FXML
    Label labelVerifyQuests;
    @FXML
    Label labelRanking;
    @FXML
    Label labelYourName;
    @FXML
    Label labelDepartment;
    @FXML
    Label labelErrors;
    @FXML
    HBox hboxVerifyQuests;
    @FXML
    HBox hboxRanking;
    @FXML
    TableView<EmployeeQuest> tableViewVerifyQuests;
    @FXML
    TableColumn<EmployeeQuest, String> tableColumnFirstNameVerifyQuests;
    @FXML
    TableColumn<EmployeeQuest, String> tableColumnLastNameVerifyQuests;
    @FXML
    TableColumn<EmployeeQuest, String> tableColumnEmailVerifyQuests;
    @FXML
    TableColumn<EmployeeQuest, String> tableColumnQuestNameVerifyQuests;
    @FXML
    TableColumn<EmployeeQuest, Integer> tableColumnPointsVerifyQuests;
    @FXML
    Button buttonValidate;
    @FXML
    Text textAreaVerifyQuests;
    @FXML
    Button buttonDecline;
    @FXML
    Label labelLink;
    @FXML
    TableView<Employee> tableViewRanking;
    @FXML
    TableColumn<Employee, String> tableColumnFirstNameRanking;
    @FXML
    TableColumn<Employee, String> tableColumnLastNameRanking;
    @FXML
    TableColumn<Employee, String> tableColumnEmailRanking;
    @FXML
    TableColumn<Employee, Integer> tableColumnPointsRanking;
    @FXML
    TableColumn<Employee, String> tableColumnBadgeRanking;

    /**
     * Removing "_" and transforming enum member to string
     * @param department enum
     * @return string without "_"
     */
    private String fromDepartmentToString(Department department) {
        if(department == Department.Software_Development) {
            return "Software Development";
        }
        else if (department == Department.Technical_Support) {
            return "Technical Support";
        }
        else if(department == Department.Information_Security) {
            return "Information Security";
        }
        else if(department == Department.Quality_Assurance) {
            return "Quality Assurance";
        }
        else if(department == Department.Project_Management) {
            return "Project Management";
        }
        else {
            return null;
        }
    }

    public void onVerifyQuestsButton() {
        try {
            labelVerifyQuests.setStyle("-fx-font-weight: bold; -fx-font-style: italic; -fx-cursor: hand");
            labelRanking.setStyle("-fx-cursor: hand");
            hboxVerifyQuests.setVisible(true);
            hboxRanking.setVisible(false);
            textAreaVerifyQuests.setText("Select a quest to see it's details!");
            labelErrors.setText("");

            employeeQuestModel = FXCollections.observableArrayList(questService.getSubmittedQuests(loggedUser));
            tableViewVerifyQuests.setItems(employeeQuestModel);
        }
        catch(Exception e) {
            labelErrors.setText(e.getMessage());
        }
    }

    public void onRankingButton() {
        try {
            labelVerifyQuests.setStyle("-fx-cursor: hand");
            labelRanking.setStyle("-fx-font-weight: bold; -fx-font-style: italic; -fx-cursor: hand");
            hboxVerifyQuests.setVisible(false);
            hboxRanking.setVisible(true);
            buttonValidate.setVisible(false);
            buttonDecline.setVisible(false);
            labelErrors.setText("");
            labelLink.setText("");

            employeeModel = FXCollections.observableArrayList(employeeService.sortedEmployeeListByPoints(loggedUser.getDepartment()));
            tableViewRanking.setItems(employeeModel);
        }
        catch(Exception e) {
            labelErrors.setText(e.getMessage());
        }
    }

    public void onButtonValidate() {
        try {
            EmployeeQuest employeeQuest = tableViewVerifyQuests.getSelectionModel().getSelectedItem();
            Quest oldQuest = questService.read(employeeQuest.getQuestId());
            Quest newQuest = questService.read(employeeQuest.getQuestId());
            newQuest.setApproval(true);
            Employee oldEmployee = employeeService.read(employeeQuest.getEmployeeId());
            Employee newEmployee = employeeService.read(employeeQuest.getEmployeeId());
            newEmployee.setCollectedPoints(newEmployee.getCollectedPoints() + employeeQuest.getQuestPoints());
            employeeService.update(oldEmployee, newEmployee);
            questService.update(oldQuest, newQuest);
            textAreaVerifyQuests.setText("Select a quest to see it's details!");
            labelLink.setText("");
            buttonValidate.setVisible(false);
            buttonDecline.setVisible(false);
            labelErrors.setText("Selected quest was successfully validated!");

            employeeQuestModel = FXCollections.observableArrayList(questService.getSubmittedQuests(loggedUser));
            tableViewVerifyQuests.setItems(employeeQuestModel);
        } catch (Exception e) {
            labelErrors.setText(e.getMessage());
        }
    }

    public void onButtonDecline() {
        try {
            EmployeeQuest employeeQuest = tableViewVerifyQuests.getSelectionModel().getSelectedItem();
            Quest oldQuest = questService.read(employeeQuest.getQuestId());
            Quest newQuest = questService.read(employeeQuest.getQuestId());
            newQuest.setLink(null);
            questService.update(oldQuest, newQuest);
            textAreaVerifyQuests.setText("Select a quest to see it's details!");
            labelLink.setText("");
            buttonValidate.setVisible(false);
            buttonDecline.setVisible(false);
            labelErrors.setText("Selected quest was declined!");

            employeeQuestModel = FXCollections.observableArrayList(questService.getSubmittedQuests(loggedUser));
            tableViewVerifyQuests.setItems(employeeQuestModel);
        } catch (Exception e) {
            labelErrors.setText(e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String name = loggedUser.getFirstName() + " " + loggedUser.getLastName();
        labelYourName.setText(name);
        String label = "Manager of the " + fromDepartmentToString(loggedUser.getDepartment()) + " department";
        labelDepartment.setText(label);

        tableColumnFirstNameVerifyQuests.setCellValueFactory(new PropertyValueFactory<EmployeeQuest, String>("EmployeeFirstName"));
        tableColumnLastNameVerifyQuests.setCellValueFactory(new PropertyValueFactory<EmployeeQuest, String>("EmployeeLastName"));
        tableColumnEmailVerifyQuests.setCellValueFactory(new PropertyValueFactory<EmployeeQuest, String>("EmployeeEmail"));
        tableColumnQuestNameVerifyQuests.setCellValueFactory(new PropertyValueFactory<EmployeeQuest, String>("QuestName"));
        tableColumnPointsVerifyQuests.setCellValueFactory(new PropertyValueFactory<EmployeeQuest, Integer>("QuestPoints"));
        tableViewVerifyQuests.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                textAreaVerifyQuests.setText(newSelection.getQuestDetails());
                labelLink.setText("Link: " + newSelection.getQuestLink());
                buttonValidate.setVisible(true);
                buttonDecline.setVisible(true);
            }
        });

        tableColumnFirstNameRanking.setCellValueFactory(new PropertyValueFactory<Employee, String>("FirstName"));
        tableColumnLastNameRanking.setCellValueFactory(new PropertyValueFactory<Employee, String>("LastName"));
        tableColumnEmailRanking.setCellValueFactory(new PropertyValueFactory<Employee, String>("Email"));
        tableColumnPointsRanking.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("CollectedPoints"));
        tableColumnBadgeRanking.setCellValueFactory(new PropertyValueFactory<Employee, String>("Badge"));

        onVerifyQuestsButton();
    }
}
