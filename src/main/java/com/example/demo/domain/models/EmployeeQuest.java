package com.example.demo.domain.models;

/**
 * I created this for the TableView used for validating/declining quests submissions.
 */
public class EmployeeQuest {
    private Integer employeeId;
    private Integer questId;
    private String employeeFirstName;
    private String employeeLastName;
    private String employeeEmail;
    private String questName;
    private String questDetails;
    private String questLink;
    private Integer questPoints;

    public EmployeeQuest(Integer employeeId, Integer questId, String employeeFirstName, String employeeLastName, String employeeEmail, String questName, String questDetails, String questLink, Integer questPoints) {
        this.employeeId = employeeId;
        this.questId = questId;
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.employeeEmail = employeeEmail;
        this.questName = questName;
        this.questDetails = questDetails;
        this.questLink = questLink;
        this.questPoints = questPoints;
    }

    public EmployeeQuest() {

    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getQuestId() {
        return questId;
    }

    public void setQuestId(Integer questId) {
        this.questId = questId;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getQuestName() {
        return questName;
    }

    public void setQuestName(String questName) {
        this.questName = questName;
    }

    public String getQuestDetails() {
        return questDetails;
    }

    public void setQuestDetails(String questDetails) {
        this.questDetails = questDetails;
    }

    public String getQuestLink() {
        return questLink;
    }

    public void setQuestLink(String questLink) {
        this.questLink = questLink;
    }

    public Integer getQuestPoints() {
        return questPoints;
    }

    public void setQuestPoints(Integer questPoints) {
        this.questPoints = questPoints;
    }
}
