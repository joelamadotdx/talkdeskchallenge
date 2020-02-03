package com.talkdesk.challenge.resource;


import com.talkdesk.challenge.model.EmployeeDTO;
import com.talkdesk.challenge.util.EnumTeam;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.Header;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.HttpHeaders.ACCEPT;
import static javax.ws.rs.core.HttpHeaders.CONTENT_TYPE;
import static javax.ws.rs.core.Response.Status.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;

@QuarkusTest
public class EmployeeResourceIT {

    private static final String APPLICATION_JSON = "application/json";

    static {
        RestAssured.filters(
                (requestSpec, responseSpec, ctx) -> {
                    requestSpec.header(new Header(CONTENT_TYPE, APPLICATION_JSON));
                    requestSpec.header(new Header(ACCEPT, APPLICATION_JSON));
                    return ctx.next(requestSpec, responseSpec);
                },
                new RequestLoggingFilter(),
                new ResponseLoggingFilter());

        RestAssured.config = RestAssured.config().objectMapperConfig(new ObjectMapperConfig(new JsonbObjectMapper()));

    }

    LocalDateTime today = LocalDateTime.now();

    @Test
    public void create() {

        EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .name("Bruno Baptista1")
                .team(EnumTeam.TEAM_A.toString())
                .tittle("admin")
                .startDate(today.atZone(ZoneId.systemDefault()).toInstant())
                .build();
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        employeeDTOList.add(employeeDTO);

        //create a employee
        EmployeeDTO[] employees = given()
                .body(employeeDTOList)
                .log().all()
                .when().post("/data/employee/create")
                .then()
                .statusCode(CREATED.getStatusCode())
                .extract().as(EmployeeDTO[].class);

        assertThat(employees.length, is(1));

    }

    @Test
    public void delete() {

        //create employee
        EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .name("Bruno Baptista1")
                .team(EnumTeam.TEAM_A.toString())
                .tittle("admin")
                .startDate(today.atZone(ZoneId.systemDefault()).toInstant())
                .build();
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        employeeDTOList.add(employeeDTO);

        EmployeeDTO[] employees = given()
                .body(employeeDTOList)
                .log().all()
                .when().post("/data/employee/create")
                .then()
                .statusCode(CREATED.getStatusCode())
                .extract().as(EmployeeDTO[].class);


        // delete employee
        given()
                .contentType("application/json")
                .when().delete("/data/employee/delete/{id}", employees[0].getEmployeeId())
                .then()
                .statusCode(ACCEPTED.getStatusCode());


        //find employee deleted, must be not_found
        given()
                .contentType("application/json")
                .when().get("/data/employee/find/{id}", employees[0].getEmployeeId())
                .then()
                .statusCode(NOT_FOUND.getStatusCode());


    }

    @Test
    public void find() {

        //create employee
        EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .name("Bruno Baptista find")
                .team(EnumTeam.TEAM_A.toString())
                .tittle("admin")
                .startDate(today.atZone(ZoneId.systemDefault()).toInstant())
                .build();
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        employeeDTOList.add(employeeDTO);

        EmployeeDTO[] employees = given()
                .body(employeeDTOList)
                .log().all()
                .when().post("/data/employee/create")
                .then()
                .statusCode(CREATED.getStatusCode())
                .extract().as(EmployeeDTO[].class);

        //find employee created
        given()
                .contentType("application/json")
                .when().get("/data/employee/find/{id}", employees[0].getEmployeeId())
                .then()
                .statusCode(OK.getStatusCode());
    }


    @Test
    public void update() {

        //create employee
        EmployeeDTO employeeDTOCreate = EmployeeDTO.builder()
                .name("Bruno Baptista Update")
                .team(EnumTeam.TEAM_A.toString())
                .tittle("admin")
                .startDate(today.atZone(ZoneId.systemDefault()).toInstant())
                .build();
        List<EmployeeDTO> employeeDTOCreateList = new ArrayList<>();
        employeeDTOCreateList.add(employeeDTOCreate);

        EmployeeDTO[] employeesCreated = given()
                .body(employeeDTOCreateList)
                .log().all()
                .when().post("/data/employee/create")
                .then()
                .statusCode(CREATED.getStatusCode())
                .extract().as(EmployeeDTO[].class);


        //find employee created
        given()
                .contentType("application/json")
                .when().get("/data/employee/find/{id}", employeesCreated[0].getEmployeeId().longValue())
                .then()
                .statusCode(OK.getStatusCode());

        //Update Employee
        EmployeeDTO employeeDTOUpdate = EmployeeDTO.builder()
                .name("Bruno Baptista Update 3")
                .employeeId(employeesCreated[0].getEmployeeId().longValue())
                .startDate(today.atZone(ZoneId.systemDefault()).toInstant())
                .build();


        given()
                .body(employeeDTOUpdate)
                .contentType("application/json")
                .when().put("/data/employee/update/{id}", employeesCreated[0].getEmployeeId())
                .then()
                .statusCode(ACCEPTED.getStatusCode());


        //find employee updated
        given()
                .contentType("application/json")
                .when().get("/data/employee/find/{id}", employeesCreated[0].getEmployeeId())
                .then()
                .statusCode(OK.getStatusCode())
                .body("name", equalTo("Bruno Baptista Update 3"));

    }

}
