package com.SauceDemo.Swanglabs.API;

import Utilis.UserFakeGeneration;
import com.SauceDemo.Swanglabs.Config.ENDPOINT;
import io.restassured.http.Cookie;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class RegisterAPI {
    private String accessToken;
    private String firstName;
    private String UserID;
    private List<Cookie> restassuredCookies;

    public void Register() {

        Response respone =
                given()
                        .baseUri("https://qacart-todo.herokuapp.com")
                        .header("Content-Type", "application/json")
                        .body(UserFakeGeneration.UserDetails())
                        .log().all()
                .when()
                        .post(ENDPOINT.API_REGISTER_ENDPOINT)
                .then()
                        .log().all()
                        .extract().response();


    if (respone.statusCode()!=201)
        throw  new RuntimeException("Error in Registertion Process");


    restassuredCookies=respone.detailedCookies().asList();
    accessToken = respone.path("access_token");
    firstName = respone.path("firstName");
    UserID = respone.path("userID");
    }

    public String getAccessToken() {
        return accessToken;
    }

    public List<Cookie> getrestassuredCookies() {
        return restassuredCookies;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getUserID() {
        return UserID;
    }
}