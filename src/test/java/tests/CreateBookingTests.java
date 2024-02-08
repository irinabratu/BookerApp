package tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.Booking;

import java.util.HashMap;

import static api.BookingApi.*;
import static api.HeaderApi.headerBuilder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CreateBookingTests {


    /**
     * create booking - positive scenarios
     **/

    @Test
    public void createBookingAllValid() {
        Booking booking = bookingBuilder("irina","bratu",23,true,"2013-02-02","2013-02-04", "needs");
        Response response = createBooking(booking,headerBuilder("valid","valid","missing",null));
        assertThat(response.getStatusCode(), equalTo(200));
    }

    /**
     * create booking - negative scenarios - headers
     **/

    @Test
    public void createBookingNoContentTypeInvalidAccept() {
        Booking booking = bookingBuilder("irina","bratu",23,true,"2013-02-02","2013-02-04", "needs");
        Response response = createBooking(booking, headerBuilder("missing","invalid","missing",null));
        assertThat(response.getStatusCode(), equalTo(500));
    }

    @Test
    public void createBookingInvalidContentTypeNoAccept() {
        Response response = createBooking(validCreateBookingBody, headerBuilder("invalid","missing","missing",null));
        assertThat(response.getStatusCode(), equalTo(400));
    }

    @Test
    public void createBookingNoContentTypeValidAccept() {
        Booking booking = bookingBuilder("irina","bratu",23,true,"2013-02-02","2013-02-04", "needs");
        Response response = createBooking(booking, headerBuilder("missing","valid","missing",null));
        assertThat(response.getStatusCode(), equalTo(500));
    }

    @Test
    public void createBookingValidContentTypeNoAccept() {
        Booking booking = bookingBuilder("irina","bratu",23,true,"2013-02-02","2013-02-04", "needs");
        Response response = createBooking(booking, headerBuilder("valid","missing","missing",null));
        assertThat(response.getStatusCode(), equalTo(200));
    }

    @Test
    public void createBookingNoContentTypeNoAccept() {
        Booking booking = bookingBuilder("irina","bratu",23,true,"2013-02-02","2013-02-04", "needs");
        Response response = createBooking(booking);
        assertThat(response.getStatusCode(), equalTo(500));
    }

    @Test
    public void createBookingInvalidContentTypeValidAccept() {
        Response response = createBooking(validCreateBookingBody,headerBuilder("invalid","valid","missing",null));
        assertThat(response.getStatusCode(), equalTo(400));
    }

    @Test
    public void createBookingValidContentTypeInvalidAccept() {
        Booking booking = bookingBuilder("irina","bratu",23,true,"2013-02-02","2013-02-04", "needs");
        Response response = createBooking(booking,headerBuilder("valid","invalid","missing",null));
        assertThat(response.getStatusCode(), equalTo(200));
    }

    @Test
    public void createBookingInvalidContentTypeInvalidAccept() {
        Response response = createBooking(validCreateBookingBody,headerBuilder("invalid","invalid","missing",null));
        assertThat(response.getStatusCode(), equalTo(400));
    }

    /**
     * create booking - negative scenarios - missing body parts
     **/

    @Test
    public void createBookingMissingFirstName() {
        Booking bookingMissingFirstName = bookingBuilder(null,"bratu",23,true,"2013-02-02","2013-02-04", "needs");
        Response response = createBooking(bookingMissingFirstName,headerBuilder("valid","valid","missing",null));
        assertThat(response.getStatusCode(), equalTo(500));
    }

    @Test
    public void createBookingMissingLastName() {
        Booking bookingMissingLastName = bookingBuilder("irina",null,23,true,"2013-02-02","2013-02-04", "needs");
        Response response = createBooking(bookingMissingLastName,headerBuilder("valid","valid","missing",null));
        assertThat(response.getStatusCode(), equalTo(500));
    }

    @Test
    public void createBookingMissingTotalPrice() {
        Booking booking = bookingBuilder("irina","bratu",null,true,"2013-02-02","2013-02-04", "needs");
        Response response = createBooking(booking,headerBuilder("valid","valid","missing",null));
        assertThat(response.getStatusCode(), equalTo(500));
    }

    @Test
    public void createBookingMissingDepositPaid() {
        Booking booking = bookingBuilder("irina","bratu",23,null,"2013-02-02","2013-02-04", "needs");
        Response response = createBooking(booking,headerBuilder("valid","valid","missing",null));
        assertThat(response.getStatusCode(), equalTo(500));
    }

    @Test
    public void createBookingMissingCheckin() {
        Booking booking = bookingBuilder("irina","bratu",23,true,null,"2013-02-04", "needs");
        Response response = createBooking(booking,headerBuilder("valid","valid","missing",null));
        assertThat(response.getStatusCode(), equalTo(500));
    }

    @Test
    public void createBookingMissingCheckout() {
        Booking booking = bookingBuilder("irina","bratu",23,true,"2013-02-02",null, "needs");
        Response response = createBooking(booking,headerBuilder("valid","valid","missing",null));
        assertThat(response.getStatusCode(), equalTo(500));
    }

    @Test
    public void createBookingMissingAdditionalNeeds() {
        Booking booking = bookingBuilder("irina","bratu",23,true,"2013-02-02","2013-02-04", null);
        Response response = createBooking(booking,headerBuilder("valid","valid","missing",null));
        assertThat(response.getStatusCode(), equalTo(200));
    }

    @Test
    public void createBookingMissingAll() {
        Booking booking = bookingBuilder(null,null,null,null,null,null, null);
        Response response = createBooking(booking,headerBuilder("valid","valid","missing",null));
        assertThat(response.getStatusCode(), equalTo(500));
    }

    /**
     * create booking - negative scenarios - invalid body parts
     **/

    @Test
    public void createBookingInvalidFirstName() {
        HashMap<String, Object> invalidBody = generateBodyMapForBooking();
        invalidBody.put("test",invalidBody.remove("firstname"));
        Response response = createBooking(invalidBody,headerBuilder("valid","valid","missing",null));
        assertThat(response.getStatusCode(), equalTo(500));
    }

    @Test
    public void createBookingInvalidLastName() {
        HashMap<String, Object> invalidBody = generateBodyMapForBooking();
        invalidBody.put("test",invalidBody.remove("lastname"));
        Response response = createBooking(invalidBody,headerBuilder("valid","valid","missing",null));
        assertThat(response.getStatusCode(), equalTo(500));
    }

    @Test
    public void createBookingInvalidTotalPrice() {
        HashMap<String, Object> invalidBody = generateBodyMapForBooking();
        invalidBody.put("test",invalidBody.remove("totalprice"));
        Response response = createBooking(invalidBody,headerBuilder("valid","valid","missing",null));
        assertThat(response.getStatusCode(), equalTo(500));
    }

    @Test
    public void createBookingInvalidDepositPaid() {
        HashMap<String, Object> invalidBody = generateBodyMapForBooking();
        invalidBody.put("test",invalidBody.remove("depositpaid"));
        Response response = createBooking(invalidBody,headerBuilder("valid","valid","missing",null));
        assertThat(response.getStatusCode(), equalTo(500));
    }

    @Test
    public void createBookingInvalidCheckin() {
        HashMap<String, Object> invalidBody = generateBodyMapForBooking();
        HashMap<String, Object> invalidBookingDates = (HashMap<String, Object>) invalidBody.get("bookingdates");
        invalidBookingDates.put("test",invalidBookingDates.remove("checkin"));
        Response response = createBooking(invalidBody,headerBuilder("valid","valid","missing",null));
        assertThat(response.getStatusCode(), equalTo(500));
    }

    @Test
    public void createBookingInvalidCheckout() {
        HashMap<String, Object> invalidBody = generateBodyMapForBooking();
        HashMap<String, Object> invalidBookingDates = (HashMap<String, Object>) invalidBody.get("bookingdates");
        invalidBookingDates.put("test",invalidBookingDates.remove("checkin"));
        Response response = createBooking(invalidBody,headerBuilder("valid","valid","missing",null));
        assertThat(response.getStatusCode(), equalTo(500));
    }

    @Test
    public void createBookingInvalidAdditionalNeeds() {
        HashMap<String, Object> invalidBody = generateBodyMapForBooking();
        invalidBody.put("test",invalidBody.remove("additionalneeds"));
        Response response = createBooking(invalidBody,headerBuilder("valid","valid","missing",null));
        assertThat(response.getStatusCode(), equalTo(200));
    }

    @Test
    public void createBookingInvalidAll() {
        HashMap<String, Object> invalidBody = generateBodyMapForBooking();
        invalidBody.put("test1",invalidBody.remove("firstname"));
        invalidBody.put("test2",invalidBody.remove("lastname"));
        invalidBody.put("test3",invalidBody.remove("totalprice"));
        invalidBody.put("test4",invalidBody.remove("depositpaid"));
        invalidBody.put("test5",invalidBody.remove("additionalneeds"));
        HashMap<String, Object> invalidBookingDates = (HashMap<String, Object>) invalidBody.get("bookingdates");
        invalidBookingDates.put("test6",invalidBookingDates.remove("checkin"));
        invalidBookingDates.put("test7",invalidBookingDates.remove("checkout"));
        Response response = createBooking(invalidBody,headerBuilder("valid","valid","missing",null));
        assertThat(response.getStatusCode(), equalTo(500));
    }
}
