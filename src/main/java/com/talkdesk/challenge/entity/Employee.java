package com.talkdesk.challenge.entity;

import com.talkdesk.challenge.mapper.EmployeeMapper;
import com.talkdesk.challenge.model.EmployeeDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.Instant;

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

    @NotNull
    @JsonbDateFormat( "yyyy-MM-dd HH:mm:ss")
    @Column(name = "startDate")
    private Instant startDate;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "team")
    private String team;

    @NotNull
    @Column(name = "tittle")
    private String tittle;

    public Employee convertToEntity(EmployeeDTO source) {
        return EmployeeMapper.INSTANCE.employeeDTOToEmployee(source);
    }

}
