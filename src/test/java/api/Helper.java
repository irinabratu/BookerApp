package api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pojo.Booking;

import java.util.HashMap;

import static api.BookingApi.bookingBuilder;

public class Helper {

    public static HashMap<String, String> getValidContentTypeHeaders() {
        HashMap<String, String> validContentTypeHeaders = new HashMap<>();
        validContentTypeHeaders.put("Content-Type", "application/json");
        return validContentTypeHeaders;
    }

    public static HashMap<String, String> getInvalidContentTypeHeaders() {
        HashMap<String, String> invalidContentTypeHeaders = new HashMap<>();
        invalidContentTypeHeaders.put("Content-Type", "application/xml");
        return invalidContentTypeHeaders;
    }

    public static HashMap<String, String> getValidAcceptHeaders() {
        HashMap<String, String> validContentTypeHeaders = new HashMap<>();
        validContentTypeHeaders.put("Accept", "application/json");
        return validContentTypeHeaders;
    }

    public static HashMap<String, String> getInvalidAcceptHeaders() {
        HashMap<String, String> invalidContentTypeHeaders = new HashMap<>();
        invalidContentTypeHeaders.put("Accept", "application/xml");
        return invalidContentTypeHeaders;
    }

    public static HashMap<String, String> getValidContentTypeValidAcceptHeaders() {
        HashMap<String, String> validContentTypeValidAcceptHeaders = new HashMap<>();
        validContentTypeValidAcceptHeaders.put("Content-Type", "application/json");
        validContentTypeValidAcceptHeaders.put("Accept", "application/json");
        return validContentTypeValidAcceptHeaders;
    }

    public static HashMap<String, String> getInvalidContentTypeInvalidAcceptHeaders() {
        HashMap<String, String> invalidContentTypeInvalidAcceptHeaders = new HashMap<>();
        invalidContentTypeInvalidAcceptHeaders.put("Content-Type", "application/xml");
        invalidContentTypeInvalidAcceptHeaders.put("Accept", "application/xml");
        return invalidContentTypeInvalidAcceptHeaders;
    }

    public static HashMap<String, String> getInvalidContentTypeValidAcceptHeaders() {
        HashMap<String, String> invalidContentTypeValidAcceptHeaders = new HashMap<>();
        invalidContentTypeValidAcceptHeaders.put("Content-Type", "application/xml");
        invalidContentTypeValidAcceptHeaders.put("Accept", "application/json");
        return invalidContentTypeValidAcceptHeaders;
    }

    public static HashMap<String, String> getValidContentTypeInvalidAcceptHeaders() {
        HashMap<String, String> validContentTypeInvalidAcceptHeaders = new HashMap<>();
        validContentTypeInvalidAcceptHeaders.put("Content-Type", "application/json");
        validContentTypeInvalidAcceptHeaders.put("Accept", "application/xml");
        return validContentTypeInvalidAcceptHeaders;
    }

    public static HashMap<String, String> getValidHeadersWithToken() {
        HashMap<String, String> validContentTypeValidAcceptHeaders = new HashMap<>();
        validContentTypeValidAcceptHeaders.put("Content-Type", "application/json");
        validContentTypeValidAcceptHeaders.put("Accept", "application/json");
        validContentTypeValidAcceptHeaders.put("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=");
        return validContentTypeValidAcceptHeaders;
    }
}
