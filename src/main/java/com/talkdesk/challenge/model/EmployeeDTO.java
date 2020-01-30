package com.talkdesk.challenge.model;


import com.talkdesk.challenge.entity.Employee;
import com.talkdesk.challenge.mapper.EmployeeMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbProperty;
import java.sql.Timestamp;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    @JsonbProperty("start_date")
    private Instant startDate;
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
