package com.talkdesk.challenge.repository;

import com.talkdesk.challenge.entity.Employee;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static javax.transaction.Transactional.TxType.REQUIRED;

@ApplicationScoped
@Transactional(REQUIRED)
public class EmployeeRepository {

    @Inject
    private EntityManager em;


    public List<Employee> create(List<Employee> employeeList) {

        IntStream.range(0, employeeList.size()).forEach(i -> {
            em.persist(employeeList.get(i));
        });
        return employeeList;
    }

    public void delete(Long id) {

        Employee employee = em.find(Employee.class, id);
        if (employee != null)
            em.remove(employee);

    }

    public Optional<Employee> find(Long id) {
        return Optional.ofNullable(em.find(Employee.class, id));

    }

    public void update(Employee employeeToUpdate) {
        em.merge(employeeToUpdate);

    }

}
