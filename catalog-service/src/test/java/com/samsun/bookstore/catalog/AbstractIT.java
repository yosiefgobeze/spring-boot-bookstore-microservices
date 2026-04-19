package com.samsun.bookstore.catalog;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public abstract class AbstractIT {
    @LocalServerPort // will bind a random port to the variable below
    int port;

    @BeforeEach
    void setUp() {
        //        RestAssured.baseURI = "http://localhost:" + port;
        RestAssured.port = port; // will take http://localhost:port by default
    }
}
