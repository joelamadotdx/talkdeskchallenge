package com.talkdesk.challenge.repository;

import com.talkdesk.challenge.entity.Employee;
import com.talkdesk.challenge.mapper.EmployeeMapper;
import com.talkdesk.challenge.util.EnumTeam;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;


@QuarkusTest
public class EmployeeRepositoryIT {

    @Inject
    EmployeeRepository employeeRepository;

    @Test
    void create() {

        Employee employee = Employee.builder()
                .name("Bruno Baptista")
                .team(EnumTeam.TEAM_A.toString())
                .tittle("admin").build();
        Employee employee2 = Employee.builder()
                .name("Joel Amado")
                .team(EnumTeam.TEAM_B.toString())
                .tittle("Agent").build();
        Employee employee3 = Employee.builder()
                .name("João Rafael")
                .team(EnumTeam.TEAM_A.toString())
                .tittle("admin").build();
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        employeeList.add(employee2);
        employeeList.add(employee3);

        employeeRepository.create(employeeList);
        IntStream.range(0, employeeList.size()).forEach(i -> {
            assertNotNull(employeeList.get(i).getEmployeeId());
        });
    }

    @Test
    void find() {
        final Optional<Employee> employee = employeeRepository.find(9L);
        assertTrue(employee.isPresent());
    }

    @Test
    void delete() {
        final Optional<Employee> employee = employeeRepository.find(8L);
        assertTrue(employee.isPresent());
        employeeRepository.delete(8l);
        assertFalse(employeeRepository.find(8L).isPresent());
    }

    @Test
    void update() {
        Employee employeeToUpdate = Employee.builder()
                .name("Bruno Baptista Update Test")
                .team(EnumTeam.TEAM_A.toString())
                .tittle("admin").build();
        final Optional<Employee> employee = employeeRepository.find(12L);
        assertTrue(employee.isPresent());
        employeeToUpdate.setEmployeeId(employee.get().getEmployeeId());
        employeeRepository.update(employeeToUpdate);
        final Optional<Employee> employeeUpdated = employeeRepository.find(employeeToUpdate.getEmployeeId());
        assertEquals(employeeUpdated.get().getName(), "Bruno Baptista Update Test");
    }

}
