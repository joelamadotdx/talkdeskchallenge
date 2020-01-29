package com.talkdesk.challenge.model;


import com.talkdesk.challenge.entity.Employee;
import com.talkdesk.challenge.mapper.EmployeeMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbProperty;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    @JsonbProperty("startDate")
    private Timestamp startDate;
    @JsonbProperty("name")
    private String name;
    @JsonbProperty("team")
    private String team;
    @JsonbProperty("tittle")
    private String tittle;

    public EmployeeDTO convertToDTO(Employee source) {
        return EmployeeMapper.INSTANCE.employeeToEmployeeDTO(source);
    }

}
