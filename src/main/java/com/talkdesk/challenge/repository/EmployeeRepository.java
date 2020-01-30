package com.talkdesk.challenge.repository;

import com.talkdesk.challenge.entity.Employee;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static javax.transaction.Transactional.TxType.REQUIRED;

@ApplicationScoped
@Transactional(REQUIRED)
public class EmployeeRepository {

    @Inject
    private EntityManager em;


    public List<Employee> create(List<Employee> employeeList) {
        return employeeList.stream()
                .map(employee -> {
                    em.persist(employee);
                    return employee;
                }).collect(toList());
    }

    public void delete(Long id) {
        ofNullable(em.find(Employee.class, id))
                .ifPresent(employee -> em.remove(employee));
    }

    public Optional<Employee> find(Long id) {
        return Optional.of(em.find(Employee.class, id));

    }

    public void update(Employee employeeToUpdate) {
        em.merge(employeeToUpdate);

    }

}
