package com.talkdesk.challenge.services;

import com.talkdesk.challenge.entity.Employee;
import com.talkdesk.challenge.model.EmployeeDTO;
import com.talkdesk.challenge.repository.EmployeeRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;


/**
 * The type Employee services.
 */
@ApplicationScoped
public class EmployeeServices {

    /**
     * The Repository.
     */
    @Inject
    private EmployeeRepository repository;

    /**
     * Create employee.
     */
    public void createEmployee(List<EmployeeDTO> employeeList) {
        List<Employee> employees = employeeList.stream().map(s -> Employee.builder().name(s.getName()).startDate(s.getStartDate()).team(s.getTeam()).tittle(s.getTittle()).build()).collect(Collectors.toList());
        this.repository.create(employees);
    }

    public void findEmployee(Long id) {
        this.repository.find(id);
    }

    public void updateEmployee(Long id) {
        this.repository.update(id);
    }
    /**
     * Delete employee.
     */
    public void deleteEmployee(Long idList) {
        this.repository.delete(idList);
    }

}
