package api;

import io.restassured.response.Response;

import java.util.HashMap;

import static api.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestResource {

    public static Response get(String path) {
        return given(getRequestSpec()).
                when().
                get(path).
                then().spec(getResponseSpec()).extract().response();
    }

    public static Response get(String path, HashMap<String, String> headersMap) {
        return given(getRequestSpec()).
                headers(headersMap).
                when().
                get(path).
                then().spec(getResponseSpec()).extract().response();
    }

    public static Response getWithParams(String path, HashMap<String, Object> paramsMap) {
        return given(getRequestSpec()).
                params(paramsMap).
                when().
                get(path).
                then().spec(getResponseSpec()).extract().response();
    }

    public static Response get(String path, String id) {
        return given(getRequestSpec()).
                when().
                get(path + "/" + id).
                then().spec(getResponseSpec()).extract().response();
    }

    public static Response get(String path, String id, HashMap<String, String> headersMap) {
        return given(getRequestSpec()).
                headers(headersMap).
                when().
                get(path + "/" + id).
                then().spec(getResponseSpec()).extract().response();
    }

    public static Response post(String path, Object body) {
        return given(getRequestSpec()).
                body(body).
                when().
                post(path).
                then().spec(getResponseSpec()).extract().response();
    }

    public static Response post(String path, Object body, HashMap<String, String> headersMap) {
        return given(getRequestSpec()).
                headers(headersMap).
                body(body).
                when().
                post(path).
                then().spec(getResponseSpec()).extract().response();
    }

    public static Response put(String path, Object body) {
        return given(getRequestSpec()).
                body(body).
                when().
                put(path).
                then().spec(getResponseSpec()).extract().response();
    }

    public static Response put(String path, Object body, HashMap<String, String> headersMap) {
        return given(getRequestSpec()).
                headers(headersMap).
                body(body).
                when().
                put(path).
                then().spec(getResponseSpec()).extract().response();
    }


    public static Response patch(String path, Object body) {
        return given(getRequestSpec()).
                body(body).
                when().
                patch(path).
                then().spec(getResponseSpec()).extract().response();
    }

    public static Response patch(String path, Object body, HashMap<String, String> headersMap) {
        return given(getRequestSpec()).
                headers(headersMap).
                body(body).
                when().
                patch(path).
                then().spec(getResponseSpec()).extract().response();
    }

    public static Response delete(String path, String id) {
        return given(getRequestSpec()).
                when().
                delete(path + "/" + id).
                then().spec(getResponseSpec()).extract().response();
    }

    public static Response delete(String path, String id, HashMap<String, String> headersMap) {
        return given(getRequestSpec()).
                headers(headersMap).
                when().
                delete(path + "/" + id).
                then().spec(getResponseSpec()).extract().response();
    }
}
