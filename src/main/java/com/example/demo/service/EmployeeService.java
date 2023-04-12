package com.example.demo.service;

import com.example.demo.domain.models.Badge;
import com.example.demo.domain.models.Department;
import com.example.demo.domain.models.Employee;
import com.example.demo.domain.validators.EmployeeValidator;
import com.example.demo.domain.validators.ValidationException;
import com.example.demo.domain.validators.Validator;
import com.example.demo.repository.EmployeeRepository;

import java.util.*;

public class EmployeeService {
    Validator<Employee> validator;

    private EmployeeRepository employeeRepository = EmployeeRepository.getInstance();

    private static EmployeeService instance = null;

    private EmployeeService() {
        this.validator = new EmployeeValidator();
    }

    public static EmployeeService getInstance() {
        if(instance == null) {
            instance = new EmployeeService();
        }
        return instance;
    }

    /**
     * Generates the id for the next entity
     * @return Integer - id
     */
    public Integer idGenerator() {
        Integer max = 0;
        if(employeeRepository.read().size() == 0) {
            return 1;
        }
        else {
            List<Employee> employees = employeeRepository.read();
            for(Employee e : employees) {
                if(e.getId() > max) {
                    max = e.getId();
                }
            }
        }
        return max + 1;
    }

    public void create(Employee entity) throws Exception {
        List<Employee> employees = employeeRepository.read();
        for(Employee e : employees) {
            if(Objects.equals(entity.getId(), e.getId())) {
                throw new Exception("An employee with this ID already exists.");
            }
            else if(Objects.equals(entity.getEmail(), e.getEmail())) {
                throw new Exception("An account with this email already exists.");
            }
        }
        validator.validate(entity);
        employeeRepository.create(entity);
    }

    public void update(Employee oldEntity, Employee newEntity) {
        Integer newEntityPoints = newEntity.getCollectedPoints();
        if(newEntityPoints < 50) {
            newEntity.setBadge(Badge.Potential);
        }
        else if(newEntityPoints < 75) {
            newEntity.setBadge(Badge.Explorer);
        }
        else if(newEntityPoints < 125) {
            newEntity.setBadge(Badge.Innovator);
        }
        else if(newEntityPoints < 175) {
            newEntity.setBadge(Badge.Challenger);
        }
        else if(newEntityPoints < 250) {
            newEntity.setBadge(Badge.Visionary);
        }
        else {
            newEntity.setBadge(Badge.Mastermind);
        }
        validator.validate(newEntity);
        employeeRepository.update(oldEntity, newEntity);
    }

    public List<Employee> read() {
        if(employeeRepository.read().size() == 0) {
            throw new ValidationException("Employee list is empty.");
        }
        return employeeRepository.read();
    }

    public Employee read(Integer id) {
        if(employeeRepository.read(id) == null) {
            throw new ValidationException("An employee with given id doesn't exist!");
        }
        return employeeRepository.read(id);
    }

    public void delete(Integer id) {
        if(employeeRepository.read(id) == null) {
            throw new ValidationException("An employee with given ID doesn't exist!");
        }
        Employee deletedEmployee = employeeRepository.read(id);
        employeeRepository.delete(deletedEmployee);
    }

    public Employee validateCredentials(String email, String password) throws Exception {
        List<Employee> employees = employeeRepository.read();
        for(Employee e : employees) {
            if(Objects.equals(e.getEmail(), email) && Objects.equals(e.getPassword(), password)) {
                return e;
            }
        }
        throw new Exception("Invalid credentials.");
    }

    public List<Employee> sortedEmployeeListByPoints(Department department) {
        List<Employee> employees = employeeRepository.read();
        List<Employee> sortedEmployees = new ArrayList<>();
        for(Employee e : employees) {
            if(e.getDepartment() == department) {
                sortedEmployees.add(e);
            }
        }
        sortedEmployees.sort(Comparator.comparing(Employee::getCollectedPoints).reversed());
        return sortedEmployees;
    }
}
