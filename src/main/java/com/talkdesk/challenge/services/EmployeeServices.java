package com.talkdesk.challenge.services;

import com.talkdesk.challenge.entity.Employee;
import com.talkdesk.challenge.mapper.EmployeeMapper;
import com.talkdesk.challenge.model.EmployeeDTO;
import com.talkdesk.challenge.repository.EmployeeRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
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
    EmployeeRepository repository;

    /**
     * Create employee.
     */
    public void createEmployee(List<EmployeeDTO> employeeList) {
        this.repository.create(employeeList.stream().map(s -> Employee.builder().name(s.getName()).startDate(s.getStartDate()).team(s.getTeam()).tittle(s.getTittle()).build()).collect(Collectors.toList()));
    }

    public Optional<EmployeeDTO> findEmployee(Long id) {
        return this.repository.find(id).map(employee -> EmployeeDTO.builder().name(employee.getName()).startDate(employee.getStartDate()).team(employee.getTeam()).tittle(employee.getTittle()).build());
    }

    public void updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Optional<Employee> employee= repository.find(id);
        Employee employeeToUpdate = EmployeeMapper.INSTANCE.employeeDTOToEmployee(employeeDTO);
        employeeToUpdate.setEmployeeId(employee.get().getEmployeeId());
        this.repository.update(employeeToUpdate);
    }

    /**
     * Delete employee.
     */
    public void deleteEmployee(Long idList) {
        this.repository.delete(idList);
    }

}
