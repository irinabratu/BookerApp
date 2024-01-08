package api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import pojo.Booking;
import pojo.BookingDates;

import java.util.HashMap;

import static api.RestResource.post;
import static api.RestResource.put;

public class BookingApi {
    public static String validCreateBookingBody = "{\"firstname\":\"irina\",\"lastname\":\"bratu\",\"totalprice\":23,\"depositpaid\":true,\"bookingdates\":{\"checkin\":\"2013-02-02\",\"checkout\":\"2013-02-04\"},\"additionalneeds\":\"needs\"}";
    private static String path = "/booking";
    public static Response createBooking(Object body) {
        return post(path, body);
    }
    public static Response createBooking(Object body, HashMap<String, String> headersMap) {
        return post(path, body, headersMap);
    }
    public static Response updateBooking(Object body, HashMap<String, String> headersMap, String bookingId) {
        return put(path + "/" + bookingId, body, headersMap);
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
    public static HashMap<String, Object> generateInvalidBodyForCreateBooking() {
        Booking bookingAllValid = bookingBuilder("irina", "bratu", 23, true, "2013-02-02", "2013-02-04", "needs");
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, Object> invalidBodyForCreateBooking = mapper.convertValue(bookingAllValid, new TypeReference<HashMap<String, Object>>() {
        });
        System.out.println(invalidBodyForCreateBooking.toString());
        return invalidBodyForCreateBooking;
    }
}
