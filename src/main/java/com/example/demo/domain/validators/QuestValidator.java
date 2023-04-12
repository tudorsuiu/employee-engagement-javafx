package com.example.demo.domain.validators;

import com.example.demo.domain.models.Quest;

import java.sql.Date;
import java.time.LocalDate;

public class QuestValidator implements Validator<Quest> {
    @Override
    public void validate(Quest entity) throws ValidationException {
        if(entity.getId() <= 0) {
            throw new ValidationException("Id must be a positive integer.");
        }
        else if(entity.getName().isEmpty()) {
            throw new ValidationException("Name section should not be empty.");
        }
        else if(entity.getDetails().isEmpty()) {
            throw new ValidationException("Details section should not be empty.");
        }
        else if(entity.getPrizePoints() < 5) {
            throw new ValidationException("The prize should be at least 5 points.");
        }
        else if(entity.getDeadline().before(Date.valueOf(LocalDate.now()))) {
            throw new ValidationException("Deadline is invalid.");
        }
        else if(entity.getDetails().length() > 2340) {
            throw new ValidationException("The detail section should contain maximum 2350 characters.");
        }
    }
}
