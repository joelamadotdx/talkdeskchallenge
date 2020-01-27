package com.talkdesk.challenge.services;

import com.talkdesk.challenge.repository.EmployeeRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * The type Employee services.
 */
@ApplicationScoped
public class EmployeeServices {

    /**
     * The Repository.
     */
    @Inject
    EmployeeRepository repository;

    /**
     * Create employee.
     */
    public void createEmployee(){
        this.repository.create();
    }

}
