package api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojo.Booking;
import pojo.BookingDates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static api.RestResource.*;
import static io.restassured.RestAssured.get;

public class BookingApi {
    public static String validCreateBookingBody = "{\"firstname\":\"irina\",\"lastname\":\"bratu\",\"totalprice\":23,\"depositpaid\":true,\"bookingdates\":{\"checkin\":\"2013-02-02\",\"checkout\":\"2013-02-04\"},\"additionalneeds\":\"needs\"}";
    public static String validUpdateBookingBody = "{\"firstname\":\"test\",\"lastname\":\"bratu\",\"totalprice\":23,\"depositpaid\":true,\"bookingdates\":{\"checkin\":\"2013-02-02\",\"checkout\":\"2013-02-04\"},\"additionalneeds\":\"needs\"}";
    public static String validPartialUpdateBookingBody = "{\"firstname\":\"test\",\"lastname\":\"bratu\"}";

    public static String invalidUpdateBookingBody = "{\"firstname\":\"test\",\"lastname\":\"bratu\",\"totalprice\":23,\"depositpaid\":true,\"bookingdates\":{\"checkin\":\"2013-02-02\",\"checkout\":\"2013-02-04\"},\"additionalneeds\":\"needs\"}";
    private static String path = "/booking";

    public static Response createBooking(Object body) {
        return post(path, body);
    }

    public static Response createBooking(Object body, HashMap<String, String> headersMap) {
        return post(path, body, headersMap);
    }
    public static Response getBooking(String id) {
        return RestResource.get(path, id);
    }
    public static Response getBooking(String id, HashMap<String, String> headersMap) {
        return RestResource.get(path, id, headersMap);
    }

    public static Response getBookings() {
        return RestResource.get(path);
    }
    public static Response getBookings(HashMap<String, Object> paramsMap) {
        return RestResource.getWithParams(path, paramsMap);
    }

    public static Response updateBooking(Object body, HashMap<String, String> headersMap, String bookingId) {
        if (bookingId == null) {
            return put(path, body, headersMap);
        } else {
            return put(path + "/" + bookingId, body, headersMap);
        }
    }

    public static Response partiallyUpdateBooking(Object body, HashMap<String, String> headersMap, String bookingId) {
        if (bookingId == null) {
            return patch(path, body, headersMap);
        } else {
            return patch(path + "/" + bookingId, body, headersMap);
        }
    }

    public static Response deleteBooking(String id) {
        return RestResource.delete(path, id);
    }
    public static Response deleteBooking(String id, HashMap<String, String> headersMap) {
        return RestResource.delete(path, id, headersMap);
    }

    public static ArrayList<String> retrieveBookingIds() {
        ArrayList<String> bookingIdList = new ArrayList<>();

        JsonPath js = new JsonPath(RestResource.get(path).asString());
        int countI = js.getInt("bookingid" + ".size()");
        for (int i = 0; i<countI; i++) {
            bookingIdList.add(js.getString("["+i+"].bookingid"));
        }
        return bookingIdList;
    }

    private static BookingDates bookingDatesBuilder(String checkin, String checkout) {
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin(checkin);
        bookingDates.setCheckout(checkout);
        return bookingDates;
    }

    public static Booking bookingBuilder(String firstName, String lastName, Integer totalPrice, Boolean depositPaid, String checkin, String checkout, String additionalNeeds) {
        Booking booking = new Booking();
        booking.setBookingdates(bookingDatesBuilder(checkin, checkout));
        booking.setFirstname(firstName);
        booking.setLastname(lastName);
        booking.setDepositpaid(depositPaid);
        booking.setTotalprice(totalPrice);
        booking.setAdditionalneeds(additionalNeeds);
        return booking;
    }

    public static HashMap<String, Object> generateBodyMapForBooking() {
        Booking bookingAllValid = bookingBuilder("irina", "bratu", 23, true, "2013-02-02", "2013-02-04", "needs");
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, Object> bodyMapForCreateBooking = mapper.convertValue(bookingAllValid, new TypeReference<HashMap<String, Object>>() {
        });
        return bodyMapForCreateBooking;
    }


    public static HashMap<String, Object> paramBuilder(Object firstName, Object lastName, Object checkin, Object checkout) {
        HashMap<String, Object> paramBuilder = new HashMap<>();
        if(firstName != null) {paramBuilder.put("firstname", firstName);}
        if(lastName != null) {paramBuilder.put("lastname", lastName);}
        if(checkin != null) {paramBuilder.put("checkin",checkin);}
        if(checkout != null) {paramBuilder.put("checkout",checkout);}
        return paramBuilder;
    }
}
