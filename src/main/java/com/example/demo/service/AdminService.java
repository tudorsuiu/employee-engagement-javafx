package com.example.demo.service;

import com.example.demo.domain.models.Admin;
import com.example.demo.domain.validators.AdminValidator;
import com.example.demo.domain.validators.ValidationException;
import com.example.demo.domain.validators.Validator;
import com.example.demo.repository.AdminRepository;

import java.util.List;
import java.util.Objects;

public class AdminService {
    Validator<Admin> validator;

    private AdminRepository adminRepository = AdminRepository.getInstance();

    private static AdminService instance = null;

    private AdminService() {
        this.validator = new AdminValidator();
    }

    public static AdminService getInstance() {
        if(instance == null) {
            instance = new AdminService();
        }
        return instance;
    }

    /**
     * Generates the id for the next entity
     * @return Integer - id
     */
    public Integer idGenerator() {
        Integer max = 0;
        if(adminRepository.read().size() == 0) {
            return 1;
        }
        else {
            List<Admin> admins = adminRepository.read();
            for(Admin a : admins) {
                if(a.getId() > max) {
                    max = a.getId();
                }
            }
        }
        return max + 1;
    }

    public void create(Admin entity) throws Exception {
        List<Admin> admins = adminRepository.read();
        for(Admin a : admins) {
            if(Objects.equals(entity.getId(), a.getId())) {
                throw new Exception("An admin with this ID already exists.");
            }
            if(Objects.equals(a.getEmail(), entity.getEmail())) {
                throw new Exception("An account with this email already exists.");
            }
        }
        validator.validate(entity);
        adminRepository.create(entity);
    }

    public void update(Admin oldEntity, Admin newEntity) {
        validator.validate(newEntity);
        adminRepository.update(oldEntity, newEntity);
    }

    public List<Admin> read() throws Exception {
        if(adminRepository.read().size() == 0) {
            throw new Exception("Admin list is empty.");
        }
        return adminRepository.read();
    }

    public Admin read(Integer id) throws Exception {
        if(adminRepository.read(id) == null) {
            throw new Exception("An admin with given ID doesn't exist.");
        }
        return adminRepository.read(id);
    }

    public void delete(Integer id) throws Exception {
        if(adminRepository.read(id) == null) {
            throw new Exception("An admin with given ID doesn't exist.");
        }
        Admin deletedAdmin = adminRepository.read(id);
        adminRepository.delete(deletedAdmin);
    }

    /**
     * Function for validating if an account existis into our database
     * @param email typed email
     * @param password typed password
     * @return Admin object if the credentials are correct, throws exception otherwise
     */
    public Admin validateCredentials(String email, String password) throws Exception {
        List<Admin> admins = adminRepository.read();
        for(Admin a : admins) {
            if(Objects.equals(a.getEmail(), email) && Objects.equals(a.getPassword(), password)) {
                return a;
            }
        }
        throw new Exception("Invalid credentials.");
    }
}
