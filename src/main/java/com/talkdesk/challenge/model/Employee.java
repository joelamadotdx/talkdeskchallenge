package com.talkdesk.challenge.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class Employee {

    @Id
    @SequenceGenerator(name = "hibernate_sequence", sequenceName = "hibernate_sequence",schema = "talkdesk")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "employeeId")
    private Long employeeId;

    @Column(name = "startDate")
    private Timestamp startDate;

    @Column(name = "name")
    private String name;

    @Column(name = "team")
    private String team;

    @Column(name = "tittle")
    private String tittle;
}
