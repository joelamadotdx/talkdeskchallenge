package com.talkdesk.challenge.repository;

import com.talkdesk.challenge.entity.Employee;
import com.talkdesk.challenge.util.EnumTeam;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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
    void delete() {
        final Optional<Employee> employee = employeeRepository.find(11L);
        assertNotNull(employee);
        employeeRepository.delete(11l);
        assertNull(employeeRepository.find(10L));

    }
}
