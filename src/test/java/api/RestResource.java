package api;

import io.restassured.response.Response;

import java.util.HashMap;

import static api.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestResource {

    public static Response post(String path, Object body){
        return given(getRequestSpec()).
                body(body).
                when().
                post(path).
                then().spec(getResponseSpec()).extract().response();
    }

    public static Response post(String path, Object body, HashMap<String, String> headersMap){
        return given(getRequestSpec()).
                headers(headersMap).
                body(body).
                when().
                post(path).
                then().spec(getResponseSpec()).extract().response();
    }

    public static Response put(String path, Object body){
        return given(getRequestSpec()).
                body(body).
                when().
                put(path).
                then().spec(getResponseSpec()).extract().response();
    }

    public static Response put(String path, Object body, HashMap<String, String> headersMap){
        return given(getRequestSpec()).
                headers(headersMap).
                body(body).
                when().
                put(path).
                then().spec(getResponseSpec()).extract().response();
    }
}
