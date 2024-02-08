package api;

import io.restassured.response.Response;

public class Helper {

    private static String pingPath = "/ping";
    public static Response tryPingBooker(){
        return RestResource.get("/ping");
    }
}
