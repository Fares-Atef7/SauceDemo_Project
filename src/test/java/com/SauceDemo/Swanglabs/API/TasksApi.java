package com.SauceDemo.Swanglabs.API;


import Objects.Task;
import com.SauceDemo.Swanglabs.Config.ENDPOINT;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TasksApi {

    public void AddTask(String token){
        Task task=new Task(false,"fares");
       Response reponse= given()
                .baseUri("https://qacart-todo.herokuapp.com")
                .header("Content-Type", "application/json")
                .body(task)
                .auth().oauth2(token)
        .when()
                .post(ENDPOINT.TASKAPI_ENDPOINT)
        .then()
                .log().all().extract().response();

       if (reponse.statusCode()!=201){
           throw new RuntimeException("Error in the Response");
       }
    }
}
