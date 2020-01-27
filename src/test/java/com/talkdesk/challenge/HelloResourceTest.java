package com.talkdesk.challenge;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class HelloResourceTest {


    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("data/hello")
          .then()
             .statusCode(200)
             .body(is("Hello World"));
    }

}