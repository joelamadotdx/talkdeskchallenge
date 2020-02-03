package com.talkdesk.challenge.services;

import com.talkdesk.challenge.entity.Employee;
import com.talkdesk.challenge.mapper.EmployeeMapper;
import com.talkdesk.challenge.model.EmployeeDTO;
import com.talkdesk.challenge.repository.EmployeeRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;


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
    public List<EmployeeDTO> createEmployee(List<EmployeeDTO> employeeList) {
        return EmployeeMapper.INSTANCE.employeeListToEmployeeDTOList(this.repository.create(employeeList.stream()
                .map(s -> Employee.builder()
                        .name(s.getName())
                        .startDate(s.getStartDate())
                        .team(s.getTeam())
                        .tittle(s.getTittle())
                        .build())
                .collect(toList())));
    }

    public Optional<EmployeeDTO> findEmployee(Long id) {
        return this.repository.find(id)
                .map(employee -> EmployeeDTO.builder()
                        .name(employee.getName())
                        .startDate(employee.getStartDate())
                        .team(employee.getTeam())
                        .tittle(employee.getTittle())
                        .build());
    }

    public void updateEmployee(final Long id, EmployeeDTO employeeDTO) {
        final Optional<Employee> employee = repository.find(id);
        Employee employee234 = EmployeeMapper.INSTANCE.employeeDTOToEmployee(employeeDTO);
        employee234.setEmployeeId(employee.get().getEmployeeId());
        this.repository.update(employee234);

        repository.find(id)
                .map(employee2 -> {
                    Employee employee1 = EmployeeMapper.INSTANCE.employeeDTOToEmployee(employeeDTO);
                    employee1.setEmployeeId(employee.get().getEmployeeId());
                    this.repository.update(employee1);
                    return employee1;
                }).orElseThrow();

    }

    /**
     * Delete employee.
     */
    public void deleteEmployee(Long idList) {
        this.repository.delete(idList);
    }

}
