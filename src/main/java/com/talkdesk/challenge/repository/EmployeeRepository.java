package com.talkdesk.challenge.repository;

import com.talkdesk.challenge.model.Employee;
import com.talkdesk.challenge.util.EnumTeam;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;

@ApplicationScoped
@Transactional(REQUIRED)
public class EmployeeRepository {

    @Inject
    EntityManager em;

    public <T extends Employee> List<T> create() {
       List<Employee> employeeList = new ArrayList<>();
        Employee employee = new Employee();
        employee.setName("Joel Amado");
        employee.setStartDate(Timestamp.valueOf("2019-10-26 02:45:00"));
        employee.setTeam(EnumTeam.TEAM_A.toString());
        employee.setTittle("Mr");
        employeeList.add(employee);


         /*IntStream.range(0, employeeList.size()).forEach(i -> {
           em.merge(employeeList);
        });*/
         return null;
    }


}
