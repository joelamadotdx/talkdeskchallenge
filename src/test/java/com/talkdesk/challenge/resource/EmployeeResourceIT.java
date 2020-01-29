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
import static org.hamcrest.core.IsEqual.equalTo;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.HttpHeaders.CONTENT_TYPE;
import static javax.ws.rs.core.HttpHeaders.ACCEPT;
import static javax.ws.rs.core.Response.Status.*;

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

    @Test
    public void create() {

        EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .name("Bruno Baptista")
                .team(EnumTeam.TEAM_A.toString())
                .tittle("admin").build();
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        employeeDTOList.add(employeeDTO);

        //create a employee
        given()
                .body(employeeDTOList)
                .log().all()
                .when().post("/data/employee/create")
                .then()
                .statusCode(CREATED.getStatusCode());

    }

    @Test
    public void delete() {

        given()
                .contentType("application/json")
                .when().delete("/data/employee/delete/{id}", 3)
                .then()
                .statusCode(ACCEPTED.getStatusCode());


        given()
                .contentType("application/json")
                .when().get("/data/employee/find/{id}", 3)
                .then()
                .statusCode(NOT_FOUND.getStatusCode());

    }

    @Test
    public void find() {
        given()
                .contentType("application/json")
                .when().get("/data/employee/find/{id}", 4)
                .then()
                .statusCode(OK.getStatusCode());
    }


    @Test
    public void update() {

        EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .name("Bruno Baptista Update")
                .team(EnumTeam.TEAM_A.toString())
                .tittle("admin").build();

        given()
                .body(employeeDTO)
                .contentType("application/json")
                .when().put("/data/employee/update/{id}", 20)
                .then()
                .statusCode(ACCEPTED.getStatusCode());


        given()
                .contentType("application/json")
                .when().get("/data/employee/find/{id}", 20)
                .then()
                .statusCode(OK.getStatusCode())
                .body("name", equalTo("Bruno Baptista Update"));

    }

}
