package com.example.demo.service;

import com.example.demo.domain.models.*;
import com.example.demo.domain.validators.QuestValidator;
import com.example.demo.domain.validators.ValidationException;
import com.example.demo.repository.QuestRepository;
import com.example.demo.domain.validators.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuestService {
    Validator<Quest> validator;

    private QuestRepository questRepository = QuestRepository.getInstance();
    private EmployeeService employeeService = EmployeeService.getInstance();

    private static QuestService instance = null;

    private QuestService() {
        this.validator = new QuestValidator();
    }

    public static QuestService getInstance() {
        if(instance == null) {
            instance = new QuestService();
        }
        return instance;
    }

    /**
     * Generates the id for the next entity
     * @return Integer - id
     */
    public Integer idGenerator() {
        Integer max = 0;
        if(questRepository.read().size() == 0) {
            return 1;
        }
        else {
            List<Quest> quests = questRepository.read();
            for(Quest q : quests) {
                if(q.getId() > max) {
                    max = q.getId();
                }
            }
        }
        return max + 1;
    }

    public void create(Quest entity) throws Exception {
        List<Quest> quests = questRepository.read();
        for(Quest q : quests) {
            if(Objects.equals(entity.getId(), q.getId())) {
                throw new Exception("A quest with this ID already exists.");
            }
        }
        validator.validate(entity);
        questRepository.create(entity);
    }

    public void update(Quest oldEntity, Quest newEntity) {
        validator.validate(newEntity);
        questRepository.update(oldEntity, newEntity);
    }

    public List<Quest> read() throws Exception {
        if(questRepository.read().size() == 0) {
            throw new Exception("Quest list is empty.");
        }
        return questRepository.read();
    }

    public Quest read(Integer id) throws Exception {
        if(questRepository.read(id) == null) {
            throw new Exception("A quest with given id doesn't exist.");
        }
        return questRepository.read(id);
    }

    public void delete(Integer id) throws Exception {
        if(questRepository.read(id) == null) {
            throw new Exception("A quest with given ID doesn't exist.");
        }
        Quest deletedQuest = questRepository.read(id);
        questRepository.delete(deletedQuest);
    }


    /**
     * Returns a list with all unassigned quests for a specified department
     * @param department department from Department enum
     * @return list with all unassigned quests for a specified department
     */
    public List<Quest> getAllQuestsForDepartment(Department department) {
        List<Quest> quests = questRepository.read();
        List<Quest> filteredQuests = new ArrayList<Quest>();
        for(Quest q : quests) {
            if(q.getDepartment() ==  department && q.getEmployeeId() == 0) {
                filteredQuests.add(q);
            }
        }
        return filteredQuests;
    }

    /**
     * Returns a list with all completed quests for a specified employee
     * @param employeeId employee's id
     * @return list with all completed quests for given employee, empty list otherwise
     */
    public List<Quest> getAllCompletedQuestsForEmployee(Integer employeeId) {
        List<Quest> quests = questRepository.read();
        List<Quest> filteredQuests = new ArrayList<Quest>();
        for(Quest q : quests) {
            if(Objects.equals(q.getEmployeeId(), employeeId) && q.getApproval()) {
                filteredQuests.add(q);
            }
        }
        return filteredQuests;
    }

    /**
     * Returns a list with ongoing quests for a specified employee
     * @param employeeId employee's id
     * @return list with all ongoing quests for a specified employee, empty list otherwise
     */
    public List<Quest> getAllCurrentQuestsForEmployee(Integer employeeId) {
        List<Quest> quests = questRepository.read();
        List<Quest> filteredQuests = new ArrayList<Quest>();
        for(Quest q : quests) {
            if(Objects.equals(q.getEmployeeId(), employeeId) && !q.getApproval()) {
                filteredQuests.add(q);
            }
        }
        return filteredQuests;
    }

    /**
     * Creates a quest with the creator id of our given employee
     * @param employee given employee
     * @param quest the quest that's going to be created
     */
    public void createQuestByEmployee(Employee employee, Quest quest) {
        if(employee.getCollectedPoints() < quest.getPrizePoints()) {
            throw new ValidationException("You do not have the necessary points to create the quest.");
        }
        Employee newEmployee = employeeService.read(employee.getId());
        Integer updatedCollectedPoints = employee.getCollectedPoints() - quest.getPrizePoints();
        newEmployee.setCollectedPoints(updatedCollectedPoints);
        validator.validate(quest);
        questRepository.create(quest);
        employeeService.update(employee, newEmployee);
    }

    /**
     * Returns a list with all submitted quests for a specified department (in this case the department that the
     * logged admin is being a part of)
     * @param admin logged admin
     * @return list with all submitted quests by employees in this department, empty list otherwise
     */
    public List<EmployeeQuest> getSubmittedQuests(Admin admin) {
        List<Quest> quests = questRepository.read();
        List<EmployeeQuest> employeeQuests = new ArrayList<EmployeeQuest>();
        for(Quest q : quests) {
            if(q.getLink() != null && q.getDepartment() == admin.getDepartment() && q.getApproval() == false) {
                Employee employee = employeeService.read(q.getEmployeeId());
                EmployeeQuest employeeQuest = new EmployeeQuest(employee.getId(), q.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmail(), q.getName(), q.getDetails(), q.getLink(), q.getPrizePoints());
                employeeQuests.add(employeeQuest);
            }
        }
        return employeeQuests;
    }
}
