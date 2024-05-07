package com.indra.preciosecommerce.infraestructura.controllers;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class PriceControllerIntegrationTest {

    private static final String BASE_URL = "http://localhost:8080/prices";

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    void getsAPriceAt10AMOnDay14() {
        getsAPrice("2020-06-14 10:00", "35.5");
    }

    @Test
    void getsAPriceAt16PMOnDay14() {
        getsAPrice("2020-06-14 16:00", "25.45");
    }

    @Test
    void getsAPriceAt21PMOnDay14() { getsAPrice("2020-06-14 21:00", "35.5"); }
    @Test
    void getsAPriceAt10AMOnDay15() {
        getsAPrice("2020-06-15 10:00", "30.5");
    }

    @Test
    void getsAPriceAt21PMOnDay16() {
        getsAPrice("2020-06-16 21:00", "38.95");
    }

    private void getsAPrice(String date, String expectedPrice) {
        Response response = RestAssured.given()
                .param("date", date)
                .param("productId", 35455)
                .param("brandId", 1)
                .when()
                .get();

        response.then().statusCode(200);
        response.then().contentType(ContentType.JSON);

        String actualPrice = response.getBody().jsonPath().getString("price");
        assertThat(actualPrice).isEqualTo(expectedPrice);
    }
}