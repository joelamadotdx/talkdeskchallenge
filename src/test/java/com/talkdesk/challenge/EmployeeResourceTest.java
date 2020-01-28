package com.talkdesk.challenge;


import com.talkdesk.challenge.entity.Employee;
import com.talkdesk.challenge.model.EmployeeDTO;
import com.talkdesk.challenge.services.EmployeeServices;
import com.talkdesk.challenge.util.EnumTeam;
import io.quarkus.test.Mock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class EmployeeResourceTest {

    @Mock
    private EmployeeServices employeeServices;

    @Test
    public void create() {
     /*  List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("Bruno Baptista");
        employeeDTO.setStartDate(Timestamp.valueOf("2019-10-26 02:45:00"));
        employeeDTO.setTeam(EnumTeam.TEAM_B.toString());
        employeeDTO.setTittle("Admin");
        employeeDTOList.add(employeeDTO);

        //create a employee
        given()
                .body(employeeDTOList)
                .log().all()
                .contentType("application/json")
                .when().post("/data/employee/create")
                .then()
                .statusCode(201);

      */
    }

    @Test
    public void delete() {

       /* Long id = 6L;

        given()
                .pathParam("id", id)
                .contentType("application/json")
                .when().delete("/data/employee/delete/")
                .then()
                .statusCode(202);
*/
    }

}
