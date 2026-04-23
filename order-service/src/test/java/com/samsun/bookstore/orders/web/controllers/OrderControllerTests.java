package com.samsun.bookstore.orders.web.controllers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

import com.samsun.bookstore.orders.AbstractIT;
import com.samsun.bookstore.orders.testdata.TestDataFactory;
import io.restassured.http.ContentType;
import java.math.BigDecimal;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class OrderControllerTests extends AbstractIT {

    @Nested
    class CreateOrderTests {
        @Test
        void shouldCreateOrderSuccessfully() {
            mockGetProductByCode("P127", "Life of Pi", new BigDecimal("16.75"));
            var payload =
                    """
                            {
                                "customer": {
                                    "name": "Daniel",
                                    "email": "daniboy@gmail.com",
                                    "phone": "2014562563"
                                },
                                "deliveryAddress":{
                                    "addressLine1": "235 Pacific",
                                    "addressLine2": "",
                                    "city": "San Jose",
                                    "state": "California",
                                    "zipCode": "96312",
                                    "country": "USA"
                                },
                                "items": [
                                    {
                                        "code": "P127",
                                        "name": "Life of Pi",
                                        "price": 16.75,
                                        "quantity": 1
                                    }
                                ]
                            }
                    """;
            given().contentType(ContentType.JSON)
                    .body(payload)
                    .when()
                    .post("/api/orders")
                    .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .body("orderNumber", notNullValue());
        }

        @Test
        void shouldReturnBadRequestWhenMandatoryDataIsMissing() {
            var payload = TestDataFactory.createOrderRequestWithInvalidCustomer();
            given().contentType(ContentType.JSON)
                    .body(payload)
                    .when()
                    .post("/api/orders")
                    .then()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }
    }
}
