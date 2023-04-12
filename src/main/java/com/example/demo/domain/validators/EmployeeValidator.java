package com.example.demo.domain.validators;

import com.example.demo.domain.models.Employee;

public class EmployeeValidator implements Validator<Employee> {
    @Override
    public void validate(Employee entity) throws ValidationException {
        if(entity.getId() <= 0) {
            throw new ValidationException("Id must be a positive integer.");
        }
        else if(!entity.getFirstName().matches("^[a-zA-Z]+$")) {
            throw new ValidationException("First name must contain only letters.");
        }
        else if(!entity.getLastName().matches("^[a-zA-Z]+$")) {
            throw new ValidationException("Last name must contain only letters.");
        }
        else if(entity.getAge() < 18) {
            throw new ValidationException("Age must be over 18 years old.");
        }
        else if(entity.getPassword().length() < 8) {
            throw new ValidationException("Weak password!");
        }
        else if(!entity.getEmail().contains("@blue.com")) {
            throw new ValidationException("Invalid email!");
        }
    }
}
