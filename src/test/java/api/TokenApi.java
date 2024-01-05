package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static api.RestResource.post;

public class TokenApi {

    private static String path = "/auth";
    public static String validTokenRequestBody = "{\"username\":\"admin\",\"password\":\"password123\"}";

    public static Response generateToken(Object credentials) {
        return post(path, credentials);
    }
    public static Response generateToken(Object credentials, HashMap<String, String> headersMap) {
        return post(path, credentials, headersMap);
    }

    public static String retrieveToken(Object credentials,HashMap<String, String> headersMap){
        return post(path, credentials, headersMap).path("reason").toString();
    }
}
