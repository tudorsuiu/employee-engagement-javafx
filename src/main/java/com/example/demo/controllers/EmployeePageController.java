package com.example.demo.controllers;

import com.example.demo.domain.instances.CurrentEmployee;
import com.example.demo.domain.models.Department;
import com.example.demo.domain.models.Employee;
import com.example.demo.domain.models.Quest;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.QuestService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class EmployeePageController implements Initializable {
    private Employee loggedUser = CurrentEmployee.getInstance().getCurrentEmployee();
    private final QuestService questService = QuestService.getInstance();
    private final EmployeeService employeeService = EmployeeService.getInstance();
    private ObservableList<Quest> model;
    @FXML
    Label labelYourName;
    @FXML
    Label labelPoints;
    @FXML
    Label labelAllQuests;
    @FXML
    Label labelCurrentQuests;
    @FXML
    Label labelCompletedQuests;
    @FXML
    Label labelCreateQuest;
    @FXML
    Label labelShop;
    @FXML
    TableView<Quest> tableViewAllQuests;
    @FXML
    TableColumn<Quest, String> tableColumnNameAllQuests;
    @FXML
    TableColumn<Quest, Integer> tableColumnPointsAllQuests;
    @FXML
    TableColumn<Quest, Date> tableColumnDeadlineAllQuests;
    @FXML
    Button buttonAcceptQuest;
    @FXML
    Label labelDepartment;
    @FXML
    Label labelErrors;
    @FXML
    HBox hboxAllQuests;
    @FXML
    Button buttonSubmit;
    @FXML
    TextField textFieldLink;
    @FXML
    HBox hboxCreateQuest;
    @FXML
    TextField textFieldName;
    @FXML
    TextArea textAreaDetails;
    @FXML
    TextField textFieldPrizePoints;
    @FXML
    DatePicker datePicker;
    @FXML
    TableView<Quest> tableViewCurrentQuests;
    @FXML
    TableColumn<Quest, String> tableColumnNameCurrentQuests;
    @FXML
    TableColumn<Quest, Integer> tableColumnPointsCurrentQuests;
    @FXML
    TableColumn<Quest, Date> tableColumnDeadlineCurrentQuests;
    @FXML
    TableView<Quest> tableViewCompletedQuests;
    @FXML
    TableColumn<Quest, String> tableColumnNameCompletedQuests;
    @FXML
    TableColumn<Quest, Integer> tableColumnPointsCompletedQuests;
    @FXML
    TableColumn<Quest, Date> tableColumnDeadlineCompletedQuests;
    @FXML
    Text textAreaCompletedQuests;
    @FXML
    Text textAreaCurrentQuests;
    @FXML
    Text textAreaAllQuests;
    @FXML
    HBox hboxCurrentQuests;
    @FXML
    HBox hboxCompletedQuests;
    @FXML
    HBox hboxShop;
    @FXML
    Label buttonBuyMousepad;
    @FXML
    Label buttonBuyMouse;
    @FXML
    Label buttonBuyKeyboard;
    @FXML
    Label buttonBuyDayOff;
    @FXML
    Label buttonBuyMerchandise;
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

    public void onButtonAcceptQuest() {
        try {
            Quest oldQuest = tableViewAllQuests.getSelectionModel().getSelectedItem();
            Quest newQuest = tableViewAllQuests.getSelectionModel().getSelectedItem();
            newQuest.setEmployeeId(loggedUser.getId());
            questService.update(oldQuest, newQuest);

            textAreaAllQuests.setText("Select a quest to see it's details!");
            labelErrors.setText("");
            buttonAcceptQuest.setVisible(false);

            model = FXCollections.observableArrayList(questService.getAllQuestsForDepartment(loggedUser.getDepartment()));
            tableViewAllQuests.setItems(model);
        }
        catch (Exception e) {
            labelErrors.setText(e.getMessage());
        }
    }

    public void onSubmitButton() {
        try {
            labelErrors.setText("Quest solution succesfully submitted.");
            Quest oldQuest = tableViewCurrentQuests.getSelectionModel().getSelectedItem();
            Quest newQuest = tableViewCurrentQuests.getSelectionModel().getSelectedItem();
            String submittedLink = textFieldLink.getText();
            if(textFieldLink.getText().isEmpty()) {
                throw new Exception("You cannot submit without inserting a link.");
            }
            newQuest.setLink(submittedLink);
            questService.update(oldQuest, newQuest);
            textFieldLink.clear();
            textFieldLink.setVisible(false);
            buttonSubmit.setVisible(false);
            textAreaCurrentQuests.setText("Select a quest to see it's details!");

            model = FXCollections.observableArrayList(questService.getAllCurrentQuestsForEmployee(loggedUser.getId()));
            tableViewCurrentQuests.setItems(model);
        }
        catch(Exception e) {
            labelErrors.setText(e.getMessage());
            textFieldLink.clear();
        }
    }

    public void onCreateQuestButton() {
        try {
            String name = textFieldName.getText();
            String details = textAreaDetails.getText();
            Integer prizePoints = Integer.valueOf(textFieldPrizePoints.getText());
            Date deadline = Date.valueOf(datePicker.getValue());
            Quest quest = new Quest(questService.idGenerator(), name, details, loggedUser.getDepartment(), prizePoints, loggedUser.getId(), 0, deadline, false, null);
            questService.createQuestByEmployee(loggedUser, quest);

            loggedUser = employeeService.read(loggedUser.getId());
            updateLoggedUserInfo(loggedUser);

            textFieldName.clear();
            textAreaDetails.clear();
            textFieldPrizePoints.clear();
            datePicker.setValue(null);
        } catch (Exception e) {
            labelErrors.setText(e.getMessage());
        }
    }

    public void onLabelAllQuests() {
        try {
            labelAllQuests.setStyle("-fx-font-weight: bold; -fx-font-style: italic; -fx-cursor: hand");
            labelCurrentQuests.setStyle("-fx-cursor: hand");
            labelCompletedQuests.setStyle("-fx-cursor: hand");
            labelCreateQuest.setStyle("-fx-cursor: hand");
            labelShop.setStyle("-fx-cursor: hand");

            hboxAllQuests.setVisible(true);
            hboxCurrentQuests.setVisible(false);
            hboxCompletedQuests.setVisible(false);
            hboxCreateQuest.setVisible(false);
            hboxShop.setVisible(false);
            buttonAcceptQuest.setVisible(false);
            textFieldLink.setVisible(false);
            buttonSubmit.setVisible(false);
            textAreaAllQuests.setText("Select a quest to see it's details!");
            labelErrors.setText("");

            model = FXCollections.observableArrayList(questService.getAllQuestsForDepartment(loggedUser.getDepartment()));
            tableViewAllQuests.setItems(model);
        }
        catch (Exception e) {
            labelErrors.setText(e.getMessage());
        }
    }

    public void onLabelCurrentQuests() {
        try {
            labelAllQuests.setStyle("-fx-cursor: hand");
            labelCurrentQuests.setStyle("-fx-font-weight: bold; -fx-font-style: italic; -fx-cursor: hand");
            labelCompletedQuests.setStyle("-fx-cursor: hand");
            labelCreateQuest.setStyle("-fx-cursor: hand");
            labelShop.setStyle("-fx-cursor: hand");

            hboxAllQuests.setVisible(false);
            hboxCurrentQuests.setVisible(true);
            hboxCompletedQuests.setVisible(false);
            hboxCreateQuest.setVisible(false);
            hboxShop.setVisible(false);
            buttonAcceptQuest.setVisible(false);
            textAreaCurrentQuests.setText("Select a quest to see it's details!");
            labelErrors.setText("");

            model = FXCollections.observableArrayList(questService.getAllCurrentQuestsForEmployee(loggedUser.getId()));
            tableViewCurrentQuests.setItems(model);
        }
        catch (Exception e) {
            labelErrors.setText(e.getMessage());
        }
    }

    public void onLabelCompletedQuests() {
        try {
            labelAllQuests.setStyle("-fx-cursor: hand");
            labelCurrentQuests.setStyle("-fx-cursor: hand");
            labelCompletedQuests.setStyle("-fx-font-weight: bold; -fx-font-style: italic; -fx-cursor: hand");
            labelCreateQuest.setStyle("-fx-cursor: hand");
            labelShop.setStyle("-fx-cursor: hand");

            hboxAllQuests.setVisible(false);
            hboxCurrentQuests.setVisible(false);
            hboxCompletedQuests.setVisible(true);
            hboxCreateQuest.setVisible(false);
            hboxShop.setVisible(false);
            buttonAcceptQuest.setVisible(false);
            textFieldLink.setVisible(false);
            buttonSubmit.setVisible(false);
            textAreaCompletedQuests.setText("Select a quest to see it's details!");
            labelErrors.setText("");

            model = FXCollections.observableArrayList(questService.getAllCompletedQuestsForEmployee(loggedUser.getId()));
            tableViewCompletedQuests.setItems(model);
        }
        catch (Exception e) {
            labelErrors.setText(e.getMessage());
        }

    }

    public void onLabelCreateQuest() {
        try {
            labelAllQuests.setStyle("-fx-cursor: hand");
            labelCurrentQuests.setStyle("-fx-cursor: hand");
            labelCompletedQuests.setStyle("-fx-cursor: hand");
            labelCreateQuest.setStyle("-fx-font-weight: bold; -fx-font-style: italic; -fx-cursor: hand");
            labelShop.setStyle("-fx-cursor: hand");

            hboxAllQuests.setVisible(false);
            hboxCurrentQuests.setVisible(false);
            hboxCompletedQuests.setVisible(false);
            hboxCreateQuest.setVisible(true);
            hboxShop.setVisible(false);
            buttonAcceptQuest.setVisible(false);
            textFieldLink.setVisible(false);
            buttonSubmit.setVisible(false);
            labelErrors.setText("");
        }
        catch (Exception e) {
            labelErrors.setText(e.getMessage());
        }
    }

    public void onLabelShop() {
        try {
            labelErrors.setText("");
            labelAllQuests.setStyle("-fx-cursor: hand");
            labelCurrentQuests.setStyle("-fx-cursor: hand");
            labelCompletedQuests.setStyle("-fx-cursor: hand");
            labelCreateQuest.setStyle("-fx-cursor: hand");
            labelShop.setStyle("-fx-font-weight: bold; -fx-font-style: italic; -fx-cursor: hand");

            hboxAllQuests.setVisible(false);
            hboxCurrentQuests.setVisible(false);
            hboxCompletedQuests.setVisible(false);
            hboxCreateQuest.setVisible(false);
            hboxShop.setVisible(true);
            buttonAcceptQuest.setVisible(false);
            textFieldLink.setVisible(false);
            buttonSubmit.setVisible(false);
            labelErrors.setText("");
        }
        catch (Exception e) {
            labelErrors.setText(e.getMessage());
        }
    }

    public void onButtonBuyMerchandise() {
        try {
            labelErrors.setText("");
            if(loggedUser.getCollectedPoints() >= 50) {
                Integer updatedCollectedPoints = loggedUser.getCollectedPoints() - 50;
                Employee updatedLoggedUser = loggedUser;
                updatedLoggedUser.setCollectedPoints(updatedCollectedPoints);
                employeeService.update(loggedUser, updatedLoggedUser);
                loggedUser = employeeService.read(loggedUser.getId());
                updateLoggedUserInfo(loggedUser);
                labelErrors.setText("Merchandise bought succesfully!");
            }
            else {
                labelErrors.setText("You do not have the necessary points.");
            }
        }
        catch(Exception e) {
            labelErrors.setText(e.getMessage());
        }
    }

    public void onButtonBuyMousepad() {
        try {
            labelErrors.setText("");
            if(loggedUser.getCollectedPoints() >= 75) {
                Integer updatedCollectedPoints = loggedUser.getCollectedPoints() - 75;
                Employee updatedLoggedUser = loggedUser;
                updatedLoggedUser.setCollectedPoints(updatedCollectedPoints);
                employeeService.update(loggedUser, updatedLoggedUser);
                loggedUser = employeeService.read(loggedUser.getId());
                updateLoggedUserInfo(loggedUser);
                labelErrors.setText("Mousepad bought succesfully!");
            }
            else {
                labelErrors.setText("You do not have the necessary points.");
            }
        }
        catch(Exception e) {
            labelErrors.setText(e.getMessage());
        }
    }

    public void onButtonBuyMouse() {
        try {
            labelErrors.setText("");
            if(loggedUser.getCollectedPoints() >= 100) {
                Integer updatedCollectedPoints = loggedUser.getCollectedPoints() - 100;
                Employee updatedLoggedUser = loggedUser;
                updatedLoggedUser.setCollectedPoints(updatedCollectedPoints);
                employeeService.update(loggedUser, updatedLoggedUser);
                loggedUser = employeeService.read(loggedUser.getId());
                updateLoggedUserInfo(loggedUser);
                labelErrors.setText("Mouse bought succesfully!");
            }
            else {
                labelErrors.setText("You do not have the necessary points.");
            }
        }
        catch(Exception e) {
            labelErrors.setText(e.getMessage());
        }

    }

    public void onButtonBuyKeyboard() {
        try {
            labelErrors.setText("");
            if(loggedUser.getCollectedPoints() >= 150) {
                Integer updatedCollectedPoints = loggedUser.getCollectedPoints() - 150;
                Employee updatedLoggedUser = loggedUser;
                updatedLoggedUser.setCollectedPoints(updatedCollectedPoints);
                employeeService.update(loggedUser, updatedLoggedUser);
                loggedUser = employeeService.read(loggedUser.getId());
                updateLoggedUserInfo(loggedUser);
                labelErrors.setText("Keyboard bought succesfully!");
            }
            else {
                labelErrors.setText("You do not have the necessary points.");
            }
        }
        catch(Exception e) {
            labelErrors.setText(e.getMessage());
        }
    }

    public void onButtonBuyDayOff() {
        try {
            labelErrors.setText("");
            if(loggedUser.getCollectedPoints() >= 300) {
                Integer updatedCollectedPoints = loggedUser.getCollectedPoints() - 300;
                Employee updatedLoggedUser = loggedUser;
                updatedLoggedUser.setCollectedPoints(updatedCollectedPoints);
                employeeService.update(loggedUser, updatedLoggedUser);
                loggedUser = employeeService.read(loggedUser.getId());
                updateLoggedUserInfo(loggedUser);
                labelErrors.setText("Day off bought succesfully!");
            }
            else {
                labelErrors.setText("You do not have the necessary points.");
            }
        }
        catch(Exception e) {
            labelErrors.setText(e.getMessage());
        }
    }

    private void updateLoggedUserInfo(Employee userToBeUpdated) {
        labelPoints.setText(userToBeUpdated.getCollectedPoints() + " points");
        labelDepartment.setText(fromDepartmentToString(loggedUser.getDepartment()) + " - Badge: " + loggedUser.getBadge());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String name = loggedUser.getFirstName() + " " + loggedUser.getLastName();
        labelYourName.setText(name);
        String points = loggedUser.getCollectedPoints() + " points";
        labelPoints.setText(points);
        String label = fromDepartmentToString(loggedUser.getDepartment()) + " - Badge: " + loggedUser.getBadge();
        labelDepartment.setText(label);

        tableColumnNameAllQuests.setCellValueFactory(new PropertyValueFactory<Quest, String>("Name"));
        tableColumnPointsAllQuests.setCellValueFactory(new PropertyValueFactory<Quest, Integer>("PrizePoints"));
        tableColumnDeadlineAllQuests.setCellValueFactory(new PropertyValueFactory<Quest, Date>("Deadline"));
        tableViewAllQuests.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                textAreaAllQuests.setText(newSelection.getDetails());
                buttonAcceptQuest.setVisible(true);
            }
        });

        tableColumnNameCurrentQuests.setCellValueFactory(new PropertyValueFactory<Quest, String>("Name"));
        tableColumnPointsCurrentQuests.setCellValueFactory(new PropertyValueFactory<Quest, Integer>("PrizePoints"));
        tableColumnDeadlineCurrentQuests.setCellValueFactory(new PropertyValueFactory<Quest, Date>("Deadline"));
        tableViewCurrentQuests.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                textAreaCurrentQuests.setText(newSelection.getDetails());
                buttonSubmit.setVisible(true);
                textFieldLink.setVisible(true);
            }
        });

        tableColumnNameCompletedQuests.setCellValueFactory(new PropertyValueFactory<Quest, String>("Name"));
        tableColumnPointsCompletedQuests.setCellValueFactory(new PropertyValueFactory<Quest, Integer>("PrizePoints"));
        tableColumnDeadlineCompletedQuests.setCellValueFactory(new PropertyValueFactory<Quest, Date>("Deadline"));
        tableViewCompletedQuests.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                textAreaCompletedQuests.setText(newSelection.getDetails());
            }
        });

        this.onLabelAllQuests();
    }
}
