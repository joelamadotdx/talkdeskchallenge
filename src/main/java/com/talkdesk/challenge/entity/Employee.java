package com.talkdesk.challenge.entity;

import com.talkdesk.challenge.mapper.EmployeeMapper;
import com.talkdesk.challenge.model.EmployeeDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class Employee {

    @Id
    @SequenceGenerator(name = "hibernate_sequence", sequenceName = "hibernate_sequence", schema = "talkdesk")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "employeeid")
    private Long employeeId;

    @Column(name = "startDate")
    private Timestamp startDate;

    @Column(name = "name")
    private String name;

    @Column(name = "team")
    private String team;

    @Column(name = "tittle")
    private String tittle;

    public Employee convertToEntity(EmployeeDTO source) {
        return EmployeeMapper.INSTANCE.employeeDTOToEmployee(source);
    }

}
